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
public class SaveProjTeamFind implements Serializable {

   
    private String prjid="";
    private String allocType="";
    private String deptId="";
    private String cellId="";
    private String roleId="";
    private String memberId="";
    private String locationId="";
    private String primaryIncharge="";
    private String saveTeam_Sql="";
    private String chkRecordSql="";
    private String teamMappedId="";
    private String chkprimaryIncharge="";
    private String chkFacilityId="";

    private int addPropValue=0;
    private String returnAlert="";


    public SaveProjTeamFind() {
        
    }

    public void setRoleId(String roleId){
        this.roleId=roleId;
    }

    public void setAllocType(String allocType) {
        this.allocType = allocType;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }
  
    public void setMemberId(String memberId){
        this.memberId=memberId;
    }

    public void setLocationId(String locationId){
        this.locationId=locationId;
 
   }

    public void setPrimaryIncharge(String primaryIncharge){
        this.primaryIncharge=primaryIncharge;

   }

   public void setProjId(String prjid){
        this.prjid=prjid;
       
   }
//addPropValue

  public void setMappedId(String teamMappedId){
        this.teamMappedId=teamMappedId;
   }

   public String getPropAdded(){
        return returnAlert;
   }

   public int getPropAddedResult(){
        return addPropValue;
   }


   public void removeTeamItem(){
       Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();

        addPropValue=statement.executeUpdate("delete from project_team where teammap_id='"+teamMappedId+"' ");
        if(addPropValue>0){
            returnAlert="Record has been removed successfully";
        }
        statement.close();
         }catch(SQLException sqle){
            System.out.println("SQLException in creating Proj Team:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Proj Team:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
   }

public void addProjTeam(){

    Connection con=null;
        try{
        saveTeam_Sql="";
        //    System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
       
        Statement statement = con.createStatement();

        int alloc_flag=Integer.parseInt(allocType);
        //the below block checks for the property ID as it should start from 106000 and starts adding the project ID from that
        //Generate Project ID
               
       // System.out.println("insert into contacts(firstname,surname) values ('"+firstName+"','"+surName+"')  ");
       //System.out.println("allocType: "+allocType);
       //System.out.println("cellId: "+cellId);
       //System.out.println("deptId: "+deptId);
       
       if (alloc_flag == 2) {

       if(memberId.equals("All")||memberId.equals(""))
       {
           memberId="";
       }

        //below query checks for any record added already for the selected Role(Activity).
        //The IF condition is executed for an EmpId wrt the selected role or else
        //the same check will be done for the Role alone
        if(!memberId.equals("")){
            //if the Employee Name is  selected then the below quey is used to check the selected Employee has been already added to the team
        chkRecordSql=" select emp_id,primary_incharge from project_team" +
                        " where proj_id='"+prjid+"' and role_id='"+roleId+"' " +
                        " and emp_id='"+memberId+"' and proj_alloc_type='"+allocType+"' and cell_code is null ";
        }
        else{
            //if the employee ID is not selected then the below query will be executed
             chkRecordSql=" select facility_id,primary_incharge from project_team" +
                        " where proj_id='"+prjid+"' and role_id='"+roleId+"' " +
                        " and emp_id is null and proj_alloc_type='"+allocType+"' and facility_id='"+locationId+"' and cell_code is null ";
        }
      String chkValue = "";

     ResultSet rsCheckProPValue =  statement.executeQuery(chkRecordSql);
     while(rsCheckProPValue.next()){
       chkValue=rsCheckProPValue.getString(1);
       chkprimaryIncharge=rsCheckProPValue.getString(2);
        if(rsCheckProPValue.wasNull()){
           chkprimaryIncharge="0";
        }
       }
/***********************************************/
       if(!memberId.equals("")){
           //with the above query we won't get if the PrimaryIncharge has been already mapped
           //in the selected location. hence the same query is executed below only if the team member is selected.
           //The reason for using above if else block is to use generic variables for both cases if the Team Meber is seleted or only the location is selected
            //if the Employee Name is  selected then the below quey is used to check the selected Employee has been already added to the team
        chkRecordSql=" select primary_incharge,facility_id from project_team" +
                        " where proj_id='"+prjid+"' and role_id='"+roleId+"' and facility_id='"+locationId+"' and proj_alloc_type='"+allocType+"' and cell_code is null ";
        }

     rsCheckProPValue =  statement.executeQuery(chkRecordSql);
     while(rsCheckProPValue.next()){
       chkprimaryIncharge=rsCheckProPValue.getString(1);
        if(rsCheckProPValue.wasNull()){
           chkprimaryIncharge="0";
        }
       chkFacilityId=rsCheckProPValue.getString(2);
        if(rsCheckProPValue.wasNull()){
           chkprimaryIncharge="0";
        }

       }
     rsCheckProPValue.close();
     /*************************************************/

//     System.out.println("chkRecordSql:"+chkRecordSql);
//     System.out.println("chkValue:"+chkValue);
//     System.out.println("chkprimaryIncharge:"+chkprimaryIncharge);

     //If the primary incharge is not selected then the below If condition will be executed
     if(primaryIncharge.equals("")){
         //If the member is selected then the below query will be executed
         saveTeam_Sql="insert into project_team(proj_id,proj_alloc_type,role_id,emp_id, " +
               " facility_id,date_assigned)" +
               " values ('"+prjid+"','"+allocType+"','"+roleId+"','"+memberId+"', " +
               "  '"+locationId+"',CURRENT_TIMESTAMP())  ";

            //If the member is not selected then the below query will be executed
         if(memberId.equals("")){
             if(chkValue.equals(locationId)){

             }else{
                saveTeam_Sql="insert into project_team(proj_id,proj_alloc_type,role_id, " +
               " facility_id,date_assigned)" +
               " values ('"+prjid+"','"+allocType+"','"+roleId+"', " +
               "  '"+locationId+"',CURRENT_TIMESTAMP())  ";
             }
         }
     }else{

//System.out.println("In else Save:"+"chkValue:"+chkValue+"locationId:"+locationId);
        if((chkprimaryIncharge.equals("0")||chkprimaryIncharge.equals(""))&&!chkValue.equals(locationId)){
            if(memberId.equals("")){
                if(chkValue.equals(locationId)){

                }else{
                     saveTeam_Sql= "insert into project_team(proj_id,proj_alloc_type,role_id," +
                       " primary_incharge,facility_id,date_assigned)" +
                       " values ('"+prjid+"','"+allocType+"','"+roleId+"', " +
                       "  '"+primaryIncharge+"','"+locationId+"',CURRENT_TIMESTAMP())  ";
                }
             
            }
            else{
                saveTeam_Sql= "insert into project_team(proj_id,proj_alloc_type,role_id,emp_id," +
               " primary_incharge,facility_id,date_assigned)" +
               " values ('"+prjid+"','"+allocType+"','"+roleId+"','"+memberId+"', " +
               " '"+primaryIncharge+"','"+locationId+"',CURRENT_TIMESTAMP())  ";
            }
        }//close of if(chkprimaryIncharge)

        
}
//System.out.println("-final:"+saveTeam_Sql);

     if(chkValue.equals("")&&!saveTeam_Sql.equals("")){
       addPropValue = statement.executeUpdate(saveTeam_Sql);
       returnAlert="Allocated";
     }
     else{
            if(!chkValue.equals(locationId)&&memberId.equals("")){
                    addPropValue = statement.executeUpdate(saveTeam_Sql);
                    returnAlert="Allocated";
            }
     }


     if(memberId.equals("")){

      if(!primaryIncharge.equals("")){
         if(chkValue.equals(locationId)&&chkprimaryIncharge.equals("1")){
            returnAlert="Primary Incharge has been already allocated";
        }
     }
      else{
           if(chkValue.equals(locationId)){
              returnAlert="Already Allocated";
           }
        }
      //System.out.println("returnAlert-Bean-In IF:"+returnAlert);
     }
     else{
//System.out.println("returnAlert-Bean-In else:"+returnAlert);
        if(!primaryIncharge.equals("")){
             if(chkFacilityId.equals(locationId)&&chkprimaryIncharge.equals("1")){
            /*System.out.println("chkFacilityId:"+chkFacilityId);
            System.out.println("primaryIncharge:"+primaryIncharge);*/
            returnAlert="Primary Incharge has been already allocated";
            }
        }
        else{
            returnAlert="Already Allocated";
        }       

     }
            }

        if (alloc_flag == 1) {
            if(deptId.equals("All")||deptId.equals("")) {
                deptId="";
            }

            

        }
        statement.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Proj Team:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Proj Team:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
             
}

}
