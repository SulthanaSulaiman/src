/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects.chapters;
                             /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/


import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Gandhimathidevic
 */
public class StageAndMilestoneForAutoSchdule {
List stageNameList = new ArrayList();
List stageCodeList = new ArrayList();
String stageName="";
String stageCodeString="";
List MappedMilestonesName =  new ArrayList();
String proj_id="";
List Chapters_name = new ArrayList();
String NameofChapter="";
String NameofMilestones="";
List disChapters_name = new ArrayList();
List disMilestones = new ArrayList();
List disReceivedDate = new ArrayList();
List chkmilestonedetails = new ArrayList();
List chkProjdetails = new ArrayList();
List chkdaysdetails = new ArrayList();
String receviedDateUpdate = "";
String chapterNoUpdate = "";
String MilestoneUpdate = "";
String milestoneTogetDays="";
String daysForProvidedMilestn="";
  public void getStageNameChk(){
    try {
      Connection con = null;
      DBconnection dbcon = new DBconnection();
      con = dbcon.getSampleProperty();
      ResultSet rs = null;
      String QuerygetStage = "select stage,stage_code from project_stage";
      Statement stm=con.createStatement();
      rs=stm.executeQuery("select * from project_stage");
      while(rs.next())
      stageNameList.add(rs.getString(1));
      stageCodeList.add(rs.getString(2));
      //con.close();
    }
    catch (SQLException sqle) {
      System.out.println("SQLException in getChapterDetails_1:" + sqle);
    } catch (Exception e) {
      System.out.println("Exception in getChapterDetails_2:" + e);
    }

  }
  public void setReceviedDateUpdate(String receviedDateUpdate){
      this.receviedDateUpdate=receviedDateUpdate;
  }
  public void setChapterNoUpdate(String chapterNoUpdate){
      this.chapterNoUpdate=chapterNoUpdate;
  }
  public void setMilestoneUpdate(String MilestoneUpdate){
      this.MilestoneUpdate =MilestoneUpdate;
  }
  public String getMilestoneUpdate(){
      return MilestoneUpdate;
  }
  public String getReceviedDateUpdate(){
      return receviedDateUpdate;
  }
  public String getChapterNoUpdate(){
      return chapterNoUpdate;
  }
  public List stageItems(){
  return stageNameList;
  }
  public List getchkMilestonesdetailsprovd(){
  return chkmilestonedetails;
  }
  public List getchkProjdetailsprovd(){
  return chkProjdetails;
  }
  public List getchkdaysdetailsprovd(){
  return chkdaysdetails;
  }
  public List stageCodes(){
  return stageCodeList;
  }
  public void setStageName(String stageName){
  this.stageName=stageName;
  }
//  String NameofChapter="";
//String NameofMilestones="";
public void setNameofChapter(String NameofChapter){
  this.NameofChapter=NameofChapter;
  }
public void setNameofMilestones(String NameofMilestones){
  this.NameofMilestones=NameofMilestones;
  }
  public void setProj_id(String proj_id){
  this.proj_id=proj_id;
  }
  public String getStageCode(){
     return stageCodeString;
  
  }
  public void setMilestoneTogetDays(String milestoneTogetDays){
      this.milestoneTogetDays=milestoneTogetDays;
  }
  public String getDaysforMilestn(){
      return daysForProvidedMilestn;
  }
  public List MilestonesName(){
     try {
      Connection con = null;

      DBconnection dbcon = new DBconnection();
      con = dbcon.getSampleProperty();
      ResultSet rs = null;
      Statement stm=con.createStatement();
        
   rs=stm.executeQuery("SELECT Milestone_name"+
" FROM milestones_autoschedule"+
" ORDER BY Milestone_id");
   int chkkk=0;
    while(rs.next())
  {
  MappedMilestonesName.add(rs.getString(1));
  chkkk++;
    }
//System.out.println("chkkk"+chkkk);

     con.close();
    }


    catch (SQLException sqle) {
      System.out.println("SQLException in getChapterDetails_3:" + sqle);
    } catch (Exception e) {
      System.out.println("Exception in getChapterDetails_4:" + e);
    }

return MappedMilestonesName;

  }
  public String getStageCodeChk(){
    try {
      Connection con = null;

      DBconnection dbcon = new DBconnection();
      con = dbcon.getSampleProperty();
      ResultSet rs = null;
      String QuerygetStage = "select stage_code from project_stage where stage='"+stageName+"'";
      Statement stm=con.createStatement();
      rs=stm.executeQuery("select stage_code from project_stage where stage='"+stageName+"'");
     while(rs.next())
     {
      stageCodeString = rs.getString(1);
     }
      //System.out.println("stagecode"+stageCodeString);
     //con.close();
     
   //  ResultSet rs1 = null;
     // rs1=stm.executeQuery("SELECT m.milestone_act_code, m.milestone_act_name"+
//" FROM proj_milestone_act m, milestone_order o"+
//" WHERE o.stage_code='"+stageCodeString+"' AND o.milestone_act_code=m.milestone_act_code ORDER BY m.milestone_act_name");
  //    while(rs1.next())
//    {
//  stageCodeString = rs.getString(1);
  //    System.out.println("Milestone Names"+rs1.getString(1)+rs1.getString(2));
    //  }


     con.close();
    }


    catch (SQLException sqle) {
      System.out.println("SQLException in getChapterDetails_5:" + sqle);
    } catch (Exception e) {
      System.out.println("Exception in getChapterDetails_6:" + e);
    }
return stageCodeString;
  }
  public List getChaptersName(){
    try {
      Connection con = null;

      DBconnection dbcon = new DBconnection();
      con = dbcon.getSampleProperty();
      ResultSet rs = null;
      Statement stm=con.createStatement();
      rs=stm.executeQuery("SELECT chapter_no FROM project_bookmap WHERE proj_id='"+proj_id+"'");
     while(rs.next())
     {
        Chapters_name.add(rs.getString(1));
     }
      
     //con.close();

   //  ResultSet rs1 = null;
     // rs1=stm.executeQuery("SELECT m.milestone_act_code, m.milestone_act_name"+
//" FROM proj_milestone_act m, milestone_order o"+
//" WHERE o.stage_code='"+stageCodeString+"' AND o.milestone_act_code=m.milestone_act_code ORDER BY m.milestone_act_name");
  //    while(rs1.next())
//    {
//  stageCodeString = rs.getString(1);
  //    System.out.println("Milestone Names"+rs1.getString(1)+rs1.getString(2));
    //  }


     con.close();
    }


    catch (SQLException sqle) {
      System.out.println("SQLException in getChapterDetails_7:" + sqle);
    } catch (Exception e) {
      System.out.println("Exception in getChapterDetails_8:" + e);
    }
return Chapters_name;
  }
public String saveChapterAutoschedule(String NameofChapter,String NameofMilestones,String ReceivedDate,String Proj_id){

 Connection con = null;
        PreparedStatement statement = null;
        int addChapterPlan = 0;
        ChapterPlanDetVO rqstVO = null;
        String Chapter_no = "";
        String Dayys2="";
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();

            String saveQry = " INSERT into autoscheduleplan(Chapter_no,Milestone,Recevied_date,planned_Date,proj_id) "
                    + " VALUES(?,?,?,CURRENT_TIMESTAMP,?)";

            statement = con.prepareStatement(saveQry);
            
            
            String Received_date = "";
            String Milestone = "";


           // Iterator itr = rqstList.iterator();
           // while (itr.hasNext()) {
              //  rqstVO = (ChapterPlanDetVO) itr.next();
                //planId = rqstVO.getPlanId();
             //   chapterId = rqstVO.getChapterId();
             //   endDate = rqstVO.getEndDate();
             //   mileStoneCode = rqstVO.getMileStoneId();

               statement.setString(1, NameofChapter);
               statement.setString(2, NameofMilestones);
               String temp = ReceivedDate != "" ? ReceivedDate : null;
               statement.setString(3, temp);
               statement.setString(4, Proj_id);
                //statement.setString(3, endDate);
//
                statement.executeUpdate();
              //  addChapterPlan++;


                    //System.out.println("saveQry:" + saveQry);



           // }

}
catch(SQLException sqle){
      System.out.println("SQLException in getChapterDetails_9:" + sqle);
}
catch(Exception e){
      System.out.println("SQLException in getChapterDetails_10:" + e);
}
return Chapter_no;
}
public void getChapterdetails(){
 try {
      Connection con = null;
      DBconnection dbcon = new DBconnection();
      con = dbcon.getSampleProperty();
      ResultSet rs = null;
      //System.out.println("stgNam" + stageName);
      //String QuerygetStage = "SELECT * FROM project_bookmap2 WHERE proj_id='"++"'";
      //System.out.println(QuerygetStage);
      Statement stm=con.createStatement();
      rs=stm.executeQuery("SELECT Chapter_no,Milestone,Recevied_date FROM autoscheduleplan WHERE proj_id='"+proj_id+"'");
      //System.out.println(proj_id);
     while(rs.next())
     {
        // System.out.println(rs.getString(1)+"=>"+rs.getString(2));
disChapters_name.add(rs.getString(1));
disMilestones.add(rs.getString(2));

if(rs.getString(3)!=null){
    disReceivedDate.add(rs.getString(3));
          }
 else{
    disReceivedDate.add("N/A");
 }

    }
     con.close();
    }

    catch (SQLException sqle) {
      System.out.println("SQLException in getChapterDetails_11:" + sqle);
    } catch (Exception e) {
      System.out.println("Exception in getChapterDetails_2:" + e);
    }

}
    public void updateAutoScheduleDays(){
 DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String set_shipping_method_query1 = "Update autoscheduleplan set Recevied_date='"+receviedDateUpdate+"' WHERE proj_id='"+proj_id+"' and Chapter_no='"+chapterNoUpdate+"' and Milestone='"+MilestoneUpdate+"'";
            //System.out.println(set_shipping_method_query1);
            st.executeUpdate(set_shipping_method_query1);
        } catch (SQLException e) {
                e.printStackTrace();
        }
}
public List setProjidForDis(){
    return disChapters_name;
}
public List getDispChapterName(){
    return disChapters_name;
}
public List getDisMilestones(){
    return disMilestones;
}
public List getdisReceivedDate(){
    return disReceivedDate;
}
public void SetUpdateautoScheduleMilestone(){
    
}
public void chkautoScheduledetailsProvd(){
 try {
      Connection con = null;
      DBconnection dbcon = new DBconnection();
      con = dbcon.getSampleProperty();
      ResultSet rschk = null;
      String updat = "";
      Statement stm=con.createStatement();
      //daysformilestoneautosch
      rschk=stm.executeQuery("SELECT Proj_id,Milestones,UpdatedDays FROM proj_updatedautoscheduledays WHERE proj_id='"+proj_id+"'");
      while (rschk.next() ) {
chkProjdetails.add(rschk.getString(1));
chkmilestonedetails.add(rschk.getString(2));
chkdaysdetails.add(rschk.getString(3));
       //System.out.println("sizeoffjava"+rschk.getString(1)+rschk.getString(2)+rschk.getString(3));
  
 }
              con.close();
 }
    catch (SQLException sqle) {
      System.out.println("SQLException in getChapterDetails_13:" + sqle);
    } catch (Exception e) {
      System.out.println("Exception in getChapterDetails_14:" + e);
    }
}
public void UpdateautoScheduleMilestone(String Proj_id, String NameofMilestones, String editDayys){
 try {
      Connection con = null;
      DBconnection dbcon = new DBconnection();
      con = dbcon.getSampleProperty();
      ResultSet rs = null;
      String updat = "";
      Statement stm=con.createStatement();
      String QueryChk="SELECT Milestones,UpdatedDays FROM proj_updatedautoscheduledays WHERE proj_id='"+Proj_id+"' and Milestones='"+NameofMilestones+"' and UpdatedDays='"+editDayys+"'";
      rs=stm.executeQuery("SELECT Milestones FROM proj_updatedautoscheduledays WHERE proj_id='"+Proj_id+"' and Milestones='"+NameofMilestones+"'");
                if (!rs.next() ) {
    StageAndMilestoneForAutoSchdule editDays = new StageAndMilestoneForAutoSchdule();
    editDays.insertEditdays(Proj_id,NameofMilestones,editDayys);
                //System.out.println("not in table"+QueryChk);
con.close();
  
}
 else{
//while(rs.next())
 //{
//System.out.println("table"+Proj_id+NameofMilestones+editDayys);
StageAndMilestoneForAutoSchdule editDays = new StageAndMilestoneForAutoSchdule();
editDays.updateEditDays(Proj_id,NameofMilestones,editDayys);
 //}

 }
      if (updat.equals("yes")){

//rs=stm.executeUpdate("INSERT into proj_updatedautoscheduledays(Proj_id) "
  //                  + " VALUES('"+Proj_id+"')");
    //  StageAndMilestoneForAutoSchdule editDays = new StageAndMilestoneForAutoSchdule();
    //  editDays.insertEditdays(Proj_id,NameofMilestones,editDayys);
      }
     con.close();
    }

    catch (SQLException sqle) {
      System.out.println("SQLException in getChapterDetails_13:" + sqle);
    } catch (Exception e) {
      System.out.println("Exception in getChapterDetails_14:" + e);
    }

}
public void insertEditdays(String Proj_id1, String NameofMilestones1, String Dayys1){

   DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

             String set_shipping_method_query = "INSERT INTO proj_updatedautoscheduledays (Proj_id, Milestones, UpdatedDays) "
                                                + " VALUES ('"  + Proj_id1 + "','" + NameofMilestones1 + "','" +Dayys1+ "')";

             st.executeUpdate(set_shipping_method_query);
             con.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
}
public void updateEditDays(String Proj_id1, String NameofMilestones1, String Dayys1){

   DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

             String set_shipping_method_query = "Update proj_updatedautoscheduledays set UpdatedDays='"+Dayys1+"' WHERE Proj_id ='"+Proj_id1+"' and Milestones ='"+NameofMilestones1+"'";
             //System.out.println("Update Query"+set_shipping_method_query);
             
             st.executeUpdate(set_shipping_method_query);
             con.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
}
public void DaysForMilestoneProvdProjid(){
DBconnection dbconnection = new DBconnection();
        Connection con=null;
        Statement st=null;
        ResultSet rs =null;
try
{
        con = dbconnection.getSampleProperty();
        st = con.createStatement();
       String queryforGetDays="select UpdatedDays from proj_updatedautoscheduledays where Proj_id='"+proj_id+"' and Milestones='"+milestoneTogetDays+"'";

      rs=st.executeQuery(queryforGetDays);
      while (rs.next()){
daysForProvidedMilestn = rs.getString(1);
      }
      con.close();
}
catch(SQLException e){

}
}

        public static void main(String ar[]){

    //StageAndMilestoneForAutoSchdule autoschdul = new StageAndMilestoneForAutoSchdule();
    //autoschdul.getStageNameChk();

  }
}

