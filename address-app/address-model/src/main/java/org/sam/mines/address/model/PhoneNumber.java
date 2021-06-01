package org.sam.mines.address.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class PhoneNumber {
    private UUID id;
    private String phoneNumber;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name= "uuid", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "phoneNumber")
    @Min(1)
    @NotNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PhoneNumber)) {
            return false;
        }

        PhoneNumber phoneObject = (PhoneNumber) obj;
        String phoneNumber = phoneObject.getPhoneNumber();

        return this.phoneNumber.equals(phoneNumber);
    }

    @Override
    public int hashCode() {
        return this.phoneNumber.hashCode();
    }
}