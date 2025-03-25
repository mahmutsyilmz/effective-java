import java.util.HashMap;

public enum CacheSingleton implements Cache {
    INSTANCE;

    private HashMap<Object, Object> map = new HashMap<>();

    @Override
    public void put(Object key, Object value) {
        map.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }
}
