package book.educational;

public abstract class EducationalLiterature {
    public String discipline;
    protected String author;
    protected String name;
    protected boolean borrowed;

    public EducationalLiterature(String author, String name, String discipline) {
        initialize(author, name);
        this.discipline = discipline;
    }

    private void initialize(String author, String name) {
        this.author = author;
        this.name = name;
        this.borrowed = false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
