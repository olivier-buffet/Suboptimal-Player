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
        this(out);
        this.jeu=jeu;
    }

    @Override
    public Coup play() {
        int[][] plateau = jeu.getCopyPlateau();
        int i=0;
        int j=0;
        try {
            i=in.read();
            j=i%4;
            i=i/4;
            plateau[i][j]=jeu.getTour();
        } catch (IOException e) {
            e.printStackTrace();
            jeu.init();
         }
        return jeu.newCoup(plateau);
    }

    public void setJeu(Jeu jeu){
        this.jeu=jeu;
    }
}