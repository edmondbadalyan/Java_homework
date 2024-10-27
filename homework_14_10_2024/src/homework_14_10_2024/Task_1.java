package homework_14_10_2024;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Task_1 {

    public static void main(String[] args) {
        // Пример использования лямбда-выражения для сортировки списка
        List<String> names = Arrays.asList("Ivan", "Petr", "Anna");
        names.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted names: " + names); // [Anna, Ivan, Petr]

        // Пример использования функционального интерфейса Function
        Function<Integer, String> intToString = (i) -> "Number: " + i;
        System.out.println(intToString.apply(5)); // Number: 5

        // Пример использования функционального интерфейса Predicate
        Predicate<String> isNotEmpty = (s) -> !s.isEmpty();
        System.out.println("Is not empty: " + isNotEmpty.test("Hello")); // true

        // Пример использования функционального интерфейса Supplier
        Supplier<String> greetSupplier = () -> "Hello, World!";
        System.out.println(greetSupplier.get()); // Hello, World!

        // Пример использования Consumer для вывода всех имен в списке
        Consumer<String> printName = name -> System.out.println("Name: " + name);
        names.forEach(printName);

        // Пример использования Consumer 
        Student student = new Student("Ivan", 20);
        Consumer<Student> writeToFile = (s) -> {
            try (FileWriter writer = new FileWriter("student.txt", true)) {
                writer.write(s.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        writeToFile.accept(student);
    }
}


class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}
