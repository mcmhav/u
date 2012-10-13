package b;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class M extends JPanel{
	
	Calendar cal = new GregorianCalendar();
	HashMap<String, ArrayList<String>> hmM = new HashMap<String, ArrayList<String>>();
	ArrayList<String> te = new ArrayList<String>();
	
	public M(){
		te.add("te");
		te.add("te2");
		te.add("te3");
		te.add("te4");
		
		hmM.put("test", te);
		hmM.put("test2", te);
		hmM.put("test3", te);
		hmM.put("test4", te);
		hmM.put("test5", te);
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		
		int width = getWidth();
		int height = getHeight();
		
		int interval = (width-40);
		int individualWidth = (int)((((width-40)/24)*0.60));
		
		int count = cal.getMaximum(Calendar.DATE);
		
		int maxCount = 0;
		for(int i = 0; i < hmM.size(); i++){
			if(maxCount < getTV(hmM.get(hmM.keySet().toArray()[i]))){
				maxCount = getTV(hmM.get(hmM.keySet().toArray()[i]));
			}
		}
		
		int x = 30;
		
		g.drawLine(10, height - 45, width - 10, height - 45);
		
		ArrayList<Integer> val = new ArrayList<Integer>();
		val.addAll(getValuesAsDates(hmM, cal.get(Calendar.MONTH)));
		
		for(int i = 0; i < count; i ++){
			int barHeight = (int)(((double)val.get(i)/(double)maxCount)*(height-55));
			g.drawRect(x, height-45-barHeight, individualWidth, barHeight);
			g.drawString(i+" ", x, height-30);
			x += interval;
		}
	}
	public int getTV(ArrayList<String> a){
		int tot = 0;
		for(int i = 0; i < a.size(); i++){
			tot += Integer.parseInt(a.get(i).split(":")[0]);
		}
		return tot;
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
					a.add(Integer.parseInt(hm.get(hm.keySet().toArray()[i]).get(j).split(":")[1]), temp);
				}
			}
		}
		return a;
	}
	
	public static void main(String[] args) {
		
	}
}
