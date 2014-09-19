/* ______________________________________________________ */
/**
 * Fichier : Match.java
 *
 * Crée le 15 déc. 2013 à 21:23:06
 *
 * Auteur : NUNES Stephen
 */
package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* ______________________________________________________ */
/** Match constituant un ensemble de Ronde de jeu
 */
public class Match implements Serializable
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = -6480622667124577459L;
	
	/** La ou les rondes présentent dans un match */
	private List<Ronde> lesRondes = null;
	
	/* ______________________________________________________ */
	/** Constructeur d'un match
	 */
	public Match()
	{
		lesRondes = new ArrayList<>();
	}
	
	/* ______________________________________________________ */
	/** Ajout d'une nouvelle ronde au match
	 * @param nouvelleRonde La nouvelle ronde ajouté à liste
	 * @return si la ronde est ajouté à la liste
	 */
	public boolean ajouterRonde(Ronde nouvelleRonde)
	{
		return lesRondes.add(nouvelleRonde);
	}
	
	/* ______________________________________________________ */
	/** Permet d'obtenir une ronde du match
	 * @param index Index de la ronde dans la liste
	 * @return la ronde obtenu
	 */
	public Ronde getRonde(int index)
	{
		return lesRondes.get(index);
	}
	
	/* ______________________________________________________ */
	/** Permet d'obtenir la liste des rondes du match
	 * @return la liste des rondes 
	 */
	public List<Ronde> getLesRondes()
	{
		return new ArrayList<>(lesRondes);
	}
}

/*__________________________________________________________*/
/* Fin du fichier Match.java. */
/*__________________________________________________________*/