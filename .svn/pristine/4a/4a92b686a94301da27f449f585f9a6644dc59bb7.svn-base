/*
 * LeaveSession.java
 *
 * Created on November 30, 2009, 5:20 PM
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
public class LeaveSession {

    private String lvCat;

    public void setLeaveCategory(String lvCat){

      this.lvCat=lvCat;
    }

    /** Creates a new instance of LeaveSession */
    public LeaveSession() {
    }
    DBconnection connection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List sessionNameList = new ArrayList();
    private List sessionCodeList = new ArrayList();
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

        sql_select = "select ls.lv_session_id,ls.lv_session ";
        sql_from = "from leave_session ls ";
        sql_where = "";

        if(lvCat.equals("")){

        }else{
            sql_where=" where ls.lv_category_id='"+lvCat+"' ";
        }

        sql_group = "";
        sql_order = "order by ls.lv_session_id desc ";

        sql_select +=sql_from+sql_where+sql_group+sql_order;

        //System.out.println("sql_select :categoryBean "+sql_select);
        try {
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            sessionCodeList.clear();
            sessionNameList.clear();
            while(rs.next()){
		testcode = rs.getString(1);
                testname = rs.getString(2);
                sessionCodeList.add(testcode);
                sessionNameList.add(testname);
            }
            rs.close();
            st.close();
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }

    }

    public List getSessionCode() {
        return sessionCodeList;
    }

    public List getSessionName() {
        return sessionNameList;
    }
}
