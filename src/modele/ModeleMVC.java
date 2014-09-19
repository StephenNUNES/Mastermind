/*___________________________________________________________________________*/
/**
 * Fichier : ModeleMVC.java
 *
 * Crée le 16 déc. 2013 à 11:22:52
 *
 * Auteur : BRUNEL Marjorie.
 */
package modele;

import java.util.Observable;

import controleur.Jeu;
import modele.graphique.ModeleComboBoxChoixCouleur;

/*___________________________________________________________________________*/
/** Modèle contenant les modèles de la partie graphique ainsi que les contrôleurs de l'application 
 */
public class ModeleMVC extends Observable
{
	/** Instance unique du Modele MVC */
	private static ModeleMVC instance = null;
	
	/** Modèle des CombBox de sélection d"une couleur */
	private ModeleComboBoxChoixCouleur modeleComboBoxChoix;
	
	/** Jeu du Mastermind */
	private Jeu controleurJeu = null;
	
	/* ______________________________________________________ */
	/** Méthode permettant d'obtenir l'instance unique du ModeleMVC
	 * @return l'instance unique
	 */
	public static ModeleMVC getInstance()
	{
		if (instance == null)
			instance = new ModeleMVC();
		return instance;
	}
	
	/*___________________________________________________________________________*/
	/** Constructeur du modèle stockant tous les autres modèles
	 */
	private ModeleMVC()
	{
		
	}


	/* ______________________________________________________ */
	/** Retourne la valeur du champ modeleComboBoxChoix.
	 * @return la valeur du champ modeleComboBoxChoix.
	 */
	public ModeleComboBoxChoixCouleur getModeleComboBoxChoix()
	{
		return modeleComboBoxChoix;
	}


	/* ______________________________________________________ */
	/** Modifie la valeur du champ modeleComboBoxChoix.
	 * @param modeleComboBoxChoix la valeur à placer dans le champ modeleComboBoxChoix.
	 */
	public void setModeleComboBoxChoix(ModeleComboBoxChoixCouleur modeleComboBoxChoix)
	{
		this.modeleComboBoxChoix = modeleComboBoxChoix;
	}


	/* ______________________________________________________ */
	/** Retourne la valeur du champ controleurJeu.
	 * @return la valeur du champ controleurJeu.
	 */
	public Jeu getControleurJeu()
	{
		return controleurJeu;
	}


	/* ______________________________________________________ */
	/** Modifie la valeur du champ controleurJeu.
	 * @param controleurJeu la valeur à placer dans le champ controleurJeu.
	 */
	public void setControleurJeu(Jeu controleurJeu)
	{
		this.controleurJeu = controleurJeu;
	}	
}


/*___________________________________________________________________________*/
/* Fin du fichier ModeleMVC.java.
/*___________________________________________________________________________*/
