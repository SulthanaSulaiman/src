/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.*;
import connection.DBconnection;
import java.sql.ResultSet;

/**
 *
 * @author Parameshwarant
 */
public class ProjectErrorCostInfoDAO {

    public void getFinalInvoicedMonthYear(ProjectErrorCostInfoVO getSetActivityReportData) {

        List invoiceMonthList = new ArrayList();
        List invoiceYearList = new ArrayList();
        List monthInvoiceValueList = new ArrayList();
        List invoiceMonthAndYearList = new ArrayList();
        String invoiceDateFrom = getSetActivityReportData.getInvoiceDateFrom();
        String invoiceDateTo = getSetActivityReportData.getInvoiceDateTo();
        try {

            DBconnection conToDB = new DBconnection();
            Connection connDB = conToDB.getSampleProperty();
            Statement stToGetProductionHours = connDB.createStatement();

            String queryToGetFinalInvoicedProj = "SELECT SUM(invoice_value), EXTRACT(month FROM DATE(invoice_date)) as InvMonth, EXTRACT(year FROM DATE(invoice_date)) as InvYear, DATE_FORMAT(invoice_date,'%M %Y') FROM invoices WHERE part_invoice_flag=0 ";
            if (!invoiceDateFrom.equals("") && !invoiceDateTo.equals("")) {
                queryToGetFinalInvoicedProj += " AND date(invoice_date) between '" + invoiceDateFrom + "' and '" + invoiceDateTo + "' ";
            }
            queryToGetFinalInvoicedProj += " GROUP BY InvYear, InvMonth";
            
            ResultSet rsToGetFinalInvoicedProj = stToGetProductionHours.executeQuery(queryToGetFinalInvoicedProj);
            while (rsToGetFinalInvoicedProj.next()) {
                monthInvoiceValueList.add(rsToGetFinalInvoicedProj.getString(1));
                invoiceMonthList.add(rsToGetFinalInvoicedProj.getString(2));
                invoiceYearList.add(rsToGetFinalInvoicedProj.getString(3));
                invoiceMonthAndYearList.add(rsToGetFinalInvoicedProj.getString(4));
            }

            getSetActivityReportData.setInvoiceMonthList(invoiceMonthList);
            getSetActivityReportData.setInvoiceValueList(monthInvoiceValueList);
            getSetActivityReportData.setInvoiceYearList(invoiceYearList);
            getSetActivityReportData.setInvoiceMonthYearList(invoiceMonthAndYearList);

            rsToGetFinalInvoicedProj.close();
            stToGetProductionHours.close();
            connDB.close();

        } catch (SQLException sqle) {
            System.out.println("SQL Exception in class=ActivityErrorInfoDAO function:getFinalInvoicedMonthYear " + sqle);
        }
    }

    public void getFinalInvoicedProjects(ProjectErrorCostInfoVO getSetActivityReportData) {

        List chennaiErrorList = new ArrayList();
        List dubuqueErrorList = new ArrayList();
        List vendorErrorList = new ArrayList();
        List invoiceDate = new ArrayList();

        String facilityId = getSetActivityReportData.getFacilityId();
        String invoiceDateSearchFlag = getSetActivityReportData.getInvoiceDateChk();
        String invoiceDateFrom = getSetActivityReportData.getInvoiceDateFrom();
        String invoiceDateTo = getSetActivityReportData.getInvoiceDateTo();

        try {

            DBconnection conToDB = new DBconnection();
            Connection connDB = conToDB.getSampleProperty();
            Statement stToGetProductionHours = connDB.createStatement();

            String queryToGetFinalInvoicedProjErrorData = "SELECT sum(CASE WHEN a.chennai_err_start IS NOT NULL THEN ROUND((TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time)))/3600,2)*f.fcy_cost ELSE 0 END) AS ChnErr,  "
                    + " sum(CASE WHEN a.dubuque_err_start IS NOT NULL THEN ROUND((TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time)))/3600,2)*f.fcy_cost ELSE 0 END) AS DbqErr, "
                    + " sum(CASE WHEN a.outside_err_start IS NOT NULL THEN ROUND((TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time)))/3600,2)*f.fcy_cost ELSE 0 END) AS OutErr, DATE_FORMAT(i.invoice_date,'%M-%Y') "
                    + " FROM activity a LEFT JOIN USER u using (emp_id), activity_type act,chapter c, facility f, projects p, invoices i "
                    + " WHERE a.activity_code = act.activity_code  AND a.chapter_id=c.chapter_id  and p.facility_id=f.facility_id and c.proj_id=p.proj_id "
                    + " and i.part_invoice_flag=0 and i.proj_id=p.proj_id and (a.chennai_err_start is not null or a.dubuque_err_start is not null or a.outside_err_start is not null) ";

            if (!facilityId.equals("")) {
                queryToGetFinalInvoicedProjErrorData += " AND p.facility_id='" + facilityId + "' ";
            }
            if (invoiceDateSearchFlag.equals("1")) {
                queryToGetFinalInvoicedProjErrorData += " AND date(i.invoice_date) between '" + invoiceDateFrom + "' and '" + invoiceDateTo + "' ";
            }

            queryToGetFinalInvoicedProjErrorData += " group by DATE_FORMAT(i.invoice_date,'%M-%Y') order by i.invoice_date";
            ResultSet rsToGetFinalInvoicedProjErrorData = stToGetProductionHours.executeQuery(queryToGetFinalInvoicedProjErrorData);

            while (rsToGetFinalInvoicedProjErrorData.next()) {
                chennaiErrorList.add(rsToGetFinalInvoicedProjErrorData.getString(1));
                dubuqueErrorList.add(rsToGetFinalInvoicedProjErrorData.getString(2));
                vendorErrorList.add(rsToGetFinalInvoicedProjErrorData.getString(3));
                invoiceDate.add(rsToGetFinalInvoicedProjErrorData.getString(4));
            }


            getSetActivityReportData.setChnErrorList(chennaiErrorList);
            getSetActivityReportData.setDbqErrorList(dubuqueErrorList);
            getSetActivityReportData.setVendorErrorList(vendorErrorList);
            getSetActivityReportData.setInvoiceDateList(invoiceDate);

            rsToGetFinalInvoicedProjErrorData.close();
            stToGetProductionHours.close();
            connDB.close();

        } catch (SQLException sqle) {
            System.out.println("SQL Exception in App:pathfinderv2 class=ActivityErrorInfoDAO function:getFinalInvoicedProjects " + sqle);
        }
    }
}
