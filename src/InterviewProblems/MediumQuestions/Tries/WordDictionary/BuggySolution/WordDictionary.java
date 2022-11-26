package InterviewProblems.MediumQuestions.Tries.WordDictionary.BuggySolution;

public class WordDictionary {

    // TODO: Add time and space complexity analysis here.
    // Let n = the total number of letters in the word dictionary.
    //
    // Time:
    // -->
    // Space:
    // -->

    public static void main(String[] args) {
//        WordDictionary wordDictionary = new WordDictionary();
//        wordDictionary.addWord("bad");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//
//        System.out.println(wordDictionary.search("pad"));  // false
//        System.out.println(wordDictionary.search("bad"));  // true
//        System.out.println(wordDictionary.search(".ad"));  // true
//        System.out.println(wordDictionary.search("b.."));  // true
//
//        // bonus test cases
//        System.out.println(wordDictionary.search("b.d"));  // true
//        System.out.println(wordDictionary.search("..."));  // true

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
        System.out.println(wordDictionary2.search(".a"));  // false  todo: returns true?
        System.out.println(wordDictionary2.search(".b"));  // false  todo: returns true?
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
        int wordIndex = 0;
        while (wordIndex < word.length()) {
            char letter = word.charAt(wordIndex);
            if (!currentNode.children.containsKey(letter)) {
                TrieNode newNode = new TrieNode(letter, wordIndex == word.length() - 1);
                currentNode.children.put(letter, newNode);
            } else {
                currentNode.isWord = wordIndex == word.length() - 1;
            }

            currentNode = currentNode.children.get(letter);
            wordIndex++;
        }

        //        for (char letter : word.toCharArray()) {
        //            if (!currentNode.children.containsKey(letter)) {
        //                TrieNode newNode = new TrieNode(letter, false);
        //                currentNode.children.put(letter, newNode);
        //            }
        //            currentNode = currentNode.children.get(letter);
        //        }
        //        currentNode.isEndOfWord = true;  // mark the last letter as true.
    }

    public boolean search(String word) {
        TrieNode currentNode = root;
        int wordIndex = 0;
        while (wordIndex < word.length()) {
            char letter = word.charAt(wordIndex);
            if (letter == '.') {
                return dfs(currentNode, word, wordIndex);
            } else if (!currentNode.children.containsKey(letter)) {  // letter != '.'
                return false;
            } else {  // currentNode.children.containsKey(letter) && letter != '.'
                currentNode = currentNode.children.get(letter);
                wordIndex++;
            }
        }
        return currentNode.isWord;
    }

    /**
     * Return true iff currentNode reaches a leaf node with isEndOfWord = true.
     */
    private boolean dfs(TrieNode currentNode, String word, int wordIndex) {
        if (wordIndex == word.length()) {
            return currentNode.isWord;
        }

        for (TrieNode childNode : currentNode.children.values()) {
            boolean foundWord = dfs(childNode, word, wordIndex + 1);
            if (foundWord) {
                return true;
            }
        }
        return false;
    }
}
