import java.util.ArrayList;

public class FrequencyRanker {
    public static ArrayList<String> words;
    public static ArrayList<WordCount> wordCountList = new ArrayList<>();

    public static void rankWords(String cleanString) {
        for(String word : cleanString.split(" ")) {
            boolean found = false;
            for(WordCount wc : wordCountList) {
                if(wc.word.equals(word)) {
                    wc.count++;
                    found = true;
                    break;
                }
            }

            if(!found) {
                wordCountList.add(new WordCount(word, 1));
            }
        }

        for(int i = 0; i < wordCountList.size() - 1; i++) {
            for(int j = 0; j < wordCountList.size() - i - 1; j++) {
                if(wordCountList.get(j).count < wordCountList.get(j + 1).count) {
                    WordCount temp = wordCountList.get(j);
                    wordCountList.set(j, wordCountList.get(j + 1));
                    wordCountList.set(j + 1, temp);
                }
            }
        }

        System.out.println("Words Ranked by Frequency: ");
        for(WordCount wc : wordCountList) {
            System.out.println(wc.word + ": " + wc.count);
        }

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