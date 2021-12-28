/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aravindhanj
 */
public class AutomatedTimeSheetVO {
    private String projectId = "";
    private String chapterId = "";
    private ArrayList chapterIdList = new ArrayList();
    private ArrayList chapterList = new ArrayList();
    private ArrayList stageCodeList = new ArrayList();
    private ArrayList stageList = new ArrayList();
    private ArrayList dueDateList = new ArrayList();
    private ArrayList chapterProcessList = new ArrayList();
    private ArrayList batchIdList = new ArrayList();
    private ArrayList shipmentdetails = new ArrayList();
    private String empId = "";
    private String result = "";

    public void setShipmentdetails(ArrayList shipmentdetails) {
        this.shipmentdetails = shipmentdetails;
    }

    public ArrayList getShipmentdetails() {
        return shipmentdetails;
    }

    List bulkMailShipKillList = new ArrayList();

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List getBulkMailShipKillList() {
        return bulkMailShipKillList;
    }

    public void setBulkMailShipKillList(List bulkMailShipKillList) {
        this.bulkMailShipKillList = bulkMailShipKillList;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public ArrayList getChapterIdList() {
        return chapterIdList;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setChapterIdList(ArrayList chapterIdList) {
        this.chapterIdList = chapterIdList;
    }

    public ArrayList getChapterList() {
        return chapterList;
    }

    public void setChapterList(ArrayList chapterList) {
        this.chapterList = chapterList;
    }

    public ArrayList getBatchIdList() {
        return batchIdList;
    }

    public void setBatchIdList(ArrayList batchIdList) {
        this.batchIdList = batchIdList;
    }

    public ArrayList getChapterProcessList() {
        return chapterProcessList;
    }

    public void setChapterProcessList(ArrayList chapterProcessList) {
        this.chapterProcessList = chapterProcessList;
    }

    public ArrayList getDueDateList() {
        return dueDateList;
    }

    public void setDueDateList(ArrayList dueDateList) {
        this.dueDateList = dueDateList;
    }

    public ArrayList getStageCodeList() {
        return stageCodeList;
    }

    public void setStageCodeList(ArrayList stageCodeList) {
        this.stageCodeList = stageCodeList;
    }

    public ArrayList getStageList() {
        return stageList;
    }

    public void setStageList(ArrayList stageList) {
        this.stageList = stageList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
