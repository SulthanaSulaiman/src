package pathfinder.functions.timesheet;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
import pathfinder.functions.generic.ReturnCodes;

//This function is used to calculate empOverheadValue
public class AllocateOverhead extends ReturnCodes {

    connection.DBconnection conProp = new connection.DBconnection();
    private double empOverheadVal;
    private List empId = new ArrayList();
    private List empName = new ArrayList();
    private List empOverheadId = new ArrayList();
    private List empCost = new ArrayList();
    private List overheadCost = new ArrayList();
    private double deptOverheadVal;
    private List deptCode = new ArrayList();
    private List deptName = new ArrayList();
    private List deptOverheadId = new ArrayList();
    private List deptCost = new ArrayList();
    private int returnCode = SUCCESS;
    private String returnMessage = "";
    private String dept;
    private String desig;
    private String costDeptCode;
    private double costDeptSal;
    //private double deptCostTot;
    private String costCurrCode;
    private String cost_added_emp="";
    private String add_bank_name;
    private String add_bank_addr="";
    private String add_acc_num;
    private String add_billing_loc;
    private String add_billing_loc_address;
    

    public String getCostCurrCode() {
        return this.costCurrCode;
    }

    public void setCostCurrCode(String costCurrCode) {
        this.costCurrCode = costCurrCode;
    }

    public String getAddAccNum() {
        return this.add_acc_num;
    }

    public void setAddAccNum(String add_acc_num) {
        this.add_acc_num = add_acc_num;
    }

    public String getAddBankAddr() {
        return this.add_bank_addr;
    }

    public void setAddBankAddr(String add_bank_addr) {
        this.add_bank_addr = add_bank_addr;
    }

    public String getAddBankName() {
        return this.add_bank_name;
    }

    public void setAddBankName(String add_bank_name) {
        this.add_bank_name = add_bank_name;
    }

    public String getCostDeptCode() {
        return this.costDeptCode;
    }

    public void setCostDeptCode(String costDeptCode) {
        this.costDeptCode = costDeptCode;
    }

    public double getCostDeptSal() {
        return this.costDeptSal;
    }

    public void setCostDeptSal(double costDeptSal) {
        this.costDeptSal = costDeptSal;
    }

    /*public double getDeptCostTot() {
        return this.deptCostTot;
    }

    public void setDeptCostTot(double deptCostTot) {
        this.deptCostTot = deptCostTot;
    }*/

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public double getEmpOverheadVal() {
        return empOverheadVal;
    }

    public void setEmpOverheadVal(double empOverheadVal) {
        this.empOverheadVal = empOverheadVal;
    }

    public double getDeptOverheadVal() {
        return deptOverheadVal;
    }

    public void setDeptOverheadVal(double deptOverheadVal) {
        this.deptOverheadVal = deptOverheadVal;
    }

    public void setEmpId(List empId) {
        this.empId = empId;
    }

    public List getEmpId() {
        return this.empId;
    }

    public void setEmpOverheadId(List empOverheadId) {
        this.empOverheadId = empOverheadId;
    }

    public void setDeptOverheadId(List deptOverheadId) {
        this.deptOverheadId = deptOverheadId;
    }

    public List getOverheadCost() {
        return this.overheadCost;
    }

    public void setOverheadCost(List overheadCost) {
        this.overheadCost = overheadCost;
    }

    public List getEmpCost() {
        return this.empCost;
    }

    public void setempCost(List empCost) {
        this.empCost = empCost;
    }

    public List getDeptCost() {
        return this.deptCost;
    }

    public void setDeptCost(List deptCost) {
        this.deptCost = deptCost;
    }

    public List getempOverheadId() {
        return this.empOverheadId;
    }

    public List getDeptOverheadId() {
        return this.deptOverheadId;
    }

    public void setDeptCode(List deptCode) {
        this.deptCode = deptCode;
    }

    public List getDeptCode() {
        return this.deptCode;
    }

    public void setEmpName(List empName) {
        this.empName = empName;
    }

    public List getEmpName() {
        return this.empName;
    }

