import java.util.*;
public class Set {
	public ArrayList<String> values;
	
	public Set(){
		values=new ArrayList<>();
	}
	
	public Set(int capacity) {
		values=new ArrayList<>(capacity);
	}
	
	public void add(String input ) {
		if(!values.contains(input))
			values.add(input);
	}
	
	
	public ArrayList<String> union(Set second){
		ArrayList<String> result=new ArrayList<>();
		for(String value:values)
			result.add(value);
		for(String secondValue:second.values) {
			if(!values.contains(secondValue))
				result.add(secondValue);
		}
		return result;
	}
	
	public ArrayList<String> intersection(Set second){
		ArrayList<String> result=new ArrayList<>();
		for(String firstValue:values) {
			for(String secondValue:second.values) {
				if(firstValue.equals(secondValue))
					result.add(firstValue);
			}
		}
		return result;
	}
	
	
	public ArrayList<String> complement(Set universe){
		ArrayList<String> result=new ArrayList<>();
		for(String universeValue:universe.values) {
			if(!values.contains(universeValue))
				result.add(universeValue);
		}
		return result;
	}
//	public static void main(String [] args) {
//		Set a=new Set();
//		String[] str= {"seif","gneedy","ans","seif","mohamed","gneedy",};
//		for(String s:str)
//			a.add(s);
//		Set b=new Set();
//		String[] str2= {"elnagar","tasaly","seif","phone","water","gneedy","ans","atatll"};
//		for(String s:str2)
//			b.add(s);
//		String[] str3= {"elnagar","tasaly","seif","phone","water","gneedy","ans","atatll","mohamed","sara","salma","noha","abeer"};
//		Set c=new Set(str3.length);
//		for(String s:str3)
//			c.add(s);
//		
//		System.out.println(a.complement(c));
//		
//	}
}