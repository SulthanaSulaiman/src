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
public class ShippedPrinterFilesDAO {

    String queryToGetShipdProject = "";
    String queryToChekWeekEndDays = "";
    String queryToGetOnTimeShipdCount = "";
    String estShipDateChk = "";
    String estShipDateFrom = "";
    String estShipDateTo = "";
    String facilityID = "";
    String estShipMonthYear = "";
    List projNameList = new ArrayList();
    List customerNameList = new ArrayList();
    List plannerNameList = new ArrayList();
    List estShipDateList = new ArrayList();
    List actShipDateList = new ArrayList();
    List actShipDateListChk = new ArrayList();
    List actAndEstShipDateDayDiffList = new ArrayList();
    List dayDiffExceptWeekEndsList = new ArrayList();
    List estShipMonthYearList = new ArrayList();
    List allProjShippedPrinterFiles = new ArrayList();
    ShippedPrinterFilesVO setShippedPrinterFiles = new ShippedPrinterFilesVO();

    public void getShippedPrinterFiles(ShippedPrinterFilesVO setShippedPrinterFiles) throws ParseException {


        projNameList.clear();
        customerNameList.clear();
        plannerNameList.clear();
        estShipDateList.clear();
        actShipDateList.clear();
        actShipDateListChk.clear();
        actAndEstShipDateDayDiffList.clear();
        dayDiffExceptWeekEndsList.clear();
        estShipMonthYearList.clear();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String currDate = sdf.format(today);
        String actShipDateChk = "";
        String fromServletPdfChk = "";
        String estShipMonth = "";
        String estShipYear = "";
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

        try {

            DBconnection con = new DBconnection();
            Connection conToDB = con.getSampleProperty();
            Statement stToGetShipdProject = conToDB.createStatement();

            estShipDateChk = setShippedPrinterFiles.getEstShipDateChk();
            facilityID = setShippedPrinterFiles.getFacilityId();
            fromServletPdfChk = setShippedPrinterFiles.getFromServletPdf();

            queryToGetShipdProject = "select concat(a.proj_id,' / ',a.proj_name), b.company, ifnull(e.emp_name,'N/A') AS Planner, a.projected_printer_date, "
                    + " IFNULL(a.act_ship_date, 'N/A') AS ActShipDate, "
                    + " CASE WHEN a.act_ship_date IS NOT NULL THEN DATEDIFF(a.act_ship_date,a.projected_printer_date) ELSE DATEDIFF(CURRENT_DATE,a.projected_printer_date) END AS DayDiff, "
                    + " ifnull(DATE_FORMAT(a.projected_printer_date,'%M'),'N/A') as EstShipDateMonth, ifnull(DATE_FORMAT(a.projected_printer_date,'%Y'),'N/A') as EstShipDateYear "
                    + " from projects a LEFT JOIN USER e ON a.planner=e.emp_id, contacts b, department c, status d "
                    + " where a.projected_printer_date is not null and a.dept_code=c.dept_code "
                    + " and a.client_name=b.contact_id AND a.project_status NOT IN (21,2) and a.project_status=d.status_id AND a.client_name IS NOT NULL ";

            if (!estShipDateChk.equals("")) {
                estShipDateFrom = setShippedPrinterFiles.getEstShipDateFrom();
                estShipDateTo = setShippedPrinterFiles.getEstShipDateTo();
                //System.out.println("estShipDateFrom"+estShipDateFrom);
                //System.out.println("estShipDateTo"+estShipDateTo);
                //queryToGetShipdProject += " AND a.projected_printer_date BETWEEN '" + estShipDateFrom + "' AND '" + estShipDateTo + "' ";
                queryToGetShipdProject += " AND (a.projected_printer_date >= '" + estShipDateFrom + "' AND a.projected_printer_date <='" + estShipDateTo + "') ";
            }
            if (!facilityID.equals("")) {
                queryToGetShipdProject += " AND a.facility_id = '" + facilityID + "' ";
            }
            if (fromServletPdfChk.equals("PrinterFilesOnTimeReportPdfservlet")) {

                queryToGetShipdProject += " ORDER BY EstShipDateYear, DayDiff DESC, a.projected_printer_date ";
            } else {
                queryToGetShipdProject += " ORDER BY DayDiff DESC, a.projected_printer_date ";
            }

            System.out.println("queryToGetShipdProject: " + queryToGetShipdProject);
            ResultSet rsToGetShipdProject = stToGetShipdProject.executeQuery(queryToGetShipdProject);
            if (!rsToGetShipdProject.wasNull()) {
                while (rsToGetShipdProject.next()) {
                    projNameList.add(rsToGetShipdProject.getString(1));
                    customerNameList.add(splChar.decoding(rsToGetShipdProject.getString(2)));
                    plannerNameList.add(rsToGetShipdProject.getString(3));
                    estShipDateList.add(rsToGetShipdProject.getString(4));
                    actShipDateChk = rsToGetShipdProject.getString(5);
System.out.println(rsToGetShipdProject.getString(4));
                    if (actShipDateChk.equals("N/A")) {
                        actShipDateListChk.add(currDate);
                    } else {
                        actShipDateListChk.add(rsToGetShipdProject.getString(5));
                    }
                    actShipDateList.add(rsToGetShipdProject.getString(5));
                    actAndEstShipDateDayDiffList.add(rsToGetShipdProject.getString(6));
                    estShipMonthYear = rsToGetShipdProject.getString(7);
                    estShipMonthYear = estShipMonthYear.concat(" " + rsToGetShipdProject.getString(8));
                    estShipMonthYearList.add(estShipMonthYear);
                }
            }


            setShippedPrinterFiles.setProjNameList(projNameList);
            setShippedPrinterFiles.setCustomerNameList(customerNameList);
            setShippedPrinterFiles.setPlannerNameList(plannerNameList);
            setShippedPrinterFiles.setEstShipDateList(estShipDateList);
            setShippedPrinterFiles.setActShipDateList(actShipDateList);
            setShippedPrinterFiles.setEstShipMonthYearList(estShipMonthYearList);


            int size = projNameList.size();
            for (int i = 0; i < size; i++) {

                int weekEndCount = 0;
                int actAndEstShipDayDiff = Integer.parseInt(actAndEstShipDateDayDiffList.get(i).toString());
                String estShipDate = estShipDateList.get(i).toString();
                if (actAndEstShipDayDiff > 0) {
                    for (int j = 1; j <= actAndEstShipDayDiff; j++) {
                        Calendar weekEndCheckCalendar = Calendar.getInstance();
                        weekEndCheckCalendar.setTime(sdf.parse(estShipDate));
                        weekEndCheckCalendar.add(Calendar.DATE, j);
                        int dayOfWeek = weekEndCheckCalendar.get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek == 7 || dayOfWeek == 1) {
                            weekEndCount++;
                        }
                    }
                    dayDiffExceptWeekEndsList.add((actAndEstShipDayDiff - weekEndCount));

                } else if (actAndEstShipDayDiff == 0) {
                    dayDiffExceptWeekEndsList.add(0);
                } else if (actAndEstShipDayDiff < 0) {
                    dayDiffExceptWeekEndsList.add(actAndEstShipDayDiff);
                }

            }

            setShippedPrinterFiles.setDayDiffExceptWeekEndsList(dayDiffExceptWeekEndsList);

            rsToGetShipdProject.close();
            stToGetShipdProject.close();
            conToDB.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException: class=ShippedPrinterFilesDAO function=getShippedPrinterFiles " + sqle);
        }
    }

    public void getProjectedShipDate(ShippedPrinterFilesVO setShippedPrinterFile) {
        String queryForGetProjShipDate = "";
        List projShipDateMonthList = new ArrayList();
        List projShipDateYearList = new ArrayList();

        try {
            DBconnection conn = new DBconnection();
            Connection conToDB = conn.getSampleProperty();
            Statement stToGetMonthYear = conToDB.createStatement();

            queryForGetProjShipDate = "SELECT DISTINCT EXTRACT(MONTH FROM a.projected_printer_date) AS MONTH, EXTRACT(YEAR FROM a.projected_printer_date) AS CurrentYear "
                    + " FROM projects a LEFT JOIN USER e ON a.planner=e.emp_id, contacts b, department c "
                    + " WHERE a.projected_printer_date IS NOT NULL AND a.dept_code=c.dept_code AND a.client_name=b.contact_id AND a.client_name IS NOT NULL ORDER BY a.projected_printer_date ";

            ResultSet rsForGetProjShipDate = stToGetMonthYear.executeQuery(queryForGetProjShipDate);


            while (rsForGetProjShipDate.next()) {
                projShipDateMonthList.add(rsForGetProjShipDate.getString(1));
                projShipDateYearList.add(rsForGetProjShipDate.getString(2));
            }
            setShippedPrinterFile.setEstShipMonthList(projShipDateMonthList);
            setShippedPrinterFile.setEstShipYearList(projShipDateYearList);

            rsForGetProjShipDate.close();
            stToGetMonthYear.close();
            conToDB.close();
        } catch (SQLException e) {
            System.out.println("SQLException: class=ShippedPrinterFilesDAO function=getProjectedShipDate " + e);
        }
    }
}
