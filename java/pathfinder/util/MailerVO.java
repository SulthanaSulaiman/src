
package pathfinder.util;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/**
 *
 * @author Aravindhanj
 */
public class MailerVO {
    
    private String projId = "";
   private String projName = "";
    private String chapterName = "";
    private String stage = "";
    private String projDeptCode = "";
    private String stageName = "";
    private String chapters = "";
    private String empId = "";
    private String projloc = "";
    private String client = "";
    private List shipmntnotes = new ArrayList();
    private List recipientList = new ArrayList();
    private List msspageList = new ArrayList();
    private List<String> chapterIdList = new ArrayList<String>();
    private List<String> chapterNameList = new ArrayList<String>();
    private List<String> chapterDueDateList = new ArrayList<String>();
    private List<String> chapterNotesFlagList = new ArrayList<String>();
    private List<String> stageList = new ArrayList<String>();
    private HashSet projectNameforSubject = new HashSet();
    private List<String> chapterMssPgList = new ArrayList<String>();
    private List<String> chapterArtCount = new ArrayList<String>();
    public List<String> getChapterIdList() {
        return chapterIdList;
    }
    public void setChapterIdList(List<String> chapterIdList) {
        this.chapterIdList = chapterIdList;
    }
    public List<String> getMssPageList() {
        return msspageList;
    }
    public List<String> getArtCountList() {
        return chapterArtCount;
    }
    public void setArtCountList(List<String> chapterArtCount) {
        this.chapterArtCount = chapterArtCount;
    }

    public void setMssPageIdList(List<String> msspageList) {
        this.msspageList = msspageList;
    }

    public List<String> getChapterNameList() {
        return chapterNameList;
    }

    public List getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List recipientList) {
        this.recipientList = recipientList;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setChapterNameList(List<String> chapterNameList) {
        this.chapterNameList = chapterNameList;
    }
    
    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public HashSet getProjName1() {
        return projectNameforSubject;
    }

    public void setProjName1(HashSet projectNameforSubject) {
        this.projectNameforSubject = projectNameforSubject;
    }

    public String getChapters() {
        return chapters;
    }

    public void setChapters(String chapters) {
        this.chapters = chapters;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<String> getChapterDueDateList() {
        return chapterDueDateList;
    }

    public void setChapterDueDateList(List<String> chapterDueDateList) {
        this.chapterDueDateList = chapterDueDateList;
    }

    public List<String> getChapterNotesFlagList() {
        return chapterNotesFlagList;
    }

    public void setChapterNotesFlagList(List<String> chapterNotesFlagList) {
        this.chapterNotesFlagList = chapterNotesFlagList;
    }

    public List<String> getStageList() {
        return stageList;
    }

    public void setStageList(List<String> stageList) {
        this.stageList = stageList;
    }

    public String getProjDeptCode() {
        return projDeptCode;
    }

    public void setProjDeptCode(String projDeptCode) {
        this.projDeptCode = projDeptCode;
    }
public void setProjlocation(String projloc) {
        this.projloc = projloc;
    }
public void setShipmentNotes(List shipmntnotes){
        this.shipmntnotes = shipmntnotes;
    }
public List getShipmentNotes(){
return shipmntnotes;
}
public String getClientName(){
    return client;
}
public void setClientName(String client){
this.client = client;
}

}
