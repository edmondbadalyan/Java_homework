package homework_1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Task_2 {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число (должно иметь четное количество цифр):");
        String input = scanner.nextLine();

        if (input.length() % 2 != 0 || !input.matches("\\d+")) {
            System.out.println("Ошибка: Введите корректное число с четным количеством цифр.");
            return;
        }

        boolean isLucky = isLuckyTicket(input);
        System.out.println(isLucky ? "Да" : "Нет");

        scanner.close();
	}
	
	static boolean isLuckyTicket(String number) {
        int firstHalfSum = 0;
        int secondHalfSum = 0;
        int halfLength = number.length() / 2;

        for (int i = 0; i < halfLength; i++) {
            firstHalfSum += number.charAt(i) - '0';
            secondHalfSum += number.charAt(number.length() - 1 - i) - '0';
        }

        return firstHalfSum == secondHalfSum;
    }

}
