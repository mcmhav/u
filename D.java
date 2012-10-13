package b;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;

public class D extends JFrame{
	
	private JTextField[] jt1;
	private JTextField[] jt2;
	
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	
	private JLabel empt;
	
	private JButton add;
	private JButton close;
	private JButton next;
	private JButton prev;
	
	private JPanel p;
	
	private GridBagConstraints c;
	
	private G g;
	
	private D d;
	
	private int ml;
	private int m;
	private int y;
	private int totGrid;
	private int count;
	private int max;
	
	private ArrayList<Color> col = new ArrayList<Color>();
	
	private HashMap<String, ArrayList<String>> hmF = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<String>> hmT = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<String>> hmM = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<String>> FGCM = new HashMap<String, ArrayList<String>>();
	
	private ArrayList<String> fg = new ArrayList<String>();
	
	private HashMap<String, HashMap<String, ArrayList<String>>> tfm = new HashMap<String, HashMap<String,ArrayList<String>>>();
	
	private Calendar cal;
	
	private JComboBox jcbF;
	private JComboBox jcbM;
	private JComboBox jcbT;
	
	private ArrayList<Integer> valF = new ArrayList<Integer>();
	private ArrayList<Integer> valT = new ArrayList<Integer>();
	private ArrayList<Integer> valM = new ArrayList<Integer>();
	
	private HashMap<String, ArrayList<Integer>> valtfm = new HashMap<String, ArrayList<Integer>>();
	private HashMap<String, Boolean> all = new HashMap<String, Boolean>();
	private HashMap<String, ArrayList<String>> archhm = new HashMap<String, ArrayList<String>>();
	
	public D(){
		g = new G();
		g.getU();
		this.d = this;
		
		cal = new GregorianCalendar();
		m = cal.get(Calendar.MONTH);
		y = cal.get(Calendar.YEAR);
		
		empt = new JLabel("test");
		
		initCol();
		
		all.put("T", true);
		all.put("F", true);
		all.put("M", true);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		hmF.putAll(g.getF());
		hmT.putAll(g.getT());
		hmM.putAll(g.getM());
		
		addFGvalues();
		addFGCMvalues();
		
		tfm.put("T", hmT);
		tfm.put("F", hmF);
		tfm.put("M", hmM);
		tfm.put("fg", FGCM);
		
		valtfm.put("T", valT);
		valtfm.put("F", valF);
		valtfm.put("M", valM);
		
		c = new GridBagConstraints();

		p = new JPanel(new GridBagLayout());
		
		jl1 = new JLabel("T");
		jl2 = new JLabel("F");
		jl3 = new JLabel("M");
		
		add = new JButton("+");
		add.addActionListener(new AL());
		close = new JButton("-");
		close.addActionListener(new AL2());
		next = new JButton(">");
		next.addActionListener(new AL6());
		prev = new JButton("<");
		prev.addActionListener(new AL7());
		
		Object[] temp3 = new Object[hmT.keySet().size()+1];
		temp3[0] = "All";
		for(int i = 0; i < hmT.keySet().size(); i++){
			temp3[i+1] = hmT.keySet().toArray()[i];
		}
		jcbT = new JComboBox(temp3);
		jcbT.addActionListener(new AL5());
		
		Object[] temp = new Object[hmF.keySet().size()+6];
		temp[0] = "All";
		temp[1] = "Fruit'n'Stuff";
		temp[2] = "Candy";
		temp[3] = "Dinner";
		temp[4] = "Drikke";
		temp[5] = "Annet";
		for(int i = 0; i < hmF.keySet().size(); i++){
			temp[i+6] = hmF.keySet().toArray()[i];
		}
		jcbF = new JComboBox(temp);
		jcbF.addActionListener(new AL3());
		
		Object[] temp2 = new Object[hmM.keySet().size()+1];
		temp2[0] = "All";
		for(int i = 0; i < hmM.keySet().size(); i++){
			temp2[i+1] = hmM.keySet().toArray()[i];
		}
		jcbM = new JComboBox(temp2);
		jcbM.addActionListener(new AL4());
		
		drawer3();
		this.pack();
	}
	
