/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
//import java.util.*;

/**
 *
 * @author ramesh
 */
public class AppliedHourBreak {
//This class retrieves the data based on the Inputtype. If the Inputtype is zero then
  //  records need to be retrieved to display them for cancellation in the LEave aPpplication page
  //  or else if the Inputtypeis one then they need to be retrieved for display as report
//the difference is that for cancellation the LeavesApproved within a week only should be displayed
//if the input type is one then the records willbe retrieved for approval with Approval_date as null condiotion
//or else all the records will be pulled for reporting

    private String empId = "";
    private String inputType = "";
    private String facilityId="";
    private String breakCategory="";
    private String fromDate="";
    private String toDate="";
    private String selYear="";
    private String temp_val = "";
    private String set_facilityId = "";
    private List empName = new ArrayList();
    private List empID = new ArrayList();
    private List department = new ArrayList();
    private List designation = new ArrayList();
    private List leaveType = new ArrayList();
    private List leaveCategory = new ArrayList();
    private List leaveDate = new ArrayList();
    private List leaveApprovedDate = new ArrayList();
    private List leaveID = new ArrayList();
    private List leaveGrpID = new ArrayList();
    private List leaveBeginDate = new ArrayList();
    private List leaveEndDate = new ArrayList();
    private List leaveBeginTime = new ArrayList();
    private List leaveEndTime = new ArrayList();
    private List totalTime = new ArrayList();
    private List leaveRemark = new ArrayList();
    private List leaveCategoryId = new ArrayList();
    private List leaveApproveStatus = new ArrayList();
    private List leaveCancelStatus = new ArrayList();
    private List leaveApproveEmp = new ArrayList();
    private List leaveApproveEmpName = new ArrayList();

    private List rej_empName = new ArrayList();
    private List rej_empID = new ArrayList();
    private List rej_department = new ArrayList();
    private List rej_designation = new ArrayList();
    private List rej_leaveType = new ArrayList();
    private List rej_leaveCategory = new ArrayList();
    private List rej_leaveDate = new ArrayList();
    private List rej_leaveApprovedDate = new ArrayList();
    private List rej_leaveID = new ArrayList();
    private List rej_leaveGrpID = new ArrayList();
    private List rej_leaveBeginDate = new ArrayList();
    private List rej_leaveEndDate = new ArrayList();
    private List rej_leaveBeginTime = new ArrayList();
    private List rej_leaveEndTime = new ArrayList();
    private List rej_totalTime = new ArrayList();
    private List rej_leaveRemark = new ArrayList();
    private List rej_leaveCategoryId = new ArrayList();
    private List rej_leaveApproveStatus = new ArrayList();
    private List rej_leaveCancelStatus = new ArrayList();
    private List rej_leaveApproveEmp = new ArrayList();
    private List rej_leaveApproveEmpName = new ArrayList();

    private List old_empName = new ArrayList();
    private List old_empID = new ArrayList();
    private List old_department = new ArrayList();
    private List old_designation = new ArrayList();
    private List old_leaveType = new ArrayList();
    private List old_leaveCategory = new ArrayList();
    private List old_leaveDate = new ArrayList();
    private List old_leaveApprovedDate = new ArrayList();
    private List old_leaveID = new ArrayList();
    private List old_leaveGrpID = new ArrayList();
    private List old_leaveBeginDate = new ArrayList();
    private List old_leaveEndDate = new ArrayList();
    private List old_leaveBeginTime = new ArrayList();
    private List old_leaveEndTime = new ArrayList();
    private List old_totalTime = new ArrayList();
    private List old_leaveRemark = new ArrayList();
    private List old_leaveCategoryId = new ArrayList();
    private List old_leaveApproveStatus = new ArrayList();
    private List old_leaveCancelStatus = new ArrayList();
    private List old_leaveApproveEmp = new ArrayList();
    private List old_leaveApproveEmpName = new ArrayList();

    private String totalAppTime = "";

    public void setYear(String selYear){
        this.selYear=selYear;
    }

    public void setEmpId(String empId){
        //System.out.println("empId : "+empId);
        this.empId=empId;
    }

    public void setFacilityId(String set_facilityId){
       // System.out.println("set_facilityId : "+set_facilityId);
        this.set_facilityId = set_facilityId;
    }

    public void setInputType(String inputType){
        //System.out.println("inputType : "+inputType);
        this.inputType=inputType;
    }

    public void setBreakCategory(String breakCategory){
        this.breakCategory=breakCategory;
    }

    public void setFromDate(String fromDate){
        this.fromDate=fromDate;
    }

    public void setToDate(String toDate){
        this.toDate=toDate;
    }

