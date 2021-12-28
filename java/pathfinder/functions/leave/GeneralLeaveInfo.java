/*
 * GeneralLeaveInfo.java
 *
 * Created on February 9, 2010, 11:15 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.leave;
import connection.DBconnection;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

/**
 *
 * @author ramyamaims
 */
public class GeneralLeaveInfo {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_query = "";
    private int countLeave = 0;
    private double temp_doubleVal = 0.00;
    private int total_countLeave = 0;
    private double total_absPercent = 0.00;

    DBconnection conProp = new DBconnection();
    private String empId = "";
    private String empDept = "";
    private String temp_val = "";
    private String date_val = "";
    private String time_val = "";
    private String dateTime_val = "";

    private int total_emp_count = 0;
    private List dept = new ArrayList();
    private List dept_code = new ArrayList();
    private List emp_count = new ArrayList();
    private List emp_present = new ArrayList();
    private List emp_app_abs = new ArrayList();
    private List emp_unapp_abs = new ArrayList();
    private List emp_permis = new ArrayList();
    private List emp_abs_percent = new ArrayList();
    private List otherShift_count = new ArrayList();

    public void setEmpId(String empId){
        this.empId = empId;
    }

    public void setEmpDept(String empDept){
        this.empDept = empDept;
    }

    public void setDateVal(String date_val){
        this.date_val = date_val;
    }

    public void setDateTimeVal(String dateTime_val){
        this.dateTime_val = dateTime_val;
    }

    public void setTimeVal(String time_val){
        this.time_val = time_val;
    }

    public void generalLeaveInfo() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_query = "";
//To get count of emp in the comp
            if(!empDept.equals("HR")){
                sql_query = "select count(u.emp_id) from user u " +
                        "where u.status = '1' and u.supervisor_id in " +
                        "(select u1.emp_id from user u1 where u1.status='1' " +
                        "and (u1.supervisor_id='"+empId+"' || u1.emp_id = '"+empId+"'))";
            }else{
                sql_query = "select count(u.emp_id) from user u " +
                        "where u.status = '1'";
            }
            rs = stmt.executeQuery(sql_query);
            emp_count.clear();

            while(rs.next()){
                total_emp_count = rs.getInt(1);
            }

            sql_query = "";
//To get count of emp deptwise
            if(!empDept.equals("HR")){
                sql_query = "select count(u.emp_id),d.department,u.dept_code " +
                        "from user u, department d " +
                        "where u.status = '1' and u.supervisor_id in " +
                        "(select u1.emp_id from user u1 where u1.status='1' and (u1.supervisor_id='"+empId+"' || u1.emp_id = '"+empId+"')) " +
                        "and u.dept_code = d.dept_code group by u.dept_code ";
            }else{
                sql_query = "select count(u.emp_id),d.department,u.dept_code " +
                        "from user u, department d " +
                        "where u.status = '1' " +
                        "and u.dept_code = d.dept_code group by u.dept_code ";
            }
            // //System.out.println("sql query for dept and emp count in generalLeaveInfo : "+sql_query);
            rs = stmt.executeQuery(sql_query);
            emp_count.clear();
            dept.clear();
            dept_code.clear();

            while(rs.next()){
                emp_count.add(rs.getString(1));
                dept.add(rs.getString(2));
                dept_code.add(rs.getString(3));
            }

            sql_query = "";
            emp_present.clear();
            emp_app_abs.clear();
            emp_unapp_abs.clear();
            emp_permis.clear();
            otherShift_count.clear();
            emp_abs_percent.clear();

