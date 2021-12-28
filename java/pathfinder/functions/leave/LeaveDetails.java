/*
 * LeaveDetails.java
 *
 * Created on July 9, 2009, 1:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.leave;
import connection.DBconnection;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramyams
 */
public class LeaveDetails {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private int intResult = 0;

    private String set_dateOfCon = "";
    private String set_dateOfJoin = "";
    private String yearOfCon = "";
    private String total_days = "";
    private String total_worked_days = "";
    private String temp_val = "";
    private String setEmp = "";
    private String sql_query = "";
    private String set_facilityId = "";
    private List lv_set_year = new ArrayList();
    private List retVal = new ArrayList();
    private List lv_type = new ArrayList();
    private List lv_applicable = new ArrayList();
    private List lv_type_id = new ArrayList();
    private List lv_limit = new ArrayList();
    private List bal_lvType_id = new ArrayList();
    private List bal_lvType = new ArrayList();
    private List lv_cur_bal = new ArrayList();
    private List lv_int_bal = new ArrayList();

    /**
     * Creates a new instance of LeaveDetails
     */
    public LeaveDetails() {
    }

    public void setEmp(String setEmp){
        this.setEmp = setEmp;
    }

    public void setDateOfCon(String set_dateOfCon){
        this.set_dateOfCon = set_dateOfCon;
    }

    public void setDateOfJoin(String set_dateOfJoin){
        this.set_dateOfJoin = set_dateOfJoin;
    }

    public void getValue(String yes){

        DBconnection connection = new DBconnection();

        try{
            con = connection.getSampleProperty();
            stmt = con.createStatement();



            rs = stmt.executeQuery("select distinct(lv_type_id) from leave_type");
            lv_type_id.clear();
            while(rs.next()){
                lv_type_id.add(rs.getString(1));
            }

            lv_set_year.clear();

            for(int loop=0;loop<lv_type_id.size();loop++){

                rs = stmt.executeQuery("select year(lv_setdate) from leavetype_limit " +
                        "where lv_type_id = '"+lv_type_id.get(loop)+"' order by limit_id desc limit 1 ");

                while(rs.next()){
                    lv_set_year.add(rs.getString(1));
                }
            }

            lv_type.clear();
            lv_limit.clear();
            lv_applicable.clear();
            for(int loop=0;loop<lv_type_id.size();loop++){
                /*System.out.println("select lt.lv_type,ll.lv_limit,ll.lv_applicable " +
                        "from leavetype_limit ll, leave_type lt where year(ll.lv_setdate) = '"+lv_set_year.get(loop)+"' " +
                        "and lt.lv_type_id = '"+lv_type_id.get(loop)+"' and ll.lv_type_id = lt.lv_type_id ");*/
                rs = stmt.executeQuery("select lt.lv_type,ll.lv_limit,ll.lv_applicable " +
                        "from leavetype_limit ll, leave_type lt where year(ll.lv_setdate) = '"+lv_set_year.get(loop)+"' " +
                        "and lt.lv_type_id = '"+lv_type_id.get(loop)+"' and ll.lv_type_id = lt.lv_type_id ");

                while(rs.next()){
                    lv_type.add(rs.getString(1));
                    lv_limit.add(rs.getString(2));
                    lv_applicable.add(rs.getString(3));
                }
            }
            //System.out.println("lv_type : "+lv_type);
            //System.out.println("lv_type_id : "+lv_type_id);
            for(int loop=0;loop<lv_type_id.size();loop++){

                    //System.out.println("set_dateOfCon : "+set_dateOfCon);
                    //System.out.println("set_dateOfJoin : "+set_dateOfJoin);
                    //System.out.println("yes : "+yes);
                if(!yes.equals("") && lv_applicable.get(loop).equals("1")) {
                    //System.out.println("insert into leave_balance(emp_id,lv_type_id,year,initial_bal,current_bal) " +
                                //"value('"+setEmp+"','"+lv_type_id.get(loop)+"',year(current_timestamp()),'"+lv_limit.get(loop)+"','"+lv_limit.get(loop)+"')");
                    try {
                        intResult = stmt.executeUpdate("insert into leave_balance(emp_id,lv_type_id,year,initial_bal,current_bal) " +
                                "value('"+setEmp+"','"+lv_type_id.get(loop)+"',year(current_timestamp()),'"+lv_limit.get(loop)+"','"+lv_limit.get(loop)+"')");
                    }catch(SQLException sqle){
                        sqle.printStackTrace();
                    }
                }
            }

            rs = stmt.executeQuery("select lt.lv_type_id,lt.lv_type, lb.initial_bal,lb.current_bal " +
                    "from leave_balance lb, leave_type lt where year = year(current_timestamp) and emp_id = '"+setEmp+"' " +
                    "and lt.lv_type_id = lb.lv_type_id");
            bal_lvType_id.clear();
            bal_lvType.clear();
            lv_int_bal.clear();
            lv_cur_bal.clear();
            while(rs.next()){
                bal_lvType_id.add(rs.getString(1));
                bal_lvType.add(rs.getString(2));
                lv_int_bal.add(rs.getString(3));
                lv_cur_bal.add(rs.getString(4));
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
         finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}
    }

    public List getIntLimit(){
        return lv_int_bal;
    }

    public List getCurLimit(){
        return lv_cur_bal;
    }

    public List getLeaveTypeId(){
        //System.out.println("lv_type_id : "+lv_type_id);
        return lv_type_id;
    }

    public List getLeaveType(){
        //System.out.println("lv_type : "+lv_type);
        return lv_type;
    }

    public List getBalLeaveTypeId(){
        return bal_lvType_id;
    }

    public List getBalLeaveType(){
        return bal_lvType;
    }

    public List getLeaveApplicable(){
        return lv_applicable;
    }
}
