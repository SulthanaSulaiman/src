/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;
import java.sql.*;
import java.util.*;
import connection.DBconnection;

/**
 *
 * @author pathfinder
 */
public class ApprovalleaveDAO {
    private List empId = new ArrayList();
    private List leaveStatus =new ArrayList();
    private List sno = new ArrayList();
    

    public void setEmpId(List empId){
    this.empId = empId;
    }
public List getEmpId(){
    return empId;
    }
public void setleaveStatus(List leaveStatus){
    this.leaveStatus = leaveStatus;
    }
public List getleaveStatus(){
    return leaveStatus;
    }
public void setSno(List sno){
    this.sno = sno;
    }
public List getSno(){
    return sno;
    }
public void approveLeave(){}

}
