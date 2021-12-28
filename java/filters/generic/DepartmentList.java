/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters.generic;

import filters.*;
import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class DepartmentList implements Serializable {

    private List deptName = new ArrayList();
    private List deptCode = new ArrayList();
    private String searchKey;
 private String isSuggest="";

      public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }
    public void collectDeptartment() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;


        String sql_select = "";
        String sql_from = "";
        String sql_where = "";

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = "SELECT dept.dept_code, dept.department ";
        sql_from = "FROM  department dept ";
        sql_where = "WHERE dept.dept_active=1";

        sql_select += sql_from + sql_where;

        if(searchKey!=null && !searchKey.equals(""))
            {
                sql_select += " and dept.department LIKE '"+searchKey+"%' ";

            }

         if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 10";
        }
        
//System.out.println("Dept query:"+sql_select);
        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_select);
            deptName.clear();
            deptName.clear();
            while (rs.next()) {
                deptCode.add(rs.getString(1));
                deptName.add(rs.getString(2));
            }

        } catch (SQLException sqle) {


            System.out.println("SQLException in collectDeptartment:" + sqle);

        } catch (Exception e) {


            System.out.println("Exception in collectDeptartment:" + e);

        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {


                    System.out.println("Exception while closing resultset:se.getMessage()");

                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {


                    System.out.println("Exception while closing Statement:se.getMessage()");

                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {


                    System.out.println("Exception while closing connection:se.getMessage()");

                }
            }

        }



    }

    public List getDeptCode() {
        return deptCode;
    }

    public List getDeptName() {
        return deptName;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;

    }
}
