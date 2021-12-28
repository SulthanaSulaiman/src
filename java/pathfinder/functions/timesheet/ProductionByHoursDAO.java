/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.timesheet;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.text.ParseException;
import java.util.*;
import connection.DBconnection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 *
 * @author Parameshwarant
 */
public class ProductionByHoursDAO {

    

    public void getProductionHours(ProductionByHoursVO getSetProductionValues) throws ParseException, SQLException {
        List empIdList = new ArrayList();
        List shiftEmpIdList = new ArrayList();
        List empNameList = new ArrayList();
        List activityIdList = new ArrayList();
        List activityNameList = new ArrayList();
        List secondsSpentList = new ArrayList();
        List shiftSecondsList = new ArrayList();
        List facilityIdList = new ArrayList();
        List facilityWorkHourList = new ArrayList();
        List empShiftStartTimeList = new ArrayList();
        List empShiftEndTimeList = new ArrayList();
        List isChargeableFlagList = new ArrayList();
        List chnErrSecondsList = new ArrayList();
        List dbqErrSecondsList = new ArrayList();
        List outErrSecondsList = new ArrayList();
        List shiftDaysdiff = new ArrayList();
        String queryToGetProductionHours = "";
        String queryToGetShiftTime = "";
        String emp_id = getSetProductionValues.getEmpId();
        //String empShiftChk = getSetProductionValues.getShiffDateChk();
        String emp_shift_start = getSetProductionValues.getEmpShiftStart();
        //String emp_shift_end = getSetProductionValues.getEmpShiftEnd();
        String shiftStart = "";
        String shiftEnd = "";
        String emp_shift_end_date = "";
        int numOfWeek = getSetProductionValues.getNumberOfWeek();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            DBconnection conToDB = new DBconnection();
            Connection connDB = conToDB.getSampleProperty();
            Statement stToGetProductionHours = connDB.createStatement();
            queryToGetShiftTime = "SELECT s.emp_id,u.facility_id, ifnull(ROUND(SUM(TIME_TO_SEC(TIMEDIFF(s.shift_end_time,s.shift_start_time)))/3600,2),0.00), min(s.shift_start_time),max(s.shift_end_time),f.fcy_work_hour,COUNT(DISTINCT DATE(s.shift_start_time))*f.fcy_work_hour  FROM shift s, USER u, facility f WHERE s.emp_id=u.emp_id AND f.facility_id=u.facility_id ";

            queryToGetProductionHours = " SELECT u.emp_id, u.emp_name, act.activity_code, act.activity,DATE_FORMAT(a.begin_time,'%Y-%m-%d %l:%i %p'),DATE_FORMAT(a.end_time,'%Y-%m-%d %l:%i %p'), "
                    + " CASE WHEN a.chargeable_flag='0' THEN 'Non-Chargeable' ELSE 'Chargeable' END AS ChargeableFlag, "
                    + " CASE WHEN a.chennai_err_start IS NOT NULL THEN ROUND(SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time)))/3600,2) ELSE 'NO ERR' END AS ChnErr, "
                    + " CASE WHEN a.dubuque_err_start IS NOT NULL THEN ROUND(SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time)))/3600,2) ELSE 'NO ERR' END AS DbqErr, "
                    + " CASE WHEN a.outside_err_start IS NOT NULL THEN ROUND(SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time)))/3600,2) ELSE 'NO ERR' END AS OutErr, "
                    + " ifnull(ROUND(SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time)))/3600,2),0.00) as Seconds FROM activity a LEFT JOIN USER u using (emp_id), activity_type act "
                    + " WHERE a.activity_code = act.activity_code  and a.emp_id is not null ";

            if (!emp_id.equals("")) {
                queryToGetProductionHours += " AND  a.emp_id = '" + emp_id + "' ";
                queryToGetShiftTime += " and s.emp_id = '" + emp_id + "' ";
            }
            
            if(numOfWeek!=0&&!emp_shift_start.equals("")) {
                Calendar weekEndCheckCalendar = Calendar.getInstance();
                weekEndCheckCalendar.setTime(sdf.parse(emp_shift_start));
                weekEndCheckCalendar.add(Calendar.DATE, (numOfWeek*7)-1);
                String day = String.valueOf(weekEndCheckCalendar.get(Calendar.DAY_OF_MONTH));
                String month = String.valueOf(weekEndCheckCalendar.get(Calendar.MONTH)+1);
                String year = String.valueOf(weekEndCheckCalendar.get(Calendar.YEAR));
                emp_shift_end_date = year+"-"+month+"-"+day;
                queryToGetProductionHours += " AND date(a.begin_time) BETWEEN '" + emp_shift_start + "' AND  '" + emp_shift_end_date + "' ";
                queryToGetShiftTime += " AND DATE(s.shift_start_time) BETWEEN '" + emp_shift_start + "' AND  '" + emp_shift_end_date + "' GROUP BY s.emp_id ";
                
            } 

            queryToGetProductionHours += " GROUP BY u.emp_id, act.activity_code, ChargeableFlag ORDER BY u.emp_id";
            //System.out.println(queryToGetProductionHours+"\n"+queryToGetShiftTime);
            ResultSet rsToGetProductionHours = stToGetProductionHours.executeQuery(queryToGetProductionHours);
            if (!rsToGetProductionHours.wasNull()) {
                while (rsToGetProductionHours.next()) {
                    empIdList.add(rsToGetProductionHours.getString(1));
                    empNameList.add(rsToGetProductionHours.getString(2));
                    activityIdList.add(rsToGetProductionHours.getString(3));
                    activityNameList.add(rsToGetProductionHours.getString(4));
                    empShiftStartTimeList.add(rsToGetProductionHours.getString(5));
                    empShiftEndTimeList.add(rsToGetProductionHours.getString(6));
                    isChargeableFlagList.add(rsToGetProductionHours.getString(7));
                    chnErrSecondsList.add(rsToGetProductionHours.getString(8));
                    dbqErrSecondsList.add(rsToGetProductionHours.getString(9));
                    outErrSecondsList.add(rsToGetProductionHours.getString(10));
                    secondsSpentList.add(rsToGetProductionHours.getString(11));
                }
            }
            if (!emp_shift_start.equals("")&&!emp_shift_end_date.equals("")) {
                Date dt1 = sdf.parse(emp_shift_start);
                Date dt2 = sdf.parse(emp_shift_end_date);
                int dayDiff = (int) ((dt2.getTime() - dt1.getTime()) / (1000 * 60 * 60 * 24));
                getSetProductionValues.setShiftDaysDiff(dayDiff + 1);
            }

            getSetProductionValues.setEmpIdList(empIdList);
            getSetProductionValues.setEmpNameList(empNameList);
            getSetProductionValues.setActivityIdList(activityIdList);
            getSetProductionValues.setActivityNameList(activityNameList);
            getSetProductionValues.setShiftStartTimeList(empShiftStartTimeList);
            getSetProductionValues.setShiftEndTimeList(empShiftEndTimeList);
            getSetProductionValues.setIsChargeableFlagList(isChargeableFlagList);
            getSetProductionValues.setChnErrorList(chnErrSecondsList);
            getSetProductionValues.setDbqErrorList(dbqErrSecondsList);
            getSetProductionValues.setOutErrorList(outErrSecondsList);
            getSetProductionValues.setSecondsSpentList(secondsSpentList);

            ResultSet rsToGetShiftHours = stToGetProductionHours.executeQuery(queryToGetShiftTime);
            if (!rsToGetShiftHours.wasNull()) {
                while (rsToGetShiftHours.next()) {
                    shiftEmpIdList.add(rsToGetShiftHours.getString(1));
                    facilityIdList.add(rsToGetShiftHours.getString(2));
                    shiftSecondsList.add(rsToGetShiftHours.getString(3));
                    shiftStart = rsToGetShiftHours.getString(4);
                    shiftEnd = rsToGetShiftHours.getString(5);
                    facilityWorkHourList.add(rsToGetShiftHours.getString(6));
                    shiftDaysdiff.add(rsToGetShiftHours.getString(7));
                }
            }
            

            if (emp_shift_start.equals("")) {
                Date dt1 = sdf.parse(shiftStart);
                Date dt2 = sdf.parse(shiftEnd);
                int dayDiff = (int) ((dt2.getTime() - dt1.getTime()) / (1000 * 60 * 60 * 24));
                getSetProductionValues.setShiftDaysDiff(dayDiff + 1);
            }
            getSetProductionValues.setShiftEmpIdList(shiftEmpIdList);
            getSetProductionValues.setShiftSecondsList(shiftSecondsList);
            getSetProductionValues.setFacilityIdList(facilityIdList);
            getSetProductionValues.setFacilityWorkList(facilityWorkHourList);
            getSetProductionValues.setShiftDayDiff(shiftDaysdiff);

            rsToGetProductionHours.close();
            stToGetProductionHours.close();
            connDB.close();

        } catch (SQLException sqle) {
            System.out.println("SQL Exception in class=ProductionByHoursDAO function:getProductionHours " + sqle);
        }
    }

    public void getEmployeeNameId(ProductionByHoursVO getSetEmpNameId) {

        List emp_idList = new ArrayList();
        List emp_nameList = new ArrayList();
        String queryToGetEmpNameId = " SELECT a.emp_id, b.emp_name FROM activity a left join user b using (emp_id) WHERE a.emp_id IS NOT NULL GROUP BY a.emp_id order by b.emp_name";
        try {

            DBconnection conToDB = new DBconnection();
            Connection connDB = conToDB.getSampleProperty();
            Statement stToGetEMpNameId = connDB.createStatement();
            ResultSet rsToGetEMpNameId = stToGetEMpNameId.executeQuery(queryToGetEmpNameId);
            while (rsToGetEMpNameId.next()) {
                emp_idList.add(rsToGetEMpNameId.getString(1));
                emp_nameList.add(rsToGetEMpNameId.getString(2));
            }
            getSetEmpNameId.setEmp_IdList(emp_idList);
            getSetEmpNameId.setEmp_NameList(emp_nameList);

        } catch (SQLException sqle) {
            System.out.println("SQL Exception in class=ProductionByHoursDAO function:getEmployeeNameId " + sqle);
        }
    }
}
