package enhanced.base.utilities;

import java.util.HashMap;

/***
 * A bidirectional map
 * @param <K>
 * @param <V>
 */
public class BidiMap<K, V> {
    HashMap<K, V> first = new HashMap<K, V>(128);
    HashMap<V, K> second = new HashMap<V, K>(128);
    
    public void add(K key, V val) {
        first.put(key, val);
        second.put(val, key);
    }
    
    public void remove(K key, V val) {
        first.remove(key);
        second.remove(val);
    }
    
    public void removeSecond(V val) {
        K key = second.get(val);
        first.remove(key);
        second.remove(val);
    }
    
    public V get(K key) {
        return first.get(key);
    }
    
    public K getSecond(V val) {
        return second.get(val);
    }

    public boolean contains(K s) {
        return first.containsKey(s);
    }
    
    public boolean containsSecond(V w) {
        return second.containsKey(w);
    }
    
    public HashMap<K, V> getMap() {
        return first;
    }
}