    public void showLeaves(){

        DBconnection dbcon = new DBconnection();
        Connection con = null;
        Statement statement = null;
        ResultSet rs=null;

        String hourBreak_sql="";
        String payBeginDay = "";
        String payEndDay = "";
        String payDispBegin = "";
        String payDispEnd = "";
        String chkDay = "";
        String monthEndDay = "";
        String payEndDate = "";
        String monthEndDate = "";
        String currentDate = "00";
        String currentMonth = "00";
        String currentYear = "0000";
        String firstLimitDate = "";
        String secondLimitDate = "";
        String firstDate = "";
        String secondDate = "";
        String approvedDate = "";
        String leaveTemp = "";//to check ResultSet is null or not

        empName.clear();
        empID.clear();
        department.clear();
        designation.clear();
        leaveType.clear();
        leaveCategory.clear();
        leaveDate.clear();
        leaveApprovedDate.clear();
        leaveRemark.clear();
        leaveCategoryId.clear();
        leaveBeginDate.clear();
        leaveEndDate.clear();
        leaveBeginTime.clear();
        leaveEndTime.clear();
        totalTime.clear();
        leaveRemark.clear();
        leaveCategoryId.clear();
        leaveApproveStatus.clear();
        leaveApproveEmp.clear();

        try{
            con = dbcon.getSampleProperty();
            statement = con.createStatement();
            approvedDate = "";

            if(selYear.equals("")){
                rs = statement.executeQuery("select year(current_timestamp())");

                while(rs.next()){
                    selYear = rs.getString(1);
                }
            }
            ResultSet rsGetDate = statement.executeQuery("select day(current_timestamp()),month(current_timestamp()),year(current_timestamp()),DAY(LAST_DAY(current_timestamp()))");

            while (rsGetDate.next()) {

                currentDate = rsGetDate.getString("day(current_timestamp())");
                currentMonth = rsGetDate.getString("month(current_timestamp())");
                currentYear = rsGetDate.getString("year(current_timestamp())");
                monthEndDay = rsGetDate.getString("DAY(LAST_DAY(current_timestamp()))");
            }

            String payCycleAtt = " select pay_begin,pay_end from pay_cycle pcy where date(pay_begin) <= date(current_date) and facility_id = '"+set_facilityId+"' order by pay_cycle_id desc limit 1";

            //System.out.println("payCycleAtt : *******");
            ResultSet rsPayCycle = statement.executeQuery(payCycleAtt);
            //System.out.println("payCycleAtt:"+payCycleAtt);

            while(rsPayCycle.next()){

                firstDate=rsPayCycle.getString(1);
                secondDate=rsPayCycle.getString(2);
                //payDispEnd=rsPayCycle.getString(4);
            }

            payEndDate=currentYear+"-"+currentMonth+"-"+firstDate;
            monthEndDate=currentYear+"-"+currentMonth+"-"+secondDate;

            /**if(payDispBegin.equals("Previous Month")) {
                firstLimitDate = "SELECT CONCAT(year(subdate((current_timestamp),interval 1 month)),'-'," +
                        "month(subdate((current_timestamp),interval 1 month)),'-','"+payBeginDay+"') as firstdate";
                secondLimitDate = "SELECT CONCAT(year(current_timestamp()),'-'," +
                        "month(current_timestamp()),'-','"+payEndDay+"') as seconddate";

                ResultSet rsCheckEndDay = statement.executeQuery("select '"+currentDate+"'>'"+payEndDay+"' ");
                while(rsCheckEndDay.next()){
                    chkDay=rsCheckEndDay.getString(1);
                }
                if(chkDay.equals("1")){
                    firstLimitDate = "SELECT CONCAT(year(current_timestamp()),'-'," +
                            "month(current_timestamp()),'-',day(adddate(('"+payEndDate+"'),interval 1 day))) as firstdate";
                    secondLimitDate = "SELECT CONCAT(year(current_timestamp()),'-'," +
                            "month(current_timestamp()),'-','"+monthEndDay+"') as seconddate";
                }
            } else if (payDispBegin.equals("Current Month")) {
                firstLimitDate = "SELECT CONCAT(year(current_timestamp()),'-'," +
                        "month(current_timestamp()),'-','"+payBeginDay+"') as firstdate";
                secondLimitDate = "SELECT CONCAT(year(current_timestamp()),'-'," +
                        "month(current_timestamp()),'-','"+monthEndDay+"') as seconddate";
            }

            if(!firstLimitDate.equals("")){
                System.out.println("firstLimitDate : *****");
                ResultSet rsFirstLimit = statement.executeQuery(firstLimitDate);
                System.out.println("firstLimitDate : "+firstLimitDate);
                while (rsFirstLimit.next()) {
                    firstDate = rsFirstLimit.getString("firstdate");
                }
            }

            if(!secondLimitDate.equals("")){
                System.out.println("secondLimitDate : *****");
                ResultSet rsSecondLimit = statement.executeQuery(secondLimitDate);
                System.out.println("secondLimitDate : "+secondLimitDate);
                while (rsSecondLimit.next()) {
                    secondDate = rsSecondLimit.getString("seconddate");
                }
            }*/

            if(!fromDate.equals("")&&!toDate.equals("")){
                firstDate=fromDate;//if a date range is selected the variables will be assigned with those days
                secondDate=fromDate;
            }

            //System.out.println("inputType:"+inputType);

            if(inputType.equals("0"))   {

                //collect begin time and end time for cancellation betweeen the firstDate and lastDate -need to be changed as permission applied within a week only could be cancelled and also the leaves too
                hourBreak_sql = "select u.emp_name,u.emp_id,dpt.department,des.designation,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id," +
                        "date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),date_format(eli.lv_begintime,'%h:%i'), date_format(eli.lv_endtime,'%h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid " +
                        "from user u,department dpt,designation des,leave_category lct,emp_leave_info eli,break_category bkc " +
                        "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' " +
                        "and eli.lv_date between '"+firstDate+"' and '"+secondDate+"' and eli.cancel_empid is null and eli.lv_approve_code is null";

                temp_val = "";
                //System.out.println("hourBreak_sql44444:"+hourBreak_sql);
                //System.out.println("hourBreak_sql : *****");
                rs = statement.executeQuery(hourBreak_sql);
                //System.out.println("hourBreak_sql : "+hourBreak_sql);

                while(rs.next()){

                    empName.add(rs.getString("u.emp_name"));
                    empID.add(rs.getString("u.emp_id"));
                    department.add(rs.getString("dpt.department"));
                    designation.add(rs.getString("des.designation"));
                    leaveCategory.add(rs.getString("lct.lv_category"));
                    temp_val = rs.getString("date_format(eli.lv_Date,'%d-%m-%y')");

                    if(rs.wasNull()) {
                        temp_val="";
                    }
                    leaveDate.add(temp_val);
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    leaveApprovedDate.add(approvedDate);

                    leaveID.add(rs.getString("eli.leave_id"));
                    leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leaveBeginDate.add(rs.getString("date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p')"));
                    leaveEndDate.add(rs.getString("date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p')"));
                    leaveBeginTime.add(rs.getString("date_format(eli.lv_begintime,'%h:%i')"));
                    leaveEndTime.add(rs.getString("date_format(eli.lv_endtime,'%h:%i %p')"));
                    totalTime.add(rs.getString("timediff(eli.lv_endtime,eli.lv_begintime)"));
                    leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";

                    leaveTemp = rs.getString("eli.lv_approve_code");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveEmp.add(leaveTemp);
                }
                /**hourBreak_sql=" select u.emp_name,u.emp_id,dpt.department,des.designation," +
                        "  lct.lv_category,date_format(eli.lv_Date,'%d-%b-%Y'),lve.lv_approve_date,eli.leave_id," +
                        " eli.lv_group_id,date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), " +
                        " date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks," +
                        " eli.lv_category_id,lve.lv_approve_code,lve.lv_approve_empid   from "+
                        " user u,department dpt,designation des,leave_category lct,emp_leave_info eli,leave_entry lve,break_category bkc "+
                        " where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' "+
                        "  and eli.lv_category_id=lct.lv_category_id and lve.leave_id=eli.leave_id "+
                        " and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' "+
                        " and eli.lv_date between '"+firstDate+"' and '"+secondDate+"' "+
                        " and eli.leave_id not in (select lv_parent_id from emp_leave_info where emp_id='"+empId+"' and lv_parent_id is not null)" +
                        "  and eli.lv_parent_id is null ";
                 */
                hourBreak_sql = "select u.emp_name,u.emp_id,dpt.department,des.designation,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,at.approve_value," +
                        "date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),date_format(eli.lv_begintime,'%h:%i'), date_format(eli.lv_endtime,'%h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks,eli.lv_category_id,at.approve_value,eli.lv_approve_empid " +
                        "from user u,department dpt,designation des,leave_category lct,emp_leave_info eli,break_category bkc, approve_type at " +
                        "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' and eli.lv_approve_code = 1 " +
                        "and eli.lv_date between '"+firstDate+"' and '"+secondDate+"' and eli.cancel_empid is null and at.approve_code = eli.lv_approve_code ";
                temp_val = "";
//                System.out.println("hourBreak_sql33333333:"+hourBreak_sql);
//                System.out.println("hourBreak_sql : *****");
                rs = statement.executeQuery(hourBreak_sql);
                //System.out.println("hourBreak_sql : "+hourBreak_sql);

                while(rs.next()){

                    empName.add(rs.getString("u.emp_name"));
                    empID.add(rs.getString("u.emp_id"));
                    department.add(rs.getString("dpt.department"));
                    designation.add(rs.getString("des.designation"));
                    leaveCategory.add(rs.getString("lct.lv_category"));
                    temp_val = rs.getString("date_format(eli.lv_Date,'%d-%m-%y')");

                    if(rs.wasNull()){
                        temp_val="";
                    }
                    leaveDate.add(temp_val);
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    leaveApprovedDate.add(approvedDate);

                    leaveID.add(rs.getString("eli.leave_id"));
                    leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leaveBeginDate.add(rs.getString("date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p')"));
                    leaveEndDate.add(rs.getString("date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p')"));
                    leaveBeginTime.add(rs.getString("date_format(eli.lv_begintime,'%h:%i')"));
                    leaveEndTime.add(rs.getString("date_format(eli.lv_endtime,'%h:%i %p')"));
                    totalTime.add(rs.getString("timediff(eli.lv_endtime,eli.lv_begintime)"));
                    leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("at.approve_value");

                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveEmp.add(leaveTemp);
                }

//To get the rejected premission
                hourBreak_sql = "select u.emp_name,u.emp_id,dpt.department,des.designation,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,at.approve_value," +
                        "date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),date_format(eli.lv_begintime,'%h:%i'), date_format(eli.lv_endtime,'%h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks,eli.lv_category_id,at.approve_value,eli.lv_approve_empid " +
                        "from user u,department dpt,designation des,leave_category lct,emp_leave_info eli,break_category bkc, approve_type at " +
                        "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' and eli.lv_approve_code = 2 " +
                        "and eli.lv_date between '"+firstDate+"' and '"+secondDate+"' and eli.cancel_empid is null and at.approve_code = eli.lv_approve_code ";
                temp_val = "";
//                System.out.println("hourBreak_sql22222222:"+hourBreak_sql);
//                System.out.println("hourBreak_sql : *****");
                rs = statement.executeQuery(hourBreak_sql);
                //System.out.println("hourBreak_sql : "+hourBreak_sql);

                while(rs.next()){

                    rej_empName.add(rs.getString("u.emp_name"));
                    rej_empID.add(rs.getString("u.emp_id"));
                    rej_department.add(rs.getString("dpt.department"));
                    rej_designation.add(rs.getString("des.designation"));
                    rej_leaveCategory.add(rs.getString("lct.lv_category"));
                    temp_val = rs.getString("date_format(eli.lv_Date,'%d-%m-%y')");

                    if(rs.wasNull()){
                        temp_val="";
                    }
                    rej_leaveDate.add(temp_val);
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    rej_leaveApprovedDate.add(approvedDate);

                    rej_leaveID.add(rs.getString("eli.leave_id"));
                    rej_leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    rej_leaveBeginDate.add(rs.getString("date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p')"));
                    rej_leaveEndDate.add(rs.getString("date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p')"));
                    rej_leaveBeginTime.add(rs.getString("date_format(eli.lv_begintime,'%h:%i')"));
                    rej_leaveEndTime.add(rs.getString("date_format(eli.lv_endtime,'%h:%i %p')"));
                    rej_totalTime.add(rs.getString("timediff(eli.lv_endtime,eli.lv_begintime)"));
                    rej_leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    rej_leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("at.approve_value");

                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    rej_leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    rej_leaveApproveEmp.add(leaveTemp);
                }

//To get the premission applied in the particular year
              /*  hourBreak_sql ="select u.emp_name,u.emp_id,dpt.department,des.designation,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id," +
                        "date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),date_format(eli.lv_begintime,'%h:%i'), date_format(eli.lv_endtime,'%h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid " +
                        "from user u,department dpt,designation des,leave_category lct,emp_leave_info eli,break_category bkc " +
                        "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' " +
                        "and eli.lv_date < '"+firstDate+"' and date_format(eli.lv_date,'%Y') = '"+selYear+"' and eli.cancel_empid is null and eli.lv_approve_code is null";*/


                hourBreak_sql ="select u.emp_name,u.emp_id,dpt.department,des.designation,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id," +
                        "date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),date_format(eli.lv_begintime,'%h:%i'), date_format(eli.lv_endtime,'%h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid " +
                        "from user u,department dpt,designation des,leave_category lct,emp_leave_info eli,break_category bkc " +
                        "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' " +
                        "and date_format(eli.lv_date,'%Y') = '"+selYear+"' and eli.cancel_empid is null and eli.lv_approve_code is null";


                temp_val = "";
//                System.out.println("hourBreak_sql1111:"+hourBreak_sql);
//                System.out.println("hourBreak_sql : *****");
                rs = statement.executeQuery(hourBreak_sql);
                //System.out.println("hourBreak_sql : "+hourBreak_sql);

                while(rs.next()){

                    old_empName.add(rs.getString("u.emp_name"));
                    old_empID.add(rs.getString("u.emp_id"));
                    old_department.add(rs.getString("dpt.department"));
                    old_designation.add(rs.getString("des.designation"));
                    old_leaveCategory.add(rs.getString("lct.lv_category"));
                    temp_val = rs.getString("date_format(eli.lv_Date,'%d-%m-%y')");

                    if(rs.wasNull()){
                        temp_val="";
                    }
                    old_leaveDate.add(temp_val);
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    old_leaveApprovedDate.add(approvedDate);

                    old_leaveID.add(rs.getString("eli.leave_id"));
                    old_leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    old_leaveBeginDate.add(rs.getString("date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p')"));
                    old_leaveEndDate.add(rs.getString("date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p')"));
                    old_leaveBeginTime.add(rs.getString("date_format(eli.lv_begintime,'%h:%i')"));
                    old_leaveEndTime.add(rs.getString("date_format(eli.lv_endtime,'%h:%i %p')"));
                    old_totalTime.add(rs.getString("timediff(eli.lv_endtime,eli.lv_begintime)"));
                    old_leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    old_leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    //leaveTemp = rs.getString("at.approve_value");

                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    old_leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    old_leaveApproveEmp.add(leaveTemp);
                }

                hourBreak_sql = "select u.emp_name,u.emp_id,dpt.department,des.designation,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id," +
                        "date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),date_format(eli.lv_begintime,'%h:%i'), date_format(eli.lv_endtime,'%h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks,eli.lv_category_id,at.approve_value,eli.lv_approve_empid " +
                        "from user u,department dpt,designation des,leave_category lct,emp_leave_info eli,break_category bkc, approve_type at " +
                        "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' " +
                        "and date_format(eli.lv_date,'%Y') = '"+selYear+"' and eli.cancel_empid is null and at.approve_code = eli.lv_approve_code ";
                temp_val = "";
                //System.out.println("hourBreak_sql:"+hourBreak_sql);
                //System.out.println("hourBreak_sql : *****");
                rs = statement.executeQuery(hourBreak_sql);
                //System.out.println("hourBreak_sql : "+hourBreak_sql);

                while(rs.next()){

                    old_empName.add(rs.getString("u.emp_name"));
                    old_empID.add(rs.getString("u.emp_id"));
                    old_department.add(rs.getString("dpt.department"));
                    old_designation.add(rs.getString("des.designation"));
                    old_leaveCategory.add(rs.getString("lct.lv_category"));
                    temp_val = rs.getString("date_format(eli.lv_Date,'%d-%m-%y')");

                    if(rs.wasNull()){
                        temp_val="";
                    }
                    old_leaveDate.add(temp_val);
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    old_leaveApprovedDate.add(approvedDate);

                    old_leaveID.add(rs.getString("eli.leave_id"));
                    old_leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    old_leaveBeginDate.add(rs.getString("date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p')"));
                    old_leaveEndDate.add(rs.getString("date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p')"));
                    old_leaveBeginTime.add(rs.getString("date_format(eli.lv_begintime,'%h:%i')"));
                    old_leaveEndTime.add(rs.getString("date_format(eli.lv_endtime,'%h:%i %p')"));
                    old_totalTime.add(rs.getString("timediff(eli.lv_endtime,eli.lv_begintime)"));
                    old_leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    old_leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("at.approve_value");

                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    old_leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    old_leaveApproveEmp.add(leaveTemp);
                }
            }else{
                if(inputType.equals("1"))   {
           //collect begin time and end time for approval that collects all the permisssion and hence the date range clause is commented
                    hourBreak_sql="select u.emp_name,u.emp_id,dpt.department,des.designation,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id," +
                            "date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),date_format(eli.lv_begintime,'%h:%i'), date_format(eli.lv_endtime,'%h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid " +
                            "from user u,department dpt,designation des,leave_category lct,emp_leave_info eli,break_category bkc " +
                            "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                            "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' " +
                            " and eli.cancel_empid is null and eli.lv_approve_code is null";
                        //"and eli.lv_date between '"+firstDate+"' and '"+secondDate+"' ";


     /**hourBreak_sql=" select u.emp_name,u.emp_id,dpt.department,des.designation," +
             "  lct.lv_category,date_format(eli.lv_Date,'%d-%b-%Y'),lve.lv_approve_date,eli.leave_id," +
             " eli.lv_group_id,date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), " +
             " date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks," +
             " eli.lv_category_id,lve.lv_approve_code,lve.lv_approve_empid   from "+
             " user u,department dpt,designation des,leave_category lct,emp_leave_info eli,leave_entry lve,break_category bkc "+
             " where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' "+
             "  and eli.lv_category_id=lct.lv_category_id and lve.leave_id=eli.leave_id "+
             " and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' "+
             //" and eli.lv_date between '"+firstDate+"' and '"+secondDate+"' "+-this part is not needed in approval page-same change might also be needed in cancellation retrival query too above
             " and eli.leave_id not in (select lv_parent_id from emp_leave_info where emp_id='"+empId+"' and lv_parent_id is not null)" +
             "  and eli.lv_parent_id is null and lve.lv_approve_code is null ";
      */
                }else if(inputType.equals("2")){//collects all begin time and end time for approving cancelled permisssion
                    hourBreak_sql="select u.emp_name,u.emp_id,dpt.department,des.designation,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),date_format(eli.lv_begintime,'%h:%i')," +
                            "date_format(eli.lv_endtime,'%h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid,eli.cancel_approve_code " +
                            "from user u,department dpt,designation des,leave_category lct,emp_leave_info eli,break_category bkc " +
                            "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                            "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' " +
                            "and eli.cancel_approve_empid is null and eli.cancel_empid is not null and eli.lv_approve_code is not null ";
                }else {//generic for display purposes in report
                    hourBreak_sql="select u.emp_name,u.emp_id,dpt.department,des.designation,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'),date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid " +
                            "from user u,department dpt,designation des,leave_category lct,emp_leave_info eli,break_category bkc " +
                            "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                            "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' " +
                            "and eli.lv_date between '"+firstDate+"' and '"+secondDate+"' and eli.cancel_empid is null ";

                    /**hourBreak_sql=" select u.emp_name,u.emp_id,dpt.department,des.designation," +
                            "  lct.lv_category,date_format(eli.lv_Date,'%d-%b-%Y'),lve.lv_approve_date,eli.leave_id," +
                            " eli.lv_group_id,date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p'), " +
                            " date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p'),timediff(eli.lv_endtime,eli.lv_begintime),eli.lv_emp_remarks," +
                            " eli.lv_category_id,lve.lv_approve_code,lve.lv_approve_empid   from "+
                            " user u,department dpt,designation des,leave_category lct,emp_leave_info eli,leave_entry lve,break_category bkc "+
                            " where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' "+
                            "  and eli.lv_category_id=lct.lv_category_id and lve.leave_id=eli.leave_id "+
                            " and lct.brk_category_id=bkc.brk_category_id and bkc.brk_category_id='2' "+
                            " and eli.lv_date between '"+firstDate+"' and '"+secondDate+"' "+
                            " and eli.leave_id not in (select lv_parent_id from emp_leave_info where emp_id='"+empId+"' and lv_parent_id is not null)" +
                            "  and eli.lv_parent_id is null ";
                    */
                }
                temp_val = "";
                //System.out.println("hourBreak_sql:"+hourBreak_sql);
                //System.out.println("hourBreak_sql : *****");
                rs = statement.executeQuery(hourBreak_sql);
                //System.out.println("hourBreak_sql : "+hourBreak_sql);

                while(rs.next()){

                    empName.add(rs.getString("u.emp_name"));
                    empID.add(rs.getString("u.emp_id"));
                    department.add(rs.getString("dpt.department"));
                    designation.add(rs.getString("des.designation"));
                    leaveCategory.add(rs.getString("lct.lv_category"));
                    temp_val = rs.getString("date_format(eli.lv_Date,'%d-%m-%y')");

                    if(rs.wasNull()){
                        temp_val="";
                    }
                    leaveDate.add(temp_val);
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    leaveApprovedDate.add(approvedDate);
                    if(inputType.equals("2")){
                        leaveTemp = "";
                        leaveTemp = rs.getString("eli.cancel_approve_code");
                        if(rs.wasNull()){
                            leaveTemp = "";
                        }
                        leaveCancelStatus.add(leaveTemp);
                    }
                    leaveID.add(rs.getString("eli.leave_id"));
                    leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leaveBeginDate.add(rs.getString("date_format(eli.lv_begintime,'%d-%b-%Y %h:%i %p')"));
                    leaveEndDate.add(rs.getString("date_format(eli.lv_endtime,'%d-%b-%Y %h:%i %p')"));
                    leaveBeginTime.add(rs.getString("date_format(eli.lv_begintime,'%h:%i')"));
                    leaveEndTime.add(rs.getString("date_format(eli.lv_endtime,'%h:%i %p')"));
                    totalTime.add(rs.getString("timediff(eli.lv_endtime,eli.lv_begintime)"));
                    leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    if(inputType.equals("0")) {
                        leaveTemp = rs.getString("at.approve_value");
                    }else {
                        leaveTemp = rs.getString("eli.lv_approve_code");
                    }
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveEmp.add(leaveTemp);
                }
            }

            leaveApproveEmpName.clear();
            for(int loop=0;loop<leaveApproveEmp.size();loop++){

                rs = statement.executeQuery("select emp_name from user where emp_id = '"+leaveApproveEmp.get(loop)+"'");

                if(rs.next()){

                    rs = statement.executeQuery("select emp_name from user where emp_id = '"+leaveApproveEmp.get(loop)+"'");

                    while(rs.next()){
                        leaveApproveEmpName.add(rs.getString(1));
                    }
                }else{
                    leaveApproveEmpName.add("");
                }
            }

            rej_leaveApproveEmpName.clear();
            for(int loop=0;loop<rej_leaveApproveEmp.size();loop++){

                rs = statement.executeQuery("select emp_name from user where emp_id = '"+rej_leaveApproveEmp.get(loop)+"'");

                if(rs.next()){

                    rs = statement.executeQuery("select emp_name from user where emp_id = '"+rej_leaveApproveEmp.get(loop)+"'");

                    while(rs.next()){
                        rej_leaveApproveEmpName.add(rs.getString(1));
                    }
                }else{
                    rej_leaveApproveEmpName.add("");
                }
            }

            old_leaveApproveEmpName.clear();
            for(int loop=0;loop<old_leaveApproveEmp.size();loop++){

                rs = statement.executeQuery("select emp_name from user where emp_id = '"+old_leaveApproveEmp.get(loop)+"'");

                if(rs.next()){

                    rs = statement.executeQuery("select emp_name from user where emp_id = '"+old_leaveApproveEmp.get(loop)+"'");

                    while(rs.next()){
                        old_leaveApproveEmpName.add(rs.getString(1));
                    }
                }else{
                    old_leaveApproveEmpName.add("");
                }
            }

            totalAppTime = "";
            /**payCycleAtt = "select pay_begin, pay_end from pay_cycle where date(pay_begin) <= date(current_date) and facility_id = '"+facilityId+"' order by pay_cycle_id desc limit 1";
            System.out.println("payCycleAtt in aphb bean : "+payCycleAtt);
            rsPayCycle = statement.executeQuery(payCycleAtt);
            while(rsPayCycle.next()){
                firstDate=rsPayCycle.getString(1);
                secondDate=rsPayCycle.getString(2);
            }*/

            String permission_SQL = " select sec_to_time(3600-sum(time_to_sec(timediff(lv_endtime,lv_begintime)))) from emp_leave_info " +
                    "where emp_id='"+empId+"' and (lv_approve_code is null or lv_approve_code =1) " +
                    "and lv_date between '" + firstDate + "' and '" + secondDate + "' and cancel_empid is null";
            //System.out.println("permission_SQL in aphb bean : "+permission_SQL);
            rs = statement.executeQuery(permission_SQL);
            while(rs.next()){
                totalAppTime = rs.getString(1);
                if(rs.wasNull()){
                    totalAppTime = "01:00:00";
                }
            }

            //System.out.println("totalAppTime in aphb bean : "+ totalAppTime);

        }catch(SQLException sqle){
            System.out.println("SQLException in AppliedHourBreak:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in AppliedHourBreak:"+e);
        }finally{
            try{
                rs.close();
                statement.close();
                con.close();
            }catch(SQLException sqle){
                System.out.println("SQLException in con close of AppliedLeaves:"+sqle);
            }
        }
    }

