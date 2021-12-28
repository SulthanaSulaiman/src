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


public class TitlesToPrinterRpt implements Serializable {
    
    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    
    
    public TitlesToPrinterRpt() {
      
    }
    
    private List projIdList= new ArrayList();
    private List firstPassShippedDateList = new ArrayList();
    private List invoicedDateList = new ArrayList();
       private String plannerId="";
       private String deptCode = "";

   public String getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(String plannerId) {
        this.plannerId = plannerId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }


    public List getFirstPassShippedDateList() {
        return firstPassShippedDateList;
    }

    public void setFirstPassShippedDateList(List firstPassShippedDateList) {
        this.firstPassShippedDateList = firstPassShippedDateList;
    }

    public List getInvoicedDateList() {
        return invoicedDateList;
    }

    public void setInvoicedDateList(List invoicedDateList) {
        this.invoicedDateList = invoicedDateList;
    }
    
    
    
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

    public List getProjPrinterDateList() {
        return projPrinterDateList;
    }

    public void setProjPrinterDateList(List projPrinterDateList) {
        this.projPrinterDateList = projPrinterDateList;
    }

    public List getProjPrinterList() {
        return projPrinterList;
    }

    public void setProjPrinterList(List projPrinterList) {
        this.projPrinterList = projPrinterList;
    }
    private List projNameList= new ArrayList();
    private List projAuthorList= new ArrayList();
    private List projIsbnList= new ArrayList();
    private List projCustomerList= new ArrayList();
    private List projPrinterDateList= new ArrayList();
    private List projPrinterList= new ArrayList();
    private List projCreatedDateList= new ArrayList();
    private List projStatusList= new ArrayList();

    private List proj_status = new ArrayList();
    private List proj_idList = new ArrayList();
    private List proj_name = new ArrayList();
    private List proj_authorName = new ArrayList();
    private List proj_planner = new ArrayList();
    private List proj_customer = new ArrayList();
    private List proj_fpShipDate = new ArrayList();
    private List proj_dueDate = new ArrayList();
    private List proj_estdPages = new ArrayList();
    private List proj_trimSize = new ArrayList();
    private List proj_category = new ArrayList();
    private List proj_estimatedPages = new ArrayList();
    private List proj_color = new ArrayList();
    private List proj_priority = new ArrayList();
    private List proj_level = new ArrayList();

    ResultSet rsCollectDetails=null;
    ResultSet rsCollectDetails1=null;
    ResultSet rs=null;
    ResultSet rs1=null;
    private String projToDate="";
    private String projFromDate="";
    private String projID="";


    public List getProjectIdList() {
        return proj_idList;
    }

    public List getProjectNameList() {
            return proj_name;
        }

    public List getProjectAuthorsList() {
            return proj_authorName;
        }

    public List getProjectPlannerList() {
            return proj_planner;
        }

    public List getProjectCustomersList() {
            return proj_customer;
        }

    public List getProjectFPShipDateList() {
            return proj_fpShipDate;
        }

    public List getProjectDueDateList() {
            return proj_dueDate;
        }
    public List getProjectEstdPagesList() {
            return proj_estdPages;
        }

    public List getProjectStatusList() {
            return proj_status;
        }

    public List getProjectTrimSizeList() {
            return proj_trimSize;
        }

    public List getProjectCategoryList() {
            return proj_category;
        }
    public List getProjectEstimatedPages() {
            return proj_estimatedPages;
        }

    public List getProjectColorList() {
            return proj_color;
        }

    public List getProjectPriorityList() {
            return proj_priority;
        }

    public List getProjectLevelList() {
            return proj_level;
        }



    public List getProjCreatedDateList() {
        return projCreatedDateList;
    }

    public void setProjCreatedDateList(List projCreatedDateList) {
        this.projCreatedDateList = projCreatedDateList;
    }

    public List getProjStatusList() {
        return projStatusList;
    }

    public void setProjStatusList(List projStatusList) {
        this.projStatusList = projStatusList;
    }

    
    
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
    
     public List getProjPlannerList() {
        return projPlannerList;
    }

    public void setProjPlannerList(List projPlannerList) {
        this.projPlannerList = projPlannerList;
    }

    public List getProjHybridPlannerList() {
        return projHybridPlannerList;
    }

    public void setProjHybridPlannerList(List projHybridPlannerList) {
        this.projHybridPlannerList = projHybridPlannerList;
    }

    
    private String sql_select = "";
    private String sql_where = "";
  
