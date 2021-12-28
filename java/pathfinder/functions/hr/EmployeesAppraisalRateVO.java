/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;

import java.util.*;

/**
 *
 * @author Parameshwarant
 */
public class EmployeesAppraisalRateVO {

    List supervisorRateList = new ArrayList();
    List supervisorNameList = new ArrayList();
    List approvalAuthorityRateList = new ArrayList();
    List approvalAuthorityNameList = new ArrayList();
    List cycleIdList = new ArrayList();
    List cycleNameList = new ArrayList();
    List cycleYearList = new ArrayList();
    List cycleCodeList = new ArrayList();
    List empIdList = new ArrayList();
    List empNameList = new ArrayList();
    List appraisalYearList = new ArrayList();
    List appraisalCycleIdList = new ArrayList();
    List appraisalCycleCountList = new ArrayList();
    HashMap overallAppraisal = new HashMap();
    List deptcodeList = new ArrayList();
    List deptNameList = new ArrayList();
    String empId = "";
    String cycleId = "";
    String appName = "";
    String appRate = "";
    String supName = "";
    String supRate = "";
    String cycleYear = "";
    String cycleDescription = "";
    String appraisalYearId = "";
    String dept_code="";
    List supervisorRateL = new ArrayList();
    List approverRate = new ArrayList();
    List selfappstatuscheck = new ArrayList();
    String dept_codeForfilter = "";

    public String getDept_codeForfilter() {
        return dept_codeForfilter;
    }

    public void setDept_codeForfilter(String dept_codeForfilter) {
        this.dept_codeForfilter = dept_codeForfilter;
    }

    
    public void setSelfappstatuscheck(List selfappstatuscheck) {
        this.selfappstatuscheck = selfappstatuscheck;
    }

    public List getSelfappstatuscheck() {
        return selfappstatuscheck;
    }


    public void setApproverRate(List approverRate) {
        this.approverRate = approverRate;
    }

    public List getApproverRate() {
        return approverRate;
    }

    public void setSupervisorRateL(List supervisorRateL) {
        this.supervisorRateL = supervisorRateL;
    }

    public List getSupervisorRateL() {
        return supervisorRateL;
    }
    

    public void setDeptNameList(List deptNameList) {
        this.deptNameList = deptNameList;
    }

    public List getDeptNameList() {
        return deptNameList;
    }

    public void setDeptcodeList(List deptcodeList) {
        this.deptcodeList = deptcodeList;
    }

    public List getDeptcodeList() {
        return deptcodeList;
    }

    

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getDept_code() {
        return dept_code;
    }

    public String getCycleYear() {
        return cycleYear;
    }

    public void setCycleYear(String cycleYear) {
        this.cycleYear = cycleYear;
    }

    

    public List getCycleYearList() {
        return cycleYearList;
    }

    public void setCycleYearList(List cycleYearList) {
        this.cycleYearList = cycleYearList;
    }

   
    public void setCycleID(List cycleIdList){
        this.cycleIdList=cycleIdList;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCycleDescription() {
        return cycleDescription;
    }

    public void setCycleDescription(String cycleDescription) {
        this.cycleDescription = cycleDescription;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getAppRate() {
        return appRate;
    }

    public void setAppRate(String appRate) {
        this.appRate = appRate;
    }

    public String getSupRate() {
        return supRate;
    }

    public void setSupRate(String supRate) {
        this.supRate = supRate;
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
    public List getCycleID(){
        return cycleIdList;
    }

    public void setCycleName(List cycleNameList){
        this.cycleNameList=cycleNameList;
    }
    public List getCycleName(){
        return cycleNameList;
    }

    public void setCycleCode(List cycleCodeList){
        this.cycleCodeList=cycleCodeList;
    }
    public List getCycleCode(){
        return cycleCodeList;
    }

    public void setSupervisorRate(List supervisorRateList){
        this.supervisorRateList=supervisorRateList;
    }
    public List getSupervisorRate(){
        return supervisorRateList;
    }

    public void setApprovalAuthorityRate(List approvalAuthorityRateList){
        this.approvalAuthorityRateList=approvalAuthorityRateList;
    }
    public List getApprovalAuthorityRate(){
        return approvalAuthorityRateList;
    }

    public List getCycleIdList() {
        return cycleIdList;
    }

    public void setCycleIdList(List cycleIdList) {
        this.cycleIdList = cycleIdList;
    }

    public List getCycleNameList() {
        return cycleNameList;
    }

    public void setCycleNameList(List cycleNameList) {
        this.cycleNameList = cycleNameList;
    }

    public List getEmpIdList() {
        return empIdList;
    }

    public void setEmpIdList(List empIdList) {
        this.empIdList = empIdList;
    }

    public List getEmpNameList() {
        return empNameList;
    }

    public void setEmpNameList(List empNameList) {
        this.empNameList = empNameList;
    }

    public HashMap getOverallAppraisal() {
        return overallAppraisal;
    }

    public void setOverallAppraisal(HashMap overallAppraisal) {
        this.overallAppraisal = overallAppraisal;
    }

    public List getAppraisalYearList() {
        return appraisalYearList;
    }

    public void setAppraisalYearList(List appraisalYearList) {
        this.appraisalYearList = appraisalYearList;
    }

    public String getAppraisalYearId() {
        return appraisalYearId;
    }

    public void setAppraisalYearId(String appraisalYearId) {
        this.appraisalYearId = appraisalYearId;
    }

    public List getAppraisalCycleCountList() {
        return appraisalCycleCountList;
    }

    public void setAppraisalCycleCountList(List appraisalCycleCountList) {
        this.appraisalCycleCountList = appraisalCycleCountList;
    }

    public List getAppraisalCycleIdList() {
        return appraisalCycleIdList;
    }

    public void setAppraisalCycleIdList(List appraisalCycleIdList) {
        this.appraisalCycleIdList = appraisalCycleIdList;
    }
    

}
