import java.util.ArrayList;

public class FrequencyRanker {
    public static ArrayList<WordCount> wordCountList = new ArrayList<>();
    public static ArrayList<Integer> vocabCountList = new ArrayList<>(3);
    public static int vocabCount;

    public static void rankWords(String cleanString) {
        wordCountList.clear();
        vocabCount = 0;

        for (String word : cleanString.split(" ")) {
            boolean found = false;
            for (WordCount wc : wordCountList) {
                if (wc.word.equals(word)) {
                    wc.count++;
                    found = true;
                    break;
                }
            }

            if (!found) {
                wordCountList.add(new WordCount(word, 1));
            }
        }

        for (int i = 0; i < wordCountList.size() - 1; i++) {
            for (int j = 0; j < wordCountList.size() - i - 1; j++) {
                if (wordCountList.get(j).count < wordCountList.get(j + 1).count) {
                    WordCount temp = wordCountList.get(j);
                    wordCountList.set(j, wordCountList.get(j + 1));
                    wordCountList.set(j + 1, temp);
                }
            }
        }

        System.out.println("Words Ranked by Frequency: ");
        for (WordCount wc : wordCountList) {
            System.out.println(wc.word + ": " + wc.count);
        }

        System.out.println("Top 3 words in this article: ");
        for (int i = 0; i < Math.min(3, wordCountList.size()); i++) {
            WordCount wc = wordCountList.get(i);
            System.out.println(wc.word + ": " + wc.count);
        }

        for (WordCount wc : wordCountList) {
            if (wc.count >= 1) {
                vocabCount++;
            }
        }

        vocabCountList.add(vocabCount);
        System.out.println("Number of unique words in this article: " + vocabCount);
    }

    public static void getRichestVocab() {
        int maxIndex = 0;
        for (int i = 1; i < vocabCountList.size(); i++) {
            if (vocabCountList.get(i) > vocabCountList.get(maxIndex)) {
                maxIndex = i;
            }
        }
        System.out.println("\nARTICLE " + (maxIndex + 1) + " HAS THE RICHEST VOCABULARY.");
    }

    public static class WordCount {
        public String word;
        public int count;

        public WordCount(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}