            for(int loop=0;loop<dept_code.size();loop++){
//Count of present emp
                if(!empDept.equals("HR")){
                    sql_query = "select count(u.emp_id) from user u, department d where status = '1' " +
                            "and u.supervisor_id in (select u1.emp_id from user u1 where u1.status='1' and (u1.supervisor_id='"+empId+"' || u1.emp_id = '"+empId+"')) " +
                            "and u.dept_code = d.dept_code " +
                            "and u.emp_id in (select s.emp_id from shift s where date(s.shift_start_time) = '"+date_val+"' and s.shift_start_time <= '"+dateTime_val+"') " +
                            "and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code";
                }else{
                    sql_query =  "select count(u.emp_id) from user u, department d where status = '1' " +
                            "and u.dept_code = d.dept_code " +
                            "and u.emp_id in (select s.emp_id from shift s where date(s.shift_start_time) ='"+date_val+"' and s.shift_start_time <= '"+dateTime_val+"') " +
                            "and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code";
                }

                //System.out.println("1 sql query for present emp count in generalLeaveInfo : "+sql_query);
                rs = stmt.executeQuery(sql_query);

                temp_val = "0";
                if(rs.next()){
                    rs = stmt.executeQuery(sql_query);
                    while(rs.next()){
                        temp_val = "0";
                        temp_val = rs.getString(1);
                        if(rs.wasNull()){
                            temp_val = "0";
                        }
                        emp_present.add(temp_val);
                    }
                }else{
                    emp_present.add(temp_val);
                }

//Count of emp with approve leaves
                if(!empDept.equals("HR")){
                    sql_query = "select count(u.emp_id) from user u, department d where status = '1' and " +
                            "u.supervisor_id in (select u1.emp_id from user u1 where u1.status='1' and (u1.supervisor_id= '"+empId+"' || u1.emp_id = '"+empId+"')) " +
                            "and u.dept_code = d.dept_code and u.emp_id in (select e.emp_id from emp_leave_info e,leave_category lc,break_category bc " +
                            "where e.lv_date = '"+date_val+"' and e.lv_category_id = lc.lv_category_id and lc.brk_category_id = bc.brk_category_id " +
                            "and bc.brk_category_id = 1 and e.cancel_empid is null and e.lv_approve_code = 1) " +
                            "and u.emp_id not in (select s.emp_id from shift s where date(s.shift_start_time) = '"+date_val+"' and s.shift_start_time <= '"+dateTime_val+"') " +
                            "and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code";
                }else{
                    sql_query = "select count(u.emp_id) from user u, department d where status = '1' " +
                            "and u.dept_code = d.dept_code and u.emp_id in (select e.emp_id from emp_leave_info e,leave_category lc,break_category bc " +
                            "where e.lv_date = '"+date_val+"' and e.lv_category_id = lc.lv_category_id and lc.brk_category_id = bc.brk_category_id " +
                            "and bc.brk_category_id = 1 and e.cancel_empid is null and e.lv_approve_code = 1) " +
                            "and u.emp_id not in (select s.emp_id from shift s where date(s.shift_start_time) = '"+date_val+"' and s.shift_start_time <= '"+dateTime_val+"') " +
                            "and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code";
                }

                //  //System.out.println("2 sql query for app absent emp count in generalLeaveInfo : "+sql_query);
                rs = stmt.executeQuery(sql_query);

                temp_val = "0";
                if(rs.next()){
                    rs = stmt.executeQuery(sql_query);
                    while(rs.next()){
                        temp_val = "0";
                        temp_val = rs.getString(1);
                        if(rs.wasNull()){
                            temp_val = "0";
                        }
                        emp_app_abs.add(temp_val);
                    }
                }else{
                    emp_app_abs.add(temp_val);
                }

//Count of emp with unapprove leaves
                if(!empDept.equals("HR")){

                    sql_query = "select count(u.emp_id)  from user u, department d " +
                            "where u.status = '1' and u.supervisor_id in (select u1.emp_id from user u1 where u1.status='1' and (u1.supervisor_id= '"+empId+"' || u1.emp_id = '"+empId+"')) " +
                            "and u.dept_code = d.dept_code and (u.emp_id not in (select s.emp_id from shift s where date(s.shift_start_time) = '"+date_val+"' " +
                            "and s.shift_start_time <= '"+dateTime_val+"' union select e.emp_id from emp_leave_info e , leave_category lc, break_category bc " +
                            "where e.lv_date = '"+date_val+"' and e.lv_category_id = lc.lv_category_id and lc.brk_category_id = bc.brk_category_id and bc.brk_category_id = 1 " +
                            "and e.cancel_empid is null and e.lv_approve_code = 1 union select u.emp_id from user u, shift_name sn " +
                            "where u.emp_shift_id = sn.emp_shift_id and sn.emp_shift_begintime >= '"+time_val+"') or u.emp_id in( select e.emp_id from emp_leave_info e , leave_category lc, break_category bc " +
                            "where e.lv_date = '"+date_val+"' and e.lv_category_id = lc.lv_category_id and lc.brk_category_id = bc.brk_category_id and bc.brk_category_id = 1 " +
                            "and (e.lv_approve_code =  2 or e.lv_approve_date is null))) and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code";

                }else{
                    sql_query = "select count(u.emp_id)  from user u, department d " +
                            "where u.status = '1' and u.dept_code = d.dept_code and (u.emp_id not in (select s.emp_id from shift s where date(s.shift_start_time) = '"+date_val+"' " +
                            "and s.shift_start_time <= '"+dateTime_val+"' union select e.emp_id from emp_leave_info e , leave_category lc, break_category bc " +
                            "where e.lv_date = '"+date_val+"' and e.lv_category_id = lc.lv_category_id and lc.brk_category_id = bc.brk_category_id and bc.brk_category_id = 1 " +
                            "and e.cancel_empid is null and e.lv_approve_code = 1 union select u.emp_id from user u, shift_name sn " +
                            "where u.emp_shift_id = sn.emp_shift_id and sn.emp_shift_begintime >= '"+time_val+"') or u.emp_id in( select e.emp_id from emp_leave_info e , leave_category lc, break_category bc " +
                            "where e.lv_date = '"+date_val+"' and e.lv_category_id = lc.lv_category_id and lc.brk_category_id = bc.brk_category_id and bc.brk_category_id = 1 " +
                            "and (e.lv_approve_code =  2 or e.lv_approve_date is null))) and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code";
                }

                //System.out.println("3 sql query for unapp absent emp count in generalLeaveInfo : "+sql_query);
                rs = stmt.executeQuery(sql_query);

                temp_val = "0";
                if(rs.next()){
                    rs = stmt.executeQuery(sql_query);
                    while(rs.next()){
                        temp_val = "0";
                        temp_val = rs.getString(1);
                        if(rs.wasNull()){
                            temp_val = "0";
                        }
                        emp_unapp_abs.add(temp_val);
                    }
                }else{
                    emp_unapp_abs.add(temp_val);
                }

//Count of emp in other shift
                if(!empDept.equals("HR")){
                    sql_query = "select count(u.emp_id) from user u, shift_name sn,department d " +
                            "where u.status = 1 and u.supervisor_id in (select u1.emp_id from user u1 where u1.status='1' " +
                            "and (u1.supervisor_id='"+empId+"' || u1.emp_id = '"+empId+"')) and u.emp_shift_id = sn.emp_shift_id " +
                            "and d.dept_code = u.dept_code and sn.emp_shift_begintime >= '"+time_val+"' " +
                            "and u.emp_id not in (select s.emp_id from shift s where date(s.shift_start_time) = '"+date_val+"' " +
                            "and s.shift_start_time <= '"+dateTime_val+"') and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code ";

                }else{
                    sql_query = "select count(u.emp_id) from user u, shift_name sn,department d " +
                            "where u.emp_shift_id = sn.emp_shift_id and u.status = 1 " +
                            "and d.dept_code = u.dept_code and sn.emp_shift_begintime >= '"+time_val+"' " +
                            "and u.emp_id not in (select s.emp_id from shift s where date(s.shift_start_time) = '"+date_val+"' " +
                            "and s.shift_start_time <= '"+dateTime_val+"') and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code ";
                }

                // //System.out.println("4 sql query for permission emp count in generalLeaveInfo : "+sql_query);
                rs = stmt.executeQuery(sql_query);

                temp_val = "0";
                if(rs.next()){
                    rs = stmt.executeQuery(sql_query);
                    while(rs.next()){
                        temp_val = "0";
                        temp_val = rs.getString(1);
                        if(rs.wasNull()){
                            temp_val = "0";
                        }
                        otherShift_count.add(temp_val);
                    }
                }else{
                    otherShift_count.add(temp_val);
                }

//Count of emp in permission
                if(!empDept.equals("HR")){
                    sql_query = "select count(u.emp_id) from user u, department d " +
                            "where u.status = 1 and u.supervisor_id in (select u1.emp_id from user u1 where u1.status='1' " +
                            "and (u1.supervisor_id='"+empId+"' || u1.emp_id = '"+empId+"')) and u.dept_code = d.dept_code and u.emp_id in " +
                            "(select e.emp_id from emp_leave_info e, leave_category lc, break_category bc " +
                            "where e.lv_date = '"+date_val+"' and e.lv_category_id = lc.lv_category_id " +
                            "and lc.brk_category_id = bc.brk_category_id and bc.brk_category_id = 2 and e.cancel_empid is null) " +
                            "and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code ";
                }else{
                    sql_query = "select count(u.emp_id) from user u, department d " +
                            "where u.status = 1 and u.dept_code = d.dept_code and u.emp_id in " +
                            "(select e.emp_id from emp_leave_info e, leave_category lc, break_category bc " +
                            "where e.lv_date = '"+date_val+"' and e.lv_category_id = lc.lv_category_id " +
                            "and lc.brk_category_id = bc.brk_category_id and bc.brk_category_id = 2 and e.cancel_empid is null) " +
                            "and u.dept_code = '"+dept_code.get(loop)+"' group by u.dept_code ";
                }

                // //System.out.println("5 sql query for permission emp count in generalLeaveInfo : "+sql_query);
                rs = stmt.executeQuery(sql_query);

                temp_val = "0";
                if(rs.next()){
                    rs = stmt.executeQuery(sql_query);
                    while(rs.next()){
                        temp_val = "0";
                        temp_val = rs.getString(1);
                        if(rs.wasNull()){
                            temp_val = "0";
                        }
                        emp_permis.add(temp_val);
                    }
                }else{
                    emp_permis.add(temp_val);
                }

                //System.out.println("dept_code ********** : "+dept_code.get(loop));
            }

