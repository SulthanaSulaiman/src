/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters.generic;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author raja
 */
public class DeptCellList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private List cellEmpID = new ArrayList();
    private List cellEmpName = new ArrayList();
    private List cellEmpDesigCode = new ArrayList();
    private List cellEmpDesigName = new ArrayList();
    private List cellCode = new ArrayList();
    private List cellName = new ArrayList();
    private List cellDeptCode = new ArrayList();
    private List cellDeptName = new ArrayList();
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String deptCode = "";
    private String empFacilityID = "";
    private String cellCodeParam = "";
    private String empIDParam = "";
    private String empGroup = "";

    public String getEmpGroup() {
        return empGroup;
    }

    public void setEmpGroup(String empGroup) {
        this.empGroup = empGroup;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptCode() {
        return this.deptCode;
    }

    public String getEmpFacilityID() {
        return this.empFacilityID;
    }

    public void setEmpFacilityID(String empFacilityID) {
        this.empFacilityID = empFacilityID;
    }

    public void setCellCodeParam(String cellCodeParam) {
        this.cellCodeParam = cellCodeParam;
    }

    public void setEmpIDParam(String empIDParam) {
        this.empIDParam = empIDParam;
    }

    public void collectCellList() {

        cellCode.clear();
        cellName.clear();
        cellDeptCode.clear();
        cellDeptName.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";
        String sql_whereAll = "";

        sql_select = " SELECT c.cell_code,c.cell_name,c.dept_code,d.department ";
        sql_from = " FROM cell c, department d ";
        sql_where = " where d.dept_code=c.dept_code and c.cell_status='1' and c.dept_code='" + deptCode + "' and d.dept_active='1' order by c.cell_name ";
        sql_whereAll = " where d.dept_code=c.dept_code and c.cell_status='1' and d.dept_active='1' order by c.cell_name ";
        if(empGroup.equals("2")) {
            sql_select += sql_from + sql_whereAll;
        } else {
            sql_select += sql_from + sql_where;
        }

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            cellCode.clear();
            cellName.clear();
            cellDeptCode.clear();
            cellDeptName.clear();
            while (rs.next()) {
                cellCode.add(rs.getString(1));
                cellName.add(rs.getString(2));
                cellDeptCode.add(rs.getString(3));
                cellDeptName.add(rs.getString(4));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    public void collectCellEmpList() {

        cellEmpID.clear();
        cellEmpName.clear();
        cellEmpDesigCode.clear();
        cellEmpDesigName.clear();
        cellCode.clear();
        cellName.clear();
        cellDeptCode.clear();
        cellDeptName.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " select u.emp_id,u.emp_name,u.desig_code,u.dept_code,dept.department,des.designation,c.cell_code,c.cell_name ";
        sql_from = " from user u,department dept,designation des,cell c,emp_cell_map emp_cell ";
        sql_where = " where u.status='1' AND u.desig_code=des.desig_code AND u.dept_code=dept.dept_code AND emp_cell.cell_code=c.cell_code AND "
                + " u.emp_id=emp_cell.emp_id AND emp_cell.cell_code='" + cellCodeParam + "' ORDER BY u.emp_name ";
        sql_select += sql_from + sql_where;

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            cellEmpID.clear();
            cellEmpName.clear();
            cellEmpDesigCode.clear();
            cellEmpDesigName.clear();
            cellCode.clear();
            cellName.clear();
            cellDeptCode.clear();
            cellDeptName.clear();
            while (rs.next()) {
                cellEmpID.add(rs.getString(1));
                cellEmpName.add(rs.getString(2));
                cellEmpDesigCode.add(rs.getString(3));
                cellDeptCode.add(rs.getString(4));
                cellDeptName.add(rs.getString(5));
                cellEmpDesigName.add(rs.getString(6));
                cellCode.add(rs.getString(7));
                cellName.add(rs.getString(8));
            }
            //System.out.println("cellEmpID - Java : "+cellEmpID);
            //System.out.println("cellEmpName - Java : "+cellEmpName);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    public void collectDeptList() {

        cellCode.clear();
        cellName.clear();
        cellDeptCode.clear();
        cellDeptName.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " SELECT c.cell_code,c.cell_name,c.dept_code,d.department ";
        sql_from = " FROM cell c, department d ";
        sql_where = " where d.dept_code=c.dept_code and c.cell_status='1' and c.cell_code='" + cellCodeParam + "' and d.dept_active='1' order by c.cell_name ";
        sql_select += sql_from + sql_where;

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            while (rs.next()) {
                deptCode = rs.getString(3);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }

        }

    }

    public void collectEmpFacilityList() {

        cellCode.clear();
        cellName.clear();
        cellDeptCode.clear();
        cellDeptName.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " SELECT u.emp_id,u.emp_name,u.facility_id ";
        sql_from = " FROM user u ";
        sql_where = " where u.emp_id='" + empIDParam + "' ";
        sql_select += sql_from + sql_where;

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            while (rs.next()) {
                empFacilityID = rs.getString(3);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }

        }

    }

    public List getCellCode() {
        return cellCode;
    }

    public List getCellDeptCode() {
        return cellDeptCode;
    }

    public List getCellDeptName() {
        return cellDeptName;
    }

    public List getCellName() {
        return cellName;
    }

    public List getCellEmpDesigCode() {
        return cellEmpDesigCode;
    }

    public List getCellEmpDesigName() {
        return cellEmpDesigName;
    }

    public List getCellEmpID() {
        return cellEmpID;
    }

    public List getCellEmpName() {
        return cellEmpName;
    }
}
