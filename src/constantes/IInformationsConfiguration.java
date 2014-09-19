/* ______________________________________________________ */
/**
 * Fichier : ICommunication.java
 *
 * Crée le 19 déc. 2013 à 15:29:28
 *
 * Auteur : NUNES Stephen
 */
package constantes;

import java.util.List;

/* ______________________________________________________ */
/** Interface définissant les méthodes de communications entres les classes.
 * Cette interface est un médiateur abstrait
 */
public interface IInformationsConfiguration
{
	
	/* ______________________________________________________ */
	/** Permet d'obtenir la liste des couleurs disponibles dans le jeu de mastermind
	 * @return La liste des couleurs disponibles
	 */
	public List<String> getCouleursAutorises();
	
	/* ______________________________________________________ */
	/** Permet d'obtenir le nombre de pions dans une combinaison
	 * @return le nombre de pion dans une combinaison
	 */
	public int getNombrePionDansCombinaison();
	
	/* ______________________________________________________ */
	/** Permet d'obtenir le nombre de couleur disponibles pour les pions
	 * @return le nombre de couleur de pion disponible
	 */
	public int getNombreCouleurDisponibles();
	
	/* ______________________________________________________ */
	/** Permet d'obtenir le nombre de proposition d'un joueur pour trouver une solution
	 * @return le nombre de proposition possible
	 */
	public int getNombrePropositionPossible();
}

/*__________________________________________________________*/
/* Fin du fichier ICommunication.java. */
/*__________________________________________________________*/