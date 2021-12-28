/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import java.io.Serializable;
import java.sql.*;
import connection.DBconnection;

/**
 *
 * @author ramesh
 */
public class UpdateLeaveBalance implements Serializable {

    private String initBal = "";
    private String curBal = "";
    private String lv_category = "";
    private String lv_type = "";
    private String lv_empid = "";
    private String lv_totalDays = "";
    private String balanceId = "";
    private String leave_id = "";
    private String appliedEmpId="";
    private String leaveDate = "";
    private String applyStatus="";
    private String leaveRemarks = "";
    private String applyStatusCode = "";
    private String updateQuery = "";
    private String canlvAppCode = "";

    private int updateLeaveBalance = 0;

    public void setInitialBalance(String initBal){
        this.initBal= initBal;
    }

    public void setBalanceId(String balanceId){
        this.balanceId= balanceId;
    }

    public void setLvStatusCode(String canlvAppCode){
        this.canlvAppCode= canlvAppCode;
    }

    public void setCurrentBalance(String curBal){
        this.curBal=curBal;
    }

    public String getCurrentBalance(){
        return curBal;
    }


    public void setLeaveCategory(String lv_category){
        this.lv_category=lv_category;
    }

    public void setStatusCode(String applyStatusCode){
        this.applyStatusCode=applyStatusCode;
    }

    public void setLeaveType(String lv_type){
        this.lv_type=lv_type;
    }

    public void setEmpId(String lv_empid){
        this.lv_empid=lv_empid;
    }

    public void setAppliedEmpId(String appliedEmpId){
        this.appliedEmpId=appliedEmpId;
    }

    public void setTotalDays(String lv_totalDays){
        this.lv_totalDays=lv_totalDays;
    }

     public void setLeaveId(String leave_id){
         this.leave_id=leave_id;
     }

    public void setLeaveDate(String leaveDate){
        this.leaveDate=leaveDate;
    }

    public void setLeaveRemarks(String leaveRemarks){
        this.leaveRemarks=leaveRemarks;
    }

    public void setStatus(String applyStatus){
        this.applyStatus=applyStatus;
    }

