package mvcMenuDifficulte;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import mvc.ModelePanneau;
import constantes.IConstantes;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/** Fenêtre contenant les boutons permettant de choisir la difficulté 
 */
public class PanneauMenuDeDifficulte extends JPanel implements IConstantesGraphiques, IConstantes
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = 1256150277213650663L;
		
	/** Map des boutons référencés par leur intitulé */
	private Map<String, JButton> boutons = null;

	/** Instance unique de la vue */
	private static PanneauMenuDeDifficulte vue = null;
	
	/** Contrôleur de la difficulté */
	private ControleurMenuDeDifficulte monControleur = null;
	
	/*_______________________________________________________*/
	/** Permet d'obtenir la vue.
	 * @return La vue.
	 */
	public static PanneauMenuDeDifficulte getVue()
	{
		if (vue == null)
			vue = new PanneauMenuDeDifficulte() ;
		return vue ;
	}
	
	/**
	 * Constructeur du menu de difficultés
	 *
	 */
	public PanneauMenuDeDifficulte()
	{
		super();
		setLayout(new BorderLayout());
		
		monControleur = new ControleurMenuDeDifficulte(ModelePanneau.getInstance(), ModeleMenuDeDifficulte.getInstance());
			
		boutons = initialiserMapBoutons(monControleur);

		add(creerSousTitre(), BorderLayout.CENTER);
		add(creerPanelCentre(), BorderLayout.SOUTH);		
	}
	
	
	/**
	 * Créé le sous-titre de la fenêtre
	 * @return le label du sous titre
	 */
	private JLabel creerSousTitre()
	{	
		JLabel ssTitre = new JLabel("Choisissez votre niveau de difficulté :");
		ssTitre.setFont(POLICE_SS_TITRE);
		ssTitre.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		
		return ssTitre;
	}
	
	/**
	 * Créé le panel avec tous les boutons
	 * @return le panel créé
	 */
	private JPanel creerPanelCentre()
	{
		//Grid de 9 lignes alors que l'on a 5 éléments
		//pour avoir une ligne vide en dessous de ces derniers
		//pour l'esthétique de la fenêtre
		JPanel panelCentre = new JPanel(new GridLayout(9,1));
		panelCentre.add(creerPanelNombreRondes());
		panelCentre.add(new JLabel());
		panelCentre.add(creerPanelBouton("Facile"));
		panelCentre.add(creerPanelBouton("Moyen"));
		panelCentre.add(creerPanelBouton("Difficile"));
		panelCentre.add(creerPanelBouton("Retour"));
		return panelCentre;
	}
	
	/** Crée le panneau contenant la zone de texte avec le nombre de ronde
	 * @return le panneau et ses composants
	 */
	private JPanel creerPanelNombreRondes()
	{
		JPanel panelRonde = new JPanel(new FlowLayout());
		
		JLabel labelNbRonde = new JLabel("Nombre de rondes :");
		labelNbRonde.setFont(POLICE_CHAMP_SAISIE);
		
		JSpinner zoneNbRonde = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		zoneNbRonde.setValue(1);
		monControleur.recupererChampTexteNombreRonde(zoneNbRonde);
		zoneNbRonde.setPreferredSize(new Dimension(60,30));
		zoneNbRonde.setFont(POLICE_CHAMP_SAISIE);
		
		
		panelRonde.add(labelNbRonde);
		panelRonde.add(zoneNbRonde);
		return panelRonde;
	}

	/**
	 * Créé le panel d'un seul bouton
	 * @param intitule Nom du bouton
	 * @return le bouton dans son panel
	 */
	private JPanel creerPanelBouton(String intitule)
	{
		JPanel panelBoutonUnique = new JPanel(new FlowLayout());
		panelBoutonUnique.add(boutons.get(intitule));
		return panelBoutonUnique;
	}
	
	
	/**
	 * Méthode d'initialisation de la map des boutons
	 * @param ctrl Contrôleur du menu de la difficulté
	 * @return la map remplie.
	 */
	private Map<String, JButton> initialiserMapBoutons(ControleurMenuDeDifficulte ctrl)
	{
		boutons = new HashMap<String, JButton>();
		boutons.put("Facile", creerBouton("Facile", ctrl));
		boutons.put("Moyen", creerBouton("Moyen", ctrl));
		boutons.put("Difficile", creerBouton("Difficile", ctrl));
		boutons.put("Retour", creerBouton("Retour", ctrl));
				
		return boutons;
	}
	
	
	/**
	 * Méthode de création d'un bouton et d'ajout de la fenetre
	 * comme son listener.
	 * @param texte le texte du bouton
	 * @param ctrl Contrôleur du menu de la difficulté
	 * @return le bouton
	 */
	private JButton creerBouton(String texte, ControleurMenuDeDifficulte ctrl)
	{
		JButton btn = new JButton(texte);
		btn.addActionListener(ctrl);
		btn.setPreferredSize(new Dimension(120,30));
		btn.setFont(POLICE_BOUTON);
		return btn;
	}
}
