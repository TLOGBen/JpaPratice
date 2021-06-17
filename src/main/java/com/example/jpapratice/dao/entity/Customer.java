package com.example.jpapratice.dao.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "ROC_ID")
    private String rocId;

    @OneToMany(mappedBy = "CUSTOMER", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Phone> phones;

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

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }
}
