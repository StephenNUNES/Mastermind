/**
 * Fichier : PanneauApplication.java
 *
 * CrÃ©Ã© le 3 fÃ©vr. 2014 Ã  09:19:43
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvc;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modele.WrongColorException;
import mvcChoixPartieReseau.PanneauChoixPartieReseau;
import mvcClassements.historique.PanneauClassementHistorique;
import mvcClassements.moyenne.PanneauClassementMoyenne;
import mvcListeJoueursReseau.PanneauListeJoueurs;
import mvcMenuChoixPseudo.PanneauChoixPseudo;
import mvcMenuDifficulte.PanneauMenuDeDifficulte;
import mvcMenuPrincipal.PanneauMenuPrincipal;
import mvcPlateauJeuMastermind.PanneauJeuMastermind;
import mvcRejoindrePartieReseau.PanneauRejoindrePartieReseau;
import constantes.IConstantesGraphiques;
import controleur.Facade;

/*_______________________________________________________*/
/**
 * Panneau Ã  insÃ©rer dans la fenÃªtre application
 */
public class PanneauApplication extends JPanel implements Observer, IConstantesGraphiques
{
	/** Attribut de sérialisation */
	private static final long serialVersionUID = 1L;

	/** Liste des panneaux particuliers. */
	private Map<String, JPanel> listePanneauxParticuliers = null;
	
	/*_______________________________________________________*/
	/**
	 * Création de la fenêtre de l'application
	 */
	public PanneauApplication()
	{
		setLayout(new BorderLayout()) ;
		ModelePanneau.getInstance().addObserver(this);
		creerLesPanneauxParticuliers() ;
		add(listePanneauxParticuliers.get(MENU_PRINCIPAL), BorderLayout.CENTER) ;
	}
	
	
	/* ______________________________________________________ */
	/** Recrée le PanneauJeuMastermind
	 */
	public void recreerPanneauJeuMastermind()
	{
		try
		{
			Facade.getInstance().getJeu().creerSolution();
		} catch (WrongColorException e)
		{
			System.err.println("Mauvaise couleur " + e);
		}
		//ModeleListeJoueurJeuMastermind.getInstance(true, true);
		listePanneauxParticuliers.remove(PLATEAU_JEU);
		listePanneauxParticuliers.put(PLATEAU_JEU, new PanneauJeuMastermind());
	}
	
	/*_______________________________________________________*/
	/** Crée la table de hashage avec le nom des panneaux ainsi que leur instance
	 */
	private void creerLesPanneauxParticuliers()
	{
		listePanneauxParticuliers = new HashMap<String, JPanel>() ;
		listePanneauxParticuliers.put(MENU_PRINCIPAL, PanneauMenuPrincipal.getVue());
		listePanneauxParticuliers.put(MENU_PSEUDO, PanneauChoixPseudo.getVue());
		listePanneauxParticuliers.put(MENU_DIFFICULTE, PanneauMenuDeDifficulte.getVue());
		listePanneauxParticuliers.put(PLATEAU_JEU, PanneauJeuMastermind.getVue());
		listePanneauxParticuliers.put(MENU_MULTIJOUEURS, PanneauChoixPartieReseau.getVue());
		listePanneauxParticuliers.put(MENU_RESEAU, PanneauRejoindrePartieReseau.getVue());
		listePanneauxParticuliers.put(LISTE_JOUEURS_RESEAU, PanneauListeJoueurs.getVue());
		listePanneauxParticuliers.put(CLASSEMENT_HISTORIQUE, PanneauClassementHistorique.getVue());
		listePanneauxParticuliers.put(CLASSEMENT_MOYENNE, PanneauClassementMoyenne.getVue());
	}
	
	/*_______________________________________________________*/
	/**
	 * Permet de mettre Ã  jour la vue.
	 * @param modele Le modÃ¨le.
	 * @param info Information complÃ©mentaire.
	 */
	@Override
	public void update(Observable modele, Object info)
	{			
		if (modele instanceof ModelePanneau)
		{
			
			this.removeAll();
			ModelePanneau modelePanneau = (ModelePanneau) modele ;
			
			String choix = modelePanneau.getChoix();
			if (choix.equals(MENU_PSEUDO))
			{
				PanneauChoixPseudo pan = (PanneauChoixPseudo) listePanneauxParticuliers.get(choix) ;
				add(pan, BorderLayout.CENTER) ;
				pan.remiseAZeroChampPseudo();
			}
			else if (choix.equals(RECREER_PLATEAU_JEU))
			{
				recreerPanneauJeuMastermind();
				
			}
			else
			{
				add(listePanneauxParticuliers.get(choix), BorderLayout.CENTER);
			}
			
			updateUI();
		}
	}
}
/*________________________________________*/
/* Fin du fichier PanneauApplication.java
/*________________________________________*/