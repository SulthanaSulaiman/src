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
 * class to calculate the target for each department
 * this class input will be generated in 3 classes TargetOrder,TargetDept and TargetAttributes
 *
 */
public class CollectTargetValues implements Serializable {


    private List grpStageCode = new ArrayList();
    private List grpDeptList = new ArrayList();
    private List grpDeptCode = new ArrayList();
    private List grpDeptClnt = new ArrayList();
    private List grpDeptClntRem = new ArrayList();
    private List grpActivityCode = new ArrayList();
    private List datesList = new ArrayList();
    private List reportDeptCode = new ArrayList();
    private List getTgtDptStg = new ArrayList();
    private List getTgtDptActivity = new ArrayList();
    private List getTgtDptFactor = new ArrayList();
    private List getTgt = new ArrayList();

    private String targetEndDate="";
    private String targetStDate="";
    private String getTgtOrder="";

    private List dataList = new ArrayList();
    private List unitsTotal = new ArrayList();
    private List unitsPercentage = new ArrayList();

    private List totalDataList = new ArrayList();
    private List totalUnitsData = new ArrayList();
    private List totalUnitsPercentage = new ArrayList();
    private List tgtHolidayList = new ArrayList();
      private String defaultEndDate="";

public void setGrpStage(List grpStageCode){
    this.grpStageCode=grpStageCode;
    }

    public void setGrpDeptList(List grpDeptList){
    this.grpDeptList=grpDeptList;
    }

    public void setGrpDeptCode(List grpDeptCode){
    this.grpDeptCode=grpDeptCode;
    }

    public void setGrpClientInclude(List grpDeptClnt){
    this.grpDeptClnt=grpDeptClnt;
    }

    public void setGrpClientNotInclude(List grpDeptClntRem){
          this.grpDeptClntRem=grpDeptClntRem;
    }

    public void setGrpActivity(List grpActivityCode){
          this.grpActivityCode=grpActivityCode;
    }

    public void setGrpDateList(List datesList){
          this.datesList=datesList;
    }

    public void setHoliday(List tgtHolidayList){
        this.tgtHolidayList=tgtHolidayList;
    }

    public void setReportDeptCode(List reportDeptCode){
          this.reportDeptCode=reportDeptCode;
    }

    public void setTarget(List getTgt){
          this.getTgt=getTgt;
    }

    public void setStartDate(String targetStDate){
        this.targetStDate=targetStDate;
    }

    public void setEndDate(String targetEndDate){
        this.targetEndDate=targetEndDate;
    }

    public void setTargetDefaultEndDate(String defaultEndDate){
    this.defaultEndDate=defaultEndDate;
    }

    public void setTargetOrder(String getTgtOrder){
        this.getTgtOrder=getTgtOrder;
    }

    public void setTargetDptStg(List getTgtDptStg){
        this.getTgtDptStg=getTgtDptStg;
    }

   public void setTargetDptActivity(List getTgtDptActivity){
        this.getTgtDptActivity=getTgtDptActivity;
    }

    public void setTargetDptFactor(List getTgtDptFactor){
        this.getTgtDptFactor=getTgtDptFactor;
    }

    public List getDetailData(){
        return dataList;
    }

    public List getUnitsData(){
       return unitsTotal;
    }

    public List getUnitsPercentage(){
        return unitsPercentage;
    }

    public List getTotalDetailData(){
        return totalDataList;
    }

    public List getTotalUnitsData(){
       return totalUnitsData;
    }

    public List getTotalUnitsPercentage(){
        return totalUnitsPercentage;
    }




    //to start from collecting other data into this class to run the loops
    public CollectTargetValues() {

    }


