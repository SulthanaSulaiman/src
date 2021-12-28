/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;
import java.io.Serializable;

import java.sql.*;
import connection.*;

/**
 *
 * @author  ramesh
 */
public class ApproveLeaves implements Serializable {

private String empId = "";
private String leaveId = "";
private String appCode = "";
private String appRemarks = "";
    private String inputType = "";

public void setEmpId(String empId){
    this.empId=empId;
}

public void setLeaveId(String leaveId){
    this.leaveId=leaveId;
}

    public void setInputType(String inputType){
        this.inputType=inputType;
    }

public void setApprovalCode(String appCode){
    this.appCode=appCode;
}

public void setApprovalRemarks(String appRemarks){
    this.appRemarks=appRemarks;
}


    private Connection con=null;

    public void approve(){
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        try{
        //the below block isto collect the leave type id's of the leave that could be applied by the employee

            Statement st = con.createStatement();
            if(inputType.equals("1")){
                //System.out.println("update emp_leave_info set lv_approve_code='"+appCode+"',lv_approve_empid='"+empId+"',lv_approve_date=CURRENT_TIMESTAMP(),lv_app_remarks='"+appRemarks+"' where leave_id='"+leaveId+"' ");
                st.executeUpdate("update emp_leave_info set lv_approve_code='"+appCode+"',lv_approve_empid='"+empId+"',lv_approve_date=CURRENT_TIMESTAMP(),lv_app_remarks='"+appRemarks+"' where leave_id='"+leaveId+"' ");
            }

            if(inputType.equals("2")){
                //System.out.println("update emp_leave_info set cancel_approve_code='"+appCode+"',cancel_approve_empid='"+empId+"',cancel_approve_date=CURRENT_TIMESTAMP(),cancel_approve_remarks='"+appRemarks+"' where leave_id='"+leaveId+"' ");
                st.executeUpdate("update emp_leave_info set cancel_approve_code='"+appCode+"',cancel_approve_empid='"+empId+"',cancel_approve_date=CURRENT_TIMESTAMP(),cancel_approve_remarks='"+appRemarks+"' where leave_id='"+leaveId+"' ");
            }
//            System.out.println("lv_TypeId in ApproveLeaves:"+empId);
//            System.out.println("lv_Type in ApproveLeaves:"+leaveId);
//            System.out.println("lv_Limit in ApproveLeaves:"+appCode);

            st.close();
        }catch(SQLException sqle){
            System.out.println("sqle in PresetLeaveBalance:"+sqle);
        }catch(Exception e){
            System.out.println("Excpetion in PresetLeaveBalance:"+e);
        }finally{
            try{
                con.close();
            }catch(SQLException sqle){
            System.out.println("sqle in PresetLEaveBalance-conclose:"+sqle);
            }
        }
    }
}