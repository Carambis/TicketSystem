package by.tc.web.controller;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class JSPFooterTag extends TagSupport {
    private String data;

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print("<span>" + data+ "</span>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
