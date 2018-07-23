import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReglesPanel extends JPanel {

	private JTextArea regles;
	private JLabel regles1;
	private JTextArea regles2;
	private JLabel titre;
	private ImageIcon bgImage = new ImageIcon("images/pendu.gif");
	private JLabel bg = new JLabel(bgImage);
	private JButton start = new JButton("JOUER!");
	
	public ReglesPanel(){
		
		this.setPreferredSize(new Dimension(1000,800));
		this.setBackground(Color.white);
		this.setName("regles");		
		
		
		setRegles();
		this.add(bg,BorderLayout.EAST);
		start.setPreferredSize(new Dimension(200,50));
		start.setBackground(Color.white);
		start.setFont(new Font("arial",20,20));
		start.setName("start");
		this.add(start);
	}

	
	private void setRegles() {
		// TODO Auto-generated method stub
		
		String titre = "Le jeu du pendu:";
		this.titre = new JLabel(titre);
		this.titre.setFont(new Font("arial", 35, 35));
		this.titre.setPreferredSize(new Dimension(900,100));
		this.add(this.titre);
		
		
		String text = "Vous avez 7 coups pour trouver le mot caché. Si vous réussissez, on recommence!\n"
				+ "Plus vous trouvez de mots, plus votre score grandi. Alors à vous de jouer!";
		regles = new JTextArea(text);
		regles.setFont(new Font("arial", 18, 18));
		regles.setPreferredSize(new Dimension(900,60));
		this.add(regles);
		
		text = "Compte des points:";
		regles1 = new JLabel(text);
		regles1.setFont(new Font("arial",22, 22));
		regles1.setPreferredSize(new Dimension(900,20));
		this.add(regles1);
		
		
		text = "Mot trouvé sans erreur: 100 points.\n"
				+ "Mot trouvé avec 1 erreur: 50 points.\n"
				+"Mot trouvé avec 2 erreur: 35 points.\n"+"Mot trouvé avec 3 erreur: 25 points.\n"
				+"Mot trouvé avec 4 erreur: 15 points.\n"
				+"Mot trouvé avec 5 erreur: 10 points.\n"
				+"Mot trouvé avec 6 erreur: 5 points.\n";
		regles2 = new JTextArea(text);
		regles2.setFont(new Font("arial", 20, 20));
		regles2.setPreferredSize(new Dimension(400,300));
		this.add(regles2);
		
		
	}
	
	public JButton getStart(){
		return start;
	}
}
