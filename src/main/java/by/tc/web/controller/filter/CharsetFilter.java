package by.tc.web.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter{
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig){
        encoding = filterConfig.getInitParameter("characterEncoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        Logger logger = Logger.getLogger(getClass());
        logger.info("Charset was set.");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