    public void setDeptName(List deptName) {
        this.deptName = deptName;
    }

    public List getDeptName() {
        return this.deptName;
    }

    public String getCostAddedEmp() {
        return this.cost_added_emp;
    }

    public void setCostAddedEmp(String cost_added_emp) {
        this.cost_added_emp = cost_added_emp;
    }

    public String getAddBillingLocation() {
        return this.add_billing_loc;
    }

    public void setAddBillingLocation(String add_billing_loc) {
        this.add_billing_loc = add_billing_loc;
    }

    public String getAddBillingLocationAddress() {
        return this.add_billing_loc_address;
    }

    public void setAddBillingLocationAddress(String add_billing_loc_address) {
        this.add_billing_loc_address = add_billing_loc_address;
    }

    
    //this method returns list of employee id for which overhead is not assigned.
    public void getEmpList() {
        //System.out.println("getEmpList");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();

        try {

            con = dbcon.getSampleProperty();

            /*String sql = "SELECT a.emp_id  "
            + "FROM user a,department b,designation c "
            + "WHERE a.emp_id  NOT IN  "
            + "(SELECT emp_id FROM emp_overhead) "
            + "AND a.dept_code = b.dept_code AND a.desig_code=c.desig_code "; */

            String sql = "SELECT a.emp_id,a.emp_name "
                    + "FROM user a,department b,designation c "
                    + "WHERE a.dept_code = b.dept_code AND a.desig_code=c.desig_code and a.status='1' ";


            if (dept != null && !dept.equals("")) {
                sql = sql + " and a.dept_code='" + dept + "'";
            }

            if (desig != null && !desig.equals("")) {
                sql = sql + " and a.desig_code='" + desig + "'";
            }

            //System.out.println("emplist query:" + sql);

            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    empId.add(rs.getString(1));
                    empName.add(rs.getString(2));
                }
            }

            //System.out.println("empIdsize:" + empId.size());
        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in getEmpList:" + sqle);

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in getEmpList:" + e);

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

    //This method returns list of department for which overhead is not assigned.
    public void getDeptList() {
        //System.out.println("getDeptList");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();

        try {

            con = dbcon.getSampleProperty();

            /*String sql = "SELECT dept_code "
            + "FROM department "
            + "WHERE dept_code NOT IN  "
            + "(SELECT dept_code FROM dept_overhead) ";*/

            String sql = "SELECT dept_code,department "
                    + "FROM department ";



            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    deptCode.add(rs.getString(1));
                    deptName.add(rs.getString(2));
                }
            }
        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in getDeptList:" + sqle);

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in getDeptList:" + e);

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

    //This methhod returns list of employees for whom the cost is allocated
    public void getAllocEmpList() {
        //System.out.println("getAllocEmpList");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();

        try {

            con = dbcon.getSampleProperty();



            String sql = "SELECT a.emp_id,a.emp_overhead_id,b.emp_name,a.emp_overhead_val FROM emp_overhead a,user b,department c, designation d "
                    + " WHERE a.emp_id = b.emp_id AND b.dept_code = c.dept_code AND b.desig_code = d.desig_code";



            if (dept != null && !dept.equals("")) {
                sql = sql + " and b.dept_code='" + dept + "'";
            }

            if (desig != null && !desig.equals("")) {
                sql = sql + " and b.desig_code='" + desig + "'";
            }

            //System.out.println("alloc emp query:" + sql);

            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    empId.add(rs.getString(1));
                    empOverheadId.add(rs.getString(2));
                    empName.add(rs.getString(3));
                    empCost.add(rs.getString(4));
                }
            }

            //System.out.println("empIdsize:" + empId.size());
        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in getAllocEmpList:" + sqle);

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in getAllocEmpList:" + e);

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

    //This methhod returns list of employees for whom the cost is allocated
    public void getAllocDeptList() {
        //System.out.println("getAllocDeptList");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();

        try {

            con = dbcon.getSampleProperty();



            String sql = "SELECT a.dept_overhead_id,a.dept_overhead_val,b.department FROM dept_overhead a,department b WHERE a.dept_code = b.dept_code";





            //System.out.println("alloc emp query:" + sql);

            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    deptOverheadId.add(rs.getString(1));
                    deptCost.add(rs.getString(2));
                    deptName.add(rs.getString(3));

                }
            }

