# Java Access Modifiers ve Sabitlerin Tanımlanması

Bu repository, Java'da access modifiers (erişim belirleyicileri) ve immutable (değiştirilemez) sabitlerin nasıl tanımlanması gerektiği konularını örnek kodlarla açıklamaktadır.

---

## İçerik

- Access Modifiers
    - Private
    - Protected
    - Public
- Sabitlerin Tanımlanması: Array vs. List
    - NonSafeCode: Public Static Final Array
    - SafeCode: Immutable Yapılar
- Örnek Kodlar ve Açıklamalar
- Sonuç

---

## Access Modifiers

Java'da erişim belirleyiciler, sınıf üyelerinin (değişken, metod, constructor vb.) nereden erişilebileceğini kontrol eder.

### Private

**Tanım:** Sadece tanımlandığı sınıf içinde erişilebilir.

**Örnek:**

```java
private String name = "Mahmut";
```

**Avantaj:** Sınıf içi kapsülleme sağlar; dış erişime kapalı olduğundan, veri bütünlüğünü korur.

### Protected

**Tanım:** Aynı paket içinden veya alt sınıflar (subclass) tarafından erişilebilir.

**Örnek:**

```java
protected String surname = "Sami";
```

**Kullanım Senaryosu:** Sınıfın bazı üyelerine alt sınıflardan erişim izni vermek istiyorsak kullanılır.

### Public

**Tanım:** Her yerden erişilebilir.

**Örnek:**

```java
public String getName() { ... }
```

**Kullanım Senaryosu:** Dış dünya ile iletişim kurması gereken metotlar ve sabitler için tercih edilir.

---

## Sabitlerin Tanımlanması: Array vs. List

Java'da sabit olarak tanımlanan nesneler için genellikle `public static final` yapısı kullanılır. Ancak burada dikkat edilmesi gereken nokta, nesnenin referansı sabittir, fakat nesnenin kendisi değiştirilebilir olabilir.

---

## NonSafeCode: Public Static Final Array

```java
public class NonSafeCode {
    public static final String[] DEALED_BRANDS = new String[]{"Apple", "Samsung", "Huawei", "Xiaomi"};

    public static void getBrands() {
        for (String brand : DEALED_BRANDS) {
            System.out.print(brand + " ");
        }
    }
}
```

**Açıklama:**

- `DEALED_BRANDS` dizisi `public static final` olarak tanımlanmıştır.
- Önemli Nokta: `final` anahtar kelimesi dizinin referansını sabitler, ancak dizinin içerisindeki elemanlar değiştirilebilir olduğu için dışarıdan erişimle elemanlar değiştirilebilir.
- **Örnek:** `Virus` sınıfında dizinin ilk elemanı `"Virus"` olarak değiştirilebilmektedir.

---

## SafeCode: Immutable Yapılar

```java
import java.util.Collections;
import java.util.List;

public class SafeCode {
    // Private array: Dışarıdan erişilemez.
    private static final String[] DEALED_BRANDS = new String[]{"Apple", "Samsung", "Huawei", "Xiaomi"};
    
    // Public immutable list: Değiştirilemez.
    public static final List<String> DEALED_BRANDS_LIST = Collections.unmodifiableList(
        List.of("Apple", "Samsung", "Huawei", "Xiaomi")
    );

    public static void getBrands() {
        for (String brand : DEALED_BRANDS) {
            System.out.print(brand + " ");
        }
    }
}
```

**Açıklama:**

- `DEALED_BRANDS` dizisi `private` olduğu için dışarıdan doğrudan erişilemez ve modifiye edilemez.
- `DEALED_BRANDS_LIST` ise `public static final` olarak tanımlanmış, fakat `Collections.unmodifiableList(...)` kullanılarak değiştirilemez hale getirilmiştir.
- **Önemli Nokta:** Bu sayede dış sınıflardan sabit veriye erişim sağlansa bile, verinin modifiye edilmesi engellenir.

---

## Örnek Kodlar ve Açıklamalar

### Class1

```java
public class Class1 {
    private String name = "Mahmut";       // Sadece Class1 içinde erişilebilir.
    protected String surname = "Sami";     // Aynı paket veya alt sınıflar tarafından erişilebilir.

    public String getName() {
        message();  // Private metodun Class1 içinden çağrılması.
        return name;
    }

    private void message() {
        System.out.println("Hello World");
    }
}
```

**Detay:**

- `name` ve `message()` metodu dışarıdan erişime kapalıdır.
- `surname` ise `protected` olduğu için sınıfı extend edenler tarafından erişilebilir.

---

### NonSafeCode ve Virus

```java
public class NonSafeCode {
    public static final String[] DEALED_BRANDS = new String[]{"Apple", "Samsung", "Huawei", "Xiaomi"};

    public static void getBrands() {
        for (String brand : DEALED_BRANDS) {
            System.out.print(brand + " ");
        }
    }
}
```

```java
public class Virus {
    public String virusOnNonSafeCode() {
        // Public static final dizinin elemanı değiştirilebiliyor!
        NonSafeCode.DEALED_BRANDS[0] = "Virus";
        return NonSafeCode.DEALED_BRANDS[0];
    }
}
```

**Detay:**

- `DEALED_BRANDS` dizisi `public` olduğundan, diğer sınıflardan doğrudan erişilip elemanları değiştirilebilmektedir.
- Bu durum güvenlik ve bütünlük açısından risklidir.

---

### SafeCode ve Virus (Immutable List Örneği)

```java
public class SafeCode {
    private static final String[] DEALED_BRANDS = new String[]{"Apple", "Samsung", "Huawei", "Xiaomi"};
    
    public static final List<String> DEALED_BRANDS_LIST = Collections.unmodifiableList(
        List.of("Apple", "Samsung", "Huawei", "Xiaomi")
    );

    public static void getBrands() {
        for (String brand : DEALED_BRANDS) {
            System.out.print(brand + " ");
        }
    }
}
```

```java
public class Virus {
    public String virusOnSafeCodeAsList() {
        // Bu satır, unmodifiable list nedeniyle çalışma zamanında exception fırlatır.
        SafeCode.DEALED_BRANDS_LIST.set(0, "Virus");
        return SafeCode.DEALED_BRANDS_LIST.get(0);
    }
}
```

**Detay:**

- `DEALED_BRANDS_LIST` immutable olarak tanımlandığı için, dışarıdan gelen herhangi bir modifikasyon girişimi çalışma zamanında hata (exception) oluşturur.
- Bu, verinin korunması açısından daha güvenlidir.

---

## Sonuç

**Access Modifiers:**

- `private`: Sadece sınıf içi erişim, kapsülleme sağlar.
- `protected`: Aynı paket veya alt sınıflar tarafından erişim.
- `public`: Her yerden erişilebilir, dikkatli kullanılmalıdır.

**Sabit Tanımlama (Constants):**

- `public static final` diziler, referansları sabitler; ancak dizinin elemanları değiştirilebilir. Bu durum güvenlik açığı oluşturabilir.
- **Immutable yapılar** (örneğin, `Collections.unmodifiableList(...)`) kullanılarak sabitlerin dış müdahalelere kapalı hale getirilmesi, daha güvenli bir yapı sunar.
