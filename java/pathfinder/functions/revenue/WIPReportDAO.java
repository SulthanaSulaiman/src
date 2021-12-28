/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author rajac
 */
public class WIPReportDAO implements Serializable {

    DBconnection dbconnection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private Statement dept_cost_st = null;
    private ResultSet dept_cost_rs = null;

    private String projList = "";
    private List facility_list = new ArrayList();
    private List facility_id_list = new ArrayList();

    public List getProjListWithCost(WIPReportVO WIPReportParam) {

        WIPReportVO VOWIPReport = null;

        String loggedFrom = "";
        String loggedTo = "";
        String facilityId = "";

        List packProjList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            loggedFrom = WIPReportParam.getProjLoggedFrom();
            loggedTo = WIPReportParam.getProjLoggedTo();
            facilityId = WIPReportParam.getFacilityId();
            String empId = WIPReportParam.getEmpId();
/*
            if(facilityId == "") {
                Connection localCon = dbconnection.getSampleProperty();
                Statement localSt = localCon.createStatement();
                ResultSet localRs = null;
                String query = "SELECT facility_id FROM user WHERE emp_id='"+empId+"' LIMIT 1";
                localRs = localSt.executeQuery(query);
                while(localRs.next()) {
                    if (localRs.getString(1) != null) {
                        facilityId = localRs.getString(1);
                        WIPReportParam.setFacilityId(facilityId);
                    }
                }
            }
            */
            //System.out.println("Facility ID: " + facilityId +"Emp ID :" + empId);

            /*projList = "SELECT p.proj_id PID, max(p.proj_name) PN, max(p.proj_bktitle) PBT, max(e.est_value) EV, SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600) BH, SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost) BC "
                    + "FROM activity a, USER u, facility f, chapter c,projects p, estimate e "
                    + "WHERE a.chapter_id = c.chapter_id AND a.chargeable_flag='1' AND a.end_time >= '" + loggedFrom + "' AND a.end_time <= '" + loggedTo + "' AND a.emp_id = u.emp_id AND "
                    + "u.facility_id = f.facility_id AND c.proj_id!= '106002' AND p.project_status='1' AND p.proj_id=c.proj_id AND f.facility_id = '" + facilityId + "' AND e.proj_id = p.proj_id "
                    + "GROUP BY c.proj_id";
            */
            /*
            projList = "SELECT p.proj_id PID, max(p.proj_name) PN, max(p.proj_bktitle) PBT, max(e.est_value) EV, SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600) BH, SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost) BC "
                    + "FROM activity a, USER u, facility f, chapter c,projects p, estimate e, activity_type t "
                    + "WHERE a.chapter_id = c.chapter_id AND a.chargeable_flag='1' AND a.end_time >= '" + loggedFrom + "' AND a.end_time <= '" + loggedTo + "' AND a.emp_id = u.emp_id AND "
                    + "u.facility_id = f.facility_id AND c.proj_id!= '106002' AND p.project_status='1' AND p.proj_id=c.proj_id AND f.facility_id = '" + facilityId + "' AND e.proj_id = p.proj_id "
                    + "AND a.activity_code = t.activity_code AND t.billable = '1' GROUP BY c.proj_id";
            */

            projList = "SELECT p.proj_id PID, max(p.proj_name) PN, max(p.proj_bktitle) PBT, max(e.est_value) EV, SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600) BH, SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost) BC "
                    + "FROM (activity a, USER u, facility f, chapter c,projects p, activity_type t) LEFT JOIN estimate e ON p.proj_id = e.proj_id "
                    + "WHERE a.chapter_id = c.chapter_id AND a.end_time >= '" + loggedFrom + "' AND a.end_time <= '" + loggedTo + "' AND a.emp_id = u.emp_id AND "
                    + "u.facility_id = f.facility_id AND c.proj_id!= '106002' AND (p.project_status='1' OR p.project_status='22') AND p.proj_id=c.proj_id AND f.facility_id = '" + facilityId + "' "
                    + "AND a.activity_code = t.activity_code AND t.productive = '1' AND a.end_time IS NOT NULL GROUP BY c.proj_id";

            //System.out.println("WIP: " + projList);
            
            rs = st.executeQuery(projList);
            while (rs.next()) {
                VOWIPReport = new WIPReportVO();
                //System.out.println("Est Val: " + rs.getString(4));
                if (rs.getString(1) != null) {
                    VOWIPReport.setProjId(rs.getString(1));
                    String poValue = getPOValueForProjId(VOWIPReport.getProjId());
                    //System.out.println("poValue: " + poValue);
                    if (poValue != null) {
                        VOWIPReport.setPoValue(poValue);
                    } else {
                        VOWIPReport.setPoValue("0");
                    }
                    String shippingValue = getShippingValueForProjId(VOWIPReport.getProjId());
                    if (shippingValue != null) {
                        VOWIPReport.setShippingValue(shippingValue);
                    } else {
                        VOWIPReport.setShippingValue("0");
                    }
                } else {
                    VOWIPReport.setPoValue("0");
                    VOWIPReport.setProjId("");
                    VOWIPReport.setShippingValue("0");
                }

                if (rs.getString(2) != null) {
                    VOWIPReport.setProjName(rs.getString(2));
                } else {
                    VOWIPReport.setProjName("");
                }

                if (rs.getString(3) != null) {
                    VOWIPReport.setProjBookTitle(rs.getString(3));
                } else {
                    VOWIPReport.setProjBookTitle("");
                }

                if (rs.getString(4) != null) {
                    VOWIPReport.setEstValue(rs.getString(4));
                } else {
                    VOWIPReport.setEstValue("0");
                    //System.out.println("Est Val 1 : " + rs.getString(4)+"_"+VOWIPReport.getEstValue());
                }

                if (rs.getString(5) != null) {
                    VOWIPReport.setBillableHours(rs.getString(5));
                } else {
                    VOWIPReport.setBillableHours("0");
                }

                if (rs.getString(6) != null) {
                    VOWIPReport.setHrCost(rs.getString(6));
                } else {
                    VOWIPReport.setHrCost("0");
                }

                VOWIPReport.setTotalCost(Double.parseDouble(VOWIPReport.getHrCost()) + Double.parseDouble(VOWIPReport.getPoValue()));

                packProjList.add(VOWIPReport);
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

        return packProjList;
    }

    public String getPOValueForProjId(String projId) {
            Connection pocon;
            ResultSet pors = null;
            Statement post = null;
            String poSQL=null;
            String POVal = "0";
            try {
                pocon = dbconnection.getSampleProperty();
                post = pocon.createStatement();
                poSQL = "SELECT SUM(pl.total) "+
                        "FROM purchase_orders po, po_lineitems pl "+
                        "WHERE po.po_number = pl.po_number AND pl.proj_id='"+projId+"' " ;
                //System.out.println("poSQL: " + poSQL);
                pors = post.executeQuery(poSQL);               
                //pors.next();
                while(pors.next()) {
                    POVal = pors.getString(1);
                    if (pors.wasNull()) {
                        POVal = "0";
                    }                    
                }
                //System.out.println("POVal: " + POVal);
                //return pors.getString(1);
                pors.close();
                post.close();
                pocon.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            return POVal;
    }

    public String getShippingValueForProjId(String projId) {
            Connection svcon;
            ResultSet svrs = null;
            Statement svst = null;
            String shippingValue=null;
            String shipVal = "0";
            try {
                svcon = dbconnection.getSampleProperty();
                svst = svcon.createStatement();
                shippingValue = "SELECT SUM(VALUE) FROM shipping_method WHERE proj_id="+projId;
                //System.out.println("poSQL: " + shippingValue);
                svrs = svst.executeQuery(shippingValue);
                //pors.next();
                while(svrs.next()) {
                    shipVal = svrs.getString(1);
                    if (svrs.wasNull()) {
                        shipVal = "0";
                    }
                }
                //System.out.println("POVal: " + shipVal);
                //return pors.getString(1);
                svrs.close();
                svst.close();
                svcon.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            return shipVal;
    }

    public WIPReportVO getFacilityList(WIPReportVO VOWIPReportForFcyList) {

        Connection fycon;
        ResultSet fyrs = null;
        Statement fyst = null;
        String fySQL = null;
        try {
            fycon = dbconnection.getSampleProperty();
            fyst = fycon.createStatement();
            fySQL = "SELECT facility_id, facility_name FROM facility";
            fyrs = fyst.executeQuery(fySQL);
            while (fyrs.next()) {
                facility_id_list.add(fyrs.getString(1));
                facility_list.add(fyrs.getString(2));
            }
            VOWIPReportForFcyList.setFacilityList(facility_list);
            VOWIPReportForFcyList.setFacilityIDList(facility_id_list);
            fyrs.close();
            fyst.close();
            fycon.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return VOWIPReportForFcyList;
    }

}
