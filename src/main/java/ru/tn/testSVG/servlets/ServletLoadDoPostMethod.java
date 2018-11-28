package ru.tn.testSVG.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tn.testSVG.beans.InMDataBeanLocal;
import ru.tn.testSVG.model.MnemonicData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Метод doPost для сервлетов загрузки мгновенных и архивных данных
 */
class ServletLoadDoPostMethod {

    static void doPost(HttpServletRequest req, HttpServletResponse resp, InMDataBeanLocal bean) throws IOException {
        String objId = req.getParameter("objId");
        List<MnemonicData> mData = bean.getData(objId);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(mData));

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(mData));
    }
}
