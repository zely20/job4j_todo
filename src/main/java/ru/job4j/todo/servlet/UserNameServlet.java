package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import ru.job4j.todo.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserNameServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        System.out.println(sc.getAttribute("user"));
        String json = new Gson().toJson(user);
        resp.setContentType("json");
        resp.getWriter().write(json);
    }
}
