package book.fiction;

import book.EnglishLanguage;

public class EnglishFictionLiterature extends FictionLiterature implements EnglishLanguage {

    public String publisher;

    public EnglishFictionLiterature(String author, String name, String genre, String publisher) {
        super(author, name, genre);
        this.publisher = publisher;
    }


}