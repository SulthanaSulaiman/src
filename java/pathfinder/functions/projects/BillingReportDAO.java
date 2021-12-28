/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import connection.DBconnection;
import java.sql.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 *
 * @author Parameshwarant
 */
public class BillingReportDAO {

    String queryToGetBillingProject = "";
    String queryToChekWeekEndDays = "";
    String queryToGetOnTimeShipdCount = "";
    String actShipDateFrom = "";
    String actShipDateTo = "";
    String plannerId = "";
    String hybridPlannerId = "";
    String shippedProjTotal = "";
    String beforeOnTime = "";
    String beforeOnTimePercentage = "";
    String onTime = "";
    String onTimePercentage = "";
    String moreThanOneDay = "";
    String moreThanOneDayPercentage = "";
    String moreThanTwoAndThreeDay = "";
    String moreThanAndThreeDayPercentage = "";
    String greaterThanThreeDay = "";
    String greaterThanThreeDayPercentage = "";
    List projIdList = new ArrayList();
    List projNameList = new ArrayList();
    List customerNameList = new ArrayList();
    List plannerNameList = new ArrayList();
    List hybridPlannerNameList = new ArrayList();
    List actShipDateList = new ArrayList();
    List actShipDateListChk = new ArrayList();
    List readyToBillDateList = new ArrayList();
    List readyToBillDateListChk = new ArrayList();
    List actualAndReadyBillWeekDayDiff = new ArrayList();
    List actualAndReadyBillDayDiff = new ArrayList();
    List billedDateList = new ArrayList();
    List billedDateListChk = new ArrayList();
    List readyBillAndBilledWeekDayDiff = new ArrayList();
    List readyBillAndBilledDayDiff = new ArrayList();
    List dayDiffExceptWeekEndsList = new ArrayList();
    List actShipMonthYearList = new ArrayList();

