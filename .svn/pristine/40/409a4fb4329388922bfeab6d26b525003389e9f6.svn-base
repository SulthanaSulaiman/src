/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Raghuramanm
 */
public class FeedbackDAO {

    public FeedbackVO getEmployeeName(FeedbackVO feedbackVO){
        
        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        String sql="";
        String employeeName="";
        String employeeDesig="";
        String employeeId = feedbackVO.getEmployeeId();
        
        try{

            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            sql="select u.emp_name, d.designation from user u, designation d where u.desig_code=d.desig_code and u.emp_id='"+employeeId+"' ";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                employeeName = resultSet.getString(1);
                employeeDesig = resultSet.getString(2);
            }
            feedbackVO.setEmployeeName(employeeName);
            feedbackVO.setDesig(employeeDesig);
            
        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - getEmployeeName()" + e);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - getEmployeeName()" + ex);
            }
        }
        return feedbackVO;
    }
    public FeedbackVO getFeedbackReport(FeedbackVO feedbackVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql="";
        String where="";
        String myIssueID = feedbackVO.getMyIssueID();

        String addedBy = "";
        String severity = "";
        String feedbackType = "";
        String feedback = "";
        String feedbackDate = "";
        String corrDate = "";
        String  prevDate = "";
        String corrAction = "";
        String prevAction = "";
        String projId="";
        try{

            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            sql="select issue_type, severity_code, corrective_action, corr_action_date, preventive_action, "
                    + "prevent_action_date, feedback, feedback_date, added_by, proj_id from issue where issue_id='"+myIssueID+"' ";

            //System.out.println("the feedback query "+sql);
            
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                if(resultSet.getString(1)!=null)
                    feedbackType=resultSet.getString(1);
                
                if(resultSet.getString(2)!=null)
                    severity=resultSet.getString(2);
                
                if(resultSet.getString(3)!=null)
                    corrAction=resultSet.getString(3);
                
                if(resultSet.getString(4)!=null)
                    corrDate=resultSet.getString(4);
                
                if(resultSet.getString(5)!=null)
                    prevAction=resultSet.getString(5);
                
                if(resultSet.getString(6)!=null)
                    prevDate=resultSet.getString(6);
                
                if(resultSet.getString(7)!=null)
                    feedback=resultSet.getString(7);
                
                if(resultSet.getString(8)!=null)
                    feedbackDate=resultSet.getString(8);
                
                if(resultSet.getString(9)!=null)
                    addedBy=resultSet.getString(9);
               // if(resultSet.getString(10)!=null)
                  //  projId=resultSet.getString(10);

            }

                feedbackVO.setFeedbackType(feedbackType);
                feedbackVO.setSeverity(severity);
                feedbackVO.setCorrectiveAction(corrAction);
                feedbackVO.setCorrectiveDate(corrDate);
                feedbackVO.setPreventiveAction(prevAction);
                feedbackVO.setPreventiveDate(prevDate);
                feedbackVO.setFeedbck(feedback);
                feedbackVO.setFeedbckDate(feedbackDate);
                feedbackVO.setAddBy(addedBy);
               // feedbackVO.setProjId(projId);

        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - getFeedbackReport()" + e);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - getFeedbackReport()" + ex);
            }
        }

        return feedbackVO;
    }
    
    public FeedbackVO getEmployees(FeedbackVO feedbackVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql="";
        String where="";
        String dept = feedbackVO.getDept();
        String desig = feedbackVO.getDesig();
        String sesEmp = feedbackVO.getSesEmpId();

        List empName = new ArrayList();
        List empId = new ArrayList();

        try{

            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            sql="SELECT emp_id,emp_name from user";
            where=" where status='1'";
            if(dept.equals("ALL")) {
                   where += " and dept_code is not null ";
            }   else {
                   where += " and dept_code = '" + dept + "' ";
            }
            if(desig.equals("ALL")) {
                where += " and desig_code is not null ";
            }    else {
                    where += " and desig_code = '" + desig + "' ";
            }
            sql += where;
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                empId.add(resultSet.getString(1));
                   empName.add(resultSet.getString(2));
            }

            feedbackVO.setEmpId(empId);
            feedbackVO.setEmpName(empName);
            
            
        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - getEmployees()" + e);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - getEmployees()" + ex);
            }
        }

        return feedbackVO;
    }

    public FeedbackVO getSeverity(FeedbackVO feedbackVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql="";
        List severityCode = new ArrayList();
        List severityVal = new ArrayList();
        try{
            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            sql="select severity_code,severity_type from issue_severity";
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                severityCode.add(resultSet.getString(1));
                severityVal.add(resultSet.getString(2));
            }

            feedbackVO.setSeverityCodeList(severityCode);
            feedbackVO.setSeverityValList(severityVal);
            
        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - getSeverity()" + e);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - getSeverity()" + ex);
            }
        }
        return feedbackVO;
    }

    public FeedbackVO getFeedbackType(FeedbackVO feedbackVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql="";
        List feedTypeCodeList = new ArrayList();
        List feedTypeValList = new ArrayList();
        try{
            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            sql="select issue_type,issue from issue_type";
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                feedTypeCodeList.add(resultSet.getString(1));
                feedTypeValList.add(resultSet.getString(2));
            }

            feedbackVO.setFeedTypeCodeList(feedTypeCodeList);
            feedbackVO.setFeedTypeValList(feedTypeValList);

        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - getFeedbackType()" + e);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - getFeedbackType()" + ex);
            }
        }
        return feedbackVO;
    }
    public FeedbackVO getFeedbackEmp(FeedbackVO feedbackVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql="";
        String sesEmpId="";
        List activeEmpName = new ArrayList();
        List activeEmpId = new ArrayList();
        List activeDept = new ArrayList();
        List activeIssueId = new ArrayList();
        List activeIssueType = new ArrayList();
        List activeDesig = new ArrayList();
        List activeCorrAction = new ArrayList();
        List activePrevAction = new ArrayList();
        List activeCorrDate = new ArrayList();
        List activePrevDate = new ArrayList();
        List activeSeverity =  new ArrayList();
        List activeFeedback = new ArrayList();
        List activeFeedbackDate = new ArrayList();
        List activeAddedBy = new ArrayList();
        List activeFeedExpire = new ArrayList();
        List myEmpName = new ArrayList();
        List myEmpId = new ArrayList();
        List myEmpDept = new ArrayList();
        List myIssueId = new ArrayList();
        List myIssueType = new ArrayList();
        List myEmpDesig = new ArrayList();
        List myCorrAction = new ArrayList();
        List myPrevAction = new ArrayList();
        List myCorrDate = new ArrayList();
        List myPrevDate = new ArrayList();
        List mySeverity = new ArrayList();
        List myFeedback = new ArrayList();
        List myFeedbackDate = new ArrayList();
        List myFeedAddedBy = new ArrayList();
        List activeProj_id = new ArrayList();
        List myFeedExpire = new ArrayList();
        List myFeedProj_id = new ArrayList();
        try{

            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            sesEmpId=feedbackVO.getSesEmpId();

            sql="SELECT u.emp_name,u.emp_id,dp.department,i.issue_id,i.issue_type,ds.designation, i.corrective_action, i.preventive_action, ise.severity_type, "
                    + "i.feedback, DATE_FORMAT(i.feedback_date,'%d-%b-%Y'), ur.emp_name, DATE_FORMAT(i.corr_action_date,'%d-%b-%Y'), DATE_FORMAT(i.prevent_action_date,'%d-%b-%Y'), date(i.prevent_action_date) < CURRENT_DATE(), pr.proj_name "
                    + "FROM USER u,department dp,issue i,designation ds, issue_severity ise, USER ur, projects pr "
                    + "WHERE u.emp_id=i.emp_id AND dp.dept_code=u.dept_code AND u.desig_code=ds.desig_code AND i.status='1' "
                    + "AND i.severity_code=ise.severity_code AND i.added_by=ur.emp_id AND i.emp_id!='"+sesEmpId+"' AND i.added_by='"+sesEmpId+"' AND i.proj_id=pr.proj_id";

                resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    if(resultSet.getString(1)!=null){
                        activeEmpName.add(resultSet.getString(1));
                    }else{
                        activeEmpName.add("");
                    }
                    if(resultSet.getString(2)!=null){
                        activeEmpId.add(resultSet.getString(2));
                    }else{
                        activeEmpId.add("");
                    }
                    if(resultSet.getString(3)!=null){
                        activeDept.add(resultSet.getString(3));
                    }else{
                        activeDept.add("");
                    }
                    if(resultSet.getString(4)!=null){
                        activeIssueId.add(resultSet.getString(4));
                    }else{
                        activeIssueId.add("");
                    }
                    if(resultSet.getString(5)!=null){
                        activeIssueType.add(resultSet.getString(5));
                    }else{
                        activeIssueType.add("");
                    }
                    if(resultSet.getString(6)!=null){
                        activeDesig.add(resultSet.getString(6));
                    }else{
                        activeDesig.add("");
                    }
                    if(resultSet.getString(7)!=null){
                        activeCorrAction.add(resultSet.getString(7));
                    }else{
                        activeCorrAction.add("");
                    }
                    if(resultSet.getString(8)!=null){
                        activePrevAction.add(resultSet.getString(8));
                    }else{
                        activePrevAction.add("");
                    }
                    if(resultSet.getString(9)!=null){
                        activeSeverity.add(resultSet.getString(9));
                    }else{
                        activeSeverity.add("");
                    }
                    if(resultSet.getString(10)!=null){
                        activeFeedback.add(resultSet.getString(10));
                    }else{
                        activeFeedback.add("");
                    }
                    if(resultSet.getString(11)!=null){
                        activeFeedbackDate.add(resultSet.getString(11));
                    }else{
                        activeFeedbackDate.add("");
                    }
                    if(resultSet.getString(12)!=null){
                        activeAddedBy.add(resultSet.getString(12));
                    }else{
                        activeAddedBy.add("");
                    }
                    if(resultSet.getString(13)!=null){
                        activeCorrDate.add(resultSet.getString(13));
                    }else{
                        activeCorrDate.add("");
                    }
                    if(resultSet.getString(14)!=null){
                        activePrevDate.add(resultSet.getString(14));
                    }else{
                        activePrevDate.add("");
                    }
                    if(resultSet.getString(15)!=null){
                        activeFeedExpire.add(resultSet.getString(15));
                    }else{
                        activeFeedExpire.add("");
                    }
                    if(resultSet.getString(16)!=null){
                       // System.out.println(resultSet.getString(16));
                        activeProj_id.add(resultSet.getString(16));
                    }else{
                        activeProj_id.add("N/A");
                    }

                }
                feedbackVO.setActiveDept(activeDept);
                feedbackVO.setActiveDesig(activeDesig);
                feedbackVO.setActiveEmpId(activeEmpId);
                feedbackVO.setActiveEmpName(activeEmpName);
                feedbackVO.setActiveIssueId(activeIssueId);
                feedbackVO.setActiveIssueType(activeIssueType);
                feedbackVO.setActiveCorrAction(activeCorrAction);
                feedbackVO.setActivePrevAction(activePrevAction);
                feedbackVO.setActiveSeverity(activeSeverity);
                feedbackVO.setActiveFeedback(activeFeedback);
                feedbackVO.setActiveFeedbackDate(activeFeedbackDate);
                feedbackVO.setActiveProId(activeProj_id);
                feedbackVO.setActiveAddedBy(activeAddedBy);
                feedbackVO.setAciveCorrDate(activeCorrDate);
                feedbackVO.setActivePrevDate(activePrevDate);
                feedbackVO.setActiveFeedExpire(activeFeedExpire);
                feedbackVO.setActiveFeedExpire(activeFeedExpire);

            //} else {
                sql="SELECT a.emp_name,a.emp_id,b.department,c.issue_id,c.issue_type,d.designation, c.corrective_action, c.preventive_action, e.severity_type,"
                  + " c.feedback, DATE_FORMAT(c.feedback_date,'%d-%b-%Y'), f.emp_name, DATE_FORMAT(c.corr_action_date,'%d-%b-%Y'), DATE_FORMAT(c.prevent_action_date,'%d-%b-%Y'), date(c.prevent_action_date) < CURRENT_DATE(), pr.proj_name "
                    + " FROM user a,department b,issue c,designation d, issue_severity e, user f, projects pr "
                    + " WHERE a.emp_id=c.added_by and b.dept_code=a.dept_code and a.desig_code=d.desig_code and c.status='1' and c.emp_id='"+sesEmpId+"'"
                    + " and c.severity_code=e.severity_code and f.emp_id=c.emp_id and c.proj_id=pr.proj_id";

                resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    if(resultSet.getString(1)!=null){
                        myEmpName.add(resultSet.getString(1));
                    }else{
                        myEmpName.add("");
                    }
                    if(resultSet.getString(2)!=null){
                        myEmpId.add(resultSet.getString(2));
                    }else{
                        myEmpId.add("");
                    }
                    if(resultSet.getString(3)!=null){
                        myEmpDept.add(resultSet.getString(3));
                    }else{
                        myEmpDept.add("");
                    }
                    if(resultSet.getString(4)!=null){
                        myIssueId.add(resultSet.getString(4));
                    }else{
                        myIssueId.add("");
                    }
                    if(resultSet.getString(5)!=null){
                        myIssueType.add(resultSet.getString(5));
                    }else{
                        myIssueType.add("");
                    }
                    if(resultSet.getString(6)!=null){
                        myEmpDesig.add(resultSet.getString(6));
                    }else{
                        myEmpDesig.add("");
                    }
                    if(resultSet.getString(7)!=null){
                        myCorrAction.add(resultSet.getString(7));
                    }else{
                        myCorrAction.add("");
                    }
                    if(resultSet.getString(8)!=null){
                        myPrevAction.add(resultSet.getString(8));
                    }else{
                        myPrevAction.add("");
                    }
                    if(resultSet.getString(9)!=null){
                        mySeverity.add(resultSet.getString(9));
                    }else{
                        mySeverity.add("");
                    }
                    if(resultSet.getString(10)!=null){
                        myFeedback.add(resultSet.getString(10));
                    }else{
                        myFeedback.add("");
                    }
                    if(resultSet.getString(11)!=null){
                        myFeedbackDate.add(resultSet.getString(11));
                    }else{
                        myFeedbackDate.add("");
                    }
                    if(resultSet.getString(12)!=null){
                        myFeedAddedBy.add(resultSet.getString(12));
                    }else{
                        myFeedAddedBy.add("");
                    }
                    if(resultSet.getString(13)!=null){
                        myCorrDate.add(resultSet.getString(13));
                    }else{
                        myCorrDate.add("");
                    }
                    if(resultSet.getString(14)!=null){
                        myPrevDate.add(resultSet.getString(14));
                    }else{
                        myPrevDate.add("");
                    }
                    if(resultSet.getString(15)!=null){
                        myFeedExpire.add(resultSet.getString(15));
                    }else{
                        myFeedExpire.add("");
                    }
                    if(resultSet.getString(16)!=null){
                        myFeedProj_id.add(resultSet.getString(16));
                    }else{
                        myFeedProj_id.add("N/A");
                    }
                    //myFeedProj_id
                }
                feedbackVO.setMyEmpDept(myEmpDept);
                feedbackVO.setMyEmpDesig(myEmpDesig);
                feedbackVO.setMyEmpId(myEmpId);
                feedbackVO.setMyEmpName(myEmpName);
                feedbackVO.setMyIssueId(myIssueId);
                feedbackVO.setMyIssueType(myIssueType);
                feedbackVO.setMyCorrAction(myCorrAction);
                feedbackVO.setMyPrevAction(myPrevAction);
                feedbackVO.setMySeverity(mySeverity);
                feedbackVO.setMyFeedback(myFeedback);
                feedbackVO.setMyFeedbackDate(myFeedbackDate);
                feedbackVO.setMyFeedAddedBy(myFeedAddedBy);
                feedbackVO.setMyCorrDate(myCorrDate);
                feedbackVO.setMyPrevDate(myPrevDate);
                feedbackVO.setMyFeedExpire(myFeedExpire);
                feedbackVO.setIssueIdNotify(myIssueId);
                feedbackVO.setMyFeedProjId(myFeedProj_id);
                //
           // }

        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - getFeedbackEmp()" + e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - getFeedbackEmp()" + ex);
            }
        }
        return feedbackVO;
    }

    public FeedbackVO updateFeedback(FeedbackVO feedbackVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;

        String sql="";
        String where="";

        String updCorrAct = feedbackVO.getCorrAction();
        String updPrevAct = feedbackVO.getPrevAction();
        String sesEmpId = feedbackVO.getSesEmpId();
        String issueId = feedbackVO.getMyIssueID();
        int recordUpdate = 0;

        try{
            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            sql="update issue "
                    + "set corrective_action='"+updCorrAct+"', "
                    + "preventive_action='"+updPrevAct+"', "
                    + "completed_date = current_timestamp() "
                    + "where issue_id='"+issueId+"'";

            recordUpdate=statement.executeUpdate(sql);
            feedbackVO.setRecoredUpdate(recordUpdate);

        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - updateFeedback()" + e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - updateFeedback()" + ex);
            }
        }
        return feedbackVO;
    }
    public FeedbackVO addFeedback(FeedbackVO feedbackVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;

        String sql="";
        String where="";
        List selEmp = new ArrayList();
        selEmp=feedbackVO.getSelEmpID();
        String issueType = feedbackVO.getIssueType();
        String severity = feedbackVO.getSeverityCode();
        String corrAction = feedbackVO.getCorrAction();
        String corrDate = feedbackVO.getCorrDate();
        String prevAction = feedbackVO.getPrevAction();
        String prevDate = feedbackVO.getPrevDate();
        String feedback = feedbackVO.getFeedback();
        String addedBy = feedbackVO.getSesEmpId();
        String proj_id = feedbackVO.getProjId();
        int recordSave=0;

        try{

            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            if(issueType.equals("PV") && corrDate.equals("") && prevDate.equals("")){
                 
                for(int i=0; i<selEmp.size(); i++){
                    String selemp_id=selEmp.get(i).toString();

                    sql="INSERT INTO issue(emp_id,issue_type,severity_code,corrective_action,corr_action_date,preventive_action,prevent_action_date,"
                            +" feedback,feedback_date,added_by,status,proj_id) VALUES('"+selemp_id+"','"+issueType+"','"+severity+"','"+corrAction+"', current_timestamp()"
                            +",'"+prevAction+"', current_timestamp(),'"+feedback+"',current_timestamp(),'"+addedBy+"','1','"+proj_id+"')";

                    recordSave=statement.executeUpdate(sql);
                    feedbackVO.setRecordSave(recordSave);
                }
            } else {
               // System.out.println("recordSave"+recordSave);
                recordSave=1;
                for(int i=0; i<selEmp.size(); i++){
                    String selemp_id=selEmp.get(i).toString();

                    sql="INSERT INTO issue(emp_id,issue_type,severity_code,corrective_action,corr_action_date,preventive_action,prevent_action_date,"
                            +" feedback,feedback_date,added_by,status,proj_id) VALUES('"+selemp_id+"','"+issueType+"','"+severity+"','"+corrAction+"','"+corrDate
                            +"','"+prevAction+"','"+prevDate+"','"+feedback+"',current_timestamp(),'"+addedBy+"','1','"+proj_id+"')";

                   // System.out.println("the insert query is "+sql);
                    recordSave=statement.executeUpdate(sql);
                    feedbackVO.setRecordSave(recordSave);
                }
            }
                
        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - addFeedback()" + e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - addFeedback()" + ex);
            }
        }
        return feedbackVO;
    }

    public FeedbackVO completeFeedback(FeedbackVO feedbackVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;

        int recordRemove=0;
        String sql="";
        String selEmpIssue = feedbackVO.getSelEmpIssue();

        try{
            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            sql="Update issue set status='0' where issue_id='"+selEmpIssue+"' ";

            recordRemove=statement.executeUpdate(sql);
            feedbackVO.setRecordRemove(recordRemove);
            
        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - completeFeedback()" + e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - completeFeedback()" + ex);
            }
        }
        return feedbackVO;
    }

    public FeedbackVO getFeedNotification(FeedbackVO feedbackVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql="";
        String empIdNotify=feedbackVO.getEmpIdNotify();

        List issueIdNotify = new ArrayList();
        try{

            connection = conProp.getSampleProperty();
            statement = connection.createStatement();

            sql="select issue_id,emp_id from issue where corrective_action='' and preventive_action='' and emp_id='"+empIdNotify+"' ";

            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                if(resultSet.getString(1)!=null){
                    issueIdNotify.add(resultSet.getString(1));
                }else{
                    issueIdNotify.add("");
                }
            }

            feedbackVO.setIssueIdNotify(issueIdNotify);
            
        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackDAO - getFeedNotification()" + e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackDAO - getFeedNotification()" + ex);
            }
        }

        return feedbackVO;
    }
}
