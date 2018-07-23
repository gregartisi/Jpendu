import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Controller {

	private PenduFrame pendu;
	private ReglesPanel regles;
	private JeuPanel jeuPanel;
	private AproposPanel apropos;
	private JButton start;
	private JeuListener jeuListener = new JeuListener();
	private LettresListener lettresListener = new LettresListener();
	private MenuListener menuListener = new MenuListener();
	private ScoresPanel scores;
	
	private String mot;
	private ArrayList<Character> lettresTrouvees= new ArrayList<>();
	private int erreurs;
	private Perdu perdu;
	private Gagne gagne;
	private int point;
	private String nom;
	private Map<String,Integer> gamePlayer = new HashMap<>();
	
	
	public Controller(PenduFrame pendu){
		
		this.pendu = pendu;
	
		regles = (ReglesPanel) pendu.getjp();
		start = regles.getStart();
		start.addActionListener(jeuListener);
		getPoint();
		
		pendu.getScores().addActionListener(menuListener);
		pendu.getNouveau().addActionListener(menuListener);
		pendu.getRegles().addActionListener(menuListener);
		pendu.getApropos().addActionListener(menuListener);
	}
	
	public void setErreurs(){
		
		erreurs++;
		jeuPanel.getDessin().setErreurs(erreurs);
		
	}
	
	public void setMot(){
		
		int nbre = (int)(Math.random()*336529);
		
		this.mot = null;
		lettresTrouvees.clear();
		lettresTrouvees.add('-');
		File f = new File("dictionnaire/dictionnaire.txt");
		
		try(LineNumberReader br = new LineNumberReader(new FileReader(f))){
			
			while(br.readLine() != null){
				if(br.getLineNumber()==nbre-1){
					String motUTF = br.readLine().toUpperCase();
					String mot =  Normalizer.normalize(motUTF, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
					
					System.out.println(nbre +" "+mot);
					this.mot = mot;
					break;
				}
			}
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public void testLettre(String lettre){
		
		if(this.mot.toString().contains(lettre)){
			char l = lettre.charAt(0);
			char[] motTab = mot.toCharArray();
			lettresTrouvees.add(l);
			boolean testMot = true;
			for(int x=0;x<motTab.length;x++){
				
				if(!lettresTrouvees.contains(motTab[x])){
					testMot = false;break;
				}
			}
			if(!testMot)	{	
					jeuPanel.getTraitLettres().setLettreTrouvees(l);
			}
			else{
				calculPoint();
				gagne = null;
				gagne = new Gagne(mot, point);
				pendu.setPage(gagne);
				gagne.getNp().addActionListener(jeuListener);}
		}
		else{
			
			setErreurs();
			if(erreurs==7){
				
				perdu = null;
				perdu = new Perdu(mot,point);
				pendu.setPage(perdu );
				
					
				perdu.getNp().addActionListener(jeuListener);
				if(point>0){
					
					nom = perdu.afficherJop();
					if(gamePlayer.containsKey(nom)){
						if(perdu.afficherConfirm()){
							
							gamePlayer.put(nom, point);
						}
					}
					
					point = 0;
					savePoint();
				}
			}
			else{
			jeuPanel.getDessin().setImage();
			}
			
		}
		
		jeuPanel.repaint();
		
		jeuPanel.getDessin().repaint();
	

	}
	
	

	public void savePoint(){
		
		//enregistre les point dans un fichier
		try(
				BufferedWriter dos =new BufferedWriter( new FileWriter( new File("scores/game.txt")))){

				for(String player : gamePlayer.keySet()){
					
					Integer p = gamePlayer.get(player);
					String playerPoint = player+"/"+p+"\n";
					dos.write(playerPoint);
				}
				
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void getPoint(){
		
		//extrait les données du fichier
		try(BufferedReader br = new BufferedReader(new FileReader(new File("scores/game.txt")))){
			
			String line;
			while(( line = br.readLine())!=null){
				String player = line.split("/")[0];
				Integer point = Integer.parseInt(line.split("/")[1]);
				gamePlayer.put(player, point);
			}
				
		}catch(IOException e){e.printStackTrace();}
	}
	public void calculPoint(){
		
		switch(erreurs){
		
		case 0: point+= 100;break;
		case 1: point+= 50;break;
		case 2: point+= 35;break;
		case 3: point+= 25;break;
		case 4: point+= 15;break;
		case 5: point+= 10;break;
		case 6: point+= 5;break;
		
		}
	}
	private int getErreurs() {
		// TODO Auto-generated method stub
		return erreurs;
	}
	
	private ArrayList<Entry<String,Integer>> orderScores(){
		
		ArrayList<Entry<String,Integer>> sortedEntries = new ArrayList<Entry<String,Integer>>(gamePlayer.entrySet());
		 Collections.sort(sortedEntries, 
		            new Comparator<Entry<String,Integer>>() {
		                @Override
		                public int compare(Entry<String,Integer> e1, Entry<String,Integer> e2) {
		                    return e2.getValue().compareTo(e1.getValue());
		                }
		            }
		    );
		
		return sortedEntries;
		
	}
	
	class MenuListener implements ActionListener{

		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JMenuItem source = (JMenuItem) arg0.getSource();
			String bouton = source.getName();
			
			switch (bouton){
			
			case "nouveau":
				jeuPanel = null;
				erreurs = 0;
				setMot();
				jeuPanel = new JeuPanel(mot.toCharArray());
				
				pendu.setPage(jeuPanel);
				for(JButton lettres :jeuPanel.getLettres().getAlphabet()){
					
					lettres.addActionListener(lettresListener);
				}
				break;
			case "scores":
				
				ArrayList<Entry<String,Integer>> orderedPlayer = orderScores();
				scores = new ScoresPanel(orderedPlayer);
				pendu.setPage(scores);
				
				break;
			case "regles":
				regles = null;
				regles = new ReglesPanel();
				regles.getStart().addActionListener(jeuListener);
				pendu.setPage(regles);
				break;
			case "apropos":
				apropos = null;
				apropos = new AproposPanel();
				pendu.setPage(apropos);break;
				}
		}}
	
	class JeuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JButton source = (JButton) arg0.getSource();
			String bouton = source.getName();
			
			switch (bouton){
			
			case "start":
				
				jeuPanel = null;
				erreurs=0;
				setMot();
				jeuPanel = new JeuPanel(mot.toCharArray());
				
				pendu.setPage(jeuPanel);
				for(JButton lettres :jeuPanel.getLettres().getAlphabet()){
					
					lettres.addActionListener(lettresListener);
				}
				break;
			
			}
		}}
	
	class LettresListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			testLettre(((JButton)arg0.getSource()).getText());
		}}
}
