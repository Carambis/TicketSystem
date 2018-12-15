package by.tc.web.controller.command.impl;

import by.tc.web.controller.ImageController;
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

public class DeleteUser implements Command {
    private final static String MESSAGE_CODE = "messageCode";
    private final static UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String DEFAULT_JPG = "default.jpg";
    private static final String ID = "id";
    private static final String OLD_USER_PIC = "oldUserPic";
    private static final String USER_PIC = "userPic";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        int id = Integer.parseInt(httpServletRequest.getParameter(ID));
        String image = httpServletRequest.getParameter(OLD_USER_PIC);
        httpSession.setAttribute(MESSAGE_CODE,0);
        try {
            userService.deleteUser(id);
            if (!image.equals(DEFAULT_JPG)) {
                ImageController.deleteImage(USER_PIC, image);
            }
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
            httpSession.setAttribute(MESSAGE_CODE, 6);
        }
    }
}
