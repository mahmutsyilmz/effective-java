# Detaylı Kod Anlatımı

Bu kod dosyasında, **Java 8** ile gelen **Lambda** ve **Stream** API kullanımlarının örneklerini görüyoruz. Kodlar, geleneksel yaklaşımla (anonymous class) yazılmış halleriyle karşılaştırılarak inceleniyor. Bu sayede, Lambda ve yeni API'lerin getirdiği kolaylıklar ve kısayollar netleşiyor.

---

## 1. Comparator Örneği (Lambda)

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparatorExampleLambda {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("Steve");
        names.add("Tim");
        names.add("Lucy");
        names.add("Pat");

        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));
        System.out.print(names);

        Collections.sort(names, (o1, o2) -> o2.compareTo(o1));
        System.out.print(names);

        Collections.sort(names, (o1, o2) -> o1.length() - o2.length());
        System.out.print(names);
    }
}
```

### Kodun Açıklaması

- `List<String> names`: Başlangıçta ArrayList oluşturuyoruz ve içine birkaç isim ekliyoruz.
- `Collections.sort(...)`: Java'da bir listeyi sıralamak için Collections sınıfının sort metodunu kullanırız. İkinci parametre olarak, bir Comparator veriyoruz.
- Lambda ifadesi `(o1, o2) -> ...`: Geleneksel comparator yazmak yerine `(o1, o2)` şeklinde parametre belirleyip, `->` ile dönüş değerini veriyoruz.
- `o1.compareTo(o2)`: A'dan Z'ye sıralar.
- `o2.compareTo(o1)`: Z'den A'ya sıralar.
- `o1.length() - o2.length()`: Kelime uzunluğuna göre küçükten büyüğe sıralar.
- Her `sort` işleminden sonra `names` liste içeriği değişir ve `System.out.print()` ile sonuç ekrana basılır.

---

## 2. Comparator Örneği (Geleneksel)

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorExampleTraditional {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Steve");
        names.add("Tim");
        names.add("Lucy");
        names.add("Pat");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
```

### Kodun Açıklaması

- Burada yine bir `List<String>` oluşturuluyor.
- `Collections.sort(names, new Comparator<String>() { ... })`: Comparator, anonim bir sınıf olarak tanımlanıyor.
- `compare` metodunda `o1.compareTo(o2)` ile A'dan Z'ye sıralama yapılıyor.
- Bu yaklaşım Java 8 öncesi kullanılan yöntemdir. Daha uzun bir yazım şekli vardır ama işlevi aynıdır.
- Lambda bu anonim sınıfın yerine daha kısa ve temiz bir kod sunar.

---

## 3. Runnable Örneği (Lambda)

```java
public class RunnableExampleLambda {

    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Lambda Runnable");
        new Thread(r).start();

        // Daha da kısaltılmış hali
        new Thread(() -> System.out.println("Lambda Runnable")).start();
    }
}
```

### Kodun Açıklaması

- `Runnable` arayüzünde tek bir metot (`run()`) olduğu için **functional interface** olarak da adlandırabiliriz. Bu nedenle Lambda ifadesiyle kullanılabilir.
- `Runnable r = () -> System.out.println("Lambda Runnable");`
- `run()` metodu gövdesi, `() -> ...` şeklinde lambda ifadesine dönüşür.
- `new Thread(r).start();` veya direkt olarak `new Thread(() -> ...)` şeklinde yazılabilir.

---

## 4. Runnable Örneği (Geleneksel)

```java
public class RunnableExampleTraditional {
    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional Runnable");
            }
        };
        new Thread(r).start();
    }
}
```

### Kodun Açıklaması

- Java 8 öncesi, `Runnable` kullanmak istediğimizde anonim bir sınıf oluşturup `run()` metodunu override ediyorduk.
- Lambda ile kıyaslandığında daha uzun ve karmaşık görünüyor.

---

## 5. Stream API Örneği (Lambda)

```java
import java.util.List;
import java.util.stream.Collectors;

public class StreamLambdaExample {
    public static void main(String[] args) {
        List<String> fruits = List.of("apple", "banana", "grape", "orange", "watermelon");

        List<String> longFruitsUpperCase = fruits.stream()
                .filter(fruit -> fruit.length() > 5)
                .map(fruit -> fruit.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(longFruitsUpperCase);
    }
}
```

### Kodun Açıklaması

- `List.of(...)`: Java 9 ile gelen bir metottur. Sabit bir liste oluşturur.
- `fruits.stream()` ile liste üzerinde bir Stream oluşturuyoruz.
- `filter(fruit -> fruit.length() > 5)`: Uzunluğu 5'ten büyük olan meyveleri filtreler. (örnek: `"banana"`, `"orange"`, `"watermelon"`)
- `map(fruit -> fruit.toUpperCase())`: Meyve isimlerini büyük harf yapar. (`"BANANA"`, `"ORANGE"`, `"WATERMELON"`)
- `collect(Collectors.toList())`: Sonuçları bir listeye dönüştürür ve `longFruitsUpperCase` değişkenine atar.
- `System.out.println(longFruitsUpperCase)`: Son listeyi ekrana basar.

---

## Genel Değerlendirme

- Lambda ifadeleri, **fonksiyonel arayüzleri** (tek metotlu arayüz) kısaca yazmamızı sağlar.
- `Comparator`, `Runnable` gibi arayüzlerde kullanmak çok yaygındır.
- **Stream API**, koleksiyonlar üzerinde fonksiyonel programlama tarzında işlem yapmayı sağlar.
- `filter`, `map`, `collect` gibi metotlar sayesinde liste işleme çok sade hale gelir.
- Geleneksel yaklaşımlarda anonim sınıflar daha uzun kod blokları oluşturur. Lambda ile bu kodlar önemli ölçüde kısalır.
- Bu örnekler, **Java 8 ve üstü** sürümlerde hem kodun temizliğini hem de geliştiricinin verimliliğini artıran modern özelliklerin nasıl kullanılacağını gösterir.
