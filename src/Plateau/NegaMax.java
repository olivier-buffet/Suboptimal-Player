/**
 * Created by Xavier on 17/02/14.
 */
package Plateau;

import java.util.ArrayList;
import java.util.Random;

public class NegaMax extends Strategie implements Participant{

    public NegaMax(){
        this.omega=1.;
        this.drunken=0;
        this.random=new Random();
        this.count=0;
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
        Arbre<Coup> racine=new Arbre<Coup>(jeu.newCoup(jeu.getCopyPlateau()));
        jeu.listerTousCoupPossible(racine,jeu.getTour());
        if(random.nextDouble()>drunken){
            for(Arbre<Coup> fils : racine.getFils()){
                negaMax(fils,jeu.getTour()^3,omega);
            }
            double gain=Double.NEGATIVE_INFINITY;
            for(Arbre<Coup> node : racine.getFils()){
                //System.out.println(node.getValeur());
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
        }else return racine.getFils().get(random.nextInt(racine.getFils().size())).getNoeud();
    }

    public void negaMax(Arbre<Coup> parent, int tour, double omega){
        count++;
        jeu.listerTousCoupPossible(parent,tour);
        for(Arbre<Coup> fils : parent.getFils()){
            if(!jeu.gagner(fils.getNoeud())){
                negaMax(fils,tour^3,omega*omega);
            }else{
                fils.setValeur(1);
            }
        }
        if(parent.getFils().size()>0){
            parent.setValeur(-max(parent.getFils()));
            parent.resetFils();
        }
    }

    public void setJeu(Jeu jeu){
        this.jeu=jeu;
    }
}
