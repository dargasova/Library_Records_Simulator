package book.fiction;

import book.RussianLanguage;


public class RussianFictionLiterature extends FictionLiterature implements RussianLanguage {
    public int year;

    public RussianFictionLiterature(String author, String name, String genre, int year) {
        super(author, name, genre);
        this.year = year;
    }

}