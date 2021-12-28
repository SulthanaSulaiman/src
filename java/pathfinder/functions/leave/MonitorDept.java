/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import java.util.*;
import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 */
public class MonitorDept implements Serializable {
    //class to get the list of dept monitored by a supervisor

   private String supervisorId = "";
   private String empId = "";
   private String ses_deptCode = "";
   private List deptCode = new ArrayList();
   private List deptName = new ArrayList();


    public MonitorDept() {

    }

     public List getDeptCode(){
        return deptCode;
    }

    public List getDeptName(){
        return deptName;
    }


    public void setSupervisorId(String supervisorId){
        this.supervisorId=supervisorId;
    }

     public void setSesDeptCode(String ses_deptCode){
        this.ses_deptCode=ses_deptCode;
    }

    public void collectDept(){
         Connection con = null;
         try{
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            Statement statement = con.createStatement();

            deptCode.clear();
            deptName.clear();
            String deptSql = "";

            if(!ses_deptCode.equals("HR")){
                deptSql = "select distinct(u.dept_code),dpt.department from user u,department dpt " +
                        "where u.supervisor_id in (select emp_id from user where status='1' " +
                        "and (supervisor_id='"+supervisorId+"' || emp_id = '"+supervisorId+"')) " +
                        "and u.dept_code=dpt.dept_code and u.status = '1' ";
            }else{
                deptSql = "select distinct(u.dept_code),dpt.department from user u,department dpt " +
                        "where u.dept_code=dpt.dept_code and u.status = '1' ";
            }

            deptSql +="order by dpt.department";

            //System.out.println("deptSql in MonitorDept : "+deptSql);

            ResultSet rsGetDept = statement.executeQuery(deptSql);
                    while(rsGetDept.next()){
                        deptCode.add(rsGetDept.getString(1));
                        deptName.add(rsGetDept.getString(2));
                        empId += "'"+rsGetDept.getString(2)+"'";
                    }
            //System.out.Println("empId : "+empId);
            rsGetDept.close();
            statement.close();

        }catch(SQLException sqle){
            System.out.println("SQLE in MonitorDept"+sqle);
        }catch(Exception e){
            System.out.println("SQLE in MonitorDept"+e);
        }finally{
            try{
                con.close();
            }catch(SQLException sqle){

            }
        }

    }

}
