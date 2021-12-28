/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.depttarget;


import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.math.*;
/**
 *
 * @author ramesh
 * calss to calcualte the target for each department
 * this class input will be generated in 3 classes TargetOrder,TargetDept and TargetAttributes
 * 
 */
public class CalculateTarget implements Serializable {   
   
    private String targetOrder="";
    
    private String query_MonthYear = "";

    private List deptCode = new ArrayList();
    private List deptName = new ArrayList();
    private List targetValue = new ArrayList();
    private List dispDeptName = new ArrayList();

    private List dispDeptCode=new ArrayList();
    private List tgtStageCode=new ArrayList();
    private List tgtActivityCode=new ArrayList();
    private List tgtDeptCode=new ArrayList();
    private List tgtFactor=new ArrayList();
    private List tgtMultiple=new ArrayList();
    private List tgtDept=new ArrayList();
    private List tgtClient=new ArrayList();
    private List tgtRemClient=new ArrayList();
    private List tgtDateList = new ArrayList();
    private List displayDateList = new ArrayList();
    private List targetList =  new ArrayList();
    private List targetPercent =  new ArrayList();
    private String targetEndDate="";
    private String targetStDate="";
    private List tgtHolidayList = new ArrayList();
    private List totalTargetValue = new ArrayList();
    private List totalTargetPercent = new ArrayList();
    private String defaultEndDate="";

    private List detailTargetValue = new ArrayList();
    private List detailTargetPercent = new ArrayList();

    private String year="";
    private String month="";


    public CalculateTarget() {
        
    }

    public void setYear(String year){
        this.year=year;
    }

   public void setMonth(String month){
        this.month=month;
       // System.out.println("getProjId:"+prjid);
   }

    public void setTargetOrder(String targetOrder){
        this.targetOrder=targetOrder;
    }

    public void setTargetStartDate(String targetStDate){
        this.targetStDate=targetStDate;
    }

    public void setTargetEndDate(String targetEndDate){
        this.targetEndDate=targetEndDate;
    }

    public void setTargetDefaultEndDate(String defaultEndDate){
    this.defaultEndDate=defaultEndDate;
    }

    public void setHoliday(List tgtHolidayList){
        this.tgtHolidayList=tgtHolidayList;
    }

    public void setDispDeptName(List dispDeptName){
        this.dispDeptName=dispDeptName;
    }

    public void setDispDeptCode(List dispDeptCode){
        this.dispDeptCode=dispDeptCode;//main dept list 
    }

   public void setDateList(List tgtDateList){
        this.tgtDateList=tgtDateList;
    }

    public void setTargetStageCode(List tgtStageCode){
        this.tgtStageCode=tgtStageCode;
    }

    public void setTargetActivityCode(List tgtActivityCode){
        this.tgtActivityCode=tgtActivityCode;
    }

    public void setTargetDeptCode(List tgtDeptCode){
        this.tgtDeptCode=tgtDeptCode;//dept code for each stage and activity
    }

    public void setTargetFactor(List tgtFactor){
        this.tgtFactor=tgtFactor;
    }

    public void setTargetDeptMultiple(List tgtMultiple){
        this.tgtMultiple=tgtMultiple;
    }

    public void setTargetDept(List tgtDept){
        this.tgtDept=tgtDept;//dept code for a particular stage or activity
    }

    public void setTargetClient(List tgtClient){
        this.tgtClient=tgtClient;
    }

    public void setRemoveTargetClient(List tgtRemClient){
        this.tgtRemClient=tgtRemClient;
    }

    public void setTarget(List targetList){
        this.targetList=targetList;
    }
 /*  public void setMonth(String month){
        this.month=month;
       // System.out.println("getProjId:"+prjid);
   }*/
//addPropValue

    public List getDetailTgtValue(){
       return detailTargetValue;
    }

    public List getDetailTgtPercent(){
       return detailTargetPercent;
    }

    public List getTargetDeptcode(){
        return deptCode;
    }

   public List getTargetDeptName(){
        return deptName;
    }

    public List getTargetValue(){
        return targetValue;
    }

    public List getTargetPercent(){
        return targetPercent;
    }

        public List getTotalTargetValue(){
        return totalTargetValue;
    }

    public List getTotalTargetPercent(){
        return totalTargetPercent;
    }

