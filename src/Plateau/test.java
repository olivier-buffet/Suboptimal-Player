package Plateau;

/**
 * Created by Xavier on 26/03/2014.
 */
public class test {
    public static void main(String args[]){
        MNK m=new MNK();
        Coup c=new CoupMNK(new int[][]{{1,2,2},{2,1,0},{2,0,0}});
        System.out.println(m.gagner(c));
        NegaMax n=new NegaMax();
        n.setJeu(m);
        m.setPlateau(c.getEtat());
        n.play();
    }
}
