/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

/**
 *
 * @author Parameshwarant
 */
import connection.DBconnection;
import java.sql.*;
import java.util.*;

public class ProfitabilityReportDAO {

    List projNameIdList = new ArrayList();
    List projIdList = new ArrayList();
    List customerNameList = new ArrayList();
    List plannerNameList = new ArrayList();
    List hybridPlannerNameList = new ArrayList();
    List invoiceAmountList = new ArrayList();
    List invoiceMonthAndYearList = new ArrayList();
    String invoiceDateFrom = "";
    String invoiceDateTo = "";
    String plannerId = "";
    String hybridPlannerId = "";
    String facilityID = "";
    String customer = "";

    public void collectProfitabilityReportDetails(ProfitabilityReportVO setProfitabilityReportValue) {

        projNameIdList.clear();
        projIdList.clear();
        customerNameList.clear();
        plannerNameList.clear();
        hybridPlannerNameList.clear();
        invoiceAmountList.clear();
        invoiceMonthAndYearList.clear();

        int projSize = 0;

        double projProductiveValue = 0;
        double projPurchaseOrderValue = 0;
        double projProductiveActualValue = 0;

        String queryToGetProjProductiveValue = "";
        String queryToGetProjPurchaseOrderValue = "";

        String projID = "";
        String invoiceDateChk = "";
        String fromServletPdfChk = "";
        String invoiceMonth = "";
        String invoiceYear = "";

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();


        try {
            DBconnection con = new DBconnection();
            Connection conToDB = con.getSampleProperty();
            Statement stToGetProfitabilityReportValue = conToDB.createStatement();

            invoiceDateChk = setProfitabilityReportValue.getInvoiceDateChk();
            fromServletPdfChk = setProfitabilityReportValue.getFromServletPdf() != null ? setProfitabilityReportValue.getFromServletPdf() : "";
            invoiceMonth = setProfitabilityReportValue.getInvoiceMonth();
            invoiceYear = setProfitabilityReportValue.getInvoiceYear();

            customer = setProfitabilityReportValue.getCustomer();
            plannerId = setProfitabilityReportValue.getPlannerId();
            hybridPlannerId = setProfitabilityReportValue.getHybridPlannerId();
            facilityID = setProfitabilityReportValue.getFacilityId();

            String queryToGetProfitabilityReportValue = " select a.proj_id,CONCAT(a.proj_id,' / ',a.proj_name), IFNULL(c.company,'N/A'), IFNULL(e.emp_name,'N/A'), IFNULL(f.emp_name,'N/A'), sum(b.invoice_value), DATE_FORMAT(b.invoice_date,'%M %Y')"
                    + " from projects a LEFT JOIN invoices b ON b.proj_id=a.proj_id AND part_invoice_flag <> '2' LEFT JOIN USER e ON a.planner=e.emp_id LEFT JOIN USER f ON a.hybrid_planner=f.emp_id, "
                    + " contacts c WHERE a.client_name=c.contact_id AND b.invoice_value IS NOT NULL ";

            if (invoiceDateChk.equals("1")) {
                invoiceDateFrom = setProfitabilityReportValue.getInvoiceDateFrom();
                invoiceDateTo = setProfitabilityReportValue.getInvoiceDateTo();
                queryToGetProfitabilityReportValue += " AND b.invoice_date BETWEEN '" + invoiceDateFrom + "' AND '" + invoiceDateTo + "' ";
            }
            if (!plannerId.equals("")) {
                queryToGetProfitabilityReportValue += " AND a.planner = '" + plannerId + "' ";
            }
            if (!hybridPlannerId.equals("")) {
                queryToGetProfitabilityReportValue += " AND a.hybrid_planner = '" + hybridPlannerId + "' ";
            }

            if (!facilityID.equals("")) {
                queryToGetProfitabilityReportValue += " AND a.facility_id = '" + facilityID + "' ";
            }
            if (!customer.equals("")) {
                queryToGetProfitabilityReportValue += " AND a.client_name = '" + customer + "' ";
            }


            if (fromServletPdfChk.equals("ProfitabilityReportPdfservlet")) {

                invoiceMonth = setProfitabilityReportValue.getInvoiceMonth();
                invoiceYear = setProfitabilityReportValue.getInvoiceYear();

                if (invoiceMonth.equals("N/A") && invoiceYear.equals("N/A")) {
                    queryToGetProfitabilityReportValue += " AND b.invoice_date IS NULL ";
                } else {
                    queryToGetProfitabilityReportValue += " AND EXTRACT(MONTH FROM b.invoice_date) IN ('" + invoiceMonth + "') AND EXTRACT(YEAR FROM b.invoice_date) IN ('" + invoiceYear + "') ";
                }
            }
            queryToGetProfitabilityReportValue += " GROUP BY b.proj_id ORDER BY b.invoice_date DESC ";
            //System.out.println("queryToGetBillingReport: " + queryToGetProfitabilityReportValue);


            ResultSet rsToGetProfitabilityReportValue = stToGetProfitabilityReportValue.executeQuery(queryToGetProfitabilityReportValue);
            while (rsToGetProfitabilityReportValue.next()) {
                projIdList.add(rsToGetProfitabilityReportValue.getString(1));
                projNameIdList.add(rsToGetProfitabilityReportValue.getString(2));
                customerNameList.add(splChar.decoding(rsToGetProfitabilityReportValue.getString(3)));
                plannerNameList.add(rsToGetProfitabilityReportValue.getString(4));
                hybridPlannerNameList.add(rsToGetProfitabilityReportValue.getString(5));
                invoiceAmountList.add(rsToGetProfitabilityReportValue.getString(6));
                invoiceMonthAndYearList.add(rsToGetProfitabilityReportValue.getString(7));
            }
            setProfitabilityReportValue.setProjNameList(projNameIdList);
            setProfitabilityReportValue.setCustomerNameList(customerNameList);
            setProfitabilityReportValue.setPlannerNameList(plannerNameList);
            setProfitabilityReportValue.setHybridPlannerNameList(hybridPlannerNameList);
            setProfitabilityReportValue.setInvoiceAmountList(invoiceAmountList);
            setProfitabilityReportValue.setInvoiceMonthAndYearList(invoiceMonthAndYearList);

            projSize = projIdList.size();

            ResultSet rsToGetProjProductiveValue = null;
            ResultSet rsToGetProjPurchaseOrderValue = null;
            ResultSet rsToGetProjCurrentWorkValue = null;

            List projProductiveActualValueList = new ArrayList();


            projProductiveValue = 0;
            projPurchaseOrderValue = 0;
            projProductiveActualValue = 0;



            queryToGetProjProductiveValue = "SELECT IFNULL(ROUND(SUM(TIME_TO_SEC(TIMEDIFF(a.end_time,a.begin_time))/3600*f.fcy_cost),2),0) ProductiveCost"
                    + " FROM activity a, USER u, facility f, chapter c,projects p, department d, activity_type t, "
                    + " proj_milestone_act ma LEFT JOIN estimate_category ec ON (ec.category_id = ma.est_category_id) "
                    + " WHERE u.dept_code = d.dept_code AND a.chapter_id = c.chapter_id AND t.productive='1' AND a.emp_id = u.emp_id "
                    + " AND u.facility_id = f.facility_id AND c.proj_id = ? AND p.proj_id=c.proj_id AND a.end_time IS NOT NULL "
                    + " AND a.activity_code = t.activity_code AND ma.milestone_act_code=a.milestone_code AND ma.JCO_WIP_enable_flag='1' ";

            PreparedStatement pstToGetProjProductiveValue = conToDB.prepareStatement(queryToGetProjProductiveValue);

            queryToGetProjPurchaseOrderValue = "SELECT IFNULL(ROUND(SUM(pl.total),2),0) AS POValue FROM po_lineitems pl, projects p WHERE p.proj_id= ? AND p.proj_id = pl.proj_id ";

            PreparedStatement pstToGetProjPurchaseOrderValue = conToDB.prepareStatement(queryToGetProjPurchaseOrderValue);

            for (int i = 0; i < projSize; i++) {

                projID = projIdList.get(i).toString();

                pstToGetProjProductiveValue.setString(1, projID);
                rsToGetProjProductiveValue = pstToGetProjProductiveValue.executeQuery();
                while (rsToGetProjProductiveValue.next()) {
                    projProductiveValue = Double.parseDouble(rsToGetProjProductiveValue.getString(1));
                }

                pstToGetProjPurchaseOrderValue.setString(1, projID);
                rsToGetProjPurchaseOrderValue = pstToGetProjPurchaseOrderValue.executeQuery();
                while (rsToGetProjPurchaseOrderValue.next()) {
                    projPurchaseOrderValue = Double.parseDouble(rsToGetProjPurchaseOrderValue.getString(1));
                }


                projProductiveActualValue = projProductiveValue + projPurchaseOrderValue;
                projProductiveActualValueList.add(projProductiveActualValue);

            }

            setProfitabilityReportValue.setProjProductiveValuesList(projProductiveActualValueList);
            if (rsToGetProjProductiveValue != null) {
                rsToGetProjProductiveValue.close();
            }
            if (rsToGetProjPurchaseOrderValue != null) {
                rsToGetProjPurchaseOrderValue.close();
            }
            if (rsToGetProjCurrentWorkValue != null) {
                rsToGetProjCurrentWorkValue.close();
            }
            pstToGetProjProductiveValue.close();
            pstToGetProjPurchaseOrderValue.close();

            rsToGetProfitabilityReportValue.close();
            stToGetProfitabilityReportValue.close();
            conToDB.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException: class=ProfitabilityReportDAO function=collectProfitabilityReportDetails " + sqle);
        }

    }

