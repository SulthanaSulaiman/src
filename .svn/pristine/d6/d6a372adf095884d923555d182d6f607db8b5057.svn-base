/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects.chaptersold;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Thanu
 */
public class ChapterPlanDetails_1 implements Serializable {

    /**
     * This function is used to get the chapter plan details for the given chapter id.
     *
     * @param planRqst value object for {@link ChapterPlanDetVO} <br>
     * @return <code> chapterPlan </code> -  List of ChapterPlanDetVO object which consist of list of chapter details.
     *
     * @exception SQLException
     * @see ChapterPlanDetVO
     */
    public List getChapterPlanDet(ChapterPlanDetVO planRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String chapterId = "";
        List chapterPlan = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterPlanDetVO response = null;


        try {

            con = dbcon.getSampleProperty();

            String chapterDetQry = " SELECT b.chapter_id,a.chapter_no,b.plan_id,b.milestone_id,c.stage,b.end_date,b.planned_date "
                    + " FROM chapter a,chapter_plan b,project_stage c "
                    + " WHERE a.chapter_id=b.chapter_id AND b.milestone_id=c.stage_code AND b.chapter_id=? ";

            chapterId = planRqst.getChapterId();
            stmt = con.prepareStatement(chapterDetQry);
            stmt.setString(1, chapterId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                response = new ChapterPlanDetVO();
                response.setChapterId(rs.getString(1));
                response.setChapterName(rs.getString(2));
                response.setPlanId(rs.getString(3));
                response.setMileStoneId(rs.getString(4));
                response.setMileStoneName(rs.getString(5));
                response.setEndDate(rs.getString(6));
                response.setPlannedDate(rs.getString(7));
                chapterPlan.add(response);

            }

        } catch (SQLException sqle) {
            System.out.println("SQLException in getChapterPlanDet:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getChapterPlanDet:" + e);
        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }

        }

