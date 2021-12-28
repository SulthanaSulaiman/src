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
public class SaveProjTeam implements Serializable {

   
    private String prjid="";
    private String allocType="";
    private String deptId="";
    private String cellId="";
    private String roleId="";
    private String desigCode="";
    private String memberId="";
    private String vendorId="";
    private String locationId="";
    private String primaryIncharge="";
    private String saveTeam_Sql="";
    private String saveCell_Sql="";
    private String updateCell_Val="";
    private String chkRecordSql="";
    private String teamMappedId="";
    private String chkprimaryIncharge="";
    private String chkCellRecordSql="";
    private String chkFacilityId="";
    private String chkCellId="";
    private String chkAllocType="";
    private String deptAndCellIsNull="";
    private String updateTeam_Val="";
    private String dept_code="";
    private String cell_code="";

    private int addPropValue=0;
    private int cellCodeUpdateValue=0;
    private String returnAlert="";


    public SaveProjTeam() {
        
    }

    public void setDesigCode(String desigCode) {
        this.desigCode = desigCode;
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

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
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

        ResultSet deptCellNullCheck=statement.executeQuery("SELECT EXISTS(SELECT proj_id,cell_code,role_id,emp_id FROM project_team WHERE proj_id='"+prjid+"') AS deptCellNullFlag");
            while(deptCellNullCheck.next())
            {
                deptAndCellIsNull=deptCellNullCheck.getString(1);
            }

        if(deptAndCellIsNull.equals("0"))   {
        cellCodeUpdateValue=statement.executeUpdate("update projects set dept_code=null, cell_code = null where proj_id = '"+prjid+"'");
        }
        if(addPropValue>0||cellCodeUpdateValue>0){
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

       if(memberId.equals("All")||memberId.equals("") || vendorId.equals(""))
       {
           memberId="";
           vendorId="";
       }

        //below query checks for any record added already for the selected Role(Activity).
        //The IF condition is executed for an EmpId wrt the selected role or else
        //the same check will be done for the Role alone
        if(!memberId.equals("") || !vendorId.equals("")){
            //if the Employee Name is  selected then the below quey is used to check the selected Employee has been already added to the team
            if(!memberId.equals("")){
        chkRecordSql=" select emp_id,primary_incharge from project_team" +
                        " where proj_id='"+prjid+"' and desig_code='"+desigCode+"' " +
                        " and emp_id='"+memberId+"' and proj_alloc_type='"+allocType+"' and cell_code is null ";
            }else if(!vendorId.equals("")){
                chkRecordSql=" select emp_id,primary_incharge from project_team" +
                        " where proj_id='"+prjid+"' and desig_code='"+desigCode+"' " +
                        " and emp_id='"+vendorId+"' and proj_alloc_type='"+allocType+"' and cell_code is null ";
            }
        }
        else{
            //if the employee ID is not selected then the below query will be executed
             chkRecordSql=" select facility_id,primary_incharge from project_team" +
                        " where proj_id='"+prjid+"' and desig_code='"+desigCode+"' " +
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
                        " where proj_id='"+prjid+"' and desig_code='"+desigCode+"' and facility_id='"+locationId+"' and proj_alloc_type='"+allocType+"' and cell_code is null ";
        String empCellDetail="SELECT dept_code, cell_code FROM emp_cell_map WHERE emp_id='"+memberId+"'";
        ResultSet rsEmpCellDetail =  statement.executeQuery(empCellDetail);
            while(rsEmpCellDetail.next())
            {
                dept_code=rsEmpCellDetail.getString(1);
                cell_code=rsEmpCellDetail.getString(1);
            }
        }
      if(!vendorId.equals("")){
          chkRecordSql=" select primary_incharge,facility_id from project_team" +
                        " where proj_id='"+prjid+"' and desig_code='"+desigCode+"' and facility_id='"+locationId+"' and proj_alloc_type='"+allocType+"' and cell_code is null ";

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
         if(!memberId.equals("")){
         saveTeam_Sql="insert into project_team(proj_id,proj_alloc_type,desig_code,emp_id, " +
               " facility_id,date_assigned)" +
               " values ('"+prjid+"','"+allocType+"','"+desigCode+"','"+memberId+"', " +
               "  '"+locationId+"',CURRENT_TIMESTAMP())  ";

         updateTeam_Val = " update projects set cell_code = '"+cell_code+"', dept_code='"+dept_code+"' WHERE proj_id = '"+prjid+"' ";
         }
          if(!vendorId.equals("")){
         saveTeam_Sql="insert into project_team(proj_id,proj_alloc_type,desig_code,emp_id, " +
               " facility_id,date_assigned)" +
               " values ('"+prjid+"','"+allocType+"','"+desigCode+"','"+vendorId+"', " +
               "  '"+locationId+"',CURRENT_TIMESTAMP())  ";
         }

            //If the member is not selected then the below query will be executed
         if(memberId.equals("") || vendorId.equals("")){
             if(chkValue.equals(locationId)){

             }else{
                saveTeam_Sql="insert into project_team(proj_id,proj_alloc_type,desig_code, " +
               " facility_id,date_assigned)" +
               " values ('"+prjid+"','"+allocType+"','"+desigCode+"', " +
               "  '"+locationId+"',CURRENT_TIMESTAMP())  ";
             }
         }
     }else{

//System.out.println("In else Save:"+"chkValue:"+chkValue+"locationId:"+locationId);
        if((chkprimaryIncharge.equals("0")||chkprimaryIncharge.equals(""))&&!chkValue.equals(locationId)){
            if(memberId.equals("") ||vendorId.equals("")){
                if(chkValue.equals(locationId)){

                }else{
                     saveTeam_Sql= "insert into project_team(proj_id,proj_alloc_type,desig_code," +
                       " primary_incharge,facility_id,date_assigned)" +
                       " values ('"+prjid+"','"+allocType+"','"+desigCode+"', " +
                       "  '"+primaryIncharge+"','"+locationId+"',CURRENT_TIMESTAMP())  ";

                     updateTeam_Val = " update projects set cell_code = '"+cell_code+"', dept_code='"+dept_code+"' WHERE proj_id = '"+prjid+"' ";
                }
             
            }
            else{
                if(!memberId.equals("")){
                saveTeam_Sql= "insert into project_team(proj_id,proj_alloc_type,desig_code,emp_id," +
               " primary_incharge,facility_id,date_assigned)" +
               " values ('"+prjid+"','"+allocType+"','"+desigCode+"','"+memberId+"', " +
               " '"+primaryIncharge+"','"+locationId+"',CURRENT_TIMESTAMP())  ";

                updateTeam_Val = " update projects set cell_code = '"+cell_code+"', dept_code='"+dept_code+"' WHERE proj_id = '"+prjid+"' ";
                }
                if(!vendorId.equals("")){
                    saveTeam_Sql= "insert into project_team(proj_id,proj_alloc_type,desig_code,emp_id," +
               " primary_incharge,facility_id,date_assigned)" +
               " values ('"+prjid+"','"+allocType+"','"+desigCode+"','"+vendorId+"', " +
               " '"+primaryIncharge+"','"+locationId+"',CURRENT_TIMESTAMP())  ";
                }
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
                    cellCodeUpdateValue = statement.executeUpdate(updateTeam_Val);
                    returnAlert="Allocated";
            }
            if(!chkValue.equals(locationId)&&vendorId.equals("")){
                    addPropValue = statement.executeUpdate(saveTeam_Sql);
                    returnAlert="Allocated";
            }
     }


     if(memberId.equals("") || vendorId.equals("")){

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

            if(cellId.equals("All")||cellId.equals("")) {
                cellId="";
            }

        if(!cellId.equals("")){
        chkCellRecordSql=" SELECT cell_code,proj_alloc_type FROM project_team " +
                        " where proj_id='"+prjid+"' AND proj_alloc_type='"+allocType+"' and cell_code is not null ";
        }
                            
     ResultSet rsCheckCellValue =  statement.executeQuery(chkCellRecordSql);
     while(rsCheckCellValue.next()){
       chkCellId = rsCheckCellValue.getString(1);
       chkAllocType = rsCheckCellValue.getString(2);
       }
rsCheckCellValue.close();
//     System.out.println("chkCellRecordSql: "+chkCellRecordSql);
//     System.out.println("chkCellId: "+chkCellId);
//     System.out.println("chkAllocType: "+chkAllocType);

     if(!cellId.equals("")){

         saveCell_Sql="insert into project_team(proj_id,proj_alloc_type,cell_code,primary_incharge, " +
               " facility_id,date_assigned)" +
               " values ('"+prjid+"','"+allocType+"','"+cellId+"','"+primaryIncharge+"', " +
               "  '"+locationId+"',CURRENT_TIMESTAMP())  ";
         updateCell_Val = " update projects set cell_code = '"+cellId+"', dept_code='"+deptId+"' WHERE proj_id = '"+prjid+"' ";
     }

     //System.out.println("saveCell_Sql: "+saveCell_Sql);

     if(chkCellId.equals("")&&!saveCell_Sql.equals("")){
       addPropValue = statement.executeUpdate(saveCell_Sql);
       cellCodeUpdateValue = statement.executeUpdate(updateCell_Val);
       returnAlert="Allocated";
     }
     else {
        if(!cellId.equals("")){
            if(chkCellId.equals(cellId)&&chkAllocType.equals("1")){
                returnAlert="Project has been already allocated for that selected cell";
            }
            else if(!chkCellId.equals("")){
                returnAlert="Project has been already allocated";
            }
        }
        else{
            returnAlert="Please select a cell.";
        }       
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
