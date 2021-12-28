/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import connection.DBconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yogananthani
 */
public class ProjectSearch {

    DBconnection dbcon = new DBconnection();
    Connection con;
    Statement statement;
    ResultSet rs;
    List proj_id = new ArrayList();
    List proj_name = new ArrayList();
    List proj_title = new ArrayList();
    List proj_isbn = new ArrayList();
    List facility_id = new ArrayList();
    List facility_name = new ArrayList();
    List status_id = new ArrayList();
    List status_name = new ArrayList();
    List proj_category = new ArrayList();
    List proj_categoryID = new ArrayList();
    List proj_planner = new ArrayList();
    List proj_plannerID = new ArrayList();
    List listForSearchKey = new ArrayList();

    public List getStatus_id() {
        return status_id;
    }

    public void setStatus_id(List status_id) {
        this.status_id = status_id;
    }

    public List getStatus_name() {
        return status_name;
    }

    public void setStatus_name(List status_name) {
        this.status_name = status_name;
    }

    public List getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(List facility_id) {
        this.facility_id = facility_id;
    }

    public List getFacility_name() {
        return facility_name;
    }

    public void setFacility_name(List facility_name) {
        this.facility_name = facility_name;
    }
    
    public List getProj_isbn() {
        return proj_isbn;
    }

    public void setProj_isbn(List proj_isbn) {
        this.proj_isbn = proj_isbn;
    }
    
    public List getProj_title() {
        return proj_title;
    }

    public void setProj_title(List proj_title) {
        this.proj_title = proj_title;
    }
    public List getProj_id() {
        return proj_id;
    }

    public void setProj_id(List proj_id) {
        this.proj_id = proj_id;
    }

    public List getProj_name() {
        return proj_name;
    }

    public void setProj_name(List proj_name) {
        this.proj_name = proj_name;
    }

     public List getProj_category() {
        return proj_category;
    }

    public void setProj_category(List proj_category) {
        this.proj_category = proj_category;
    }

    public List getProj_categoryID() {
        return proj_categoryID;
    }

    public void setProj_categoryID(List proj_categoryID) {
        this.proj_categoryID = proj_categoryID;
    }

    public List getProj_planner() {
        return proj_planner;
    }

    public void setProj_planner(List proj_planner) {
        this.proj_planner = proj_planner;
    }

    public List getProj_plannerID() {
        return proj_plannerID;
    }

    public void setProj_plannerID(List proj_plannerID) {
        this.proj_plannerID = proj_plannerID;
    }

