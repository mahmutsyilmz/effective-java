# Mutable vs. Immutable Objects in Java

Bu README, Java'da mutable (değiştirilebilir) ve immutable (değiştirilemez) nesnelerin ne olduğunu, nasıl çalıştığını ve bu nesnelerin tasarımındaki önemli noktaları örnek kodlar üzerinden açıklamaktadır.

---

## İçerik

- Mutable ve Immutable Kavramları
- Immutable Sınıf Tasarımı
- Mutable Sınıf Tasarımı
- Standart Java Sınıflarında Mutable ve Immutable Davranışlar
- Static Factory Tasarımı ve Immutable Nesneler
- Özet

---

## Mutable ve Immutable Kavramları

### Mutable Nesneler

Bir nesnenin durumu oluşturulduktan sonra değiştirilebiliyorsa, o nesne mutable'dır.

Örnekler: `Date`, `BitSet`, `StringBuilder`

### Immutable Nesneler

Bir nesnenin oluşturulduktan sonra durumu değiştirilemiyorsa, o nesne immutable'dır.

Örnekler: `String`, `LocalDate`, `BigInteger`

Immutable nesneler, thread-safe olmaları ve veri bütünlüğünü korumaları açısından avantaj sağlarken, mutable nesneler ise esneklik sunar fakat çoklu iş parçacığı ortamında dikkatle kullanılmalıdır.

---

## Immutable Sınıf Tasarımı

Immutable bir sınıf oluşturmak için:

- Sınıf `final` olmalıdır (extend edilemesin).
- Tüm alanlar `private` ve `final` tanımlanmalıdır.
- Alanlar sadece constructor’da atanmalı, setter metotları bulunmamalıdır.

```java
public final class Immutable {
    private final String name;

    public Immutable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

Bu sınıf oluşturulduktan sonra `name` alanı değiştirilemez; böylece veri bütünlüğü korunur.

---

## Mutable Sınıf Tasarımı

Mutable sınıflarda ise alanlar, oluşturulduktan sonra değiştirilebilir. Setter metotları kullanılarak nesnenin durumu güncellenebilir.

```java
public class Mutable {
    private String name;

    public Mutable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

Bu sınıfta `name` alanı `setName` metodu aracılığıyla değiştirilebilir.

---

## Standart Java Sınıflarında Mutable ve Immutable Davranışlar

Aşağıdaki örnek kod, çeşitli Java sınıflarının mutable veya immutable davranışlarını göstermektedir.

```java
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.BitSet;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // String immutable olduğu için değişiklik yapılmıyor:
        String name = new String("John");
        name.toLowerCase();
        System.out.println(name.toLowerCase()); // Output: john
        System.out.println(name);               // Output: John

        // Date mutable olduğu için nesne değiştirilebiliyor:
        Date date = new Date();
        System.out.println(date); // Örnek: Wed Mar 31 14:47:00 EEST 2021
        date.setDate(7);
        System.out.println(date); // Örnek: Wed Apr 07 14:47:00 EEST 2021

        // LocalDate immutable olduğu için değişiklik için yeni nesne oluşturulmalı:
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate); // Örnek: 2025-03-30
        localDate.plusDays(2);
        System.out.println(localDate); // Hala: 2025-03-30
        localDate = localDate.plusDays(2);
        System.out.println(localDate); // Output: 2025-04-01

        // BigInteger immutable'dir:
        BigInteger bigInteger = new BigInteger("1234567890");
        System.out.println(bigInteger); // Output: 1234567890
        bigInteger.add(BigInteger.ONE);
        System.out.println(bigInteger); // Yine: 1234567890

        // BitSet mutable olduğu için değişiklik yapılabiliyor:
        BitSet bitSet = new BitSet();
        System.out.println(bitSet); // Output: {}
        bitSet.set(0);
        System.out.println(bitSet); // Output: {0}

        // StringBuilder mutable olduğu için, içerik değiştirilebilir:
        StringBuilder stringBuilder = new StringBuilder("John");
        System.out.println(stringBuilder); // Output: John
        stringBuilder.append(" Doe");
        System.out.println(stringBuilder); // Output: John Doe
    }
}
```

### Detaylı Açıklamalar

- **String**: Immutable olduğu için `toLowerCase()` metodu, orijinal nesneyi değiştirmek yerine yeni bir String döndürür.
- **Date**: Mutable olduğundan `setDate()` metodu, nesnenin değerini doğrudan değiştirir.
- **LocalDate**: Immutable olduğundan, `plusDays()` metodu mevcut nesneyi değiştirmez; bunun yerine yeni bir nesne döndürür.
- **BigInteger**: Immutable bir sınıftır; herhangi bir matematiksel işlem orijinal nesneyi değiştirmez.
- **BitSet ve StringBuilder**: Mutable olduklarından, içerdikleri değerler üzerinde yapılan değişiklikler nesne üzerinde kalıcıdır.

---

## Static Factory Tasarımı ve Immutable Nesneler

Immutable nesneler için static factory metodları kullanmak, nesne oluşturma maliyetini azaltmak ve önceden oluşturulmuş nesnelerin (cache) yeniden kullanılmasını sağlamak açısından faydalıdır.

Örneğin, Java'nın Boolean sınıfı buna güzel bir örnektir:

```java
public final class MyBoolean {
    private final boolean value;

    private static final MyBoolean TRUE = new MyBoolean(true);
    private static final MyBoolean FALSE = new MyBoolean(false);

    private MyBoolean(boolean value) {
        this.value = value;
    }

    public static MyBoolean valueOf(boolean value) {
        return value ? TRUE : FALSE;
    }

    public boolean booleanValue() {
        return value;
    }
}
```

Bu örnekte:

- `MyBoolean` immutable olarak tasarlanmıştır.
- `valueOf()` metodu, yeni nesne oluşturmak yerine önceden oluşturulmuş `TRUE` veya `FALSE` nesnelerini döndürerek performans artışı sağlar.

---

## Özet

### Mutable Nesneler

- Değiştirilebilir.
- Çoklu iş parçacığı ortamında dikkat edilmezse veri bütünlüğü sorunlarına yol açabilir.

### Immutable Nesneler

- Değiştirilemez.
- Thread-safe ve veri bütünlüğünü korur.
- Her küçük değişiklik yeni nesne oluşturma maliyeti getirir.

### Tasarım İpuçları

- Immutable sınıflar için:
    - `final` sınıf
    - `private final` alanlar
    - Setter metodlarının olmaması önemlidir.

- Mutable nesnelerde:
    - Kapsülleme
    - Gerektiğinde senkronizasyon önlemleri alınmalıdır.

- Static factory metodları:
    - Immutable nesnelerin cache edilmesi ve yeniden kullanılması açısından önemli avantaj sağlar.

### Alternatifler

- Mutable `String` işlemleri için `StringBuilder` kullanmak, performans açısından faydalıdır.
