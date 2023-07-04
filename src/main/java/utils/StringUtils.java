package utils;

public class StringUtils {
    // 직접 생성 금지
    private StringUtils(){}

    public static final String NEWLINE = System.getProperty("line.separator");

    public static String appendNewLine(String str) {
        return str.concat(NEWLINE);
    }

    public static StringBuilder appendNewLine(StringBuilder sb) {
        return sb.append(NEWLINE);
    }
}
