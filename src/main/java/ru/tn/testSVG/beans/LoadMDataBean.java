package ru.tn.testSVG.beans;

import ru.tn.testSVG.model.MnemonicData;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Stateless бин для загрузки архивных данных объекта
 * для мнемосхемы объекта
 */
@Stateless(name = "LoadMDataBean")
public class LoadMDataBean implements InMDataBeanLocal {

    @Resource(name = "OracleDataSource", mappedName = "jdbc/OracleDataSource")
    private DataSource ds;

    @EJB
    private ParseMDataBean bean;

    private static final String SQL = "select * from table(mnemo.get_mnemo_hist_data(?))";
    private static final String NLS_SQL = "alter session set NLS_NUMERIC_CHARACTERS='.,'";

    @Override
    public List<MnemonicData> getData(String object) {
        List<MnemonicData> result = new ArrayList<>();
        try(Connection connect = ds.getConnection();
                PreparedStatement stmNls = connect.prepareStatement(NLS_SQL);
                PreparedStatement stm = connect.prepareStatement(SQL)) {
            stmNls.executeQuery();

            stm.setString(1, object);
            ResultSet res = stm.executeQuery();

            bean.parseData(result, res);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
