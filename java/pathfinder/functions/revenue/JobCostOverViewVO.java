/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import java.util.ArrayList;

/**
 *
 * @author rajac
 */
public class JobCostOverViewVO {

    private String projId = "";
    private String projName = "";
    //private String dept_function = "";
    private String FPBH = "";
    private String FPBC = "";
    private String RPBH = "";
    private String RPBC = "";
    private String CHNERRHR = "";
    private String CHNERRCT = "";
    private String DBQERRHR = "";
    private String DBQERRCT = "";
    private String OUTERRHR = "";
    private String OUTERRCT = "";
    private String TOTBH = "";
    private String TOTBC = "";
    private String activity = "";
    private String milestone = "";
    private String category = "";
    private double unPosted = 0.0;
    private ArrayList jobcostCategoryIDList = new ArrayList();
    private ArrayList jobcostCategoryValueList = new ArrayList();
    private ArrayList jobcostCategoryAltIDList = new ArrayList();
    private ArrayList jobcostCategoryAltValueList = new ArrayList();
    private String wipValueList;
    private String toDate;
    private String facilityID;
    private ArrayList categoryId = new ArrayList();
    private ArrayList categoryName = new ArrayList();
    private ArrayList categoryValue = new ArrayList();

    //Method for WIP
    
    public ArrayList getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ArrayList categoryId) {
        this.categoryId = categoryId;
    }

    public ArrayList getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(ArrayList categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(ArrayList categoryValue) {
        this.categoryValue = categoryValue;
    }
    
    public String getWipValueList() {
        return wipValueList;
    }

    public void setWipValueList(String wipValueList) {
        this.wipValueList = wipValueList;
    }
    
    
    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public ArrayList getJobcostCategoryAltIDList() {
        return jobcostCategoryAltIDList;
    }

    public void setJobcostCategoryAltIDList(ArrayList jobcostCategoryAltIDList) {
        this.jobcostCategoryAltIDList = jobcostCategoryAltIDList;
    }

    public ArrayList getJobcostCategoryAltValueList() {
        return jobcostCategoryAltValueList;
    }

    public void setJobcostCategoryAltValueList(ArrayList jobcostCategoryAltValueList) {
        this.jobcostCategoryAltValueList = jobcostCategoryAltValueList;
    }

    public ArrayList getJobcostCategoryIDList() {
        return jobcostCategoryIDList;
    }

    public void setJobcostCategoryIDList(ArrayList jobcostCategoryIDList) {
        this.jobcostCategoryIDList = jobcostCategoryIDList;
    }

    public ArrayList getJobcostCategoryValueList() {
        return jobcostCategoryValueList;
    }

    public void setJobcostCategoryValueList(ArrayList jobcostCategoryValueList) {
        this.jobcostCategoryValueList = jobcostCategoryValueList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getProjId() {
        return this.projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return this.projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getFPBC() {
        return this.FPBC;
    }

    public void setFPBC(String FPBC) {
        this.FPBC = FPBC;
    }

    public String getFPBH() {
        return this.FPBH;
    }

    public void setFPBH(String FPBH) {
        this.FPBH = FPBH;
    }

    public String getRPBC() {
        return this.RPBC;
    }

    public void setRPBC(String RPBC) {
        this.RPBC = RPBC;
    }

    public String getCHNERRHR() {
        return CHNERRHR;
    }

    public void setCHNERRHR(String CHNERRHR) {
        this.CHNERRHR = CHNERRHR;
    }

    public String getCHNERRCT() {
        return CHNERRCT;
    }

    public void setCHNERRCT(String CHNERRCT) {
        this.CHNERRCT = CHNERRCT;
    }

    public String getDBQERRHR() {
        return DBQERRHR;
    }

    public void setDBQERRHR(String DBQERRHR) {
        this.DBQERRHR = DBQERRHR;
    }

    public String getDBQERRCT() {
        return DBQERRCT;
    }

    public void setDBQERRCT(String DBQERRCT) {
        this.DBQERRCT = DBQERRCT;
    }

    public String getOUTERRHR() {
        return OUTERRHR;
    }

    public void setOUTERRHR(String OUTERRHR) {
        this.OUTERRHR = OUTERRHR;
    }

    public String getOUTERRCT() {
        return OUTERRCT;
    }

    public void setOUTERRCT(String OUTERRCT) {
        this.OUTERRCT = OUTERRCT;
    }

    public String getRPBH() {
        return this.RPBH;
    }

    public void setRPBH(String RPBH) {
        this.RPBH = RPBH;
    }

    public String getTOTBC() {
        return this.TOTBC;
    }

    public void setTOTBC(String TOTBC) {
        this.TOTBC = TOTBC;
    }

    public String getTOTBH() {
        return this.TOTBH;
    }

    public void setTOTBH(String TOTBH) {
        this.TOTBH = TOTBH;
    }

    public double getUnPosted() {
        return unPosted;
    }

    public void setUnPosted(double unPosted) {
        this.unPosted = unPosted;
    }

    /*
    public String getDeptFunction() {
        return this.dept_function;
    }

    public void setDeptFunction(String dept_function) {
        this.dept_function = dept_function;
    }
     *
     */

}
