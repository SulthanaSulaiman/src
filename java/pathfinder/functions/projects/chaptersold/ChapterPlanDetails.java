/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects.chaptersold;

import java.io.Serializable;
import connection.DBconnection;
import pathfinder.functions.projects.BatchTicketReport;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Thanu
 */
public class ChapterPlanDetails implements Serializable {
 private String projId1="";
    private String stage1="";
    private String location1="";
    /**
     * This function is used to get the chapter plan details for the given
     * chapter id.
     *
     * @param planRqst value object for {@link ChapterPlanDetVO} <br>
     * @return <code> chapterPlan </code> - List of ChapterPlanDetVO object
     * which consist of list of chapter details.
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

            String chapterDetQry = " SELECT b.chapter_id,a.chapter_no,b.plan_id,b.milestone_id,c.milestone_act_name,b.end_date,b.planned_date "
                    + " FROM chapter a,chapter_plan b,proj_milestone_act c "
                    + " WHERE a.chapter_id=b.chapter_id AND b.milestone_id=c.milestone_act_code AND b.chapter_id=? ";

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

    public List getPlannedChapters(ChapterPlanDetVO req) {
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
                    + " WHERE a.chapter_id=b.chapter_id and a.proj_id=? and a.chapter_deleted='0'  ";

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

    public List getEditChapterMileStoneOrder(ChapterPlanDetVO req) {
        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String stageCode = "";
        String milestoneCode = "";
        List MilestoneOrderNum = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterPlanDetVO response = null;


        try {

            con = dbcon.getSampleProperty();

            String chapterDetQry = " SELECT mo.stage_code,mo.milestone_act_code,mo.milestone_order_number "
                    + " FROM milestone_order mo,project_stage ps,proj_milestone_act pma "
                    + " WHERE mo.stage_code=? AND mo.milestone_act_code=? AND ps.stage_code=mo.stage_code AND pma.milestone_act_code=mo.milestone_act_code ";

            stageCode = req.getChapterStgCode();
            milestoneCode = req.getMileStoneId();
            stmt = con.prepareStatement(chapterDetQry);
            stmt.setString(1, stageCode);
            stmt.setString(2, milestoneCode);
            //System.out.println("EditmileStoneOrder - stageCode : " + stageCode);
            //System.out.println("EditmileStoneOrder - milestoneCode : " + milestoneCode);
            //System.out.println("EditmileStoneOrderchapterDetQry : " + chapterDetQry);
            rs = stmt.executeQuery();
            while (rs.next()) {
                response = new ChapterPlanDetVO();
                response.setEditmileStoneOrderNo(rs.getString(3));
                MilestoneOrderNum.add(response);
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

        return MilestoneOrderNum;
    }

    /**
     * This function is used to save chapter plan details.
     *
     * @param rqstList which contains objects of value object for
     * {@link ChapterPlanDetVO} <br>
     * @return <code> addChapterPlan </code> - Returns the number of records
     * inserted.
     *
     * @exception SQLException
     * @see ChapterPlanDetVO
     */
    public List savePlan(List rqstList,String empId) {

        Connection con = null;
        Connection con1 = null;
        PreparedStatement statement = null, statement2 = null;
        Statement sta = null;
        int addChapterPlan = 0;
        int addedChapter = 0;
        ChapterPlanDetVO rqstVO = null;
        List chapterNotInserted = new ArrayList();
        BatchTicketReport batchTicketReport = new BatchTicketReport();
        try {
            DBconnection dbcon = new DBconnection();
            DBconnection dbcon1 = new DBconnection();
            con = dbcon.getSampleProperty();
            con1 = dbcon1.getSampleProperty();
            sta = con.createStatement();
            String saveQry = "", saveQry1 = "";
            saveQry = "INSERT INTO chapter(chapter_no,mss_count,remark,chapter_process,proj_id,stage,due_date,receipt_date,added_date,chapter_added_by,artcount)"
                    + " VALUES (?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)";
            saveQry1 = " INSERT into chapter_plan(plan_id,chapter_id,milestone_id,end_date,planned_Date,auto_fill_flag) "
                    + " VALUES(?,?,?,?,CURRENT_TIMESTAMP,?)";

            String chapterName = "";
            String mssCount = "";
            String artCount = "";
            String remark = "";
            String status = "";
            String projectId = "";
            String stage = "";
            String chapterId = "";
            String enddate = "";
            List mileStoneList = new ArrayList();
            List endDateList = new ArrayList();
            List autoFlagList = new ArrayList();




            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                Boolean insertFlag = true;
                statement = con.prepareStatement(saveQry);
                rqstVO = (ChapterPlanDetVO) itr.next();
                chapterName = rqstVO.getChapterName();
                mssCount = rqstVO.getMssCount();
                artCount = rqstVO.getArtCount();
                if (artCount.equals("")) {
                    artCount = "0";
                }
                if (mssCount.equals("")) {
                    mssCount = "0";
                }
                remark = rqstVO.getRemark();
                status = rqstVO.getProcess();
                projectId = rqstVO.getProjectId();
                stage = rqstVO.getChapterStgCode();
                ResultSet executeQuery = sta.executeQuery("select * from chapter where proj_id='" + projectId + "' and stage = '" + stage + "' and chapter_no = '" + chapterName + "' and batch_end_date is null and chapter_deleted=0");
                if (executeQuery.next()) {
                    insertFlag = false;
                    chapterNotInserted.add(chapterName);
                }
                enddate = !rqstVO.getEndDate().trim().equals("") ? rqstVO.getEndDate() : null;
                statement.setString(1, chapterName);
                statement.setString(2, mssCount);
                statement.setString(3, remark);
                statement.setString(4, status);
                statement.setString(5, projectId);
                statement.setString(6, stage);
                statement.setString(7, enddate);
                statement.setString(8, empId);
                statement.setString(9, artCount);
                //System.out.println("Checking chapter plan" + statement);
                if (insertFlag) {
                    statement.executeUpdate();
                    addedChapter++;
                }
                chapterId = maxChapId();
                mileStoneList = rqstVO.getMileStoneBook();
                endDateList = rqstVO.getMileStoneBookend();
                autoFlagList = rqstVO.getAutoFillFlag();
                //String planId = maxPlanId();
                String planId = null;
                for (int i = 0; i < mileStoneList.size(); i++) {
                    statement2 = con1.prepareStatement(saveQry1);
                    statement2.setString(1, planId);
                    statement2.setString(2, chapterId);
                    statement2.setString(3, mileStoneList.get(i).toString());
                    String temp = endDateList.get(i).toString().trim() != "" ? endDateList.get(i).toString().trim() : null;
                    statement2.setString(4, temp);
                    statement2.setString(5, autoFlagList.get(i).toString());
                    try {
                        //System.out.println("Chapter Plan Details:" + statement2);
                        if (insertFlag) {
                            statement2.executeUpdate();
                        }
                    } catch (Exception e1) {
                        System.out.println("Checking plan error:" + e1.toString());
                        return chapterNotInserted;
                    }
                    //System.out.println("Updated Successfully" + statement2);

                    // Generate Batch Ticket for created Plan
                    batchTicketReport.setChapterId(chapterId);
                    batchTicketReport.generateReportFields();
                }
            }
        } catch (SQLException sqle) {
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
        }

