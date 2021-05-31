package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// record available in Java 16
record User(String firstname, String lastname, int age, double height) {

    public static final class UserBuilder {
        private String firstname;
        private String lastname;
        private int age;
        private double height;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder height(double height) {
            this.height = height;
            return this;
        }

        public User build() {
            return new User(firstname, lastname, age, height);
        }
    }
}

class BuilderTest {

    @Test
    void shouldBuildObject() {

        User user = User.UserBuilder.anUser().age(50).firstname("tom").lastname("araya").build();

        assertEquals(50, user.age());
    }
}
