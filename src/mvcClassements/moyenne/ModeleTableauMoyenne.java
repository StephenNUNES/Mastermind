/*___________________________________________________________________________*/
/**
 * Fichier : ModeleTableauMoyenne.java
 *
 * Crée le 13 janv. 2014 à 09:38:54
 *
 * Auteur : BRUNEL Marjorie.
 */
package mvcClassements.moyenne;

import java.util.HashMap;
import java.util.Vector;

import mvcClassements.ModeleClassementAbstrait;

/*___________________________________________________________________________*/
/** Modele du tableau de classement de moyenne
 */
public class ModeleTableauMoyenne extends ModeleClassementAbstrait
{	
	/** serialVersionUID */
	private static final long serialVersionUID = 5768794609688604897L;

	/** map joueurs, map difficulte, nbcoupsjoués */
	private static HashMap<String, HashMap<String, Integer>> mapJoueursScore = null ;
	
	/** Instance unique du modèle de tableau de moyenne */
	private static ModeleTableauMoyenne instance;
	
	/*___________________________________________________________________________*/
	/** Constructeur du modele
	 */
	private  ModeleTableauMoyenne()
	{
		super();
		mapJoueursScore = new HashMap<>();
		this.addColumn("Moyenne (*)");
	}
	
	/* ______________________________________________________ */
	/** Permet d'obtenir l'instance unique du modèle
	 * @return l'instance unique
	 */
	public static ModeleTableauMoyenne getInstance()
	{
		if (instance == null)
			instance  = new ModeleTableauMoyenne();
		return instance;
	}
		
	/*___________________________________________________________________________*/
	/** Ajoute une ligne au tableau 
	 * @param pseudo Pseudonyme à ajouter
	 * @param nbCoupsJoues Moyenne associé au pseudo
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
		else if (mapJoueursScore.get(pseudo).containsKey(difficulte))
		{
			mapJoueursScore.get(pseudo).put(difficulte, mapJoueursScore.get(pseudo).get(difficulte) + nbCoupsJoues);
			vec.add(pseudo);
			vec.add(difficulte);
			vec.add(String.valueOf(mapJoueursScore.get(pseudo).get(difficulte)));
			if (this.getRowCount() > 0)
				this.removeRow(getRowCount()-1);
			this.addRow(vec);
		}
		else
		{
			mapJoueursScore.get(pseudo).put(difficulte, nbCoupsJoues);
			this.addRow(vec);
			vec.removeAllElements();
			vec.add(pseudo);
			vec.add(difficulte);
			vec.add(String.valueOf(nbCoupsJoues));
		}
	}

}


/*___________________________________________________________________________*/
/* Fin du fichier ModeleTableauMoyenne.java.
/*___________________________________________________________________________*/