     private List projPlannerList = new ArrayList();
     private List projHybridPlannerList = new ArrayList();
    public void collectProjPrinterDates(){
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try{
         
            con = connection.getSampleProperty();
            stmt = con.createStatement();
            String  queryData = "";
               
            sql_select="";
            sql_where="";
            
                sql_select= "select pr.proj_id,pr.proj_name,"
                    + "concat(athr.firstname,' ',athr.surname),pr.proj_isbn_10,"
                    + "concat(cnt.company,'/',divi.company),date_format(pr.projected_printer_date,'%m/%d/%y'),date_format(pr.proj_date,'%m/%d/%y'), s.status,pc.proj_category,pr.estimated_pages "
                    + " from "
                    + "contacts divi, contacts cnt, department d, user u, status s, proj_category pc, projects pr LEFT JOIN contacts athr ON pr.author_id=athr.contact_id ";
            
             sql_where= "where "
                    + "d.dept_code=u.dept_code and u.emp_id=pr.planner and pr.client_name=cnt.contact_id  AND divi.contact_id=pr.division_id and pr.act_ship_date is null AND pr.project_status not in (2,23,21,24) and pr.project_status=s.status_id and pr.projcategory_id=pc.projcategory_id";
              
           if(!projID.equals("")&&projID!=null){
              sql_where += " and pr.proj_id='"+projID+"' " ;
           }
           
           if(!plannerId.equals("")&&plannerId!=null){
              sql_where += " and pr.planner='"+plannerId+"' " ;
           }
           if(!deptCode.equals("")&&deptCode!=null&&!deptCode.equals("ALL")){
              sql_where += " and u.dept_code='"+deptCode+"' " ;
           }
           
           if(!projFromDate.equals("")&&!projToDate.equals("")){               
               sql_where += " and date_format(pr.projected_printer_date,'%Y-%m-%d') between  '"+projFromDate+"' and '"+projToDate+"' " ;
           }
           
            sql_select = sql_select+sql_where+" order by pr.projected_printer_date IS NULL, pr.projected_printer_date ";

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
                 
                 queryData = rsCollectDetails.getString("date_format(pr.projected_printer_date,'%m/%d/%y')");
                  if(rsCollectDetails.wasNull()){
                    queryData= "";
                  }                 
                 projPrinterDateList.add(queryData);
                 
                queryData = rsCollectDetails.getString("date_format(pr.proj_date,'%m/%d/%y')");
                if(rsCollectDetails.wasNull()){
                    queryData= "";
                }
                projCreatedDateList.add(queryData);

                queryData = rsCollectDetails.getString("s.status");
                if(rsCollectDetails.wasNull()){
                    queryData= "";
                }
                projStatusList.add(queryData);
               queryData = rsCollectDetails.getString("pc.proj_category");
                if(rsCollectDetails.wasNull()){
                    queryData= "";
                }
                proj_category.add(queryData);
                 queryData = rsCollectDetails.getString("pr.estimated_pages");
                if(rsCollectDetails.wasNull()){
                    queryData= "";
                }
                proj_estimatedPages.add(queryData);
            }
             
            int projIDSize = projIdList.size();
            
            String loopProjId="";
            for(int idx=0;idx<projIDSize;idx++){
                loopProjId = projIdList.get(idx).toString();
                rsCollectDetails = stmt.executeQuery("select cnt.company from contacts cnt,projects pr"
                        + " where pr.proj_id='"+loopProjId+"' and pr.proj_printer=cnt.contact_id");                
                if(rsCollectDetails.next()){
                    rsCollectDetails = stmt.executeQuery("select cnt.company from contacts cnt,projects pr"
                        + " where pr.proj_id='"+loopProjId+"' and pr.proj_printer=cnt.contact_id");
                    while(rsCollectDetails.next()){
                      queryData = splChar.decoding(rsCollectDetails.getString("cnt.company"));
                      if(rsCollectDetails.wasNull()){
                        queryData= "";
                      }                        
                        projPrinterList.add(queryData);
                    }
                }
                else{
                    projPrinterList.add("");
                }
                
                
                
                         rsCollectDetails = stmt.executeQuery("select u.emp_name, h.emp_name from projects p left join user u on p.planner=u.emp_id left join user h on p.hybrid_planner=h.emp_id WHERE  p.proj_id='"+loopProjId+"' ");
              if(rsCollectDetails.next()){   
                  rsCollectDetails=stmt.executeQuery("select u.emp_name, h.emp_name from projects p left join user u on  p.planner=u.emp_id left join user h on p.hybrid_planner=h.emp_id where p.proj_id='"+loopProjId+"' ");
                   while(rsCollectDetails.next()) {
                        queryData = rsCollectDetails.getString("u.emp_name");
                        if(rsCollectDetails.wasNull()){
                            queryData= "N/A";
                        }
                        projPlannerList.add(queryData);

                        queryData = rsCollectDetails.getString("h.emp_name");
                        if(rsCollectDetails.wasNull()){
                            queryData= "N/A";
                        }
                        projHybridPlannerList.add(queryData);
                            }
                }else{
                      projPlannerList.add("");
                      projHybridPlannerList.add("");
                     // System.out.println("sql_select planner sql:"+sql_select);
                }
              
                
                
                 rsCollectDetails = stmt.executeQuery("select date_format(ship_date,'%m/%d/%y') from chapter "
                        + "where proj_id='"+loopProjId+"' and ship_Date is not null and stage = 'FP' order by ship_date limit 1");        
                
                  if(rsCollectDetails.next()){
                    rsCollectDetails = stmt.executeQuery("select date_format(ship_date,'%m/%d/%y') from chapter "
                        + "where proj_id='"+loopProjId+"' and ship_Date is not null and stage = 'FP' order by ship_date limit 1");        
                
                    while(rsCollectDetails.next()){
                      queryData = rsCollectDetails.getString("date_format(ship_date,'%m/%d/%y')");
                      if(rsCollectDetails.wasNull()){
                        queryData= "";
                      }                        
                        firstPassShippedDateList.add(queryData);
                    }
                }
                else{
                    firstPassShippedDateList.add("");
                }
                  
                  
                  
                       rsCollectDetails = stmt.executeQuery("select date_format(invoice_date,'%m/%d/%y') from invoices "
                        + "where proj_id='"+loopProjId+"' ");        
                
                  if(rsCollectDetails.next()){
                    rsCollectDetails = stmt.executeQuery("select date_format(invoice_date,'%m/%d/%y') from invoices "
                        + "where proj_id='"+loopProjId+"' ");        
                
                    while(rsCollectDetails.next()){
                      queryData = rsCollectDetails.getString("date_format(invoice_date,'%m/%d/%y')");
                      if(rsCollectDetails.wasNull()){
                        queryData= "";
                      }                        
                        invoicedDateList.add(queryData);
                    }
                }
                else{
                    invoicedDateList.add("");
                }
                
                
                
            }
            
             //System.out.println("projIdList:"+projIdList.size());
              //System.out.println("projNameList :"+projNameList.size());
               //System.out.println("projAuthorList :"+projAuthorList.size());
                 //System.out.println("projIsbnList :"+projIsbnList.size());
                   //System.out.println("projCustomerList :"+projCustomerList.size());
                     //System.out.println("projPrinterDateList :"+projPrinterDateList.size());
                       //System.out.println("projPrinterList :"+projPrinterList.size());
                       
            
            
            
            
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
    
    
    public void plannersProjectDetails(String plannerID)
    {
        String sql_select1="";
        String sql_where1="";

        proj_status.clear();
        proj_idList.clear();
        proj_name.clear();
        proj_authorName.clear();
        proj_planner.clear();
        proj_customer.clear();
        proj_fpShipDate.clear();
        proj_dueDate.clear();
        proj_estdPages.clear();
        proj_trimSize.clear();
        proj_category.clear();
        proj_color.clear();
        proj_priority.clear();
        proj_level.clear();
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try
        {
            con = connection.getSampleProperty();
            stmt = con.createStatement();

            String  queryData = "";
            
            
                sql_select1= "SELECT s.status_alias, pr.proj_id,pr.proj_name,CONCAT_WS('',athr.firstname,' ',athr.surname) AS Author_Name, "
                    + "CONCAT(cnt.company,'/',divi.company) AS Customer, DATE_FORMAT(pr.proj_date,'%m/%d/%Y') AS FP_Ship_Date, DATE_FORMAT(pr.projected_printer_date,'%m/%d/%Y') AS Due_Date, "
                    + "u.emp_name,pr.estimated_pages, pr.trim_size, pc.proj_category, CONCAT(pcl.color,'/','c') AS Color,pp.priority_value,pl.proj_level "
                    + " from "
                    + " STATUS s, contacts divi, contacts cnt, projects pr LEFT JOIN proj_category pc USING(projcategory_id) "
                    + "LEFT JOIN contacts athr ON pr.author_id=athr.contact_id "
                    + "LEFT JOIN proj_color pcl USING(color_id) "
                    + "LEFT JOIN proj_priority pp USING(priority_id) "
                    + "LEFT JOIN project_level pl ON pr.proj_level=pl.level_id "
                    + "LEFT JOIN USER u ON pr.planner=u.emp_id "
                    + "LEFT JOIN department d on d.dept_code=u.dept_code and pr.planner=u.emp_id ";
            if (plannerID != null) {
                sql_where1 = "where "
                        + "pr.client_name=cnt.contact_id  AND divi.contact_id=pr.division_id AND pr.project_status not in (2,23,21,24) AND s.status_id=pr.project_status and pr.act_ship_date is null and pr.planner='" + plannerID + "'";
                    
            } else {
                sql_where1 = "where "
                        + "pr.client_name=cnt.contact_id  AND divi.contact_id=pr.division_id AND pr.project_status not in (2,23,21,24) AND s.status_id=pr.project_status and pr.act_ship_date is null and pr.planner is null ";
            }
                    if (!projID.equals("") && projID != null) {
                        sql_where1 += " and pr.proj_id='" + projID + "' ";
                    }

                    if (!projFromDate.equals("") && !projToDate.equals("")) {
                        sql_where1 += " and date_format(pr.projected_printer_date,'%Y-%m-%d') between  '" + projFromDate + "' and '" + projToDate + "' ";
                    }
                
                    if (!deptCode.equals("") && deptCode != null && !deptCode.equals("ALL")) {
                        sql_where1 += " and u.dept_code='" + deptCode + "' ";
                    }
            

            sql_select1 = sql_select1 + sql_where1 + " ORDER BY pr.projected_printer_date IS NULL, pr.projected_printer_date ";


             rsCollectDetails1 = stmt.executeQuery(sql_select1);
             while(rsCollectDetails1.next()){
                 queryData=rsCollectDetails1.getString("pr.proj_id");
                proj_idList.add(rsCollectDetails1.getString("pr.proj_id"));

                 queryData = rsCollectDetails1.getString("pr.proj_name");
                  if(rsCollectDetails1.wasNull()){
                    queryData= "";
                  }
                 proj_name.add(queryData);

                 queryData = splChar.decoding(rsCollectDetails1.getString("Author_Name"));
                  if(rsCollectDetails1.wasNull()){
                    queryData= "";
                  }
                 proj_authorName.add(queryData);

                 queryData = splChar.decoding(rsCollectDetails1.getString("Customer"));
                  if(rsCollectDetails1.wasNull()){
                    queryData= "";
                  }
                 proj_customer.add(queryData);

                 queryData = rsCollectDetails1.getString("FP_Ship_Date");
                  if(rsCollectDetails1.wasNull()){
                    queryData= "";
                  }
                 //proj_fpShipDate.add(queryData);

                 queryData=rsCollectDetails1.getString("Due_Date");
                 if(rsCollectDetails1.wasNull())
                 {
                  queryData="";
                 }
                 proj_dueDate.add(queryData);

                 queryData=rsCollectDetails1.getString("u.emp_name");
                 if(rsCollectDetails1.wasNull())
                 {
                  queryData="";
                 }
                 proj_planner.add(queryData);

                 queryData=rsCollectDetails1.getString("s.status_alias");
                 if(rsCollectDetails1.wasNull())
                 {
                  queryData="";
                 }
                 proj_status.add(queryData);

                queryData=rsCollectDetails1.getString("pr.estimated_pages");
                 if(rsCollectDetails1.wasNull())
                 {
                  queryData="";
                 }
                 proj_estdPages.add(queryData);

                 queryData=rsCollectDetails1.getString("pr.trim_size");
                 if(rsCollectDetails1.wasNull())
                 {
                  queryData="";
                 }
                 proj_trimSize.add(queryData);

                 queryData=rsCollectDetails1.getString("pc.proj_category");
                 if(rsCollectDetails1.wasNull())
                 {
                  queryData="";
                 }
                 proj_category.add(queryData);

                 queryData=rsCollectDetails1.getString("Color");
                 if(rsCollectDetails1.wasNull())
                 {
                  queryData="";
                 }
                 proj_color.add(queryData);

                 queryData=rsCollectDetails1.getString("pp.priority_value");
                 if(rsCollectDetails1.wasNull())
                 {
                  queryData="";
                 }
                 proj_priority.add(queryData);

                 queryData=rsCollectDetails1.getString("pl.proj_level");
                 if(rsCollectDetails1.wasNull())
                 {
                  queryData="";
                 }
                 proj_level.add(queryData);
            }



        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }

    }
    

    public List<String> getStatusAliasList(Connection connStatus) throws SQLException {
        
        String query="";

        List<String> statusList = new ArrayList<String>();

        query = "SELECT STATUS AS a,status_alias as b FROM STATUS WHERE status_type = 'projects'";
        try {
            stmt = connStatus.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                statusList.add(rs.getString("b") + " - " + rs.getString("a"));
            }

        } catch (Exception e) {
            System.out.println("Exception" + e.toString());
            e.printStackTrace();
        }
        
        
        return statusList;
    }

    
    public List<Integer> getPlannerNameList(Connection conPlan) throws SQLException {
        
        String queryForPlanner="";
        String data="";
        
        List plannerList = new ArrayList<Integer>();
        
        queryForPlanner = "SELECT a.planner as Planners, b.emp_name FROM department d, projects a LEFT JOIN USER b ON a.planner=b.emp_id WHERE a.project_status NOT IN (2,23,21,24) AND a.act_ship_date is null AND d.dept_code=b.dept_code AND b.emp_id=a.planner ";

        try {
            if (!deptCode.equals("") && deptCode != null && !deptCode.equals("ALL")) {
                queryForPlanner += " and d.dept_code='" + deptCode + "' ";
            }
            if(!projID.equals("")&&projID!=null){
              queryForPlanner += " and a.proj_id='"+projID+"' " ;
           }

           if(!projFromDate.equals("")&&!projToDate.equals("")){
               queryForPlanner += " and date_format(a.projected_printer_date,'%Y-%m-%d') between  '"+projFromDate+"' and '"+projToDate+"' " ;
           }
            if(!plannerId.equals("")){
               queryForPlanner += " and a.planner='"+plannerId+"' " ;
           }

            queryForPlanner += " GROUP BY a.planner ORDER BY b.emp_name IS NULL, b.emp_name ";
            
            stmt = conPlan.createStatement();
            rs1 = stmt.executeQuery(queryForPlanner);
            while (rs1.next()) {
                data=rs1.getString("Planners");
                if(rs1.wasNull()) {
                    data=null;
                }
                plannerList.add(data);
            }

        } catch (Exception e) {
            System.out.println("Exception" + e.toString());
            e.printStackTrace();
        }
        
        
        return plannerList;
    }
    
