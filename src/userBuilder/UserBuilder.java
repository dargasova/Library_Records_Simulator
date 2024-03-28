package userBuilder;

import user.User;

public interface UserBuilder<T extends User> {
    void buildName(String name);
    void buildLastname(String lastname);
    void buildPatronymic(String patronymic);
    T getUser();
}