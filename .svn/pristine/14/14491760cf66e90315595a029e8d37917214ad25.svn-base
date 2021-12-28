/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import connection.DBconnection;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.revenue.*;

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 *
 * @author Yogananthani
 */
public class WipPdf extends HttpServlet {

    //Font Style for pdf getration
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 6,
            Font.BOLD);
    private static Font smallFont = new Font(Font.FontFamily.HELVETICA, 6,
            Font.NORMAL);

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
        response.setContentType("application/pdf");
        String fileName = "WIPReport.pdf";
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);
        response.setContentType("application/pdf");

        //Variable for WIP
        
    
    DBconnection conProp = new DBconnection();
    Connection con = null;
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
    List projName = new ArrayList();
    List superCategory = new ArrayList();
    List superCategoryId = new ArrayList();
    Double postageTotal = 0.0;
    Double fpcost;
    Double rpcost;
    Double debuerrcost;
    Double chennaierrcost;
    Double outside;
    Double total;
    Double wipTotal;
    Double wipCostTotal = 0.0;
    Double wippartialInvoice = 0.0;
    Double tempValue = 0.0;
    DecimalFormat df = new DecimalFormat("0.00");
    DateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy");
    Date estimateDate;
    Date migrateDate;
    PdfPTable lineTable;
    PdfPCell lineCell;
    PdfPCell emptyCell = new PdfPCell();

    
        try {

            con = conProp.getSampleProperty();

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        migrateDate = dateformat.parse("04-01-2013");
        //Getting Project ID's based on the To Date

        pathfinder.functions.revenue.InvoiceLineItem WIPInvoice = new pathfinder.functions.revenue.InvoiceLineItem();
        WIPInvoice.setToDate(toDate);
        WIPInvoice.setBillingLocation(facilityID);
        WIPInvoice.getWIPProjList(con);
        wip_proj_id_list = WIPInvoice.getWip_proj_id_list();
        epaceId = WIPInvoice.getEpaceId();
        projName = WIPInvoice.getProjName();

        //Getting all the super category
        WIPInvoice.getSuperCategoryValues(con);
        superCategory = WIPInvoice.getSuperCategory();
        superCategoryId = WIPInvoice.getSuperCategoryId();
        double[] superCategoryFinalTotal = new double[superCategory.size()];

        for (int j = 0; j < wip_proj_id_list.size(); j++) {

            //Variable for supercategory total
            double[] superCategoryTotal = new double[superCategory.size()];

            //Getting project Details
            String plannerFacilityId;
            pathfinder.functions.projects.ProjIdGeneralInfo pia = new pathfinder.functions.projects.ProjIdGeneralInfo();
            pia.setProjId((String) wip_proj_id_list.get(j));
            pia.projectDetails(con);
            plannerFacilityId = pia.getPlannerFacilityId();
            authorId = pia.getAuthorId();
            authorName = pia.getAuthorsList();

            //Getting PO Activity Details and Total

            PODetailsVO po_vo = new pathfinder.functions.revenue.PODetailsVO();
            PODetailsDAO po_dao = new pathfinder.functions.revenue.PODetailsDAO();
            po_vo.setWip_proj_id((String) wip_proj_id_list.get(j));
            po_vo = po_dao.getPoofWIP(po_vo, con);
            poActivity = po_vo.getActivityName();
            poTotal = po_vo.getTotal();

            //Getting PO Activity Details and Total based on category

            po_vo = po_dao.getPoCategoryWIP(po_vo, con);
            pocatId.clear();
            pocatName.clear();
            pocatTotal.clear();
            pocatId = po_vo.getCategoryId();
            pocatName = po_vo.getCategoryName();
            pocatTotal = po_vo.getCategoryTotal();

            // Get Project Postage Detail (which Tranmittal Type is 'Outbound' and Billing Information/Option is 'Sender')

            pathfinder.functions.revenue.TransmittalDAO transmittalDAO = new pathfinder.functions.revenue.TransmittalDAO();
            pathfinder.functions.revenue.TransmittalVO transmittalVO = new pathfinder.functions.revenue.TransmittalVO();
            transmittalVO.setProjId((String) wip_proj_id_list.get(j));
            transmittalVO = transmittalDAO.GetShippingForReports(transmittalVO);
            postageTotal = 0.0;

            //Getting JCO Details and Values

            JobCostOverViewVO job_cost_over_view_VO = new pathfinder.functions.revenue.JobCostOverViewVO();
            JobCostOverViewDAO job_cost_over_view_DAO = new pathfinder.functions.revenue.JobCostOverViewDAO();
            job_cost_over_view_VO.setProjId((String) wip_proj_id_list.get(j));
            job_cost_over_view_VO.setToDate(toDate);
            job_cost_over_view_VO.setFacilityID(facilityID);
            dispProjDetailList = job_cost_over_view_DAO.getProjWIPJCOList(job_cost_over_view_VO, con);

            fpcatId.clear();
            fpcatName.clear();
            fpcatTotal.clear();
            //Getting FPP value based on the category

            job_cost_over_view_VO = job_cost_over_view_DAO.getFPPError(job_cost_over_view_VO, con);
            fpcatId = job_cost_over_view_VO.getCategoryId();
            fpcatName = job_cost_over_view_VO.getCategoryName();
            fpcatTotal = job_cost_over_view_VO.getCategoryValue();

            //Getting Altraion value based on the category
            altcatId.clear();
            altcatName.clear();
            altcatTotal.clear();
            job_cost_over_view_VO = job_cost_over_view_DAO.getAltraion(job_cost_over_view_VO, con);
            altcatId = job_cost_over_view_VO.getCategoryId();
            altcatName = job_cost_over_view_VO.getCategoryName();
            altcatTotal = job_cost_over_view_VO.getCategoryValue();


            //Getting Estimate Details and Values
            pathfinder.functions.revenue.EstimationInfo det = new pathfinder.functions.revenue.EstimationInfo();
            det.setProjId((String) wip_proj_id_list.get(j));
            det.getEstDetails(con);

            //Getting Estimate Details based on the category
            catTotal.clear();
            catName.clear();
            catId.clear();
            det.getEstCatgoryDetails(con);
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
            WIPInvoice.getInvoiceDetails(con);
            invoice_number_list = WIPInvoice.getInvoice_number_list();
            invoice_date_list = WIPInvoice.getInvoice_date_list();
            invoice_flag_list = WIPInvoice.getInvoice_flag_list();
            invoice_value_list = WIPInvoice.getInvoice_value_list();
            part_no_list = WIPInvoice.getPart_no_list();
            proj_id_list = WIPInvoice.getProj_id_list();

            Double wipcategoryTotal = 0.0;
            Double partialInvoice = 0.0;

            estimateDate = dateformat.parse(det.getEst_date());
            String author = "";
            for (int index = 0; index < authorId.size(); index++) {
                if (index > 0) {
                    author += "/ ";
                }
                author += authorName.get(index) + " ";
            }
            Paragraph heraderPara = new Paragraph();

            //To showing project Details

            heraderPara.add(new Paragraph("Job - " + projName.get(j) + " - " + author + " - " + pia.getTitle(), headerFont));
            heraderPara.setAlignment(Element.ALIGN_LEFT);
            heraderPara.setIndentationRight(45);
            document.add(heraderPara);

            heraderPara = new Paragraph();

            heraderPara.add(new Paragraph("Postage : ", subFont));
            heraderPara.setAlignment(Element.ALIGN_RIGHT);
            heraderPara.setIndentationRight(45);
            document.add(heraderPara);

            heraderPara = new Paragraph();
            heraderPara.add(new Paragraph("Estimate / Invoices to Customer", subFont));
            document.add(heraderPara);


            float[] lineItemsWidths = {10f, 10f, 10f, 10f, 10f, 10f};
            PdfPTable lineTable1 = new PdfPTable(6); //Code 3
            lineTable1.setWidthPercentage(100);
            lineTable1.setSplitRows(false);

            //To showing estimate details

            lineCell = new PdfPCell(new Paragraph("Estimate : $ " + ("0".equals(df.format(Double.parseDouble(det.getEst_value()))) ? "" : df.format(Double.parseDouble(det.getEst_value()))) + "\n\nEstimate# : " + det.getEst_number() + "\n\nEstimate Date : " + det.getEst_date(), smallFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineCell.setBorderWidthLeft(1f);
            lineCell.setPaddingLeft(5f);
            lineTable1.addCell(lineCell);

            //To showing invoice details

            String invoiceDetails;
            for (int partNo = 1; partNo < 5; partNo++) {
                invoiceDetails = "";
                invoiceDetails += "Partial " + partNo + " : ";
                if (part_no_list.contains(partNo + "")) {
                    partialInvoice += Double.parseDouble(invoice_value_list.get(part_no_list.indexOf(partNo + "")).toString());
                    invoiceDetails += "$ " + invoice_value_list.get(part_no_list.indexOf(partNo + ""));
                }
                invoiceDetails += "\n\nInvoice# : ";
                if (part_no_list.contains(partNo + "")) {
                    invoiceDetails += invoice_number_list.get(part_no_list.indexOf(partNo + ""));
                }
                invoiceDetails += "\n\nInvoice Date : ";
                if (part_no_list.contains(partNo + "")) {
                    invoiceDetails += invoice_date_list.get(part_no_list.indexOf(partNo + ""));
                }
                lineCell = new PdfPCell(new Paragraph(invoiceDetails, smallFont));
                lineCell.setBorderWidth(0f);
                lineCell.setBorderWidthTop(1f);
                lineTable1.addCell(lineCell);
            }
            invoiceDetails = "";
            invoiceDetails += "Final : ";
            if (invoice_flag_list.contains("0")) {
                invoiceDetails += "$ " + invoice_value_list.get(invoice_flag_list.indexOf("0"));
            }
            invoiceDetails += "\n\nInvoice# : ";
            if (invoice_flag_list.contains("0")) {
                invoiceDetails += invoice_number_list.get(invoice_flag_list.indexOf("0"));
            }
            invoiceDetails += "\n\nInvoice Date : ";
            if (invoice_flag_list.contains("0")) {
                invoiceDetails += invoice_date_list.get(invoice_flag_list.indexOf("0"));
            }
            lineCell = new PdfPCell(new Paragraph(invoiceDetails, smallFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineCell.setBorderWidthRight(1f);
            lineTable1.addCell(lineCell);

            lineTable1.getDefaultCell().setBorder(0);
            lineTable1.setWidths(lineItemsWidths);
            lineTable1.setSpacingBefore(5);
            //document.add(lineTable);

            //To showing estimate supercategory and its value

            PdfPTable lineTable2 = new PdfPTable(superCategory.size() + 1); //Code 3
            lineTable2.setWidthPercentage(100);
            lineTable2.setSplitRows(false);

            for (int index = 0; index < superCategory.size(); index++) {
                lineCell = new PdfPCell(new Paragraph(superCategory.get(index).toString(), subFont));
                lineCell.setBorderWidth(0f);
                lineCell.setPaddingTop(10f);
                if (index == 0) {
                    lineCell.setBorderWidthLeft(1f);
                    lineCell.setPaddingLeft(5f);
                }
                lineTable2.addCell(lineCell);
            }

            double categoryTotal = 0;
            lineCell = new PdfPCell(new Paragraph("Total", subFont));
            lineCell.setPaddingTop(10f);
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthRight(1f);
            lineTable2.addCell(lineCell);

            for (int index = 0; index < superCategoryId.size(); index++) {
                String tempString = null;
                if (catId.contains(superCategoryId.get(index) + "")) {
                    categoryTotal += Double.parseDouble(catTotal.get(catId.indexOf(superCategoryId.get(index) + "")).toString());
                    tempString = ("0".equals(df.format(Double.parseDouble(catTotal.get(catId.indexOf(superCategoryId.get(index) + "")).toString()))) ? "" : "$ " + df.format(Double.parseDouble(catTotal.get(catId.indexOf(superCategoryId.get(index) + "")).toString())));
                }
                lineCell = new PdfPCell(new Paragraph(tempString, subFont));
                lineCell.setBorderWidth(0f);
                if (index == 0) {
                    lineCell.setBorderWidthLeft(1f);
                    lineCell.setPaddingLeft(5f);
                }
                lineCell.setPaddingBottom(5f);
                lineCell.setBorderWidthBottom(1f);
                lineTable2.addCell(lineCell);
            }

            lineCell = new PdfPCell(new Paragraph(("0".equals(df.format(categoryTotal)) ? "" : "$ " + df.format(categoryTotal)), subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthBottom(1f);
            lineCell.setBorderWidthRight(1f);
            lineTable2.addCell(lineCell);
            //document.add(lineTable);

            //To Showing PO Details
            fpcost = 0.0;
            rpcost = 0.0;
            debuerrcost = 0.0;
            chennaierrcost = 0.0;
            outside = 0.0;
            total = 0.0;
            wipTotal = 0.0;
            String temp;

            float[] jcoWidths = {25f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
            PdfPTable lineTable3 = new PdfPTable(8);
            lineTable3.setWidthPercentage(100);
            lineTable3.setHeaderRows(1);
            lineTable3.setSplitRows(false);
            lineTable3.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            lineCell = new PdfPCell(new Paragraph("WIP Categories", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setPaddingBottom(5f);
            lineCell.setBorderWidthBottom(1f);
            lineTable3.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("FPP", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setPaddingBottom(5f);
            lineCell.setBorderWidthBottom(1f);
            lineTable3.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Alterations", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setPaddingBottom(5f);
            lineCell.setBorderWidthBottom(1f);
            lineTable3.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Dubuque Errors", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setPaddingBottom(5f);
            lineCell.setBorderWidthBottom(1f);
            lineTable3.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Chennai Errors", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setPaddingBottom(5f);
            lineCell.setBorderWidthBottom(1f);
            lineTable3.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Outside Errors", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setPaddingBottom(5f);
            lineCell.setBorderWidthBottom(1f);
            lineTable3.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Total", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setPaddingBottom(5f);
            lineCell.setBorderWidthBottom(1f);
            lineTable3.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("WIP Value", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setPaddingBottom(5f);
            lineCell.setBorderWidthBottom(1f);
            lineTable3.addCell(lineCell);

            lineTable3.getDefaultCell().enableBorderSide(PdfPCell.LEFT);
            lineTable3.getDefaultCell().enableBorderSide(PdfPCell.RIGHT);

            lineTable3.getDefaultCell().setBorderWidth(1f);
            lineTable3.setWidths(jcoWidths);

            if(Double.parseDouble(transmittalVO.getTotalCarrierCost().toString()) != 0.0) {
                postageTotal = Double.parseDouble(transmittalVO.getTotalCarrierCost().toString());
                fpcost += postageTotal;
                total += postageTotal;
                if (plannerFacilityId.equals(facilityID)) {
                    wipTotal += postageTotal;
                }

                lineCell = new PdfPCell(new Paragraph("Postage", smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph("$ " + df.format(postageTotal), smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
                emptyCell.setBorderWidth(0f);
                lineTable3.addCell(emptyCell);
                lineTable3.addCell(emptyCell);
                lineTable3.addCell(emptyCell);
                lineTable3.addCell(emptyCell);

                lineCell = new PdfPCell(new Paragraph("$ " + df.format(postageTotal), smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);

                if (plannerFacilityId.equals(facilityID)) {
                    lineCell = new PdfPCell(new Paragraph("$ " + df.format(postageTotal), smallFont));
                    lineCell.setBorderWidth(0f);
                    lineTable3.addCell(lineCell);
                } else {
                    lineTable3.addCell(emptyCell);
                }
            }

            for (int index = 0; index < poTotal.size(); index++) {
                fpcost += Double.parseDouble(poTotal.get(index).toString());
                total += Double.parseDouble(poTotal.get(index).toString());
                if (plannerFacilityId.equals(facilityID)) {
                    wipTotal += Double.parseDouble(poTotal.get(index).toString());
                }

                lineCell = new PdfPCell(new Paragraph(poActivity.get(index).toString(), smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph("$ " + df.format(Double.parseDouble(poTotal.get(index).toString())), smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
                emptyCell.setBorderWidth(0f);
                lineTable3.addCell(emptyCell);
                lineTable3.addCell(emptyCell);
                lineTable3.addCell(emptyCell);
                lineTable3.addCell(emptyCell);

                lineCell = new PdfPCell(new Paragraph("$ " + df.format(Double.parseDouble(poTotal.get(index).toString())), smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);

                if (plannerFacilityId.equals(facilityID)) {
                    lineCell = new PdfPCell(new Paragraph("$ " + df.format(Double.parseDouble(poTotal.get(index).toString())), smallFont));
                    lineCell.setBorderWidth(0f);
                    lineTable3.addCell(lineCell);
                } else {
                    lineTable3.addCell(emptyCell);
                }
            }


            //To Showing JCO Details
            Iterator dispProjWIPItr = dispProjDetailList.iterator();
            while (dispProjWIPItr.hasNext()) {
                job_cost_over_view_VO = (pathfinder.functions.revenue.JobCostOverViewVO) dispProjWIPItr.next();
                lineCell = new PdfPCell(new Paragraph(job_cost_over_view_VO.getMilestone(), smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
                fpcost += Double.parseDouble(job_cost_over_view_VO.getFPBC().toString());
                temp = Double.parseDouble(job_cost_over_view_VO.getFPBC().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getFPBC().toString()));
                lineCell = new PdfPCell(new Paragraph(temp, smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
                rpcost += Double.parseDouble(job_cost_over_view_VO.getRPBC().toString());
                temp = Double.parseDouble(job_cost_over_view_VO.getRPBC().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getRPBC().toString()));
                lineCell = new PdfPCell(new Paragraph(temp, smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
                debuerrcost += Double.parseDouble(job_cost_over_view_VO.getDBQERRCT().toString());
                temp = Double.parseDouble(job_cost_over_view_VO.getDBQERRCT().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getDBQERRCT().toString()));
                lineCell = new PdfPCell(new Paragraph(temp, smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
                chennaierrcost += Double.parseDouble(job_cost_over_view_VO.getCHNERRCT().toString());
                temp = Double.parseDouble(job_cost_over_view_VO.getCHNERRCT().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getCHNERRCT().toString()));
                lineCell = new PdfPCell(new Paragraph(temp, smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
                outside += Double.parseDouble(job_cost_over_view_VO.getOUTERRCT().toString());
                temp = Double.parseDouble(job_cost_over_view_VO.getOUTERRCT().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getOUTERRCT().toString()));
                lineCell = new PdfPCell(new Paragraph(temp, smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
                total += Double.parseDouble(job_cost_over_view_VO.getTOTBC().toString());
                temp = Double.parseDouble(job_cost_over_view_VO.getTOTBC().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getTOTBC().toString()));
                lineCell = new PdfPCell(new Paragraph(temp, smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
                wipTotal += Double.parseDouble(job_cost_over_view_VO.getWipValueList().toString());
                temp = Double.parseDouble(job_cost_over_view_VO.getWipValueList().toString()) <= 0.0 ? "" : "$ " + df.format(Double.parseDouble(job_cost_over_view_VO.getWipValueList().toString()));
                lineCell = new PdfPCell(new Paragraph(temp, smallFont));
                lineCell.setBorderWidth(0f);
                lineTable3.addCell(lineCell);
            }
            lineCell = new PdfPCell(new Paragraph("Subtotal", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineTable3.addCell(lineCell);
            lineCell = new PdfPCell(new Paragraph((fpcost == 0.0 ? "" : "$ " + df.format(fpcost)), subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineTable3.addCell(lineCell);
            lineCell = new PdfPCell(new Paragraph((rpcost == 0.0 ? "" : "$ " + df.format(rpcost)), subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineTable3.addCell(lineCell);
            lineCell = new PdfPCell(new Paragraph((debuerrcost == 0.0 ? "" : "$ " + df.format(debuerrcost)), subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineTable3.addCell(lineCell);
            lineCell = new PdfPCell(new Paragraph((chennaierrcost == 0.0 ? "" : "$ " + df.format(chennaierrcost)), subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineTable3.addCell(lineCell);
            lineCell = new PdfPCell(new Paragraph((outside == 0.0 ? "" : "$ " + df.format(outside)), subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineTable3.addCell(lineCell);
            lineCell = new PdfPCell(new Paragraph((total == 0.0 ? "" : "$ " + df.format(total)), subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineTable3.addCell(lineCell);
            lineCell = new PdfPCell(new Paragraph((wipTotal == 0.0 ? "" : "$ " + df.format(wipTotal)), subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineTable3.addCell(lineCell);
            lineTable3.setSpacingBefore(20);
            //document.add(lineTable);
            //document.add(new Paragraph("\nCost", subFont));

            //To showing PO & JCO value grouped by category

            PdfPTable lineTable4 = new PdfPTable(superCategory.size() + 2);
            lineTable4.setWidthPercentage(100);
            lineTable4.setSplitRows(false);

            lineCell = new PdfPCell(new Paragraph(""));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineCell.setBorderWidthLeft(1f);
            lineCell.setPaddingLeft(5f);
            lineTable4.addCell(lineCell);

            for (int index = 0; index < superCategory.size(); index++) {
                lineCell = new PdfPCell(new Paragraph(superCategory.get(index).toString(), subFont));
                lineCell.setBorderWidth(0f);
                lineCell.setBorderWidthTop(1f);
                lineTable4.addCell(lineCell);
            }

            lineCell = new PdfPCell(new Paragraph("Sub-Total", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthTop(1f);
            lineCell.setBorderWidthRight(1f);
            lineTable4.addCell(lineCell);

            lineTable4.getDefaultCell().enableBorderSide(PdfPCell.LEFT);
            lineTable4.getDefaultCell().enableBorderSide(PdfPCell.RIGHT);

            lineCell = new PdfPCell(new Paragraph("FPP", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthLeft(1f);
            lineCell.setPaddingLeft(5f);
            lineTable4.addCell(lineCell);
            categoryTotal = 0;
            String tempString = "";
            String categoryValue = "";
            for (int index = 0; index < superCategoryTotal.length; index++) {
                superCategoryTotal[index] = 0;
            }

            for (int index = 0; index < superCategory.size(); index++) {
                tempString = "";
                categoryValue = "0";
                if (fpcatId.contains(superCategoryId.get(index) + "")) {
                    categoryTotal += Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(superCategoryId.get(index) + "")).toString());
                    tempString = ("0".equals(df.format(Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(superCategoryId.get(index) + "")).toString()))) ? "" : "$ " + df.format(Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(superCategoryId.get(index) + "")).toString())));
                    categoryValue = df.format(Double.parseDouble(fpcatTotal.get(fpcatId.indexOf(superCategoryId.get(index) + "")).toString()));
                }
                superCategoryTotal[index] += Double.parseDouble(categoryValue);
                lineCell = new PdfPCell(new Paragraph(tempString, smallFont));
                lineCell.setBorderWidth(0f);
                lineTable4.addCell(lineCell);
            }
            wipcategoryTotal += categoryTotal;
            lineCell = new PdfPCell(new Paragraph(("0".equals(df.format(categoryTotal)) ? "" : "$ " + df.format(categoryTotal)), smallFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthRight(1f);
            lineTable4.addCell(lineCell);
            lineCell = new PdfPCell(new Paragraph("Alterations", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthLeft(1f);
            lineCell.setPaddingLeft(5f);
            lineTable4.addCell(lineCell);
            categoryTotal = 0;
            for (int index = 0; index < superCategory.size(); index++) {
                tempString = "";
                categoryValue = "0";
                if (altcatId.contains(superCategoryId.get(index) + "")) {
                    categoryTotal += Double.parseDouble(altcatTotal.get(altcatId.indexOf(superCategoryId.get(index) + "")).toString());
                    tempString = ("0".equals(df.format(Double.parseDouble(altcatTotal.get(altcatId.indexOf(superCategoryId.get(index) + "")).toString()))) ? "" : "$ " + df.format(Double.parseDouble(altcatTotal.get(altcatId.indexOf(superCategoryId.get(index) + "")).toString())));
                    categoryValue = df.format(Double.parseDouble(altcatTotal.get(altcatId.indexOf(superCategoryId.get(index) + "")).toString()));
                }
                superCategoryTotal[index] += Double.parseDouble(categoryValue);
                lineCell = new PdfPCell(new Paragraph(tempString, smallFont));
                lineCell.setBorderWidth(0f);
                lineTable4.addCell(lineCell);
            }
            wipcategoryTotal += categoryTotal;
            lineCell = new PdfPCell(new Paragraph(("0".equals(df.format(categoryTotal <= 0 ? 0 : categoryTotal)) ? "" : "$ " + df.format(categoryTotal)), smallFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthRight(1f);
            lineTable4.addCell(lineCell);
            
            lineCell = new PdfPCell(new Paragraph("Postage", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthLeft(1f);
            lineCell.setPaddingLeft(5f);
            lineTable4.addCell(lineCell);
            if (plannerFacilityId.equals(facilityID)) {
                for (int index = 0; index < superCategory.size(); index++) {
                    emptyCell.setBorderWidth(0f);
                    lineTable4.addCell(emptyCell);
                }
                wipcategoryTotal += postageTotal;

                if (plannerFacilityId.equals(facilityID)) {
                    lineCell = new PdfPCell(new Paragraph("$ " + df.format(postageTotal), smallFont));
                    lineCell.setBorderWidth(0f);
                    lineCell.setBorderWidthRight(1f);
                    lineTable4.addCell(lineCell);
                } else {
                    lineTable4.addCell(emptyCell);
                }
            } else {
                for (int index = 0; index <= superCategoryId.size(); index++) {
                    lineCell = new PdfPCell(new Paragraph(""));
                    lineCell.setBorderWidth(0f);
                    if (index == superCategoryId.size()) {
                        lineCell.setBorderWidthRight(1f);
                    }
                    lineTable4.addCell(lineCell);
                }
            }

            lineCell = new PdfPCell(new Paragraph("Purchase Order", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthLeft(1f);
            lineCell.setPaddingLeft(5f);
            lineTable4.addCell(lineCell);
            categoryTotal = 0;
            if (plannerFacilityId.equals(facilityID)) {
                for (int index = 0; index < superCategory.size(); index++) {
                    tempString = "";
                    categoryValue = "0";
                    if (pocatId.contains(superCategoryId.get(index) + "")) {
                        categoryTotal += Double.parseDouble(pocatTotal.get(pocatId.indexOf(superCategoryId.get(index) + "")).toString());
                        tempString = ("0".equals(df.format(Double.parseDouble(pocatTotal.get(pocatId.indexOf(superCategoryId.get(index) + "")).toString()))) ? "" : "$ " + df.format(Double.parseDouble(pocatTotal.get(pocatId.indexOf(superCategoryId.get(index) + "")).toString())));
                        categoryValue = df.format(Double.parseDouble(pocatTotal.get(pocatId.indexOf(superCategoryId.get(index) + "")).toString()));
                    }
                    superCategoryTotal[index] += Double.parseDouble(categoryValue);
                    lineCell = new PdfPCell(new Paragraph(tempString, smallFont));
                    lineCell.setBorderWidth(0f);
                    lineTable4.addCell(lineCell);
                }
                wipcategoryTotal += categoryTotal;
                lineCell = new PdfPCell(new Paragraph(("0".equals(df.format(categoryTotal <= 0 ? 0 : categoryTotal)) ? "" : "$ " + df.format(categoryTotal)), smallFont));
                lineCell.setBorderWidth(0f);
                lineCell.setBorderWidthRight(1f);
                lineTable4.addCell(lineCell);
            } else {
                for (int index = 0; index <= superCategoryId.size(); index++) {
                    lineCell = new PdfPCell(new Paragraph(""));
                    lineCell.setBorderWidth(0f);
                    if (index == superCategoryId.size()) {
                        lineCell.setBorderWidthRight(1f);
                    }
                    lineTable4.addCell(lineCell);
                }
            }

            for (int index = 0; index < superCategory.size(); index++) {
                lineCell = new PdfPCell(new Paragraph(""));
                lineCell.setBorderWidth(0f);
                lineCell.setBorderWidthBottom(1f);
                if (index == 0) {
                    lineCell.setBorderWidthLeft(1f);
                    lineCell.setPaddingLeft(5f);
                }
                lineTable4.addCell(lineCell);
            }
            lineCell = new PdfPCell(new Paragraph("Total", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthBottom(1f);
            lineCell.setPaddingBottom(5f);
            lineTable4.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph(("0".equals(df.format(wipcategoryTotal <= 0 ? 0 : wipcategoryTotal)) ? "" : "$ " + df.format(wipcategoryTotal)), subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthRight(1f);
            lineCell.setBorderWidthBottom(1f);
            lineCell.setPaddingBottom(5f);
            lineTable4.addCell(lineCell);
            lineTable4.setSpacingBefore(5);
            //document.add(lineTable);
            wipcategoryTotal -= partialInvoice;
            //document.add(new Paragraph("\nWIP", subFont));

            //To showing supercatogory total for PO & JCO

            PdfPTable lineTable5 = new PdfPTable(superCategory.size() + 2);
            lineTable5.setWidthPercentage(100);
            lineTable5.setHeaderRows(1);
            lineTable5.setSplitRows(false);
            lineTable5.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            lineCell = new PdfPCell(new Paragraph("********** (FPP + Alterations + Purchase Order + Postage) - Partial Invoices **********", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setBorderWidthBottom(0f);
            lineCell.setColspan(superCategory.size() + 2);
            lineCell.setPaddingBottom(10f);
            lineCell.setPaddingLeft(180f);
            lineTable5.addCell(lineCell);

            for (int index = 0; index < superCategory.size(); index++) {
                lineCell = new PdfPCell(new Paragraph(superCategory.get(index).toString(), subFont));
                lineCell.setBorderWidth(0f);
                if (index == 0) {
                    lineCell.setBorderWidthLeft(1f);
                    lineCell.setPaddingLeft(5f);
                }
                lineTable5.addCell(lineCell);
            }

            lineCell = new PdfPCell(new Paragraph("Mi Partial Invoices", subFont));
            lineCell.setBorderWidth(0f);
            lineTable5.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("WIP Value", subFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthRight(1f);
            lineTable5.addCell(lineCell);


            for (int index = 0; index < superCategory.size(); index++) {
                lineCell = new PdfPCell(new Paragraph("$ " + df.format(superCategoryTotal[index]), smallFont));
                lineCell.setBorderWidth(0f);
                if (index == 0) {
                    lineCell.setBorderWidthLeft(1f);
                    lineCell.setPaddingLeft(5f);
                }
                lineCell.setBorderWidthBottom(1f);
                lineCell.setPaddingBottom(5f);
                lineTable5.addCell(lineCell);
                superCategoryFinalTotal[index] += superCategoryTotal[index];
            }

            lineCell = new PdfPCell(new Paragraph(("0.0".equals(df.format(partialInvoice <= 0 ? 0 : partialInvoice)) ? "" : "$ " + df.format(partialInvoice)), smallFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthBottom(1f);
            lineCell.setPaddingBottom(5f);
            lineTable5.addCell(lineCell);

            if(wipcategoryTotal<0){
                wipcategoryTotal = 0.0;
            }
            if(Double.parseDouble(det.getEst_value())<wipcategoryTotal){
                wipcategoryTotal = Double.parseDouble(det.getEst_value());
            }
            lineCell = new PdfPCell(new Paragraph(("0.0".equals(df.format(wipcategoryTotal <= 0 ? 0 : wipcategoryTotal)) ? "" : "$ " + df.format(wipcategoryTotal)), smallFont));
            lineCell.setBorderWidth(0f);
            lineCell.setBorderWidthBottom(1f);
            lineCell.setPaddingBottom(5f);
            lineCell.setBorderWidthRight(1f);
            lineTable5.addCell(lineCell);

            lineTable5.setSpacingBefore(5);
            //document.add(lineTable);

            wippartialInvoice += partialInvoice;
            wipCostTotal += wipcategoryTotal;
            document.add(lineTable1);
            document.add(lineTable2);
            document.add(lineTable3);
            document.add(new Paragraph("\nCost", subFont));
            document.add(lineTable4);
            document.add(new Paragraph("\nWIP", subFont));
            document.add(lineTable5);
            document.newPage();
        }
        document.newPage();
        document.add(new Paragraph("WIP as of  " + toDate, subFont));

        //To showing Final WIP valuue based on the supercategory

        lineTable = new PdfPTable(superCategory.size() + 3); //Code 3
        lineTable.setWidthPercentage(100);
        lineTable.setHeaderRows(1);
        lineTable.setSplitRows(false);
        lineTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        for (int index = 0; index < superCategory.size(); index++) {
            lineCell = new PdfPCell(new Paragraph(superCategory.get(index).toString(), subFont));
            lineCell.setBorderWidth(0f);
            if (index == 0) {
                lineCell.setBorderWidthLeft(1f);
                lineCell.setPaddingLeft(5f);
            }
            lineCell.setBorderWidthTop(1f);
            lineTable.addCell(lineCell);
        }
        lineCell = new PdfPCell(new Paragraph("Mi Partial Invoices", subFont));
        lineCell.setBorderWidth(0f);
        lineCell.setBorderWidthTop(1f);
        lineTable.addCell(lineCell);
        
        lineCell = new PdfPCell(new Paragraph("Postage", subFont));
        lineCell.setBorderWidth(0f);
        lineCell.setBorderWidthTop(1f);
        lineTable.addCell(lineCell);

        lineCell = new PdfPCell(new Paragraph("WIP Value", subFont));
        lineCell.setBorderWidth(0f);
        lineCell.setBorderWidthTop(1f);
        lineCell.setBorderWidthRight(1f);
        lineTable.addCell(lineCell);

        for (int index = 0; index < superCategory.size(); index++) {
            lineCell = new PdfPCell(new Paragraph("$ " + df.format(superCategoryFinalTotal[index]), smallFont));
            lineCell.setBorderWidth(0f);
            if (index == 0) {
                lineCell.setBorderWidthLeft(1f);
                lineCell.setPaddingLeft(5f);
            }
            lineCell.setBorderWidthBottom(1f);
            lineCell.setPaddingBottom(5f);
            lineTable.addCell(lineCell);
        }

        lineCell = new PdfPCell(new Paragraph(("0.0".equals(df.format(wippartialInvoice <= 0 ? 0 : wippartialInvoice)) ? "" : "$ " + df.format(wippartialInvoice)), smallFont));
        lineCell.setBorderWidth(0f);
        lineCell.setBorderWidthBottom(1f);
        lineCell.setPaddingBottom(5f);
        lineTable.addCell(lineCell);
        
        lineCell = new PdfPCell(new Paragraph("", smallFont));
        lineCell.setBorderWidth(0f);
        lineCell.setBorderWidthBottom(1f);
        lineCell.setPaddingBottom(5f);
        lineTable.addCell(lineCell);

        lineCell = new PdfPCell(new Paragraph(("0.0".equals(df.format(wipCostTotal <= 0 ? 0 : wipCostTotal)) ? "" : "$ " + df.format(wipCostTotal)), smallFont));
        lineCell.setBorderWidth(0f);
        lineCell.setBorderWidthBottom(1f);
        lineCell.setPaddingBottom(5f);
        lineCell.setBorderWidthRight(1f);
        lineTable.addCell(lineCell);

        lineTable.setSpacingBefore(5);
        document.add(lineTable);
        document.close();

        con.close();
    }
    catch (Exception e

    
        ) {
            System.out.println("Exception on WIP PDF Servlet : " + e.getStackTrace());
        e.printStackTrace();
    }

    
    

finally {
            //out.close();
        }
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
