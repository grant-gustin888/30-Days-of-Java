package InterviewProblems.MediumQuestions.Tries.WordDictionary.OptimalSolution;

public class WordDictionary {

    // TODO: Add time and space complexity analysis here.
    // Let n = the total number of letters in the word dictionary.
    //
    // Time:
    // -->
    // Space:
    // -->

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        System.out.println(wordDictionary.search("pad"));  // false
        System.out.println(wordDictionary.search("bad"));  // true
        System.out.println(wordDictionary.search(".ad"));  // true
        System.out.println(wordDictionary.search("b.."));  // true

        // bonus test cases
        System.out.println(wordDictionary.search("b.d"));  // true
        System.out.println(wordDictionary.search("..."));  // true

        // ["WordDictionary","addWord","addWord","search","search","search","search","search","search","search","search"]
        // [[],["a"],["ab"],["a"],["a."],["ab"],*[".a"],[".b"],["ab."],["."],[".."]]
        // Output:
        // [null,null,null,true,true,true,*true,true,false,true,true]
        // Expected:
        // [null,null,null,true,true,true,*false,true,false,true,true]
        WordDictionary wordDictionary2 = new WordDictionary();
        wordDictionary2.addWord("a");
        wordDictionary2.addWord("ab");
        System.out.println(wordDictionary2.search("a"));  // true
        System.out.println(wordDictionary2.search("a."));  // true
        System.out.println(wordDictionary2.search("ab"));  // true
        System.out.println(wordDictionary2.search(".a"));  // false
        System.out.println(wordDictionary2.search(".b"));  // true
        System.out.println(wordDictionary2.search("ab."));  // false
        System.out.println(wordDictionary2.search("."));  // true
        System.out.println(wordDictionary2.search(".."));  // true
    }

    private final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode currentNode = root;
        for (char letter : word.toCharArray()) {
            if (!currentNode.children.containsKey(letter)) {
                TrieNode newNode = new TrieNode(letter);
                currentNode.children.put(letter, newNode);
            }
            currentNode = currentNode.children.get(letter);
        }
        currentNode.isWord = true;
    }

    public boolean search(String word) {
        return search(word, root, 0);
    }

    private boolean search(String word, TrieNode currentNode, int index) {
        for (int i = index; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (letter == '.') {
                for (TrieNode childNode : currentNode.children.values()) {
                    if (search(word, childNode, i + 1)) {
                        return true;
                    }
                }
                return false;
            } else if (!currentNode.children.containsKey(letter)) {  // letter != '.'
                    return false;
            } else {  // currentNode.children.containsKey(letter)
                currentNode = currentNode.children.get(letter);
            }
        }
        return currentNode.isWord;
    }
}
