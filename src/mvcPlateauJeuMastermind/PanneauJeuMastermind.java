/*________________________________________________________*/
/**
 * Fichier : Mastermind.java
 *
 * Cr�� le 07 d�cembre 2013 � 14:31:32
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcPlateauJeuMastermind;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import metier.CoupJoue;
import modele.graphique.ImageCouleur;
import modele.graphique.ModeleComboBoxChoixCouleur;
import mvc.ModelePanneau;
import constantes.IConstantes;
import constantes.IConstantesGraphiques;
import controleur.Facade;


/*________________________________________________________*/
/** 
 * Cette classe permet de gérer le mastermind
 */
public class PanneauJeuMastermind extends JPanel implements IConstantesGraphiques, Observer, IConstantes
{	
	/** Attribut obligatoire */
	private static final long serialVersionUID = 1L;

	/** Panneau comportant le plateau de jeu */
	private JPanel panneauPlateau;

	/** Liste des comboBox de la fenêtre */
	private List<JComboBox<ImageCouleur>> listeCombo;

	/** Contrôleur du plateau de jeu mastermind */
	private ControleurJeuMastermind controleurDuPanneau = null;

	/** modele de la Combo */
	private ModeleComboBoxChoixCouleur modeleCombo  = null;

	/** Instance unique de la vue */
	private static PanneauJeuMastermind vue = null;

	/** Modèle du panneau jeu mastermind */
	private ModeleJeuMastermind monModeleJeuMastermind = null;

	/** Mon modèle de panneau */
	private ModelePanneau monModelePanneau = null;


	/*_______________________________________________________*/
	/** Permet d'obtenir la vue.
	 * @return La vue.
	 */
	public static PanneauJeuMastermind getVue()
	{
		if (vue == null)
			vue = new PanneauJeuMastermind() ;
		return vue ;
	}
	
	/*________________________________________________________*/
	/** 
	 * Constructeur du panneau du jeu Mastermind
	 */
	public PanneauJeuMastermind()
	{
		super(new BorderLayout());
		
		if (Facade.getInstance().getJeu() != null)
		{
			listeCombo = new ArrayList<>();
			controleurDuPanneau = new ControleurJeuMastermind(ModelePanneau.getInstance(), ModeleJeuMastermind.getInstance());
			controleurDuPanneau.creerTourDeJeu();
			monModeleJeuMastermind = ModeleJeuMastermind.getInstance();
			monModelePanneau = ModelePanneau.getInstance();
			monModelePanneau.addObserver(this);
			monModeleJeuMastermind.deleteObservers();
			monModeleJeuMastermind.addObserver(this);

			creerComposantFenetre();
		}

	}

	/* ______________________________________________________ */
	/** Crée les composants de la fenêtre
	 */
	private void creerComposantFenetre()
	{
		add(creerPanneauJeu(), BorderLayout.WEST);
		add(creerPanneauDroite(), BorderLayout.EAST);

	}

	/* ______________________________________________________ */
	/** Crée le panneau contenant la liste des joueurs connectés à la partie
	 * @return le panneau avec sa liste des joueurs
	 */
	private JPanel creerPanneauDroite()
	{
		JPanel panneauDroite = new JPanel(new BorderLayout());
		panneauDroite.add(creerListeJoueur(), BorderLayout.NORTH);
		panneauDroite.add(creerImage(), BorderLayout.CENTER);
		return panneauDroite;
	}



	/**
	 * Créé le panel avec l'image du jeu
	 * @return le panel créé
	 */
	private JPanel creerImage()
	{
		JPanel panelImage = new JPanel();
		
		panelImage.add(new JLabel(new ImageIcon("images_png/icone.png")));
		
		return panelImage;
	}

	/* ______________________________________________________ */
	/** Crée la liste des joueurs connectés à la partie
	 * @return la liste des joueurs décoré d'une barre de défilement
	 */
	private JScrollPane creerListeJoueur()
	{
		JList<String> listeJoueurs = new JList<String>(ModeleListeJoueurJeuMastermind.getInstance(false, false));
		JScrollPane barreDefilement = new JScrollPane(listeJoueurs);
		barreDefilement.setPreferredSize(new Dimension(300,200));
		ModeleListeJoueurJeuMastermind.getInstance(false, false).affichageJoueursListe();

		return barreDefilement;
	}



	/* ______________________________________________________ */
	/** Crée le panneau contenant le panneau du jeu ainsi que les intéractions
	 * @return le panneau du jeu
	 */
	private JPanel creerPanneauJeu()
	{
		JPanel panneauJeuEtInteraction = new JPanel(new BorderLayout());
		panneauPlateau = new JPanel(new GridLayout(Facade.getInstance().getNombrePropositionPossible(), 2));
		panneauJeuEtInteraction.add(panneauPlateau, BorderLayout.NORTH);
		panneauJeuEtInteraction.add(creerPanneauInteraction(), BorderLayout.SOUTH);
		return panneauJeuEtInteraction;
	}



	/* ______________________________________________________ */
	/** Crée le panneau contenant les intéractions possibles du joueurs avec les choix de couleur et le bouton valider
	 * @return le panneau créé
	 */
	private JPanel creerPanneauInteraction()
	{
		JPanel panneauInteraction  = new JPanel(new FlowLayout());

		panneauInteraction.add(creerPanneauChoixCouleurs());
		panneauInteraction.add(creerPanneauBoutonUnique("Valider", controleurDuPanneau));
		panneauInteraction.add(creerPanneauBoutonUnique("Sauvegarder", controleurDuPanneau));
		return panneauInteraction;
	}