    public void cancelLeave(){
        try{
            DBconnection dbcon = new DBconnection();
            Connection con = dbcon.getSampleProperty();
            Statement st = con.createStatement();
//this method gets executed while denying the leave

            if(appliedEmpId.equals("")){
                appliedEmpId=lv_empid;
            }

            //System.out.println("appliedEmpId in UpdateLeaveBalance:"+appliedEmpId);
            //System.out.println("LeaveId in UpdateLeaveBalance:"+leave_id);
            //System.out.println("LeaveTypeId in UpdateLeaveBalance:"+lv_type);
            //System.out.println("LeaveCategoryId in UpdateLeaveBalance:"+lv_category);
            //System.out.println("canlvAppCode in UpdateLeaveBalance:"+canlvAppCode);
            //System.out.println("curBal in UpdateLeaveBalance:"+curBal);
            int getResult = 0;
            //System.out.println("applyStatus:"+applyStatus);
            //System.out.println("getResult:"+getResult);
            AddLeaveRecord alvr = new AddLeaveRecord();

            alvr.setEmpId(lv_empid);
            alvr.setAppliedEmpId(appliedEmpId);
            alvr.setLeaveCategory(lv_category);
            alvr.setLeaveDate(leaveDate);
            alvr.setLeaveId(leave_id);
            alvr.setLeaveRemarks(leaveRemarks);
            if(Double.parseDouble(curBal)<=0 && applyStatus.equals("Approve")){
                ResultSet rs = st.executeQuery("select lv_type_id from leave_type where default_type = 1");
                    while(rs.next()){
                       lv_type = rs.getString(1);
                    }
                alvr.setLeaveType(lv_type);
                rs.close();
            }else{
                //alvr.setLeaveType(lv_type);
            }
            alvr.setStatus(applyStatus);

            //The below IF condition is because If the leave is denied then
            //balance alone should be increased and record should not be added
            if(applyStatus.equals("2")){
                alvr.addLeave();
                getResult = alvr.getAddStatus();
            }else{
                getResult=1;
            }

            //System.out.println("applyStatus:"+applyStatus);
            //System.out.println("getResult:"+getResult);

            //the below is a check variable to check whther the Denied break is leave or permission. For Leave the balance has to be increasd which is
            //done below
            String breakCategory = "";
            ResultSet rsGetBreakCategory = st.executeQuery("select brk_category_id from leave_category where lv_category_id='"+lv_category+"' ");
            while(rsGetBreakCategory.next()){
                breakCategory=rsGetBreakCategory.getString(1);
            }
            rsGetBreakCategory.close();

            updateLeaveBalance = 0;

            if(getResult>0&&breakCategory.equals("1")){

                double appliedDays = 0.00;
                double currentBalance = Double.parseDouble(curBal);

                if(lv_category.equals("2")){
                    appliedDays = 1.0;
                }else{
                    appliedDays = 0.5;
                }

                if(applyStatus.equals("Approve")){
                    currentBalance = currentBalance-appliedDays;
                }

                if(applyStatus.equals("CanApprove") || applyStatus.equals("AllocateApprove")){
                    currentBalance = currentBalance+appliedDays;
                }
                //System.out.println("lv_type app applyStatus 1 : "+lv_type);
                curBal = String.valueOf(currentBalance);

                updateQuery = "";
                updateQuery = "update leave_balance set current_bal='"+curBal+"' where emp_id='"+lv_empid+"' and lv_type_id='"+lv_type+"' and lv_balance_id='"+balanceId+"' ";

                //System.out.println("********** updateQuery ***************** in update leave balance: ");
                //System.out.println(updateQuery);
                //System.out.println("********** updateQuery ***************** in update leave balance: ");
                if(applyStatus.equals("Approve")){
                    //System.out.println("inside app applyStatus");
                    if(currentBalance>=0){
                        updateLeaveBalance = st.executeUpdate(updateQuery);
                    }else {

                        ResultSet rs = st.executeQuery("select lv_type_id from leave_type where default_type = 1");
                        while(rs.next()){
                        lv_type = rs.getString(1);
                        }
                        //System.out.println("applyStatusCode in ulb : "+applyStatusCode);
                        //System.out.println("leaveRemarks in ulb : "+leaveRemarks);
                        alvr.setCurrentBalance(curBal);
                        alvr.setLeaveId(leave_id);
                        alvr.setLeaveType(lv_type);
                        alvr.setStatusCode(applyStatusCode);
                        alvr.setLeaveRemarks(leaveRemarks);
                        alvr.addLeave();
                        getResult = alvr.getAddStatus();
                        rs.close();
                    }
                }else if(applyStatus.equals("CanApprove") && canlvAppCode.equals("1")){
                    updateLeaveBalance = st.executeUpdate(updateQuery);
                    //System.out.println("outside app applyStatus");
                }else if(applyStatus.equals("AllocateApprove")){
                    updateLeaveBalance = st.executeUpdate(updateQuery);
                    //System.out.println("outside app applyStatus");
                    alvr.setAppliedEmpId(appliedEmpId);
                    alvr.setStatus("AllocateApprove");
                    alvr.setLeaveId(leave_id);
                    alvr.addLeave();
                    getResult = alvr.getAddStatus();
                }else if(applyStatus.equals("AllocatePermisApprove")){
                     updateLeaveBalance = st.executeUpdate(updateQuery);
                    //System.out.println("outside app applyStatus");
                }
                //System.out.println("lv_type app applyStatus 2 : "+lv_type);
                //System.out.println("updateQuery : "+updateQuery);
                //System.out.println("updateLeaveBalance:"+updateLeaveBalance);
            }else {
                if(applyStatus.equals("AllocatePermisApprove")){
                    updateQuery = "";
                    updateQuery = "update emp_leave_info set cancel_approve_date = current_timestamp()," +
                        "cancel_approve_empid='"+appliedEmpId+"',cancel_approve_code='1'," +
                        "cancel_empid='"+appliedEmpId+"',cancel_date=current_date where leave_id = '"+leave_id+"'";
                    //System.out.println("********** updateQuery ***************** in update leave balance: ");
                    //System.out.println(updateQuery);
                    //System.out.println("********** updateQuery ***************** in update leave balance: ");

                    updateLeaveBalance = st.executeUpdate(updateQuery);
                    //System.out.println("breakCategory : "+breakCategory);
                }
            }
            st.close();
            con.close();

        }catch(SQLException sqle){
            System.out.println("SQL Exception in Update Leave Balance"+sqle);
        }
    }

    public void updateBalance(){
//while applying leave jsp inserts the record in the tables - emp_leave_info and leave_entry
 //and calls this method to deduct the  leave balance
        //System.out.println("lv_totalDays:"+lv_totalDays);
        //System.out.println("curBal:"+curBal);
        //System.out.println("lv_empid:"+lv_empid);
        //System.out.println("lv_type:"+lv_type);
        //System.out.println("balanceId:"+balanceId);

        try{

            double appliedDays = Double.parseDouble(lv_totalDays);
            double currentBalance = Double.parseDouble(curBal);
            double InitialBalance = Double.parseDouble(initBal);

            if(lv_category.equals("2")){
                appliedDays = Double.parseDouble(lv_totalDays);
            }else{
                appliedDays = 0.5;
            }

            currentBalance = currentBalance-appliedDays;
            curBal = String.valueOf(currentBalance);

            //System.out.println("currentBalance:"+currentBalance);
            //System.out.println("curBal:"+curBal);

            DBconnection dbcon = new DBconnection();
            Connection con = dbcon.getSampleProperty();
            Statement st = con.createStatement();

            int updateLeaveBalance = st.executeUpdate("update leave_balance set current_bal='"+curBal+"' where emp_id='"+lv_empid+"' and lv_type_id='"+lv_type+"' and lv_balance_id='"+balanceId+"' ");

            st.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQL Exception in Update Leave Balance"+sqle);
        }
    }
}