/* ______________________________________________________ */
/**
 * Fichier : FenetreListeJoueur.java
 *
 * Créé le 31 janv. 2014 à 11:58:48
 *
 * Auteur : NUNES Stephen
 */
package mvcListeJoueursReseau;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/** Fenetre contenant la liste des joueurs ainsi que les événements passé sur le serveur
 */
public class PanneauListeJoueurs extends JPanel implements IConstantesGraphiques, Observer
{

	/** Numéro de sérialisation */
	private static final long serialVersionUID = 6489523023099903271L;
	
	/** Liste des joueurs connectés au serveur */
	private JList<String> listeJoueur;
	
	/** Instance unique de la vue */
	private static PanneauListeJoueurs vue = null;
	
	/** Le modèle spécifique à ce panneau */
	private ModelePanneauListeJoueurs monModelePanneauListeJoueur = null;
	
	/** Bouton paramétrer du panneau */
	private JButton boutonParametrer = null;
	
	/*_______________________________________________________*/
	/** Permet d'obtenir la vue.
	 * @return La vue.
	 */
	public static PanneauListeJoueurs getVue()
	{
		if (vue == null)
			vue = new PanneauListeJoueurs() ;
		return vue ;
	}

	/* ______________________________________________________ */
	/** Construction de la fenêtre liste joueur
	 */
	public PanneauListeJoueurs()
	{
		super();
		setLayout(new BorderLayout());
		
		ControleurListeJoueursReseau ctrl = new ControleurListeJoueursReseau(ModelePanneau.getInstance(), ModelePanneauListeJoueurs.getInstance());
		monModelePanneauListeJoueur = ModelePanneauListeJoueurs.getInstance();
		monModelePanneauListeJoueur.addObserver(this);
		add(creerPanneauPrincipal(), BorderLayout.CENTER);
		add(creerPanneauBouton(ctrl), BorderLayout.SOUTH);
	}

	/* ______________________________________________________ */
	/** Crée le panneau contenant les boutons d'intéraction
	 * @param ctrl Contrôleur de la liste des joueurs en réseau
	 * @return panneau avec les boutons
	 */
	private JPanel creerPanneauBouton(ControleurListeJoueursReseau ctrl)
	{
		JPanel panneauBouton = new JPanel(new FlowLayout());
		creerBoutonParametrer(panneauBouton, ctrl, !ModelePanneauListeJoueurs.getInstance().isConfigurationSetted());
		creerBouton("Retour", panneauBouton, ctrl);
		return panneauBouton;
	}

	/* ______________________________________________________ */
	/**
	 * Créé un bouton
	 * @param label : intitule du bouton
	 * @param panneauRecepteur : le panneau qui recoit le bouton
	 * @param ctrl Contrôleur de la liste des joueurs en réseau
	 */
	private void creerBouton(String label, JPanel panneauRecepteur, ControleurListeJoueursReseau ctrl)
	{
		JButton monBouton = new JButton(label);
		monBouton.addActionListener(ctrl);
		monBouton.setFont(POLICE_BOUTON);
		monBouton.setPreferredSize(new Dimension(200,30));
		if (label.equals(BTN_RETOUR))
			monBouton.setPreferredSize(new Dimension(100,30));
		panneauRecepteur.add(monBouton);
	}
	
	/* ______________________________________________________ */
	/**
	 * Créé un bouton paramétrer un match en réseau
	 * @param panneauRecepteur le panneau qui recoit le bouton
	 * @param ctrl Contrôleur de la liste des joueurs en réseau
	 * @param actif Si le bouton est activé ou désactivé
	 */
	private void creerBoutonParametrer(JPanel panneauRecepteur, ControleurListeJoueursReseau ctrl, boolean actif)
	{
		boutonParametrer = new JButton(BTN_PARAMETRER_MATCH);
		boutonParametrer.addActionListener(ctrl);
		boutonParametrer.setFont(POLICE_BOUTON);
		boutonParametrer.setPreferredSize(new Dimension(200,30));
		boutonParametrer.setEnabled(actif);
		panneauRecepteur.add(boutonParametrer);
	}
	
	
	
	/* ______________________________________________________ */
	/** Crée le panneau principal
	 * @return le panneau et ses composants
	 */
	private JPanel creerPanneauPrincipal()
	{
		JPanel panneauPrincipal = new JPanel(new BorderLayout());
		panneauPrincipal.add(creerPanneauListeJoueurs(), BorderLayout.CENTER);
		return panneauPrincipal;
	}

	/* ______________________________________________________ */
	/** Crée le panneau contenant la liste des joueurs
	 * @return le panneau ayant la liste
	 */
	private JPanel creerPanneauListeJoueurs()
	{
		JPanel panneauListeJoueur = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Liste des joueurs connectés au serveur (Veuillez attentre que tous les joueurs attentu se connecter pour paramétrer la partie)");
		listeJoueur = new JList<String>(ModeleListeJoueurs.getInstance());
		JScrollPane barresDefilement = new JScrollPane(listeJoueur);
		
		panneauListeJoueur.add(label, BorderLayout.NORTH);
		panneauListeJoueur.add(barresDefilement, BorderLayout.CENTER);
		
		return panneauListeJoueur;
	}

	/* ______________________________________________________ */
	/** Implémentation de la méthode update de Observer/Observable
	 * @param o
	 * @param evenementNotifie L'événement notifié
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object evenementNotifie)
	{
		if (evenementNotifie.toString().equals("desactiveBoutonParametrer"))
		{
			boutonParametrer.setEnabled(false);
			updateUI();
		}
	}
}

/*__________________________________________________________*/
/* Fin du fichier FenetreListeJoueur.java. */
/*__________________________________________________________*/