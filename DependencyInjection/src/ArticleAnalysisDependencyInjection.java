public class ArticleAnalysisDependencyInjection {

    private Dictionary dictionary;

    public ArticleAnalysisDependencyInjection(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isValidWord(String word) {
        return dictionary.contains(word);
    }
}
