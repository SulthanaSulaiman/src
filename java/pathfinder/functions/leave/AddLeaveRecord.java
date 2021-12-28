/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 */
public class AddLeaveRecord implements Serializable{

    private String empId = "";
    private String appliedEmpId = "";
    private String leaveType = "";
    private String curBal = "";
    private String leaveCategory = "";
    private String leaveDate = "";
    private String leaveRemarks = "";
    private int incrGpId = 0;
    private String applyStatus="";
    private String leaveId="";
    private String collectAppliedLeave="";
    private int applyNewLeave=0;
    private String applyStatusCode = "";
    private String cancel_approve_empid="";

    public void setEmpId(String empId){
        this.empId=empId;
    }

    public void setCanAppEmpId(String cancel_approve_empid){
        this.cancel_approve_empid=cancel_approve_empid;
    }

    public void setAppliedEmpId(String appliedEmpId){
        this.appliedEmpId=appliedEmpId;
    }

    public void setLeaveType(String leaveType){
        this.leaveType=leaveType;
    }

    public void setLeaveCategory(String leaveCategory){
        this.leaveCategory=leaveCategory;
    }

    public void setLeaveDate(String leaveDate){
        this.leaveDate=leaveDate;
    }

    public void setLeaveRemarks(String leaveRemarks){
        this.leaveRemarks=leaveRemarks;
    }

    public void setCurrentBalance(String curBal){
        this.curBal=curBal;
    }

    public void setStatus(String applyStatus){
        this.applyStatus=applyStatus;
    }

    public void setStatusCode(String applyStatusCode){
        this.applyStatusCode=applyStatusCode;
    }

    public void setLeaveId(String leaveId){
        this.leaveId=leaveId;
    }

    public int getAddStatus(){
        return applyNewLeave;
    }

    public void addLeave(){

        if(appliedEmpId.equals("")){
            appliedEmpId=empId;
        }

        Connection con=null;
        Statement statement = null;
        incrGpId=0;
        String insertLeaveInfo ="";
        applyNewLeave=0;
        String maxGrpId="";
        collectAppliedLeave="";

        try{
            DBconnection dbcon = new DBconnection();
            con=dbcon.getSampleProperty();
            statement = con.createStatement();

//System.out.println("leaveId in add leave records : "+leaveId);
//System.out.println("applyStatus in add leave records : "+applyStatus);
//System.out.println("applyStatusCode in add leave records : "+applyStatusCode);
//System.out.println("leaveRemarks in add leave records : "+leaveRemarks);

            if(applyStatus.equals("2"))  {
                insertLeaveInfo = "";
                insertLeaveInfo = "update emp_leave_info set cancel_empid = '"+empId+"',cancel_date=current_date() where leave_id = '"+leaveId+"'";
                //System.out.println("insertLeaveInfo in AdRecord:"+insertLeaveInfo);

                applyNewLeave = statement.executeUpdate(insertLeaveInfo);
            }

            if(applyStatus.equals("AllocateApprove")){
                 insertLeaveInfo = "";
                insertLeaveInfo = "update emp_leave_info set cancel_approve_date = current_timestamp()," +
                        "cancel_approve_empid='"+appliedEmpId+"',cancel_approve_code='1'," +
                        "cancel_empid='"+appliedEmpId+"',cancel_date=current_date where leave_id = '"+leaveId+"'";
                //System.out.println("insertLeaveInfo in AdRecord:"+insertLeaveInfo);

                applyNewLeave = statement.executeUpdate(insertLeaveInfo);
            }
            if(!leaveType.equals("") && !curBal.equals("")){
                insertLeaveInfo = "";
                insertLeaveInfo = "update emp_leave_info set lv_type_id='"+leaveType+"' " +
                        "where leave_id = '"+leaveId+"'";
                //System.out.println("insertLeaveInfo in AdRecord:"+insertLeaveInfo);

                applyNewLeave = statement.executeUpdate(insertLeaveInfo);
            }

        }catch(SQLException sqle){
            System.out.println("SQLException in AddLeaveRecord:"+sqle);
        }finally{
            try{
                statement.close();
                con.close();
            }catch(SQLException sqle){
            System.out.println("SQLException in AddLeaveRecord:"+sqle);
            }
        }
    }
}