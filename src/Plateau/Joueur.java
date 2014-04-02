package Plateau;

import java.io.*;
import java.util.concurrent.Semaphore;

/**
 * Created by Xavier on 26/02/14.
 */
public class Joueur implements Participant {

    protected Jeu jeu;
    protected int[] xy;
    protected Semaphore s1,s2;


    public  Joueur(int[] xy, Semaphore s1, Semaphore s2) throws IOException{
        this.jeu=null;
        this.xy=xy;
        this.s1=s1;
        this.s2=s2;
    }

    public Joueur(int[] xy, Semaphore s1, Semaphore s2, Jeu jeu) throws IOException {
        this(xy,s1,s2);
        this.jeu=jeu;
    }

    @Override
    public Coup play() {
        int[][] plateau = jeu.getCopyPlateau();
        try {
            s2.acquire();
            plateau[xy[0]][xy[1]]=jeu.getTour();
            s1.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
         }
        return jeu.newCoup(plateau);
    }

    public void setJeu(Jeu jeu){
        this.jeu=jeu;
    }
}