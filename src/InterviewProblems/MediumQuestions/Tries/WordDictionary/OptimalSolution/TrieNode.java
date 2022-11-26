package InterviewProblems.MediumQuestions.Tries.WordDictionary.OptimalSolution;

import java.util.HashMap;
import java.util.Map;

class TrieNode {

    char letter;
    boolean isWord;
    Map<Character, TrieNode> children;

    public TrieNode() {
        this.letter = '\0';
        this.isWord = false;
        this.children = new HashMap<>();
    }

    public TrieNode(char newLetter) {
        this.letter = newLetter;
        this.isWord = false;
        this.children = new HashMap<>();
    }
}
