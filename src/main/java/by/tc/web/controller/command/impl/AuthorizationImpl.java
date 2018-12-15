package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;
import by.tc.web.entity.user.User;
import by.tc.web.service.UserService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationImpl implements Command {
    public static final String ID = "id";
    private final static String PARAMETER_LOGIN = "fieldLogin";
    private final static String PARAMETER_PASSWORD = "fieldPassword";
    private final static String PARAMETER_USER = "user";
    private final static String MESSAGE_CODE = "messageCode";
    private static final String COMMAND = "?id=";
    private static final String REMEMBER_ME = "rememberME";
    private static final int ONE_DAY = 24 * 60 * 60;
    private static final int UNTIL_BROWSER_CLOSE = -1;
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                        HttpSession httpSession) throws ServletException, IOException {
        User user;
        String login;
        String password;
        String idFilm;
        httpSession.setAttribute(MESSAGE_CODE,0);
        login = httpServletRequest.getParameter(PARAMETER_LOGIN);
        password = httpServletRequest.getParameter(PARAMETER_PASSWORD);
        idFilm = httpServletRequest.getParameter(ID);
        try {
            user = userService.authorization(login, password);
            if (user.getLogin() != null && user.getBan() == 0) {
                httpSession.setAttribute(PARAMETER_USER, user);
                putCookie(httpServletRequest, httpServletResponse, user);
            } else if (user.getLogin() != null && user.getBan() != 0) {
                httpSession.setAttribute(MESSAGE_CODE, 8);
            } else {
                httpSession.setAttribute(MESSAGE_CODE, 1);
            }
            if (idFilm != null) {
                httpServletResponse.sendRedirect(httpServletRequest.getRequestURI() + COMMAND + idFilm);
            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getRequestURI());
            }
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
    }

    private void putCookie(HttpServletRequest request, HttpServletResponse response, User user) {
        String rememberMe = request.getParameter(REMEMBER_ME);
        Cookie cookie = new Cookie(user.getLogin(), user.getPassword());
        int cookieLife;
        cookieLife = rememberMe != null ? ONE_DAY : UNTIL_BROWSER_CLOSE;
        cookie.setMaxAge(cookieLife);
        response.addCookie(cookie);
    }
}
