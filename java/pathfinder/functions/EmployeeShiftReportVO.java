/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions;
import java.util.*;
/**
 *
 * @author Parameshwarant
 */
public class EmployeeShiftReportVO {
    String deptID = "";
    String desigID = "";
    String formDate = "";
    String toDate = "";
    String facilityID = "";

    List empIdList = new ArrayList();
    List empNameList = new ArrayList();
    List deptNameList = new ArrayList();
    List faciliytList = new ArrayList();
    List totWorkingDaysList = new ArrayList();

    String totalDays = "";
    String workingDays = "";
    String paidHolidays = "";
    String unPaidHolidays = "";

    public List getDeptNameList() {
        return deptNameList;
    }

    public void setDeptNameList(List deptNameList) {
        this.deptNameList = deptNameList;
    }

    public List getFaciliytList() {
        return faciliytList;
    }

    public void setFaciliytList(List faciliytList) {
        this.faciliytList = faciliytList;
    }

    
    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public List getEmpIdList() {
        return empIdList;
    }

    public void setEmpIdList(List empIdList) {
        this.empIdList = empIdList;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getDesigID() {
        return desigID;
    }

    public void setDesigID(String desigID) {
        this.desigID = desigID;
    }

    public List getEmpNameList() {
        return empNameList;
    }

    public void setEmpNameList(List empNameList) {
        this.empNameList = empNameList;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List getTotWorkingDaysList() {
        return totWorkingDaysList;
    }

    public void setTotWorkingDaysList(List totWorkingDaysList) {
        this.totWorkingDaysList = totWorkingDaysList;
    }

    public String getPaidHolidays() {
        return paidHolidays;
    }

    public void setPaidHolidays(String paidHolidays) {
        this.paidHolidays = paidHolidays;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }

    public String getUnPaidHolidays() {
        return unPaidHolidays;
    }

    public void setUnPaidHolidays(String unPaidHolidays) {
        this.unPaidHolidays = unPaidHolidays;
    }

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

}
