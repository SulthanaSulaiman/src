/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import connection.DBconnection;
import java.io.Serializable;
import java.util.*;
import java.sql.*;

/**
 *
 * @author ramesh
 */
public class ReportingEmployees implements Serializable {

    private String deptCode="";
    private String desigCode="";
    private String ses_deptCode = "";

    private List empId = new ArrayList();
    private List set_empId = new ArrayList();
    private List set_supEmpId = new ArrayList();
    private List empName = new ArrayList();

    private List supEmpId = new ArrayList();
    private List supEmpName = new ArrayList();
    private List supDesig = new ArrayList();
    private List supDept = new ArrayList();

    private String supervisorId = "";

    public void setDeptCode(String deptCode){
        this.deptCode=deptCode;
        //System.out.println("deptCode:"+deptCode);
     }

     public void setDesigCode(String desigCode){
        this.desigCode=desigCode;
        //System.out.println("desigCode:"+desigCode);
     }

     public void setSupervisorId(String supervisorId){
         this.supervisorId=supervisorId;
     }

     public void setSesDeptCode(String ses_deptCode){
        this.ses_deptCode=ses_deptCode;
     }

     public void getEmployeeList(){

         Connection con = null;

         try{
             DBconnection dbcon = new DBconnection();
             con = dbcon.getSampleProperty();
             String empSql= "";
             Statement statement = con.createStatement();

             empId.clear();
             empName.clear();
             set_supEmpId.clear();
             set_empId.clear();
             if(!ses_deptCode.equals("HR")){

                 empSql="select u.emp_id,u.emp_name from user u where u.status='1' and supervisor_id='"+supervisorId+"' ";


                 //u.dept_code='"+deptCode+"' and u.desig_code='"+desigCode+"' and

                 /**if(!deptCode.equals("")&&!deptCode.equals("All")){
                  * empSql += " and u.dept_code='"+deptCode+"' ";
                  * }
                  *
                  * if(!desigCode.equals("")&&!desigCode.equals("All")){
                  * empSql += " and u.desig_code='"+desigCode+"' ";
                  * }*/

                 empSql += " order by u.emp_name ";

                 //System.out.print("empSql:"+empSql);
                 ResultSet rsGetDept = statement.executeQuery(empSql);
                 while(rsGetDept.next()){
                     empId.add(rsGetDept.getString("u.emp_id"));
                     empName.add(rsGetDept.getString("u.emp_name"));
                 }
                 rsGetDept.close();
                 //System.out.print("empId:"+empId);
                 //System.out.print("empName:"+empName);

                 ReportingSupervisors rs = new ReportingSupervisors();

                 for(int loop=0;loop<empId.size();loop++){

                     ResultSet resultSet = statement.executeQuery("select u.emp_id " +
                             "from user u where u.supervisor_id = '"+empId.get(loop)+"' and u.status=1");

                     if(resultSet.next()){
                         set_supEmpId.add(empId.get(loop));
                         resultSet = statement.executeQuery("select u.emp_id " +
                                 "from user u where u.supervisor_id = '"+empId.get(loop)+"' and u.status=1");

                         while(resultSet.next()){
                             set_empId.add(resultSet.getString(1));
                         }
                     }else {
                         set_supEmpId.add(empId.get(loop));
                     }
                     resultSet.close();
                 }
                 //System.out.println("set_empId ************ :"+set_empId+": ************** set_empId");
                 rs.setEmpIdList(set_empId);
                 rs.setEmpDept(deptCode);
                 rs.setEmpDesig(desigCode);
                 rs.setSupEmpIdList(set_supEmpId);
                 rs.getReportingSupervisors();
                 supEmpId = rs.getSupEmpId();
                 supEmpName = rs.getSupEmpName();
                 supDesig = rs.getSupDesig();
                 supDept  = rs.getSupDept();
                 //System.out.println("supEmpId ************ :"+supEmpId+": ************** supEmpId");
                 //to get the reporting Employees for the Employee ID's collected in above loop
                 //and append it to the Employee ID and Name list
             }else{

                 empSql="select u.emp_id,u.emp_name from user u where u.status='1' ";

                 //u.dept_code='"+deptCode+"' and u.desig_code='"+desigCode+"' and

                 if(!deptCode.equals("")&&!deptCode.equals("All")){
                     empSql += " and u.dept_code='"+deptCode+"' ";
                 }

                 if(!desigCode.equals("")&&!desigCode.equals("All")){
                     empSql += " and u.desig_code='"+desigCode+"' ";
                 }

                 empSql += " order by u.emp_name ";

                 //System.out.print("empSql:"+empSql);
                 ResultSet rsGetDept = statement.executeQuery(empSql);
                 while(rsGetDept.next()){
                     supEmpId.add(rsGetDept.getString("u.emp_id"));
                     supEmpName.add(rsGetDept.getString("u.emp_name"));
                 }
                 rsGetDept.close();
                 //System.out.print("empId:"+empId);
                 //System.out.print("empName:"+empName);
             }

             statement.close();
         }catch(SQLException sqle){
             System.out.println("SQLE in ReportingEmployees : "+sqle);
         }catch(Exception e){
             System.out.println("SQLE in ReportingEmployees : "+e);
         }finally{
             try{
                 con.close();
             }catch(SQLException sqle){

             }
         }
    }

    public List getEmpId(){
        return supEmpId;
    }

    public List getEmpName(){
        return supEmpName;
    }

    public List getDesig(){
        return supDesig;
    }

    public List getDept(){
        return supDept;
    }
}