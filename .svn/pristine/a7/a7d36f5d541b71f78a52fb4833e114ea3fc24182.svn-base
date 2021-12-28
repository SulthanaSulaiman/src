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
 * Once a set of chapters are added then their ID's should be passed to the page where the plan is created
 * TO retrieve those chapter ID's this class is used. since the link "Create Plan"
 * will be clicked or referenced from a single chapter, The chapters of the same stage
 * added in the same date and time are retrieved in the below class and further process is done
 */
public class ListPlanChapter implements Serializable {

   private List chapterName= new ArrayList();
   private List chapterId= new ArrayList();

   private String prjid= "";
   private String addedDate= "";
   private String stage= "";
   private String tempResult= "";

   private int addProj=0;

    public ListPlanChapter() {

    }

    public List getChapterName(){
        return chapterName;
    }

   public List getChapterId(){
        return chapterId;
    }

      public void setAddedDate(String addedDate){
         this.addedDate=addedDate;
        // System.out.println("prjid:"+prjid);
   }

         public void setStage(String stage){
         this.stage=stage;
        // System.out.println("prjid:"+prjid);
   }



   public void setProjId(String prjid){
         this.prjid=prjid;
        // System.out.println("prjid:"+prjid);
   }



public void collectPlanChapter(){

Connection con=null;
     try{
            //the fields are retrieved in parts so taht the resultset
            //doesnot get affected while referencing null values in joining the tables
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        chapterId.clear();
        chapterName.clear();
        
        Statement statement = con.createStatement();

        String planSQL =  " select chapter_id,chapter_no from chapter where " +
                            " stage='"+stage+"' and added_date='"+addedDate+"' " +
                                " and proj_id='"+prjid+"' " ;

        //System.out.println("projNameSQL-Client Name:"+planSQL);

        //the below block is for retreiving the information for the projectId passed to display in the Add Projects interface once the project has been addded

              ResultSet rsGetPlan=statement.executeQuery(planSQL);
                while(rsGetPlan.next()){
                    tempResult=rsGetPlan.getString("chapter_id");
                    chapterId.add(tempResult);
                    tempResult=rsGetPlan.getString("chapter_no");
                    chapterName.add(tempResult);
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
