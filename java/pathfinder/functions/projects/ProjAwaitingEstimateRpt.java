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

/**
 *
 * @author PERUMAL
 */
public class ProjAwaitingEstimateRpt implements Serializable {
    
    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    
    
    public ProjAwaitingEstimateRpt() {
      
    }
    
    private List projIdList= new ArrayList();

    public List getProjAuthorList() {
        return projAuthorList;
    }

    public void setProjAuthorList(List projAuthorList) {
        this.projAuthorList = projAuthorList;
    }

    public List getProjCustomerList() {
        return projCustomerList;
    }

    public void setProjCustomerList(List projCustomerList) {
        this.projCustomerList = projCustomerList;
    }

    public List getProjIdList() {
        return projIdList;
    }

    public void setProjIdList(List projIdList) {
        this.projIdList = projIdList;
    }

    public List getProjIsbnList() {
        return projIsbnList;
    }

    public void setProjIsbnList(List projIsbnList) {
        this.projIsbnList = projIsbnList;
    }

    public List getProjNameList() {
        return projNameList;
    }

    public void setProjNameList(List projNameList) {
        this.projNameList = projNameList;
    }

    
    public List getProjCreatedDateList() {
        return projCreatedDateList;
    }

    public void setProjCreatedDateList(List projCreatedDateList) {
        this.projCreatedDateList = projCreatedDateList;
    }
 
    public List getProjPlannerList() {
        return projPlannerList;
    }

    public void setProjPlannerList(List projPlannerList) {
        this.projPlannerList = projPlannerList;
    }
       
    private List projCreatedDateList = new ArrayList();
    private List projPlannerList = new ArrayList();
    private List projNameList= new ArrayList();
    private List projAuthorList= new ArrayList();
    private List projIsbnList= new ArrayList();
    private List projCustomerList= new ArrayList();
    private List estimatesDueOnList = new ArrayList();

    public List getEstimatesDueOnList() {
        return estimatesDueOnList;
    }

    public void setEstimatesDueOnList(List estimatesDueOnList) {
        this.estimatesDueOnList = estimatesDueOnList;
    }

    public List getEstimatesSentOnList() {
        return estimatesSentOnList;
    }

    public void setEstimatesSentOnList(List estimatesSentOnList) {
        this.estimatesSentOnList = estimatesSentOnList;
    }
    private List estimatesSentOnList = new ArrayList();
    
    ResultSet rsCollectDetails=null;
    private String projToDate="";
    private String projFromDate="";
    private String projID="";

    public String getProjID() {
        return projID;
    }

