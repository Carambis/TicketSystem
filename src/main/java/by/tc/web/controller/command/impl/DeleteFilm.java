package by.tc.web.controller.command.impl;

import by.tc.web.controller.ImageController;
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

public class DeleteFilm implements Command {
    private static final String ID = "id";
    private static final String LANG = "lang";
    private static final String POSTER = "poster";
    private static final String WIDE_SCREEN = "wideScreen";
    private static final String OLD_POSTER = "oldPoster";
    private static final String OLD_WIDE_SCREEN = "oldWideScreen";
    private final static String MESSAGE_CODE = "messageCode";
    private static FilmService filmService = ServiceFactory.getInstance().getFilmService();
    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        boolean result;
        httpSession.setAttribute(MESSAGE_CODE,0);
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        String lang = httpServletRequest.getParameter(LANG);
        try {
            result = filmService.deleteFilm(id,lang);
            if(result) {
                ImageController.deleteImage(POSTER, httpServletRequest.getParameter(OLD_POSTER));
                ImageController.deleteImage(WIDE_SCREEN, httpServletRequest.getParameter(OLD_WIDE_SCREEN));
            }
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
            httpSession.setAttribute(MESSAGE_CODE,6);
        }
        httpServletResponse.sendRedirect(httpServletRequest.getRequestURI());
    }
}
