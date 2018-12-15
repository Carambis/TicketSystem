package by.tc.web.service.validation;

import by.tc.web.entity.film.Film;
import by.tc.web.entity.film.Mark;

public class ValidatorFilm {

    private static final String REGEX_STRING = "[a-zA-Zа-яА-Я ,&()]{1,70}";
    private static final int MAX_LENGHT_DESCRIPTION = 5000;
    private static final String REGEX_DURATION = "^[0-9]{2,3}$";

    public static boolean setCommentValidator(Mark mark) {
        if (!ValidatorGeneral.validationId(mark.getFilmId())) {
            return false;
        }
        if (!ValidatorGeneral.validationId(mark.getUserId())) {
            return false;
        }
        if (!(mark.getMark() > 0 && mark.getMark() <= 5)) {
            return false;
        }

        if (!(mark.getComment().length() < 200)) {
            return false;
        }
        return true;
    }

    public static boolean addFilmValidator(Film film) {
        if (film.getName().isEmpty()
                || film.getGanre().isEmpty()
                || film.getDirector().isEmpty()
                || film.getDuration().isEmpty()
                || film.getDatePrimer() == null
                || film.getDescription().isEmpty()
                || film.getNameImage().isEmpty()
                || film.getNamePoster().isEmpty()
                || film.getLang().isEmpty()) {
            return false;
        }
        if (!film.getGanre().matches(REGEX_STRING)) {
            return false;
        }
        if (!film.getDirector().matches(REGEX_STRING)) {
            return false;
        }
        if (!film.getDuration().matches(REGEX_DURATION)) {
            return false;
        }
        if (film.getDescription().length() > MAX_LENGHT_DESCRIPTION) {
            return false;
        }
        return true;
    }

    public static boolean checkFilmInfo(Film film)
    {
        if (film.getName().isEmpty()
                || film.getGanre().isEmpty()
                || film.getDirector().isEmpty()
                || film.getDescription().isEmpty()
                || film.getLang().isEmpty()) {
            return false;
        }
        if (!film.getGanre().matches(REGEX_STRING)) {
            return false;
        }
        if (!film.getDirector().matches(REGEX_STRING)) {
            return false;
        }
        if (film.getDescription().length() > MAX_LENGHT_DESCRIPTION) {
            return false;
        }
        return true;
    }

    public static boolean editFilmValidator(Film film) {
        if (film.getName().isEmpty()
                || film.getGanre().isEmpty()
                || film.getDirector().isEmpty()
                || film.getDuration().isEmpty()
                || film.getDatePrimer() == null
                || film.getDescription().isEmpty()
               ){
            return false;
        }
        if (!film.getGanre().matches(REGEX_STRING)) {
            return false;
        }
        if (!film.getDirector().matches(REGEX_STRING)) {
            return false;
        }
        if (!film.getDuration().matches(REGEX_DURATION)) {
            return false;
        }
        if (!(film.getDescription().length() < MAX_LENGHT_DESCRIPTION)) {
            return false;
        }
        return true;
    }

    public static boolean alighnValidator(String nameFilm) {
        return !nameFilm.isEmpty();
    }

    public static boolean langValidator(String lang) {
        return !lang.isEmpty();
    }
}
