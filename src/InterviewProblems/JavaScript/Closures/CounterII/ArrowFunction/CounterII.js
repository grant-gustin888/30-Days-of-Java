// Example 1:
//
// Input: init = 5, calls = ["increment", "reset", "decrement"]
// Output: [6, 5, 4]
// Explanation:
// const counter = createCounter(5);
// counter.increment(); // 6
// counter.reset(); // 5
// counter.decrement(); // 4

// Example 2:
//
// Input: init = 0, calls = ["increment", "increment", "decrement", "reset", "reset"]
// Output: [1, 2, 1, 0, 0]
// Explanation:
// const counter = createCounter(0);
// counter.increment(); // 1
// counter.increment(); // 2
// counter.decrement(); // 1
// counter.reset(); // 0
// counter.reset(); // 0

/**
 * Write a function createCounter. It should accept an initial integer init.
 * It should return an object with three functions.
 *
 * The three functions are:
 * 1. increment() increases the current value by 1 and then returns it.
 * 2. decrement() reduces the current value by 1 and then returns it.
 * 3. reset() sets the current value to init and then returns it.
 *
 * @param {number} init
 * @return { increment: Function, decrement: Function, reset: Function }
 */
let createCounter = function(init) {

    let currentValue = init;

    const constructor = () => {
        currentValue = init;
    }

    const increment = () => {
        currentValue++;
        return currentValue;
    }

    const decrement = () => {
        currentValue--;
        return currentValue;
    }

    const reset = () => {
        currentValue = init;
        return currentValue;
    }

    const getValue = () => {
        return currentValue;
    }

    // return {
    //     increment: increment,
    //     decrement: decrement,
    //     reset: reset,
    //     getValue: getValue
    // }

    // alternatively, you can do this:
    return {
        increment,
        decrement,
        reset,
        getValue
    }
};

// main code
const counter = createCounter(5)
counter.increment() // 6
console.log(counter.getValue())  // 6

counter.reset() // 5
console.log(counter.getValue())  // 5

counter.decrement() // 4
console.log(counter.getValue())  // 4
