package mvc;

import places.*;
import java.util.*;
import java.util.function.*;

public class MultiMap<K, V> {

	HashMap<K, ArrayList<V>> map;

	public MultiMap() {

		map = new HashMap<K, ArrayList<V>>();

	}

	public void put(K key, V value) {

		ArrayList<V> values = map.get(key);

		if(values == null) {

			values = new ArrayList<V>();
			map.put(key, values);

		}
		values.add(value);

	}

	public ArrayList<V> get(K key) {

		ArrayList<V> values = map.get(key);

		return values;

	}

	/*public void remove(K key, V value) {

		ArrayList<V> values = map.get(key);

		values.remove(value);

	}*/

	public void removeIf(Predicate predicate) {

		Iterator<K> mapIterator = map.keySet().iterator();

		while(mapIterator.hasNext()) {

			ArrayList<V> values = map.get(mapIterator.next());
			
			Iterator<V> listIterator = values.iterator();

			while(listIterator.hasNext()) {

				V nextItem = listIterator.next();

				/*if(remover.remove(nextItem)) {

					listIterator.remove();

				}*/
				if(predicate.test(nextItem)) {

					listIterator.remove();

				}

			}
		}

	}

	public boolean isEmpty() {

		return map.isEmpty();

	}

	@FunctionalInterface
	interface Remover<K> {

		public boolean remove(K key);

	}

}