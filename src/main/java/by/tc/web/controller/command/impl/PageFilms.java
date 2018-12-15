package by.tc.web.controller.command.impl;

import by.tc.web.entity.film.Film;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public final class PageFilms {
    private static final String PAGE = "page";
    private static final String LIST_FILM = "listFilm";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String MESSAGE_CODE = "messageCode";
    private static final int RECORDS_PER_PAGE = 5;
    private static final int OFFSET = 1;

    private PageFilms(){

    }

    public static void makePage(HttpServletRequest req, List<Film> list) {
        int page = 1;
        req.getSession().setAttribute(MESSAGE_CODE,0);
        if(list == null || list.isEmpty()) {
            req.setAttribute(MESSAGE_CODE, 9);
            return;
        }

        if (req.getParameter(PAGE) != null) {
            page = Integer.parseInt(req.getParameter(PAGE));
        }

        List<Film> listToDisplay = getRows(list, (page - OFFSET) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
        int numOfRecords = list.size();
        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / RECORDS_PER_PAGE);

        req.setAttribute(LIST_FILM, listToDisplay);
        req.setAttribute(NO_OF_PAGES, numOfPages);
        req.setAttribute(CURRENT_PAGE, page);
    }

    private static List<Film> getRows(List<Film> list, int offset, int rows) {
        List<Film> filmList = new ArrayList<>();
        for (int i = offset; i < offset + rows && i < list.size(); i++) {
            filmList.add(list.get(i));
        }
        return filmList;
    }
}
