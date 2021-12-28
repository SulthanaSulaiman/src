/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramesh
 */
public class ProjScheduleValues implements Serializable {

    private String prjid="";

    private List batchName = new ArrayList();
    private List milestone = new ArrayList();
    private List milestonecode = new ArrayList();
    private List display_startDate = new ArrayList();
    private List display_endDate = new ArrayList();
    private List startDate = new ArrayList();
    private List endDate = new ArrayList();
    private List schRemarks = new ArrayList();

    private String WorkflowId = "";
    private String WorkflowName = "";

    private String scheduleSql = "";

    public ProjScheduleValues() {

    }

    public List getBatchName(){
        return batchName;
    }

     public List getMileStone(){
        return milestone;
    }

     public List getDisplayStartDate(){
        return display_startDate;
    }

     public List getDisplayEndDate(){
        return display_endDate;
    }

     public List getStartDate(){
        return startDate;
    }

     public List getEndDate(){
        return endDate;
    }

    public List getRemarks(){
        return schRemarks;
    }

   public List getMileStoneCode(){
        return milestonecode;
    }

    public String getWorkflowId() {
        return WorkflowId;
    }

    public String getWorkflowName() {
        return WorkflowName;
    }
    
//
   public void setProjId(String prjid){
        this.prjid=prjid;
       // System.out.println("getProjId:"+prjid);
   }

//addPropValue

public void projScheduleValue(){

Connection con=null;
        try{
            batchName.clear();
            milestone.clear();
            milestonecode.clear();
            display_startDate.clear();
            display_endDate.clear();
            startDate.clear();
            endDate.clear();
            schRemarks.clear();

        //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();

     Statement statement = con.createStatement();
     String schValue = "";

     /*System.out.println("select atp.activity,ptm.emp_id,u.emp_name,ptm.primary_incharge,fc.facility_name,fc.facility_id,ptm.teammap_id " +
             "from project_team ptm,user u,facility fc,activity_type atp " +
             "where u.emp_id=ptm.emp_id and ptm.role_id=atp.activity_code" +
             " and ptm.facility_id=fc.facility_id and ptm.proj_id='"+prjid+"' " +
             "order by atp.activity,ptm.primary_incharge desc");
     */

     //the query below is executed twice. First to retrieve records for which Employee has been mapped and the
     //second is to retrieve the records for which only the location has been mapped

    scheduleSql="select psc.batch,ps.stage,psc.milestone_code,date_format(psc.estimated_start_date,'%d-%b-%Y') as display_startdate, " +
            "date_format(psc.estimated_end_date,'%d-%b-%Y')  as display_enddate,psc.remarks," +
            " psc.estimated_start_date,psc.estimated_end_date " +
            "from project_schedule psc,project_stage ps where psc.milestone_code=ps.stage_code " +
            "and psc.proj_id='"+prjid+"'  order by psc.estimated_end_date  ";




     ResultSet rsScheduleValue =  statement.executeQuery(scheduleSql);

     while(rsScheduleValue.next()){

       schValue=rsScheduleValue.getString("psc.batch");
       if(rsScheduleValue.wasNull()){
         schValue="";
        }
        batchName.add(schValue);

       schValue=rsScheduleValue.getString("ps.stage");
       if(rsScheduleValue.wasNull()){
         schValue="";
        }
        milestone.add(schValue);

      schValue=rsScheduleValue.getString("display_startdate");
       if(rsScheduleValue.wasNull()){
         schValue="";
        }
        display_startDate.add(schValue);
        //ptm.primary_incharge,fc.facility_name,fc.facility_id
       schValue=rsScheduleValue.getString("display_enddate");
       if(rsScheduleValue.wasNull()){
         schValue="";
        }
        display_endDate.add(schValue);


       schValue=rsScheduleValue.getString("psc.estimated_start_date");
       if(rsScheduleValue.wasNull()){
         schValue="";
        }
        startDate.add(schValue);
        //ptm.primary_incharge,fc.facility_name,fc.facility_id
       schValue=rsScheduleValue.getString("psc.estimated_end_date");
       if(rsScheduleValue.wasNull()){
         schValue="";
        }
        endDate.add(schValue);



       schValue=rsScheduleValue.getString("psc.remarks");
       if(rsScheduleValue.wasNull()){
         schValue="";
        }
        schRemarks.add(schValue);

      schValue=rsScheduleValue.getString("psc.milestone_code");
       if(rsScheduleValue.wasNull()){
         schValue="";
        }
        milestonecode.add(schValue);

       }

       rsScheduleValue.close();
       statement.close();

        }catch(SQLException sqle){
            System.out.println("SQLException in creating Proj ID:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Proj ID:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }


}

    public void ProjWorkflowValue() {

        Connection con = null;
        try {
            String chkRecordSql="";
            String dispWorkflowSql="";
            int addPropValue=0;
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            Statement statement = con.createStatement();

           String chkValue = "";

            chkRecordSql = " select proj_workflow from projects where proj_id='"+prjid+"' ";

            ResultSet rsCheckProPValue = statement.executeQuery(chkRecordSql);
            while (rsCheckProPValue.next()) {
                chkValue=rsCheckProPValue.getString("proj_workflow");
                    if(rsCheckProPValue.wasNull()){
                        chkValue="0";
                    }
            }

            //System.out.println("ProjWFValue:"+chkValue);

            if (!chkValue.equals("0")) {
                //System.out.println("IF--ProjWFValue:"+chkValue);
                dispWorkflowSql = " SELECT workflow_id,workflow_name FROM project_workflow_master WHERE workflow_id='"+chkValue+"' ";

                ResultSet rsCheckWorkflowValue = statement.executeQuery(dispWorkflowSql);
                while (rsCheckWorkflowValue.next()) {
                    WorkflowId = rsCheckWorkflowValue.getString("workflow_id");
                    WorkflowName = rsCheckWorkflowValue.getString("workflow_name");
                }
            } else {
                //System.out.println("ELSE--ProjWFValue:"+chkValue);
            }

        } catch (SQLException sqle) {
            System.out.println("SQLException in creating Proj Schedule:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in creating Proj Schedule:" + e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

}
