/**
 * Created by Xavier on 17/02/14.
 */
package Plateau;

public class NegaMax extends Strategie{

    protected double omega;

    public NegaMax(){
        this.omega=1.;
    }

    public NegaMax(double omega){
        this.omega=omega;
    }

    public void compute(Arbre<Coup> parent){
        compute(parent,omega);
    }

    public void compute(Arbre<Coup> parent, double omega){
        if(parent.getFils().size()>0){
            for(Arbre<Coup> node : parent.getFils()){
                compute(node, omega*omega);
            }
            parent.setValeur(-max(parent)*omega);
        }
    }
}
