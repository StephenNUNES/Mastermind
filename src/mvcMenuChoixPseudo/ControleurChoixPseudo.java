/**
 * Fichier : ControleurMenuDeDifficulte.java
 *
 * Créé le 6 févr. 2014 à 15:23:13
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcMenuChoixPseudo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;

/**
 * Controleur du choix du pseudo
 */
public class ControleurChoixPseudo implements ActionListener, IConstantesGraphiques, KeyListener
{
	/** ModeleMenuDeDifficulte */
	private ModeleChoixPseudo modeleChoixPseudo = null;

	/** ModelePanneau */
	private ModelePanneau modelePanneau = null;

	/** Pseudonyme du joueur à créer */
	private JTextField pseudo = null;

	/**
	 * Constructeur de la classe ControleurMenuDeDifficulte
	 * @param modeleP Modèle du panneau
	 * @param modeleCP Modèle du choix du pseudo
	 */
	public ControleurChoixPseudo(ModelePanneau modeleP, ModeleChoixPseudo modeleCP)
	{
		this.modelePanneau = modeleP;
		this.modeleChoixPseudo = modeleCP;
	}

	/**
	 *  Permet de récupérer le champ texte du pseudo
	 * @param champTextePseudo
	 */
	public void recupChampTextePseudo(JTextField champTextePseudo)
	{
		this.pseudo = champTextePseudo;
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
			if (texte.equals(BTN_RETOUR))
				modelePanneau.setChoix(MENU_PRINCIPAL);
			else if (texte.equals(BTN_VALIDER) && !pseudo.getText().trim().isEmpty())
			{
				modeleChoixPseudo.creerJoueur(pseudo.getText());
				modelePanneau.setChoix(MENU_DIFFICULTE);
			}
			else
			{
				UIManager.put("OptionPane.font", POLICE_TEXTE);
				UIManager.put("OptionPane.messageFont", POLICE_TEXTE);
				UIManager.put("OptionPane.buttonFont", POLICE_BOUTON_CLASSEMENT);
				JOptionPane.showMessageDialog(null, "Le pseudonyme est obligatoire", "Impossible de lancer la partie !", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	/* ______________________________________________________ */
	/** 
	 * @param e
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{}


	/* ______________________________________________________ */
	/** 
	 * @param e
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{}


	/* ______________________________________________________ */
	/** 
	 * @param e
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e)
	{
		if (e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			if (!pseudo.getText().trim().isEmpty())
			{
				modeleChoixPseudo.creerJoueur(pseudo.getText());
				modelePanneau.setChoix(MENU_DIFFICULTE);
			}
			else
			{
				UIManager.put("OptionPane.font", POLICE_TEXTE);
				UIManager.put("OptionPane.messageFont", POLICE_TEXTE);
				UIManager.put("OptionPane.buttonFont", POLICE_BOUTON_CLASSEMENT);
				JOptionPane.showMessageDialog(null, "Le pseudonyme est obligatoire", "Impossible de lancer la partie !", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}


/*________________________________________*/
/* Fin du fichier ControleurMenuDeDifficulte.java
/*________________________________________*/