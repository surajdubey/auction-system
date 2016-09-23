package com.crossover.auctionsystem.model;

public class User {
    public static final int USER_TYPE_ADMIN = 0;
    public static final int USER_TYPE_DOCTOR = 1;
    public static final int INVALID_USER_ID = -1;
    private String name;
    private String username;
    private String password;
    private int userType;
    private String createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        String result = name + " " + username;
        return result;
    }
}
