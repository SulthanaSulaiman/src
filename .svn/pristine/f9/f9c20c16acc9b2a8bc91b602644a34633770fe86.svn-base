/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects.chapters;

/**
 *
 * @author Gandhimathidevic
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class AddingBusinessdays {
 private String addingDays="";
 private String NumberOfDays="";
 private int NumberOfWorkingDays;
 private int ConvertNumberOfWorkingDays;
 private int ConvertNumberOfWorkingDYS;
  private String setcheckdays="";
  private String toDate="";
   public void setaddDays(String addingDays,String NumberOfDays){
       this.addingDays=addingDays;
       this.NumberOfDays=NumberOfDays;
       //System.out.println("workFlowId:"+workFlowId);
   }
public void checkDays(String setcheckdays){
//System.out.println("checkDays"+setcheckdays);
    this.setcheckdays=setcheckdays;
  }
  public String addDays() {

    Calendar c = Calendar.getInstance();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date myDate = df.parse(addingDays.trim());
      c.setTime(myDate);
      int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

      if (dayOfWeek == Calendar.FRIDAY) {
        NumberOfDays =NumberOfDays+3;
       // System.out.println("Day:FRIDAY");

      }
      else if (dayOfWeek == Calendar.SATURDAY) {
        NumberOfDays =NumberOfDays+2;
      //  System.out.println("Day:SATURDAY");

      }
      else if (dayOfWeek == Calendar.SUNDAY) {
        NumberOfDays =NumberOfDays+1;
      //  System.out.println("Day:SUNDAY");

      }

      c.add(Calendar.DATE, Integer.parseInt(NumberOfDays));
    } catch (ParseException e) {
      e.printStackTrace();
    }

   String toDate = df.format(c.getTime());
   // System.out.println("toDate"+toDate);
    return toDate;
    }
  public String getcheckDays(){
    Calendar c = Calendar.getInstance();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {

      Date myDate = df.parse(setcheckdays.trim());
      c.setTime(myDate);
      int dayOfWeek1 = c.get(Calendar.DAY_OF_WEEK);

      if (dayOfWeek1 == Calendar.FRIDAY) {
        c.add(Calendar.DATE, 3);
       // System.out.println("Day:FRIDAY1");

      }
      else if (dayOfWeek1 == Calendar.SATURDAY) {
        c.add(Calendar.DATE, 2);
      //  System.out.println("Day:SATURDAY1");

      }
      else if (dayOfWeek1 == Calendar.SUNDAY) {
        c.add(Calendar.DATE, 1);
      //  System.out.println("Day:SUNDAY1");

      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    String toDate1 = df.format(c.getTime());
   // System.out.println("toDate1"+toDate1);
    return toDate1;
  }
}