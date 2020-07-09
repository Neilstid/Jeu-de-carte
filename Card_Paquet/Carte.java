package Card_Paquet;

import java.util.Comparator;

public class Carte {
	private String family = new String();
	private String name = new String();
	private int value;
	
	public Carte(String _family, String _name, int _value) {
		this.family = _family;
		this.name = _name;
		this.value = _value;
	}
	
	public String getFamily() {
		return this.family;
	}

	public String getName() {
		return this.name;
	}

	public int getValue() {
		return this.value;
	}
	
	public String afficher() {
		return this.name + " de " + this.family + " : " + this.value;
	}
	
	public boolean is_equals(Carte c) {
		if (c.getValue() == this.getValue()){
			return true;
		}	
		return false;
	}
	
	public boolean is_greater(Carte c) {
		if (this.getValue() > c.getValue()) {
			return true;
		}
		return false;
	}
	
}

class SortByValue implements Comparator<Carte> 
{ 
    public int compare(Carte a, Carte b) 
    { 
        return Integer.compare(a.getValue(), b.getValue());
    } 
}
