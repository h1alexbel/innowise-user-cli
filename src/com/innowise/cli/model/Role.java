package com.innowise.cli.model;

public class Role implements ModelAttribute {

    private Long id;
    private RoleType roleType;
    private Integer level;
    private User user;

    public Role() {
    }

    public Role(Long id, RoleType role, Integer level, User user) {
        this.id = id;
        this.roleType = role;
        this.level = level;
        this.user = user;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private RoleType roleType;
        private Integer level;
        private User user;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder roleType(RoleType roleType) {
            this.roleType = roleType;
            return this;
        }

        public Builder level(Integer level) {
            this.level = level;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Role build() {
            return new Role(id, roleType, level, user);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

        Role role = (Role) o;

        return id != null ? id.equals(role.id) : role.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
               "id=" + id +
               ", roleType=" + roleType +
               ", level=" + level +
               ", user=" + user +
               '}';
    }
}