package by.tc.web.dao;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.entity.film.*;
import by.tc.web.entity.user.UserMarkStatus;

import java.util.List;

public interface FilmDAO {
    List<Film> getListRatingOnMounth(String lang) throws DAOException;

    List<Film> getListNewFilm(String lang) throws DAOException;

    List<Film> getAllFilm(String lang) throws DAOException;

    Film getInfFilm(String lang, int id) throws DAOException;

    Comment getCurUserComment(int userId, int filmId) throws DAOException;

    void setUserComment(Mark mark) throws DAOException;

    List<Comment> getCommentCritic(int filmId) throws DAOException;

    List<Comment> getCommentUser(int filmId) throws DAOException;

    void addFilm(Film[] films) throws DAOException;

    List<Film> getAllInfAllFilm() throws DAOException;

    int getAlig(String nameFilm) throws DAOException;

    void editFilm(Film film) throws DAOException;

    void deleteFilm(int id, String lang) throws DAOException;

    List<CommentForDelete> getAllComment(String lang) throws DAOException;

    void deleteComment(int userId, int filmId) throws DAOException;

    Score getScore(int filmId) throws DAOException;

    List<Film> getFilmForSearch(String nameFilm, String lang) throws DAOException;

    int getCountUsersMark(int filmId) throws DAOException;

    List<UserMarkStatus> getUsersMarkAndStatus(int filmId) throws DAOException;

    double getAvgScore(int filmId) throws DAOException;
}
