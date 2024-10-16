import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ArticleProcessor {
    public static ArrayList<String> stopWords;
    public static File[] fileList;

    public static void readFiles(String directoryPath) throws FileNotFoundException {
        File directory = new File(directoryPath);
        fileList = directory.listFiles();

        String outString = "";
        for (File file : fileList) {
            if (file.isFile()) {
                Scanner scan = new Scanner(new FileReader(file));
                StringBuilder sb = new StringBuilder();

                while (scan.hasNext()) {
                    sb.append(scan.next());
                    sb.append(" ");
                }

                outString = sb.toString().toLowerCase();
                System.out.println("\nOriginal File: " + outString);
                String removedStopWords = removeStopWords(outString);
                Statistics.getWordCount(outString, removedStopWords);
                Statistics.getStatementCount(outString);
                FrequencyRanker.rankWords(removedStopWords);


                scan.close();
            }
        }
    }

    public static String removeStopWords(String outString) throws FileNotFoundException {
        File stopWordsFile = new File("/Users/kevintorrealba/ProjectLibrary/stopwords.txt");
        Scanner scanner = new Scanner(stopWordsFile);

        stopWords = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String data = scanner.nextLine();
            stopWords.add(data);
        }
        scanner.close();

        String[] words = outString.split(" ");
        StringBuilder sb = new StringBuilder();

        System.out.print("Removed Stop Words: ");
        for (String word : words) {
            boolean isStopWord = false;
            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }
            if (!isStopWord) {
                System.out.print(word + " ");
                sb.append(word).append(" ");
            }
        }
        return sb.toString().trim();
    }
}