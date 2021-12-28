/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.generic;

import connection.DBconnection;
import java.beans.Statement;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.DateFormat;

/**
 *
 * @author ramesh
 */
public class PFUtil implements Serializable {

    private String inputString;
    private String resultString;
    connection.DBconnection connection = new connection.DBconnection();
    private String destCode = "";
    private String toDaydate = "";
    private String weekStart = "";
    private String weekEnd = "";
    private String monthStart = "";
    private String monthEnd = "";
    private List deptCode = new ArrayList();
    private List deptName = new ArrayList();
    private String db_timezone_id = "";
    private String db_timezone_ref = "";
    private String destDiffParam = "";
    private String db_timezone_diffhrs = "";
    private String db_timezone_diffmts = "";
    private String destTimeZoneCode = "";
    private String srcDiffParam = "";
    String srcHr = "";
    String srcMt = "";
    String destHr = "";
    String destMt = "";

    public PFUtil() {
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return resultString;
    }

    public String getToDaydate() {
        return this.toDaydate;
    }

    public String getWeekEnd() {
        return this.weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }

    public String getWeekStart() {
        return this.weekStart;
    }

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    public String getMonthEnd() {
        return this.monthEnd;
    }

    public void setMonthEnd(String monthEnd) {
        this.monthEnd = monthEnd;
    }

    public String getMonthStart() {
        return this.monthStart;
    }

    public void setMonthStart(String monthStart) {
        this.monthStart = monthStart;
    }

    public List getDeptCode() {
        return this.deptCode;
    }

    public void setDeptCode(List deptCode) {
        this.deptCode = deptCode;
    }

    public List getDeptName() {
        return this.deptName;
    }

    public void setDeptName(List deptName) {
        this.deptName = deptName;
    }
    

    public String encodeSpecialCharacter(String text) {

        int len = text.length();
        //System.out.println("text:" + text);
        StringBuffer encodedString = new StringBuffer();

        for (int m = 0; m < len; m++) {
            char c = text.charAt(m);

            if (c == '\'') {
                encodedString.append("\\\'");
            } /*else if(c=='>')
            encodedQuestion.append("&gt;");
            else if(c=='&')
            encodedQuestion.append("&amp;");
            else if(c=='"')
            encodedQuestion.append("&quot;");
            else if(c=='\n')
            encodedQuestion.append("<br>");*/ else {
                encodedString.append(c);
            }
        }

        //System.out.println("result text:" + encodedString.toString());
        return encodedString.toString();
    }

    public String GetDateTime() {

        SimpleDateFormat sdfDate = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");

        Date now = new Date();

        String strDate = sdfDate.format(now);
        String strTime = sdfTime.format(now);

        //System.out.println("Date: " + strDate);
        //System.out.println("Time: " + strTime);

        return strDate + strTime;
    }

    //This function is used to round of the given double varibale to 2 decimal places
    public double roundOff(double value) {

        double val = 0;

        DecimalFormat dc = new DecimalFormat("#,###,###,##0.00");
        val = new Double(dc.format(value)).doubleValue();

        return val;

    }

    public String dateFormat(String inputDate) throws ParseException {
        Format formatter = new SimpleDateFormat("dd-MMM-yy");
        Date date = null;
        try {
            date = (Date) formatter.parseObject(inputDate);
        } catch (ParseException ex) {
            System.out.println("Error occured at dateFormat:" + ex);
        }



        String reportDate = formatter.format(date);


        //System.out.println(reportDate);
        return reportDate;
    }

    public String convertDateFormat(String inputDate, String format) throws ParseException {
        Format formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = (Date) formatter.parseObject(inputDate);
        } catch (ParseException ex) {
            System.out.println("Error occured at dateFormat:" + ex);
        }



        String reportDate = formatter.format(date);


