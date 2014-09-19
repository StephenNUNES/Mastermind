/* ______________________________________________________ */
/**
 * Fichier : ModeleListeJoueurJeuMastermind.java
 *
 * Créé le 8 févr. 2014 à 10:23:25
 *
 * Auteur : NUNES Stephen
 */
package mvcPlateauJeuMastermind;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

/* ______________________________________________________ */
/** Modèle unique de la liste des joueurs du Mastermind
 */
public class ModeleListeJoueurJeuMastermind extends DefaultListModel<String>
{

	/** Numéro de sérialisation */
	private static final long serialVersionUID = 6507104322501658488L;

	/** Instance unique de la classe */
	private static ModeleListeJoueurJeuMastermind instance;
	
	/** Dictionnaire des joueurs jouant la partie avec leur nombre de proposition associé */
	private Map<String, Integer> joueursCoupJoue = null;
	
	/* ______________________________________________________ */
	/** Permet d'obtenir l'instance unique de cette classe
	 * @param isNewInstance Si l'instance doit être renouvelée
	 * @param razJoueurs Si la liste des joueurs doit être remise à zéro
	 * @return l'instance unique
	 */
	public static ModeleListeJoueurJeuMastermind getInstance(boolean isNewInstance, boolean razJoueurs)
	{
		if (instance == null)
		{
			instance = new ModeleListeJoueurJeuMastermind();
		}
		
		if (isNewInstance == true)
		{
			Set<String> listejoueurs = null;
			if (razJoueurs == false)
			{
				listejoueurs = instance.getJoueursCoupJoue().keySet();
			}
			instance = new ModeleListeJoueurJeuMastermind();
			if (listejoueurs != null)
			{
				for (String joueur : listejoueurs)
				{
					instance.ajouterJoueur(joueur);
				}
			}
		}
		return instance;
	}

	/* ______________________________________________________ */
	/** Constructeur privée de la classe
	 */
	private ModeleListeJoueurJeuMastermind()
	{
		joueursCoupJoue = new HashMap<>();
		addElement("");
	}
	
	/* ______________________________________________________ */
	/** Permet d'ajouter un joueurs à la Map
	 * @param nomJoueur Nom du joueur
	 */
	public void ajouterJoueur(String nomJoueur)
	{
		joueursCoupJoue.put(nomJoueur, 0);
	}
	
	/* ______________________________________________________ */
	/** Incrémente le le nombre de proposition de 1, du joueur passé en paramètre
	 * @param nomJoueur Nom du joueur où l'on doit incrémenter son score
	 * @throws NullPointerException Joueur déconnecté
	 */
	public void incrementerNombreProposition(String nomJoueur) throws NullPointerException
	{
		int ancienNombre = joueursCoupJoue.get(nomJoueur);
		joueursCoupJoue.put(nomJoueur, ancienNombre + 1);
		affichageJoueursListe();
	}
	
	/* ______________________________________________________ */
	/** Permet de re-créer l'affichage de la JList des joueurs
	 */
	public void affichageJoueursListe()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() 
			{
				clear();
		  		addElement("Joueur(s) : nombre coups joués");
		  		for (String joueur : joueursCoupJoue.keySet())
		  		{
		  			addElement(joueur + " a joué : " + joueursCoupJoue.get(joueur) + " coup(s)");
		  		}
		   }
		});
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ joueursCoupJoue.
	 * @return la valeur du champ joueursCoupJoue.
	 */
	public Map<String, Integer> getJoueursCoupJoue()
	{
		return joueursCoupJoue;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ joueursCoupJoue.
	 * @param joueursCoupJoue la valeur à placer dans le champ joueursCoupJoue.
	 */
	public void setJoueursCoupJoue(Map<String, Integer> joueursCoupJoue)
	{
		this.joueursCoupJoue = joueursCoupJoue;
	}
}

/*__________________________________________________________*/
/* Fin du fichier ModeleListeJoueurJeuMastermind.java. */
/*__________________________________________________________*/