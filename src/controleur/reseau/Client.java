/* ______________________________________________________ */
/**
 * Fichier : Client.java
 *
 * Créé le 15 janv. 2014 à 10:29:44
 *
 * Auteur : NUNES Stephen
 */
package controleur.reseau;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import metier.Combinaison;
import metier.Pion;
import modele.WrongColorException;
import mvc.ModelePanneau;
import mvcListeJoueursReseau.ModeleListeJoueurs;
import mvcListeJoueursReseau.ModelePanneauListeJoueurs;
import mvcMenuDifficulte.ModeleMenuDeDifficulte;
import mvcPlateauJeuMastermind.ModeleListeJoueurJeuMastermind;
import constantes.IConstantesCommunication;
import constantes.IConstantesGraphiques;
import controleur.ConfigurationJeu;
import controleur.Difficulte;
import controleur.Facade;
import controleur.Jeu;


/* ______________________________________________________ */
/** Création d'un Client lors du choix de la connexion au ServeurJeu
 */
public class Client implements Runnable, IConstantesCommunication, IConstantesGraphiques
{
	/** Instance du client */
	private static Client instance;

	/** Socket du serveur */
	private Socket socketClient;

	/** Nom de ce client */
	private String nomClient;

	/** Flux de sortie du client */
	private DataOutputStream fluxSortie;

	/** Flux d'entrée du client */
	private DataInputStream fluxEntree;

	/** Thread où est lancé le client */
	private Thread threadCourant;


	/* ______________________________________________________ */
	/** Constructeur du client, affectant son nom passé en paramètre
	 * @param nomClient Nom du Client
	 */
	public Client(String nomClient)
	{
		instance = this;
		this.nomClient = nomClient;
	}

	/* ______________________________________________________ */
	/** Retourne l'instance du client aux autres classes le demandant
	 * @return l'instance du client
	 */
	public static Client getInstance()
	{
		return instance;
	}

	/* ______________________________________________________ */
	/** Tentative de connexion du client au serveur
	 * @param nomServeur Adresse du serveur où l'on se connectera
	 * @throws UnknownHostException Nom du serveur inconnu
	 * @throws Exception Erreur sur la création de la socket
	 */
	public void connexionServeur(String nomServeur) throws UnknownHostException, Exception
	{
		socketClient = new Socket(nomServeur, PORT_DEFAUT);
	}

	/* ______________________________________________________ */
	/** Définition de la méthode run lancée lors de la création du thread
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				byte code = fluxEntree.readByte();
				if (code == NOUVELLE_CONNEXION)
				{
					nouveauConnecte();
				}
				else if (code == DESACTIVE_BOUTON_PARAMETRE)
				{
					ModelePanneauListeJoueurs.getInstance().setConfigurationSetted(true);
					ModelePanneauListeJoueurs.getInstance().desactiverBoutonParametrer();
				}
				else if (code == OUVRIR_JEU_MASTERMIND)
				{
					demarrerJeuPartieLocale();
				}
				else if (code == COPIER_LISTE_JOUEURS)
				{
					ModeleListeJoueurs.getInstance().transfererJoueursVersListeJoueursJeuMastermind();
				}
				else if (code == COUP_JOUE_SUPPLEMENTAIRE)
				{
					nouveauCoupJoue();
				}
			} catch (IOException e)
			{
				System.err.println("Erreur d'entrée sortie sur la réception d'un message par le Client " + e);
				try
				{
					fermerSocket();
				} catch (IOException e1)
				{
					System.err.println("Erreur d'entrée sortie sur la fermeture de la socket client " + e);
				}
				break;
			}

		}

	}

	/* ______________________________________________________ */
	/** Gestion d'un nouveau coup joué par un autre client
	 * @throws IOException Erreur d'entrée / sortie
	 */
	private void nouveauCoupJoue() throws IOException
	{
		
		String nomClientCoupJoue = fluxEntree.readUTF();
		if (!nomClientCoupJoue.equals(nomClient))
		{
			try {
				ModeleListeJoueurJeuMastermind.getInstance(false, false).incrementerNombreProposition(nomClientCoupJoue);
				ModeleListeJoueurJeuMastermind.getInstance(false, false).affichageJoueursListe();
			}
			catch (NullPointerException e)
			{
				System.err.println("Joueur déconnecté : " + nomClientCoupJoue);
			}
		}


	}

