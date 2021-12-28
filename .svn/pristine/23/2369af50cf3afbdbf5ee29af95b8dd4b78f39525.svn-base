/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pattany
 */

public class AddTimeSheetDAO {

    DBconnection dbconnection = new DBconnection();
    Statement stmt = null;
    PreparedStatement preStmt = null;
    ResultSet rs = null;
    Connection con = null;
        
    private String searchKey = "";
    private String projId = "";
    private String empId = "";
    private int result = 0;
    private List<String> chapterId = new ArrayList<String>();
    private List<String> chapterNo = new ArrayList<String>();
    private List<String> milestoneId = new ArrayList<String>();
    private List<String> milestoneName = new ArrayList<String>();
    private List<String> activityId = new ArrayList<String>();
    private List<String> activityName = new ArrayList<String>();

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public List<String> getChapterId() {
        return chapterId;
    }

    public void setChapterId(List<String> chapterId) {
        this.chapterId = chapterId;
    }

    public List<String> getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(List<String> chapterNo) {
        this.chapterNo = chapterNo;
    }

    public List<String> getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(List<String> milestoneId) {
        this.milestoneId = milestoneId;
    }

    public List<String> getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(List<String> milestoneName) {
        this.milestoneName = milestoneName;
    }

    public List<String> getActivityId() {
        return activityId;
    }

    public void setActivityId(List<String> activityId) {
        this.activityId = activityId;
    }

    public List<String> getActivityName() {
        return activityName;
    }

