/*________________________________________________________*/
/**
 * Fichier : Couleur.java
 *
 * Cr�� le 09 d�cembre 2013 � 10:40:34
 *
 * Auteur : Arthur CHAMBRIARD
 */
package modele.graphique;

import javax.swing.ImageIcon;

/*________________________________________________________*/
/** 
 * Classe Couleur.
 */
public class ImageCouleur extends ImageIcon
{
	/** Attribut de sérialisation */
	private static final long serialVersionUID = 1L;
	
	/** Le nom de la couleur */
	String nomCouleur;
	
	/**
	 * Constructeur de la couleur
	 * @param nameColor : le nom dela couleur
	 */
	public ImageCouleur(String nameColor)
	{
		super(String.format("images_png/%s.png", nameColor));
		nomCouleur = nameColor;
	}

	/**
	 * Accesseur en lecteur pour le nom de la couleur
	 * @return le nom de la couleur
	 */
	public String getNomCouleur()
	{
		return nomCouleur;
	}
	
	/* ______________________________________________________ */
	/** Permet de retourner le nom de la couleur en prennant son chemin d'ImageCouleur en paramètre
	 * @param cheminImageCouleur
	 * @return le nom de la couleur
	 */
	public static String extraireNomCouleur(String cheminImageCouleur)
	{
		String[] res1 = cheminImageCouleur.split("/");
		String[] res2 = res1[1].split("\\.");
		return res2[0];
		
	}
}
/*________________________________________________________*/
/* Fin du fichier Mastermind.java.
/*________________________________________________________*/

