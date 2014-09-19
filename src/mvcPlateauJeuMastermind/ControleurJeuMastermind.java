/**
 * Fichier : ControleurJeuMastermind.java
 *
 * Créé le 6 févr. 2014 à 14:10:11
 *
 * Auteur : Arthur CHAMBRIARD
 */
package mvcPlateauJeuMastermind;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import metier.Combinaison;
import metier.CoupJoue;
import metier.Joueur;
import metier.Persistance;
import metier.Pion;
import metier.Resultat;
import metier.Ronde;
import metier.TourDeJeu;
import modele.WrongColorException;
import modele.graphique.ImageCouleur;
import mvc.ModelePanneau;
import mvcClassements.historique.ModeleTableauHistorique;
import mvcClassements.moyenne.ModeleTableauMoyenne;
import mvcMenuChoixPseudo.ModeleChoixPseudo;
import mvcMenuDifficulte.ModeleMenuDeDifficulte;
import constantes.IConstantes;
import constantes.IConstantesGraphiques;
import controleur.Facade;
import controleur.reseau.Client;

/* ______________________________________________________ */
/**
 * Controleur du plateau de jeu du mastermind
 */
public class ControleurJeuMastermind implements ActionListener, IConstantesGraphiques, IConstantes
{
	/** ModeleMenuDeDifficulte */
	private ModeleJeuMastermind modeleJeu = null;
	
	/** ModelePanneau */
	private ModelePanneau modelePanneau = null;
	
	/**
	 * Constructeur de la classe ControleurMenuDeDifficulte
	 * @param modeleP Modèle du Panneau commun
	 * @param modeleJM Modèle du jeu du mastermind
	 */
	public ControleurJeuMastermind(ModelePanneau modeleP, ModeleJeuMastermind modeleJM)
	{
		this.modelePanneau = modeleP;
		this.modeleJeu = modeleJM;
		
		
	}
	
	/* ______________________________________________________ */
	/** Permet de récuperer les item sélectionné dans les comboBox du plateau et crée la proposition
	 * @param listeCombo Liste des comboBox en jeu 
	 * @return combinaison créée
	 * @throws WrongColorException Mauvaise couleur ajoutée
	 */
	private Combinaison creerCombinaison(List<JComboBox<ImageCouleur>> listeCombo) throws WrongColorException
	{
		Combinaison proposition = new Combinaison();
		for (JComboBox<ImageCouleur> combo : listeCombo)
		{
			proposition.ajouterPion(new Pion(ImageCouleur.extraireNomCouleur(combo.getSelectedItem().toString())));
		}
		return proposition;
	}
	
	/* ______________________________________________________ */
	/** Récupère la liste des comboBox pour l'affecter dans le modèle du jeuMastermind
	 * @param listeCombo La liste des combobBox
	 */
	public void recupererListeComboBox(List<JComboBox<ImageCouleur>> listeCombo)
	{
		modeleJeu.setListeCombo(listeCombo);
	}
	
