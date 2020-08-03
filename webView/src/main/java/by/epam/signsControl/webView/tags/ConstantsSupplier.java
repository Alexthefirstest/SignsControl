//package by.epam.signsControl.webView.tags;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.tagext.TagSupport;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * tag
// * <p>
// * return constants
// */
//public class ConstantsSupplier extends TagSupport {
//
//
//    /**
//     * logger
//     */
//    private static final Logger logger = LogManager.getLogger(by.epam.signsControl.webView.tags.ConstantsSupplier.class);
//
//    private static final long serialVersionUID = -6581309001279299615L;
//
//    private String constantName;
//
//    public void setConstantName(String constantName) {
//        this.constantName = constantName;
//    }
//
//    /**
//     * @return return app constant by output stream using server
//     * @throws JspException when catch {@link IOException} from {@link javax.servlet.jsp.JspWriter#write(String)}
//     */
//    @Override
//    public int doStartTag() throws JspException {
//
//        logger.info("inside tag start");
//        try {
//
//
//        } catch (IOException e) {
//            logger.warn(e);
//            throw new JspException(e.getMessage());
//        }
//
//        return SKIP_BODY;
//    }
//}
