import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ArticleProcessor {
    public static String[] stopWords;
    public static File[] fileList;

    public static void readFiles(String directoryPath) throws FileNotFoundException {
        File directory = new File(directoryPath);
        fileList = directory.listFiles();

        for (File file : fileList) {
            if (file.isFile()) {
                Scanner scan = new Scanner(new FileReader(file));
                StringBuilder sb = new StringBuilder();

                sb.setLength(0);

                while (scan.hasNext()) {
                    sb.append(scan.next());
                    sb.append(" ");
                }

                String outString = sb.toString().toLowerCase();
                System.out.println(outString);
                ArticleProcessor.removeStopWords(outString);

                scan.close();
            }
        }
    }

    public static void removeStopWords(String outString) {
        stopWords = new String[]{"and", "but", "or"};
        String[] words = outString.split(" ");

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
            }
        }
    }
}

