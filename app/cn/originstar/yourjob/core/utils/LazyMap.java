package cn.originstar.yourjob.core.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Delegation of HashMap. A temporary solution to exception - Could not determine property type for auto-growing a
 * default value - of Spring DataBinder when trying to bind something like foo[3].id=3 to a Map<Integer, FooBean>, which
 * is bug introduced in Spring 3.0.5 and fixed in 3.1 M2.
 * 
 * <p>
 * Usage: use LazyMap instead of Map in beans. Must initialize the LazyMap before binding any data. See
 * {@link cn.originstar.yourjob.system.project.controllers.TemplatesController.TemplateForm} for details.
 * </p>
 * 
 * @author Yonggang Yuan
 * 
 * @see <a href="https://jira.springsource.org/browse/SPR-7839">https://jira.springsource.org/browse/SPR-7839</a>
 * @see <a
 *      href="http://stackoverflow.com/questions/10944867/could-not-determine-property-type-for-auto-growing-a-default-value">http://stackoverflow.com/questions/10944867/could-not-determine-property-type-for-auto-growing-a-default-value </a>
 */
public class LazyMap<K, V> implements Map<K, V> {

    private Map<K, V> nestedMap;
    private Class<V> valueType;

    public LazyMap(Class<V> valueType) {
        nestedMap = new HashMap<K, V>();
        this.valueType = valueType;
    }

    @Override
    public void clear() {
        nestedMap.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return nestedMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return nestedMap.containsValue(value);
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return nestedMap.entrySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(Object key) {
        if (!containsKey(key)) {
            try {
                put((K) key, valueType.newInstance());
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
        return nestedMap.get(key);
    }

    @Override
    public boolean isEmpty() {
        return nestedMap.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return nestedMap.keySet();
    }

    @Override
    public V put(K key, V value) {
        return nestedMap.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        nestedMap.putAll(m);
    }

    @Override
    public V remove(Object key) {
        return nestedMap.remove(key);
    }

    @Override
    public int size() {
        return nestedMap.size();
    }

    @Override
    public Collection<V> values() {
        return nestedMap.values();
    }

}
