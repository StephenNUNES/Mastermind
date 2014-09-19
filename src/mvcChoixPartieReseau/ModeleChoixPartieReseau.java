/**
 * Fichier : ModeleMenuDeDifficulte.java
 *
 * Créé le 6 févr. 2014 à 15:25:22
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcChoixPartieReseau;

import java.util.Observable;

import controleur.reseau.FacadeReseau;
import controleur.reseau.ServeurJeu;

/*_______________________________________________________*/
/**
 * Modèle du panneau ChoixPseudo
 */
public class ModeleChoixPartieReseau extends Observable
{
	/** Instance unique du modèle */
	private static ModeleChoixPartieReseau instance = null;

	/** Instance d'un joueur créé avec le bon pseudo */
	private ServeurJeu serveurCree = null;
	
	/** Choix donnée par le contrôleur */
	private String choix = null;

	/*_______________________________________________________*/
	/**
	 * Constructeur privé de la classe ModelePanneau
	 */
	private ModeleChoixPartieReseau()
	{
	}
	
	/*_______________________________________________________*/
	/**
	 * Accesseur en lecture de l'instance unique du modèle
	 * @return l'instance unique du modèle
	 */
	public static ModeleChoixPartieReseau getInstance()
	{
		if (instance == null)
			instance = new ModeleChoixPartieReseau();
		return instance;
	}
	
	/**
	 * Créé un serveur de jeu et le stocke dans la FacadeReseau
	 * 
	 */
	public void creerServeur()
	{
		serveurCree = new ServeurJeu();
		FacadeReseau.getInstance().setServeurJeu(serveurCree);
	}
	
	/**
	 * Accesseur en lecture de l'attribut serveurCree
	 * @return la valeur de serveurCree
	 */
	public ServeurJeu getServeurCree()
	{
		return serveurCree;
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ choix.
	 * @return la valeur du champ choix.
	 */
	public String getChoix()
	{
		return choix;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ exceptionRetenu.
	 * @param choix la valeur à placer dans le champ exceptionRetenu.
	 */
	public void setChoix(String choix)
	{
		this.choix = choix;
		setChanged();
		notifyObservers(choix);
	}
	
	
}
/*________________________________________*/
/* Fin du fichier ModeleMenuDeDifficulte.java
/*________________________________________*/