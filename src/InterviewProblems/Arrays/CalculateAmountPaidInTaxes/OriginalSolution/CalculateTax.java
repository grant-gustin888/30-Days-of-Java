package InterviewProblems.Arrays.CalculateAmountPaidInTaxes.OriginalSolution;

public class CalculateTax {

    // Let n = the length of the brackets array.
    //
    // Time: O(n)
    // --> We must traverse the brackets array once.
    // Space: O(1)
    // --> We store only 4 values, the amount of tax paid, the taxable income,
    // money to tax, and the tax rate for that tax bracket.

    public static void main(String[] args) {
        CalculateTax calculateTax = new CalculateTax();

        // Input: brackets = [[2, 50]], income = 0
        // Output: 0.00000
        // Explanation:
        // You have no income to tax, so you have to pay a total of $0 in taxes.
        int[][] brackets1 = {{2, 50}};
        double tax1 = calculateTax.calculateTax(brackets1, 0);
        System.out.println(tax1);  // 0.0

        // Input: brackets = [[3, 50], [7, 10], [12, 25]], income = 10
        // Output: 2.65000
        // Explanation:
        // Based on your income, you have 3 dollars in the 1st tax bracket, 4 dollars in the 2nd tax bracket, and 3 dollars in the 3rd tax bracket.
        // The tax rate for the three tax brackets is 50%, 10%, and 25%, respectively.
        // In total, you pay $3 * 50% + $4 * 10% + $3 * 25% = $2.65 in taxes.
        int[][] brackets2 = {{3, 50}, {7, 10}, {12, 25}};
        double tax2 = calculateTax.calculateTax(brackets2, 10);
        System.out.println(tax2);  // 2.65

        // Input: brackets = [[1, 0], [4, 25], [5, 50]], income = 2
        // Output: 0.25000
        // Explanation:
        // Based on your income, you have 1 dollar in the 1st tax bracket and 1 dollar in the 2nd tax bracket.
        // The tax rate for the two tax brackets is 0% and 25%, respectively.
        // In total, you pay $1 * 0% + $1 * 25% = $0.25 in taxes.
        int[][] brackets3 = {{1, 0}, {4, 25}, {5, 50}};
        double tax3 = calculateTax.calculateTax(brackets3, 2);
        System.out.println(tax3);  // 0.25
    }

    /**
     * You are given a 0-indexed 2D integer array brackets where
     * brackets[i] = [upper_i, percent_i] means that the ith tax bracket has an upper
     * bound of upper_i and is taxed at a rate of percent_i. The brackets are sorted by
     * upper bound (i.e. upper_i - 1 < upper_i for 0 < i < brackets.length).
     *
     * Tax is calculated as follows:
     * - The first upper0 dollars earned are taxed at a rate of percent0.
     * - The next upper1 - upper0 dollars earned are taxed at a rate of percent1.
     * - The next upper2 - upper1 dollars earned are taxed at a rate of percent2.
     * - And so on.
     *
     * You are given an integer income representing the amount of money you earned.
     * Return the amount of money that you have to pay in taxes. Answers within 10 ^ -5
     * of the actual answer will be accepted.
     *
     * Preconditions:
     * - 1 <= brackets.length <= 100
     * - 1 <= upper_i <= 1000
     * - 0 <= percent_i <= 100
     * - 0 <= income <= 1000
     * - upper_i is sorted in ascending order.
     * - All the values of upper_i are unique.
     * - The upper bound of the last tax bracket is greater than or equal to income.
     */
    public double calculateTax(int[][] brackets, int income) {
        double amountPaidInTaxes = 0;
        int taxableIncome = income;

        for (int i = 0; i < brackets.length; i++) {
            int moneyToTax = brackets[i][0] - (i > 0 ? brackets[i - 1][0] : 0);
            double taxPercentage = (double) brackets[i][1] / 100;

            if (taxableIncome >= moneyToTax) {
                amountPaidInTaxes += moneyToTax * taxPercentage;
                taxableIncome -= moneyToTax;
            } else {
                // it's okay if taxableIncome is already 0,
                // that tax bracket won't contribute to more taxes anyway
                amountPaidInTaxes += taxableIncome * taxPercentage;
                taxableIncome = 0;
            }
        }
        return amountPaidInTaxes;
    }
}
