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
public class RoleMemberList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List memberID = new ArrayList();
    private List memberName = new ArrayList();
    private List vendorID= new ArrayList();
    private List vendorName = new ArrayList();
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where="";
    private String activityCode="";
    private String locationCode="";
    private String desigCode = "";

    public void setActivityCode(String activityCode){
        this.activityCode=activityCode;
    }

    public void setLocationCode(String LocationCode) {
        this.locationCode = LocationCode;
    }

    public List getVendorID() {
        return vendorID;
    }

    public List getVendorName() {
        return vendorName;
    }

    public String getDesigCode() {
        return desigCode;
    }

    public void setDesigCode(String desigCode) {
        this.desigCode = desigCode;
    }

    public void collectVendorList(){

       pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
       vendorID.clear();
       vendorName.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " SELECT CONCAT(cnt.firstname,' ', cnt.surname) AS contact, cnt.contact_id ";
        sql_from = " FROM contacts cnt, contacttype_map cntm  ";
        sql_where = " WHERE cntm.type_id=9 AND cnt.contact_id=cntm.contact_id AND CONCAT(cnt.firstname,' ', cnt.surname) IS NOT NULL GROUP BY contact_id ORDER BY CONCAT(cnt.firstname,' ', cnt.surname)";
        sql_select +=sql_from+sql_where;

        //System.out.println("vendorID - sql_select : "+sql_select);

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            vendorID.clear();
            vendorName.clear();
            while(rs.next()){
                vendorID.add(rs.getString(2));
                vendorName.add(splChar.decoding(rs.getString(1)));
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


    public void collectMemberList(){
        
       memberID.clear();
       memberName.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

//        sql_select = " SELECT u.emp_id,u.emp_name ";
//        sql_from = " FROM USER u,milestone_desig_map adm  ";
//        sql_where = " WHERE u.desig_code=adm.desig_code AND adm.milestone_act_code='"+activityCode+"' AND u.status='1' AND u.facility_id = '"+locationCode+"' ORDER BY u.emp_name";
//        sql_select +=sql_from+sql_where;

        sql_select = " SELECT u.emp_id,u.emp_name ";
        sql_from = " FROM USER u,designation d  ";
        sql_where = " WHERE u.desig_code=d.desig_code AND u.desig_code='" + desigCode + "' AND u.status='1' AND u.facility_id = '"+locationCode+"' ORDER BY u.emp_name";
        sql_select +=sql_from+sql_where;

        //System.out.println("memberId - sql_select : "+sql_select);

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            memberID.clear();
            memberName.clear();
            while(rs.next()){
                memberName.add(rs.getString(2));
                memberID.add(rs.getString(1));
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

    public List getMemberId() {
        return memberID;
    }

    public List getMemberName() {
        return memberName;
    }

}
