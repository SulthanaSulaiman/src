/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.sql.Statement;
import java.beans.*;
import java.io.Serializable;



public class BatchTicketReport implements Serializable {
    
    DBconnection connection = new DBconnection();

    
    public BatchTicketReport() {

    }
    
    private String chkBatchID="";
    private String projId="";
    private String chapterId="";
    private String notes="";
    private String plainId = "";
    private String ftpLastUpdatedTimeStamp = "";
    private String ftpLastUpdatedBy = "";

    public String getFtpLastUpdatedBy() {
        return ftpLastUpdatedBy;
    }

    public String getFtpLastUpdatedTimeStamp() {
        return ftpLastUpdatedTimeStamp;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChkBatchID() {
        return chkBatchID;
    }

    public void setChkBatchID(String chkBatchID) {
        this.chkBatchID = chkBatchID;
    }

    public String getGeneratedBatchId() {
        return generatedBatchId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public void setGeneratedBatchId(String generatedBatchId) {
        this.generatedBatchId = generatedBatchId;
    }

    public List getMileStoneEndDateList() {
        return mileStoneEndDateList;
    }

    public void setMileStoneEndDateList(List mileStoneEndDateList) {
        this.mileStoneEndDateList = mileStoneEndDateList;
    }

    public List getMileStoneList() {
        return mileStoneList;
    }

    public void setMileStoneList(List mileStoneList) {
        this.mileStoneList = mileStoneList;
    }
    public List getMileStonecode() {
        return mileStonecode;
    }

    public void setMileStoneCode(List mileStonecode) {
        this.mileStonecode = mileStonecode;
    }
    private String chapterNumber="";
    private String generatedBatchId="";
    private String projCategory="";
    private String jobType="";
    private String RevisionNo="";
    private String inDate="";
    private String customer="";
    private String projAuthor="";
    private String projTitle="";
    private String tempResult="";
    
    private List mileStoneEndDateList = new ArrayList();
    private List mileStoneList = new ArrayList();
    private List mileStonecode = new ArrayList();
    
    public void generateReportFields(){
        
        //System.out.println("batch TicketReport:"+"select pma.milestone_act_name,chp.end_date from proj_milestone_act pma,chapter_plan chp where pma.milestone_act_code=chp.milestone_id and chp.chapter_id='"+chapterId+"' ");
        try{
            Connection con = connection.getSampleProperty();
            Statement stmt=con.createStatement();

            
            ResultSet rsChkBatchId = stmt.executeQuery("select batch_id,proj_id,remark from chapter where chapter_id='"+chapterId+"' ");
            
            while(rsChkBatchId.next()){
                tempResult = rsChkBatchId.getString("batch_id");
                if(rsChkBatchId.wasNull()){
                    tempResult="";
                }
                else{
                    chkBatchID = tempResult;
                }
                
                projId = rsChkBatchId.getString("proj_id");
                notes =rsChkBatchId.getString("remark");;
                
            }
            rsChkBatchId.close();
            
            int getMaxBatchId = 0;
            
            if(tempResult.equals(""))
            {
                ResultSet rsGetMaxBatchId = stmt.executeQuery("select max(batch_id) from chapter where batch_id is not null and proj_id='"+ projId+"' order by chapter_id   ");
                while(rsGetMaxBatchId.next()){
                    getMaxBatchId = rsGetMaxBatchId.getInt(1);
                    if(rsGetMaxBatchId.wasNull()){
                    getMaxBatchId=0;
                    }
                    
                }
                rsGetMaxBatchId.close();
                
                getMaxBatchId++;
                
               // System.out.println("getMaxBatchId after incr:"+getMaxBatchId);
                tempResult = String.valueOf(getMaxBatchId);
                
               /* System.out.println("getMaxBatchId after temp result:"+getMaxBatchId);
                System.out.println("tempResult after temp result:"+tempResult);
                */
                int updateBatchID = stmt.executeUpdate("update chapter set batch_id='"+tempResult+"' where chapter_id='"+chapterId+"' and proj_id='"+ projId +"' ");
                if(updateBatchID>0){
                    generatedBatchId = tempResult;
                }
            }
            else{
               generatedBatchId = chkBatchID;
            }
            
            
            ResultSet rsGetMileStoneDetails = stmt.executeQuery("SELECT c.milestone_act_name,DATE_FORMAT(b.end_date, '%d-%b-%Y'), b.plan_auto_id, c.milestone_act_code "
                    + "FROM chapter a,chapter_plan b,proj_milestone_act c,milestone_order mo,project_stage ps "
                    + "WHERE a.chapter_id=b.chapter_id AND b.milestone_id=mo.milestone_act_code AND b.chapter_id='"+chapterId+"' "
                    + "AND mo.stage_code=a.stage AND mo.milestone_act_code=c.milestone_act_code AND ps.stage_code=mo.stage_code "
                    + "ORDER BY b.order_no,mo.milestone_order_number,b.end_date");

            /*System.out.println("SELECT c.milestone_act_name,DATE_FORMAT(b.end_date, '%d-%b-%Y') "
                    + "FROM chapter a,chapter_plan b,proj_milestone_act c,milestone_order mo,project_stage ps "
                    + "WHERE a.chapter_id=b.chapter_id AND b.milestone_id=mo.milestone_act_code AND b.chapter_id='"+chapterId+"'+"
                    + "AND mo.stage_code=a.stage AND mo.milestone_act_code=c.milestone_act_code AND ps.stage_code=mo.stage_code "
                    + "GROUP BY b.milestone_id ORDER BY mo.milestone_order_number");*/
            
            while(rsGetMileStoneDetails.next()){
                
                   tempResult=rsGetMileStoneDetails.getString("c.milestone_act_name");
                    if(rsGetMileStoneDetails.wasNull()){
                      tempResult="";
                    }
                    mileStoneList.add(tempResult);
                
                tempResult=rsGetMileStoneDetails.getString("DATE_FORMAT(b.end_date, '%d-%b-%Y')");
                    if(rsGetMileStoneDetails.wasNull()){
                      tempResult="";
                    }
                    mileStoneEndDateList.add(tempResult);

                    tempResult=rsGetMileStoneDetails.getString("c.milestone_act_code");
                    if(rsGetMileStoneDetails.wasNull()){ tempResult=""; }
                    mileStonecode.add(tempResult);
                    if(tempResult.equals("21")) {
                        plainId = rsGetMileStoneDetails.getString("b.plan_auto_id");
                    }

                    //System.out.println("mileStoneEndDateList;"+mileStoneEndDateList);
        
            }

            ResultSet rsGetFTPLastUpdated = stmt.executeQuery("SELECT DATE_FORMAT(cpl.planned_date,'%d-%b-%Y %T') as planned_date, cpl.logged_by , u.emp_name "
                    + " FROM chapter_plan cp, chapter_plan_log cpl, USER u "
                    + " WHERE cp.plan_auto_id=cpl.milestone_id AND cpl.logged_by=u.emp_id AND cp.milestone_id='21' AND cpl.milestone_id='" + plainId + "' "
                    + " ORDER BY cpl.planned_date DESC LIMIT 1");

            while (rsGetFTPLastUpdated.next()) {
                tempResult = rsGetFTPLastUpdated.getString("planned_date");
                if (rsGetFTPLastUpdated.wasNull()) { tempResult = ""; }
                ftpLastUpdatedTimeStamp = tempResult;

                tempResult = rsGetFTPLastUpdated.getString("u.emp_name");
                if (rsGetFTPLastUpdated.wasNull()) { tempResult = ""; }
                ftpLastUpdatedBy = tempResult;
            }

                rsGetMileStoneDetails.close();
                stmt.close();
                con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting milestone:"+sqle);
        }catch(Exception e){
            e.printStackTrace();
            //System.out.println(e.printStackTrace());
        }
        
        
        
    }
    
    
}
