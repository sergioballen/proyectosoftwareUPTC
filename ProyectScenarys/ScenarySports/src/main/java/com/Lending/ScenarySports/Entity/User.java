package com.Lending.ScenarySports.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private int id;
    @Column(name = "Code", nullable = false, length = 9, unique=true)
    private int userCode;
    @Column(name = "Name", nullable = false, length = 50)
    private String name;
    @Column(name = "School", nullable = false, length = 50)

    private String school;

    @Column(name = "Email", nullable = false, length = 50,unique = true)
    private String email;
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Column(name = "Role", nullable = false)
    private String role;

   // @OneToMany(mappedBy = "user")
   // @JsonIgnore
    //private List<Scenary> scenarys;
   // @OneToMany(mappedBy = "user")
    //@JsonIgnore
    //private List<Booking> bookings;

    public User() {
    }

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

    public String getRole() {return role;}

    public void setRole(String role) {
        this.role = role;
    }

   /* public List<Scenary> getScenarys() {
        return scenarys;
    }

    public void setScenarys(List<Scenary> scenarys) {
        this.scenarys = scenarys;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }*/
}
