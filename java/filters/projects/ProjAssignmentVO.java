/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters.projects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rajac
 */
public class ProjAssignmentVO {

    private String assignedUser = "";
    private String unAssignedProjId = "";
    private String unAssignedProjName = "";
    private String unAssignedProjRecDate = "";

    //For Unassigned Function Project List
    private String unAssignedFunctionProjId = "";
    private String unAssignedFunctionProjName = "";
    private String unAssignedFunctionProjRecDate = "";
    //For Facility List and Emps on Facilities
    private String FcyId = "";
    private String functionFcyId = "";
    private String functionFcyName = "";
    private String functionDeptId = "";
    private String functionDeptName = "";
    private List functionList = new ArrayList();

    //For Unassigned Estimator Project List
    private String unAssignedEstimatorProjId = "";
    private String unAssignedEstimatorProjName = "";
    private String unAssignedEstimatorProjRecDate = "";
    //For Estimator List
    private String estimatorId = "";
    private String estimatorName = "";
    //private String estimatorDeptId = "";
    //private String estimatorDeptName = "";
    //private List estimatorList = new ArrayList();
    
    private String unAssignedCellProjId = "";
    private String unAssignedCellProjName = "";
    private String unAssignedCellProjRecDate = "";
    private String projPlannerID = "";
    private String projCellID = "";
    private List projCellEmpID = new ArrayList();
    private List projEmpFacilityID = new ArrayList();
    private String projDeptCode = "";
    private String projPlannerName = "";
    private String projPlannerDeptCode = "";
    private String projPlannerDesigCode = "";
    private String projPlannerDeptName = "";
    private String projPlannerDesigName = "";

    private List unAsProjId = new ArrayList();
    private List unAsProjName = new ArrayList();
    private List unAsProjClientId = new ArrayList();
    private List unAsProjClientName = new ArrayList();
    private List unAsProjDate = new ArrayList();
    private List unAsProjPlanner = new ArrayList();

    public List getUnAsProjClientId() {
        return unAsProjClientId;
    }

    public void setUnAsProjClientId(List unAsProjClientId) {
        this.unAsProjClientId = unAsProjClientId;
    }

    public List getUnAsProjClientName() {
        return unAsProjClientName;
    }

    public void setUnAsProjClientName(List unAsProjClientName) {
        this.unAsProjClientName = unAsProjClientName;
    }

    public List getUnAsProjDate() {
        return unAsProjDate;
    }

    public void setUnAsProjDate(List unAsProjDate) {
        this.unAsProjDate = unAsProjDate;
    }

    public List getUnAsProjPlanner() {
        return unAsProjPlanner;
    }

    public void setUnAsProjPlanner(List unAsProjPlanner) {
        this.unAsProjPlanner = unAsProjPlanner;
    }

    public List getUnAsProjId() {
        return unAsProjId;
    }

    public void setUnAsProjId(List unAsProjId) {
        this.unAsProjId = unAsProjId;
    }

    public List getUnAsProjName() {
        return unAsProjName;
    }

    public void setUnAsProjName(List unAsProjName) {
        this.unAsProjName = unAsProjName;
    }
    
    public String getUnAssignedEstimatorProjId() {
        return unAssignedEstimatorProjId;
    }

    public void setUnAssignedEstimatorProjId(String unAssignedEstimatorProjId) {
        this.unAssignedEstimatorProjId = unAssignedEstimatorProjId;
    }

    public String getUnAssignedEstimatorProjName() {
        return unAssignedEstimatorProjName;
    }

    public void setUnAssignedEstimatorProjName(String unAssignedEstimatorProjName) {
        this.unAssignedEstimatorProjName = unAssignedEstimatorProjName;
    }

    public String getUnAssignedEstimatorProjRecDate() {
        return unAssignedEstimatorProjRecDate;
    }

    public void setUnAssignedEstimatorProjRecDate(String unAssignedEstimatorProjRecDate) {
        this.unAssignedEstimatorProjRecDate = unAssignedEstimatorProjRecDate;
    }

    public String getEstimatorId() {
        return estimatorId;
    }

    public void setEstimatorId(String estimatorId) {
        this.estimatorId = estimatorId;
    }

    public String getEstimatorName() {
        return estimatorName;
    }

    public void setEstimatorName(String estimatorName) {
        this.estimatorName = estimatorName;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public List getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List functionList) {
        this.functionList = functionList;
    }

    public String getFcyId() {
        return FcyId;
    }

    public void setFcyId(String FcyId) {
        this.FcyId = FcyId;
    }

    public String getFunctionDeptId() {
        return functionDeptId;
    }

    public void setFunctionDeptId(String functionDeptId) {
        this.functionDeptId = functionDeptId;
    }

    public String getFunctionDeptName() {
        return functionDeptName;
    }

