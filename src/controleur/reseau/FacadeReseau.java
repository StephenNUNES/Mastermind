/* ______________________________________________________ */
/**
 * Fichier : FacadeReseau.java
 *
 * Créé le 27 févr. 2014 à 11:57:40
 *
 * Auteur : NUNES Stephen
 */
package controleur.reseau;


/* ______________________________________________________ */
/** Interface des classes ayant la responsabilitée des communications
 */
public class FacadeReseau
{
	/** Instance de la facade */
	private static FacadeReseau instance = null;
	
	/** Serveur de jeu */
	private ServeurJeu monServeurJeu = null;
	
	/* ______________________________________________________ */
	/** Constructeur privée
	 */
	private FacadeReseau()
	{
		
	}
	
	/* ______________________________________________________ */
	/** Permet de retourné l'instance unique de la facade
	 * @return l'instance
	 */
	public static FacadeReseau getInstance()
	{
		if (instance == null)
		{
			instance = new FacadeReseau();
		}
		return instance;
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ monServeurJeu.
	 * @return la valeur du champ monServeurJeu.
	 */
	public ServeurJeu getServeurJeu()
	{
		return monServeurJeu;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ monServeurJeu.
	 * @param monServeurJeu la valeur à placer dans le champ monServeurJeu.
	 */
	public void setServeurJeu(ServeurJeu monServeurJeu)
	{
		this.monServeurJeu = monServeurJeu;
	}
	
	
}

/*__________________________________________________________*/
/* Fin du fichier FacadeReseau.java. */
/*__________________________________________________________*/