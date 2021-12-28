/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

/**
 *
 * @author Pattany
 */
public class BillingNotesVO {

    private String projId = "";
    private String billingNotesID="";
    private String billingNotes="";
    private String empID="";
    private String empName="";
    private String date="";
    private String chargeable="";
    private String nonChargeble="";

    public String getBillingNotes() {
        return billingNotes;
    }

    public String getChargeable() {
        return chargeable;
    }

    public void setChargeable(String chargeable) {
        this.chargeable = chargeable;
    }

    public String getNonChargeble() {
        return nonChargeble;
    }

    public void setNonChargeble(String nonChargeble) {
        this.nonChargeble = nonChargeble;
    }

    public void setBillingNotes(String billingNotes) {
        this.billingNotes = billingNotes;
    }

    public String getBillingNotesID() {
        return billingNotesID;
    }

    public void setBillingNotesID(String billingNotesID) {
        this.billingNotesID = billingNotesID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }


}
