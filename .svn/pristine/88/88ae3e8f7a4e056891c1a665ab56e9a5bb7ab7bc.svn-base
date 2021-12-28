/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.hr;

import pathfinder.functions.projects.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.io.*;


/**
 *
 * @author PERUMAL
 */
public class ShiftTimeReport implements Serializable {
    
     DBconnection connection = new DBconnection();
//    Connection con = null;
//    java.sql.Statement stmt;
    private String sql_select = "";
    private String sql_select1 = "";
    private String fromDate = "";

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDesigCode() {
        return desigCode;
    }

    public void setDesigCode(String desigCode) {
        this.desigCode = desigCode;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public List getEmpIdList() {
        return empIdList;
    }

    public void setEmpIdList(List empIdList) {
        this.empIdList = empIdList;
    }

    public List getEmpNameList() {
        return empNameList;
    }

    public void setEmpNameList(List empNameList) {
        this.empNameList = empNameList;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    private String toDate = "";
    private String deptCode = "";
    private String desigCode = "";
    private String empId = "";
      
    private String loopEmpId="";
    private String resultData = "";
    private String resultData1 = "";
    private String resultData2 = "";
    private String resultData3 = "";
    private String resultData4 = "";
    private String resultData5 = "";

    private String resultDataT = "";
    private String resultDataL = "";
    private String resultDataTAL = "";

    private List empIdList = new ArrayList();
    private List empNameList = new ArrayList();
    private List deptNameList = new ArrayList();

    public List getDeptCodeList() {
        return deptCodeList;
    }

    public void setDeptCodeList(List deptCodeList) {
        this.deptCodeList = deptCodeList;
    }

    public List getDeptNameList() {
        return deptNameList;
    }

    public void setDeptNameList(List deptNameList) {
        this.deptNameList = deptNameList;
    }

    public List getDesigCodeList() {
        return desigCodeList;
    }

    public void setDesigCodeList(List desigCodeList) {
        this.desigCodeList = desigCodeList;
    }

    public List getDesignationList() {
        return designationList;
    }

    public void setDesignationList(List designationList) {
        this.designationList = designationList;
    }
    private List deptCodeList = new ArrayList();
    private List designationList = new ArrayList();
    private List desigCodeList = new ArrayList();
    private List dateList = new ArrayList();
    private List breakTimeList = new ArrayList();
    private List breakTimeList1 = new ArrayList();
    private List shiftEnteredList = new ArrayList();
    private List shiftStartTimeList = new ArrayList();
    private List shiftEndTimeList = new ArrayList();
    
    public List getDateList() {
        return dateList;
    }

    public void setDateList(List dateList) {
        this.dateList = dateList;
    }

    public List getBreakTimeList() {
        return breakTimeList;
    }
    public List getBreakTimeList1() {
        return breakTimeList1;
    }

    public void setBreakList(List breakTimeList) {
        this.breakTimeList = breakTimeList;
    }

    public List getShiftEndTimeList() {
        return shiftEndTimeList;
    }

    public void setShiftEndTimeList(List shiftEndTimeList) {
        this.shiftEndTimeList = shiftEndTimeList;
    }

    public List getShiftStartTimeList() {
        return shiftStartTimeList;
    }

    public void setShiftStartTimeList(List shiftStartTimeList) {
        this.shiftStartTimeList = shiftStartTimeList;
    }
   
    
    
    
    public ShiftTimeReport() {
   
    }
   
    public void collectShiftData() throws SQLException{
       try{
         Connection con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            
           sql_select = "select u.emp_id,u.emp_name,dpt.department,dsg.designation,"
                   + "date_format(sh.shift_start_time,'%d/%m/%y'),"
                   + "date_format(sh.shift_start_time,'%d/%m/%y %H:%i:%s %p'),"
                   + "date_format(sh.shift_end_time,'%d/%m/%y %H:%i:%s %p')";
           String sql_from = " from user u,department dpt,designation dsg,shift sh ";
           String sql_where = " where u.dept_code=dpt.dept_code and u.desig_Code=dsg.desig_code and u.emp_id=sh.emp_id ";
            if(fromDate.equals("")){
                sql_where += " and date(sh.shift_start_time)=date(current_timestamp)";                       
            }
            else{
                  if(!fromDate.equals("")&&!toDate.equals(""))
                sql_where += " and date(sh.shift_start_time) between '"+fromDate+"' and '"+toDate+"' ";                         
            }
            
            if(!deptCode.equals("")){
                sql_where += " and u.dept_code='"+deptCode+"'";
            }
            
              if(!desigCode.equals("")){
                sql_where += " and u.desig_code='"+desigCode+"'";
            }
          

            if(!empId.equals("")){
                sql_where += " and u.emp_id='"+empId+"'";
            }
            
            sql_select = sql_select+sql_from+sql_where+" order by u.emp_id,sh.shift_start_time ";
            
            //System.out.println("sql_select:"+sql_select);
            
    ResultSet rsGetEmpDetails = stmt.executeQuery(sql_select);
    while(rsGetEmpDetails.next()){
        resultData4="";
        resultData5="";
       empIdList.add(rsGetEmpDetails.getString("u.emp_id"));
       empNameList.add(rsGetEmpDetails.getString("u.emp_name"));
       String emp_id = rsGetEmpDetails.getString("u.emp_id");
       
         resultData = rsGetEmpDetails.getString("dpt.department");
            if(rsGetEmpDetails.wasNull()){
                resultData="";
            }
           deptNameList.add(resultData);
           
          resultData = rsGetEmpDetails.getString("dsg.designation");
            if(rsGetEmpDetails.wasNull()){
                resultData="";
            }
           designationList.add(resultData);
       
       resultData = rsGetEmpDetails.getString("date_format(sh.shift_start_time,'%d/%m/%y')");
       String dateTimebrk = rsGetEmpDetails.getString("date_format(sh.shift_start_time,'%d/%m/%y')");
       // date format
       try
    {
      //create SimpleDateFormat object with source string date format
      SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yy");
      //parse the string into Date object
      Date date = sdfSource.parse(dateTimebrk);
      //create SimpleDateFormat object with desired date format
      SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
      //parse the date into another format
      dateTimebrk = sdfDestination.format(date);
    }
    catch(ParseException pe)
    {
      System.out.println("Parse Exception : " + pe);
    }
       // end of date format
            if(rsGetEmpDetails.wasNull()){
                resultData="";
            }
           dateList.add(resultData);
           
           
           
          resultData = rsGetEmpDetails.getString("date_format(sh.shift_start_time,'%d/%m/%y %H:%i:%s %p')");
            if(rsGetEmpDetails.wasNull()){
                resultData="";
            }
           shiftStartTimeList.add(resultData);
       
             resultData = rsGetEmpDetails.getString("date_format(sh.shift_end_time,'%d/%m/%y %H:%i:%s %p')");
            if(rsGetEmpDetails.wasNull()){
                resultData="";
            }
           shiftEndTimeList.add(resultData);
           //sql_select1 = "select group_concat(begin_time, end_time, '<b>Diff:', TIMEDIFF(Time(end_time), Time(begin_time)),'</b><br/>') from activity where emp_id='"+emp_id+"' and DATE(begin_time)='"+dateTimebrk+"' and activity_code in (170,75)";
           sql_select1 = "select begin_time, end_time, TIMEDIFF(Time(end_time), Time(begin_time)) from activity where emp_id='"+emp_id+"' and DATE(begin_time)='"+dateTimebrk+"' and activity_code in (170,75)";
           //System.out.println("sql_select1"+sql_select1);
           ResultSet rsGetEmpDetails1 = stmt1.executeQuery(sql_select1);
          
           while(rsGetEmpDetails1.next()){
          if(rsGetEmpDetails1.getString(1)==null){
      resultData1 = "N/A";
  }
      else{
      resultData1 = rsGetEmpDetails1.getString(1);
      try{
          SimpleDateFormat sdfSource1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
          SimpleDateFormat sdfDestination1 = new SimpleDateFormat("HH:mm:ss");
	  Date date1 = sdfSource1.parse(resultData1);
           
	  resultData1 = sdfDestination1.format(date1);
          //System.out.println("resultData1"+resultData1);
   }
   catch(ParseException pe)
    {
      System.out.println("Parse Exception : " + pe);
    }
      }
 if(rsGetEmpDetails1.getString(2)==null){
      resultData2 = "N/A";
  }
      else{
 resultData2 = rsGetEmpDetails1.getString(2);
  try{
          SimpleDateFormat sdfSource2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
          SimpleDateFormat sdfDestination2 = new SimpleDateFormat("HH:mm:ss");
	  Date date2 = sdfSource2.parse(resultData2);
          resultData2 = sdfDestination2.format(date2);
          //System.out.println("resultData1"+resultData1);
   }
   catch(ParseException pe)
    {
      System.out.println("Parse Exception : " + pe);
    }
      }
if(rsGetEmpDetails1.getString(3)==null){
      resultData3 = "N/A";
}
      else{
      resultData3 = rsGetEmpDetails1.getString(3);
      }
  resultData4 +="<tr><td>"+resultData1+" to "+ resultData2 + "</td><td>Taken:"+ resultData3 + "</td></tr>";
  resultData5 += resultData1+"-";

  //System.out.println("shiftStartTimeList:"+resultData4);
 
         //resultData4 = "<table>"+resultData4+"</table>";

         //breakTimeList.add("<table border=\"1\" id=\"details\" class=\"display\">"+resultData4+"</table>");
           
           //resultData4="";
    }
     breakTimeList.add("<table border=\"1\" id=\"details\" class=\"display\">"+resultData4+"</table>");
     breakTimeList1.add(resultData5);
           }
 
    //System.out.println("shiftEndTimeList:"+shiftEndTimeList.size());
    //System.out.println("breakTimeList:"+breakTimeList.size());

   
    /*
    for(int idx=0;idx<empIdList.size();idx++){
        loopEmpId = empIdList.get(idx).toString();
        ResultSet rsGetDesigDept = stmt.executeQuery("select dpt.department,dsg.designation"
                + "from department dpt,designation dsg,user u where"
                + "u.emp_id='"+loopEmpId+"' and u.dept_code=dpt.dept_code and u.desig_code=dsg.desig_code");
        while(rsGetDesigDept.next()){
            resultData = rsGetDesigDept.getString("dpt.department");
            if(rsGetDesigDept.wasNull()){
                resultData="";
            }
           deptNameList.add(resultData);
           
          resultData = rsGetDesigDept.getString("dsg.designation");
            if(rsGetDesigDept.wasNull()){
                resultData="";
            }
           designationList.add(resultData);
        }        
    }
    */
    rsGetEmpDetails.close();
    stmt1.close();
    stmt.close();
    con.close();
    }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
    
    
    
    
}
