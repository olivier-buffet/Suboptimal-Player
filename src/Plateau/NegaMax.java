/**
 * Created by Xavier on 17/02/14.
 */
package Plateau;

import Utilitaires.Chrono;
import Utilitaires.Fonctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NegaMax extends Strategie implements Participant{

    public NegaMax(){
        this.omega=1.;
        this.drunken=0;
        this.random=new Random();
        this.count=0;
        this.chrono=new Chrono();
        this.jeu=null;
    }

    public NegaMax(double omega){
        this();
        this.omega=omega;
    }

    public NegaMax(double omega, double drunken){
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
            valeurs[0]=negaMax(fils.get(0),jeu.getTour()^3,omega);
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
                    valeurs[i] = negaMax(fils.get(i), jeu.getTour() ^ 3,omega);
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

    public double negaMax(Coup parent, int tour, double omega) {
        count++;
        if (jeu.gagner(parent)) {
            return 1;
        } else {
            List<Coup> fils = jeu.listerTousCoupPossible(parent, tour);
            if (fils.isEmpty()) {
                return 0;
            } else {
                double valeur = 0, max = Double.NEGATIVE_INFINITY;
                for (int i = 0; i < fils.size(); i++) {
                    valeur = negaMax(fils.get(i), tour ^ 3, omega * omega);
                    if (valeur > max) {
                        max = valeur;
                    }
                }
                return -max * omega;
            }
        }
    }

    public void setJeu(Jeu jeu){
        this.jeu=jeu;
    }
}
