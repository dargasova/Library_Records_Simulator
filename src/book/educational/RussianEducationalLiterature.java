package book.educational;

import book.RussianLanguage;

public class RussianEducationalLiterature extends EducationalLiterature implements RussianLanguage {
    public String type;

    public RussianEducationalLiterature(String author, String name, String discipline, String type) {
        super(author, name, discipline);
        this.type = type;
    }

}