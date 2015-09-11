package util;

public class StringUtils {

    public static String padRight(String s, int n) {
        if(n < 1) throw new IllegalStateException("Cannot pad with a number less than 1.");
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        if(n < 1) throw new IllegalStateException("Cannot pad with a number less than 1.");
        return String.format("%1$" + n + "s", s);
    }

}
