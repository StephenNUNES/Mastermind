/**
 * Fichier : ControleurRejoindrePartieReseau.java
 *
 * Créé le 9 févr. 2014 à 23:30:59
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcRejoindrePartieReseau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import mvc.ModelePanneau;
import mvcListeJoueursReseau.ModeleListeJoueurs;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/** Contrôleur de rejoindre une partie en réseau
 */
public class ControleurRejoindrePartieReseau implements IConstantesGraphiques, ActionListener, KeyListener
{
	/** ModelePanneau */
	private ModelePanneau modelePanneau = null;

	/** ModeleChoixPartieReseau */
	private ModeleRejoindrePartieReseau modeleRejoindrePartieReseau = null;

	/** Pseudonyme du joueur à créer */
	private JTextField pseudoClient = null;

	/** Nom du serveur à rejoindre */
	private JTextField nomServeur = null;

	/**
	 * Constructeur de la classe ControleurMenuDeDifficulte
	 * @param modeleP modèle de panneau
	 * @param modeleRPR  modèle de rejoindrePartieReseau
	 */
	public ControleurRejoindrePartieReseau(ModelePanneau modeleP, ModeleRejoindrePartieReseau modeleRPR)
	{
		this.modelePanneau = modeleP;
		this.modeleRejoindrePartieReseau = modeleRPR;
	}

	/**
	 * Récupère la valeur du jtextfield pseudo dans la vue
	 * @param champTextePseudo
	 */
	public void recupChampTextePseudo(JTextField champTextePseudo)
	{
		this.pseudoClient = champTextePseudo;
	}

	/**
	 * Récupère la valeur du jtextfield nom du serveur dans la vue
	 * @param champTexteNomServeur
	 */
	public void recupChampTexteNomServeur(JTextField champTexteNomServeur)
	{
		this.nomServeur = champTexteNomServeur;
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
		{
			modelePanneau.setChoix(MENU_PRINCIPAL);
		}
		else if (texte.equals(BTN_VALIDER) && !pseudoClient.getText().trim().isEmpty()  && !nomServeur.getText().trim().isEmpty())
		{
			gestionConnexionServeur();
			
		}
		else
		{
			UIManager.put("OptionPane.font", POLICE_TEXTE);
			UIManager.put("OptionPane.messageFont", POLICE_TEXTE);
			UIManager.put("OptionPane.buttonFont", POLICE_BOUTON_CLASSEMENT);
			JOptionPane.showMessageDialog(null, "Le pseudonyme et le nom du serveur sont obligatoires", "Impossible de lancer la partie !", JOptionPane.ERROR_MESSAGE);
		}
	}

	/*____________________________________________________ */
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
			if (!pseudoClient.getText().trim().isEmpty() && !nomServeur.getText().trim().isEmpty())
			{
				gestionConnexionServeur();
			}
			else
			{
				UIManager.put("OptionPane.font", POLICE_TEXTE);
				UIManager.put("OptionPane.messageFont", POLICE_TEXTE);
				UIManager.put("OptionPane.buttonFont", POLICE_BOUTON_CLASSEMENT);
				JOptionPane.showMessageDialog(null, "Le pseudonyme et le nom du serveur sont obligatoires", "Impossible de lancer la partie !", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/* ______________________________________________________ */
	/** S'occupe de la gestion de connexion du client au serveur
	 */
	public void gestionConnexionServeur()
	{
		// Création du client
		modeleRejoindrePartieReseau.creerClient(pseudoClient.getText());

		// Connexion au serveur du Client
		try {
			modeleRejoindrePartieReseau.getClientCree().connexionServeur(nomServeur.getText());
		} catch (UnknownHostException e1)
		{
			System.err.println("Nom du serveur inconnu " + e1);
			JOptionPane.showMessageDialog(null, "Nom du serveur inconnu", "Impossible de rejoindre la partie multi-joueurs !", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (Exception e1)
		{
			System.err.println("Erreur sur la création du socket du client" + e1);
			return;
		}
		ModeleListeJoueurs.getInstance().ajouterLigne(modeleRejoindrePartieReseau.getClientCree().getNomClient());

		try
		{
			modeleRejoindrePartieReseau.getClientCree().creerFluxCommunication();
		} catch (IOException e1)
		{
			System.err.println("Erreur E/S sur l'ouverture des flux coté client" + e1);
			return;
		} catch (Exception e1)
		{
			System.err.println("Erreur sur la connexion du Client " + e1);
			JOptionPane.showMessageDialog(null, "Serveur inexistant", "Impossible de se connecter au serveur !", JOptionPane.ERROR_MESSAGE);
			try
			{
				modeleRejoindrePartieReseau.getClientCree().fermerSocket();
			} catch (IOException e)
			{
				System.err.println("Erreur d'entrée sortie sur la fermeture de la socket client" + e);
			}
			return;
		}

		try
		{
			modeleRejoindrePartieReseau.getClientCree().transmettrePremierPaquet();
		} catch (IOException e1)
		{
			System.err.println("Erreur E/S sur la transmission de paquet " + e1);
			JOptionPane.showMessageDialog(null, "Erreur de communication", "Impossible de transmettre le paquet !", JOptionPane.ERROR_MESSAGE);
			try
			{
				modeleRejoindrePartieReseau.getClientCree().fermerSocket();
			} catch (IOException e)
			{
				System.err.println("Erreur d'entrée sortie sur la fermeture de la socket client" + e);
			}
			return;
		}

		modeleRejoindrePartieReseau.getClientCree().lancerThread();
		
		modelePanneau.setChoix(LISTE_JOUEURS_RESEAU);

		
	}
}


/*________________________________________*/
/* Fin du fichier ControleurRejoindrePartieReseau.java
/*________________________________________*/