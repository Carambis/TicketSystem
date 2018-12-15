package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalPageFilm implements Command {
    private static final String LOCAL = "local";
    private static final String ID = "id";
    private static final String COMMAND = "?id=";
    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        httpServletRequest.getSession(true).setAttribute(LOCAL, httpServletRequest.getParameter(LOCAL));
        httpServletResponse.sendRedirect(httpServletRequest.getRequestURI() + COMMAND + httpServletRequest.getParameter(ID));
    }
}
