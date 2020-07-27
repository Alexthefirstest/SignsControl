package by.epam.signsControl.webView.tags;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetCurrentDate extends TagSupport {

    private static final long serialVersionUID = -8654754364947529830L;
    private static final Logger logger = LogManager.getLogger(GetCurrentDate.class);

    @Override
    public int doStartTag() throws JspException {

        logger.info("inside teg start");
        try {
            pageContext.getOut().write(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        } catch (IOException e) {
            logger.warn(e);
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }
}
