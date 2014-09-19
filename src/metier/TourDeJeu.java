/* ______________________________________________________ */
/**
 * Fichier : TourDeJeu.java
 *
 * Crée le 15 déc. 2013 à 21:14:29
 *
 * Auteur : NUNES Stephen
 */
package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* ______________________________________________________ */
/** Tour de jeu contenant les coups joués par 1 joueur pour trouver une solution
 */
public class TourDeJeu implements Serializable
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = -7678585916470607345L;
	
	/** Liste des coups joués */
	private List<CoupJoue> lesCoupJoue = null;
	
	/* ______________________________________________________ */
	/** Retourne la valeur du champ lesCoupJoue.
	 * @return la valeur du champ lesCoupJoue.
	 */
	public List<CoupJoue> getLesCoupJoue()
	{
		return new ArrayList<>(lesCoupJoue);
	}


	/* ______________________________________________________ */
	/** Tour de jeu d'un joueur
	 */
	public TourDeJeu()
	{
		lesCoupJoue = new ArrayList<>(); 
	}
	
	/* ______________________________________________________ */
	/** Ajoute un coup joué au tour de jeu d'un joueur
	 * @param coupAjoute
	 * @throws NullPointerException Si le coupAjoute est inexistant
	 * @return Si l'ajout s'est réalisé correctement
	 */
	public boolean ajouteCoupJoue(CoupJoue coupAjoute)
		throws NullPointerException
	{
		if (coupAjoute == null)
			throw new NullPointerException("Le coup joué ajouté est inexistant");
		return lesCoupJoue.add(coupAjoute);
		
	}
	
	
}

/*__________________________________________________________*/
/* Fin du fichier TourDeJeu.java. */
/*__________________________________________________________*/