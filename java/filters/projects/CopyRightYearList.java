/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.projects;


import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class CopyRightYearList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List yearList = new ArrayList();
    private List facilityNameList = new ArrayList();
    private List facilityIdList = new ArrayList();
    private List projdiscipline = new ArrayList();
    private List projdiscipline_id = new ArrayList();
    private List workFlowNameList = new ArrayList();
    private List workflowIdList = new ArrayList();
    private List status = new ArrayList();
    private List statusId = new ArrayList();
    private List defaultStatus = new ArrayList();
    private List jobLostReasonId = new ArrayList();
    private List jobLostReason = new ArrayList();
    private List xmpPropID = new ArrayList();
    private List xmpPropName = new ArrayList();
    private List priorityNameList = new ArrayList();
    private List priorityIdList = new ArrayList();
    private List colorNameList = new ArrayList();
    private List colorIdList = new ArrayList();
    
    private String testcode = "";
    private String testname = "";


    private String sql_select = "";
    private String sql_from = "";

    private int calculateYear=0;
    private int currentYear=0;

    public void collectYearList(){
        sql_select = "";
        sql_from = "";

        sql_select = "select year(current_timestamp()) ";
        

        sql_select +=sql_from;


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            yearList.clear();
            
            while(rs.next()){
		testcode = rs.getString(1);
                
            }

            currentYear=Integer.parseInt(testcode);
            calculateYear=currentYear;
            //the below is done to set previous 2 yeaer as copyright year too.
            calculateYear=currentYear-2;
            yearList.add(calculateYear);
            calculateYear=currentYear-1;
            yearList.add(calculateYear);
            

             yearList.add(currentYear);

            calculateYear=currentYear;
            for(int idx=0;idx<5;idx++){
               calculateYear=calculateYear+1;
               yearList.add(calculateYear);
            }

            rs = st.executeQuery("select facility_id,facility_name from facility ");
            while(rs.next()){
                facilityIdList.add(rs.getString(1));
                facilityNameList.add(rs.getString(2));
            }

            rs = st.executeQuery("select projdiscipline_id, proj_discipline from proj_discipline");
            while(rs.next()){
                projdiscipline_id.add(rs.getString(1));
                projdiscipline.add(rs.getString(2));
            }

            rs = st.executeQuery("SELECT workflow_id, workflow_name FROM project_workflow_master");
            while(rs.next()){
                workflowIdList.add(rs.getString(1));
                workFlowNameList.add(rs.getString(2));
            }

            rs = st.executeQuery("SELECT status_id,STATUS,default_status FROM STATUS WHERE status_type='projects' ");
            while(rs.next()) {
                statusId.add(rs.getString(1));
                status.add(rs.getString(2));
                defaultStatus.add(rs.getString(3));
            }
            rs = st.executeQuery("select job_lost_reason_code, job_lost_reason from job_lost_reason");
            while(rs.next()) {
                jobLostReasonId.add(rs.getString(1));
                jobLostReason.add(rs.getString(2));
            }
            rs = st.executeQuery("select type_id, type_name from proj_xml_prop");
            while(rs.next()) {
                xmpPropID.add(rs.getString(1));
                xmpPropName.add(rs.getString(2));
            }
            rs = st.executeQuery("SELECT priority_id, priority_value FROM proj_priority");
            while(rs.next()) {
                priorityIdList.add(rs.getString(1));
                priorityNameList.add(rs.getString(2));
            }
            rs = st.executeQuery("SELECT color_id, color FROM proj_color");
            while(rs.next()) {
                colorIdList.add(rs.getString(1));
                colorNameList.add(rs.getString(2));
            }


           

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
           
        }catch(Exception sqle){

        }

        }

    }

    public List getYearList() {
        return yearList;
    }

    public List getFacilityId() {
        return facilityIdList;
    }

    public List getFacilityName() {
        return facilityNameList;
    }

    public List getDisciplineID() {
        return projdiscipline_id;
    }

    public List getDiscipline() {
        return projdiscipline;
    }

    public List getWorkflowId() {
        return workflowIdList;
    }

    public List getWorkflowName() {
        return workFlowNameList;
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

    public List getXmpPropID() {
        return xmpPropID;
    }

    public List getXmpPropName() {
        return xmpPropName;
    }

    public List getPriorityId() {
        return priorityIdList;
    }

    public List getPriorityIName() {
        return priorityNameList;
    }

    public List getColorId() {
        return colorIdList;
    }

    public List getColorName() {
        return colorNameList;
    }


}