	/* ______________________________________________________ */
	/** Permet de démarrer le jeu en partie locale lorsque que cela nous est autorisé
	 * @throws IOException Erreur d'entrée / sortie 
	 */
	private void demarrerJeuPartieLocale() throws IOException
	{
		String informationsComplementaires;

		informationsComplementaires = fluxEntree.readUTF();
		String[] informationsParse = informationsComplementaires.split(";");
		Facade.getInstance(new Jeu(new ConfigurationJeu(new Difficulte(informationsParse[0]))));
		ModeleMenuDeDifficulte.getInstance().setNbRondes(Integer.parseInt(informationsParse[1]));

		// Crée la solution du Jeu
		Combinaison solution = new Combinaison();
		for (int i = 2; i < informationsParse.length; ++i)
		{
			try
			{
				solution.ajouterPion(new Pion(informationsParse[i]));
			} catch (WrongColorException e)
			{
				System.err.println("Erreur de couleurs " + e);
			}
		}
		ModelePanneau.getInstance().setChoix(RECREER_PLATEAU_JEU);
		Facade.getInstance().getJeu().setSolution(solution);
		ModelePanneau.getInstance().setChoix(PLATEAU_JEU);

	}

	/* ______________________________________________________ */
	/** Méthode lancée lorsque un autre client vient de se connecter
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	private void nouveauConnecte() throws IOException 
	{
		String nomClientNouveauConnecte;
		nomClientNouveauConnecte = fluxEntree.readUTF();
		ModeleListeJoueurs.getInstance().ajouterLigne(nomClientNouveauConnecte);
	}

	/* ______________________________________________________ */
	/** Lance le thread qui appelle la méthode run()
	 */
	public void lancerThread()
	{
		if (threadCourant == null)
		{
			threadCourant = new Thread(this);
			threadCourant.start();
		}
	}

	/* ______________________________________________________ */
	/** Initialise les flux de communication
	 * @throws IOException Problème d'entrée/sortie sur l'ouverture des flux de communication
	 * @throws Exception Problème sur la connexion du client
	 */
	public void creerFluxCommunication() throws IOException, Exception
	{
		fluxEntree = new DataInputStream(socketClient.getInputStream());
		fluxSortie = new DataOutputStream(socketClient.getOutputStream());
	}

	/* ______________________________________________________ */
	/** Ferme la socket du client
	 * @throws IOException Erreurs d'entrée / sortie sur la fermeture de la socket client
	 */
	public void fermerSocket() throws IOException
	{
		if (socketClient != null)
		{
			socketClient.close();
		}
	}

	/* ______________________________________________________ */
	/** Transmet le premier pour faire état de la connexion
	 * @throws IOException Erreur d'entrée / sortie sur le l'envoie d'un paquet
	 */
	public void transmettrePremierPaquet() throws IOException
	{
		fluxSortie.writeUTF(nomClient);
	}

	/* ______________________________________________________ */
	/** Avertie le serveur que le bouton paramétrer match doit être désactivé
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirDesactiverParametrer() throws IOException
	{
		fluxSortie.writeByte(DESACTIVE_BOUTON_PARAMETRE);
	}

	/* ______________________________________________________ */
	/** Averti le serveur que le jeu doit démarrer tout en communicant la difficulte et le nombre de ronde
	 * @param difficulteChoisi La difficulté choisie
	 * @param nbRondes Le nombre de ronde choisie
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirUtiliserPanneauJeuMastermind(String difficulteChoisi, int nbRondes) throws IOException
	{
		fluxSortie.writeByte(OUVRIR_JEU_MASTERMIND);
		StringBuilder chaineConstruite = new StringBuilder();
		chaineConstruite.append(difficulteChoisi);
		chaineConstruite.append(";");
		chaineConstruite.append(nbRondes);
		for (String couleurPion : Facade.getInstance().getJeu().getSolution().getCouleurPions())
		{
			chaineConstruite.append(";");
			chaineConstruite.append(couleurPion);

		}
		fluxSortie.writeUTF(chaineConstruite.toString());		
	}

	/* ______________________________________________________ */
	/** Averti le serveur que la liste joueurs doit être copiée dans la liste joueur jeuMastermind
	 * @throws IOException Erreurs d'entrés/sorties
	 */
	public void avertirCopierListeJoueurs() throws IOException
	{
		fluxSortie.writeByte(COPIER_LISTE_JOUEURS);
	}

	/* ______________________________________________________ */
	/** Averti le serveur que le client envoyant ce paquet à joué un coup supplémentaire
	 * @throws IOException
	 */
	public void avertirCoupJoueSupplementaire() throws IOException
	{
		fluxSortie.writeByte(COUP_JOUE_SUPPLEMENTAIRE);
		fluxSortie.writeUTF(nomClient);
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ nomClient.
	 * @return la valeur du champ nomClient.
	 */
	public String getNomClient()
	{
		return nomClient;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ nomClient.
	 * @param nomClient la valeur à placer dans le champ nomClient.
	 */
	public void setNomClient(String nomClient)
	{
		this.nomClient = nomClient;
	}


}

/*__________________________________________________________*/
/* Fin du fichier Client.java. */
/*__________________________________________________________*/