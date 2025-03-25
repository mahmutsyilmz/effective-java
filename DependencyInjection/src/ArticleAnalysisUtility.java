public class ArticleAnalysisUtility {

    private static final Dictionary dictionary = new Dictionary("English");

    private ArticleAnalysisUtility() {
        throw new UnsupportedOperationException("Cannot instantiate utility class.");
    }

    public static boolean isValidWord(String word) {
        return dictionary.contains(word);
    }
}
