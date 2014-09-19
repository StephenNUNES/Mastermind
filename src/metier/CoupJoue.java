package metier;

import java.io.Serializable;



/* ______________________________________________________ */
/** Stockage d'un coup joué contenant la Combinaison et la correction
 */
public class CoupJoue implements Serializable
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = -1121889049476213193L;

	/** Combinaison du coup joué */
	private Combinaison combinaisonJoue = null;
	
	/** Résultat du coup joué */
	private Resultat resultatObtenu = null;

	
	/* ______________________________________________________ */
	/** Permet d'obtenir la Combinaison du Coup joué
	 * @return la Combinaison du coup joué
	 */
	public Combinaison getCombinaison() {
		return combinaisonJoue;
	}

	/* ______________________________________________________ */
	/** Permet de définir la Combinaison du Coup joué
	 * @param Combinaison Combinaison du coup joué
	 */
	private void setCombinaison(Combinaison Combinaison) {
		this.combinaisonJoue = Combinaison;
	}
	
	
	/* ______________________________________________________ */
	/** Permet d'obtenir le résultat
	 * @return le résultatr du coup joué
	 */
	public Resultat getResultat() {
		return resultatObtenu;
	}
	
	/* ______________________________________________________ */
	/** Permet de définir le Resultat du Coup joué
	 * @param resultat Résultat du coup joué
	 */
	private void setResultat(Resultat resultat) {
		this.resultatObtenu = resultat;
	}
	
	/* ______________________________________________________ */
	/** Constructeur du coup joué
	 * @param p Combinaison du coup joué
	 * @param r Résultat du coup joué 
	 */
	public CoupJoue(Combinaison p, Resultat r)
	{
		setCombinaison(p);
		setResultat(r);
	}
}
