package eda1.practicas.practica03.parte03;

import java.util.*;

import java.util.Map.Entry;

public class NodeCollection<T extends Comparable<T>> {
	private final HashMap<Node<T>, HashSet<Node<T>>> data;

	public NodeCollection(){
		this.data = new HashMap<>();
	}

	public void add(Node<T> nodeO, Node<T> nodeD) {
		HashSet<Node<T>> setValues =  data.getOrDefault(nodeO, new HashSet<>());
		setValues.add(nodeD);
		data.putIfAbsent(nodeO, setValues);
	}

	public int size() {
		return this.data.size();
	}

	public void clear() {
		for (Entry<Node<T>, HashSet<Node<T>>> dataEntry : data.entrySet()) {
			HashSet<Node<T>> setValues = dataEntry.getValue();
			setValues.clear();
		}
		data.clear();
	}

	public HashSet<Node<T>> getNeighbours(Node<T> node) { 
		return this.data.get(node);
	}
	
	@Override
	public String toString() {
		return this.data.toString();
	}


	public String toStringOrdered(Comparator<Node<T>> comp) {
		TreeMap<Node<T>, TreeSet<Node<T>>> result = new TreeMap<>(comp);
		for (Entry<Node<T>, HashSet<Node<T>>> dataEntry : data.entrySet()) {
	        TreeSet<Node<T>> sortedSet = new TreeSet<>(comp);
	        sortedSet.addAll( dataEntry.getValue());
	        result.put(dataEntry.getKey(), sortedSet);
	    }

		return result.toString();
	}
}