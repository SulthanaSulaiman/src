/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.timesheet;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
import pathfinder.functions.generic.ReturnCodes;

/**
 *
 * @author Thanu
 */
public class JobCostReport extends ReturnCodes {

    private int returnCode = SUCCESS;
    private String returnMessage = "";
    private String projectId = "";
    private String empId = "";
    private String clientCode = "";
    private String divisionId = "";
    private String startDate = "";
    private String endDate = "";
    private List projName = new ArrayList();
    private List activity = new ArrayList();
    private List empCost = new ArrayList();
    private List deptCost = new ArrayList();
    private List totalCost = new ArrayList();

    public List getProjName() {
        return this.projName;
    }

    public List getActivity() {
        return this.activity;
    }

    public List getEmpCost() {
        return this.empCost;
    }

    public List getDeptCost() {
        return this.deptCost;
    }

    public List getTotalCost() {
        return this.totalCost;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setProjId(String projectId)
    {
        this.projectId=projectId;
    }
    public int getReturnCode() {
        return returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    //this method returns
    public void getJobCost() {
        //System.out.println("getJobCost");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();

        try {

            con = dbcon.getSampleProperty();

            String sql = "";
            String sql_select = "SELECT a.proj_name,a.proj_id,b.activity,SUM(c.emp_overhead_val),SUM(c.dept_overhead_val),SUM(c.emp_overhead_val+c.dept_overhead_val)";
            String sql_from = " FROM projects a,activity c,chapter d,activity_type b";
            String sql_where = " WHERE a.proj_id=d.proj_id AND c.chapter_id=d.chapter_id AND b.activity_code=c.activity_code";
            String sql_group = " GROUP BY c.activity_code,a.proj_id";

            //If project choosen ie.projectId not null
            if (projectId != null && !("").equals(projectId)) {
                sql_where += " AND a.proj_id='" + projectId + "' ";
            }

            //If client is choosen ie.clientCode not null
            if (clientCode != null && !("").equals(clientCode)) {
                sql_where += " AND a.client_name='" + clientCode + "' ";
            }

            //If division is choosen ie.divisionId not null
            if (divisionId != null && !("").equals(divisionId)) {
                sql_where += " AND a.division_id='" + divisionId + "' ";
            }

            //If project manager is choosen ie.empId not null
            if (empId != null && !("").equals(empId)) {
                sql_where += "  AND a.proj_id IN(SELECT DISTINCT(proj_id) FROM project_team WHERE emp_id='" + empId + "') ";
            }

            //if startDate  and End date are not null
            if ((startDate != null && !("").equals(startDate)) && (endDate != null && !("").equals(endDate))) {
                sql_where += " AND DATE(c.entry_date) BETWEEN '" + startDate + "' AND '" + endDate + "' ";
            }

            //if start date alone is selected then
            if ((startDate != null && !("").equals(startDate)) && (endDate == null && ("").equals(endDate))) {
                sql_where += " AND DATE(c.entry_date)>='" + startDate + "' ";
            }



            sql = sql_select + sql_from + sql_where + sql_group;

            //System.out.println("sql query:" + sql);

            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                projName.add(rs.getString(1));
                activity.add(rs.getString(3));
                if (rs.getString(4) != null) {
                    empCost.add(rs.getString(4));
                } else {
                    empCost.add("0");
                }

                if (rs.getString(5) != null) {
                    deptCost.add(rs.getString(5));
                } else {
                    deptCost.add("0");
                }

                if (rs.getString(6) != null) {
                    totalCost.add(rs.getString(6));
                } else {
                    totalCost.add("0");
                }

            }


        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in getJobCost:" + sqle);

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in getJobCost:" + e);

        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing resultset:se.getMessage()");

                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing Statement:se.getMessage()");

                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing connection:se.getMessage()");

                }
            }

        }

        returnCode = SUCCESS;


    }
}
