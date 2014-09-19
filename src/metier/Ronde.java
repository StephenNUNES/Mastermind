package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* ______________________________________________________ */
/** Ronde d'un jeu
 */
public class Ronde implements Serializable {
	
	/** Numéro de sérialisation */
	private static final long serialVersionUID = 8439209773832946951L;
	
	/** Les joueurs participant à cette ronde */
	private List<Joueur> lesParticipants = null;
	
	/* ______________________________________________________ */
	/** Constructeur d'une ronde
	 */
	public Ronde()
	{
		lesParticipants = new ArrayList<>();
	}
	
	/* ______________________________________________________ */
	/** Ajoute un nouveau joueur à la liste des participants
	 * @param nouveauParticipant Nouveau joueur ajouté
	 * @return si le joueur a bien été ajouté 
	 */
	public boolean ajouterJoueur(Joueur nouveauParticipant)
	{
		return lesParticipants.add(nouveauParticipant);
	}
	
	/* ______________________________________________________ */
	/** Permet d'obtenir un joueur participant à une ronde
	 * @param index Index du joueur dans la liste
	 * @return le joueur obtenu
	 */
	public Joueur getJoueur(int index)
	{
		return lesParticipants.get(index);
	}
}