    public String roundValue(String target)
    {
        Double productivity=Double.parseDouble(target);

       	String str_productivity = String.valueOf(productivity);

	if(str_productivity.equals("Infinity"))
	{
	str_productivity="0.0";
	}

        if(str_productivity.equals("NaN"))
	{
	str_productivity="0.0";
	}


	//System.out.println("productivity:"+productivity);

	//System.out.println("str_productivity-before split:"+str_productivity);

	int decimalIndex = str_productivity.indexOf(".");

	str_productivity = str_productivity.substring(decimalIndex+1,decimalIndex+2);

	//System.out.println("str_productivity-after split:"+str_productivity);

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


    public void calculateProductivity(){

        int firstIndex=0;
        int lastIndex=0;
        int differenceIndex=0;
        String chkValue="";
        List tempDeptList=new ArrayList();
        List indexList = new ArrayList();

        String grpStage="";
        String groupStage="";
        String grpDept="";
        StringTokenizer stk1 = null;
        String deptList="";
        String grpClient="";
        String grpRemclient="";
        String grpActivity="";

        String targetSql="";
        String totalTargetSql="";
        String queryDept="";
        String groupSql="";
        String totalGroupSql="";
List collectDateList = new ArrayList();
List collectDeptList = new ArrayList();
List collectResultDataList = new ArrayList();

List collectIndexList = new ArrayList();
List collectDataList = new ArrayList();

List collectTotalIndexList = new ArrayList();
List collectTotalDataList = new ArrayList();

double resultValue=0;
double totalResultValue=0;

 int grpListSize=grpStageCode.size();
  for(int grpIdx=0;grpIdx<grpListSize;grpIdx++){

  //form the query for each stage collected for the respective order

      tempDeptList.clear();
      targetSql = "";
      queryDept ="";
         grpStage=grpStageCode.get(grpIdx).toString();
         groupStage=grpStage;
         grpDept=grpDeptCode.get(grpIdx).toString();
         deptList=grpDeptList.get(grpIdx).toString();
         grpClient=grpDeptClnt.get(grpIdx).toString();
         grpRemclient=grpDeptClntRem.get(grpIdx).toString();
         grpActivity=grpActivityCode.get(grpIdx).toString();
//         System.out.println("grpStage:"+grpStage);
//         System.out.println("grpDept:"+grpDept);

         if(!deptList.equals("")){
                 stk1 = new StringTokenizer(deptList,",");
                 deptList="";
                while(stk1.hasMoreTokens()) {
                    deptList = stk1.nextToken();
                    tempDeptList.add(deptList);
                    //queryDept
                   queryDept += "'"+deptList+"'";
                    if(stk1.hasMoreElements()){
                        queryDept += ",";
                    }
             }
         }

       if(!grpStage.equals("")){
        stk1 = new StringTokenizer(grpStage,",");
                 grpStage="";
                while(stk1.hasMoreTokens()) {
                    grpStage += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        grpStage += ",";
                    }
             }
         }//close of if(!grpStage.equals(""))

                   if(!defaultEndDate.equals("")){

                    }
                   else{
                       defaultEndDate=targetEndDate;
                   }

         if(grpActivity.equals("")){

                   if(grpDept.equals("")){
                    targetSql=" select sum(ch.proof_page),pr.dept_code,date_format(ch.ship_date,'%Y-%m-%d')" +
                           " from chapter ch,projects pr where pr.proj_id=ch.proj_id and " +
                         "  ch.stage="+grpStage+" and date(ch.ship_date) between '"+targetStDate+"' and '"+targetEndDate+"'" +
                         " and pr.dept_code in ("+queryDept+") " ;
                       groupSql = " group by date(ch.ship_date),pr.dept_code order by date(ch.ship_date) " ;
                   }else{
                       //dept_code is removed as only the sum(all department data) for a particualr date is needed
                       targetSql=" select sum(ch.proof_page),date_format(ch.ship_date,'%Y-%m-%d')" +
                           " from chapter ch,projects pr where pr.proj_id=ch.proj_id and " +
                         "  ch.stage="+grpStage+" and date(ch.ship_date) between '"+targetStDate+"' and '"+targetEndDate+"'" +
                         " and pr.dept_code in ("+queryDept+") " ;
                       groupSql = " group by date(ch.ship_date) order by date(ch.ship_date) " ;
                   }
                  /********* the below String forms the total Query ***/


                   if(grpDept.equals("")){
                    totalTargetSql=" select sum(ch.proof_page),pr.dept_code " +
                           " from chapter ch,projects pr where pr.proj_id=ch.proj_id and " +
                         "  ch.stage="+grpStage+" and date(ch.ship_date) between '"+targetStDate+"' and '"+defaultEndDate+"'" +
                         " and pr.dept_code in ("+queryDept+") " ;
                     totalGroupSql = " group by pr.dept_code " ;//group by is applicable only for default case
                   }else{
                       //dept_code is removed as only the sum(all department data) for a particualr date is needed
                        totalTargetSql=" select sum(ch.proof_page) " +
                           " from chapter ch,projects pr where pr.proj_id=ch.proj_id and " +
                         "  ch.stage="+grpStage+" and date(ch.ship_date) between '"+targetStDate+"' and '"+defaultEndDate+"'" +
                         " and pr.dept_code in ("+queryDept+") " ;
                        totalGroupSql="";
                   }

               }
              else if(!grpActivity.equals("")){

                  //collect the activity hours in this query and multiply it with calculation factor in the data loading part

                   if(grpDept.equals("")){//default case
                       targetSql= " select time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(timediff(at.end_time,at.begin_time)))),'%H'), " +
                         " pr.dept_code,date_format(at.begin_time,'%Y-%m-%d')," +
                         " (time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(timediff(at.end_time,at.begin_time)))),'%i')*.0166) " +
                         " from activity at,chapter ch,projects pr,user u " +
                         " where at.chapter_id=ch.chapter_id and at.end_time is not null and ch.proj_id=pr.proj_id " +
                         "  and date(at.begin_time)  between '"+targetStDate+"' and '"+targetEndDate+"'  and " +
                         " at.activity_code in ("+grpActivity+") and ch.stage in ("+grpStage+") and u.emp_id=at.emp_id " +
                         " and pr.dept_code in ("+queryDept+") " ;
                   groupSql = " group by date(at.begin_time),pr.dept_code order by date(at.begin_time) ";
                   }
                   else{
                       targetSql= " select time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(timediff(at.end_time,at.begin_time)))),'%H'), " +
                         " date_format(at.begin_time,'%Y-%m-%d')," +
                         " (time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(timediff(at.end_time,at.begin_time)))),'%H')*.0166) " +
                         " from activity at,chapter ch,projects pr,user u " +
                         " where at.chapter_id=ch.chapter_id and at.end_time is not null and ch.proj_id=pr.proj_id " +
                         "  and date(at.begin_time)  between '"+targetStDate+"' and '"+targetEndDate+"'  and " +
                         " at.activity_code in ("+grpActivity+") and ch.stage in ("+grpStage+") and u.emp_id=at.emp_id " +
                         " and pr.dept_code in ("+queryDept+") " ;
                       groupSql = " group by date(at.begin_time) order by date(at.begin_time) ";
                   }
                   /********* the below String forms the total Query ***/


                   if(grpDept.equals("")){//default case
                         totalTargetSql=" select time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(timediff(at.end_time,at.begin_time)))),'%H'), " +
                         " pr.dept_code " +
                         " from activity at,chapter ch,projects pr,user u " +
                         " where at.chapter_id=ch.chapter_id and at.end_time is not null and ch.proj_id=pr.proj_id " +
                         "  and date(at.begin_time)  between '"+targetStDate+"' and '"+defaultEndDate+"'  and " +
                         " at.activity_code in ("+grpActivity+") and ch.stage in ("+grpStage+") and u.emp_id=at.emp_id " +
                         " and pr.dept_code in ("+queryDept+") " ;
                        totalGroupSql = " group by pr.dept_code ";
                   }
                  else{
                      totalTargetSql=" select time_format(SEC_TO_TIME(SUM(TIME_TO_SEC(timediff(at.end_time,at.begin_time)))),'%H') " +
                         " from activity at,chapter ch,projects pr,user u " +
                         " where at.chapter_id=ch.chapter_id and at.end_time is not null and ch.proj_id=pr.proj_id " +
                         "  and date(at.begin_time)  between '"+targetStDate+"' and '"+defaultEndDate+"'  and " +
                         " at.activity_code in ("+grpActivity+") and ch.stage in ("+grpStage+") and u.emp_id=at.emp_id " +
                         " and pr.dept_code in ("+queryDept+") " ;
                      totalGroupSql="";
                    }
              }

            if(!grpClient.equals("")){
                targetSql += "  and pr.client_name in ("+grpClient+") ";
                totalTargetSql+= "  and pr.client_name in ("+grpClient+") ";
              }

              if(!grpRemclient.equals("")){
                targetSql += "  and pr.client_name not in ("+grpRemclient+") ";
                totalTargetSql += "  and pr.client_name not in ("+grpRemclient+") ";
              }

targetSql=targetSql+groupSql;
totalTargetSql=totalTargetSql+totalGroupSql;

//System.out.println("targetSql:"+targetSql);
//System.out.println("totalTargetSql:"+totalTargetSql);
//with the above query formation of target report completes

Connection con=null;
try{
  /*  if(val.startsWith("0")){
int testIndex=val.indexOf("0");
val=val.substring(testIndex+1);

System.out.println("testIndex:"+testIndex);

}*/
   DBconnection dbcon = new DBconnection();
   con = dbcon.getSampleProperty();
   Statement statement = con.createStatement();
   String resultData1="";
   String resultData2="";
   String resultData3="";
   String resultData4="";
int testIndex=0;
   ResultSet rsProductivity = statement.executeQuery(targetSql);
   while(rsProductivity.next()){

           resultData1=rsProductivity.getString(1);
           if(rsProductivity.wasNull()){
               resultData1="0";
           }
           resultData2=rsProductivity.getString(2);

     if(resultData1.startsWith("0")){
        testIndex=resultData1.indexOf("0");
        if(resultData1.length()>1){
            resultData1=resultData1.substring(testIndex+1);
        }
        }

if(!grpActivity.equals("")){
     resultData4=rsProductivity.getString(4);
     resultData4=roundDecimal(resultData4);
     resultData1=resultData1+"."+resultData4.substring(resultData4.indexOf(".")+1);
}



       if(grpDept.equals("")){
          resultData3=rsProductivity.getString(3);
       collectIndexList.add(resultData3+"_"+resultData2+"_"+groupStage+"_");
       collectDataList.add(resultData3+"_"+resultData2+"_"+groupStage+"_"+resultData1);
       }
       else{
       collectIndexList.add(resultData2+"_"+grpDept+"_"+groupStage+"_");
       collectDataList.add(resultData2+"_"+grpDept+"_"+groupStage+"_"+resultData1);
       }

   }

   rsProductivity = statement.executeQuery(totalTargetSql);
   while(rsProductivity.next()){

      resultData1=rsProductivity.getString(1);
           if(rsProductivity.wasNull()){
               resultData1="0";
           }


     if(resultData1.startsWith("0")){
        testIndex=resultData1.indexOf("0");
        if(resultData1.length()>1){
            resultData1=resultData1.substring(testIndex+1);
        }
     }

       if(grpDept.equals("")){
       resultData2=rsProductivity.getString(2);
       collectTotalIndexList.add(resultData2+"_"+groupStage+"_");
       collectTotalDataList.add(resultData2+"_"+groupStage+"_"+resultData1);
       }
       else{
        collectTotalIndexList.add(grpDept+"_"+groupStage+"_");
       collectTotalDataList.add(grpDept+"_"+groupStage+"_"+resultData1);
       }
   }


//System.out.println("addContact:"+addContact);
  /* System.out.println("grpStage:"+grpStage);
   System.out.println("collectResultDataList:"+collectResultDataList);

         System.out.println("collectIndexList:"+collectIndexList);
      System.out.println("collectDataList:"+collectDataList);*/
      rsProductivity.close();
      statement.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in CollectTargetValues:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in CollectTargetValues:"+e);
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
}//close of grpIdx - withthe above the query's should have got executed

int dateListSize=datesList.size();
int index1=dateListSize;
int reportDeptSize=reportDeptCode.size();
int index2=reportDeptSize;
int index3=0;

        String dateLoopVble="";
        String deptLoopVble="";

    String[] stgArr=null;
    String[] activityArr=null;
    String[] factorArr=null;


        String stgLoopVble="";
        String factorLoopVble="";
        int dataIndex=0;
        String loopData="";
        int loopTarget = 0;

        int dataIdentifier=0;
        List targetDeptStage = new ArrayList();
        List targetDeptActivity = new ArrayList();
        List targetDeptFactor = new ArrayList();

        List dayTotalProductivity = new ArrayList();
        double calculateTarget=0.0;
        double calculatePercent=0.0;

        double calculateTotalTarget=0.0;
        double calculateTotalPercent=0.0;
//System.out.println("reportDeptCode:"+reportDeptCode);
String dataModel="";//to build the element so that it could be searched in the indexlist and the corresponding data could be derived from the datalist
String totalDataModel="";//to build the element so that it could be searched in the indexlist and the corresponding data could be derived from the datalist
       for(int idx=0;idx<index1;idx++){
          dateLoopVble=datesList.get(idx).toString();
          dataModel=dateLoopVble+"_";
//with the above loop opened all the dept values should be uploaded

              for(int deptIdx=0;deptIdx<index2;deptIdx++){
                calculateTarget=0.0;
                calculateTotalTarget=0.0;
                dataModel=dateLoopVble+"_";
                deptLoopVble="";
                deptLoopVble=reportDeptCode.get(deptIdx).toString();
                loopTarget=Integer.parseInt(getTgt.get(deptIdx).toString());
                //collect the stages needed for target report of the depratment in the loop
               targetDeptStage.clear();
               targetDeptActivity.clear();
               targetDeptFactor.clear();

               //System.out.println("deptLoopVble:"+deptLoopVble);

               stgArr=(String[]) getTgtDptStg.get(deptIdx);
               for(int i=0;i<stgArr.length;i++){
                   targetDeptStage.add(stgArr[i]);
               }

               activityArr=(String[]) getTgtDptActivity.get(deptIdx);
               for(int i=0;i<activityArr.length;i++){
                   targetDeptActivity.add(activityArr[i]);
               }

               factorArr=(String[]) getTgtDptFactor.get(deptIdx);
               for(int i=0;i<factorArr.length;i++){
                   targetDeptFactor.add(factorArr[i]);
               }

                index3=targetDeptStage.size();

                // int dept

   dayTotalProductivity.clear();//to collect the data for each day for the dept in the loop and then add it to the final dataList
               for(int stgIdx=0;stgIdx<index3;stgIdx++){
                   resultValue=0;
                   totalResultValue=0;
                      stgLoopVble=targetDeptStage.get(stgIdx).toString();
                      factorLoopVble=targetDeptFactor.get(stgIdx).toString();

                      dataModel=dateLoopVble+"_"+deptLoopVble+"_"+stgLoopVble+"_";

                     // System.out.println("dataModel:"+dataModel);

                      dataIndex=collectIndexList.indexOf(dataModel);
                      if(dataIndex!=-1){
                            loopData=collectDataList.get(dataIndex).toString();
                            dataIdentifier=loopData.lastIndexOf("_");
                            dataIdentifier++;
                            loopData=loopData.substring(dataIdentifier);
                      }
                      else{
                          loopData="0";
                      }


                       dataList.add(loopData);


                      if(factorLoopVble.equals("")){
                       resultValue = Double.parseDouble(loopData);
                      }
                      else{
                          resultValue = Double.parseDouble(loopData);
                          //System.out.println("resultValue:"+resultValue);
                          resultValue = resultValue*Double.parseDouble(factorLoopVble);


                      }


                       calculateTarget=calculateTarget+resultValue;
                       // System.out.println("calculateTarget-after:"+calculateTarget);

               }//close of stage loop


                dataList.add(roundDecimal(String.valueOf(calculateTarget)));
                calculatePercent=(calculateTarget/loopTarget)*100;
                dataList.add(roundValue(String.valueOf(calculatePercent)));
                unitsTotal.add(roundDecimal(String.valueOf(calculateTarget)));
                unitsPercentage.add(roundValue(String.valueOf(calculatePercent)));

              }//close of deptIdx

       }//close of datetIdx

/*******Load the Total Values*********/
int totalDays = datesList.size();
if(!defaultEndDate.equals("")){
   totalDays=totalDays-1;
}
int holidayCount=tgtHolidayList.size();
int finalCount =  totalDays-holidayCount;
     // System.out.println("collectTotalIndexList:"+collectTotalIndexList);
     // System.out.println("collectTotalDataList:"+collectTotalDataList);
              for(int deptIdx=0;deptIdx<index2;deptIdx++){
                calculateTarget=0.0;
                calculateTotalTarget=0.0;
                dataModel=dateLoopVble+"_";
                deptLoopVble="";
                deptLoopVble=reportDeptCode.get(deptIdx).toString();
                loopTarget = 0;
                loopTarget = Integer.parseInt(getTgt.get(deptIdx).toString());
                loopTarget=loopTarget*finalCount;

                //collect the stages needed for target report of the depratment in the loop
               targetDeptStage.clear();
               targetDeptActivity.clear();
               targetDeptFactor.clear();

               //System.out.println("deptLoopVble:"+deptLoopVble);

              stgArr=(String[]) getTgtDptStg.get(deptIdx);
               for(int i=0;i<stgArr.length;i++){
                   targetDeptStage.add(stgArr[i]);
               }

              activityArr=(String[]) getTgtDptActivity.get(deptIdx);
               for(int i=0;i<activityArr.length;i++){
                   targetDeptActivity.add(activityArr[i]);
               }

              factorArr=(String[]) getTgtDptFactor.get(deptIdx);
               for(int i=0;i<factorArr.length;i++){
                   targetDeptFactor.add(factorArr[i]);
               }

                index3=targetDeptStage.size();

                // int dept

               for(int stgIdx=0;stgIdx<index3;stgIdx++){
                      resultValue=0.0;
                   totalResultValue=0.0;
                      stgLoopVble=targetDeptStage.get(stgIdx).toString();
                      factorLoopVble=targetDeptFactor.get(stgIdx).toString();

                         totalDataModel=deptLoopVble+"_"+stgLoopVble+"_";
                       /*** get the total value from the collections **/
                       loopData="0";
                    dataIndex=collectTotalIndexList.indexOf(totalDataModel);
                    if(dataIndex!=-1){
                            loopData=collectTotalDataList.get(dataIndex).toString();
                            dataIdentifier=loopData.lastIndexOf("_");
                            dataIdentifier++;
                            loopData=loopData.substring(dataIdentifier);
                      }
                      else{
                          loopData="0";
                      }
//System.out.println("loopData:"+loopData);
                 /*   System.out.println("stgLoopVble:"+stgLoopVble);
                    System.out.println("factorLoopVble:"+factorLoopVble);*/
                      if(factorLoopVble.equals("")){
                       totalResultValue = Double.parseDouble(loopData);
                      }
                      else{
                          totalResultValue = Double.parseDouble(loopData);
                          totalResultValue=totalResultValue*Double.parseDouble(factorLoopVble);
                      }

                     calculateTotalTarget=calculateTotalTarget+totalResultValue;

                   /*  System.out.println("totalResultValue:"+totalResultValue);
                       System.out.println("calculateTotalTarget:"+calculateTotalTarget);
                  */
                       totalDataList.add(loopData);

               }//close of stage loop



              totalDataList.add(roundDecimal(String.valueOf(calculateTotalTarget)));
              calculatePercent=(calculateTotalTarget/loopTarget)*100;
              totalDataList.add(roundValue(String.valueOf(calculatePercent)));
              totalUnitsData.add(roundDecimal(String.valueOf(calculateTotalTarget)));
              totalUnitsPercentage.add(roundValue(String.valueOf(calculatePercent)));

              }//close of deptIdx

/*
System.out.println("totalDataList:"+totalDataList);*/
//System.out.println("totalUnitsData:"+totalUnitsData);
/*System.out.println("totalUnitsPercentage:"+totalUnitsPercentage);
*/




/*************Load the total Values ****/

}




public String roundDecimal(String calculateTarget){
String chkcalculateTarget=String.valueOf(calculateTarget);
//System.out.println("chkcalculateTarget-before:"+chkcalculateTarget);
 int decimalIndex=chkcalculateTarget.indexOf(".");
 int checkDecimal = Integer.parseInt(chkcalculateTarget.substring(decimalIndex+1,decimalIndex+2));

//System.out.println("checkDecimal:"+checkDecimal);
if(checkDecimal>0){
    try{
           chkcalculateTarget=chkcalculateTarget.substring(0,chkcalculateTarget.indexOf(".")+3);
    }catch(StringIndexOutOfBoundsException SIBE){
           chkcalculateTarget=chkcalculateTarget.substring(0,chkcalculateTarget.indexOf(".")+2);
    }

//System.out.println("chkcalculateTarget-after:"+chkcalculateTarget);
}
else{
  chkcalculateTarget = chkcalculateTarget.substring(0,chkcalculateTarget.indexOf("."));
}
//System.out.println("chkcalculateTarget:"+chkcalculateTarget);
 return chkcalculateTarget;
}






}
