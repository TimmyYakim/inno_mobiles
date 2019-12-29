package com.inno.servlet;

import com.inno.dao.MobileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Timofey Yakimov
 */
@WebListener
public class AppAttributeContextListener implements ServletContextAttributeListener {

    private Logger logger = LoggerFactory.getLogger(AppAttributeContextListener.class);

    @Inject
    private MobileDao mobileDao;

    @Override
    public void attributeAdded(ServletContextAttributeEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        Boolean isLoggedIn = (Boolean)servletContext.getAttribute("isLoggedIn");
        if (isLoggedIn != null && isLoggedIn) {
            servletContext.setAttribute("dao", mobileDao);
            mobileDao.createTable();
            logger.info("Added attribute DAO");
        }
    }

}
