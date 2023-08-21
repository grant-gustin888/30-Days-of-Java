package DataStructures.CustomDataStructures.HuffmanTree.CleanCode;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static String generateLongText() {
        Map<Character, Integer> characterFrequencies = new HashMap<>();
        characterFrequencies.put('a', 45_000);
        characterFrequencies.put('b', 13_000);
        characterFrequencies.put('c', 12_000);
        characterFrequencies.put('d', 16_000);
        characterFrequencies.put('e', 9_000);
        characterFrequencies.put('f', 5_000);
        StringBuilder longText = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : characterFrequencies.entrySet()) {
            char letter = entry.getKey();
            int frequency = entry.getValue();
            for (int i = 0; i < frequency; i++) {
                longText.append(letter);
            }
        }
        return longText.toString();
    }

    public static void main(String[] args) {
        String text1 = generateLongText();
        Map<Character, String> huffmanEncodings1 = HuffmanCompression.generateHuffmanEncodings(text1);
        System.out.println(huffmanEncodings1);
        // {a=0, b=101, c=100, d=111, e=1101, f=1100}
        // bits required: 1 * 45_000 + 3 * 13_000 + 3 * 12_000 + 3 * 16_000 + 4 * 9_000 + 4 * 5_000 = 224_000 bits

        String text2 = "";
        Map<Character, String> huffmanEncodings2 = HuffmanCompression.generateHuffmanEncodings(text2);
        System.out.println(huffmanEncodings2);
        // {}
        // bits required: 0 bits

        String text3 = "helloworld";  // h = 1, e = 1, l = 3, o = 2, w = 1, r = 1, d = 1
        Map<Character, String> huffmanEncodings3 = HuffmanCompression.generateHuffmanEncodings(text3);
        System.out.println(huffmanEncodings3);
        // {r=1011, d=000, e=001, w=100, h=1010, l=11, o=01}
        // bits required: 2 * 2 + 3 * 1 + 3 * 2 + 2 * 3 + 1 * 3 + 1 * 3 + 1 * 4 = 27 bits
    }
}
