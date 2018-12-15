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

public class FillPageFilms implements Command {
    private static final String LOCAL = "local";
    private final static String PATH = "listFilm.jsp";
    private final FilmService filmService = ServiceFactory.getInstance().getFilmService();

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        List<Film> list;
        try {
            list = filmService.getAllFilm(httpSession.getAttribute(LOCAL).toString());
            PageFilms.makePage(httpServletRequest,list);
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
        httpServletRequest.getRequestDispatcher(PATH).forward(httpServletRequest, httpServletResponse);
    }
}
