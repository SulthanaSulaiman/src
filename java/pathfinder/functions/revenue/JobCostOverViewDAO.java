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
public class JobCostOverViewDAO implements Serializable {

    DBconnection dbconnection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String projList = "";
    private String whereCondition = "";

    //Method For WIP JCO Details
    //Getting FPP and Error value group by super category
    public JobCostOverViewVO getFPPError(JobCostOverViewVO JobCostWIPParam,Connection con) {

        List categoryId = new ArrayList();
        List categoryName = new ArrayList();
        List categoryValue = new ArrayList();
        String projIdParam = "";
        String toDate = "";
        String facilityID = "";


        try {
            st = con.createStatement();

            projIdParam = JobCostWIPParam.getProjId();
            toDate = JobCostWIPParam.getToDate();
            facilityID = JobCostWIPParam.getFacilityID();
            
//            projList = "SELECT sc.super_cat_id,sc.super_category,"
//                    + "(SUM((CASE WHEN (u.facility_id = '" + facilityID + "' AND a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL)  THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) ) WIP"
//                    + " FROM activity a,chapter c,category_milestone ma, facility f,USER u,"
//                    + "estimate_super_category sc,estimate_category ec  "
//                    + "WHERE a.chapter_id = c.chapter_id AND c.proj_id = '" + projIdParam + "' AND u.facility_id = '" + facilityID + "' AND a.end_time <= '" + toDate + "' "
//                    + "AND ma.milestone_code=a.milestone_code AND a.emp_id = u.emp_id "
//                    + "AND u.facility_id = f.facility_id AND  ec.category_id = ma.category_id AND sc.super_cat_id = ec.super_category"
//                    + " GROUP BY sc.super_cat_id ORDER BY sc.super_cat_id";

            projList = "SELECT (CASE WHEN sc.super_cat_id IS NULL THEN '9' ELSE sc.super_cat_id END) CATY_ID,"
                    + "(CASE WHEN sc.super_category  IS NULL THEN 'Uncategorized' ELSE sc.super_category  END) CATY_NAME,"
                    + "(SUM((CASE WHEN (u.facility_id = '" + facilityID + "' AND a.chargeable_flag='0' AND a.chennai_err_start IS NULL "
                    + "AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL) THEN "
                    + "TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END))) WIP "
                    + "FROM activity a, USER u, facility f, chapter c,projects p, department d, activity_type t, proj_milestone_act ma "
                    + "LEFT JOIN estimate_category ec ON (ec.category_id=ma.est_category_id) "
                    + "LEFT JOIN estimate_super_category sc ON (sc.super_cat_id = ec.super_category) "
                    + "WHERE u.dept_code = d.dept_code AND a.chapter_id = c.chapter_id AND t.productive='1' AND a.emp_id = u.emp_id "
                    + "AND u.facility_id = f.facility_id AND c.proj_id = '" + projIdParam + "' AND p.proj_id=c.proj_id AND a.end_time IS NOT NULL "
                    + "AND a.activity_code = t.activity_code AND ma.milestone_act_code=a.milestone_code AND ma.JCO_WIP_enable_flag='1' "
                    + "AND a.end_time <= '" + toDate + "' AND a.activity_deleted='0' GROUP BY sc.super_cat_id ORDER BY sc.super_cat_id";

           // System.out.println("FPP&Error Cost: " + projList);

            rs = st.executeQuery(projList);
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    categoryId.add("0");
                } else {
                    categoryId.add(rs.getString(1));
                }
                if (rs.getString(2) == null) {
                    categoryName.add("0");
                } else {
                    categoryName.add(rs.getString(2));
                }
                if (rs.getString(3) == null) {
                    categoryValue.add("0");
                } else {
                    categoryValue.add(rs.getString(3));
                }
            }
            JobCostWIPParam.setCategoryId((ArrayList) categoryId);
            JobCostWIPParam.setCategoryName((ArrayList) categoryName);
            JobCostWIPParam.setCategoryValue((ArrayList) categoryValue);
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error on getting value in JCO Milestone Category : " + e.toString());
        }
        return JobCostWIPParam;
    }
    //Getting altration value based group by super category

    public JobCostOverViewVO getAltraion(JobCostOverViewVO JobCostWIPParam,Connection con) {

        List categoryId = new ArrayList();
        List categoryName = new ArrayList();
        List categoryValue = new ArrayList();
        String projIdParam = "";
        String toDate = "";
        String facilityID = "";


        try {
            st = con.createStatement();

            projIdParam = JobCostWIPParam.getProjId();
            toDate = JobCostWIPParam.getToDate();
            facilityID = JobCostWIPParam.getFacilityID();
//            projList = "SELECT sc.super_cat_id,sc.super_category,"
//                    + "SUM((CASE WHEN (u.facility_id = '" + facilityID + "' AND a.chargeable_flag='1') THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) WIP "
//                    + " FROM activity a,chapter c,category_milestone ma, facility f,USER u,"
//                    + "estimate_super_category sc,estimate_category ec  "
//                    + "WHERE a.chapter_id = c.chapter_id AND c.proj_id = '" + projIdParam + "' AND u.facility_id = '" + facilityID + "' AND a.end_time <= '" + toDate + "' "
//                    + "AND ma.milestone_code=a.milestone_code AND a.emp_id = u.emp_id "
//                    + "AND u.facility_id = f.facility_id AND  ec.category_id = ma.category_id AND sc.super_cat_id = ec.super_category"
//                    + " GROUP BY sc.super_cat_id ORDER BY sc.super_cat_id";

            projList = "SELECT (CASE WHEN sc.super_cat_id IS NULL THEN '9' ELSE sc.super_cat_id END) CATY_ID,"
                    + "(CASE WHEN sc.super_category  IS NULL THEN 'Uncategorized' ELSE sc.super_category  END) CATY_NAME,"
                    + "(SUM((CASE WHEN (u.facility_id = '" + facilityID + "' AND a.chargeable_flag='1') THEN "
                    + "TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END))) WIP "
                    + "FROM activity a, USER u, facility f, chapter c,projects p, department d, activity_type t, proj_milestone_act ma "
                    + "LEFT JOIN estimate_category ec ON (ec.category_id=ma.est_category_id) "
                    + "LEFT JOIN estimate_super_category sc ON (sc.super_cat_id = ec.super_category) "
                    + "WHERE u.dept_code = d.dept_code AND a.chapter_id = c.chapter_id AND t.productive='1' AND a.emp_id = u.emp_id "
                    + "AND u.facility_id = f.facility_id AND c.proj_id = '" + projIdParam + "' AND p.proj_id=c.proj_id AND a.end_time IS NOT NULL "
                    + "AND a.activity_code = t.activity_code AND ma.milestone_act_code=a.milestone_code AND ma.JCO_WIP_enable_flag='1' "
                    + "AND a.end_time <= '" + toDate + "' AND a.activity_deleted='0' GROUP BY sc.super_cat_id ORDER BY sc.super_cat_id";

           // System.out.println("Altration Cost : " + projList);

            rs = st.executeQuery(projList);
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    categoryId.add("0");
                } else {
                    categoryId.add(rs.getString(1));
                }
                if (rs.getString(2) == null) {
                    categoryName.add("0");
                } else {
                    categoryName.add(rs.getString(2));
                }
                if (rs.getString(3) == null) {
                    categoryValue.add("0");
                } else {
                    categoryValue.add(rs.getString(3));
                }
            }
            JobCostWIPParam.setCategoryId((ArrayList) categoryId);
            JobCostWIPParam.setCategoryName((ArrayList) categoryName);
            JobCostWIPParam.setCategoryValue((ArrayList) categoryValue);
        } catch (Exception e) {
            System.out.println("Error on getting value in JCO Milestone Category : " + e.toString());
        }
        return JobCostWIPParam;
    }

    public List getProjWIPJCOList(JobCostOverViewVO JobCostWIPParam,Connection con) {

        JobCostOverViewVO VOJobCost = null;

        String projIdParam = "";
        String toDate = "";
        String facilityID = "";

        List packProjBWIPValue = new ArrayList();

        try {

            st = con.createStatement();

            projIdParam = JobCostWIPParam.getProjId();
            toDate = JobCostWIPParam.getToDate();
            facilityID = JobCostWIPParam.getFacilityID();
//            projList = "SELECT  ma.milestone_act_name, MAX(p.proj_name) PN, "
//                    + "SUM(("
//                    + "CASE WHEN (a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL) "
//                    + "THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600 "
//                    + "ELSE 0 END)) FBH, "
//                    + "SUM(("
//                    + "CASE WHEN (a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL)  "
//                    + "THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost "
//                    + "ELSE 0 END)) FBC, "
//                    + "SUM((CASE a.chargeable_flag WHEN '1' THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600 ELSE 0 END)) RBH, "
//                    + "SUM((CASE a.chargeable_flag WHEN '1' THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) RBC, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.chennai_err_end,a.chennai_err_start))/3600) CHNERRH, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.chennai_err_end,a.chennai_err_start))/3600*f.fcy_cost) CHNERRC, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.dubuque_err_end,a.dubuque_err_start))/3600) DBQERRH, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.dubuque_err_end,a.dubuque_err_start))/3600*f.fcy_cost) DBQERRC, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.outside_err_end,a.outside_err_start))/3600) OUTERRH, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.outside_err_end,a.outside_err_start))/3600*f.fcy_cost) OUTERRC, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600) BH, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost) BC, t.activity, j.category,"
//                    + "(SUM((CASE WHEN (u.facility_id = '" + facilityID + "' AND a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL)  THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) + "
//                    + "SUM((CASE WHEN (u.facility_id = '" + facilityID + "' AND a.chargeable_flag='1') THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END))) WIP "
//                    + "FROM activity a, USER u, facility f, chapter c,projects p, department d, activity_type t, proj_milestone_act ma, jobcost_category_milestone j "
//                    + "WHERE u.dept_code = d.dept_code AND a.chapter_id = c.chapter_id AND t.productive='1'  AND a.emp_id = u.emp_id "
//                    + "AND u.facility_id = f.facility_id AND c.proj_id = '" + projIdParam + "' AND p.proj_id=c.proj_id AND a.end_time IS NOT NULL AND a.activity_code = t.activity_code "
//                    + "AND ma.milestone_act_code=a.milestone_code AND ma.jobcost_avail_status='1' AND ma.milestone_act_code = j.milestone_act_code AND a.end_time <= '" + toDate + "'"
//                    + "GROUP BY ma.milestone_act_code "
//                    + "ORDER BY j.category, ma.milestone_act_name";

            projList = "SELECT ma.milestone_act_name, MAX(p.proj_name) PN,"
                    + "SUM((CASE WHEN (a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL) THEN "
                    + "TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600 ELSE 0 END)) FBH,"
                    + "SUM((CASE WHEN (a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL) THEN "
                    + "TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) FBC,"
                    + "SUM((CASE a.chargeable_flag WHEN '1' THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600 ELSE 0 END)) RBH,"
                    + "SUM((CASE a.chargeable_flag WHEN '1' THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) RBC,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.chennai_err_end,a.chennai_err_start))/3600) CHNERRH,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.chennai_err_end,a.chennai_err_start))/3600*f.fcy_cost) CHNERRC,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.dubuque_err_end,a.dubuque_err_start))/3600) DBQERRH,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.dubuque_err_end,a.dubuque_err_start))/3600*f.fcy_cost) DBQERRC,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.outside_err_end,a.outside_err_start))/3600) OUTERRH,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.outside_err_end,a.outside_err_start))/3600*f.fcy_cost) OUTERRC,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600) BH, SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost) BC,"
                    + "t.activity,(SUM((CASE WHEN (u.facility_id = '" + facilityID + "' AND a.chargeable_flag='0' AND a.chennai_err_start IS NULL "
                    + "AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL) THEN "
                    + "TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) + "
                    + "SUM((CASE WHEN (u.facility_id = '" + facilityID + "' AND a.chargeable_flag='1') THEN "
                    + "TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END))) WIP "
                    + "FROM activity a, USER u, facility f, chapter c,projects p, department d, activity_type t, proj_milestone_act ma "
                    + "WHERE u.dept_code = d.dept_code AND a.chapter_id = c.chapter_id AND t.productive='1' AND a.emp_id = u.emp_id "
                    + "AND u.facility_id = f.facility_id AND c.proj_id = '" + projIdParam + "' AND p.proj_id=c.proj_id AND a.end_time IS NOT NULL "
                    + "AND a.activity_code = t.activity_code AND ma.milestone_act_code=a.milestone_code AND ma.JCO_WIP_enable_flag='1' "
                    + "AND a.end_time <= '" + toDate + "' AND a.activity_deleted='0' GROUP BY ma.milestone_act_code ORDER BY ma.milestone_act_name";

            //System.out.println("projList: " + projList);

            rs = st.executeQuery(projList);

            while (rs.next()) {

                VOJobCost = new JobCostOverViewVO();
                /*
                 if (rs.getString(1) != null) {
                 VOJobCost.setDeptFunction(rs.getString(1));
                 } else {
                 VOJobCost.setDeptFunction("");
                 }
                 */
                if (rs.getString(1) != null) {
                    VOJobCost.setMilestone(rs.getString(1));
                } else {
                    VOJobCost.setMilestone("");
                }

                if (rs.getString(2) != null) {
                    VOJobCost.setProjName(rs.getString(2));
                } else {
                    VOJobCost.setProjName("");
                }

                if (rs.getString(3) != null) {
                    VOJobCost.setFPBH(rs.getString(3));
                } else {
                    VOJobCost.setFPBH("0");
                }

                if (rs.getString(4) != null) {
                    VOJobCost.setFPBC(rs.getString(4));
                } else {
                    VOJobCost.setFPBC("0");
                }

                if (rs.getString(5) != null) {
                    VOJobCost.setRPBH(rs.getString(5));
                } else {
                    VOJobCost.setRPBH("0");
                }

                if (rs.getString(6) != null) {
                    VOJobCost.setRPBC(rs.getString(6));
                } else {
                    VOJobCost.setRPBC("0");
                }

                if (rs.getString(7) != null) {
                    VOJobCost.setCHNERRHR(rs.getString(7));
                } else {
                    VOJobCost.setCHNERRHR("0");
                }

                if (rs.getString(8) != null) {
                    VOJobCost.setCHNERRCT(rs.getString(8));
                } else {
                    VOJobCost.setCHNERRCT("0");
                }

                if (rs.getString(9) != null) {
                    VOJobCost.setDBQERRHR(rs.getString(9));
                } else {
                    VOJobCost.setDBQERRHR("0");
                }

                if (rs.getString(10) != null) {
                    VOJobCost.setDBQERRCT(rs.getString(10));
                } else {
                    VOJobCost.setDBQERRCT("0");
                }

                if (rs.getString(11) != null) {
                    VOJobCost.setOUTERRHR(rs.getString(11));
                } else {
                    VOJobCost.setOUTERRHR("0");
                }

                if (rs.getString(12) != null) {
                    VOJobCost.setOUTERRCT(rs.getString(12));
                } else {
                    VOJobCost.setOUTERRCT("0");
                }

                if (rs.getString(13) != null) {
                    VOJobCost.setTOTBH(rs.getString(13));
                } else {
                    VOJobCost.setTOTBH("0");
                }

                if (rs.getString(14) != null) {
                    VOJobCost.setTOTBC(rs.getString(14));
                } else {
                    VOJobCost.setTOTBC("0");
                }

                if (rs.getString(15) != null) {
                    VOJobCost.setActivity(rs.getString(15));
                } else {
                    VOJobCost.setActivity("0");
                }

//                if (rs.getString(16) != null) {
//                    VOJobCost.setCategory(rs.getString(16));
//                } else {
//                    VOJobCost.setCategory("0");
//                }

                if (rs.getString(16) != null) {
                    VOJobCost.setWipValueList(rs.getString(16));
                } else {
                    VOJobCost.setWipValueList("0");
                }

                packProjBWIPValue.add(VOJobCost);
            }
            rs.close();
            st.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
          
        }

        return packProjBWIPValue;
    }

    public List getProjWIPList(JobCostOverViewVO JobCostWIPParam) {

        JobCostOverViewVO VOJobCost = null;

        String projIdParam = "";

        List packProjBWIPValue = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            projIdParam = JobCostWIPParam.getProjId();

//            projList = "SELECT  ma.milestone_act_name, MAX(p.proj_name) PN, "
//                    + "SUM(("
//                    + "CASE WHEN (a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL) "
//                    + "THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600 "
//                    + "ELSE 0 END)) NBH, "
//                    + "SUM(("
//                    + "CASE WHEN (a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL)  "
//                    + "THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost "
//                    + "ELSE 0 END)) NBC, "
//                    + "SUM((CASE a.chargeable_flag WHEN '1' THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600 ELSE 0 END)) BH, "
//                    + "SUM((CASE a.chargeable_flag WHEN '1' THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) BC, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.chennai_err_end,a.chennai_err_start))/3600) CHNERRH, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.chennai_err_end,a.chennai_err_start))/3600*f.fcy_cost) CHNERRC, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.dubuque_err_end,a.dubuque_err_start))/3600) DBQERRH, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.dubuque_err_end,a.dubuque_err_start))/3600*f.fcy_cost) DBQERRC, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.outside_err_end,a.outside_err_start))/3600) OUTERRH, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.outside_err_end,a.outside_err_start))/3600*f.fcy_cost) OUTERRC, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600) BH, "
//                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost) BC, t.activity, (CASE WHEN  j.category IS NULL THEN 'Uncategorized' ELSE j.category END) CATY "
//                    + "FROM activity a, USER u, facility f, chapter c,projects p, department d, activity_type t, proj_milestone_act ma, jobcost_category_milestone j "
//                    + "WHERE u.dept_code = d.dept_code AND a.chapter_id = c.chapter_id AND t.productive='1'  AND a.emp_id = u.emp_id "
//                    + "AND u.facility_id = f.facility_id AND c.proj_id = '" + projIdParam + "' AND p.proj_id=c.proj_id AND a.end_time IS NOT NULL AND a.activity_code = t.activity_code "
//                    + "AND ma.milestone_act_code=a.milestone_code AND ma.milestone_act_code = j.milestone_act_code  AND a.approval_status is NOT NULL  "
//                    //+ "AND e.proj_id = c.proj_id AND e.est_number = el.est_number AND LCASE(REPLACE(j.category,' ','')) = LCASE(REPLACE(el.category_id,' ','')) "
//                    + "GROUP BY ma.milestone_act_code "
//                    + "ORDER BY j.category IS NULL, j.category, ma.milestone_act_name";

            projList = "SELECT ma.milestone_act_name, MAX(p.proj_name) PN,"
                    + "SUM((CASE WHEN (a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL) THEN "
                    + "TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600 ELSE 0 END)) FBH,"
                    + "SUM((CASE WHEN (a.chargeable_flag='0' AND a.chennai_err_start IS NULL AND a.dubuque_err_start IS NULL AND a.outside_err_start IS NULL) THEN "
                    + "TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) FBC,"
                    + "SUM((CASE a.chargeable_flag WHEN '1' THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600 ELSE 0 END)) RBH,"
                    + "SUM((CASE a.chargeable_flag WHEN '1' THEN TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost ELSE 0 END)) RBC,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.chennai_err_end,a.chennai_err_start))/3600) CHNERRH,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.chennai_err_end,a.chennai_err_start))/3600*f.fcy_cost) CHNERRC,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.dubuque_err_end,a.dubuque_err_start))/3600) DBQERRH,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.dubuque_err_end,a.dubuque_err_start))/3600*f.fcy_cost) DBQERRC,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.outside_err_end,a.outside_err_start))/3600) OUTERRH,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.outside_err_end,a.outside_err_start))/3600*f.fcy_cost) OUTERRC,"
                    + "SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600) BH, SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost) BC,"
                    + "t.activity,(CASE WHEN ec.category IS NULL THEN 'Uncategorized' ELSE ec.category END) CATY "
                    + "FROM activity a, USER u, facility f, chapter c,projects p, department d, activity_type t, proj_milestone_act ma "
                    + "LEFT JOIN estimate_category ec ON (ec.category_id = ma.est_category_id) "
                    + "WHERE u.dept_code = d.dept_code AND a.chapter_id = c.chapter_id AND t.productive='1' AND a.emp_id = u.emp_id "
                    + "AND u.facility_id = f.facility_id AND c.proj_id = '" + projIdParam + "' AND p.proj_id=c.proj_id AND a.end_time IS NOT NULL "
                    + "AND a.activity_code = t.activity_code AND ma.milestone_act_code=a.milestone_code AND ma.JCO_WIP_enable_flag='1' "
                    + "AND a.approval_status IS NOT NULL AND a.activity_deleted='0' GROUP BY ma.milestone_act_code ORDER BY ec.category IS NULL, ec.category, ma.milestone_act_name";
            
            //System.out.println("JCO : " + projList);

            rs = st.executeQuery(projList);

            while (rs.next()) {

                VOJobCost = new JobCostOverViewVO();
                /*
                 if (rs.getString(1) != null) {
                 VOJobCost.setDeptFunction(rs.getString(1));
                 } else {
                 VOJobCost.setDeptFunction("");
                 }
                 */
                if (rs.getString(1) != null) {
                    VOJobCost.setMilestone(rs.getString(1));
                } else {
                    VOJobCost.setMilestone("");
                }

                if (rs.getString(2) != null) {
                    VOJobCost.setProjName(rs.getString(2));
                } else {
                    VOJobCost.setProjName("");
                }

                if (rs.getString(3) != null) {
                    VOJobCost.setFPBH(rs.getString(3));
                } else {
                    VOJobCost.setFPBH("0");
                }

                if (rs.getString(4) != null) {
                    VOJobCost.setFPBC(rs.getString(4));
                } else {
                    VOJobCost.setFPBC("0");
                }

                if (rs.getString(5) != null) {
                    VOJobCost.setRPBH(rs.getString(5));
                } else {
                    VOJobCost.setRPBH("0");
                }

                if (rs.getString(6) != null) {
                    VOJobCost.setRPBC(rs.getString(6));
                } else {
                    VOJobCost.setRPBC("0");
                }

                if (rs.getString(7) != null) {
                    VOJobCost.setCHNERRHR(rs.getString(7));
                } else {
                    VOJobCost.setCHNERRHR("0");
                }

                if (rs.getString(8) != null) {
                    VOJobCost.setCHNERRCT(rs.getString(8));
                } else {
                    VOJobCost.setCHNERRCT("0");
                }

                if (rs.getString(9) != null) {
                    VOJobCost.setDBQERRHR(rs.getString(9));
                } else {
                    VOJobCost.setDBQERRHR("0");
                }

                if (rs.getString(10) != null) {
                    VOJobCost.setDBQERRCT(rs.getString(10));
                } else {
                    VOJobCost.setDBQERRCT("0");
                }

                if (rs.getString(11) != null) {
                    VOJobCost.setOUTERRHR(rs.getString(11));
                } else {
                    VOJobCost.setOUTERRHR("0");
                }

                if (rs.getString(12) != null) {
                    VOJobCost.setOUTERRCT(rs.getString(12));
                } else {
                    VOJobCost.setOUTERRCT("0");
                }

                if (rs.getString(13) != null) {
                    VOJobCost.setTOTBH(rs.getString(13));
                } else {
                    VOJobCost.setTOTBH("0");
                }

                if (rs.getString(14) != null) {
                    VOJobCost.setTOTBC(rs.getString(14));
                } else {
                    VOJobCost.setTOTBC("0");
                }

                if (rs.getString(15) != null) {
                    VOJobCost.setActivity(rs.getString(15));
                } else {
                    VOJobCost.setActivity("0");
                }

                if (rs.getString(16) != null) {
                    VOJobCost.setCategory(rs.getString(16));
                } else {
                    VOJobCost.setCategory("0");
                }

                packProjBWIPValue.add(VOJobCost);
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

        return packProjBWIPValue;
    }

    public JobCostOverViewVO CollectEstimateValForJCOV(JobCostOverViewVO jobCostOverViewVO) {

        String categoryEstimateQuery = "";
        String projIdParam = "";
        ArrayList jobCostCategoryIDList = new ArrayList();
        ArrayList jobCostCategoryValueList = new ArrayList();
        projIdParam = jobCostOverViewVO.getProjId();
        jobCostCategoryIDList = jobCostOverViewVO.getJobcostCategoryIDList();

        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            for (int i = 0; i < jobCostCategoryIDList.size(); i++) {
                /*
                 categoryEstimateQuery = "SELECT SUM(total) FROM estimate_lineitems el, jobcost_category jc, estimate e "
                 + "WHERE e.proj_id='"+projIdParam+"' AND e.est_number=el.est_number AND jc.jobcost_category_id = '"+jobCostCategoryIDList.get(i)+"' "
                 + "AND jc.jobcost_category_id = el.category_id";
                 *
                 */

                categoryEstimateQuery = "SELECT SUM(el.total) FROM estimate AS e, estimate_lineitems AS el "
                        + "WHERE e.proj_id='" + projIdParam + "' AND e.est_number = el.est_number "
                        + "AND el.category_id = '" + jobCostCategoryIDList.get(i) + "'";

                //System.out.println("Category Estimate Query : " + categoryEstimateQuery);
                rs = st.executeQuery(categoryEstimateQuery);

                while (rs.next()) {
                    if (rs.getString(1) != null) {
                        jobCostCategoryValueList.add(rs.getString(1));
                    } else {
                        jobCostCategoryValueList.add("0");
                    }
                }
            }
            jobCostOverViewVO.setJobcostCategoryValueList(jobCostCategoryValueList);
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
        return jobCostOverViewVO;
    }

    public JobCostOverViewVO CollectAlterationValueForJCOV(JobCostOverViewVO jobCostOverViewVO) {

        String categoryAlterationQuery = "";
        String projIdParam = "";
        ArrayList clientCategoryCostIDList = new ArrayList();
        ArrayList clientCategoryCostValueList = new ArrayList();
        ArrayList generalCategoryCostIDList = new ArrayList();
        ArrayList generalCategoryCostValueList = new ArrayList();

        ArrayList jobCostCategoryAltIDList = new ArrayList();
        ArrayList jobCostCategoryAltValueList = new ArrayList();
        projIdParam = jobCostOverViewVO.getProjId();
        jobCostCategoryAltIDList = jobCostOverViewVO.getJobcostCategoryAltIDList();

        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            categoryAlterationQuery = "SELECT ec.category, cc.category_value "
                    + " FROM category_cost cc, estimate_category ec, projects p, contacts c "
                    + " WHERE p.proj_id='"+projIdParam+"' AND p.client_name=c.contact_id AND c.contact_id=cc.client_id "
                    + " AND cc.category_id=ec.category_id ";
            rs = st.executeQuery(categoryAlterationQuery);

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    clientCategoryCostIDList.add(rs.getString(1));
                } else {
                    clientCategoryCostIDList.add("");
                }
                if (rs.getString(2) != null) {
                    clientCategoryCostValueList.add(rs.getString(2));
                } else {
                    clientCategoryCostValueList.add("0");
                }
            }

            categoryAlterationQuery = "SELECT ec.category, cc.category_value "
                    + " FROM category_cost cc, estimate_category ec "
                    + " WHERE cc.category_id=ec.category_id AND cc.client_id='0'";
            rs = st.executeQuery(categoryAlterationQuery);

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    generalCategoryCostIDList.add(rs.getString(1));
                } else {
                    generalCategoryCostIDList.add("");
                }
                if (rs.getString(2) != null) {
                    generalCategoryCostValueList.add(rs.getString(2));
                } else {
                    generalCategoryCostValueList.add("0");
                }
            }

            for (int i = 0; i < jobCostCategoryAltIDList.size(); i++) {
                if (clientCategoryCostIDList.contains(jobCostCategoryAltIDList.get(i).toString())) {
                    jobCostCategoryAltValueList.add(clientCategoryCostValueList.get(clientCategoryCostIDList.indexOf(jobCostCategoryAltIDList.get(i).toString())));
                } else if (generalCategoryCostIDList.contains(jobCostCategoryAltIDList.get(i).toString())) {
                    jobCostCategoryAltValueList.add(generalCategoryCostValueList.get(generalCategoryCostIDList.indexOf(jobCostCategoryAltIDList.get(i).toString())));
                } else if (clientCategoryCostIDList.contains("General")) {
                    jobCostCategoryAltValueList.add(clientCategoryCostValueList.get(clientCategoryCostIDList.indexOf(("General"))).toString());
                } else if (generalCategoryCostIDList.contains("General")) {
                    jobCostCategoryAltValueList.add(generalCategoryCostValueList.get(generalCategoryCostIDList.indexOf(("General"))).toString());
                } else {
                    jobCostCategoryAltValueList.add("0");
                }
            }
            jobCostOverViewVO.setJobcostCategoryAltValueList(jobCostCategoryAltValueList);
        } catch (SQLException sqle) {
            System.out.println("SQLException : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException : "+npe);
        } catch (Exception ex) {
            System.out.println("Exception : "+ex);
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
        return jobCostOverViewVO;
    }

    public JobCostOverViewVO GetUnPosted(JobCostOverViewVO jobCostOverViewVO) {

        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String unPosted = "";
            String projIdParam = jobCostOverViewVO.getProjId();

            // Get the UnPosted activities value
//            projList = "SELECT IFNULL(SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost),0) AS TOTAL "
//                    + "FROM activity a, USER u, facility f, chapter c,projects p, department d, activity_type t, proj_milestone_act ma, jobcost_category_milestone j, estimate e, estimate_lineitems el "
//                    + "WHERE u.dept_code = d.dept_code AND a.chapter_id = c.chapter_id AND t.productive='1'  AND a.emp_id = u.emp_id "
//                    + "AND u.facility_id = f.facility_id AND c.proj_id = '"+projIdParam+"' AND p.proj_id=c.proj_id AND a.end_time IS NOT NULL AND a.activity_code = t.activity_code "
//                    + "AND ma.milestone_act_code=a.milestone_code AND ma.milestone_act_code = j.milestone_act_code AND e.proj_id = c.proj_id "
//                    + "AND e.est_number = el.est_number AND LCASE(REPLACE(j.category,' ','')) = LCASE(REPLACE(el.category_id,' ','')) AND a.approval_status IS NULL "
//                    + "GROUP BY p.proj_id ORDER BY j.category, ma.milestone_act_name";

            projList = "SELECT IFNULL(SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost),0) AS TOTAL "
                    + "FROM activity a, USER u, facility f, chapter c,projects p, department d, activity_type t, proj_milestone_act ma "
                    + "WHERE u.dept_code = d.dept_code AND a.chapter_id = c.chapter_id AND t.productive='1' AND a.emp_id = u.emp_id "
                    + "AND u.facility_id = f.facility_id AND c.proj_id = '"+projIdParam+"' AND p.proj_id=c.proj_id AND a.end_time IS NOT NULL "
                    + "AND a.activity_code = t.activity_code AND ma.milestone_act_code=a.milestone_code AND ma.JCO_WIP_enable_flag='1' "
                    + "AND a.approval_status IS NULL AND a.activity_deleted='0' GROUP BY p.proj_id";

            //System.out.println("Unposted : "+projList);
            rs = st.executeQuery(projList);

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    jobCostOverViewVO.setUnPosted(Double.parseDouble(rs.getString(1)));
                } else {
                    jobCostOverViewVO.setUnPosted(Double.parseDouble("0.0"));
                }
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in JobCostOverViewDAO - GetUnPosted : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException in JobCostOverViewDAO - GetUnPosted : "+npe);
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException in JobCostOverViewDAO - GetUnPosted : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException in JobCostOverViewDAO - GetUnPosted : "+npe);
            }
        }
        return jobCostOverViewVO;
    }
}
