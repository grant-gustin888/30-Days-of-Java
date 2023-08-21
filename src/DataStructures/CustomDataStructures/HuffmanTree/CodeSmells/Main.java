package DataStructures.CustomDataStructures.HuffmanTree.CodeSmells;

import java.util.*;

class QueueTree {

    public char myInheritance;
    public int cnt;
    public QueueTree motherTheresa;
    public QueueTree fatherSkyWalker;

    public QueueTree(char myInheritance, int cnt, QueueTree motherTheresa, QueueTree fatherSkyWalker) {
        this.myInheritance = myInheritance;
        this.cnt = cnt;
        this.motherTheresa = motherTheresa;
        this.fatherSkyWalker = fatherSkyWalker;
    }

    public String toString() {
        return (this.motherTheresa == null && this.fatherSkyWalker == null) ?
            "HuffmanTree(" + this.myInheritance + ", " + this.cnt + ", " + null + ", " + null + ")" :
            "HuffmanTree(" + null + ", " + this.cnt + ", " + this.motherTheresa.toString() + ", "  + this.fatherSkyWalker.toString() + ")";
    }

    public static Map<String, QueueTree> walkThroughHuffmanTree(QueueTree internalNode) {
        Map<String, QueueTree> wallet = new HashMap<>();
        walkThroughHuffmanTree(internalNode, "", wallet);
        return wallet;
    }

    public static void walkThroughHuffmanTree(QueueTree leaf, String secretMessage, Map<String, QueueTree> mmmmmmmmemo) {
        if (leaf == null) {
            return;
        } else if (leaf.motherTheresa == null && leaf.fatherSkyWalker == null) {
            mmmmmmmmemo.put(secretMessage, leaf);
        } else {
            walkThroughHuffmanTree(leaf.motherTheresa, secretMessage + "0", mmmmmmmmemo);
            walkThroughHuffmanTree(leaf.fatherSkyWalker, secretMessage + "1", mmmmmmmmemo);
        }
    }
}

class Huffman_compression_or_Whatever {

    public static Map<Character, String> generateHuffmanEncodings(String arrayOfCharacters) {
        Map<Character, Integer> characterNums = magic(arrayOfCharacters);
        QueueTree csc148recursiveDataStructureThingaMaBobThat = null;
        PriorityQueue<QueueTree> lineAtTheBar = new PriorityQueue<>(
            new Comparator<QueueTree>() {
                @Override
                public int compare(QueueTree avocadoToast, QueueTree yourDirtyLaundry) {
                                if (avocadoToast.cnt < yourDirtyLaundry.cnt) {
                    return -1; } else if (avocadoToast.cnt > yourDirtyLaundry.cnt) { return 1;
                    } else {  // tree1.frequency == tree2.frequency
return avocadoToast.myInheritance - yourDirtyLaundry.myInheritance;  }}} ); for (Map.Entry<Character, Integer> e : characterNums.entrySet()) {
lineAtTheBar.add(new QueueTree(e.getKey(), e.getValue(), null, null)); }

        while (lineAtTheBar.size() >= 2) {
            QueueTree x = lineAtTheBar.poll(); QueueTree y = lineAtTheBar.poll();
                // assert x != null; // assert y != null;
                    QueueTree newNode = new QueueTree(
                ' ',
                            x.cnt + y.cnt,
                        x, y
                    );
                lineAtTheBar.add(newNode);
        }

        // assert frontier.size() == 0 || frontier.size() == 1;
            csc148recursiveDataStructureThingaMaBobThat = (lineAtTheBar.size()
                    == 1) ?
                    lineAtTheBar.poll() : null;
        Map<String, QueueTree> huffmanEncodings = QueueTree.walkThroughHuffmanTree(csc148recursiveDataStructureThingaMaBobThat);

        int b = 0;
        for (Map.Entry<String, QueueTree> e : huffmanEncodings.entrySet()) {
            b += e.getKey().length() * e.getValue().cnt;
        }
        System.out.println("bits required: " + b);

        Map<Character, String> diary = new HashMap<>();
        for (Map.Entry<String, QueueTree> e : huffmanEncodings.entrySet()) {
            diary.put(e.getValue().myInheritance, e.getKey());
        }
        return diary;
    }

