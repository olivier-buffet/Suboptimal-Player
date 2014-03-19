package Plateau;

import java.util.ArrayList;
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
        Arbre<Coup> racine=new Arbre<Coup>(jeu.newCoup(jeu.getCopyPlateau()));
        if(random.nextDouble()>drunken){
            negaAlphaBetaInit(racine, jeu.getTour(), -100, 100);
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

    public double negaAlphaBetaInit(Arbre<Coup> parent,int tour, double a, double b){
        jeu.listerTousCoupPossible(parent,tour);
        if(jeu.gagner(parent.getNoeud())){
            return parent.getValeur();
        }else{
            double meilleur=Double.NEGATIVE_INFINITY;
            for(Arbre<Coup> fils : parent.getFils()){
                parent.setValeur(-negaAlphaBeta(fils,tour^3,-b,-a));
                if(parent.getValeur()>meilleur){
                    meilleur=parent.getValeur();
                    if(meilleur>a){
                        a=meilleur;
                        if(a>=b){
                            return meilleur;
                        }
                    }
                }
            }
            return meilleur;
        }
    }
      
    public double negaAlphaBeta(Arbre<Coup> parent,int tour, double a, double b){
        count++;
        jeu.listerTousCoupPossible(parent,tour);
        if(jeu.gagner(parent.getNoeud())){
            parent.resetFils();
            return 1;
        }else{
            double meilleur=Double.NEGATIVE_INFINITY;
            for(Arbre<Coup> fils : parent.getFils()){
                parent.setValeur(-negaAlphaBeta(fils,tour^3,-b,-a));
                if(parent.getValeur()>meilleur){
                    meilleur=parent.getValeur();
                    if(meilleur>a){
                        a=meilleur;
                        if(a>=b){
                            parent.resetFils();
                            return meilleur;
                        }
                    }
                }
            }
            parent.resetFils();
            return meilleur;
        }
    }

    @Override
    public void setJeu(Jeu jeu) {
        this.jeu=jeu;
    }
}
