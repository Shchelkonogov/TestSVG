package ru.tn.testSVG.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tn.testSVG.beans.LoadMDataBean;
import ru.tn.testSVG.model.MnemonicData;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadMDataServlet", urlPatterns = "/load")
public class LoadMDataServlet extends HttpServlet {

    @Inject
    private LoadMDataBean bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String objId = req.getParameter("objId");
        List<MnemonicData> mData = bean.getData(objId);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(mData));

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(mData));
    }
}
