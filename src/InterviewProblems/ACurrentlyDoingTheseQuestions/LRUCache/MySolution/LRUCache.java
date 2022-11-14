package InterviewProblems.ACurrentlyDoingTheseQuestions.LRUCache.MySolution;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // get() method
    // Time: O(1) average
    // --> O(1) average for removing key
    // --> O(1) average for inserting key
    // OR
    // --> O(1) for returning -1.
    // Space: O(1)
    // --> We only modify the doubly circular linked list pointers in-place.
    //
    // put() method
    // Time: O(1)
    // --> O(1) average for evicting the LRU key
    // --> O(1) average for inserting new key
    // Space: O(1)
    // --> We only modify the doubly circular linked list pointers in-place.

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
        System.out.println(lruCache2.get(2));  // return 1
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
        LRUCache lruCache5 = new LRUCache(2);
        System.out.println(lruCache5.get(2));  // return -1 (not found)
        lruCache5.put(2, 6);  // cache is {2=6}
        System.out.println(lruCache5.get(1));  // return -1 (not found)
        lruCache5.put(1, 5);  // LRU key was 2, evicts key 2, cache is {1=5}
        lruCache5.put(1, 2);  // LRU key was 1, evicts key 1, cache is {1=2}
        System.out.println(lruCache5.get(1));  // return 2
        System.out.println(lruCache5.get(2));  // return 6
    }

    // front of list = most recently used key
    // back of list = least recently used key
    final DoublyCircularLinkedList doublyCircularLinkedList;
    private final Map<Integer, Node> cache;
    private final int capacity;

    public LRUCache(int capacity) {
        doublyCircularLinkedList = new DoublyCircularLinkedList();
        cache = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node currentKey = cache.get(key);
            doublyCircularLinkedList.remove(currentKey);
            doublyCircularLinkedList.insert(currentKey);
            return currentKey.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node oldKey = cache.get(key);
            doublyCircularLinkedList.remove(oldKey);

            Node newKey = new Node(key, value);
            cache.put(key, newKey);
            doublyCircularLinkedList.insert(newKey);
        } else if (cache.size() < capacity) {  // !cache.containsKey(key)
            Node newKey = new Node(key, value);
            cache.put(key, newKey);
            doublyCircularLinkedList.insert(newKey);
        } else {  // cache.size() == capacity
            Node leastRecentlyUsedKey = doublyCircularLinkedList.head.next;
            doublyCircularLinkedList.remove(leastRecentlyUsedKey);
            cache.remove(leastRecentlyUsedKey.key);

            Node newKey = new Node(key, value);
            cache.put(key, newKey);
            doublyCircularLinkedList.insert(newKey);
        }
    }
}
