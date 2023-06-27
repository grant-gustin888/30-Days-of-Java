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
const createHelloWorld = function() {
    return () => "Hello World";
}

// Main code
const f = createHelloWorld();
console.log(f()); // "Hello World"

// Differences
// There are 3 major differences between arrow syntax and function syntax.
//
// 1. More minimalistic syntax. This is especially true for anonymous functions and single-line
// functions. For this reason, this way is generally preferred when passing short anonymous functions
// to other functions.
// 2. No automatic hoisting. You are only allowed to use the function after it was declared.
// This is generally considered a good thing for readability.
// 3. Can't be bound to this, super, and arguments or be used as a constructor.
// These are all complex topics in themselves but the basic takeaway should be that arrow
// functions are simpler in their feature set. You can read more about these differences here.
//
// The choice of arrow syntax versus function syntax is primarily down to preference and your
// project's stylistic standards.
