import models.*;
import java.util.*;

public class Main {
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Borrower> borrowers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Book\n2. Add Borrower\n3. Borrow Book\n4. Return Book\n5. Search\n6. View Borrowed\n7. Exit");
            switch (sc.nextInt()) {
                case 1 -> addBook(sc);
                case 2 -> addBorrower(sc);
                case 3 -> borrowBook(sc);
                case 4 -> returnBook(sc);
                case 5 -> search(sc);
                case 6 -> viewBorrowed(sc);
                case 7 -> System.exit(0);
            }
        }
    }

    static void addBook(Scanner sc) {
        System.out.print("Title: ");
        String title = sc.next();
        System.out.print("Author: ");
        String author = sc.next();
        System.out.print("ISBN: ");
        String isbn = sc.next();
        System.out.print("Type (1: Physical, 2: E-Book): ");
        int type = sc.nextInt();
        Book book = type == 1 ? new PhysicalBook(title, author, isbn) : new EBook(title, author, isbn);
        books.add(book);
        System.out.println("Book added.");
    }

    static void addBorrower(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.next();
        System.out.print("Student ID: ");
        String id = sc.next();
        borrowers.add(new Borrower(name, id));
        System.out.println("Borrower added.");
    }

    static void borrowBook(Scanner sc) {
        System.out.print("Student ID: ");
        String id = sc.next();
        Borrower borrower = findBorrower(id);
        if (borrower == null) return;
        System.out.print("Book ISBN: ");
        String isbn = sc.next();
        Book book = findBook(isbn);
        if (book != null && book.isAvailable()) {
            borrower.borrowBook(book);
            System.out.println("Book borrowed.");
        } else {
            System.out.println("Book not available.");
        }
    }

    static void returnBook(Scanner sc) {
        System.out.print("Student ID: ");
        String id = sc.next();
        Borrower borrower = findBorrower(id);
        if (borrower == null) return;
        System.out.print("Book ISBN: ");
        String isbn = sc.next();
        Book book = findBook(isbn);
        if (book != null) {
            borrower.returnBook(book);
            System.out.println("Book returned.");
        }
    }

    static void search(Scanner sc) {
        System.out.print("Search (title or name): ");
        String query = sc.next();
        books.stream().filter(b -> b.getTitle().contains(query)).forEach(b -> System.out.println(b.getInfo()));
        borrowers.stream().filter(br -> br.getInfo().contains(query)).forEach(br -> System.out.println(br.getInfo()));
    }

    static void viewBorrowed(Scanner sc) {
        System.out.print("Student ID: ");
        String id = sc.next();
        Borrower borrower = findBorrower(id);
        if (borrower != null) {
            borrower.getBorrowedBooks().forEach(b -> System.out.println(b.getInfo()));
        }
    }

    static Book findBook(String isbn) {
        return books.stream().filter(b -> b.isbn.equals(isbn)).findFirst().orElse(null);
    }

    static Borrower findBorrower(String id) {
        return borrowers.stream().filter(br -> br.getInfo().contains(id)).findFirst().orElse(null);
    }
}
