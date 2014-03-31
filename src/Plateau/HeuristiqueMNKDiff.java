package Plateau;

public class HeuristiqueMNKDiff implements Heuristique{

	private Heuristique heuri;
	private int couleur1;
	private int couleur2;
	
	public HeuristiqueMNKDiff(Heuristique heuri,int couleur1,int couleur2){
		this.heuri=heuri;
		this.couleur1=couleur1;
		this.couleur2=couleur2;
	}
	
	public int getValue(int[][] tab, int couleur) {
		int resCoul1=heuri.getValue(tab, couleur);
		int resCoul2=heuri.getValue(tab, couleur==couleur1?couleur2:couleur1);
		return resCoul1-resCoul2;
	}

} 
