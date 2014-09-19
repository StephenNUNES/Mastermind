/*________________________________________________________*/
/**
 * Fichier : Main.java
 *
 * Cr�� le 07 d�cembre 2013 � 14:30:10
 *
 * Auteur : Arthur CHAMBRIARD
 */
package controleur;

import mvc.FenetreApplication;
import mvc.PanneauApplication;


/*________________________________________________________*/
/** 
 * Cette classe permet de lancer la fen�tre.
 */
public class Main
{

	/**
	 * Main pour lancer la fenêtre
	 * @param args
	 */
	public static void main(String[] args)
	{
		/* Création du panneau */
		PanneauApplication vue = new PanneauApplication() ;
		
		/* Création du cadre */
		FenetreApplication fen = new FenetreApplication(vue);
		fen.setVisible(true);
	}
}
/*________________________________________________________*/
/* Fin du fichier Main.java.
/*________________________________________________________*/