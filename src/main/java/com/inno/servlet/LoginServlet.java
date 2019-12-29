package com.inno.servlet;

import com.inno.dao.UserDao;
import com.inno.pojo.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Timofey Yakimov
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("dao");
        if (userDao != null) {
            userDao.createTable();
            User testUser = new User.Builder("test", "test")
                    .withPassword("test")
                    .withPhone("03")
                    .withEmail("test")
                    .build();
            if (!userDao.addUser(testUser)) {
                throw new IllegalArgumentException("Can't add a testUser");
            }
        }
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userDao.getUserByLoginAndPassword(login, password);
        if (user == null) {
            throw new IllegalAccessError("No such user or password");
        }
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("isLoggedIn", true);
        req.getRequestDispatcher("/main.jsp")
                .forward(req, resp);
    }

}
