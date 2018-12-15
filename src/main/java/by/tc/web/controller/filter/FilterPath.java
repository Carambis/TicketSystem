package by.tc.web.controller.filter;

import by.tc.web.controller.CommandName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterPath implements Filter {
    private static final String PARAMETER_COMMAND = "command";
    private static final String HOME = "/home";
    private static final String INVALID = "/";
    private static final String COMMAND = "?command=";
    private static final String FILMS = "/films";
    private static final String FILM = "/film";
    private static final String ADMIN_MENU = "/adminMenu";
    private static final String ID = "id";
    private static final String COMMAND_ID = "&id=";
    private static final String EDIT_USER = "/editUser";
    private static final String PAGE = "page";
    private static final String COMMAND_PAGE = "&page=";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String loginURI = req.getRequestURI();
        String command = req.getParameter(PARAMETER_COMMAND);
        if (command == null) {
            if (loginURI.equals(INVALID) || (loginURI.equals(HOME))) {
                resp.sendRedirect(HOME + COMMAND + CommandName.FILL_PAGE_INDEX);
            } else if (loginURI.equals(FILMS)) {
                String page = req.getParameter(PAGE);
                if (page == null) {
                    resp.sendRedirect(FILMS + COMMAND + CommandName.FILL_PAGE_FILMS);
                } else {
                    resp.sendRedirect(FILMS + COMMAND + CommandName.FILL_PAGE_FILMS + COMMAND_PAGE + page);
                }
            } else if (loginURI.equals(FILM)) {
                String id = req.getParameter(ID);
                resp.sendRedirect(FILM + COMMAND + CommandName.FILL_PAGE_FILM + COMMAND_ID + id);
            } else if (loginURI.equals(ADMIN_MENU)) {
                resp.sendRedirect(ADMIN_MENU + COMMAND + CommandName.FILL_ADMIN_MENU);
            } else if (loginURI.equals(EDIT_USER)) {
                resp.sendRedirect(EDIT_USER + COMMAND + CommandName.LOAD_PROFILE);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
