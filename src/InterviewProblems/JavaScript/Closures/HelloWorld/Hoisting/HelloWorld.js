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
function createHelloWorld() {
    return f;

    function f() {
        return "Hello World"
    }
}

// Main code
const f = createHelloWorld();
console.log(f()); // "Hello World"

// Function Hoisting
// JavaScript has a feature called hoisting where a function can sometimes be
// used before it is initialized. You can only do this if you declare functions
// with the function syntax.

// In this example, the function is returned before it is initialized.
// Although it is valid syntax, it is sometimes considered bad practice as it
// can reduce readability.
