/*
 * TimeDifference.java
 *
 * Created on June 30, 2009, 12:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.util;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Weeks;

/**
 *
 * @author ramyams
 */
public class TimeDifference {
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    private int div1 = 0;
    private int div2 = 0;
    private int div3 = 0;
    private int div4 = 0;    
    private int timespent = 0;
    
    private String hours = "";
    private String minute = "";
    private String total_time = "";
    private String setTime_diff = "";
    //private List dis_time_diff = new ArrayList();
    
    /** Creates a new instance of TimeDifference */
    public TimeDifference() {
    }
    
    public void setTime(String setTime){
        this.setTime_diff = setTime;
    }
    
    public void getValue(){
        
        div1 = 0;
        div2 = 0;
        div3 = 0;
        div4 = 0;
        try{
        timespent = Integer.parseInt(setTime_diff);
       // System.out.print("setTime_diff  in timeDiff bean : "+setTime_diff);
        div1 = timespent/3600;
        div2 = timespent%3600;
        div3 = div2/60;
        div4 = div2%60;
        hours = String.valueOf(div1);
        minute = String.valueOf(div3);
        total_time = hours+":"+minute;
       // System.out.print("total_time in timeDiff bean : "+total_time);
        }
        catch(Exception e)
        {
            System.out.println("Time Diff Exception is::::::"+e);
        }
    }
    
    public String getTime(){
        return total_time;
    }

    public int DaysWithoutWeekEnds(String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String timeStamp = formatter.format(Calendar.getInstance().getTime());
        int weeks = 0;
        int startWeekDay = 0;
        int endWeekDay = 0;
        int days = 0;
        int buffer = 0;
        int buffer1 = 0;
        try {
            java.util.Date formatedStartDate = new java.util.Date();
            java.util.Date formatedEndDate = new java.util.Date();

            // If any input date is empty then fill it with current date
            startDate = startDate.equals("") ? timeStamp : startDate;
            endDate = endDate.equals("") ? timeStamp : endDate;

            formatedStartDate = formatter.parse(startDate);
            formatedEndDate = formatter.parse(endDate);

            DateTime startDateTime = new DateTime(formatedStartDate);
            DateTime endDateTime = new DateTime(formatedEndDate);
//System.out.println("startDateTime"+formatedStartDate);
//System.out.println("endDateTime"+formatedEndDate);
            // Count number of weeks between two dates
            weeks = Weeks.weeksBetween(startDateTime, endDateTime).getWeeks();

            startWeekDay = startDateTime.getDayOfWeek();
            endWeekDay = endDateTime.getDayOfWeek();

            // Count number of days between two dates
            days = Days.daysBetween(startDateTime, endDateTime).getDays();
            
           
            // If Start date is Saturday or Sunday then consider ignore
            
            days = startWeekDay == 6 ? days -= 2 : days;
            days = startWeekDay == 7 ? days -= 1 : days;
        
            // If End date is Saturday or Sunday then consider ignore
            days = endWeekDay == 6 ? days -= 1 : days;
            days = endWeekDay == 7 ? days -= 2 : days;

            // If Start Weekday is less than End WeekDay then one more weekend have to be eliminated
            buffer = startWeekDay>endWeekDay ? buffer = 2 : 0;
            //System.out.println("startDateTime"+startDateTime+"endDateTime"+endDateTime+"days"+buffer);
            buffer1 = startWeekDay<endWeekDay ? buffer = 2 : 0;

        } catch (ParseException e) {
            System.out.println("ParseException > TimeDifference - DaysWithoutWeekEnds(): " + e);
	} catch (Exception e) {
            System.out.println("Exception > TimeDifference - DaysWithoutWeekEnds(): " + e);
        }
        // return number of days excluding weekends
        // DaysCountWithoutWeekend = (Total number of dates) - (Number of rounded Weeks * Number of Weekend) - (Number of days which is rounded off from Week)
        if(days<0){
        return((days-(weeks*2)-buffer1)+1);
        }
 else
        return(days-(weeks*2)-buffer);
    }
}
