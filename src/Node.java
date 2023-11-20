public class Node {
    final int ALPHABET_SIZE = 26;

    Node[] children;
    boolean isWordEnd;

    Node () {
        children = new Node[ALPHABET_SIZE];
        isWordEnd = false;
    }
}


