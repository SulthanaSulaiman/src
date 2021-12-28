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
public class ForwardLoadDAO {
    public ForwardLoadVO getStageDetails(ForwardLoadVO forwardLoadVO){
        String projId="";
        List projWrkflowList = new ArrayList();
        List projMilestoneList = new ArrayList();
        List projStageList = new ArrayList();
        List projStageCodeList = new ArrayList();
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            projId = forwardLoadVO.getProjId();
            String stageQuery="SELECT pwmp.workflow_id,pwmp.milestone_code,ps.stage,ps.stage_code "
                            +"FROM project_workflow_milestone_map pwmp,project_workflow_master pwm,project_stage ps "
                            +"WHERE pwmp.workflow_id=(SELECT proj_workflow FROM projects WHERE proj_id='"+projId+"') "
                            +"AND pwmp.workflow_id=pwm.workflow_id AND pwmp.milestone_code=ps.stage_code";
            
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
            forwardLoadVO.setProjMilestoneList(projMilestoneList);
            forwardLoadVO.setProjStageList(projStageList);
            forwardLoadVO.setProjWrkflowList(projWrkflowList);
            forwardLoadVO.setProjStageCodeList(projStageCodeList);

        }catch(Exception e){
            System.out.println("The Exception in getStageDetails() from ForwardLoadVO "+e);
        }
        return forwardLoadVO;
    }

    public ForwardLoadVO getForwardLoad(ForwardLoadVO forwardLoadVO){
        String projId="";
        List scheduleId = new ArrayList();
        List projStageList = new ArrayList();
        List projStageCodeList = new ArrayList();
        ArrayList fromDateList = new ArrayList();
        ArrayList toDateList = new ArrayList();
        ArrayList inputPagesList = new ArrayList();
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            projId = forwardLoadVO.getProjId();
            //String stage_query="SELECT ps.stage,fl.from_date,fl.to_date,fl.input_page_count,fl.stage_code,fl.forward_load_id FROM forward_load fl, project_stage ps WHERE ps.stage_code=fl.stage_code AND fl.proj_id='"+projId+"'";
            String stage_query = " SELECT ps.stage, ps.stage_code, fl.from_date,fl.to_date,fl.input_page_count, fl.forward_load_id "
                                + " FROM project_workflow_milestone_map pwmp,project_workflow_master pwm,project_stage ps left join forward_load fl on  ps.stage_code=fl.stage_code and fl.proj_id='"+projId+"' "
                                + " WHERE pwmp.workflow_id=(SELECT proj_workflow FROM projects WHERE proj_id='"+projId+"') AND pwmp.workflow_id=pwm.workflow_id "
                                + " AND pwmp.milestone_code=ps.stage_code ORDER BY ps.order";
            
            ResultSet rs = st.executeQuery(stage_query);
            while(rs.next()){
                if(rs.getString(1)!=null){
                    projStageList.add(rs.getString(1));
                }else{
                    projStageList.add("");
                }
                if(rs.getString(2)!=null){
                    projStageCodeList.add(rs.getString(2));
                }else{
                    projStageCodeList.add("");
                }
                if(rs.getString(3)!=null){
                    fromDateList.add(rs.getString(3));
                }else{
                    fromDateList.add("");
                }
                if(rs.getString(4)!=null){
                    toDateList.add(rs.getString(4));
                }else{
                    toDateList.add("");
                }
                if(rs.getString(5)!=null){
                    inputPagesList.add(rs.getString(5));
                }else{
                    inputPagesList.add("");
                }
                if(rs.getString(6)!=null){
                    scheduleId.add(rs.getString(6));
                }else{
                    scheduleId.add("");
                }
            }
            forwardLoadVO.setProjStageList(projStageList);
            forwardLoadVO.setProjStageCodeList(projStageCodeList);
            forwardLoadVO.setFromDateList(fromDateList);
            forwardLoadVO.setToDateList(toDateList);
            forwardLoadVO.setInputPageList(inputPagesList);
            forwardLoadVO.setScheduleId(scheduleId);
        }catch(Exception e){
            System.out.println("The Exception in getSchedule() from ForwardLoadVO "+e);
        }
        return forwardLoadVO;
    }
    public ForwardLoadVO addForwardLoad(ForwardLoadVO forwardLoadVO){
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

            projId = forwardLoadVO.getProjId();
            sesEmpId = forwardLoadVO.getSesEmpId();
            projStagesList = forwardLoadVO.getProjStagesList();
            fromDateList = forwardLoadVO.getFromDateList();
            toDateList = forwardLoadVO.getToDateList();
            inputPageList = forwardLoadVO.getInputPageList();

            
                /*sql=" INSERT INTO forward_load(proj_id,stage_code,from_date,to_date,input_page_count,created_by,created_date,last_modified_by, "
                        +" last_modified_date) VALUES('"+projId+"', '"+projStagesList.get(index)+"', '"+fromDateList.get(index)+"', '"+toDateList.get(index)+"',"
                        +" '"+inputPageList.get(index)+"', '"+sesEmpId+"', current_timestamp(), '"+sesEmpId+"', current_timestamp())";*/

                sql = "INSERT INTO forward_load (proj_id,stage_code,from_date,to_date,input_page_count,created_by,created_date,last_modified_by, "
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

            forwardLoadVO.setSaveVar(saveVar);
            forwardLoadVO.setTempSave(rowCount);

        }catch(Exception e){
            System.out.println("The Exception in addForwardLoad() from ForwardLoadDAO()"+e);
        }
        return forwardLoadVO;
    }
    public ForwardLoadVO updateForwardLoad(ForwardLoadVO forwardLoadVO){
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

            sesEmpId = forwardLoadVO.getSesEmpId();
            scheduleIdUpdate = forwardLoadVO.getScheduleIdUpdate();
            startDateUpdate = forwardLoadVO.getStartDateUpdate();
            endDateUpdate = forwardLoadVO.getEndDateUpdate();
            inputPageUpdate = forwardLoadVO.getInputPageUpdate();

            
                /*sql=" update forward_load set from_date='"+startDateUpdate.get(index)+"', to_date='"+endDateUpdate.get(index)+"', input_page_count='"+inputPageUpdate.get(index)
                        +"', last_modified_by='"+sesEmpId+"', last_modified_date = current_timestamp() where forward_load_id='"+scheduleIdUpdate.get(index)+"'";*/

                sql = "update forward_load set from_date=?, to_date=?, input_page_count=?, last_modified_by=?, last_modified_date=current_timestamp() where forward_load_id=?";
                ps = con.prepareStatement(sql);

            for(int index=0; index<scheduleIdUpdate.size(); index++){
                ps.setString(1, startDateUpdate.get(index).toString());
                ps.setString(2, endDateUpdate.get(index).toString());
                ps.setString(3, inputPageUpdate.get(index).toString());
                ps.setString(4, sesEmpId);
                ps.setString(5, scheduleIdUpdate.get(index).toString());

                updateVar = ps.executeUpdate();
            }
            forwardLoadVO.setUpdateVar(updateVar);
            
        }catch(Exception e){
            System.out.println("The Exception in updateSchedule() from ForwardLoadVO"+e);
        }
        return forwardLoadVO;
    }
    public ForwardLoadVO removeForwardLoad(ForwardLoadVO forwardLoadVO){
        int removeVar=0;
        
        String sql="";

        List scheduleIdUpdate = new ArrayList();

        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            scheduleIdUpdate = forwardLoadVO.getScheduleIdUpdate();
            
            for(int index=0; index<scheduleIdUpdate.size(); index++){
                sql=" delete from forward_load where forward_load_id='"+scheduleIdUpdate.get(index)+"'";

                removeVar = st.executeUpdate(sql);

            }
        forwardLoadVO.setRemoveVar(removeVar);

        }catch(Exception e){
            System.out.println("The Exception in updateSchedule() from ForwardLoadVO"+e);
        }
        return forwardLoadVO;
    }
    public ForwardLoadVO getStageFilterRpt(ForwardLoadVO forwardLoadVO){

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

            forwardLoadVO.setStageCodeList(stageCodeList);
            forwardLoadVO.setStageList(stageNameList);

        }catch(Exception e){
            System.out.println("Exception in getStageFilterRpt of ForwardLoadDAO "+e);
        }
        return forwardLoadVO;
    }
    public ForwardLoadVO getForwardLoadReport(ForwardLoadVO forwardLoadVO){

        String clientId = "";
        String plannerId = "";
        String deptCode = "";
        String stageCode = "";
        String forwardFromDate = "";
        String forwardToDate = "";
        String selectQuery="";

        String clientWhereSql = "";
        String plannerWhereSql = "";
        String deptWhereSql = "";
        String stageWhereSql = "";
        String forwardDateWhereSql = "";

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        ArrayList clientNameList = new ArrayList();
        ArrayList departmentNameList = new ArrayList();
        ArrayList projNameList = new ArrayList();
        ArrayList plannerNameList = new ArrayList();
        ArrayList stageNameList = new ArrayList();
        ArrayList fromDateList = new ArrayList();
        ArrayList toDateList = new ArrayList();
        ArrayList inputPageCountList = new ArrayList();
        ArrayList stageOrderList = new ArrayList();

        clientId = forwardLoadVO.getClientId();
        plannerId = forwardLoadVO.getPlannerId();
        deptCode = forwardLoadVO.getDeptCode();
        stageCode =  forwardLoadVO.getStageCode();
        forwardFromDate = forwardLoadVO.getForwardFromDate();
        forwardToDate = forwardLoadVO.getForwardToDate();

        if(!clientId.equals("")) {
            clientWhereSql = " AND p.client_name=" + clientId;
        }
        if(!plannerId.equals("")) {
            plannerWhereSql = " AND p.planner=" + plannerId;
        }

        if(!deptCode.equals("")) {
            deptWhereSql = " AND p.dept_code IN (" + deptCode + ")";
        }
        if(!stageCode.equals("")){
            stageWhereSql = " AND ps.stage_code IN (" + stageCode+ ")";
        }
        if(!forwardFromDate.equals("") && !forwardToDate.equals("")) {
            forwardDateWhereSql = " AND fl.to_date BETWEEN DATE('" + forwardFromDate + "') AND DATE('" + forwardToDate + "') ";
        }

        try{
            DBconnection dbconnection = new DBconnection();
            Connection con = dbconnection.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;

            selectQuery = " SELECT c.company, d.department, p.proj_name, e.emp_name, ps.stage, fl.from_date, fl.to_date, fl.input_page_count, ps.order "
                    + " FROM forward_load fl, project_stage ps, contacts c, projects p "
                    + " LEFT JOIN USER e ON p.planner=e.emp_id "
                    + " LEFT JOIN department d ON p.dept_code=d.dept_code " 
                    + " WHERE p.client_name=c.contact_id " + clientWhereSql + plannerWhereSql + forwardDateWhereSql +  deptWhereSql + stageWhereSql
                    + " AND fl.proj_id=p.proj_id AND fl.stage_code=ps.stage_code AND p.project_status NOT IN (21,2) ORDER BY p.proj_name ";

            rs = st.executeQuery(selectQuery);

            while(rs.next()) {
                clientNameList.add(rs.getString(1)!=null ? splChar.decoding(rs.getString(1).toString()) : "");
                departmentNameList.add(rs.getString(2)!=null ? rs.getString(2).toString() : "");
                projNameList.add(rs.getString(3)!=null ? rs.getString(3).toString() : "");
                plannerNameList.add(rs.getString(4)!=null ? rs.getString(4).toString() : "");
                stageNameList.add(rs.getString(5)!=null ? rs.getString(5).toString() : "");
                fromDateList.add(rs.getString(6)!=null ? rs.getString(6).toString() : "");
                toDateList.add(rs.getString(7)!=null ? rs.getString(7).toString() : "");
                inputPageCountList.add(rs.getString(8)!=null ? rs.getString(8).toString() : "");
                stageOrderList.add(rs.getString(9)!=null ? rs.getString(9) : "0");
            }

            forwardLoadVO.setClientNameList(clientNameList);
            forwardLoadVO.setDepartmentNameList(departmentNameList);
            forwardLoadVO.setProjNameList(projNameList);
            forwardLoadVO.setPlannerNameList(plannerNameList);
            forwardLoadVO.setStageNameList(stageNameList);
            forwardLoadVO.setFromDateList(fromDateList);
            forwardLoadVO.setToDateList(toDateList);
            forwardLoadVO.setInputPageCountList(inputPageCountList);
            forwardLoadVO.setStageOrderList(stageOrderList);

        }catch(Exception e){
            System.out.println("The Exception in getForwardLoadReport() of ForwardLoadDAO()"+e);
        }
        
        return forwardLoadVO;
    }
}
