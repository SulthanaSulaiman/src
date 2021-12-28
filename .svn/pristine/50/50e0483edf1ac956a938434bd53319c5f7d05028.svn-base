/*
 * LeaveStatus.java
 *
 * Created on November 30, 2009, 5:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramyamaims
 */
public class LeaveStatus {

    /**
     * Creates a new instance of LeaveStatus
     */
    public LeaveStatus() {
    }

    DBconnection connection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List statusNameList = new ArrayList();
    private List statusCodeList = new ArrayList();
    private String testcode = "";
    private String testname = "";

    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String sql_group = "";
    private String sql_order = "";

    public void getValue(){

        sql_select = "";
        sql_from = "";
        sql_where = "";
        sql_group = "";
        sql_order = "";

        sql_select = "select ls.lv_status_id,ls.lv_status ";
        sql_from = "from leave_status ls ";
        sql_where = "";
        sql_group = "";
        sql_order = "";

        sql_select +=sql_from+sql_where+sql_group+sql_order;

        //System.out.println("sql_select :categoryBean "+sql_select);
        try {
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            statusCodeList.clear();
            statusNameList.clear();
            while(rs.next()){
		testcode = rs.getString(1);
                testname = rs.getString(2);
                statusCodeList.add(testcode);
                statusNameList.add(testname);
            }
            rs.close();
            st.close();
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }

    }

    public List getStatusCode() {
        return statusCodeList;
    }

    public List getStatusName() {
        return statusNameList;
    }
}
