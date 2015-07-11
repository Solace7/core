package enhanced.base.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BidiArrayMap<K, V> extends BidiMap<K, V> {
    HashMap<V, ArrayList<K>> second = new HashMap<V, ArrayList<K>>(128);
    
    @Override
    public void add(K key, V val) {
        first.put(key, val);
        
        ArrayList<K> list = getList(val);
        
        if (list != null)
            list.add(key);
        else {
            list = new ArrayList<K>();
            list.add(key);
        }
        
        second.put(val, list);
    }
    
    @Override
    public void remove(K key, V val) {
        first.remove(key);
        
        ArrayList<K> list = getList(val);
        
        if (list.size() == 1)
            second.remove(val);
        else {
            list.remove(key);
            second.put(val, list);
        }
    }
    
    public void remove(K key) {
        if (key == null) return;
        remove(key, get(key));
    }
    
    public ArrayList<K> removeAll(V val) {
        ArrayList<K> keys = new ArrayList<K>();
        Collections.copy(keys, getList(val));
        
        for (K k : keys)
            first.remove(k);
        
        second.remove(val);        
        return keys;
    }
    
    public ArrayList<K> getList(V val) {
        return second.get(val);
    }
    
    @Override
    @Deprecated
    public K getSecond(V val) {
        return null;
    }

    @Override
    public boolean containsSecond(V w) {
        return second.containsKey(w);
    }
}
