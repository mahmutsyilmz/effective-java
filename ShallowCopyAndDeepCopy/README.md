# Shallow Copy ve Deep Copy Kavramları - Clone Metodu ile Örnekler

Bu README dosyasında, Java’da **shallow copy** (yüzeysel kopyalama) ve **deep copy** (derin kopyalama) kavramlarını, `clone` metodunun nasıl kullanıldığıyla ilgili örnekler üzerinden açıklayacağız.

---

## 1. Shallow Copy Nedir?

**Shallow copy**, bir nesnenin kopyasını oluştururken, orijinal nesnedeki alanların (özellikle referans tipindeki alanların) aynısını kullanır. Yani kopyalanan nesne ile orijinal nesne aynı alt nesnelere referans verir. Bu durumda, kopya üzerinde yapılan bir değişiklik, orijinal nesnenin ilgili alanını da etkiler.

**Örnek:**

```java
Obje obje = new Obje();
Obje obje1 = obje; // obje1, obje ile aynı referansı paylaşır.
```

- `obje1 == obje` → `true` döner.
- Bu yöntem, referansları paylaşması nedeniyle bazı senaryolarda istenmeyen yan etkilere neden olabilir.

---

## 2. Deep Copy Nedir?

**Deep copy**, bir nesnenin kopyasını oluştururken, nesnenin sahip olduğu tüm verilerin (özellikle referans tipindeki alanların) tamamen yeni nesneler olarak kopyalanmasıdır. Böylece orijinal nesne ve kopya birbirinden **bağımsız** hale gelir; kopya üzerinde yapılan değişiklik orijinal nesneyi etkilemez.

**Örnek:**

```java
Obje obje = new Obje();
obje.setName("obje");

Obje obje1 = new Obje();
obje1.setName(obje.getName());
```

- `obje1`, `obje` ile aynı değere sahip olsa da bağımsız bir nesnedir.
- Değişiklikler birbirini etkilemez.

---

## 3. Clone Metodu ve Kopyalama İşlemleri

Java’da bir nesnenin kopyasını oluşturmak için `clone()` metodu kullanılır. Ancak bu metodu kullanabilmek için:

- İlgili sınıfın **`Cloneable`** arayüzünü implement etmesi gerekir.
- Aksi takdirde `CloneNotSupportedException` hatası alınır.

### Örnek: `Stack` Sınıfı

```java
import java.util.Objects;

public class Stack implements Cloneable {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public String stackName;

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    public String getStackName() {
        return stackName;
    }

    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            Object[] oldElements = elements;
            elements = new Object[2 * size + 1];
            System.arraycopy(oldElements, 0, elements, 0, size);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    protected Object clone() {
        try {
            // Burada super.clone() shallow copy oluşturur.
            return (Stack) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        Stack stack1 = new Stack();
        String[] numbers = {"1", "2", "3", "4", "5"};

        for (String number : numbers) {
            stack1.push(number);
        }

        // clone() metodu sayesinde stack1'in kopyası oluşturuluyor.
        Stack copy = (Stack) stack1.clone();

        while (!stack1.isEmpty()) {
            System.out.println(stack1.pop());
        }
        System.out.println("Stack1 is empty: " + stack1.isEmpty());

        while (!copy.isEmpty()) {
            System.out.println(copy.pop());
        }
        System.out.println("Copy is empty: " + copy.isEmpty());
    }
}
```

- `clone()` metodu override edilmediğinde hata alınır.
- `Cloneable` arayüzü implement edilmiş ve `clone()` metodu düzenlenmiştir.
- `super.clone()` yalnızca **shallow copy** üretir.

---

## 4. Shallow Copy ve Deep Copy Örnekleri: `Test1` Sınıfı

```java
public class Test1 {
    public static void main(String[] args) {
        // Shallow Copy örneği:
        Stack stack1 = new Stack();
        Stack stack2 = stack1;  // Aynı nesne referansı
        System.out.println(stack1 == stack2); // true

        // Deep Copy örneği:
        Stack stc = new Stack();
        stc.setStackName("Stack1");
        System.out.println(stc.getStackName()); // "Stack1"

        Stack stc2 = new Stack();
        stc2.setStackName(stc.getStackName());  // Değer kopyalanır
        System.out.println(stc2.getStackName()); // "Stack1"
    }
}
```

### Açıklamalar:

- **Shallow Copy:**
  ```java
  Stack stack2 = stack1;
  ```
    - Her iki referans aynı nesneyi gösterir.
    - Biri üzerinde yapılan değişiklik diğerini de etkiler.

- **Deep Copy:**
    - `stc` ve `stc2` farklı nesnelerdir.
    - `stackName` değeri kopyalanır ama nesneler birbirinden bağımsızdır.

---

## 5. Sonuç

| Kopya Türü      | Açıklama                                                                 |
|------------------|--------------------------------------------------------------------------|
| **Shallow Copy** | Sadece referanslar kopyalanır, aynı bellek adresi paylaşılır.            |
| **Deep Copy**    | Tüm veriler yeni nesneler olarak kopyalanır, nesneler tamamen ayrıdır.   |

- `clone()` metodu, Java’da kopyalama işlemlerinde kullanılır.
- Doğru kullanım için:
    - `Cloneable` arayüzü implement edilmelidir.
    - Gerekirse `clone()` metodu override edilmelidir.
- `super.clone()` → varsayılan olarak **shallow copy** üretir.
- Derin kopya yapılacaksa, `clone()` içinde referans tipteki alanların da kopyalanması gerekir.

Bu örnekler ve açıklamalar, projelerinizde kopyalama işlemlerini doğru bir şekilde uygulamanız için temel kavramları sağlam bir şekilde anlamanıza yardımcı olacaktır.
