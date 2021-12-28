/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.timesheet;


import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.io.*;

import java.beans.*;
import java.io.Serializable;

public class DeptCostReport implements Serializable {
    
   DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;

    public List getAddedDateList() {
        return addedDateList;
    }

    public void setAddedDateList(List addedDateList) {
        this.addedDateList = addedDateList;
    }

    public List getCostValueList() {
        return costValueList;
    }

    public void setCostValueList(List costValueList) {
        this.costValueList = costValueList;
    }

    public List getCurrencyNameList() {
        return currencyNameList;
    }

    public void setCurrencyNameList(List currencyNameList) {
        this.currencyNameList = currencyNameList;
    }

    public List getDeptNameList() {
        return deptNameList;
    }

    public void setDeptNameList(List deptNameList) {
        this.deptNameList = deptNameList;
    }
    ResultSet rsCollectDetails = null;
    
    public DeptCostReport() {
        
    }
    
    private List deptNameList = new ArrayList();
    private List currencyNameList = new ArrayList();
    private List costValueList = new ArrayList();
    private List addedDateList = new ArrayList();
    private String queryData="";
    
     public void collectDeptCostReport(){
        try{
           con = connection.getSampleProperty();
            stmt = con.createStatement();
            rsCollectDetails=stmt.executeQuery("select dpt. department,cu.short_name,dpct.dept_cost,date_format(added_Date,'%d-%m-%Y') from department dpt, currency cu, dept_cost dpct where dpt.dept_code=dpct.dept_code and dpct.currency_id=cu.currency_id ");
            while(rsCollectDetails.next()){
                queryData = rsCollectDetails.getString("dpt.department"); 
                deptNameList.add(queryData);
                queryData = rsCollectDetails.getString("cu.short_name");   
                currencyNameList.add(queryData);                
                queryData = rsCollectDetails.getString("dpct.dept_cost");
                costValueList.add(queryData);
                queryData = rsCollectDetails.getString("date_format(added_Date,'%d-%m-%Y')");   
                addedDateList.add(queryData);
            }
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
       
    }
    
    
}