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
public class SaveApproverAppraisalVO {

    List approvalAppQuesID = new ArrayList();
    List approvalAppRemark = new ArrayList();
    List approvalAppMemberEmpId = new ArrayList();
    List approvalAppEmpId = new ArrayList();
    List approvalAppraisedStatus = new ArrayList();
    List approvalAppSubmitStatus = new ArrayList();
    List approverAppQuesID = new ArrayList();
    List approverAppRemark = new ArrayList();
    List approverAppMemberEmpId = new ArrayList();
    List approverSuperEmpId = new ArrayList();
    List approverAppEmpId = new ArrayList();
    List approverAppStatus = new ArrayList();
    List empNameForApproval = new ArrayList();
    List empIDForApproval = new ArrayList();
    List deptcodeList = new ArrayList();
    String approverAppStartDate = "";
    String approverAppEndDate = "";
    String appraisalCycleCode = "";
    String approvalAppEmpEncryptKey = "";
    String approvalAppSubmitFlag = "";
    String saveOrModifyFlag = "";
    String approvalAppMemberEmpID = "";
    String approvalAppSuperEmpID = "";
    String approvalAppEmpID = "";

    public void setApproverAppStartDate(String approverAppStartDate) {
        this.approverAppStartDate = approverAppStartDate;
    }

    public String getApproverAppStartDate() {
        return approverAppStartDate;
    }

    public void setApproverAppEndDate(String approverAppEndDate) {
        this.approverAppEndDate = approverAppEndDate;
    }

    public String getApproverAppEndDate() {
        return approverAppEndDate;
    }

    public void setCycleCode(String appraisalCycleCode) {
        this.appraisalCycleCode = appraisalCycleCode;
    }

    public String getCycleCode() {
        return appraisalCycleCode;
    }

    public void setApprovalAppEmpEncryptKey(String approvalAppEmpEncryptKey) {
        this.approvalAppEmpEncryptKey = approvalAppEmpEncryptKey;
    }

    public String getApprovalAppEmpEncryptKey() {
        return approvalAppEmpEncryptKey;
    }

    public void setApprovalAppMemberEmpId(String approvalAppMemberEmpID) {
        this.approvalAppMemberEmpID = approvalAppMemberEmpID;
    }

    public String getApprovalAppMemberEmpId() {
        return approvalAppMemberEmpID;
    }

    public void setApprovalAppSuperEmpId(String approvalAppSuperEmpID) {
        this.approvalAppSuperEmpID = approvalAppSuperEmpID;
    }

    public String getApprovalAppSuperEmpId() {
        return approvalAppSuperEmpID;
    }

    public void setApprovalAppEmpId(String approvalAppEmpID) {
        this.approvalAppEmpID = approvalAppEmpID;
    }

    public String getApprovalAppEmpId() {
        return approvalAppEmpID;
    }

    public void setapprovalAppQuesID(List approvalAppQuesID) {
        this.approvalAppQuesID = approvalAppQuesID;
    }

    public List getapprovalAppQuesID() {
        return approvalAppQuesID;
    }

    public void setapprovalAppRemark(List approvalAppRemark) {
        this.approvalAppRemark = approvalAppRemark;
    }

    public List getapprovalAppRemark() {
        return approvalAppRemark;
    }

    public void setapprovalAppMemberEmpId(List approvalAppMemberEmpId) {
        this.approvalAppMemberEmpId = approvalAppMemberEmpId;
    }

    public List getapprovalAppMemberEmpId() {
        return approvalAppMemberEmpId;
    }

    public void setapprovalAppEmpId(List approvalAppEmpId) {
        this.approvalAppEmpId = approvalAppEmpId;
    }

    public List getapprovalAppEmpId() {
        return approvalAppEmpId;
    }

    public void setapprovalAppSubmitStatus(List approvalAppSubmitStatus) {
        this.approvalAppSubmitStatus = approvalAppSubmitStatus;
    }

    public List getapprovalAppSubmitStatus() {
        return approvalAppSubmitStatus;
    }

    public void setapprovalAppSubmitFlag(String approvalAppSubmitFlag) {
        this.approvalAppSubmitFlag = approvalAppSubmitFlag;
    }

    public String getapprovalAppSubmitFlag() {
        return approvalAppSubmitFlag;
    }

    public void setapprovalAppraisedDate(List approvalAppraisedStatus) {
        this.approvalAppraisedStatus = approvalAppraisedStatus;
    }

    public List getapprovalAppraisedDate() {
        return approvalAppraisedStatus;
    }

    public void setSaveOrModifyFlag(String saveOrModifyFlag) {
        this.saveOrModifyFlag = saveOrModifyFlag;
    }

    public String getSaveOrModifyFlag() {
        return saveOrModifyFlag;
    }

    public void setEmpNameForApproval(List empNameForApproval) {
        this.empNameForApproval = empNameForApproval;
    }

    public List getEmpNameForApproval() {
        return empNameForApproval;
    }

    public void setEmpIDForApproval(List empIDForApprovalList) {
        this.empIDForApproval = empIDForApprovalList;
    }

    public List getEmpIDForApproval() {
        return empIDForApproval;
    }
}