            System.out.println("deptOverheadId:" + deptOverheadId.size());
        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in getAllocDeptList:" + sqle);

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in getAllocDeptList:" + e);

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

    //This method is used to save the employee overhead ie.allocate cost to employees
    public int saveEmpOverhead() {
        //System.out.println("saveEmpOverhead");
        Connection con = null;

        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        int empSize = 0;
        int recordsInserted = 0;

        if (empId != null) {
            empSize = empId.size();
        } else {
            returnCode = BAD_REQUEST;
            returnMessage = "Select employee to allocate cost";
            return returnCode;
        }

        if (empSize == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Select employee to allocate cost";
            return returnCode;

        } else if (empOverheadVal == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Enter cost to allocate";
            return returnCode;

        }

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();


            for (int i = 0; i < empSize; i++) {
                /* stmt.setDouble(1, empOverheadVal);
                stmt.setString(2, empId.get(i).toString());*/

                stmt.addBatch("INSERT INTO emp_overhead (emp_overhead_val,emp_id,assigned_date) VALUES(" + empOverheadVal + ",'" + empId.get(i) + "',CURRENT_TIMESTAMP)");

            }

            int[] count = stmt.executeBatch();


            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsInserted++;
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
            System.out.println("SQLException in saveEmpOverhead:" + sqle);
            return returnCode;

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in saveEmpOverhead:" + e);
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
        returnMessage = "Employee overhead saved successfully";
        return returnCode;

    }

    //This method is used to delete the allocated employee overhead
    public int deleteEmpOverhead() {
        //System.out.println("deleteEmpOverhead");
        Connection con = null;

        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        int empSize = 0;
        int recordsDeleted = 0;
        //System.out.println("empOverheadId:" + empOverheadId);

        if (empOverheadId != null) {
            empSize = empOverheadId.size();
            //System.out.println("empSize:" + empSize);
        } else {
            returnCode = BAD_REQUEST;
            returnMessage = "Select employee to deallocate cost";
            return returnCode;
        }

        if (empSize == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Select employee to deallocate cost";
            return returnCode;

        }

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();


            for (int i = 0; i < empSize; i++) {

                stmt.addBatch("DELETE FROM emp_overhead WHERE emp_overhead_id=" + empOverheadId.get(i));

            }

            int[] count = stmt.executeBatch();


            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsDeleted++;
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
            System.out.println("SQLException in deleteEmpOverhead:" + sqle);
            return returnCode;

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in deleteEmpOverhead:" + e);
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
        returnMessage = "Employee overhead saved successfully";
        return returnCode;

    }

