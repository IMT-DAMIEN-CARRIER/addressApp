package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Target {
    private UUID id;
    private String firstname;
    private String name;
    private Set<Address> addresses;
    private PhoneNumber phoneNumber;

    public void setId(UUID id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name= "uuid", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "targets")
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToOne()
    @JoinColumn(name = "id", referencedColumnName = "uuid")
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Target) {
            Target targetObject = (Target) obj;

            return this.id.equals(targetObject.id) && this.firstname == targetObject.firstname &&
                   this.name == targetObject.name && this.addresses.equals(targetObject.addresses) &&
                   this.phoneNumber.equals(phoneNumber);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.firstname.hashCode() + this.phoneNumber.hashCode() +
               this.name.hashCode() + this.addresses.hashCode() +
               this.phoneNumber.hashCode();
    }
}
