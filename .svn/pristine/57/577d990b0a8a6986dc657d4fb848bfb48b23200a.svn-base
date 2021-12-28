/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import com.mysql.jdbc.Statement;
import connection.DBconnection;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Aravindhanj
 */
public class AutomatedTimeSheetDAO {

    public static AutomatedTimeSheetVO getProjectChapters(AutomatedTimeSheetVO automatedTimeSheetVO) {
        DBconnection dbconnection = new DBconnection();
        PreparedStatement preStmt = null;
        Connection connection = null;
        ResultSet rs = null;

        int result = 0;
        String projectId = "";
        String chapterQuery = "";
        ArrayList chapterIdList = new ArrayList();
        ArrayList chapterList = new ArrayList();
        ArrayList stageCodeList = new ArrayList();
        ArrayList stageList = new ArrayList();
        ArrayList dueDateList = new ArrayList();
        ArrayList chapterProcessList = new ArrayList();
        ArrayList batchIdList = new ArrayList();
        ArrayList shipmentdetails = new ArrayList();
        try
        {
            connection = dbconnection.getSampleProperty();
            projectId = automatedTimeSheetVO.getProjectId();
            
                chapterQuery = "select a.chapter_no,b.stage,a.chapter_id,date_format(a.due_date,'%d-%b-%Y'),a.chapter_process,b.stage_code,a.batch_id,a.ship_date from chapter a,project_stage b "
                                    + "where ( a.proj_id=? and a.batch_end_date = '0000-00-00 00:00:00' and b.stage_code=a.stage and a.chapter_deleted='0') "
                                    + "or ( a.proj_id=? and a.batch_end_date is null and b.stage_code=a.stage and a.chapter_deleted='0') order by b.stage,a.due_date";
                //System.out.println("TIME : "+chapterQuery);
                preStmt = connection.prepareStatement(chapterQuery);
                preStmt.setString(1, projectId);
                preStmt.setString(2, projectId);
                rs = preStmt.executeQuery();
                while(rs.next()) {
                    if(rs.getString(1)!=null) {
                        chapterList.add(rs.getString(1));
                    } else {
                        chapterList.add("");
                    }
                    if(rs.getString(2)!=null) {
                        stageList.add(rs.getString(2));
                    } else {
                        stageList.add("");
                    }
                    if(rs.getString(3)!=null) {
                        chapterIdList.add(rs.getString(3));
                    } else {
                        chapterIdList.add("");
                    }
                    if(rs.getString(4)!=null) {
                        dueDateList.add(rs.getString(4));
                    } else {
                        dueDateList.add("");
                    }
                    if(rs.getString(5)!=null) {
                        chapterProcessList.add(rs.getString(5));
                    } else {
                        chapterProcessList.add("");
                    }
                    if(rs.getString(6)!=null) {
                        stageCodeList.add(rs.getString(6));
                    } else {
                        stageCodeList.add("");
                    }
                    if(rs.getString(7)!=null) {
                        batchIdList.add(rs.getString(7));
                    } else {
                        batchIdList.add("");
                    }
                     if(rs.getString(8)!=null) {
                        shipmentdetails.add(rs.getString(8));
                    } else {
                        shipmentdetails.add("Not yet Shipped");
                    }
                }
                automatedTimeSheetVO.setChapterIdList(chapterIdList);
                automatedTimeSheetVO.setChapterList(chapterList);
                automatedTimeSheetVO.setStageCodeList(stageCodeList);
                automatedTimeSheetVO.setStageList(stageList);
                automatedTimeSheetVO.setDueDateList(dueDateList);
                automatedTimeSheetVO.setChapterProcessList(chapterProcessList);
                automatedTimeSheetVO.setBatchIdList(batchIdList);
                automatedTimeSheetVO.setShipmentdetails(shipmentdetails);

        } catch (SQLException sqle) {
                System.out.println("SQL Exception in AutomatedTimeSheetDAO : "+sqle);
        } finally {
            try
            {
                rs.close();
                preStmt.close();
                connection.close();
            } catch (SQLException sqle) {
                System.out.println("SQL Exception in AutomatedTimeSheetDAO : "+sqle);
            } catch (Exception npe) {
                System.out.println("Null Pointer Exception in AutomatedTimeSheetDAO : "+npe);
            }
        }
        return automatedTimeSheetVO;
    }

