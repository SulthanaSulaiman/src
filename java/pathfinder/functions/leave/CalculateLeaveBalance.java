/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import java.sql.*;
import java.io.Serializable;
import connection.*;

/**
 *
 * @author  ramesh
 */
public class CalculateLeaveBalance implements Serializable {

    private String leaveLimit="";
    private int remainingDays = 0;
    private String calculatedLimit="";
    private String dateOfConfrim = "";
    private String balance_value = "";
    private int totalDays = 0;
    private int chkLeapYear = 0;
    private String currentYear="";
    private double balance = 0.00;
    private String balance_val = "";

    public void setLeaveLimit(String leaveLimit){
        this.leaveLimit = leaveLimit;
    }

    public void setDateOfConfirm(String dateOfConfrim){
        this.dateOfConfrim = dateOfConfrim;
    }

     public void setRemainingDays(int remainingDays){
        this.remainingDays = remainingDays;
     }

     public void setCurrentYear(String currentYear){
        this.currentYear = currentYear;
     }

     public String getCalcBalance(){
         return balance_val;
     }

     public void calculateBal(){

          String firstDate = "01-01";
          Connection con = null;
           Statement st = null;
           int days_NonCalc = 0;
           int days_Calculative =0;
           String days_WorkedDiff="";

         firstDate = currentYear+"-"+firstDate;

         chkLeapYear = Integer.parseInt(currentYear)%4;
         if(chkLeapYear==0){
          totalDays=366;
         }
         else{
           totalDays=365;
         }

         try{
         DBconnection dbcon = new DBconnection();
         con = dbcon.getSampleProperty();

         st = con.createStatement();

         //System.out.println("select DATEDIFF('"+dateOfConfrim+"','"+firstDate+"') ");

         ResultSet rsGetDiffDays = st.executeQuery("select DATEDIFF('"+dateOfConfrim+"','"+firstDate+"') ");
         while(rsGetDiffDays.next()){

                days_WorkedDiff = rsGetDiffDays.getString(1);
                if(rsGetDiffDays.wasNull()){

                  days_WorkedDiff="0";
                }
         }
         rsGetDiffDays.close();

         days_NonCalc = Integer.parseInt(days_WorkedDiff);
         days_Calculative = totalDays-days_NonCalc;

         if(remainingDays>0){
             days_Calculative = totalDays-remainingDays;
         }

         }catch(SQLException sqle){
            System.out.println(sqle);
         }finally{
             try{
                 st.close();
               con.close();
             }catch(SQLException sqle){
                System.out.println(sqle);
             }

         }
        // int DOC_diff =
/*  System.out.println("days_Calculative:"+days_Calculative);
  System.out.println("totalDays:"+totalDays);*/

          String daysToAccount = String.valueOf(days_Calculative);
          String totalDays_Year = String.valueOf(totalDays);


               //System.out.println("daysToAccount : "+daysToAccount);
               //System.out.println("totalDays_Year : "+totalDays_Year);

    //  double balance_calc =    (Double.parseDouble(daysToAccount)/Double.parseDouble(totalDays_Year))*Double.parseDouble(leaveLimit);
try{
          balance =    (Double.parseDouble(daysToAccount)/Double.parseDouble(totalDays_Year))*Double.parseDouble(leaveLimit);

           //System.out.println("balance : "+balance);


          balance_value = String.valueOf(balance);

          try{
          if((balance_value.indexOf(".")+3)==-1){

           balance_value = balance_value.substring(balance_value.indexOf(".")+1);
          }else{
          balance_value = balance_value.substring(balance_value.indexOf(".")+1,balance_value.indexOf(".")+3);
          }
          }catch(Exception e){
              balance_value="0";
          }

//To round off the leave balance to a integer or value with .5 decimal place

          if((int)balance>=1 || (Integer.parseInt(balance_value)>40 && (int)balance<1  )){

              //System.out.println("balance_value .5 : "+balance_value);
              //System.out.println("balance int : "+(int)balance);
//To check for near .5 value
              if(Integer.parseInt(balance_value)>40){
                  //System.out.println("balance_value .5 : "+balance_value);
                  balance = (int)balance+0.5;
                  balance_val=String.valueOf(balance);
                  //System.out.println("balance_val * 41 : "+balance_val);
              }
//To check for near .5 value
              if(Integer.parseInt(balance_value)<=90){
                  //System.out.println("balance_value .5 : "+balance_value);
                  balance = (int)balance+0.5;
                  balance_val=String.valueOf(balance);
                  //System.out.println("balance_val * 90 : "+balance_val);
              }
//To check for near integer
              if(Integer.parseInt(balance_value)<=40){
                  //System.out.println("balance_value .0 : "+balance_value);
                  balance_val=String.valueOf((int)balance);
                  //System.out.println("balance_val * 40 : "+balance_val);
              }
//To check for near integer
              if(Integer.parseInt(balance_value)>90){

                  //System.out.println("balance_value .0 : "+balance_value);
                  balance_val=String.valueOf((int)balance);
                  //System.out.println("balance_val * 91 : "+balance_val);
              }
              //System.out.println("balance_val : "+balance_val);
          }else {
              balance_val="0";
          }
          //System.out.println("balance : "+balance);

      /* String chkVal = String.valueOf(balance_calc);


       balance = Double.parseDouble(chkVal);*/

       //  //System.out.println("balance-divide:"+balance);

     //  balance = balance*Double.parseDouble(leaveLimit);

    //   //System.out.println("balance:"+balance);
}catch(Exception e){
    System.out.println(e);
}


     }


}