    public String getFPDataeList(String projID, Connection conFPDate) throws SQLException {
        
        String queryForFPdate="";
        String data1="";
    
        List fpDateList = new ArrayList();
        
        stmt = conFPDate.createStatement();
        rs1 = stmt.executeQuery("select date_format(ship_date,'%d/%m/%y') as FP_date from chapter "
                        + "where proj_id='"+projID+"' and ship_Date is not null and stage = 'FP' order by ship_date limit 1");
        try {
            if(rs1.next())
        {

        queryForFPdate=("select date_format(ship_date,'%d/%m/%Y') as FP_date from chapter "
                        + "where proj_id='"+projID+"' and ship_Date is not null and stage = 'FP' order by ship_date limit 1");
        
            
            rs1 = stmt.executeQuery(queryForFPdate);
            while (rs1.next()) {
                data1=rs1.getString("FP_date");
                if(rs1.wasNull())
                 {
                  data1="";
                 }
                fpDateList.add(data1);
            }
            }
 else
            {
                data1="";
 }
        }
            
         catch (Exception e) {
             System.out.println("Exception" + e.toString());
            e.printStackTrace();
        }
        
        
        return data1;
    }

    
    public String getCodingType(String projID, Connection conCode) throws SQLException {

        String queryForCodingType="";
        String codingType="";

        stmt = conCode.createStatement();
        rs1 = stmt.executeQuery("SELECT value FROM project_properties where proj_id='"+projID+"' AND property_id=4");
        try {
            if(rs1.next())
        {

        queryForCodingType=("SELECT value FROM project_properties where proj_id='"+projID+"' AND property_id=4");

            rs1 = stmt.executeQuery(queryForCodingType);
            while (rs1.next()) {
                codingType=rs1.getString("value");
                if(rs1.wasNull())
                 {
                  codingType="";
                 }
            }
            }
 else
            {
                codingType="";
 }
        }

         catch (Exception e) {
             System.out.println("Exception" + e.toString());
            e.printStackTrace();
        }
        

        return codingType;
    }
    
    
}
