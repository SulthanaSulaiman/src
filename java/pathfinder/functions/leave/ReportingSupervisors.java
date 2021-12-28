/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import java.io.*;
import java.sql.*;
import java.util.*;
import connection.DBconnection;

/**
 *
 * @author ramyamaims
 */
public class ReportingSupervisors {

    private List supEmpId = new ArrayList();
    private List supEmpName = new ArrayList();
    private List supDesig = new ArrayList();
    private List supDept = new ArrayList();

    private String deptCode="";
    private String desigCode="";
    private String empSql = "";

    private List setEmpId = new ArrayList();
    private List setSupEmpId = new ArrayList();

    public void setEmpIdList(List setEmpId){
        this.setEmpId = setEmpId;
    }

    public void setSupEmpIdList(List setSupEmpId){
        this.setSupEmpId = setSupEmpId;
    }
    public void setEmpDept(String deptCode){
        this.deptCode=deptCode;
        //System.out.println("deptCode:"+deptCode);
     }

     public void setEmpDesig(String desigCode){
        this.desigCode=desigCode;
        //System.out.println("desigCode:"+desigCode);
     }

    public void getReportingSupervisors(){
        Connection con = null;
        ResultSet rs = null;

         try{
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();

            Statement stmt = con.createStatement();
            supEmpId.clear();
            supEmpName.clear();
            supDesig.clear();
            supDept.clear();


            //System.out.println("setEmpId in RS************ :"+setEmpId+": ************** setEmpId");
            for(int loop=0;loop<setSupEmpId.size();loop++){
                empSql = "";

                //System.out.println("setEmpId in RS************ :"+setSupEmpId.get(loop));

                empSql = "select u.emp_id,u.emp_name,d.department,de.designation " +
                        "from user u, department d, designation de where u.dept_code = d.dept_code " +
                        "and u.desig_code = de.desig_code and u.emp_id = '"+setSupEmpId.get(loop)+"' and u.status=1 ";
                if(!deptCode.equals("")&&!deptCode.equals("All")){
                    empSql += " and u.dept_code='"+deptCode+"' ";
                }

                if(!desigCode.equals("")&&!desigCode.equals("All")){
                    empSql += " and u.desig_code='"+desigCode+"' ";
                }
                rs = stmt.executeQuery(empSql);

                while(rs.next()){
                    supEmpId.add(rs.getString(1));
                    supEmpName.add(rs.getString(2));
                    supDesig.add(rs.getString(4));
                    supDept.add(rs.getString(3));
                    //System.out.println("supEmpId . add ************ :"+supEmpId);
                }
                empSql = "";
                empSql = "select u.emp_id,u.emp_name,d.department,de.designation " +
                        "from user u, department d, designation de where u.dept_code = d.dept_code " +
                        "and u.desig_code = de.desig_code and u.supervisor_id = '"+setSupEmpId.get(loop)+"' and u.status=1 ";
                if(!deptCode.equals("")&&!deptCode.equals("All")){
                    empSql += " and u.dept_code='"+deptCode+"' ";
                }

                if(!desigCode.equals("")&&!desigCode.equals("All")){
                    empSql += " and u.desig_code='"+desigCode+"' ";
                }
                rs = stmt.executeQuery(empSql);

                while(rs.next()){
                    supEmpId.add(rs.getString(1));
                    supEmpName.add(rs.getString(2));
                    supDesig.add(rs.getString(4));
                    supDept.add(rs.getString(3));
                    //System.out.println("supEmpId . add ************ :"+supEmpId);
                }

            }
//System.out.println("supEmpId in RS************ :"+supEmpId+" : ********* supEmpId");
            stmt.close();

         }catch(SQLException sqle){
             sqle.printStackTrace();
         }catch(NullPointerException npe){
             npe.printStackTrace();
         }
        finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}
    }

    public List getSupEmpId(){
        return supEmpId;
    }

    public List getSupEmpName(){
        return supEmpName;
    }

    public List getSupDesig(){
        return supDesig;
    }

    public List getSupDept(){
        return supDept;
    }
}