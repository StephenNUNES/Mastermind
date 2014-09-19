/* ______________________________________________________ */
/**
 * Fichier : Pion.java
 *
 * Crée le 6 déc. 2013 à 14:49:02
 *
 * Auteur : NUNES Stephen
 */
package metier;

import java.io.Serializable;

import modele.WrongColorException;
import controleur.Facade;


/* ______________________________________________________ */
/** Pion utilisé pour jouer au Mastermind
 */
public class Pion implements Serializable
{
	
	/** Numéro de sérialisation */
	private static final long serialVersionUID = -2715781623194533227L;
	
	/** Couleur d'un pion noté sous forme de caractères */
	private String couleur = null;

	/* ______________________________________________________ */
	/** Constructeur d'un pion définissant la couleur par défaut à noir
	 * @param couleur Couleur du pion
	 * @throws WrongColorException Mauvaise couleur de pion
	 */
	public Pion(String couleur) throws WrongColorException
	{
		setCouleur(couleur);
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ couleur.
	 * @return la valeur du champ couleur.
	 */
	public String getCouleur()
	{
		return new String(couleur);
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ couleur.
	 * @param couleur la valeur à placer dans le champ couleur.
	 * @throws WrongColorException Mauvaise couleur de pion
	 */
	private void setCouleur(String couleur) throws WrongColorException
	{
		if (!Facade.getInstance().getCouleursAutorises().contains(couleur))
		{
			throw new WrongColorException();
		}
		this.couleur = couleur;
	}
	
	
	/* ______________________________________________________ */
	/** Code de hachage d'un pion par sa couleur
	 * @return Le noueau code de hashage
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Integer.parseInt(couleur);
	}
	
	/* ______________________________________________________ */
	/** Redéfiniton du protocole d'égalité d'un Pion
	 * @param pionCompare Pion comparé à l'instance courante
	 * @return Si les deux instances sont identiques où non
	 */
	@Override
	public boolean equals(Object pionCompare)
	{
		if (this == pionCompare)
			return true;
		
		if (pionCompare instanceof Pion)
		{
			if (this.couleur.equals(((Pion) pionCompare).getCouleur()))
			{
				return true;
			}
		}
		return false;
	}
	
	/* ______________________________________________________ */
	/** Redéfinition du toString() affichant la couleur du pion
	 * @return la châine de caractère
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return couleur.toString();
	}
	
}

/*__________________________________________________________*/
/* Fin du fichier Pion.java. */
/*__________________________________________________________*/