package by.tc.web.service.factory;

import by.tc.web.service.FilmService;
import by.tc.web.service.UserService;
import by.tc.web.service.impl.FilmServiceImpl;
import by.tc.web.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private UserService userService = new UserServiceImpl();
    private FilmService filmService = new FilmServiceImpl();

    private ServiceFactory() {

    }

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public FilmService getFilmService() {
        return filmService;
    }
}
