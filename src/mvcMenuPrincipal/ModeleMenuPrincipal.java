/**
 * Fichier : ModeleMenuDeDifficulte.java
 *
 * Créé le 6 févr. 2014 à 15:25:22
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcMenuPrincipal;

import java.util.Observable;

/*_______________________________________________________*/
/**
 * Modèle du panneau ChoixPseudo
 */
public class ModeleMenuPrincipal extends Observable
{
	/** Instance unique du modèle */
	private static ModeleMenuPrincipal instance = null;
		
	/*_______________________________________________________*/
	/**
	 * Constructeur privé de la classe ModelePanneau
	 */
	private ModeleMenuPrincipal()
	{
	}
	
	/*_______________________________________________________*/
	/**
	 * Accesseur en lecture de l'instance unique du modèle
	 * @return l'instance unique du modèle
	 */
	public static ModeleMenuPrincipal getInstance()
	{
		if (instance == null)
			instance = new ModeleMenuPrincipal();
		return instance;
	}
}
/*________________________________________*/
/* Fin du fichier ModeleMenuDeDifficulte.java
/*________________________________________*/