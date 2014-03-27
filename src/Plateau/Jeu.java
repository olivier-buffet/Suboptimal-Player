package Plateau;

import java.util.List;
import java.util.Observable;

/**
 *
 * @author Phongphet
 */
public abstract class Jeu extends Observable {
    
    public abstract void jouerUnCoup(Coup c);
    
    public abstract boolean gagner(Coup c, int couleur);
    
    public abstract Coup current();

    public abstract List<Coup> listerTousCoupPossible(Coup racine, int couleur);
    
    public abstract boolean gagner(Coup c);
    
    public abstract void effacerCoup(Coup c);
    
    public abstract Jeu getCopyDeJeu();

	public abstract int[][] getCopyPlateau();

	public abstract int getTour();

	public abstract void init();

    public abstract Coup newCoup();

    public  abstract Coup newCoup(int[][] etat);
    
}
