/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions;

import connection.DBconnection;
import java.io.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author ramyamaims
 */
public class AddWeekendHoliday implements Serializable {

    DBconnection connection = new DBconnection();
    Connection con = null;
    Statement stmt = null;
    Statement stmt1 = null;
    ResultSet rs = null;

    int intRes = 0;
    int intResCnt = 0;

    int setCurMonVal = 0;
    int setCurYearVal = 0;
    int chkLeapYear = 0;
    int totalDays  = 0;
    int dayOfWeek = 0;

    String year_start_date = "";
    String year_next_date = "";
    String holiday_detail = "";

    String sql_select = "";
    String sql_from = "";
    String sql_where = "";
    String sql_group = "";
    String sql_order = "";

    public void getValue(){

        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            setCurYearVal = 0;

            Calendar calendar = Calendar.getInstance();
            setCurYearVal = calendar.get(Calendar.YEAR);
            setCurMonVal = calendar.get(Calendar.MONTH)+1;

           /* if(setCurMonVal<10){*/
               // year_start_date = setCurYearVal+"-0"+setCurMonVal+"-01";
                 year_start_date = setCurYearVal+"-01"+"-01";
         /*   }else {
                year_start_date = setCurYearVal+"-"+setCurMonVal+"-01";
            }*/

            chkLeapYear = setCurYearVal%4;
            totalDays = 0;

            if(chkLeapYear==0){
                totalDays=366;
            }else{
                totalDays=365;
            }

            dayOfWeek = 0;

            intResCnt = 0;

            //System.out.println("year_start_date : "+year_start_date);

            for(int loop=0;loop<totalDays;loop++){
                intRes = 0;
                rs = stmt.executeQuery("select DATE_ADD('"+year_start_date+"', INTERVAL "+loop+" DAY), DAYOFWEEK(DATE_ADD('"+year_start_date+"', INTERVAL "+loop+" DAY)) ");
                //System.out.println("select DATE_ADD('"+year_start_date+"', INTERVAL "+loop+" DAY), DAYOFWEEK(DATE_ADD('"+year_start_date+"', INTERVAL "+loop+" DAY)) ");
                while(rs.next()){

                    year_next_date = rs.getString(1);
                    dayOfWeek = rs.getInt(2);

                   if(dayOfWeek==1 || dayOfWeek==7){
                       if(dayOfWeek==1){
                           holiday_detail = "Sunday";
                       }else{
                           holiday_detail = "Saturday";
                       }
                       intRes = stmt1.executeUpdate("insert into holiday_detail(holiday_date,holiday_detail,holidaytype_id) " +
                               "values('"+year_next_date+"','"+holiday_detail+"','1')");

                       if(intRes==1){
                           intResCnt++;
                       }
                   }
                }
            }

//            if(intResCnt>0){
//                System.out.println("Success");
//            }else{
//                System.out.println("Try again");
//            }


        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}
    }

    public int getResultSetValue(){
        return intResCnt;
    }

}
