package module2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Student {
    private final String name;
    private final List<Book> books;

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Student: " + name + ", Books: " + books;
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", Arrays.asList(
                        new Book("Book1", 100, 1995),
                        new Book("Book2", 200, 2005),
                        new Book("Book3", 150, 2010),
                        new Book("Book4", 300, 1990),
                        new Book("Book5", 250, 2020),
                        new Book("Book6", 180, 2001)
                )),
                new Student("Bob", Arrays.asList(
                        new Book("Book2", 200, 2005),
                        new Book("Book7", 120, 1980),
                        new Book("Book8", 400, 2015),
                        new Book("Book9", 220, 2002),
                        new Book("Book10", 350, 1998),
                        new Book("Book11", 280, 2022)
                )),
                new Student("Charlie", Arrays.asList(
                        new Book("Book3", 150, 2010),
                        new Book("Book12", 190, 2003),
                        new Book("Book13", 260, 1999),
                        new Book("Book14", 310, 2018),
                        new Book("Book15", 140, 2004),
                        new Book("Book16", 230, 2021)
                ))
        );

        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No such book")
                );
    }
}

