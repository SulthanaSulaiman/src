package pathfinder.functions.generic;

import java.io.Serializable;

import java.util.*;
import java.text.SimpleDateFormat;
/**
 *
 * @author ramesh
 */
public class DateDifference implements Serializable {

    private String beginDate;
    private String endDt;
    private String dateDiff;
    private List selectedDates=new ArrayList();

    public void setBeginDate(String beginDate){
        this.beginDate=beginDate;
    }

      public void setEndDate(String endDt){
        this.endDt=endDt;
    }

      public String getDateDiff(){
          return dateDiff;
      }

     public List getSelDates(){
          return selectedDates;
      }


      public void calculateDateDiff(){

          try{
//          System.out.println("beginDate:"+beginDate);
//          System.out.println("endDt:"+endDt);

          int strtYear = Integer.parseInt(beginDate.substring(0,4));
          int strtMonth = Integer.parseInt(beginDate.substring(5,7));
          int strtDate = Integer.parseInt(beginDate.substring(8,10));
          int endYear = Integer.parseInt(endDt.substring(0,4));
          int endMonth = Integer.parseInt(endDt.substring(5,7));
          int endDate = Integer.parseInt(endDt.substring(8,10));


          /*System.out.println("strtYear:"+strtYear);
          System.out.println("strtMonth:"+strtMonth);
          System.out.println("strtDate:"+strtDate);

          System.out.println("endYear:"+endYear);
          System.out.println("endMonth:"+endMonth);
          System.out.println("endDate:"+endDate);*/

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.set(strtYear, strtMonth, strtDate);
        calendar2.set(endYear, endMonth, endDate);


    long milliseconds1 = calendar1.getTimeInMillis();
    long milliseconds2 = calendar2.getTimeInMillis();
    long diff = milliseconds2 - milliseconds1;
    long diffDays = diff / (24 * 60 * 60 * 1000);

     selectedDates.clear();


    	Calendar setCal = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date fromDate = sdf.parse(beginDate);
	java.util.Date toDate = sdf.parse(endDt);

        selectedDates.add(beginDate);

        String toYear = beginDate.substring(0,4);
	String toMonth = beginDate.substring(5,7);
	String to_Date = beginDate.substring(8,10);

        //System.out.println("toMonth:"+toMonth);

	int monthVal = Integer.parseInt(toMonth);
	monthVal = monthVal-1;

        setCal.set(setCal.DATE,Integer.parseInt(to_Date));
	setCal.set(setCal.MONTH,monthVal);
	setCal.set(setCal.YEAR,Integer.parseInt(toYear));
        String param2 = "";

        for(int idx=0;idx<diffDays;idx++){
        setCal.add(setCal.DATE,1);
        param2 = sdf.format(setCal.getTime());
        selectedDates.add(param2);
        }




    diffDays=diffDays+1;//this is to get the exact count as the difference between '2009-07-01' and '2009-07-02' will be calculated as one day but they are actually two days for which the leave is been applied

    dateDiff = String.valueOf(diffDays);





          }catch(Exception e){
              System.out.println("Exception in DateDiffernce"+e);
          }

      }


}
