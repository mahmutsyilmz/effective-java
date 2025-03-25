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
