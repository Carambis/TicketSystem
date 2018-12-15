package by.tc.web.controller;


import by.tc.web.controller.command.Command;
import by.tc.web.entity.user.User;
import by.tc.web.service.UserService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final String COMMAND = "command";
    private static final String LOG_PROPERTIES = "log4j.properties";
    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String USER = "user";
    private static final String EMPTY_STRING = "";
    public static final String MESSAGE_CODE = "messageCode";
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getServletContext().getRealPath(EMPTY_STRING);
        PropertyConfigurator.configure(path + LOG_PROPERTIES);
        Logger log = Logger.getLogger(getClass());
        log.info("Start Servlet");
        HttpSession httpSession = req.getSession();
        checkCookie(httpSession, req);
        String commandName = req.getParameter(COMMAND);
        Command command;
        command = commandProvider.getCommand(commandName);
        command.execute(req, resp, httpSession);
    }

    private void checkCookie(HttpSession httpSession, HttpServletRequest req) {
        User user;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                try {
                    user = userService.authorization(c.getName(), c.getValue());
                    if (user.getLogin() != null) {
                        httpSession.setAttribute(USER, user);
                    }
                } catch (ServiceException e) {
                    Logger logger = Logger.getLogger(getClass());
                    logger.error("User has delete");
                }
            }
        }
    }
}
