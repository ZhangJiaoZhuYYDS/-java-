package com.cainiao.pojo;

public class Account {
    private Integer id;
    private String username;
    private Integer password;
    private String name;

    public Account(Integer id, String username, Integer password, String name) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }
    public Account( String username, Integer password, String name) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Account(String username,Integer password) {
        this.username=username;
        this.password=password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
