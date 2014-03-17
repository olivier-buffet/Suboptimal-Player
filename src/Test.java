import Utilitaires.Fonctions;

/**
 * Created by Xavier on 07/03/14.
 */
public class Test {

    public static void main(String args[]){
        int[][] tab=new int[][]{{1,2},{3,4},{5,6}};
        int[][] tab2= Fonctions.rotation90(tab);
        Fonctions.print(tab2);
        System.out.println(Fonctions.isSame(tab,tab2));
        int[][] tab3= Fonctions.rotation90(tab2);
        Fonctions.print(tab2);
        System.out.println(Fonctions.isSame(tab,tab3));
        int[][] tab4= Fonctions.rotation90(tab3);
        Fonctions.print(tab2);
        System.out.println(Fonctions.isSame(tab,tab4));
        int[][] tab5= Fonctions.rotation90(tab4);
        Fonctions.print(tab2);
        System.out.println(tab5.equals(tab));
        System.out.println(Fonctions.isSame(tab,tab5));
    }
}
