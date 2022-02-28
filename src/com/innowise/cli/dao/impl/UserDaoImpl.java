package com.innowise.cli.dao.impl;

import com.innowise.cli.dao.UserDao;
import com.innowise.cli.dbconnection.ConnectionManager;
import com.innowise.cli.exception.DaoException;
import com.innowise.cli.exception.DataBaseException;
import com.innowise.cli.model.PhoneNumber;
import com.innowise.cli.model.RoleType;
import com.innowise.cli.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    private static final String SQL_SAVE_USER = """
            INSERT INTO user_cli.user_data(email, last_name, first_name)
            VALUES (?, ?, ?);
            """;

    private static final String SQL_UPDATE_USER = """
            UPDATE user_cli.user_data
            SET email      = ?,
                first_name = ?,
                last_name  = ?
            WHERE id = ?
            """;

    private static final String SQL_FIND_ALL = """
            SELECT u.id         AS id,
                   u.last_name  AS last_name,
                   u.first_name AS first_name,
                   u.email      AS email,
                   r.id         AS r_id,
                   r.role_type  AS r_role,
                   r.role_level AS r_level,
                   pn.number
            FROM user_cli.user_data u
                     LEFT JOIN user_cli.user_roles ur ON u.id = ur.user_id
                     LEFT JOIN user_cli.role r ON r.id = ur.role_id
                     LEFT JOIN user_cli.phone_number pn ON u.id = pn.user_id
            ORDER BY u.id DESC
            """;

    private static final String SQL_DELETE_BY_ID = """
            DELETE
            FROM user_cli.user_data u
            WHERE u.id = ?
            """;

    private static final String SQL_FIND_BY_ID = """
            SELECT u.id         AS id,
                   u.last_name  AS last_name,
                   u.first_name AS first_name,
                   u.email      AS email,
                   r.id         AS r_id,
                   r.role_type  AS r_role,
                   r.role_level AS r_level,
                   pn.number
            FROM user_cli.user_data u
                     LEFT JOIN user_cli.user_roles ur ON u.id = ur.user_id
                     LEFT JOIN user_cli.role r ON r.id = ur.role_id
                     LEFT JOIN user_cli.phone_number pn ON u.id = pn.user_id
            WHERE u.id = ?
            """;

    private static final String SQL_ADD_USER_ROLE = """
            INSERT INTO user_cli.user_roles(user_id, role_id)
            VALUES ((SELECT u.id FROM user_cli.user_data u WHERE u.email = ?),
                    (SELECT r.id FROM user_cli.role r WHERE r.role_type = ?));
            """;

    private static final String SQL_ADD_PHONE_TO_USER = """
            INSERT INTO user_cli.phone_number(number, user_id)
            VALUES (?, (SELECT u.id FROM user_cli.user_data u WHERE u.email = ?));
            """;

    private static final String SQL_DELETE_USER_PHONE = """
            DELETE
            FROM user_cli.phone_number
            WHERE user_id = ?
            """;

    private static final String SQL_DELETE_USER_ROLE = """
            DELETE
            FROM user_cli.user_roles
            WHERE user_id = ?
            """;

    @Override
    public User save(User user) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement saveUserStatement = connection.prepareStatement(SQL_SAVE_USER,
                     Statement.RETURN_GENERATED_KEYS)) {
            saveUserStatement.setString(1, user.getEmail());
            saveUserStatement.setString(2, user.getLastName());
            saveUserStatement.setString(3, user.getFirstName());
            saveUserStatement.executeUpdate();
            ResultSet generatedKeys = saveUserStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long userId = generatedKeys.getLong("id");
                user.setId(userId);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override

    public void update(User user) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            updateStatement.setString(1, user.getEmail());
            updateStatement.setString(2, user.getFirstName());
            updateStatement.setString(3, user.getLastName());
            updateStatement.setLong(4, user.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             Statement findAllStatement = connection.createStatement()) {
            ResultSet resultSet = findAllStatement.executeQuery(SQL_FIND_ALL);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DaoException { // TODO: 28.02.22 autoCommitFalse problem 
        Connection connection = null;
        PreparedStatement deleteUserStatement = null;
        PreparedStatement deleteRoleStatement = null;
        PreparedStatement deletePhoneNumberStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            deleteUserStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            deleteRoleStatement = connection.prepareStatement(SQL_DELETE_USER_ROLE);
            deletePhoneNumberStatement = connection.prepareStatement(SQL_DELETE_USER_PHONE);
            deletePhoneNumberStatement.setLong(1, id);
            deletePhoneNumberStatement.executeUpdate();
            deleteRoleStatement.setLong(1, id);
            deleteRoleStatement.executeUpdate();
            deleteUserStatement.setLong(1, id);
            connection.commit();
            return deleteUserStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            rollbackTransaction(connection);
            throw new DaoException(e);
        } finally {
            closeConnection(connection);
            closeStatement(deletePhoneNumberStatement);
            closeStatement(deleteRoleStatement);
            closeStatement(deleteUserStatement);
        }
    }

    @Override
    public Optional<User> findUserById(Long id) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement findUserByIdStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            findUserByIdStatement.setLong(1, id);
            ResultSet resultSet = findUserByIdStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PhoneNumber addPhoneNumberToUser(PhoneNumber phoneNumber, User user) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement addPhoneToUserStatement = connection.prepareStatement(SQL_ADD_PHONE_TO_USER,
                     Statement.RETURN_GENERATED_KEYS)) {
            addPhoneToUserStatement.setString(1, phoneNumber.getNumber());
            addPhoneToUserStatement.setString(2, user.getEmail());
            addPhoneToUserStatement.executeUpdate();
            ResultSet generatedKeys = addPhoneToUserStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long phoneNumberId = generatedKeys.getLong("id");
                phoneNumber.setId(phoneNumberId);
            }
            return phoneNumber;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addRoleToUser(RoleType roleType, User user) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement addRoleToUserStatement = connection.prepareStatement(SQL_ADD_USER_ROLE)) {
            addRoleToUserStatement.setString(1, user.getEmail());
            addRoleToUserStatement.setString(2, roleType.toString());
            addRoleToUserStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .build();
    }

    private void closeStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void rollbackTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
        }
    }
}