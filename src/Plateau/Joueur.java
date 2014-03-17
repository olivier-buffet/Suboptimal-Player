package Plateau;

import java.io.*;

/**
 * Created by Xavier on 26/02/14.
 */
public class Joueur implements Participant {

    protected Jeu jeu;
    protected InputStream in;


    public  Joueur(PipedOutputStream out) throws IOException{
        this.jeu=null;
        this.in=new PipedInputStream(out);
    }

    public Joueur(Jeu jeu, PipedOutputStream out) throws IOException {
        this.jeu=jeu;
        this.in=new PipedInputStream(out);
    }

    @Override
    public void play() {
        int[][] plateau = jeu.getCopyPlateau();
        int i=0;
        int j=0;
        try {
            i=in.read();
            j=i%3;
            i=i/3;
            plateau[i][j]=jeu.getTour();
        } catch (IOException e) {
            e.printStackTrace();
            jeu.init();
            return;
        }

        jeu.jouerUnCoup(new CoupMNK(plateau));
    }

    public void setJeu(Jeu jeu){
        this.jeu=jeu;
    }
}