package by.tc.web.controller.command.impl;

import by.tc.web.controller.ImageController;
import by.tc.web.controller.command.Command;
import by.tc.web.entity.user.User;
import by.tc.web.service.UserService;
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
import java.util.Iterator;
import java.util.List;

public class AddUser implements Command {
    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String STATUS = "status";
    private static final String NICKNAME = "nickname";
    private final static String MESSAGE_CODE = "messageCode";
    private final static String ENCODING = "UTF-8";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        User user = new User();
        boolean result;
        httpSession.setAttribute(MESSAGE_CODE,0);
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List items = upload.parseRequest(httpServletRequest);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                fillObject(item,user);
            }
            result = userService.addUser(user);
            if (!result) {
                httpSession.setAttribute(MESSAGE_CODE, 2);
            }
        } catch (FileUploadException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
            httpSession.setAttribute(MESSAGE_CODE, 3);
        }
        httpServletResponse.sendRedirect(httpServletRequest.getRequestURI());
    }

    private void fillObject(FileItem item, User user)
    {
        String name;
        byte[] data;
        try {
            if (item.isFormField()) {
                switch (item.getFieldName()) {
                    case LOGIN:
                        user.setLogin(item.getString());
                        break;
                    case PASSWORD:
                        user.setPassword(item.getString());
                        break;
                    case EMAIL:
                        user.seteMail(item.getString());
                        break;
                    case STATUS:
                        user.setStatus(Integer.parseInt(item.getString()));
                        break;
                    case NICKNAME:
                        user.setNickname(item.getString(ENCODING));
                        break;
                    default:
                        break;
                }
            } else {
                if (item.getName() != null) {
                    name = item.getName();
                    data = item.get();
                    if (data.length != 0) {
                        ImageController.saveImage(item.getFieldName(), data, name);
                        user.setPathToImage(name);
                    }
                }
            }
        }catch (UnsupportedEncodingException e)
        {
            Logger logger = Logger.getLogger(getClass());
            logger.error(e.getMessage());
        }
    }

}
