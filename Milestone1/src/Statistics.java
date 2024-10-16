
public class Statistics {
    public int orgWordCount;
    public int newWordCount;
    public int statementCount;

    public static void getWordCount(String outString, String stopWordsString) {
        int orgWordCount = outString.split("\\s+").length;
        System.out.println("\nOriginal Word Count: " + orgWordCount);

        int newWordCount = stopWordsString.split("\\s+").length;
        System.out.println("New Word Count: " + newWordCount);
    }

    public static void getStatementCount(String outString) {
        int statementCount = outString.split("[.!?]").length;
        System.out.println("Statement Count: " + statementCount);

    }
}