/* ______________________________________________________ */
/**
 * Fichier : ModeleListeJoueurs.java
 *
 * Créé le 1 févr. 2014 à 16:11:02
 *
 * Auteur : NUNES Stephen
 */
package mvcListeJoueursReseau;

import javax.swing.DefaultListModel;

import mvcPlateauJeuMastermind.ModeleListeJoueurJeuMastermind;

/* ______________________________________________________ */
/** Modele unique de la liste des joueurs d'un serveur
 */
public class ModeleListeJoueurs extends DefaultListModel<String>
{

	/** Numéro de sérialisation */
	private static final long serialVersionUID = 4104986733256172216L;
	
	/** Instance unique de la classe */
	private static ModeleListeJoueurs instance;
	
	/* ______________________________________________________ */
	/** Permet d'obtenir l'instance unique de cette classe
	 * @return l'instance unique
	 */
	public static ModeleListeJoueurs getInstance()
	{
		if (instance == null)
			instance = new ModeleListeJoueurs();
		return instance;
	}

	/* ______________________________________________________ */
	/** Constructeur privée de la classe
	 */
	private ModeleListeJoueurs()
	{
		addElement("");
	}
	
	/* ______________________________________________________ */
	/** Ajoute une ligne à la liste
	 * @param ligne ligne ajouté
	 */
	public void ajouterLigne(String ligne)
	{
		addElement(ligne);
		
	}

	/* ______________________________________________________ */
	/** Permet de transférer la liste des joueurs de cette liste vers la liste des joueurs du panneau JeuMastermind
	 */
	public void transfererJoueursVersListeJoueursJeuMastermind()
	{
		ModeleListeJoueurJeuMastermind.getInstance(true, true);
		for (int i = 0; i < getSize(); i++)
		{
			if (getElementAt(i) != "")
			{
				ModeleListeJoueurJeuMastermind.getInstance(false, false).ajouterJoueur(getElementAt(i));
			}
		}
	}
}

/*__________________________________________________________*/
/* Fin du fichier ModeleListeJoueurs.java. */
/*__________________________________________________________*/