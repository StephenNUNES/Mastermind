package mvcMenuChoixPseudo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/**
 * Fenêtre permettant de choisir le pseudonyme du joueur
 */
public class PanneauChoixPseudo extends JPanel implements IConstantesGraphiques, Observer
{
	/** Attribut de sérialisation obligatoire */
	private static final long serialVersionUID = 7331655143413288097L;
	
	/** Zone de texte accueillant le pseudonyme du joueur */
	private JTextField champPseudo;

	/** Instance unique de la vue */
	private static PanneauChoixPseudo vue = null;
	
	/*_______________________________________________________*/
	/** Permet d'obtenir la vue.
	 * @return La vue.
	 */
	public static PanneauChoixPseudo getVue()
	{
		if (vue == null)
			vue = new PanneauChoixPseudo() ;
		return vue ;
	}

	/*_______________________________________________________*/
	/**
	 * Constructeur du panneau de choix d'un pseudonyme
	 */
	public PanneauChoixPseudo()
	{
		super();
		BorderLayout bdl = new BorderLayout();
		setLayout(bdl);
		
		ModeleChoixPseudo mdl = ModeleChoixPseudo.getInstance();
		mdl.addObserver(this) ;
		
		ControleurChoixPseudo ctrl = new ControleurChoixPseudo(ModelePanneau.getInstance(), ModeleChoixPseudo.getInstance());
		
		add(creerSousTitre(), BorderLayout.CENTER);
		add(creerCentre(ctrl), BorderLayout.SOUTH);
	}
	
	/**
	 * Permet de remettre à null le texte dans le champ du pseudo
	 */
	public void remiseAZeroChampPseudo()
	{
		champPseudo.setText("");
	}
	
	/**
	 * Créé panneau du titre
	 * @return le JPanel du titre
	 */
	private JLabel creerSousTitre()
	{
		JLabel labelPhrase = new JLabel("Entrez votre pseudonyme ici : ");
		labelPhrase.setFont(POLICE_SS_TITRE);
		labelPhrase.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		return labelPhrase;
	}

	/** Méthode de création des boutons
	 * @param intitule l'intitulé du bouton
	 * @param panelRecepteur le panel qui recoit le bouton
	 * @param ctrl Contrôleur du choix du pseudonyme
	 * @return le bouton créé
	 */
	private JButton creerBouton(String intitule, JPanel panelRecepteur, ControleurChoixPseudo ctrl)
	{
		JButton monBouton = new JButton(intitule);
		monBouton.addActionListener(ctrl);
		monBouton.setFont(POLICE_BOUTON);
		panelRecepteur.add(monBouton);
		return monBouton;
	}
	
	/** Méthode de création du panneau central
	 * @param ctrl Contrôleur du choix du pseudonyme
	 * @return le panneau créé et rempli
	 */
	private JPanel creerCentre(ControleurChoixPseudo ctrl)
	{
		//Grid de 6 lignes alors que l'on a 2 éléments
		//pour avoir deux lignes vides en dessous de ces derniers
		//pour l'esthétique de la fenêtre
		JPanel panneauCentral = new JPanel(new GridLayout(6,1));
		panneauCentral.add(creerPanneauTexte(ctrl));
		panneauCentral.add(creerPanneauBoutons(ctrl));
		return panneauCentral;
	}


	/**
	 * Créé le panel des boutons
	 * @param ctrl Contrôleur du choix du pseudonyme
	 * @return le panel créé
	 */
	private JPanel creerPanneauBoutons(ControleurChoixPseudo ctrl)
	{
		JPanel panelBoutons = new JPanel(new FlowLayout());
		panelBoutons.add(creerPanneauValider(ctrl));
		panelBoutons.add(creerPanneauRetour(ctrl));
		return panelBoutons;
	}
	
	/**
	 * Créé le panneau contenant le bouton valider
	 * @param ctrl Contrôleur du choix du pseudonyme
	 * @return le panel créé
	 */
	private JPanel creerPanneauValider(ControleurChoixPseudo ctrl)
	{
		JPanel panneauValider = new JPanel(new FlowLayout());
		creerBouton("Valider", panneauValider, ctrl);
		return panneauValider;
	}

	/**
	 * Créé le panneau contenant le bouton retour
	 * @param ctrl Contrôleur du choix du pseudonyme
	 * @return le panel créé
	 */
	private JPanel creerPanneauRetour(ControleurChoixPseudo ctrl)
	{
		JPanel panneauRetour = new JPanel(new FlowLayout());
		creerBouton("Retour", panneauRetour, ctrl);
		return panneauRetour;
	}
	
	/**
	 * Créé le panel de saise du pseudo
	 * @param ctrl Contrôleur choix du pseudo
	 * @return le panel créé
	 */
	private JPanel creerPanneauTexte(ControleurChoixPseudo ctrl)
	{
		JPanel panneauTexte = new JPanel(new FlowLayout());
		champPseudo = new JTextField("");
		ctrl.recupChampTextePseudo(champPseudo);
		initParamChampPseudo(ctrl);

		JLabel labelPseudo = new JLabel("Pseudo : ");
		labelPseudo.setFont(POLICE_SS_TITRE);
		
		panneauTexte.add(labelPseudo);
		panneauTexte.add(champPseudo);
		
		return panneauTexte;
	}

	/**
	 * Initialise les paramètres du textfield du pseudo
	 * @param ctrl Contrôleur du choix pseudo
	 */
	private void initParamChampPseudo(ControleurChoixPseudo ctrl)
	{
		champPseudo.addKeyListener(ctrl);
		champPseudo.setPreferredSize(new Dimension(150, 30));
		champPseudo.setFont(POLICE_CHAMP_SAISIE);
		champPseudo.setHorizontalAlignment(JTextField.CENTER);
	}

	/* ______________________________________________________ */
	/** Définition de la méthode de mise à jour de la vue par rapport au modèle
	 * @param arg0 Observé avertissant l'observateur
	 * @param arg1 se qui est notifié
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1)
	{
		((ModeleChoixPseudo) arg0).creerJoueur(champPseudo.getText().trim());
	}	
}
