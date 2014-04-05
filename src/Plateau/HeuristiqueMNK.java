package Plateau;

import java.util.ArrayList;

public class HeuristiqueMNK implements Heuristique{

	ArrayList<PointDir> list= new ArrayList<PointDir>();
	
	public int getValue(int[][] tab,int couleur,int k) {
		int somme=0;
		for(int i=0;i<tab.length;i++){
			for(int j=0;j<tab[0].length;j++){
				if(tab[i][j]==couleur){
					somme=somme+getValeurPoint(tab,i,j,couleur);
				}
			}
		}
		return somme;
	}
	
	private int getValeurPoint(int[][] tab,int x, int y,int couleur){
		Chaine diag1=getChaine(tab,1,1,x,y,couleur);
		Chaine diag2=getChaine(tab,-1,-1,x,y,couleur);
		Chaine diag3=getChaine(tab,1,-1,x,y,couleur);
		Chaine diag4=getChaine(tab,-1,1,x,y,couleur);
		Chaine vert1=getChaine(tab,0,1,x,y,couleur);
		Chaine vert2=getChaine(tab,0,-1,x,y,couleur);
		Chaine hori1=getChaine(tab,1,0,x,y,couleur);
		Chaine hori2=getChaine(tab,-1,0,x,y,couleur);
		int fact1= diag1.getLibre()?(diag2.getLibre()?2:1):(diag2.getLibre()?1:0);
		int fact2= diag3.getLibre()?(diag4.getLibre()?2:1):(diag4.getLibre()?1:0);
		int fact3= vert1.getLibre()?(vert2.getLibre()?2:1):(vert2.getLibre()?1:0);
		int fact4= hori1.getLibre()?(hori2.getLibre()?2:1):(hori2.getLibre()?1:0);
		//System.out.println(diag1.getLibre()+"    "+diag2.getLibre()+"    "+diag3.getLibre()+"    "+diag4.getLibre());
		//System.out.println(fact1+"   "+fact2+"   "+"   "+fact3+"   "+fact4);
		int res=0;
		if(!(diag1.getLongueur()==0&&diag2.getLongueur()==0)){
			res=res+(1+diag1.getLongueur()+diag2.getLongueur())*fact1;
		}
		if(!(diag3.getLongueur()==0&&diag4.getLongueur()==0)){
			res=res+(1+diag3.getLongueur()+diag4.getLongueur())*fact2;
		}
		if(!(vert1.getLongueur()==0&&vert2.getLongueur()==0)){
			res=res+(1+vert1.getLongueur()+vert2.getLongueur())*fact3;
		}
		if(!(hori1.getLongueur()==0&&hori2.getLongueur()==0)){
			res=res+(1+hori1.getLongueur()+hori2.getLongueur())*fact4;
		}
		return res;
	}
	
	private Chaine getChaine(int[][] tab, int xdir,int ydir,int x,int y,int couleur){
		boolean dejaFait= false;
		PointDir p = new PointDir(x,y,xdir,ydir);
		for(PointDir e :list){
			dejaFait=dejaFait||e.equals(p);
		}
		if(dejaFait){
			Chaine c = new Chaine(0);
			if(x+xdir<tab.length&&y+ydir<tab[0].length&&x+xdir>=0&&y+ydir>=0){
				if(tab[x+xdir][y+ydir]!=0&&tab[x+xdir][y+ydir]!=couleur){
					c.notFree();
				}
			}
			else{
				c.notFree();
			}
			return c;
		}
		else{
			list.add(p);
			Chaine c= new Chaine(0);
			getLongueur(tab,xdir,ydir,x+xdir,y+ydir,couleur,c);
			return c;
		}
	}
	
	private void getLongueur(int[][] tab, int xdir,int ydir,int x,int y,int couleur,Chaine c){
		//System.out.println(x+"   "+y+"   "+tab[x][y]);
		if(x<tab.length&&y<tab[0].length&&x>=0&&y>=0){
			//System.out.println(x+"   "+y+"   "+tab[x][y]);
			if(tab[x][y]!=0&&tab[x][y]!=couleur){
				c.notFree();
			}
			else if(tab[x][y]==0){
				
			}
			else{
				PointDir p = new PointDir(x,y,xdir,ydir);
				list.add(p);
				c.increment();
				getLongueur(tab,xdir,ydir,x+xdir,y+ydir,couleur,c);
			}
		}
		else{
			c.notFree();
		}
	}
	
	private void validationPassage(){
		
	}

}
