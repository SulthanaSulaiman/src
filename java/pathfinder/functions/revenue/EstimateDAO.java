/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;
import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Pattany
 */
public class EstimateDAO {

    private String projId = "";

    public String getProjId() {
        return projId;
    }

    public void setProjIdParam(String projId) {
        this.projId = projId;
    }



    public List getEstimateList(EstimateVO estimateVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;



        List estimateList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String projIdParam = estimateVO.getProjId();

            String estimate_query = "SELECT el.est_lineitem_id,el.category_id,el.item_id,el.quantity,el.act_quantity "
                    + " FROM estimate_lineitems el, estimate e WHERE e.proj_id='" + projIdParam + "' AND e.est_number=el.est_number order by sort_order";

            //System.out.println(estimate_query);
            rs = st.executeQuery(estimate_query);

            while (rs.next()) {
                EstimateVO estimate = new EstimateVO();

                if (rs.getString(1) != null) {
                    estimate.setEstId(rs.getString(1));
                } else {
                    estimate.setEstId("");
                }

                if (rs.getString(2)  != null) {
                    estimate.setCategory(rs.getString(2));
                } else {
                    estimate.setCategory("");
                }

                if (rs.getString(3) != null) {
                    estimate.setEstLineItems(rs.getString(3));
                } else {
                    estimate.setEstLineItems("");
                }

                if (rs.getString(4) != null) {
                    estimate.setQuant(rs.getString(4));
                } else {
                    estimate.setQuant("");
                }

                if (rs.getString(5) != null) {
                    estimate.setActualQuant(rs.getString(5));
                } else {
                    estimate.setActualQuant("");
                }

                estimateList.add(estimate);

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

        return estimateList;
    }

    public int updateEstimate(EstimateVO estimateVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        int count=0;

        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

             String estId = estimateVO.getEstId();
             String actQuantity = estimateVO.getActualQuant();
             //System.out.println(actQuantity);

             String update_estimate = "UPDATE estimate_lineitems SET act_quantity='" + actQuantity + "' WHERE est_lineitem_id='"+ estId + "'";

             //System.out.println("Update Query ::"+update_estimate);
            count = st.executeUpdate(update_estimate);
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
