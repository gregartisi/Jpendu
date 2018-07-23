import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gagne extends JPanel{
	

		private Image im ;
		private JButton np = new JButton("Continuer!");
		private String result;
		private Integer point;
		
		public Gagne(String mot, int point){
			this.setPreferredSize(new Dimension(1000,800));
			this.setBackground(Color.white);
			
			result = mot;
			this.point = point;
				JLabel sep = new JLabel();
				sep.setPreferredSize(new Dimension(1000,500));
				JLabel sep2 = new JLabel();
				sep2.setPreferredSize(new Dimension(700,100));
				
				np.setPreferredSize(new Dimension(200,50));
				np.setBackground(Color.white);
				np.setFont(new Font("arial",20,20));
				np.setName("start");
				this.add(sep);
				this.add(sep2);
				this.add(np);
				
			
			
		}
		
		public void paintComponent(Graphics g){
			
			 Graphics2D g2 = (Graphics2D) g;
			 
			 g2.setColor(Color.white);
			 g2.fillRect(0, 0, 1000, 800);
			 
			try {
				im = ImageIO.read(new File("images/alright.jpg"));
				g2.drawImage(im, 200, 0, this);
			}
				catch(IOException e){e.printStackTrace();}
			
			char [] gag = {'B','R','A','V','O','!'};
			g2.setColor(Color.black);
			g2.setFont(new Font("arial",Font.BOLD,40));
			g2.drawChars(gag, 0, 6, 700,50);
			
			char [] result = this.result.toCharArray();
			g2.drawChars(result, 0,result.length, 30,650);
			
			char [] point = ("Score: "+this.point).toCharArray();
			g2.drawChars(point, 0,point.length, 650,650);
		}
		public JButton getNp(){
			return np;
		}
	}

		

	
