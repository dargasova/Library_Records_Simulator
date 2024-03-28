package book.fiction;

import book.Book;

public abstract class FictionLiterature extends Book {
    public String genre;
    public FictionLiterature(String author, String name, String genre) {
        super(author, name);
        this.genre = genre;
    }


}
