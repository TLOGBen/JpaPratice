package com.example.jpapratice.dao.entity;

import javax.persistence.*;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    @Column(name = "ROC_ID")
    private String rocId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRocId() {
        return rocId;
    }

    public void setRocId(String rocId) {
        this.rocId = rocId;
    }
}
