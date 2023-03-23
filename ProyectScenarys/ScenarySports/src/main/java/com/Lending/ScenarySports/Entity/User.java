package com.Lending.ScenarySports.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private int id;
    @Column(name = "Code", nullable = false, length = 9)
    private int userCode;
    @Column(name = "Name", nullable = false, length = 50)
    private String name;
    @Column(name = "School", nullable = false, length = 50)

    private String school;
    @Column(name = "Email", nullable = false, length = 50)
    private String email;
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    public User() {
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
