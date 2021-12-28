/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.io.*;

import java.beans.*;
import java.io.Serializable;


public class ProjAllocateReport implements Serializable {
    
    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    
    private String projID="";
    private String plannerId="";
    private String empId="";
    private String cellId="";
    private String projFromDate="";

    public String getProjFromDate() {
        return projFromDate;
    }

    public void setProjFromDate(String projFromDate) {
        this.projFromDate = projFromDate;
    }

    public String getProjToDate() {
        return projToDate;
    }

    public void setProjToDate(String projToDate) {
        this.projToDate = projToDate;
    }
    private String projToDate="";
    
    private String sql_select = "";
    private String sql_where = "";
    
    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(String plannerId) {
        this.plannerId = plannerId;
    }

    public String getProjID() {
        return projID;
    }

    public void setProjID(String projID) {
        this.projID = projID;
    }

   
    private List projIDList = new ArrayList();

    public List getCellList() {
        return cellList;
    }

    public void setCellList(List cellList) {
        this.cellList = cellList;
    }

    public List getClientList() {
        return clientList;
    }

    public void setClientList(List clientList) {
        this.clientList = clientList;
    }

    public List getProjBkTitleList() {
        return projBkTitleList;
    }

    public void setProjBkTitleList(List projBkTitleList) {
        this.projBkTitleList = projBkTitleList;
    }

    public List getProjCreatedDateList() {
        return projCreatedDateList;
    }

    public void setProjCreatedDateList(List projCreatedDateList) {
        this.projCreatedDateList = projCreatedDateList;
    }

    public List getProjIDList() {
        return projIDList;
    }

    public void setProjIDList(List projIDList) {
        this.projIDList = projIDList;
    }

    public List getProjIsbnList() {
        return projIsbnList;
    }

    public void setProjIsbnList(List projIsbnList) {
        this.projIsbnList = projIsbnList;
    }

    public List getProjPlannerList() {
        return projPlannerList;
    }

    public void setProjPlannerList(List projPlannerList) {
        this.projPlannerList = projPlannerList;
    }
    private List projBkTitleList = new ArrayList();
    private List projIsbnList = new ArrayList();
    private List projPlannerList = new ArrayList();
    private List clientList = new ArrayList();
    private List cellList = new ArrayList();
    private List cellCodeList = new ArrayList();
    private List projCreatedDateList = new ArrayList();
    private List projTeamList = new ArrayList();
    private List compositionDateList = new ArrayList();

    public List getCompositionDateList() {
        return compositionDateList;
    }

    public void setCompositionDateList(List compositionDateList) {
        this.compositionDateList = compositionDateList;
    }

    public List getProjTeamList() {
        return projTeamList;
    }

    public void setProjTeamList(List projTeamList) {
        this.projTeamList = projTeamList;
    }
    
