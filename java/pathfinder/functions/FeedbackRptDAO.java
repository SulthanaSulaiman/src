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
public class FeedbackRptDAO {

    

    public FeedbackRptVO getSubmitterDet(FeedbackRptVO feedbackRptVO) {
        
        connection.DBconnection conProp = new connection.DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        String sql="";
        String where="";

        String deptCode = feedbackRptVO.getDeptCode();
        String desigCode = feedbackRptVO.getDesigCode();
        
        List submitterCode = new ArrayList();
        List submitterName = new ArrayList();
        
        try{
            
           con = conProp.getSampleProperty();
           st = con.createStatement(); 
           
           //sql="SELECT emp_id, emp_name FROM USER WHERE emp_id IN (SELECT emp_id FROM groups WHERE group_id in ('2'))";
           sql="select u.emp_id, u.emp_name from issue i, user u where u.emp_id=i.added_by group by u.emp_id";
           /*where="";

           if(!deptCode.equals("ALL")&&!desigCode.equals("ALL")){
                where =" and dept_code='"+deptCode+"'and desig_code='"+desigCode+"' ";
            }else if(deptCode.equals("ALL")&&!desigCode.equals("ALL")){
                where =" and dept_code is not null and desig_code='"+desigCode+"' ";
            }else{
                where =" and dept_code='"+deptCode+"' and desig_code is not null ";
            }

           sql = sql+where + " group by added_by";*/

           //System.out.println("the querey "+sql);
           
           rs = st.executeQuery(sql);
           while(rs.next()){
                   submitterCode.add(rs.getString(1));
                   submitterName.add(rs.getString(2));
               }

           feedbackRptVO.setSubmitterCode(submitterCode);
           feedbackRptVO.setSubmitterName(submitterName);

        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackRptDAO - getSubmitterDet()" + e);
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackRptDAO - getSubmitterDet()" + ex);
            }
        }
        return feedbackRptVO;
    }
    public FeedbackRptVO getEmpParam(FeedbackRptVO feedbackRptVO) {

        connection.DBconnection conProp = new connection.DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String sql="";
        String where="";

        String deptCode = feedbackRptVO.getDeptCode();
        String desigCode = feedbackRptVO.getDesigCode();

        List empCode = new ArrayList();
        List empName = new ArrayList();

        try{
            con = conProp.getSampleProperty();
            st = con.createStatement();


            sql = "select emp_id,emp_name from user ";
            where = "";

            if(!deptCode.equals("ALL")&&!desigCode.equals("ALL")){
                where =" where dept_code='"+deptCode+"'and desig_code='"+desigCode+"' ";
            }else if(deptCode.equals("ALL")&&!desigCode.equals("ALL")){
                where =" where dept_code is not null and desig_code='"+desigCode+"' ";
            }else{
                where =" where dept_code='"+deptCode+"' and desig_code is not null ";
            }
            sql = sql+where;
              //System.out.println("the emp query "+sql);
              rs = st.executeQuery(sql);
               empCode.clear();
               empName.clear();

               while(rs.next()){
                   empCode.add(rs.getString(1));
                   empName.add(rs.getString(2));
               }

               feedbackRptVO.setEmployeeCode(empCode);
               feedbackRptVO.setEmployeeName(empName);

        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackRptDAO - getEmpParam()" + e);
     } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackRptDAO - getEmpParam()" + ex);
            }
        }

        return feedbackRptVO;
    }

    public FeedbackRptVO getReport(FeedbackRptVO feedbackRptVO) {

        connection.DBconnection conProp = new connection.DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String sql="";
        String from="";
        String where="";
        String order="";

        String deptCode=feedbackRptVO.getDeptCode();
        String desigCode=feedbackRptVO.getDesigCode();
        String feedType=feedbackRptVO.getFeedType();
        String empId=feedbackRptVO.getEmpCode();
        String submitterCode=feedbackRptVO.getSubmiterCode();
        String feedStatus=feedbackRptVO.getFeedStatus();
        String fromDate=feedbackRptVO.getFromDate();
        String toDate=feedbackRptVO.getToDate();
        
        List empNameRpt = new ArrayList();
        List empIdRpt = new ArrayList();
        List issueIdRpt = new ArrayList();
        List deptNameRpt = new ArrayList();
        List deptCodeRpt = new ArrayList();
        List desigNameRpt = new ArrayList();
        List desigCodeRpt = new ArrayList();
        List preActionRpt = new ArrayList();
        List preDateRpt = new ArrayList();
        List corrActionRpt = new ArrayList();
        List corrDateRpt = new ArrayList();
        List feedbackRpt = new ArrayList();
        List feedbackDateRpt = new ArrayList();
        List feedTypeRpt = new ArrayList();
        List severityRpt = new ArrayList();
        List submitterNameRpt = new ArrayList();
        List submitterCodeRpt = new ArrayList();
        List statusRpt = new ArrayList();
        List completedDateRpt = new ArrayList();
        List listProjId = new ArrayList();
        List delayCompleteRpt = new ArrayList();

        try{

            con = conProp.getSampleProperty();
            st = con.createStatement();

            sql="SELECT u.emp_name, u.emp_id, i.issue_id, d.department, d.dept_code, de.designation, de.desig_code,"
                    +" i.preventive_action,DATE_FORMAT(i.prevent_action_date, '%d-%b-%y %h:%i:%s %p'),i.corrective_action,"
                    +" DATE_FORMAT(i.corr_action_date,'%d-%b-%y %h:%i:%s %p'),DATE_FORMAT(i.feedback_date,'%d-%b-%Y'),it.issue,"
                    +" ise.severity_type, i.feedback, ur.emp_name, ur.emp_id, i.status, DATE_FORMAT(i.completed_date,'%d-%b-%Y'), date(i.prevent_action_date) < date(i.completed_date), pr.proj_name";
            from=" FROM USER u, issue i, department d, designation de, issue_type it, issue_severity ise, USER ur, projects pr"
                    +" WHERE u.dept_code=d.dept_code AND u.emp_id=i.emp_id AND it.issue_type=i.issue_type AND u.desig_code=de.desig_code "
                    +" and i.severity_code=ise.severity_code and ur.emp_id=i.added_by and ur.emp_id=i.added_by and i.proj_id=pr.proj_id";

            where="";
            order=" group by i.issue_id";

            if(!deptCode.equals("ALL")){
                where += " and d.dept_code='"+deptCode+"' ";
            }

            if(!desigCode.equals("ALL")){
                where += " and de.desig_code='"+desigCode+"' ";
            }

            if(!feedType.equals("ALL")){
                where += " and i.issue_type='"+feedType+"' ";
            }

            if(!empId.equals("ALL")){
                where += " and u.emp_id='"+empId+"' ";
            }

            if(!submitterCode.equals("ALL")){
                where += " and i.added_by='"+submitterCode+"' ";
            }

            if(!feedStatus.equals("ALL")){
                where += " and i.status='"+feedStatus+"' ";
            }

            if(!fromDate.equals("")&&!toDate.equals("")){
                where += " and i.feedback_date between '"+fromDate+"' and '"+toDate+"' ";
            }

            if(!where.equals("")){
                sql += from + where + order;
            }else{
                sql += from + order;
            }

            //System.out.println("the report query "+sql);

            rs = st.executeQuery(sql);

            while(rs.next()){
                empNameRpt.add(rs.getString(1));
                empIdRpt.add(rs.getString(2));
                issueIdRpt.add(rs.getString(3));
                deptNameRpt.add(rs.getString(4));
                deptCodeRpt.add(rs.getString(5));
                desigNameRpt.add(rs.getString(6));
                desigCodeRpt.add(rs.getString(7));

                if(rs.getString(8) != null){
                    preActionRpt.add(rs.getString(8));
                }else{
                    preActionRpt.add("");
                }

                if(rs.getString(9) != null){
                    preDateRpt.add(rs.getString(9));
                }else{
                    preDateRpt.add("");
                }

                if(rs.getString(10) != null){
                    corrActionRpt.add(rs.getString(10));
                }else{
                    corrActionRpt.add("");
                }

                if(rs.getString(11) != null){
                    corrDateRpt.add(rs.getString(11));
                }else{
                    corrDateRpt.add("");
                }

                feedbackDateRpt.add(rs.getString(12));
                feedTypeRpt.add(rs.getString(13));
                severityRpt.add(rs.getString(14));

                if(rs.getString(15) != null){
                    feedbackRpt.add(rs.getString(15));
                }else{
                    feedbackRpt.add("");
                }

                submitterNameRpt.add(rs.getString(16));
                submitterCodeRpt.add(rs.getString(17));
                statusRpt.add(rs.getString(18));

                if(rs.getString(19) != null){
                    completedDateRpt.add(rs.getString(19));
                }else{
                    completedDateRpt.add("");
                }

                if(rs.getString(20) != null){
                    delayCompleteRpt.add(rs.getString(20));
                }else{
                    delayCompleteRpt.add("");
                }
                if(rs.getString(21) != null){
                    listProjId.add(rs.getString(21));
                }else{
                    listProjId.add("");
                }
            }

            feedbackRptVO.setEmpNameRpt(empNameRpt);
            feedbackRptVO.setEmpIdRpt(empIdRpt);
            feedbackRptVO.setIssueIdRpt(issueIdRpt);
            feedbackRptVO.setDeptNameRpt(deptNameRpt);
            feedbackRptVO.setDeptCodeRpt(deptCodeRpt);
            feedbackRptVO.setDesigNameRpt(desigNameRpt);
            feedbackRptVO.setDesigCodeRpt(desigCodeRpt);
            feedbackRptVO.setPreActionRpt(preActionRpt);
            feedbackRptVO.setPreDateRpt(preDateRpt);
            feedbackRptVO.setCorrActioRpt(corrActionRpt);
            feedbackRptVO.setCorrDateRpt(corrDateRpt);
            feedbackRptVO.setFeedbackDateRpt(feedbackDateRpt);
            feedbackRptVO.setFeedTypeRpt(feedTypeRpt);
            feedbackRptVO.setSeverityRpt(severityRpt);
            feedbackRptVO.setFeedbackRpt(feedbackRpt);
            feedbackRptVO.setSubmitterNameRpt(submitterNameRpt);
            feedbackRptVO.setSubmitterCodeRpt(submitterCodeRpt);
            feedbackRptVO.setStatusRpt(statusRpt);
            feedbackRptVO.setCompletedDateRpt(completedDateRpt);
            feedbackRptVO.setProjId(listProjId);
            feedbackRptVO.setDelayComplete(delayCompleteRpt);

        }catch (SQLException e) {
            System.out.println("SQLException : FeedbackRptDAO - getReport()" + e);
     } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackRptDAO - getReport()" + ex);
            }
        }

        return feedbackRptVO;
    }

    public FeedbackRptVO getFeedbackType(FeedbackRptVO feedbackRptVO) {

        connection.DBconnection conProp = new connection.DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String sql="";
        List typeCode = new ArrayList();
        List typeName = new ArrayList();

        try{

            con = conProp.getSampleProperty();
            st = con.createStatement();

            sql = "select issue_type,issue from issue_type order by issue_type desc";
            rs = st.executeQuery(sql);

            while(rs.next()){
                typeCode.add(rs.getString(1));
                typeName.add(rs.getString(2));
            }

            feedbackRptVO.setTypeCode(typeCode);
            feedbackRptVO.setTypeName(typeName);
            
        } catch (SQLException e) {
            System.out.println("SQLException : FeedbackReportDAO - getFeedbackType()" + e);
            } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackReportDAO - getFeedbackType()" + ex);
                }
            }

        return feedbackRptVO;

    }
}
