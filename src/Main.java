import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {
        // Store dictionary words in a vector
        Vector<String> words = new Vector<>();
        BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            words.add(line);
        }
        br.close();

        // Transfrom dictionary words to lowercase
        words.replaceAll(String::toLowerCase);

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        SpellChecker spellchecker = new SpellChecker(trie);
        Scanner scanner = new Scanner(System.in);
        // Program loop
        while (true) {
            System.out.println("\n-Please, Enter a word below to check its spelling:");
            String userInput = scanner.nextLine();
            if (userInput.equals("?")) break;
            userInput = userInput.toLowerCase();
            spellchecker.spellCheck(userInput);
        }
    }
}