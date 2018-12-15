package by.tc.web.controller.command.impl;

import by.tc.web.controller.ImageController;
import by.tc.web.controller.command.Command;
import by.tc.web.entity.film.Film;
import by.tc.web.service.FilmService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.factory.ServiceFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

public class AddFilmImpl implements Command {
    private static final String RU = "Ru";
    private static final String EN = "En";
    private static final String PARAMETER_NAME_FILM = "nameFilm";
    private static final String PARAMETER_GENRE = "genre";
    private static final String PARAMETER_DURATION = "duration";
    private static final String PARAMETER_DESCRIPTION = "descr";
    private static final String PARAMETER_DIRECTOR = "director";
    private static final String POSTER = "poster";
    private final static FilmService filmService = ServiceFactory.getInstance().getFilmService();
    private static final String PARAMETER_DATE = "date";
    private final static String MESSAGE_CODE = "messageCode";
    private static final String ENCODING = "UTF-8";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        Film filmRu = new Film();
        filmRu.setLang(RU.toLowerCase());
        Film filmEn = new Film();
        filmEn.setLang(EN.toLowerCase());
        boolean result;
        httpSession.setAttribute(MESSAGE_CODE,0);
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List items = upload.parseRequest(httpServletRequest);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                fillObject(item, filmRu, filmEn);
            }
            result = filmService.addFilm(filmEn, filmRu);
            if (!result) {
                httpSession.setAttribute(MESSAGE_CODE, 7);
            }
        } catch (FileUploadException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
            httpSession.setAttribute(MESSAGE_CODE, 4);
        }
        httpServletResponse.sendRedirect(httpServletRequest.getRequestURI());
    }

    private void fillObject(FileItem item, Film filmRu, Film filmEn) {
        String name;
        byte[] data;
        try {
            if (item.isFormField()) {
                switch (item.getFieldName()) {
                    case PARAMETER_NAME_FILM + RU:
                        filmRu.setName(item.getString(ENCODING));
                        break;
                    case PARAMETER_NAME_FILM + EN:
                        filmEn.setName(item.getString());
                        break;
                    case PARAMETER_GENRE + RU:
                        filmRu.setGanre(item.getString(ENCODING));
                        break;
                    case PARAMETER_GENRE + EN:
                        filmEn.setGanre(item.getString());
                        break;
                    case PARAMETER_DURATION:
                        filmEn.setDuration(item.getString(ENCODING));
                        break;
                    case PARAMETER_DATE:
                        filmEn.setDatePrimer(Date.valueOf(item.getString()));
                        break;
                    case PARAMETER_DESCRIPTION + RU:
                        filmRu.setDescription(item.getString(ENCODING));
                        break;
                    case PARAMETER_DESCRIPTION + EN:
                        filmEn.setDescription(item.getString());
                        break;
                    case PARAMETER_DIRECTOR + RU:
                        filmRu.setDirector(item.getString(ENCODING));
                        break;
                    case PARAMETER_DIRECTOR + EN:
                        filmEn.setDirector(item.getString());
                        break;
                    default:
                        break;
                }
            } else {
                if (item.getName() != null) {
                    name = item.getName();
                    data = item.get();
                    ImageController.saveImage(item.getFieldName(), data, name);
                    if (item.getFieldName().equalsIgnoreCase(POSTER)) {
                        filmEn.setNamePoster(name);
                    } else {
                        filmEn.setNameImage(name);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
    }
}
