/**
 * Created by Xavier on 17/02/14.
 */
package Plateau;

public abstract class Strategie {

    public abstract void compute(Arbre<Coup> parent);
    public abstract void compute(Arbre<Coup> parent, double omega);
    

    public double max(Arbre<Coup> parent){
        double max=Double.NEGATIVE_INFINITY;
        for(Arbre<Coup> node : parent.getFils()) {
            if(node.getValeur() > max){
                max = node.getValeur();
            }
        }
        return max;
    }
}
