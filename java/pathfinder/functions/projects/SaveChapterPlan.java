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
public class SaveChapterPlan implements Serializable {

    private String chapterId="";
    private String mileStoneCode="";
    private String endDate="";
    private String maxPlanId="";
    private String planId="";

    private int addChapterPlan=0;


    public SaveChapterPlan() {
        
    }
   
     public void setChapterId(String chapterId){
        this.chapterId=chapterId;
    }

public String getMaxPlanId(){
    return maxPlanId;
}

public void setPlanId(String planId){
 this.planId=planId;
}


   public String getPlanAdded(){
        return String.valueOf(addChapterPlan);
   }

   public void setMileStone(String mileStoneCode){
        this.mileStoneCode=mileStoneCode;
   }

   public void setEndDate(String endDate){
        this.endDate=endDate;
   }

   public void maxPlanId(){
       Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        
        Statement statement = con.createStatement();
        ResultSet rsmaxPlanId = statement.executeQuery("select max(plan_id) from chapter_plan");
        while(rsmaxPlanId.next()){
            maxPlanId= rsmaxPlanId.getString(1);
            if(rsmaxPlanId.wasNull()){
                maxPlanId="0";
            }
         }
        rsmaxPlanId.close();
        statement.close();


        }catch(SQLException sqle){
            System.out.println("SQLException in creating Plan Creation:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Plan Creation:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
   }


public void addPlan(){
    
Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();       
        Statement statement = con.createStatement(); 

        //System.out.println("insert into chapter_plan(plan_id,chapter_id,milestone_id,end_date,planned_Date) "+
                    //" values ('"+planId+"',"+chapterId+"','"+mileStoneCode+"','"+endDate+"' )  ");

       addChapterPlan = statement.executeUpdate("insert into chapter_plan(plan_id,chapter_id,milestone_id,end_date,planned_date) "+
                    " values ('"+planId+"','"+chapterId+"','"+mileStoneCode+"','"+endDate+"',CURRENT_TIMESTAMP() )  ");
       
       statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Plan Creation:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Plan Creation:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
        }


}


public void modifyPlan(){

Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        String chap_MileStone = "";
        String chap_EndDate = "";
        Statement statement = con.createStatement();

        ResultSet rsChapPlanInfo = statement.executeQuery("select milestone_id,end_date from chapter_plan where chapter_id='"+chapterId+"' ");
        while(rsChapPlanInfo.next()){
           chap_MileStone=rsChapPlanInfo.getString("milestone_id") ;
           if(rsChapPlanInfo.wasNull()){
              chap_MileStone="";
           }
           chap_EndDate=rsChapPlanInfo.getString("end_date") ;
           if(rsChapPlanInfo.wasNull()){
              chap_EndDate="";
           }
        }
rsChapPlanInfo.close();

        if(!chap_MileStone.equals("")&&!chap_MileStone.equals(mileStoneCode)){

        addChapterPlan = statement.executeUpdate("update chapter_plan set milestone_id='"+mileStoneCode+"' "+
                    " where chapter_id='"+chapterId+"'   ");
        }

        if(!endDate.equals("")&&!endDate.equals(mileStoneCode)){

        addChapterPlan = statement.executeUpdate("update chapter_plan set end_date='"+endDate+"' "+
                    " where chapter_id='"+chapterId+"'   ");
        }
statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Plan Creation:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Plan Creation:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }


}

public void deletePlan(){

Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        String chap_MileStone = "";
        String chap_EndDate = "";
        Statement statement = con.createStatement();

        addChapterPlan = statement.executeUpdate("delete from  chapter_plan where milestone_id='"+mileStoneCode+"' "+
                    " and chapter_id='"+chapterId+"'   ");
statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Plan Creation:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Plan Creation:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }


}

}
