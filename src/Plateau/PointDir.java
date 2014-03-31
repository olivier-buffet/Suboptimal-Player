package Plateau;

public class PointDir{
	private int x;
	private int y;
	private int dirx;
	private int diry;
	
	public PointDir(int x,int y,int dirx,int diry){
		this.x=x;
		this.y=y;
		this.dirx=dirx;
		this.diry=diry;
	}
	
	public boolean equals(PointDir p){
		return x==p.getX()&&y==p.getY()&&dirEquivalent(p);
	}
	
	public boolean dirEquivalent(PointDir p){
		if(dirx==diry){
			return p.dirx==p.diry;
		}
		else if(dirx==-diry){
			return p.dirx==-p.diry;
		}
		else if(dirx==0){
			return p.diry==diry||p.diry==-diry;
		}
		else if(diry==0){
			return p.dirx==dirx||p.dirx==-dirx;
		}
		else{
			return false;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDirx() {
		return dirx;
	}

	public int getDiry() {
		return diry;
	}
	
	
}
