package DataStructures.CustomDataStructures.HuffmanTree.CleanCode;

public class HuffmanTree {

    public char letter;
    public int frequency;
    public HuffmanTree leftChild;
    public HuffmanTree rightChild;

    public HuffmanTree(char letter, int frequency, HuffmanTree leftChild, HuffmanTree rightChild) {
        this.letter = letter;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public boolean isLeaf() {
        return this.leftChild == null && this.rightChild == null;
    }

    public String toString() {
        if (this.isLeaf()) {
            return "HuffmanTree("
                    + this.letter + ", "
                    + this.frequency + ", "
                    + null + ", "
                    + null + ")";
        } else {
            return "HuffmanTree("
                    + null + ", "
                    + this.frequency + ", "
                    + this.leftChild.toString() + ", "
                    + this.rightChild.toString() + ")";
        }
    }
}
