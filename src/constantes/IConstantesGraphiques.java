/* ______________________________________________________ */
/**
 * Fichier : IConstantesGraphiques.java
 *
 * Créé le 27 janv. 2014 à 09:14:39
 *
 * Auteur : NUNES Stephen
 */
package constantes;

import java.awt.Font;

/* ______________________________________________________ */
/** Stockage des constantes graphiques devant être implémenté par toutes les fenêtres définissant le style des fenêtres
 */
public interface IConstantesGraphiques
{
	/* ______________________________________________________ */
	/**
	 * Constantes de la forme de l'application
	 */
	
	/** Police du texte des fenêtres */
	public static final Font POLICE_TEXTE = new Font("Segoe UI", 0, 14);
	
	/** Police des sous-titres des fenêtres */
	public static final Font POLICE_SS_TITRE = new Font("Segoe UI", Font.BOLD, 24);
	
	/** Police du titre des fenêtres */
	public static final Font POLICE_CHAMP_SAISIE = new Font("Segoe UI", 0, 20);
	
	/** Police des boutons */
	public static final Font POLICE_BOUTON = new Font("Segoe UI", Font.ITALIC, 18);
	
	/** Police des boutons */
	public static final Font POLICE_BOUTON_CLASSEMENT = new Font("Segoe UI", Font.ITALIC, 14);
	
	/** Police des indices en bas des tableaux de classement */
	public static final Font POLICE_INDICE = new Font("Arial", Font.BOLD, 9);
	/* ______________________________________________________ */
	
	
	
	
	/* ______________________________________________________ */
	/**
	 * Constantes des panneaux
	 */
	
	/** Menu Principal */
	public static final String MENU_PRINCIPAL = "MenuPrincipal" ;
	
	/** Solo ------> Choix pseudonyme */
	public static final String MENU_PSEUDO = "PanneauChoixPseudo" ;
	
	/** MenuDeDifficulte */
	public static final String MENU_DIFFICULTE = "PanneauMenuDeDifficulte" ;
	
	/** Plateau de jeu Mastermind */
	public static final String PLATEAU_JEU = "PlateauJeuMastermind" ;
	
	/** Menu Multi-joueurs */
	public static final String MENU_MULTIJOUEURS = "PanneauChoixPartieReseau" ;
	
	/** Rejoindre Partie Réseau */
	public static final String MENU_RESEAU = "PanneauRejoindrePartieReseau" ;
	
	/** Liste des joueurs en réseau */
	public static final String LISTE_JOUEURS_RESEAU = "PanneauListeJoueurs" ;
	
	/** Classement historique */
	public static final String CLASSEMENT_HISTORIQUE = "PanneauClassementHistorique";
	
	/** Classement moyenne */
	public static final String CLASSEMENT_MOYENNE = "PanneauClassementMoyenne";
	/* ______________________________________________________ */
	
	
	
	
	/* ______________________________________________________ */
	/**
	 * Constantes des intitulés de boutons
	 */
	
	/** Valider */
	public static final String BTN_VALIDER = "Valider";
	
	/** Retour */
	public static final String BTN_RETOUR = "Retour";
	
	/** Jeu solo */
	public static final String BTN_SOLO = "Jeu solo";
	
	/** Jeu multi-joueurs */
	public static final String BTN_MULTI = "Jeu multi-joueurs";
	
	/** Classement historique */
	public static final String BTN_CLASSEMENT_HISTORIQUE = "Classement historique";
	
	/** Classement moyenne */
	public static final String BTN_CLASSEMENT_MOYENNE = "Classement moyenne";
	
	/** Créer un serveur */
	public static final String BTN_CREER_SERVEUR = "Créer un serveur de jeu";
	
	/** Rejoindre un serveur */
	public static final String BTN_REJOINDRE_SERVEUR = "Rejoindre un serveur de jeu";
	
	/** Facile */
	public static final String BTN_FACILE = "Facile";
	
	/** Moyen */
	public static final String BTN_MOYEN = "Moyen";
	
	/** Difficile */
	public static final String BTN_DIFFICILE = "Difficile";
	
	/** Paramétrer un match */
	public static final String BTN_PARAMETRER_MATCH = "Paramétrer un match";
	/* ______________________________________________________ */
	
    
    /* ______________________________________________________ */
	/**
	 * Constantes des actions
	 */
	
	/** Permet de recréer le PanneauJeuMastermind */
	public static final String RECREER_PLATEAU_JEU = "recreerPanneauJeuMastermind";
	
	/** Action reçu de remonter une Exception */
	public static final String EXCEPTION = "Exception";
	
	/** Action reçu de remonter une BindException */
	public static final String BIND_EXCEPTION = "BindException";
	
	/** Action reçu de remonter un UnknownHostException */
	public static final String UNKNOWN_HOST_EXCEPTION = "UnknownHostException";
	
	/* ______________________________________________________ */
	/**
	 * Constantes des fichiers de persistance
	 */
	
	/** Nom du fichier */
	public static final String NOM_FICHIER = "resultats/fichierSauvegarde";
}

/*__________________________________________________________*/
/* Fin du fichier IConstantesGraphiques.java. */
/*__________________________________________________________*/