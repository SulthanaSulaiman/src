/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;
import java.util.*;
/**
 *
 * @author Raghuramanm
 */
public class FollowLoadVO {

    String projId="";
    String sesEmpId="";
    String clientId = "";
    String plannerId = "";
    String deptCode = "";
    String followFromDate = "";
    String followToDate = "";
    String[] multiDept;

    int saveVar;
    int tempSave;
    int updateVar;
    int removeVar;
    
    List projWrkflowList = new ArrayList();
    List projMilestoneList = new ArrayList();
    List projStageList = new ArrayList();
    List scheduleId = new ArrayList();
    List projStageCodeList = new ArrayList();
    List projStageCodeSchedule = new ArrayList();
    List scheduleIdUpdate = new ArrayList();
    List startDateUpdate = new ArrayList();
    List endDateUpdate = new ArrayList();
    List inputPageUpdate = new ArrayList();
    ArrayList projStagesList = new ArrayList();
    ArrayList fromDateList = new ArrayList();
    ArrayList toDateList = new ArrayList();
    ArrayList inputPageList = new ArrayList();
    ArrayList clientNameList = new ArrayList();
    ArrayList departmentNameList = new ArrayList();
    ArrayList projNameList = new ArrayList();
    ArrayList plannerNameList = new ArrayList();
    ArrayList stageNameList = new ArrayList();
    ArrayList inputPageCountList = new ArrayList();
    ArrayList totalPagesList = new ArrayList();

    public ArrayList getTotalPagesList() {
        return totalPagesList;
    }

    public void setTotalPagesList(ArrayList totalPagesList) {
        this.totalPagesList = totalPagesList;
    }
    
    public String[] getMultiDept() {
        return multiDept;
    }

    public void setMultiDept(String[] multiDept) {
        this.multiDept = multiDept;
    }
    
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public ArrayList getClientNameList() {
        return clientNameList;
    }

    public void setClientNameList(ArrayList clientNameList) {
        this.clientNameList = clientNameList;
    }

    public ArrayList getDepartmentNameList() {
        return departmentNameList;
    }

    public void setDepartmentNameList(ArrayList departmentNameList) {
        this.departmentNameList = departmentNameList;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getFollowFromDate() {
        return followFromDate;
    }

    public void setFollowFromDate(String followFromDate) {
        this.followFromDate = followFromDate;
    }

    public String getFollowToDate() {
        return followToDate;
    }

    public void setFollowToDate(String followToDate) {
        this.followToDate = followToDate;
    }

    public ArrayList getInputPageCountList() {
        return inputPageCountList;
    }

    public void setInputPageCountList(ArrayList inputPageCountList) {
        this.inputPageCountList = inputPageCountList;
    }

    public String getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(String plannerId) {
        this.plannerId = plannerId;
    }

    public ArrayList getPlannerNameList() {
        return plannerNameList;
    }

    public void setPlannerNameList(ArrayList plannerNameList) {
        this.plannerNameList = plannerNameList;
    }

    public ArrayList getProjNameList() {
        return projNameList;
    }

    public void setProjNameList(ArrayList projNameList) {
        this.projNameList = projNameList;
    }

    public ArrayList getStageNameList() {
        return stageNameList;
    }

    public void setStageNameList(ArrayList stageNameList) {
        this.stageNameList = stageNameList;
    }

    
    public int getRemoveVar() {
        return removeVar;
    }

    public void setRemoveVar(int removeVar) {
        this.removeVar = removeVar;
    }
    
    public int getUpdateVar() {
        return updateVar;
    }

    public void setUpdateVar(int updateVar) {
        this.updateVar = updateVar;
    }
    
    public List getEndDateUpdate() {
        return endDateUpdate;
    }

    public void setEndDateUpdate(List endDateUpdate) {
        this.endDateUpdate = endDateUpdate;
    }

    public List getInputPageUpdate() {
        return inputPageUpdate;
    }

    public void setInputPageUpdate(List inputPageUpdate) {
        this.inputPageUpdate = inputPageUpdate;
    }

    public List getScheduleIdUpdate() {
        return scheduleIdUpdate;
    }

    public void setScheduleIdUpdate(List scheduleIdUpdate) {
        this.scheduleIdUpdate = scheduleIdUpdate;
    }

    public List getStartDateUpdate() {
        return startDateUpdate;
    }

    public void setStartDateUpdate(List startDateUpdate) {
        this.startDateUpdate = startDateUpdate;
    }
    
    public List getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(List scheduleId) {
        this.scheduleId = scheduleId;
    }
    
    public List getProjStageCodeSchedule() {
        return projStageCodeSchedule;
    }

    public void setProjStageCodeSchedule(List projStageCodeSchedule) {
        this.projStageCodeSchedule = projStageCodeSchedule;
    }
    
    public int getSaveVar() {
        return saveVar;
    }

    public int getTempSave() {
        return tempSave;
    }

    public void setTempSave(int tempSave) {
        this.tempSave = tempSave;
    }
    
    public void setSaveVar(int saveVar) {
        this.saveVar = saveVar;
    }
    
    public String getSesEmpId() {
        return sesEmpId;
    }

    public void setSesEmpId(String sesEmpId) {
        this.sesEmpId = sesEmpId;
    }
    
    public ArrayList getFromDateList() {
        return fromDateList;
    }

    public void setFromDateList(ArrayList fromDateList) {
        this.fromDateList = fromDateList;
    }

    public ArrayList getInputPageList() {
        return inputPageList;
    }

    public void setInputPageList(ArrayList inputPageList) {
        this.inputPageList = inputPageList;
    }

    public ArrayList getProjStagesList() {
        return projStagesList;
    }

    public void setProjStagesList(ArrayList projStagesList) {
        this.projStagesList = projStagesList;
    }

    public ArrayList getToDateList() {
        return toDateList;
    }

    public void setToDateList(ArrayList toDateList) {
        this.toDateList = toDateList;
    }
    
    public List getProjStageCodeList() {
        return projStageCodeList;
    }

    public void setProjStageCodeList(List projStageCodeList) {
        this.projStageCodeList = projStageCodeList;
    }
    
    public List getProjMilestoneList() {
        return projMilestoneList;
    }

    public void setProjMilestoneList(List projMilestoneList) {
        this.projMilestoneList = projMilestoneList;
    }

    public List getProjStageList() {
        return projStageList;
    }

    public void setProjStageList(List projStageList) {
        this.projStageList = projStageList;
    }

    public List getProjWrkflowList() {
        return projWrkflowList;
    }

    public void setProjWrkflowList(List projWrkflowList) {
        this.projWrkflowList = projWrkflowList;
    }
    
    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
    
}
