package pushdown.structs;

/**
 * @author jose
 *
 *  Represents an automaton symbol.
 */
public class Symbol {
	String name;

	public Symbol(String name){
		this.name = name;
	}
	
	public String toString(){
        return name;
    }
	
	/*
	 * Equals and hashCode.
	 */
	
	public boolean equals(Object ob){
		if (ob == null) return false;
		if (ob.getClass() != getClass()) return false;
		Symbol other = (Symbol)ob;
		if (!name.equals(other.name)) return false;
		return true;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	/*
	 * Getters and setters.
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}