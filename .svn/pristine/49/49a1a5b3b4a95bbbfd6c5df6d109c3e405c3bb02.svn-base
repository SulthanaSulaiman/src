package pathfinder.functions.projects.chapters;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

public class ChapterAddDetails implements Serializable {
private String projMappedLocation = "";
private String projId="";
private String stageforLocation="";

    public List getChapterDetails(ChapterAddDetailsVO chapRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projectId = "";
        List chapterDet = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterAddDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String chapterDetQry = " SELECT pbm.proj_id,pbm.chapter_id,pbm.chapter_no,pbm.cast_off_pages,pbm.total_pages, pbm.mss_page_count, pbm.artcount"
                    + " FROM project_bookmap pbm,projects p "
                    + " WHERE pbm.proj_id=? AND pbm.proj_id=p.proj_id order by pbm.sort_order";

            projectId = chapRqst.getProjectId();
            stmt = con.prepareStatement(chapterDetQry);
            stmt.setString(1, projectId);
            System.out.println("chapterDetQry:"+chapterDetQry);

            rs = stmt.executeQuery();
            while (rs.next()) {
                response = new ChapterAddDetailsVO();
                response.setProjectId(rs.getString(1) != null ? rs.getString(1).toString() : "");
                response.setChapterId(rs.getString(2) != null ? rs.getString(2).toString() : "");
                response.setChapterName(rs.getString(3) != null ? rs.getString(3).toString() : "");
                response.setCastOffPages(rs.getString(4) != null ? rs.getString(4).toString() : "");
                response.setTotalPages(rs.getString(5) != null ? rs.getString(5).toString() : "");
                response.setMssPages(rs.getString(6) != null ? rs.getString(6).toString() : "");
                response.setBookMapartcount(rs.getString(7) != null ? rs.getString(7).toString() : "");
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
public List getChapterDetails1(ChapterAddDetailsVO chapRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projectId = "";
        List chapterDet = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterAddDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String chapterDetQry = " SELECT pbm.chapter_no,pbm.total_pages "
                    + " FROM project_bookmap pbm "
                    + " WHERE pbm.proj_id=? order by pbm.sort_order";

            projectId = chapRqst.getProjectId();
            stmt = con.prepareStatement(chapterDetQry);
            stmt.setString(1, projectId);
            //System.out.println("chapterDetQry:"+chapterDetQry);

            rs = stmt.executeQuery();
            while (rs.next()) {
                response = new ChapterAddDetailsVO();
                //response.setProjectId(rs.getString(1) != null ? rs.getString(1).toString() : "");
                //response.setChapterId(rs.getString(2) != null ? rs.getString(2).toString() : "");
                response.setChapterName(rs.getString(1) != null ? rs.getString(1).toString() : "");
                //response.setCastOffPages(rs.getString(4) != null ? rs.getString(4).toString() : "");
                response.setTotalPages(rs.getString(2) != null ? rs.getString(2).toString() : "");
                //response.setMssPages(rs.getString(6) != null ? rs.getString(6).toString() : "");
                //response.setBookMapartcount(rs.getString(7) != null ? rs.getString(7).toString() : "");
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


    public List getWorkflowDetails(ChapterAddDetailsVO workflowRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projectId = "";
        List workflowDet = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterAddDetailsVO workflowresp = null;

        try {

            con = dbcon.getSampleProperty();

            String workflowDetQry = " SELECT proj_workflow, dept_code FROM projects WHERE proj_id=? ";

            projectId = workflowRqst.getProjectId();
            stmt = con.prepareStatement(workflowDetQry);
            stmt.setString(1, projectId);
            //System.out.println("chapterDetQry:"+workflowDetQry);

            rs = stmt.executeQuery();
            while (rs.next()) {
                workflowresp = new ChapterAddDetailsVO();

                if(rs.getString(1)!=null)
                  workflowresp.setProjWorkflowId(rs.getString(1));
                else
                  workflowresp.setProjWorkflowId("0");

                if(rs.getString(2)!=null) {
                  workflowresp.setProjDeptCode(rs.getString(2));
                } else {
                  workflowresp.setProjDeptCode("");
                }

                workflowDet.add(workflowresp);
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

        return workflowDet;

    }


    public List getStageDetails(ChapterAddDetailsVO stageRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String workflowId = "";
        List stageDet = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterAddDetailsVO stageresponse = null;

        try {

            con = dbcon.getSampleProperty();

            String stageDetQry = " SELECT pwmp.workflow_id,pwmp.milestone_code,ps.stage "
                    + " from project_workflow_milestone_map pwmp,project_workflow_master pwm,project_stage ps "
                    + " WHERE pwmp.workflow_id=? and pwmp.workflow_id=pwm.workflow_id and pwmp.milestone_code=ps.stage_code";


            workflowId = stageRqst.getProjWorkflowId();
            stmt = con.prepareStatement(stageDetQry);
            stmt.setString(1, workflowId);
            //System.out.println("chapterDetQry:"+stageDetQry);

            rs = stmt.executeQuery();
            while (rs.next()) {
                    stageresponse = new ChapterAddDetailsVO();
                    stageresponse.setProjWorkflowId(rs.getString(1));
                    stageresponse.setProjWorkflowStageCode(rs.getString(2));
                    stageresponse.setProjWorkflowStageName(rs.getString(3));

                    stageDet.add(stageresponse);
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

        return stageDet;

    }
 public List getStageDetails1(ChapterAddDetailsVO stageRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String workflowId = "";
        List stageDet = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterAddDetailsVO stageresponse = null;

        try {

            con = dbcon.getSampleProperty();

            String stageDetQry = " select stage,stage_code from project_stage";


            workflowId = stageRqst.getProjWorkflowId();
            stmt = con.prepareStatement(stageDetQry);
            //stmt.setString(1, workflowId);
            //System.out.println("chapterDetQry:"+stageDetQry);

            rs = stmt.executeQuery();
            while (rs.next()) {
                    stageresponse = new ChapterAddDetailsVO();
                    //stageresponse.setProjWorkflowId(rs.getString(1));
                    stageresponse.setProjWorkflowStageCode(rs.getString(1));
                    stageresponse.setProjWorkflowStageName(rs.getString(2));

                    stageDet.add(stageresponse);
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

        return stageDet;

    }
    public List getOnShoreOffShoreInfromation(){
        List locationType= new ArrayList();
        Connection con=null;
        Statement stmt = null;
        ResultSet rs = null;
        DBconnection dbcon = new DBconnection();

       try{
        con=dbcon.getSampleProperty();
        stmt=con.createStatement();
        rs = stmt.executeQuery("select Loction_type from onoffshore" );
        while (rs.next()){
       locationType.add(rs.getString(1));
             }
        
        }
       catch(SQLException e){
       }
        catch(Exception e){
        }
finally {

            //Close resultset
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }
        }
       return locationType;
    }
    public String getStagemappedToLocation(){
    String usedlocation="";
    Connection conn = null;
    Statement stmet =null;
    ResultSet rst = null;
     DBconnection dbcon1 = new DBconnection();
    try{
        conn = dbcon1.getSampleProperty();
        stmet = conn.createStatement();
        rst = stmet.executeQuery("select location from stage_handled_location where proj_id='"+ projId +"' and stage='"+stageforLocation+"'");
        while (rst.next())
        {
            //System.out.println(rst.getString(1));
projMappedLocation = rst.getString(1);

        }

    }
    catch(SQLException e){
    }
    catch(Exception e){
    }
     finally {

            //Close resultset
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }
        }
    // System.out.println(projMappedLocation);
return projMappedLocation;
        }
      public List getStageChapterPlan(){
    List usedstage=new ArrayList();
    Connection conn = null;
    Statement stmet =null;
    ResultSet rst = null;
     DBconnection dbcon1 = new DBconnection();
    try{
        conn = dbcon1.getSampleProperty();
        stmet = conn.createStatement();
        rst = stmet.executeQuery("select chapter_id from chapter where proj_id='"+projId+"' and stage='"+stageforLocation+"'");
        while (rst.next())
        {
            //System.out.println(rst.getString(1));
usedstage.add(rst.getString(1));

      }

    }
    catch(SQLException e){
    }
    catch(Exception e){
    }
     finally {

            //Close resultset
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }
        }
return usedstage;
        }

    public void SetStageproj_id(String projId){
        this.projId = projId;
    }
    public void SetStageforLoc(String stageforLocation){
        this.stageforLocation = stageforLocation;
    }
}
