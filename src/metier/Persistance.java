package metier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import mvc.ModelePanneau;
import mvcPlateauJeuMastermind.ModeleJeuMastermind;
import mvcPlateauJeuMastermind.ModeleListeJoueurJeuMastermind;
import constantes.IConstantesGraphiques;
import controleur.ConfigurationJeu;
import controleur.Difficulte;
import controleur.Facade;
import controleur.Jeu;

/** Classe qui gère la persistance de la partie en cours */
public class Persistance implements IConstantesGraphiques
{
	/** Instance unique de persistance */
	private static Persistance instance;
	
	/** Modèle du jeu */
	private ModeleJeuMastermind modeleJeu;
	
	/** Modèle du panneau */
	private ModelePanneau modelePanneau;
	
	/** Méthode de récupération de l'instance unique 
	 * @return retourne une instance de Persistance
	 */
	public static Persistance getInstance()
	{
		if(instance == null)
			instance = new Persistance();
		return instance;
	}
	
	
	/** Constructeur */
	private Persistance()
	{
		modelePanneau = ModelePanneau.getInstance();
		modeleJeu = ModeleJeuMastermind.getInstance();
	}
	
	
	/** Méthode qui sauvegarde la partie dans un fichier binaire */
	public void sauvegarderPartie()
	{
		File fic = new File(NOM_FICHIER);
		fic.getParentFile().mkdirs() ;
		
		try
		{
			ObjectOutputStream ecrivain = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(NOM_FICHIER)));
			// Ecriture du modele
			ecrivain.writeObject(ModeleListeJoueurJeuMastermind.getInstance(false, false).getJoueursCoupJoue());
			// Ecriture du joueur
			ecrivain.writeObject(Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().
					getNombreRondeJoue()).getJoueur(0));
			// Ecriture de la difficulté
			ecrivain.writeObject(Facade.getInstance().getJeu().getConfigJeu().getDifficulteJeu());
			// Ecriture de la solution
			ecrivain.writeObject(Facade.getInstance().getJeu().getSolution());
			// Ecriture de la liste des coups joués
			ecrivain.writeObject(Facade.getInstance().getJeu().getMatchDuJeu().getRonde(0).getJoueur(0).getTourDeJeu(0).getLesCoupJoue());
			
			System.out.println("Ecriture fichier réussie");
			ecrivain.close();
		} catch (IOException e)
		{
			System.out.println("Erreur d'E/S (" + e.getClass().getName() + ") : " + e.getMessage());
			return;
		}
	}
	
	
	/** Méthode qui permet de reprendre la partie sauvegardée */
	@SuppressWarnings("unchecked")
	public void reprendrePartie()
	{
		Map<String, Integer> map;
		Joueur j;
		Difficulte diff;
		Combinaison solution;
		List<CoupJoue> coups;
		ObjectInputStream lecteur = null;
		
		try
		{
			lecteur = new ObjectInputStream(new BufferedInputStream(new FileInputStream(NOM_FICHIER)));
			map = (Map<String, Integer>) lecteur.readObject();
			j = (Joueur) lecteur.readObject();
			diff = (Difficulte) lecteur.readObject();
			solution = (Combinaison) lecteur.readObject();
			coups = (List<CoupJoue>) lecteur.readObject();
			lecteur.close();
		} catch (FileNotFoundException e) {
			System.err.println("Erreur : fichier introuvable (" + e.getClass().getName() + ") : " + e.getMessage());
			return;
		} catch (IOException e) {
			System.err.println("Erreur d'ouverture du flux (" + e.getClass().getName() + ") : " + e.getMessage());
			return;
		} catch (ClassNotFoundException e) {
			System.err.println("Classe inconnue (" + e.getClass().getName() + ") : " + e.getMessage());
			try
			{
				lecteur.close();
			} catch (IOException e1)
			{
				System.err.println("Erreur de fermeture du flux (" + e.getClass().getName() + ") : " + e.getMessage());
				return;
			}
			return;
		}
		
		recreerLeMatch(map, j, diff, solution, coups);
		
		System.out.println("Récupération terminée");
		File f = new File(NOM_FICHIER);
		if(f.exists())
			f.delete();
		System.out.println("Fichier supprimé");

	}
	

	/* ______________________________________________________ */
	/** Permet de recréer le match dans un match
	 * @param map table de paires clé/valeurs possedant les coups joués
	 * @param joueur Joueur ayant joué les différents coups
	 * @param difficulte Difficulté de la partie à reprendre
	 * @param solution Solution à devinier dans la partie à reprendre
	 * @param liste Liste des coup déjà joués
	 */
	private void recreerLeMatch(Map<String, Integer> map, Joueur joueur, Difficulte difficulte, Combinaison solution, List<CoupJoue> liste)
	{
		Facade.getInstance(new Jeu(new ConfigurationJeu(new Difficulte(difficulte.getLibelle()))));
		modelePanneau.setChoix(RECREER_PLATEAU_JEU);
		modelePanneau.setChoix(PLATEAU_JEU);
		
		ModeleListeJoueurJeuMastermind.getInstance(false, false).setJoueursCoupJoue(map);
		Facade.getInstance().getJeu().setSolution(solution);
		Facade.getInstance().getJeu().creerLeMatch();//.getRonde(0);
		Facade.getInstance().getJeu().getMatchDuJeu().ajouterRonde(new Ronde());
		Facade.getInstance().getJeu().getMatchDuJeu().getRonde(0).ajouterJoueur(joueur);
		Facade.getInstance().getJeu().getMatchDuJeu().getRonde(0).getJoueur(0).creerTourDeJeu(new TourDeJeu());
		for (CoupJoue c : liste)
		{
			Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().
					getNombreRondeJoue()).getJoueur(0).getTourDeJeu(Facade.getInstance().
							getNombreRondeJoue()).ajouteCoupJoue(c);
			
			// Ajoute le CoupJoue au modèle ainsi qu'à la vue.
			//ModeleListeJoueurJeuMastermind.getInstance(false, false).incrementerNombreProposition((Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().getNombreRondeJoue()).getJoueur(0).getPseudonyme()));
			modeleJeu.ajouterCoupJoue(c);
		}
		ModeleListeJoueurJeuMastermind.getInstance(false, false).setJoueursCoupJoue(map);
	}
}