    public List getEmpName(){
        //System.out.println("emp : "+empName);
        return empName;
    }

    public List getEmpId(){
        return empID;
    }

    public List getEmpDept(){
        return department;
    }

    public List getEmpDesig(){
        return designation;
    }

    public List getLeaveCategory(){
        //System.out.println("emp in ahb : "+empName);
        return leaveCategory;
    }

    public List getLeaveCategoryId(){
        return leaveCategoryId;
    }

    public List getLeaveDate(){
        return leaveDate;
    }

    public List getApprovedDate(){
        //System.out.println("emp in ahb : "+empName);
        return leaveApprovedDate;
    }

    public List getLeaveId(){
        return leaveID;
    }

    public List getLeaveGrpId(){
        return leaveGrpID;
    }

    public List getBeginTime(){
        return leaveBeginTime;
    }

    public List getEndTime(){
        return leaveEndTime;
    }

    public List getBeginDate(){
        return leaveBeginDate;
    }

    public List getEndDate(){
        return leaveEndDate;
    }

    public List getTotalTime(){
        return totalTime;
    }

    public List getLeaveRemark(){
        return leaveRemark;
    }

    public List getLeaveAppStatus(){
        //System.out.println("leaveApproveStatus in ahb : "+leaveApproveStatus);
        return leaveApproveStatus;
    }

