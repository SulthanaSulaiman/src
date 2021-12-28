package pathfinder.functions.projects.chaptersold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This Value Object (VO) is used to set and get chapter plan details.
 * @author Thanu
 */
public class ChapterPlanDetVO {

    private String chapterId = "";
    private String chapterStgCode = "";
    private String chapterName = "";
    private String planId = "";
    private String endDate = "";
    private String plannedDate = "";
    private HashMap mileStone = new HashMap();
    private String projId = "";
    private String maxPlanId = "";
    private String mileStoneId = "";
    private String mileStoneName = "";
    private String copyChapterId = "";
    private String beforeEditmileStoneID = "";
    private String beforeEditmileStoneED = "";
    private String EditmileStoneOrderNo = "";
    private String mssCount = "";
    private String artCount = "";
    private String remark = "";
    private String process = "";
    private String chapterOrderId = "";
    private List mileStoneBook = new ArrayList();
    private List mileStoneBookend = new ArrayList();
    private List autoFillFlag = new ArrayList();
    private String projMappedOnoffShore = "";

    public List getAutoFillFlag() {
        return autoFillFlag;
    }

    public void setAutoFillFlag(List autoFillFlag) {
        this.autoFillFlag = autoFillFlag;
    }
public void setOnOffShore(String projMappedOnoffShore){
this.projMappedOnoffShore = projMappedOnoffShore;
}
    public String getChapterOrderId() {
        return chapterOrderId;
    }

    public void setChapterOrderId(String chapterOrderId) {
        this.chapterOrderId = chapterOrderId;
    }

    public List getMileStoneBookend() {
        return mileStoneBookend;
    }

    public void setMileStoneBookend(List mileStoneBookend) {
        this.mileStoneBookend = mileStoneBookend;
    }

    public List getMileStoneBook() {
        return mileStoneBook;
    }

    public void setMileStoneBook(List mileStoneBook) {
        this.mileStoneBook = mileStoneBook;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setMssCount(String mssCount) {
        this.mssCount = mssCount;
    }
public void setArtCount(String artCount) {
        this.artCount = artCount;
    }
public String getArtCount() {
        return artCount;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    

    public String getMssCount() {
        return mssCount;
    }

    public String getRemark() {
        return remark;
    }


    public ChapterPlanDetVO() {
    }

    public void setCopyChapterId(String copyChapterId)
    {
        this.copyChapterId = copyChapterId;
    }
    public String getCopyChapterId()
    {
        return this.copyChapterId;
    }

    public String getProjectId() {
        return this.projId;
    }

    public void setProjectId(String projId) {
        this.projId = projId;
    }

    public String getMaxPlanId() {
        return this.maxPlanId;
    }

    public void setMaxPlanId(String maxPlanId) {
        this.maxPlanId = maxPlanId;
    }

    public String getMileStoneId() {
        return this.mileStoneId;
    }

    public void setMileStoneId(String mileStoneId) {
        this.mileStoneId = mileStoneId;
    }

    public String getEditmileStoneOrderNo() {
        return this.EditmileStoneOrderNo;
    }

    public void setEditmileStoneOrderNo(String EditmileStoneOrderNo) {
        this.EditmileStoneOrderNo = EditmileStoneOrderNo;
    }

    public String getBeforeEditMilestoneDate() {
        return this.beforeEditmileStoneED;
    }

    public void setBeforeEditMilestoneDate(String beforeEditmileStoneED) {
        this.beforeEditmileStoneED = beforeEditmileStoneED;
    }

    public String getBeforeEditMilestoneID() {
        return this.beforeEditmileStoneID;
    }

    public void setBeforeEditMilestoneID(String beforeEditmileStoneID) {
        this.beforeEditmileStoneID = beforeEditmileStoneID;
    }

    public String getMileStoneName() {
        return this.mileStoneName;
    }

    public void setMileStoneName(String mileStoneName) {
        this.mileStoneName = mileStoneName;
    }

    public String getChapterId() {
        return this.chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterStgCode() {
        return this.chapterStgCode;
    }

    public void setChapterStgCode(String chapterStgCode) {
        this.chapterStgCode = chapterStgCode;
    }

    public String getChapterName() {
        return this.chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getPlanId() {
        return this.planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlannedDate() {
        return this.plannedDate;
    }

    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
    }

    public HashMap getMileStone() {
        return this.mileStone;
    }

    public void setMileStone(HashMap mileStone) {
        this.mileStone = mileStone;
    }
}

