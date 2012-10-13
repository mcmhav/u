package b;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class S {

	private HashMap<String, ArrayList<String>> T = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<String>> F = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<String>> M = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> fg = new ArrayList<String>();

	private File file = new File("/home/cube/Documents/A/u.txt");
	
	private FileOutputStream out;
	private PrintStream prt;
	
	private G g;
	
	public S(G g){
	    this.g = g;
	}
	
	public void save(){
		try {
			T.putAll(g.getT());
	    	F.putAll(g.getF());
	    	M.putAll(g.getM());
	    	fg.addAll(g.getFG());
	        out = new FileOutputStream(file);
	        prt = new PrintStream(out);
	        prt.println(T.entrySet());
	        prt.println(F.entrySet());
	        prt.println(M.entrySet());
	        prt.println(g.getFGCM().entrySet());
	        prt.close();
	    } catch(Exception e) {
	        System.out.println("Write error");
	    }
	}
}
