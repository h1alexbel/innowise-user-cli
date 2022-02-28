package com.innowise.cli.model;

public class PhoneNumber implements ModelAttribute {

    private Long id;
    private String number;
    private User user;

    public PhoneNumber() {
    }

    public PhoneNumber(Long id, String phoneNumber, User user) {
        this.id = id;
        this.number = phoneNumber;
        this.user = user;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String number;
        private User user;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.number = phoneNumber;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public PhoneNumber build() {
            return new PhoneNumber(id, number, user);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
               "id=" + id +
               ", number='" + number + '\'' +
               '}';
    }
}