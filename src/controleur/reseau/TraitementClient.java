/* ______________________________________________________ */
/**
 * Fichier : TraitementJoueur.java
 *
 * Créé le 14 janv. 2014 à 18:11:42
 *
 * Auteur : NUNES Stephen
 */
package controleur.reseau;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import constantes.IConstantesCommunication;

/* ______________________________________________________ */
/** Traite les connexions client au serveur
 */
public class TraitementClient implements Runnable, IConstantesCommunication
{
	/** Nom du client traité */
	private String nomClient;

	/** Flux de sortie du client */
	private DataOutputStream fluxSortie;

	/** Flux d'entrée du client */
	private DataInputStream fluxEntree;

	/** Thread lancé pour le TraitementClient */
	private Thread threadCourant;

	/** Dictionnaire permettant de stocker les joueurs */
	private static Map<String, TraitementClient> joueurs = new HashMap<>();

	/* ______________________________________________________ */
	/** Constructeur du traitement client prennant la socket du client en paramètre
	 * @param socketClient Socket du client
	 */
	public TraitementClient(Socket socketClient)
	{
		/*creerFluxCommunicationTraitementClient(socketClient);
		ajouterClientCorrespondant();
		lancerThread();*/
	}

	/* ______________________________________________________ */
	/** Ajoute le Client correspondant à cette instance de TraitementClient à la Map static
	 * @throws IOException Erreurs d'entrée sortie
	 */
	public void ajouterClientCorrespondant() throws IOException
	{
		nomClient = fluxEntree.readUTF();
		fluxSortie.writeByte(CORRECT);
		joueurs.put(nomClient, this);
	}

	/* ______________________________________________________ */
	/** Crée les flux de communication coté serveur pour communiquer avec le client
	 * @param socketClient la socket du client
	 * @throws IOException Erreurs d'entrée / sortie sur l'ouverture des flux du TraitementClient
	 */
	public void creerFluxCommunicationTraitementClient(Socket socketClient) throws IOException
	{
		fluxEntree = new DataInputStream(socketClient.getInputStream());
		fluxSortie = new DataOutputStream(socketClient.getOutputStream());
	}

	/* ______________________________________________________ */
	/** Redéfinition de la méthode run qui est lancé dans le thread
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		try
		{
			nouvelleConnexion();
		} catch (IOException e1)
		{
			System.err.println("Erreur E/S sur l'utilisation des flux lors d'une nouvelle connexion" + e1);
		}
		while (true)
		{
			try
			{
				byte code = fluxEntree.readByte();
				if (code == DESACTIVE_BOUTON_PARAMETRE)
				{
					FacadeReseau.getInstance().getServeurJeu().avertirTousClientDesactiverParametrer();
				}
				else if (code == OUVRIR_JEU_MASTERMIND)
				{
					String chaineRecu = fluxEntree.readUTF();
					FacadeReseau.getInstance().getServeurJeu().avertirTousClientOuvrirJeuMastermind(chaineRecu);
				}
				else if (code == COPIER_LISTE_JOUEURS)
				{
					FacadeReseau.getInstance().getServeurJeu().avertirTousClientCopierListeJoueurs();
				}
				else if (code == COUP_JOUE_SUPPLEMENTAIRE)
				{
					String nomClientCoupJoue = fluxEntree.readUTF();
					FacadeReseau.getInstance().getServeurJeu().avertirTousClientNouveauCoupJoue(nomClientCoupJoue);
				}
			} catch (IOException e)
			{
				System.err.println("Erreur E/S sur la reception d'un message du client par le TraitementClient"  + e);
				break;
			}
		}

	}

	/* ______________________________________________________ */
	/** Averti les clients déjà connectés qu'un nouveau client vient de se connecté
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	private void nouvelleConnexion() throws IOException
	{
		for (TraitementClient monTraitementClient : joueurs.values())
		{
			if (monTraitementClient.nomClient != nomClient)
			{
				monTraitementClient.fluxSortie.writeByte(NOUVELLE_CONNEXION);
				monTraitementClient.fluxSortie.writeUTF(nomClient);
				fluxSortie.writeByte(NOUVELLE_CONNEXION);
				fluxSortie.writeUTF(monTraitementClient.nomClient);
			}	
		}
	}

	/* ______________________________________________________ */
	/** Lance le thread où s'effectue la traitement de la méthode run() 
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
	/** Avertie le client qu'il traite que le bouton parametrer match doit être désactivé
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirClientTraiteDesactiverParametrer() throws IOException
	{
		fluxSortie.writeByte(DESACTIVE_BOUTON_PARAMETRE);
	}

	/* ______________________________________________________ */
	/** Avertie le client traité que le match doit démarré, en lui fournissant difficulté ainsi que le nombre de ronde et la solution
	 * @param informationsComplementaires difficulté du jeu, le nombre de ronde et la solution
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirClientTraiteOuvrirJeuMastermind(String informationsComplementaires) throws IOException
	{
		fluxSortie.writeByte(OUVRIR_JEU_MASTERMIND);
		fluxSortie.writeUTF(informationsComplementaires);
	}

	/* ______________________________________________________ */
	/** Averti le client traité par cette instance qu'il doit copier la liste des joueurs
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirClientTraiteCopierListeJoueurs() throws IOException
	{
		fluxSortie.writeByte(COPIER_LISTE_JOUEURS);
	}

	/* ______________________________________________________ */
	/** Averti le client traité par cette instance que le nom du client passé en paramètre à joué un nouveau coup
	 * @param nomClientCoupJoue Nom du client ayant joué un nouveau coup
	 * @throws IOException Erreurs d'entrée/sortie
	 */
	public void avertirClientTraiteNouveauCoupJoue(String nomClientCoupJoue) throws IOException
	{
		fluxSortie.writeByte(COUP_JOUE_SUPPLEMENTAIRE);
		fluxSortie.writeUTF(nomClientCoupJoue);

	}


}

/*__________________________________________________________*/
/* Fin du fichier TraitementJoueur.java. */
/*__________________________________________________________*/