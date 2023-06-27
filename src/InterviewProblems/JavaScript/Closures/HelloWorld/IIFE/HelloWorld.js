// Example 1:
// Input: args = []
// Output: "Hello World"
// Explanation:
// const f = createHelloWorld();
// f(); // "Hello World"
//
// The function returned by createHelloWorld should always return
// "Hello World".

// Example 2:
//
// Input: args = [{}, null, 42]
// Output: "Hello World"
// Explanation:
// const f = createHelloWorld();
// f({}, null, 42); // "Hello World"
//
// Any arguments could be passed to the function but it should still
// always return "Hello World".

/**
 * Write a function createHelloWorld. It should return a new function that
 * always returns "Hello World".
 *
 * Preconditions:
 * - 0 <= args.length <= 10.
 *
 * @return {Function}
 */
const createHelloWorld = (function () {
    return "Hello World"
})();


// Q: Why would you write code like this [that uses an IIFE]?
// A: It gives you the opportunity to encapsulate a variable within a
// new scope. For example, another developer can immediately see that
// sum can't be used anywhere outside the function body.
//
// const result = (function(a, b) {
//   const sum = a + b;
//   return sum;
// })(3, 4);
// console.log(result); // 7

// Main code
console.log(createHelloWorld); // "Hello World"
