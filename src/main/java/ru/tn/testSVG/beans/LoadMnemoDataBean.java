package ru.tn.testSVG.beans;

import ru.tn.testSVG.model.MnemoData;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class LoadMnemoDataBean {

    @Resource(mappedName = "jdbc/OracleDataSource")
    private DataSource ds;

    private static final String SQL = "select * from table(get_mnemo_p1(?))";

    public List<MnemoData> getData(String object) {
        List<MnemoData> result = new ArrayList<>();
        try(Connection connect = ds.getConnection();
                PreparedStatement pstm = connect.prepareStatement(SQL)) {
            String color;

            pstm.setString(1, object);
            ResultSet res = pstm.executeQuery();
            while(res.next()) {
                switch(res.getInt(2)) {
                    case 1:
                    case 41: {
                        color = "white";
                        break;
                    }
                    case 6:
                    case 2: {
                        color = "red";
                        break;
                    }
                    case 3:
                    case 5: {
                        color = "yellow";
                        break;
                    }
                    case 4: {
                        color = "green";
                        break;
                    }
                    case 7: {
                        color = "grey";
                        break;
                    }
                    default: color = "white";
                }

                result.add(new MnemoData(res.getString(1).replaceAll("'", "\\\\'"),
                        color, res.getString(3)));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
