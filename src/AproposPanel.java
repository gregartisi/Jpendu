import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AproposPanel extends JPanel {

	public AproposPanel(){
		this.setPreferredSize(new Dimension(1000,800));
		this.setBackground(Color.white);
		this.setName("A propos");		
		
		JLabel ap = new JLabel("Réalisé par Greg Artisi!");
		ap.setFont(new Font("arial",22, 22));
		ap.setPreferredSize(new Dimension(250,50));
		this.add(ap);
	}
}