    public void getBilledAndReadyToBillProjects(BillingReportVO setBillingReportValues) throws ParseException {

        projIdList.clear();
        projNameList.clear();
        customerNameList.clear();
        plannerNameList.clear();
        hybridPlannerNameList.clear();
        actShipDateList.clear();
        readyToBillDateList.clear();
        billedDateList.clear();
        readyBillAndBilledDayDiff.clear();
        actualAndReadyBillWeekDayDiff.clear();
        readyBillAndBilledWeekDayDiff.clear();
        actualAndReadyBillDayDiff.clear();
        actShipMonthYearList.clear();
        plannerId = "";
        hybridPlannerId = "";
        actShipDateFrom = "";
        actShipDateTo = "";


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String currDate = sdf.format(today);

        String actShipDateChk = "";
        String readyToBillDateChk = "";
        String fromServletPdfChk = "";
        String actShipMonth = "";
        String actShipYear = "";

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        
        try {

            DBconnection con = new DBconnection();
            Connection conToDB = con.getSampleProperty();
            Statement stToGetBillingProject = conToDB.createStatement();

            actShipDateChk = setBillingReportValues.getActShipDateChk();
            fromServletPdfChk = setBillingReportValues.getFromServletPdf();
            plannerId = setBillingReportValues.getPlannerId();
            hybridPlannerId = setBillingReportValues.getHybridPlannerId();

            queryToGetBillingProject = "SELECT a.proj_id,CONCAT(a.proj_id,' / ',a.proj_name), b.company, "
                    + " case when a.planner=e.emp_id then e.emp_name else 'N/A' end as Planner, "
                    + " case when a.hybrid_planner=f.emp_id  then f.emp_name else 'N/A' end as HybridPlanner, "
                    + " CASE WHEN a.act_ship_date IS NOT NULL THEN a.act_ship_date ELSE 'N/A' END as ActShipDate, "
                    + " CASE WHEN a.ready_to_bill_date IS NOT NULL THEN date(a.ready_to_bill_date) ELSE 'N/A' END as ReadyToBillDate, "
                    + " CASE WHEN a.ready_to_bill_date IS NOT NULL and a.act_ship_date IS NOT NULL THEN datediff(date(a.ready_to_bill_date),a.act_ship_date) ELSE DATEDIFF(CURRENT_DATE,a.act_ship_date) END as ActReadyToBillDiff, "
                    + " CASE WHEN a.billed_date  IS NOT NULL THEN date(a.billed_date)  ELSE 'N/A' END as Billed, "
                    + " CASE WHEN a.billed_date IS NOT NULL and a.ready_to_bill_date IS NOT NULL THEN datediff(date(a.billed_date),date(a.ready_to_bill_date)) ELSE DATEDIFF(CURRENT_DATE,date(a.ready_to_bill_date)) END as ReadyToBillBilledDiff, "
                    + " CASE WHEN a.act_ship_date IS NOT NULL THEN DATE_FORMAT(a.act_ship_date,'%M %Y') ELSE 'N/A' END AS ActShipMonthYear "
                    + " FROM projects a LEFT JOIN USER e ON a.planner=e.emp_id LEFT JOIN USER f  ON a.hybrid_planner=f.emp_id, contacts b, department c,STATUS d WHERE a.dept_code=c.dept_code AND a.client_name=b.contact_id "
                    + " AND a.project_status=d.status_id AND a.client_name IS NOT NULL AND a.act_ship_date IS NOT NULL ";


            if (actShipDateChk.equals("1")) {
                actShipDateFrom = setBillingReportValues.getActShipDateFrom();
                actShipDateTo = setBillingReportValues.getActShipDateTo();
                queryToGetBillingProject += " AND a.act_ship_date BETWEEN '" + actShipDateFrom + "' AND '" + actShipDateTo + "' ";
            }
            if (!plannerId.equals("")) {
                queryToGetBillingProject += " AND a.planner = '" + plannerId + "' ";
            }
            if (!hybridPlannerId.equals("")) {
                queryToGetBillingProject += " AND a.hybrid_planner = '" + hybridPlannerId + "' ";
            }
            if (fromServletPdfChk.equals("BillingReportPdfservlet") && actShipMonth.equals("N/A") && actShipYear.equals("N/A")) {
                queryToGetBillingProject += " ORDER BY ActShipMonthYear, ActReadyToBillDiff desc, b.company, e.emp_name  ";
            } else {
                queryToGetBillingProject += " ORDER BY ActReadyToBillDiff desc, b.company, e.emp_name ";
            }



            //System.out.println("queryToGetBillingReport: " + queryToGetBillingProject);

            ResultSet rsToGetBillingProject = stToGetBillingProject.executeQuery(queryToGetBillingProject);
            while (rsToGetBillingProject.next()) {
                projIdList.add(rsToGetBillingProject.getString(1));
                projNameList.add(rsToGetBillingProject.getString(2));
                customerNameList.add(splChar.decoding(rsToGetBillingProject.getString(3)));
                plannerNameList.add(rsToGetBillingProject.getString(4));
                hybridPlannerNameList.add(rsToGetBillingProject.getString(5));

                actShipDateChk = rsToGetBillingProject.getString(6);
                if (actShipDateChk.equals("N/A")) {
                    actShipDateListChk.add(currDate);
                } else {
                    actShipDateListChk.add(rsToGetBillingProject.getString(6));
                }
                actShipDateList.add(rsToGetBillingProject.getString(6));

                readyToBillDateChk = rsToGetBillingProject.getString(7);
                if (readyToBillDateChk.equals("N/A")) {
                    readyToBillDateListChk.add(currDate);
                } else {
                    readyToBillDateListChk.add(rsToGetBillingProject.getString(7));
                }
                readyToBillDateList.add(rsToGetBillingProject.getString(7));

                actualAndReadyBillDayDiff.add(rsToGetBillingProject.getString(8) != null ? rsToGetBillingProject.getString(8) : "N/A");
                billedDateList.add(rsToGetBillingProject.getString(9));
                readyBillAndBilledDayDiff.add(rsToGetBillingProject.getString(10) != null ? rsToGetBillingProject.getString(10) : "N/A");
                actShipMonthYearList.add(rsToGetBillingProject.getString(11));
            }


            setBillingReportValues.setProjNameList(projNameList);
            setBillingReportValues.setCustomerNameList(customerNameList);
            setBillingReportValues.setPlannerNameList(plannerNameList);
            setBillingReportValues.setActShipDateList(actShipDateList);
            setBillingReportValues.setReadyToBillDateList(readyToBillDateList);
            setBillingReportValues.setBilledDateList(billedDateList);
            setBillingReportValues.setHybridPlannerNameList(hybridPlannerNameList);
            setBillingReportValues.setActShipMonthYearList(actShipMonthYearList);


            actualAndReadyBillWeekDayDiff.clear();
            int size = projNameList.size();
            String actualAndReadyBillDayDiffChk = "";
            String readyBillAndBilledDayDiffChk = "";

            for (int i = 0; i < size; i++) {

                actualAndReadyBillDayDiffChk = actualAndReadyBillDayDiff.get(i).toString();
                readyBillAndBilledDayDiffChk = readyBillAndBilledDayDiff.get(i).toString();

                int weekEndCount = 0;
                int weekEndCount1 = 0;
                if (!actualAndReadyBillDayDiffChk.equals("N/A")) {
                    int actualAndReadyBillDiffDay = Integer.parseInt(actualAndReadyBillDayDiffChk);
                    String actualShipDate = actShipDateListChk.get(i).toString();
                    if (actualAndReadyBillDiffDay > 0) {
                        for (int j = 1; j <= actualAndReadyBillDiffDay; j++) {
                            Calendar weekEndCheckCalendar = Calendar.getInstance();
                            weekEndCheckCalendar.setTime(sdf.parse(actualShipDate));
                            weekEndCheckCalendar.add(Calendar.DATE, j);
                            int dayOfWeek = weekEndCheckCalendar.get(Calendar.DAY_OF_WEEK);
                            if (dayOfWeek == 7 || dayOfWeek == 1) {
                                weekEndCount++;
                            }
                        }

                        actualAndReadyBillWeekDayDiff.add((actualAndReadyBillDiffDay - weekEndCount));

                    } else if (actualAndReadyBillDiffDay == 0) {
                        actualAndReadyBillWeekDayDiff.add(0);
                    } else if (actualAndReadyBillDiffDay < 0) {
                        actualAndReadyBillWeekDayDiff.add(actualAndReadyBillDiffDay);
                    }
                } else {
                    actualAndReadyBillWeekDayDiff.add("N/A");
                }


                if (!readyBillAndBilledDayDiffChk.equals("N/A")) {
                    int readyBillAndBilledDiffDay = Integer.parseInt(readyBillAndBilledDayDiffChk);

                    String readyToBill_Date = readyToBillDateListChk.get(i).toString();
                    if (readyBillAndBilledDiffDay > 0) {
                        for (int j = 1; j <= readyBillAndBilledDiffDay; j++) {
                            Calendar weekEndCheckCalendar = Calendar.getInstance();
                            weekEndCheckCalendar.setTime(sdf.parse(readyToBill_Date));
                            weekEndCheckCalendar.add(Calendar.DATE, j);
                            int dayOfWeek = weekEndCheckCalendar.get(Calendar.DAY_OF_WEEK);
                            if (dayOfWeek == 7 || dayOfWeek == 1) {
                                weekEndCount1++;
                            }
                        }

                        readyBillAndBilledWeekDayDiff.add((readyBillAndBilledDiffDay - weekEndCount1));

                    } else if (readyBillAndBilledDiffDay == 0) {
                        readyBillAndBilledWeekDayDiff.add(0);
                    } else if (readyBillAndBilledDiffDay < 0) {
                        readyBillAndBilledWeekDayDiff.add(readyBillAndBilledDiffDay);
                    }
                } else {
                    readyBillAndBilledWeekDayDiff.add("N/A");
                }

            }

            setBillingReportValues.setActualAndReadyBillDayDiff(actualAndReadyBillWeekDayDiff);
            setBillingReportValues.setReadyBillAndBilledDayDiff(readyBillAndBilledWeekDayDiff);

            rsToGetBillingProject.close();
            stToGetBillingProject.close();
            conToDB.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException: class=BillingReportDAO function=getBilledAndReadyToBillProjects " + sqle);
        }

    }

