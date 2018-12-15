package by.tc.web.controller.command.impl;

import by.tc.web.controller.command.Command;
import by.tc.web.entity.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ExitUser implements Command {

    private static final String USER = "user";
    private static final String VALUE = "0";

    @Override
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws ServletException, IOException {
        User user = (User)httpSession.getAttribute(USER);
        httpSession.removeAttribute(USER);
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null)
        {
            for(Cookie c: cookies)
            {
                if(c.getName().equals(user.getLogin()))
                {
                    c.setValue(VALUE);
                    c.setMaxAge(0);
                    httpServletResponse.addCookie(c);
                }
            }
        }
        httpServletResponse.sendRedirect(httpServletRequest.getRequestURI());
    }
}
