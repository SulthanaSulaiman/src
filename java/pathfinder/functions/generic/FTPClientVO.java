
package pathfinder.functions.generic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aravindhanj
 */
public class FTPClientVO {
    private String projectId = "";
    private List ftpIdList = new ArrayList();
    private List ftpServerList = new ArrayList();
    private List ftpPathList = new ArrayList();
    private List usernameList = new ArrayList();
    private List passwordList = new ArrayList();
    private int result = 0;
    
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List getFtpPathList() {
        return ftpPathList;
    }

    public List getFtpIdList() {
        return ftpIdList;
    }

    public void setFtpIdList(List ftpIdList) {
        this.ftpIdList = ftpIdList;
    }

    public void setFtpPathList(List ftpPathList) {
        this.ftpPathList = ftpPathList;
    }

    public List getFtpServerList() {
        return ftpServerList;
    }

    public void setFtpServerList(List ftpServerList) {
        this.ftpServerList = ftpServerList;
    }

    public List getPasswordList() {
        return passwordList;
    }

    public void setPasswordList(List passwordList) {
        this.passwordList = passwordList;
    }

    public List getUsernameList() {
        return usernameList;
    }

    public void setUsernameList(List usernameList) {
        this.usernameList = usernameList;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    
}
