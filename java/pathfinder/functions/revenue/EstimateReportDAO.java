/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Raghuramanm
 */
public class EstimateReportDAO {

    public EstimateReportVO getEstimationPeriod(EstimateReportVO estimateReportVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List monthWiseEst = new ArrayList();
        List monthList = new ArrayList();
        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            for (int i = 0; i < 12; i++) {
                String sql = "SELECT DATE_FORMAT(DATE_ADD(CURRENT_TIMESTAMP,INTERVAL " + i + " MONTH),'20%y-%m'),DATE_FORMAT(DATE_ADD(CURRENT_TIMESTAMP,INTERVAL " + i + " MONTH),'%b-20%y')";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    monthWiseEst.add(rs.getString(1));
                    monthList.add(rs.getString(2));
                }
            }
            estimateReportVO.setMonthWiseEst(monthWiseEst);
            estimateReportVO.setMonthList(monthList);
        } catch (Exception e) {
            System.out.println("Exception in getEstimationPeriod() of EstimateReportDAO " + e);
        }
        return estimateReportVO;
    }

    public EstimateReportVO getEstimatedValues(EstimateReportVO estimateReportVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List clientList = new ArrayList();
        List clientCodeList = new ArrayList();
        List estValueList = new ArrayList();
        List monthWiseEst = new ArrayList();
        monthWiseEst = estimateReportVO.getMonthWiseEst();

        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            String sqlQuery = " SELECT c.contact_id, c.company FROM contacts c,projects p, contacttype_map ct "
                    + " WHERE c.contact_id=p.client_name AND c.contact_id=ct.contact_id AND ct.type_id='1' AND "
                    + " DATE(p.projected_printer_date) BETWEEN DATE(DATE_ADD(CURRENT_TIMESTAMP,INTERVAL 0 MONTH)) AND "
                    + " DATE(DATE_ADD(CURRENT_TIMESTAMP,INTERVAL 12 MONTH)) GROUP BY c.contact_id ";

            rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    clientCodeList.add(rs.getString(1));
                } else {
                    clientCodeList.add("");
                }
                if (rs.getString(2) != null) {
                    clientList.add(rs.getString(2));
                } else {
                    clientList.add("");
                }
            }
            for (int index = 0; index < clientList.size(); index++) {
                for (int idx = 0; idx < monthWiseEst.size(); idx++) {
                    String sql = "SELECT c.company,SUM(e.est_value) FROM contacts c,projects p,estimate e"
                            + " WHERE c.contact_id=p.client_name AND e.proj_id=p.proj_id AND p.projected_printer_date LIKE '" + monthWiseEst.get(idx) + "%' "
                            + " AND c.contact_id in (" + clientCodeList.get(index) + ") AND p.project_status NOT IN (2,20,21,23) GROUP BY c.contact_id";

                    rs = st.executeQuery(sql);
                    if (rs.next()) {
                        do {
                            if (clientList.contains(rs.getString(1))) {
                                if (rs.getString(2) != null) {
                                    estValueList.add(rs.getString(2));
                                } else {
                                    estValueList.add("");
                                }
                            }
                        } while (rs.next());
                    } else {
                        estValueList.add("0.00");
                    }
                }
            }
            estimateReportVO.setClientList(clientList);
            estimateReportVO.setEstValueList(estValueList);
        } catch (Exception e) {
            System.out.println("Exception in getEstimatedValues() of EstimateReportDAO " + e);
        }
        return estimateReportVO;
    }
}
