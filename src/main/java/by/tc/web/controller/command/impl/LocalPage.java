package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalPage implements Command {
    private static final String LOCAL = "local";
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpSession httpSession) throws ServletException, IOException {
        req.getSession(true).setAttribute(LOCAL, req.getParameter(LOCAL));
        resp.sendRedirect(req.getRequestURI());
    }
}
