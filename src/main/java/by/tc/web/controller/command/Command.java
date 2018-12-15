package by.tc.web.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface Command {
    void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                 HttpSession httpSession)
            throws ServletException,IOException;
}
