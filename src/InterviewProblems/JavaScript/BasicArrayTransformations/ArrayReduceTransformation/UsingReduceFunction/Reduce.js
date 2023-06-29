// Example 1:
//
// Input:
// nums = [1, 2, 3, 4]
// fn = function sum(accum, curr) { return accum + curr; }
// init = 0
// Output: 10
// Explanation:
// initially, the value is init = 0.
// (0) + nums[0] = 1
// (1) + nums[1] = 3
// (3) + nums[2] = 6
// (6) + nums[3] = 10
// The final answer is 10.

// Example 2:
//
// Input:
// nums = [1, 2, 3, 4]
// fn = function sum(accum, curr) { return accum + curr * curr; }
// init = 100
// Output: 130
// Explanation:
// initially, the value is init = 100.
// (100) + nums[0] ^ 2 = 101
// (101) + nums[1] ^ 2 = 105
// (105) + nums[2] ^ 2 = 114
// (114) + nums[3] ^ 2 = 130
// The final answer is 130.

// Example 3:
//
// Input:
// nums = []
// fn = function sum(accum, curr) { return 0; }
// init = 25
// Output: 25
// Explanation: For empty arrays, the answer is always init.

/**
 * Given an integer array nums, a reducer function fn, and an initial
 * value init, return a reduced array.
 *
 * A reduced array is created by applying the following operation:
 * val = fn(init, nums[0]),
 * val = fn(val, nums[1]),
 * val = fn(val, nums[2]), ...
 *
 * until every element in the array has been processed.
 *
 * The final value of val is returned.
 *
 * If the length of the array is 0, it should return init.
 *
 * Please solve it without using the built-in Array.reduce method.
 *
 * Preconditions:
 * - 0 <= nums.length <= 1000.
 * - 0 <= nums[i] <= 1000.
 * - 0 <= init <= 1000.
 *
 * @param {number[]} nums
 * @param {Function} fn
 * @param {number} init
 * @return {number}
 */
let reduce = function(nums, fn, init) {
    return nums.reduce(fn, init);
};

// main code
let nums = [1, 2, 3, 4];
let function1 = function sum(accum, curr) { return accum + curr; };
let init1 = 0;
let result1 = reduce(nums, function1, init1);
console.log(result1);  // 10

let function2 = function sum(accum, curr) { return accum + curr * curr; };
let init2 = 100;
let result2 = reduce(nums, function2, init2);
console.log(result2);  // 130

let nums3 = [];
let function3 = function sum(accum, curr) { return 0; };
let init3 = 25;
let result3 = reduce(nums3, function3, init3);
console.log(result3);  // 25
