/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.generic;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class ProjStatusList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List status = new ArrayList();
    private List statusId = new ArrayList();
    private List defaultStatus = new ArrayList();
    private String tempResult = "";
    private List jobLostReasonId = new ArrayList();
    private List jobLostReason = new ArrayList();
    
    private String sql_select = "";
    private String sql_from = "";


    public void collectProjStatusList(){
        sql_select = "";
        sql_from = "";

        sql_select = "select st.status_id,st.status,st.default_status ";
        sql_from = "from status st where status_type='projects' ";

        sql_select +=sql_from;



        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            statusId.clear();
            status.clear();
            while(rs.next()) {
		tempResult = rs.getString(1);
                statusId.add(tempResult);
                tempResult = rs.getString(2);
                status.add(tempResult);
                tempResult = rs.getString(3);
                defaultStatus.add(tempResult);
            }
            rs = st.executeQuery("select job_lost_reason_code, job_lost_reason from job_lost_reason");
            while(rs.next()) {
                jobLostReasonId.add(rs.getString(1));
                jobLostReason.add(rs.getString(2));
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public void collectProjLostReasonList(){
        sql_select = "";

        sql_select = "select job_lost_reason_code, job_lost_reason from job_lost_reason";

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            statusId.clear();
            status.clear();
            while(rs.next()){
		tempResult = rs.getString(1);
                statusId.add(tempResult);
                tempResult = rs.getString(2);
                status.add(tempResult);
                tempResult = rs.getString(3);
                defaultStatus.add(tempResult);
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public List getProjStatusID() {
        return statusId;
    }

    public List getProjStatus() {
        return status;
    }

    public List getProjDefaultStatus() {
        return defaultStatus;
    }

    public List getJobLostReasonID() {
        return jobLostReasonId;
    }

    public List getJobLostReason() {
        return jobLostReason;
    }
}
