import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ArticleProcessor {
    public static ArrayList<String> stopWords = new ArrayList<>();
    public static File[] fileList;
    public static ArrayList<String> positiveWords = new ArrayList<>();
    public static ArrayList<String> negativeWords = new ArrayList<>();
    public static HashMap<String, String> topics = new HashMap<>();

    public static void readFiles(String directoryPath) throws FileNotFoundException {
        File directory = new File(directoryPath);
        fileList = directory.listFiles();

        if (fileList == null || fileList.length == 0) {
            System.out.println("No articles found in this topic.");
            return;
        }

        String outString = "";
        for (File file : fileList) {
            if(file.isHidden() || file.getName().equals(".DS_Store")) {
                continue;
            }

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
        if (fileList != null && fileList.length > 0) {
            FrequencyRanker.getRichestVocab();
        }
    }

    public static String removeStopWords(String outString) throws FileNotFoundException {
        File stopWordsFile = new File("/Users/kevintorrealba/ProjectLibrary/stopwords.txt");
        Scanner scanner = new Scanner(stopWordsFile);

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

    public static String organizeTopics(String directoryPath, String topicName) {
        File newTopic = new File(directoryPath, topicName);

        try {
            if (newTopic.mkdirs()) {
                System.out.println("Topic folder '" + topicName + "' created successfully in '" + directoryPath + "'.");
            } else if (newTopic.exists()) {
                System.out.println("Topic folder '" + topicName + "' already exists in '" + directoryPath + "'.");
            } else {
                System.out.println("Failed to create topic folder. Please check the directory path.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return newTopic.getAbsolutePath();
    }

    public static String searchTopics(String topicName) {
        if (topics.containsKey(topicName)) {
            return topics.get(topicName);
        } else {
            System.out.println("Topic not found.");
        }
        return null;
    }

    public static void createTXTFile(String path, String content) {
        String filePath = path + "/article_" + System.currentTimeMillis() + ".txt";
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content);
            writer.close();
            System.out.println("Successfully created the file.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}