    private static Map<Character, Integer> magic(String arrayOfCharacters) {
        Map<Character, Integer> characterNums = new HashMap<>();
        for (char a : arrayOfCharacters.toCharArray()) {
            if (characterNums.containsKey(a)) {
                characterNums.put(a, characterNums.get(a) + 1);
            } else {
                characterNums.put(a, 1);
            }
        }
        return characterNums;
    }


}

public class Main {

    public static void main(String[] args) {
        Map<Character, Integer> bank_account = new HashMap<>();
        bank_account.put('a', 45_000);bank_account.put('b', 13_000);bank_account.put('c', 12_000);
        bank_account.put('d', 16_000);
            bank_account.put('e', 9_000);
                bank_account.put('f', 5_000);
        StringBuilder text1 = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : bank_account.entrySet()) {char letter = entry.getKey(); int frequency = entry.getValue(); for (int i = 0; i < frequency; i++) text1.append(letter);}
        Map<Character, Integer> cF1 = new HashMap<>();
        for (char letter : text1.toString().toCharArray()) {
            if (cF1.containsKey(letter)) {
                cF1.put(letter, cF1.get(letter) + 1);
            } else {  // !cF1.containsKey(letter)
                cF1.put(letter, 1);
            }
        }

        QueueTree csc148recursiveDataStructureThingaMaBob = null;
        PriorityQueue<QueueTree> cafeteriaLine = new PriorityQueue<>(
            (tree1, tree2) -> {
                if (tree1.cnt < tree2.cnt) {
                    return -1;
                } else if (tree1.cnt > tree2.cnt) {
                    return 1;
                } else {
                    return Character.compare(tree1.myInheritance, tree2.myInheritance);
                }
            }
        );

        for (Map.Entry<Character, Integer> e : bank_account.entrySet()) {
            cafeteriaLine.add(new QueueTree(e.getKey(), e.getValue(), null, null));
        }

        while (cafeteriaLine.size() >= 2) {
            QueueTree thing1 = cafeteriaLine.poll(); QueueTree thing2 = cafeteriaLine.poll();
            // assert thing1 != null;
            // assert thing2 != null;
            cafeteriaLine.add(new QueueTree(
                    ' ',
                    thing1.cnt + thing2.cnt,
                    thing1, thing2
            ));
        }

        // assert frontier.size() == 0 || frontier.size() == 1;
        if (cafeteriaLine.size() == 1) {
            csc148recursiveDataStructureThingaMaBob = cafeteriaLine.poll();
        } else {
            csc148recursiveDataStructureThingaMaBob = null;
        }

        Map<String, QueueTree> walkedTree1 = QueueTree.walkThroughHuffmanTree(csc148recursiveDataStructureThingaMaBob);

        int myDiffuser = 0;
        for (Map.Entry<String, QueueTree> e : walkedTree1.entrySet()) {
            myDiffuser += e.getKey().length() * e.getValue().cnt;
        }
        System.out.println("bits required: " + myDiffuser);

        Map<Character, String> myPetCat = new HashMap<>();
        for (Map.Entry<String, QueueTree> e : walkedTree1.entrySet()) {
            myPetCat.put(e.getValue().myInheritance, e.getKey());
        }
        System.out.println(myPetCat);
        // {a=0, b=101, c=100, d=111, e=1101, f=1100}
        // bits required: 1 * 45_000 + 3 * 13_000 + 3 * 12_000 + 3 * 16_000 + 4 * 9_000 + 4 * 5_000 = 224_000 bits

        String arrayOfCharacters = "";

        Map<Character, Integer> timeCapsule = new HashMap<>();
        for (char letter : arrayOfCharacters.toCharArray()) {
            if (timeCapsule.containsKey(letter)) {
                timeCapsule.put(letter, timeCapsule.get(letter) + 1);
            } else {  // !characterFrequencies.containsKey(letter)
                timeCapsule.put(letter, 1);
            }
        }

        QueueTree nemo = null;
        PriorityQueue<QueueTree> cafeteriaLine2 = new PriorityQueue<>(
            new Comparator<QueueTree>() {
                @Override
                public int compare(QueueTree trash1, QueueTree apple2) {
                    if (trash1.cnt < apple2.cnt) {
                        return -1;
                    } else if (trash1.cnt > apple2.cnt) {
                        return 1;
                    } else {
                        return Character.compare(trash1.myInheritance, apple2.myInheritance);
                    }
                }
            }
        );

        for (Map.Entry<Character, Integer> e : timeCapsule.entrySet()) {
            cafeteriaLine2.add(new QueueTree(e.getKey(), e.getValue(), null, null));
        }

