/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.projects;


import filters.generic.*;
import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class ActivityList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List activityCode = new ArrayList();
    private List activityName = new ArrayList();

    private List milestoneCode = new ArrayList();
    private List milestoneName = new ArrayList();
    private List milestoneOrderNum = new ArrayList();
    private List milestoneBufferDays = new ArrayList();
    private List milestonebufferPlanFlag = new ArrayList();
    
    private List standardMilestoneCode = new ArrayList();
    private List standardMilestoneName = new ArrayList();
    private List standardMilestoneOrderNum = new ArrayList();

    private String stage_code="";
    private String proj_id="";
    private String client_code="";
    private String milestone_code="";
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where="";

    public List getMilestonebufferPlanFlag() {
        return milestonebufferPlanFlag;
    }

    public void setMilestonebufferPlanFlag(List milestonebufferPlanFlag) {
        this.milestonebufferPlanFlag = milestonebufferPlanFlag;
    }

    public List getMilestoneBufferDays() {
        return milestoneBufferDays;
    }

    public void setMilestoneBufferDays(List milestoneBufferDays) {
        this.milestoneBufferDays = milestoneBufferDays;
    }

    public List getStandardMilestoneCode() {
        return standardMilestoneCode;
    }

    public void setStandardMilestoneCode(List standardMilestoneCode) {
        this.standardMilestoneCode = standardMilestoneCode;
    }

    public List getStandardMilestoneName() {
        return standardMilestoneName;
    }

    public void setStandardMilestoneName(List standardMilestoneName) {
        this.standardMilestoneName = standardMilestoneName;
    }

    public List getStandardMilestoneOrderNum() {
        return standardMilestoneOrderNum;
    }

    public void setStandardMilestoneOrderNum(List standardMilestoneOrderNum) {
        this.standardMilestoneOrderNum = standardMilestoneOrderNum;
    }

    public String getProj_id() {
        return proj_id;
    }

    public void setProj_id(String proj_id) {
        this.proj_id = proj_id;
    }
    
    

    public void setStageCode(String stage_code) {
        this.stage_code = stage_code;
    }

    public void setMilestoneCode(String milestone_code) {
        this.milestone_code = milestone_code;
    }

    
    public void collectActivityList(){

                activityCode.clear();
                activityName.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " select activity_code,activity   ";
        sql_from = " from activity_type ";
        sql_where = " where old_activity='0' ";
        sql_select +=sql_from+sql_where;

       // System.out.println("sql_select:"+sql_select);


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            activityCode.clear();
            activityName.clear();
            while(rs.next()){
                activityCode.add(rs.getString(1));
                activityName.add(rs.getString(2));
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

    public void collectMilestoneList(){

        milestoneCode.clear();
        milestoneName.clear();
        milestoneOrderNum.clear();
        milestonebufferPlanFlag.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " SELECT pma.milestone_act_code,pma.milestone_act_name,ps.stage,ps.stage_code,mo.milestone_order_number,bp.buffer_day,pma.buffer_plan_flag ";
        sql_from = " from project_stage ps,milestone_order mo,proj_milestone_act pma LEFT JOIN buffer_plan bp INNER JOIN projects p ON bp.customer_id=p.client_name and p.proj_id='"+proj_id+"' ON pma.milestone_act_code=bp.milestone_act_code  ";
        sql_where = " WHERE ps.stage_code=mo.stage_code AND mo.milestone_act_code=pma.milestone_act_code AND pma.milestone_act_status='1' "
                    + " AND mo.stage_code='"+stage_code+"' and mo.standard_milestone_flag = '0' ";

        if(!milestone_code.equals("")){
            sql_where += "  AND mo.milestone_act_code='"+milestone_code+"' ";

        }

        sql_select +=sql_from+sql_where+" ORDER BY mo.milestone_order_number ";

       //System.out.println("sql_select:"+sql_select);


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            milestoneCode.clear();
            milestoneName.clear();
            milestoneOrderNum.clear();
            milestoneBufferDays.clear();
            while(rs.next()){
                milestoneCode.add(rs.getString("pma.milestone_act_code")!=null?rs.getString("pma.milestone_act_code"):"");
                milestoneName.add(rs.getString("pma.milestone_act_name")!=null?rs.getString("pma.milestone_act_name"):"");
                milestoneOrderNum.add(rs.getString("mo.milestone_order_number")!=null?rs.getString("mo.milestone_order_number"):"");
                milestoneBufferDays.add(rs.getString("bp.buffer_day")!=null?rs.getString("bp.buffer_day"):"");
                milestonebufferPlanFlag.add(rs.getString("pma.buffer_plan_flag")!=null?rs.getString("pma.buffer_plan_flag"):"");
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
    
    public void collectStandardMilestoneList(){

        standardMilestoneCode.clear();
        standardMilestoneName.clear();
        standardMilestoneOrderNum.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " SELECT pma.milestone_act_code,pma.milestone_act_name,ps.stage,ps.stage_code,mo.milestone_order_number ";
        sql_from = " from proj_milestone_act pma,project_stage ps,milestone_order mo  ";
        sql_where = " WHERE ps.stage_code=mo.stage_code AND mo.milestone_act_code=pma.milestone_act_code AND pma.milestone_act_status='1' "
                    + " AND mo.stage_code='"+stage_code+"' ";


        sql_select +=sql_from+sql_where+" ORDER BY mo.milestone_order_number ";

       //System.out.println("sql_select:"+sql_select);


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            while(rs.next()){
                standardMilestoneCode.add(rs.getString("pma.milestone_act_code"));
                standardMilestoneName.add(rs.getString("pma.milestone_act_name"));
                standardMilestoneOrderNum.add(rs.getString("mo.milestone_order_number"));
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

    public List getActivityCode() {
        return activityCode;
    }

    public List getActivityName() {
        return activityName;
    }

    public List getMilestoneCode() {
        return milestoneCode;
    }

    public List getMilestoneName() {
        return milestoneName;
    }

    public List getMilestoneOrderNum() {
        return milestoneOrderNum;
    }
    
}
