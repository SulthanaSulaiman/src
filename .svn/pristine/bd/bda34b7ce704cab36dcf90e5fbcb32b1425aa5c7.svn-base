/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.io.*;

public class ProjPlannerSearchBean {
    
    
    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    private String sql_select = "";
    private String empName;
    private String empID;
    
    private String setClientName = "";
    private String nextToken = "";
    public String setSearchKey = "";
    private StringTokenizer stk;
    String isSuggest="";
    private String projId = "";
    private List searchWordList = new ArrayList();
    
    public String getProjID() {
        return projId;
    }

    public void setProjID(String projId) {
        this.projId = projId;
    }
    
    private List empIDList = new ArrayList();

    
        
    public void setSearchKey(String setSearchKey){
        this.setSearchKey = setSearchKey;        
        //System.out.println("setSearchKey-Bean:"+setSearchKey);
    }

    public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }
   
    
    public void setClientName(String setClientName){
        this.setClientName = setClientName;
    }
    
    public ProjPlannerSearchBean(){
        
    }
    
    
    
    public List getEmpIDList() {
        return empIDList;
    }

    public void setEmpIDList(List empIDList) {
        this.empIDList = empIDList;
    }

    public List getEmpNameList() {
        return empNameList;
    }

    public void setEmpNameList(List empNameList) {
        this.empNameList = empNameList;
    }
private List empNameList = new ArrayList();
    
    
    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    public void collectPlannerList(){
        
        
        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            
            
            stk=new StringTokenizer(setSearchKey," ");
                nextToken = "%";
                while(stk.hasMoreTokens()) {
                    nextToken+=stk.nextToken()+"%";             
                }
        
        searchWordList.add(nextToken);
            
            sql_select="";
            
            sql_select= "select u.emp_id,u.emp_name from projects p,user u where u.emp_id=p.planner  and p.project_status='1' ";
            
            if(!setSearchKey.equals("")){
                
                sql_select +=" and u.emp_name  like '"+searchWordList.get(0)+"' ";
                
            }
            sql_select += " GROUP BY u.emp_id";
            System.out.println(sql_select);
            rs = stmt.executeQuery(sql_select);
            
            empIDList.clear();
            empNameList.clear();
            
            while(rs.next()){
                empIDList.add(rs.getString(1));
                empNameList.add(rs.getString(2));
                
            }
            
          
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}
        
    }
    
    
}
