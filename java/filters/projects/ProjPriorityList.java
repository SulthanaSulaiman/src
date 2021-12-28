/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.projects;


import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class ProjPriorityList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List priorityNameList = new ArrayList();
    private List priorityIdList = new ArrayList();
    private String testcode = "";
    private String testname = "";


    private String sql_select = "";
    private String sql_from = "";


    public void collectPriorityList(){
        sql_select = "";
        sql_from = "";

        sql_select = "select priority_id, priority_value ";
        sql_from = "from proj_priority ";

        sql_select +=sql_from;


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            priorityIdList.clear();
            priorityNameList.clear();
            while(rs.next()){
		testcode = rs.getString(1);
                testname = rs.getString(2);
                priorityIdList.add(testcode);
                priorityNameList.add(testname);
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public List getPriorityId() {
        return priorityIdList;
    }

    public List getPriorityIName() {
        return priorityNameList;
    }

}
