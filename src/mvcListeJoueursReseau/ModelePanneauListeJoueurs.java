/* ______________________________________________________ */
/**
 * Fichier : ModelePanneauListeJoueurs.java
 *
 * Créé le 27 févr. 2014 à 12:15:15
 *
 * Auteur : NUNES Stephen
 */
package mvcListeJoueursReseau;

import java.util.Observable;

/* ______________________________________________________ */
/** Modèle du panneau de la liste des joueurs de la partie réseau
 */
public class ModelePanneauListeJoueurs extends Observable
{
	/** Instance unique du modèle de panneau liste des joueurs */
	private static ModelePanneauListeJoueurs instance = null;
	
	/** Si la configuration de la partie est définie */
	private boolean configurationSetted = false;
	
	/* ______________________________________________________ */
	/** Constructeur privée
	 */
	private ModelePanneauListeJoueurs()
	{
		
	}
	
	/* ______________________________________________________ */
	/** Permet de récupérer l'instance unique de la classe
	 * @return l'instance unique
	 */
	public static ModelePanneauListeJoueurs getInstance()
	{
		if (instance == null)
		{
			instance = new ModelePanneauListeJoueurs();
		}
		return instance;
	}

	/* ______________________________________________________ */
	/** Retourne si la partie a été configuré pour la partie en réseau ou non
	 * @return un booléen signifiant si la configuration est définie
	 */
	public boolean isConfigurationSetted()
	{
		return configurationSetted;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ configurationSetted.
	 * @param configurationSetted la valeur à placer dans le champ configurationSetted.
	 */
	public void setConfigurationSetted(boolean configurationSetted)
	{
		this.configurationSetted = configurationSetted;
	}
	
	/* ______________________________________________________ */
	/** Notifie à la vue (Panneau) de désactiver le bouton parametrer
	 */
	public void desactiverBoutonParametrer()
	{
		setChanged();	
		notifyObservers("desactiveBoutonParametrer");
	}
	
	
}

/*__________________________________________________________*/
/* Fin du fichier ModelePanneauListeJoueurs.java. */
/*__________________________________________________________*/