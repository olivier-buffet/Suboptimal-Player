import Plateau.MNK;
import Plateau.NegaAlphaBeta;
import Plateau.Participant;
import Utilitaires.Chrono;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

/**
 * Created by Xavier on 26/02/14.
 */
public class Test {
    static int nb=4000;
    static Participant[] participants;
    static int rond=0,croix=0;

    public static void main(String args[]){
        double d1=Double.parseDouble(args[0]),d2=Double.parseDouble(args[0]);
        participants= new Participant[]{new NegaAlphaBeta(1,d1),new NegaAlphaBeta(1,d2)};
        try {
            System.setOut(new PrintStream("test"+d1+d2+".txt"));
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
        Chrono c=new Chrono();
        c.start();
        for(int i=0;i<nb;i++){
            MNK m=new MNK(3,3,3,participants);
            m.init();
            if(i%(nb/100)==0){
                System.err.println(i/(nb/100)+"%");
            }
        }
        c.stop();
        System.err.println(c);
        try {
            BufferedReader br = new BufferedReader(new FileReader("test"+d1+d2+".txt"));
            String s="";
            while((s=br.readLine())!=null){
                if(s.equals("Joueur O")){
                    rond++;
                }
                if(s.equals("Joueur X")){
                    croix++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(d1+" "+d2);
        System.err.println("partie gagner : \nJoueur O : "+rond+"\nJoueur X : "+croix);
    }
}
