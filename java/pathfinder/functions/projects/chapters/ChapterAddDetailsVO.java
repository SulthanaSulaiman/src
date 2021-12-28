package pathfinder.functions.projects.chapters;

public class ChapterAddDetailsVO {

    private String chapterId = "";
    private String chapterName = "";
    private String stage = "";
    private String createDate = "";
    private String shipDate = "";
    private String mssPages = "";
    private String bookMapartcount = "";
    private String castOffPages = "";
    private String totalPages = "";
    private String estPages = "";
    private String proofPages = "";
    private String castOffVariance = "";
    private String castOffRemarks = "";
    private String castOffEmpId = "";
    private String castOffDate="";
    private String projId = "";
    private String workflowId = "";
    private String projWorkflowId = "";
    private String projDeptCode = "";
    private String projWorkflowStageCode = "";
    private String projWorkflowStageName = "";
    String chapterList = "";

    public ChapterAddDetailsVO() {
    }
    
    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getCastOffPages() {
        return castOffPages;
    }

    public void setCastOffPages(String castOffPages) {
        this.castOffPages = castOffPages;
    }

    public String getProjWorkflowId() {
        return this.projWorkflowId;
    }

    public void setProjWorkflowId(String projWorkflowId) {
        this.projWorkflowId = projWorkflowId;
    }

    public String getProjWorkflowStageCode() {
        return this.projWorkflowStageCode;
    }

    public void setProjWorkflowStageCode(String projWorkflowStageCode) {
        this.projWorkflowStageCode = projWorkflowStageCode;
    }

    public String getProjWorkflowStageName() {
        return this.projWorkflowStageName;
    }

    public void setProjWorkflowStageName(String projWorkflowStageName) {
        this.projWorkflowStageName = projWorkflowStageName;
    }

      public String getChapters() {
        return this.chapterList;
    }

    public void setChapters(String chapterList) {
        this.chapterList = chapterList;
    }

    public String getProjectId() {
        return this.projId;
    }

    public void setProjectId(String projId) {
        this.projId = projId;
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

    public String getStage() {
        return this.stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getShipDate() {
        return this.shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getMssPages() {
        return this.mssPages;
    }

    public void setMssPages(String mssPages) {
        this.mssPages = mssPages;
    }
    public String getBookMapartcount() {
        return this.bookMapartcount;
    }

    public void setBookMapartcount(String bookMapartcount) {
        this.bookMapartcount = bookMapartcount;
    }
    //added during cast off interface creation
     public String getEstPages() {
        return this.estPages;
    }

    public void setEstPages(String estPages) {
        this.estPages = estPages;
    }

    public String getProofPages() {
        return this.proofPages;
    }

    public void setProofPages(String proofPages) {
        this.proofPages = proofPages;
    }

     public String getCastOfVariance() {
        return this.castOffVariance;
    }

    public void setCastOfVariance(String castOffVariance) {
        this.castOffVariance = castOffVariance;
    }

    public String getCastOffRmks() {
        return this.castOffRemarks;
    }

    public void setCastOffRmks(String castOffRemarks) {
        this.castOffRemarks = castOffRemarks;
    }

    public String getCastOffEmpId() {
        return this.castOffEmpId;
    }

    public void setCastOffEmpId(String castOffEmpId) {
        this.castOffEmpId = castOffEmpId;
    }

     public void setCastOffDate(String castOffDate) {
        this.castOffDate = castOffDate;
    }

    public String getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getProjDeptCode() {
        return projDeptCode;
    }

    public void setProjDeptCode(String projDeptCode) {
        this.projDeptCode = projDeptCode;
    }
}
