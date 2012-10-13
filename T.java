package b;

import java.util.HashMap;

public class T {

	HashMap<String, Integer> t = new HashMap<String, Integer>();
	
	public T(){
		G g = new G();
		t.putAll(g.getT());
	}
	
	public void addNewT(String s){
		t.put(s, 0);
	}
	
	public void addTUse(String s, int u){
		t.put(s, t.get(s)+u);
	}
	
	public HashMap<String, Integer> getT(){
		return t;
	}
}