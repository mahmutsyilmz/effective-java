import java.util.HashMap;

public class EagerCache implements Cache {

    private static final EagerCache instance = new EagerCache();
    private HashMap<Object, Object> map;

    private EagerCache() {
        map = new HashMap<>();
    }

    @Override
    public void put(Object key, Object value) {
        map.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    public static EagerCache getInstance() {
        return instance;
    }
}
