# Semester Project: Milestone 1

## Group Members: Kevin Torrealba, Nicholas Murphy, Kevin Carcamo

### Main Class

The `Main` class serves as the starting point of the program, handling user input and initiating the process of reading and processing articles.

### Key Functions:
- **User Interaction**: The class prompts the user to input the directory path where article files are stored.
- **Process Initialization**: It passes the user-provided directory path to the `ArticleProcessor` class to start reading and processing the files.

The `Main` class is the entry point for the program, ensuring that user input is gathered before starting the core text processing tasks performed by other classes.

### ArticleProcessor Class

The `ArticleProcessor` class is responsible for reading and processing the article files in the directory provided by the user. It performs key tasks such as file reading, stop word removal, and generating word frequency statistics.

### Fields:
- **`stopWords`**: A static array list that stores the stop words used for filtering out common words from the text.
- **`fileList`**: A static array of `File` objects that stores the list of files found in the specified directory.

### Methods:
- **`readFiles(String directoryPath)`**: This static method reads all the files in the provided directory, processes each file's content, and calls other methods to analyze the text. It prints the original text and processes it to remove stop words, count words, and rank words by frequency.
  
- **`removeStopWords(String outString)`**: This static method removes stop words from the provided text (`outString`) by comparing each word with a predefined list of stop words. It returns the cleaned string, which no longer contains stop words, and prints the cleaned version of the text.

The `ArticleProcessor` class manages the entire text processing workflow, from reading files and removing stop words to generating word frequency statistics. It acts as the central component for processing and analyzing the content of the article files.

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

## UML Diagram
<img width="852" alt="UML_Diagram" src="https://github.com/user-attachments/assets/ba605c2a-0437-454c-9fcf-7d1a05399f9f">

# Semester Project: Milestone 2
In this milestone we've updated the program to be able to determine which article per topic uses the richest vocabulary. Additionally, the program is also able to print the top 3 words per each article. Lastly, the code was updated so that the program can now analyze the sentiment of each article and determine whether it is positive or negative. 

### ArticleProcessor Class
In this milestone `ArticleProcessor` was updated with two additional fields and one additional method so that it may analyze the sentiment per article.

### New Additional Fields:
- **`positiveWords`**: A static array list that stores the positive words used for analyzing the sentiment of the text.
- **`negativeWords`**: A static array list that stores the negative words used for analyzing the sentiment of the text.

### New Additional Method:
- **`analyzeSentiment(String text)`**: This static method 

### Frequency Ranker


## UML Diagram for Milestone 2
![Milestone 1](https://github.com/user-attachments/assets/d570213e-8b8d-4800-9dbe-2a14ed2ccf27)

## Milestone 2 Example Outputs
<img width="363" alt="Screenshot 2024-11-06 at 11 52 31 AM" src="https://github.com/user-attachments/assets/2a0182b1-7d0d-4801-8470-c87ff15849cd">
<img width="359" alt="Screenshot 2024-11-06 at 11 53 01 AM" src="https://github.com/user-attachments/assets/964bcd61-17a7-4681-9e24-54a03d4a6d1e">
<img width="356" alt="Screenshot 2024-11-07 at 10 43 21 AM" src="https://github.com/user-attachments/assets/0583a07e-1af1-49aa-806d-daa68aae2d5d">

