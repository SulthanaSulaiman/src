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
public class ChapterPlanInfo implements Serializable {

   private List chapterName= new ArrayList();
   private List mileStoneCode= new ArrayList();
   private List mileStoneName= new ArrayList();
   private List dispChapterId= new ArrayList();
   private List endDate= new ArrayList();
   private List dispEndDate= new ArrayList();

   private String chapterId= "";
   private String planId= "";
   private String tempResult= "";

    private int addProj=0;


    public ChapterPlanInfo() {

    }

    public List getMileStoneCode(){
        return mileStoneCode;
    }

   public List getMileStoneName(){
        return mileStoneName;
    }

      public List getDispEndDate(){
        return dispEndDate;
    }

    public List getEndDate(){
        return endDate;
    }


   /*public void setChapterId(String chapterId){
         this.chapterId=chapterId;
        // System.out.println("prjid:"+prjid);
   }*/

      public void setPlanId(String planId){
         this.planId=planId;
        // System.out.println("prjid:"+prjid);
   }

public void collectChapterPlan(){

Connection con=null;
mileStoneCode.clear();
mileStoneName.clear();
dispEndDate.clear();
endDate.clear();

        try{
            //the fields are retrieved in parts so taht the resultset
            //doesnot get affected while referencing null values in joining the tables
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();

        Statement statement = con.createStatement();

        String planSQL =  " select distinct(atp.activity),date_format(chp.end_date,'%Y-%m-%d %H:%i:%s') as end_date, " +
                            "date_format(chp.end_date,'%d-%b-%Y %H:%i %p')as endDate,chp.milestone_id " +
                                " from chapter_plan chp,activity_type atp " +
                                " where chp.milestone_id=atp.activity_code " +
                                " and chp.plan_id='"+planId+"' order by milestone_id ";

        //System.out.println("Chapter Plan Info SQl:"+planSQL);

        //the below block is for retreiving the information for the projectId passed to display in the Add Projects interface once the project has been addded

              ResultSet rsGetPlan=statement.executeQuery(planSQL);
                while(rsGetPlan.next()){
                    tempResult=rsGetPlan.getString("atp.activity");
                    mileStoneName.add(tempResult);
                    tempResult=rsGetPlan.getString("end_date");
                    endDate.add(tempResult);
                    tempResult=rsGetPlan.getString("endDate");
                    dispEndDate.add(tempResult);
                    tempResult=rsGetPlan.getString("chp.milestone_id");
                    mileStoneCode.add(tempResult);
                }
              statement.close();
              rsGetPlan.close();

        }catch(SQLException sqle){
            System.out.println("SQLException in ProjIdAttribs:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in ProjIdAttribs:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }


}

}
