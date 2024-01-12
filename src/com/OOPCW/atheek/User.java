package com.OOPCW.atheek;

public class User {
    private String username;

    private String password;

    private PurchaseHistory history;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.history = new PurchaseHistory();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PurchaseHistory getHistory() {
        return history;
    }

    public void setHistory(PurchaseHistory history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
