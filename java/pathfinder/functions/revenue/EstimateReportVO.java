/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.util.*;

/**
 *
 * @author Raghuramanm
 */
public class EstimateReportVO {

    List monthWiseEst = new ArrayList();
    List monthList = new ArrayList();
    List clientList = new ArrayList();
    List projList = new ArrayList();
    List estValueList = new ArrayList();

    public List getMonthWiseEst() {
        return monthWiseEst;
    }

    public void setMonthWiseEst(List monthWiseEst) {
        this.monthWiseEst = monthWiseEst;
    }

    public List getMonthList() {
        return monthList;
    }

    public void setMonthList(List monthList) {
        this.monthList = monthList;
    }

    public List getClientList() {
        return clientList;
    }

    public void setClientList(List clientList) {
        this.clientList = clientList;
    }

    public List getEstValueList() {
        return estValueList;
    }

    public void setEstValueList(List estValueList) {
        this.estValueList = estValueList;
    }

    public List getProjList() {
        return projList;
    }

    public void setProjList(List projList) {
        this.projList = projList;
    }
}
