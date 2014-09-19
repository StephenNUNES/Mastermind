/* ______________________________________________________ */
/**
 * Fichier : Combinaison.java
 *
 * Crée le 6 déc. 2013 à 15:34:29
 *
 * Auteur : NUNES Stephen
 */
package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controleur.Facade;

/* ______________________________________________________ */
/** Contient une combinaison de pions ne dépassant le nombre de pions définit dans les règles du jeu
 */
public class Combinaison implements Serializable
{
	
	/** Numéro de sérialisation */
	private static final long serialVersionUID = -5879306126343766360L;
	
	/** Liste contenant les pions de la combinaison */
	private List<Pion> listePions = null;
		
		
	/* ______________________________________________________ */
	/** Constructeur d'une combinaison avec instanciation de la liste de pion
	 */
	public Combinaison()
	{
		listePions = new ArrayList<>();
	}
	
	
	
	/* ______________________________________________________ */
	/** Ajoute un pion à la liste des pions
	 * @param pionAjoute Pion à ajouter
	 * @return Si le pion a été ajouté
	 */
	public boolean ajouterPion(Pion pionAjoute)
	{
		if (listePions.size() >= Facade.getInstance().getNombrePionDansCombinaison())
		{
			return false;
		}
		return listePions.add(pionAjoute);
	}

	
	/* ______________________________________________________ */
	/** Accesseur en lecture du nombre de pion de la liste de pion de la combinaison
	 * @return Le nombre de pion contenu dans la liste
	 */
	public int getNombreDePion()
	{
		return listePions.size();
	}
	
	/* ______________________________________________________ */
	/** Permet d'obtenir la liste des pions sans modifier celle de la combinaison
	 * @return La liste de pion
	 */
	public List<Pion> getListePions()
	{
		return new ArrayList<Pion>(listePions);
	}
	
	/* ______________________________________________________ */
	/** Affiche la couleur des pions contenu dans la combinasion
	 */
	public void afficherCombinaison()
	{
		for (Pion p : listePions)
		{
			System.out.println(p);
		}
	}
	
	/* ______________________________________________________ */
	/** Permet d'obtenir les noms des couleurs dans la combinaison, dans l'ordre des pions
	 * @return liste de chaîne de caractères représentant les noms des couleurs
	 */
	public List<String> getCouleurPions()
	{
		List<String> nomsPions = new ArrayList<>();
		for (Pion p : listePions)
		{
			nomsPions.add(p.getCouleur());
		}
		return nomsPions;
	}
	
	/* ______________________________________________________ */
	/** Comparaison de deux combinaisons indiquant le nombre de pions bien placés et le nombre de pion mal placés
	 * @param proposition
	 * @param solution
	 * @return Le Résultat de la comparaison
	 */
	public static Resultat comparerCombinaison(Combinaison proposition, Combinaison solution) 
	{
		Resultat resultatComparaison = new Resultat();
		int nombreDePionBienPlace = 0;
		int nombreDePionMalPlace = 0;
		int i;
		
		// La clé est le nom de la couleur, la valeur le nombre d'occurence dans la combinaison de cette couleur
		Map<String, Integer> mapProposition = new HashMap<String, Integer>(); 
		
		Map<String, Integer> mapSolution = new HashMap<String, Integer>(); 
		
		
		for (i = 0; i < Facade.getInstance().getCouleursAutorises().size(); ++i) // Initilisation des deux Maps pour compter les occurences des couleurs dans les combinaisons
		{
			mapProposition.put(Facade.getInstance().getCouleursAutorises().get(i), 0);
			mapSolution.put(Facade.getInstance().getCouleursAutorises().get(i), 0);
		}
	
		for (i = 0; i < solution.getListePions().size(); ++i) // Parcours des deux combinaisons afin de trouver les pions bien placés
		{
			
			if (solution.getCouleurPions().get(i).equals(proposition.getCouleurPions().get(i)))
			{
				++nombreDePionBienPlace;
			}
			else // Incrémentation du nombre d'occurence dans les maps pour le calcul du nombre de pions mal placés
			{
				mapProposition.put(proposition.getCouleurPions().get(i), 
						mapProposition.get(proposition.getCouleurPions().get(i)) + 1);
				mapSolution.put(solution.getCouleurPions().get(i), 
						mapSolution.get(solution.getCouleurPions().get(i)) + 1);
			}
		}
		
		for (i = 0; i < Facade.getInstance().getCouleursAutorises().size(); ++i) // Calcul du nombre de pions mal placé
		{
			nombreDePionMalPlace += Math.min(mapProposition.get(Facade.getInstance().getCouleursAutorises().get(i)),
					mapSolution.get(Facade.getInstance().getCouleursAutorises().get(i)));
		}
		
		resultatComparaison.setNombreDePionBienPlace(nombreDePionBienPlace);
		resultatComparaison.setNombreDePionMalPlace(nombreDePionMalPlace);
		return resultatComparaison;
		
	}



	/* ______________________________________________________ */
	/** Redéfinition de toString
	 * @return la chaîne créée
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuffer combinaisonRetourne = new StringBuffer();
		for (Pion p : listePions)
		{
			combinaisonRetourne.append(p + " ");
		}
		return combinaisonRetourne.toString();
	}



}

/*__________________________________________________________*/
/* Fin du fichier Combinaison.java. */
/*__________________________________________________________*/