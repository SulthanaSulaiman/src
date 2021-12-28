/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import connection.DBconnection;
//import com.mysql.jdbc.Connection;
import java.util.*;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author Parameshwarant
 */
public class ProjectsByProjectedShipDate {

    Connection conn=null;
    Connection con=null;
    DBconnection connToDB=new DBconnection();

    private String queryForGetProjShipDate="";
    private String queryForGetProjectByProjShipDate="";

    private List projShipDateMonthList=new ArrayList();
    private List projShipDateYearList=new ArrayList();

    private List proj_id = new ArrayList();
    private List proj_name = new ArrayList();
    private List proj_title = new ArrayList();
    private List proj_planner = new ArrayList();
    private List proj_customer = new ArrayList();
    private List proj_customer_state = new ArrayList();
    private List proj_customer_division = new ArrayList();
    private List proj_create_date = new ArrayList();
    private List proj_ship_date = new ArrayList();
    private List proj_estd_pages = new ArrayList();
    private List proj_category = new ArrayList();
    private List proj_type = new ArrayList();
    private List proj_ship_month_year = new ArrayList();

    public List getShipDateMonthList()
    {
        return projShipDateMonthList;
    }

    public List getShipDateYearList()
    {
        return projShipDateYearList;
    }


    public List getProjectIdList() {
        return proj_id;
    }

    public List getProjectNameList() {
            return proj_name;
        }

   public List getProjectPlannerList() {
            return proj_planner;
        }

    public List getProjectCustomersList() {
            return proj_customer;
        }

    public List getProjectShipDateList() {
            return proj_ship_date;
        }

    public List getProjectCreatedDateList() {
            return proj_create_date;
        }
    public List getProjectEstdPagesList() {
            return proj_estd_pages;
        }

    public List getProjectCategoryList() {
            return proj_category;
        }

    public List getProjectTypeList() {
            return proj_type;
        }

    public List getProjectTitleList() {
            return proj_title;
        }

    public List getProjectShipMonthYear() {
            return proj_ship_month_year;
        }
    public List getProjectCustomersDivisionList() {
            return proj_customer_division;
        }

    public List getProjectCustomersStateList() {
            return proj_customer_state;
        }

    public void getProjectedShipDate()
    {
        projShipDateMonthList.clear();
        projShipDateYearList.clear();

        try{

        conn=connToDB.getSampleProperty();
        
        Statement stToGetMonthYear=conn.createStatement();
        queryForGetProjShipDate="SELECT DISTINCT EXTRACT(MONTH FROM projected_printer_date) AS MONTH, EXTRACT(YEAR FROM projected_printer_date) AS CurrentYear "
                + " FROM projects WHERE projected_printer_date IS NOT NULL AND act_ship_date IS NULL AND project_status NOT IN(2,23,21,24) ORDER BY projected_printer_date";
        ResultSet rsForGetProjShipDate=stToGetMonthYear.executeQuery(queryForGetProjShipDate);
        //System.out.println(queryForGetProjShipDate);
        while(rsForGetProjShipDate.next())
        {
            projShipDateMonthList.add(rsForGetProjShipDate.getString(1));
            projShipDateYearList.add(rsForGetProjShipDate.getString(2));
        }
        conn.close();
        stToGetMonthYear.close();
        rsForGetProjShipDate.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void getGetProjectByProjectedShipDate(String month, String year)
    {
        String storeData="";

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        
        proj_id.clear();
        proj_name.clear();
        proj_title.clear();
        proj_planner.clear();
        proj_customer.clear();
        proj_create_date.clear();
        proj_ship_date.clear();
        proj_estd_pages.clear();
        proj_category.clear();
        proj_type.clear();
        proj_ship_month_year.clear();
        proj_customer_division.clear();
        proj_customer_state.clear();

        try{

        con=connToDB.getSampleProperty();
        Statement st=con.createStatement();
        queryForGetProjectByProjShipDate="SELECT DATE_FORMAT(pr.projected_printer_date,'%M %Y'),pr.proj_id, pr.proj_name, pr.proj_bktitle,pt.type, "
                + " cnt.company AS Customer, cnt.state, divi.company AS Division, DATE_FORMAT(pr.proj_date,'%d/%m/%Y') AS Award_Date, "
                + " DATE_FORMAT(pr.projected_printer_date,'%d/%m/%Y') AS Due_Date, u.emp_name, pr.estimated_pages, pc.proj_category "
                + " FROM  contacts divi, contacts cnt, projects pr LEFT JOIN proj_category pc USING(projcategory_id) "
                + " LEFT JOIN project_type pt ON pr.proj_type=pt.type_code LEFT JOIN USER u ON pr.planner=u.emp_id "
                + " WHERE pr.client_name=cnt.contact_id  AND divi.contact_id=pr.division_id AND pr.projected_printer_date IS NOT NULL AND pr.act_ship_date IS NULL "
                + " AND EXTRACT(MONTH FROM pr.projected_printer_date) IN ('"+month+"') AND EXTRACT(YEAR FROM pr.projected_printer_date) IN ('"+year+"') AND pr.project_status NOT IN(2,23,21,24)"
                + " ORDER BY pr.projected_printer_date ";

        ResultSet rsForGetProjectByProjShipDate=st.executeQuery(queryForGetProjectByProjShipDate);
        //System.out.println(queryForGetProjectByProjShipDate);
        while(rsForGetProjectByProjShipDate.next())
        {
            storeData=rsForGetProjectByProjShipDate.getString(1);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_ship_month_year.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(2);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_id.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(3);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_name.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(4);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_title.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(5);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_type.add(storeData);

            storeData=splChar.decoding(rsForGetProjectByProjShipDate.getString(6));
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_customer.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(7);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "-";
                  }
            proj_customer_state.add(storeData);

            storeData=splChar.decoding(rsForGetProjectByProjShipDate.getString(8));
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_customer_division.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(9);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_create_date.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(10);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_ship_date.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(11);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_planner.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(12);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_estd_pages.add(storeData);

            storeData=rsForGetProjectByProjShipDate.getString(13);
            if(rsForGetProjectByProjShipDate.wasNull()){
                    storeData= "";
                  }
            proj_category.add(storeData);
            
        }

        con.close();
        st.close();
        rsForGetProjectByProjShipDate.close();
        }

        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}
