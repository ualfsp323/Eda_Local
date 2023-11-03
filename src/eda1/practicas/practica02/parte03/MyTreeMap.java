package eda1.practicas.practica02.parte03;

import eda1.practicas.auxiliar.BSTree;
import eda1.practicas.auxiliar.Par;

import java.util.ArrayList;
import java.util.Iterator;

public class MyTreeMap<K extends Comparable<K>,V> {
	
	private final BSTree<Par<K,V>> treePair;

	public MyTreeMap(){
		this.treePair = new BSTree<>();
	}

	public V put(K key, V value) {

		Par<K, V> parCurr = this.treePair.find(new Par<>(key, null));
		V vOld = null;
		if (parCurr == null) {
			this.treePair.add( new Par<>(key, value));

		} else {
			vOld = get(key);
			parCurr.setValue(value);
			this.treePair.add(parCurr);
		}
		return vOld;
	}

	public V get(K key) {
		Par<K, V> parCurr = this.treePair.find(new Par<>(key, null));
		return (parCurr == null) ? null : parCurr.getValue();
	}

	public boolean containsKey(K key) {
		return this.get(key) != null;
	}

	public void clear() {
		this.treePair.clear();
	}

	public boolean isEmpty() {
		return this.treePair.isEmpty();
	}

	public int size() {
		return this.treePair.size();
	}

	public ArrayList<K> keySet() {
		ArrayList<K> resultado = new ArrayList<>();

		for (Par<K, V> parCurr : treePair) {
			resultado.add(parCurr.getKey());
		}
		return resultado;
	}

	public ArrayList<V> valueSet() {
		ArrayList<V> resultado = new ArrayList<>();
		for (Par<K, V> parCurr : treePair) {
			resultado.add(parCurr.getValue());
		}
		return resultado;
	}

	public ArrayList<Par<K, V>> pairSet() {
		ArrayList<Par<K, V>> resultado = new ArrayList<>();
		for (Par<K, V> parCurr : treePair) {
			resultado.add(parCurr);
		}
		return resultado;
	}

	@Override
	public String toString() {
		return this.pairSet().toString();
	}
}