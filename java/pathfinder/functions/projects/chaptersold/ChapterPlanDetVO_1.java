package pathfinder.functions.projects.chaptersold;

import java.util.HashMap;

/**
 * This Value Object (VO) is used to set and get chapter plan details.
 * @author Thanu
 */
public class ChapterPlanDetVO_1 {

    private String chapterId = "";
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


    public ChapterPlanDetVO_1() {
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

