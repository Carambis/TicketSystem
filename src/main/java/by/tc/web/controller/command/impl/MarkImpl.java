package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;
import by.tc.web.entity.film.Mark;
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

public class MarkImpl implements Command {
    private static final FilmService filmService = ServiceFactory.getInstance().getFilmService();
    private static final String REVIEW_STARS = "reviewStars";
    private static final String USER = "user";
    public static final String ID = "id";
    private static final String COMMENT = "comment";
    private static final String PATH = "/film?id=";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        int mark = Integer.parseInt(httpServletRequest.getParameter(REVIEW_STARS));
        int filmId = Integer.parseInt(httpServletRequest.getParameter(ID));
        User user = (User)httpSession.getAttribute(USER);
        String comment = httpServletRequest.getParameter(COMMENT);
        Mark userMark = new Mark(filmId,user.getId(),mark,comment);
        try {
            filmService.setUserComment(userMark);
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }

        httpServletResponse.sendRedirect(PATH + filmId);
    }
}
