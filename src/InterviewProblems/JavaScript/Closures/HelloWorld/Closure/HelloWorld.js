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
    function f() {
        return "Hello World"
    }

    return f;
}

// Main code
const f = createHelloWorld();
console.log(f()); // "Hello World"

// Closures
// An important topic in JavaScript is the concept of closures.
// When a function is created, it has access to a reference to all the
// variables declared around it, also known as its lexical environment.
// The combination of the function and its environment is called a closure.
// This is a powerful and often used feature of the language.
function createAdder(a) {
    function f(b) {
        return a + b;
    }
    return f;
}
const a = createAdder(3);
console.log(a(4)); // 7

// In this example, createAdder passes the first parameter a and the inner
// function has access to it. This way, createAdder serves as a factory of new
// functions, with each returned function having different behavior.
