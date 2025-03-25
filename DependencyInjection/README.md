# Detaylı Kod Anlatımı

Bu kod parçası, **Dictionary** (Sözlük) nesnesiyle kelime doğrulaması yapmanın farklı yollarını gösteriyor. Temel amaç, bir kelimenin sözlükte geçerli olup olmadığını kontrol etmek. Fakat bunu üç farklı tasarım yaklaşımı ile inceliyoruz: **Dependency Injection** (bağımlılık enjeksiyonu), **Singleton**, ve **Utility** (yardımcı sınıf).

---

## 1. Dictionary Sınıfı

```java
public class Dictionary {

    private String language;

    public Dictionary(String language) {
        this.language = language;
    }

    //toString()
    public String toString() {
        return language;
    }

    //contains()
    public boolean contains(String word) {
        return true;
    }
}
```

- `language`: Sözlüğün dili (ör. "English", "Turkish").
- Kurucu metot `public Dictionary(String language)` ile sözlüğü bir dil ile başlatıyoruz.
- `toString()` metodu, bu nesneyi string olarak yazdırdığımızda dil bilgisini verir.
- `contains(String word)`: Örnekte her zaman `true` döner. Gerçekte kelimenin sözlükte olup olmadığını kontrol etmesi gerekir.

---

## 2. ArticleAnalysisDependencyInjection

```java
public class ArticleAnalysisDependencyInjection {

    private Dictionary dictionary;

    public ArticleAnalysisDependencyInjection(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isValidWord(String word) {
        return dictionary.contains(word);
    }
}
```

### Özellikleri

- **Dependency Injection** yaklaşımı kullanılıyor.
- `Dictionary` nesnesi dışarıdan parametre olarak veriliyor.
- Hangi dili kullandığımızı dinamik olarak belirleyebiliriz.
- `isValidWord(String word)`: `dictionary.contains(word)` çağırır.

### Avantajı

- Aynı sınıfı farklı sözlük nesneleriyle (İngilizce, Türkçe, Almanca vs.) kullanabiliriz.
- **Birim testleri** kolaylaştırır, çünkü farklı `Dictionary` implementasyonlarını bağımsız test edebiliriz.

---

## 3. ArticleAnalysisSingleton

```java
public class ArticleAnalysisSingleton {

    private static final Dictionary dictionary = new Dictionary("English");
    public static ArticleAnalysisSingleton instance = new ArticleAnalysisSingleton();

    private ArticleAnalysisSingleton(){

    }

    public static boolean isValidWord(String word) {
        return dictionary.contains(word);
    }
}
```

### Özellikleri

- **Singleton** tasarım deseni kullanılıyor.
- `dictionary` nesnesi `static` ve `final`. Sadece bir kez oluşturulur.
- `instance` adlı `static` değişken, sınıfın tek örneğini saklar.
- `isValidWord(...)` metodu doğrudan sınıf adı üzerinden erişilir.

### Dezavantajı

- Sözlük **sabit olarak "English"**. Başka dil için kodun değiştirilmesi gerekir.
- Test etmek zordur. Singleton olduğu için farklı test senaryolarında yeni sözlük kullanamayız.

---

## 4. ArticleAnalysisUtility

```java
public class ArticleAnalysisUtility {

    private static final Dictionary dictionary = new Dictionary("English");

    private ArticleAnalysisUtility() {
        throw new UnsupportedOperationException("Cannot instantiate utility class.");
    }

    public static boolean isValidWord(String word) {
        return dictionary.contains(word);
    }
}
```

### Özellikleri

- **Utility (yardımcı)** sınıf yaklaşımı.
- Tüm metotlar `static`.
- Kurucu metot özeldir ve nesne oluşturulamaz.
- `isValidWord(...)` yine `dictionary.contains(word)` çağırır.

### Dezavantajı

- Dil değiştirmek için doğrudan sınıfın kodu değiştirilmelidir.
- **Genişletilebilir** değildir, test senaryolarında esneklik sunmaz.

---

## 5. Main Sınıfı

```java
public class Main {

    public static void main(String[] args) {

        //this works only on english words
        //if we want to use it for other languages, we need to create a new dictionary object
        ArticleAnalysisUtility.isValidWord("hello");

        //this works only on english words
        //if we want to use it for other languages, we need to create a new dictionary object
        ArticleAnalysisSingleton.isValidWord("hello");

        //as you see we can create multiple objects with different dictionaries
        ArticleAnalysisDependencyInjection articleAnalysisDependencyInjection1 = new ArticleAnalysisDependencyInjection(new Dictionary("English"));
        ArticleAnalysisDependencyInjection articleAnalysisDependencyInjection2 = new ArticleAnalysisDependencyInjection(new Dictionary("Turkish"));
    }
}
```

### Açıklama

- `ArticleAnalysisUtility.isValidWord("hello")`: Sadece İngilizce sözlükle çalışır.
- `ArticleAnalysisSingleton.isValidWord("hello")`: Bu da sabit İngilizce sözlük kullanır.
- `ArticleAnalysisDependencyInjection`:
    - `articleAnalysisDependencyInjection1`: İngilizce sözlük ile.
    - `articleAnalysisDependencyInjection2`: Türkçe sözlük ile.
    - Farklı dillerde kontrol yapmak mümkün.

---

## Genel Değerlendirme

- **Dependency Injection** yaklaşımı esneklik ve test kolaylığı sağlar.
- Farklı sözlük nesneleri kullanılarak çoklu dil desteği sunulabilir.
- Sınıf içinde sabit sözlük yerine constructor aracılığıyla dışarıdan bir sözlük almak, kodun genişletilebilirliğini artırır.

### Singleton Yaklaşımı

- Tek bir sözlük nesnesiyle çalışır.
- Kod içinde pratik ama farklı dil ihtiyacında esneklik sunmaz.
- Test edilmesi zordur.

### Utility Yaklaşımı

- Nesne oluşturmadan kullanım sağlar.
- Kod sade ama test edilebilirlik ve dil esnekliği düşüktür.

---

Bu örnekler, farklı tasarım kalıplarının (**Dependency Injection**, **Singleton**, **Utility**) avantajlarını ve dezavantajlarını vurgular. Özellikle **esneklik** ve **test edilebilirlik** konularında **Dependency Injection** yaklaşımı öne çıkmaktadır.
