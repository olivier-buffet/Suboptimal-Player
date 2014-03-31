package Utilitaires;

public class Fonctions {
	
	public static int[][] cloner(int[][] tab){
		int[][] cop = new int[tab.length][tab[0].length];
		for(int i=0;i<tab.length;i++){
			cop[i]=tab[i].clone();
		}
		return cop;
	}

    public static int[][] rotation90(int[][] tab){
        int l=tab.length,c=tab[0].length;
        int[][] newArray=new int[c][l];
        for (int i=0;i<c;i++)
            for(int j=0;j<l;j++){
                newArray[i][j]=tab[j][c-1-i];
            }
        return newArray;
    }

    public static boolean isSame(int[][] tab, int[][] tab2){
        if(tab.length==tab2.length && tab[0].length==tab2[0].length){
            for (int i=0;i<tab.length;i++)
                for(int j=0;j<tab[0].length;j++)
                    if(tab[i][j]!=tab2[i][j])
                        return false;
            return true;
        }else return false;
    }

    public static void print(int[][] tab){
        for(int[] l : tab){
            for(int c : l)
                System.out.print(c+" ");
            System.out.println();
        }
    }
}
