import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PenduFrame extends JFrame {

	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenuItem nouveau = new JMenuItem("Nouveau");
	private JMenuItem regles = new JMenuItem("Règles");
	private JMenuItem scores = new JMenuItem("Scores");
	private JMenuItem apropos = new JMenuItem("A propos?");
	
	private JPanel jp = new ReglesPanel();
	
	public PenduFrame(){
		
		this.setTitle("Le pendu");
	    this.setSize(1000, 800);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
		
		setMenu();
		this.getContentPane().add(jp);
		this.setVisible(true);
		
	}
	
	private void setMenu() {
		// TODO Auto-generated method stub
		fichier.add(nouveau);
		fichier.add(regles);
		fichier.add(scores);
		
		nouveau.setName("nouveau");
		regles.setName("regles");
		scores.setName("scores");
		apropos.setName("apropos");
		
		menuBar.add(fichier);
		menuBar.add(apropos);
		
		this.add(menuBar, BorderLayout.NORTH);
	}
	
	public JPanel getjp(){
		return jp;
	}
	
	public JMenuItem getScores(){
		return scores;
		
		
	}
	public void setPage(JPanel jp){
		
		Container cp = this.getContentPane();
		
		cp.remove(this.jp);
		this.jp = jp;
		cp.add(this.jp);
		
		revalidate();
		
		
	}

	public JMenuItem getNouveau() {
		// TODO Auto-generated method stub
		return nouveau;
	}
	public JMenuItem getRegles() {
		// TODO Auto-generated method stub
		return regles;
	}

	public JMenuItem getApropos() {
		// TODO Auto-generated method stub
		return apropos;
	}
}
