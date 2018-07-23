import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JeuPanel extends JPanel {

	private Dessin dessin = new Dessin();
	private TraitLettres tl ;
	private Lettres lettres = new Lettres();
	
	
	public JeuPanel(char[] mot){
		
		this.setPreferredSize(new Dimension(1000,800));
		this.setBackground(Color.white);
		this.setName("jeu");
		
		this.add(dessin);
		tl = new TraitLettres(mot);
		this.add(tl);
		this.add(lettres,BorderLayout.SOUTH);
		
	}

	public Lettres getLettres() {
		// TODO Auto-generated method stub
		return lettres;
	}
	public Dessin getDessin() {
		// TODO Auto-generated method stub
		return dessin;
	}
	public TraitLettres getTraitLettres(){
		return tl;
	}
	
	
}
class Lettres extends JPanel{
	
	
	
	private JButton a = new JButton("A");
	private JButton b = new JButton("B");
	private JButton c = new JButton("C");
	private JButton d = new JButton("D");
	private JButton e = new JButton("E");
	private JButton f = new JButton("F");
	private JButton g = new JButton("G");
	private JButton h = new JButton("H");
	private JButton i = new JButton("I");
	private JButton j = new JButton("J");
	private JButton k = new JButton("K");
	private JButton l = new JButton("L");
	private JButton m = new JButton("M");
	private JButton n = new JButton("N");
	private JButton o = new JButton("O");
	private JButton p = new JButton("P");
	private JButton q = new JButton("Q");
	private JButton r = new JButton("R");
	private JButton s = new JButton("S");
	private JButton t = new JButton("T");
	private JButton u = new JButton("U");
	private JButton v = new JButton("V");
	private JButton w = new JButton("W");
	private JButton x = new JButton("X");
	private JButton y = new JButton("Y");
	private JButton z = new JButton("Z");
	
	//tableau de boutons
	private JButton alphabet[]= {a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z};
	
	
	 public Lettres(){
		 
		 	this.setPreferredSize(new Dimension(750,100));
			this.setBackground(Color.white);
			this.setName("lettres");
			
			for(JButton lettre:alphabet){
				
				Dimension dimension = new Dimension(50,40);
				lettre.setPreferredSize(dimension);
				lettre.setBackground(Color.white);
				this.add(lettre);
			}
			
	 }
	 public JButton[] getAlphabet(){
		 
		 return alphabet;
	 }
	 
}

class Dessin extends JPanel{
	
	//savoir le nombre d'erreurs
	private int erreurs;
	private Image im;
	
	public Dessin(){
		
		this.setPreferredSize(new Dimension(1000,500));
		this.setBackground(Color.white);
		this.setName("dessin");
		setImage();
		
	}
	public void setErreurs(int erreurs){
		
		this.erreurs = erreurs;
	}
	
	public void paintComponent(Graphics g){
		
		 
	        g.drawImage(im,325,50,null);
	 
	}
	public void setImage(){
		
		try {
			switch(erreurs){
			
			case 0:
				im = ImageIO.read(new File("images/0.jpg"));
				break;
			case 1:
				im = ImageIO.read(new File("images/1.jpg"));
				break;
			case 2:
				im = ImageIO.read(new File("images/2.jpg"));
				break;
			case 3:
				im = ImageIO.read(new File("images/3.jpg"));
				break;
			case 4:
				im = ImageIO.read(new File("images/4.jpg"));
				break;
			case 5:
				im = ImageIO.read(new File("images/5.jpg"));
				break;
			case 6:
				im = ImageIO.read(new File("images/6.jpg"));
				break;
			case 7:
				im = ImageIO.read(new File("images/7.jpg"));
				break;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class TraitLettres extends JPanel{
	
	private int width;
	private char[] mot;
	private int traits;
	private ArrayList<Character> lettresTrouvees= new ArrayList<>();
	
	public TraitLettres(char[] mot){
		
		
		this.setBackground(Color.white);
		this.setName("traits");
		
		this.mot = mot;
		traits = mot.length;
		int width = traits*40-10;
		if(width<250){
			int dif = 250-width;
			width+=dif;
			this.width = width;
		}
		this.setPreferredSize(new Dimension(width,100));
		
		
		
	}
	public void setLettreTrouvees(char c){
		
		lettresTrouvees.add(c);
		
	}
	public void paintComponent(Graphics g){
		
		int xpos=0;
		removeAll();
		g.setColor(Color.white);
		g.clearRect(0, 0, width, 100);
		System.out.println(lettresTrouvees);
		for(int x=0;x<traits;x++){
			
			g.setColor(Color.black);
			
			if(!lettresTrouvees.contains(mot[x])){
				
				g.fillRect(xpos,80,30,5);
				
				
			}
			else{
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
				g.drawChars(mot, x, 1, xpos, 80);
				
				
			}
			xpos+=40;
		}
	}
}