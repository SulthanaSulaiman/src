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
public class FeedbackReportDAO {

    List issueID = new ArrayList();
    List msgID = new ArrayList();
    List msgType = new ArrayList();
    List msgBody = new ArrayList();
    List employee = new ArrayList();
    List empCode = new ArrayList();
    List empName = new ArrayList();
    List designation = new ArrayList();
    List dsgCode = new ArrayList();
    List desigCode = new ArrayList();
    List desigName = new ArrayList();
    List department = new ArrayList();
    List dptCode = new ArrayList();
    List preventiveAction = new ArrayList();
    List preDate = new ArrayList();
    List correctiveAction = new ArrayList();
    List correDate = new ArrayList();
    List remarks = new ArrayList();
    List employeeID = new ArrayList();
    List postDate = new ArrayList();
    List feedback = new ArrayList();
    List issue = new ArrayList();
    List issueType = new ArrayList();
    List projectName = new ArrayList();
    List cause = new ArrayList();
    List typeCode = new ArrayList();
    List typeName = new ArrayList();
    
    public FeedbackReportVO getFilterParam(FeedbackReportVO feedbackReportVO) {
        
        connection.DBconnection conProp = new connection.DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String sql="";
        String where="";

        String desig = feedbackReportVO.getDesig();
        String dept = feedbackReportVO.getDept();
        String emp = feedbackReportVO.getEmp();
        String type = feedbackReportVO.getType();
        String fromDate = feedbackReportVO.getFromDate();
        String toDate = feedbackReportVO.getToDate();
        String sesEmpId = feedbackReportVO.getSesEmpId();
        String stringview = feedbackReportVO.getView();
        
        try{

            con = conProp.getSampleProperty();
            st = con.createStatement();


            sql = "select emp_id,emp_name from user ";
            where = "";
            String param = "";

               param=dept;

              if(param.compareTo("ALL")!=0&&param.compareTo("")!=0) {

                where ="dept_code='"+param+"'";
              }

              param = desig;

              if( param.compareTo("ALL")!=0&&param.compareTo("")!=0) {

                if(where.length()>0) {
                    where += " and ";
                }
                where += "desig_code='"+param+"'";
              }

              if(!where.equals("")) {
                  sql += " where " + where;
              }
              
              rs = st.executeQuery(sql);
               empCode.clear();
               empName.clear();

               while(rs.next()){
                   empCode.add(rs.getString(1));
                   empName.add(rs.getString(2));
               }

               
           sql = "";
           sql = "select distinct(d.desig_code),designation from designation d";

           if(!dept.equals("")){
               sql += ",user u where u.desig_code = d.desig_code and dept_code= '"+dept+"' ";
           }

           rs = st.executeQuery(sql);
           desigCode.clear();
           desigName.clear();

           while(rs.next()){
               desigCode.add(rs.getString(1));
               desigName.add(rs.getString(2));
           }

      feedbackReportVO.setDesigCode(desigCode);
      feedbackReportVO.setDesigName(desigName);
      feedbackReportVO.setEmpCode(empCode);
      feedbackReportVO.setEmpName(empName);
          
    } catch (SQLException e) {
            System.out.println("SQLException : FeedbackReportDAO - getFilterParam()" + e);
     } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackReportDAO - getFilterParam()" + ex);
            }
        }
        return feedbackReportVO;
}

