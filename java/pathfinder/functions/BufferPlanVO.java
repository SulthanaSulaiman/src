
package pathfinder.functions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aravindhanj
 */
public class BufferPlanVO {

    String customerId = "";
    String empId = "";
    String proj_id="";
    String stage_code="";
    List bufferPlanIdList = new ArrayList();
    List bufferMilestoneCodeList =  new ArrayList();
    List customerIdList = new ArrayList();
    List customerNameList = new ArrayList();
    List stageCodeList = new ArrayList();
    List stageNameList = new ArrayList();
    List milestoneActCodeList = new ArrayList();
    List milestoneActNameList = new ArrayList();
    List bufferDaysList = new ArrayList();
    List lastModifiedByList = new ArrayList();
    List lastModifiedDateList = new ArrayList();
    int result = 0;

    public List getBufferMilestoneCodeList() {
        return bufferMilestoneCodeList;
    }

    public void setBufferMilestoneCodeList(List bufferMilestoneCodeList) {
        this.bufferMilestoneCodeList = bufferMilestoneCodeList;
    }

    public String getProj_id() {
        return proj_id;
    }

    public void setProj_id(String proj_id) {
        this.proj_id = proj_id;
    }

    public String getStage_code() {
        return stage_code;
    }

    public void setStage_code(String stage_code) {
        this.stage_code = stage_code;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public List getBufferDaysList() {
        return bufferDaysList;
    }

    public void setBufferDaysList(List bufferDaysList) {
        this.bufferDaysList = bufferDaysList;
    }

    public List getBufferPlanIdList() {
        return bufferPlanIdList;
    }

    public void setBufferPlanIdList(List bufferPlanIdList) {
        this.bufferPlanIdList = bufferPlanIdList;
    }
    
    public List getCustomerIdList() {
        return customerIdList;
    }

    public void setCustomerIdList(List customerIdList) {
        this.customerIdList = customerIdList;
    }

    public List getCustomerNameList() {
        return customerNameList;
    }

    public void setCustomerNameList(List customerNameList) {
        this.customerNameList = customerNameList;
    }

    public List getLastModifiedByList() {
        return lastModifiedByList;
    }

    public void setLastModifiedByList(List lastModifiedByList) {
        this.lastModifiedByList = lastModifiedByList;
    }

    public List getLastModifiedDateList() {
        return lastModifiedDateList;
    }

    public void setLastModifiedDateList(List lastModifiedDateList) {
        this.lastModifiedDateList = lastModifiedDateList;
    }

    public List getMilestoneActCodeList() {
        return milestoneActCodeList;
    }

    public void setMilestoneActCodeList(List milestoneActCodeList) {
        this.milestoneActCodeList = milestoneActCodeList;
    }

    public List getMilestoneActNameList() {
        return milestoneActNameList;
    }

    public void setMilestoneActNameList(List milestoneActNameList) {
        this.milestoneActNameList = milestoneActNameList;
    }

    public List getStageCodeList() {
        return stageCodeList;
    }

    public void setStageCodeList(List stageCodeList) {
        this.stageCodeList = stageCodeList;
    }

    public List getStageNameList() {
        return stageNameList;
    }

    public void setStageNameList(List stageNameList) {
        this.stageNameList = stageNameList;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    
}