	private void drawer3(){
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=2;
		p.add(jl2,c);
		c.gridwidth=1;
		toSplitD(hmF);
		
		c.gridx=5;
		c.gridy=0;
		c.gridwidth=2;
		p.add(jl1,c);
		c.gridwidth=1;
		toSplitD(hmT);
		c.gridwidth=2;
		p.add(jl3,c);
		c.gridwidth=1;
		toSplitD(hmM);
		
		
		c.gridwidth=3;
		c.gridheight=16;
		c.gridx=2;
		c.gridy=0;
		Grr grr = new Grr("T");
		p.add(grr,c);
		c.gridy=15;
		Grr grr2 = new Grr("F");
		p.add(grr2, c);
		c.gridy=30;
		Grr grr3 = new Grr("M");
		p.add(grr3, c);
		c.gridheight=1;
		c.gridwidth=1;
		
		
		c.gridx=3;
		c.gridy=46;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.WEST;
		p.add(prev,c);
		c.gridx=4;
		c.anchor=GridBagConstraints.CENTER;
		p.add(add,c);
		c.gridx=5;
		c.anchor=GridBagConstraints.EAST;
		p.add(next,c);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		p.add(close,c);
		
		add(p);
	}
	
	private class AL7 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(m==0){
				m=11;
				y--;
			}else{
				m--;
			}
			repaint();
		}
	}
	
	private class AL6 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(m==11){
				m=0;
				y++;
			}else{
				m++;
			}
			repaint();
		}
	}
	
	public HashMap<String, HashMap<String, ArrayList<String>>> getTFM(){
		return tfm;
	}
	
	private void addFGvalues(){
		for(int i = 0; i < g.getFG().size(); i++){
			for(int j = 0; j < hmF.size(); j++){
				if(hmF.keySet().toArray()[j].equals(g.getFG().get(i))){
					for(int k = 0; k < hmF.get(hmF.keySet().toArray()[j]).size(); k++){
						fg.add(hmF.get(hmF.keySet().toArray()[j]).get(k));
					}
				}
			}
		}
	}
	
	private void addFGCMvalues(){
		ArrayList<String> temp;
		for(int r = 0; r < g.getFGCM().size(); r++){
			temp = new ArrayList<String>();
			temp.clear();
			for(int i = 0; i < g.getFGCM().get(g.getFGCM().keySet().toArray()[r]).size(); i++){
				for(int j = 0; j < hmF.size(); j++){
					if(hmF.keySet().toArray()[j].equals(g.getFGCM().get(g.getFGCM().keySet().toArray()[r]).get(i))){
						temp.addAll(hmF.get(hmF.keySet().toArray()[j]));
					}
				}
			}
			FGCM.put((String)g.getFGCM().keySet().toArray()[r], temp);
		}
	}
	
	private ArrayList<String> getValueAsArr(String s){
		ArrayList<String> a = new ArrayList<String>();
		
		for(int i = 0; i < hmF.size(); i++){
			if(s.equals(hmF.keySet().toArray()[i])){
				a.addAll(hmF.get(s));
				return a;
			}
		}
		for(int i = 0; i < hmM.size(); i++){
			if(s.equals(hmM.keySet().toArray()[i])){
				a.addAll(hmM.get(s));
				return a;
			}
		}
		for(int i = 0; i < hmT.size(); i++){
			if(s.equals(hmT.keySet().toArray()[i])){
				a.addAll(hmT.get(s));
				return a;
			}
		}
		
		return null;
	}
	
	private class AL5 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox b = (JComboBox)e.getSource();
			String s = (String)b.getSelectedItem();
			if(s.equals("All")){
				Grr grr = new Grr("T");
			}else{
				Grr grr = new Grr("T", s);
			}
			repaint();
			p.repaint();
		}
	}
	
	private class AL3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox b = (JComboBox)e.getSource();
			String s = (String)b.getSelectedItem();
			if(s.equals("All")){
				Grr grr2 = new Grr("F");
			}else if(s.equals("Fruit'n'Stuff")||s.equals("Dinner")||s.equals("Candy")||s.equals("Drikke")||s.equals("Annet")){
				Grr grr2 = new Grr("F", FGCM.get(s));
			}else{
				Grr grr2 = new Grr("F",s);
			}
			repaint();
			p.repaint();
		}
	}
	
	private class AL4 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox b = (JComboBox)e.getSource();
			String s = (String)b.getSelectedItem();
			if(s.equals("All")){
				Grr grr3 = new Grr("M");
			}else{
				Grr grr3 = new Grr("M",s);
			}
			repaint();
			p.repaint();
		}
	}

	private void drawer(){
		
		c.gridx=2;
		c.gridy=0;
		p.add(jl1, c);
		c.gridx=6;
		c.gridy=0;
		p.add(jl2, c);
		c.gridx=11;
		c.gridy=0;
		p.add(jl3, c);
		

		c.gridx=0;
		c.gridy=1;
		toSplitD(hmT);
		c.gridx = 4;
		c.gridy = 1;
		toSplitD(hmF);
		c.gridx = 8;
		c.gridy = 1;
		toSplitD(hmM);
		
		ml += 3;
		c.gridx=8;
		c.gridy=ml;
		p.add(add,c);
		c.gridx=0;
		c.gridy=ml;
		p.add(close,c);
		
		c.gridwidth=1;
		c.gridheight=ml-3;
		c.gridx=2;
		c.gridy=2;
		Arch ar = new Arch("T");
		ar.setPreferredSize(new Dimension(200,200));
		p.add(ar, c);
		c.gridx=6;
		c.gridy=2;
		Arch ar2 = new Arch("F");
		ar2.setPreferredSize(new Dimension(200,200));
		p.add(ar2, c);
		c.gridx=8;
		c.gridy=2;
		Arch ar4 = new Arch("fg");
		ar4.setPreferredSize(new Dimension(200,200));
		p.add(ar4, c);
		c.gridx=11;
		c.gridy=2;
		Arch ar3 = new Arch("M");
		ar3.setPreferredSize(new Dimension(200,200));
		p.add(ar3, c);
		
		add(p);
	}
	
	private void drawer2(){

		c.gridx=0;
		c.gridy=1;
		toSplitD(hmT);
		c.gridheight=1;
		c.gridx=5;
		c.gridy=totGrid;
		c.gridwidth=3;
		p.add(jcbT,c);
		c.gridwidth=1;
		
		c.gridx = 0;
		c.gridy = hmT.size()+5;
		toSplitD(hmF);
		c.gridx=5;
		c.gridy=totGrid;
		c.gridwidth=3;
		p.add(jcbF,c);
		c.gridwidth=1;
		
		c.gridx = 0;
		c.gridy = hmF.size()+hmT.size()+hmM.size()+1;
		toSplitD(hmM);
		c.gridx=5;
		c.gridy=totGrid;
		c.gridwidth=3;
		p.add(jcbM,c);
		c.gridwidth=1;
		
		c.gridwidth=8;
		c.gridx=1;
		c.gridy=0;
		p.add(jl1, c);
		c.gridx=1;
		c.gridy=hmT.size()+4;
		p.add(jl2, c);
		c.gridx=1;
		c.gridy=hmF.size()+hmT.size()+hmM.size();
		p.add(jl3, c);
		c.gridwidth=1;
		
		
		ml += 3;
		c.gridx=5;
		c.gridy=ml*3+2;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.WEST;
		p.add(prev,c);
		c.gridx=7;
		c.anchor=GridBagConstraints.CENTER;
		p.add(add,c);
		c.gridx=7;
		c.anchor=GridBagConstraints.EAST;
		p.add(next,c);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		p.add(close,c);
		
		
		c.gridwidth=1;
		c.gridheight=11;
		c.gridx=4;
		c.gridy=0;
		Arch ar = new Arch("T");
		p.add(ar, c);
		c.gridx=4;
		c.gridy=hmT.size()+4;
		Arch ar4 = new Arch("fg");
		p.add(ar4, c);
		c.gridx=4;
		c.gridy=hmT.size()+13;
		Arch ar2 = new Arch("F");
		p.add(ar2, c);
		c.gridx=4;
		c.gridy=hmF.size()+hmT.size()+hmM.size();
		Arch ar3 = new Arch("M");
		p.add(ar3, c);
		
		c.gridwidth=3;
		c.gridheight=16;
		c.gridx=5;
		c.gridy=1;
		Grr grr = new Grr("T");
		p.add(grr,c);
		c.gridx=5;
		c.gridy=hmT.size()+6;
		Grr grr2 = new Grr("F");
		p.add(grr2, c);
		c.gridx=5;
		c.gridy=hmF.size()+hmT.size()+hmM.size()+2;
		Grr grr3 = new Grr("M");
		p.add(grr3, c);
		c.gridheight=1;
		c.gridwidth=1;
		
		
		add(p);
	}
	
	private int getMaxValue(HashMap<String, ArrayList<String>> hm, int mon){
		int max = 0;
		ArrayList<Integer> ai = new ArrayList<Integer>();
		ai.addAll(getValuesAsDates(hm,mon));
		for(int i = 0; i < ai.size(); i++){
			if(ai.get(i)>max){
				max = ai.get(i);
			}
		}
		return max;
	}
	
	private int getMaxValue(ArrayList<String> a, int mon){
		int max = 0;
		ArrayList<Integer> ai = new ArrayList<Integer>();
		ai.addAll(getValuesAsDates(a,mon));
		for(int i = 0; i < ai.size(); i++){
			if(ai.get(i)>max){
				max = ai.get(i);
			}
		}
		return max;
	}
	
	private class Grr extends JPanel{
		ArrayList<String> a;
		String s = "";
		String v = "";
		int maxCount = 0;
		
		public Grr(String s){
			this.s = s;
			maxCount = getMaxValue(tfm.get(s), m);
			valtfm.get(s).clear();
			valtfm.get(s).addAll(getValuesAsDates(tfm.get(s), m));
			all.put(s, true);
		}
		public Grr(String s,String v){
			a = new ArrayList<String>();
			a.addAll(getValueAsArr(v));
			this.v = v;
			this.s = s;
			maxCount = getMaxValue(a, m);
			valtfm.get(s).clear();
			valtfm.get(s).addAll(getValuesAsDates(a, m));
			all.put(s, false);
		}
		public Grr(String s, ArrayList<String> l){
			this.s = s;
			maxCount = getMaxValue(l, m);
			valtfm.get(s).clear();
			valtfm.get(s).addAll(getValuesAsDates(l, m));
			all.put(s, false);
		}
		protected void paintComponent(Graphics g){
			super.paintComponents(g);
			
			int width = getWidth();
			int height = getHeight();
			
			int interval = (width-40);
			int individualWidth = (int)((((width-40)/24)*0.60));
			individualWidth = 15;
			interval =18;
			
			count = getMonMax(m,y);
			int x = 0;
			g.drawLine(10, height - 45, width - 10, height - 45);
			int i;
			int barHeight = 0;
			for(i = 0; i < count; i ++){
				g.setColor(Color.blue);
				barHeight = (int)(((double)valtfm.get(s).get(i)/(double)maxCount)*(height-60));
				if(valtfm.get(s).get(i)!=0&&m==cal.get(Calendar.MONTH)){
					if(valtfm.get(s).get(i)<10){
						g.drawString(valtfm.get(s).get(i)+" ", x-14, height-48-barHeight);
					}else if(valtfm.get(s).get(i)>9&&valtfm.get(s).get(i)<100){
						g.drawString(valtfm.get(s).get(i)+" ", x-18, height-48-barHeight);
					}else if(valtfm.get(s).get(i)>99&&valtfm.get(s).get(i)<1000){
						g.drawString(valtfm.get(s).get(i)+" ", x-22, height-48-barHeight);
					}else if(valtfm.get(s).get(i)>999&&valtfm.get(s).get(i)<10000){
						g.drawString(valtfm.get(s).get(i)+" ", x-25, height-48-barHeight);
					}
				}
				if(all.get(s)){
					int temp = 0;
					int temp2 = 0;
					for(int j = 0; j < tfm.get(s).size(); j++){
						for(int h = 0; h < tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).size(); h++){
							if(Integer.parseInt(tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).get(h).split(":")[1])==i+1&&Integer.parseInt(tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).get(h).split(":")[2])==m){
								g.setColor(col.get(j));
								temp = (int)((double)Integer.parseInt(tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).get(h).split(":")[0])/(double)maxCount*(height-60));
								g.fillRect(x, height-45-temp-temp2, individualWidth, temp);
								temp2 += (int)((double)Integer.parseInt(tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).get(h).split(":")[0])/(double)maxCount*(height-60));
							}
						}
					}
					g.setColor(Color.blue);
					g.drawRect(x, height-45-temp2, individualWidth, temp2);
				}else{
					g.drawRect(x-18, height-45-barHeight, individualWidth, barHeight);
				}
				g.setColor(Color.blue);
				if(i+1==cal.get(Calendar.DATE)&&m ==cal.get(Calendar.MONTH)){
					g.setColor(Color.red);
				}
				g.drawString(i+1+" ", x, height-30);
				x += interval;
			}
			g.setColor(Color.blue);
			barHeight = (int)(((double)valtfm.get(s).get(i)/(double)maxCount)*(height-60));
			if(valtfm.get(s).get(i)!=0&&m==cal.get(Calendar.MONTH)){
				if(valtfm.get(s).get(i)<10){
					g.drawString(valtfm.get(s).get(i)+" ", x-14, height-48-barHeight);
				}else if(valtfm.get(s).get(i)>9&&valtfm.get(s).get(i)<100){
					g.drawString(valtfm.get(s).get(i)+" ", x-18, height-48-barHeight);
				}else if(valtfm.get(s).get(i)>99&&valtfm.get(s).get(i)<1000){
					g.drawString(valtfm.get(s).get(i)+" ", x-22, height-48-barHeight);
				}else if(valtfm.get(s).get(i)>999&&valtfm.get(s).get(i)<10000){
					g.drawString(valtfm.get(s).get(i)+" ", x-25, height-48-barHeight);
				}
			}
			if(all.get(s)){
				int temp = 0;
				int temp2 = 0;
				for(int j = 0; j < tfm.get(s).size(); j++){
					for(int h = 0; h < tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).size(); h++){
						if(Integer.parseInt(tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).get(h).split(":")[1])==i+1&&Integer.parseInt(tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).get(h).split(":")[2])==m){
							g.setColor(col.get(j));
							temp = (int)((double)Integer.parseInt(tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).get(h).split(":")[0])/(double)maxCount*(height-60));
							g.fillRect(x, height-45-temp-temp2, individualWidth, temp);
							temp2 += (int)((double)Integer.parseInt(tfm.get(s).get(tfm.get(s).keySet().toArray()[j]).get(h).split(":")[0])/(double)maxCount*(height-60));
						}
					}
				}
				g.setColor(Color.blue);
				g.drawRect(x, height-45-temp2, individualWidth, temp2);
			}else{
				g.drawRect(x-18, height-45-barHeight, individualWidth, barHeight);
			}
		}
		@Override
		public Dimension getPreferredSize(){
			return new Dimension(570,275);
		}
	}
	
	public int getMonMax(int m, int y){
		max = 0;
		
		switch(m){
			case 0: max = 31; break;
			case 1: max = (y%4==0&&y%100!=0||y%400==0) ? 28 : 29; break;
			case 2: max =31; break;
			case 3: max =30; break;
			case 4: max =31; break;
			case 5: max =30; break;
			case 6: max =31; break;
			case 7: max =31; break;
			case 8: max =30; break;
			case 9: max =31; break;
			case 10: max =30; break;
			case 11: max =31; break;
			default: max = -1; break;
		}
		
		return max; 
	}
	
	public int getMaxhmSize(){
		int max = 0;
		for(int i = 0; i < tfm.size(); i++){
			if(max<tfm.get(tfm.keySet().toArray()[i]).size()){
				max = tfm.get(tfm.keySet().toArray()[i]).size();
			}
		}
		return max;
	}
	
	public ArrayList<Integer> getValuesAsDates(HashMap<String, ArrayList<String>> hm, int m){
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < 32; i++){
			a.add(i, 0);
		}
		for(int i = 0; i < hm.size(); i ++){
			hm.get(hm.keySet().toArray()[i]);
			for(int j = 0; j < hm.get(hm.keySet().toArray()[i]).size(); j++){
				if(m==Integer.parseInt(hm.get(hm.keySet().toArray()[i]).get(j).split(":")[2])){
					int temp = a.get(Integer.parseInt(hm.get(hm.keySet().toArray()[i]).get(j).split(":")[1]));
					temp += Integer.parseInt(hm.get(hm.keySet().toArray()[i]).get(j).split(":")[0]);
					a.remove(Integer.parseInt(hm.get(hm.keySet().toArray()[i]).get(j).split(":")[1]));
					a.add(Integer.parseInt(hm.get(hm.keySet().toArray()[i]).get(j).split(":")[1]), temp);
				}
			}
		}
		return a;
	}
	
	private ArrayList<Integer> getValuesAsDates(ArrayList<String> a, int m){
		ArrayList<Integer> ai = new ArrayList<Integer>();
		for(int i = 0; i < 32; i ++){
			ai.add(i, 0);
		}
		for(int i = 0; i < a.size(); i++){
			if(m==Integer.parseInt(a.get(i).split(":")[2])){
				int temp = ai.get(Integer.parseInt(a.get(i).split(":")[1]));
				temp += Integer.parseInt(a.get(i).split(":")[0]);
				ai.remove(Integer.parseInt(a.get(i).split(":")[1]));
				ai.add(Integer.parseInt(a.get(i).split(":")[1]),temp);
			}
		}
		return ai;
	}
	
	private int getMonTotA(ArrayList<String> a, int m){
		int tot = 0;
		ArrayList<Integer> ai = new ArrayList<Integer>();
		ai.addAll(getValuesAsDates(a, m));
		for(int i = 0; i < ai.size(); i++){
			tot += ai.get(i);
		}
		return tot;
	}
	
	public int getMonTot(HashMap<String, ArrayList<String>> hm, int m){
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.addAll(getValuesAsDates(hm, m));
		int tot = 0;
		for(int i = 0; i < a.size(); i++){
			tot += a.get(i);
		}
		return tot;
	}
	
	private class Arch extends JPanel{
		
		String s = "";
		
		public Arch(String s){
			this.s = s;
		}
		public Arch(HashMap<String, ArrayList<String>> hm){
			archhm.clear();
			archhm.putAll(hm);
		}
		
		protected void paintComponent(Graphics g){
			super.paintComponents(g);
			
			int xc = getWidth() / 2;
			int yc = getHeight() / 2;
			int r = (int) (Math.min(getWidth(), getHeight()) * 0.4);
			
			int x = xc - r;
			int y = yc - r;
			
			double t1;
			double t2 = 0;
			for(int i = 0; i < tfm.get(s).size(); i++){
				g.setColor(col.get(i));
				
				double temp1 = getTV(tfm.get(s).get(tfm.get(s).keySet().toArray()[i]));
				double temp2 = getTot(tfm.get(s));
				t1 = ((temp1/temp2)*360);
				g.fillArc(x, y, 2 * r, 2 * r, (int)t2, (int)t1);
				t2 += t1;
			}
		}
		@Override
		public Dimension getPreferredSize(){
			return new Dimension(170,170);
		}
	}

	private class AL implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			A a = new A(g, d);
			a.setVisible(true);
			a.setLocation(1500, 200);
		}
	}
	
	public int getTV(ArrayList<String> a){
		int tot = 0;
		for(int i = 0; i < a.size(); i++){
			tot += Integer.parseInt(a.get(i).split(":")[0]);
		}
		return tot;
	}
	
	public D update(){
		dispose();
		D d = new D();
		d.setVisible(true);
		d.setLocationRelativeTo(null);
//		repaint();
		return d;
	}
	
	public int getTot(HashMap<String, ArrayList<String>> hm){
		int tot = 0;
		for(int i = 0; i < hm.size(); i ++){
			tot += getTV(hm.get(hm.keySet().toArray()[i]));
		}
		return tot;
	}
	
	public void toSplitD(HashMap<String, ArrayList<String>> hm){
		String in = hm.entrySet().toString();
		in = in.substring(1, in.length()-1);
		
		jt1 = new JTextField[hm.size()+1];
		jt2 = new JTextField[hm.size()+1];
		
		int tot = 0;
		
		if(hm.size()>ml){
			ml = hm.size();
		}
		
		String[] s2 = new String[hm.keySet().size()];
		
		for(int i = 0; i < hm.keySet().size(); i ++){
			s2[i] = (String) hm.keySet().toArray()[i];
		}
		int ii = 0;
		for(int i = 0; i < hm.size(); i ++){
//			if(i==14){
//				ii = c.gridy;
//				c.gridy -=14;
//				c.gridx +=2;
//			}
			c.gridy ++;
			
			jt1[i] = new JTextField();
			jt2[i] = new JTextField();
			
			jt1[i].setBackground(col.get(i));
			jt2[i].setBackground(col.get(i));
			
			jt1[i].setText((String)(hm.keySet().toArray()[i]));
			jt2[i].setText(""+getTV(hm.get(hm.keySet().toArray()[i])));
			
			jt1[i].setEditable(false);
			jt2[i].setEditable(false);
			
			p.add(jt1[i], c);
			c.gridx ++;
			p.add(jt2[i], c);
			c.gridx--;
			
			tot += getTV(hm.get(s2[i]));
		}
		if(ii>0){
			c.gridy = ii;
			c.gridx = 0;
			c.gridwidth = 2;
		}
		jt1[hm.size()] = new JTextField();
		jt1[hm.size()].setText("Total");
		jt2[hm.size()] = new JTextField();
		jt2[hm.size()].setText(""+tot);
		jt1[hm.size()].setEditable(false);
		jt2[hm.size()].setEditable(false);
		jt1[hm.size()].setBackground(Color.blue);
		jt2[hm.size()].setBackground(Color.blue);
		jt1[hm.size()].setForeground(Color.yellow);
		jt2[hm.size()].setForeground(Color.yellow);
		c.gridy++;
		totGrid = c.gridy;
		p.add(jt1[hm.size()],c);
//		if(ii>0){
//			c.gridx = 2;
//		}else{
//			c.gridx++;
//		}
		c.gridx++;
		p.add(jt2[hm.size()],c);
		c.gridx--;
		c.gridy++;
//		c.gridy++;
//		p.add(empt,c);
		c.gridwidth=1;
	}
	
	private class AL2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private void initCol(){
		col.add(Color.green);
		col.add(Color.yellow);
		col.add(Color.red);
		col.add(Color.LIGHT_GRAY);
		col.add(Color.cyan);
		col.add(Color.gray);
		col.add(Color.orange);
		col.add(Color.pink);
		col.add(Color.white);
		col.add(Color.magenta);
		
		col.add(Color.green);
		col.add(Color.yellow);
		col.add(Color.red);
		col.add(Color.LIGHT_GRAY);
		col.add(Color.cyan);
		col.add(Color.gray);
		col.add(Color.orange);
		col.add(Color.pink);
		col.add(Color.white);
		col.add(Color.magenta);

		col.add(Color.green);
		col.add(Color.yellow);
		col.add(Color.red);
		col.add(Color.LIGHT_GRAY);
		col.add(Color.cyan);
		col.add(Color.gray);
		col.add(Color.orange);
		col.add(Color.pink);
		col.add(Color.white);
		col.add(Color.magenta);

		col.add(Color.green);
		col.add(Color.yellow);
		col.add(Color.red);
		col.add(Color.LIGHT_GRAY);
		col.add(Color.cyan);
		col.add(Color.gray);
		col.add(Color.orange);
		col.add(Color.pink);
		col.add(Color.white);
		col.add(Color.magenta);

		col.add(Color.green);
		col.add(Color.yellow);
		col.add(Color.red);
		col.add(Color.LIGHT_GRAY);
		col.add(Color.cyan);
		col.add(Color.gray);
		col.add(Color.orange);
		col.add(Color.pink);
		col.add(Color.white);
		col.add(Color.magenta);
	}
	
	public static void main(String[] args) {
		D d = new D();
		d.setVisible(true);
		d.setLocationRelativeTo(null);
	}
}
