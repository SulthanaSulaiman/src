/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

/**
 *
 * @author Gandhimathidevic
 */
import java.sql.*;
import java.util.*;
import connection.*;

public class EmployeeAvailableLeaveDetails {

private String empId = "";
private float clnodaysAvailable = 0;
private float slnodaysAvailable = 0;
private float plnodaysAvailable = 0;
private String leaveType="";



private Connection con = null;
private Statement stmt = null;
private ResultSet rs = null;

public float getclAvailableLeaveDays(){
return clnodaysAvailable;
}
public void setclAvailableDays(float clnodaysAvailable){
this.clnodaysAvailable = clnodaysAvailable;
}
public float getslAvailableLeaveDays(){
return slnodaysAvailable;
}
public void setslAvailableDays(float slnodaysAvailable){
this.slnodaysAvailable = slnodaysAvailable;
}
public float getplAvailableLeaveDays(){
return plnodaysAvailable;
}
public void setplAvailableDays(float plnodaysAvailable){
this.plnodaysAvailable = plnodaysAvailable;
}
public void setLeaveType(String leaveType){
this.leaveType = leaveType;
}
public String getLeaveType(){
return leaveType;
}
public String getEmpId(){
return empId;
}
public void setEmpId(String empId){
this.empId = empId;
}
public void leavedetialsInfo(){
try {
            DBconnection dbcon = new DBconnection();
            Connection con = null;
            Statement stmt = null;
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            EmployeeAvailableLeaveDetails e1 = new EmployeeAvailableLeaveDetails();
            String leave =leaveType;
            String columnName = "";
            if (leave.equals("CL")){
                columnName ="cl_available";
            }
            else if(leave.equals("SL"))
            {
                columnName ="sl_available";
            }
            else if(leave.equals("PL"))
            {
                columnName ="pl_available";
            }
 else{

 }
            String query = "select "+columnName+" from emp_available_leave_details where emp_id='"+empId+"'";
            ResultSet rs1 = stmt.executeQuery("select cl_available,sl_available,pl_available from emp_available_leave_details where emp_id='"+empId+"'");
while (rs1.next()){
clnodaysAvailable = Float.parseFloat(rs1.getString(1));
slnodaysAvailable = Float.parseFloat(rs1.getString(2));
plnodaysAvailable = Float.parseFloat(rs1.getString(3));

}
     e1.setclAvailableDays(clnodaysAvailable);
     e1.setslAvailableDays(slnodaysAvailable);
     e1.setplAvailableDays(plnodaysAvailable);
}
catch(SQLException e){

e.printStackTrace();
}}
}
