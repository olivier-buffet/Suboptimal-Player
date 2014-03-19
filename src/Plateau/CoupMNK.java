package Plateau;

public class CoupMNK implements Coup{

	private int[][] etat;
	
	public CoupMNK(){}

    public CoupMNK(int[][] etat){
		this();
        this.etat=etat;
	}

    @Override
	public int[][] getEtat() {
		return etat;
	}

    @Override
    public void setEtat(int[][] etat){
        this.etat=etat;
    }
}
