/* ______________________________________________________ */
/**
 * Fichier : ControleurPanneau.java
 *
 * Créé le 2 févr. 2014 à 14:16:00
 *
 * Auteur : NUNES Stephen
 */
package mvcMenuPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import metier.Persistance;
import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/**
 * Controleur de l'affichage des différents panneaux de l'application
 */
public class ControleurMenuPincipal implements ActionListener, IConstantesGraphiques
{
	/** ModelePanneau */
	private ModelePanneau modelePanneau = null;
	
	/**
	 * Constructeur de la classe ControleurMenuPincipal
	 * @param modeleP Modèle du panneau
	 */
	public ControleurMenuPincipal(ModelePanneau modeleP)
	{
		this.modelePanneau = modeleP;
	}

	/**
	 * Déclenche les actions lorsque l'évènement se déclenche
	 * @param evt : évènement qui se déclenche
	 */
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getSource() instanceof JButton)
		{
			JButton bouton = (JButton) evt.getSource() ;
			String texte = bouton.getText() ;
			if (texte.equals(BTN_SOLO))
				modelePanneau.setChoix(MENU_PSEUDO) ;
			else if (texte.equals(BTN_MULTI))
				modelePanneau.setChoix(MENU_MULTIJOUEURS);
			else if (texte.equals(BTN_CLASSEMENT_HISTORIQUE))
				modelePanneau.setChoix(CLASSEMENT_HISTORIQUE);
			else if (texte.equals(BTN_CLASSEMENT_MOYENNE))
				modelePanneau.setChoix(CLASSEMENT_MOYENNE);
			else if (texte.equals("Reprendre"))
				Persistance.getInstance().reprendrePartie();
		}	
	}

}
/*__________________________________________________________*/
/* Fin du fichier ControleurPanneau.java. */
/*__________________________________________________________*/