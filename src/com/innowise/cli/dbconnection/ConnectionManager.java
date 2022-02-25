package com.innowise.cli.dbconnection;

import com.innowise.cli.exception.DataBaseException;
import com.innowise.cli.util.ExceptionMessageUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    private ConnectionManager() {
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesManager.getPropertyByKey(URL_KEY),
                    PropertiesManager.getPropertyByKey(USER_KEY),
                    PropertiesManager.getPropertyByKey(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionMessageUtils.CONNECTION_ERROR, e);
        }
    }
}