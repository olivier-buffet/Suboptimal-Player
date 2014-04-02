package InterfaceGraphique;



import Ecouteurs.ListenerBouton;
import Plateau.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class IG extends JFrame implements Observer{
	
	private MNK m;
	private JButton[][] bout;
    private Semaphore s1=new Semaphore(1),s2=new Semaphore(0);
    private int[] xy=new int[2];
	
	public IG(){
        Participant[] participants=null;
        try {
            participants= new Participant[]{new Joueur(xy,s1,s2),new NegaAlphaBeta(5)};
        } catch (Exception e) {
            System.exit(-1);
        }
        m= new MNK(6,6,5,participants);
		m.addObserver(this);
		this.setLayout(new BorderLayout());
		JPanel centre = new JPanel();
		
		GridLayout lay = new GridLayout(m.getM(),m.getN());
		lay.setHgap(5);
		lay.setVgap(5);
		centre.setLayout(lay);
		centre.setBackground(Color.black);
		
		this.setPreferredSize(new Dimension(400,450));
		bout = new JButton[m.getM()][m.getN()];
		for(int i=0;i<bout.length;i++){
			for(int j=0;j<bout[0].length;j++){
				bout[i][j]= new JButton();
				bout[i][j].setBorderPainted(false);
				bout[i][j].setBackground(Color.white);
				bout[i][j].addActionListener(new ListenerBouton(i,j,xy,s1,s2));
				centre.add(bout[i][j]);
			}
		}
		
		this.add(centre,BorderLayout.CENTER);
		
		JMenuBar menu = new JMenuBar();
		JMenu game = new JMenu("Partie");
		JMenuItem nouv = new JMenuItem("Nouvelle partie");
		game.add(nouv);
		menu.add(game);
		
		this.add(menu,BorderLayout.NORTH);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		m.play();
	}

	public static void main(String[] args){
		new IG();
	}

    @Override
    public void update(Observable o, Object arg) {
        int[][] plat = m.getPlateau();
        for(int i=0;i<plat.length;i++){
            for(int j=0;j<plat[0].length;j++){
                if(plat[i][j]==1){
                    bout[i][j].setIcon(new ImageIcon(getClass().getResource("rond.png")));
                    bout[i][j].setFocusable(false);
                }
                else if(plat[i][j]==2){
                    bout[i][j].setIcon(new ImageIcon(getClass().getResource("croix.png")));
                    bout[i][j].setFocusable(false);
                }
            }
        }
    }
}
