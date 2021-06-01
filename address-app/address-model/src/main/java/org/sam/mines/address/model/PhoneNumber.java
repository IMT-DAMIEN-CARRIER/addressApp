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
    private int[] phoneNumber = new int[10];

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
    public int[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PhoneNumber) {
            PhoneNumber phoneObject = (PhoneNumber) obj;

            int[] phoneNumber = phoneObject.getPhoneNumber();
            for (int i = 0; i < phoneNumber.length; ++i) {
                if (phoneNumber[i] != this.phoneNumber[i]) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.phoneNumber.hashCode();
    }
}