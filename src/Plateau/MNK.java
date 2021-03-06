package Plateau;


import InterfaceGraphique.IG;
import Utilitaires.Fonctions;

import java.util.ArrayList;
import java.util.List;


public class MNK extends Jeu{
	
	private int[][] plateau;
	private int m;
	private int n;
	private int k;
	private Participant[] participants;
	private int tour;
	private ArrayList<Coup> ListeCoup;

	
	public MNK(int m, int n, int k) {
        ListeCoup=new ArrayList<Coup>();
        tour=1;
        this.m=m;
		this.n=n;
		this.k=k;
		plateau = new int[m][n];
		for(int i=0;i<plateau.length;i++){
			for(int j=0;j<plateau[i].length;j++){
				plateau[i][j]=0;
			}
		}
    }
	
	public MNK(int m, int n, int k, Participant[] participants){
		this(m,n,k);
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
		while((!gagner(new CoupMNK(plateau)))&&!termine()){
			//System.out.println(tour==1?"Joueur O":"Joueur X");
			jouerUnCoup(participants[tour - 1].play());
		}

	}
	
	public void jouerUnCoup(Coup c) {
        if(gagner(c))
            System.out.println(tour==1?"Joueur O":"Joueur X");
		plateau = Fonctions.cloner(c.getEtat());
		ListeCoup.add(c);
		tour=tour==1?2:1;
		this.setChanged();
        this.notifyObservers();
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
      
    public Coup current() {
        return new CoupMNK(Fonctions.cloner(plateau));
    }

    public List<Coup> listerTousCoupPossible(Coup racine, int couleur) {
        int[][] plat = racine.getEtat();
        List<Coup> fils=new ArrayList<>();
        for(int i=0;i<plat.length;i++){
            for(int j=0;j<plat[i].length;j++){
                if(plat[i][j]==0){
                    int[][] copiePlat = Fonctions.cloner(plat);
                    copiePlat[i][j]=couleur;
                    Coup coup= new CoupMNK(copiePlat);
                    fils.add(coup);
                }
            }
        }
		return fils;
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

    public void setPlateau(int[][] plat){
        this.plateau=plat;
    }
}
