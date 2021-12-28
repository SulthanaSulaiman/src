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
public class ShippingMethodDAO {

    public List getQuerier(ShippingMethodVO shippingMethodVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List QuerierList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            String querier_query = "SELECT querier_id,querier_name FROM shipping_querier WHERE querier_status = 1 ";

            rs = st.executeQuery(querier_query);

            while (rs.next()) {
               ShippingMethodVO shippingMethod = new ShippingMethodVO();

                if (rs.getString(1) != null) {
                    shippingMethod.setQerierId(rs.getString(1));
                } else {
                    shippingMethod.setQerierId("");
                }

                if (rs.getString(2)  != null) {
                    shippingMethod.setQerierName(rs.getString(2));
                } else {
                    shippingMethod.setQerierName("");
                }

                QuerierList.add(shippingMethod);

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

        return QuerierList;
    }

    public List getModeOfShippment(ShippingMethodVO shippingMethodVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List ModeOfShippmentList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            String mode_of_shippment_query = "SELECT mode_of_shippment_id,mode_of_shippment_name FROM mode_of_shippment "
                                            + " WHERE mode_of_shippment_status = 1 ";

            rs = st.executeQuery(mode_of_shippment_query);

            while (rs.next()) {
               ShippingMethodVO shippingMethod = new ShippingMethodVO();

                if (rs.getString(1) != null) {
                    shippingMethod.setModeOfShippmentId(rs.getString(1));
                } else {
                    shippingMethod.setModeOfShippmentId("");
                }

                if (rs.getString(2)  != null) {
                    shippingMethod.setModeOfShippmentName(rs.getString(2));
                } else {
                    shippingMethod.setModeOfShippmentName("");
                }

                ModeOfShippmentList.add(shippingMethod);

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

        return ModeOfShippmentList;
    }

    public List getShippingMethodForProjectId(ShippingMethodVO shippingMethodVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        //System.out.println("To Table List :");
        List shippingMethodList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String projIdParam = shippingMethodVO.getProjId();
             //System.out.println("Project ID: "+projIdParam);

            String shipping_method_query = "select sm.shipping_method_id, u.user_name, sm.proj_id, sm.querier_id, "
                                            + " sm.mode_of_shippment_id,sm.value,sm.shipping_method_added_date "
                                            + " from shipping_method sm,user u "
                                            + " where u.emp_id=sm.emp_id AND sm.proj_id='"+projIdParam+"' order by "
                                            + " sm.shipping_method_added_date ";
            System.err.println("SQL : "+shipping_method_query);
            rs = st.executeQuery(shipping_method_query);

            while (rs.next()) {
               ShippingMethodVO shippingMethod = new ShippingMethodVO();

                if (rs.getString(1) != null) {
                    shippingMethod.setShippingMethodId(rs.getString(1));
                } else {
                    shippingMethod.setShippingMethodId("");
                }

                if (rs.getString(2)  != null) {
                    shippingMethod.setEmpName(rs.getString(2));
                } else {
                    shippingMethod.setEmpName("");
                }

                if (rs.getString(3) != null) {
                    shippingMethod.setProjId(rs.getString(3));
                } else {
                    shippingMethod.setProjId("");
                }

                if (rs.getString(4) != null) {
                    shippingMethod.setQerierId(rs.getString(4));
                } else {
                    shippingMethod.setQerierId("");
                }

                if (rs.getString(5) != null) {
                    shippingMethod.setModeOfShippmentId(rs.getString(5));
                } else {
                    shippingMethod.setModeOfShippmentId("");
                }

                if (rs.getString(6) != null) {
                    shippingMethod.setValue(rs.getString(6));
                } else {
                    shippingMethod.setValue("");
                }

                if (rs.getString(7) != null) {
                    shippingMethod.setDate(rs.getString(7));
                } else {
                    shippingMethod.setDate("");
                }


                shippingMethodList.add(shippingMethod);

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
        //System.out.println("shippingMethodVO.getEmpId()"+shippingMethodVO.getEmpId());
             //System.out.println("shippingMethodVO.getProjId()"+shippingMethodVO.getProjId());
             //System.out.println("shippingMethodVO.getValue()"+shippingMethodVO.getValue());
             //System.out.println("shippingMethodVO.getQerierId()"+shippingMethodVO.getQerierId());
             //System.out.println("shippingMethodVO.getModeOfShippmentId()"+shippingMethodVO.getModeOfShippmentId());
        return shippingMethodList;
    }

    public void setShippingMethod(ShippingMethodVO shippingMethodVO) {

        //System.out.println("Set Function for the Shipping Method");
        //System.out.println("shippingMethodVO.getEmpId()"+shippingMethodVO.getEmpId());
             //System.out.println("shippingMethodVO.getProjId()"+shippingMethodVO.getProjId());
             //System.out.println("shippingMethodVO.getValue()"+shippingMethodVO.getValue());
             //System.out.println("shippingMethodVO.getQerierId()"+shippingMethodVO.getQerierId());
             //System.out.println("shippingMethodVO.getModeOfShippmentId()"+shippingMethodVO.getModeOfShippmentId());
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

             //String projIdParam = shippingMethodVO.getProjId();
             String empId = shippingMethodVO.getEmpId();
             String projId = shippingMethodVO.getProjId();
             String value = shippingMethodVO.getValue();
             String querierId = shippingMethodVO.getQerierId();
             String modeOfShippmentId = shippingMethodVO.getModeOfShippmentId();


             String set_shipping_method_query = "INSERT INTO shipping_method (emp_id, proj_id, querier_id,"
                                                + " mode_of_shippment_id, value, shipping_method_added_date) "
                                                + " VALUES ('"  + empId + "','" + projId + "',"  + querierId + "," +modeOfShippmentId+ "," + value + ", CURRENT_TIMESTAMP())";

             st.executeUpdate(set_shipping_method_query);
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
        return;
    }

}
