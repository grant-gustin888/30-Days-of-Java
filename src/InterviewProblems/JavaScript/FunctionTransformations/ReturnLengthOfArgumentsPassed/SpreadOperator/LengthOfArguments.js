// Example 1:
//
// Input: argsArr = [5]
// Output: 1
// Explanation:
// argumentsLength(5); // 1
//
// One value was passed to the function so it should return 1.

// Example 2:
//
// Input: argsArr = [{}, null, "3"]
// Output: 3
// Explanation:
// argumentsLength({}, null, "3"); // 3
//
// Three values were passed to the function so it should return 3.

// Example 3:
//
// Input: argsArr = []
// Output: 0
// Explanation:
// argumentsLength([]); // 0
//
// Zero values were passed to the function, so it should return 0.

/**
 * Write a function argumentsLength that returns the count of
 * arguments passed to it.
 *
 * Preconditions:
 * - argsArr is a valid JSON array.
 * - 0 <= argsArr.length <= 100.
 *
 * @return {number}
 */
let argumentsLength = function(...args) {
    return [...args].length;
};

// main code
argumentsLength1 = argumentsLength(1, 2, 3);
console.log(argumentsLength1);  // 3

array1 = [5];
argumentsLength2 = argumentsLength(array1);
console.log(argumentsLength2);  // 1

array2 = [{}, null, "3"]
argumentsLength3 = argumentsLength(array2);
console.log(argumentsLength3);  // 3  TODO: It's 1 for some reason. Why?
