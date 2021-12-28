/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;
import java.sql.*;
import java.util.*;
import connection.DBconnection;
/**
 *
 * @author Raghuramanm
 */
public class FollowLoadDAO {
    public FollowLoadVO getStageDetails(FollowLoadVO followLoadVO){
        String projId="";
        List projWrkflowList = new ArrayList();
        List projMilestoneList = new ArrayList();
        List projStageList = new ArrayList();
        List projStageCodeList = new ArrayList();
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            projId = followLoadVO.getProjId();
            String stageQuery="SELECT pwmp.workflow_id,pwmp.milestone_code,ps.stage,ps.stage_code "
                            +"FROM project_workflow_milestone_map pwmp,project_workflow_master pwm,project_stage ps "
                            +"WHERE pwmp.workflow_id=(SELECT proj_workflow FROM projects WHERE proj_id='"+projId+"') "
                            +"AND pwmp.workflow_id=pwm.workflow_id AND pwmp.milestone_code=ps.stage_code";
            System.out.println("the stage query "+stageQuery);
            ResultSet rs = st.executeQuery(stageQuery);
            while(rs.next()){
                if(rs.getString(1)!=null){
                    projWrkflowList.add(rs.getString(1));
                }else{
                    projWrkflowList.add("");
                }
                if(rs.getString(2)!=null){
                    projMilestoneList.add(rs.getString(2));
                }else{
                    projMilestoneList.add("");
                }
                if(rs.getString(3)!=null){
                    projStageList.add(rs.getString(3));
                }else{
                    projStageList.add("");
                }
                if(rs.getString(4)!=null){
                    projStageCodeList.add(rs.getString(4));
                }else{
                    projStageCodeList.add("");
                }
            }
            followLoadVO.setProjMilestoneList(projMilestoneList);
            followLoadVO.setProjStageList(projStageList);
            followLoadVO.setProjWrkflowList(projWrkflowList);
            followLoadVO.setProjStageCodeList(projStageCodeList);

        }catch(Exception e){
            System.out.println("The Exception in getStageDetails() from FollowLoadVO "+e);
        }
        return followLoadVO;
    }

    public FollowLoadVO getFollowLoad(FollowLoadVO followLoadVO){
        String projId="";
        List scheduleId = new ArrayList();
        List projStageList = new ArrayList();
        List projStageCodeSchedule =  new ArrayList();
        ArrayList fromDateList = new ArrayList();
        ArrayList toDateList = new ArrayList();
        ArrayList inputPagesList = new ArrayList();
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            projId = followLoadVO.getProjId();
            String stage_query="SELECT ps.stage,fl.from_date,fl.to_date,fl.input_page_count,fl.stage_code,fl.follow_load_id FROM follow_load fl, project_stage ps WHERE ps.stage_code=fl.stage_code AND fl.proj_id='"+projId+"'";
            //System.out.println("the stage query "+stage_query);
            ResultSet rs = st.executeQuery(stage_query);
            while(rs.next()){
                if(rs.getString(1)!=null){
                    projStageList.add(rs.getString(1));
                }else{
                    projStageList.add("");
                }
                if(rs.getString(2)!=null){
                    fromDateList.add(rs.getString(2));
                }else{
                    fromDateList.add("");
                }
                if(rs.getString(3)!=null){
                    toDateList.add(rs.getString(3));
                }else{
                    toDateList.add("");
                }
                if(rs.getString(4)!=null){
                    inputPagesList.add(rs.getString(4));
                }else{
                    inputPagesList.add("");
                }
                if(rs.getString(5)!=null){
                    projStageCodeSchedule.add(rs.getString(5));
                }else{
                    projStageCodeSchedule.add("");
                }
                if(rs.getString(6)!=null){
                    scheduleId.add(rs.getString(6));
                }else{
                    scheduleId.add("");
                }
            }
            followLoadVO.setProjStageList(projStageList);
            followLoadVO.setFromDateList(fromDateList);
            followLoadVO.setToDateList(toDateList);
            followLoadVO.setInputPageList(inputPagesList);
            followLoadVO.setProjStageCodeSchedule(projStageCodeSchedule);
            followLoadVO.setScheduleId(scheduleId);
        }catch(Exception e){
            System.out.println("The Exception in getSchedule() from FollowLoadVO "+e);
        }
        return followLoadVO;
    }
    public FollowLoadVO addFollowLoad(FollowLoadVO followLoadVO){
        int saveVar=0;
        int rowCount=0;
        int tempSave=0;
        String sql="";
        String projId="";
        String sesEmpId="";
        ArrayList projStagesList = new ArrayList();
        ArrayList fromDateList = new ArrayList();
        ArrayList toDateList = new ArrayList();
        ArrayList inputPageList = new ArrayList();
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();
            //PreparedStatement ps = null;

            projId = followLoadVO.getProjId();
            sesEmpId = followLoadVO.getSesEmpId();
            projStagesList = followLoadVO.getProjStagesList();
            fromDateList = followLoadVO.getFromDateList();
            toDateList = followLoadVO.getToDateList();
            inputPageList = followLoadVO.getInputPageList();

            
                /*sql=" INSERT INTO follow_load(proj_id,stage_code,from_date,to_date,input_page_count,created_by,created_date,last_modified_by, "
                        +" last_modified_date) VALUES('"+projId+"', '"+projStagesList.get(index)+"', '"+fromDateList.get(index)+"', '"+toDateList.get(index)+"',"
                        +" '"+inputPageList.get(index)+"', '"+sesEmpId+"', current_timestamp(), '"+sesEmpId+"', current_timestamp())";*/

                sql = "INSERT INTO follow_load (proj_id,stage_code,from_date,to_date,input_page_count,created_by,created_date,last_modified_by, "
                        +"last_modified_date) VALUES(?,?,?,?,?,?,current_timestamp(),?,current_timestamp())";

                PreparedStatement ps = con.prepareStatement(sql);

            for(int index=0; index<projStagesList.size(); index++){
                ps.setString(1, projId);
                ps.setString(2, projStagesList.get(index).toString());
                ps.setString(3, fromDateList.get(index).toString());
                ps.setString(4, toDateList.get(index).toString());
                ps.setString(5, inputPageList.get(index).toString());
                ps.setString(6, sesEmpId);
                ps.setString(7, sesEmpId);

                saveVar = ps.executeUpdate();
            }

            followLoadVO.setSaveVar(saveVar);
            followLoadVO.setTempSave(rowCount);

        }catch(Exception e){
            System.out.println("The Exception in addSchedule() from FollowLoadVO()"+e);
        }
        return followLoadVO;
    }
    public FollowLoadVO updateFollowLoad(FollowLoadVO followLoadVO){
        int updateVar=0;
        int rowCount=0;
        int tempSave=0;
        String sql="";
        String sesEmpId="";
        
        List scheduleIdUpdate = new ArrayList();
        List startDateUpdate = new ArrayList();
        List endDateUpdate = new ArrayList();
        List inputPageUpdate = new ArrayList();
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();
            PreparedStatement ps = null;

            sesEmpId = followLoadVO.getSesEmpId();
            scheduleIdUpdate = followLoadVO.getScheduleIdUpdate();
            startDateUpdate = followLoadVO.getStartDateUpdate();
            endDateUpdate = followLoadVO.getEndDateUpdate();
            inputPageUpdate = followLoadVO.getInputPageUpdate();

            
                /*sql=" update follow_load set from_date='"+startDateUpdate.get(index)+"', to_date='"+endDateUpdate.get(index)+"', input_page_count='"+inputPageUpdate.get(index)
                        +"', last_modified_by='"+sesEmpId+"', last_modified_date = current_timestamp() where follow_load_id='"+scheduleIdUpdate.get(index)+"'";*/

                sql = "update follow_load set from_date=?, to_date=?, input_page_count=?, last_modified_by=?, last_modified_date=current_timestamp() where follow_load_id=?";
                ps = con.prepareStatement(sql);

            for(int index=0; index<scheduleIdUpdate.size(); index++){
                ps.setString(1, startDateUpdate.get(index).toString());
                ps.setString(2, endDateUpdate.get(index).toString());
                ps.setString(3, inputPageUpdate.get(index).toString());
                ps.setString(4, sesEmpId);
                ps.setString(5, scheduleIdUpdate.get(index).toString());

                updateVar = ps.executeUpdate();
            }
            followLoadVO.setUpdateVar(updateVar);
            
        }catch(Exception e){
            System.out.println("The Exception in updateSchedule() from FollowLoadVO"+e);
        }
        return followLoadVO;
    }
    public FollowLoadVO removeFollowLoad(FollowLoadVO followLoadVO){
        int removeVar=0;
        
        String sql="";

        List scheduleIdUpdate = new ArrayList();

        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            scheduleIdUpdate = followLoadVO.getScheduleIdUpdate();
            
            for(int index=0; index<scheduleIdUpdate.size(); index++){
                sql=" delete from follow_load where follow_load_id='"+scheduleIdUpdate.get(index)+"'";

                //System.out.println("query update "+sql);
                removeVar = st.executeUpdate(sql);

            }
        followLoadVO.setRemoveVar(removeVar);

        }catch(Exception e){
            System.out.println("The Exception in updateSchedule() from FollowLoadVO"+e);
        }
        return followLoadVO;
    }
    public FollowLoadVO getFollowLoadReport(FollowLoadVO followLoadVO){

        String clientId = "";
        String plannerId = "";
        String deptCode = "";
        String followFromDate = "";
        String followToDate = "";
        String selectQuery="";

        String clientWhereSql = "";
        String plannerWhereSql = "";
        String deptWhereSql = "";
        String followDateWhereSql = "";

        ArrayList clientNameList = new ArrayList();
        ArrayList departmentNameList = new ArrayList();
        ArrayList projNameList = new ArrayList();
        ArrayList plannerNameList = new ArrayList();
        ArrayList stageNameList = new ArrayList();
        ArrayList fromDateList = new ArrayList();
        ArrayList toDateList = new ArrayList();
        ArrayList inputPageCountList = new ArrayList();

        clientId = followLoadVO.getClientId();
        plannerId = followLoadVO.getPlannerId();
        deptCode = followLoadVO.getDeptCode();
        followFromDate = followLoadVO.getFollowFromDate();
        followToDate = followLoadVO.getFollowToDate();

        //System.out.println(deptCode);

        if(!clientId.equals("")) {
            clientWhereSql = " AND p.client_name=" + clientId;
        }
        if(!plannerId.equals("")) {
            plannerWhereSql = " AND p.planner=" + plannerId;
        }

        if(!deptCode.equals("")) {
            deptWhereSql = " AND p.dept_code IN (" + deptCode + ")";
        }
        if(!followFromDate.equals("") && !followToDate.equals("")) {
            followDateWhereSql = " AND fl.to_date BETWEEN DATE('" + followFromDate + "') AND DATE('" + followToDate + "') ";
        }

        try{
            DBconnection dbconnection = new DBconnection();
            Connection con = dbconnection.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;

            selectQuery = " SELECT c.company, d.department, p.proj_name, e.emp_name, ps.stage, fl.from_date, fl.to_date, fl.input_page_count "
                    + " FROM follow_load fl, project_stage ps, contacts c, projects p "
                    + " LEFT JOIN USER e ON p.planner=e.emp_id "
                    + " LEFT JOIN department d ON p.dept_code=d.dept_code " 
                    + " WHERE p.client_name=c.contact_id " + clientWhereSql + plannerWhereSql + followDateWhereSql +  deptWhereSql +" AND fl.proj_id=p.proj_id AND fl.stage_code=ps.stage_code AND p.project_status NOT IN (21,2) ORDER BY p.proj_name";

            //System.out.println("Query:    " + selectQuery);
            rs = st.executeQuery(selectQuery);

            while(rs.next()) {
                clientNameList.add(rs.getString(1)!=null ? rs.getString(1).toString() : "");
                departmentNameList.add(rs.getString(2)!=null ? rs.getString(2).toString() : "");
                projNameList.add(rs.getString(3)!=null ? rs.getString(3).toString() : "");
                plannerNameList.add(rs.getString(4)!=null ? rs.getString(4).toString() : "");
                stageNameList.add(rs.getString(5)!=null ? rs.getString(5).toString() : "");
                fromDateList.add(rs.getString(6)!=null ? rs.getString(6).toString() : "");
                toDateList.add(rs.getString(7)!=null ? rs.getString(7).toString() : "");
                inputPageCountList.add(rs.getString(8)!=null ? rs.getString(8).toString() : "");
            }

            followLoadVO.setClientNameList(clientNameList);
            followLoadVO.setDepartmentNameList(departmentNameList);
            followLoadVO.setProjNameList(projNameList);
            followLoadVO.setPlannerNameList(plannerNameList);
            followLoadVO.setStageNameList(stageNameList);
            followLoadVO.setFromDateList(fromDateList);
            followLoadVO.setToDateList(toDateList);
            followLoadVO.setInputPageCountList(inputPageCountList);

        }catch(Exception e){
            System.out.println("The Exception in getFollowLoadReport() of FollowLoadDAO()"+e);
        }
        
        return followLoadVO;
    }
}
