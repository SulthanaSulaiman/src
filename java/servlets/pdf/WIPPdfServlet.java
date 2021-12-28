/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.contacts.ProjAuthorInfo;
import pathfinder.functions.revenue.EstimateLineItems;
import pathfinder.functions.revenue.EstimationInfo;
import pathfinder.functions.revenue.InvoiceSummaryInfo;
import pathfinder.functions.revenue.InvoiceLine;
import pathfinder.functions.revenue.*;
import pathfinder.functions.projects.*;

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 *
 * @author Yogananthani
 */
public class WIPPdfServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String stylestr = "<style> *{  font-family: Helvetica; font-size: 11px;}</style>";
        out.println(stylestr);
        //Variable for WIP invoice

        String toDate = request.getParameter("shipped_to") == null ? "" : request.getParameter("shipped_to");
        String facilityID = request.getParameter("facility_id") == null ? "" : request.getParameter("facility_id");
        List invoice_number_list = new ArrayList();
        List part_no_list = new ArrayList();
        List invoice_date_list = new ArrayList();
        List invoice_value_list = new ArrayList();
        List proj_id_list = new ArrayList();
        List wip_proj_id_list = new ArrayList();
        List invoice_flag_list = new ArrayList();
        List dispProjDetailList = new ArrayList();
        List poActivity = new ArrayList();
        List poTotal = new ArrayList();
        List catTotal = new ArrayList();
        List catName = new ArrayList();
        List catId = new ArrayList();
        List fpcatTotal = new ArrayList();
        List fpcatName = new ArrayList();
        List fpcatId = new ArrayList();
        List altcatTotal = new ArrayList();
        List altcatName = new ArrayList();
        List altcatId = new ArrayList();
        List pocatTotal = new ArrayList();
        List pocatName = new ArrayList();
        List pocatId = new ArrayList();
        List authorId = new ArrayList();
        List authorName = new ArrayList();
        List epaceId = new ArrayList();
        Double fpcost;
        Double rpcost;
        Double debuerrcost;
        Double chennaierrcost;
        Double outside;
        Double total;
        Double wipTotal;
        Double wipCostDev = 0.0;
        Double wipCostCreative = 0.0;
        Double wipCostCom = 0.0;
        Double wipCostOther = 0.0;
        Double wipCostEd = 0.0;
        Double wipCostPostage = 0.0;
        Double wipCostTotal = 0.0;
        Double wippartialInvoice = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        DateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy");
        Date estimateDate;
        Date migrateDate;
    /*       try {
         migrateDate = dateformat.parse("04-01-2013");
            //Getting Project ID's based on the To Date

            pathfinder.functions.revenue.InvoiceLineItem WIPInvoice = new pathfinder.functions.revenue.InvoiceLineItem();
            WIPInvoice.setToDate(toDate);
            WIPInvoice.setBillingLocation(facilityID);
//            WIPInvoice.getWIPProjList();
            wip_proj_id_list = WIPInvoice.getWip_proj_id_list();
            epaceId = WIPInvoice.getEpaceId();

            for (int j = 0; j < wip_proj_id_list.size(); j++) {

                //Getting project Details
                String plannerFacilityId;
                pathfinder.functions.projects.ProjIdGeneralInfo pia = new pathfinder.functions.projects.ProjIdGeneralInfo();
                pia.setProjId((String) wip_proj_id_list.get(j));
                pia.projectDetails();
                plannerFacilityId = pia.getPlannerFacilityId();
                authorId = pia.getAuthorId();
                authorName = pia.getAuthorFirstName();

                //Getting PO Activity Details and Total

                PODetailsVO po_vo = new pathfinder.functions.revenue.PODetailsVO();
                PODetailsDAO po_dao = new pathfinder.functions.revenue.PODetailsDAO();
                po_vo.setWip_proj_id((String) wip_proj_id_list.get(j));
//                po_vo = po_dao.getPoofWIP(po_vo);
                poActivity = po_vo.getActivityName();
                poTotal = po_vo.getTotal();

     //           po_vo = po_dao.getPoCategoryWIP(po_vo);
                pocatId.clear();
                pocatName.clear();
                pocatTotal.clear();
                pocatId = po_vo.getCategoryId();
                pocatName = po_vo.getCategoryName();
                pocatTotal = po_vo.getCategoryTotal();

                //Getting JCO Details and Values

                JobCostOverViewVO job_cost_over_view_VO = new pathfinder.functions.revenue.JobCostOverViewVO();
                JobCostOverViewDAO job_cost_over_view_DAO = new pathfinder.functions.revenue.JobCostOverViewDAO();
                job_cost_over_view_VO.setProjId((String) wip_proj_id_list.get(j));
                job_cost_over_view_VO.setToDate(toDate);
                job_cost_over_view_VO.setFacilityID(facilityID);
//                dispProjDetailList = job_cost_over_view_DAO.getProjWIPJCOList(job_cost_over_view_VO);

                fpcatId.clear();
                fpcatName.clear();
                fpcatTotal.clear();
                //Getting FPP value based on the category
        //        job_cost_over_view_VO = job_cost_over_view_DAO.getFPPError(job_cost_over_view_VO);
                fpcatId = job_cost_over_view_VO.getCategoryId();
                fpcatName = job_cost_over_view_VO.getCategoryName();
                fpcatTotal = job_cost_over_view_VO.getCategoryValue();

                //Getting Altraion value based on the category
                altcatId.clear();
                altcatName.clear();
                altcatTotal.clear();
                job_cost_over_view_VO = job_cost_over_view_DAO.getAltraion(job_cost_over_view_VO);
                altcatId = job_cost_over_view_VO.getCategoryId();
                altcatName = job_cost_over_view_VO.getCategoryName();
                altcatTotal = job_cost_over_view_VO.getCategoryValue();


                //Getting Estimate Details and Values
                pathfinder.functions.revenue.EstimationInfo det = new pathfinder.functions.revenue.EstimationInfo();
                det.setProjId((String) wip_proj_id_list.get(j));
                det.getEstDetails();

                //Getting Estimate Details based on the category
                catTotal.clear();
                catName.clear();
                catId.clear();
                det.getEstCatgoryDetails();
                catTotal = det.getCat_total();
                catName = det.getCat_name();
                catId = det.getCat_id();

                //Getting Invoice Details 
                invoice_number_list.clear();
                invoice_date_list.clear();
                invoice_flag_list.clear();
                invoice_value_list.clear();
                part_no_list.clear();
                proj_id_list.clear();
                WIPInvoice = new pathfinder.functions.revenue.InvoiceLineItem();
                WIPInvoice.setProjID((String) wip_proj_id_list.get(j));
                WIPInvoice.setToDate(toDate);
                WIPInvoice.getInvoiceDetails();
                invoice_number_list = WIPInvoice.getInvoice_number_list();
                invoice_date_list = WIPInvoice.getInvoice_date_list();
                invoice_flag_list = WIPInvoice.getInvoice_flag_list();
                invoice_value_list = WIPInvoice.getInvoice_value_list();
                part_no_list = WIPInvoice.getPart_no_list();
                proj_id_list = WIPInvoice.getProj_id_list();

                Double totalCostDev = 0.0;
                Double totalCostCreative = 0.0;
                Double totalCostCom = 0.0;
                Double totalCostOther = 0.0;
                Double totalCostEd = 0.0;
                Double totalCostPostage = 0.0;
                Double wipcategoryTotal = 0.0;
                Double partialInvoice = 0.0;
                
                estimateDate = dateformat.parse(det.getEst_date()); 
                String dispProjId = "";
                if(estimateDate.compareTo(migrateDate)>0){
                    dispProjId = (String) wip_proj_id_list.get(j);
                } else {
                    dispProjId = (String) epaceId.get(j);
                }
                    
                out.print("<table><tr><td style=\" width: 700px;\"><b>Job - " + dispProjId + " - ");
                for(int index=0;index<authorId.size();index++){
                    if(index>0)
                        out.print("/ ");
                    out.print(authorName.get(index)+" ");
                }
                out.print("- "+pia.getTitle() + "</b><br><b>Estimate / Invoices to Customer</b></td><td style=\" width: 100px; text-align: right;\"><b>Postage : </b></td></tr></table>");
                out.print("<font style=\" text-align:  right;\"></font>");
                out.print("<div style=\"border:1px solid black; width: 1000px; height: auto;\"><table><tr>");

                //To Showing Estimate Details
                out.print("<td style=\" width: 200px\">Estimate : $ " + ("0".equals(df.format(Double.parseDouble(det.getEst_value()))) ? "" : df.format(Double.parseDouble(det.getEst_value()))) + "<br>Estimate# : " + det.getEst_number() + "<br>Estimate Date : " + det.getEst_date() + "</td>");

                for (int partNo = 1; partNo < 5; partNo++) {
                    out.print("<td style=\" width: 200px\">Partial " + partNo + " : ");
                    if (part_no_list.contains(partNo + "")) {
                        partialInvoice += Double.parseDouble(invoice_value_list.get(part_no_list.indexOf(partNo + "")).toString());
                        out.print("$ " + invoice_value_list.get(part_no_list.indexOf(partNo + "")));
                    }
                    out.print("<br>Invoice# : ");
                    if (part_no_list.contains(partNo + "")) {
                        out.print(invoice_number_list.get(part_no_list.indexOf(partNo + "")));
                    }
                    out.print("<br>Invoice Date : ");
                    if (part_no_list.contains(partNo + "")) {
                        out.print(invoice_date_list.get(part_no_list.indexOf(partNo + "")));
                    }
                    out.print("</td>");
                }
                out.print("<td style=\" width: 200px\">Final : ");
                if (invoice_flag_list.contains("0")) {
                    out.print("$ " + invoice_value_list.get(invoice_flag_list.indexOf("0")));
                }
                out.print("<br>Invoice# : ");
                if (invoice_flag_list.contains("0")) {
                    out.print(invoice_number_list.get(invoice_flag_list.indexOf("0")));
                }
                out.print("<br>Invoice Date : ");
                if (invoice_flag_list.contains("0")) {
                    out.print(invoice_date_list.get(invoice_flag_list.indexOf("0")));
                }
                out.print("</td></tr>");
                double categoryTotal = 0;
                out.print("<tr><td></td><td><b>Development</b></td><td><b>Ed. Services</b></td><td><b>Creative Srvs</b></td><td><b>Composition</b></td><td><b>Total</b></td></tr>");
                out.print("<tr><td></td>");
                for (int index = 1; index < 5; index++) {
                    out.print("<td>");
                    if (catId.contains(index + "")) {
                        categoryTotal = Double.parseDouble(catTotal.get(catId.indexOf(index + "")).toString());
                        out.print(("0".equals(df.format(Double.parseDouble(catTotal.get(catId.indexOf(index + "")).toString()))) ? "" : "$ " + df.format(Double.parseDouble(catTotal.get(catId.indexOf(index + "")).toString()))));
                    }
                    out.print("</td>");
                }
                out.print("<td>" + ("0".equals(df.format(categoryTotal)) ? "" : "$ " + df.format(categoryTotal)) + "</td></tr></table></div><br>");

                //To Showing PO Details
                fpcost = 0.0;
                rpcost = 0.0;
                debuerrcost = 0.0;
                chennaierrcost = 0.0;
                outside = 0.0;
                total = 0.0;
                wipTotal = 0.0;
                String temp;
                out.print("<div style=\"border:1px solid black; width: 1000px; height: auto;\"><table><tr><td style=\" width: 200px\"><b>WIP Categories</b></td><td colspan=\"1\"  style=\" width: 100px\"><b>FPP</b></td><td colspan=\"1\"  style=\" width: 100px\"><b>Alterations</b></td><td style=\" width: 100px\" colspan=\"1\"><b>Dubuque Errors</b></td><td style=\" width: 100px\" colspan=\"1\"><b>Chennai Errors</b></td><td style=\" width: 100px\" colspan=\"1\"><b>Outside Errors</b></td><td style=\" width: 100px\"><b>Total</b></td><td style=\" width: 100px\"><b>WIP Value</b></td></tr>");
                for (int index = 0; index < poTotal.size(); index++) {
                    fpcost += Double.parseDouble(poTotal.get(index).toString());
                    total += Double.parseDouble(poTotal.get(index).toString());
                    if (plannerFacilityId.equals(facilityID)) {
                        wipTotal += Double.parseDouble(poTotal.get(index).toString());
                    }
                    out.print("<tr><td>" + poActivity.get(index) + "</td><td>$ " + poTotal.get(index) + "</td><td></td><td></td><td></td><td></td><td>$ " + poTotal.get(index) + "</td>");
                    if (plannerFacilityId.equals(facilityID)) {
                        out.print("<td>$ " + poTotal.get(index) + "</td>");
                    }
                    out.print("</tr>");
                }

                //To Showing JCO Details
                Iterator dispProjWIPItr = dispProjDetailList.iterator();
                while (dispProjWIPItr.hasNext()) {
                    job_cost_over_view_VO = (pathfinder.functions.revenue.JobCostOverViewVO) dispProjWIPItr.next();
                    out.print("<tr><td>" + job_cost_over_view_VO.getMilestone() + "</td>");
                    fpcost += Double.parseDouble(job_cost_over_view_VO.getFPBC().toString());
                    temp = Double.parseDouble(job_cost_over_view_VO.getFPBC().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getFPBC().toString()));
                    out.print("<td>" + temp + "</td>");
                    rpcost += Double.parseDouble(job_cost_over_view_VO.getRPBC().toString());
                    temp = Double.parseDouble(job_cost_over_view_VO.getRPBC().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getRPBC().toString()));
                    out.print("<td>" + temp + "</td>");
                    debuerrcost += Double.parseDouble(job_cost_over_view_VO.getDBQERRCT().toString());
                    temp = Double.parseDouble(job_cost_over_view_VO.getDBQERRCT().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getDBQERRCT().toString()));
                    out.print("<td>" + temp + "</td>");
                    chennaierrcost += Double.parseDouble(job_cost_over_view_VO.getCHNERRCT().toString());
                    temp = Double.parseDouble(job_cost_over_view_VO.getCHNERRCT().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getCHNERRCT().toString()));
                    out.print("<td>" + temp + "</td>");
                    outside += Double.parseDouble(job_cost_over_view_VO.getOUTERRCT().toString());
                    temp = Double.parseDouble(job_cost_over_view_VO.getOUTERRCT().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getOUTERRCT().toString()));
                    out.print("<td>" + temp + "</td>");
                    total += Double.parseDouble(job_cost_over_view_VO.getTOTBC().toString());
                    temp = Double.parseDouble(job_cost_over_view_VO.getTOTBC().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getTOTBC().toString()));
                    out.print("<td>" + temp + "</td>");
                    wipTotal += Double.parseDouble(job_cost_over_view_VO.getWipValueList().toString());
                    temp = Double.parseDouble(job_cost_over_view_VO.getWipValueList().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getWipValueList().toString()));
                    out.print("<td>" + temp + "</td></tr>");
                }
                out.print("<tr><td><b>Subtotal</b></td><td>" + (fpcost == 0.0 ? "" : "$ " + df.format(fpcost)) + "</td><td>" + (rpcost == 0.0 ? "" : "$ " + df.format(rpcost)) + "</td><td>" + (debuerrcost == 0.0 ? "" : "$ " + df.format(debuerrcost)) + "</td><td>" + (chennaierrcost == 0.0 ? "" : "$ " + df.format(chennaierrcost)) + "</td><td>" + (outside == 0.0 ? "" : "$ " + df.format(outside)) + "</td><td>" + (total == 0.0 ? "" : "$ " + df.format(total)) + "</td><td>" + (wipTotal == 0.0 ? "" : "$ " + df.format(wipTotal)) + "</td></tr>");
                out.print("</table></div><br><br>");
                out.print("<div style=\"border:1px solid black; width: 1000px; height: auto;\"><table>");
                out.print("<tr><td><b>Cost</b></td></tr>");
                out.print("<tr><td style=\" width: 150px\"></td><td style=\" width: 100px\"><b>Development</b></td><td style=\" width: 100px\"><b>Ed. Services</b></td><td style=\" width: 100px\"><b>Creative Srvs</b></td><td style=\" width: 100px\"><b>Composition</b></td><td style=\" width: 100px\"><b>Other</b></td><td style=\" width: 100px\"><b>Postage</b></td><td style=\" width: 100px\"><b>Sub-Total</b></td></tr>");
                out.print("<tr><td><b>FPP</b></td>");
                categoryTotal = 0;
                for (int index = 1; index < 7; index++) {
                    out.print("<td>");
                    if (fpcatId.contains(index + "")) {
                        if (index == 1) {
                            totalCostDev += Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(index + "")).toString());
                        }
                        if (index == 2) {
                            totalCostEd += Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(index + "")).toString());
                        }
                        if (index == 3) {
                            totalCostCreative += Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(index + "")).toString());
                        }
                        if (index == 4) {
                            totalCostCom += Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(index + "")).toString());
                        }
                        if (index == 5) {
                            totalCostOther += Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(index + "")).toString());
                        }
                        if (index == 6) {
                            totalCostPostage += Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(index + "")).toString());
                        }
                        categoryTotal += Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(index + "")).toString());
                        out.print(("0".equals(df.format(Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(index + "")).toString()))) ? "" : "$ " + df.format(Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(index + "")).toString()))));
                    }
                    out.print("</td>");
                }
                wipcategoryTotal += categoryTotal;
                out.print("<td>" + ("0".equals(df.format(categoryTotal)) ? "" : "$ " + df.format(categoryTotal)) + "</td></tr>");
                out.print("<tr><td><b>Alterations</b></td>");
                categoryTotal = 0;
                for (int index = 1; index < 7; index++) {
                    out.print("<td>");
                    if (altcatId.contains(index + "")) {
                        if (index == 1) {
                            totalCostDev += Double.parseDouble(altcatTotal.get(altcatId.indexOf(index + "")).toString());
                        }
                        if (index == 2) {
                            totalCostEd += Double.parseDouble(altcatTotal.get(altcatId.indexOf(index + "")).toString());
                        }
                        if (index == 3) {
                            totalCostCreative += Double.parseDouble(altcatTotal.get(altcatId.indexOf(index + "")).toString());
                        }
                        if (index == 4) {
                            totalCostCom += Double.parseDouble(altcatTotal.get(altcatId.indexOf(index + "")).toString());
                        }
                        if (index == 5) {
                            totalCostOther += Double.parseDouble(altcatTotal.get(altcatId.indexOf(index + "")).toString());
                        }
                        if (index == 6) {
                            totalCostPostage += Double.parseDouble(altcatTotal.get(altcatId.indexOf(index + "")).toString());
                        }
                        categoryTotal += Double.parseDouble(altcatTotal.get(altcatId.indexOf(index + "")).toString());
                        out.print(("0".equals(df.format(Double.parseDouble(altcatTotal.get(altcatId.indexOf(index + "")).toString()))) ? "" : "$ " + df.format(Double.parseDouble(altcatTotal.get(altcatId.indexOf(index + "")).toString()))));
                    }
                    out.print("</td>");
                }
                wipcategoryTotal += categoryTotal;
                out.print("<td>" + ("0".equals(df.format(categoryTotal <= 0 ? 0 : categoryTotal)) ? "" : "$ " + df.format(categoryTotal)) + "</td></tr>");
                out.print("<tr><td><b>Purchase Order</b></td>");
                categoryTotal = 0;
                if (plannerFacilityId.equals(facilityID)) {
                    for (int index = 1; index < 7; index++) {
                        out.print("<td>");
                        if (pocatId.contains(index + "")) {
                            if (index == 1) {
                                totalCostDev += Double.parseDouble(pocatTotal.get(pocatId.indexOf(index + "")).toString());
                            }
                            if (index == 2) {
                                totalCostEd += Double.parseDouble(pocatTotal.get(pocatId.indexOf(index + "")).toString());
                            }
                            if (index == 3) {
                                totalCostCreative += Double.parseDouble(pocatTotal.get(pocatId.indexOf(index + "")).toString());
                            }
                            if (index == 4) {
                                totalCostCom += Double.parseDouble(pocatTotal.get(pocatId.indexOf(index + "")).toString());
                            }
                            if (index == 5) {
                                totalCostOther += Double.parseDouble(pocatTotal.get(pocatId.indexOf(index + "")).toString());
                            }
                            if (index == 6) {
                                totalCostPostage += Double.parseDouble(pocatTotal.get(pocatId.indexOf(index + "")).toString());
                            }
                            categoryTotal += Double.parseDouble(pocatTotal.get(pocatId.indexOf(index + "")).toString());
                            out.print(("0".equals(df.format(Double.parseDouble(pocatTotal.get(pocatId.indexOf(index + "")).toString()))) ? "" : "$ " + df.format(Double.parseDouble(pocatTotal.get(pocatId.indexOf(index + "")).toString()))));
                        }
                        out.print("</td>");
                    }
                    wipcategoryTotal += categoryTotal;
                    out.print("<td>" + ("0".equals(df.format(categoryTotal <= 0 ? 0 : categoryTotal)) ? "" : "$ " + df.format(categoryTotal)) + "</td>");
                }
                out.print("</tr><tr><td></td><td></td><td></td><td></td><td></td><td></td><td><b>Total</b></td><td><b>" + ("0".equals(df.format(wipcategoryTotal <= 0 ? 0 : wipcategoryTotal)) ? "" : "$ " + df.format(wipcategoryTotal)) + "</b></td></tr>");
                out.print("</table></div><br><br>");
                out.print("<div style=\"border:1px solid black; width: 1000px; height: auto;\"><table>");
                out.print("<tr><td><b>WIP</b></td></tr>");
                out.print("<tr><td colspan=\"8\" style=\" text-align: center;\"><b>********** (FPP + Alterations + Outside Costs) - Partial Invoices **********</b></td></tr>");
                wipcategoryTotal -= partialInvoice;
                out.print("<tr><td></td><td style=\" width: 100px\"><b>Development</b></td><td style=\" width: 200px\"><b>Ed. Services</b></td><td style=\" width: 100px\"><b>Creative Srvs</b></td><td style=\" width: 100px\"><b>Composition</b></td><td style=\" width: 100px\"><b>Other</b></td><td><b>Mi</b></td><td style=\" width: 100px\"><b>Partial Invoices</b></td><td style=\" width: 100px\"><b>WIP Value</b></td></tr>");
                out.print("<tr><td></td><td>" + ("0.0".equals(df.format(totalCostDev <= 0 ? 0 : totalCostDev)) ? "" : "$ " + df.format(totalCostDev)) + "</td><td>" + ("0.0".equals(df.format(totalCostEd <= 0 ? 0 : totalCostEd)) ? "" : "$ " + df.format(totalCostEd)) + "</td><td>" + ("0.0".equals(df.format(totalCostCreative <= 0 ? 0 : totalCostCreative)) ? "" : "$ " + df.format(totalCostCreative)) + "</td><td>" + ("0.0".equals(df.format(totalCostCom <= 0 ? 0 : totalCostCom)) ? "" : "$ " + df.format(totalCostCom)) + "</td><td>" + ("0.0".equals(df.format(totalCostOther <= 0 ? 0 : totalCostOther)) ? "" : "$ " + df.format(totalCostOther)) + "</td><td></td><td>" + ("0.0".equals(df.format(partialInvoice <= 0 ? 0 : partialInvoice)) ? "" : "$ " + df.format(partialInvoice)) + "</td><td>" + ("0.0".equals(df.format(wipcategoryTotal <= 0 ? 0 : wipcategoryTotal)) ? "" : "$ " + df.format(wipcategoryTotal)) + "</td></tr>");
                wipCostDev += totalCostDev;
                wipCostEd += totalCostEd;
                wipCostCreative += totalCostCreative;
                wipCostCom += totalCostCom;
                wipCostOther += totalCostOther;
                wippartialInvoice += partialInvoice;
                wipCostTotal += wipcategoryTotal;
                out.print("</table></div>");
                out.print("<br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
            }
            out.print("<hr><br><br><b>WIP as of  " + toDate + "</b><br><div style=\"border:1px solid black; width: 1000px; height: auto;\" ><table>");
            out.print("<tr><td></td><td style=\" width: 100px\"><b>Development</b></td><td style=\" width: 100px\"><b>Ed. Services</b></td><td style=\" width: 100px\"><b>Creative Srvs</b></td><td style=\" width: 100px\"><b>Composition</b></td><td style=\" width: 100px\"><b>Other</b></td><td style=\" width: 100px\"><b>Mi</b></td><td style=\" width: 100px\"><b>Partial Invoices</b></td><td style=\" width: 100px\"><b>WIP Value</b></td></tr>");
            out.print("<tr><td></td><td>" + ("0.0".equals(df.format(wipCostDev <= 0 ? 0 : wipCostDev)) ? "" : "$ " + df.format(wipCostDev)) + "</td><td>" + ("0.0".equals(df.format(wipCostEd <= 0 ? 0 : wipCostEd)) ? "" : "$ " + df.format(wipCostEd)) + "</td><td>" + ("0.0".equals(df.format(wipCostCreative <= 0 ? 0 : wipCostCreative)) ? "" : "$ " + df.format(wipCostCreative)) + "</td><td>" + ("0.0".equals(df.format(wipCostCom <= 0 ? 0 : wipCostCom)) ? "" : "$ " + df.format(wipCostCom)) + "</td><td>" + ("0.0".equals(df.format(wipCostOther <= 0 ? 0 : wipCostOther)) ? "" : "$ " + df.format(wipCostOther)) + "</td><td></td><td>" + ("0.0".equals(df.format(wippartialInvoice <= 0 ? 0 : wippartialInvoice)) ? "" : "$ " + df.format(wippartialInvoice)) + "</td><td>" + ("0.0".equals(df.format(wipCostTotal <= 0 ? 0 : wipCostTotal)) ? "" : "$ " + df.format(wipCostTotal)) + "</td></tr>");
            out.print("</table></div>");
        } catch (Exception e) {
            System.out.println("Exception on WIP PDF Servelt : " + e.getStackTrace());
            e.printStackTrace();
        } finally {
            out.close();
        }*/
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
