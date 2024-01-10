package com.example.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class User {

    private final String name;
    private final int age;
    private final Map<Long, Book> takenBooks = new HashMap<>();

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Map<Long, Book> getTakenBooks() {
        return takenBooks;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        User user = (User) object;

        if (age != user.age) return false;
        if (!Objects.equals(name, user.name)) return false;
        return takenBooks.equals(user.takenBooks);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + takenBooks.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
