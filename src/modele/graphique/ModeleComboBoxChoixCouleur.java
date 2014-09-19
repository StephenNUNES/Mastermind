/*___________________________________________________________________________*/
/**
 * Fichier : ModeleListChoix.java
 *
 * Crée le 16 déc. 2013 à 12:13:27
 *
 * Auteur : BRUNEL Marjorie.
 */
package modele.graphique;

import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;

import controleur.Facade;

/*___________________________________________________________________________*/
/** Modèle des données des comboBox de choix des couleurs
 */
public class ModeleComboBoxChoixCouleur extends DefaultComboBoxModel<ImageCouleur> implements Observer
{
	/** Numéro de sérialisation */
	private static final long serialVersionUID = 7768585123122557196L;
	
	/*___________________________________________________________________________*/
	/** Constructeur de modèle de combobox.
	 * Il crée la liste des couleurs de la combo et les ajoutes.
	 */
	public ModeleComboBoxChoixCouleur()
	{
		super();
		
		for (int i = 0; i < Facade.getInstance().getNombreCouleurDisponibles(); ++i)
		{
			this.addElement(new ImageCouleur(Facade.getInstance().getCouleursAutorises().get(i)));	
		}
	}
	

	/*___________________________________________________________________________*/
	/** Définition de la méthode update de Observer
	 * @param arg0 Objet observé
	 * @param arg1 evenement notifié
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1)
	{
		if(arg1 != null && arg1.toString().equals("COULEURS_AUTORISEES"))
		{
			fireContentsChanged(this, 0, getSize());
		}
	}
}


/*___________________________________________________________________________*/
/* Fin du fichier ModeleListChoix.java.
/*___________________________________________________________________________*/
