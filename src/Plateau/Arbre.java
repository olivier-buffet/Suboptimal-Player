package Plateau;

import java.util.ArrayList;


public class Arbre <T>{

	T noeud;
	ArrayList<Arbre<T>> liste;
	int profondeur;
	double valeur=0;
	
	public Arbre(T noeud){
		this.noeud=noeud;
		liste = new ArrayList<Arbre<T>>();
		profondeur = 0;
	}
	
	public void addFils(Arbre<T> a){
		liste.add(a);
	}
	
	public ArrayList<Arbre<T>> getFils(){
		return liste;
	}
	
	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

	public T getNoeud(){
		return noeud;
	}
	
	public double getValeur() {
		return valeur;
	}

	public void setValeur(double d) {
		this.valeur = d;
	}

	public void actualiserProfondeur(){
		for(Arbre<T> a : liste){
			a.setProfondeur(profondeur+1);
			a.actualiserProfondeur();
		}
	}
	
}
