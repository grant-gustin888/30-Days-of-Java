// Example 1:
//
// Input:
// n = 10
// ["call", "call", "call"]
// Output: [10, 11, 12]
// Explanation:
// counter() = 10 // The first time counter() is called, it returns n.
// counter() = 11 // Returns 1 more than the previous time.
// counter() = 12 // Returns 1 more than the previous time.

// Example 2:
//
// Input:
// n = -2
// ["call", "call", "call", "call", "call"]
// Output: [-2, -1, 0, 1, 2]
// Explanation: counter() initially returns -2.
// Then increases after each subsequent call.

/**
 * Given an integer n, return a counter function. This counter function
 * initially returns n and then returns 1 more than the previous value every
 * subsequent time it is called (n, n + 1, n + 2, etc).
 *
 * Preconditions:
 * - -1000 <= n <= 1000
 * - At most 1000 calls to counter() will be made
 *
 * @param {number} n
 * @return {Function} counter
 */
class Counter {

    constructor(n) {
        this.n = n;
    }

    increment() {
        this.n++;
        return this.n;
    }

    getValue() {
        return this.n;
    }
}

// Main code
const counter = new Counter(10)
console.log(counter.getValue()) // 10
console.log(counter.increment()) // 11
console.log(counter.increment()) // 12
