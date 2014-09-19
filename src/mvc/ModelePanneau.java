/**
 * Fichier : ModelePanneau.java
 *
 * Créé le 3 févr. 2014 à 10:35:11
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvc;

import java.util.Observable;

/**
 * Modèle principal des panneaux de l'application
 */
public class ModelePanneau extends Observable
{
	/** Instance unique du modèle */
	private static ModelePanneau instance = null;
	
	/** Le panneau principal à afficher qui est par défaut */
	private String choix = "MenuPrincipal";
	
	/*_______________________________________________________*/
	/**
	 * Constructeur privé de la classe ModelePanneau
	 */
	private ModelePanneau()
	{
	}
	
	/*_______________________________________________________*/
	/**
	 * Accesseur en lecture de l'instance unique du modèle
	 * @return l'instance unique du modèle
	 */
	public static ModelePanneau getInstance()
	{
		if (instance == null)
			instance = new ModelePanneau();
		return instance;
	}
	
	/*_______________________________________________________*/
	/**
	 * Retourne le nom de panneau choisi.
	 * @return Le nom du panneau choisi.
	 */
	public String getChoix()
	{
		return choix;
	}
	
	/*_______________________________________________________*/
	/**
	 * Modifie le choix du panneau.
	 * @param choix Le nouveau choix.
	 */
	public void setChoix(String choix)
	{
		this.choix = choix ;
		setChanged() ;
		notifyObservers() ;
	}
}


/*________________________________________*/
/* Fin du fichier ModelePanneau.java
/*________________________________________*/