    public List getSearchCategory()
    {
        return listForSearchKey;
    }
    //Getting project details based on the searchKey and value
    public void getProjectDetails(String status, String searchKey, String searchValue) {
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {
            con = dbcon.getSampleProperty();
            String sql = "SELECT proj_id,proj_name,proj_bktitle FROM projects WHERE project_status = 1 order by proj_id";
            if (searchKey.equals("setSelectedClient")) {
                sql = "SELECT p.proj_id,p.proj_name,p.client_name "
                        + " FROM projects p,contacts c"
                        + " WHERE p.client_name = c.contact_id AND p.project_status IN (" + status + ") AND p.client_name = '" + searchValue + "' order by p.proj_id ";

            }   else if (searchKey.equals("setSelectedAuthor")) {
                sql = "SELECT p.proj_id,p.proj_name,concat(c.firstname,'/',c.surname)"
                        + " FROM projects p,project_authors a,contacts c "
                        + " WHERE p.proj_id = a.proj_id AND a.author_id  = c.contact_id AND p.project_status IN (" + status + ")  AND a.author_id = '" + searchValue + "' order by p.proj_id";

            }   else if(searchKey.equals("setSelectedISBN-10")) {
                sql = "SELECT proj_id,proj_name, proj_isbn_10 FROM projects WHERE project_status IN (" + status + ") AND proj_isbn_10 = '" + searchValue + "' order by p.proj_id";

            }   else if(searchKey.equals("setSelectedISBN-13")) {
                sql = "SELECT proj_id,proj_name, proj_isbn_13 FROM projects WHERE project_status IN (" + status + ") AND proj_isbn_13 = '" + searchValue + "' order by p.proj_id";

            }   else if(searchKey.equals("setSelectedBillingLocation")){
                sql = "SELECT proj_id,proj_name,facility_id  FROM projects WHERE facility_id = '" + searchValue + "' AND project_status IN (" + status + ")  order by proj_id";

            }   else if(searchKey.equals("setSelectedJob")||searchKey.equals("setSelectedJobtitle")){
                sql = "SELECT proj_id,proj_name, proj_bktitle FROM projects WHERE project_status IN (" + status + ") ";
                        if(!searchValue.equals("")) {
                        sql+=" and proj_id = '" + searchValue + "'";
                        }
                        sql+= " order by proj_id";

            }   else if(searchKey.equals("setSelectedCategory")){
                sql = "SELECT proj_id,proj_name, projcategory_id FROM projects WHERE projcategory_id = '" + searchValue + "' AND project_status IN (" + status + ") order by proj_id";

            }   else if(searchKey.equals("setSelectedPlanner")){
                sql = "SELECT proj_id,proj_name, planner FROM projects WHERE planner = '" + searchValue + "' AND project_status IN (" + status + ") order by proj_id";
            }   else if(searchKey.equals("Job")){
                sql = "SELECT proj_id,proj_name,proj_bktitle FROM projects WHERE project_status IN (" + status + ") order by proj_id";
            }   else if(searchKey.equals("Job Title")) {
                sql = "SELECT proj_id,proj_name,proj_bktitle FROM projects WHERE project_status IN (" + status + ") AND proj_bktitle!='' order by proj_id";
            }   else if(searchKey.equals("10 Digit ISBN")) {
                sql = "SELECT proj_id,proj_name,proj_isbn_10 FROM projects WHERE project_status IN (" + status + ") AND proj_isbn_10!='' order by proj_id";
            }   else if(searchKey.equals("13 Digit ISBN")) {
                sql = "SELECT proj_id,proj_name,proj_isbn_13 FROM projects WHERE project_status IN (" + status + ") AND proj_isbn_13!='' order by proj_id";
            }
            //System.out.println("Project Search Query: "+sql);

            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                proj_id.add(rs.getString(1) == null ? "" : rs.getString(1));
                proj_name.add(rs.getString(2) == null ? "" : rs.getString(2));
                listForSearchKey.add(rs.getString(3) == null ? "" : splChar.decoding(rs.getString(3)));
            }
            rs.close();
            statement.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error on " + System.getProperty("user.dir") + " " + e.toString());
        }
    }
    //Getting all the job based on proj_name
    public void jobSearch(String status,String searchKey){
        try{
            con = dbcon.getSampleProperty();
            String sql = "SELECT proj_id,proj_bktitle,proj_name FROM projects WHERE project_status in ("+status+") AND (proj_id LIKE '"+searchKey+"%' OR proj_name LIKE '"+searchKey+"%' OR proj_bktitle LIKE '"+searchKey+"%') order by proj_id";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                proj_id.add(rs.getString(1) == null ? "" : rs.getString(1));
                proj_title.add(rs.getString(2) == null ? "" : rs.getString(2));
                proj_name.add(rs.getString(3) == null ? "" : rs.getString(3));
            }
            rs.close();
            statement.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error on " + System.getProperty("user.dir") + " " + e.toString());
        }
    }
    //Getting isbn nuber
    public void isbnSearch(String status,String searchKey,String isbnNo){
        try{
            con = dbcon.getSampleProperty();
            String sql = "SELECT proj_id,proj_isbn_10,proj_name FROM projects WHERE project_status in ("+status+") AND proj_isbn_10 LIKE '"+searchKey+"%' group by proj_isbn_10 order by proj_id";
            if(isbnNo.equals("13"))
                sql = "SELECT proj_id,proj_isbn_13,proj_name FROM projects WHERE project_status in ("+status+") AND proj_isbn_13 LIKE '"+searchKey+"%' group by proj_isbn_13 order by proj_id";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                proj_id.add(rs.getString(1) == null ? "" : rs.getString(1));
                proj_isbn.add(rs.getString(2) == null ? "" : rs.getString(2));
                proj_name.add(rs.getString(3) == null ? "" : rs.getString(3));
            }
            rs.close();
            statement.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error on " + System.getProperty("user.dir") + " " + e.toString());
        }
    }
    //Getting facility
    public void getFacility(String status,String searchKey){
        try{
            con = dbcon.getSampleProperty();
            String sql = "SELECT facility_id,facility_name FROM facility WHERE facility_name LIKE '"+searchKey+"%'";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()){
                facility_id.add(rs.getString(1));
                facility_name.add(rs.getString(2));
            }
            rs.close();
            statement.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error on " + System.getProperty("user.dir") + " " + e.toString());
        }
    }
    //Getting all the availabe status
    public void getStatus(){
        try{
            con = dbcon.getSampleProperty();
            statement = con.createStatement();
            String sql = "SELECT status_id,STATUS FROM STATUS WHERE status_type='projects' ";
            rs = statement.executeQuery(sql);
            while(rs.next()){
                status_id.add(rs.getString(1));
                status_name.add(rs.getString(2));
            }
            rs.close();
            statement.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error on " + System.getProperty("user.dir") + " " + e.toString());
        }

    }

    public void categorySearch(String status,String searchKey){
        try{
            con = dbcon.getSampleProperty();
            String sql = "SELECT a.proj_id, a.projcategory_id, b.proj_category FROM projects a, proj_category b WHERE a.projcategory_id IS NOT NULL AND a.projcategory_id =b.projcategory_id AND a.project_status in ("+status+") AND b.proj_category LIKE '"+searchKey+"%' group by b.projcategory_id order by a.proj_id";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                proj_id.add(rs.getString(1) == null ? "" : rs.getString(1));
                proj_categoryID.add(rs.getString(2) == null ? "" : rs.getString(2));
                proj_category.add(rs.getString(3) == null ? "" : rs.getString(3));
            }
            //System.out.println("Inside categorySearch: "+sql);
            rs.close();
            statement.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error on " + System.getProperty("user.dir") + " " + e.toString());
        }
    }


    public void plannerSearch(String status,String searchKey){
        try{
            con = dbcon.getSampleProperty();
            String sql = "SELECT a.proj_id, b.emp_id, b.emp_name FROM projects a, USER b WHERE a.planner=b.emp_id AND a.project_status in ("+status+") AND b.emp_name LIKE '"+searchKey+"%' group by b.emp_id order by a.proj_id";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                proj_id.add(rs.getString(1) == null ? "" : rs.getString(1));
                proj_plannerID.add(rs.getString(2) == null ? "" : rs.getString(2));
                proj_planner.add(rs.getString(3) == null ? "" : rs.getString(3));
            }
            //System.out.println("Inside plannerSearch: "+sql);
            rs.close();
            statement.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error on " + System.getProperty("user.dir") + " " + e.toString());
        }
    }
}
