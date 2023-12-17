package Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
	private static int numNodes = 0;
	private final int id;
	protected final ArrayList<T> components;

	public static void initializeNumNodes(){
		numNodes = 0;
	}

	//Por qué es necesaria la anotación @SafeVarargs. ¿Se puede eliminar? 0 warnings, recuerda...
	@SafeVarargs
	public Node(T...components) {
		this.id = ++numNodes;
		this.components = new ArrayList<>(List.of(components));
	}
	
	
	@Override 
	public int hashCode() {
		return Objects.hash(this.components);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Node)) return false;
		return this.hashCode()== ((Node<?>)o).hashCode();
	}
	@Override
	public String toString(){
		return this.components.toString();
	}

	public String toStringWithNodeId() {
		return "Node "+ "#"+this.id+" -> " + this.components.toString();

	}

	@Override
	public int compareTo(Node<T> o) {
		int minLengt=Math.min(this.components.size(), o.components.size());
		for(int i=0;i<minLengt;i++) {
			int cmp = this.components.get(i).compareTo(o.components.get(i));
			if (cmp!=0) return cmp;
		}
		
		return Integer.compare(this.components.size(), o.components.size());
	}
	
	
}