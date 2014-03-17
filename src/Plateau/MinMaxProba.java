package Plateau;



public class MinMaxProba extends Strategie{
	
	private int profondeur;
	private double omega;
	
	public MinMaxProba(double omega, int profondeur){
		this.omega=omega;
		this.profondeur=profondeur;
	}

	public MinMaxProba(){
		this.omega=1;
		this.profondeur=Integer.MAX_VALUE;
	}

	public void compute(Arbre<Coup> parent) {
		compute(parent,omega);
	}

	public void compute(Arbre<Coup> parent, double omega) {
		if(!(parent.getFils().size()==0)){
			for(Arbre<Coup> ar : parent.getFils()){
				compute(ar,omega);
			}
			if(parent.profondeur>profondeur){
				double somme=0;
				for(Arbre<Coup> ar : parent.getFils()){
					somme=somme+ar.getValeur();
				}
				parent.setValeur(somme/parent.getFils().size());
			}
			else{
				double max=0;
				for(Arbre<Coup> ar : parent.getFils()){
					if(ar.getValeur()>max){
						max=ar.getValeur();
					}
				}
				parent.setValeur(-omega*max);
			}
		}	
	}
}
