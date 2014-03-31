package Plateau;

public class Heuristique2MNK {
	
	public int getValue(int[][] tab, int couleur,int k) {
		int valeur=0;
		for(int i=0;i<tab.length-k;i++){
			for(int j=0;j<tab[0].length-k;j++){
				if(libre(tab,i,j,0,1,k,couleur)){
					valeur+=valeurNBCouleur(nbCouleur(tab,i,j,0,1,k,couleur));
				}
				if(libre(tab,i,j,1,1,k,couleur)){
					valeur+=valeurNBCouleur(nbCouleur(tab,i,j,1,1,k,couleur));
				}
				if(libre(tab,i,j,1,0,k,couleur)){
					valeur+=valeurNBCouleur(nbCouleur(tab,i,j,1,0,k,couleur));
				}
				if(libre(tab,i,j,1,-1,k,couleur)){
					valeur+=valeurNBCouleur(nbCouleur(tab,i,j,1,-1,k,couleur));
				}
			}
		}
		return valeur;
	}
	
	private boolean libre(int[][]tab,int x,int y,int dirx,int diry,int k,int couleur){
		for(int i=0;i<k;i++){
			if(!(tab[x][y]==0||tab[x][y]==couleur)||(x<0||y<0||x>=tab.length||y>=tab[0].length)){
				return false;
			}
			x+=dirx;
			y+=diry;
		}
		return true;
	}
	
	private int nbCouleur(int[][]tab,int x,int y,int dirx,int diry,int k,int couleur){
		int nb=0;
		for(int i=0;i<k;i++){
			if(tab[x][y]==couleur){
				nb++;
			}
			x+=dirx;
			y+=diry;
		}
		return nb;
	}
	
	private int valeurNBCouleur(int nb){
		return (nb-1)*2;
	}
}
