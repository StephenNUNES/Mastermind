package controleur;

import java.util.List;

import constantes.IInformationsConfiguration;


/**Classe de façade, créée en singleton pour être testée*/
public class Facade implements IInformationsConfiguration
{
	
	/** La configuration de la facade *//*
	private ConfigurationJeu laConfiguration = null;*/
	
	/** Le jeu de la façade */
	private Jeu leJeu = null;
	
	/**Instance unique de la façade*/
	private static Facade instance;
	
	/** Nombre de rondes déjà jouées  */
	private int nombreRondeJoue = 0;
	
	/* ______________________________________________________ 
	*//** Retourne la valeur du champ laConfiguration.
	 * @return la valeur du champ laConfiguration.
	 *//*
	public ConfigurationJeu getLaConfiguration()
	{
		return laConfiguration;
	}*/

	
	
	/** Méthode d'accès à l'instance unique de la façade
	 * @return L'instance unique
	 */
	public static Facade getInstance()
	{
		if (instance == null)
			instance = new Facade();
		return instance;
	}
	
	/* ______________________________________________________ */
	/** Méthode d'accès à l'instance unique de la façade en définissant le jeu
	 * @param newJeu Le jeu associé
	 * @return l'instance unique
	 */
	public static Facade getInstance(Jeu newJeu)
	{
		if (instance == null)
		{
			instance = new Facade(newJeu);
		}
		else
		{
			instance.setJeu(newJeu);
		}
		return instance;
	}
	
	/* ______________________________________________________ 
	*//** Méthode d'accès à l'instance unique de la facade
	 * @param laConfiguration 
	 * @return l'instance unique de la facade
	 *//*
	public static Facade getInstance(ConfigurationJeu laConfiguration)
	{
		if (instance == null)
			instance = new Facade(laConfiguration);
		return instance;
	}*/
	
	/** Le constructeur privé du singleton */
	private Facade()
	{
		
	}
	
	/** Le constructeur privé du singleton
	 * @param laConfigurationJeu Configuration de la facade
	 *//*
	private Facade(ConfigurationJeu laConfigurationJeu)
	{
		laConfiguration = laConfigurationJeu;
	}*/
	

	/* ______________________________________________________ */
	/**
	 * @param newJeu
	 */
	private Facade(Jeu newJeu)
	{
		leJeu = newJeu;
	}

	/* ______________________________________________________ */
	/** Permet d'obtenir la liste des couleurs autorisées
	 * @return la liste des couleurs autorisées
	 */
	@Override
	public List<String> getCouleursAutorises()
	{
		return leJeu.getConfigJeu().getListeCouleursAutorise();
	}

	/* ______________________________________________________ */
	/** Retourne le nombre de pion dans une combinaison
	 * @return le nombre de pion par combinaison
	 */
	@Override
	public int getNombrePionDansCombinaison()
	{
		return leJeu.getConfigJeu().getDifficulteJeu().getNbPionCombinaison();
	}

	/* ______________________________________________________ */
	/** Retourne le nombre de couleurs disponible
	 * @return le nombre de couleurs disponible
	 */
	@Override
	public int getNombreCouleurDisponibles()
	{
		return leJeu.getConfigJeu().getListeCouleursAutorise().size();
	
	}

	/* ______________________________________________________ */
	/** Retourne le nombre de proposition possible
	 * @return le nombre de proposition possible
	 */
	@Override
	public int getNombrePropositionPossible()
	{
		return leJeu.getConfigJeu().getDifficulteJeu().getNbEssais();
	}

	
	
	/** Getter du jeu, pour ensuite récupérer la solution
	 * @return le jeu en cours.
	 */
	public Jeu getJeu()
	{
		return leJeu;
	}
	
	/** Setter du jeu de façade, correspondant au jeu courant,
	 * initialisé en même temps que le jeu
	 * @param jeu le jeu
	 */
	public void setJeu(Jeu jeu)
	{
		Facade.getInstance().leJeu = jeu;
	}

	/*___________________________________________________________________________*/
	/** Permet d'obtenir la liste des marqueurs autorisées
	 * @return la liste des marqueurs autorisés
	 */
	public List<String> getMarqueurAutorise()
	{
		
		return leJeu.getConfigJeu().getListeMarqueurAutorise();
	}

	/* ______________________________________________________ */
	/** Retourne la valeur du champ nombreRondeJoue.
	 * @return la valeur du champ nombreRondeJoue.
	 */
	public int getNombreRondeJoue()
	{
		return nombreRondeJoue;
	}

	/* ______________________________________________________ */
	/** Modifie la valeur du champ nombreRondeJoue.
	 * @param nombreRondeJoue la valeur à placer dans le champ nombreRondeJoue.
	 */
	public void setNombreRondeJoue(int nombreRondeJoue)
	{
		this.nombreRondeJoue = nombreRondeJoue;
	}

}
