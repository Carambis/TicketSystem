package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;
import by.tc.web.service.UserService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BanUser implements Command {
    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String USER_ID = "userId";
    private static final String BAN = "ban";
    private static final String PARAMETER_NULL = "NULL";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        int userId = Integer.parseInt(httpServletRequest.getParameter(USER_ID));
        int banId;
        try {
            if (httpServletRequest.getParameter(BAN).equals(PARAMETER_NULL)) {
                userService.banUser(userId, null);
            } else {
                banId = Integer.parseInt(httpServletRequest.getParameter(BAN));
                userService.banUser(userId, banId);
            }
        }catch (ServiceException e){
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }

        httpServletResponse.sendRedirect(httpServletRequest.getRequestURI());
    }
}
