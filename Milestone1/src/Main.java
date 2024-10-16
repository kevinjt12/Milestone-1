import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input a directory: ");
        String path = scan.nextLine();
        ArticleProcessor.readFiles(path);
    }
}