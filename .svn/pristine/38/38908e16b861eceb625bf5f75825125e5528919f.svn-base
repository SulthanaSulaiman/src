/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class AppliedLeaves {
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
    private String selYear="";
    private String fromDate="";
    private String toDate="";
    private List breakCategoryIdList = new ArrayList();
    private List breakCategoryNameList = new ArrayList();

    private List empName = new ArrayList();
    private List empID = new ArrayList();
    private List department = new ArrayList();
    private List designation = new ArrayList();
    private List leaveType = new ArrayList();
    private List leaveCategory = new ArrayList();
    private List leaveTypeId = new ArrayList();
    private List leaveDate = new ArrayList();
    private List leaveApprovedDate = new ArrayList();
    private List leaveID = new ArrayList();
    private List leaveGrpID = new ArrayList();
    private List leaveSession = new ArrayList();
    private List leaveRemark = new ArrayList();
    private List leaveCategoryId = new ArrayList();
    private List leaveApproveCode = new ArrayList();
    private List leaveApproveStatus = new ArrayList();
    private List leaveCancelStatus = new ArrayList();
    private List leaveApproveEmp = new ArrayList();
    private List leaveBalanceId = new ArrayList();
    private List leaveLimit = new ArrayList();
    private List leaveApproveEmpName = new ArrayList();

    private List rej_empName = new ArrayList();
    private List rej_empID = new ArrayList();
    private List rej_department = new ArrayList();
    private List rej_designation = new ArrayList();
    private List rej_leaveType = new ArrayList();
    private List rej_leaveCategory = new ArrayList();
    private List rej_leaveTypeId = new ArrayList();
    private List rej_leaveDate = new ArrayList();
    private List rej_leaveApprovedDate = new ArrayList();
    private List rej_leaveID = new ArrayList();
    private List rej_leaveGrpID = new ArrayList();
    private List rej_leaveSession = new ArrayList();
    private List rej_leaveRemark = new ArrayList();
    private List rej_leaveCategoryId = new ArrayList();
    private List rej_leaveApproveCode = new ArrayList();
    private List rej_leaveApproveStatus = new ArrayList();
    private List rej_leaveCancelStatus = new ArrayList();
    private List rej_leaveApproveEmp = new ArrayList();
    private List rej_leaveBalanceId = new ArrayList();
    private List rej_leaveLimit = new ArrayList();
    private List rej_leaveApproveEmpName = new ArrayList();

    private List old_empName = new ArrayList();
    private List old_empID = new ArrayList();
    private List old_department = new ArrayList();
    private List old_designation = new ArrayList();
    private List old_leaveType = new ArrayList();
    private List old_leaveCategory = new ArrayList();
    private List old_leaveTypeId = new ArrayList();
    private List old_leaveDate = new ArrayList();
    private List old_leaveApprovedDate = new ArrayList();
    private List old_leaveID = new ArrayList();
    private List old_leaveGrpID = new ArrayList();
    private List old_leaveSession = new ArrayList();
    private List old_leaveRemark = new ArrayList();
    private List old_leaveCategoryId = new ArrayList();
    private List old_leaveApproveCode = new ArrayList();
    private List old_leaveApproveStatus = new ArrayList();
    private List old_leaveCancelStatus = new ArrayList();
    private List old_leaveApproveEmp = new ArrayList();
    private List old_leaveBalanceId = new ArrayList();
    private List old_leaveLimit = new ArrayList();
    private List old_leaveApproveEmpName = new ArrayList();

    public void setYear(String selYear){
        this.selYear=selYear;
    }

    public void setEmpId(String empId){
        this.empId=empId;
    }

    public void setInputType(String inputType){
        this.inputType=inputType;
    }

    public void setFacilityId(String facilityId){
        this.facilityId=facilityId;
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
        Statement stmt = null;
        Statement st = null;
        ResultSet rs=null;
        ResultSet resultSet=null;

        String leaves_sql="";
        String approvedDate = "";
        String dateRange_Sql = " and lv_date between '"+fromDate+"' and '"+toDate+"' ";
        String leave_Sess = "";
        String leaveTemp = "";//to check ResultSet is null or not

        empName.clear();
        empID.clear();
        department.clear();
        designation.clear();
        leaveType.clear();
        leaveCategory.clear();
        leaveDate.clear();
        leaveApprovedDate.clear();
        leaveSession.clear();
        leaveRemark.clear();
        leaveCategoryId.clear();
        leaveApproveCode.clear();
        leaveApproveStatus.clear();
        leaveApproveEmp.clear();
        leaveBalanceId.clear();
        leaveLimit.clear();
        leaveCancelStatus.clear();

        rej_empName.clear();
        rej_empID.clear();
        rej_department.clear();
        rej_designation.clear();
        rej_leaveType.clear();
        rej_leaveCategory.clear();
        rej_leaveDate.clear();
        rej_leaveApprovedDate.clear();
        rej_leaveSession.clear();
        rej_leaveRemark.clear();
        rej_leaveCategoryId.clear();
        rej_leaveApproveCode.clear();
        rej_leaveApproveStatus.clear();
        rej_leaveApproveEmp.clear();
        rej_leaveBalanceId.clear();
        rej_leaveLimit.clear();

        old_empName.clear();
        old_empID.clear();
        old_department.clear();
        old_designation.clear();
        old_leaveType.clear();
        old_leaveCategory.clear();
        old_leaveDate.clear();
        old_leaveApprovedDate.clear();
        old_leaveSession.clear();
        old_leaveRemark.clear();
        old_leaveCategoryId.clear();
        old_leaveApproveCode.clear();
        old_leaveApproveStatus.clear();
        old_leaveApproveEmp.clear();
        old_leaveBalanceId.clear();
        old_leaveLimit.clear();

        try{

            con = dbcon.getSampleProperty();
            st = con.createStatement();
            stmt = con.createStatement();
            if(selYear.equals("")){
                rs = st.executeQuery("select year(current_timestamp())");

                while(rs.next()){
                    selYear = rs.getString(1);
                }
            }

            breakCategoryIdList.clear();
            breakCategoryNameList.clear();

            if(inputType.equals("0")){

                //retrieve records for cancellation

               //to get the list of un approved leaves
                //first collect the applied leaves that are not approved and then collect leaves taht are approved within a week-this should bechanged as leaves applied within a week only need to be shown

                leaves_sql="select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit " +
                        "from user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,break_category bkc,leave_session lvs " +
                        "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' and eli.lv_type_id=lvt.lv_type_id " +
                        "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and eli.lv_approve_date is null and eli.lv_session_id=lvs.lv_session_id " +
                        "and eli.cancel_empid is null and eli.lv_date>=(select date(subdate(current_timestamp(),interval 7 day))) order by eli.lv_category_id ";
                if(!fromDate.equals("")&&!toDate.equals("")) {
                    leaves_sql = leaves_sql+dateRange_Sql;
                }

                //System.out.println("leaves_sql-applied only 1:"+leaves_sql);

                rs = st.executeQuery(leaves_sql);

                while(rs.next()){

                    empName.add(rs.getString("u.emp_name"));
                    empID.add(rs.getString("u.emp_id"));
                    department.add(rs.getString("dpt.department"));
                    designation.add(rs.getString("des.designation"));
                    leaveType.add(rs.getString("lvt.lv_type"));
                    leaveCategory.add(rs.getString("lct.lv_category"));
                    leaveDate.add(rs.getString("date_format(eli.lv_Date,'%d-%m-%y')"));
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    leaveApprovedDate.add(approvedDate);
                    leaveID.add(rs.getString("eli.leave_id"));
                    leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leave_Sess = rs.getString("lvs.lv_session");
                    if(rs.wasNull()){
                        leave_Sess="";
                    }
                    leaveSession.add(leave_Sess);
                    leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_code");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveCode.add(leaveTemp);
                    leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveEmp.add(leaveTemp);
                    leaveTypeId.add(rs.getString("eli.lv_type_id"));

                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_balance_id");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    leaveBalanceId.add(leaveTemp);

                    leaveTemp="";
                    leaveTemp = rs.getString("lct.lv_cat_limit");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    leaveLimit.add(leaveTemp);
                    leaveCancelStatus.add("");

                }

//to get the list of approved leaves
                leaves_sql = "select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks,eli.lv_category_id,at.approve_value,eli.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit,eli.lv_approve_code " +
                        "from user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,break_category bkc,leave_session lvs,approve_type at " +
                        "where at.approve_code = eli.lv_approve_code and u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and eli.lv_approve_code = 1 " +
                        "and eli.lv_date>=(select date(subdate(current_timestamp(),interval 7 day))) and eli.lv_session_id=lvs.lv_session_id and eli.cancel_empid is null order by eli.lv_category_id";
                /**leaves_sql=" select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,eli.lv_Date,lve.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks, " +
                        " eli.lv_category_id,at.approve_value,lve.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit  from " +
                        "user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,leave_entry lve,break_category bkc,leave_session lvs,approve_type at   "+
                        "where at.approve_code = lve.lv_approve_code and u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' "+
                        "and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and lve.leave_id=eli.leave_id "+
                        "and lct.brk_category_id=bkc.brk_category_id and lve.lv_approve_date is not null and "+
                        "eli.lv_date>=(select date(subdate(current_timestamp(),interval 7 day))) and eli.lv_session_id=lvs.lv_session_id "+
                        "and eli.leave_id not in (select lv_parent_id from emp_leave_info where emp_id='"+empId+"' and lv_parent_id is not null) " +
                        "and eli.lv_parent_id is null order by eli.lv_category_id ";
                */
                //System.out.println("leaves_sql-applied and approved 2:"+leaves_sql);

                if(!fromDate.equals("")&&!toDate.equals("")){
                    leaves_sql = leaves_sql+dateRange_Sql;
                }

                rs = st.executeQuery(leaves_sql);

                while(rs.next()){
                    empName.add(rs.getString("u.emp_name"));
                    empID.add(rs.getString("u.emp_id"));
                    department.add(rs.getString("dpt.department"));
                    designation.add(rs.getString("des.designation"));
                    leaveType.add(rs.getString("lvt.lv_type"));
                    leaveCategory.add(rs.getString("lct.lv_category"));
                    leaveDate.add(rs.getString("date_format(eli.lv_Date,'%d-%m-%y')"));
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    leaveApprovedDate.add(approvedDate);
                    leaveID.add(rs.getString("eli.leave_id"));
                    leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leave_Sess = rs.getString("lvs.lv_session");
                    if(rs.wasNull()){
                        leave_Sess="";
                    }
                    leaveSession.add(leave_Sess);
                    leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("at.approve_value");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_code");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveCode.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveEmp.add(leaveTemp);
                    leaveTypeId.add(rs.getString("eli.lv_type_id"));

                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_balance_id");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    leaveBalanceId.add(leaveTemp);

                    leaveTemp="";
                    leaveTemp = rs.getString("lct.lv_cat_limit");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    leaveLimit.add(leaveTemp);
                    leaveCancelStatus.add("");
                }
                leaves_sql = "";
                leaves_sql = "select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks,eli.lv_category_id,at.approve_value,eli.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit,eli.lv_approve_code " +
                        "from user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,break_category bkc,leave_session lvs,approve_type at " +
                        "where at.approve_code = eli.lv_approve_code and u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and eli.lv_approve_code = 1 " +
                        "and eli.lv_session_id=lvs.lv_session_id and eli.cancel_empid is not null and eli.cancel_approve_empid is null order by eli.lv_category_id";

                //System.out.println("leaves_sql-cancelled only 3:"+leaves_sql);

                if(!fromDate.equals("")&&!toDate.equals("")){
                    leaves_sql = leaves_sql+dateRange_Sql;
                }

                rs = st.executeQuery(leaves_sql);

                while(rs.next()){
                    empName.add(rs.getString("u.emp_name"));
                    empID.add(rs.getString("u.emp_id"));
                    department.add(rs.getString("dpt.department"));
                    designation.add(rs.getString("des.designation"));
                    leaveType.add(rs.getString("lvt.lv_type"));
                    leaveCategory.add(rs.getString("lct.lv_category"));
                    leaveDate.add(rs.getString("date_format(eli.lv_Date,'%d-%m-%y')"));
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    leaveApprovedDate.add(approvedDate);
                    leaveID.add(rs.getString("eli.leave_id"));
                    leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leave_Sess = rs.getString("lvs.lv_session");
                    if(rs.wasNull()){
                        leave_Sess="";
                    }
                    leaveSession.add(leave_Sess);
                    leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("at.approve_value");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_code");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveCode.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveEmp.add(leaveTemp);
                    leaveTypeId.add(rs.getString("eli.lv_type_id"));

                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_balance_id");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    leaveBalanceId.add(leaveTemp);

                    leaveTemp="";
                    leaveTemp = rs.getString("lct.lv_cat_limit");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    leaveLimit.add(leaveTemp);
                    leaveCancelStatus.add("yes");
                }

                //To get list of rejected leaves
                leaves_sql = "";
                leaves_sql = "select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks,eli.lv_category_id,at.approve_value,eli.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit,eli.lv_approve_code " +
                        "from user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,break_category bkc,leave_session lvs,approve_type at " +
                        "where at.approve_code = eli.lv_approve_code and u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and eli.lv_approve_code = 2 " +
                        "and eli.lv_date>=(select date(subdate(current_timestamp(),interval 7 day))) and eli.lv_session_id=lvs.lv_session_id and eli.cancel_empid is null order by eli.lv_category_id";
                /**leaves_sql=" select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,eli.lv_Date,lve.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks, " +
                        " eli.lv_category_id,at.approve_value,lve.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit  from " +
                        "user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,leave_entry lve,break_category bkc,leave_session lvs,approve_type at   "+
                        "where at.approve_code = lve.lv_approve_code and u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' "+
                        "and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and lve.leave_id=eli.leave_id "+
                        "and lct.brk_category_id=bkc.brk_category_id and lve.lv_approve_date is not null and "+
                        "eli.lv_date>=(select date(subdate(current_timestamp(),interval 7 day))) and eli.lv_session_id=lvs.lv_session_id "+
                        "and eli.leave_id not in (select lv_parent_id from emp_leave_info where emp_id='"+empId+"' and lv_parent_id is not null) " +
                        "and eli.lv_parent_id is null order by eli.lv_category_id ";
                */
                //System.out.println("leaves_sql-rejected 4 :"+leaves_sql);

                if(!fromDate.equals("")&&!toDate.equals("")){
                    leaves_sql = leaves_sql+dateRange_Sql;
                }

                rs = st.executeQuery(leaves_sql);

                while(rs.next()){
                    rej_empName.add(rs.getString("u.emp_name"));
                    rej_empID.add(rs.getString("u.emp_id"));
                    rej_department.add(rs.getString("dpt.department"));
                    rej_designation.add(rs.getString("des.designation"));
                    rej_leaveType.add(rs.getString("lvt.lv_type"));
                    rej_leaveCategory.add(rs.getString("lct.lv_category"));
                    rej_leaveDate.add(rs.getString("date_format(eli.lv_Date,'%d-%m-%y')"));
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    rej_leaveApprovedDate.add(approvedDate);
                    rej_leaveID.add(rs.getString("eli.leave_id"));
                    rej_leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leave_Sess = rs.getString("lvs.lv_session");
                    if(rs.wasNull()){
                        leave_Sess="";
                    }
                    rej_leaveSession.add(leave_Sess);
                    rej_leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    rej_leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("at.approve_value");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    rej_leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_code");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    rej_leaveApproveCode.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    rej_leaveApproveEmp.add(leaveTemp);
                    rej_leaveTypeId.add(rs.getString("eli.lv_type_id"));

                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_balance_id");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    rej_leaveBalanceId.add(leaveTemp);

                    leaveTemp="";
                    leaveTemp = rs.getString("lct.lv_cat_limit");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    rej_leaveLimit.add(leaveTemp);
                }

                //To get list of leaves taken previously
                leaves_sql = "";
                leaves_sql="select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit " +
                        "from user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,break_category bkc,leave_session lvs " +
                        "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' and eli.lv_type_id=lvt.lv_type_id " +
                        "and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id and eli.lv_approve_date is null and eli.lv_session_id=lvs.lv_session_id " +
                        "and eli.cancel_empid is null and date_format(eli.lv_date,'%Y') = '"+selYear+"' and eli.lv_date<(select date(subdate(current_timestamp(),interval 7 day))) order by eli.lv_category_id ";
                if(!fromDate.equals("")&&!toDate.equals("")) {
                    leaves_sql = leaves_sql+dateRange_Sql;
                }

                //System.out.println("leaves_sql-leave history 1:"+leaves_sql);

                rs = st.executeQuery(leaves_sql);

                while(rs.next()){

                    old_empName.add(rs.getString("u.emp_name"));
                    old_empID.add(rs.getString("u.emp_id"));
                    old_department.add(rs.getString("dpt.department"));
                    old_designation.add(rs.getString("des.designation"));
                    old_leaveType.add(rs.getString("lvt.lv_type"));
                    old_leaveCategory.add(rs.getString("lct.lv_category"));
                    old_leaveDate.add(rs.getString("date_format(eli.lv_Date,'%d-%m-%y')"));
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    old_leaveApprovedDate.add(approvedDate);
                    old_leaveID.add(rs.getString("eli.leave_id"));
                    old_leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leave_Sess = rs.getString("lvs.lv_session");
                    if(rs.wasNull()){
                        leave_Sess="";
                    }
                    old_leaveSession.add(leave_Sess);
                    old_leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    old_leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_code");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    old_leaveApproveCode.add(leaveTemp);
                    old_leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    old_leaveApproveEmp.add(leaveTemp);
                    old_leaveTypeId.add(rs.getString("eli.lv_type_id"));

                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_balance_id");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    old_leaveBalanceId.add(leaveTemp);

                    leaveTemp="";
                    leaveTemp = rs.getString("lct.lv_cat_limit");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    old_leaveLimit.add(leaveTemp);

                }

                leaves_sql = "";
                leaves_sql = "select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks,eli.lv_category_id,at.approve_value,eli.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit,eli.lv_approve_code " +
                        "from user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,break_category bkc,leave_session lvs,approve_type at " +
                        "where at.approve_code = eli.lv_approve_code and u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' " +
                        "and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id " +
                        "and date_format(eli.lv_date,'%Y') = '"+selYear+"' and eli.lv_date<(select date(subdate(current_timestamp(),interval 7 day))) and eli.lv_session_id=lvs.lv_session_id and eli.cancel_empid is null order by eli.lv_category_id";
                /**leaves_sql=" select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,eli.lv_Date,lve.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks, " +
                        " eli.lv_category_id,at.approve_value,lve.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit  from " +
                        "user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,leave_entry lve,break_category bkc,leave_session lvs,approve_type at   "+
                        "where at.approve_code = lve.lv_approve_code and u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' "+
                        "and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and lve.leave_id=eli.leave_id "+
                        "and lct.brk_category_id=bkc.brk_category_id and lve.lv_approve_date is not null and "+
                        "eli.lv_date>=(select date(subdate(current_timestamp(),interval 7 day))) and eli.lv_session_id=lvs.lv_session_id "+
                        "and eli.leave_id not in (select lv_parent_id from emp_leave_info where emp_id='"+empId+"' and lv_parent_id is not null) " +
                        "and eli.lv_parent_id is null order by eli.lv_category_id ";
                */
                //System.out.println("leaves_sql-cancel 2:"+leaves_sql);

                if(!fromDate.equals("")&&!toDate.equals("")){
                    leaves_sql = leaves_sql+dateRange_Sql;
                }

                rs = st.executeQuery(leaves_sql);

                while(rs.next()){
                    old_empName.add(rs.getString("u.emp_name"));
                    old_empID.add(rs.getString("u.emp_id"));
                    old_department.add(rs.getString("dpt.department"));
                    old_designation.add(rs.getString("des.designation"));
                    old_leaveType.add(rs.getString("lvt.lv_type"));
                    old_leaveCategory.add(rs.getString("lct.lv_category"));
                    old_leaveDate.add(rs.getString("date_format(eli.lv_Date,'%d-%m-%y')"));
                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    old_leaveApprovedDate.add(approvedDate);
                    old_leaveID.add(rs.getString("eli.leave_id"));
                    old_leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leave_Sess = rs.getString("lvs.lv_session");
                    if(rs.wasNull()){
                        leave_Sess="";
                    }
                    old_leaveSession.add(leave_Sess);
                    old_leaveRemark.add(rs.getString("eli.lv_emp_remarks"));
                    old_leaveCategoryId.add(rs.getString("eli.lv_category_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("at.approve_value");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    old_leaveApproveStatus.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_code");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    old_leaveApproveCode.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    old_leaveApproveEmp.add(leaveTemp);
                    old_leaveTypeId.add(rs.getString("eli.lv_type_id"));

                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_balance_id");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    old_leaveBalanceId.add(leaveTemp);

                    leaveTemp="";
                    leaveTemp = rs.getString("lct.lv_cat_limit");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }

                    old_leaveLimit.add(leaveTemp);
                }

            }else{

                //System.out.println("inputType : "+inputType);

                if(inputType.equals("1")){//for approval

                    //System.out.println("inputType : "+inputType);

                    leaves_sql = "";
                    leaves_sql="select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit " +
                            "from user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,break_category bkc,leave_session lvs " +
                            "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='"+empId+"' and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id  and eli.lv_session_id=lvs.lv_session_id and eli.lv_approve_code is null " +
                            "and eli.cancel_empid is null " +
                            "order by eli.lv_category_id";
                }else if(inputType.equals("2")){//retrive leaves cancelled by employee for approval
                    //System.out.println("inputType : "+inputType);
                    leaves_sql = "";
                    leaves_sql = "select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.leave_id,eli.lv_approve_date,eli.lv_group_id,eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit,eli.cancel_approve_code " +
                            "from emp_leave_info eli,leave_type lvt,leave_category lct, user u, designation des, department dpt,break_category bkc " +
                            "where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and lct.brk_category_id=bkc.brk_category_id and u.emp_id=eli.emp_id and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and eli.emp_id='"+empId+"' and eli.cancel_approve_empid is null " +
                            "and eli.cancel_empid is not null and eli.lv_approve_code is not null ";


                    //System.out.println("leaves_sql : "+leaves_sql);
                }else{//for reports and generic purpose
                    //System.out.println("inputType : "+inputType);
                    leaves_sql="select u.emp_name,u.emp_id,dpt.department,des.designation,lvt.lv_type,lct.lv_category,date_format(eli.lv_Date,'%d-%m-%y'),eli.lv_approve_date,eli.leave_id,eli.lv_group_id,lvs.lv_session,eli.lv_emp_remarks,eli.lv_category_id,eli.lv_approve_code,eli.lv_approve_empid,eli.lv_type_id,eli.lv_balance_id,lct.lv_cat_limit from user u,department dpt,designation des,leave_type lvt,leave_category lct,emp_leave_info eli,break_category bkc,leave_session lvs where u.dept_code=dpt.dept_code and u.desig_code=des.desig_code and u.emp_id=eli.emp_id and eli.emp_id='R&D' and eli.lv_type_id=lvt.lv_type_id and eli.lv_category_id=lct.lv_category_id and lct.brk_category_id=bkc.brk_category_id  and eli.lv_session_id=lvs.lv_session_id order by eli.lv_category_id";
                }

                //System.out.println("leaves_sql:"+leaves_sql);

                if(!fromDate.equals("")&&!toDate.equals("")){
                    leaves_sql = leaves_sql+dateRange_Sql;
                }

                rs = st.executeQuery(leaves_sql);

                while(rs.next()){
                    empName.add(rs.getString("u.emp_name"));
                    empID.add(rs.getString("u.emp_id"));
                    department.add(rs.getString("dpt.department"));
                    designation.add(rs.getString("des.designation"));
                    leaveType.add(rs.getString("lvt.lv_type"));
                    leaveCategory.add(rs.getString("lct.lv_category"));

                    approvedDate = rs.getString("eli.lv_approve_date");
                    if(rs.wasNull()){
                        approvedDate="";
                    }
                    leaveApprovedDate.add(approvedDate);

                    leaveDate.add(rs.getString("date_format(eli.lv_Date,'%d-%m-%y')"));

                    if(inputType.equals("2")){

                        leaveTemp = "";
                        leaveTemp = rs.getString("eli.cancel_approve_code");
                        if(rs.wasNull()){
                            leaveTemp = "";
                        }
                        leaveCancelStatus.add(leaveTemp);

                        leave_Sess="";
                    }else{
                        leave_Sess = rs.getString("lvs.lv_session");
                        if(rs.wasNull()){
                            leave_Sess="";
                        }
                    }
                    leaveSession.add(leave_Sess);

                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_code");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveStatus.add(leaveTemp);

                    leaveID.add(rs.getString("eli.leave_id"));
                    leaveGrpID.add(rs.getString("eli.lv_group_id"));
                    leave_Sess="";
                    leave_Sess = rs.getString("eli.lv_emp_remarks");
                    if(rs.wasNull()){
                        leave_Sess="";
                    }
                    leaveRemark.add(leave_Sess);
                    leaveCategoryId.add(rs.getString("eli.lv_category_id"));


                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_approve_empid");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveApproveEmp.add(leaveTemp);
                    leaveTypeId.add(rs.getString("eli.lv_type_id"));
                    leaveTemp="";
                    leaveTemp = rs.getString("eli.lv_balance_id");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveBalanceId.add(leaveTemp);
                    leaveTemp="";
                    leaveTemp = rs.getString("lct.lv_cat_limit");
                    if(rs.wasNull()){
                        leaveTemp="";
                    }
                    leaveLimit.add(leaveTemp);
                }
            }//close of else part

            ////System.out.println("leaves_sql:"+leaves_sql);

            leaveApproveEmpName.clear();

            for(int loop=0;loop<leaveApproveEmp.size();loop++){

                rs = st.executeQuery("select emp_name from user where emp_id = '"+leaveApproveEmp.get(loop)+"'");

                if(rs.next()){

                    rs = st.executeQuery("select emp_name from user where emp_id = '"+leaveApproveEmp.get(loop)+"'");

                    while(rs.next()){
                        leaveApproveEmpName.add(rs.getString(1));
                    }
                }else{
                    leaveApproveEmpName.add("");
                }
            }

            rej_leaveApproveEmpName.clear();

            for(int loop=0;loop<rej_leaveApproveEmp.size();loop++){

                rs = st.executeQuery("select emp_name from user where emp_id = '"+rej_leaveApproveEmp.get(loop)+"'");

                if(rs.next()){

                    rs = st.executeQuery("select emp_name from user where emp_id = '"+rej_leaveApproveEmp.get(loop)+"'");

                    while(rs.next()){
                        rej_leaveApproveEmpName.add(rs.getString(1));
                    }
                }else{
                    rej_leaveApproveEmpName.add("");
                }
            }

            old_leaveApproveEmpName.clear();

            for(int loop=0;loop<old_leaveApproveEmp.size();loop++){

                rs = st.executeQuery("select emp_name from user where emp_id = '"+old_leaveApproveEmp.get(loop)+"'");

                if(rs.next()){

                    rs = st.executeQuery("select emp_name from user where emp_id = '"+old_leaveApproveEmp.get(loop)+"'");

                    while(rs.next()){
                        old_leaveApproveEmpName.add(rs.getString(1));
                    }
                }else{
                    old_leaveApproveEmpName.add("");
                }
            }

            //System.out.println("empName:"+empName);
            //System.out.println("empID:"+empID);
            //System.out.println("department:"+department);
            //System.out.println("designation:"+designation);
            //System.out.println("leaveType:"+leaveType);
            //System.out.println("leaveCategory:"+leaveCategory);
            //System.out.println("leaveDate:"+leaveDate);
            //System.out.println("leaveApprovedDate:"+leaveApprovedDate);
            //System.out.println("leaveID:"+leaveID);
            //System.out.println("leaveGrpID:"+leaveGrpID);
            //System.out.println("leaveApproveEmp : "+leaveApproveEmp);
            //System.out.println("leaveApproveEmpName : "+leaveApproveEmpName);
            //System.out.println("leaveLimit in AppliedLeaves:"+leaveLimit);

        }catch(SQLException sqle){
            System.out.println("SQLException in AppliedLeaves:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in AppliedLeaves:"+e);
        }finally{
            try{
                rs.close();
                st.close();
                con.close();
            }catch(SQLException sqle){
                System.out.println("SQLException in con close of AppliedLeaves:"+sqle);
            }
        }
    }


    public List getEmpName(){
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

    public List getLeaveType(){
        return leaveType;
    }

    public List getLeaveCategory(){
        return leaveCategory;
    }

    public List getLeaveDate(){
        return leaveDate;
    }

    public List getApprovedDate(){
        return leaveApprovedDate;
    }

    public List getLeaveId(){
        return leaveID;
    }

    public List getLeaveGrpId(){
        return leaveGrpID;
    }

    public List getLeaveSession(){
        return leaveSession;
    }

    public List getLeaveRemark(){
        return leaveRemark;
    }

    public List getLeaveCategoryId(){
        return leaveCategoryId;
    }

    public List getLeaveAppStatus(){
        return leaveApproveStatus;
    }

    public List getLeaveAppCode(){
        return leaveApproveCode;
    }

    public List getLeaveAppEmp(){
        return leaveApproveEmpName;
    }

    public List getLeaveTypeId(){
        return leaveTypeId;
    }

    public List getLeaveBalanceId(){
        return leaveBalanceId;
    }

    public List getLeaveLimit(){
        return leaveLimit;
    }

    public List getCancelStatusCode(){
        return leaveCancelStatus;
    }

    public List getOldEmpName(){
        return old_empName;
    }

    public List getOldEmpId(){
        return old_empID;
    }

    public List getOldEmpDept(){
        return old_department;
    }

    public List getOldEmpDesig(){
        return old_designation;
    }

    public List getOldLeaveType(){
        return old_leaveType;
    }

    public List getOldLeaveCategory(){
        return old_leaveCategory;
    }

    public List getOldLeaveDate(){
        return old_leaveDate;
    }

    public List getOldApprovedDate(){
        return old_leaveApprovedDate;
    }

    public List getOldLeaveId(){
        return old_leaveID;
    }

    public List getOldLeaveGrpId(){
        return old_leaveGrpID;
    }

    public List getOldLeaveSession(){
        return old_leaveSession;
    }

    public List getOldLeaveRemark(){
        return old_leaveRemark;
    }

    public List getOldLeaveCategoryId(){
        return old_leaveCategoryId;
    }

    public List getOldLeaveAppStatus(){
        return old_leaveApproveStatus;
    }

    public List getOldLeaveAppCode(){
        return old_leaveApproveCode;
    }

    public List getOldLeaveAppEmp(){
        return old_leaveApproveEmpName;
    }

    public List getOldLeaveTypeId(){
        return old_leaveTypeId;
    }

    public List getOldLeaveBalanceId(){
        return old_leaveBalanceId;
    }

    public List getOldLeaveLimit(){
        return old_leaveLimit;
    }

    public List getOldCancelStatusCode(){
        return old_leaveCancelStatus;
    }

    public List getRejEmpName(){
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

    public List getRejLeaveType(){
        return rej_leaveType;
    }

    public List getRejLeaveCategory(){
        return rej_leaveCategory;
    }

    public List getRejLeaveDate(){
        return rej_leaveDate;
    }

    public List getRejApprovedDate(){
        return rej_leaveApprovedDate;
    }

    public List getRejLeaveId(){
        return rej_leaveID;
    }

    public List getRejLeaveGrpId(){
        return rej_leaveGrpID;
    }

    public List getRejLeaveSession(){
        return rej_leaveSession;
    }

    public List getRejLeaveRemark(){
        return rej_leaveRemark;
    }

    public List getRejLeaveCategoryId(){
        return rej_leaveCategoryId;
    }

    public List getRejLeaveAppStatus(){
        return rej_leaveApproveStatus;
    }

    public List getRejLeaveAppCode(){
        return rej_leaveApproveCode;
    }

    public List getRejLeaveAppEmp(){
        return rej_leaveApproveEmpName;
    }

    public List getRejLeaveTypeId(){
        return rej_leaveTypeId;
    }

    public List getRejLeaveBalanceId(){
        return rej_leaveBalanceId;
    }

    public List getRejLeaveLimit(){
        return rej_leaveLimit;
    }

    public List getRejCancelStatusCode(){
        return rej_leaveCancelStatus;
    }
}