    public void setActivityName(List<String> activityName) {
        this.activityName = activityName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void getChapterDetails() {
        try {
            con = dbconnection.getSampleProperty();
            stmt = con.createStatement();
            String chapter_query = "select a.chapter_id,a.stage,CONCAT(b.stage,' / ',a.chapter_no),a.batch_id from chapter a,project_stage b "
                        + "where a.proj_id='" + projId + "' and b.stage_code=a.stage and a.chapter_deleted='0' order by b.stage,a.due_date";
            rs = stmt.executeQuery(chapter_query);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    if (rs.getString(2) != null) {
                        chapterId.add(rs.getString(1)+"@@@"+rs.getString(2));
                    } else {
                        chapterId.add("");
                    }
                } else {
                    chapterId.add("");
                }
                if (rs.getString(3) != null) {
                    if (rs.getString(4) != null) {
                        chapterNo.add(rs.getString(3)+" ("+rs.getString(4)+")");
                    } else {
                        chapterNo.add(rs.getString(3)+" (-)");
                    }
                } else {
                    chapterNo.add("");
                }
            }
        } catch (SQLException sqle) {
            System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("Null Pointer Exception in AddTimeSheetDAO : "+npe);
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("Null Pointer Exception in AddTimeSheetDAO : "+npe);
            }
        }
    }

    public void getMilestoneDetails() {
        try {
            con = dbconnection.getSampleProperty();
            stmt = con.createStatement();
            String[] keySplit = searchKey.split("@@@");
            String getChapterId = keySplit[0];
            String getStageCode = keySplit[1];
            /*
            String milestone_query = "SELECT mdm.milestone_act_code,pma.milestone_act_name "
                                    + " FROM milestone_desig_map mdm,proj_milestone_act pma,designation d "
                                    + " WHERE mdm.desig_code='" + empId + "' AND pma.milestone_act_code=mdm.milestone_act_code AND mdm.desig_code=d.desig_code ";
             *
             */
            //String milestone_query = "SELECT b.chapter_id,a.chapter_no,b.plan_id,b.milestone_id,c.milestone_act_name,DATE_FORMAT(b.end_date, '%Y-%m-%d'),b.planned_date,mo.milestone_order_number,b.plan_auto_id"
            String milestone_query = "SELECT b.milestone_id,c.milestone_act_name "
                    + " FROM chapter a,chapter_plan b,proj_milestone_act c,milestone_order mo,project_stage ps "
                    + " WHERE a.chapter_id=b.chapter_id AND b.milestone_id=mo.milestone_act_code AND b.chapter_id='"+getChapterId+"' and mo.stage_code='"+getStageCode+"' and mo.milestone_act_code=c.milestone_act_code and ps.stage_code=mo.stage_code "
                    + " ORDER BY b.order_no,mo.milestone_order_number,b.end_date";
            rs = stmt.executeQuery(milestone_query);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    milestoneId.add(rs.getString(1));
                } else {
                    milestoneId.add(rs.getString(1));
                }
                if (rs.getString(2)  != null) {
                    milestoneName.add(rs.getString(2));
                } else {
                    milestoneName.add("");
                }
            }
        } catch (SQLException sqle) {
            System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("Null Pointer Exception in AddTimeSheetDAO : "+npe);
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("Null Pointer Exception in AddTimeSheetDAO : "+npe);
            }
        }
    }

    public void getActivityDetails() {
        try {
            con = dbconnection.getSampleProperty();
            stmt = con.createStatement();
            String[] keySplit = searchKey.split("@@@");
            String stageCode = keySplit[1];
            String milestoneCode = keySplit[2];
            String activity_query = "SELECT a.activity_code,a.activity FROM activity_type a,milestone_act_map mam,proj_milestone_act pma,project_stage ps "
                                        + " WHERE a.general_activity='0' AND mam.stage_code='" + stageCode + "' AND mam.milestone_act_code='" + milestoneCode + "' AND mam.milestone_act_code=pma.milestone_act_code "
                                        + " AND a.activity_code=mam.activity_code AND ps.stage_code=mam.stage_code";
            rs = stmt.executeQuery(activity_query);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    activityId.add(rs.getString(1));
                } else {
                    activityId.add("");
                }
                if (rs.getString(2)  != null) {
                    activityName.add(rs.getString(2));
                } else {
                    activityName.add("");
                }
            }
        } catch (SQLException sqle) {
            System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("Null Pointer Exception in AddTimeSheetDAO : "+npe);
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("Null Pointer Exception in AddTimeSheetDAO : "+npe);
            }
        }
    }

    public void SaveEmpTimeSheet(AddTimeSheetVO addTimeSheetVO) {
        PreparedStatement ps = null;
        PreparedStatement innerPs = null;
        PreparedStatement nestedPs = null;

        String addTimeSheet_query = "";
        String dropOff_query = "";
        String chapterUpdate_query = "";
        String chapterCompleted = "";
        String errorStart = "";
        String errorEnd = "";

        int res = 0;
        String getCurrentEmpId = "";

        try
        {
            con = dbconnection.getSampleProperty();
            stmt = con.createStatement();
            
            ArrayList getEmpIdList = new ArrayList();
            ArrayList getProjIdList = new ArrayList();
            ArrayList getChapterIdList = new ArrayList();
            ArrayList getStageCodeList = new ArrayList();
            ArrayList getMilestoneIdList = new ArrayList();
            ArrayList getActivityCodeList = new ArrayList();
            ArrayList getStartTimeList = new ArrayList();
            ArrayList getEndTimeList = new ArrayList();
            ArrayList getCompletedStatusList = new ArrayList();
            ArrayList getChargeableStatusList = new ArrayList();
            ArrayList errorStatusList = new ArrayList();
            ArrayList getPageCountList = new ArrayList();

            getEmpIdList = addTimeSheetVO.getEmpId();
            getProjIdList = addTimeSheetVO.getProjId();
            getChapterIdList = addTimeSheetVO.getChapterId();
            getStageCodeList = addTimeSheetVO.getStageCode();
            getMilestoneIdList = addTimeSheetVO.getMilestoneId();
            getActivityCodeList = addTimeSheetVO.getActivityCode();
            getStartTimeList = addTimeSheetVO.getStartTime();
            getEndTimeList = addTimeSheetVO.getEndTime();
            getCompletedStatusList = addTimeSheetVO.getCompletedStatus();
            getChargeableStatusList = addTimeSheetVO.getChargeableStatus();
            errorStatusList = addTimeSheetVO.getErrorStatus();
            getPageCountList = addTimeSheetVO.getPageCount();
            getCurrentEmpId = addTimeSheetVO.getCurrentEmpId();

            for(int index = 0; index<getEmpIdList.size(); index++) {
                try {
                    // Check for Shift details according to the activity, do update if needed
                    UpdateShiftDetail(getStartTimeList.get(index).toString(), getEndTimeList.get(index).toString(), getEmpIdList.get(index).toString());
                } catch (ParseException ex) {
                    System.out.println("ParseException Error in AddTimeSheetDAO : "+ex);
                }

                // Activity insertion
                if(!errorStatusList.get(index).equals("")) {
                    if(errorStatusList.get(index).equals("1")) {
                        errorStart = "chennai_err_start";
                        errorEnd = "chennai_err_end";
                    } else if(errorStatusList.get(index).equals("2")) {
                        errorStart = "dubuque_err_start";
                        errorEnd = "dubuque_err_end";
                    } else {
                        errorStart = "outside_err_start";
                        errorEnd = "outside_err_end";
                    }
                    addTimeSheet_query = "INSERT into activity (emp_id,chapter_id,proof_pages,begin_time,end_time,milestone_code,milestone_comp_flag,"
                            + "activity_code,activity_comp_flag,entry_date,entry_type,chargeable_flag,"+errorStart+","+errorEnd+") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                } else {
                    addTimeSheet_query = "INSERT into activity (emp_id,chapter_id,proof_pages,begin_time,end_time,milestone_code,milestone_comp_flag,"
                            + "activity_code,activity_comp_flag,entry_date,entry_type,chargeable_flag) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                }
                ps = con.prepareStatement(addTimeSheet_query);
                ps.setString(1, getEmpIdList.get(index).toString());
                ps.setInt(2, Integer.parseInt(getChapterIdList.get(index).toString()));
                ps.setInt(3, Integer.parseInt(getPageCountList.get(index).toString()));
                ps.setString(4, getStartTimeList.get(index).toString());
                ps.setString(5, getEndTimeList.get(index).toString());
                ps.setString(6, getMilestoneIdList.get(index).toString());
                ps.setInt(7, Integer.parseInt(getCompletedStatusList.get(index).toString()));
                ps.setString(8, getActivityCodeList.get(index).toString());
                ps.setInt(9, Integer.parseInt(getCompletedStatusList.get(index).toString()));
                ps.setString(10, getStartTimeList.get(index).toString());
                ps.setInt(11, 1);
                ps.setInt(12, Integer.parseInt(getChargeableStatusList.get(index).toString()));
                if(!errorStatusList.get(index).equals("")) {
                    ps.setString(13, getStartTimeList.get(index).toString());
                    ps.setString(14, getEndTimeList.get(index).toString());
                }
                res = ps.executeUpdate();

                if(res == 1 && getCompletedStatusList.get(index).toString().equals("1")) {
                    // Insert into dropoff if the given activity completed
                    dropOff_query = "Insert into dropoff(chapter_id,date,dropoff_by,dropoff_to) VALUES (?,CURRENT_TIMESTAMP(),?,?)";
                    innerPs = con.prepareStatement(dropOff_query);
                    innerPs.setInt(1, Integer.parseInt(getChapterIdList.get(index).toString()));
                    innerPs.setString(2, getEmpIdList.get(index).toString());
                    innerPs.setString(3, getMilestoneIdList.get(index).toString());
                    res = innerPs.executeUpdate();
                    if(res == 1) {
                        // Update Batch end date if the batch completed employee is Dubuque user
                        if(getMilestoneIdList.get(index).toString().equals("74")) {
                            chapterCompleted = ", batch_end_date = ? ";
                        } else {
                            chapterCompleted = "";
                        }
                        //Update Chapter details according to the activity completed
                        chapterUpdate_query = "update chapter set milestone_empcompleted=?, "
                                + "milestone_status=CONCAT((SELECT milestone_act_name FROM proj_milestone_act WHERE milestone_act_code=?),' Completed'), "
                                + "stage_empcompleted=CONCAT((SELECT stage FROM project_stage WHERE stage_code=?),' Completed') " + chapterCompleted
                                + "where chapter_id=?";
                        nestedPs = con.prepareStatement(chapterUpdate_query);
                        nestedPs.setInt(1, Integer.parseInt(getMilestoneIdList.get(index).toString()));
                        nestedPs.setString(2, getMilestoneIdList.get(index).toString());
                        nestedPs.setString(3, getStageCodeList.get(index).toString());
                        if(getMilestoneIdList.get(index).toString().equals("74")) {
                            nestedPs.setString(4, getEndTimeList.get(index).toString());
                            nestedPs.setInt(5, Integer.parseInt(getChapterIdList.get(index).toString()));
                        } else {
                            nestedPs.setInt(4, Integer.parseInt(getChapterIdList.get(index).toString()));
                        }
                        res = nestedPs.executeUpdate();
                    }
                } else if(res == 1 && getCompletedStatusList.get(index).toString().equals("0")) {
                    //Update Chapter details according to the activity in Progress
                    chapterUpdate_query = "update chapter set stage_empcompleted=CONCAT((SELECT stage FROM project_stage WHERE stage_code=?),' in Progress') where chapter_id = ?";
                    innerPs = con.prepareStatement(chapterUpdate_query);
                    innerPs.setString(1, getStageCodeList.get(index).toString());
                    innerPs.setInt(2, Integer.parseInt(getChapterIdList.get(index).toString()));
                    res = innerPs.executeUpdate();
                }
                if(res == 0 && result == 1) {
                    result = 0;
                } else {
                    result = 1;
                }
            }
            addTimeSheetVO.setResult(result);
        } catch (SQLException sqle) {
                System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
        } finally {
            try
            {
                nestedPs.close();
                innerPs.close();
                ps.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
            } catch (Exception npe) {
                System.out.println("Null Pointer Exception in AddTimeSheetDAO : "+npe);
            }
        }
    }

    public void UpdateShiftDetail(String start, String end, String emp) throws ParseException {
        Connection connection = null;
        PreparedStatement ps = null;

        String shift_query = "";
        String shiftUpdateFields = "";
        String startAt = "";
        String endAt = "";
        String getShiftId = "";
        String[] keySplit;

        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        java.util.Date shiftStart = null;
        java.util.Date shiftEnd = null;
        java.util.Date shiftStarted = null;
        java.util.Date shiftEnded = null;
        int res = 0;
        
        try
        {
            connection = dbconnection.getSampleProperty();
            stmt = connection.createStatement();

            shiftStart = formatter.parse(start);
            shiftEnd = formatter.parse(end);

            // Separate date from Start and End Date&Time
            keySplit = start.split(" ");
            startAt = keySplit[0];
            keySplit = end.split(" ");
            endAt = keySplit[0];

            // Get the Shift detail on the given date
            shift_query = "SELECT shift_id, shift_start_time, shift_end_time FROM shift "
                    + "WHERE emp_id=? AND (shift_start_time LIKE ? OR shift_end_time LIKE ?) LIMIT 1";
            ps = connection.prepareStatement(shift_query);
            ps.setString(1, emp);
            ps.setString(2, startAt+" %");
            ps.setString(3, endAt+" %");
            rs = ps.executeQuery();

            while (rs.next()) {
                res++;
                if (rs.getString(1) != null) {
                    getShiftId = rs.getString(1);
                } else {
                    getShiftId = "";
                }
                if (rs.getString(2)  != null) {
                    shiftStarted = formatter.parse(rs.getString(2).toString());
                }
                if (rs.getString(3)  != null) {
                    shiftEnded = formatter.parse(rs.getString(3).toString());
                }
            }
            rs.close();

            if (res == 0) {
                // Insert Shift detail if there is no shift on that date
                shift_query = "INSERT INTO shift (emp_id, shift_start_time, shift_end_time, entry_type) "
                        + "VALUES (?,?,?,'1')";
                ps = con.prepareStatement(shift_query);
                ps.setString(1, emp);
                ps.setString(2, start);
                ps.setString(3, end);
                res = ps.executeUpdate();
            }
            res = 0;

            if((shiftStarted != null && (shiftStart.getTime() - shiftStarted.getTime() < 0)) || (shiftEnded != null && (shiftEnd.getTime() - shiftEnded.getTime() > 0))) {
                // Update Shift detail if the start time and end time is miss matchs
                shift_query = "UPDATE shift SET ";
                if(shiftStart.getTime() - shiftStarted.getTime() < 0) {
                    shiftUpdateFields = "shift_start_time='"+start+"'";
                }
                if(shiftEnd.getTime() - shiftEnded.getTime() > 0) {
                    if(shiftUpdateFields.length() > 0) shiftUpdateFields += ", ";
                    shiftUpdateFields += "shift_end_time='"+end+"'";
                }
                //shiftUpdateFields +=
                shift_query += shiftUpdateFields + " WHERE shift_id='"+getShiftId+"'";
                res = stmt.executeUpdate(shift_query);
            }
        } catch (SQLException sqle) {
                System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
        } finally {
            try
            {
                ps.close();
                stmt.close();
                connection.close();
            } catch (SQLException sqle) {
                System.out.println("SQL Exception in AddTimeSheetDAO : "+sqle);
            } catch (Exception npe) {
                System.out.println("Null Pointer Exception in AddTimeSheetDAO : "+npe);
            }
        }
    }

    public AddTimeSheetVO getStageMilestones(AddTimeSheetVO addTimeSheetVO){

        String sqlQuery="";
        ArrayList milestoneActCodeList = new ArrayList();
        ArrayList milestoneActNameList = new ArrayList();

        DBconnection dbconnection = new DBconnection();
        Connection con = dbconnection.getSampleProperty();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String stageCodeStr = addTimeSheetVO.getSearchKey();
        String bufferPlanFlag = addTimeSheetVO.getBufferPlanFlag();
        String bufferPlanStr = bufferPlanFlag.toString().equals("1")? " AND pma.buffer_plan_flag='1' " : "";
        System.out.println(stageCodeStr);
        try {
            pst = con.prepareStatement("SELECT mo.milestone_act_code, pma.milestone_act_name FROM project_stage ps, milestone_order mo, proj_milestone_act pma "
                    + " WHERE ps.stage_code=mo.stage_code  AND mo.milestone_act_code=pma.milestone_act_code AND ps.stage_code=? "
                    + bufferPlanStr
                    + " ORDER BY milestone_act_name");
            pst.setString(1, stageCodeStr);

            rs = pst.executeQuery();
            while(rs.next()) {
                milestoneActCodeList.add(rs.getString(1));
                milestoneActNameList.add(rs.getString(2));
            }
            addTimeSheetVO.setMilestoneActCode(milestoneActCodeList);
            addTimeSheetVO.setMilestoneActName(milestoneActNameList);
        } catch(Exception e) {
            System.out.println("Exception in getStageMilestones of AddTimeSheetDAO : " + e);
        } finally {
            try {
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Exception in getStageMilestones of AddTimeSheetDAO : " + ex);
            }
            
        }
        return addTimeSheetVO;
    }
}
