/*
 * LeaveCategory.java
 *
 * Created on November 30, 2009, 5:05 PM
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
public class LeaveCategory {

    DBconnection connection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List categoryNameList = new ArrayList();
    private List categoryCodeList = new ArrayList();
    private List breakCategory = new ArrayList();

    private List categoryLimit = new ArrayList();
    private List categoryUnit = new ArrayList();

    private String testcode = "";
    private String testname = "";

    private String categoryValue = "";//to get the null value and reset it to avoid Nullpointer Exception


    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String sql_group = "";
    private String sql_order = "";
    private String empFacility = "";

    public void setFacility(String empFacility){
        this.empFacility = empFacility;
    }


    public void getValue(){

        sql_select = "";
        sql_from = "";
        sql_where = "";
        sql_group = "";
        sql_order = "";

        sql_select = " select lc.lv_category_id, lc.lv_category,lc.brk_category_id,lc.lv_cat_limit,bc.brk_category_value ";
        sql_from = " from leave_category lc,lvcategory_facilitymap lcfm,break_category bc ";
        sql_where = " where lc.lv_category_id=lcfm.lv_category_id and bc.brk_category_id = lc.brk_category_id and lcfm.facility_id='"+empFacility+"' ";
        sql_group = "";
        sql_order = "";

        sql_select +=sql_from+sql_where+sql_group+sql_order;

        //System.out.println("sql_select :categoryBean "+sql_select);
        try {
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            categoryCodeList.clear();
            categoryNameList.clear();
            while(rs.next()){
                //System.out.println("inside while");
		testcode = rs.getString(1);
                testname = rs.getString(2);
                categoryCodeList.add(testcode);
                categoryNameList.add(testname);
                categoryValue = rs.getString(3);
                if(rs.wasNull()){
                    categoryValue="";
                }
                breakCategory.add(categoryValue);

               categoryValue = rs.getString(4);
                if(rs.wasNull()){
                    categoryValue="";
                }
                categoryLimit.add(categoryValue);

                                categoryValue = rs.getString(5);
                if(rs.wasNull()){
                    categoryValue="";
                }
                categoryUnit.add(categoryValue);
            }
            rs.close();
            st.close();
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }

    }

    public List getCategoryCode() {
        return categoryCodeList;
    }

    public List getCategoryName() {
        return categoryNameList;
    }


    public List getBreakCategory() {
        return breakCategory;
    }

    public List getCategoryLimit(){
        return categoryLimit;
    }

        public List getCategoryUnit(){
        return categoryUnit;
    }

}
