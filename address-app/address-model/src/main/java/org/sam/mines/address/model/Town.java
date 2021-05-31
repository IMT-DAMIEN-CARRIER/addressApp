package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class Town {
    private UUID id;
    private int postCode;
    private String name;
    private Set<Address> addresses;

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

    @Column(name = "postcode")
    @Min(1)
    @NotNull
    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "town")
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }


    public static final class TownBuilder {
        private UUID id;
        private int postCode;
        private String name;
        private Set<Address> addresses;

        private TownBuilder() {
        }

        public static TownBuilder aTown() {
            return new TownBuilder();
        }

        public TownBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public TownBuilder withPostCode(int postCode) {
            this.postCode = postCode;
            return this;
        }

        public TownBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TownBuilder withAddresses(Set<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public Town build() {
            Town town = new Town();
            town.setId(id);
            town.setPostCode(postCode);
            town.setName(name);
            town.setAddresses(addresses);
            return town;
        }
    }
}