    public List getLeaveAppEmp(){
        return leaveApproveEmpName;
    }

    public List getLeaveAppEmpId(){
        return leaveApproveEmp;
    }

    public List getCancelStatusCode(){
        return leaveCancelStatus;
    }

    public String getTotalAppTime(){
        return totalAppTime;
    }

    public List getRejEmpName(){
        //System.out.println("emp : "+rej_empName);
        return rej_empName;
    }

    public List getRejEmpId(){
        return rej_empID;
    }

    public List getRejEmpDept(){
        return rej_department;
    }

    public List getRejEmpDesig(){
        return rej_designation;
    }

    public List getRejLeaveCategory(){
        //System.out.println("emp in ahb : "+rej_empName);
        return rej_leaveCategory;
    }

    public List getRejLeaveCategoryId(){
        return rej_leaveCategoryId;
    }

    public List getRejLeaveDate(){
        return rej_leaveDate;
    }

    public List getRejApprovedDate(){
        //System.out.println("emp in ahb : "+rej_empName);
        return rej_leaveApprovedDate;
    }

    public List getRejLeaveId(){
        return rej_leaveID;
    }

    public List getRejLeaveGrpId(){
        return rej_leaveGrpID;
    }

    public List getRejBeginTime(){
        return rej_leaveBeginTime;
    }