    public static void bulkMailShipKill(AutomatedTimeSheetVO automatedTimeSheetVO) {
        DBconnection dbconnection = new DBconnection();
        PreparedStatement preStmt = null;
        Connection connection = null;
        ResultSet rs = null;

        String empId = "";
        String empFacilityId = "";
        String showTimeStamp = "";
        boolean queryResult = false;
        int result = 0;

        List chapterIdList = new ArrayList();
        try
        {
            connection = dbconnection.getSampleProperty();
            empId = automatedTimeSheetVO.getEmpId();

            preStmt = connection.prepareStatement("SELECT facility_id FROM USER WHERE emp_id=?");
            preStmt.setString(1, empId);
            rs = preStmt.executeQuery();
            while(rs.next()){
                empFacilityId = rs.getString(1)!=null?rs.getString(1):"";
            }

            //Functionality for TimeZone Selection
            java.util.Date date = new java.util.Date();
            if (empFacilityId == "1" || empFacilityId.equals("1")) {
                //Instead of CURRENT_TIMESTAMP(), TimeStamp will used which has Chennai time.
                showTimeStamp = new Timestamp(date.getTime()).toString();
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.HOUR, -10);
                calendar.add(Calendar.MINUTE, -30);
                date = calendar.getTime();
                //Instead of CURRENT_TIMESTAMP(), TimeStamp will used which has Dubuque time.
                showTimeStamp = new Timestamp(date.getTime()).toString();
            }

            chapterIdList = automatedTimeSheetVO.getBulkMailShipKillList();
            for(int index=0; index<chapterIdList.size(); index++) {

                // Activity Entry
                preStmt = connection.prepareStatement("INSERT into activity "
                        + "(emp_id,chapter_id,begin_time,end_time,milestone_code,milestone_comp_flag,"
                        + "activity_code,activity_comp_flag,entry_date,entry_type,chargeable_flag) "
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                preStmt.setString(1, empId);
                preStmt.setString(2, chapterIdList.get(index).toString());
                preStmt.setString(3, showTimeStamp);
                preStmt.setString(4, showTimeStamp);
                preStmt.setString(5, "74");
                preStmt.setString(6, "1");
                preStmt.setString(7, "129");
                preStmt.setString(8, "1");
                preStmt.setString(9, showTimeStamp);
                preStmt.setString(10, "1");
                preStmt.setString(11, "1");
                queryResult = preStmt.execute();

                // Insert into dropoff
                preStmt = connection.prepareStatement("Insert into dropoff(chapter_id,date,dropoff_by,dropoff_to) "
                        + "VALUES (?,?,?,?)");
                preStmt.setString(1, chapterIdList.get(index).toString());
                preStmt.setString(2, showTimeStamp);
                preStmt.setString(3, empId);
                preStmt.setString(4, "74");
                queryResult = preStmt.execute();

                preStmt = connection.prepareStatement("UPDATE chapter "
                        + "SET batch_end_date=?, milestone_empcompleted=?, milestone_status=?, stage_empcompleted=? "
                        + "WHERE chapter_id = ?");
                preStmt.setString(1, showTimeStamp);
                preStmt.setString(2, "74");
                preStmt.setString(3, "Mail/Ship/Kill Completed");
                preStmt.setString(4, "Mail/Ship/Kill Completed");
                preStmt.setString(5, chapterIdList.get(index).toString());
                queryResult = preStmt.execute();
                if(!queryResult) {
                    result++;
                }
            }
            if(chapterIdList.size()==result) {
                automatedTimeSheetVO.setResult("1");
            } else {
                automatedTimeSheetVO.setResult("0");
            }
        } catch (SQLException sqle) {
                System.out.println("SQL Exception in AutomatedTimeSheetDAO : "+sqle);
        } finally {
            try
            {
                rs.close();
                preStmt.close();
                connection.close();
            } catch (SQLException sqle) {
                System.out.println("SQL Exception in AutomatedTimeSheetDAO : "+sqle);
            } catch (Exception npe) {
                System.out.println("Null Pointer Exception in AutomatedTimeSheetDAO : "+npe);
            }
        }
    }
}
