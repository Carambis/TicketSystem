package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;
import by.tc.web.entity.film.CommentForDelete;
import by.tc.web.entity.film.Film;
import by.tc.web.entity.user.Ban;
import by.tc.web.entity.user.User;
import by.tc.web.service.FilmService;
import by.tc.web.service.UserService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FillAdminMenu implements Command {

    private static final String LOCAL = "local";
    private static final String LIST_FILM = "listFilm";
    private static final String USER_LIST = "userList";
    private static final String BAN_LIST = "banList";
    private static final String COMMENTS = "comments";
    private static final String PATH = "/WEB-INF/jsp/admin.jsp";
    private final static FilmService filmService = ServiceFactory.getInstance().getFilmService();
    private final static UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String USER = "user";
    private static final String HOME = "/home";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        List<Film> list = null;
        List<User> userList = null;
        List<Ban> banList = null;
        List<CommentForDelete> comment = null;

        if(httpSession.getAttribute(USER) == null){
            httpServletResponse.sendRedirect(HOME);
            return;
        }
        try {
            list = filmService.getAllInfAllFilm();
            userList = userService.getAllUser((String) httpSession.getAttribute(LOCAL));
            banList = userService.getAllBanReason((String) httpSession.getAttribute(LOCAL));
            comment = filmService.getAllComment((String) httpSession.getAttribute(LOCAL));
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }

        httpServletRequest.setAttribute(LIST_FILM, list);
        httpServletRequest.setAttribute(USER_LIST, userList);
        httpServletRequest.setAttribute(BAN_LIST, banList);
        httpServletRequest.setAttribute(COMMENTS, comment);

        httpServletRequest.getRequestDispatcher(PATH).forward(httpServletRequest, httpServletResponse);
    }
}
