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

    protected Heuristique2MNK heuristique;

    public NegaAlphaBeta(){
        this.omega=1.;
        this.drunken=0;
        this.random=new Random();
        this.count=0;
        this.chrono=new Chrono();
        this.jeu=null;
        this.heuristique=new Heuristique2MNK();
        this.profondeur=7;
    }

    public NegaAlphaBeta(int profondeur){
        this();
        this.profondeur=profondeur;
    }

    public NegaAlphaBeta(int profondeur, double omega){
        this(profondeur);
        this.omega=omega;
    }

    public NegaAlphaBeta(int profondeur, double omega, double drunken){
        this(profondeur,omega);
        this.drunken=drunken;
    }

    public Coup play(){
        count=0;
        Coup racine=jeu.current();
        List<Coup> fils=jeu.listerTousCoupPossible(racine,jeu.getTour());
        double[] valeurs=new double[fils.size()];
        if(random.nextDouble()>drunken){
            chrono.start();
            valeurs[0]=negaAlphaBeta(fils.get(0), profondeur, jeu.getTour()^3, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            double max=valeurs[0];
            int nb_branches=1;
            for(int i=1;i<fils.size();i++){
                boolean compute=true;
                int[][] rot90= Fonctions.rotation90(fils.get(i).getEtat());
                int[][] rot180= Fonctions.rotation90(rot90); // = symC
                int[][] rot270= Fonctions.rotation90(rot180);
                int[][] symV=Fonctions.symetrieV(fils.get(i).getEtat());
                int[][] symV90=Fonctions.rotation90(symV);
                int[][] symH=Fonctions.rotation90(symV90);
                int[][] symH90=Fonctions.rotation90(symH);
                for(int j=0;j<i;j++){
                    if(Fonctions.isSame(fils.get(j).getEtat(),rot90) || Fonctions.isSame(fils.get(j).getEtat(),rot180) || Fonctions.isSame(fils.get(j).getEtat(),rot270)
                            || Fonctions.isSame(fils.get(j).getEtat(),symH) || Fonctions.isSame(fils.get(j).getEtat(),symV)
                            || Fonctions.isSame(fils.get(j).getEtat(),symH90) || Fonctions.isSame(fils.get(j).getEtat(),symV90)){
                        valeurs[i]=valeurs[j];
                        compute=false;
                        break;
                    }
                }
                if(compute) {
                    nb_branches++;
                    valeurs[i] = negaAlphaBeta(fils.get(i), profondeur, jeu.getTour() ^ 3, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
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
      
    public double negaAlphaBeta(Coup parent, int profondeur, int tour, double a, double b) {
        count++;
        double best = Double.NEGATIVE_INFINITY;
        if (jeu.gagner(parent)) {
            return Double.POSITIVE_INFINITY;
        } else if (profondeur <= 0) {
            return heuristique.getValue(parent.getEtat(),tour,4);
        } else {
            List<Coup> fils = jeu.listerTousCoupPossible(parent, tour);
            if (fils.isEmpty()) {
                return 0;
            } else {
                for (int i = 0; i < fils.size(); i++) {
                    double valeur = negaAlphaBeta(fils.get(i), profondeur - 1, tour ^ 3, -b, -a);
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
                        return -valeur;  // fail-soft beta-cutoff
                    if (valeur > best) {
                        best = valeur;
                        if (valeur > a)
                            a = valeur;
                    }
                }
                return -best;
            }
        }
    }

    @Override
    public void setJeu(Jeu jeu) {
        this.jeu=jeu;
    }
}
