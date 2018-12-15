package by.tc.web.dao.impl;

import by.tc.web.dao.FilmDAO;
import by.tc.web.dao.connection_pool.ConnectionPool;
import by.tc.web.dao.connection_pool.exception.ConnectionPoolException;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.entity.film.*;
import by.tc.web.entity.user.UserMarkStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements FilmDAO {
    private static final String SELECT_USER_MARK_STATUS = "SELECT `mark`.`id_user`,`mark`.`user_mark`,`account`.`status` FROM `mark` INNER JOIN `account` ON `account`.`id` = `mark`.`id_user` WHERE `film_id` = ?";
    private static final String STATUS = "status";
    private static final String SELECT_AVG_RAITING = "SELECT `avg_raiting` FROM `film` WHERE `id` = ?";
    private static final String SELECT_COUNT_USERS = "SELECT COUNT(`id_user`) as `count` FROM `mark` WHERE `id_film` = ?";
    private static final String SELECT_CRITICS = "SELECT COUNT(`mark`.`id_user`) as `count`,AVG(`mark`.`user_mark`) as avg FROM `mark` " +
            "INNER JOIN `account` ON `mark`.id_user = account.id" +
            "WHERE `mark`.`id_film` = ? AND  `account`.`status`= 5";
    private static final String PERSENT = "%";
    private static final String AVG = "avg";
    private final static String ID = "id";
    private final static String AVG_RAITING = "avg_raiting";
    private final static String IMAGE_WIDESCREEN = "image_widescreen";
    private final static String DATE_PRIMER = "date_primer";
    private final static String IMAGE_POSTER = "image_poster";
    private final static String NAME = "name";
    private final static String GANRE = "ganre";
    private final static String DIRECTOR = "director";
    private final static String DURATION = "duration";
    private final static String DESCRIPTION = "description";
    private static final String USER_MARK = "user_mark";
    private static final String USER_COMMENT = "user_comment";
    private static final String NICKNAME = "nickname";
    private static final String DATE_POST = "date_post";
    private static final String LANG = "lang";
    private static final String COUNT = "count";
    private static final String ID_FILM = "id_film";
    private static final String ID_USER = "id_user";
    private final static String SELECT_LIST_FILM_AVG =
            "SELECT `film_info`.`name`,`film`.`avg_raiting`,`film`.`date_primer`" +
                    "FROM film INNER JOIN `film_info` ON `film`.`id`=`film_info`.`film_id`" +
                    "WHERE `film_info`.`lang` = ? AND " +
                    "DATE(`film`.`date_primer`) BETWEEN DATE_SUB(DATE(NOW()), INTERVAL 1 MONTH) AND DATE(NOW()) LIMIT 0,15";
    private final static String SELECT_LIST_FILM_IMAGE =
            "SELECT `film`.`id`,`film_info`.`name`,`film`.`image_widescreen`" +
                    "FROM `film` INNER JOIN `film_info` ON `film`.`id`=`film_info`.`film_id`" +
                    "WHERE `film_info`.`lang` = ? ORDER BY `film`.`date_primer` LIMIT 3";
    private final static String SELECT_ALL_FILM =
            "SELECT `film`.`id`,`film`.`avg_raiting`,`film`.`date_primer`,`film`.`image_poster`," +
                    "`film_info`.`name`,`film_info`.`ganre`,`film_info`.`director`" +
                    "FROM `film` INNER JOIN `film_info` ON `film`.`id` = `film_info`.`film_id`" +
                    "WHERE `film_info`.`lang` = ?";
    private final static String SELECT_INF_FILM = "SELECT `film`.`id`,`film`.`avg_raiting`,`film`.`date_primer`," +
            "`film`.`image_poster`,`film`.`image_widescreen`,`film_info`.`name`,`film_info`.`ganre`,`film_info`.`director`," +
            "`film`.`duration`,`film_info`.`description`" +
            "FROM `film` INNER JOIN `film_info` ON `film`.`id` = `film_info`.`film_id`" +
            "WHERE `film_info`.`lang` = ? AND `film`.`id` = ?";
    private final static String SELECT_CUR_USER_COMMENT = "SELECT `user_mark`,`user_comment` FROM `mark` " +
            "WHERE `id_user` = ? AND `id_film` = ?";
    private final static String ADD_COMMENT = "INSERT INTO `mark` (`id_user`,`id_film`,`user_mark`,`user_comment`)" +
            "VALUES (?,?,?,?)";
    private final static String SELECT_CRITIC_COMMENT = "SELECT `account`.`nickname`,`account`.`image`,`mark`.`user_mark`," +
            "`mark`.`user_comment`,`mark`.`date_post` FROM `mark` INNER JOIN `account` ON `mark`.`id_user` = `account`.`id`" +
            "WHERE `mark`.`id_film` = ? AND `account`.`status` = '4' ORDER BY `mark`.`date_post`";
    private final static String SELECT_USER_COMMENT = "SELECT `account`.`nickname`,`account`.`image`,`mark`.`user_mark`," +
            "`mark`.`user_comment`,`mark`.`date_post` FROM `mark` INNER JOIN `account` ON `mark`.`id_user` = `account`.`id`" +
            "WHERE `mark`.`id_film` = ? AND `account`.`status` <> '5' ORDER BY `mark`.`date_post`";
    private final static String ADD_FILM = "INSERT INTO `film` (`image_widescreen`,`date_primer`,`image_poster`,`duration`)" +
            "VALUES (?,?,?,?)";
    private final static String SELECT_FILM = "SELECT MAX(`id`) as id FROM `film`";
    private final static String ADD_FILM_INFO = "INSERT INTO `film_info` (`lang`,`film_id`,`name`,`ganre`,`director`,`description`)" +
            "VALUES (?,?,?,?,?,?)";
    private final static String SELECT_ALL_INF_ALL_FILM = "SELECT `film`.`id`,`film`.`avg_raiting`,`film`.`date_primer`," +
            "`film`.`image_poster`,`film`.`image_widescreen`,`film_info`.`name`,`film_info`.`ganre`,`film_info`.`director`," +
            "`film`.`duration`,`film_info`.`description`,`film_info`.`lang`" +
            "FROM `film_info` INNER JOIN `film` ON `film_info`.`film_id` = `film`.`id`";
    private static final String SELECT_COUNT_IMAGE = "SELECT COUNT(`id`) as `count` FROM `film` WHERE `image_poster` LIKE CONCAT(?,'%.jpg') OR `image_widescreen` LIKE CONCAT(?,'%.jpg')";
    private static final String EDIT_FILM = "UPDATE `film`" +
            " SET `image_widescreen` = ?,`date_primer` = ?, `image_poster` = ?, `duration` = ? " +
            "WHERE `id` = ?";
    private static final String EDIT_FILM_INFO = "UPDATE `film_info`" +
            "SET `name` = ?,`ganre` = ?, `director` = ?, `description` = ? " +
            "WHERE `film_id` = ? AND `lang` = ?";
    private static final String DELETE_FILM_INFO = "DELETE FROM `film_info` WHERE `film_id` = ? AND `lang` = ?";
    private static final String DELETE_FILM = "DELETE FROM `film` WHERE `film_id` = ?";
    private static final String SELECT_COMMENT = "SELECT `mark`.`id_user`,`mark`.`id_film`,`mark`.`user_comment`,`film_info`.`name`" +
            "FROM `mark` INNER JOIN `film_info` ON `film_info`.`film_id` = `mark`.`id_film` WHERE `film_info`.`lang` = ? ";
    private static final String DELETE_MARK = "DELETE FROM `mark` WHERE `id_user` = ? AND `id_film` = ?";
    private static final String SELECT_FILM_BY_NAME = "SELECT `film`.`id`,`film`.`avg_raiting`,`film`.`date_primer`,`film`.`image_poster`," +
            "`film_info`.`name`,`film_info`.`ganre`,`film_info`.`director`" +
            "FROM `film` INNER JOIN `film_info` ON `film`.`id` = `film_info`.`film_id`" +
            "WHERE `film_info`.`lang` = ? AND `film_info`.`name` LIKE ?";
    private static final String SELECT_COUNT_MARK = "SELECT COUNT(`id_user`) as `count` FROM `mark` WHERE `id_film` = ?";
    private ConnectionPool connectionPool;

    public FilmDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Film> getListRatingOnMounth(String lang) throws DAOException {
        List<Film> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_LIST_FILM_AVG);
            preparedStatement.setString(1, lang);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();
                film.setName(resultSet.getString(NAME));
                film.setAvgRating(resultSet.getDouble(AVG_RAITING));
                film.setDatePrimer(resultSet.getDate(DATE_PRIMER));
                list.add(film);
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public List<Film> getListNewFilm(String lang) throws DAOException {
        List<Film> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_LIST_FILM_IMAGE);
            preparedStatement.setString(1, lang);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt(ID));
                film.setName(resultSet.getString(NAME));
                film.setNameImage(resultSet.getString(IMAGE_WIDESCREEN));
                list.add(film);
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public List<Film> getAllFilm(String lang) throws DAOException {
        List<Film> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_FILM);
            preparedStatement.setString(1, lang);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(setFilmInf(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public Film getInfFilm(String lang, int id) throws DAOException {
        Film film = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_INF_FILM);
            preparedStatement.setString(1, lang);
            preparedStatement.setString(2, String.valueOf(id));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                film = new Film();
                film.setId(resultSet.getInt(ID));
                film.setName(resultSet.getString(NAME));
                film.setAvgRating(resultSet.getDouble(AVG_RAITING));
                film.setNameImage(resultSet.getString(IMAGE_WIDESCREEN));
                film.setDatePrimer(resultSet.getDate(DATE_PRIMER));
                film.setNamePoster(resultSet.getString(IMAGE_POSTER));
                film.setGanre(resultSet.getString(GANRE));
                film.setDirector(resultSet.getString(DIRECTOR));
                film.setDuration(resultSet.getString(DURATION));
                film.setDescription(resultSet.getString(DESCRIPTION));
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return film;
    }

    @Override
    public Comment getCurUserComment(int userId, int filmId) throws DAOException {
        Comment comment = new Comment();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_CUR_USER_COMMENT);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, filmId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                comment.setMark(resultSet.getInt(USER_MARK));
                comment.setComment(resultSet.getString(USER_COMMENT));
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return comment;
    }

    @Override
    public void setUserComment(Mark mark) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(ADD_COMMENT);
            preparedStatement.setInt(1, mark.getUserId());
            preparedStatement.setInt(2, mark.getFilmId());
            preparedStatement.setInt(3, mark.getMark());
            preparedStatement.setString(4, mark.getComment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<Comment> getCommentCritic(int filmId) throws DAOException {
        return getComment(filmId, SELECT_CRITIC_COMMENT);
    }

    @Override
    public List<Comment> getCommentUser(int filmId) throws DAOException {
        return getComment(filmId, SELECT_USER_COMMENT);
    }

    private List<Comment> getComment(int filmId, String req) throws DAOException {
        List<Comment> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, filmId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setNickname(resultSet.getString(NICKNAME));
                comment.setImage(resultSet.getString(IMAGE_WIDESCREEN));
                comment.setMark(resultSet.getInt(USER_MARK));
                comment.setComment(resultSet.getString(USER_COMMENT));
                comment.setDate(resultSet.getDate(DATE_POST));
                list.add(comment);
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public void addFilm(Film[] films) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String id;
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_FILM);
            preparedStatement.setString(1, films[0].getNameImage());
            preparedStatement.setString(2, String.valueOf(films[0].getDatePrimer()));
            preparedStatement.setString(3, films[0].getNamePoster());
            preparedStatement.setString(4, films[0].getDuration());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SELECT_FILM);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString(ID);
                preparedStatement = connection.prepareStatement(ADD_FILM_INFO);
                for (int i = 0; i < films.length; i++) {
                    preparedStatement.clearParameters();
                    preparedStatement.setString(1, films[i].getLang());
                    preparedStatement.setString(2, id);
                    preparedStatement.setString(3, films[i].getName());
                    preparedStatement.setString(4, films[i].getGanre());
                    preparedStatement.setString(5, films[i].getDirector());
                    preparedStatement.setString(6, films[i].getDescription());
                    preparedStatement.executeUpdate();
                }
                connection.commit();
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Film> getAllInfAllFilm() throws DAOException {
        List<Film> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_INF_ALL_FILM);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt(ID));
                film.setAvgRating(resultSet.getDouble(AVG_RAITING));
                film.setNameImage(resultSet.getString(IMAGE_WIDESCREEN));
                film.setDatePrimer(resultSet.getDate(DATE_PRIMER));
                film.setNamePoster(resultSet.getString(IMAGE_POSTER));
                film.setDuration(resultSet.getString(DURATION));
                film.setName(resultSet.getString(NAME));
                film.setGanre(resultSet.getString(GANRE));
                film.setDirector(resultSet.getString(DIRECTOR));
                film.setDescription(resultSet.getString(DESCRIPTION));
                film.setLang(resultSet.getString(LANG));
                list.add(film);
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public int getAlig(String nameFilm) throws DAOException {
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_COUNT_IMAGE);
            preparedStatement.setString(1, nameFilm);
            preparedStatement.setString(2, nameFilm);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(COUNT);
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public void editFilm(Film film) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(EDIT_FILM);
            preparedStatement.setString(1, film.getNameImage());
            preparedStatement.setString(2, String.valueOf(film.getDatePrimer()));
            preparedStatement.setString(3, film.getNamePoster());
            preparedStatement.setString(4, film.getDuration());
            preparedStatement.setInt(5, film.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(EDIT_FILM_INFO);
            preparedStatement.setString(1, film.getName());
            preparedStatement.setString(2, film.getGanre());
            preparedStatement.setString(3, film.getDirector());
            preparedStatement.setString(4, film.getDescription());
            preparedStatement.setInt(5, film.getId());
            preparedStatement.setString(6, film.getLang());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public void deleteFilm(int id, String lang) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_FILM_INFO);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(DELETE_FILM);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<CommentForDelete> getAllComment(String lang) throws DAOException {
        List<CommentForDelete> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_COMMENT);
            preparedStatement.setString(1, lang);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CommentForDelete commentForDelete = new CommentForDelete();
                commentForDelete.setFilmId(resultSet.getInt(ID_FILM));
                commentForDelete.setUserId(resultSet.getInt(ID_USER));
                commentForDelete.setNameFilm(resultSet.getString(NAME));
                commentForDelete.setComment(resultSet.getString(USER_COMMENT));
                list.add(commentForDelete);
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public void deleteComment(int userId, int filmId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_MARK);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, filmId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public Score getScore(int filmId) throws DAOException {
        Score score = new Score();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_COUNT_USERS);
            preparedStatement.setInt(1, filmId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                score.setCountUser(resultSet.getInt(COUNT));
            }
            preparedStatement = connection.prepareStatement(SELECT_CRITICS);
            preparedStatement.setInt(1, filmId);
            if (resultSet.next()) {
                score.setCountCritic(resultSet.getInt(COUNT));
                score.setAvgCritic(resultSet.getInt(AVG));
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement,resultSet);
        }
        return score;
    }

    @Override
    public List<Film> getFilmForSearch(String nameFilm, String lang) throws DAOException {
        List<Film> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_FILM_BY_NAME);
            preparedStatement.setString(1, lang);
            preparedStatement.setString(2, PERSENT + nameFilm + PERSENT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(setFilmInf(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public int getCountUsersMark(int filmId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_COUNT_MARK);
            preparedStatement.setInt(1, filmId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(COUNT);
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public List<UserMarkStatus> getUsersMarkAndStatus(int filmId) throws DAOException {
        List<UserMarkStatus> list = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_MARK_STATUS);
            preparedStatement.setInt(1, filmId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserMarkStatus userMarkStatus = new UserMarkStatus();
                userMarkStatus.setUserId(resultSet.getInt(ID_USER));
                userMarkStatus.setUserMark(resultSet.getInt(USER_MARK));
                userMarkStatus.setStatus(resultSet.getInt(STATUS));
                list.add(userMarkStatus);
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public double getAvgScore(int filmId) throws DAOException {
        double avgRating = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_AVG_RAITING);
            preparedStatement.setInt(1, filmId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                avgRating = resultSet.getDouble(AVG_RAITING);
            }
        } catch (SQLException e) {
            throw new DAOException("Some Problem with data", e);
        } catch (ConnectionPoolException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error("Problem with connection pool");
            throw new DAOException("Problem with connection pool");
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return avgRating;
    }

    private Film setFilmInf(ResultSet resultSet) throws SQLException{
        Film film = new Film();
        film.setId(resultSet.getInt(ID));
        film.setAvgRating(resultSet.getDouble(AVG_RAITING));
        film.setDatePrimer(resultSet.getDate(DATE_PRIMER));
        film.setNamePoster(resultSet.getString(IMAGE_POSTER));
        film.setName(resultSet.getString(NAME));
        film.setGanre(resultSet.getString(GANRE));
        film.setDirector(resultSet.getString(DIRECTOR));
        return film;
    }
}
