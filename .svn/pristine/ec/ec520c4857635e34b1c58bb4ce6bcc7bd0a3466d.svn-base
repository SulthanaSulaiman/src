/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
import pathfinder.functions.generic.ReturnCodes;

/**
 *
 * @author Thanu
 */
public class EmpDetails extends ReturnCodes {

    private int returnCode = SUCCESS;
    private String returnMessage = "";
    private String desigCode = "";
    private String deptCode = "";
    private String status = "1";

    /*flag to set whether to return supervisor emp details or not
     * 0 = return emp who is not a supervisor
     * 1 = return emp who is a supervisor
     */
    private String isSupervisor = "";
    /*flag to set whether empDetails who has supervisor allocated or not allocated should be returned.
     * 0 = return emp without supervisor allocated.
     * 1 = return emp with supervisor allocated
     */
    private String empSupervisor = "";
    private String empApprover = "";
    private String isApprover = "";
    List empName = new ArrayList();
    List empId = new ArrayList();
    List supervisorId = new ArrayList();
    List supervisorName = new ArrayList();
    List approverId = new ArrayList();
    List approverName = new ArrayList();
    private String searchKey = "";
    private String isSuggest = "";
    private String isSupervisorDet = "";
    private String isApproverDet = "";

    public void setIsSuggest(String isSuggest) {
        this.isSuggest = isSuggest;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public String getDesigCode() {
        return desigCode;
    }

    public void setDesigCode(String desigCode) {
        this.desigCode = desigCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setIsSupervisor(String isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

    public String getIsSupervisor() {
        return isSupervisor;
    }

    public void setIsApprover(String isApprover) {
        this.isApprover = isApprover;
    }

    public String getIsApprover() {
        return isApprover;
    }

    public void setEmpSupervisor(String empSupervisor) {
        this.empSupervisor = empSupervisor;
    }

    public String getEmpSupervisor() {
        return empSupervisor;
    }

    public void setEmpApprover(String empApprover) {
        this.empApprover = empApprover;
    }

    public String getEmpApprover() {
        return empApprover;
    }

    public List getEmpName() {
        return empName;
    }

    public List getEmpId() {
        return empId;
    }

    public void setEmpId(List empId) {
        this.empId = empId;
    }

    public List getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(List supervisorId) {
        this.supervisorId = supervisorId;
    }

    public void setIsSupervisorDet(String isSupervisorDet) {
        this.isSupervisorDet = isSupervisorDet;
    }

    public List getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(List supervisorName) {
        this.supervisorName = supervisorName;
    }

    public List getApproverId() {
        return approverId;
    }

    public void setApproverId(List approverId) {
        this.approverId = approverId;
    }

    public void setIsApproverDet(String isApproverDet) {
        this.isApproverDet = isApproverDet;
    }

    public List getApproverName() {
        return approverName;
    }

    public void setApproverName(List approverName) {
        this.approverName = approverName;
    }

    //This methhod Employee details from user table based on the designation passed
    public void collectEmpDet() {
        //System.out.println("collectEmpDet");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String temp = "";
        String emptyString = " ";

        try {

            con = dbcon.getSampleProperty();



            //String sql = "SELECT u.emp_id,u.emp_name,u.supervisor_id,s.emp_id,s.emp_name FROM USER u,USER s ";
            String sql1 = "select u.emp_id,u.emp_name ";
            String sql1_from = " from user u ";
            String sql = "select u.emp_id,u.emp_name,u.supervisor_id,u.approval_authority FROM user u ";
            String where = "";
            String whereClause = " where";


            //designation
            if (desigCode != null && !desigCode.equals("")) {
                if (!where.equals("")) {
                    where += " and";
                }

                where = where + " u.desig_code ='" + desigCode + "'";
            }

            //department
            if (deptCode != null && !deptCode.equals("")) {
                if (!where.equals("")) {
                    where += " and";
                }
                where = where + " u.dept_code = '" + deptCode + "'";
            }

            //status
            if (status != null && !status.equals("")) {
                if (!where.equals("")) {
                    where += " and";
                }
                where = where + " u.status='" + status + "'";
            }

            //empSupervisor. return the emp details who has supervisor allocated to them.
            if (empSupervisor != null && !empSupervisor.equals("") && empSupervisor.equals("1")) {
                if (!where.equals("")) {
                    where += " and";
                }
                where = where + " u.supervisor_id is not null ";
            }

            //empSupervisor. return the emp details who has supervisor not allocated to them.
            if (empSupervisor != null && !empSupervisor.equals("") && empSupervisor.equals("0")) {
                if (!where.equals("")) {
                    where += " and";
                }
                where = where + " ((u.supervisor_id is null or u.supervisor_id='" + emptyString + "') ";
            }


            //empApprover. return the emp details who has approval authority not allocated to them.
            if (empApprover != null && !empApprover.equals("") && empApprover.equals("0")) {
                if (!where.equals("")) {
                    where += " or";
                }
                where = where + " (u.approval_authority is null or u.approval_authority='" + emptyString + "') ";

            }

            if (empSupervisor != null && !empSupervisor.equals("") && empSupervisor.equals("0")) {

                where = where + ")";
            }

            // empApprover return the emp details who has approval authority allocated to them.
            if (empApprover != null && !empApprover.equals("") && empApprover.equals("1")) {
                if (!where.equals("")) {
                    where += " and";
                }
                where = where + " u.approval_authority is not null ";
            }

            if (isSupervisor != null && !isSupervisor.equals("") && isSupervisor.equals("1")) {


                if (!where.equals("")) {
                    where += " and";
                }
                where = where + " u.is_supervisor = 1 ";
            }

            //return supervisor_id details
            if (isSupervisorDet != null && !isSupervisorDet.equals("") && isSupervisorDet.equals("1")) {

                sql1 += " ,u.supervisor_id,s.emp_name ";
                sql1_from = sql1_from + " ,user s ";

                if (!where.equals("")) {
                    where += " and";
                }
                where = where + " s.is_supervisor = 1 and s.emp_id=u.supervisor_id";


            }

            if (isApprover != null && !isApprover.equals("") && isApprover.equals("1")) {


                if (!where.equals("")) {
                    where += " and";
                }
                where = where + " u.is_app_authority = 1 ";
            }

            //return approval authority details
            if (isApproverDet != null && !isApproverDet.equals("") && isApproverDet.equals("1")) {

                sql1 += " ,u.approval_authority,a.emp_name ";
                sql1_from = sql1_from + " ,user a ";

                if (!where.equals("")) {
                    where += " and";
                }
                where = where + " a.is_app_authority = 1 and a.emp_id=u.approval_authority ";


            }

            if ((isSupervisorDet != null && !isSupervisorDet.equals("") && isSupervisorDet.equals("1")) || (isApproverDet != null && !isApproverDet.equals("") && isApproverDet.equals("1"))) {
                sql = sql1;
                sql = sql + sql1_from;
            }

            if (searchKey != null && !searchKey.equals("")) {
                sql += " and u.emp_name like '%" + searchKey + "%' ";

            }



            if (!where.equals("")) {
                sql = sql + whereClause + where;
            }

            if (isSuggest.equals("1")) {
                sql = sql + " limit 10";
            }

            //System.out.println("query:" + sql);

            stmt = con.prepareStatement(sql);
            //stmt.setString(1, desigCode.toUpperCase());

            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    empId.add(rs.getString(1));
                    empName.add(rs.getString(2));

                    if (isSupervisorDet != null && !isSupervisorDet.equals("") && isSupervisorDet.equals("1")) {
                        supervisorId.add(rs.getString("u.supervisor_id"));
                        supervisorName.add(rs.getString("s.emp_name"));
                    }

                    if (isApproverDet != null && !isApproverDet.equals("") && isApproverDet.equals("1")) {
                        approverId.add(rs.getString("u.approval_authority"));
                        approverName.add(rs.getString("a.emp_name"));
                    }
                }
            }


        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in collectEmpDet:" + sqle);

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in collectEmpDet:" + e);

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

    //This method is used to update emp details
    public int updateEmpDetails() {
        //System.out.println("updateEmpDetails");
        Connection con = null;

        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        int empSize = 0;
        int recordsUpdated = 0;
        String sql = "";
        String where = "";
        String whereClause = " WHERE";
        int supervisorSize = 0;
        int approverSize = 0;

//        System.out.println("isSupervisor:" + isSupervisor);
//        System.out.println("isApprover:" + isApprover);


        if (empId != null) {
            empSize = empId.size();
        }

        if (empSize == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Select employee to allocate";
            return returnCode;

        }

        if (isSupervisor.equals("") && isApprover.equals("")) {
            if (supervisorId != null) {
                supervisorSize = supervisorId.size();
            }

            if (approverId != null) {
                approverSize = approverId.size();
            }

            if (supervisorSize == 0) {
                returnCode = BAD_REQUEST;
                returnMessage = "Select supervisor to map ";
                return returnCode;
            }

            if (approverSize == 0) {
                returnCode = BAD_REQUEST;
                returnMessage = "Select approver to map ";
                return returnCode;
            }

        } else if (!isSupervisor.equals("")) {
            if (supervisorId != null) {
                supervisorSize = supervisorId.size();
            }

            if (supervisorSize == 0) {
                returnCode = BAD_REQUEST;
                returnMessage = "Select supervisor to map ";
                return returnCode;
            }

        } else if (!isApprover.equals("")) {
            if (approverId != null) {
                approverSize = approverId.size();
            }

            if (approverSize == 0) {
                returnCode = BAD_REQUEST;
                returnMessage = "Select approver to map ";
                return returnCode;
            }
        }


        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();


            for (int i = 0; i < empSize; i++) {

                sql = "";
                where = "";
                sql = " update user set ";

                if (supervisorId != null && supervisorId.size() > 0) {
                    sql += " supervisor_id = '" + supervisorId.get(i) + "'";
                }

                if (approverId != null && approverId.size() > 0) {
                    if (supervisorId != null && supervisorId.size() > 0) {
                        sql += ",";
                    }
                    sql += " approval_authority = '" + approverId.get(i) + "'";
                }

                //construct where clause
                if (!where.equals("")) {
                    where += ",";
                }
                where += " emp_id='" + empId.get(i) + "'";



                if (!where.equals("")) {
                    sql += whereClause + where;
                }

                //System.out.println("sql:" + sql);

                stmt.addBatch(sql);

            }

            int[] count = stmt.executeBatch();


            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsUpdated++;
                }
            }


        } catch (BatchUpdateException b) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.err.println("SQLException: " + b.getMessage());
            return returnCode;
        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in updateEmpDetails:" + sqle);
            return returnCode;

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in updateEmpDetails:" + e);
            return returnCode;

        } finally {

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing Statement:se.getMessage()");
                    return returnCode;

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
                    return returnCode;

                }
            }

        }
        returnCode = SUCCESS;
        returnMessage = "Employee Supervisor Mapped successfully";
        return returnCode;

    }
}
