package controleur;

import metier.Combinaison;
import metier.Match;
import metier.Pion;
import modele.WrongColorException;


/* ______________________________________________________ */
/** Jeu du Mastermind 
 */
public class Jeu 
{
	
	/** Configuration du jeu */
	private ConfigurationJeu laConfiguration = null;
	
	/** Solution du jeu initialisé aléatoirement à la construction */
	private Combinaison solution = null;
	
	/** Match de ce jeu */
	private Match matchDuJeu = null;

	/* ______________________________________________________ */
	/** Retourne la valeur du champ configJeu.
	 * @return la valeur du champ configJeu.
	 */
	public ConfigurationJeu getConfigJeu()
	{
		return laConfiguration;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ configJeu.
	 * @param configJeu la valeur à placer dans le champ configJeu.
	 */
	public void setConfigJeu(ConfigurationJeu configJeu)
	{
		this.laConfiguration = configJeu;
	}
	
	/* ______________________________________________________ */
	/** Constructeur du jeu
	 * @param laConfigurationJeu La configuration du jeu créé
	 * @throws NullPointerException Si la configuration est null
	 */
	public Jeu(ConfigurationJeu laConfigurationJeu)
		throws NullPointerException
	{
		if (laConfigurationJeu == null)
			throw new NullPointerException("La configuration est inéxistante");
		setConfigJeu(laConfigurationJeu);
	}

	/* ______________________________________________________ */
	/** Crée la solution du jeu 
	 * @throws WrongColorException Couleur inéxistante
	 */
	public void creerSolution() throws WrongColorException
	{
		solution = new Combinaison();
		/*int nombreAleatoire;
		int borneMaximum = laConfiguration.getDifficulteJeu().getNbCouleurPion();
		Random rand = new Random();
		for (int i = 0; i < laConfiguration.getDifficulteJeu().getNbPionCombinaison(); ++i)
		{
			nombreAleatoire = rand.nextInt(borneMaximum);
			solution.ajouterPion(new Pion(laConfiguration.getListeCouleursAutorise().get(nombreAleatoire)));
		}*/
		solution.ajouterPion(new Pion("rouge"));
		solution.ajouterPion(new Pion("blanc"));
		solution.ajouterPion(new Pion("rouge"));
		solution.ajouterPion(new Pion("noir"));
	}
	
	/* ______________________________________________________ */
	/** Peremt d'obtenir la solution initialisé la solution
	 * @return la solution
	 */
	public Combinaison getSolution()
	{
		return solution;
	}
	
	/* ______________________________________________________ */
	/** Crée le match de ce jeu
	 */
	public void creerLeMatch()
	{
		setMatchDuJeu(new Match());
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ matchDuJeu.
	 * @return la valeur du champ matchDuJeu.
	 */
	public Match getMatchDuJeu()
	{
		return matchDuJeu;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ matchDuJeu.
	 * @param matchDuJeu la valeur à placer dans le champ matchDuJeu.
	 */
	public void setMatchDuJeu(Match matchDuJeu)
	{
		this.matchDuJeu = matchDuJeu;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ solution. La visibilité est package
	 * @param solution la valeur à placer dans le champ solution.
	 */
	public void setSolution(Combinaison solution)
	{
		this.solution = solution;
	}
}
