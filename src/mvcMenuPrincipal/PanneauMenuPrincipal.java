package mvcMenuPrincipal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/** Fenêtre contenant le menu principal du jeu Mastermind
 */
public class PanneauMenuPrincipal extends JPanel implements IConstantesGraphiques, Observer
{

	/** Numéro de sérialisation  */
	private static final long serialVersionUID = 1256150277213650663L;
	
	/** Tableau de hashage contenant les boutons de la fenêtre */
	private Map<String, JButton> boutons = null;
	
	/** Instance unique de la vue */
	private static PanneauMenuPrincipal vue = null;
	
	
	/*_______________________________________________________*/
	/** Permet d'obtenir la vue.
	 * @return La vue.
	 */
	public static PanneauMenuPrincipal getVue()
	{
		if (vue == null)
			vue = new PanneauMenuPrincipal() ;
		return vue ;
	}
	
	/*_______________________________________________________*/
	/**
	 *  Constructeur du panneau principal
	 */
	public PanneauMenuPrincipal()
	{
		super();
		setLayout(new BorderLayout());
		
		ControleurMenuPincipal ctrl = new ControleurMenuPincipal(ModelePanneau.getInstance());

		boutons = creerBoutons(ctrl);
		
		add(creerSousTitre(), BorderLayout.CENTER);
		add(creerPanelBoutons(ctrl), BorderLayout.SOUTH);
	}
	
	/**
	 * Méthode d'initialisation de la map des boutons
	 * @param ctrl Contrôleur du menu principal
	 * @return la map remplie.
	 */
	private Map<String, JButton> creerBoutons(ControleurMenuPincipal ctrl)
	{
		boutons = new HashMap<String, JButton>();
		boutons.put("Jeu solo", creerBouton("Jeu solo", ctrl));
		boutons.put("Jeu multi-joueurs", creerBouton("Jeu multi-joueurs", ctrl));
		boutons.put("Classement historique", creerBouton("Classement historique", ctrl));
		boutons.put("Classement moyenne", creerBouton("Classement moyenne", ctrl));
		boutons.put("Reprendre", creerBouton("Reprendre", ctrl));
				
		return boutons;
	}
	
	/**
	 * Créé le panel avec tous les boutons
	 * @param ctrl Contrôleur du menu principal
	 * @return le panel créé
	 */
	private JPanel creerPanelBoutons(ControleurMenuPincipal ctrl)
	{
		//Grid de 8 lignes alors que l'on a 5 boutons
		//pour avoir deux lignes vides en dessous de ces derniers
		//pour l'esthétique de la fenêtre
		JPanel panelBoutons = new JPanel(new GridLayout(8,1));
		panelBoutons.add(creerPanelBoutonUnique("Jeu solo", ctrl));
		panelBoutons.add(creerPanelBoutonUnique("Jeu multi-joueurs", ctrl));
		panelBoutons.add(creerPanelBoutonUnique("Classement historique", ctrl));
		panelBoutons.add(creerPanelBoutonUnique("Classement moyenne", ctrl));
		File f = new File(NOM_FICHIER);
		if(f.exists())
			panelBoutons.add(creerPanelBoutonUnique("Reprendre", ctrl));
		return panelBoutons;
	}
	
	/**
	 * Créé le panel d'un seul bouton
	 * @param intitule Nom du bouton
	 * @param ctrl Contrôleur du menu principal
	 * @return le bouton dans son panel
	 */
	private JPanel creerPanelBoutonUnique(String intitule, ControleurMenuPincipal ctrl)
	{
		JPanel panelBoutonUnique = new JPanel(new FlowLayout());
		panelBoutonUnique.add(boutons.get(intitule));
		return panelBoutonUnique;
	}


	/**
	 * Créé le label du sous-titre de la fenêtre
	 * @return le label créé du sous-titre de la fenêtre
	 */
	private JLabel creerSousTitre()
	{		
		JLabel ssTitre = new JLabel("Veuillez choisir votre mode de jeu :");
		ssTitre.setFont(POLICE_SS_TITRE);
		ssTitre.setHorizontalAlignment((int)CENTER_ALIGNMENT);
			
		return ssTitre;
	}	
	
	/**
	 * Méthode de création d'un bouton et d'ajout de la fenetre
	 * comme son listener.
	 * @param texte le texte du bouton
	 * @param ctrl Contrôleur du menu principal
	 * @return le bouton
	 */
	private JButton creerBouton(String texte, ControleurMenuPincipal ctrl)
	{
		JButton btn = new JButton(texte);
		btn.setActionCommand(texte);
		btn.addActionListener(ctrl);
		btn.setPreferredSize(new Dimension(230, 30));
		btn.setFont(POLICE_BOUTON);
		return btn;
	}
	
	/* ______________________________________________________ */
	/** Définition de la méthode update de l'interface Observer
	 * @param modele
	 * @param evenementNotifie
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable modele, Object evenementNotifie)
	{}
}
