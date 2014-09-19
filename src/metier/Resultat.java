/* ______________________________________________________ */
/**
 * Fichier : Resultat.java
 *
 * Crée le 8 déc. 2013 à 13:54:45
 *
 * Auteur : NUNES Stephen
 */
package metier;

import java.io.Serializable;


/* ______________________________________________________ */
/** Résultat d'une proposition par rapport à une combinaison
 */
public class Resultat implements Serializable
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = -8994497586445812244L;

	/** Nombre de pion bien placé */
	private int nombreDePionBienPlace;
	
	/** Nombre de pion mal placé */
	private int nombreDePionMalPlace;
	
	/* ______________________________________________________ */
	/** Constructeur du résultat avec instanciation de la liste des marqueurs
	 */
	public Resultat()
	{
	
	}
	

	/* ______________________________________________________ */
	/** Retourne la valeur du champ nombreDePionBienPlace.
	 * @return la valeur du champ nombreDePionBienPlace.
	 */
	public int getNombreDePionBienPlace()
	{
		return nombreDePionBienPlace;
	}



	/* ______________________________________________________ */
	/** Modifie la valeur du champ nombreDePionBienPlace.
	 * @param nombreDePionBienPlace la valeur à placer dans le champ nombreDePionBienPlace.
	 */
	public void setNombreDePionBienPlace(int nombreDePionBienPlace)
	{
		this.nombreDePionBienPlace = nombreDePionBienPlace;
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ nombreDePionMalPlace.
	 * @return la valeur du champ nombreDePionMalPlace.
	 */
	public int getNombreDePionMalPlace()
	{
		return nombreDePionMalPlace;
	}



	/* ______________________________________________________ */
	/** Modifie la valeur du champ nombreDePionMalPlace.
	 * @param nombreDePionMalPlace la valeur à placer dans le champ nombreDePionMalPlace.
	 */
	public void setNombreDePionMalPlace(int nombreDePionMalPlace)
	{
		this.nombreDePionMalPlace = nombreDePionMalPlace;
	}
	
	
	
}

/*__________________________________________________________*/
/* Fin du fichier Resultat.java. */
/*__________________________________________________________*/