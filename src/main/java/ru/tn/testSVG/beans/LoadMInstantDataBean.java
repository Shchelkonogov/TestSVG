package ru.tn.testSVG.beans;

import ru.tn.testSVG.model.MnemonicData;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Stateless бин для загрузки мгновенных данных объекта
 * для мнемосхемы объекта
 */
@Stateless(name = "LoadMInstantDataBean")
public class LoadMInstantDataBean implements InMDataBeanLocal {

    private static final String GET_MUID_SQL = "{? = call mnemo.set_mnemo_async_request(?)}";
    private static final String GET_STATUS_SQL = "select mnemo.get_mnemo_async_status(?) from dual";
    private static final String GET_DATA_SQL = "select * from table (mnemo.get_mnemo_async_data(?))";
    private static final String NLS_SQL = "alter session set NLS_NUMERIC_CHARACTERS='.,'";

    @Resource(name = "OracleDataSource", mappedName = "jdbc/OracleDataSource")
    private DataSource ds;

    @EJB
    private ParseMDataBean bean;

    @Override
    public List<MnemonicData> getData(String object) {
        List<MnemonicData> result = new ArrayList<>();
        String muid = null;
        try(Connection connect = ds.getConnection();
                CallableStatement stm = connect.prepareCall(GET_MUID_SQL)) {
            stm.setString(2, object);
            stm.registerOutParameter(1, Types.VARCHAR);
            stm.execute();

            muid = stm.getString(1);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        System.out.println("LoadMInstantDataBean.getData muid: " + muid + " for object: " + object + " load instant data");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("LoadMInstantDataBean.getData waiting: " + ((i + 1) * 6000) + " ms for object: " + object);

            try(Connection connect = ds.getConnection();
                    PreparedStatement stmGetStatus = connect.prepareStatement(GET_STATUS_SQL);
                    PreparedStatement stmGetData = connect.prepareStatement(GET_DATA_SQL);
                    PreparedStatement stmNls = connect.prepareStatement(NLS_SQL)) {
                stmNls.executeQuery();

                stmGetStatus.setString(1, muid);

                ResultSet res = stmGetStatus.executeQuery();
                while(res.next()) {
                    if (Objects.nonNull(res.getString(1))) {
                        stmGetData.setString(1, muid);

                        ResultSet resData = stmGetData.executeQuery();
                        bean.parseData(result, resData);

                        boolean noData = true;
                        for (int j = 5; j < result.size(); j++) {
                            if (result.get(j).getData() != null
                                    && !result.get(j).getData().equals("-")
                                    && !result.get(j).getData().substring(result.get(j).getData().length() - 1).equals("=")) {
                                noData = false;
                                break;
                            }
                        }
                        if (noData) {
                            result.get(2).setData("Невозможно получить данные с объекта");
                        }

                        System.out.println("LoadMInstantDataBean.getData data load for object: " + object);
                        return result;
                    } else {
                        System.out.println("LoadMInstantDataBean.getData waiting data for object: " + object);
                    }
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("LoadMInstantDataBean.getData no data load for object: " + object);
        return result;
    }
}
