import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {

    public static void printMenu() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("-------------------------------");
            System.out.println("Welcome to our article analyzer");
            System.out.println("1. Select a topic");
            System.out.println("2. Add a new topic");
            System.out.println("3. Add a new article");
            System.out.println("4. Exit");
            System.out.println("-------------------------------");
            System.out.print("Please choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Input a topic to analyze: ");
                    String topic = scan.nextLine();
                    String path = ArticleProcessor.searchTopics(topic);
                    if(path != null) {
                        ArticleProcessor.readFiles(path);
                        break;
                    }
                    break;
                case 2:
                    System.out.println("Please input a directory to add topic folder: ");
                    String directory = scan.nextLine();
                    System.out.print("Enter the new topic name: ");
                    String topicName = scan.nextLine();
                    String newTopic = ArticleProcessor.organizeTopics(directory, topicName);
                    ArticleProcessor.topics.put(topicName, newTopic);
                    break;
                case 3:
                    System.out.print("Enter the topic: ");
                    String accessTopic = scan.nextLine();
                    String accessPath = ArticleProcessor.searchTopics(accessTopic);
                    System.out.print("Enter the article content: ");
                    String articleContent = scan.nextLine();
                    ArticleProcessor.createTXTFile(accessPath, articleContent);
                    System.out.println("New article added to topic '" + accessTopic + "'.");
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scan.close();
    }
}
