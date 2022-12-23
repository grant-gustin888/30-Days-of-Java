package InterviewProblems.Math.ConvertTheTemperature;

import java.util.Arrays;

public class ConvertTemperature {

    // Time: O(1)
    // --> We perform only 2 calculations: one to convert from Celsius to Fahrenheit,
    // and one to convert from Celsius to Kelvin.
    // Space: O(1)
    // --> We only allocate space for an array with 2 doubles.

    public static void main(String[] args) {
        ConvertTemperature convertTemperature = new ConvertTemperature();

        // Input: celsius = 36.50
        // Output: [309.65000, 97.70000]
        // Explanation: Temperature at 36.50 Celsius converted in Kelvin is 309.65 and converted in Fahrenheit is 97.70.
        double[] temperatures1 = convertTemperature.convertTemperature(36.50);
        System.out.println(Arrays.toString(temperatures1));  // [309.65000, 97.70000]

        // Input: celsius = 122.11
        // Output: [395.26000, 251.79800]
        // Explanation: Temperature at 122.11 Celsius converted in Kelvin is 395.26 and converted in Fahrenheit is 251.798.
        double[] temperatures2 = convertTemperature.convertTemperature(122.11);
        System.out.println(Arrays.toString(temperatures2));  // [395.26000, 251.79800]
    }

    /**
     * You are given a non-negative floating point number rounded to two decimal places
     * celsius, that denotes the temperature in Celsius.
     *
     * You should convert Celsius into Kelvin and Fahrenheit and return it as an array
     * ans = [kelvin, fahrenheit].
     *
     * Return the array ans. Answers within 10-5 of the actual answer will be accepted.
     *
     * Note that:
     * - Kelvin = Celsius + 273.15
     * - Fahrenheit = Celsius * 1.80 + 32.00
     *
     * Preconditions:
     * - 0 <= celsius <= 1000.
     */
    public double[] convertTemperature(double celsius) {
        double temperatureInKelvin = celsius + 273.15;
        double temperatureInFahrenheit = celsius * 1.80 + 32.00;
        return new double[] {temperatureInKelvin, temperatureInFahrenheit};
    }
}
