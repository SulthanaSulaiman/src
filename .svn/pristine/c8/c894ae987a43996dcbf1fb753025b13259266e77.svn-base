/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters.projects;

import connection.DBconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormatSymbols;
import pathfinder.functions.BaseClass;

/**
 *
 * @author Aravindhanj
 */
public class ProjectActivityReportManipulation {

    // DBconnection db = new DBconnection();
    //Connection con = db.getSampleProperty();
    List<String> category_name = new ArrayList<String>();
    List<String> categoryNames = new ArrayList<String>();
    List projId = new ArrayList();
    List projName = new ArrayList();
    List customerName = new ArrayList();
    List plannerName = new ArrayList();
    List projCreatedDate = new ArrayList();
    List projEstShipDate = new ArrayList();
    List projActShipDate = new ArrayList();
    List projDueMonthYear = new ArrayList();
    List projCategory = new ArrayList();
    List projEstimateValue = new ArrayList();
    List projInvoiceValue = new ArrayList();
    List projActualPages = new ArrayList();
    List projEstimatePages = new ArrayList();
    List projTrimSize = new ArrayList();
    List projColor = new ArrayList();
    List projAuthors = new ArrayList();
    List projStatus = new ArrayList();
    List projTitle = new ArrayList();
    Statement statement;
    ArrayList<ProjectActivityReport> projectListObj;
    ProjectActivityReport projectActivityReportObject;
    ResultSet rs;
    String query;
    String projectId, temp;
    double estimateValueTemp;
    int temp1;
    String temp_val = "";
    String categoryName="";
    BaseClass baseObj = new BaseClass();
    List facilityId = new ArrayList();
    List facilityName = new ArrayList();

    public List getFacilityId() {
        return facilityId;
    }
    public List getProjId() {
        return projId;
    }
    public List getProjName() {
        return projName;
    }
    public List getCustomerName() {
        return customerName;
    }
    public List getPlannerName() {
        return plannerName;
    }
    public List getProjCreatedDate() {
        return projCreatedDate;
    }
    public List getProjEstShipDate() {
        return projEstShipDate;
    }
    public List getProjActShipDate() {
        return projActShipDate;
    }
    public List getProjDueMonthYear() {
        return projDueMonthYear;
    }
    public List getProjEstimateValue() {
        return projEstimateValue;
    }
    public List getProjInvoiceValue() {
        return projInvoiceValue;
    }
    public List getProjCategory() {
        return projCategory;
    }
    public List getProjActualPages() {
        return projActualPages;
    }
    public List getProjTrimSize() {
        return projTrimSize;
    }
    public List getProjColor() {
        return projColor;
    }
    public List getProjAuthors() {
        return projAuthors;
    }
    public List getProjStatus() {
        return projStatus;
    }
    public List getProjTitle() {
        return projTitle;
    }
    public List getProjEstimatePages() {
        return projEstimatePages;
    }


    public void setFacilityId(List facilityId) {
        this.facilityId = facilityId;
    }