        return chapterNotInserted;
    }

    public int saveMileStone(List rqstList) {
        try {
            DBconnection dbcon = new DBconnection();
            Connection con = dbcon.getSampleProperty();
            Statement statement = con.createStatement();
            PreparedStatement preparedStatement = null;
            ChapterPlanDetVO rqstVO = null;
            String chapterId = "";
            String planId = "";
            String mileStoneId = "";
            String mileStoneEnd = "";
            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                rqstVO = (ChapterPlanDetVO) itr.next();
                chapterId = rqstVO.getChapterId();
                //planId = rqstVO.getPlanId().trim().equals("")?null:rqstVO.getPlanId();
                mileStoneId = rqstVO.getMileStoneId();
                mileStoneEnd = rqstVO.getPlannedDate().trim().equals("") ? null : rqstVO.getPlannedDate();
                preparedStatement = con.prepareStatement("INSERT INTO chapter_plan (chapter_id,milestone_id,end_date,planned_date)"
                        + "VALUES(?,?,?,CURRENT_TIMESTAMP)");
                preparedStatement.setString(1, chapterId);
                preparedStatement.setString(2, mileStoneId);
                preparedStatement.setString(3, mileStoneEnd);
                preparedStatement.executeUpdate();
                if (mileStoneId.equals("21")) {
                    preparedStatement = con.prepareStatement("UPDATE chapter SET due_date=?,ship_date=null,proof_page=null,completed_date=null WHERE chapter_id=?");
                    preparedStatement.setString(1, mileStoneEnd);
                    preparedStatement.setString(2, chapterId);
                    preparedStatement.executeUpdate();

                    preparedStatement = con.prepareStatement("UPDATE activity SET milestone_comp_flag='0', activity_comp_flag='0' WHERE chapter_id=?");
                    preparedStatement.setString(1, chapterId);
                    preparedStatement.executeUpdate();

                    preparedStatement = con.prepareStatement("DELETE FROM dropoff WHERE chapter_id=?");
                    preparedStatement.setString(1, chapterId);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return 0;
        }
        return 1;
    }

    public ChapterPlanDetVO getAutoFillFlag(ChapterPlanDetVO planVO){
        String chapterId = "";
        List autoFillFlag = new ArrayList();
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();
            
            chapterId = planVO.getChapterId();
            String query="select auto_fill_flag from chapter_plan where chapter_id ="+chapterId;
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                if(rs.getString(1)!=null ) {
                    autoFillFlag.add(rs.getString(1));
                }else{
                    autoFillFlag.add("");
                }
            }
            planVO.setAutoFillFlag(autoFillFlag);
        }catch(Exception e){
            System.out.println("Exception in getAutoFillFlag of ChapterPlanDetails "+e);
        }
        return planVO;
    }
    public int editPlan(List rqstList,String empId) {

        Connection con = null;
        PreparedStatement statement = null, chapStatement = null;
        int addChapterPlan = 0;
        ChapterPlanDetVO rqstVO = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            String saveQry = " update chapter_plan SET end_date = ?, auto_fill_flag=? where chapter_id = ? and plan_auto_id = ?;";
            String saveChapQry = "UPDATE chapter SET chapter_no = ?, mss_count = ?, remark = ?, chapter_process = ?, due_date = ?,mod_del_date = CURRENT_TIMESTAMP, chapter_mod_del_by = ?, artcount = ? WHERE chapter_id = ? AND proj_id = ?;";
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO chapter_plan_log (chapter_id,milestone_id,end_date,planned_date,logged_by)"
                        + "VALUES(?,?,?,CURRENT_TIMESTAMP,?)");
            chapStatement = con.prepareStatement(saveChapQry);
            statement = con.prepareStatement(saveQry);
            //String planId = maxPlanId();
            String chapterId = "";
            String chapterName = "";
            String mssCount = "";
            String artCount = "";
            String remark = "";
            String status = "";
            String projectId = "";
            String enddate = "";

            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                List mileStoneList = new ArrayList();
                List endDateList = new ArrayList();
                List editAutoFill = new ArrayList();
                rqstVO = (ChapterPlanDetVO) itr.next();
                chapterName = rqstVO.getChapterName();
                mssCount = rqstVO.getMssCount();
                artCount = rqstVO.getArtCount();
                System.out.println("artCount"+artCount);
                if (mssCount == "null" || mssCount == null || mssCount.equals("")) {
                    mssCount = "0";
                }
                 if (artCount == "null" || artCount == null || artCount.equals("")) {
                    artCount = "0";
                }
                remark = rqstVO.getRemark();
                status = rqstVO.getProcess();
                enddate = !rqstVO.getEndDate().trim().equals("") ? rqstVO.getEndDate() : null;
                chapterId = rqstVO.getChapterId();
                projectId = rqstVO.getProjectId();
                chapStatement.setString(1, chapterName);
                chapStatement.setString(2, mssCount);
                chapStatement.setString(3, remark);
                chapStatement.setString(4, status);
                chapStatement.setString(5, enddate);
                chapStatement.setString(6, empId);
                chapStatement.setString(8, chapterId);
                chapStatement.setString(9, projectId);
                chapStatement.setString(7, artCount);
                //System.out.println("Checking chapter plan" + chapStatement);
                chapStatement.executeUpdate();
                mileStoneList = rqstVO.getMileStoneBook();
                endDateList = rqstVO.getMileStoneBookend();
                editAutoFill = rqstVO.getAutoFillFlag();
                //System.out.println("Testing" + endDateList);
                for (int i = 0; i < mileStoneList.size(); i++) {
                    String temp = endDateList.get(i).toString().trim() != "" ? endDateList.get(i).toString().trim() : null;
                    statement.setString(1, temp);
                    statement.setString(2, editAutoFill.get(i).toString());
                    statement.setString(3, chapterId);
                    statement.setString(4, mileStoneList.get(i).toString());
                    try {
                        //System.out.println("Chapter Plan Details:" + statement);
                        if (temp != null) {
                            statement.executeUpdate();
                            preparedStatement.setString(1, chapterId);
                            preparedStatement.setString(2, mileStoneList.get(i).toString());
                            preparedStatement.setString(3, temp);
                            preparedStatement.setString(4, empId);
                            preparedStatement.executeUpdate();
                        }
                    } catch (Exception e1) {
                        System.out.println("Checking plan error:" + e1.toString());
                        return 0;
                    }
                    //System.out.println("Updated Successfully" + statement);
                }
            }
        } catch (Exception sqle) {
            System.out.println("SQLException in creating Plan Creation:" + sqle.toString());
            return 0;
        }
        return 1;
    }

    public int deletePlanMileStone(List rqstList) {

        Connection con = null;
        Statement statement = null;
        int addChapterPlan = 0;
        ChapterPlanDetVO rqstVO = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            statement = con.createStatement();
            String planId = "";
            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                rqstVO = (ChapterPlanDetVO) itr.next();
                planId = rqstVO.getPlanId();
                statement.executeUpdate("DELETE FROM chapter_plan WHERE plan_auto_id = '" + planId + "'");
            }
        } catch (Exception sqle) {
            System.out.println("SQLException in Deleting Plan Creation:" + sqle.toString());
        }
        return addChapterPlan;
    }

    public int orderMilestone(List rqstList) {

        Connection con = null;
        Statement statement = null;
        int addChapterPlan = 0;
        ChapterPlanDetVO rqstVO = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            statement = con.createStatement();
            String planId = "";
            String orderNo = "";
            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                rqstVO = (ChapterPlanDetVO) itr.next();
                planId = rqstVO.getPlanId();
                orderNo = rqstVO.getChapterOrderId();
                statement.executeUpdate("UPDATE chapter_plan SET order_no = '" + orderNo + "' WHERE plan_auto_id = '" + planId + "'");
            }
        } catch (Exception sqle) {
            System.out.println("SQLException in creating Plan Creation:" + sqle.toString());
            return 0;
        }
        return 1;
    }

    public int deleteChapters(List chapterId, String empId) {
        Connection con = null;
        //prepardedStatement statement = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            for(int index=0;index < chapterId.size(); index++){
                PreparedStatement ps = con.prepareStatement("UPDATE chapter SET `chapter_deleted` = 1,mod_del_date = CURRENT_TIMESTAMP, chapter_mod_del_by = ? WHERE chapter_id =?");
                ps.setString(1, empId);
                ps.setString(2, (String) chapterId.get(index));
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.print("Error while deleting chapter" + e.toString());
            return 0;
        }
        return 1;
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
            //deleteChapterPlan = statement.executeUpdate("delete from  chapter where chapter_id='" + chapterId + "' ");
            deleteChapterPlan = statement.executeUpdate("UPDATE chapter SET `chapter_deleted` = 1 WHERE chapter_id ='" + chapterId + "'");
            //deleteChapterPlan = statement.executeUpdate("delete from  chapter_plan where chapter_id='" + chapterId + "' ");

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


                //System.out.println("copyQry:" + copyQry);



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

     public int editQucikPlan(List rqstList,String empId) {

        Connection con = null;
        PreparedStatement statement = null, chapStatement = null;
        int addChapterPlan = 0;
        ChapterPlanDetVO rqstVO = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            String saveQry = " update chapter_plan SET end_date = ?, auto_fill_flag=? where chapter_id = ? and plan_auto_id = ?;";
            String saveChapQry = "UPDATE chapter SET chapter_no = ?, mss_count = ?, remark = ?, chapter_process = ?, due_date = ?,mod_del_date = CURRENT_TIMESTAMP, chapter_mod_del_by = ?, artcount = ? WHERE chapter_id = ? AND proj_id = ?;";
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO chapter_plan_log (chapter_id,milestone_id,end_date,planned_date,logged_by)"
                        + "VALUES(?,?,?,CURRENT_TIMESTAMP,?)");
            chapStatement = con.prepareStatement(saveChapQry);
            statement = con.prepareStatement(saveQry);
            //String planId = maxPlanId();
            String chapterId = "";
            String chapterName = "";
            String mssCount = "";
            String artCount = "";
            String remark = "";
            String status = "";
            String projectId = "";
            String enddate = "";

            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                List mileStoneList = new ArrayList();
                List endDateList = new ArrayList();
                List editAutoFill = new ArrayList();
                rqstVO = (ChapterPlanDetVO) itr.next();
                chapterName = rqstVO.getChapterName();
                mssCount = rqstVO.getMssCount();
                artCount = rqstVO.getArtCount();
                System.out.println("artCount"+artCount);
                if (mssCount == "null" || mssCount == null || mssCount.equals("")) {
                    mssCount = "0";
                }
                 if (artCount == "null" || artCount == null || artCount.equals("")) {
                    artCount = "0";
                }
                remark = rqstVO.getRemark();
                status = rqstVO.getProcess();
                enddate = !rqstVO.getEndDate().trim().equals("") ? rqstVO.getEndDate() : null;
                chapterId = rqstVO.getChapterId();
                projectId = rqstVO.getProjectId();
                chapStatement.setString(1, chapterName);
                chapStatement.setString(2, mssCount);
                chapStatement.setString(3, remark);
                chapStatement.setString(4, status);
                chapStatement.setString(5, enddate);
                chapStatement.setString(6, empId);
                chapStatement.setString(8, chapterId);
                chapStatement.setString(9, projectId);
                chapStatement.setString(7, artCount);
                //System.out.println("Checking chapter plan" + chapStatement);
                chapStatement.executeUpdate();
                mileStoneList = rqstVO.getMileStoneBook();
                endDateList = rqstVO.getMileStoneBookend();
                editAutoFill = rqstVO.getAutoFillFlag();
                //System.out.println("Testing" + endDateList);
                for (int i = 0; i < mileStoneList.size(); i++) {
                    String temp = endDateList.get(i).toString().trim() != "" ? endDateList.get(i).toString().trim() : null;
                    statement.setString(1, temp);
                    statement.setString(2, editAutoFill.get(i).toString());
                    statement.setString(3, chapterId);
                    statement.setString(4, mileStoneList.get(i).toString());
                    try {
                        //System.out.println("Chapter Plan Details:" + statement);
                        if (temp != null) {
                            statement.executeUpdate();
                            preparedStatement.setString(1, chapterId);
                            preparedStatement.setString(2, mileStoneList.get(i).toString());
                            preparedStatement.setString(3, temp);
                            preparedStatement.setString(4, empId);
                            preparedStatement.executeUpdate();
                        }
                    } catch (Exception e1) {
                        System.out.println("Checking plan error:" + e1.toString());
                        return 0;
                    }
                    //System.out.println("Updated Successfully" + statement);
                }
            }
        } catch (Exception sqle) {
            System.out.println("SQLException in creating Plan Creation:" + sqle.toString());
            return 0;
        }
        return 1;
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
     public String insertonOffShoreDetails(){
        Connection con= null;
        DBconnection dbcon = new DBconnection();
        Statement stmt = null;
        ResultSet rst = null;
        String detailsofOnOffShore = "";
        String projId = "";
        String stage = "";
            projId = ChapterPlanDetails.this.getProjectId1();
            stage = ChapterPlanDetails.this.getStage1();
            detailsofOnOffShore = ChapterPlanDetails.this.getlocation1();

        try{
        con = dbcon.getSampleProperty();
        stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO stage_handled_location(proj_id,stage,location) values('" + projId1 + "','"+ stage1 +"','"+ location1 +"')");
        }
        catch (SQLException e){

        }
        catch (Exception e){

        }
        return detailsofOnOffShore;
     }
     public void updateonOffShoreDetails(){
        Connection con= null;
        DBconnection dbcon = new DBconnection();
        Statement stmt = null;
        ResultSet rst = null;
        String detailsofOnOffShore = "";
        String projId = "";
        String stage = "";
            projId = ChapterPlanDetails.this.getProjectId1();
            stage = ChapterPlanDetails.this.getStage1();
            detailsofOnOffShore = ChapterPlanDetails.this.getlocation1();

        try{
        con = dbcon.getSampleProperty();
        stmt = con.createStatement();
        stmt.executeUpdate("update stage_handled_location set location='"+ location1 +"' where proj_id='" + projId1 + "'and stage='"+ stage1 +"'");
        }
        catch (SQLException e){

        }
        catch (Exception e){

        }
             }
 public void setProjectId1(String projId1) {
        this.projId1 = projId1;
    }
 public void setStage1(String stage1) {
        this.stage1 = stage1;
    }
 public void setlocation1(String location1) {
        this.location1 = location1;
    }
 public String getProjectId1() {
        return projId1;
    }
 public String getStage1() {
        return stage1;
    }
 public String getlocation1() {
        return location1;
    }
}
