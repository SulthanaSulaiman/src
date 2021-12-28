/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;
import java.util.*;
/**
 *
 * @author Parameshwarant
 */
public class ProjectsByCategoryVO {
   
    List projIDList = new ArrayList();
    List projNameList = new ArrayList();
    List clientNameList = new ArrayList();
    List projDateList = new ArrayList();
    List statusNameList = new ArrayList();

    String categoryID = "";
    String fromDate = "";
    String toDate = "";

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public List getClientNameList() {
        return clientNameList;
    }

    public void setClientNameList(List clientNameList) {
        this.clientNameList = clientNameList;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public List getProjDateList() {
        return projDateList;
    }

    public void setProjDateList(List projDateList) {
        this.projDateList = projDateList;
    }

    public List getProjIDList() {
        return projIDList;
    }

    public void setProjIDList(List projIDList) {
        this.projIDList = projIDList;
    }

    public List getProjNameList() {
        return projNameList;
    }

    public void setProjNameList(List projNameList) {
        this.projNameList = projNameList;
    }

    public List getStatusNameList() {
        return statusNameList;
    }

    public void setStatusNameList(List statusNameList) {
        this.statusNameList = statusNameList;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

}
