package ru.tn.testSVG.beans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class LoadSvgBean {

    private static final String SQL = "select mnemo.get_mnemo_type (?) from dual";

    @Resource(name = "OracleDataSource", mappedName = "jdbc/OracleDataSource")
    private DataSource ds;

    public String loadSvgName(String object) {
        String result = null;
        try(Connection connect = ds.getConnection();
                PreparedStatement stm = connect.prepareStatement(SQL)) {
            stm.setString(1, object);

            ResultSet res = stm.executeQuery();
            while(res.next()) {
                result = res.getString(1);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
