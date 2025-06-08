package com.intimoda.app.jpa.model;

import com.intimoda.app.jpa.enums.DocumentType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
    // Clase creada DocumentType
    private DocumentType documentType;
    private int documentNumber;
    private int phone;
    private LocalDateTime registrationDate=LocalDateTime.now();

    public User(){}

    public User(String username,String password,String email,String name,
                String lastname,DocumentType documentType,int phone,int documentNumber){

        this.username=username;
        this.password=password;
        this.email=email;
        this.name=name;
        this.lastname=lastname;
        this.documentType=documentType;
        this.documentNumber=documentNumber;
        this.phone=phone;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }


}
