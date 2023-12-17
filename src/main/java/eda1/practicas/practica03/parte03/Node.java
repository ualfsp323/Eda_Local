package eda1.practicas.practica03.parte03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Node<T extends Comparable<T>> implements Comparable<Node<T>>  {
	private static int numNodes = 0;
	private final int id;
	protected final ArrayList<T> components;

	public static void initializeNumNodes(){
		numNodes = 0;
	}

	@SafeVarargs
	public Node(T...components) {
		this.id = ++numNodes;
		this.components = new ArrayList<>(List.of(components));
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Node))return false;
		return this.hashCode() == ((Node<?>)o).hashCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(components);
	}
	@Override
	public String toString() {
		return components.toString();
	}

	public Object toStringWithNodeId() {
	    return "Node #" + id + " -> " + components.toString();
	}


    @Override
    public int compareTo(Node<T> otherNode) {
        int minLength = Math.min( this.components.size(), otherNode.components.size());
        int cmp =0;
        for (int i = 0; i < minLength; i++) {
             cmp = this.components.get(i).compareTo(otherNode.components.get(i));   
             if (cmp != 0)   return cmp;
        }
        return Integer.compare(this.components.size(),  otherNode.components.size());
    }
        
}
    
