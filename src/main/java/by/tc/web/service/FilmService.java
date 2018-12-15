package by.tc.web.service;

import by.tc.web.entity.film.*;
import by.tc.web.service.exception.ServiceException;

import java.util.List;

public interface FilmService {
    List<Film> getListRatingOnMounth(String lang) throws ServiceException;

    List<Film> getCorusel(String lang) throws ServiceException;

    List<Film> getAllFilm(String lang) throws ServiceException;

    Film getInfFilm(String lang, int id) throws ServiceException;

    Comment getCurUserComment(int userId, int filmId) throws ServiceException;

    void setUserComment(Mark mark) throws ServiceException;

    List<Comment> getCommentCritic(int filmId) throws ServiceException;

    List<Comment> getCommentUser(int filmId) throws ServiceException;

    boolean addFilm(Film... films) throws ServiceException;

    List<Film> getAllInfAllFilm() throws ServiceException;

    int getAlig(String nameFilm) throws ServiceException;

    boolean editFilm(Film film) throws ServiceException;

    boolean deleteFilm(int id, String lang) throws ServiceException;

    List<CommentForDelete> getAllComment(String lang) throws ServiceException;

    void deleteComment(int userId, int filmId) throws ServiceException;

    Score getScore(int filmId) throws ServiceException;

    List<Film> getFilmForSearch(String lang, String nameFilm) throws ServiceException;
}