    public ProjAllocateReport() {
       
    }
    
    
    public void generateProjAllocateReport(){

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        
        try{
               con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            
            sql_select="";
            sql_where="";
            
                sql_select= "select p.proj_id,p.proj_name,p.proj_isbn_10,"
                        + " concat(c.company,'/',divi.company),date_format(p.proj_date,'%d/%m/%y') from "
+"projects p,contacts c,contacts divi ";
            
             sql_where=" where  p.client_name=c.contact_id  and divi.contact_id=p.division_id  ";
             
             
           if(!projID.equals("")&&projID!=null){
              sql_where += " and p.proj_id='"+projID+"' " ;
           }
            
           if(!empId.equals("")&&empId!=null){
               sql_select += " , emp_cell_map ecm ";
              sql_where += " and ecm.emp_id='"+empId+"' and ecm.cell_code=p.cell_code " ;
           }
            
            if(!cellId.equals("")&&cellId!=null){
              sql_where += " and p.cell_code='"+cellId+"' " ;
           }           
           
           if(!plannerId.equals("")&&plannerId!=null){
              sql_where += " and p.planner='"+plannerId+"' " ;
           } 
           
           if(!projFromDate.equals("")&&!projToDate.equals("")){               
               sql_where += " and date_format(p.proj_date,'%Y-%m-%d') between  '"+projFromDate+"' and '"+projToDate+"' " ;
           }
           
           sql_select = sql_select+sql_where+ " order by p.proj_date desc ";
           //System.out.println("sql_select prj allocate report:"+sql_select);
           
            String queryData = "";
            rs = stmt.executeQuery(sql_select);            
            while(rs.next()){                            
                queryData = rs.getString("p.proj_id");
                projIDList.add(queryData);    
                
                queryData = rs.getString("p.proj_name");
                if(rs.wasNull()){
                    queryData= "";
                }
                projBkTitleList.add(queryData);
                
                queryData = rs.getString("p.proj_isbn_10");
                if(rs.wasNull()){
                    queryData= "";
                }
                projIsbnList.add(queryData);
                
                queryData = splChar.decoding(rs.getString("concat(c.company,'/',divi.company)"));
                if(rs.wasNull()){
                    queryData= "";
                }
                clientList.add(queryData); 
                
                queryData = rs.getString("date_format(p.proj_date,'%d/%m/%y')");                
                if(rs.wasNull()){
                    queryData= "";
                }
                projCreatedDateList .add(queryData);                
                
            }
            
            //System.out.println("projBkTitleList:"+projBkTitleList);
            
            String getLoopProjCell = "";
            String getLoopProjId = "";
            String getLoopQueryResult = "";
            for(int idx=0;idx<projIDList.size();idx++){
                
            //get the cell code and cell name for the project
              
                 queryData= "";
                 getLoopProjCell = "";
   
               sql_select= "select cl.cell_code,cl.cell_name from "
                            +"projects p,cell cl where   cl.cell_code=p.cell_code and p.proj_id='"+getLoopProjId+"' ";
               
             ResultSet  rs_cellName=stmt.executeQuery(sql_select);
              if(rs_cellName.next()){   
                  rs_cellName=stmt.executeQuery(sql_select);
                        while(rs_cellName.next()) {
                            queryData = rs_cellName.getString("cl.cell_code");
                            if(rs_cellName.wasNull()){
                                queryData= "";
                            }
                        cellCodeList.add(queryData);
                        getLoopProjCell = queryData;
                        
                        queryData = rs_cellName.getString("cl.cell_name");
                        if(rs_cellName.wasNull()){
                            queryData= "";
                        }
                        cellList.add(queryData);
                     }
                }else{
                     cellCodeList.add(""); 
                     cellList.add(""); 
                     // //System.out.println("sql_select planner sql:"+sql_select);
                }
             rs_cellName.close();
                
                getLoopProjId = projIDList.get(idx).toString(); 
                getLoopQueryResult="";
                
               /* if(getLoopProjCell.equals("DEW")) 
                //System.out.println("select u.emp_name from emp_cell_map ecm,user u where"
                        + " u.emp_id=ecm.emp_id and ecm.cell_code='"+getLoopProjCell+"' ");*/
                
               if(!getLoopProjCell.equals("")){ 
                rs = stmt.executeQuery("select u.emp_name from emp_cell_map ecm,user u where"
                        + " u.emp_id=ecm.emp_id and ecm.cell_code='"+getLoopProjCell+"' ");
                       if(rs.next()){
                            rs = stmt.executeQuery("select u.emp_name from emp_cell_map ecm,user u where"
                                + " u.emp_id=ecm.emp_id and ecm.cell_code='"+getLoopProjCell+"' ");

                                    while(rs.next()){
                                        queryData = rs.getString("u.emp_name");                      
                                    getLoopQueryResult += queryData;                        
                                        if(rs.isLast()){

                                        }
                                        else{
                                           getLoopQueryResult += ","; 
                                        }

                                    }
               
                 projTeamList.add(getLoopQueryResult);             
               }
               else{
                    projTeamList.add(""); 
               }
            }else{
                    projTeamList.add(""); 
               }
               
          //get the planner name for the project     
           queryData= "";
               sql_select= "select u.emp_name from "
                            +"projects p,user u where  p.planner=u.emp_id and p.proj_id='"+getLoopProjId+"' ";
               
             ResultSet  rs_planner=stmt.executeQuery(sql_select);
              if(rs_planner.next()){   
                  rs_planner=stmt.executeQuery(sql_select);
                   while(rs_planner.next()) {
                        queryData = rs_planner.getString("u.emp_name");
                        if(rs_planner.wasNull()){
                            queryData= "";
                        }
                        projPlannerList.add(queryData); 
                            }
                }else{
                      projPlannerList.add(""); 
                     // //System.out.println("sql_select planner sql:"+sql_select);
                }
             rs_planner.close();
             
              
              
                       getLoopQueryResult="";
                       
                       rs = stmt.executeQuery("select date_format(ch.added_date,'%d/%m/%y') from chapter ch,projects p where ch.proj_id=p.proj_id "
                               + "and p.proj_id='"+getLoopProjId+"' and ch.stage='FP' order by ch.added_date limit 1");
                       if(rs.next()){
                           rs = stmt.executeQuery("select date_format(ch.added_date,'%d/%m/%y') from chapter ch,projects p where ch.proj_id=p.proj_id "
                               + "and p.proj_id='"+getLoopProjId+"' and ch.stage='FP' order by ch.added_date limit 1");
                     
                       while(rs.next()){
                           getLoopQueryResult = rs.getString(1);  
                       }
                       compositionDateList.add(getLoopQueryResult);
                       }else{
                       compositionDateList.add("");
                       }
                        
            }
                //System.out.println("projTeamListSize:"+projTeamList.size());
                // //System.out.println("projTeam:"+projTeamList);
             //System.out.println("projCreatedDateList:"+projCreatedDateList.size());
              //System.out.println("projIDList :"+projIDList.size());
               //System.out.println("projBkTitleList :"+projBkTitleList.size());
                 //System.out.println("projIsbnList :"+projIsbnList.size());
                   //System.out.println("projPlannerList :"+projPlannerList.size());
                     //System.out.println("clientList :"+clientList.size());
                       //System.out.println("cellList :"+cellList.size());
                       //System.out.println("cellCodeList :"+cellCodeList.size());
            
                     //System.out.println("clientList :"+clientList.size());
                       //System.out.println("compositionDateList :"+compositionDateList.size());
                       
                       
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
