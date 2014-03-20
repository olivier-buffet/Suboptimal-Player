package InterfaceGraphique;



import Ecouteurs.ListenerBouton;
import Plateau.*;

import javax.swing.*;
import java.awt.*;
import java.io.PipedOutputStream;


public class IG extends JFrame{
	
	private MNK m;
	private JButton[][] bout;
	
	
	public IG(){
		PipedOutputStream out=new PipedOutputStream();
        Participant[] participants=null;
        try {
            participants= new Participant[]{new NegaMax(), new Joueur(out)};
        } catch (Exception e) {
            System.exit(-1);
        }
        m= new MNK(participants);
		m.setVue(this);
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
				bout[i][j].addActionListener(new ListenerBouton(i*m.getN()+j,out));
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
		
		this.pack();
		this.setVisible(true);
		m.play();
	}
	
	public void MAJ(){
		int[][] plat = m.getPlateau();
		for(int i=0;i<plat.length;i++){
			for(int j=0;j<plat[0].length;j++){
				if(plat[i][j]==1){
					bout[i][j].setIcon(new ImageIcon(getClass().getResource("valider.png")));
					bout[i][j].setFocusable(false);
				}
				else if(plat[i][j]==2){
					bout[i][j].setIcon(new ImageIcon(getClass().getResource("croix.png")));
					bout[i][j].setFocusable(false);
				}
			}
		}
	}
	
	public static void main(String[] args){
		new IG();
	}
}