	/* ______________________________________________________ */
	/** Permet de créer le tour de jeu pour le joueur actuel qui va jouer
	 */
	public void creerTourDeJeu()
	{
		if (ModeleChoixPseudo.getInstance().getJoueurCree() != null)
		{
			ModeleChoixPseudo.getInstance().getJoueurCree().creerTourDeJeu(new TourDeJeu());
		}
		
	}
	/**
	 * Déclenche les actions lorsque l'évènement se déclenche
	 * @param evt Evénement qui se déclenche
	 */
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getSource().equals(MENU_PRINCIPAL))
		{
			ModelePanneau.getInstance().setChoix(MENU_PRINCIPAL);
		}
		if (evt.getSource() instanceof JButton)
		{
			JButton bouton = (JButton) evt.getSource() ;
			String texte = bouton.getText();
			if (texte.equals("Valider"))
			{
				demarrerMatch();
				try
				{
					traitementJeu();
				} catch (WrongColorException e)
				{
					System.err.println("Erreur de mauvaise couleur dans les comboBox " + e);
				}
			}
			else if (texte.equals("Sauvegarder"))
			{
				Persistance.getInstance().sauvegarderPartie();
			}
			else if (texte.equals("Reprendre"))
			{
				Persistance.getInstance().reprendrePartie();
			}
			
		}
		
	}

	/* ______________________________________________________ */
	/** Démarre le match et crée le nombre de ronde necéssaire pour ce match
	 */
	private void demarrerMatch()
	{
		if (Facade.getInstance().getJeu().getMatchDuJeu() == null)
		{
			Facade.getInstance().getJeu().creerLeMatch();
		}
		if (Facade.getInstance().getJeu().getMatchDuJeu().getLesRondes().size() == 0)
		{			
			for (int i = 0; i < ModeleMenuDeDifficulte.getInstance().getNbRondes(); i++)
			{
				Facade.getInstance().getJeu().getMatchDuJeu().ajouterRonde(new Ronde());
				if (Client.getInstance() == null)
				{
					Facade.getInstance().getJeu().getMatchDuJeu().getRonde(i).ajouterJoueur(ModeleChoixPseudo.getInstance().getJoueurCree());
				}
				else
				{
					Facade.getInstance().getJeu().getMatchDuJeu().getRonde(i).ajouterJoueur(new Joueur(Client.getInstance().getNomClient()));
				}
				
				Facade.getInstance().getJeu().getMatchDuJeu().getRonde(i).getJoueur(0).creerTourDeJeu(new TourDeJeu());
			}
		}
		
	}

	/* ______________________________________________________ */
	/** Permet de gérer les différents traitement du jeu 
	 * @throws WrongColorException Mauvaise couleur lors de la récupération des comboBox
	 */
	private void traitementJeu() throws WrongColorException 
	{
		// Crée la combinaison proprosée et le résultat associé pour créer un CoupJoue
		Combinaison proposition = creerCombinaison(modeleJeu.getListeCombo());
		Resultat resultatCoup = Combinaison.comparerCombinaison(proposition, Facade.getInstance().getJeu().getSolution());
		
		CoupJoue coupJoueJoueur = new CoupJoue(proposition, resultatCoup);
		if (Client.getInstance() != null)
		{
			try
			{
				Client.getInstance().avertirCoupJoueSupplementaire();
			} catch (IOException e)
			{
				System.err.println("Erreurs d'entrée / sortie sur l'envoi de l'information d'un nouveau CoupJoue");
			}
		}
		
		// Ajoute le coup joué
		Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().getNombreRondeJoue()).getJoueur(0).getTourDeJeu(Facade.getInstance().getNombreRondeJoue()).ajouteCoupJoue(coupJoueJoueur);
		
		// Ajoute le CoupJoue au modèle ainsi qu'à la vue.
		ModeleListeJoueurJeuMastermind.getInstance(false, false).incrementerNombreProposition((Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().getNombreRondeJoue()).getJoueur(0).getPseudonyme()));
		//ModeleListeJoueurJeuMastermind.getInstance(false, false).affichageJoueursListe();
		modeleJeu.ajouterCoupJoue(coupJoueJoueur);
		
		// Test si le TourDeJeu est gagné
		if (resultatCoup.getNombreDePionBienPlace() == Facade.getInstance().getNombrePionDansCombinaison())
		{
			modeleJeu.evenementPartie(EVENT_GAGNE);
			ModeleTableauMoyenne.getInstance().ajouterLigne(Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().getNombreRondeJoue()).getJoueur(0).getPseudonyme(),
															Facade.getInstance().getJeu().getConfigJeu().getDifficulteJeu().getLibelle(),
				   											Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().getNombreRondeJoue()).getJoueur(0).getTourDeJeu(Facade.getInstance().getNombreRondeJoue()).getLesCoupJoue().size());
			
			ModeleTableauHistorique.getInstance().ajouterLigne(Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().getNombreRondeJoue()).getJoueur(0).getPseudonyme(),
															   Facade.getInstance().getJeu().getConfigJeu().getDifficulteJeu().getLibelle(),
															   Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().getNombreRondeJoue()).getJoueur(0).getTourDeJeu(Facade.getInstance().getNombreRondeJoue()).getLesCoupJoue().size());

			avertirFinRonde();
		}
		
		// Test si le TourDeJeu est perdu
		else if (Facade.getInstance().getJeu().getMatchDuJeu().getRonde(Facade.getInstance().getNombreRondeJoue()).getJoueur(0).getTourDeJeu(Facade.getInstance().getNombreRondeJoue()).getLesCoupJoue().size() > Facade.getInstance().getNombrePropositionPossible() - 1)
		{
			modeleJeu.evenementPartie(EVENT_PERDU);
			avertirFinRonde();
		}	
		
	}

	/* ______________________________________________________ */
	/** Avertie les autres classes que cette partie est gagnée ou perdue
	 */
	private void avertirFinRonde()
	{
		Facade.getInstance().setNombreRondeJoue(Facade.getInstance().getNombreRondeJoue() + 1);
		
		if (Facade.getInstance().getNombreRondeJoue() == ModeleMenuDeDifficulte.getInstance().getNbRondes())
		{
			Facade.getInstance().setNombreRondeJoue(0);
			modelePanneau.setChoix(MENU_PRINCIPAL);
			ModeleListeJoueurJeuMastermind.getInstance(true, true);
		}
		else 
		{
			ModeleListeJoueurJeuMastermind.getInstance(true, false);
			modelePanneau.setChoix(RECREER_PLATEAU_JEU);
			modelePanneau.setChoix(PLATEAU_JEU);
		}	
	}
}
/*________________________________________*/
/* Fin du fichier ControleurJeuMastermind.java
/*________________________________________*/