        return chapterPlan;

    }

    public List getPlannedChapters(ChapterPlanDetVO req)
    {
        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projId = "";
        List chapterPlan = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterPlanDetVO response = null;


        try {

            con = dbcon.getSampleProperty();

            String chapterDetQry = " SELECT DISTINCT(a.chapter_id),a.chapter_no "
                    + " FROM chapter a,chapter_plan b "
                    + " WHERE a.chapter_id=b.chapter_id and proj_id=?  ";

            projId = req.getProjectId();
            stmt = con.prepareStatement(chapterDetQry);
            stmt.setString(1, projId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                response = new ChapterPlanDetVO();
                response.setChapterId(rs.getString(1));
                response.setChapterName(rs.getString(2));
                
                chapterPlan.add(response);

            }

        } catch (SQLException sqle) {
            System.out.println("SQLException in getChapterPlanDet:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getChapterPlanDet:" + e);
        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }

        }

        return chapterPlan;




    }

    /**
     * This function is used to save chapter plan details.
     *
     * @param rqstList which contains objects of  value object for {@link ChapterPlanDetVO} <br>
     * @return <code> addChapterPlan </code> -  Returns the number of records inserted.
     *
     * @exception SQLException
     * @see ChapterPlanDetVO
     */
    public int savePlan(List rqstList) {

        Connection con = null;
        PreparedStatement statement = null;
        int addChapterPlan = 0;
        ChapterPlanDetVO rqstVO = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            String saveQry = " INSERT into chapter_plan(plan_id,chapter_id,milestone_id,end_date,planned_Date) "
                    + " VALUES(?,?,?,?,CURRENT_TIMESTAMP)";

            statement = con.prepareStatement(saveQry);
            String planId = maxPlanId();
            String chapterId = "";
            String endDate = "";
            String mileStoneCode = "";


            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                rqstVO = (ChapterPlanDetVO) itr.next();
                //planId = rqstVO.getPlanId();
                chapterId = rqstVO.getChapterId();
                endDate = rqstVO.getEndDate();
                mileStoneCode = rqstVO.getMileStoneId();

                statement.setString(1, planId);
                statement.setString(2, chapterId);
                statement.setString(3, mileStoneCode);
                statement.setString(4, endDate);

                statement.executeUpdate();
                addChapterPlan++;


                System.out.println("saveQry:" + saveQry);



            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in creating Plan Creation:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in creating Plan Creation:" + e);
        } finally {
            //Close statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }
        }

        return addChapterPlan;
    }

        public int deletePlan(ChapterPlanDetVO rqst) {

        Connection con = null;
        Statement statement = null;
        int deleteChapterPlan = 0;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            String chap_MileStone = "";
            String chap_EndDate = "";
            statement = con.createStatement();
            String chapterId = rqst.getChapterId();
            String mileStoneCode = rqst.getMileStoneId();
            String planId = rqst.getPlanId();

           /* deleteChapterPlan = statement.executeUpdate("delete from  chapter_plan where milestone_id='" + mileStoneCode + "' "
                    + " and chapter_id='" + chapterId + "'   ");*/

             deleteChapterPlan = statement.executeUpdate("delete from  chapter_plan where chapter_id='" + chapterId + "' ");

            //System.out.println("addContact:"+addContact);
        } catch (SQLException sqle) {
            System.out.println("SQLException in creating Plan Creation:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in creating Plan Creation:" + e);
        } finally {
            //Close statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }

        }
        return deleteChapterPlan;

    }

   
    //For copy pass the req to getChapterPlanDet. get the arraylist of object and loop that and find max plan id. now insert into table
    public int copyPlan(ChapterPlanDetVO planRqst) {

        int recordsAdded = 0;
        List chapters = new ArrayList();
        String chapterId = planRqst.getCopyChapterId();

        //pass the request list to getChapterPlanDet which will return the given chapters plan details.
        chapters = getChapterPlanDet(planRqst);


        //insert the records.
        Connection con = null;
        PreparedStatement statement = null;

        ChapterPlanDetVO rqstVO = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            String copyQry = " INSERT into chapter_plan(plan_id,chapter_id,milestone_id,end_date,planned_Date) "
                    + " VALUES(?,?,?,?,CURRENT_TIMESTAMP)";

            statement = con.prepareStatement(copyQry);
            String planId = maxPlanId();

            String endDate = "";
            String mileStoneCode = "";


            Iterator itr = chapters.iterator();
            while (itr.hasNext()) {
                rqstVO = (ChapterPlanDetVO) itr.next();
                //planId = rqstVO.getPlanId();
                //chapterId = rqstVO.getChapterId();
                endDate = rqstVO.getEndDate();
                mileStoneCode = rqstVO.getMileStoneId();

                statement.setString(1, planId);
                statement.setString(2, chapterId);
                statement.setString(3, mileStoneCode);
                statement.setString(4, endDate);
                statement.executeUpdate();
                recordsAdded++;


                System.out.println("copyQry:" + copyQry);



            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in copyPlan " + sqle);
        } catch (Exception e) {
            System.out.println("Exception in copyPlan:" + e);
        } finally {
            //Close statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }
        }


        return recordsAdded;

    }

    /* public void modifyPlan(ChapterPlanDetVO rqst) {

    Connection con = null;
    Statement statement = null;
    try {
    DBconnection dbcon = new DBconnection();
    con = dbcon.getSampleProperty();
    String chap_MileStone = "";
    String chap_EndDate = "";
    statement = con.createStatement();

    String chapterId = rqst.getChapterId();

    ResultSet rsChapPlanInfo = statement.executeQuery("select milestone_id,end_date from chapter_plan where chapter_id='" + chapterId + "' ");
    while (rsChapPlanInfo.next()) {
    chap_MileStone = rsChapPlanInfo.getString("milestone_id");
    if (rsChapPlanInfo.wasNull()) {
    chap_MileStone = "";
    }
    chap_EndDate = rsChapPlanInfo.getString("end_date");
    if (rsChapPlanInfo.wasNull()) {
    chap_EndDate = "";
    }
    }

    if (!chap_MileStone.equals("") && !chap_MileStone.equals(mileStoneCode)) {

    addChapterPlan = statement.executeUpdate("update chapter_plan set milestone_id='" + mileStoneCode + "' "
    + " where chapter_id='" + chapterId + "'   ");
    }

    if (!endDate.equals("") && !endDate.equals(mileStoneCode)) {

    addChapterPlan = statement.executeUpdate("update chapter_plan set end_date='" + endDate + "' "
    + " where chapter_id='" + chapterId + "'   ");
    }

    //System.out.println("addContact:"+addContact);
    } catch (SQLException sqle) {
    System.out.println("SQLException in creating Plan Creation:" + sqle);
    } catch (Exception e) {
    System.out.println("Exception in creating Plan Creation:" + e);
    } finally {
    //Close connection
    if (con != null) {
    //Close statement
    if (statement != null) {
    try {
    statement.close();
    } catch (SQLException se) {
    System.out.println("Exception while closing Statement:se.getMessage()");
    }
    }

    //Close connection
    if (con != null) {
    try {
    con.close();
    } catch (SQLException se) {
    System.out.println("Exception while closing connection:se.getMessage()");
    }
    }
    }

    }

    } */

     public String maxPlanId() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String maxPlanId = "";
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();

            stmt = con.createStatement();
            rs = stmt.executeQuery("select max(plan_id+1) from chapter_plan");
            if (rs.next()) {
                maxPlanId = rs.getString(1);
                if (rs.wasNull()) {
                    maxPlanId = "0";
                }
            }


        } catch (SQLException sqle) {
            System.out.println("SQLException in maxPlanId:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in maxPlanId:" + e);
        } finally {
            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }


        }
        return maxPlanId;
    }

}
