/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;
import java.util.*;
/**
 *
 * @author Pattany
 */
public class ActivityVO {

    private List activityCode = new ArrayList();
    private List activityName = new ArrayList();
    private List activityBillable = new ArrayList();
    private List activityProductive = new ArrayList();
    private List generalActivity = new ArrayList();

    public ActivityVO() {
    }

    public List getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(List activityCode) {
        this.activityCode = activityCode;
    }


    public List getActivityBillable() {
        return activityBillable;
    }

    public void setActivityBillable(List activityBillable) {
        this.activityBillable = activityBillable;
    }

    public List getActivityName() {
        return activityName;
    }

    public void setActivityName(List activityName) {
        this.activityName = activityName;
    }

    public List getActivityProductive() {
        return activityProductive;
    }

    public void setActivityProductive(List activityProductive) {
        this.activityProductive = activityProductive;
    }

    public List getGeneralActivity() {
        return generalActivity;
    }

    public void setGeneralActivity(List generalActivity) {
        this.generalActivity = generalActivity;
    }

    


}
