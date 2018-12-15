package by.tc.web.dao.impl;

import by.tc.web.dao.UserDAO;
import by.tc.web.dao.connection_pool.ConnectionPool;
import by.tc.web.dao.connection_pool.exception.ConnectionPoolException;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.entity.user.Ban;
import by.tc.web.entity.user.User;
import by.tc.web.entity.user.UserMarkStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final static String SELECT_STRING = "SELECT * FROM account WHERE login = ? and password = ?";
    private final static String STRING = "id";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String EMAIL = "email";
    private final static String STATUS = "status";
    private final static String NICKNAME = "nickname";
    private final static String DATA_REGISTERED = "data_registered";
    private final static String INSERT_USER = "INSERT INTO account (login,password,email,nickname,image) VALUES (?,?,?,?,?)";
    private static final String INDSER_USER = "INSERT INTO `account`(`login`,`password`,`email`,`nickname`,`image`,`status`) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE `account` SET `login` = ?,`password` = ?,`email` = ?,`nickname`=?,`image`=?,`status` = ?,`ban` = ? WHERE `id` = ?";
    private static final String DELETE_MARK = "DELETE FROM `mark` WHERE `id_user` = ?";
    private static final String DELETE_USER = "DELETE FROM `account` WHERE `id` = ?";
    private static final String SELECT_ALL_USER = "SELECT `id`,`login`,`password`,`email`,`status`,`nickname`,`ban`,`image`" +
            "FROM `account`";
    private static final String ID = "id";
    private static final String BAN = "ban";
    private static final String IMAGE = "image";
    private static final String SELECT_ALL_BAN_REASON = "SELECT * FROM `ban_reason` WHERE `lang` = ?";
    private static final String LANG = "lang";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String BAN_USER = "UPDATE `account` SET `ban` = ?,`ban_date` = CURRENT_TIMESTAMP() WHERE `id` = ?";
    private static final String UPDATE_ACCOUNT = "UPDATE `account` SET `password`=?,`nickname`=?,`image`=? WHERE `id` = ?";
    private static final String UPDETE_USER_STATUS = "UPDATE `account` SET `status` = ? WHERE `id` = ?";
    private ConnectionPool connectionPool;

    public UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public User authorization(String login, String password) throws DAOException {
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_STRING);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(STRING));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
                user.seteMail(resultSet.getString(EMAIL));
                user.setStatus(Integer.parseInt(resultSet.getString(STATUS)));
                user.setNickname(resultSet.getString(NICKNAME));
                user.setDateRegistered(Timestamp.valueOf(resultSet.getString(DATA_REGISTERED)));
                user.setBan(resultSet.getInt(BAN));
            }
        } catch (SQLException e) {
            throw new DAOException("Problem with search user", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection");
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return user;
    }

    public void registration(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.geteMail());
            preparedStatement.setString(4, user.getNickname());
            preparedStatement.setString(5, user.getPathToImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Duplicate User", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection", e);
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void addUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(INDSER_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.geteMail());
            preparedStatement.setString(4, user.getNickname());
            preparedStatement.setString(5, user.getPathToImage());
            preparedStatement.setInt(6, user.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Duplicate User", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection", e);
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void editUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.geteMail());
            preparedStatement.setString(4, user.getNickname());
            preparedStatement.setString(5, user.getPathToImage());
            preparedStatement.setInt(6, user.getStatus());
            preparedStatement.setInt(7, user.getBan());
            preparedStatement.setInt(8, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Some Problems with Database", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection", e);
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void deleteUser(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_MARK);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException("Some Problems with Database", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection", e);
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<User> getAllUser(String lang) throws DAOException {
        List<User> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
                user.seteMail(resultSet.getString(EMAIL));
                user.setStatus(resultSet.getInt(STATUS));
                user.setNickname(resultSet.getString(NICKNAME));
                user.setBan(resultSet.getInt(BAN));
                user.setPathToImage(resultSet.getString(IMAGE));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Duplicate User", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection", e);
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public List<Ban> getAllBanReason(String lang) throws DAOException {
        List<Ban> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_BAN_REASON);
            preparedStatement.setString(1, lang);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ban ban = new Ban();
                ban.setId(resultSet.getInt(ID));
                ban.setLang(resultSet.getString(LANG));
                ban.setName(resultSet.getString(NAME));
                ban.setDescription(resultSet.getString(DESCRIPTION));
                list.add(ban);
            }
        } catch (SQLException e) {
            throw new DAOException("Duplicate User", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection", e);
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
        return list;
    }

    @Override
    public void editProfile(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getPathToImage());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Duplicate User", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection", e);
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void banUser(int userId, Integer banId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(BAN_USER);
            if (banId == null) {
                preparedStatement.setNull(1, 1);
            } else {
                preparedStatement.setInt(1, banId);
            }
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Duplicate User", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection", e);
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }

    }

    @Override
    public void setUsersStatus(List<UserMarkStatus> list) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDETE_USER_STATUS);
            for (UserMarkStatus item:list) {
                preparedStatement.clearParameters();
                preparedStatement.setInt(1, item.getStatus());
                preparedStatement.setInt(2, item.getUserId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Duplicate User", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection", e);
            throw new DAOException("Problem with connection", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }
}