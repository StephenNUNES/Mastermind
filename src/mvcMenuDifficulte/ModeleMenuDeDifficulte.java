/**
 * Fichier : ModeleMenuDeDifficulte.java
 *
 * Créé le 6 févr. 2014 à 15:25:22
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcMenuDifficulte;

import java.util.Observable;

/*_______________________________________________________*/
/**
 * Modèle du panneau ChoixPseudo
 */
public class ModeleMenuDeDifficulte extends Observable
{
	/** Instance unique du modèle */
	private static ModeleMenuDeDifficulte instance = null;

	/** Le nombre de rondes choisi par l'utilisateur */
	private int nbRondes;
	
	/**
	 * Accesseur en lecture de l'attribut nbRondes
	 * @return la valeur de nbRondes
	 */
	public int getNbRondes()
	{
		return nbRondes;
	}
	
	/* ______________________________________________________ */
	/** Accesseur en écriture de l'attribut nbRondes
	 * @param nombreRonde Nombre de ronde défini
	 */
	public void setNbRondes(int nombreRonde)
	{
		nbRondes = nombreRonde;
	}

	/*_______________________________________________________*/
	/**
	 * Constructeur privé de la classe ModelePanneau
	 */
	private ModeleMenuDeDifficulte()
	{
	}
	
	/*_______________________________________________________*/
	/**
	 * Accesseur en lecture de l'instance unique du modèle
	 * @return l'instance unique du modèle
	 */
	public static ModeleMenuDeDifficulte getInstance()
	{
		if (instance == null)
			instance = new ModeleMenuDeDifficulte();
		return instance;
	}
	
	
}
/*________________________________________*/
/* Fin du fichier ModeleMenuDeDifficulte.java
/*________________________________________*/