    public List getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(List facilityName) {
        this.facilityName = facilityName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public List initCategory(String arg_category, String arg_endDate, Connection con,String facilityId) {
        List<String> category = new ArrayList<String>();
        projectListObj = new ArrayList<ProjectActivityReport>();







        try {


            statement = con.createStatement();
            if (!"undefined".equals(arg_endDate) && !"undefined".equals(arg_category)) {

                query = "SELECT p.proj_id FROM projects p \n"
                        + "INNER JOIN proj_category c ON p.projcategory_id=c.projcategory_id\n"
                        + " WHERE project_status not in (2,23,21) and facility_id in ("+facilityId+")  AND p.proj_id NOT IN (SELECT proj_id FROM invoices  WHERE part_invoice_flag = 0) AND c.proj_category in (" + arg_category + ") \n"
                        + " AND  "
                        + "CONCAT (EXTRACT(MONTH FROM p.projected_printer_date),' ',EXTRACT(YEAR FROM p.projected_printer_date))='" + arg_endDate + "' ";

            } else if (!"undefined".equals(arg_category) && arg_endDate.equals("undefined")) {

                query = "SELECT p.proj_id FROM projects p \n"
                        + "INNER JOIN proj_category c ON p.projcategory_id=c.projcategory_id\n"
                        + " WHERE project_status not in (2,23,21) and facility_id in ("+facilityId+")  AND p.proj_id NOT IN (SELECT proj_id FROM invoices  WHERE part_invoice_flag = 0) AND c.proj_category in (" + arg_category + ") \n"
                        + " AND  p.projected_printer_date IS NULL ";
            } else if (!"undefined".equals(arg_endDate) && arg_category.equals("undefined")) {

                query = "SELECT p.proj_id FROM projects p \n"
                        + " WHERE project_status not in (2,23,21) and facility_id in ("+facilityId+")  AND p.proj_id NOT IN (SELECT proj_id FROM invoices  WHERE part_invoice_flag = 0) AND p.projcategory_id IS NULL \n"
                        + " AND"
                        + "  CONCAT (EXTRACT(MONTH FROM p.projected_printer_date),' ',EXTRACT(YEAR FROM p.projected_printer_date))='" + arg_endDate + "' ";
            } else {

                query = "SELECT p.proj_id FROM projects p \n"
                        + " WHERE project_status not in (2,23,21) and facility_id in ("+facilityId+")  AND p.proj_id NOT IN (SELECT proj_id FROM invoices  WHERE part_invoice_flag = 0) AND p.projcategory_id IS NULL \n"
                        + " AND EXTRACT(MONTH FROM p.projected_printer_date) IS NULL ";

            }




            ResultSet rs = statement.executeQuery(query + " ORDER BY p.projected_printer_date");
            
            while (rs.next()) {

                category.add(rs.getString("proj_id"));

            }

            getProjectDetails(category, con);

        } catch (Exception e) {
            System.out.println("exception occured:1" + e.toString());
        }
        return projectListObj;
    }

    public void getProjectDetails(List projectId, Connection con) {
        for (Object id : projectId) {
            projectActivityReportObject = new ProjectActivityReport();
            String projId = id.toString();
            getDirectvalues(projId, projectActivityReportObject, con);
            getMappedValues(projId, projectActivityReportObject, con);
            projectListObj.add(projectActivityReportObject);
        }

    }

    public void getDirectvalues(String project_id, ProjectActivityReport obj, Connection con) {
        try {




            statement = con.createStatement();
            String query1 = "SELECT proj_name,proj_bktitle,trim_size,DATE_FORMAT(proj_date,'%m/%d/%y') as awardDate,"
                    + "DATE_FORMAT(projected_printer_date,'%m/%d/%y') AS dueDate,DATE(act_ship_date) as dateShipped "
                    + "FROM projects  where proj_id='" + project_id + "'";


            ResultSet rs1 = statement.executeQuery(query1);
            while (rs1.next()) {

                obj.set_project_Id(project_id);
                obj.set_customer(rs1.getString("proj_name") != null ? rs1.getString("proj_name") : "");
                obj.set_title(rs1.getString("proj_bktitle") != null ? rs1.getString("proj_bktitle") : "");
                obj.set_trim_Size(rs1.getString("trim_size") != null ? rs1.getString("trim_size") : "");
                obj.set_award_Date(rs1.getString("awardDate") != null ? rs1.getString("awardDate") : "");
                obj.set_due_Date(rs1.getString("dueDate") != null ? rs1.getString("dueDate") : "");
                obj.set_date_Shipped(rs1.getString("dateShipped") != null ? rs1.getString("dateShipped") : "");


            }

        } catch (Exception e) {
            System.out.println("exception2" + e.toString());
        }

    }
    /*
     public void disp(List proj_reportList_obj,List category_list,List month_list) {
     int size = proj_reportList_obj.size();
      
     System.out.println("Size:"+size);
     int i,j,k;
     for(i=0;i<size;i++)
     {
     System.out.println("----------"+category_list.get(i)+"------------");
     List a=  (List) proj_reportList_obj.get(i);
     System.out.println("Month:"+ a.size());
     for(j=0;j<a.size();j++)
     {
     List<String> month=(List) month_list.get(i);
         
     List b=(List) a.get(j);
     System.out.println("Id:"+ b.size());
     for(k=0;k<b.size();k++)
     {
     projectActivityReportObject=(ProjectActivityReport) b.get(k);
     System.out.println(  projectActivityReportObject.get_project_Id());
     }
     System.out.println("__________________________________________");
     System.out.println("Total projects:"+b.size());
     System.out.println("__________________________________________");
     }
     }
            
           
     System.out.println("id:" + obj.get_project_Id());
            
     System.out.println(  "clientname:"+      obj.get_customer());
     System.out.println( "title:"+     obj.get_title());
     System.out.println( "trimsize:"+   obj.get_trim_Size());
     System.out.println( "award date:"+   obj.get_award_Date());
     System.out.println(   "due_date"+ obj.get_due_Date());
     System.out.println(  "date shipped"+  obj.get_date_Shipped());
     System.out.println( "author"+ obj.get_author());
     System.out.println("planner"+ obj.get_planner());
     System.out.println( "division"+obj.get_division());
     System.out.println( "color"+obj.get_color());
           
     System.out.println("--------------------------------------");
       

     }  */

    public void getMappedValues(String projectId, ProjectActivityReport obj, Connection con) {
        // statement3=
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {
            statement = con.createStatement();
            // query to get author name
            String query2 = "SELECT CONCAT_WS('',c.firstname,' ',c.surname)as author_name FROM project_authors a,contacts c \n"
                    + "WHERE  a.author_id=c.contact_id AND a.proj_id='" + projectId + "'";
            temp = "";
            rs = statement.executeQuery(query2);
            int count = 1;
            while (rs.next()) {
                if (count > 1) {
                    temp += ",";
                }
                temp = temp + splChar.decoding(rs.getString("author_name"));
                count++;
            }
            obj.set_author(temp);
            //end of estimated query process



            // query to get division
            String query3 = " select concat(divi.firstname,' ',divi.surname) as division,divi.contact_id, "
                    + " divi.is_person, divi.company, CONCAT(cnt.company,'/',divi.company) AS Customer"
                    + " from contacts divi, projects pr,  contacts cnt"
                    + " where pr.proj_id='" + projectId + "' and divi.contact_id=pr.division_id And pr.client_name=cnt.contact_id  AND divi.contact_id=pr.division_id";
            temp = "";
            rs = statement.executeQuery(query3);
            while (rs.next()) {
                String tempResult = rs.getString("division") != null ? splChar.decoding(rs.getString("division")) : "";
                String division = tempResult;
                tempResult = rs.getString("divi.contact_id") != null ? rs.getString("divi.contact_id") : "";
                String divisionID = tempResult;

                String contactCategory = rs.getString("divi.is_person");
                if (contactCategory.equals("2")) {
                    tempResult = rs.getString("Customer") != null ? rs.getString("Customer") : "";
                    division = tempResult;
                }
                temp = temp + division;
            }
            obj.set_division(temp);
            //end of estimated query process



            // query to get planner name

            query = "SELECT  u.emp_name FROM projects p INNER JOIN   USER u ON p.planner=u.emp_id WHERE p.proj_id='" + projectId + "'";
            rs = statement.executeQuery(query);
            temp = "";
            while (rs.next()) {
                temp = temp + rs.getString("emp_name");
            }
            obj.set_planner(temp);
            //end of estimated query process


            //query to get color 
            query = "SELECT  c.color FROM projects p INNER JOIN   proj_color c ON p.color_id=c.color_id"
                    + " WHERE p.proj_id='" + projectId + "'";
            temp = "";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                temp = temp + rs.getString("color");
            }
            obj.set_color(temp);
            //end of estimated query process


            //query to get Category
            query = "SELECT proj_category FROM projects p INNER JOIN proj_category c ON p.projcategory_id=c.projcategory_id"
                    + " WHERE p.proj_id='" + projectId + "'";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                obj.set_category(rs.getString("proj_category") != null ? rs.getString("proj_category") : "");
            }
            //end of estimated query process


            //QUERY TO GET STATUS OF THE PROJECT

            query = "SELECT s.status,s.status_alias FROM projects p "
                    + "INNER JOIN STATUS s ON p.project_status=s.status_id "
                    + "WHERE p.proj_id='" + projectId + "' ";

            rs = statement.executeQuery(query);
            while (rs.next()) {
                obj.set_status(rs.getString("s.status") != null ? rs.getString("s.status") : "");
                obj.set_status_alias(rs.getString("s.status_alias") != null ? rs.getString("s.status_alias") : "");
            }
            //end of estimated query process

            //QUERY TO GET ESTIMATED VALUE FOR THE PROJECT
            estimateValueTemp = 0;
            query = "SELECT  e.est_value FROM estimate e WHERE e.proj_id='" + projectId + "'";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                estimateValueTemp = rs.getDouble("est_value");
            }
            obj.set_estiamted_Selling_Value(estimateValueTemp);

