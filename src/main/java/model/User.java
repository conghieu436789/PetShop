package model;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String gender;
    private Date createdAt;
    private Date updatedAt;

    public User() {
    }

    public User(String username, String password, String phone, String gender) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
    }

    public User(int id, String username, String password, String phone, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
