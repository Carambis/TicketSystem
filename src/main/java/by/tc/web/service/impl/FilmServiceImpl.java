package by.tc.web.service.impl;

import by.tc.web.dao.FilmDAO;
import by.tc.web.dao.UserDAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.factory.DAOFactory;
import by.tc.web.entity.film.*;
import by.tc.web.entity.user.UserMarkStatus;
import by.tc.web.service.FilmService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.validation.ValidatorFilm;
import by.tc.web.service.validation.ValidatorGeneral;

import java.util.List;

public class FilmServiceImpl implements FilmService {
    private static final int Status1 = 1;
    private static final int Status2 = 2;
    private static final int Status3 = 3;
    private static final int Status4 = 4;
    private final FilmDAO filmDAO = DAOFactory.getInstance().getFilmDAO();
    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public List<Film> getListRatingOnMounth(String lang) throws ServiceException {
        List<Film> list;
        if (!ValidatorFilm.langValidator(lang)) {
            return null;
        }
        try {
            list = filmDAO.getListRatingOnMounth(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Film> getCorusel(String lang) throws ServiceException {
        List<Film> list = null;
        if (!ValidatorFilm.langValidator(lang)) {
            return null;
        }
        try {
            list = filmDAO.getListNewFilm(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Film> getAllFilm(String lang) throws ServiceException {
        List<Film> list = null;
        if (!ValidatorFilm.langValidator(lang)) {
            return null;
        }
        try {
            list = filmDAO.getAllFilm(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Film getInfFilm(String lang, int id) throws ServiceException {
        Film film = null;
        if (!ValidatorFilm.langValidator(lang)) {
            return null;
        }
        if (!ValidatorGeneral.validationId(id)) {
            return null;
        }
        try {
            film = filmDAO.getInfFilm(lang, id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return film;
    }

    @Override
    public Comment getCurUserComment(int userId, int filmId) throws ServiceException {
        if (!ValidatorGeneral.validationId(userId)) {
            return null;
        }
        if (!ValidatorGeneral.validationId(filmId)) {
            return null;
        }
        try {
            return filmDAO.getCurUserComment(userId, filmId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void setUserComment(Mark mark) throws ServiceException {
        if (!ValidatorFilm.setCommentValidator(mark)) {
            return;
        }
        try {
            filmDAO.setUserComment(mark);
            editStatusUser(mark.getFilmId());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Comment> getCommentCritic(int filmId) throws ServiceException {
        if (!ValidatorGeneral.validationId(filmId)) {
            return null;
        }
        try {
            return filmDAO.getCommentCritic(filmId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Comment> getCommentUser(int filmId) throws ServiceException {
        if (!ValidatorGeneral.validationId(filmId)) {
            return null;
        }
        try {
            return filmDAO.getCommentUser(filmId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Film> getAllInfAllFilm() throws ServiceException {
        try {
            return filmDAO.getAllInfAllFilm();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean addFilm(Film... films) throws ServiceException {
        if (!ValidatorFilm.addFilmValidator(films[0])) {
            return false;
        }
        if(!ValidatorFilm.checkFilmInfo(films[1])) {
            return false;
        }
        try {
            filmDAO.addFilm(films);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public int getAlig(String nameFilm) throws ServiceException {
        int count = 0;
        nameFilm = nameFilm.substring(0, nameFilm.indexOf('.'));
        if (!ValidatorFilm.alighnValidator(nameFilm)) {
            return -1;
        }
        try {
            count = filmDAO.getAlig(nameFilm);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }

    @Override
    public boolean editFilm(Film film) throws ServiceException {
        if (!ValidatorFilm.editFilmValidator(film)) {
            return false;
        }
        try {
            filmDAO.editFilm(film);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public boolean deleteFilm(int id, String lang) throws ServiceException {
        if (!ValidatorGeneral.validationId(id)) {
            return false;
        }
        if (!ValidatorFilm.langValidator(lang)) {
            return false;
        }
        try {
            filmDAO.deleteFilm(id, lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public List<CommentForDelete> getAllComment(String lang) throws ServiceException {
        if (!ValidatorFilm.langValidator(lang)) {
            return null;
        }
        try {
            return filmDAO.getAllComment(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteComment(int userId, int filmId) throws ServiceException {
        if (!ValidatorGeneral.validationId(userId)) {
            return;
        }
        if (!ValidatorGeneral.validationId(filmId)) {
            return;
        }
        try {
            filmDAO.deleteComment(userId, filmId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Score getScore(int filmId) throws ServiceException {
        Score score = null;
        if (!ValidatorGeneral.validationId(filmId)) {
            return null;
        }
        try {
            score = filmDAO.getScore(filmId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return score;
    }

    @Override
    public List<Film> getFilmForSearch(String lang, String nameFilm) throws ServiceException {
        List<Film> list;
        if (!ValidatorFilm.langValidator(lang)) {
            return null;
        }
        if (!ValidatorFilm.alighnValidator(nameFilm)) {
            return null;
        }
        try {
            list = filmDAO.getFilmForSearch(nameFilm, lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return list;
    }

    private void editStatusUser(int filmId) throws ServiceException {
        List<UserMarkStatus> userMarkStatuses;
        double avgScoreFilm;
        int countMark;
        int userMark;
        try {
            countMark = filmDAO.getCountUsersMark(filmId);
            if (countMark > 15) {
                userMarkStatuses = filmDAO.getUsersMarkAndStatus(filmId);
                avgScoreFilm = filmDAO.getAvgScore(filmId);
                for (int i = 0; i < userMarkStatuses.size(); i++) {
                    userMark = userMarkStatuses.get(i).getUserMark();
                    if (userMark < avgScoreFilm + 1 && userMark > avgScoreFilm - 1) {
                        statusUp(userMarkStatuses.get(i));
                    } else if (userMark < avgScoreFilm + 3 && userMark > avgScoreFilm - 3) {
                        statusDown(userMarkStatuses.get(i));
                    }
                }
                userDAO.setUsersStatus(userMarkStatuses);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private void statusUp(UserMarkStatus user) {
        int status = user.getStatus();
        if (status == 1 || status == 2 || status == 3) {
            status++;
        }
        user.setStatus(status);
    }

    private void statusDown(UserMarkStatus user) {
        int status = user.getStatus();
        if (status == 2 || status == 3 || status == 4) {
            status--;
        }
        user.setStatus(status);
    }
}
