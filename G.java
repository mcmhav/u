package b;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class G {

	HashMap<String, ArrayList<String>> T = new HashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> F = new HashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> M = new HashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> FGCM = new HashMap<String, ArrayList<String>>();
	
	ArrayList<String> fg = new ArrayList<String>();
	
	Calendar c;
	
	ArrayList<HashMap<String, ArrayList<String>>> us = new ArrayList<HashMap<String,ArrayList<String>>>();
	
	ArrayList<String> ta = new ArrayList<String>();
	
	File f = new File("/home/cube/Documents/A/u.txt");
	FileInputStream fis = null;
    BufferedInputStream bis = null;
    BufferedReader br = null;
    
	public G(){
		
	}
	
	public void getU(){
		us.add(T);
		us.add(F);
		us.add(M);
		us.add(FGCM);
		
		try{
			fis = new FileInputStream(f);
			bis = new BufferedInputStream(fis);
			br = new BufferedReader(new InputStreamReader(bis));

			int c = 0;
			while(br.ready()){
				String inn = br.readLine();
				
//				if(c<3){
					String in = inn.substring(1, inn.length()-2);
					int l = in.split("], ").length;
					
					String[] s = new String[l];
					s = in.split("], ");
					
					for(int i = 0; i < l; i++){
						String[] s2 = new String[1];
						s2 = s[i].split("=");
						int temp = s2[1].split(", ").length;
						String[] s3 = new String[temp];
						s3 = s2[1].split(", ");
						s3[0] = s3[0].substring(1);
						ArrayList<String> atemp = new ArrayList<String>();
						for(int j = 0; j < temp; j ++){
							atemp.add(s3[j]);
						}
						us.get(c).put(s2[0], atemp);
					}
				c++;
			}
			fis.close();
			bis.close();
			br.close();
		} catch (Exception e){
			System.err.println(e.getStackTrace());
		}
	}
	
	public void update(){
		
	}
	
	public void addNewFGCM(String v, String s){
		FGCM.get(v).add(s);
	}
	public HashMap<String, ArrayList<String>> getFGCM(){
		return FGCM;
	}
	
	public void addNewFG(String s){
		fg.add(s);
	}
	public ArrayList<String> getFG(){
		return fg;
	}
	
	public HashMap<String, ArrayList<String>> getT(){
		return T;
	}
	public void addNewT(String s){
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("0:0:0:0");
		T.put(s, ar);
	}
	public void addTUse(String s, String u){
		c = new GregorianCalendar();
		u += ":"+c.get(Calendar.DATE)+":"+c.get(Calendar.MONTH)+":"+c.get(Calendar.YEAR);
		T.get(s).add(u);
		T.put(s, T.get(s));
	}
	public void addTUse(String s, String u, String d){
		u += d;
		T.get(s).add(u);
		T.put(s, T.get(s));
	}
	
	public HashMap<String, ArrayList<String>> getF(){
		return F;
	}
	public void addNewF(String s){
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("0:0:0:0");
		F.put(s, ar);
	}
	public void addFUse(String s, String u){
		c = new GregorianCalendar();
		u += ":"+c.get(Calendar.DATE)+":"+c.get(Calendar.MONTH)+":"+c.get(Calendar.YEAR);
		F.get(s).add(u);
		F.put(s, F.get(s));
	}
	public void addFUse(String s, String u, String d){
		u += d;
		F.get(s).add(u);
		F.put(s, F.get(s));
	}
	
	public HashMap<String, ArrayList<String>> getM(){
		return M;
	}
	public void addNewM(String s){
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("0:0:0:0");
		M.put(s, ar);
	}
	public void addMUse(String s, String u){
		c = new GregorianCalendar();
		u += ":"+c.get(Calendar.DATE)+":"+c.get(Calendar.MONTH)+":"+c.get(Calendar.YEAR);
		M.get(s).add(u);
		M.put(s, M.get(s));
	}
	public void addMUse(String s, String u, String d){
		u += d;
		M.get(s).add(u);
		M.put(s, M.get(s));
	}
}
