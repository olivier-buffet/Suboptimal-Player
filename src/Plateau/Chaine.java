package Plateau;

public class Chaine {

	private int longueur;
	private boolean libre;
	
	public Chaine(int longueur){
		this.longueur=longueur;
		libre=true;
	}
	
	public void increment(){
		longueur++;
	}
	
	public void notFree(){
		libre=false;
	}
	
	public int getLongueur(){
		return longueur;
	}
	
	public boolean getLibre(){
		return libre;
	}
	
}
