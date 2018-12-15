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

public class EditFIlm implements Command {
    private static final String NAME_FILM = "nameFilm";
    private static final String GANRE = "ganre";
    private static final String DURATION = "duration";
    private static final String DESCRIPTION = "descr";
    private static final String LANG = "lang";
    private static final String DIRECTOR = "director";
    private static final String POSTER = "poster";
    private final static FilmService filmService = ServiceFactory.getInstance().getFilmService();
    private static final String DATE = "date";
    private static final String ID = "id";
    private static final String OLD_POSTER = "oldPoster";
    private static final String OLD_WIDE_SCREEN = "oldWideScreen";
    private static final String EMPTY_STRING = "";
    private static final String WIDE_SCREEN = "wideScreen";
    private final static String MESSAGE_CODE = "messageCode";
    private static final String ENCODING = "UTF-8";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        Film film = new Film();
        boolean result;
        httpSession.setAttribute(MESSAGE_CODE,0);
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List items = upload.parseRequest(httpServletRequest);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                fillObject(item, film);
            }
            result = filmService.editFilm(film);
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

    private void fillObject(FileItem item, Film film) {
        String oldPoster = null;
        String oldWideScreen = null;
        try {
            if (item.isFormField()) {
                switch (item.getFieldName()) {
                    case ID:
                        film.setId(Integer.parseInt(item.getString()));
                        break;
                    case NAME_FILM:
                        film.setName(item.getString(ENCODING));
                        break;
                    case GANRE:
                        film.setGanre(item.getString(ENCODING));
                        break;
                    case DURATION:
                        film.setDuration(item.getString(ENCODING));
                        break;
                    case DATE:
                        film.setDatePrimer(Date.valueOf(item.getString()));
                        break;
                    case DESCRIPTION:
                        film.setDescription(item.getString(ENCODING));
                        break;
                    case DIRECTOR:
                        film.setDirector(item.getString(ENCODING));
                        break;
                    case LANG:
                        film.setLang(item.getString(ENCODING));
                        break;
                    case OLD_POSTER:
                        film.setNamePoster(item.getString());
                        break;
                    case OLD_WIDE_SCREEN:
                        film.setNameImage(item.getString());
                        break;
                    default:
                        break;
                }
            } else {
                saveImage(item, film, film.getNamePoster(), film.getNameImage());
            }
        }catch (UnsupportedEncodingException e)
        {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
    }

    private void saveImage(FileItem item, Film film, String oldPoster, String oldWideScreen) {
        String name;
        byte[] data;
        if (item.getName() != null && !item.getName().equalsIgnoreCase(EMPTY_STRING)) {
            name = item.getName();
            data = item.get();
            String typeName = item.getFieldName();
            name = ImageController.saveImage(item.getFieldName(), data, name);
            if (typeName.equalsIgnoreCase(POSTER)) {
                film.setNamePoster(name);
                ImageController.deleteImage(POSTER, oldPoster);
            } else {
                film.setNameImage(name);
                ImageController.deleteImage(WIDE_SCREEN, oldWideScreen);
            }
        } else if (item.getName() != null) {
            String typeName = item.getFieldName();
            if (typeName.equalsIgnoreCase(POSTER)) {
                film.setNamePoster(oldPoster);
            } else {
                film.setNameImage(oldWideScreen);
            }
        }
    }

}
