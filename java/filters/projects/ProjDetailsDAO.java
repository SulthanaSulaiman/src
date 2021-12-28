/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters.projects;

/**
 *
 * @author rajac
 */
import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class ProjDetailsDAO implements Serializable {

    public List getBookMappedChapEstDet(ProjDetailsVO projBookMapRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projectId = "";
        String bookMapchap = "";
        List chapterEstDet = new ArrayList();

        ProjDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String chapterEstQry = " SELECT pro.proj_id,ch.chapter_no,ch.est_pages "
                    + " FROM projects pro,chapter ch,project_bookmap pbm "
                    + " WHERE pro.proj_id=? AND ch.proj_id=pro.proj_id and ch.proj_id=pbm.proj_id AND TRIM(ch.chapter_no)=? and "
                    + " TRIM(ch.chapter_no)=TRIM(pbm.chapter_no) AND ch.stage='CTOF' AND ch.chapter_deleted='0' ";

            projectId = projBookMapRqst.getProjIdParam();
            bookMapchap = projBookMapRqst.getProjChapName();
            stmt = con.prepareStatement(chapterEstQry);
            stmt.setString(1, projectId);
            stmt.setString(2, bookMapchap);

            rs = stmt.executeQuery();

            response = new ProjDetailsVO();

            if (!rs.next()) {
                response.setProjIdParam("-");
                response.setProjChapName("-");
                response.setProjChapEstPages("-");

                chapterEstDet.add(response);
                
            } else {
                do {
                    if (rs.getString(1) != null) {
                        response.setProjIdParam(rs.getString(1));
                    } else {
                        response.setProjIdParam("-");
                    }

                    if (rs.getString(2) != null) {
                        response.setProjChapName(rs.getString(2));
                    } else {
                        response.setProjChapName("-");
                    }

                    if (rs.getString(3) != null) {
                        response.setProjChapEstPages(rs.getString(3));
                    } else {
                        response.setProjChapEstPages("-");
                    }

                    chapterEstDet.add(response);
                } while (rs.next());
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
        return chapterEstDet;
    }
    
    public String getProjectId(String projectId){
        Connection con = null;
        DBconnection dbcon = new DBconnection();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT (CASE WHEN ePace_id IS NULL THEN proj_id ELSE ePace_id END) AS proj_id FROM projects WHERE proj_id = '"+projectId+"'");
            while(rs.next())
                projectId = rs.getString(1);
            
        }catch(Exception e){
            System.out.println(e.toString());
        }
        finally {
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
        return projectId;
    }

    public List getChapActualPageDet(ProjDetailsVO projActPageRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projectId = "";
        String bookMapchap = "";
        List chapterActualPageDet = new ArrayList();

        ProjDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String chapterActualPgeQry = " SELECT ch.chapter_id,ch.chapter_no,ch.proof_page "
                    + " FROM projects pro,chapter ch "
                    + " WHERE pro.proj_id=? AND ch.proj_id=pro.proj_id AND TRIM(ch.chapter_no)=? "
                    + " AND ch.chapter_deleted='0' AND ch.stage='FP' AND ship_date IS NOT NULL ORDER BY ch.chapter_id DESC LIMIT 1 ";

            projectId = projActPageRqst.getProjIdParam();
            bookMapchap = projActPageRqst.getProjChapName();
            stmt = con.prepareStatement(chapterActualPgeQry);
            stmt.setString(1, projectId);
            stmt.setString(2, bookMapchap);

            rs = stmt.executeQuery();

            response = new ProjDetailsVO();

            if (!rs.next()) {
                response.setProjChapId("-");
                response.setProjChapName("-");
                response.setProjChapActualPages("-");

                chapterActualPageDet.add(response);

            } else {
                do {
                    if (rs.getString(1) != null) {
                        response.setProjChapId(rs.getString(1));
                    } else {
                        response.setProjChapId("-");
                    }

                    if (rs.getString(2) != null) {
                        response.setProjChapName(rs.getString(2));
                    } else {
                        response.setProjChapName("-");
                    }

                    if (rs.getString(3) != null) {
                        response.setProjChapActualPages(rs.getString(3));
                    } else {
                        response.setProjChapActualPages("-");
                    }

                    chapterActualPageDet.add(response);
                } while (rs.next());
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
        return chapterActualPageDet;
    }

    public List getChapterStageDetails(ProjDetailsVO projStgRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projectId = "";
        List chapterStageDet = new ArrayList();

        ProjDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String chapterStageQry = " SELECT ch.stage,ps.stage "
                    + " FROM projects pro,chapter ch,project_stage ps "
                    + " WHERE ch.proj_id=? AND ch.proj_id=pro.proj_id AND ch.stage=ps.stage_code group by ch.stage order by ch.added_date ";

            projectId = projStgRqst.getProjIdParam();
            stmt = con.prepareStatement(chapterStageQry);
            stmt.setString(1, projectId);

            rs = stmt.executeQuery();

            while (rs.next()) {

                response = new ProjDetailsVO();

                if (rs.getString(1) != null) {
                    response.setProjChapStgCode(rs.getString(1));
                } else {
                    response.setProjChapStgCode("");
                }

                if (rs.getString(2) != null) {
                    response.setProjChapStgName(rs.getString(2));
                } else {
                    response.setProjChapStgName("");
                }

                chapterStageDet.add(response);
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
        return chapterStageDet;
    }

    public List getStageChaptersDet(ProjDetailsVO projStgChapRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projectId = "";
        String projectStgCode = "";
        List projStageChapList = new ArrayList();

        ProjDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String stageChapterQry = " SELECT ch.chapter_id,ch.chapter_no,ps.stage,DATE_FORMAT(ch.added_date,'%d-%b-%Y %h:%i %p') "
                    + " FROM projects pro,chapter ch,project_stage ps "
                    + " WHERE ch.proj_id=? AND ch.proj_id=pro.proj_id AND ch.stage=ps.stage_code AND ch.stage=? ORDER BY ch.added_date ";

            projectId = projStgChapRqst.getProjIdParam();
            projectStgCode = projStgChapRqst.getProjChapStgCode();
            stmt = con.prepareStatement(stageChapterQry);
            stmt.setString(1, projectId);
            stmt.setString(2, projectStgCode);

            rs = stmt.executeQuery();

            while (rs.next()) {

                response = new ProjDetailsVO();

                if (rs.getString(1) != null) {
                    response.setProjChapId(rs.getString(1));
                } else {
                    response.setProjChapId("");
                }

                if (rs.getString(2) != null) {
                    response.setProjChapName(rs.getString(2));
                } else {
                    response.setProjChapName("");
                }

                if (rs.getString(3) != null) {
                    response.setProjChapStgName(rs.getString(3));
                } else {
                    response.setProjChapStgName("");
                }

                if (rs.getString(4) != null) {
                    response.setProjStgChapAddedDate(rs.getString(4));
                } else {
                    response.setProjStgChapAddedDate("");
                }

                projStageChapList.add(response);
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
        return projStageChapList;
    }

    public List getChapterMileSoneDet(ProjDetailsVO projChapMilestoneRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String chapterId = "";
        String stageCode = "";
        List chapMileStoneList = new ArrayList();

        ProjDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String chapMileStoneQry = " SELECT b.chapter_id,b.milestone_id,c.milestone_act_name,DATE_FORMAT(b.end_date, '%Y-%m-%d'),"
                    + " b.planned_date,mo.milestone_order_number "
                    + " FROM chapter a,chapter_plan b,proj_milestone_act c,milestone_order mo,project_stage ps "
                    + " WHERE a.chapter_id=b.chapter_id AND b.milestone_id=mo.milestone_act_code AND b.chapter_id=? and mo.stage_code=? "
                    + " and mo.milestone_act_code=c.milestone_act_code and ps.stage_code=mo.stage_code "
                    + " group by b.milestone_id ORDER BY mo.milestone_order_number ";

            chapterId = projChapMilestoneRqst.getProjChapId();
            stageCode = projChapMilestoneRqst.getProjChapStgCode();
            stmt = con.prepareStatement(chapMileStoneQry);
            stmt.setString(1, chapterId);
            stmt.setString(2, stageCode);

            rs = stmt.executeQuery();

            while (rs.next()) {

                response = new ProjDetailsVO();

                if (rs.getString(1) != null) {
                    response.setProjChapId(rs.getString(1));
                } else {
                    response.setProjChapId("");
                }

                if (rs.getString(2) != null) {
                    response.setChapMileStoneId(rs.getString(2));
                } else {
                    response.setChapMileStoneId("");
                }

                if (rs.getString(3) != null) {
                    response.setChapMileStoneName(rs.getString(3));
                } else {
                    response.setChapMileStoneName("");
                }

                if (rs.getString(4) != null) {
                    response.setChapMileStoneEndDate(rs.getString(4));
                } else {
                    response.setChapMileStoneEndDate("");
                }

                chapMileStoneList.add(response);
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
        return chapMileStoneList;
    }

    public List getChapterActivityDet(ProjDetailsVO projChapActivityRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String chapterId = "";
        List chapActivityList = new ArrayList();

        ProjDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String chapActivityQry = " SELECT a.emp_name,e.department,f.designation,d.activity,g.stage,c.proof_pages,DATE_FORMAT(c.begin_time,'%d-%b-%Y %r'),"
                    + " DATE_FORMAT(c.end_time,'%d-%b-%Y %r'),TIME_TO_SEC(TIMEDIFF(c.end_time,c.begin_time)),c.remarks,b.stage,b.chapter_id,"
                    + " d.activity_code,f.desig_code,c.activity_comp_flag "
                    + " FROM user a,chapter b,activity c,activity_type d,department e,designation f,project_stage g "
                    + " WHERE c.chapter_id=? AND a.emp_id=c.emp_id AND c.activity_code=d.activity_code AND c.chapter_id=b.chapter_id AND "
                    + " e.dept_code=a.dept_code AND a.desig_code=f.desig_code AND g.stage_code=b.stage ";

            chapterId = projChapActivityRqst.getProjChapId();
            stmt = con.prepareStatement(chapActivityQry);
            stmt.setString(1, chapterId);

//            System.out.println(" SELECT a.emp_name,e.department,f.designation,d.activity,g.stage,c.proof_pages,DATE_FORMAT(c.begin_time,'%d-%b-%Y %r'),"
//                    + " DATE_FORMAT(c.end_time,'%d-%b-%Y %r'),TIME_TO_SEC(TIMEDIFF(c.end_time,c.begin_time)),c.remarks,b.stage,b.chapter_id,"
//                    + " d.activity_code,f.desig_code,c.activity_comp_flag "
//                    + " FROM user a,chapter b,activity c,activity_type d,department e,designation f,project_stage g "
//                    + " WHERE c.chapter_id=? AND a.emp_id=c.emp_id AND c.activity_code=d.activity_code AND c.chapter_id=b.chapter_id AND "
//                    + " e.dept_code=a.dept_code AND a.desig_code=f.desig_code AND g.stage_code=b.stage ");

            rs = stmt.executeQuery();

            while (rs.next()) {

                response = new ProjDetailsVO();

                if (rs.getString(1) != null) {
                    response.setChapActivityEmpName(rs.getString(1));
                } else {
                    response.setChapActivityEmpName("");
                }

                if (rs.getString(2) != null) {
                    response.setChapActivityDept(rs.getString(2));
                } else {
                    response.setChapActivityDept("");
                }

                if (rs.getString(3) != null) {
                    response.setChapActivityDesig(rs.getString(3));
                } else {
                    response.setChapActivityDesig("");
                }

                if (rs.getString(4) != null) {
                    response.setChapActivityName(rs.getString(4));
                } else {
                    response.setChapActivityName("");
                }

                if (rs.getString(5) != null) {
                    response.setChapActivityStage(rs.getString(5));
                } else {
                    response.setChapActivityStage("");
                }

                if (rs.getString(6) != null) {
                    response.setChapActivityProofPage(rs.getString(6));
                } else {
                    response.setChapActivityProofPage("");
                }

                if (rs.getString(7) != null) {
                    response.setChapActivityStartTime(rs.getString(7));
                } else {
                    response.setChapActivityStartTime("");
                }

                if (rs.getString(8) != null) {
                    response.setChapActivityEndTime(rs.getString(8));
                } else {
                    response.setChapActivityEndTime("");
                }

                if (rs.getString(9) != null) {
                    response.setChapActivityTotalTime(rs.getString(9));
                } else {
                    response.setChapActivityTotalTime("");
                }

                if (rs.getString(10) != null) {
                    response.setChapActivityRemarks(rs.getString(10));
                } else {
                    response.setChapActivityRemarks("");
                }

                if (rs.getString(15) != null) {
                    response.setChapActivityCompFlag(rs.getString(15));
                } else {
                    response.setChapActivityCompFlag("");
                }

                chapActivityList.add(response);
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
        return chapActivityList;
    }

    public List getProjMessageDetails(ProjDetailsVO projMsgRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projId = "";
        List projMessageList = new ArrayList();

        ProjDetailsVO response = null;
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {

            con = dbcon.getSampleProperty();

            String projMsgQry = " SELECT parent_id,msg_id,CONCAT(g.company,'/',divi.company),prj_id,msg_title,msg_body,DATE_FORMAT(msg_postdate,'%d-%b-%Y %r'),"
                    + " msg_typecode,msg_flagcode,msg_empID,projects.proj_name,u.emp_name "
                    + " FROM messages,projects,contacts g,contacts divi,user u "
                    + " WHERE messages.prj_id=projects.proj_id AND parent_id=0 AND messages.prj_id=? AND g.contact_id=projects.client_name "
                    + " AND divi.contact_id=projects.division_id AND u.emp_id=messages.msg_empID ORDER BY messages.msg_postdate DESC ";

            projId = projMsgRqst.getProjIdParam();
            stmt = con.prepareStatement(projMsgQry);
            stmt.setString(1, projId);

            rs = stmt.executeQuery();

            while (rs.next()) {

                response = new ProjDetailsVO();

                if (rs.getString(2) != null) {
                    response.setProjMsgId(rs.getString(2));
                } else {
                    response.setProjMsgId("");
                }

                if (rs.getString(3) != null) {
                    response.setProjClientName(splChar.decoding(rs.getString(3)));
                } else {
                    response.setProjClientName("");
                }

                if (rs.getString(5) != null) {
                    response.setProjMsgTitle(rs.getString(5));
                } else {
                    response.setProjMsgTitle("");
                }

                if (rs.getString(6) != null) {
                    response.setProjMsgBody(rs.getString(6));
                } else {
                    response.setProjMsgBody("");
                }

                if (rs.getString(7) != null) {
                    response.setProjMsgPostedDate(rs.getString(7));
                } else {
                    response.setProjMsgPostedDate("");
                }

                if (rs.getString(10) != null) {
                    response.setProjMsgPostedEmpId(rs.getString(10));
                } else {
                    response.setProjMsgPostedEmpId("");
                }

                if (rs.getString(12) != null) {
                    response.setProjMsgPostedEmpName(rs.getString(12));
                } else {
                    response.setProjMsgPostedEmpName("");
                }

                projMessageList.add(response);
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
        return projMessageList;
    }

    public List getProjFTPSrvIDDet(ProjDetailsVO projFTPSrvRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projId = "";
        List projFTPSrvIDList = new ArrayList();

        ProjDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String projFTPSrvIDQry = " select p.proj_id,ftp.ftp_serverid from projects p,ftpserver ftp where p.proj_id=? and ftp.ftp_serverid=p.ftp_serverid ";

            projId = projFTPSrvRqst.getProjIdParam();
            stmt = con.prepareStatement(projFTPSrvIDQry);
            stmt.setString(1, projId);

            rs = stmt.executeQuery();

            while (rs.next()) {

                response = new ProjDetailsVO();

                if (rs.getString(2) != null) {
                    response.setProjFTPServerId(rs.getString(2));
                } else {
                    response.setProjFTPServerId("");
                }

                projFTPSrvIDList.add(response);
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
        return projFTPSrvIDList;
    }

    public List getProjFTPSrvInfoDet(ProjDetailsVO projFTPSrvInfoRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projFTPId = "";
        List projFTPSrvInfoList = new ArrayList();

        ProjDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String projFTPSrvInfoQry = " select ftp_serverid,ftp_servername,ftp_server,ftp_user,ftp_pwd,ftp_path from ftpserver where ftp_serverid=? ";

            projFTPId = projFTPSrvInfoRqst.getProjFTPServerId();
            stmt = con.prepareStatement(projFTPSrvInfoQry);
            stmt.setString(1, projFTPId);

            rs = stmt.executeQuery();

            while (rs.next()) {

                response = new ProjDetailsVO();

                if (rs.getString(2) != null) {
                    response.setProjFTPServerName(rs.getString(2));
                } else {
                    response.setProjFTPServerName("");
                }

                if (rs.getString(3) != null) {
                    response.setProjFTPServerIP(rs.getString(3));
                } else {
                    response.setProjFTPServerIP("");
                }

                if (rs.getString(4) != null) {
                    response.setProjFTPServerUserName(rs.getString(4));
                } else {
                    response.setProjFTPServerUserName("");
                }

                if (rs.getString(5) != null) {
                    response.setProjFTPServerPwd(rs.getString(5));
                } else {
                    response.setProjFTPServerPwd("");
                }

                if (rs.getString(6) != null) {
                    response.setProjFTPServerPath(rs.getString(6));
                } else {
                    response.setProjFTPServerPath("");
                }

                projFTPSrvInfoList.add(response);
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
        return projFTPSrvInfoList;
    }
}
