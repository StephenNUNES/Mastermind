/**
 * Fichier : ModeleMenuDeDifficulte.java
 *
 * Créé le 6 févr. 2014 à 15:25:22
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcMenuChoixPseudo;

import java.util.Observable;

import metier.Joueur;
import mvcPlateauJeuMastermind.ModeleListeJoueurJeuMastermind;

/*_______________________________________________________*/
/**
 * Modèle du panneau ChoixPseudo
 */
public class ModeleChoixPseudo extends Observable
{
	/** Instance unique du modèle */
	private static ModeleChoixPseudo instance = null;

	/** Instance d'un joueur créé avec le bon pseudo */
	private Joueur joueurCree = null;
	
	/**
	 * Accesseur en lecture de l'attribut joueurCree
	 * @return la valeur de joueurCree
	 */
	public Joueur getJoueurCree()
	{
		return joueurCree;
	}

	/*_______________________________________________________*/
	/**
	 * Constructeur privé de la classe ModelePanneau
	 */
	private ModeleChoixPseudo()
	{
	}
	
	/*_______________________________________________________*/
	/**
	 * Accesseur en lecture de l'instance unique du modèle
	 * @return l'instance unique du modèle
	 */
	public static ModeleChoixPseudo getInstance()
	{
		if (instance == null)
			instance = new ModeleChoixPseudo();
		return instance;
	}
	
	/**
	 * Créé un joueur
	 * @param pseudonyme : le pseudonyme du joueur
	 */
	public void creerJoueur(String pseudonyme)
	{
		joueurCree = new Joueur(pseudonyme);
		ModeleListeJoueurJeuMastermind.getInstance(false, false).ajouterJoueur(pseudonyme);
	}
}
/*________________________________________*/
/* Fin du fichier ModeleMenuDeDifficulte.java
/*________________________________________*/