            //end of estimated query process



            //query to get estimate/actual pages 
            temp1 = 0;
            //  query to get actual page 
            query = "SELECT actual_pages FROM projects WHERE proj_id='" + projectId + "' ";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                temp1 = Integer.parseInt(rs.getString("actual_pages") != null ? rs.getString("actual_pages") : "0");

            }
            obj.set_actual_Page(temp1); // set actual page value
            //end of  query process


            // if the actual value is null then we get estimated pages value
            if (temp1 == 0) {
                query = "SELECT estimated_pages FROM projects WHERE proj_id='" + projectId + "' ";
                rs = statement.executeQuery(query);
                while (rs.next()) {
                    temp1 = rs.getInt("estimated_pages");

                }
                obj.set_estimated_Page(temp1);
            }
            //end of estimated query process

        } catch (Exception e) {
            System.out.println("exception3" + e.toString());
        }

    }

    public void getFacility() {
        try {
            DBconnection dbcon = new DBconnection();
            Connection con = dbcon.getSampleProperty();
            Statement ste = con.createStatement();
            ResultSet rs = ste.executeQuery("SELECT facility_id,facility_name FROM facility");
            while (rs.next()) {
                temp = rs.getString("facility_id");
                temp = temp == null ? "" : temp;
                facilityId.add(temp);
                temp = rs.getString("facility_name");
                temp = temp == null ? "" : temp;
                facilityName.add(temp);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error while getting facility list" + e.toString());
        }
    }
    /*--------------------------------------------------------------------------------------*/

    public List category_List(Connection con,String facilityId) {
        
        String category_id_check="";
        String category_names="";
        categoryName="";
        category_name.clear();
        categoryNames.clear();
        try {



            statement = con.createStatement();


            query = "SELECT DISTINCT proj_category,c.projcategory_id FROM projects p "
                    + "INNER JOIN proj_category c ON p.projcategory_id=c.projcategory_id WHERE project_status not in (2,23,21) and facility_id in ("+facilityId+") AND p.proj_id NOT IN (SELECT proj_id FROM invoices  WHERE part_invoice_flag = 0) ORDER BY c.proj_category ASC ";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                category_id_check=rs.getString(2);
                if(category_id_check.equals("1")||category_id_check.equals("4")||category_id_check.equals("11")||category_id_check.equals("15")) {
                    if(categoryName.length()>0) {
                        categoryName+=","+"'"+rs.getString("proj_category")+"'";
                    }   else    {
                        categoryName="'"+rs.getString("proj_category")+"'";
                    }
                }   else    {
                    category_names="'"+rs.getString("proj_category")+"'" != null ? "'"+rs.getString("proj_category")+"'" : "undefined" ;
                category_name.add(category_names);
                
                // 
            }
            }
            categoryNames.add(categoryName);
            
            // since there is projects without defining category. to identify that projects we adding one category named undefined
            category_name.add("undefined");
            categoryNames.addAll(category_name);
            // System.out.println("category_list_count:"+category_name.size());
        } catch (Exception e) {
            System.out.println("exception4" + e.toString());
        }
        
        return categoryNames;

    }

    public List month_List(String category, Connection con,String facilityId) {
        List<String> month_list = new ArrayList<String>();
        String toAddAtLast="";
        String emptycheck="";
        try {
            statement = con.createStatement();
            if (!"undefined".equals(category)) {

                query = "SELECT  DISTINCT "
                        + " CONCAT (EXTRACT(MONTH FROM p.projected_printer_date),' ',EXTRACT(YEAR FROM p.projected_printer_date)) "
                        + "as endDate FROM projects p\n"
                        + "INNER JOIN proj_category c  ON p.projcategory_id=c.projcategory_id WHERE  project_status not in (2,23,21) and facility_id in ("+facilityId+")  AND p.proj_id NOT IN (SELECT proj_id FROM invoices  WHERE part_invoice_flag = 0) AND c.proj_category in (" + category + ")"
                        + " ORDER BY p.projected_printer_date IS NULL, p.projected_printer_date ";

            } else {
                query = "SELECT  DISTINCT "
                        + " CONCAT (EXTRACT(MONTH FROM p.projected_printer_date),' ',EXTRACT(YEAR FROM p.projected_printer_date)) "
                        + "as endDate FROM projects p\n"
                        + " WHERE  project_status not in (2,23,21) and facility_id in ("+facilityId+")  AND p.proj_id NOT IN (SELECT proj_id FROM invoices  WHERE part_invoice_flag = 0) AND p.projcategory_id IS NULL"
                        + " ORDER BY p.projected_printer_date IS NULL, p.projected_printer_date  ";
            }
            rs = statement.executeQuery(query);
            while (rs.next()) {

                month_list.add(rs.getString("endDate") != null ? rs.getString("endDate") : "undefined");
            }

        } catch (Exception e) {
            System.out.println("exception5" + e.toString());
        }

        return month_list;

    }

    public List<String> get_statusAliasList(Connection con) {
        List<String> statusList = new ArrayList<String>();
        query = "SELECT STATUS AS a,status_alias as b FROM STATUS WHERE status_type = 'projects'";
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                statusList.add(rs.getString("b") + " = " + rs.getString("a"));
            }

        } catch (Exception e) {
        }
        return statusList;
    }

    public String get_status_alias(String status, Connection con) {
        temp = "";
        query = "SELECT  status_alias FROM STATUS WHERE  status='" + status + "' LIMIT 1";
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                temp = rs.getString("status_alias");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;

    }
    
    public void collect(String category, String facility_id, String categoryInChk, Connection con) {
        String tempResult = "";
        String projID = "";
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        String query = "SELECT p.proj_id,p.proj_name,concat(cnt.company,' / ',divi.company) as company,u.emp_name, "
                + " date(p.proj_date),date(p.projected_printer_date),p.act_ship_date,DATE_FORMAT(p.projected_printer_date,'%M %Y') AS monthAndYear,"
                + " c.proj_category, es.est_value,SUM(i.invoice_value),ifnull(p.actual_pages,0),p.color_id,p.trim_size,s.status_alias,p.proj_bktitle,ifnull(p.estimated_pages,0) "
                + " FROM projects p left JOIN proj_category c  ON p.projcategory_id=c.projcategory_id "
                + " LEFT JOIN invoices i ON i.proj_id=p.proj_id and part_invoice_flag IN (1)"
                + " LEFT JOIN estimate es ON es.proj_id=p.proj_id "
                + " LEFT JOIN project_authors pa ON pa.proj_id=p.proj_id "
                + " LEFT JOIN USER u on p.planner=u.emp_id, contacts cnt, contacts divi,STATUS s "
                + " WHERE  project_status NOT IN (2,23,21) and cnt.contact_id=p.client_name and p.division_id=divi.contact_id and p.proj_id NOT IN (SELECT proj_id FROM invoices  WHERE part_invoice_flag = 0) AND p.project_status=s.status_id ";

        if (categoryInChk.equals("IN")) {
            query += " AND p.facility_id in (" + facility_id + ") AND c.proj_category in (" + category + ")  GROUP BY monthAndYear, p.proj_id ORDER BY p.projected_printer_date is null,p.projected_printer_date,c.proj_category IS NULL, c.proj_category ";
        }
        if (categoryInChk.equals("NOTIN")) {
            query += " AND p.facility_id in (" + facility_id + ") AND c.proj_category not in (" + category + ") GROUP BY monthAndYear, p.proj_id ORDER BY c.proj_category is null,c.proj_category,p.projected_printer_date is null,p.projected_printer_date ";
        }
        if (categoryInChk.equals("NULL")) {
            query += " AND p.facility_id in (" + facility_id + ") AND c.proj_category is null GROUP BY monthAndYear, p.proj_id ORDER BY c.proj_category is null,c.proj_category,p.projected_printer_date is null,p.projected_printer_date ";
        }
        
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                projID = rs.getString(1);
                if (rs.wasNull()) {
                    projID = "";
                }
                projId.add(projID);

                tempResult = rs.getString(2);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projName.add(tempResult);

                tempResult = splChar.decoding(rs.getString(3));
                if (rs.wasNull()) {
                    tempResult = "";
                }
                customerName.add(tempResult);

                tempResult = rs.getString(4);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                plannerName.add(tempResult);

                tempResult = rs.getString(5);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projCreatedDate.add(tempResult);

                tempResult = rs.getString(6);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projEstShipDate.add(tempResult);

                tempResult = rs.getString(7);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projActShipDate.add(tempResult);

                tempResult = rs.getString(8);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projDueMonthYear.add(tempResult);

                tempResult = rs.getString(9);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projCategory.add(tempResult);

                tempResult = rs.getString(10);
                if (rs.wasNull()) {
                    tempResult = "0";
                }
                projEstimateValue.add(tempResult);

                tempResult = rs.getString(11);
                if (rs.wasNull()) {
                    tempResult = "0";
                }
                projInvoiceValue.add(tempResult);

                
                projActualPages.add(rs.getString(12));

                tempResult = rs.getString(13);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projColor.add(tempResult);

                tempResult = rs.getString(14);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projTrimSize.add(tempResult);

                tempResult = rs.getString(15);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projStatus.add(tempResult);

                tempResult = rs.getString(16);
                if (rs.wasNull()) {
                    tempResult = "";
                }
                projTitle.add(tempResult);
                projEstimatePages.add(rs.getString(17));

                Statement stToGetAuthors = con.createStatement();
                ResultSet rsToGetAuthors = stToGetAuthors.executeQuery("SELECT IFNULL(GROUP_CONCAT(CONCAT_WS('',ath.firstname,' ',ath.surname)),'--') AS author_name FROM project_authors pa LEFT JOIN contacts ath ON pa.author_id=ath.contact_id WHERE pa.proj_id='"+projID+"' ");
                while(rsToGetAuthors.next()) {
                    projAuthors.add(splChar.decoding(rsToGetAuthors.getString(1)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
