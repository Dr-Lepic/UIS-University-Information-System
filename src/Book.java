

import java.util.Date;

public class Book {

    public String name;
    public String author;
    Date borrowDate;
    Date returnDate;
    public boolean available;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.available = true;
    }

    @Override
    public String toString() {
        return  "\"" + name +"\"" + " By " + author;
    }
}
