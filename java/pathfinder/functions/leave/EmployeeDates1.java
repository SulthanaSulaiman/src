/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import java.io.Serializable;
import java.sql.*;
import connection.*;

/**
 *
 * @author Administrator
 */
public class EmployeeDates1 {


   private String empId = "";
   private String doc = "";
   private String doj = "";
   //private String empId = "";
   private String currentYear="";
   private String currentMonth="";
   private String currentDay="";

   private String currentDate="";

   private String DOJ_year="";
   private String DOC_year="";

   private String days_Worked="";

    public void setEmpId(String empId){
        this.empId=empId;
        
    }

    public void setDateOfConfirm(String doc){
        this.doc=doc;
    }

    public void setDateOfJoin(String doj){
        this.doj=doj;
    }

    public String getDateOfConfirm(){
        return doc;
    }

    public String getDateOfJoin(){
        return doj;
    }

    public String getCurrentYear(){
        return currentYear;
    }

    public String getCurrentMonth(){
        return currentMonth;
    }

    public String getCurrentDay(){
        return currentDay;
    }

    public String getCurrentDate(){
        return currentDate;
    }

  public String getDOJYear(){
        return DOJ_year;
    }

    public String getDOCYear(){
        return DOC_year;
    }



    public void getDates(){
        //method to get the employee doc and doj from db
       
     Connection con=null;
     Statement st;
     ResultSet rsEmpDates;
        try{

        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        st=con.createStatement();
        //System.out.println("select doj,year(doj),confirmation_date,year(confirmation_date),datediff(confirmation_date,doj) from user where emp_id='"+empId+"' ");
        rsEmpDates = st.executeQuery("select doj,year(doj),confirmation_date,year(confirmation_date),datediff(confirmation_date,doj) from user where emp_id='"+empId+"' ");

        while(rsEmpDates.next()){

           doj = rsEmpDates.getString("doj");
           if(rsEmpDates.wasNull()){
            doj="";
           }

           doc =  rsEmpDates.getString("confirmation_date");
           if(rsEmpDates.wasNull()){
            doc="";
           }

           DOJ_year = rsEmpDates.getString("year(doj)");
           if(rsEmpDates.wasNull()){
            DOJ_year="";
           }

           DOC_year =  rsEmpDates.getString("year(confirmation_date)");
           if(rsEmpDates.wasNull()){
            DOC_year="";
           }

 //System.out.println("empId in empdates1.getDates.rsset:"+empId);
        }


        //the below resultset captures the year,month,day and date - each seperately of the current day

        ResultSet rsDateAtributes = st.executeQuery("select year(current_timestamp()),month(current_timestamp()),day(current_timestamp()),date(current_timestamp())");

        while(rsDateAtributes.next()){
            currentYear=rsDateAtributes.getString("year(current_timestamp())");
            currentMonth=rsDateAtributes.getString("month(current_timestamp())");
            currentDay=rsDateAtributes.getString("day(current_timestamp())");

            currentDate=rsDateAtributes.getString("date(current_timestamp())");


       }


    //System.out.println("doj:"+doj);
        //System.out.println("doc:"+currentYear);
        rsDateAtributes.close();
        st.close();

        }catch(SQLException sqle){

        }catch(Exception e){

        }finally{
            try{
                con.close();
            }catch(SQLException sqle){
                System.out.println("SQL Exception in EmployeeDates:"+sqle);

            }
        }



    }


}
