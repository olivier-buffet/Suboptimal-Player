/**
 * Created by Xavier on 17/02/14.
 */
package Plateau;

import Utilitaires.Chrono;

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
            double max=Double.NEGATIVE_INFINITY;
            for(int i=0;i<fils.size();i++){
                valeurs[i]=negaMax(fils.get(i), jeu.getTour() ^ 3, omega);
                if(valeurs[i]>max){
                    max=valeurs[i];
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
            System.out.println(count + " noeuds visite(s) en " + chrono);
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
