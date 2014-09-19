/**
 * Fichier : ModeleMenuDeDifficulte.java
 *
 * Créé le 6 févr. 2014 à 15:25:22
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcPlateauJeuMastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JComboBox;

import metier.CoupJoue;
import modele.graphique.ImageCouleur;

/*_______________________________________________________*/
/**
 * Modèle du panneau Jeu mastermind
 */
public class ModeleJeuMastermind extends Observable
{
	/** Instance unique du modèle */
	private static ModeleJeuMastermind instance = null;
	
	/** Liste des coups joués sur une partie */
	private List<CoupJoue> coupJouePartie = null;
	
	/** Liste des comboBox de la fenêtre */
	private List<JComboBox<ImageCouleur>> listeCombo = null;
	
	/*_______________________________________________________*/
	/**
	 * Constructeur privé de la classe ModelePanneau
	 */
	private ModeleJeuMastermind()
	{
		coupJouePartie = new ArrayList<>();
	}
	
	/*_______________________________________________________*/
	/**
	 * Accesseur en lecture de l'instance unique du modèle
	 * @return l'instance unique du modèle
	 */
	public static ModeleJeuMastermind getInstance()
	{
		if (instance == null)
			instance = new ModeleJeuMastermind();
		return instance;
	}
	
	/* ______________________________________________________ */
	/** Ajoute un nouveau coup joué au modele
	 * @param coupJoueAjoute Le nouveau coup joué ajouté
	 * @return si le CoupJoue s'est bien ajouté à la liste
	 */
	public boolean ajouterCoupJoue(CoupJoue coupJoueAjoute)
	{
		setChanged();
		notifyObservers(coupJoueAjoute);
		
		return coupJouePartie.add(coupJoueAjoute);
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ listeCombo.
	 * @return la valeur du champ listeCombo.
	 */
	public List<JComboBox<ImageCouleur>> getListeCombo()
	{
		return listeCombo;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ listeCombo.
	 * @param listeCombo la valeur à placer dans le champ listeCombo.
	 */
	public void setListeCombo(List<JComboBox<ImageCouleur>> listeCombo)
	{
		this.listeCombo = listeCombo;
	}

	/* ______________________________________________________ */
	/** Evenement à notifier à la vue
	 * @param evenement Evenement passé à la vue du PanneauJeuMastermind
	 */
	public void evenementPartie(String evenement)
	{
		setChanged();
		notifyObservers(evenement);
	}
}
/*________________________________________*/
/* Fin du fichier ModeleMenuDeDifficulte.java
/*________________________________________*/