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

public class FillPageIndex implements Command{
    private final FilmService filmService = ServiceFactory.getInstance().getFilmService();
    private final static String LOCAL = "local";
    private final static String PAGE = "index.jsp";
    private final static String PARAMETER_LIST_RATING = "listFilm";
    private final static String PARAMETER_LIST_CAROUSEL = "listForCarousel";
    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        String lang;

        lang = httpSession.getAttribute(LOCAL).toString();
        try {
            List<Film> list = filmService.getListRatingOnMounth(lang);
            httpServletRequest.setAttribute(PARAMETER_LIST_RATING, list);

            list = filmService.getCorusel(lang);

            httpServletRequest.setAttribute(PARAMETER_LIST_CAROUSEL,list);

            httpServletRequest.getRequestDispatcher(PAGE).forward(httpServletRequest,httpServletResponse);
        }catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
    }
}
