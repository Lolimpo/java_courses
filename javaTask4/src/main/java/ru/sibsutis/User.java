package ru.sibsutis;

abstract class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}

    protected String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}