package Plateau;

import Utilitaires.Chrono;
import Utilitaires.Fonctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Xavier on 19/03/14.
 */
public class NegaAlphaBeta extends Strategie implements Participant {

    public NegaAlphaBeta(){
        this.omega=1.;
        this.drunken=0;
        this.random=new Random();
        this.count=0;
        this.chrono=new Chrono();
        this.jeu=null;
    }

    public NegaAlphaBeta(double omega){
        this();
        this.omega=omega;
    }

    public NegaAlphaBeta(double omega, double drunken){
        this(omega);
        this.drunken=drunken;
    }

    public Coup play(){
        count=0;
        Coup racine=jeu.current();
        List<Coup> fils=jeu.listerTousCoupPossible(racine,jeu.getTour());
        double[] valeurs=new double[fils.size()];
        if(random.nextDouble()>drunken){
            chrono.start();
            valeurs[0]=negaAlphaBeta(fils.get(0),jeu.getTour()^3,-2,2);
            double max=valeurs[0];
            int nb_branches=1;
            for(int i=1;i<fils.size();i++){
                boolean compute=true;
                int[][] rot90= Fonctions.rotation90(fils.get(i).getEtat());
                int[][] rot180= Fonctions.rotation90(rot90);
                int[][] rot270= Fonctions.rotation90(rot180);
                for(int j=0;j<i;j++){
                    if(Fonctions.isSame(fils.get(j).getEtat(),rot90) || Fonctions.isSame(fils.get(j).getEtat(),rot180) || Fonctions.isSame(fils.get(j).getEtat(),rot270)){
                        valeurs[i]=valeurs[j];
                        compute=false;
                        break;
                    }
                }
                if(compute) {
                    nb_branches++;
                    valeurs[i] = negaAlphaBeta(fils.get(i), jeu.getTour() ^ 3, -2, 2);
                    if (valeurs[i] > max) {
                        max = valeurs[i];
                    }
                }
            }
            List<Coup> coupJouable=new ArrayList<>(9);
            for(int i=0;i<fils.size();i++){
                System.out.println(valeurs[i]);
                if(valeurs[i]>=max){
                    coupJouable.add(fils.get(i));
                }
            }
            chrono.stop();
            System.out.println(nb_branches+"/"+fils.size()+" branche(s) developpee(s), "+count + " noeuds visite(s) en " + chrono);
            return coupJouable.get(random.nextInt(coupJouable.size()));
        }else{
            return fils.get(random.nextInt(fils.size()));
        }
    }
      
    public double negaAlphaBeta(Coup parent,int tour, double a, double b){
        count++;
        if(jeu.gagner(parent)){
            return 1;
        } else {
            List<Coup> fils=jeu.listerTousCoupPossible(parent,tour);
            if(fils.isEmpty()){
                return 0;
            } else {
                double valeur = 0; //double max = Double.NEGATIVE_INFINITY;
                for (int i = 0; i < fils.size(); i++) {
                    valeur = negaAlphaBeta(fils.get(i), tour ^ 3, -b, -a);
                    /*if (valeur > max) {
                        max = valeur;
                        if (valeur > a) {
                            a = valeur;
                            if (a >= b) {
                                return -a;
                            }
                        }
                    }*/
                    if (valeur >= b)
                        return -b;   //  fail hard beta-cutoff
                    if (valeur > a)
                        a = valeur;
                }
                //return -max;
                return -a;
            }
        }
    }

    @Override
    public void setJeu(Jeu jeu) {
        this.jeu=jeu;
    }
}
