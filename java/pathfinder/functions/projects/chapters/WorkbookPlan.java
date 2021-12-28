/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects.chapters;

/**
 *
 * @author Gandhimathidevic
 */
import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class WorkbookPlan {
public int returnvalue=0;
     public String saveWorkbookPlan(String NameofChapter,String Pgcont,String chapterprocess,String projid,String stage, String empId,List milestoneCod,List MilestoneDue,String dueDate) {
//workbook, pageCount, "1", proj_id, "FP", "csr", milestoneCod, MilestoneDue,receivedDate
        Connection con = null;
        Connection con1 = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        Statement sta = null;
        Statement sta1 = null;
        int addChapterPlan = 0;
        int addedChapter = 0;
        ChapterPlanDetVO rqstVO = null;
        String chapterNotInserted = "";
        String workbookInserted = "";
        String workbookInserted1="";
        //BatchTicketReport batchTicketReport = new BatchTicketReport();
        try {
            DBconnection dbcon = new DBconnection();
             DBconnection dbcon1 = new DBconnection();
            con = dbcon.getSampleProperty();
              con1 = dbcon1.getSampleProperty();
            sta = con.createStatement();
            sta1 = con.createStatement();
            if (!projid.equals("")){
            String chapterId = "";
            String saveQry = "";
            String remark = "";
                  String saveQry1 = "";
            saveQry = "INSERT INTO chapter(chapter_no,mss_count,chapter_process,proj_id,stage,due_date,chapter_added_by,receipt_date,added_date)"
                    + " VALUES (?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
            saveQry1 = " INSERT into chapter_plan(chapter_id,milestone_id,end_date,auto_fill_flag,planned_Date) "
                    + " VALUES(?,?,?,?,CURRENT_TIMESTAMP)";
            
               statement = con.prepareStatement(saveQry);

               statement.setString(1, NameofChapter);
               statement.setString(2, Pgcont);
               statement.setString(3, chapterprocess);
               statement.setString(4, projid);
               statement.setString(5, stage);
               statement.setString(6, dueDate);
               statement.setString(7, empId);
               //statement.setString(8, remark);
              //statement.setString(10, remark);
              //System.out.println("Updated Successfully" + saveQry);
               statement.executeUpdate();
               chapterId = maxChapId();
                //mileStoneList = rqstVO.getMileStoneBook();
                //endDateList = rqstVO.getMileStoneBookend();
                //autoFlagList = rqstVO.getAutoFillFlag();
                System.out.println("Updated Successfully" + chapterId);
                //int message=getworkbookInserted(1);
                String planId = null;
                for (int i = 0; i < milestoneCod.size(); i++) {
                    statement2 = con1.prepareStatement(saveQry1);
                    statement2.setString(1, chapterId);
                    statement2.setString(2, milestoneCod.get(i).toString());
                   String temp = MilestoneDue.get(i).toString() != "" ? MilestoneDue.get(i).toString() : null;
                    statement2.setString(3, temp);
                   statement2.setString(4, "0");
                    statement2.executeUpdate();
                   // System.out.println("Updated Successfully" + chapterId);
                    System.out.println("Updated Successfully" + milestoneCod.get(i).toString()+MilestoneDue.get(i).toString());

                    // Generate Batch Ticket for created Plan
                    //batchTicketReport.setChapterId(chapterId);
                   // batchTicketReport.generateReportFields();
                    workbookInserted="yes";
                    returnvalue=1;
                }
            }
 else{
  returnvalue=0;
 }
            }
         catch (SQLException sqle) {
            System.out.println("SQLException in creating Plan Creation:" + sqle);
            return chapterNotInserted;
        } catch (Exception e) {
            System.out.println("Exception in creating Plan Creation:" + e);
            return chapterNotInserted;
        } finally {
            //Close statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                    return chapterNotInserted;
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                    return chapterNotInserted;
                }
            }
                return chapterNotInserted;
        }
//return workbookInserted;

    }
     public int getworkbookInserted(){
     return returnvalue;
     }
 public int getworkbookInserted1(){
     return returnvalue;
     }
    
     public String maxChapId() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String maxPlanId = "";
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();

            stmt = con.createStatement();
            rs = stmt.executeQuery("select max(chapter_id) from chapter");
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