    public List getRejEndTime(){
        return rej_leaveEndTime;
    }

    public List getRejBeginDate(){
        return rej_leaveBeginDate;
    }

    public List getRejEndDate(){
        return rej_leaveEndDate;
    }

    public List getRejTotalTime(){
        return rej_totalTime;
    }

    public List getRejLeaveRemark(){
        return rej_leaveRemark;
    }

    public List getRejLeaveAppStatus(){
        //System.out.println("leaveApproveStatus in ahb : "+rej_leaveApproveStatus);
        return rej_leaveApproveStatus;
    }

    public List getRejLeaveAppEmp(){
        return rej_leaveApproveEmpName;
    }

    public List getRejLeaveAppEmpId(){
        return rej_leaveApproveEmp;
    }

    public List getRejCancelStatusCode(){
        return rej_leaveCancelStatus;
    }

     public List getOldEmpName(){
        //System.out.println("emp : "+old_empName);
        return old_empName;
    }

    public List getOldEmpId(){
        return old_empID;
    }

    public List getOldEmpDept(){
        return old_department;
    }

    public List getOldjEmpDesig(){
        return old_designation;
    }

    public List getOldLeaveCategory(){
        //System.out.println("emp in ahb : "+rej_empName);
        return old_leaveCategory;
    }

