/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.depttarget;


import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramesh
 * class for DeptTarget which builds the dates needed 
 * for the Target report to be generated
 * also the holidays in the month which includes the saturdays and sundays too
 */
public class TargetDates implements Serializable {
   
    private String year="";
    private String month="";
    private String targetOrder="";
    private String query_MonthYear = "";
    private String CurrentMonth_Yr="";
    private String Current_day="";
    private int lastDateOfMonth=0;
    private String firstDate = "";
    private String last_Date = "";
    private String Query_Date = "";
    private String shipDate = "";
    private List DateList = new ArrayList();
    private List monthDates = new ArrayList();
    private List holidayDates = new ArrayList();
    private String CurLastDay="";
    private String curLastDate="";
    public TargetDates() {
        
    }

    public void setYear(String year){
        this.year=year;
    }

   public void setMonth(String month){
        this.month=month;
       // System.out.println("getProjId:"+prjid);
   }
//addPropValue

    public String getTargetOrder(){
        return targetOrder;
    }

   public String getStartDate(){
       return firstDate;
   }

   public String getEndDate(){
       return last_Date;
   }

      public String getDefaultEndDate(){
       return curLastDate;
   }

   public List getDateList(){
       return DateList;
   }

  public List getDisplayDateList(){
       return monthDates;
   }

    public List getHolidayList(){
       return holidayDates;
   }

public void loadTargetDates(){

Connection con=null;
        try{

int selected_month=Integer.parseInt(month);
if(selected_month<10){
month = "0"+String.valueOf(selected_month);
}
else{
month = String.valueOf(selected_month);
}
query_MonthYear = year+"-"+month;
        targetOrder="";

        //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
     Statement statement = con.createStatement();

     ResultSet rsCurrentMonth =  statement.executeQuery("select date_format(current_timestamp(),'%Y-%m') as yearmonth, day(current_timestamp()) as curdate,day(current_timestamp)-1 as curLastDay");
     while(rsCurrentMonth.next()){
         CurrentMonth_Yr=rsCurrentMonth.getString("yearmonth");
         Current_day=rsCurrentMonth.getString("curdate");
         CurLastDay=rsCurrentMonth.getString("curLastDay");

     }

rsCurrentMonth.close();


     String chkValue = "";
//" select pp.value from project_properties_value_master ppvm,project_properties pp where ppvm.value=pp.value and pp.proj_id='"+prjid+"' and pp.property_id='"+propId+"'   ");
     //System.out.println("select target_order from dept_target where date_format(target_date,'%Y-%m')<='"+query_MonthYear+"' order by target_order desc limit 1");
     ResultSet rsTargetOrder =  statement.executeQuery("select target_order from dept_target where date_format(target_date,'%Y-%m')<='"+query_MonthYear+"' order by target_order desc limit 1");
     while(rsTargetOrder.next()){
       targetOrder=rsTargetOrder.getString(1);
       if(rsTargetOrder.wasNull()){
         targetOrder="";
        }
       }
     rsTargetOrder.close();


firstDate = year+"-"+String.valueOf(month)+"-"+"01";
last_Date = "";

//out.println("firstDate:"+firstDate);
//loop to check if the current month and year are equal to the Target report month and year
//based on which the lastdate in the report could be found
//out.println("select DAY(LAST_DAY('"+firstDate+"'))");
if(!CurrentMonth_Yr.equals(query_MonthYear)){
    ResultSet rsgetLastDate = statement.executeQuery("select DAY(LAST_DAY('"+firstDate+"'))");
    while(rsgetLastDate.next()){
        lastDateOfMonth = rsgetLastDate.getInt(1);
    }
    rsgetLastDate.close();

    last_Date = String.valueOf(lastDateOfMonth);

    if(lastDateOfMonth<10){
        last_Date = "0"+last_Date;
    }
    last_Date = year+"-"+month+"-"+last_Date;
    curLastDate="";//set this as empty variable. This is check while calcualting the total Target
}
else{
    lastDateOfMonth=Integer.parseInt(Current_day);
    if(lastDateOfMonth<10){
        Current_day = "0"+Current_day;
    }
    else{
        Current_day = Current_day;
    }

         if(Current_day.equals("01"))
         {
              CurLastDay="1";
         }


    int defaultLastDay=Integer.parseInt(CurLastDay);

    if(defaultLastDay<10){
        CurLastDay = "0"+String.valueOf(defaultLastDay);
    }
    else{
        CurLastDay = String.valueOf(defaultLastDay);
    }

    //System.out.println("CurLastDay:"+CurLastDay);

    last_Date = year+"-"+month+"-"+Current_day;
    curLastDate = year+"-"+month+"-"+CurLastDay;




}


for(int idx=1;idx<lastDateOfMonth+1;idx++){

if(idx<10){
Query_Date =  "0"+String.valueOf(idx);
}
else{
Query_Date =  String.valueOf(idx);
}

monthDates.add(idx);
shipDate=query_MonthYear+ "-" + Query_Date;
DateList.add(shipDate);
}

//System.out.println("DateList:"+DateList);
//DateList
String holidaySql=" ";
if(!CurrentMonth_Yr.equals(query_MonthYear)){
holidaySql=" select holiday_date from holiday_detail where date_format(holiday_date,'%Y-%m')='"+query_MonthYear+"' and working_day='0' ";
}
else{
holidaySql=" select holiday_date from holiday_detail where date_format(holiday_date,'%Y-%m')='"+query_MonthYear+"' " +
        " and date(holiday_date)<date(current_timestamp())  and working_day='0' ";

}
ResultSet rsHoliday = statement.executeQuery(holidaySql);
while(rsHoliday.next()){
    holidayDates.add(rsHoliday.getString("holiday_date"));
}
rsHoliday.close();


//System.out.println("addContact:"+addContact);
statement.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Proj ID:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Proj ID:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            //System.out.println(e);
            }
            catch(NullPointerException npe){
            //System.out.println(e);
            }catch(Exception npe){
            //System.out.println(e);
            }


        }     
               
               
}

}
