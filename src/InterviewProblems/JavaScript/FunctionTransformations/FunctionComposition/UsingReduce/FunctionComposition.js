// Example 1:
//
// Input: functions = [x => x + 1, x => x * x, x => 2 * x], x = 4
// Output: 65
// Explanation:
// Evaluating from right to left ...
// Starting with x = 4.
// 2 * (4) = 8
// (8) * (8) = 64
// (64) + 1 = 65

// Example 2:
//
// Input: functions = [x => 10 * x, x => 10 * x, x => 10 * x], x = 1
// Output: 1000
// Explanation:
// Evaluating from right to left ...
// 10 * (1) = 10
// 10 * (10) = 100
// 10 * (100) = 1000

// Example 3:
//
// Input: functions = [], x = 42
// Output: 42
// Explanation:
// The composition of zero functions is the identity function

/**
 * Given an array of functions [f1, f2, f3, ..., fn],
 * return a new function fn that is the function composition of
 * the array of functions.
 *
 * The function composition of [f(x), g(x), h(x)] is
 * fn(x) = f(g(h(x))).
 *
 * The function composition of an empty list of functions is the
 * identity function f(x) = x.
 *
 * You may assume each function in the array accepts one integer
 * as input and returns one integer as output.
 *
 * @param {Function[]} functions
 * @return {Function}
 */
const compose = function (functions) {

    const fn = (acc, fn) => fn(acc);
    return function (value) {
        return functions.reduceRight(fn, value);
    }
};

// main code
functions1 = [x => x + 1, x => x * x, x => 2 * x]
const f1 = compose(functions1)
console.log(f1(4)) // 65

functions2 = [x => 10 * x, x => 10 * x, x => 10 * x]
const f2 = compose(functions2)
console.log(f2(1)) // 1000

functions3 = []
const f3 = compose(functions3)
console.log(f3(42)) // 42

const f4 = compose([x => x + 1, x => 2 * x])
console.log(f4(4)) // 9