	/**
	 * Créé le panel d'un seul bouton
	 * @param intitule Nom du bouton
	 * @param controleur Controleur des évènement survenu sur le bouton
	 * @return le bouton dans son panel
	 */
	private JPanel creerPanneauBoutonUnique(String intitule, ControleurJeuMastermind controleur)
	{
		JPanel panneauBouton = new JPanel(new FlowLayout());
		JButton bouton = new JButton(intitule);
		bouton.addActionListener(controleur);
		bouton.setFont(POLICE_BOUTON);
		bouton.setPreferredSize(new Dimension(100, 30));
		panneauBouton.add(bouton);
		return panneauBouton;
	}


	/*________________________________________________________*/
	/**
	 * Cr�� le panel o� se trouve les combosBox pour le choix des couleurs
	 * @return Panel des ComboBox
	 */
	private JPanel creerPanneauChoixCouleurs()
	{

		JPanel panneauLesComboBox = new JPanel(new FlowLayout());
		for (int i = 0; i < Facade.getInstance().getNombrePionDansCombinaison() ; ++i)
		{
			panneauLesComboBox.add(creerPanneauComboBox());
		}
		controleurDuPanneau.recupererListeComboBox(listeCombo);

		return panneauLesComboBox;
	}	

	/* ______________________________________________________ */
	/** Panneau contenant une comboBox
	 * @return le panneau et sa comboBox
	 */
	private JPanel creerPanneauComboBox()
	{
		JPanel panneauComboBox = new JPanel();

		JComboBox<ImageCouleur> comboBoxDImageCouleur = new JComboBox<ImageCouleur>();
		modeleCombo = new ModeleComboBoxChoixCouleur();
		comboBoxDImageCouleur.setModel(modeleCombo);
		comboBoxDImageCouleur.addActionListener(controleurDuPanneau);
		listeCombo.add(comboBoxDImageCouleur);
		panneauComboBox.add(comboBoxDImageCouleur, BorderLayout.PAGE_START);
		panneauComboBox.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));
		return panneauComboBox;
	}

	/* ______________________________________________________ */
	/** Ajoute le coup joué au panneau
	 * @param coupJoueAAffiche Le coup joué ajouté au panneau
	 */
	public void ajouterCoupJouePanneau(CoupJoue coupJoueAAffiche)
	{
		JPanel panneauCombinasion = new JPanel(new FlowLayout());
		JPanel panneauMarqueurs = new JPanel(new GridLayout(2,Facade.getInstance().getNombrePionDansCombinaison()/2));

		for (String couleur : coupJoueAAffiche.getCombinaison().getCouleurPions())
		{
			JLabel labelImage = new JLabel(new ImageCouleur(couleur));
			panneauCombinasion.add(labelImage);
		}


		for (int i = 0; i < coupJoueAAffiche.getResultat().getNombreDePionBienPlace(); i++)
		{
			JLabel imageMarqueur = new JLabel(new ImageCouleur("petit_rouge"));
			panneauMarqueurs.add(imageMarqueur);
		}
		for (int i = 0; i < coupJoueAAffiche.getResultat().getNombreDePionMalPlace(); i++)
		{
			JLabel imageMarqueur = new JLabel(new ImageCouleur("petit_blanc"));
			panneauMarqueurs.add(imageMarqueur);
		}

		panneauCombinasion.add(panneauMarqueurs);
		panneauPlateau.add(panneauCombinasion);
		updateUI();
	}

	/* ______________________________________________________ */
	/** Redéfinition de la méthode de mise à jour du panneau après notification du modèle observé
	 * @param observe Modele du panneauJeuMastermind observé
	 * @param evenementNotifie Coup joué à ajouter au panneau
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable observe, Object evenementNotifie)
	{
		if (evenementNotifie instanceof CoupJoue)
		{
			ajouterCoupJouePanneau((CoupJoue) evenementNotifie);
		}
		else if (evenementNotifie instanceof String)
		{
			finDePartie((String) evenementNotifie);
		}
	}

	/* ______________________________________________________ */
	/** Actions réalisées lors de la fin d'une partie lorsque le jeu est gagné ou non
	 * @param evenementNotifie événement reçu
	 */
	private void finDePartie(String evenementNotifie)
	{
		UIManager.put("OptionPane.font", POLICE_TEXTE);
		UIManager.put("OptionPane.messageFont", POLICE_TEXTE);
		UIManager.put("OptionPane.buttonFont", POLICE_BOUTON_CLASSEMENT);
		if (evenementNotifie.equals(EVENT_PERDU))
		{
			JOptionPane.showMessageDialog(this, "Vous avez perdu, la solution était : " + Facade.getInstance().getJeu().getSolution() , "Fin de partie", JOptionPane.INFORMATION_MESSAGE);

		}
		else if (evenementNotifie.equals(EVENT_GAGNE))
		{
			JOptionPane.showMessageDialog(this, "Vous avez gagné ! Félicitations ! ", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
		}
		controleurDuPanneau.actionPerformed(new ActionEvent(this, 0, MENU_PRINCIPAL));
		remettreAZero();

	}

	/* ______________________________________________________ */
	/** Remet à zéro le plateau de jeu et la liste des joueurs
	 */
	private void remettreAZero()
	{
		panneauPlateau.removeAll();
		ModeleListeJoueurJeuMastermind.getInstance(true, false);
		updateUI();
	}


}

/*________________________________________________________*/
/* Fin du fichier Mastermind.java.
/*________________________________________________________*/
