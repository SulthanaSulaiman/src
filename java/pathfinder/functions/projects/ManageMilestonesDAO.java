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
public class ManageMilestonesDAO implements Serializable{


    public List getMasterActivityMapNames(ManageMilestonesVO manageMilestonesVO) {

            DBconnection dbconnection = new DBconnection();
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            String milestoneId=manageMilestonesVO.getMilestoneCode();
            String stageCode = manageMilestonesVO.getStageCode();

            List masterActivityMapNameList = new ArrayList();

            try
            {
                    con = dbconnection.getSampleProperty();
                    st = con.createStatement();

                    String masterActivityMapNames_query = "SELECT DISTINCT(t.activity), t.activity_code, ma.milestone_act_id "
                                                + " FROM milestone_act_map ma, proj_milestone_act pm, activity_type t "
                                                + " WHERE pm.milestone_act_code=ma.milestone_act_code "
                                                + " AND ma.activity_code=t.activity_code AND ma.milestone_act_code='" + milestoneId + "' AND stage_code='" + stageCode + "' GROUP BY(t.activity)";

                    //System.out.println("QUERY QUERY  ACTIVITY:"+masterActivityMapNames_query);
                    rs = st.executeQuery(masterActivityMapNames_query);

                    while (rs.next()) {
                    ManageMilestonesVO activityNames = new ManageMilestonesVO();


                    if (rs.getString(1) != null) {
                        activityNames.setActivityMapName(rs.getString(1));
                    } else {
                        activityNames.setActivityMapName("");
                    }

                    if (rs.getString(2)  != null) {
                        activityNames.setActivityMapCode(rs.getString(2));
                    } else {
                        activityNames.setActivityMapCode("");
                    }
                    if (rs.getString(3) != null) {
                        activityNames.setMilestoneActId(rs.getString(3));
                    } else {
                        activityNames.setMilestoneActId("");
                    }

                     masterActivityMapNameList.add(activityNames);
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
            return masterActivityMapNameList;
        }

        public List getStageNames(ManageMilestonesVO manageMilestonesVO) {

            DBconnection dbconnection = new DBconnection();
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            List stageNamesList = new ArrayList();

            try
            {
                    con = dbconnection.getSampleProperty();
                    st = con.createStatement();

                    String stageNames_query = "SELECT stage_code, stage  FROM  project_stage ";

                    rs = st.executeQuery(stageNames_query);

                    while (rs.next()) {
                    ManageMilestonesVO manageStageNames = new ManageMilestonesVO();

                    if (rs.getString(1) != null) {
                        manageStageNames.setStageCode(rs.getString(1));
                    } else {
                        manageStageNames.setStageCode("");
                    }

                    if (rs.getString(2)  != null) {
                        manageStageNames.setStageName(rs.getString(2));
                    } else {
                        manageStageNames.setStageName("");
                    }

                     stageNamesList.add(manageStageNames);
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
            return stageNamesList;
        }

        // The Following Function is used for to retrive all the milestones
        public List getMilestonesNames(ManageMilestonesVO manageMilestonesVO) {

            DBconnection dbconnection = new DBconnection();
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            String stageCode = manageMilestonesVO.getStageCode();
            List milestoneNameList = new ArrayList();

            try
            {
                    con = dbconnection.getSampleProperty();
                    st = con.createStatement();

                    String milestoneNames_query = "SELECT m.milestone_act_code, m.milestone_act_name "
                            + "FROM proj_milestone_act m, milestone_order o "
                            + "WHERE o.stage_code='"+stageCode+"' AND o.milestone_act_code=m.milestone_act_code ORDER BY m.milestone_act_name";

                    rs = st.executeQuery(milestoneNames_query);

                    while (rs.next()) {
                    ManageMilestonesVO milestoneNames = new ManageMilestonesVO();

                    if (rs.getString(1) != null) {
                        milestoneNames.setMilestoneCode(rs.getString(1));
                    } else {
                        milestoneNames.setMilestoneCode("");
                    }

                    if (rs.getString(2)  != null) {
                        milestoneNames.setMilestoneName(rs.getString(2));
                    } else {
                        milestoneNames.setMilestoneName("");
                    }

                     milestoneNameList.add(milestoneNames);
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
            return milestoneNameList;
        }

        public List getActivityNames(ManageMilestonesVO manageMilestonesVO) {

            DBconnection dbconnection = new DBconnection();
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            String milestoneId=manageMilestonesVO.getMilestoneCode();
            String stageCode = manageMilestonesVO.getStageCode();

            List activityNameList = new ArrayList();

            try
            {
                    con = dbconnection.getSampleProperty();
                    st = con.createStatement();

                    String activityNames_query = "SELECT DISTINCT(t.activity), t.activity_code"
                                                + " FROM milestone_act_map ma, proj_milestone_act pm, activity_type t "
                                                + " WHERE pm.milestone_act_code=ma.milestone_act_code "
                                                + " AND ma.activity_code=t.activity_code AND ma.milestone_act_code='" + milestoneId + "' AND stage_code='"+stageCode+"' GROUP BY(t.activity)";
                    //System.out.println("QUERY QUERY  ACTIVITY Miles:"+activityNames_query);
                    rs = st.executeQuery(activityNames_query);

                    while (rs.next()) {
                    ManageMilestonesVO activityNames = new ManageMilestonesVO();

                    if (rs.getString(1) != null) {
                        activityNames.setActivityMapName(rs.getString(1));
                    } else {
                        activityNames.setActivityMapName("");
                    }

                    if (rs.getString(2)  != null) {
                        activityNames.setActivityMapCode(rs.getString(2));
                    } else {
                        activityNames.setActivityMapCode("");
                    }
                    
                    /*if (rs.getString(3)  != null) {
                        activityNames.setMilestoneActId(rs.getString(3));
                    } else {
                        activityNames.setMilestoneActId("");
                    }*/
                    

                     activityNameList.add(activityNames);
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
            return activityNameList;
        }

        public List getUnMappedActivityNames(ManageMilestonesVO manageMilestonesVO) {

            DBconnection dbconnection = new DBconnection();
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            String milestoneId=manageMilestonesVO.getMilestoneCode();
            String stageCode = manageMilestonesVO.getStageCode();

            List unMapActivityNameList = new ArrayList();

            try
            {
                    con = dbconnection.getSampleProperty();
                    st = con.createStatement();

//                    String unMapActivityNames_query = "SELECT DISTINCT(t.activity), t.activity_code "
//                                                     +" FROM milestone_act_map ma, proj_milestone_act pm, activity_type t "
//                                                     +" WHERE pm.milestone_act_code=ma.milestone_act_code "
//                                                     +" AND ma.activity_code=t.activity_code AND ma.milestone_act_code <>'"+ milestoneId + "'"
//                                                     +" AND t.activity NOT IN (SELECT DISTINCT(t.activity) FROM milestone_act_map ma, proj_milestone_act pm, activity_type t "
//                                                     +" WHERE pm.milestone_act_code=ma.milestone_act_code "
//                                                     +" AND ma.activity_code=t.activity_code"
//                                                     +" AND ma.milestone_act_code='"+ milestoneId + "') ";


                    
                    String unMapActivityNames_query = "SELECT DISTINCT(t.activity), t.activity_code "
                                                        + " FROM activity_type t WHERE t.activity NOT IN "
                                                        + " (SELECT DISTINCT(t.activity) "
                                                        + " FROM milestone_act_map ma, proj_milestone_act pm, activity_type t "
                                                        + " WHERE pm.milestone_act_code=ma.milestone_act_code "
                                                        + " AND ma.activity_code=t.activity_code AND ma.milestone_act_code='"+ milestoneId + "' AND stage_code='"+ stageCode + "' GROUP BY(t.activity)) ORDER BY (t.activity)";

                    rs = st.executeQuery(unMapActivityNames_query);

                    while (rs.next()) {
                    ManageMilestonesVO activityNames = new ManageMilestonesVO();

                    if (rs.getString(1) != null) {
                        activityNames.setActivityUnmapName(rs.getString(1));
                    } else {
                        activityNames.setActivityUnmapName("");
                    }

                    if (rs.getString(2)  != null) {
                        activityNames.setActivityUnmapCode(rs.getString(2));
                    } else {
                        activityNames.setActivityUnmapCode("");
                    }

                     unMapActivityNameList.add(activityNames);
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
            return unMapActivityNameList;
        }

    public void setActivityNames(ManageMilestonesVO manageMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

             String milestoneCodeParam = manageMilestonesVO.getMilestoneCode();
             String activityCode = manageMilestonesVO.getActivityMapCode();
             String stageCode = manageMilestonesVO.getStageCode();

             String set_activity_names_query = "INSERT INTO milestone_act_map(milestone_act_code,activity_code,stage_code) VALUES ("+milestoneCodeParam+",'"+activityCode+"' ,'"+stageCode+"')";
             //System.out.println(set_activity_names_query);
             st.executeUpdate(set_activity_names_query);
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

    public void delActivityNames(ManageMilestonesVO manageMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

             String milestoneActId =manageMilestonesVO.getMilestoneActId();
             String milestoneStageCode = manageMilestonesVO.getStageCode();
             String milestoneCodeParam = manageMilestonesVO.getMilestoneCode();
             

             String set_activity_names_query = "DELETE FROM milestone_act_map WHERE milestone_act_id='"+ milestoneActId+"' and stage_code='"+ milestoneStageCode + "' AND milestone_act_code=" + milestoneCodeParam +" " ;
             //System.out.println(set_activity_names_query);
             st.executeUpdate(set_activity_names_query);
             //System.out.println("Successfully");
        } catch (SQLException e) {
                System.out.println("SQLException : "+e.toString());
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


    // The Following Function is used for to retrive Mapped and UnMapped milestones
    public ManageMilestonesVO GetStageMilestones(ManageMilestonesVO manageMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String stageCode = manageMilestonesVO.getStageCode();
        List mappedMilestoneId = new ArrayList();
        List unMappedMilestoneId = new ArrayList();
        List mappedMilestoneName = new ArrayList();
        List unMappedMilestoneName = new ArrayList();
        List mappedMilestoneOrder = new ArrayList();

        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String milestoneNames_query = "SELECT m.milestone_act_code, m.milestone_act_name, o.milestone_order_number "
                    + "FROM milestone_order o, proj_milestone_act m, project_stage s "
                    + "WHERE o.stage_code=s.stage_code AND o.milestone_act_code=m.milestone_act_code AND o.stage_code='"+stageCode+"' "
                    + "AND standard_milestone_flag='0' "
                    + "GROUP BY o.milestone_act_code ORDER BY o.milestone_order_number";

            rs = st.executeQuery(milestoneNames_query);

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    mappedMilestoneId.add(rs.getString(1));
                } else {
                    mappedMilestoneId.add("");
                }
                if (rs.getString(2)  != null) {
                    mappedMilestoneName.add(rs.getString(2));
                } else {
                    mappedMilestoneName.add("");
                }
                if (rs.getString(3)  != null) {
                    mappedMilestoneOrder.add(rs.getString(3));
                } else {
                    mappedMilestoneOrder.add("");
                }
            }

            milestoneNames_query = "Select milestone_act_code, milestone_act_name FROM proj_milestone_act WHERE milestone_act_code NOT IN ("
                    + "SELECT m.milestone_act_code FROM milestone_order o, proj_milestone_act m, project_stage s "
                    + "WHERE o.stage_code=s.stage_code AND o.milestone_act_code=m.milestone_act_code "
                    + "AND o.stage_code='"+stageCode+"')";

            rs = st.executeQuery(milestoneNames_query);

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    unMappedMilestoneId.add(rs.getString(1));
                } else {
                    unMappedMilestoneId.add("");
                }
                if (rs.getString(2)  != null) {
                    unMappedMilestoneName.add(rs.getString(2));
                } else {
                    unMappedMilestoneName.add("");
                }
            }

            manageMilestonesVO.setMappedMilestoneId(mappedMilestoneId);
            manageMilestonesVO.setMappedMilestoneName(mappedMilestoneName);
            manageMilestonesVO.setUnMappedMilestoneId(unMappedMilestoneId);
            manageMilestonesVO.setUnMappedMilestoneName(unMappedMilestoneName);
            manageMilestonesVO.setMappedMilestoneOrder(mappedMilestoneOrder);

        } catch (SQLException sqle) {
            System.out.println("SQLException : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException : "+npe);
        } finally {
            try {
                    rs.close();
                    st.close();
                    con.close();
                } catch (SQLException sqle) {
                    System.out.println("SQLException : "+sqle);
                } catch (NullPointerException npe) {
                    System.out.println("NullPointerException : "+npe);
            }
        }
        return manageMilestonesVO;
    }

    public static void AllocateStageMilestone(ManageMilestonesVO manageMilestonesVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        int result = 0;
        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String stageCode = manageMilestonesVO.getStageCode();
            List allocateMilestoneList = new ArrayList();
            allocateMilestoneList = manageMilestonesVO.getMappedMilestoneId();
            for(int idx=0; idx<allocateMilestoneList.size(); idx++) {
                String allocateMilestoneQuery = "INSERT INTO milestone_order "
                        + "(stage_code, milestone_act_code, milestone_order_number) VALUES "
                        + "('"+stageCode+"','"+allocateMilestoneList.get(idx).toString()+"',"
                            + "(SELECT CASE WHEN MAX(mo.milestone_order_number) IS NULL THEN '1' ELSE MAX(mo.milestone_order_number)+1 END "
                            + "	FROM milestone_order mo WHERE mo.stage_code='"+stageCode+"'))";
                result += st.executeUpdate(allocateMilestoneQuery);
            }
            if(result == allocateMilestoneList.size()) {
                result = 1;
            } else {
                result = 0;
            }
            manageMilestonesVO.setResult(result);
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException : " + e);
            } catch (Exception e) {
                System.out.println("NullPointerException : " + e);
            }
        }
        return;
    }
    
    public static void UnAllocateStageMilestone(ManageMilestonesVO manageMilestonesVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        int result = 0;
        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String stageCode = manageMilestonesVO.getStageCode();
            List unAllocateMilestoneList = new ArrayList();
            unAllocateMilestoneList = manageMilestonesVO.getUnMappedMilestoneId();
            for(int idx=0; idx<unAllocateMilestoneList.size(); idx++) {
                String unAllocateMilestoneQuery = "DELETE FROM milestone_order WHERE stage_code='"+stageCode+"' AND milestone_act_code='"+unAllocateMilestoneList.get(idx).toString()+"'";
                result += st.executeUpdate(unAllocateMilestoneQuery);
            }
            if(result == unAllocateMilestoneList.size()) {
                result = 1;
            } else {
                result = 0;
            }
            manageMilestonesVO.setResult(result);
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException : " + e);
            } catch (Exception e) {
                System.out.println("NullPointerException : " + e);
            }
        }
        return;
    }

    public static void RedefineMilestoneOrder(ManageMilestonesVO manageMilestonesVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        int result = 0;
        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String stageCode = manageMilestonesVO.getStageCode();
            List milestoneIdList = new ArrayList();
            List milestoneOrderList = new ArrayList();
            milestoneIdList = manageMilestonesVO.getMappedMilestoneId();
            milestoneOrderList = manageMilestonesVO.getMappedMilestoneOrder();
            for(int idx=0; idx<milestoneIdList.size(); idx++) {
                String orderMilestoneQuery = "UPDATE milestone_order SET milestone_order_number='"+milestoneOrderList.get(idx).toString()+"' "
                        + "WHERE stage_code='"+stageCode+"' AND milestone_act_code='"+milestoneIdList.get(idx).toString()+"'";
                result += st.executeUpdate(orderMilestoneQuery);
            }
            if(result == milestoneIdList.size()) {
                result = 1;
            } else {
                result = 0;
            }
            manageMilestonesVO.setResult(result);
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException : " + e);
            } catch (Exception e) {
                System.out.println("NullPointerException : " + e);
            }
        }
        return;
    }
}



/*
       //The Following Function for selecting the milestones as per the stages.
    public List getManageMilestones(ManageMilestonesVO manageMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List manageMilestonesList = new ArrayList();

        //String stageIdParam = manageMilestonesVO.getStageCode();

        try
        {
                con = dbconnection.getSampleProperty();
                st = con.createStatement();

                String stageCodeParam = manageMilestonesVO.getStageCode();

                String manage_milestone_query = "SELECT milestone_act_code,milestone_act_name "
                                                +"FROM proj_milestone_act "
                                                +"WHERE milestone_act_code IN("
                                                +"SELECT DISTINCT(`milestone_act_code`) FROM `milestone_act_map` WHERE `stage_code`='"+stageCodeParam+"')";

                rs = st.executeQuery(manage_milestone_query);

            while (rs.next()) {
                ManageMilestonesVO manageMilestones = new ManageMilestonesVO();

                if (rs.getString(1) != null) {
                    manageMilestones.setMilestoneCode(rs.getString(1));
                } else {
                    manageMilestones.setMilestoneCode("");
                }

                if (rs.getString(2)  != null) {
                    manageMilestones.setMilestoneName(rs.getString(2));
                } else {
                    manageMilestones.setMilestoneName("");
                }
                 manageMilestonesList.add(manageMilestones);
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
        return manageMilestonesList;
    }
    public List getManageActivity(ManageMilestonesVO manageMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List manageActivityList = new ArrayList();

        //String milestoneIdParam = manageMilestonesVO.getMilestoneCode();

        try
        {
                con = dbconnection.getSampleProperty();
                st = con.createStatement();

                String milestoneCodeParam = manageMilestonesVO.getMilestoneCode();

                String manage_activity_query = "SELECT t.activity, pm.milestone_act_code "
                                                +"FROM milestone_act_map ma, proj_milestone_act pm, activity_type t"
                                                +"WHERE pm.milestone_act_code=ma.milestone_act_code "
                                                +"AND ma.activity_code=t.activity_code AND ma.milestone_act_code='" + milestoneCodeParam+ "'";

                rs = st.executeQuery(manage_activity_query);

            while (rs.next()) {
                ManageMilestonesVO manageActivity = new ManageMilestonesVO();

                if (rs.getString(1) != null) {
                    manageActivity.setActivityName(rs.getString(1));
                } else {
                    manageActivity.setActivityName("");
                }

                if (rs.getString(2)  != null) {
                    manageActivity.setActivityCode(rs.getString(2));
                } else {
                    manageActivity.setActivityCode("");
                }
                 manageActivityList.add(manageActivity);
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
        return manageActivityList;
    }
*/