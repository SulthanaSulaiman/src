/*
 * LeaveType.java
 *
 * Created on December 1, 2009, 11:02 AM
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
public class LeaveType {

    DBconnection connection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List lv_typeNameList = new ArrayList();
    private List lv_typeCodeList = new ArrayList();
    private String testcode = "";
    private String testname = "";

    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String sql_group = "";
    private String sql_order = "";

    private String empFacility = "";

    public void setFacility(String empFacility){
        this.empFacility = empFacility;
    }
    /** Creates a new instance of LeaveType */
    public LeaveType() {
    }

    public void getValue(){

        sql_select = "";
        sql_from = "";
        sql_where = "";
        sql_group = "";
        sql_order = "";

        sql_select = "select lv.lv_type_id, lv.lv_type ";
        sql_from = " from leave_type lv,lvtype_facilitymap ltfm ";
        sql_where = " where lv.lv_type_id=ltfm.lv_type_id and ltfm.facility_id='"+empFacility+"' and lv.lv_applicable='1' ";
        sql_group = "";
        sql_order = "";

        sql_select +=sql_from+sql_where+sql_group+sql_order;

        //System.out.println("sql_select :LeaveType "+sql_select);
        try {
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);

            lv_typeNameList.clear();
            lv_typeCodeList.clear();

            while(rs.next()){
		testcode = rs.getString(1);
                testname = rs.getString(2);
                lv_typeCodeList.add(testcode);
                lv_typeNameList.add(testname);
            }
            rs.close();
            st.close();
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }

    }

    public List getTypeCode() {
        return lv_typeCodeList;
    }

    public List getTypeName() {
        return lv_typeNameList;
    }
}
