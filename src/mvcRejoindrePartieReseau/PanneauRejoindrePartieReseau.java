/* ______________________________________________________ */
/**
 * Fichier : FenetreCreerPartieReseau.java
 *
 * Créé le 27 janv. 2014 à 10:43:00
 *
 * Auteur : NUNES Stephen
 */
package mvcRejoindrePartieReseau;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/** Fenêtre permettant de créer une partie en réseau avec son nom
 */
public class PanneauRejoindrePartieReseau extends JPanel implements IConstantesGraphiques
{

	/** Numéro de sérialisation */
	private static final long serialVersionUID = -578662948424517277L;

	/** Zone de texte contenant le nom de la partie à saisir */
	private JTextField zoneDeTexteNomServeur = null;

	/** Zone de texte où l'on saisie le nombre de joueurs maximum de la partie */
	private JTextField zoneDeTexteNomJoueur;
	
	/** Instance unique de la vue */
	private static PanneauRejoindrePartieReseau vue = null;
	
	
	/*_______________________________________________________*/
	/** Permet d'obtenir la vue.
	 * @return La vue.
	 */
	public static PanneauRejoindrePartieReseau getVue()
	{
		if (vue == null)
			vue = new PanneauRejoindrePartieReseau() ;
		return vue ;
	}

	/* ______________________________________________________ */
	/** Constructeur de la fenêtre de la création d'une partie en réseau
	 */
	public PanneauRejoindrePartieReseau() 
	{
		super();
		
		setLayout(new BorderLayout());
		
		ControleurRejoindrePartieReseau ctrl = new ControleurRejoindrePartieReseau(ModelePanneau.getInstance(), ModeleRejoindrePartieReseau.getInstance());
		
		add(creerPanneauTitre(), BorderLayout.CENTER);
		add(creerPanneauFormulaire(ctrl), BorderLayout.SOUTH);
	}

	/* ______________________________________________________ */
	/** Crée le label contenant du titre
	 * @return Le label
	 */
	private JLabel creerPanneauTitre()
	{
		JLabel titre = new JLabel("Rejoindre un serveur de jeu");
		titre.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		titre.setFont(POLICE_SS_TITRE);
		return titre;
	}

	/* ______________________________________________________ */
	/** Crée le panneau contenant les boutons 
	 * @param ctrl 
	 * @return panneau contenant les boutons
	 */
	private JPanel creerPanneauFormulaire(ControleurRejoindrePartieReseau ctrl)
	{
		JPanel panneauBouton = new JPanel(new GridLayout(4,1));
		panneauBouton.add(creerPanneauZoneDeTexte(ctrl));
		panneauBouton.add(creerPanneauBoutons(ctrl));
		return panneauBouton;
	}

	/**
	 * Créé le panneau des boutons
	 * @param ctrl Contrôleur de rejoindre une partie en réseau
	 * @return le panneau des boutons
	 */
	private JPanel creerPanneauBoutons(ControleurRejoindrePartieReseau ctrl)
	{
		JPanel panelBoutons = new JPanel(new FlowLayout());
		panelBoutons.add(creerPanneauBoutonValider(ctrl));
		panelBoutons.add(creerPanneauBoutonRetour(ctrl));
		return panelBoutons;
	}

	/* ______________________________________________________ */
	/** Crée le panneau contenant le bouton retour
	 * @param ctrl Contrôleur de rejoindre une partie en réseau
	 * @return le panneau et ses composants
	 */
	private JPanel creerPanneauBoutonRetour(ControleurRejoindrePartieReseau ctrl)
	{
		JPanel panneauBoutonRetour = new JPanel(new FlowLayout());
		creerBouton("Retour", panneauBoutonRetour, ctrl);
		return panneauBoutonRetour;
	}

	/* ______________________________________________________ */
	/** Panneau contenant le bouton valider
	 * @param ctrl Contrôleur de rejoindre une partie en réseau
	 * @return le panneau et ses composants
	 */
	private JPanel creerPanneauBoutonValider(ControleurRejoindrePartieReseau ctrl)
	{
		JPanel panneauBoutonValider = new JPanel(new FlowLayout());
		creerBouton("Valider", panneauBoutonValider, ctrl);
		return panneauBoutonValider;
	}

