package ru.javaops.masterjava;

import org.thymeleaf.context.WebContext;
import ru.javaops.masterjava.common.web.ThymeleafListener;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.UserDao;
import ru.javaops.masterjava.persist.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/users", loadOnStartup = 1)
public class UserServlet extends HttpServlet {
    private final UserDao userDao = DBIProvider.getDao(UserDao.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final WebContext webContext = new WebContext(req, resp, req.getServletContext(), req.getLocale());
        List<User> users = userDao.getWithLimit(20);
        webContext.setVariable("users", users);
        ThymeleafListener.engine.process("users", webContext, resp.getWriter());
    }
}
