package DataStructures.CustomDataStructures.HuffmanTree.CleanCode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCompression {

    public static Map<Character, String> generateHuffmanEncodings(String text) {
        Map<Character, Integer> characterFrequencies = getCharacterFrequencies(text);
        HuffmanTree huffmanTree = generateHuffmanTree(characterFrequencies);
        Map<String, HuffmanTree> huffmanEncodings = expand(huffmanTree);
        int bitsRequired = getBitsRequired(huffmanEncodings);
        return buildHuffmanTable(huffmanEncodings);
    }

    private static Map<Character, Integer> getCharacterFrequencies(String text) {
        Map<Character, Integer> characterFrequencies = new HashMap<>();
        for (char letter : text.toCharArray()) {
            if (characterFrequencies.containsKey(letter)) {
                characterFrequencies.put(letter, characterFrequencies.get(letter) + 1);
            } else {  // !characterFrequencies.containsKey(letter)
                characterFrequencies.put(letter, 1);
            }
        }
        return characterFrequencies;
    }

    private static HuffmanTree generateHuffmanTree(Map<Character, Integer> characterFrequencies) {
        PriorityQueue<HuffmanTree> frontier = new PriorityQueue<>(
            new Comparator<HuffmanTree>() {
                @Override
                public int compare(HuffmanTree tree1, HuffmanTree tree2) {
                    if (tree1.frequency < tree2.frequency) {
                        return -1;
                    } else if (tree1.frequency > tree2.frequency) {
                        return 1;
                    } else {  // tree1.frequency == tree2.frequency
                        return tree1.letter - tree2.letter;
                        // return Character.compare(tree1.letter, tree2.letter);
                    }
                }
            }
        );  // sort by least frequencies first. break ties by alphabetical order

        // add all huffman tree leafs to priority queue
        for (Map.Entry<Character, Integer> entry : characterFrequencies.entrySet()) {
            char letter = entry.getKey();
            int frequency = entry.getValue();
            HuffmanTree newNode = new HuffmanTree(letter, frequency, null, null);
            frontier.add(newNode);
        }

        while (frontier.size() >= 2) {
            HuffmanTree leftChild = frontier.poll();
            HuffmanTree rightChild = frontier.poll();
            // assert leftChild != null;
            // assert rightChild != null;
            HuffmanTree newNode = new HuffmanTree(
                ' ',
                leftChild.frequency + rightChild.frequency,
                leftChild, rightChild
            );
            frontier.add(newNode);
        }

        // assert frontier.size() == 0 || frontier.size() == 1;
        if (frontier.size() == 1) {
            return frontier.poll();
        } else {
            return null;
        }
    }

    private static Map<String, HuffmanTree> expand(HuffmanTree root) {
        Map<String, HuffmanTree> huffmanEncodings = new HashMap<>();
        expand(root, "", huffmanEncodings);
        return huffmanEncodings;
    }

    private static void expand(HuffmanTree root, String encoding, Map<String, HuffmanTree> huffmanEncodings) {
        if (root == null) {
            return;
        } else if (root.isLeaf()) {
            huffmanEncodings.put(encoding, root);
        } else {
            expand(root.leftChild, encoding + "0", huffmanEncodings);
            expand(root.rightChild, encoding + "1", huffmanEncodings);
        }
    }

    private static Map<Character, String> buildHuffmanTable(Map<String, HuffmanTree> huffmanEncodings) {
        Map<Character, String> encodingTable = new HashMap<>();
        for (Map.Entry<String, HuffmanTree> entry : huffmanEncodings.entrySet()) {
            String encoding = entry.getKey();
            HuffmanTree huffmanTree = entry.getValue();
            assert huffmanTree != null;
            encodingTable.put(huffmanTree.letter, encoding);
        }
        return encodingTable;
    }

    private static int getBitsRequired(Map<String, HuffmanTree> huffmanEncodings) {
        int bitsRequired = 0;
        for (Map.Entry<String, HuffmanTree> entry : huffmanEncodings.entrySet()) {
            String encoding = entry.getKey();
            HuffmanTree huffmanTree = entry.getValue();
            assert huffmanTree != null;
            bitsRequired += encoding.length() * huffmanTree.frequency;
        }
        System.out.println("bits required: " + bitsRequired);
        return bitsRequired;
    }
}
