package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;
import by.tc.web.service.FilmService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteComment implements Command {
    private final static FilmService filmService = ServiceFactory.getInstance().getFilmService();
    private static final String FILM_ID = "filmId";
    private static final String USER_ID = "userId";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        int filmId = Integer.parseInt(httpServletRequest.getParameter(FILM_ID));
        int userId = Integer.parseInt(httpServletRequest.getParameter(USER_ID));
        try {
            filmService.deleteComment(userId,filmId);
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
        httpServletResponse.sendRedirect(httpServletRequest.getRequestURI());
    }
}
