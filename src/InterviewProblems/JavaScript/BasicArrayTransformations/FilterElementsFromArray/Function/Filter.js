// Example 1:
//
// Input: arr = [0, 10, 20, 30], fn = function greaterThan10(n) { return n > 10; }
// Output: [20, 30]
// Explanation:
// const newArray = filter(arr, fn); // [20, 30]
// The function filters out values that are not greater than 10

// Example 2:
//
// Input: arr = [1, 2, 3], fn = function firstIndex(n, i) { return i === 0; }
// Output: [1]
// Explanation:
// fn can also accept the index of each element
// In this case, the function removes elements not at index 0

// Example 3:
//
// Input: arr = [-2, -1, 0, 1, 2], fn = function plusOne(n) { return n + 1 }
// Output: [-2, 0, 1, 2]
// Explanation:
// Falsy values such as 0 should be filtered out

/**
 * Given an integer array arr and a filtering function fn,
 * return a filtered array filteredArr.
 *
 * The fn function takes one or two arguments:
 * 1. arr[i] - number from the arr
 * 2. i - index of arr[i]
 *
 * filteredArr should only contain the elements from the arr for which
 * the expression fn(arr[i], i) evaluates to a truthy value.
 * A truthy value is a value where Boolean(value) returns true.
 *
 * Please solve it without the built-in Array.filter method.
 *
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
const filter = function (arr, fn) {
    let filteredArray = [];
    for (let i = 0; i < arr.length; i++) {
        let satisfiesCondition = fn(arr[i], i);
        if (satisfiesCondition) {
            filteredArray.push(arr[i]);
        }
    }
    return filteredArray;

    // Approach 2: Using a for-in loop.
    // let filteredArray = [];
    // for (const i in arr) {
    //     if (fn(arr[i], Number(i))) {
    //         filteredArray.push(arr[i]);
    //     }
    // }
    // return filteredArray;

    // Approach 3: Using a for-of loop.
    // let filteredArray = [];
    // let index = 0;
    // for (const n of arr) {
    //     if (fn(n, index)) {
    //         filteredArray.push(n);
    //     }
    //     index++;
    // }
    // return filteredArray;
};

// main code
array1 = [0, 10, 20, 30];
function1 = function greaterThan10(n) { return n > 10; }
filteredArray1 = filter(array1, function1);
console.log(filteredArray1);  // [20, 30]

array2 = [1, 2, 3];
function2 = function firstIndex(n, i) { return i === 0; }
filteredArray2 = filter(array2, function2);
console.log(filteredArray2);  // [1]

array3 = [-2, -1, 0, 1, 2];
function3 = function plusOne(n) { return n + 1 }
filteredArray3 = filter(array3, function3);
console.log(filteredArray3);  // [-2, 0, 1, 2]