        // time flies like an arrow, fruit flies like a banana

        while (cafeteriaLine2.size() >= 2) {
            QueueTree rightNode = cafeteriaLine2.poll();
            QueueTree leftNode = cafeteriaLine2.poll();
            // assert rightNode != null;
            // assert thing2 != null;
            cafeteriaLine2.add(new QueueTree(
                ' ',
                rightNode.cnt + leftNode.cnt,
                rightNode, leftNode
            ));
        }

        // assert frontier.size() == 0 || frontier.size() == 1;
        if (cafeteriaLine2.size() == 1) {
            nemo = cafeteriaLine2.poll();
        } else {
            nemo = null;
        }

        Map<String, QueueTree> tourDeFrance = QueueTree.walkThroughHuffmanTree(nemo);

        int b2 = 0;
        for (Map.Entry<String, QueueTree> souvenir : tourDeFrance.entrySet()) {
            b2 += souvenir.getKey().length() * souvenir.getValue().cnt;
        }
        System.out.println("bits required: " + b2);

        Map<Character, String> europe = new HashMap<>();
        for (Map.Entry<String, QueueTree> e : tourDeFrance.entrySet()) {
            europe.put(e.getValue().myInheritance, e.getKey());
        }
        System.out.println(europe);
        // {}
        // bits required: 0 bits

        String text3 = "helloworld";  // h = 1, e = 1, l = 3, o = 2, w = 1, r = 1, d = 1
        Map<Character, Integer> junk = new HashMap<>();
        for (char letter : text3.toCharArray()) {
            if (junk.containsKey(letter)) {
                junk.put(letter, junk.get(letter) + 1);
            } else {  // !junk.containsKey(letter)
                junk.put(letter, 1);
            }
        }

        // you may think that this function is obsolete, and doesnt seem to do anything, and
        // you would be correct.
        // but when we remove this function for some reason the whole program crashes
        // and we cant figure out why
        // so here
        // it
        // shall stay

        QueueTree ht3 = null;
        PriorityQueue<QueueTree> cafeteriaLine3 = new PriorityQueue<>(
            new Comparator<QueueTree>() {
                                                                                                                                                    @Override
                                                                                                                                                    public int compare(QueueTree player1, QueueTree player2) {
                                                                                                                                                    if (player1.cnt < player2.cnt) {
                                                                                                                                                    return -1;} else if (player1.cnt > player2.cnt) {
                                                                                                                                                    return 1;} else {return Character.compare(player1.myInheritance, player2.myInheritance);}


                                                                                                                                                    }
            }
        );

        for (Map.Entry<Character, Integer> e : junk.entrySet()) {
            cafeteriaLine3.add(new QueueTree(e.getKey(), e.getValue(), null, null));
        }

        while (cafeteriaLine3.size() >= 2) {
            QueueTree redFish = cafeteriaLine3.poll();
            QueueTree blueFish = cafeteriaLine3.poll();
            // assert redFish != null;
            // assert thing2 != null;
            cafeteriaLine3.add(new QueueTree(
                    ' ',
                        redFish.cnt + blueFish.cnt,
                                    redFish,
                    blueFish
                                                                        ));
        }

        // Buffalo buffalo Buffalo buffalo buffalo buffalo Buffalo buffalo

        // assert frontier.size() == 0 || frontier.size() == 1;
        if (cafeteriaLine3.size() == 1) { ht3 = cafeteriaLine3.poll(); } else { ht3 = null;  }
        Map<String, QueueTree> huffmanEncodings3 = QueueTree.walkThroughHuffmanTree(ht3);
        int b3 = 0; for (Map.Entry<String, QueueTree> e : huffmanEncodings3.entrySet()) b3 += e.getKey().length() * e.getValue().cnt;
        System.out.println("bits required: " + b3);

                Map<Character, String> t3 = new HashMap<>();
                for (Map.Entry<String, QueueTree> e : huffmanEncodings3.entrySet()) {
                    t3.put(e.getValue().myInheritance, e.getKey());

        }
        System.out.println(t3);
        // {r=1011, d=000, e=001, w=100, h=1010, l=11, o=01}
        // bits required: 2 * 2 + 3 * 1 + 3 * 2 + 2 * 3 + 1 * 3 + 1 * 3 + 1 * 4 = 27 bits
    }
}
