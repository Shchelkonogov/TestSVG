package ru.tn.testSVG.beans;

import ru.tn.testSVG.model.MnemonicData;

import javax.ejb.Stateless;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class ParseMDataBean {

    public void parseData(List<MnemonicData> result, ResultSet resData) throws SQLException {
        String color;
        while(resData.next()) {
            switch(resData.getInt(2)) {
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
                    color = "lightGrey";
                    break;
                }
                default: color = "white";
            }

            result.add(new MnemonicData(resData.getString(1).replaceAll("'", "\\\\'"),
                    color, resData.getString(3), resData.getString(4)));
        }
    }
}
