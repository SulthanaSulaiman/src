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
public class DesignationList implements Serializable {

    private List desigName = new ArrayList();
    private List desigCode = new ArrayList();
    private String searchKey;
    private String deptCode = "";
     private String isSuggest="";

      public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }
      

      public void setDeptCode(String deptCode) {
          this.deptCode = deptCode;
      }

    public String getDeptCode() {
        return deptCode;
    }

    public void collectDesignation() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;


        String sql_select = "";
        String sql_from = "";


        sql_select = "";
        sql_from = "";

        sql_select = "select DISTINCT desig.desig_code, desig.designation ";
        sql_from = "from  designation desig ";
        if(!deptCode.equals("")) {
            sql_from+=", user u where u.desig_code=desig.desig_code and u.dept_code='"+deptCode+"'";
        }

        sql_select += sql_from;

        if(searchKey!=null && !searchKey.equals(""))
            {
                sql_select += " where desig.designation LIKE '"+searchKey+"%' ";

            }

 if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 10";
        }
        
        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_select);
            desigCode.clear();
            desigName.clear();
            while (rs.next()) {
                desigCode.add(rs.getString(1));
                desigName.add(rs.getString(2));
            }

        } catch (SQLException sqle) {


            System.out.println("SQLException in collectDesignation:" + sqle);

        } catch (Exception e) {


            System.out.println("Exception in collectDesignation:" + e);

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

    public List getDesigCode() {
        return desigCode;
    }

    public List getDesigName() {
        return desigName;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;

    }
}
