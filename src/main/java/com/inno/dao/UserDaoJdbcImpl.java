package com.inno.dao;

import com.inno.ConnectionManager.ConnectionManager;
import com.inno.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timofey Yakimov
 */
public class UserDaoJdbcImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoJdbcImpl.class);

    public static final String INSERT_INTO_USR = "INSERT INTO usr values (DEFAULT, ?, ?, ?, ?, ?)";
    public static final String SELECT_USER_BY_LOGIN_AND_PWD = "SELECT * FROM usr WHERE login = ? and password = ?";
    public static final String CREATE_TABLE_USR
            = "DROP TABLE IF EXISTS usr;\n"
            + "create table usr\n"
            + "(\n"
            + "    id bigserial not null\n"
            + "        constraint usr_pkey\n"
            + "            primary key,\n"
            + "    name varchar(100) not null,\n"
            + "    login varchar(100) not null,\n"
            + "    password varchar(100) not null,\n"
            + "    phone varchar(100) not null,\n"
            + "    email varchar(100) not null\n"
            + "); commit;\n"
            + "\n"
            + "alter table usr owner to usr;";

    private ConnectionManager connectionManager;

    @Inject
    public UserDaoJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override public void createTable() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_USR)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in createTable method", e);
        }
    }

    @Override
    public boolean addUser(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USR)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in addUser method", e);
            return false;
        }
        return true;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PWD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Something wrong in  getUserByLoginAndPassword method", e);
        }
        return null;
    }

}
