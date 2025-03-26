# 1. Nesnelerin Karşılaştırılması: `==` Operatörü vs. `equals()` Metodu

## a. Primitif Tip Karşılaştırmaları

**Integer, Char, Double, Boolean:**

- `10 == 20`, `'A' == 'B'` gibi karşılaştırmalar doğrudan **değerleri** kıyaslar.
- Örnek:

  `'A' == 65.0`  
  `char` tipindeki `'A'` (ASCII değeri 65), `double` olan `65.0` ile karşılaştırıldığında **`true`** döner.

---

## b. String Karşılaştırmaları

- `"Hello" == "Hello"`  
  JVM’in **String pool** mekanizması sayesinde her iki literal aynı referansı işaret eder → **`true`**

- `"Hello" == new String("Hello")`  
  `new` ile oluşturulan nesne farklı bir bellek adresindedir → **`false`**

---

## c. Nesne Karşılaştırmaları (Referans ve Mantıksal Eşitlik)

### Referans Karşılaştırması (`==`):

```java
Class class1 = new Class(1,2);
Class class2 = new Class(1,2);
```

- `class1 == class2` → `false` çünkü farklı referanslar.

### `equals()` Metodu:

- `Class` sınıfında `equals()` override edilmemişse, **Object** sınıfındaki referans karşılaştırması yapılır.
- `ClassWithOverride` sınıfında `equals()` metodu, `a` ve `b` alanlarının eşitliğine göre yeniden tanımlanmıştır:
    - İçerikler eşitse → `true`

---

# 2. `equals()` Metodunun Override Edilmesi ve Sözleşmesi (Contract)

Java’da `equals()` override edilirken şu sözleşmelere uyulmalıdır:

### 🔁 Refleksif (Reflexive)
```java
x.equals(x) == true
```

### 🔄 Simetrik (Symmetric)
```java
x.equals(y) == true → y.equals(x) == true
```

### 🔗 Transitif (Transitive)
```java
x.equals(y) && y.equals(z) → x.equals(z)
```

### 🔁 Tutarlı (Consistent)
```java
x.equals(y) → aynı nesne durumu için hep aynı sonucu verir
```

### 🚫 Null Güvenliği
```java
x.equals(null) == false
```

### Uygulama Detayı:

```java
@Override
public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof ClassWithOverride)) return false;

    ClassWithOverride other = (ClassWithOverride) obj;
    return this.a == other.a && this.b == other.b;
}
```

---

# 3. `hashCode()` Metodunun Override Edilmesi ve Önemi

## a. Neden `hashCode()` Override Edilmeli?

- `HashMap`, `HashSet` gibi yapılar **hashCode()** kullanır.
- `equals()` true olsa bile `hashCode()` farklıysa eşit nesneler farklı **bucket**'lara düşer.
- Java sözleşmesine göre:

```java
x.equals(y) == true → x.hashCode() == y.hashCode()
```

## b. Uygulama Detayları

```java
@Override
public int hashCode() {
    int result = Integer.hashCode(a);
    result = 31 * result + Integer.hashCode(b);
    return result;
}
```

- **`31`** ile çarpma: Hash'lerin daha iyi dağılmasını sağlar (asal sayı).
- `a` ve `b`: `Integer.hashCode()` kullanılarak hesaplanır.

---

## c. HashMap Üzerinde Uygulama Örneği

```java
Map<ClassWithOverride, String> map = new HashMap<>();
map.put(new ClassWithOverride(1, 2), "Hello");

String value = map.get(new ClassWithOverride(1, 2));
```

- `hashCode()` override edilmemişse → **`null`** döner.
- `hashCode()` doğru şekilde override edilmişse → **`"Hello"`** döner.

---

# 4. Özet ve Teknik Değerlendirme

### `==` vs `equals()`:

- `==`: Primitif tiplerde değer, nesnelerde referans karşılaştırması yapar.
- `equals()`: Override edilmezse referans kıyaslar, override edilirse içerik kıyaslanabilir.

---

### `equals()` Override Edilmesi:

- Nesnenin mantıksal eşitliği sağlanır (örneğin `a`, `b` alanları).
- Java `equals()` sözleşmesi kurallarına uyulmalıdır.

---

### `hashCode()` Override Edilmesi:

- `equals()` override edilmişse **mutlaka** `hashCode()` da override edilmelidir.
- Aksi takdirde hash tabanlı koleksiyonlarda hatalar oluşur.

---

## ✅ Pratik Sonuç

- `Class` sınıfında `equals()` ve `hashCode()` override edilmemişse → aynı değerlere sahip nesneler eşit sayılmaz.
- `ClassWithOverride` içinde `equals()` ve `hashCode()` doğru override edildiğinde:
    - Mantıksal eşitlik sağlanır
    - HashMap gibi koleksiyonlarda beklenen sonuçlar alınır.
