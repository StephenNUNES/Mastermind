/**
 * Fichier : ControleurListeJoueursReseau.java
 *
 * Créé le 10 févr. 2014 à 08:30:33
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcListeJoueursReseau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;
import controleur.reseau.Client;

/* ______________________________________________________ */
/**
 */
public class ControleurListeJoueursReseau implements ActionListener, IConstantesGraphiques
{
	/** Modèle de tous les panneaux de l'application */
	private ModelePanneau modelePanneau = null;
	
	/** Modèle du panneau de la liste des joueurs */
	//private ModelePanneauListeJoueurs modelePanneauListeJoueurs = null;
	
	/**
	 * Constructeur de la classe ControleurMenuDeDifficulte
	 * @param modeleP Modèle des panneaux de la fenêtre
	 * @param modelePLJ Modèle du panneau de la liste
	 */
	public ControleurListeJoueursReseau(ModelePanneau modeleP, ModelePanneauListeJoueurs modelePLJ)
	{
		this.modelePanneau = modeleP;
		//this.modelePanneauListeJoueurs = modelePLJ;
	}
	
	/**
	 * Déclenche les actions lorsque l'évènement se déclenche
	 * @param evt : évènement qui se déclenche
	 */
	public void actionPerformed(ActionEvent evt)
	{
		JButton bouton = (JButton) evt.getSource() ;
		String texte = bouton.getText() ;
		if (texte.equals(BTN_RETOUR))
			modelePanneau.setChoix(MENU_PRINCIPAL);
		else if (texte.equals(BTN_PARAMETRER_MATCH))
		{
			try
			{
				Client.getInstance().avertirCopierListeJoueurs();
			} catch (IOException e1)
			{
				System.err.println("Erreur d'entrée sortie " + e1);
			}
			modelePanneau.setChoix(MENU_DIFFICULTE);
			try
			{
				Client.getInstance().avertirDesactiverParametrer();
				
			} catch (IOException e)
			{
				System.err.println("Erreur d'entrée sortie " + e);
			}
		}
	}
}
/*________________________________________*/
/* Fin du fichier ControleurListeJoueursReseau.java
/*________________________________________*/