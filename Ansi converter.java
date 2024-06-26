import java.util.regex.*;

public class AnsiToHtmlConverter {

    // ANSI color to HTML color mapping
    private static final String ANSI_RESET = "<span style=\"color:inherit\">";
    private static final String ANSI_BLACK = "<span style=\"color:black\">";
    private static final String ANSI_RED = "<span style=\"color:red\">";
    private static final String ANSI_GREEN = "<span style=\"color:green\">";
    private static final String ANSI_YELLOW = "<span style=\"color:yellow\">";
    private static final String ANSI_BLUE = "<span style=\"color:blue\">";
    private static final String ANSI_PURPLE = "<span style=\"color:purple\">";
    private static final String ANSI_CYAN = "<span style=\"color:cyan\">";
    private static final String ANSI_WHITE = "<span style=\"color:white\">";

    // Regular expression to match ANSI color codes
    private static final String ANSI_PATTERN = "\u001B\\[(\\d+);(\\d+)m(.+?)\u001B\\[0m";

    public static String convertAnsiToHtml(String input) {
        Pattern pattern = Pattern.compile(ANSI_PATTERN);
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String colorCode = matcher.group(1);
            String text = matcher.group(3);

            String htmlColor;
            switch (colorCode) {
                case "30":
                    htmlColor = ANSI_BLACK;
                    break;
                case "31":
                    htmlColor = ANSI_RED;
                    break;
                case "32":
                    htmlColor = ANSI_GREEN;
                    break;
                case "33":
                    htmlColor = ANSI_YELLOW;
                    break;
                case "34":
                    htmlColor = ANSI_BLUE;
                    break;
                case "35":
                    htmlColor = ANSI_PURPLE;
                    break;
                case "36":
                    htmlColor = ANSI_CYAN;
                    break;
                case "37":
                    htmlColor = ANSI_WHITE;
                    break;
                default:
                    htmlColor = ANSI_RESET; // default to resetting color
                    break;
            }

            matcher.appendReplacement(sb, htmlColor + text + "</span>");
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "123\u001B[31;1mHello \u001B[32mWorld\u001B[0m456";
        String htmlOutput = convertAnsiToHtml(input);
        System.out.println(htmlOutput);
    }
}
