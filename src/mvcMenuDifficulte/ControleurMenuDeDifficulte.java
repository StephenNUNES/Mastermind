/**
 * Fichier : ControleurMenuDeDifficulte.java
 *
 * Créé le 6 févr. 2014 à 15:23:13
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcMenuDifficulte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JSpinner;

import mvc.ModelePanneau;
import constantes.IConstantes;
import constantes.IConstantesGraphiques;
import controleur.ConfigurationJeu;
import controleur.Difficulte;
import controleur.Facade;
import controleur.Jeu;
import controleur.reseau.Client;

/**
 * Controleur du choix du pseudo
 */
public class ControleurMenuDeDifficulte implements ActionListener, IConstantesGraphiques, IConstantes
{	
	/** ModelePanneau */
	private ModelePanneau modelePanneau = null;
	
	/** Modèle de la difficultée */
	private ModeleMenuDeDifficulte modeleDifficulte = null;
	
	/** Champ texte contenant le nombre de ronde */
	private JSpinner champTexteNombreRonde = null;
	
	/** Renseigne la difficultée choisie */
	private String difficulteChoisi = null;
	
	/**
	 * Constructeur de la classe ControleurMenuDeDifficulte
	 * @param modeleP Modèle du panneau
	 * @param modeleD Modèle du menu de la difficultée
	 */
	public ControleurMenuDeDifficulte(ModelePanneau modeleP, ModeleMenuDeDifficulte modeleD)
	{
		this.modelePanneau = modeleP;
		this.modeleDifficulte = modeleD;
	}
	
	/* ______________________________________________________ */
	/** Permet de récupérer l'instance du champ texte permettant la saisie du nombre de ronde
	 * @param champTexteNombreRonde Champ texte récupéré
	 */
	public void recupererChampTexteNombreRonde(JSpinner champTexteNombreRonde)
	{
		this.champTexteNombreRonde = champTexteNombreRonde;
	}
	
	/**
	 * Déclenche les actions lorsque l'évènement se déclenche
	 * @param evt Evénement qui se déclenche
	 */
	public void actionPerformed(ActionEvent evt)
	{
		JButton bouton = (JButton) evt.getSource() ;
		String texte = bouton.getText() ;
		if (texte.equals(BTN_RETOUR))
			modelePanneau.setChoix(MENU_PRINCIPAL);
		
		if (texte.equals(BTN_FACILE))
		{
			difficulteChoisi = DIFF_FACILE;
			Facade.getInstance(new Jeu(new ConfigurationJeu(new Difficulte(DIFF_FACILE))));
			modelePanneau.setChoix(RECREER_PLATEAU_JEU);
			modelePanneau.setChoix(PLATEAU_JEU);
		}
		else if (texte.equals(BTN_MOYEN))
		{
			difficulteChoisi = DIFF_MOYENNE;
			Facade.getInstance(new Jeu(new ConfigurationJeu(new Difficulte(DIFF_MOYENNE))));
			modelePanneau.setChoix(RECREER_PLATEAU_JEU);
			modelePanneau.setChoix(PLATEAU_JEU);
		}
		else if (texte.equals(BTN_DIFFICILE))
		{
			difficulteChoisi = DIFF_DIFFICILE;
			Facade.getInstance(new Jeu(new ConfigurationJeu(new Difficulte(DIFF_DIFFICILE))));
			modelePanneau.setChoix(RECREER_PLATEAU_JEU);
			modelePanneau.setChoix(PLATEAU_JEU);
		}
		modeleDifficulte.setNbRondes((int) champTexteNombreRonde.getValue());
		// Test si la partie est en réseau
		if (Client.getInstance() != null)
		{
			try
			{
				Client.getInstance().avertirUtiliserPanneauJeuMastermind(difficulteChoisi, ModeleMenuDeDifficulte.getInstance().getNbRondes());
			} catch (IOException e)
			{
				System.err.println("Erreurs d'entrée/sortie lors de l'avertissement de changement de panneau " + e);
			}
		}
		
	}
}


/*________________________________________*/
/* Fin du fichier ControleurMenuDeDifficulte.java
/*________________________________________*/