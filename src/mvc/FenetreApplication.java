/* ______________________________________________________ */
/**
 * Fichier : FenetreApplication.java
 *
 * Créé le 2 févr. 2014 à 14:12:20
 *
 * Auteur : NUNES Stephen
 */
package mvc;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/* ______________________________________________________ */
/**
 * Fenêtre de l'application
 */
public class FenetreApplication extends JFrame
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = 6632077572766359552L;

	/** L'adaptateur de la fenêtre */
	AdaptateurFenetre adaptateur = new AdaptateurFenetre() ;
	
	/* ______________________________________________________ */
	/** Constructeur de la fenêtre de l'application
         * @param panneauApplication : la vue à transmettre
	 */
	public FenetreApplication(PanneauApplication panneauApplication)
	{
		super("Mastermind");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		try {
			setIconImage(ImageIO.read(new File("images_png/icone.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		getContentPane().add(panneauApplication);
		
		this.setPreferredSize(new Dimension(1000, 600));
		definirProprieteFenetre();
	}

	/* ______________________________________________________ */
	/** Défini les propriétés de la fenêtre
	 */
	private void definirProprieteFenetre()
	{
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// ajoute l'adaptateur de la fenêtre
		addWindowListener(adaptateur);
		pack();
		setLocationRelativeTo(null);		
	}
}

/*__________________________________________________________*/
/* Fin du fichier FenetreApplication.java. */
/*__________________________________________________________*/