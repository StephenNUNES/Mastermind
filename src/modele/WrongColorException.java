/* ______________________________________________________ */
/**
 * Fichier : WrongColorException.java
 *
 * Crée le 6 déc. 2013 à 15:21:33
 *
 * Auteur : NUNES Stephen
 */
package modele;

/* ______________________________________________________ */
/** Exception déclenché qu'un pion est de mauvaise couleur
 */
public class WrongColorException extends Exception
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = 1L;

	/* ______________________________________________________ */
	/** Constructeur de l'exception 
	 * @param message Message ajouté lors de l'invocation de l'exception
	 */
	public WrongColorException(String message)
	{
		super(message);
	}
	
	/* ______________________________________________________ */
	/** Constructeur de l'exception 
	 */
	public WrongColorException()
	{
		new WrongColorException("Pion de mauvaise couleur");
	}
	
	
}

/*__________________________________________________________*/
/* Fin du fichier WrongColorException.java. */
/*__________________________________________________________*/