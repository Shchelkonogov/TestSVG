package ru.tn.testSVG.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tn.testSVG.beans.LoadMnemoDataBean;
import ru.tn.testSVG.model.MnemoData;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadMnemoDataServlet", urlPatterns = "/load")
public class LoadMnemoDataServlet extends HttpServlet {

    @Inject
    LoadMnemoDataBean bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String objId = req.getParameter("objId");
        List<MnemoData> mnemoData = bean.getData(objId);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(mnemoData));

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(mnemoData));
    }
}
