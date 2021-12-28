/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.timesheet;

/**
 *
 * @author rajac
 */
import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class DeptCostDAO implements Serializable {

    DBconnection dbconnection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private String dept_cost_query = "";
    private String bank_det_query = "";
    private String billing_loc_query = "";


    public List getDeptCost() {

        DeptCostVO dept_cost = null;
        List deptCostList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            dept_cost_query = " select dc.dept_cost_id,dc.dept_code,d.department,dc.dept_cost,dc.currency_id,c.long_name "
                                    + " from dept_cost dc,department d,currency c "
                                    + " where d.dept_code=dc.dept_code and c.currency_id=dc.currency_id ORDER BY dc.currency_id,dc.dept_cost ";

            rs = st.executeQuery(dept_cost_query);

            while (rs.next()) {

                dept_cost = new DeptCostVO();

                if (rs.getString(1) != null) {
                    dept_cost.setDeptCostID(rs.getString(1));
                } else {
                    dept_cost.setDeptCostID("");
                }

                if (rs.getString(2) != null) {
                    dept_cost.setMappedDeptCode(rs.getString(2));
                } else {
                    dept_cost.setMappedDeptCode("");
                }

                if (rs.getString(3) != null) {
                    dept_cost.setMappedDeptName(rs.getString(3));
                } else {
                    dept_cost.setMappedDeptName("");
                }

                if (rs.getString(4) != null) {
                    dept_cost.setDeptCostVal(rs.getString(4));
                } else {
                    dept_cost.setDeptCostVal("");
                }

                if (rs.getString(5) != null) {
                    dept_cost.setDeptCostCurrID(rs.getString(5));
                } else {
                    dept_cost.setDeptCostCurrID("");
                }

                if (rs.getString(6) != null) {
                    dept_cost.setDeptCostCurrName(rs.getString(6));
                } else {
                    dept_cost.setDeptCostCurrName("");
                }

                deptCostList.add(dept_cost);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return deptCostList;
    }

    public List getBankDetails() {

        DeptCostVO dept_cost = null;
        List bankDetailList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            bank_det_query = " select bdet.bank_acc_number,bdet.bank_name,bdet.bank_address,bdet.added_date,bdet.added_by,u.emp_name "
                                    + " from bank_details bdet,user u "
                                    + " where u.emp_id=bdet.added_by order by bdet.added_date ";

            rs = st.executeQuery(bank_det_query);

            while (rs.next()) {

                dept_cost = new DeptCostVO();

                if (rs.getString(1) != null) {
                    dept_cost.setAvailBankAccNum(rs.getString(1));
                } else {
                    dept_cost.setAvailBankAccNum("");
                }

                if (rs.getString(2) != null) {
                    dept_cost.setAvailBankName(rs.getString(2));
                } else {
                    dept_cost.setAvailBankName("");
                }

                if (rs.getString(3) != null) {
                    dept_cost.setAvailBankAddress(rs.getString(3));
                } else {
                    dept_cost.setAvailBankAddress("");
                }

                if (rs.getString(4) != null) {
                    dept_cost.setAvailBankDetAddedDate(rs.getString(4));
                } else {
                    dept_cost.setAvailBankDetAddedDate("");
                }

                if (rs.getString(5) != null) {
                    dept_cost.setAvailBankDetAddedEmpID(rs.getString(5));
                } else {
                    dept_cost.setAvailBankDetAddedEmpID("");
                }

                if (rs.getString(6) != null) {
                    dept_cost.setAvailBankDetAddedEmpName(rs.getString(6));
                } else {
                    dept_cost.setAvailBankDetAddedEmpName("");
                }

                bankDetailList.add(dept_cost);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return bankDetailList;
    }

    public List getBillingLocation() {

        DeptCostVO dept_cost = null;
        List billingLocationList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            billing_loc_query = " select bloc.billing_location,bloc.billing_location_address,bloc.added_date,bloc.added_by,u.emp_name "
                                    + " from billing_location bloc,user u "
                                    + " where u.emp_id=bloc.added_by order by bloc.added_date ";

            rs = st.executeQuery(billing_loc_query);

            while (rs.next()) {

                dept_cost = new DeptCostVO();

                if (rs.getString(1) != null) {
                    dept_cost.setAvailBillingLocName(rs.getString(1));
                } else {
                    dept_cost.setAvailBillingLocName("");
                }

                if (rs.getString(2) != null) {
                    dept_cost.setAvailBillingLocAddress(rs.getString(2));
                } else {
                    dept_cost.setAvailBillingLocAddress("");
                }

                if (rs.getString(3) != null) {
                    dept_cost.setAvailBillingLocAddedDate(rs.getString(3));
                } else {
                    dept_cost.setAvailBillingLocAddedDate("");
                }

                if (rs.getString(4) != null) {
                    dept_cost.setAvailBillingLocAddedEmpID(rs.getString(4));
                } else {
                    dept_cost.setAvailBillingLocAddedEmpID("");
                }

                if (rs.getString(5) != null) {
                    dept_cost.setAvailBillingLocAddedEmpName(rs.getString(5));
                } else {
                    dept_cost.setAvailBillingLocAddedEmpName("");
                }
                billingLocationList.add(dept_cost);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return billingLocationList;
    }

}
