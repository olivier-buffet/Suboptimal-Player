package Plateau;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Xavier on 19/03/14.
 */
public class AlphaBeta extends Strategie implements Participant {

    public AlphaBeta(){
        this.omega=1.;
        this.drunken=0;
        this.random=new Random();
        this.count=0;
        this.jeu=null;
    }

    public AlphaBeta(double omega){
        this();
        this.omega=omega;
    }

    public AlphaBeta(double omega, double drunken){
        this(omega);
        this.drunken=drunken;
    }

    public Coup play(){
        count=0;
        Arbre<Coup> racine=new Arbre<Coup>(jeu.newCoup(jeu.getCopyPlateau()));
        if(random.nextDouble()>drunken){
            maxValue(racine, jeu.getTour(), -100, 100);
            double gain=Double.NEGATIVE_INFINITY;
            for(Arbre<Coup> node : racine.getFils()){
                System.out.println(node.getValeur());
                if(node.getValeur()>gain){
                    gain=node.getValeur();
                }
            }
            ArrayList<Coup> coupJouable=new ArrayList<Coup>(9);
            for(Arbre<Coup> node : racine.getFils()){
                if(node.getValeur()==gain){
                    coupJouable.add(node.getNoeud());
                }
            }
            System.out.println(count);
            return coupJouable.get(random.nextInt(coupJouable.size()));
        }else{
            jeu.listerTousCoupPossible(racine,jeu.getTour());
            return racine.getFils().get(random.nextInt(racine.getFils().size())).getNoeud();
        }
    }

    public double maxValue(Arbre<Coup> state, int player, double alpha, double beta) {
        count++;
        if (jeu.gagner(state.getNoeud()))
            return 1;
        jeu.listerTousCoupPossible(state,player);
        double value = Double.NEGATIVE_INFINITY;
        for (Arbre<Coup> action : state.getFils()) {
            value = Math.max(value, minValue(action, player^3, alpha, beta));
            if (value >= beta)
                return value;
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    public double minValue(Arbre<Coup> state, int player, double alpha, double beta) {
        count++;
        if (jeu.gagner(state.getNoeud()))
            return -1;
        jeu.listerTousCoupPossible(state,player);
        double value = Double.POSITIVE_INFINITY;
        for (Arbre<Coup> action : state.getFils()) {
            value = Math.min(value, maxValue(action, player^3, alpha, beta));
            if (value <= alpha)
                return value;
            beta = Math.min(beta, value);
        }
        return value;
    }


    @Override
    public void setJeu(Jeu jeu) {
        this.jeu=jeu;
    }
}
