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
 * This class is to retrieve the chapterID's for which
 * the plan was created within 24 hours from the current time
 */
public class ChapPlanCache implements Serializable {

   private List chapterName= new ArrayList();
   private List chapterId= new ArrayList();

      private List planId= new ArrayList();

   private String prjid= "";
   private String addedDate= "";
   private String stage= "";
   private String tempResult= "";

   private int addProj=0;

    public ChapPlanCache() {

    }


 /*  public List getChapterId(){
        return chapterId;
    }*/

      public List getPlanId(){
        return planId;
    }


         public void setStage(String stage){
         this.stage=stage;
        // System.out.println("prjid:"+prjid);
   }



   public void setProjId(String prjid){
         this.prjid=prjid;
        // System.out.println("prjid:"+prjid);
   }



public void collectPlanCache(){

Connection con=null;
chapterId.clear();
     try{
            //the fields are retrieved in parts so taht the resultset
            //doesnot get affected while referencing null values in joining the tables
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();

        Statement statement = con.createStatement();

        String planSQL =  " select distinct(chp.plan_id) from chapter_plan chp,chapter ch  " +
                            " where chp.chapter_id=ch.chapter_id " +
                            " and TIMESTAMPDIFF(HOUR,chp.planned_date,CURRENT_TIMESTAMP())<'24' " +
                            " and ch.stage='"+stage+"' and ch.proj_id='"+prjid+"' " ;

        //System.out.println("projNameSQL-Client Name:"+planSQL);

              ResultSet rsGetPlan=statement.executeQuery(planSQL);
                while(rsGetPlan.next()){
                    tempResult=rsGetPlan.getString("chp.plan_id");
                    planId.add(tempResult);
                }
              statement.close();
              rsGetPlan.close();

        }catch(SQLException sqle){
            System.out.println("SQLException in ChapPlanCache:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in ChapPlanCache:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }


}


}
