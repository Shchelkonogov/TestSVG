package ru.tn.testSVG;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "servlet", urlPatterns = "test")
public class Servlet extends HttpServlet {

    private int i = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("OK");

        System.out.println("old: " + i);
        i++;
        System.out.println("new: " + i);

        response.setContentType("text/plain");
        response.getWriter().write("TEST" + i);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