            total_countLeave = 0;
            //System.out.println("&&&&&&&&&&&&&&& emp_present in GeneralLeaveInfo &&&&&&&&&&&&&&");
            //System.out.println("emp_count : "+emp_count);
            //System.out.println("&&&&&&&&&&&&&&& emp_present in GeneralLeaveInfo &&&&&&&&&&&&&&");

            for(int loop=0;loop<emp_count.size();loop++){
                int x = 0;
                countLeave = 0;
                temp_doubleVal = 0.00;
                countLeave = Integer.parseInt(emp_app_abs.get(loop).toString())+Integer.parseInt(emp_unapp_abs.get(loop).toString());
                x = Integer.parseInt(emp_count.get(loop).toString());
                total_countLeave = total_countLeave+countLeave;
                if(countLeave>0){
                    //System.out.println("&&&&&&&&&&&&&&& total_countLeave in GeneralLeaveInfo &&&&&&&&&&&&&&");
                    //System.out.println("total_countLeave : "+total_countLeave);
                    //System.out.println("countLeave : "+countLeave);
                    //System.out.println("emp_count.get(loop) : "+x);
                    //System.out.println("&&&&&&&&&&&&&&& total_countLeave in GeneralLeaveInfo &&&&&&&&&&&&&&");
                    temp_doubleVal = countLeave*100;
                    temp_doubleVal = temp_doubleVal/x;
                    //System.out.println("&&&&&&&&&&&&&&& temp_doubleVal in GeneralLeaveInfo &&&&&&&&&&&&&&");
                    //System.out.println("temp_doubleVal : "+temp_doubleVal);
                    //System.out.println("&&&&&&&&&&&&&&& temp_doubleVal in GeneralLeaveInfo &&&&&&&&&&&&&&");
                    temp_doubleVal = floatPrecision(String.valueOf(temp_doubleVal));
                }
                emp_abs_percent.add(temp_doubleVal);
            }

