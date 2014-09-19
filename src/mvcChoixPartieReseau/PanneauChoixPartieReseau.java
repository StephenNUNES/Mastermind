/* ______________________________________________________ */
/**
 * Fichier : FenetreChoixPartieReseau.java
 *
 * Créé le 27 janv. 2014 à 08:50:53
 *
 * Auteur : NUNES Stephen
 */
package mvcChoixPartieReseau;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/** Fenêtre permettant de créer une partie en réseau
 */
public class PanneauChoixPartieReseau extends JPanel implements IConstantesGraphiques, Observer
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = 5759622921545482581L;
	
	/** Instance unique de la vue */
	private static PanneauChoixPartieReseau vue = null;
	
	
	/*_______________________________________________________*/
	/** Permet d'obtenir la vue.
	 * @return La vue.
	 */
	public static PanneauChoixPartieReseau getVue()
	{
		if (vue == null)
			vue = new PanneauChoixPartieReseau() ;
		return vue ;
	}
	/* ______________________________________________________ */
	/** Constructeur de la fenêtre
	 */
	public PanneauChoixPartieReseau()
	{
		super();
		definirStyleBoiteDeDialogue();
		setLayout(new BorderLayout());
		ControleurChoixPartieReseau ctrl = new ControleurChoixPartieReseau(ModelePanneau.getInstance(), ModeleChoixPartieReseau.getInstance());
		
		add(creerPanneauTitre(), BorderLayout.CENTER);
		add(creerPanneauBoutons(ctrl), BorderLayout.SOUTH);
	}

	/* ______________________________________________________ */
	/** Crée le panneau contenant les boutons 
	 * @param ctrl Controleur du choix de la partie réseau
	 * @return le panneau avec les boutons adéquats
	 */
	private JPanel creerPanneauBoutons(ControleurChoixPartieReseau ctrl)
	{
		JPanel panneauBouton = new JPanel(new GridLayout(8, 1));
		panneauBouton.add(creerPanneauBoutonCreerServeur(ctrl));
		panneauBouton.add(creerPanneauBoutonRejoindreServeur(ctrl));
		panneauBouton.add(creerPanneauBoutonRetour(ctrl));
		return panneauBouton;
	}

	/* ______________________________________________________ */
	/** Crée le panneau contenant le bouton permettant de rejoindre un serveur
	 * @param ctrl Controleur du choix de la partie en réseau
	 * @return le panneau et son bouton
	 */
	private JPanel creerPanneauBoutonRejoindreServeur(ControleurChoixPartieReseau ctrl)
	{
		JPanel panneauBoutonRejoindreServeur = new JPanel(new FlowLayout());
		creerBouton("Rejoindre un serveur de jeu", panneauBoutonRejoindreServeur,ctrl);
		return panneauBoutonRejoindreServeur;
	}

	/* ______________________________________________________ */
	/** Crée un panneau contenant le bouton retour
	 * @param ctrl Controleur du choix de la partie en réseau
	 * @return le panneau et son bouton retour
	 */
	private JPanel creerPanneauBoutonRetour(ControleurChoixPartieReseau ctrl)
	{
		JPanel panneauBoutonRetour = new JPanel(new FlowLayout());
		creerBouton("Retour", panneauBoutonRetour, ctrl);
		return panneauBoutonRetour;
	}

	/* ______________________________________________________ */
	/** Crée le panneau contenant le bouton créer serveur
	 * @param ctrl Contrôleur du choix de la partie réseau
	 * @return panneau contenant le bouton créer serveur
	 */
	private JPanel creerPanneauBoutonCreerServeur(ControleurChoixPartieReseau ctrl)
	{
		JPanel panneauBoutonCreerServeur = new JPanel(new FlowLayout());
		creerBouton("Créer un serveur de jeu", panneauBoutonCreerServeur, ctrl);
		return panneauBoutonCreerServeur;
	}
	
	/* ______________________________________________________ */
	/**
	 * Créé un bouton
	 * @param label : intitule du bouton
	 * @param panneauRecepteur : le panneau qui recoit le bouton
	 * @param ctrl Contrôleur du choix de la partie réseau
	 */
	private void creerBouton(String label, JPanel panneauRecepteur, ControleurChoixPartieReseau ctrl)
	{
		JButton monBouton = new JButton(label);
		monBouton.addActionListener(ctrl);
		monBouton.setFont(POLICE_BOUTON);
		monBouton.setPreferredSize(new Dimension(250,30));
		if (label.equals(BTN_RETOUR))
			monBouton.setPreferredSize(new Dimension(100,30));
		panneauRecepteur.add(monBouton);
	}


	/* ______________________________________________________ */
	/** Crée le panneau contenant du titre
	 * @return Le panneau et ses composants
	 */
	private JLabel creerPanneauTitre()
	{
		JLabel titre = new JLabel("Choisissez de créer ou rejoindre un serveur :");
		titre.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		titre.setFont(POLICE_SS_TITRE);
		return titre;
	}
	/* ______________________________________________________ */
	/** Définition de la méthode de mise à jour appelée par l'observé
	 * @param o l'objet observé
	 * @param choixRecu l'argument de mise à jour
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object choixRecu)
	{}
	
	/* ______________________________________________________ */
	/** Défini le style de la boîte de dialogue
	 */
	private void definirStyleBoiteDeDialogue()
	{
		UIManager.put("OptionPane.font", POLICE_TEXTE);
		UIManager.put("OptionPane.messageFont", POLICE_TEXTE);
		UIManager.put("OptionPane.buttonFont", POLICE_BOUTON_CLASSEMENT);
	}
}

/*__________________________________________________________*/
/* Fin du fichier FenetreChoixPartieReseau.java. */
/*__________________________________________________________*/