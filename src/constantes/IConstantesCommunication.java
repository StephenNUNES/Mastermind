/* ______________________________________________________ */
/**
 * Fichier : ConstantesCommunication.java
 *
 * Créé le 27 janv. 2014 à 07:40:41
 *
 * Auteur : NUNES Stephen
 */
package constantes;

/* ______________________________________________________ */
/** Interface contenant les constantes de communication.
 * Les nombres négatifs signifient une erreur
 * Les nombres positifs notifient une information
 * 0 est un code par défaut signifiant que tout s'est bien passé
 */
public interface IConstantesCommunication
{
	/** Code signifiant qu'il n'y a eu aucun problème */
	public static final byte CORRECT = 0;
	
	/** Code de problème de connexion */
	public static final byte PROBLEME_CONNEXION = -1;
	
	/** Code signifiant une nouvelle connexion au serveur */
	public static final byte NOUVELLE_CONNEXION = 1;
	
	/** Code signifiant qu'un message est envoyé */
	public static final byte MESSAGE_ENVOIE = 2;
	
	/** Code signifiant que le bouton paramétrer un match doit être desactivé */
	public static final byte DESACTIVE_BOUTON_PARAMETRE = 3;
	
	/** Code signifiant aux autres clients qu'il faut ouvrir le PanneauJeuMastermind*/
	public static final byte OUVRIR_JEU_MASTERMIND = 4;
	
	/** Code signifiant aux autres clients qu'il faut copier la ListeJoueurs dans la ListeJoueursJeuMastermind */
	public static final byte COPIER_LISTE_JOUEURS = 5;
	
	/** Code signifiant aux autres clients que le client ayant envoyé ce code à joué un coup supplémentaire */
	public static final byte COUP_JOUE_SUPPLEMENTAIRE = 6;
	
	/**  Port utilisé par défaut */
	public static final int PORT_DEFAUT = 4444;

}

/*__________________________________________________________*/
/* Fin du fichier ConstantesCommunication.java. */
/*__________________________________________________________*/