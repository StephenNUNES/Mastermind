/*___________________________________________________________________________*/
/**
 * Fichier : FenetreClassementMeilleurScore.java
 *
 * Crée le 13 janv. 2014 à 10:20:08
 *
 * Auteur : BRUNEL Marjorie.
 */
package mvcClassements.historique;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import mvcClassements.PanneauClassementAbstrait;
import constantes.IConstantes;

/*___________________________________________________________________________*/
/** Panneau des meilleurs score du jeu
 */
public class PanneauClassementHistorique extends JPanel implements IConstantes
{
	/** serialVersionUID */
	private static final long serialVersionUID = -1255873501214678703L;
	
	/** Instance unique de la vue */
	private static PanneauClassementHistorique vue = null;
	
	/*_______________________________________________________*/
	/** Permet d'obtenir la vue.
	 * @return La vue.
	 */
	public static PanneauClassementHistorique getVue()
	{
		if (vue == null)
			vue = new PanneauClassementHistorique() ;
		return vue ;
	}
	
	/*___________________________________________________________________________*/
	/** Constructeur du panneau du classement des meilleurs scores
	 */
	public PanneauClassementHistorique()
	{
		super();
		setLayout(new BorderLayout());
		PanneauClassementAbstrait panneautab = new PanneauClassementAbstrait(CLASS_HISTORIQUE);
		//this.setContentPane(pan);
		add(panneautab, BorderLayout.CENTER);
	}

}
/*___________________________________________________________________________*/
/* Fin du fichier FenetreClassementMeilleurScore.java.
/*___________________________________________________________________________*/
