# Semester Project: Milestone 1

## Group Members: Kevin Torrealba, Nicholas Murphy, Kevin Carcamo

### Main Class
The `Main.java` file serves as the entry point of the program. It is responsible for interacting with the user and passing the necessary data to other parts of the application.

#### Key Functions:
- **User Interaction**: The program prompts the user to input a directory path where the article files are stored.
- **Data Handling**: Once the user provides the directory path, it is passed to the `ArticleProcessor` class for file reading and further processing.

The purpose of this class is to gather input from the user and initiate the file processing by calling the appropriate method in the `ArticleProcessor` class. This ensures that the user can specify the location of the files, and the program will handle the rest of the processing from there.

### ArticleProcessor Class

The `ArticleProcessor` class is responsible for reading and processing the files from the directory provided by the user. It handles the core functionality of the project, including reading article files, counting words, and processing the text.

#### Key Functions:
- **File Reading**: The class takes a directory path as input and reads all the article files within that directory.
- **Text Processing**: It processes the text in each article by counting the total number of words, removing stop words, and counting the number of statements (sentences) in the articles.
- **Statistics Generation**: After processing, the class gathers the relevant statistics such as original word count, new word count (after stop words are removed), and statement count.

The `ArticleProcessor` is the core component that performs the main processing tasks for the articles, preparing the data for analysis and generating statistics based on the text.

### Statistics Class

The `Statistics` class is responsible for calculating basic textual statistics such as word count and statement count.

### Fields:
- `orgWordCount`: Stores the original word count of the text.
- `newWordCount`: Stores the word count after removing stop words.
- `statementCount`: Stores the number of statements in the text.

### Methods:
- **`getWordCount(String outString, String stopWordsString)`**: This static method calculates the total number of words in the original text (`outString`) and the number of words remaining after stop words are removed (`stopWordsString`). It prints both values.
- **`getStatementCount(String outString)`**: This static method calculates the number of statements in the text by splitting the text based on punctuation (`.`, `!`, `?`) and prints the count.

### FrequencyRanker Class

The `FrequencyRanker` class ranks words based on their frequency in a given text and stores the results in a list.

### Fields:
- `words`: A static list that holds all words from the text (not used directly in the methods provided).
- `wordCountList`: A static list of `WordCount` objects that stores each unique word and its frequency in the text.

### Methods:
- **`rankWords(String cleanString)`**: This static method splits the provided `cleanString` into words, counts the frequency of each word, and sorts the words in descending order of frequency. It prints the ranked words and their frequencies.

### Nested Class:
- **`WordCount`**: This class represents a word and its associated frequency.
  - **Fields:**
    - `word`: The word being counted.
    - `count`: The frequency of the word.
  - **Constructor:**
    - `WordCount(String word, int count)`: Initializes a new `WordCount` object with the given word and frequency.