    //This method is used to update the allocated employee overhead
    public int updateEmpOverhead() {
        System.out.println("updateEmpOverhead");
        Connection con = null;

        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        int empSize = 0;
        int recordsUpdated = 0;

        if (empOverheadId != null) {
            empSize = empOverheadId.size();
        } else {
            returnCode = BAD_REQUEST;
            returnMessage = "Select employee to update cost";
            return returnCode;
        }

        if (empSize == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Select employee to update cost";
            return returnCode;

        } else if (empOverheadVal == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Enter cost to allocate";
            return returnCode;

        }

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();


            for (int i = 0; i < empSize; i++) {

                stmt.addBatch("UPDATE emp_overhead SET emp_overhead_val=" + empOverheadVal + " WHERE emp_overhead_id=" + empOverheadId.get(i));

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
            System.out.println("SQLException in updateEmpOverhead:" + sqle);
            return returnCode;

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in updateEmpOverhead:" + e);
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
        returnMessage = "Employee overhead saved successfully";
        return returnCode;

    }

    //This method is used to save the employee overhead ie.allocate cost to employees
    public int saveDeptOverhead() {
        //System.out.println("saveDeptOverhead");
        Connection con = null;

        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        int size = deptCode.size();
        int recordsInserted = 0;

        if (deptCode != null) {
            size = deptCode.size();
        } else {
            returnCode = BAD_REQUEST;
            returnMessage = "Select department to allocate cost";
            return returnCode;
        }

        if (size == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Select department to allocate cost";
            return returnCode;

        } else if (deptOverheadVal == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Enter cost to allocate";
            return returnCode;

        }


        try {

            con = dbcon.getSampleProperty();

            String sql = "INSERT INTO dept_overhead "
                    + "(dept_overhead_val,dept_code,assigned_date)"
                    + "VALUES(?,?,CURRENT_TIMESTAMP)";

            stmt = con.prepareStatement(sql);

            for (int i = 0; i < size; i++) {
                //System.out.println("INSERT INTO dept_overhead(dept_overhead_val,dept_code,assigned_date) VALUES((" + deptOverheadVal + ",'" + deptCode.get(i) + "',CURRENT_TIMESTAMP)");
                stmt.addBatch("INSERT INTO dept_overhead(dept_overhead_val,dept_code,assigned_date) VALUES(" + deptOverheadVal + ",'" + deptCode.get(i) + "',CURRENT_TIMESTAMP)");
            }

            int[] count = stmt.executeBatch();

            //System.out.println("count : " + count.length);
            for (int i = 0; i < count.length; i++) {
                //System.out.println(count[i]);
                if (count[i] == 1) {
                    recordsInserted++;
                }
            }
            //System.out.println("recordsInserted : " + recordsInserted);

        } catch (BatchUpdateException b) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.err.println("SQLException: " + b.getMessage());
            return returnCode;

        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in saveDeptOverhead:" + sqle);
            return returnCode;

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in saveDeptOverhead:" + e);
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
        returnMessage = "Department overhead saved successfully";
        return returnCode;

    }

    //This method is used to delete the allocated employee overhead
    public int deleteDeptOverhead() {
        //System.out.println("deleteDeptOverhead");
        Connection con = null;

        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        int size = 0;
        int recordsDeleted = 0;
        // System.out.println("empOverheadId:"+empOverheadId);

        if (deptOverheadId != null) {
            size = deptOverheadId.size();
            //System.out.println("size:" + size);
        } else {
            returnCode = BAD_REQUEST;
            returnMessage = "Select department to deallocate cost";
            return returnCode;
        }

        if (size == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Select department to deallocate cost";
            return returnCode;

        }

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();


            for (int i = 0; i < size; i++) {

                stmt.addBatch("DELETE FROM dept_overhead WHERE dept_overhead_id=" + deptOverheadId.get(i));

            }

            int[] count = stmt.executeBatch();


            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsDeleted++;
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
            System.out.println("SQLException in deleteDeptOverhead:" + sqle);
            return returnCode;

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in deleteDeptOverhead:" + e);
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
        returnMessage = "Employee overhead saved successfully";
        return returnCode;

    }

    //This method is used to update the allocated employee overhead
    public int updateDeptOverhead() {
        //System.out.println("updateDeptOverhead");
        Connection con = null;

        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        int size = 0;
        int recordsUpdated = 0;

        if (deptOverheadId != null) {
            size = deptOverheadId.size();
        } else {
            returnCode = BAD_REQUEST;
            returnMessage = "Select department to update cost";
            return returnCode;
        }

        if (size == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Select department to update cost";
            return returnCode;

        } else if (deptOverheadVal == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Enter cost to allocate";
            return returnCode;

        }

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();


            for (int i = 0; i < size; i++) {

                stmt.addBatch("UPDATE dept_overhead SET dept_overhead_val=" + deptOverheadVal + " WHERE dept_overhead_id=" + deptOverheadId.get(i));

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
            System.out.println("SQLException in updateDeptOverhead:" + sqle);
            return returnCode;

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in updateDeptOverhead:" + e);
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
        returnMessage = "Employee overhead saved successfully";
        return returnCode;

    }

    public int saveDeptCost() {
        //System.out.println("saveDeptCost");

        Connection con = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        
        int deptCostSal = 0;
        int recordsInserted = 0;

        deptCostSal = (int)costDeptSal;
        //System.out.println("deptCostSal: "+deptCostSal);

        if (deptCostSal != 0 && deptCostSal > 0) {
            System.out.println("If-deptCostSal: "+deptCostSal);
        } else {
            returnCode = BAD_REQUEST;
            returnMessage = "Department cost should not zero or less than zero.";
            return returnCode;
        }

        if (deptCostSal == 0) {
            returnCode = BAD_REQUEST;
            returnMessage = "Department cost should not be zero.";
            return returnCode;
        } 

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            if(deptCostSal != 0 && deptCostSal > 0){
                //System.out.println("SUCCESS - ADD DEPT COST");
                stmt.addBatch("INSERT INTO dept_cost (dept_code,dept_cost,currency_id,added_date,added_by) VALUES ('"+costDeptCode+"',"+costDeptSal+","+costCurrCode+",CURRENT_TIMESTAMP(),'"+cost_added_emp+"')");
                int[] count = stmt.executeBatch();

                for (int i = 0; i < count.length; i++) {
                    if (count[i] == 1) {
                        recordsInserted++;
                    }
                }
                //System.out.println("recordsInserted : " + recordsInserted);
           }
        } catch (BatchUpdateException b) {
            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.err.println("SQLException: " + b.getMessage());
            return returnCode;
        } catch (SQLException sqle) {
            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in saveEmpOverhead:" + sqle);
            return returnCode;

        } catch (Exception e) {
            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in saveEmpOverhead:" + e);
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
        returnMessage = "Department cost saved successfully";
        return returnCode;
    }

    public int saveBankDetails() {
        //System.out.println("saveBankDetails");

        Connection con = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();

        int recordsInserted = 0;

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            if(!add_acc_num.equals("") && !add_bank_addr.equals("") && !add_bank_name.equals("")){
                //System.out.println("SUCCESS - ADD Bank Details");
                stmt.addBatch("INSERT INTO bank_details (bank_acc_number,bank_name,bank_address,added_date,added_by) VALUES ('"+add_acc_num+"','"+add_bank_name+"','"+add_bank_addr+"',CURRENT_TIMESTAMP(),'"+cost_added_emp+"')");
                int[] count = stmt.executeBatch();

                for (int i = 0; i < count.length; i++) {
                    if (count[i] == 1) {
                        recordsInserted++;
                    }
                }
                //System.out.println("recordsInserted : " + recordsInserted);
           }
        } catch (BatchUpdateException b) {
            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.err.println("SQLException: " + b.getMessage());
            return returnCode;
        } catch (SQLException sqle) {
            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in saveEmpOverhead:" + sqle);
            return returnCode;

        } catch (Exception e) {
            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in saveEmpOverhead:" + e);
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
        returnMessage = "Bank details are added successfully.";
        return returnCode;
    }

    public int saveBillingLocation() {
        //System.out.println("saveBillingLocation");

        Connection con = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();

        int recordsInserted = 0;

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            if(!add_billing_loc.equals("") && !add_billing_loc_address.equals("")){
                //System.out.println("SUCCESS - ADD Billing Location");
                stmt.addBatch("INSERT INTO billing_location (billing_location,billing_location_address,added_date,added_by) VALUES ('"+add_billing_loc+"','"+add_billing_loc_address+"',CURRENT_TIMESTAMP(),'"+cost_added_emp+"')");
                int[] count = stmt.executeBatch();

                for (int i = 0; i < count.length; i++) {
                    if (count[i] == 1) {
                        recordsInserted++;
                    }
                }
                //System.out.println("recordsInserted : " + recordsInserted);
           }
        } catch (BatchUpdateException b) {
            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.err.println("SQLException: " + b.getMessage());
            return returnCode;
        } catch (SQLException sqle) {
            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in saveEmpOverhead:" + sqle);
            return returnCode;

        } catch (Exception e) {
            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in saveEmpOverhead:" + e);
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
        returnMessage = "Billing Location is added successfully.";
        return returnCode;
    }
}
