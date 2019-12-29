package com.inno.pojo;

import java.util.Objects;

/**
 * @author Timofey Yakimov
 */
public class User {

    private Integer id;
    private String name;
    private String login;
    private String password;
    private String phone;
    private String email;


    public User() {}

    public User(Builder builder) {
        this.id = null;
        this.name = builder.name;
        this.login = builder.login;
        this.password = builder.password;
        this.phone = !Objects.isNull(builder.phone) ? builder.phone : "";
        this.email = !Objects.isNull(builder.email) ? builder.email : "";
    }

    public User(Integer id, String name, String login, String password, String phone, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public static class Builder {
        private final String name;
        private final String login;
        private String password;
        private String phone;
        private String email;


        public Builder(String name, String login) {
            this.name = name;
            this.login = login;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }


        public Builder withEmail(String password) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}