public FeedbackReportVO getFeedbackReport(FeedbackReportVO feedbackReportVO) {
   
    connection.DBconnection conProp = new connection.DBconnection();
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    String sql="";
    String from="";
    String order="";
    String where="";

    String desig = feedbackReportVO.getDesig();
    String dept = feedbackReportVO.getDept();
    String emp = feedbackReportVO.getEmp();
    String type = feedbackReportVO.getType();
    String fromDate = feedbackReportVO.getFromDate();
    String toDate = feedbackReportVO.getToDate();
    String sesEmpId = feedbackReportVO.getSesEmpId();
    String stringview = feedbackReportVO.getView();
    //System.out.println("session emp Id"+sesEmpId);
    String view = "1";

    try{
        
        con = conProp.getSampleProperty();
        st = con.createStatement();

        sql = "select u.emp_name, u.emp_id, i.issue_id, d.department, d.dept_code, de.designation, de.desig_code,"
                + "i.preventive_action,date_format(i.prevent_action_date, '%d-%b-%y %h:%i:%s %p'),i.corrective_action,"
                + "date_format(i.corr_action_date,'%d-%b-%y %h:%i:%s %p'),date_format(i.feedback_date,'%d-%b-%y %h:%i:%s %p'),"
                + "it.issue, it.issue_type, i.remarks ";
        from = "from  issue i,department d,user u, designation de, issue_type it "
                + " where u.dept_code=d.dept_code and u.emp_id=i.emp_id "
                + " and it.issue_type=i.issue_type and u.desig_code=de.desig_code and i.status='1' ";
        where = "";
        order = " group by i.issue_id ";

        if (!type.equals("All")) {
            where += " and i.issue_type = '" + type + "' ";
        }

        if (!emp.equals("All")) {
            where += " and u.emp_id = '" + emp + "' ";
        }

        if (!desig.equals("All")) {
            where += " and de.desig_code = '" + desig + "' ";
        }

        if (!fromDate.equals("") && !toDate.equals("")) {
            where += " and i.feedback_date between '" + fromDate + "' and '" + toDate + "' ";
        }

        if (!dept.equals("All")) {
            where += " and d.dept_code = '" + dept + "' ";
        }

        if (!stringview.equals(view)) {
            where += " and u.emp_id='" + sesEmpId + "'";
        }

        if (!where.equals("")) {
            sql += from + where + order;
        } else {
            sql += from + order;
        }
        //System.out.println("the original query runs in DAO "+sql);
        rs = st.executeQuery(sql);

        msgID.clear();
        msgType.clear();
        msgBody.clear();
        department.clear();
        preventiveAction.clear();
        preDate.clear();
        correctiveAction.clear();
        correDate.clear();
        postDate.clear();
        feedback.clear();
        issue.clear();
        projectName.clear();
        cause.clear();
        employee.clear();
        designation.clear();
        remarks.clear();

        String preAct = "";
        String preActDate = "";
        String correAct = "";
        String correActDate = "";
        String fdbkDate = "";
        String post_Date = "";
        String Issue = "";
        String proj_Name = "";
        String msgTitle = "";
        // String msgBody = "";

        while (rs.next()) {
            preAct = "";
            preActDate = "";
            correAct = "";
            correActDate = "";
            fdbkDate = "";
            post_Date = "";
            Issue = "";
            proj_Name = "";

            employee.add(rs.getString(1));
            employeeID.add(rs.getString(2));
            issueID.add(rs.getString(3));
            department.add(rs.getString(4));
            dptCode.add(rs.getString(5));
            designation.add(rs.getString(6));
            dsgCode.add(rs.getString(7));
            preAct = rs.getString(8);
            if (rs.wasNull()) {
                preAct = "";
            }
            preventiveAction.add(preAct);
            preActDate = rs.getString(9);
            if (rs.wasNull()) {
                preActDate = "";
            }
            preDate.add(preActDate);
            correAct = rs.getString(10);
            if (rs.wasNull()) {
                correAct = "";
            }
            correctiveAction.add(correAct);
            correActDate = rs.getString(11);
            if (rs.wasNull()) {
                correActDate = "";
            }
            correDate.add(correActDate);
            //  projectName.add(rs.getString(12));
            fdbkDate = rs.getString(12);
            if (rs.wasNull()) {
                fdbkDate = "";
            }
            feedback.add(fdbkDate);
            
            Issue = rs.getString(13);
            if (rs.wasNull()) {
                Issue = "";
            }
            issue.add(Issue);
            issueType.add(rs.getString(14));
            remarks.add(rs.getString(15));
        }
            
        feedbackReportVO.setEmployee(employee);
        feedbackReportVO.setEmployeeID(employeeID);
        feedbackReportVO.setDepartment(department);
        feedbackReportVO.setDptCode(dptCode);
        feedbackReportVO.setDesignation(designation);
        feedbackReportVO.setDsgCode(dsgCode);
        feedbackReportVO.setFeedback(feedback);
        feedbackReportVO.setIssueID(issueID);
        feedbackReportVO.setIssueType(issueType);
        feedbackReportVO.setCorrectiveAction(correctiveAction);
        feedbackReportVO.setCorreDate(correDate);
        feedbackReportVO.setPreventiveAction(preventiveAction);
        feedbackReportVO.setPreDate(preDate);
        feedbackReportVO.setDesignation(designation);
        feedbackReportVO.setIssue(issue);
        feedbackReportVO.setRemarks(remarks);
        
    } catch (SQLException e) {
            System.out.println("SQLException : FeedbackReportDAO - getFeedbackReport()" + e);
     } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : FeedbackReportDAO - getFeedbackReport()" + ex);
            }
        }
        return feedbackReportVO;
}
        
public FeedbackReportVO getFeedbackType(FeedbackReportVO feedbackReportVO) {

        connection.DBconnection conProp = new connection.DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String sql="";

        try{

            con = conProp.getSampleProperty();
            st = con.createStatement();

            sql = "select issue_type,issue from issue_type order by issue_type desc";
            rs = st.executeQuery(sql);

            while(rs.next()){
                typeCode.add(rs.getString(1));
                typeName.add(rs.getString(2));
            }
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

        return feedbackReportVO;

    }

    public List getTypeCode() {
        return typeCode;
    }

    public List getTypeName() {
        return typeName;
    }
    
}
