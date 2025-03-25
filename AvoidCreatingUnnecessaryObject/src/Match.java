import java.util.regex.Pattern;

public class Match {

    private static final Pattern REGEX = Pattern.compile("your-regex-pattern");

    public static boolean isMatching(String s) {
        return s.matches("your-regex-pattern");
    }

    static boolean isNonMatching(String s) {
        return !REGEX.matcher(s).matches();
    }


}
