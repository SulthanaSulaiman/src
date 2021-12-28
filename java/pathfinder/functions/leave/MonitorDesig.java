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
public class MonitorDesig implements Serializable {

    //class to get the list of desig monitored by a supervisor

   private String supervisorId = "";
   private String deptCode = "";
   private String ses_deptCode = "";
   private List desigCode = new ArrayList();
   private List desigName = new ArrayList();


    public MonitorDesig() {

    }

    public List getDesigCode(){
        return desigCode;
    }

    public List getDesigName(){
        return desigName;
    }


    public void setSupervisorId(String supervisorId){
        this.supervisorId=supervisorId;
    }

     public void setSesDeptCode(String ses_deptCode){
        this.ses_deptCode=ses_deptCode;
    }

     public void setDeptCode(String deptCode){
        this.deptCode=deptCode;
    }



    public void collectDesig() {

        Connection con = null;

        try{
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();

            desigCode.clear();
            desigName.clear();

            Statement statement = con.createStatement();

            String desigSql = "";

            if(!ses_deptCode.equals("HR")){
                desigSql = " select distinct(u.desig_code),dsg.designation from user u,designation dsg " +
                        "where u.supervisor_id in (select emp_id from user where status='1' " +
                        "and (supervisor_id='"+supervisorId+"' || emp_id = '"+supervisorId+"')) " +
                        "and u.desig_code=dsg.desig_code and u.status = '1' ";
            }else{
                desigSql = " select distinct(u.desig_code),dsg.designation from user u,designation dsg " +
                        "where u.desig_code=dsg.desig_code and u.status = '1' ";
            }

            if(!deptCode.equals("")){
                desigSql +="and u.dept_code='"+deptCode+"' ";
            }

            desigSql +="order by dsg.designation ";

            //System.out.println("desigSql in MonitorDesig : "+desigSql);

            ResultSet rsGetDept = statement.executeQuery(desigSql);
            while(rsGetDept.next()){
                desigCode.add(rsGetDept.getString("u.desig_code"));
                desigName.add(rsGetDept.getString("dsg.designation"));
            }

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
