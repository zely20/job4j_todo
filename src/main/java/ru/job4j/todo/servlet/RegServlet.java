package ru.job4j.todo.servlet;

import ru.job4j.todo.model.User;
import ru.job4j.todo.store.HibernateImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        User user = HibernateImpl.instOf().findByName(name);
        if (user != null) {
            req.setAttribute("error", "Пользователь с данным email уже зарегистрирован");
            req.getRequestDispatcher("reg.html").forward(req, resp);
        } else {
            HibernateImpl.instOf().saveUser(
                    new User(req.getParameter("name"), req.getParameter("password"))
            );
            resp.sendRedirect(req.getContextPath());
        }
    }
}
