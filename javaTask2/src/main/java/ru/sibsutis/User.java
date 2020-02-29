package ru.sibsutis;

abstract class User {
    private String name;
    private String email;

    public User(String name) { this.name = name; }

    public User(String name, String email) {
        this(name);
        this.email = email;
    }

    protected String getName() { return this.name; }

    protected String getEmail() { return this.email; }
}