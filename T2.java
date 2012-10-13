package b;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

public class T2 {

	HashMap<String, ArrayList<String>> T = new HashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> F = new HashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> M = new HashMap<String, ArrayList<String>>();
	
	ArrayList<HashMap<String, ArrayList<String>>> us = new ArrayList<HashMap<String,ArrayList<String>>>();
	
	ArrayList<String> ta = new ArrayList<String>();
	
	File f = new File("/home/cube/Documents/A/u.txt");
	FileInputStream fis = null;
    BufferedInputStream bis = null;
    BufferedReader br = null;
	
	public void t(){
		
		us.add(T);
		us.add(F);
		us.add(M);
		
		try{
			fis = new FileInputStream(f);
			bis = new BufferedInputStream(fis);
			br = new BufferedReader(new InputStreamReader(bis));

			int c = 0;
			while(br.ready()){
				String inn = br.readLine();
				String in = inn.substring(1, inn.length()-2);
				
				int l = in.split("], ").length;
				
				String[] s = new String[l];
				s = in.split("], ");
				
				for(int i = 0; i < l; i++){
					String[] s2 = new String[1];
					System.out.println(s[i].split("="));
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

	public static void main(String[] args) {
		T2 t = new T2();
		t.t();
		Calendar c = new GregorianCalendar();
		System.out.println(c.get(Calendar.DATE));
		System.out.println(c.get(Calendar.MONTH));
		System.out.println(c.get(Calendar.YEAR));
		System.out.println(c.getTime());
		
//		Mon May 16 09:10:01 CEST 2011
	}
}
