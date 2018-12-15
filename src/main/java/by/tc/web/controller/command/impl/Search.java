package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;
import by.tc.web.entity.film.Film;
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

public class Search implements Command {
    private static final FilmService filmService = ServiceFactory.getInstance().getFilmService();
    private static final String SEARCH = "search";
    private static final String LOCAL = "local";
    private static final String PATH = "listFilm.jsp";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        String nameFilm = httpServletRequest.getParameter(SEARCH);
        List<Film> list = null;
        try {
            list = filmService.getFilmForSearch((String)httpSession.getAttribute(LOCAL),nameFilm);
            PageFilms.makePage(httpServletRequest,list);
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
        httpServletRequest.getRequestDispatcher(PATH).forward(httpServletRequest,httpServletResponse);

    }
}
