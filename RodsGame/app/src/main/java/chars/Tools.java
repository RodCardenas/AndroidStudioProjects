package chars;

import java.util.ArrayList;
import java.util.List;

public class Tools {
	String name;
	String description;
	int cost;
	
	public Tools(String n, String d, int c){
		this.name = n;
		this.description = d;
		this.cost = c;
	}
	
	public static List <Tools> toolkit = new ArrayList<Tools>();
	
	public static void addTool (Tools t){
		toolkit.add(t);
	}
	
	public static String getToolName (Tools t){
		return t.name;
	}
}
