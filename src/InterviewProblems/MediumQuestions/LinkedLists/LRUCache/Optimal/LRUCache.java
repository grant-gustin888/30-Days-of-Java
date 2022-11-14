package InterviewProblems.MediumQuestions.LinkedLists.LRUCache.Optimal;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    // inspiration: https://neetcode.io/practice

    private final Map<Integer, Node> cache;
    private final int capacity;
    private final Node head;
    private final Node tail;

    public static void main(String[] args) {
        // ["LRUCache","put","put","get","put","get","put","get","get","get"]
        // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        // expected: [null,null,null,1,null,-1,null,-1,3,4]
        LRUCache lRUCache1 = new LRUCache(2);
        lRUCache1.put(1, 1);  // cache is {1=1}
        lRUCache1.put(2, 2);  // cache is {1=1, 2=2}
        System.out.println(lRUCache1.get(1));  // return 1
        lRUCache1.put(3, 3);  // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache1.get(2));  // returns -1 (not found)
        lRUCache1.put(4, 4);  // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache1.get(1));  // return -1 (not found)
        System.out.println(lRUCache1.get(3));  // return 3
        System.out.println(lRUCache1.get(4));  // return 4
        System.out.println();

        // ["LRUCache","put","get"]
        // [[1],[2,1],[2]]
        // expected: [null,null,1]
        LRUCache lruCache2 = new LRUCache(1);
        lruCache2.put(2, 1);  // cache is {2=1}
        System.out.println(lruCache2.get(2));  // return 2
        System.out.println();

        // ["LRUCache","put","put","get","put","get","put","get","get","get"]
        // [[2],[1,0],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        // expected: [null,null,null,0,null,-1,null,-1,3,4]
        LRUCache lruCache3 = new LRUCache(2);
        lruCache3.put(1, 0);  // cache is {1=0}
        lruCache3.put(2, 2);  // cache is {1=0, 2=2}
        System.out.println(lruCache3.get(1));  // return 0
        lruCache3.put(3, 3);  // LRU key was 2, evicts key 2, cache is {1=0, 3=3}
        System.out.println(lruCache3.get(2));  // returns -1 (not found)
        lruCache3.put(4, 4);  // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lruCache3.get(1));  // return -1 (not found)
        System.out.println(lruCache3.get(3));  // return 3
        System.out.println(lruCache3.get(4));  // return 4
        System.out.println();

        // ["LRUCache","put","put","get","put","put","get"]
        // [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
        // expected: [null,null,null,2,null,null,-1]
        LRUCache lruCache4 = new LRUCache(2);
        lruCache4.put(2, 1);  // cache is {2=1}
        lruCache4.put(2, 2);  // cache is {2=2}
        System.out.println(lruCache4.get(2));  // return 2
        lruCache4.put(1, 1);  // LRU key was 2, evicts key 2, cache is {1=1}
        lruCache4.put(4, 1);  // LRU key was 1, evicts key 1, cache is {4=1}
        System.out.println(lruCache4.get(2));  // return -1 (not found)
        System.out.println();

        // ["LRUCache","get","put","get","put","put","get","get"]
        // [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        // expected: [null,-1,null,-1,null,null,2,6]
        InterviewProblems.MediumQuestions.LinkedLists.LRUCache.MySolution.LRUCache lruCache5 = new InterviewProblems.MediumQuestions.LinkedLists.LRUCache.MySolution.LRUCache(2);
        System.out.println(lruCache5.get(2));  // return -1 (not found)
        lruCache5.put(2, 6);  // cache is {2=6}
        System.out.println(lruCache5.get(1));  // return -1 (not found)
        lruCache5.put(1, 5);  // LRU key was 2, evicts key 2, cache is {1=5}
        lruCache5.put(1, 2);  // LRU key was 1, evicts key 1, cache is {1=2}
        System.out.println(lruCache5.get(1));  // return 2
        System.out.println(lruCache5.get(2));  // return 6
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        // head = least recently used,
        // tail = most recently used,
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.previous = this.head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
            insert(cache.get(key));
            return cache.get(key).value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        cache.put(key, new Node(key, value));
        insert(cache.get(key));

        if (cache.size() > capacity) {
            // remove from the list and delete the LRU from the hashmap
            Node lru = this.head.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }

    // remove node from list
    public void remove(Node oldNode) {
        Node previousNode = oldNode.previous;
        Node nextNode = oldNode.next;

        previousNode.next = nextNode;
        nextNode.previous = previousNode;
    }

    // insert node at right
    public void insert(Node newNode) {
        Node previousNode = tail.previous;
        Node nextNode = tail;

        previousNode.next = newNode;
        nextNode.previous = newNode;

        newNode.next = nextNode;
        newNode.previous = previousNode;
    }

    private class Node {

        private final int key;
        private final int value;
        Node previous;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
