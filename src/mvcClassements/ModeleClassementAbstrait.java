/*___________________________________________________________________________*/
/**
 * Fichier : ModeleClassementAbstrait.java
 *
 * Crée le 13 janv. 2014 à 09:30:02
 *
 * Auteur : BRUNEL Marjorie.
 */
package mvcClassements;

import javax.swing.table.DefaultTableModel;

/*___________________________________________________________________________*/
/**Modèle de tableau abstrait
 */
public abstract class ModeleClassementAbstrait extends DefaultTableModel
{
	/** serialVersionUID */
	private static final long serialVersionUID = 7993906990496254383L;

	/** Constructeur de modèle */
	public ModeleClassementAbstrait()
	{
		super();
		this.addColumn("Joueur");
		this.addColumn("Difficulté");
	}
	
	/** Méthode abstraite d'ajout de lignes 
	 * @param pseudo pseudonyme du joueur
	 * @param difficulte difficulte de la partie jouée
	 * @param valeur selon le classement, soit la moyenne (score/partie jouée)
	 * 									  soit le meilleur score
	 * */
	public abstract void ajouterLigne(String pseudo, String difficulte, int valeur);
	
	
	
	/* ______________________________________________________ */
	/** Ordonne si le tableau est éditable ou non
	 * @param row ligne choisie
	 * @param column colonne choisie
	 * @return si le tableau est éditable ou non
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
	
}


/*___________________________________________________________________________*/
/* Fin du fichier ModeleClassementAbstrait.java.
/*___________________________________________________________________________*/
