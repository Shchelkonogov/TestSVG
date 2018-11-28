package ru.tn.testSVG.servlets;

import ru.tn.testSVG.beans.InMDataBeanLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет загрузки мгновенных данных
 * Запускается из js
 */
@WebServlet(name = "LoadMInstantDataServlet", urlPatterns = "/instLoad")
public class LoadMInstantDataServlet extends HttpServlet {

    @EJB(beanName = "LoadMInstantDataBean")
    private InMDataBeanLocal bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletLoadDoPostMethod.doPost(req, resp, bean);
    }
}
