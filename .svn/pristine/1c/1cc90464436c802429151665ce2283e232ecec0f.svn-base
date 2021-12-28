package pathfinder.functions.projects.chaptersold;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 * This file contains methods to get the chapter details and plan details.
 * @author Thanu
 */
public class ChapterDetails implements Serializable {

    /**
     * This function is used to get the chapter details for the given project id.
     *
     * @param chapRqst value object for {@link ChapterDetailsVO} <br>
     * @return <code> chapterDet </code> -  List of ChapterDetailsVO objects which consist of list of chapter details.
     *
     * @exception SQLException
     * @see ChapterDetailsVO
     */
    public List getChapterDetails(ChapterDetailsVO chapRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projectId = "";
        List chapterDet = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String chapterDetQry = " SELECT a.chapter_id,a.chapter_no,a.mss_count,b.stage,a.receipt_date,DATE_FORMAT(a.due_date,'%Y-%m-%d'), "
                    + " DATE_FORMAT(a.due_time,'%H:%i'),a.chapter_status,a.chapter_process,a.due_date_received,a.remark,a.stage "
                    + " FROM chapter a,project_stage b "
                    + " WHERE a.proj_id=? AND a.stage = b.stage_code AND (a.ship_date IS NULL OR a.ship_date = '0000-00-00 00:00:00') AND a.chapter_deleted='0' ORDER BY a.due_date";

            String chapterList = chapRqst.getChapters();
    System.out.println("chapList----:"+chapterList);
    if(chapterList!=null && !chapterList.equals(""))
        //chapterDetQry = chapterDetQry + " and a.chapter_id in "+chapterList;

            System.out.println("chapQry:"+chapterDetQry);
            projectId = chapRqst.getProjectId();
            stmt = con.prepareStatement(chapterDetQry);
            stmt.setString(1, projectId);
            System.out.println("chapterDetQry:"+chapterDetQry);

            rs = stmt.executeQuery();
            while (rs.next()) {
                response = new ChapterDetailsVO();
                response.setChapterId(rs.getString(1));
                response.setChapterName(rs.getString(2));
                response.setMssPages(rs.getString(3));
                response.setStage(rs.getString(4));

                if(rs.getString(5)!=null)
                    response.setCreateDate(rs.getString(5));
                else
                    response.setCreateDate("-");

                if(rs.getString(6)!=null)
                    response.setDueDate(rs.getString(6));
                else
                    response.setDueDate("-");

                if(rs.getString(7)!=null)
                    response.setDueTime(rs.getString(7));
                else
                    response.setDueTime("-");

                response.setChapProcess(rs.getString(9));
                response.setChapdueFlag(rs.getString(10));

                if(rs.getString(11)!=null)
                    response.setChapRemark(rs.getString(11));
                else
                    response.setChapRemark("");

                response.setStageCode(rs.getString(12));

                chapterDet.add(response);
            }

        } catch (SQLException sqle) {
            System.out.println("SQLException in getChapterDetails:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getChapterDetails:" + e);
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

        return chapterDet;

    }

    public List getMilestoneDetails(ChapterDetailsVO milestoneRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String StageCode = "";
        List milestoneDet = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterDetailsVO milestoneresponse = null;

        try {

            con = dbcon.getSampleProperty();

            String milestoneDetQry = " SELECT ps.stage,ps.stage_code,pma.milestone_act_code,pma.milestone_act_name "
                    + " FROM project_stage ps,milestone_stage_map msm,proj_milestone_act pma "
                    + " WHERE pma.milestone_act_status='1' AND ps.stage_code=msm.stage_code AND msm.milestone_act_code=pma.milestone_act_code AND "
                    + " ps.stage_code=? ";

            StageCode = milestoneRqst.getMilestoneStageCode();
            stmt = con.prepareStatement(milestoneDetQry);
            stmt.setString(1, StageCode);
            System.out.println("milestoneDetQry:"+milestoneDetQry);

            rs = stmt.executeQuery();
            while (rs.next()) {
                milestoneresponse = new ChapterDetailsVO();
                milestoneresponse.setMilestoneCode(rs.getString(3));
                milestoneresponse.setMilestoneName(rs.getString(4));

                milestoneDet.add(milestoneresponse);
            }

        } catch (SQLException sqle) {
            System.out.println("SQLException in getChapterDetails:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getChapterDetails:" + e);
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

        return milestoneDet;

    }


     /* This function is used to save cast off details.
     *
     * @param rqstList which contains objects of  value object for {@link ChapterDetailsVO} <br>
     * @return <code> addCastOff </code> -  Returns the number of records inserted.
     *
     * @exception SQLException
     * @see ChapterDetailsVO
      * */


    public int saveCastOffDet(List rqstList) {

        Connection con = null;
        PreparedStatement statement = null;
        int addCastOff = 0;
        ChapterDetailsVO rqstVO = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            String saveQry = " update chapter set est_pages=?,est_pages_date=current_timestamp,est_pages_rmk=?,est_pages_empid=?"+
                             " where chapter_id=? ";

            statement = con.prepareStatement(saveQry);

            String chapterId = "";
            String estPages="";
            String estRmk="";
            String estEmpId="";




            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                rqstVO = (ChapterDetailsVO) itr.next();
                chapterId = rqstVO.getChapterId();
                estPages = rqstVO.getEstPages();
                estRmk=rqstVO.getCastOffRmks();
                estEmpId=rqstVO.getCastOffEmpId();

                statement.setString(1, estPages);
                statement.setString(2, estRmk);
                statement.setString(3, estEmpId);
                statement.setString(4, chapterId);

                statement.executeUpdate();
                addCastOff++;


                System.out.println("saveQry:" + saveQry);



            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in adding castOff details:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in adding castOff details:" + e);
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

        return addCastOff;
    }

}
