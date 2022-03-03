package com.innowise.cli.model;

import java.io.Serializable;
import java.util.List;

public class User implements ModelAttribute, Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;
    private List<PhoneNumber> phoneNumbers;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email,
                List<Role> roles,
                List<PhoneNumber> phoneNumbers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.phoneNumbers = phoneNumbers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private List<Role> roles;
        private List<PhoneNumber> phoneNumbers;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder roles(List<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Builder phoneNumbers(List<PhoneNumber> phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
            return this;
        }

        public User build() {
            return new User(id, firstName, lastName, email, roles, phoneNumbers);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}