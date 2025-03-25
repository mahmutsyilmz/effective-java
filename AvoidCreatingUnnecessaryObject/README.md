# Detaylı Kod Anlatımı

Bu dosyada iki farklı sınıf bulunuyor: **Match** ve **ObjectCreation**. Kod örnekleri, Java'da **Regex (Regular Expressions)** kullanımı ve **nesne oluşturma** ile ilgili bazı performans detaylarını gösteriyor.

---

## 1. Match Sınıfı

```java
public class Match {

    private static final Pattern REGEX = Pattern.compile("your-regex-pattern");

    public static boolean isMatching(String s) {
        return s.matches("your-regex-pattern");
    }

    static boolean isNonMatching(String s) {
        return !REGEX.matcher(s).matches();
    }
}
```

### Kod İncelemesi

- `Pattern.compile("your-regex-pattern")`: Java'da düzenli ifadeler (regex) ile çalışırken, tekrar tekrar derleme maliyetini düşürmek için kullanılır.
- `REGEX`: Sabit bir `Pattern` nesnesi.
- `isMatching(String s)`: `s.matches("...")` çağrısı her seferinde regex derler.
- `isNonMatching(String s)`: Önceden derlenmiş `Pattern` ile eşleşme yapılır.

### Önemli Noktalar

- `Pattern.compile(...)` ile tekrar regex derlemesi önlenir.
- `s.matches(...)` her çağrıda yeni `Pattern` üretir.
- Statik metodlarla basit regex kontrolleri yapan yardımcı (utility) sınıf olarak kullanılabilir.

---

## 2. ObjectCreation Sınıfı

```java
import java.util.ArrayList;
import java.util.List;

public class ObjectCreation {

    private static long sumPrimitive (){
        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        return sum;
    }

    private static Long sumWrapper (){
        Long sum = 0L;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {

        String word = new String("Hello");

        // This is the same as the above line
        String word2 = "Hello";
        String word3 = "Hello";

        System.out.println(word == word2); // false
        System.out.println(word2 == word3); // true

        long start = System.currentTimeMillis();
        sumPrimitive();
        System.out.println("Time taken for primitive: " + (System.currentTimeMillis() - start) + "ms");

        sumWrapper();
        System.out.println("Time taken for wrapper: " + (System.currentTimeMillis() - start) + "ms");

        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            integers.add(i); // i -> Integer.valueOf(i);
        }

        List<Integer> integers2 = new ArrayList<>();
        int sum2 = 0;
        for (Integer i = 0; i < 1000000; i++) {
            if (i % 2 == 0) {
                sum2 += i; // i.intValue()
            }
        }
    }
}
```

---

### 2.1. String Nesneleri

```java
String word = new String("Hello");
String word2 = "Hello";
String word3 = "Hello";
```

- `new String("Hello")`: Yeni bir `String` nesnesi oluşturur.
- `"Hello"`: String pool’da saklanır.
- `word == word2` → `false`
- `word2 == word3` → `true`

---

### 2.2. Primitive vs Wrapper Tipleri

```java
private static long sumPrimitive () {
    long sum = 0;
    for (int i = 0; i < 1000000; i++) {
        sum += i;
    }
    return sum;
}

private static Long sumWrapper () {
    Long sum = 0L;
    for (int i = 0; i < 1000000; i++) {
        sum += i;
    }
    return sum;
}
```

- `sumPrimitive()`: Primitive `long` kullanır. Daha hızlı, daha az bellek kullanır.
- `sumWrapper()`: `Long` (wrapper) kullanır. Otomatik kutulama (autoboxing) nedeniyle yavaştır.

```java
long start = System.currentTimeMillis();
sumPrimitive();
System.out.println("Time taken for primitive: " + (System.currentTimeMillis() - start) + "ms");

sumWrapper();
System.out.println("Time taken for wrapper: " + (System.currentTimeMillis() - start) + "ms");
```

- `primitive`: ~2ms
- `wrapper`: ~16ms

---

### 2.3. Autoboxing ve Performans

```java
ArrayList<Integer> integers = new ArrayList<>();
for (int i = 0; i < 1000000; i++) {
    integers.add(i); // i -> Integer.valueOf(i)
}
```

- `int → Integer`: Autoboxing yapılır.
- 1 milyon kez çağrıldığında yüksek maliyet yaratır.

```java
List<Integer> integers2 = new ArrayList<>();
int sum2 = 0;
for (Integer i = 0; i < 1000000; i++) {
    if (i % 2 == 0) {
        sum2 += i; // i.intValue()
    }
}
```

- `Integer` üzerinden `int`'e dönüştürme: Unboxing.

---

## Genel Yorum

- **Primitive** tipler (int, long): Daha hızlı ve az kaynak tüketir.
- **Wrapper** tipler (Integer, Long): Otomatik kutulama ve nesne yönetimi nedeniyle yavaştır.
- **String Pool**: Bellek optimizasyonu sağlar.
- `new String(...)` → her zaman yeni nesne oluşturur, `==` ile karşılaştırıldığında fark yaratır.

---

## Sonuç

### Match Sınıfı

- Regex kullanımı iki yöntemle gösterildi.
- Performans açısından `Pattern.compile(...)` ile önceden derlenmiş yapı daha verimlidir.

### ObjectCreation Sınıfı

- `String` oluşturma ve String pool kavramı açıklandı.
- Primitive ve wrapper tipler arası performans farkı örneklendi.
- Autoboxing/unboxing işlemlerinin büyük döngülerde nasıl maliyet yarattığı gösterildi.

---

Bu örnekler, Java'da hem **performans odaklı düşünmeyi** hem de **nesne oluşturma ile ilgili temel kavramları** somut şekilde açıklamaktadır.
