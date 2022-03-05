package com.innowise.cli.util;

import com.innowise.cli.model.Role;
import com.innowise.cli.model.RoleType;

public final class RoleUtils {

    private static final String USER_SIGN = "U";
    private static final String CUSTOMER_SIGN = "C";
    private static final String ADMIN_SIGN = "A";
    private static final String PROVIDER_SIGN = "P";
    private static final String SUPER_ADMIN_SIGN = "SA";
    private static final int USER_CUSTOMER_LEVEL = 1;
    private static final int ADMIN_PROVIDER_LEVEL = 2;
    private static final int SUPER_ADMIN_LEVEL = 3;

    private RoleUtils() {
        throw new UnsupportedOperationException();
    }

    public static RoleType defineRoleType(String name) {
        return switch (name) {
            case USER_SIGN -> RoleType.USER;
            case CUSTOMER_SIGN -> RoleType.CUSTOMER;
            case ADMIN_SIGN -> RoleType.ADMIN;
            case PROVIDER_SIGN -> RoleType.PROVIDER;
            case SUPER_ADMIN_SIGN -> RoleType.SUPER_ADMIN;
            default -> throw new IllegalStateException();
        };
    }

    public static Role mapRoleTypeToRole(RoleType roleType) {
        return switch (roleType) {
            case USER -> Role.builder()
                    .level(USER_CUSTOMER_LEVEL)
                    .roleType(RoleType.USER)
                    .build();
            case CUSTOMER -> Role.builder()
                    .level(USER_CUSTOMER_LEVEL)
                    .roleType(RoleType.CUSTOMER)
                    .build();
            case ADMIN -> Role.builder()
                    .level(ADMIN_PROVIDER_LEVEL)
                    .roleType(RoleType.ADMIN)
                    .build();
            case PROVIDER -> Role.builder()
                    .level(ADMIN_PROVIDER_LEVEL)
                    .roleType(RoleType.PROVIDER)
                    .build();
            case SUPER_ADMIN -> Role.builder()
                    .level(SUPER_ADMIN_LEVEL)
                    .roleType(RoleType.SUPER_ADMIN)
                    .build();
        };
    }
}