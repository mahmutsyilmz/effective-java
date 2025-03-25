public class ArticleAnalysisSingleton {

    private static final Dictionary dictionary = new Dictionary("English");
    public static ArticleAnalysisSingleton instance = new ArticleAnalysisSingleton();

    private ArticleAnalysisSingleton(){

    }

    public static boolean isValidWord(String word) {
        return dictionary.contains(word);
    }

}
