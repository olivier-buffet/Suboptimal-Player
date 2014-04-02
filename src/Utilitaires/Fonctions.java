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

    public static int[][] symetrieH(int[][] tab){
        int l=tab.length;
        int[][] newArray=new int[l][tab[0].length];
        for (int i=0;i<=l/2;i++){
            for(int j=0;j<tab[0].length;j++){
                newArray[i][j]=tab[l-i-1][j];
            }
        }
        for (int i=l-1;i>=l/2;i--){
            for(int j=0;j<tab[0].length;j++){
                newArray[i][j]=tab[l-i-1][j];
            }
        }
        return newArray;
    }

    public static int[][] symetrieV(int[][] tab) {
        int c = tab[0].length;
        int[][] newArray = new int[tab.length][c];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j <= c/2; j++) {
                newArray[i][j] = tab[i][c-j-1];
            }
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = c-1; j >= c/2; j--) {
                newArray[i][j] = tab[i][c-j-1];
            }
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
