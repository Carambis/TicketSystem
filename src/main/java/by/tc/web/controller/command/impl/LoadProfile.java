package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoadProfile implements Command {

    private static final String PATH = "WEB-INF/jsp/editUser.jsp";
    private static final String USER = "user";
    private static final String HOME = "/home";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        if (httpSession.getAttribute(USER) == null) {
            httpServletResponse.sendRedirect(HOME);
            return;
        }

        httpServletRequest.getRequestDispatcher(PATH).forward(httpServletRequest, httpServletResponse);
    }
}
