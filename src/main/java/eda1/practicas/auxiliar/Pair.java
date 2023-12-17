package eda1.practicas.auxiliar;


public class Pair <K extends Comparable<K>,V> implements Comparable<Pair<K,V>>{
	
	private final K key;
	private V value;
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}
	
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}
	
	@Override
	public String toString() {
		return key + " <" + value + ">";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pair)) return false;
		return this.key.equals(((Pair<?,?>)o).key);
	}
	
	@Override
	public int compareTo(Pair<K,V> other) {
		return this.key.compareTo(other.key);
	}
}
