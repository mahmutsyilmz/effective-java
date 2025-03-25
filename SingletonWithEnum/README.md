# Code Analysis

This code shows three **Singleton** implementations in Java. A **singleton** class has only **one** instance in the program. We use a **Cache** interface to show how we can store and get data in these singleton classes.

---

## 1. Cache Interface

```java
public interface Cache<F, S> {
    public void put(F key, S value);
    public S get(F key);
}
```

- This interface has two methods: `put()` and `get()`.
- It uses generic types (`F` and `S`).
- These methods save and retrieve data.

---

## 2. Enum Singleton (CacheSingleton)

```java
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
```

- We use an enum called `CacheSingleton`.
- `INSTANCE` is the only object of this enum.
- The `map` is a `HashMap<Object, Object>`.
- `put()` and `get()` store and retrieve values from the map.
- Enums in Java are thread-safe and easy to use for singletons.

---

## 3. Eager Singleton (EagerCache)

```java
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
```

- `EagerCache` creates one instance at the start (`new EagerCache()`).
- `getInstance()` returns this single instance.
- This is thread-safe because the instance is created at class load time.
- The `map` is again a `HashMap<Object, Object>` for storing data.

---

## 4. Lazy Singleton (LazyCache)

```java
public class LazyCache implements Cache {
    private static LazyCache instance;
    private HashMap<Object, Object> map;

    private LazyCache() {
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

    public static LazyCache getInstance() {
        if (instance == null) {
            instance = new LazyCache();
        }
        return instance;
    }
}
```

- This class creates the instance only when `getInstance()` is called.
- This is lazy creation because the object is not created until it is needed.
- This simple version is not thread-safe. In multithreading, two threads can create two objects at the same time.

---

## 5. Main Class

```java
public class Main {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1 " + "Hashcode of Lazy: " + LazyCache.getInstance().hashCode());
                System.out.println("Thread 1 " + "Hashcode of Eager: " + EagerCache.getInstance().hashCode());
                System.out.println("Thread 1 " + "Hashcode of Enum: " + CacheSingleton.INSTANCE.hashCode());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2 " + "Hashcode of Lazy: " + LazyCache.getInstance().hashCode());
                System.out.println("Thread 2 " + "Hashcode of Eager: " + EagerCache.getInstance().hashCode());
                System.out.println("Thread 2 " + "Hashcode of Enum: " + CacheSingleton.INSTANCE.hashCode());
            }
        }).start();
    }
}
```

- We create two threads.
- Each thread prints the `hashCode()` of the Lazy, Eager, and Enum singletons.
- Eager and Enum singletons usually have the same hash code in both threads.
- Lazy can show different hash codes because it is not thread-safe.

---

## Conclusion

- `EnumSingleton` and `EagerCache` are thread-safe in this example.
- `LazyCache` is not thread-safe. In multiple threads, it can create two different instances.
- We see different hash codes for `LazyCache` but same hash codes for `EagerCache` and `CacheSingleton`.
- This code shows why we must be careful with lazy singletons in multithreading.
- For a safe lazy singleton, we must add synchronization or use a thread-safe approach like double-checked locking or an enum.
