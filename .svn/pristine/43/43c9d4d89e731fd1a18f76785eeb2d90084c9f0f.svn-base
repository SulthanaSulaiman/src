/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Raghuramanm
 */
public class TitlesToPrinterRptDAO {

    public TitlesToPrinterRptVO collectProjPrinterDates(TitlesToPrinterRptVO titlesToPrinterRptVO){

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        connection.DBconnection dbCon = new connection.DBconnection();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String sql_select = "";
        //String sql_from = "";
        String sql_where = "";

        //String projID = titlesToPrinterRptVO.getProjID();
        String fromDate = titlesToPrinterRptVO.getFromDate();
        String toDate = titlesToPrinterRptVO.getToDate();
        String deptCode = titlesToPrinterRptVO.getDeptCode();

        List projIDList = new ArrayList();
        List projNameList = new ArrayList();
        List projPlannerList = new ArrayList();
        List projAuthorList = new ArrayList();
        List projIsbnList = new ArrayList();
        List projCustomerList = new ArrayList();
        List projPrinterDateList = new ArrayList();
        List projCreatedDateList = new ArrayList();

        try {
            con = dbCon.getSampleProperty();
            stmt = con.createStatement();

            sql_select= "select pr.proj_id,pr.proj_name,u.emp_name,"
                    + "concat(athr.firstname,' ',athr.surname),pr.proj_isbn_10,"
                    + "concat(cnt.company,'/',divi.company),date_format(pr.projected_printer_date,'%d/%m/%y'),date_format(pr.proj_date,'%d/%m/%y')"
                    + " from "
                    + "contacts divi, contacts cnt, USER u, projects pr LEFT JOIN contacts athr ON pr.author_id=athr.contact_id ";

            sql_where= "where "
                    + "pr.client_name=cnt.contact_id  AND divi.contact_id=pr.division_id and pr.act_ship_date is null AND pr.project_status not in (2,23,21) and u.emp_id=pr.planner";


           /*if(!deptCode.equals("")&&deptCode!=null&&!deptCode.equals("ALL")){
              sql_where += " and pr.dept_code='"+deptCode+"' " ;
           }*/
            if(deptCode.equals("ALL")){
                sql_where += " and pr.dept_code is not null ";
            } else {
                sql_where += " and pr.dept_code ='"+deptCode+"' ";
            }

           if(!fromDate.equals("")&&!toDate.equals("")){
               sql_where += " and date_format(pr.projected_printer_date,'%Y-%m-%d') between  '"+fromDate+"' and '"+toDate+"' " ;
           }
            
           sql_select +=  sql_where;

           System.out.println("Sql querey"+sql_select);

           rs = stmt.executeQuery(sql_select);

            while(rs.next()) {
                    if(rs.getString(1) != null) {
                        projIDList.add(rs.getString(1));
                    } else {
                        projIDList.add("");
                    }
                    if(rs.getString(2) != null) {
                        projNameList.add(rs.getString(2));
                    } else {
                        projNameList.add("");
                    }
                    if(rs.getString(3) != null) {
                        projPlannerList.add(splChar.decoding(rs.getString(3)));
                    } else {
                        projPlannerList.add("");
                    }
                    if(rs.getString(4) != null) {
                        projAuthorList.add(splChar.decoding(rs.getString(4)));
                    } else {
                        projAuthorList.add("");
                    }
                    if(rs.getString(5) != null) {
                        projIsbnList.add(rs.getString(5));
                    } else {
                        projIsbnList.add("");
                    }
                    if(rs.getString(6) != null) {
                        projCustomerList.add(splChar.decoding(rs.getString(6)));
                    } else {
                        projCustomerList.add("");
                    }
                    if(rs.getString(7) != null) {
                        projPrinterDateList.add(rs.getString(7));
                    } else {
                        projPrinterDateList.add("");
                    }
                    if(rs.getString(8) != null) {
                        projCreatedDateList.add(rs.getString(8));
                    } else {
                        projCreatedDateList.add("");
                    }
            }
            titlesToPrinterRptVO.setProjIDList(projIDList);
            titlesToPrinterRptVO.setProjNameList(projNameList);
            titlesToPrinterRptVO.setProjPlannerList(projPlannerList);
            titlesToPrinterRptVO.setProjAuthorList(projAuthorList);
            titlesToPrinterRptVO.setProjIsbnList(projIsbnList);
            titlesToPrinterRptVO.setProjCustomerList(projCustomerList);
            titlesToPrinterRptVO.setProjPrinterDateList(projPrinterDateList);
            titlesToPrinterRptVO.setProjCreatedDateList(projCreatedDateList);

        } catch (SQLException ex) {
                System.out.println("SQLException : TitlesToPrinterRptDAO - collectProjPrinterDates()" + ex);
          }finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : EmployeeShiftReportDAO - getEmployeeShiftReport()" + ex);
            }
        }
          return titlesToPrinterRptVO;
    }
}
