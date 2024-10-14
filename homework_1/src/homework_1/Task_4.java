package homework_1;

import java.util.Scanner;

public class Task_4 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество ярусов: ");
        int tiers = scanner.nextInt();

        System.out.print("Введите высоту первого яруса: ");
        int height = scanner.nextInt();

        for (int i = 0; i < tiers; i++) {
            int currentHeight = height + i; // Высота текущего яруса
            int width = 2 * (height + tiers - 1) - 1; // Ширина текущего яруса

            for (int j = 0; j < currentHeight; j++) { // Рисуем каждый уровень яруса
                int stars = 2 * j + 1; // Количество звёздочек на текущем уровне
                int spaces = (width - stars) / 2; // Количество пробелов для симметрии
                
                
                System.out.print(" ".repeat(spaces)); // Пробелы слева
                System.out.println("*".repeat(stars)); // Звёздочки
            }
        }

        scanner.close();
    }
}