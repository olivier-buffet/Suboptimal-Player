/**
 * Created by Xavier on 17/02/14.
 */
package Plateau;

import Utilitaires.Chrono;

import java.util.List;
import java.util.Random;

public abstract class Strategie {

    protected double omega;
    protected double drunken;
    protected Random random;
    protected long count;
    protected Chrono chrono;
    protected Jeu jeu;

    public double max(List<Arbre<Coup>> fils){
        double max=Double.NEGATIVE_INFINITY;
        for(Arbre<Coup> e : fils) {
            if(e.getValeur() > max){
                max = e.getValeur();
            }
        }
        return max;
    }
}