            if(total_countLeave>0){
                temp_doubleVal = 0.00;
                temp_doubleVal = total_countLeave*100;
                temp_doubleVal = temp_doubleVal/total_emp_count;
                temp_doubleVal = floatPrecision(String.valueOf(temp_doubleVal));
                total_absPercent = temp_doubleVal;
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(Exception npe){
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

    public double floatPrecision(String lvCntVal){

        double return_lvCntVal = 0.00;
        String str_lvCntVal = "0.00";

        int y=(int)Float.parseFloat(lvCntVal);
        int decimalIndex = 0;

        //System.out.println("lvCntVal-before split:"+lvCntVal);
        if(lvCntVal.equals("0.00")){
            lvCntVal = "0.0000";
        }
        decimalIndex = lvCntVal.indexOf(".");
        //System.out.println("lvCntVal.substring : "+lvCntVal.substring(decimalIndex+1).length());
        if(lvCntVal.substring(decimalIndex+1).length()>2){
            str_lvCntVal = lvCntVal.substring(decimalIndex+1,decimalIndex+3);
        }else{
            str_lvCntVal = lvCntVal.substring(decimalIndex+1);
        }
        //System.out.println("lvCntVal-after split:"+str_lvCntVal);
        int chkRoundOff = Integer.parseInt(str_lvCntVal);
        BigDecimal roundedValue = null;

        try{
            roundedValue = new BigDecimal(chkRoundOff);
        }catch(NumberFormatException nme) {
            roundedValue = new BigDecimal(0.0);
        }
        //System.out.println("roundedValue b4 : "+roundedValue);
        if(chkRoundOff<5) {
            roundedValue=roundedValue.setScale(0,BigDecimal.ROUND_DOWN);
        }else if(chkRoundOff>=5) {
            roundedValue=roundedValue.setScale(0,BigDecimal.ROUND_UP);
        }
        //System.out.println("roundedValue aft : "+roundedValue);
        lvCntVal = y+"."+String.valueOf(roundedValue);
        return_lvCntVal = Double.parseDouble(lvCntVal);
        return return_lvCntVal;
    }

    public List getEmpCount(){
        //System.out.println("emp_count:"+emp_count);
        return emp_count;
    }

    public List getEmpDept(){
        return dept;
    }

    public List getPresentCount(){
        //System.out.println("emp_present : "+emp_present);
        return emp_present;
    }

    public List getApproveLvCount(){
        return emp_app_abs;
    }

    public List getUnapproveLvCount(){
        return emp_unapp_abs;
    }

    public List getPermissionCount(){
        return emp_permis;
    }

    public List getAbsPercent(){
        return emp_abs_percent;
    }

    public int getTotalLeaveCount(){
        return total_countLeave;
    }

    public double getTotalAbsPercent(){
        return total_absPercent;
    }

    public List getOtherShiftCount(){
        return otherShift_count;
    }
}
