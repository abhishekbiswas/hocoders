package com.barclaycardus.hackathon.hocoders.service.dto;

import javax.persistence.*;

/**
 * Created by abhishek on 11/06/16.
 */

@Entity(name = "User")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userId","phoneNumber"}))
@NamedQuery(
        name="findAllUsersByUserId",
        query="SELECT c FROM User c WHERE c.userId LIKE :userId"
)
public class User {

    @Id
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    public User() {}

    public  User (String userId, String firstName, String lastName, String phoneNumber, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