    public void setFunctionDeptName(String functionDeptName) {
        this.functionDeptName = functionDeptName;
    }

    public String getFunctionFcyId() {
        return functionFcyId;
    }

    public void setFunctionFcyId(String functionFcyId) {
        this.functionFcyId = functionFcyId;
    }

    public String getFunctionFcyName() {
        return functionFcyName;
    }

    public void setFunctionFcyName(String functionFcyName) {
        this.functionFcyName = functionFcyName;
    }

    public String getUnAssignedFunctionProjId() {
        return unAssignedFunctionProjId;
    }

    public void setUnAssignedFunctionProjId(String unAssignedFunctionProjId) {
        this.unAssignedFunctionProjId = unAssignedFunctionProjId;
    }

    public String getUnAssignedFunctionProjName() {
        return unAssignedFunctionProjName;
    }

    public void setUnAssignedFunctionProjName(String unAssignedFunctionProjName) {
        this.unAssignedFunctionProjName = unAssignedFunctionProjName;
    }

    public String getUnAssignedFunctionProjRecDate() {
        return unAssignedFunctionProjRecDate;
    }

    public void setUnAssignedFunctionProjRecDate(String unAssignedFunctionProjRecDate) {
        this.unAssignedFunctionProjRecDate = unAssignedFunctionProjRecDate;
    }

    public ProjAssignmentVO() {
    }

    public String getUnAssignedProjId() {
        return this.unAssignedProjId;
    }

    public void setUnAssignedProjId(String unAssignedProjId) {
        this.unAssignedProjId = unAssignedProjId;
    }

    public String getUnAssignedProjName() {
        return this.unAssignedProjName;
    }

    public void setUnAssignedProjName(String unAssignedProjName) {
        this.unAssignedProjName = unAssignedProjName;
    }

    public String getUnAssignedProjRecDate() {
        return this.unAssignedProjRecDate;
    }

    public void setUnAssignedProjRecDate(String unAssignedProjRecDate) {
        this.unAssignedProjRecDate = unAssignedProjRecDate;
    }

    public String getUnAssignedCellProjId() {
        return this.unAssignedCellProjId;
    }

    public void setUnAssignedCellProjId(String unAssignedCellProjId) {
        this.unAssignedCellProjId = unAssignedCellProjId;
    }

    public String getUnAssignedCellProjName() {
        return this.unAssignedCellProjName;
    }

    public void setUnAssignedCellProjName(String unAssignedCellProjName) {
        this.unAssignedCellProjName = unAssignedCellProjName;
    }

    public String getUnAssignedCellProjRecDate() {
        return this.unAssignedCellProjRecDate;
    }

    public void setUnAssignedCellProjRecDate(String unAssignedCellProjRecDate) {
        this.unAssignedCellProjRecDate = unAssignedCellProjRecDate;
    }

    public String getProjPlannerDeptCode() {
        return this.projPlannerDeptCode;
    }

    public void setProjPlannerDeptCode(String projPlannerDeptCode) {
        this.projPlannerDeptCode = projPlannerDeptCode;
    }

    public String getProjPlannerDeptName() {
        return this.projPlannerDeptName;
    }

    public void setProjPlannerDeptName(String projPlannerDeptName) {
        this.projPlannerDeptName = projPlannerDeptName;
    }

    public String getProjPlannerDesigCode() {
        return this.projPlannerDesigCode;
    }

    public void setProjPlannerDesigCode(String projPlannerDesigCode) {
        this.projPlannerDesigCode = projPlannerDesigCode;
    }

    public String getProjPlannerDesigName() {
        return this.projPlannerDesigName;
    }

    public void setProjPlannerDesigName(String projPlannerDesigName) {
        this.projPlannerDesigName = projPlannerDesigName;
    }

    public String getProjPlannerID() {
        return this.projPlannerID;
    }

    public void setProjPlannerID(String projPlannerID) {
        this.projPlannerID = projPlannerID;
    }

    public String getProjPlannerName() {
        return this.projPlannerName;
    }

    public void setProjPlannerName(String projPlannerName) {
        this.projPlannerName = projPlannerName;
    }

    public String getProjCellID() {
        return this.projCellID;
    }

    public void setProjCellID(String projCellID) {
        this.projCellID = projCellID;
    }

    public String getProjDeptCode() {
        return this.projDeptCode;
    }

    public void setProjDeptCode(String projDeptCode) {
        this.projDeptCode = projDeptCode;
    }

    public List getProjCellEmpID() {
        return this.projCellEmpID;
    }

    public void setProjCellEmpID(List projCellEmpID) {
        this.projCellEmpID = projCellEmpID;
    }

    public List getProjEmpFacilityID() {
        return this.projEmpFacilityID;
    }

    public void setProjEmpFacilityID(List projEmpFacilityID) {
        this.projEmpFacilityID = projEmpFacilityID;
    }
}
