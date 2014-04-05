package Plateau;


import Utilitaires.Fonctions;

/**
 * Created by Xavier on 26/03/2014.
 */
public class test {
    public static void main(String args[]){
        MNK m=new MNK(3,3,3);
        Coup c=new CoupMNK(new int[][]{{1,2,2},{2,1,0},{2,0,0}});
        System.out.println(m.gagner(c));
        NegaMax n=new NegaMax();
        n.setJeu(m);
        m.setPlateau(c.getEtat());
        n.play();

        int [][] plat=new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] plat2 = Fonctions.rotation90(plat);
        for(int i=0;i<plat2.length;i++){
            for(int j=0;j<plat2[0].length;j++){
                System.out.print(plat2[i][j]);
            }
            System.out.println();
        }
        System.out.println(Fonctions.isSame(plat,Fonctions.rotation90(Fonctions.rotation90(Fonctions.rotation90(Fonctions.rotation90(plat))))));
        Fonctions.print(Fonctions.rotation90(plat));
        Fonctions.print(Fonctions.symetrieV(plat));
        Fonctions.print(Fonctions.symetrieH(Fonctions.symetrieV(plat)));
    }
}
