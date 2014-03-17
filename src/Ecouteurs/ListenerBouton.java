package Ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;


public class ListenerBouton implements ActionListener{

	protected int i;
    protected OutputStream out;
	
	public ListenerBouton(int i, OutputStream out){
		this.i=i;
        this.out=out;
	}

	public void actionPerformed(ActionEvent e) {
	    try{
            out.write(i);
            out.flush();
        }catch (Exception ex){
        }
	}
}
