package book.educational;

import book.Book;

public abstract class EducationalLiterature extends Book {
    public String discipline;

    public EducationalLiterature(String author, String name, String discipline) {
        super(author, name);
        this.discipline = discipline;
    }

}