    public String roundValue(String target)
    {
        Double productivity=Double.parseDouble(target);

       	String str_productivity = String.valueOf(productivity);

	if(str_productivity.equals("Infinity"))
	{

	str_productivity="0.0";

	}


	System.out.println("productivity:"+productivity);

	System.out.println("str_productivity-before split:"+str_productivity);

	int decimalIndex = str_productivity.indexOf(".");


	str_productivity = str_productivity.substring(decimalIndex+1,decimalIndex+2);

	System.out.println("str_productivity-after split:"+str_productivity);

	int chkRoundOff = Integer.parseInt(str_productivity);


	BigDecimal roundedValue = null;

	try{

	roundedValue = new BigDecimal(productivity);

	}catch(NumberFormatException nme)
	{

		roundedValue = new BigDecimal(0.0);

	}

	if(chkRoundOff<5)
	{
		roundedValue=roundedValue.setScale(0,BigDecimal.ROUND_DOWN);
	}
	else if(chkRoundOff>=5)
	{
		roundedValue=roundedValue.setScale(0,BigDecimal.ROUND_UP);
	}


	productivity = roundedValue.doubleValue();


	String productivity_Achieved = String.valueOf(productivity);

	productivity_Achieved = productivity_Achieved.substring(0,productivity_Achieved.indexOf("."));

        return productivity_Achieved;
    }

public void productivity(){

Connection con=null;
String targetResult="";
try{

        //System.out.println("getProjId in method:"+prjid);
   DBconnection dbcon = new DBconnection();
   con = dbcon.getSampleProperty();
       
   Statement statement = con.createStatement(); 
    
    /* System.out.println("tgtDateList:"+tgtDateList);
     System.out.println("dispDeptCode:"+dispDeptCode);
     System.out.println("tgtDeptCode:"+tgtDeptCode);
     System.out.println("tgtStageCode:"+tgtStageCode);
System.out.println("tgtActivityCode:"+tgtActivityCode);
System.out.println("tgtFactor:"+tgtFactor);
System.out.println("tgtMultiple:"+tgtMultiple);
System.out.println("tgtClient:"+tgtClient);
System.out.println("tgtRemClient:"+tgtRemClient);
System.out.println("tgtDept:"+tgtDept);*/
     /*System.out.println();
     System.out.println();*/

int selected_month=Integer.parseInt(month);
if(selected_month<10){
month = "0"+String.valueOf(selected_month);
}
else{
month = String.valueOf(selected_month);
}

query_MonthYear = year+"-"+month;


int datesize=tgtDateList.size();
int dispDeptsize=dispDeptCode.size();
int rptDeptsize=tgtDeptCode.size();

String loopDate = "";
String loopDept = "";
String loopRptDept = "";
String loopRptStg = "";
String loopRptActivity = "";
String loopRptFactor = "";
String loopRptMultiple = "";
String loopRptDepts = "";
String loopRptClient = "";
String loopRptRemoveClient = "";
int loopTarget = 0;
String targetSql = "";
double calculateTarget=0;
double calculatePercent=0;
int firstIndex=0;
int lastIndex=0;
int differenceIndex=0;
double queryResult = 0.0;

//the main for loop will open below for each date

//query_MonthYear


    System.out.println("targetValue:"+targetValue);
    System.out.println("targetPercent:"+targetPercent);

    System.out.println("targetValue:"+targetValue.size());
    System.out.println("targetPercent:"+targetPercent.size());

//System.out.println("addContact:"+addContact);
    statement.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in CalculateTarget:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in CalculateTarget:"+e);
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




public String roundDecimal(String calculateTarget){
String chkcalculateTarget=String.valueOf(calculateTarget);
 int decimalIndex=chkcalculateTarget.indexOf(".");

 int checkDecimal = Integer.parseInt(chkcalculateTarget.substring(decimalIndex+1,decimalIndex+2));
if(checkDecimal>0){
   chkcalculateTarget=chkcalculateTarget.substring(0,chkcalculateTarget.indexOf(".")+2);

}
else{
  chkcalculateTarget = chkcalculateTarget.substring(0,chkcalculateTarget.indexOf("."));
}

 return chkcalculateTarget;
}






public void totalProductivity(){


Connection con=null;
String targetResult="";
int totalDays = tgtDateList.size();
int holidayCount=tgtHolidayList.size();

if(!defaultEndDate.equals("")){
   totalDays=totalDays-1;
   targetEndDate=defaultEndDate;
}

int finalCount =  totalDays-holidayCount;
System.out.println("totalDays:"+totalDays);
System.out.println("holidayCount:"+holidayCount);
System.out.println("finalCount:"+finalCount);
try{

String loopDate = "";
String loopDept = "";
String loopRptDept = "";
String loopRptStg = "";
String loopRptActivity = "";
String loopRptFactor = "";
String loopRptMultiple = "";
String loopRptDepts = "";
String loopRptClient = "";
String loopRptRemoveClient = "";
int loopTarget = 0;
String targetSql = "";
double calculateTarget=0;
double calculatePercent=0;
int firstIndex=0;
int lastIndex=0;
int differenceIndex=0;
double queryResult = 0.0;
int dispDeptsize=dispDeptCode.size();
int rptDeptsize=tgtDeptCode.size();
        //System.out.println("getProjId in method:"+prjid);
   DBconnection dbcon = new DBconnection();
   con = dbcon.getSampleProperty();

   Statement statement = con.createStatement();
//finalCount
 for(int dptIdx=0;dptIdx<dispDeptsize;dptIdx++){

      int rptIdx=0;
        loopDept=dispDeptCode.get(dptIdx).toString();
        calculateTarget=0;
        calculatePercent=0;
        firstIndex=tgtDeptCode.indexOf(loopDept);
        lastIndex=tgtDeptCode.lastIndexOf(loopDept);
        differenceIndex=lastIndex-firstIndex;

          differenceIndex++;
          lastIndex=firstIndex+differenceIndex;//to set the loop working
          loopTarget = 0;

           loopTarget = Integer.parseInt(targetList.get(dptIdx).toString());
           loopTarget=loopTarget*finalCount;
            for(rptIdx=firstIndex;rptIdx<lastIndex;rptIdx++){

            loopRptDept = "";
            loopRptStg = "";
            loopRptActivity = "";
            loopRptFactor = "";
            loopRptMultiple = "";
            loopRptDepts = "";
            loopRptClient = "";
            loopRptRemoveClient = "";


              loopRptDept=tgtDeptCode.get(rptIdx).toString();
              loopRptStg=tgtStageCode.get(rptIdx).toString();
              loopRptActivity=tgtActivityCode.get(rptIdx).toString();
              loopRptFactor=tgtFactor.get(rptIdx).toString();
              loopRptMultiple=tgtMultiple.get(rptIdx).toString();
              loopRptDepts=tgtDept.get(rptIdx).toString();
              loopRptClient=tgtClient.get(rptIdx).toString();
              loopRptRemoveClient=tgtRemClient.get(rptIdx).toString();

              targetSql = "";

               if(loopRptActivity.equals("")){
                   targetSql=" select sum(ch.proof_page) from chapter ch where" +
                         "  ch.stage in ("+loopRptStg+") and date(ch.ship_date) between '"+targetStDate+"' and '"+targetEndDate+"' " ;
               }
              else if(!loopRptActivity.equals("")){
                   targetSql=" select time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(timediff(at.end_time,at.begin_time)))),'%H'), " +
                         " u.dept_code,date_format(at.begin_time,'%Y-%m-%d') " +
                         " and at.activity_code in ("+loopRptActivity+") and ch.stage in ("+loopRptStg+") " ;
              }

              if(loopRptMultiple.equals("0")){

                 targetSql += "  and ch.proj_id in (select proj_id from projects where dept_code='"+loopDept+"' ";
              }
              else{
                   targetSql += " and ch.proj_id in (select proj_id from projects where dept_code in ("+loopRptDepts+")   ";
              }


              if(!loopRptClient.equals("")){
                targetSql += "  and client_name in ("+loopRptClient+") ";
              }

              if(!loopRptRemoveClient.equals("")){
                targetSql += "  and client_name not in ("+loopRptRemoveClient+") ";
              }
              targetSql +=")";

              System.out.println("targetSql:"+targetSql);

              ResultSet rsTarget = statement.executeQuery(targetSql);
                    while(rsTarget.next()){
                    targetResult=rsTarget.getString(1);
                    if(rsTarget.wasNull()){
                      targetResult="0";
                    }

                    System.out.println("targetResult:"+targetResult);
                    }

              rsTarget.close();

                    queryResult=Integer.parseInt(targetResult);
                    if(!loopRptFactor.equals("")&&loopRptActivity.equals("")){//since the Factor is multiplied in the activity query no need to add it again here otherwise results will be doubled
                      queryResult=queryResult*Double.parseDouble(loopRptFactor);
                    }
               calculateTarget+=queryResult;


            }//close of for(rptIdx

 calculatePercent=(calculateTarget/loopTarget)*100;
 targetResult=roundValue(String.valueOf(calculatePercent));

 System.out.println("calculateTarget:"+calculateTarget);
 System.out.println("calculatePercent:"+calculatePercent);

 totalTargetValue.add(calculateTarget);
 totalTargetPercent.add(targetResult+"%");
 
 }//close of for dptIdx
   statement.close();
   
        }catch(SQLException sqle){
            System.out.println("SQLException in CalculateTarget:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in CalculateTarget:"+e);
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
