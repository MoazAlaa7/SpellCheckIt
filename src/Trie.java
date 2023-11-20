public class Trie {
    Node root;

    Trie () {
        root = new Node();
    }

    public void insert(String key) {
        int length = key.length();
        int index;
        Node next = root;
        for (int lvl = 0 ; lvl < length ; lvl++) {
            index = key.charAt(lvl)-'a';
            if (next.children[index] == null) {
                next.children[index] = new Node();
            }
            next = next.children[index];
        }
        next.isWordEnd = true;
    }

    public boolean search(String key) {
        int length = key.length();
        int index;
        Node next = root;
        for (int lvl = 0; lvl < length ; lvl++) {
            index = key.charAt(lvl) - 'a';
            if (next.children[index] == null) {
                return false;
            }
            next = next.children[index];
        }
        return (next.isWordEnd);
    }

}


