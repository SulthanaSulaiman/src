/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raghuramanm
 */
public class AwaitingForFinalsVO {
    String project="";
    String client="";
    String dept="";
    List projectList = new ArrayList();
    List deptList = new ArrayList();
    List clientList = new ArrayList();
    List statusList = new ArrayList();
    List totalPageCountList = new ArrayList();

    public List getStatusList() {
        return statusList;
    }

    public void setStatusList(List statusList) {
        this.statusList = statusList;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public List getProjectList() {
        return projectList;
    }

    public void setProjectList(List projectList) {
        this.projectList = projectList;
    }

    public List getDeptList() {
        return deptList;
    }

    public void setDeptList(List deptList) {
        this.deptList = deptList;
    }

    public List getClientList() {
        return clientList;
    }

    public void setClientList(List clientList) {
        this.clientList = clientList;
    }

    public List getTotalPageCountList() {
        return totalPageCountList;
    }

    public void setTotalPageCountList(List totalPageCountList) {
        this.totalPageCountList = totalPageCountList;
    }
    
    
}
