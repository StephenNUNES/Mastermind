/* ______________________________________________________ */
/**
 * Fichier : ServeurJeu.java
 *
 * Créé le 27 janv. 2014 à 07:49:51
 *
 * Auteur : NUNES Stephen
 */
package controleur.reseau;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import constantes.IConstantesCommunication;
import constantes.IConstantesGraphiques;

/* ______________________________________________________ */
/** Serveur d'un jeu créé par un joueur lors du choix du jeu en multi-joueurs
 */
public class ServeurJeu implements Runnable, IConstantesCommunication, IConstantesGraphiques
{
	/** Socket du serveur lui permettant de communiquer avec ses clients */
	private ServerSocket socketAttente;

	/** Thread où sera lancé le serveur */
	private Thread threadServeur;

	/** Liste des traitements des clients */
	private static List<TraitementClient> listeTraitementClient = new ArrayList<>();

	/* ______________________________________________________ */
	/** Constructeur du Serveur
	 */
	public ServeurJeu()
	{
	}	

	/* ______________________________________________________ */
	/** Crée la socket d'attente
	 * @throws BindException Port déjà utilisé
	 * @throws Exception Erreur sur la création de la socket
	 */
	public void creerSocketAttente() throws BindException, Exception
	{
		socketAttente = new ServerSocket(PORT_DEFAUT);
	}

	/* ______________________________________________________ */
	/** Boucle infini d'attente de connexion client
	 */
	private void attenteConnexionClient() 
	{
		Socket socketClient;
		TraitementClient monTraitementClient;
		while (true)
		{
			try
			{
				socketClient = socketAttente.accept();
			} catch (IOException e)
			{
				System.err.println("Erreur lors de l'attente de connexion de client " + e);
				try
				{
					fermerServeurSocket();
				} catch (IOException e1)
				{
					System.err.println("Erreur d'entrée sortie sur la fermeture de la socket serveur" + e);
				}
				break;
			}

			monTraitementClient = new TraitementClient(socketClient);
			listeTraitementClient.add(monTraitementClient);

			try
			{
				monTraitementClient.creerFluxCommunicationTraitementClient(socketClient);
			} catch (IOException e1)
			{
				System.err.println("Erreur E/S sur l'ouverture des flux du TraitementClient " + e1);
				break;
			}

			try
			{
				monTraitementClient.ajouterClientCorrespondant();
			} catch (IOException e)
			{
				System.err.println("Erreur E/S sur l'ajout du client aux connexions du serveur " + e);
				break;
			}
			monTraitementClient.lancerThread();
		}
	}

	/* ______________________________________________________ */
	/** Confirme si la connexion est possible au serveur, en affichant son adresse
	 * @throws UnknownHostException Nom de serveur non-trouvé
	 */
	public void confirmationConnexion() throws UnknownHostException
	{
		String[] adresseServeur;
		adresseServeur = InetAddress.getLocalHost().toString().split("/");
		JOptionPane.showMessageDialog(null, "Vous pouvez dès à présent rejoindre le serveur de jeu à l'adresse : " + adresseServeur[1], "Serveur démarré avec succès", JOptionPane.INFORMATION_MESSAGE);
	}

	/* ______________________________________________________ */
	/** Lance le thread où se trouve le serveur
	 */
	public void lancerThread()
	{
		if (threadServeur == null)
		{
			threadServeur = new Thread(this);
			threadServeur.start();
		}
	}


	/* ______________________________________________________ */
	/** Définition de la méthode run qui est appelé lors du lancement du thread
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
		attenteConnexionClient();
	}

	/* ______________________________________________________ */
	/** Ferme la socket d'attente de connexion du serveur
	 * @throws IOException Erreurs d'entrée / sortie sur la fermeture de la socket server
	 */
	public void fermerServeurSocket() throws IOException
	{
		if (socketAttente != null)
		{
			socketAttente.close();
		}
	}

	/* ______________________________________________________ */
	/** Avertie le client qu'il traite que le bouton parametrer match doit être désactivé
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirTousClientDesactiverParametrer() throws IOException
	{
		for (TraitementClient tc : listeTraitementClient)
		{
			tc.avertirClientTraiteDesactiverParametrer();
		}
	}

	/* ______________________________________________________ */
	/** Averti tous les clients de démarrer le jeu en leur transmettant la difficulté et le nombre de ronde
	 * @param informationsComplementaires Difficultée du match , nombre de ronde et solution
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirTousClientOuvrirJeuMastermind(String informationsComplementaires) throws IOException
	{
		for (TraitementClient tc : listeTraitementClient)
		{
			tc.avertirClientTraiteOuvrirJeuMastermind(informationsComplementaires);
		}

	}

	/* ______________________________________________________ */
	/** Averti tous les clients connectés qu'il faut copier la liste des joueurs
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirTousClientCopierListeJoueurs() throws IOException
	{
		for (TraitementClient tc : listeTraitementClient)
		{
			tc.avertirClientTraiteCopierListeJoueurs();
		}

	}

	/* ______________________________________________________ */
	/** Averti tous les clients connectés que le nom du joueur pasé en paramètre à joué un nouveau coup
	 * @param nomClientCoupJoue Nom du client ayant joué un nouveau
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirTousClientNouveauCoupJoue(String nomClientCoupJoue) throws IOException
	{
		for (TraitementClient tc : listeTraitementClient)
		{
			tc.avertirClientTraiteNouveauCoupJoue(nomClientCoupJoue);
		}

	}

	/* ______________________________________________________ 
	*//** Défini le style de la boîte de dialogue
	 *//*
	private void definirStyleBoiteDeDialogue()
	{
		UIManager.put("OptionPane.font", POLICE_TEXTE);
		UIManager.put("OptionPane.messageFont", POLICE_TEXTE);
		UIManager.put("OptionPane.buttonFont", POLICE_BOUTON_CLASSEMENT);
	}*/
}

/*__________________________________________________________*/
/* Fin du fichier ServeurJeu.java. */
/*__________________________________________________________*/