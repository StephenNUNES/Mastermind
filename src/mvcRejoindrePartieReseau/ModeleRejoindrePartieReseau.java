/**
 * Fichier : ModeleMenuDeDifficulte.java
 *
 * Créé le 6 févr. 2014 à 15:25:22
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcRejoindrePartieReseau;

import java.util.Observable;

import controleur.reseau.Client;

/*_______________________________________________________*/
/**
 * Modèle du panneau ChoixPseudo
 */
public class ModeleRejoindrePartieReseau extends Observable
{
	/** Instance unique du modèle */
	private static ModeleRejoindrePartieReseau instance = null;

	/** Instance d'un joueur créé avec le bon pseudo */
	private Client clientJeu = null;

	/*_______________________________________________________*/
	/**
	 * Constructeur privé de la classe ModelePanneau
	 */
	private ModeleRejoindrePartieReseau()
	{
	}
	
	/*_______________________________________________________*/
	/**
	 * Accesseur en lecture de l'instance unique du modèle
	 * @return l'instance unique du modèle
	 */
	public static ModeleRejoindrePartieReseau getInstance()
	{
		if (instance == null)
			instance = new ModeleRejoindrePartieReseau();
		return instance;
	}
	
	/**
	 * Créé un joueur
	 * @param pseudonyme : le pseudonyme du joueur
	 */
	public void creerClient(String pseudonyme)
	{
		clientJeu = new Client(pseudonyme);
	}
	
	/**
	 * Accesseur en lecture de l'attribut joueurCree
	 * @return la valeur de joueurCree
	 */
	public Client getClientCree()
	{
		return clientJeu;
	}
}
/*________________________________________*/
/* Fin du fichier ModeleMenuDeDifficulte.java
/*________________________________________*/