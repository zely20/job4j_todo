package ru.job4j.todo.servlet;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.HibernateImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MarkReadyItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getParameter("id");
        Item itemDB = HibernateImpl.instOf().findById(Integer.valueOf(req.getParameter("id")));
        itemDB.setDone(false);
        if(itemDB != null) {
            HibernateImpl.instOf().update(itemDB);
        }
    }
}
