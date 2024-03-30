package book.fiction;

public abstract class FictionLiterature {
    public String genre;
    protected String author;
    protected String name;
    protected boolean borrowed;
    public FictionLiterature(String author, String name, String genre) {
        initialize(author, name);
        this.genre = genre;
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
