import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoresPanel extends JPanel {


	public ScoresPanel(ArrayList<Entry<String, Integer>> gamePlayer) {
		// TODO Auto-generated constructor stub
		
		this.setPreferredSize(new Dimension(1000,800));
		this.setBackground(Color.white);
		this.setName("Scores");		
		
		JLabel scores = new JLabel("Scores:");
		scores.setPreferredSize(new Dimension(950,40));
		scores.setFont(new Font("arial",Font.BOLD,25));
		this.add(scores);
		System.out.println(gamePlayer);
		int x = 1;
		for(Entry<String,Integer> player : gamePlayer){
			
			JLabel jl = new JLabel(x+": "+player.getKey() + ": "+player.getValue()+" points.");
			jl.setPreferredSize(new Dimension(950,30));
			jl.setFont(new Font("arial",Font.BOLD,20));
			this.add(jl);
			x++;
		}
	}
}
