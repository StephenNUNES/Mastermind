/* ______________________________________________________ */
/**
 * Fichier : AdaptateurFenetre.java
 *
 * Créé le 2 mars 2014 à 10:56:42
 *
 * Auteur : NUNES Stephen
 */
package mvc;

import java.awt.event.WindowAdapter;
import java.io.IOException;

import controleur.reseau.Client;
import controleur.reseau.FacadeReseau;

/* ______________________________________________________ */
/** L'adaptateur de la fenêtre de l'application
 */
public class AdaptateurFenetre extends WindowAdapter			
{
	/* ______________________________________________________ */
	/** Evénement déclenché lors de la fermeture de la fenêtre
	 * @param e
	 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(java.awt.event.WindowEvent e) 
	{
		if (Client.getInstance() != null)
		{
			try
			{
				Client.getInstance().fermerSocket();
			} catch (IOException e1)
			{
				System.err.println("Erreur d'entrée sortie sur la fermeture de la socket client" + e);
			}
		}
		if (FacadeReseau.getInstance().getServeurJeu() != null)
		{
			try
			{
				FacadeReseau.getInstance().getServeurJeu().fermerServeurSocket();
			} catch (IOException e1)
			{
				System.err.println("Erreur d'entrée sortie sur la fermeture de la socket serveur" + e1);
			}
		}
		System.out.println("Fin de l'application.") ;
		System.exit(0);
	}
}

/*__________________________________________________________*/
/* Fin du fichier AdaptateurFenetre.java. */
/*__________________________________________________________*/