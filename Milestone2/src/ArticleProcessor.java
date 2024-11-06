import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArticleProcessor {
    public static ArrayList<String> stopWords;
    public static File[] fileList;
    public static ArrayList<String> positiveWords = new ArrayList<>();
    public static ArrayList<String> negativeWords = new ArrayList<>();


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
                String newOutString = outString.toLowerCase().trim().replaceAll("[^a-zA-Z]", " ").replaceAll(" +", " ");
                System.out.println("\nOriginal File: " + newOutString);
                String removedStopWords = removeStopWords(newOutString);
                Statistics.getWordCount(outString, removedStopWords);
                Statistics.getStatementCount(outString);
                FrequencyRanker.rankWords(removedStopWords);

                analyzeSentiment(removedStopWords);
                scan.close();
            }
        }
        FrequencyRanker.getRichestVocab();
    }

    public static String removeStopWords(String outString) throws FileNotFoundException {
        File stopWordsFile = new File("/Users/kevintorrealba/ProjectLibrary/stopwords.txt");
        Scanner scanner = new Scanner(stopWordsFile);

        stopWords = new ArrayList<>();
        while (scanner.hasNextLine()) {
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

    public static void analyzeSentiment(String text) throws FileNotFoundException {
        File positiveWordsFile = new File("/Users/kevintorrealba/ProjectLibrary/Lexicon_Dataset/positive-words.txt");
        File negativeWordsFile = new File("/Users/kevintorrealba/ProjectLibrary/Lexicon_Dataset/negative-words.txt");

        Scanner positiveScan = new Scanner(positiveWordsFile);
        Scanner negativeScan = new Scanner(negativeWordsFile);

        while (positiveScan.hasNextLine()) {
            String posWords = positiveScan.nextLine();
            positiveWords.add(posWords);
        }
        positiveScan.close();

        while (negativeScan.hasNextLine()) {
            String negWords = negativeScan.nextLine();
            negativeWords.add(negWords);
        }
        negativeScan.close();

        int positiveCount = 0;
        int negativeCount = 0;

        for (String word : text.split(" ")) {
            if (positiveWords.contains(word)) {
                positiveCount++;
            } else if (negativeWords.contains(word)) {
                negativeCount++;
            }
        }

        System.out.println("Positive Word Count: " + positiveCount);
        System.out.println("Negative Word Count: " + negativeCount);

        if (positiveCount > negativeCount) {
            System.out.println("Overall Sentiment: Positive");
        } else if (negativeCount > positiveCount) {
            System.out.println("Overall Sentiment: Negative");
        } else {
            System.out.println("Overall Sentiment: Neutral");
        }
    }
}