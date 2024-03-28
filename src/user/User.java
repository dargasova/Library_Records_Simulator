package user;

import library.Subscription;
public abstract class User {
    private String name;
    private String lastname;
    private String patronymic;
    private final Subscription subscription;

    public User(int id, String name, String lastname, String patronymic) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.subscription = new Subscription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Subscription getSubscription() {
        return subscription;
    }
    public String getFullName() {
        return name + " " + lastname + " " + patronymic;
    }
}
