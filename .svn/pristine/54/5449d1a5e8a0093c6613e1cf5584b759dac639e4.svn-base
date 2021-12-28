/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.hr;

import pathfinder.functions.projects.*;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.io.*;

public class CellSearchBean {
    
    
    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    private String sql_select = "";
    private String cellName;
    private String cellID;
    
  
    private String nextToken = "";
    public String setSearchKey = "";
    private StringTokenizer stk;
    String isSuggest="";
    
    private List searchWordList = new ArrayList();
    
   
    
    private List cellIDList = new ArrayList();
    private List cellNameList = new ArrayList();
    
        
    public void setSearchKey(String setSearchKey){
        this.setSearchKey = setSearchKey;        
        //System.out.println("setSearchKey-Bean:"+setSearchKey);      
    }

    public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }
   
    
    public CellSearchBean(){
        
    }
    
    
    
    public List getEmpIDList() {
        return cellIDList;
    }

    public void setEmpIDList(List empIDList) {
        this.cellIDList = empIDList;
    }

    public List getEmpNameList() {
        return cellNameList;
    }

    public void setEmpNameList(List empNameList) {
        this.cellNameList = empNameList;
    }
    
    public String getEmpID() {
        return cellID;
    }

    public void setEmpID(String empID) {
        this.cellID = cellID;
    }

    public String getEmpName() {
        return cellName;
    }

    public void setEmpName(String empName) {
        this.cellName = cellName;
    }
    
    public void collectEmpNameList(){
        
        
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
            
            sql_select= "select cl.cell_code,cl.cell_name from cell cl where cl.cell_name  like '"+searchWordList.get(0)+"' ";
            
            rs = stmt.executeQuery(sql_select);
            
            cellIDList.clear();
            cellNameList.clear();
            
            while(rs.next()){
                cellIDList.add(rs.getString(1));
                cellNameList.add(rs.getString(2));                
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