    public List getOldLeaveCategoryId(){
        return old_leaveCategoryId;
    }

    public List getOldLeaveDate(){
        return old_leaveDate;
    }

    public List getOldApprovedDate(){
        //System.out.println("emp in ahb : "+rej_empName);
        return old_leaveApprovedDate;
    }

    public List getOldLeaveId(){
        return old_leaveID;
    }

    public List getOldLeaveGrpId(){
        return old_leaveGrpID;
    }

    public List getOldBeginTime(){
        return old_leaveBeginTime;
    }

    public List getOldEndTime(){
        return old_leaveEndTime;
    }

    public List getOldBeginDate(){
        return rej_leaveBeginDate;
    }

    public List getOldEndDate(){
        return rej_leaveEndDate;
    }

    public List getOldTotalTime(){
        return old_totalTime;
    }

    public List getOldLeaveRemark(){
        return old_leaveRemark;
    }

    public List getOldLeaveAppStatus(){
        //System.out.println("leaveApproveStatus in ahb : "+rej_leaveApproveStatus);
        return old_leaveApproveStatus;
    }

    public List getOldLeaveAppEmp(){
        return old_leaveApproveEmpName;
    }

    public List getOldLeaveAppEmpId(){
        return old_leaveApproveEmp;
    }

    public List getOldCancelStatusCode(){
        return old_leaveCancelStatus;
    }
}