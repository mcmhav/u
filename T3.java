package b;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class T3 {
	
	public static int getMonMax(int m, int y){
		int max = 0;
		
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

	  public static void main(String[] args) {
		  Calendar cal = new GregorianCalendar();
		  System.out.println(getMonMax(3, 2011));
	  }
}