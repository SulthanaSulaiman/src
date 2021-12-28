/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects.chapters;
import connection.DBconnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Gandhimathidevic
 */
public class AutoScheduleForAspenDAO {
    public void fetchdaysForstages(List Stages, List chapDetails,String projId){
    DBconnection dbcon = new DBconnection();
    Connection con =null;
    Statement stmt = null;
    ResultSet rst = null;
    List chapterDetails = new ArrayList();
    chapterDetails = chapDetails;
    con = dbcon.getSampleProperty();
    Map m1 = new HashMap();
    List weekend = new ArrayList();
    int noofdays = 0;
    try{
    stmt = con.createStatement();
    for(int i=0; i<Stages.size();i++){
    String stgname = Stages.get(i).toString();
    String sql = "select stage, days, weekend_inculding from apsenautoscheduleaftrshipment where stage='"+stgname+"'";
    rst = stmt.executeQuery(sql);
    while (rst.next()){
        m1.put(rst.getString(1),rst.getString(2));
        weekend.add(rst.getString(3));
    }
    }
    }
    catch(SQLException sqle){
    sqle.printStackTrace();
    }
    AutoScheduleForAspenDAO d1= new AutoScheduleForAspenDAO();
  //d1.insertChapteplan(m1,chapDetails,projId,weekend);
 }
public void insertChapteplan(Map stageAndDays,List chapDetails,String projId,List weekend,String proofPage){
    List milestoneCod = new ArrayList();
    DBconnection dbcon = new DBconnection();
    DBconnection dbcon1 = new DBconnection();
    Connection con = null;
    Connection con1 = null;
    PreparedStatement statement = null;
    PreparedStatement statement2 = null;
    int addChapterPlan = 0;
    int addedChapter = 0;
    int wkd = 0;
    ChapterPlanDetVO rqstVO = null;
    String chapterNotInserted = "";
    String workbookInserted = "";
    String workbookInserted1="";
    AutoScheduleForAspenVO autoscheduleforAspen1 = new AutoScheduleForAspenVO();
    Set mapSet = (Set) stageAndDays.entrySet();
    Iterator mapIter = mapSet.iterator();
    while (mapIter.hasNext()){
        Map.Entry mapEntry = (Map.Entry) mapIter.next();
        String keyValue = mapEntry.getKey().toString();
        String pairValue = mapEntry.getValue().toString();
        try {
           if (!projId.equals("")){
            String chapterId = "";
            String saveQry = "";
            String remark = "";
            String saveQry1 = "";
            con = dbcon.getSampleProperty();
            con1 = dbcon1.getSampleProperty();
            saveQry = "INSERT INTO chapter(chapter_no,mss_count,chapter_process,proj_id,stage,due_date,chapter_added_by,added_date,receipt_date)"
        + " VALUES (?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?)";
            saveQry1 = " INSERT into chapter_plan(chapter_id,milestone_id,end_date,auto_fill_flag,planned_Date) "
                    + " VALUES(?,?,?,?,CURRENT_TIMESTAMP)";
statement = con.prepareStatement(saveQry);
statement.setString(1, chapDetails.get(0).toString());
System.out.println("Stagetesttt"+keyValue);
if(keyValue.toString().equals("EDPF")){
statement.setString(2, proofPage);
}
else{
    statement.setString(2, chapDetails.get(1).toString());
}
statement.setString(3, "1");
statement.setString(4, projId);
statement.setString(5, keyValue);
SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
Date today = new Date();
Calendar c = Calendar.getInstance();
c.setTime(today);
//System.out.println("testtt"+pairValue);
c.add(Calendar.DATE, Integer.parseInt(pairValue));
if (Integer.parseInt(weekend.get(wkd).toString())==1){
int dayOfWeek1 = c.get(Calendar.DAY_OF_WEEK);
if (dayOfWeek1 == Calendar.SATURDAY) {
        c.add(Calendar.DATE, 2);
      //  System.out.println("Day:SATURDAY1");
      }
      else if (dayOfWeek1 == Calendar.SUNDAY) {
        c.add(Calendar.DATE, 1);
      //  System.out.println("Day:SUNDAY1");
      }
 }
Date currentDatePlusOne = c.getTime();
c.setTime(currentDatePlusOne);
String date = DATE_FORMAT.format(currentDatePlusOne);
statement.setString(6, date);
statement.setString(7, chapDetails.get(3).toString());
statement.setString(8, DATE_FORMAT.format(today));
statement.executeUpdate();
WorkbookPlan w1 = new WorkbookPlan();
chapterId = w1.maxChapId();
String planId = null;
milestoneCod.clear();
AutoScheduleForAspenDAO d1 = new AutoScheduleForAspenDAO();
milestoneCod = d1.fecthmilestoneAndActivity(keyValue);
for (int i = 0; i < milestoneCod.size(); i++) {
statement2 = con1.prepareStatement(saveQry1);
statement2.setString(1, chapterId);
statement2.setString(2, milestoneCod.get(i).toString());
String temp="";
System.out.println("code"+milestoneCod);
if ((milestoneCod.get(i).toString().equals("74"))||milestoneCod.get(i).toString().equals("21")){
temp = date;
statement2.setString(3, temp);
}
    
 else{
 statement2.setNull(3, Types.DATE);
 }
statement2.setString(4, "0");
statement2.executeUpdate();

//                    System.out.println("Updated Successfully" + milestoneCod.get(i).toString()+MilestoneDue.get(i).toString());
//
//                     Generate Batch Ticket for created Plan
//                    batchTicketReport.setChapterId(chapterId);
//                    batchTicketReport.generateReportFields();
//                    workbookInserted="yes";
//                    returnvalue=1;
//                }
//            }
// else{
//  returnvalue=0;
// }
//
}
  }}
 catch (SQLException sqle) {
  System.out.println("SQLException in creating Plan Creation:" + sqle);
  sqle.printStackTrace();
//            //return chapterNotInserted;
    }
//    catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Exception in creating Plan Creation:" + e);
//            //return chapterNotInserted;
//        }
        finally {
            if (statement != null) {
               try {
                    statement.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                            }
}
            if (statement2 != null) {
               try {
                    statement2.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                            }
}
//            Close connection
if (con != null) {
try {
con.close();
} catch (SQLException se) {
System.out.println("Exception while closing connection:se.getMessage()");
}
}
if (con1 != null) {
try {
con1.close();
} catch (SQLException se) {
System.out.println("Exception while closing connection:se.getMessage()");
}
}
}
        wkd++;
}
}
public void fetchStageForSchedule(AutoScheduleForAspenVO autoscheduleforAspen)
{
    List wkd = new ArrayList();
    String projId = autoscheduleforAspen.getProjId();
    String clientdiv_code = autoscheduleforAspen.getDivisionCode();
    String Proofpage = autoscheduleforAspen.getPageNu();
    List chapDetails = new ArrayList();
    Map m1 = new HashMap();
    chapDetails = autoscheduleforAspen.getValues();
    DBconnection dbcon = new DBconnection();
    Connection con =null;
    Statement stmt = null;
    ResultSet rst = null;
    con = dbcon.getSampleProperty();
    String sql1 = "select stage,days,weekend_inculding from apsenautoscheduleaftrshipment where division_code='"+clientdiv_code+"' and ForFP='0'" ;
    System.out.println("string"+sql1);
    try{
    stmt = con.createStatement();
    rst = stmt.executeQuery(sql1);
    while (rst.next()){
        wkd.add(rst.getString(3));
        m1.put(rst.getString(1),rst.getString(2));
    }
    }
    catch(SQLException sqle){
    sqle.printStackTrace();
    }
    AutoScheduleForAspenDAO d1 = new AutoScheduleForAspenDAO();
    //d1.fetchdaysForstages(stages,chapDetails,projId);
    System.out.println(m1 +", " + chapDetails + ", " + ", "+projId +", "+wkd);
    d1.insertChapteplan(m1,chapDetails,projId,wkd,Proofpage);
    }
public void fetchStageForSchedulePlanner(AutoScheduleForAspenVO autoscheduleforAspen)
{
    List wkd = new ArrayList();
    String projId = autoscheduleforAspen.getProjId();
    String planner = autoscheduleforAspen.getPlanner();
    String Proofpager = autoscheduleforAspen.getPageNu();
    String clientdiv_code = autoscheduleforAspen.getDivisionCode();
    List chapDetails = new ArrayList();
    Map m1 = new HashMap();
    chapDetails = autoscheduleforAspen.getValues();
    DBconnection dbcon = new DBconnection();
    Connection con =null;
    Statement stmt = null;
    ResultSet rst = null;
    con = dbcon.getSampleProperty();
    String sql1 = "select stage,days,weekend_inculding,division_code from apsenautoscheduleaftrshipment where planner='"+planner+"' and ForFP='1'";
    try{
    stmt = con.createStatement();
    rst = stmt.executeQuery(sql1);
    while (rst.next()){
        System.out.println("string"+sql1);
        if (clientdiv_code.equals(rst.getString(4))){
        wkd.add(rst.getString(3));
        m1.put(rst.getString(1),rst.getString(2));
        }
    }
    }
    catch(SQLException sqle){
    sqle.printStackTrace();
    }
    AutoScheduleForAspenDAO d1 = new AutoScheduleForAspenDAO();
    //d1.fetchdaysForstages(stages,chapDetails,projId);
    System.out.println(m1 +", " + chapDetails + ", " + ", "+projId +", "+wkd);
    d1.insertChapteplan(m1,chapDetails,projId,wkd,Proofpager);
    }
public List fecthmilestoneAndActivity(String stage){
DBconnection db = new DBconnection();
Connection con = null;
Statement stmt = null;
ResultSet rst = null;
List milest = new ArrayList();
con = db.getSampleProperty();
milest.clear();
try {
stmt = con.createStatement();
rst = stmt.executeQuery("select DISTINCT(milestone_act_code) from milestone_act_map where stage_code='"+stage+"'");
while (rst.next()){
    milest.add(rst.getString(1));
}
AutoScheduleForAspenDAO d1= new AutoScheduleForAspenDAO();
}
catch(SQLException sqle){
}
finally {
 if (con != null) {
try {
con.close();
} catch (SQLException se) {
System.out.println("Exception while closing connection:se.getMessage()");
}
}
}
return milest;
}
}

