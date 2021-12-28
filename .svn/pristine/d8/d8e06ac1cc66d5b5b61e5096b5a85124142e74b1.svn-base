/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

/**
 *
 * @author Parameshwarant
 */
import connection.DBconnection;
import java.sql.*;
import java.util.*;

public class JobLostReportDAO {

    public void collectJobLostInformation(JobLostReportVO setJobLostInfo) {
        String queryToGetJobLostInfo = "";
        List jobIdList = new ArrayList();
        List jobTypeList = new ArrayList();
        List customerList = new ArrayList();
        List jobLostReasonList = new ArrayList();
        List jobEstCostList = new ArrayList();
        List jobInvCostList = new ArrayList();

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

        String projSearchFlag = "";
        String projCreateDateFrom = "";
        String projCreateDateTo = "";
        String projType = "";

        try {

            DBconnection connToDB = new DBconnection();
            Connection conn = connToDB.getSampleProperty();
            Statement stToGetJobLostInfo = conn.createStatement();

            projSearchFlag = setJobLostInfo.getProjSearchFlag();
            projType = setJobLostInfo.getProjType();
            queryToGetJobLostInfo = " SELECT CONCAT(a.proj_id,' / ',a.proj_name), IFNULL(e.type,'N/A') AS projType, f.company, "
                    + " case when a.job_lost_reason_code IS not NULL then b.job_lost_reason else 'N/A' end as jobLostReason, IFNULL(c.est_value,0) estcost, IFNULL(d.invoice_value,0) invCost "
                    + " from projects a left join project_type e on e.type_code=a.proj_type left join  estimate c using(proj_id) left join invoices d using(proj_id), job_lost_reason b, contacts f  "
                    + " WHERE a.project_status='21' AND (a.job_lost_reason_code=b.job_lost_reason_code OR a.job_lost_reason_code IS NULL) AND a.client_name=f.contact_id ";


            if(projSearchFlag.equals("1")) {
                projCreateDateFrom = setJobLostInfo.getProjCreateDateFrom();
                projCreateDateTo = setJobLostInfo.getProjCreateDateTo();
                queryToGetJobLostInfo += " AND DATE(a.proj_date) BETWEEN '"+projCreateDateFrom+"' AND '"+projCreateDateTo+"' ";
            }

            if(!projType.equals("")) {
                queryToGetJobLostInfo += " AND proj_type = '"+projType+"' ";
            }

            queryToGetJobLostInfo += " GROUP BY a.proj_id ORDER BY f.company ";

            ResultSet rsToGetJobLostInfo = stToGetJobLostInfo.executeQuery(queryToGetJobLostInfo);
            while (rsToGetJobLostInfo.next()) {
                jobIdList.add(rsToGetJobLostInfo.getString(1));
                jobTypeList.add(rsToGetJobLostInfo.getString(2));
                customerList.add(splChar.decoding(rsToGetJobLostInfo.getString(3)));
                jobLostReasonList.add(rsToGetJobLostInfo.getString(4));
                jobEstCostList.add(rsToGetJobLostInfo.getString(5));
                jobInvCostList.add(rsToGetJobLostInfo.getString(6));
            }
            setJobLostInfo.setJobIdList(jobIdList);
            setJobLostInfo.setJobTypeList(jobTypeList);
            setJobLostInfo.setCustomerList(customerList);
            setJobLostInfo.setJobLostReasonList(jobLostReasonList);
            setJobLostInfo.setJobEstCostList(jobEstCostList);
            setJobLostInfo.setJobInvCostList(jobInvCostList);

            rsToGetJobLostInfo.close();
            stToGetJobLostInfo.close();
            conn.close();
            
        } catch (SQLException sqle) {
            System.out.println("SQL Exception in Class:JobLostReportDAO Function:collectJobLostInformation " + sqle);
        }


    }
}
