# Comparable vs. Comparator in Java

Bu proje, Java'da iki nesnenin nasıl karşılaştırılacağını gösteren **Comparable** ve **Comparator** arayüzlerini örnek kodlarla açıklamaktadır.

---

## 🧠 Konseptler

### ✅ Comparable

- `Comparable` arayüzü, bir sınıfın nesnelerinin **doğal sıralamasını** belirlemek için kullanılır.
- Sınıf, `Comparable<T>`'yi implement ederek `compareTo()` metodunu override eder.
- Bu sayede `Collections.sort(fruits)` gibi metotlarla nesneler sıralanabilir.
- Örneğimizde `Fruit` sınıfı, **isimlerine göre** sıralanmak üzere `Comparable<Fruit>` arayüzünü uygular.

### ✅ Comparator

- `Comparator` arayüzü, nesnelerin karşılaştırılmasında **farklı sıralama kriterlerine** ihtiyaç duyulduğunda kullanılır.
- İki nesneyi parametre olarak alır ve karşılaştırmayı gerçekleştiren `compare()` metodunu override eder.
- Aynı sınıf için farklı sıralama düzenleri tanımlamak mümkündür.
- Örneğimizde `FruitComparator` sınıfı, `Fruit` nesnelerini **renklerine göre** sıralamak için kullanılır.

---

## 💻 Kod Örnekleri

Aşağıdaki kodlar, `Fruit` sınıfının doğal sıralaması (**isimlerine göre**) ve `FruitComparator` kullanılarak **renklerine göre** sıralanmasını göstermektedir.

### 🔹 Test1.java

```java
import java.util.ArrayList;
import java.util.Collections;

public class Test1  {
    public static void main(String[] args) {
        ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit("Apple", "Red"));
        fruits.add(new Fruit("Banana", "Yellow"));
        fruits.add(new Fruit("Orange", "Orange"));
        fruits.add(new Fruit("Grape", "Green"));
        fruits.add(new Fruit("Plum", "Purple"));

        // Doğal sıralama: isimlerine göre sıralama (Comparable)
        Collections.sort(fruits);
        System.out.println("*İsimlerine göre sıralama*");
        for (Fruit fruit : fruits) {
            System.out.println(fruit.toString());
        }

        System.out.println("************");

        // Farklı sıralama: renklerine göre sıralama (Comparator)
        System.out.println("*Renklerine göre sıralama*");
        Collections.sort(fruits, new FruitComparator());
        for (Fruit fruit : fruits) {
            System.out.println(fruit.toString());
        }
    }
}
```

---

### 🔹 FruitComparator.java

```java
import java.util.Comparator;

public class FruitComparator implements Comparator<Fruit> {
    @Override
    public int compare(Fruit o1, Fruit o2) {
        return o1.getColor().compareTo(o2.getColor());
    }
}
```

---

### 🔹 Fruit.java

```java
public class Fruit implements Comparable<Fruit> {
    private String name;
    private String color;

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return name + " " + color;
    }

    // Doğal sıralama: isimlerine göre
    @Override
    public int compareTo(Fruit o) {
        return this.name.compareTo(o.name);
    }
}
```

---

## ⚙️ Nasıl Çalışır?

### 🔸 Doğal Sıralama (Comparable)

- `Fruit` sınıfı `Comparable` arayüzünü implemente ettiğinden:

  ```java
  Collections.sort(fruits);
  ```

  çağrısı, `compareTo()` metodunu kullanarak nesneleri **isimlerine göre** sıralar.

---

### 🔸 Özel Sıralama (Comparator)

- `FruitComparator` sınıfı `Comparator` arayüzünü implemente eder.
- `compare()` metodu override edilerek **renklere göre sıralama** yapılır.

  ```java
  Collections.sort(fruits, new FruitComparator());
  ```

---

## 📝 Özet

| Özellik     | Comparable                                | Comparator                              |
|-------------|--------------------------------------------|------------------------------------------|
| Tanım       | Doğal sıralamayı tanımlar                  | Alternatif sıralama tanımlar             |
| Metot       | `compareTo(T o)`                          | `compare(T o1, T o2)`                    |
| Kullanım    | Sınıf içinde implement edilir              | Harici sınıf ya da anonim olarak yazılır |
| Parametre   | 1 nesne                                    | 2 nesne                                  |
| Kullanım Yeri | `Collections.sort(list)`                | `Collections.sort(list, comparator)`     |

---

Bu örnekler, Java'da nesnelerin nasıl sıralanabileceğini ve hangi durumlarda **Comparable** ya da **Comparator** kullanmanın uygun olduğunu göstermektedir.
