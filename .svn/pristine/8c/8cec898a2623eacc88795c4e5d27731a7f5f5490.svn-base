/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;


/**
 *
 * @author Pattany
 */
public class BillingNotesDAO {

    public List getBillingNotesForProjectId(BillingNotesVO billingNotesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List billingNotesList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String projIdParam = billingNotesVO.getProjId();

            String billing_notes_query = "select bn.billing_notes_id, u.user_name, bn.proj_id, bn.billing_notes, DATE_FORMAT(billing_notes_added_date,'%Y-%m-%d'), bn.chargeable_flag " +
                                         " from billing_notes bn,user u " +
                                         " where u.emp_id=bn.emp_id AND bn.proj_id='"+projIdParam+"' order by bn.billing_notes_added_date ";

            rs = st.executeQuery(billing_notes_query);

            while (rs.next()) {
                BillingNotesVO billingNotes = new BillingNotesVO();

                if (rs.getString(1) != null) {
                    billingNotes.setBillingNotesID(rs.getString(1));
                } else {
                    billingNotes.setBillingNotesID("");
                }

                if (rs.getString(2)  != null) {
                    billingNotes.setEmpName(rs.getString(2));
                } else {
                    billingNotes.setEmpName("");
                }

                if (rs.getString(3) != null) {
                    billingNotes.setProjId(rs.getString(3));
                } else {
                    billingNotes.setProjId("");
                }

                if (rs.getString(4) != null) {
                    billingNotes.setBillingNotes(rs.getString(4));
                } else {
                    billingNotes.setBillingNotes("");
                }

                if (rs.getString(5) != null) {
                    billingNotes.setDate(rs.getString(5));
                } else {
                    billingNotes.setDate("");
                }

                if (rs.getString(6) != null) {
                    billingNotes.setChargeable(rs.getString(6));
                } else {
                    billingNotes.setChargeable("");
                }

                billingNotesList.add(billingNotes);

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return billingNotesList;
    }

    public int setBillingNotes(BillingNotesVO billingNotesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count=0;

        try
        {
            con = dbconnection.getSampleProperty();
           // st = con.createStatement();
             String projIdParam = billingNotesVO.getProjId();
             String empId = billingNotesVO.getEmpID();
             String projId = billingNotesVO.getProjId();
             String billingNotes = billingNotesVO.getBillingNotes();
             String chargeableFlag = billingNotesVO.getChargeable();

             //String set_billing_notes_query = "INSERT INTO billing_notes (emp_id, proj_id, billing_notes, billing_notes_added_date) VALUES ('"  + empId + "','" + projId + "','" + billingNotes + "', CURRENT_TIMESTAMP())";
             String set_billing_notes_query = "INSERT INTO billing_notes (emp_id, proj_id, billing_notes, billing_notes_added_date, chargeable_flag) VALUES (?,?,?, CURRENT_TIMESTAMP(), ?)";
             ps = con.prepareStatement(set_billing_notes_query);
             ps.setString(1, empId);
             ps.setString(2, projId);
             ps.setString(3, billingNotes);
             ps.setString(4, chargeableFlag);
             ps.executeUpdate();
             //System.out.println("insert Query ::"+ps);
           // count= st.executeUpdate(set_billing_notes_query);
        } catch (SQLException e) {
                e.printStackTrace();
        } finally
        {
                try
                {
                    if (rs != null)
                            rs.close();
                    if (ps != null)
                            ps.close();
                    if (st != null)
                            st.close();
                    if (con != null)
                            con.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        return count;
    }

    public int updateBillingNotes(BillingNotesVO billingNotesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count=0;

        try
        {
            con = dbconnection.getSampleProperty();
            //st = con.createStatement();

             //String projIdParam = billingNotesVO.getProjId();
             //String empId = billingNotesVO.getEmpID();
             //String projId = billingNotesVO.getProjId();
             String billingNotes = billingNotesVO.getBillingNotes();
             String chargableVal = billingNotesVO.getChargeable();
             String billingNotesId=billingNotesVO.getBillingNotesID();

            /* String update_billing_notes_query = "UPDATE billing_notes SET "
                                                + " billing_notes ='" + billingNotes +" ', "
                                                + " chargeable_flag = " + chargableVal +" , "
                                                + " billing_notes_added_date = CURRENT_TIMESTAMP() "
                                                + " WHERE billing_notes_id = " + billingNotesId +" ";*/
             String update_billing_notes_query = "UPDATE billing_notes SET "
                                                + " billing_notes =?,"
                                                + " chargeable_flag =?,"
                                                + " billing_notes_added_date = CURRENT_TIMESTAMP() "
                                                + " WHERE billing_notes_id =?";

             ps  = con.prepareStatement(update_billing_notes_query);
             ps.setString(1, billingNotes);
             ps.setString(2, chargableVal);
             ps.setString(3, billingNotesId);
             ps.executeUpdate();
             //System.out.println("Update Query ::"+ps);
            //count = st.executeUpdate(update_billing_notes_query);
        } catch (SQLException e) {
                e.printStackTrace();
        } finally
        {
                try
                {
                    if (st != null)
                            st.close();
                    if (con != null)
                            con.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        return count;
    }

    public int deleteBillingNotes(BillingNotesVO billingNotesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        int count=0;

        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

             //String projIdParam = billingNotesVO.getProjId();
             //String empId = billingNotesVO.getEmpID();
             //String projId = billingNotesVO.getProjId();
             //String billingNotes = billingNotesVO.getBillingNotes();
             //String chargableVal = billingNotesVO.getChargeable();
             String billingNotesId=billingNotesVO.getBillingNotesID();

             String update_billing_notes_query = "DELETE FROM billing_notes WHERE billing_notes_id ="+ billingNotesId + " ";

             //System.out.println("Update Query ::"+update_billing_notes_query);
            count = st.executeUpdate(update_billing_notes_query);
        } catch (SQLException e) {
                e.printStackTrace();
        } finally
        {
                try
                {
                    if (st != null)
                            st.close();
                    if (con != null)
                            con.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        return count;
    }
}
