package Plateau;


import InterfaceGraphique.IG;
import Utilitaires.Fonctions;

import java.util.ArrayList;


public class MNK extends Jeu{
	
	private int[][] plateau;
	private int m;
	private int n;
	private int k;
	private Participant[] participants;
	private int tour;
	private ArrayList<Coup> ListeCoup;
	private IG vue;

	
	public MNK() {
        ListeCoup=new ArrayList<Coup>();
        tour=1;
        m=3;
		n=3;
		k=3;
		plateau = new int[m][n];
		for(int i=0;i<plateau.length;i++){
			for(int j=0;j<plateau[i].length;j++){
				plateau[i][j]=0;
			}
		}
    }
	
	public MNK(Participant[] participants){
		this();
		this.participants=participants;
        for(Participant p: participants){
            p.setJeu(this);
        }
	}
	
	public void init(){
        tour=1;
        ListeCoup=new ArrayList<Coup>();
        for(int i=0;i<plateau.length;i++){
			for(int j=0;j<plateau[i].length;j++){
				plateau[i][j]=0;
			}
		}
        this.setChanged();
        this.notifyObservers();
        play();
	}

    @Override
    public Coup newCoup() {
        return new CoupMNK();
    }

    @Override
    public Coup newCoup(int[][] etat) {
        return new CoupMNK(etat);
    }

    public void play(){
		if((!gagner(new CoupMNK(plateau)))&&!termine()){
			System.out.println(tour==1?"Joueur":"Ordi");
			jouerUnCoup(participants[tour - 1].play());
		}
	}
	
	public void jouerUnCoup(Coup c) {
		plateau = Fonctions.cloner(c.getEtat());
		ListeCoup.add(c);
		tour=tour==1?2:1;
		vue.MAJ();
		play();
	}
	
	public boolean termine(){
		for(int i=0;i<plateau.length;i++){
			for(int j=0;j<plateau[0].length;j++){
				if(plateau[i][j]==0){
					return false;
				}
			}
		}
		return true;
	}

    public boolean gagner(Coup c, int couleur) {
        int[][] etat = c.getEtat();
        for(int i=0;i<etat.length;i++){
            for(int j=0;j<etat[i].length;j++){
                if(etat[i][j]==couleur){
                    int longueurDiag1 = longueurChaine(etat, couleur,i,j,1,1)+longueurChaine(etat, couleur,i,j,-1,-1)+1;
                    int longueurDiag2 = longueurChaine(etat, couleur,i,j,-1,1)+longueurChaine(etat, couleur,i,j,1,-1)+1;
                    int longueurVert = longueurChaine(etat, couleur,i,j,0,1)+longueurChaine(etat, couleur,i,j,0,-1)+1;
                    int longueurHori = longueurChaine(etat, couleur,i,j,1,0)+longueurChaine(etat, couleur,i,j,-1,0)+1;
                    if(longueurDiag1>=k||longueurDiag2>=k||longueurVert>=k||longueurHori>=k){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int longueurChaine(int[][] etat, int couleur,int x,int y, int dirx,int diry){
        if(!(y+diry>=n||x+dirx>=m||y+diry<0||x+dirx<0)){
            if(etat[x+dirx][y+diry]==couleur){
                return longueurChaine(etat,couleur,x+dirx,y+diry,dirx,diry)+1;
            }
            else{
                return 0;
            }
        }
        else{
            return 0;
        }
    }
      
    public Arbre<Coup> listerTousCoupPossible(int couleur) {
        Arbre<Coup> racine=new Arbre<Coup>(new CoupMNK(Fonctions.cloner(plateau)));
        listerTousCoupPossible(racine,couleur);
        return racine;

    }

    public void listerTousCoupPossible(Arbre<Coup> racine, int couleur) {
        int[][] plat = Fonctions.cloner(racine.getNoeud().getEtat());
        for(int i=0;i<plat.length;i++){
            for(int j=0;j<plat[i].length;j++){
                if(plat[i][j]==0){
                    int[][] copiePlat = Fonctions.cloner(plat);
                    copiePlat[i][j]=couleur;
                    Arbre<Coup> coup= new Arbre<Coup>(new CoupMNK(Fonctions.cloner(copiePlat)));
                    racine.addFils(coup);
                }
            }
        }
		racine.actualiserProfondeur();
	}
	
	public boolean gagner(Coup c) {
		return gagner(c,1)||gagner(c,2);
	}

	public void effacerCoup(Coup c) {
		ListeCoup.remove(c);
	}

	public Jeu getCopyDeJeu() {
		try {
			return (Jeu) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setVue(IG ig) {
		this.vue=ig;
	}

	public int[][] getPlateau() {
		return plateau;
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}

	public int[][] getCopyPlateau() {
		return Fonctions.cloner(plateau);
	}

	public int getTour() {
		return tour;
    }
}
