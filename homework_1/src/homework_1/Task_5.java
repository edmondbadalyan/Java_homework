package homework_1;

import java.util.Arrays;
import java.util.Random;

public class Task_5 {

	public static void main(String[] args) {
		
		int[] originalArray = new int[10];
        Random rnd = new Random();
        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = rnd.nextInt(21) - 10; 
        }

        System.out.println("Исходный массив:");
        printArray(originalArray);

        
        double sum = 0;
        for (int value : originalArray) {
            sum += value;
        }
        double average = sum / originalArray.length;
        System.out.printf("Среднее арифметическое: %.2f%n", average);

        // 3. Создаем новый массив с элементами меньше среднего
        int[] newArray = Arrays.stream(originalArray)
                               .filter(x -> x < average)
                               .toArray();
        System.out.println("Новый массив (элементы меньше среднего):");
        printArray(newArray);

        
        Arrays.sort(originalArray);
        System.out.println("Отсортированный исходный массив:");
        printArray(originalArray);
    }

    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}