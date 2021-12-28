/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;



import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 */
public class SaveProjSchedule implements Serializable {

   
    private String prjid="";
    private String milestone_code="";
    private String workflow_id="";
    private String oldmilestone_code="";
    private String startDate="";
    private String endDate="";
    private String batchName="";
    private String schRemarks="";
    private String returnAlert="";
    
    private String chkRecordSql="";

    private int addPropValue=0;
    private int addWorkflowValue=0;


    public SaveProjSchedule() {
        
    }

    public void setMileStoneCode(String milestone_code){
        this.milestone_code=milestone_code;
    }

        public void setOldMileStoneCode(String oldmilestone_code){
        this.oldmilestone_code=oldmilestone_code;
    }

    public void setStartDate(String startDate){
        this.startDate=startDate;
            }

    public void setEndDate(String endDate){
        this.endDate=endDate;
 
   }

    public void setBachName(String batchName){
        this.batchName=batchName;

   }

    public void setSchRemarks(String schRemarks){
        this.schRemarks=schRemarks;

   }

   public void setProjId(String prjid){
        this.prjid=prjid;
   }
//addPropValue

   public int getPropAdded(){
        return addPropValue;
   }

   public String getPropAddedAlert(){
        return returnAlert;
   }

    public void setWorkflow_id(String workflow_id) {
        this.workflow_id = workflow_id;
    }

   
public void modifyProjSchedule(){

Connection con=null;
        try{
String modifySchedule_Sql="";
           //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();

        Statement statement = con.createStatement();


     if(!startDate.equals("")){
         modifySchedule_Sql="update project_schedule set " +
                 " batch='"+batchName+"',milestone_code='"+milestone_code+"', " +
                 " estimated_start_date='"+startDate+"',estimated_end_date='"+endDate+"', " +
                 " remarks='"+schRemarks+"' " +
                 " where proj_id='"+prjid+"' and milestone_code='"+oldmilestone_code+"' ";
             
     }else{

         modifySchedule_Sql="update project_schedule set " +
                 " batch='"+batchName+"',milestone_code='"+milestone_code+"', " +
                 " estimated_start_date=null,estimated_end_date='"+endDate+"', " +
                 " remarks='"+schRemarks+"' " +
                 " where proj_id='"+prjid+"' and milestone_code='"+oldmilestone_code+"' ";
     }
String chkValue = "";
//System.out.println("modifySchedule_Sql:"+modifySchedule_Sql);

    
       addPropValue = statement.executeUpdate(modifySchedule_Sql);
    
statement.close();

//System.out.println("modifyrojSchedule-Bean:"+addPropValue);
        }catch(SQLException sqle){
            System.out.println("SQLException in modifying Proj Schedule:"+sqle);
        }catch(NullPointerException npe){
            System.out.println("Exception in modifying Proj Schedule:"+npe);
        }catch(Exception e){
            System.out.println("Exception in modifying Proj Schedule:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }

}


public void deleteProjSchedule(){

Connection con=null;
        try{
String deleteSchedule_Sql="";
        //    System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();

        Statement statement = con.createStatement();

         deleteSchedule_Sql="delete from project_schedule " +
                 " where proj_id='"+prjid+"' and milestone_code='"+oldmilestone_code+"' ";
   
       addPropValue = statement.executeUpdate(deleteSchedule_Sql);
    statement.close();
//System.out.println("addPropValue-Bean:"+addPropValue);
        }catch(SQLException sqle){
            System.out.println("SQLException in deleting Proj Schedule:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in deleting Proj Schedule:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }

}

public void addProjSchedule(){

Connection con=null;
        try{
String saveSchedule_Sql="";
String saveWorkflow_Sql="";
        //    System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
       
        Statement statement = con.createStatement();
      
        //the below block checks for the property ID as it should start from 106000 and starts adding the project ID from that
        //Generate Project ID
               
       // System.out.println("insert into contacts(firstname,surname) values ('"+firstName+"','"+surName+"')  ");
        
             String chkValue = "";
             
        chkRecordSql=" select proj_id from project_schedule" +
                        " where proj_id='"+prjid+"' and milestone_code='"+milestone_code+"' and batch='"+batchName+"' ";

     ResultSet rsCheckProPValue =  statement.executeQuery(chkRecordSql);
     while(rsCheckProPValue.next()){
       chkValue=rsCheckProPValue.getString(1);
       }
       rsCheckProPValue.close();
//System.out.println("chkRecordSql:"+chkRecordSql);

     if(!startDate.equals("")){
         saveSchedule_Sql="insert into project_schedule(proj_id,batch,milestone_code,estimated_start_date, " +
               " estimated_end_date,remarks)" +
               " values ('"+prjid+"','"+batchName+"','"+milestone_code+"','"+startDate+"', " +
               "  '"+endDate+"','"+schRemarks+"')  ";
         
         saveWorkflow_Sql = " update projects set proj_workflow = '"+workflow_id+"' WHERE proj_id = '"+prjid+"' ";

     }else{
         saveSchedule_Sql="insert into project_schedule(proj_id,batch,milestone_code, " +
               " estimated_end_date,remarks)" +
               " values ('"+prjid+"','"+batchName+"','"+milestone_code+"', " +
               "  '"+endDate+"','"+schRemarks+"' )  ";

         saveWorkflow_Sql = " update projects set proj_workflow = '"+workflow_id+"' WHERE proj_id = '"+prjid+"' ";
     }

//       System.out.println("addProjSchedule-saveSchedule_Sql:"+saveSchedule_Sql);
//       System.out.println("addProjSchedule-saveWorkflow_Sql:"+saveWorkflow_Sql);

   if(chkValue.equals("")){
       addPropValue = statement.executeUpdate(saveSchedule_Sql);
       addWorkflowValue = statement.executeUpdate(saveWorkflow_Sql);
     }
   else{
           returnAlert="Already added.";
   }
       statement.close();
//System.out.println("addPropValue-Bean:"+addPropValue);
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Proj Schedule:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Proj Schedule:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
             
}

}
