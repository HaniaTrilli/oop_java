package models;

import java.util.ArrayList;

public class Borrower {
    private String name;
    private String studentId;
    private ArrayList<Book> borrowedBooks;

    public Borrower(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrow();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getInfo() {
        return name + " (" + studentId + ")";
    }
}