	/* ______________________________________________________ */
	/** Créé un bouton sur ce panneau
	 * @param label : intitule du bouton
	 * @param panneauRecepteur : le panneau qui recoit le bouton
	 * @param ctrl Contrôleur de rejoindre une partie en réseau
	 */
	private void creerBouton(String label, JPanel panneauRecepteur, ControleurRejoindrePartieReseau ctrl)
	{
		JButton monBouton = new JButton(label);
		monBouton.addActionListener(ctrl);
		monBouton.setFont(POLICE_BOUTON);
		monBouton.setPreferredSize(new Dimension(100, 30));
		panneauRecepteur.add(monBouton);
	}

	/* ______________________________________________________ */
	/** Crée le panneau avec le label et la zone de texte pour le choix du nom de la partie
	 * @param ctrl Contrôleur de rejoindre une partie en réseau
	 * @return le panneau et ses composants
	 */
	private JPanel creerPanneauZoneDeTexte(ControleurRejoindrePartieReseau ctrl)
	{
		JPanel panneauZoneDeTexte = new JPanel(new BorderLayout());
		panneauZoneDeTexte.add(creerPanneauZoneDeTexteNomPartie(ctrl), BorderLayout.NORTH);
		panneauZoneDeTexte.add(creerPanneauZoneDeTexteNomJoueur(ctrl), BorderLayout.SOUTH);
		return panneauZoneDeTexte;
	}


	/* ______________________________________________________ */
	/** Crée le panneau contenant la zone de texte nombre de joueurs et son label
	 * @param ctrl Contrôleur de rejoindre une partie en réseau
	 * @return le panneau et ses composants
	 */
	private JPanel creerPanneauZoneDeTexteNomJoueur(ControleurRejoindrePartieReseau ctrl)
	{
		JPanel panneauZoneDeTexteNombreJoueur = new JPanel(new FlowLayout());
		zoneDeTexteNomJoueur = new JTextField();
		initParamChampPseudo(zoneDeTexteNomJoueur, ctrl);
		
		JLabel labelNombreJoueurs = new JLabel("Votre pseudonyme : ");
		labelNombreJoueurs.setFont(POLICE_CHAMP_SAISIE);
		
		ctrl.recupChampTextePseudo(zoneDeTexteNomJoueur);
		
		panneauZoneDeTexteNombreJoueur.add(labelNombreJoueurs);
		panneauZoneDeTexteNombreJoueur.add(zoneDeTexteNomJoueur);
		return panneauZoneDeTexteNombreJoueur;
	}

	/* ______________________________________________________ */
	/** Crée le panneau contenant la zone de texte et son label du nom de la partie
	 * @param ctrl Contrôleur de rejoindre une partie en réseau
	 * @return le panneau et ses composants
	 */
	private JPanel creerPanneauZoneDeTexteNomPartie(ControleurRejoindrePartieReseau ctrl)
	{
		JPanel panneauZoneDeTexteNomPartie = new JPanel(new FlowLayout());
		zoneDeTexteNomServeur = new JTextField();
		initParamChampPseudo(zoneDeTexteNomServeur, ctrl);
		
		JLabel labelNomPartie = new JLabel("Nom du serveur : ");
		labelNomPartie.setFont(POLICE_CHAMP_SAISIE);

		ctrl.recupChampTexteNomServeur(zoneDeTexteNomServeur);
		
		panneauZoneDeTexteNomPartie.add(labelNomPartie);
		panneauZoneDeTexteNomPartie.add(zoneDeTexteNomServeur);
		return panneauZoneDeTexteNomPartie;
	}
	
	/**
	 * Initialise les paramètres des textfields
	 * @param textField le textfield à paramétrer
	 * @param ctrl Contrôleur rejoindre partie en réseau
	 */
	private void initParamChampPseudo(JTextField textField, ControleurRejoindrePartieReseau ctrl)
	{
		textField.addKeyListener(ctrl);
		textField.setPreferredSize(new Dimension(150, 30));
		textField.setFont(POLICE_CHAMP_SAISIE);
		textField.setHorizontalAlignment(JTextField.CENTER);
	}
}

/*__________________________________________________________*/
/* Fin du fichier FenetreCreerPartieReseau.java. */
/*__________________________________________________________*/