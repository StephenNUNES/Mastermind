/* ______________________________________________________ */
/**
 * Fichier : Configuration.java
 *
 * Crée le 15 déc. 2013 à 22:24:53
 *
 * Auteur : NUNES Stephen
 */
package controleur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* ______________________________________________________ */
/** Configuration du match 
 */
public class ConfigurationJeu
{
	
	/** Liste des couleurs disponibles pour la couleur des pions */
	private static List<String> listeCouleursDisponibles = new ArrayList<String>(Arrays.asList(
				"rouge", "bleu", "jaune", "vert", "noir", "blanc", "orange", "violet", "gris",
				"marron", "cyan", "rose", "vertfonce", "bleumarine","petit_blanc", "petit_rouge",
				"petit_noir")); // rajouter vide entre gris et marron
	
	/** Difficulté d'un jeu */
	private Difficulte difficulteJeu = null;
	
	/** Liste des pions d'indication */
	private static List<String> listeMarqueursDispo = new ArrayList<>(Arrays.asList("petit_blanc", "petit_rouge",
			"petit_noir"));	
	
	/** Liste des couleurs autorisé pour un jeu */
	private List<String> listeCouleursAutorise = null;
	
	/** Points de pénalité à appliquer si le joueur n'a pas trouvé la solution */
	private int pointsPenalite = 0;

	/* ______________________________________________________ */
	/** Retourne la valeur du champ listeCouleursAutorise.
	 * @return la valeur du champ listeCouleursAutorise.
	 */
	public List<String> getListeCouleursAutorise()
	{
		return listeCouleursAutorise;
	}

	/* ______________________________________________________ */
	/** Défini la valeur du champ listeCouleursAutorise.
	 */
	public void setListeCouleursAutorise()
	{ 
		
		if (difficulteJeu == null)
		{
			throw new NullPointerException("La difficulté de la configuration est inéxistante");
		}
		listeCouleursAutorise = new ArrayList<String>();
		for (int i = 0; i < difficulteJeu.getNbCouleurPion(); ++i)
		{
			listeCouleursAutorise.add(listeCouleursDisponibles.get(i));
		}
		
		
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ difficulteJeu.
	 * @return la valeur du champ difficulteJeu.
	 */
	public Difficulte getDifficulteJeu()
	{
		return difficulteJeu;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ difficulteJeu.
	 * @param difficulteJeu la valeur à placer dans le champ difficulteJeu.
	 * @throws NullPointerException Si la difficulté est null
	 */
	private void setDifficulteJeu(Difficulte difficulteJeu)
		throws NullPointerException
	{
		if (difficulteJeu == null)
			throw new NullPointerException("La difficulté est inexistante");
		this.difficulteJeu = difficulteJeu;
	}
	
	
	/* ______________________________________________________ */
	/** Constructeur de la Configuration d'un jeu
	 * @param laDifficulte Difficulté de la configuration d'un jeu
	 */
	public ConfigurationJeu(Difficulte laDifficulte)
	{
		setDifficulteJeu(laDifficulte);
		setListeCouleursAutorise();
	
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ pointsPenalite.
	 * @return la valeur du champ pointsPenalite.
	 */
	public int getPointsPenalite()
	{
		return pointsPenalite;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ pointsPenalite.
	 * @param pointsPenalite la valeur à placer dans le champ pointsPenalite.
	 */
	public void setPointsPenalite(int pointsPenalite)
	{
		this.pointsPenalite = pointsPenalite;
	}

 
	/* ______________________________________________________ */
	/** Permet d'obtenir la liste des marqueurs autorisés
	 * @return la liste des marqueurs
	 */
	public List<String> getListeMarqueurAutorise()
	{
		return listeMarqueursDispo;
	}
	
}

/*__________________________________________________________*/
/* Fin du fichier Configuration.java. */
/*__________________________________________________________*/