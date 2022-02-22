package com.innowise.cli.model;

public enum Role {

    USER(1),
    CUSTOMER(1),
    ADMIN(2),
    PROVIDER(2),
    SUPER_ADMIN(3);

    private final Integer level;

    Role(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Role{" +
               "level=" + level +
               '}';
    }
}