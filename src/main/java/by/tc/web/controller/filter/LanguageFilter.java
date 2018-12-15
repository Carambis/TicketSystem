package by.tc.web.controller.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LanguageFilter implements Filter {

    private static final String LOCAL = "local";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession(true);
        if(session.getAttribute(LOCAL) == null){
            String defaultLocale = Locale.getDefault().getLanguage();
            session.setAttribute(LOCAL, defaultLocale);
            Logger logger = Logger.getLogger(getClass());
            logger.info("LanguageFilter: set default locale(" + defaultLocale+ ")");
        }
        chain.doFilter(request,response);
    }
}
