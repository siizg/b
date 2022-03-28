package com.example.iamtired;

import java.util.Objects;

public class User {
    String email;
    String id;

    public User(String email, String id) {
        this.email = email;
        this.id = id;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, id);
    }
}
