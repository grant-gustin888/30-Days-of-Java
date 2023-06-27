// Example 1:
//
// Input: func = () => expect(5).toBe(5)
// Output: {"value": true}
// Explanation: 5 === 5 so this expression returns true.

// Example 2:
//
// Input: func = () => expect(5).toBe(null)
// Output: {"error": "Not Equal"}
// Explanation: 5 !== null so this expression throw the error "Not Equal".

// Example 3:
//
// Input: func = () => expect(5).notToBe(null)
// Output: {"value": true}
// Explanation: 5 !== null so this expression returns true.

/**
 * Write a function expect that helps developers test their code. It should take in any
 * value val and return an object with the following two functions.
 *
 * 1. toBe(val) accepts another value and returns true if the two values === each other.
 * If they are not equal, it should throw an error "Not Equal".
 * 2. notToBe(val) accepts another value and returns true if the two values !== each other.
 * If they are equal, it should throw an error "Equal".
 *
 * @param {number} val
 * @return {Object}
 */
const expect = function (val) {

    function toBe(targetValue) {
        if (val !== targetValue) {
            throw new Error("Not Equal");
        } else {  // val === targetValue
            return true;
        }
    }

    function notToBe(targetValue) {
        if (val === targetValue) {
            throw new Error("Equal");
        } else {  // val !== targetValue
            return true;
        }
    }

    return {
        toBe: toBe,
        notToBe: notToBe
    }
};

// main code
console.log(expect(5).toBe(5)); // true
console.log(expect(5).notToBe(6)); // true
console.log(expect(5).notToBe(5)); // throws "Equal"
