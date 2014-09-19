/*___________________________________________________________________________*/
/**
 * Fichier : ModeleTableauHistorique.java
 *
 * Crée le 13 janv. 2014 à 10:36:38
 *
 * Auteur : BRUNEL Marjorie.
 */
package mvcClassements.historique;

import java.util.HashMap;
import java.util.Vector;

import mvcClassements.ModeleClassementAbstrait;

/*___________________________________________________________________________*/
/**
 */
public class ModeleTableauHistorique extends ModeleClassementAbstrait
{
	/** serialVersionUID */
	private static final long serialVersionUID = -8999339753714714979L;
	
	/** instance du modele */
	private static ModeleTableauHistorique instance;
	
	/** map joueurs, map difficulte, nbcoupsjoués */
	private static HashMap<String, HashMap<String, Integer>> mapJoueursScore = null ;
	
	/*___________________________________________________________________________*/
	/**Constructeur de tableau de meilleurs scores
	 */
	private  ModeleTableauHistorique()
	{
		super();
		mapJoueursScore = new HashMap<>();
		this.addColumn("Nombre de coups joués");
	}
	
	/*___________________________________________________________________________*/
	/** Méthode qui retourne l'instance du modèle du tableau meilleur score
	 * @return l'instance unique du modèle
	 */
	public static ModeleTableauHistorique getInstance()
	{
		if(instance == null)
			instance = new ModeleTableauHistorique();
		return instance;
	}

	/*___________________________________________________________________________*/
	/** Ajoute une ligne au tableau avec le pseudo et le score associé
	 * @param pseudo Pseudonyme du joueur à ajouter au tableau
	 * @param nbCoupsJoues Valeur du meilleur score
	 */
	@Override
	public void ajouterLigne(String pseudo, String difficulte, int nbCoupsJoues)
	{
		Vector<String> vec = new Vector<>() ;
		
		if (!mapJoueursScore.containsKey(pseudo))
		{
			mapJoueursScore.put(pseudo, new HashMap<String, Integer>());
			(mapJoueursScore.get(pseudo)).put(difficulte, nbCoupsJoues);
			vec.add(pseudo);
			vec.add(difficulte);
			vec.add(String.valueOf(nbCoupsJoues));
			this.addRow(vec);
		}
		else
		{
			vec.add("");
			vec.add(difficulte);
			vec.add(String.valueOf(nbCoupsJoues));
			this.addRow(vec);
		}
	}
}


/*___________________________________________________________________________*/
/* Fin du fichier ModeleTableauHistorique.java.
/*___________________________________________________________________________*/
