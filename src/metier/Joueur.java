/* ______________________________________________________ */
/**
 * Fichier : Joueur.java
 *
 * Crée le 6 déc. 2013 à 14:34:37
 *
 * Auteur : NUNES Stephen
 */
package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/* ______________________________________________________ */
/** Joueur du mastermind 
 */
public class Joueur implements Serializable
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = -2969618322390223342L;

	/** Pseudonyme */
	private String pseudonyme;
	
	/** Action de jeu du joueur étant une Combinaison pour trouver la solution */
	private Combinaison proposition = null;
	
	/** Liste des tours de jeu réalisés par le joueur */
	private List<TourDeJeu> lesToursDeJeu = null;

	/* ______________________________________________________ */
	/** Retourne la valeur du champ pseudonyme.
	 * @return la valeur du champ pseudonyme.
	 */
	public String getPseudonyme()
	{
		return pseudonyme;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ pseudonyme.
	 * @param pseudonyme la valeur à placer dans le champ pseudonyme.
	 */
	private void setPseudonyme(String pseudonyme)
	{
		this.pseudonyme = pseudonyme;
	}

	/* ______________________________________________________ */
	/** Constructeur d'un joueur avec son pseudonyme en paramètre
	 * @param pseudonyme pseudo du joueur
	 */
	public Joueur(String pseudonyme)
	{
		setPseudonyme(pseudonyme);
		lesToursDeJeu = new ArrayList<TourDeJeu>();
	}
	
	
	/* ______________________________________________________ */
	/** Ajoute un pion choisi par le joueur à sa combinaison
	 * @param pionAjoute Pion ajouté à la combinaison
	 */
	public void choixPion(Pion pionAjoute)
	{
		proposition.ajouterPion(pionAjoute);
	}
	
	/* ______________________________________________________ */
	/** Ajoute un tour de jeu à la liste des tours de jeu du joueur
	 * @param tourDeJeu le tour de jeu ajouté
	 * @return si le tour de jeu à ajouté
	 */
	public boolean creerTourDeJeu(TourDeJeu tourDeJeu)
	{
		return lesToursDeJeu.add(tourDeJeu);
	}
	
	/* ______________________________________________________ */
	/** Réinitialise les tours de jeu du joueur
	 */
	public void reinitialiserTourDeJeu()
	{
		lesToursDeJeu.clear();
	}
	
	/* ______________________________________________________ */
	/** Permet d'obtenir un TourDeJeu du Joueu
	 * @param index Index du TourDeJeu dans la liste
	 * @return le TourDeJeu obtenu
	 */
	public TourDeJeu getTourDeJeu(int index)
	{
		return lesToursDeJeu.get(index);
	}
	

	
	
}

/*__________________________________________________________*/
/* Fin du fichier Joueur.java. */
/*__________________________________________________________*/