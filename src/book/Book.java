package book;

public abstract class Book {
    protected int id;
    protected String author;
    protected String name;
    protected boolean borrowed;

    public Book(String author, String name) {
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
