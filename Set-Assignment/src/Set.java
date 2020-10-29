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
	
}