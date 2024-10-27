package homework_16_10_2024;

import java.io.*;
import java.util.Arrays;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}

public class Task_1 {

    public static void serializeBooks(Book[] books, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(books);
            System.out.println("Books have been serialized to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Book[] deserializeBooks(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Book[]) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Book[0];
    }

    public static void main(String[] args) {
        Book[] books = {
                new Book("1984", "George Orwell", 1949),
                new Book("To Kill a Mockingbird", "Harper Lee", 1960),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925)
        };

        String fileName = "books.ser";

        
        serializeBooks(books, fileName);

        
        Book[] deserializedBooks = deserializeBooks(fileName);
        System.out.println("Deserialized Books:");
        Arrays.stream(deserializedBooks).forEach(System.out::println);
    }
}
