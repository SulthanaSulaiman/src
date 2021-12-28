/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;
import java.util.*;
/**
 *
 * @author Raghuramanm
 */
public class ManageAppraisalVO {

    int saveVar;
    int updateVar;
    String selfAppStart="";
    String selfAppEnd="";
    String supAppStart="";
    String supAppEnd="";
    String appAppStart="";
    String appAppEnd="";
    String appraisalYear="";
    String appraisalMonth="";
    String appraisalDesc="";
    String totalCycles="";
    String totalCyclesYear="";
    List cycleIdList = new ArrayList();
    List cycleYearList = new ArrayList();
    List cycleMonthList = new ArrayList();
    List cycleDescList = new ArrayList();
    List selfStartList = new ArrayList();
    List selfEndList = new ArrayList();
    List supStartList = new ArrayList();
    List supEndList = new ArrayList();
    List appStartList = new ArrayList();
    List appEndList = new ArrayList();

    public String getTotalCyclesYear() {
        return totalCyclesYear;
    }

    public void setTotalCyclesYear(String totalCyclesYear) {
        this.totalCyclesYear = totalCyclesYear;
    }
    
    //List totalCycleList = new ArrayList();

    /*public List getTotalCycleList() {
        return totalCycleList;
    }

    public void setTotalCycleList(List totalCycleList) {
        this.totalCycleList = totalCycleList;
    }*/
    
    public String getTotalCycles() {
        return totalCycles;
    }

    public void setTotalCycles(String totalCycles) {
        this.totalCycles = totalCycles;
    }
    
    public List getCycleIdList() {
        return cycleIdList;
    }

    public void setCycleIdList(List cycleIdList) {
        this.cycleIdList = cycleIdList;
    }
    
    public List getAppEndList() {
        return appEndList;
    }

    public void setAppEndList(List appEndList) {
        this.appEndList = appEndList;
    }

    public List getAppStartList() {
        return appStartList;
    }

    public void setAppStartList(List appStartList) {
        this.appStartList = appStartList;
    }

    public List getCycleDescList() {
        return cycleDescList;
    }

    public void setCycleDescList(List cycleDescList) {
        this.cycleDescList = cycleDescList;
    }

    public List getCycleMonthList() {
        return cycleMonthList;
    }

    public void setCycleMonthList(List cycleMonthList) {
        this.cycleMonthList = cycleMonthList;
    }

    public List getCycleYearList() {
        return cycleYearList;
    }

    public void setCycleYearList(List cycleYearList) {
        this.cycleYearList = cycleYearList;
    }

    public List getSelfEndList() {
        return selfEndList;
    }

    public void setSelfEndList(List selfEndList) {
        this.selfEndList = selfEndList;
    }

    public List getSelfStartList() {
        return selfStartList;
    }

    public void setSelfStartList(List selfStartList) {
        this.selfStartList = selfStartList;
    }

    public List getSupEndList() {
        return supEndList;
    }

    public void setSupEndList(List supEndList) {
        this.supEndList = supEndList;
    }

    public List getSupStartList() {
        return supStartList;
    }

    public void setSupStartList(List supStartList) {
        this.supStartList = supStartList;
    }
    
    public int getUpdateVar() {
        return updateVar;
    }

    public void setUpdateVar(int updateVar) {
        this.updateVar = updateVar;
    }

    public int getSaveVar() {
        return saveVar;
    }

    public void setSaveVar(int saveVar) {
        this.saveVar = saveVar;
    }

    public String getAppraisalDesc() {
        return appraisalDesc;
    }

    public void setAppraisalDesc(String appraisalDesc) {
        this.appraisalDesc = appraisalDesc;
    }

    public String getAppraisalMonth() {
        return appraisalMonth;
    }

    public void setAppraisalMonth(String appraisalMonth) {
        this.appraisalMonth = appraisalMonth;
    }

    public String getAppraisalYear() {
        return appraisalYear;
    }

    public void setAppraisalYear(String appraisalYear) {
        this.appraisalYear = appraisalYear;
    }

    public String getAppAppEnd() {
        return appAppEnd;
    }

    public void setAppAppEnd(String appAppEnd) {
        this.appAppEnd = appAppEnd;
    }

    public String getAppAppStart() {
        return appAppStart;
    }

    public void setAppAppStart(String appAppStart) {
        this.appAppStart = appAppStart;
    }

    public String getSelfAppEnd() {
        return selfAppEnd;
    }

    public void setSelfAppEnd(String selfAppEnd) {
        this.selfAppEnd = selfAppEnd;
    }

    public String getSelfAppStart() {
        return selfAppStart;
    }

    public void setSelfAppStart(String selfAppStart) {
        this.selfAppStart = selfAppStart;
    }

    public String getSupAppEnd() {
        return supAppEnd;
    }

    public void setSupAppEnd(String supAppEnd) {
        this.supAppEnd = supAppEnd;
    }

    public String getSupAppStart() {
        return supAppStart;
    }

    public void setSupAppStart(String supAppStart) {
        this.supAppStart = supAppStart;
    }

}
