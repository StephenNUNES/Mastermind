/**
 * Fichier : ControleurListeJoueursReseau.java
 *
 * Créé le 10 févr. 2014 à 08:30:33
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcChoixPartieReseau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.BindException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import mvc.ModelePanneau;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/** Contrôleur du choix de la partie en réseau
 */
public class ControleurChoixPartieReseau implements ActionListener, IConstantesGraphiques
{
	/** ModelePanneau */
	private ModelePanneau modelePanneau = null;
	
	/** ModelePanneau */
	private ModeleChoixPartieReseau modeleChoixPartieReseau = null;
	
	/**
	 * Constructeur de la classe ControleurMenuDeDifficulte
	 * @param modeleP Modèle du panneau de la fenêtre
	 * @param modeleCPR Modèle du choixPartieRéseau
	 */
	public ControleurChoixPartieReseau(ModelePanneau modeleP, ModeleChoixPartieReseau modeleCPR)
	{
		this.modelePanneau = modeleP;
		this.modeleChoixPartieReseau = modeleCPR;
	}
	
	/**
	 * Déclenche les actions lorsque l'évènement se déclenche
	 * @param evt : événement qui se déclenche
	 */
	public void actionPerformed(ActionEvent evt)
	{
		JButton bouton = (JButton) evt.getSource() ;
		String texte = bouton.getText() ;
		if (texte.equals(BTN_RETOUR))
			modelePanneau.setChoix(MENU_PRINCIPAL);
		if (texte.equals(BTN_CREER_SERVEUR))
		{
			try
			{
				modeleChoixPartieReseau.creerServeur();
				modeleChoixPartieReseau.getServeurCree().creerSocketAttente();
				modeleChoixPartieReseau.getServeurCree().confirmationConnexion();
				modeleChoixPartieReseau.getServeurCree().lancerThread();
			} catch (BindException e)
			{
				System.err.println("Port pour le serveur déjà utilisé " + e);
				JOptionPane.showMessageDialog(null, "Port utilisé", "Impossible de créer la partie multi-joueurs !", JOptionPane.ERROR_MESSAGE);
			} catch (UnknownHostException e)
			{
				System.err.println("Nom de serveur inconnu " + e);
				JOptionPane.showMessageDialog(null, "Nom de serveur inconnu", "Impossible de rejoindre le serveur", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e)
			{
				System.err.println("Erreur de création de ServerSocket " + e);
				JOptionPane.showMessageDialog(null, "Erreur inconnue", "Impossible de créer la partie multi-joueurs !", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (texte.equals(BTN_REJOINDRE_SERVEUR))
		{
			modelePanneau.setChoix(MENU_RESEAU);
		}
	}
}


/*________________________________________*/
/* Fin du fichier ControleurListeJoueursReseau.java
/*________________________________________*/