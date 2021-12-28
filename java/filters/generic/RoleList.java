/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.generic;


import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class RoleList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List roleID = new ArrayList();
    private List roleName = new ArrayList();
    private List desigCode = new ArrayList();
    private List desigName = new ArrayList();
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where="";



    public void collectRoleList(){

                roleID.clear();
                roleName.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " SELECT milestone_act_code,milestone_act_name   ";
        sql_from = " FROM proj_milestone_act ";
        sql_where = " WHERE milestone_act_status = '1' ";
        sql_select +=sql_from+sql_where;

       // System.out.println("sql_select:"+sql_select);


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            roleID.clear();
            roleName.clear();
            while(rs.next()){
                roleID.add(rs.getString(1));
                roleName.add(rs.getString(2));
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

    public void collectDesignationList() {
        sql_select = " select desig_code, designation from designation order by designation";
        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            desigCode.clear();
            desigName.clear();
            while (rs.next()) {
                desigCode.add(rs.getString(1));
                desigName.add(rs.getString(2));
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in Rolelist - Collect Designation List() : " + sqle);
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException in Rolelist - Collect Designation List() : " + sqle);
            }
        }
    }

    public List getDesigCode() {
        return desigCode;
    }

    public List getDesigName() {
        return desigName;
    }
    
    public List getRoleId() {
        return roleID;
    }

    public List getRoleName() {
        return roleName;
    }

  

}
