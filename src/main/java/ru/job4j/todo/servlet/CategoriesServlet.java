package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.HibernateImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoriesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = HibernateImpl.instOf().getCategories();
        String json = new Gson().toJson(categories);
        resp.setContentType("json");
        resp.getWriter().write(json);
    }
}
