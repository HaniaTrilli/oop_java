package models;

import java.time.LocalDate;

public class BorrowingProcess {
    private Book book;
    private Borrower borrower;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowingProcess(Book book, Borrower borrower) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    public void returnBook() {
        book.returnBook();
        returnDate = LocalDate.now();
    }

    public String getDetails() {
        return borrower.getInfo() + " borrowed " + book.getInfo() + " on " + borrowDate +
                (returnDate != null ? ", returned on " + returnDate : "");
    }
}
