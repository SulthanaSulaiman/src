/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects.chapters;
import java.sql.*;
import java.util.*;
import connection.DBconnection;
/**
 *
 * @author Raghuramanm
 */
public class MilestoneSchedulesDAO {
    public MilestoneSchedulesVO getStageFilterRpt(MilestoneSchedulesVO milestoneSchedulesVO){

        String sql="";
        List stageCodeList = new ArrayList();
        List stageNameList = new ArrayList();

        try{
            DBconnection dbconnection = new DBconnection();
            Connection con = dbconnection.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;

            sql = "select stage,stage_code from project_stage";

            rs = st.executeQuery(sql);
            while(rs.next()){
                stageNameList.add(rs.getString(1));
                stageCodeList.add(rs.getString(2));
            }

            milestoneSchedulesVO.setStageCodeList(stageCodeList);
            milestoneSchedulesVO.setStageNameList(stageNameList);
            

        }catch(Exception e){
            System.out.println("Exception in getStageFilterRpt of milestoneSchedulesDAO "+e);
        }
        return milestoneSchedulesVO;
    }
     public MilestoneSchedulesVO getStageFilterRpt1(MilestoneSchedulesVO milestoneSchedulesVO){

        String sql="";
        List milestoneCodeList = new ArrayList();
        List milestoneNameList = new ArrayList();

        try{
            DBconnection dbconnection = new DBconnection();
            Connection con = dbconnection.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;

            sql = "select milestone_act_name,milestone_act_code from proj_milestone_act";

            rs = st.executeQuery(sql);
            while(rs.next()){
                milestoneNameList.add(rs.getString(1));
                milestoneCodeList.add(rs.getString(2));
            }

            milestoneSchedulesVO.setMilestoneCodeList(milestoneCodeList);
            milestoneSchedulesVO.setMilestoneNameList(milestoneNameList);
            System.out.println("MilestoneName"+milestoneNameList);

        }catch(Exception e){
            System.out.println("Exception in getStageFilterRpt1 of milestoneSchedulesDAO "+e);
        }
        return milestoneSchedulesVO;
    }
    public MilestoneSchedulesVO getMilestoneSchedulesReport(MilestoneSchedulesVO milestoneSchedulesVO){

        String clientId = "";
        String projId = "";
        String deptCode = "";
        String stageCode = "";
         String milestoneCode = "";
        String chapStatus = "";
        String dueDateFrom = "";
        String dueDateTo = "";
        String selectQuery="";

        String clientWhereSql = "";
        String projectWhereSql = "";
        String deptWhereSql = "";
        String stageWhereSql = "";
          String milestoneWhereSql = "";
        String chapStatusWhereSql = "";
        String duedateWhereSql = "";
        
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        List clientNameList = new ArrayList();
        List projNameList = new ArrayList();
        List stageList = new ArrayList();
        List chapterList = new ArrayList();
        List milestoneList = new ArrayList();
        List mssCountList = new ArrayList();
        List dueDateList = new ArrayList();
        List plannedDateList = new ArrayList();
        List dueDateChkList = new ArrayList();
        List mileCompletionList = new ArrayList();
        List chapIdList = new ArrayList();
        List milestoneIdList = new ArrayList();

        clientId = milestoneSchedulesVO.getClientId();
        projId = milestoneSchedulesVO.getProjId();
        deptCode = milestoneSchedulesVO.getDeptCode();
        stageCode =  milestoneSchedulesVO.getStageCode();
         milestoneCode =  milestoneSchedulesVO.getMilestoneCode();
         System.out.println("milestoneCode:"+milestoneCode);
        chapStatus = milestoneSchedulesVO.getChapStatus();
        dueDateFrom = milestoneSchedulesVO.getFromDueDate();
        dueDateTo = milestoneSchedulesVO.getToDueDate();
        
        if(!clientId.equals("")) {
            clientWhereSql = " AND p.client_name=" + clientId;
        }
        if(!projId.equals("")) {
            projectWhereSql = " AND c.proj_id=" + projId;
        }

        if(!deptCode.equals("ALL")&&!deptCode.equals("")) {
            deptWhereSql = " AND p.dept_code IN (" + deptCode + ")";
        }
        if(!stageCode.equals("ALL")&&!stageCode.equals("")){
            stageWhereSql = " AND ps.stage_code IN (" + stageCode+ ")";
        }
        if(!milestoneCode.equals("ALL")&&!milestoneCode.equals("")){
            milestoneWhereSql = " AND pma.milestone_act_code=" + milestoneCode;
            System.out.println("MilestoneCode:"+milestoneCode);
        }
        if(!chapStatus.equals("ALL")&&!chapStatus.equals("")){
            chapStatusWhereSql = " AND c.chapter_status=" + chapStatus;
        }
        if(!dueDateFrom.equals("")&&!dueDateTo.equals("")){
            duedateWhereSql = " AND cp.end_date BETWEEN DATE('" + dueDateFrom + "') AND DATE('" + dueDateTo + "') ";
        }
        
        try{
            DBconnection dbconnection = new DBconnection();
            Connection con = dbconnection.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;

            selectQuery = "SELECT CONCAT(cnt.company,'/',divi.company), p.proj_name, ps.stage,c.chapter_no, pma.milestone_act_name, date_format(cp.end_date,'%d-%b-%Y') AS due_date, "
                            + " c.mss_count,date_format(cp.planned_date,'%d-%b-%Y'), case when current_date>date(cp.end_date) then 1 else 0 end as dueDateCheck, "
                            + " a.milestone_comp_flag, cp.chapter_id, cp.milestone_id, MAX(a.end_time)"
                            + " FROM projects p,chapter c, proj_milestone_act pma, project_stage ps, contacts cnt, contacts divi, chapter_plan cp LEFT JOIN activity a "
                            + " ON cp.chapter_id=a.chapter_id and cp.milestone_id=a.milestone_code "
                            + " WHERE c.ship_date IS NULL AND c.due_date IS NOT NULL AND c.chapter_id=cp.chapter_id AND c.stage=ps.stage_code  AND cp.milestone_id=pma.milestone_act_code "
                            + " AND cp.end_date IS NOT NULL AND c.chapter_deleted=0 AND p.project_status NOT IN ('2','21') "
                            +   clientWhereSql + projectWhereSql  +  deptWhereSql + stageWhereSql +milestoneWhereSql+ chapStatusWhereSql + duedateWhereSql
                            + " AND c.proj_id=p.proj_id AND p.client_name=cnt.contact_id AND p.division_id=divi.contact_id "
                            + " group by cp.chapter_id, cp.milestone_id "
                            + " ORDER BY cp.end_date";

            rs = st.executeQuery(selectQuery);
System.out.println("test"+selectQuery);
            while(rs.next()) {
                clientNameList.add(rs.getString(1)!=null ? splChar.decoding(rs.getString(1).toString()) : "");
                projNameList.add(rs.getString(2)!=null ? rs.getString(2).toString() : "");
                stageList.add(rs.getString(3)!=null ? rs.getString(3).toString() : "");
                chapterList.add(rs.getString(4)!=null ? rs.getString(4).toString() : "");
                milestoneList.add(rs.getString(5)!=null ? rs.getString(5).toString() : "");
                dueDateList.add(rs.getString(6)!=null ? rs.getString(6).toString() : "");
                mssCountList.add(rs.getString(7)!=null ? rs.getString(7).toString() : "");
                plannedDateList.add(rs.getString(8)!=null ? rs.getString(8).toString() : "");
                dueDateChkList.add(rs.getString(9)!=null ? rs.getString(9).toString() : "");
                mileCompletionList.add(rs.getString(10)!=null ? rs.getString(10).toString() : "");
                chapIdList.add(rs.getString(11)!=null ? rs.getString(11).toString() : "");
                milestoneIdList.add(rs.getString(12)!=null ? rs.getString(12).toString() : "");
            }

            milestoneSchedulesVO.setClientNameList(clientNameList);
            milestoneSchedulesVO.setProjNameList(projNameList);
            milestoneSchedulesVO.setStageList(stageList);
            milestoneSchedulesVO.setChapterList(chapterList);
            milestoneSchedulesVO.setMilestoneList(milestoneList);
            milestoneSchedulesVO.setDueDateList(dueDateList);
            milestoneSchedulesVO.setMssCountList(mssCountList);
            milestoneSchedulesVO.setPlannedDateList(plannedDateList);
            milestoneSchedulesVO.setDueDateChkList(dueDateChkList);
            milestoneSchedulesVO.setMileCompletionList(mileCompletionList);
            milestoneSchedulesVO.setChapIdList(chapIdList);
            milestoneSchedulesVO.setMilestoneIdList(milestoneIdList);

        }catch(Exception e){
            System.out.println("Exception in getMilestoneSchedulesReport of milestoneSchedulesDAO"+e);
        }
        
        return milestoneSchedulesVO;
    }
}
