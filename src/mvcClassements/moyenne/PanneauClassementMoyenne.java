/*___________________________________________________________________________*/
/**
 * Fichier : FenetreClassement.java
 *
 * Crée le 13 janv. 2014 à 09:01:00
 *
 * Auteur : BRUNEL Marjorie.
 */
package mvcClassements.moyenne;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import mvcClassements.PanneauClassementAbstrait;
import constantes.IConstantes;


/*___________________________________________________________________________*/
/**Classe de fenetre  qui hérite de la fenetre de classement et qui affiche le
 * classement par les moyennes des joueurs, c'est à dire le total des scores
 * des parties jouées divisé par le nombre de parties jouées.
 */
public class PanneauClassementMoyenne extends JPanel implements IConstantes
{
	/** serialVersionUID */
	private static final long serialVersionUID = -7045547148002104806L;
	
	/** Instance unique de la vue */
	private static PanneauClassementMoyenne vue = null;
	
	
	/*_______________________________________________________*/
	/** Permet d'obtenir la vue.
	 * @return La vue.
	 */
	public static PanneauClassementMoyenne getVue()
	{
		if (vue == null)
			vue = new PanneauClassementMoyenne() ;
		return vue ;
	}
	
	/*___________________________________________________________________________*/
	/** Constructeur de fenetre avec le classement par moyenne
	 */
	public PanneauClassementMoyenne()
	{
		super();
		setLayout(new BorderLayout());
		PanneauClassementAbstrait panneautab = new PanneauClassementAbstrait(CLASS_MOYENNE);
		add(panneautab, BorderLayout.CENTER);
	}
}


/*___________________________________________________________________________*/
/* Fin du fichier FenetreClassement.java.
/*___________________________________________________________________________*/
