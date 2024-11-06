public class Statistics {
    public static int orgWordCount;
    public static int newWordCount;
    public static int statementCount;

    public static void getWordCount(String outString, String stopWordsString) {
        orgWordCount = outString.split("\\s+").length;
        System.out.println("\nOriginal Word Count: " + orgWordCount);

        newWordCount = stopWordsString.split("\\s+").length;
        System.out.println("New Word Count: " + newWordCount);
    }

    public static void getStatementCount(String outString) {
        statementCount = outString.split("[.!?]").length;
        System.out.println("Statement Count: " + statementCount);

    }
}