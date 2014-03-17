package Plateau;

import java.util.Observable;

/**
 *
 * @author Phongphet
 */
public abstract class Jeu extends Observable {
    
    public abstract void jouerUnCoup(Coup c);
    
    public abstract boolean gagner(Coup c, int couleur);
    
    public abstract Arbre<Coup> listerTousCoupPossible();
    
    public abstract boolean gagner(Coup c);
    
    public abstract void effacerCoup(Coup c);
    
    public abstract Jeu getCopyDeJeu();

	public abstract int[][] getCopyPlateau();

	public abstract int getTour();

	public abstract void init();
    
}
