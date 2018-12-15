package by.tc.web.dao.factory;

import by.tc.web.dao.FilmDAO;
import by.tc.web.dao.UserDAO;
import by.tc.web.dao.connection_pool.ConnectionPool;
import by.tc.web.dao.connection_pool.exception.ConnectionPoolException;
import by.tc.web.dao.impl.FilmDAOImpl;
import by.tc.web.dao.impl.UserDAOImpl;
import org.apache.log4j.Logger;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();
    private ConnectionPool connectionPool = new ConnectionPool();
    private UserDAO userDAO = new UserDAOImpl(connectionPool);
    private FilmDAO filmDAO = new FilmDAOImpl(connectionPool);

    private DAOFactory() {
        try {
            connectionPool.initConnectionPool();
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public FilmDAO getFilmDAO() {
        return filmDAO;
    }
}
