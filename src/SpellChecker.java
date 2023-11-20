import java.util.*;

public class SpellChecker {
    final int ALPHABET_SIZE = 26;
    Trie root;
    Set<String> suggestedWords = new HashSet<>();

    SpellChecker(Trie trie) {
        root = trie;
        welcomeMessage();
    }

    void welcomeMessage() {
        System.out.println("=============== Welcome to SpellCheckIt ===============");
        System.out.println("a tool designed to help you ensure the accuracy of words you enter.\nWhether you're writing a document, crafting an email, or simply want to verify the spelling of a word,\nthis program has got you covered.");
        System.out.println("- Enter (?) on keyboard to exit");
        System.out.println("=============== Let's get started! ===============");
    }

    public void editDistance(String word) {
        int length = word.length();

        int i, j;
        // Check for missing letter
        for (i = -1; i < length; i++) {
            for (j = 0; j < ALPHABET_SIZE; j++)
            {
                char C = (char) ('a' + j);
                String newWord = word.substring(0, i + 1) + C + word.substring(i + 1);

                boolean wordExist = root.search(newWord);
                if (wordExist) {
                    suggestedWords.add(newWord);
                }
            }
        }
        // Check for misspelled letter
        for (i = 0; i < length; i++)
        {
            for (j = 0; j < ALPHABET_SIZE; j++)
            {
                char C = (char) ('a' + j);
                String newWord = word.substring(0, i) + C + word.substring(i + 1);

                boolean wordExist = root.search(newWord);
                if (wordExist) {
                    suggestedWords.add(newWord);
                }
            }
        }
        // Check for extra letter
        for (i = 0; i < length; i++)
        {
            String newWord = word.substring(0, i) + word.substring(i + 1);

            boolean wordExist = root.search(newWord);
            if (wordExist) {
                suggestedWords.add(newWord);
            }
        }
        // Check for adjacent transposed letters
        for (i = 0; i < length - 1; i++)
        {
            char A = word.charAt(i), B = word.charAt(i + 1);
            String newWord = word.substring(0, i) + B + A + word.substring(i + 2);

            boolean wordExist = root.search(newWord);
            if (wordExist) {
                suggestedWords.add(newWord);
            }
        }
    }
    void spellCheck(String word) {
        suggestedWords.clear();
        boolean wordFound = root.search(word);

        if (wordFound) {
            System.out.println("Word is spelled correctly :)");
        }
        else {
            System.out.println("Word is not spelled correctly :(");

            editDistance(word);
            if (suggestedWords.isEmpty()) {
                System.out.println("Sorry, No suggestions; Maybe word doesn't exist :/");
            }
            else {
                System.out.println("Suggested word/s:");
                for (String suggestedWord : suggestedWords)
                {
                    System.out.println(suggestedWord);
                }
            }
        }
    }
}
