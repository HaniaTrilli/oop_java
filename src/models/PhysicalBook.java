package models;

public class PhysicalBook extends Book {
    public PhysicalBook(String title, String author, String isbn) {
        super(title, author, isbn);
    }

    @Override
    public String getType() {
        return "Physical";
    }
}