    public void setProjID(String projID) {
        this.projID = projID;
    }
    
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
    
    
    private String sql_select = "";
    private String sql_where = "";
  
    
    public void collectProjAwaitingEstimates(){
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try{
         
            con = connection.getSampleProperty();
            stmt = con.createStatement();
            String  queryData = "";
               
            sql_select="";
            sql_where="";
            
                sql_select= "select pr.proj_id,pr.proj_name,"
                    + "concat(athr.firstname,' ',athr.surname),pr.proj_isbn_10,"
                    + "concat(cnt.company,'/',divi.company),date_format(pr.projected_printer_date,'%d/%m/%Y'),"
                        + "date_format(pr.proj_date,'%d/%m/%Y'),date_format(date(pr.proj_date) + INTERVAL 7 day,'%d/%m/%Y') "
                    + " from "
                    + "projects pr,contacts athr,project_authors pra,contacts cnt,contacts divi ";
            
             sql_where= "where "
                    + "pr.proj_id=pra.proj_id and pra.primary_author='1' and athr.contact_id=pra.author_id and"
                    + " pr.client_name=cnt.contact_id  and divi.contact_id=pr.division_id";
             
            
              
           if(!projID.equals("")&&projID!=null){
              sql_where += " and pr.proj_id='"+projID+"' " ;
           }
           
           if(!projFromDate.equals("")&&!projToDate.equals("")){               
               sql_where += " and date_format(pr.proj_date,'%Y-%m-%d') between  '"+projFromDate+"' and '"+projToDate+"' " ;
           }
                          
                sql_select = sql_select+sql_where+" order by pr.est_sent_date desc ";

             //System.out.println("sql_select:"+sql_select);
             
            rsCollectDetails = stmt.executeQuery(sql_select);                    
            while(rsCollectDetails.next()){
                projIdList.add(rsCollectDetails.getString("pr.proj_id"));
                
                 queryData = rsCollectDetails.getString("pr.proj_name");
                  if(rsCollectDetails.wasNull()){
                    queryData= "";
                  }
                 projNameList.add(queryData);
                 
                 queryData = splChar.decoding(rsCollectDetails.getString("concat(athr.firstname,' ',athr.surname)"));
                 if(rsCollectDetails.wasNull()){
                    queryData= "";
                  }
                 projAuthorList.add(queryData);
                 
                 queryData = rsCollectDetails.getString("pr.proj_isbn_10");
                  if(rsCollectDetails.wasNull()){
                    queryData= "";
                  }                 
                 projIsbnList.add(queryData);
                 
                 queryData = splChar.decoding(rsCollectDetails.getString("concat(cnt.company,'/',divi.company)"));
                  if(rsCollectDetails.wasNull()){
                    queryData= "";
                  }                 
                 projCustomerList.add(queryData);
                 
                    
                queryData = rsCollectDetails.getString("date_format(pr.proj_date,'%d/%m/%y')");                
                if(rsCollectDetails.wasNull()){
                    queryData= "";
                }
                projCreatedDateList.add(queryData);   
                
                queryData = rsCollectDetails.getString("date_format(date(pr.proj_date) + INTERVAL 7 day,'%d/%m/%y')");                
                if(rsCollectDetails.wasNull()){
                    queryData= "";
                }
                estimatesDueOnList.add(queryData);   
                
            }
            
            
            int projIDSize = projIdList.size();
            
                      String getLoopProjId = "";
            String getLoopQueryResult = "";
            
            
            for(int idx=0;idx<projIdList.size();idx++){
                
            //get the cell code and cell name for the project
              
                 queryData= "";
                 getLoopProjId = projIdList.get(idx).toString(); 
                 
               sql_select= "select u.emp_name from "
                            +"projects p,user u where  p.planner=u.emp_id and p.proj_id='"+getLoopProjId+"' ";
               
             ResultSet  rsCollectDetails=stmt.executeQuery(sql_select);
              if(rsCollectDetails.next()){   
                  rsCollectDetails=stmt.executeQuery(sql_select);
                   while(rsCollectDetails.next()) {
                        queryData = rsCollectDetails.getString("u.emp_name");
                        if(rsCollectDetails.wasNull()){
                            queryData= "";
                        }
                        projPlannerList.add(queryData); 
                            }
                }else{
                      projPlannerList.add(""); 
                     // System.out.println("sql_select planner sql:"+sql_select);
                }
              
               sql_select= "select date_format(pr.est_sent_date,'%d/%m/%y') from "
                            +" projects pr where  pr.proj_id='"+getLoopProjId+"' ";
               
               
               
             rsCollectDetails=stmt.executeQuery(sql_select);
              if(rsCollectDetails.next()){
                  rsCollectDetails=stmt.executeQuery(sql_select);
                   while(rsCollectDetails.next()) {
                        queryData = rsCollectDetails.getString("date_format(pr.est_sent_date,'%d/%m/%y')");
                        if(rsCollectDetails.wasNull()){
                            queryData= "-";
                        }
                        estimatesSentOnList.add(queryData); 
                    }
                }else{
                      estimatesSentOnList.add("-");
                     // System.out.println("sql_select planner sql:"+sql_select);
                }             
            }
            
            
            
             //System.out.println("projIdList:"+projIdList.size());
              //System.out.println("projNameList :"+projNameList.size());
               //System.out.println("projAuthorList :"+projAuthorList.size());
                 //System.out.println("projIsbnList :"+projIsbnList.size());
                   //System.out.println("projCustomerList :"+projCustomerList.size());
          //System.out.println("estimatesSentOnList :"+estimatesSentOnList);
                       
            
            
            
            
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        finally {
            try {
                rsCollectDetails.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}
    }
    
    

    
    
}
