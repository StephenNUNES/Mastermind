/**
 * Fichier : ControleurClassementAbstrait.java
 *
 * Créé le 10 févr. 2014 à 08:30:33
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcClassements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/** Contrôleur de classement abstrait
 */
public class ControleurClassementAbstrait implements ActionListener, IConstantesGraphiques
{
	/** ModelePanneau */
	private ModelePanneau modelePanneau = null;
	
	/**
	 * Constructeur de la classe ControleurMenuDeDifficulte
	 * @param modeleP Modèle de panneau
	 */
	public ControleurClassementAbstrait(ModelePanneau modeleP)
	{
		this.modelePanneau = modeleP;
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
		if (texte.equals(BTN_PARAMETRER_MATCH))
		{
			modelePanneau.setChoix(MENU_DIFFICULTE);
		}
	}
}
/*________________________________________*/
/* Fin du fichier ControleurClassementAbstrait.java
/*________________________________________*/