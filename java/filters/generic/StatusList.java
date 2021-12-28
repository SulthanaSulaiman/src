/*
 * This class collects status list for the give type like projects, PO,estimation,bill or invoice.
 * It also returns the default status for particular status type.
 */
package filters.generic;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author thanuja
 */
public class StatusList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private List status = new ArrayList();
    private List statusId = new ArrayList();
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String defaultStatus ="";
    private String temp="";
    private String type="";

    public void getStatusList() {
        sql_select = "";
        sql_from = "";
        sql_where = "";


        sql_select = "SELECT status_id,status,default_status ";
        sql_from = "FROM status ";



        if (type != null && !type.equals("")) {
            sql_where = "WHERE status_type='" + type + "'";
        }
        sql_select += sql_from + sql_where;

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            statusId.clear();
            status.clear();
            while (rs.next()) {

                statusId.add(rs.getString(1));
                status.add(rs.getString(2));
                temp = String.valueOf(rs.getString(3));
                if(temp!=null && !temp.equals("") && temp.equals("1"))
                       defaultStatus=rs.getString(1);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }

        }

    }

    public List getStatusID() {
        return statusId;
    }

    public List getStatus() {
        return status;
    }

    public String getDefaultStatus() {
        return defaultStatus;
    }

    public void setStatusType(String type) {
        this.type = type;
    }
}
