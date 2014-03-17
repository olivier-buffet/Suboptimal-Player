package Plateau;

public class CoupMNK implements Coup{

	private int[][] etat;
	
	public CoupMNK(int[][] etat){
		this.etat=etat;
	}
	
	public int[][] getEtat() {
		return etat;
	}

}
