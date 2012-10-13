package b;

import java.util.HashMap;

public class F {

	HashMap<String, Integer> f = new HashMap<String, Integer>();
	
	public F(){
		G g = new G();
		f.putAll(g.getF());
	}
	
	public void addNewF(String s){
		f.put(s, 0);
	}
	
	public void addFUse(String s, int u){
		f.put(s, f.get(s) + u);
	}
	
	public HashMap<String, Integer> getF(){
		return f;
	}
}