    public void collectProfitReportInvoiceDate(ProfitabilityReportVO setProfitReportInvoiceDate) {
        String queryForGetProfitReportInvoiceDate = "";

        List projInvoiceMonthList = new ArrayList();
        List projInvoiceYearList = new ArrayList();

        try {
            DBconnection conn = new DBconnection();
            Connection conToDB = conn.getSampleProperty();
            Statement stToGetMonthYear = conToDB.createStatement();

            queryForGetProfitReportInvoiceDate = "SELECT DISTINCT EXTRACT(MONTH FROM DATE(invoice_date)) AS InvoiceMonth, EXTRACT(YEAR FROM DATE(invoice_date)) InvoiceYear "
                    + " FROM invoices where invoice_date is not null group by proj_id order by invoice_date";


            ResultSet rsForGetInvoiceDate = stToGetMonthYear.executeQuery(queryForGetProfitReportInvoiceDate);


            while (rsForGetInvoiceDate.next()) {
                projInvoiceMonthList.add(rsForGetInvoiceDate.getString(1));
                projInvoiceYearList.add(rsForGetInvoiceDate.getString(2));
            }
            setProfitReportInvoiceDate.setInvoiceMonthList(projInvoiceMonthList);
            setProfitReportInvoiceDate.setInvoiceYearList(projInvoiceYearList);

            rsForGetInvoiceDate.close();
            stToGetMonthYear.close();
            conToDB.close();
        } catch (SQLException e) {
            System.out.println("SQLException: class=ProfitabilityReportDAO function=collectProfitReportInvoiceDate " + e);
        }
    }
}