        //System.out.println(reportDate);
        return reportDate;
    }

    /*
     * Converts time from sourceTZ TimeZone to destTZ TimeZone.
     *
     * @return converted time, or the original time, in case the datetime could not be parsed
     *
     */
    public String convTimeZone(String time, String sourceTZ, String destTZ, String format) {

        //System.out.println("time:" + time);
        //System.out.println("source:" + sourceTZ);
        //System.out.println("destTz:" + destTZ);
        //System.out.println("format:" + format);


        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date specifiedTime = null;
        try {
            if (sourceTZ != null) {
                sdf.setTimeZone(TimeZone.getTimeZone(sourceTZ));
            } else {
                sdf.setTimeZone(TimeZone.getDefault()); // default to server's timezone
            }
            specifiedTime = sdf.parse(time);
        } catch (Exception e1) {
            e1.printStackTrace();

        }

        // switch timezone
        if (destTZ != null) {
            sdf.setTimeZone(TimeZone.getTimeZone(destTZ));
        } else {
            sdf.setTimeZone(TimeZone.getDefault()); // default to server's timezone
        }
        return sdf.format(specifiedTime);
    }

    public Date stringToDate(String stringDate, String format) {
        Date date = null;

      /*  System.out.println("StringToDate--------------");
        System.out.println("stringDate:" + stringDate);
        System.out.println("format:" + format);*/
        try {

            DateFormat formatter;
            formatter = new SimpleDateFormat(format);
            date = (Date) formatter.parse(stringDate);
            //System.out.println("stringToDate " + date);



        } catch (ParseException e) {
            System.out.println("Exception :" + e);
        }

        //System.out.println("End of StringToDate--------");
        return date;
    }

    public String splitDateFormat(Date dateToExtract, String extractFormat) {
       // System.out.println("date:" + dateToExtract);



        String extractedDate = "";
        SimpleDateFormat simpleDateformat = new SimpleDateFormat(extractFormat);

        extractedDate = simpleDateformat.format(dateToExtract);

       // System.out.println("extractedDate:" + extractedDate);

        return extractedDate;

    }

    public String timeZoneConvert(String time, String sourceTZ, String destTZ, String sourceFormat, String format) {

       /* System.out.println("Source Time:" + time);

        System.out.println("Source Time:" + time);
        System.out.println("SourceTz:" + sourceTZ);
        System.out.println("destTZ:" + destTZ);
        System.out.println("format:" + format);*/


        String convertedDate = "";
        String sourceTime = time;


        //Call getDbTimeZoneDetails to get the details
        getDbTimeZoneDetails(sourceTZ);

        String sourceDiffParam = destDiffParam; //(+)
        int sourceHr = Integer.parseInt(db_timezone_diffhrs);
        int sourceMt = Integer.parseInt(db_timezone_diffmts);
/*System.out.println("---------");
        System.out.println("sourcediffparam:" + sourceDiffParam);
        System.out.println("sourceHr:" + sourceHr);
        System.out.println("sourceMt:" + sourceMt);*/

        getDbTimeZoneDetails(destTZ);
        String destinationDiffParam = destDiffParam;//cst;;
        int destinationHr = Integer.parseInt(db_timezone_diffhrs); //CST-6hrs and 30 mins
        int destinationMt = Integer.parseInt(db_timezone_diffmts);



      /*  System.out.println("destinationDiffParam:" + destinationDiffParam);
        System.out.println("destinationHr:" + destinationHr);
        System.out.println("destinationMt:" + destinationMt);

        System.out.println("sourcediffparam:" + sourceDiffParam);
        System.out.println("sourceHr:" + sourceHr);
        System.out.println("sourceMt:" + sourceMt);

        System.out.println("---------");*/

        if (destinationDiffParam.equals("-")) {
            destinationHr = -destinationHr;
            destinationMt = -destinationMt;
        }


        Date dateToExtract = stringToDate(sourceTime, sourceFormat);
        String set_yr = splitDateFormat(dateToExtract, "yyyy");
        String set_mnth = splitDateFormat(dateToExtract, "MM");
        String set_dat = splitDateFormat(dateToExtract, "dd");

        String set_hr = splitDateFormat(dateToExtract, "hh");
        String set_mt = splitDateFormat(dateToExtract, "mm");
        String set_AM_PM = splitDateFormat(dateToExtract, "a");

        //System.out.println("sourceTime :" + sourceTime);

        set_AM_PM = set_AM_PM.trim();
        int cal_AM_PM = 0;
        int cal_yr = Integer.parseInt(set_yr.trim());
        int cal_mnth = Integer.parseInt(set_mnth.trim());
        int cal_dat = Integer.parseInt(set_dat.trim());
        int cal_hr = Integer.parseInt(set_hr.trim());
        int cal_mt = Integer.parseInt(set_mt.trim());
        if (set_AM_PM.equals("AM")) {
            if (cal_hr == 12) {
                cal_hr = 00;
            }
        } else if (set_AM_PM.equals("PM")) {

            if (cal_hr != 12) {
                cal_hr += 12;
            }
        }



        Calendar zonecal = new GregorianCalendar(cal_yr, cal_mnth, cal_dat, cal_hr, cal_mt);
        zonecal.set(Calendar.YEAR, cal_yr);
        zonecal.set(Calendar.MONTH, cal_mnth - 1);
        zonecal.set(Calendar.DATE, cal_dat);
        zonecal.set(Calendar.HOUR, cal_hr);
        zonecal.set(Calendar.MINUTE, cal_mt);
        zonecal.set(Calendar.AM_PM, cal_AM_PM);

if(sourceDiffParam.equals("+"))
{
        sourceHr = -sourceHr;
        sourceMt = -sourceMt;
}
else if(sourceDiffParam.equals("-"))
{
     sourceHr = +sourceHr;
     sourceMt = +sourceMt;
}

//System.out.println("CAlder with est Time:"+zonecal.getTime());
        zonecal.add(Calendar.HOUR, sourceHr);
        zonecal.add(Calendar.MINUTE, sourceMt);
//System.out.println("CAlder with est Time+Ist:"+zonecal.getTime());

        zonecal.add(Calendar.HOUR, destinationHr);
        zonecal.add(Calendar.MINUTE, destinationMt);

       // System.out.println("CAlder with est Time+Ist+Diff Hrs:"+zonecal.getTime());
        if (cal_hr == 12 && cal_AM_PM == 1) {
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        convertedDate = sdf.format(zonecal.getTime());

       // System.out.println("Converted Time:" + convertedDate);
        return convertedDate;
    }

    public void getDbTimeZoneDetails(String code) {
        DBconnection dbcon = new DBconnection();
        Connection con = null;
        java.sql.Statement stmt = null;
        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            ResultSet rsGet_Tz = stmt.executeQuery(" select timezone_code,timezone_id,reference_timezone,time_difference_param,diff_hours,diff_minutes from timezone where timezone_code='" + code + "'");

            while (rsGet_Tz.next()) {
                destCode = rsGet_Tz.getString(1);
                if (rsGet_Tz.wasNull()) {
                    destCode = "";
                }

                db_timezone_id = rsGet_Tz.getString(2);
                if (rsGet_Tz.wasNull()) {
                    db_timezone_id = "";
                }


                db_timezone_ref = rsGet_Tz.getString(3);
                if (rsGet_Tz.wasNull()) {
                    db_timezone_ref = "";
                }


                destDiffParam = rsGet_Tz.getString(4);
                if (rsGet_Tz.wasNull()) {
                    destDiffParam = "";
                }

                db_timezone_diffhrs = rsGet_Tz.getString(5);
                if (rsGet_Tz.wasNull()) {
                    db_timezone_diffhrs = "";
                }

                db_timezone_diffmts = rsGet_Tz.getString(6);
                if (rsGet_Tz.wasNull()) {
                    db_timezone_diffmts = "";
                }

            }
            rsGet_Tz.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException in getDbTimeZoneDetails:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getDbTimeZoneDetails:" + e);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException in con close of getDbTimeZoneDetails:" + sqle);
            }
        }

    }

    public void getTodayDate() {
        DBconnection dbcon = new DBconnection();
        Connection con = null;
        java.sql.Statement stmt = null;
        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            ResultSet rsGet_Tz = stmt.executeQuery(" SELECT DATE(CURRENT_TIMESTAMP),DATE_FORMAT(CURRENT_TIMESTAMP,'%d-%b-%Y') ");

            while (rsGet_Tz.next()) {
                toDaydate = rsGet_Tz.getString(1);
                if (rsGet_Tz.wasNull()) {
                    toDaydate = "";
                }
            }
            rsGet_Tz.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException in getDbTimeZoneDetails:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getDbTimeZoneDetails:" + e);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException in con close of getDbTimeZoneDetails:" + sqle);
            }
        }

    }

    public void getCurrentWeek() {
        DBconnection dbcon = new DBconnection();
        Connection con = null;
        java.sql.Statement stmt = null;
        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            ResultSet rsGet_Tz = stmt.executeQuery(" SELECT ADDDATE(CURDATE(), INTERVAL 1-DAYOFWEEK(CURDATE()) DAY), ADDDATE(CURDATE(), INTERVAL 7-DAYOFWEEK(CURDATE()) DAY) ");

            while (rsGet_Tz.next()) {
                weekStart = rsGet_Tz.getString(1);
                if (rsGet_Tz.wasNull()) {
                    weekStart = "";
                }
                weekEnd = rsGet_Tz.getString(2);
                if (rsGet_Tz.wasNull()) {
                    weekEnd = "";
                }
            }
            rsGet_Tz.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException in getDbTimeZoneDetails:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getDbTimeZoneDetails:" + e);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException in con close of getDbTimeZoneDetails:" + sqle);
            }
        }

    }

    public void getCurrentMonthStartEnd() {
        DBconnection dbcon = new DBconnection();
        Connection con = null;
        java.sql.Statement stmt = null;
        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            ResultSet rsGet_Tz = stmt.executeQuery(" SELECT CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') AS DATE) AS firstOfcurrentMonth,DATE_FORMAT(CURRENT_TIMESTAMP, '%Y-%m-%d') AS currentDay ");

            while (rsGet_Tz.next()) {
                monthStart = rsGet_Tz.getString(1);
                if (rsGet_Tz.wasNull()) {
                    monthStart = "";
                }
                monthEnd = rsGet_Tz.getString(2);
                if (rsGet_Tz.wasNull()) {
                    monthEnd = "";
                }
            }
            rsGet_Tz.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException in getDbTimeZoneDetails:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getDbTimeZoneDetails:" + e);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException in con close of getDbTimeZoneDetails:" + sqle);
            }
        }

    }

    public void getScheduleDepartment() {
        DBconnection dbcon = new DBconnection();
        Connection con = null;
        java.sql.Statement stmt = null;
        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            ResultSet rsGet_Dept = stmt.executeQuery(" SELECT distinct(a.department),a.dept_code FROM department a,projects b WHERE a.dept_code=b.dept_code ");

            deptName.clear();
            deptCode.clear();
            
            while (rsGet_Dept.next()) {
                deptName.add(rsGet_Dept.getString(1));
                if (rsGet_Dept.wasNull()) {
                     deptName.add("");
                }
                deptCode.add(rsGet_Dept.getString(2));
                if (rsGet_Dept.wasNull()) {
                    deptCode.add("");
                }
            }
            rsGet_Dept.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException in getDbTimeZoneDetails:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getDbTimeZoneDetails:" + e);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException in con close of getDbTimeZoneDetails:" + sqle);
            }
        }

    }

    public String decodeToHtmlTag(String text){
    //since rich text editor is being used in the interfces this method is required to decode the characters added in the condition to the respective html characters
        int len= text.length();
        StringBuffer encodedQuestion=new StringBuffer();
	String testString = "";
	testString  = text.replaceAll("&lt;", "<");
	testString  = testString.replaceAll("&gt;", ">");
	testString  = testString.replaceAll("\n", "<br/>");
	return testString;
    }
}
