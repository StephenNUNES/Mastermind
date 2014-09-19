/* ______________________________________________________ */
/**
 * Fichier : Configuration.java
 *
 * Crée le 7 déc. 2013 à 17:58:24
 *
 * Auteur : NUNES Stephen
 */
package controleur;

import java.io.Serializable;

import constantes.IConstantes;

/* ______________________________________________________ */
/** Configuration du jeu selon la difficulté et les règles du jeu
 */
public class Difficulte implements IConstantes, Serializable
{	
	/** Numéro de sérialisation */
	private static final long serialVersionUID = -3096730496722301831L;

	/** Nombre de pion par combinaison */
	private int nbPionCombinaison;
	
	/** Nombre d'essai permis au joueur pour trouver la solution */
	private int nbEssai;
	
	/** Nombre de couleur de pion */
	private int nbCouleurPion;

	/** Libelle de la difficulté*/
	private String libelle;
		
	/**
	 * Accesseur en lecture de l'attribut libelle
	 * @return la valeur de libelle
	 */
	public String getLibelle()
	{
		return libelle;
	}

	/* ______________________________________________________ */
	/** Constructeur de la configuration du jeu
	 * @param nbPionCombinaison Nombre de pion dans une combinaison
	 * @param nbEssai Nombre d'essai pour trouver la solution
	 * @param nbCouleurPion Nombre de couleur de pion disponible
	 */
	public Difficulte(int nbPionCombinaison, int nbEssai, int nbCouleurPion)
	{
		setNbPionCombinaison(nbPionCombinaison);
		setNbEssais(nbEssai);
		setNbCouleurPion(nbCouleurPion);
	}
	
	/* ______________________________________________________ */
	/** Constructeur de la difficulté avec le nom de la difficulté passé en paramètre
	 * @param nomDifficulte Nom de la difficulté
	 */
	public Difficulte(String nomDifficulte)
	{
		if (nomDifficulte == null)
			throw new NullPointerException("Nom de la difficulté null");
		if (nomDifficulte.equals(DIFF_FACILE))
		{
			this.libelle = DIFF_FACILE;
			setDifficulteFacile();
		}
		else if (nomDifficulte.equals(DIFF_MOYENNE))
		{
			this.libelle = DIFF_MOYENNE;
			setDifficulteMoyenne();
		}
		else if (nomDifficulte.equals(DIFF_DIFFICILE))
		{
			this.libelle = DIFF_DIFFICILE;
			setDifficulteDifficile();
		}
		
	}

	/* ______________________________________________________ */
	/** Défini la difficulté à difficile
	 */
	private void setDifficulteDifficile()
	{
		setNbEssais(10);
		setNbCouleurPion(10);
		setNbPionCombinaison(6);
		
	}

	/* ______________________________________________________ */
	/** Défini la difficulté à moyen
	 */
	private void setDifficulteMoyenne()
	{
		setNbEssais(10);
		setNbCouleurPion(8);
		setNbPionCombinaison(4);
	}

	/* ______________________________________________________ */
	/** Défini la difficulté à facile
	 */
	private void setDifficulteFacile()
	{
		setNbEssais(14);
		setNbCouleurPion(6);
		setNbPionCombinaison(4);
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ nbPionCombinaison.
	 * @return la valeur du champ nbPionCombinaison.
	 */
	public int getNbPionCombinaison()
	{
		return nbPionCombinaison;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ nbPionCombinaison.
	 * @param nbPionCombinaison la valeur à placer dans le champ nbPionCombinaison.
	 */
	private void setNbPionCombinaison(int nbPionCombinaison)
	{
		this.nbPionCombinaison = nbPionCombinaison;
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ nbEssais.
	 * @return la valeur du champ nbEssais.
	 */
	public int getNbEssais()
	{
		return nbEssai;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ nbEssais.
	 * @param nbEssais la valeur à placer dans le champ nbEssais.
	 */
	private void setNbEssais(int nbEssais)
	{
		this.nbEssai = nbEssais;
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ nbCouleurPion.
	 * @return la valeur du champ nbCouleurPion.
	 */
	public int getNbCouleurPion()
	{
		return nbCouleurPion;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ nbCouleurPion.
	 * @param nbCouleurPion la valeur à placer dans le champ nbCouleurPion.
	 */
	private void setNbCouleurPion(int nbCouleurPion)
	{
		this.nbCouleurPion = nbCouleurPion;
	}

	
	
}

/*__________________________________________________________*/
/* Fin du fichier Configuration.java. */
/*__________________________________________________________*/