    public void getBillingReportActualShipDate(BillingReportVO setBillingReportShipDate) {

        String queryForGetProjShipDate = "";
        List projShipDateMonthList = new ArrayList();
        List projShipDateYearList = new ArrayList();

        try {
            DBconnection conn = new DBconnection();
            Connection conToDB = conn.getSampleProperty();
            Statement stToGetMonthYear = conToDB.createStatement();

            queryForGetProjShipDate = "SELECT CASE WHEN a.act_ship_date IS NOT NULL THEN EXTRACT(MONTH FROM a.act_ship_date) ELSE 'N/A' END AS ActShipMonth, "
                    + " CASE WHEN a.act_ship_date IS NOT NULL THEN EXTRACT(YEAR FROM a.act_ship_date) ELSE 'N/A' END AS ActShipYear "
                    + " FROM projects a LEFT JOIN USER e ON a.planner=e.emp_id LEFT JOIN USER f  ON a.hybrid_planner=f.emp_id, contacts b, department c "
                    + " WHERE a.dept_code=c.dept_code AND a.client_name=b.contact_id AND a.client_name IS NOT NULL AND a.act_ship_date IS NOT NULL GROUP BY ActShipMonth, ActShipYear ORDER BY a.act_ship_date ";


            ResultSet rsForGetProjShipDate = stToGetMonthYear.executeQuery(queryForGetProjShipDate);


            while (rsForGetProjShipDate.next()) {
                projShipDateMonthList.add(rsForGetProjShipDate.getString(1));
                projShipDateYearList.add(rsForGetProjShipDate.getString(2));
            }
            setBillingReportShipDate.setActShipMonthList(projShipDateMonthList);
            setBillingReportShipDate.setActShipYearList(projShipDateYearList);

            rsForGetProjShipDate.close();
            stToGetMonthYear.close();
            conToDB.close();
        } catch (SQLException e) {
            System.out.println("SQLException: class=BillingReportDAO function=getBillingReportActualShipDate " + e);
        }
    }
}
