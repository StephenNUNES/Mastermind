/*___________________________________________________________________________*/
/**
 * Fichier : PanneauTableauClassement.java
 *
 * Crée le 13 janv. 2014 à 09:03:19
 *
 * Auteur : BRUNEL Marjorie.
 */
package mvcClassements;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mvc.ModelePanneau;
import mvcClassements.historique.ModeleTableauHistorique;
import mvcClassements.moyenne.ModeleTableauMoyenne;
import constantes.IConstantes;
import constantes.IConstantesGraphiques;

/*___________________________________________________________________________*/
/** Tableau permettant d'afficher le classement
 */
public class PanneauClassementAbstrait extends JPanel implements IConstantesGraphiques, IConstantes
{
	/** Attribut de serialisation */
	private static final long serialVersionUID = 9138970113120691334L;
	
	/**Le tableau du classement */
	private JTable tableauClassement = null;
	
	/** Nom du tableau */
	private String nomTableau = null;
	
	/**
	 * Constructeur de tableau
	 * @param modele le modele du tableau choisi
	 */
	public PanneauClassementAbstrait(String modele)
	{
		this.nomTableau = modele;
		this.setLayout(new BorderLayout());
		
		ControleurClassementAbstrait ctrl = new ControleurClassementAbstrait(ModelePanneau.getInstance());
		
		this.add(creerTableau() , BorderLayout.CENTER);	
		this.add(creerPanneauBas(ctrl), BorderLayout.SOUTH);
	}
	
	/*___________________________________________________________________________*/
	/**Méthode de création du panneau des boutons
	 * @param ctrl Contrôleur du classement abstrait
	 * @return le panneau complet de boutons
	 */
	private JPanel creerPanneauBas(ControleurClassementAbstrait ctrl)
	{
		JPanel panBouton = new JPanel();	
		panBouton.setLayout(new BorderLayout());
		panBouton.add(creerPanneauBoutons(ctrl),BorderLayout.CENTER);
		if (nomTableau.equals("Moyenne"))
		{
			panBouton.add(creerIndication(), BorderLayout.SOUTH);
		}
		
		return panBouton;
	}


	/*___________________________________________________________________________*/
	/**Méthode de création du label d'indication de l'astérisque
	 * @return le panneau avec l'indication
	 */
	private JPanel creerIndication()
	{
		JPanel pan = new JPanel();
		pan.setLayout(new FlowLayout());
		
		JLabel labIndic = new JLabel("*nombre total de coups joués");
		labIndic.setFont(POLICE_INDICE);
		pan.add(labIndic);
		return pan;
	}


	/*___________________________________________________________________________*/
	/**Méthode de création des boutons
	 * @param ctrl Contrôleur du classement abstrait
	 * @return le panneau des boutons
	 */
	private JPanel creerPanneauBoutons(ControleurClassementAbstrait ctrl)
	{
		JPanel pan = new JPanel();
		creerBouton("Retour", pan, ctrl);
		return pan;
	}

	/*___________________________________________________________________________*/
	/**
	 * Créé un bouton
	 * @param label : intitule du bouton
	 * @param panelRecepteur : panel qui recoit le bouton
	 * @param ctrl Contrôleur du classement abstrait
	 */
	private void creerBouton(String label, JPanel panelRecepteur, ControleurClassementAbstrait ctrl)
	{
		JButton monBouton = new JButton(label);
		monBouton.addActionListener(ctrl);
		monBouton.setFont(POLICE_BOUTON_CLASSEMENT);
		monBouton.setPreferredSize(new Dimension(160,25));
		panelRecepteur.add(monBouton);
	}


	/*___________________________________________________________________________*/
	/** Methode de création du tableau
	 * @return le scrollPane qui contient le tableau
	 */
	private JScrollPane creerTableau()
	{
		tableauClassement = new JTable();
		ModeleClassementAbstrait modeleTab;
		if (nomTableau.equals(CLASS_MOYENNE))
		{
			modeleTab = ModeleTableauMoyenne.getInstance();
		}
		else
		{
			modeleTab = ModeleTableauHistorique.getInstance();
		}
		
		
		tableauClassement.setModel(modeleTab);
		tableauClassement.setAutoCreateRowSorter(true);
		
		JScrollPane scrollTableau = new JScrollPane(tableauClassement);
		return scrollTableau;
	}
}


/*___________________________________________________________________________*/
/* Fin du fichier PanneauTableauClassement.java.
/*___________________________________________________________________________*/
