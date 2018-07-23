import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Perdu extends JPanel{

	private Image im ;
	private JButton np = new JButton("Nouvelle partie!");
	private PerduLabel pl = new PerduLabel();
	private PerduMot pm;
	private int score;
	private JOptionPane jop = new JOptionPane();;
	
	public Perdu(String mot, int score){
		this.setPreferredSize(new Dimension(1000,800));
		this.setBackground(Color.white);
		
		this.score =score;
		try {
			im = ImageIO.read(new File("images/intro.jpg"));
			ImageIcon icon = new ImageIcon(im); 
			JLabel perduImg = new JLabel();
			perduImg.setIcon(icon);
			this.add(perduImg);
			
			pm = new PerduMot(mot);
			
			this.add(pl);
			this.add(pm);
			np.setPreferredSize(new Dimension(200,50));
			np.setBackground(Color.white);
			np.setFont(new Font("arial",20,20));
			np.setName("start");
			this.add(np);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public JButton getNp(){
		return np;
	}
	public JOptionPane getJop() {
		return jop;
	}
	public String afficherJop(){
		
		String nom = jop.showInputDialog(null, "Félicitation! votre score est: "+score+"\nQuel est votre nom?", "Score", JOptionPane.QUESTION_MESSAGE);
		return nom;
	}
	public boolean afficherConfirm() {
		// TODO Auto-generated method stub
		JOptionPane jop = new JOptionPane();			
		int option = jop.showConfirmDialog(null, "Voulez-vous effacer votre score précédent?", "Score", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
			return true;
		}
		else{return false;}
	}
}
class PerduLabel extends JPanel{
	
	PerduLabel(){
		this.setPreferredSize(new Dimension(1000,40));
		this.setBackground(Color.white);
		
		JLabel perdu = new JLabel("PERDU!");
		perdu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		perdu.setForeground(Color.red);
		this.add(perdu);
	}
}

class PerduMot extends JPanel{
	
	PerduMot(String mot){
		this.setPreferredSize(new Dimension(1000,40));
		this.setBackground(Color.white);
		
		JLabel motLabel = new JLabel("Le mot était: "+mot);
		motLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		motLabel.setForeground(Color.red);
		this.add(motLabel);
	}
}