package Ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;


public class ListenerBouton implements ActionListener{

	protected int[] xy;
    protected int x,y;
    protected Semaphore s1,s2;
	
	public ListenerBouton(int x, int y, int[] xy, Semaphore s1, Semaphore s2){
		this.xy=xy;
        this.x=x;
        this.y=y;
        this.s1=s1;
        this.s2=s2;
	}

	public void actionPerformed(ActionEvent e) {
            try {
                s1.acquire();
                xy[0] = x;
                xy[1] = y;
                s2.release();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
	}
}
