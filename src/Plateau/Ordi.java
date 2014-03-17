package Plateau;

import java.util.ArrayList;


public class Ordi implements Participant{

	private Strategie strat;
	private Jeu jeu;
	
	public Ordi(Strategie s,Jeu j){
		strat=s;
		jeu=j;
	}
	
	public void play() {
		System.out.println("arrive");
		Arbre<Coup> racine=jeu.listerTousCoupPossible();
        strat.compute(racine);
        Arbre<Coup> max = racine.getFils().get(0);
		ArrayList<Arbre<Coup>> listeMeilleursCoups= new ArrayList<Arbre<Coup>>();
		for(int i=0;i<racine.getFils().size();i++){
			System.out.println(i+":  "+racine.getFils().get(i).getValeur());
			if(max.getValeur()<racine.getFils().get(i).getValeur()){
				max=racine.getFils().get(i);
			}
		}
		for(int i=0;i<racine.getFils().size();i++){
			if(max.getValeur()<=racine.getFils().get(i).getValeur()){
				listeMeilleursCoups.add(racine.getFils().get(i));
			}
		}
		System.out.println("fini");
		jeu.jouerUnCoup(listeMeilleursCoups.get((int) (Math.random()*listeMeilleursCoups.size())).getNoeud());
	}

	public void setJeu(Jeu jeu) {
		this.jeu=jeu;
	}

}
