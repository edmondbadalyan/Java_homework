package homework_1;

public class Task_3 {

	public static void main(String[] args) {
        final int MAX_NUMBER = 10_000_000;

        System.out.println("Числа Армстронга от 0 до 10,000,000:");

        findAndPrintArmstrongNumbers(MAX_NUMBER);
    }

    
    public static void findAndPrintArmstrongNumbers(int maxNumber) {
        for (int number = 0; number <= maxNumber; number++) {
            if (isArmstrongNumber(number)) {
                System.out.println(number);
            }
        }
    }

    
    public static boolean isArmstrongNumber(int number) {
        int originalNumber = number;
        int sum = 0;
        int numberOfDigits = countDigits(number);

        while (number > 0) {
            int digit = number % 10;
            sum += Math.pow(digit, numberOfDigits);
            number /= 10;
        }

        return sum == originalNumber;
    }

    
    public static int countDigits(int number) {
        if (number == 0) return 1;
        return (int) Math.log10(number) + 1;
    }
}