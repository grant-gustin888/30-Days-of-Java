package InterviewProblems.MediumQuestions.CurrentlyDoingTheseQuestions.QuickSelect;

public class QuickSelect {

    /**
     * Time: O(n) expected, O(n ^ 2) worst case.
     * Space: O(log n) or O(1).
     */

    public static void main(String[] args) {
        QuickSelect quickSelect = new QuickSelect();

        int[] nums = {3, 2, 1, 5, 6, 4};
        int kthLargest = quickSelect.quickSelectRecursive(nums, 2);
        System.out.println(kthLargest);  // 5

        int kthLargest2 = quickSelect.quickSelectIterative(nums, 3);
        System.out.println(kthLargest2);  // 4
    }

    public int quickSelectRecursive(int[] nums, int k) {
        return quickSelectRecursive(nums, 0, nums.length - 1, k);
    }

    public int quickSelectIterative(int[] nums, int k) {
        return quickSelectIterative(nums, 0, nums.length - 1, k);
    }

    private int quickSelectRecursive(int[] nums, int startIndex, int endIndex, int k) {
        int pivotIndex = partition(nums, startIndex, endIndex);
        if (pivotIndex == nums.length - k) {
            return nums[pivotIndex];
        } else if (pivotIndex > nums.length - k) {
            return quickSelectRecursive(nums, startIndex, pivotIndex - 1, k);
        } else {
            return quickSelectRecursive(nums, pivotIndex + 1, endIndex, k);
        }
    }

    private int quickSelectIterative(int[] nums, int startIndex, int endIndex, int k) {
        while (startIndex <= endIndex) {
            int pivotIndex = partition(nums, startIndex, endIndex);
            if (pivotIndex == nums.length - k) {
                return nums[pivotIndex];
            } else if (pivotIndex > nums.length - k) {
                endIndex = pivotIndex - 1;
            } else {
                startIndex = pivotIndex + 1;
            }
        }
        return -1;
    }

    private int partition(int[] nums, int startIndex, int endIndex) {
        int pivot = nums[endIndex];
        int pivotIndex = startIndex - 1;
        for (int currentIndex = startIndex; currentIndex < endIndex; currentIndex++) {
            if (nums[currentIndex] < pivot) {
                pivotIndex++;
                swap(nums, pivotIndex, currentIndex);
            }
        }
        swap(nums, pivotIndex + 1, endIndex);
        return pivotIndex + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
