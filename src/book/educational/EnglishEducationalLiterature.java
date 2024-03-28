package book.educational;

import book.EnglishLanguage;

public class EnglishEducationalLiterature extends EducationalLiterature implements EnglishLanguage {
    public String level;
    public String university;

    public EnglishEducationalLiterature(String author, String name, String discipline, String level, String university) {
        super(author, name, discipline);
        this.level = level;
        this.university = university;
    }

}