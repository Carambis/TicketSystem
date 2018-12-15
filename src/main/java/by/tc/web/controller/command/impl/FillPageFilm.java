package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;
import by.tc.web.entity.film.Comment;
import by.tc.web.entity.film.Film;
import by.tc.web.entity.film.Score;
import by.tc.web.entity.user.User;
import by.tc.web.service.FilmService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FillPageFilm implements Command {
    private final static String ID = "id";
    private final static String LOCAL = "local";
    private static final String USER = "user";
    private static final String LIST_CRITIC = "listCritic";
    private static final String LIST_USERS = "listUsers";
    private static final String FILM = "film";
    private static final String COMMENT = "comment";
    private static final String PATH = "film.jsp";
    private static final String SCORE = "score";
    private final FilmService filmService = ServiceFactory.getInstance().getFilmService();

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        List<Comment> list;
        int filmId = Integer.parseInt(httpServletRequest.getParameter(ID));
        User user = (User) httpSession.getAttribute(USER);
        Comment curUserComment = null;
        Score score= null;
        String lang = httpSession.getAttribute(LOCAL).toString();
        try {

            Film film = filmService.getInfFilm(lang, filmId);
            httpServletRequest.setAttribute(FILM, film);
            if (user != null) {
                curUserComment = filmService.getCurUserComment(filmId, user.getId());
            }

            list = filmService.getCommentCritic(filmId);
            httpServletRequest.setAttribute(LIST_CRITIC, list);

            list = filmService.getCommentUser(filmId);

            httpServletRequest.setAttribute(LIST_USERS, list);

            httpServletRequest.setAttribute(COMMENT, curUserComment);

            score = filmService.getScore(filmId);

            httpServletRequest.setAttribute(SCORE,score);

        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
        httpServletRequest.getRequestDispatcher(PATH).forward(httpServletRequest, httpServletResponse);
    }
}
