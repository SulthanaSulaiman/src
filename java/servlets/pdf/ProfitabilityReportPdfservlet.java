/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.*;
import pathfinder.functions.revenue.*;

/**
 *
 * @author Parameshwarant
 */
public class ProfitabilityReportPdfservlet extends HttpServlet {

    protected PdfPTable prjoectsHeader, prjoectsFooter, footerStatus, headerDisp, pageNumber;
    protected Phrase phrase;
    float[] columnParams = {30f, 23f, 18f, 18f, 10f, 10f, 10f};
    float[] columnParams1 = {10f, 15f, 10f, 10f, 10f};
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
    private static Font blueColorFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 255));
    private static Font goldenRedFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, new BaseColor(184, 134, 11));
    private static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 9);
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    List profitProjectsList = new ArrayList();
    List projNameList = new ArrayList();
    List customerNameList = new ArrayList();
    List plannerNameList = new ArrayList();
    List hybridPlannerNameList = new ArrayList();
    List dupProjNameListChk = new ArrayList();
    List invoiceMonthYearList = new ArrayList();
    List invoiceMonthList = new ArrayList();
    List invoiceYearList = new ArrayList();
    List invoiceAmountList = new ArrayList();
    List projProductiveActualValueList = new ArrayList();
    int totalproj = 0;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=ProfitReport.pdf");

        response.setContentType("application/pdf");
        Document document = new Document();


        String SourcePage = request.getRequestURI().toString();
        SourcePage = SourcePage.substring(SourcePage.lastIndexOf("/") + 1);

        String getInvoiceFromDate = request.getParameter("invoiceFromDate") != null ? request.getParameter("invoiceFromDate") : "";
        String getInvoiceToDate = request.getParameter("invoiceToDate") != null ? request.getParameter("invoiceToDate") : "";
        String plannerId = request.getParameter("planner") != null ? request.getParameter("planner") : "";
        String facilityId = request.getParameter("facility") != null ? request.getParameter("facility") : "";
        String customer = request.getParameter("customer") != null ? request.getParameter("customer") : "";
        String hybridPlannerId = request.getParameter("hybridPlanner") != null ? request.getParameter("hybridPlanner") : "";

        String noOfProjects = request.getParameter("noOfProjects") != null ? request.getParameter("noOfProjects") : "";
        String noOfProfitable = request.getParameter("noOfProfitables") != null ? request.getParameter("noOfProfitables") : "";

        String projName = "";
        String customerName = "";
        String plannerName = "";
        String hybridPlannerName = "";
        String invoiceAmount = "";

        double projProfit = 0;
        double projInvoiceAmount = 0;
        double overallProjInvoiceAmount = 0;
        double overallProjActualCost = 0;
        double overallProjProfit = 0;
        double actualCost = 0;
        String projActualProfit = "";


        try {
            ProfitabilityReportDAO getProfitReport = new ProfitabilityReportDAO();
            ProfitabilityReportVO setProfitReportValues = new ProfitabilityReportVO();

            if (!getInvoiceFromDate.equals("") && !getInvoiceToDate.equals("")) {
                setProfitReportValues.setInvoiceDateChk("1");
                setProfitReportValues.setInvoiceDateFrom(getInvoiceFromDate);
                setProfitReportValues.setInvoiceDateTo(getInvoiceToDate);
            }

            if (!plannerId.equals("")) {
                setProfitReportValues.setPlannerId(plannerId);
            }

            if (!hybridPlannerId.equals("")) {
                setProfitReportValues.setHybridPlannerId(hybridPlannerId);
            }

            if (!facilityId.equals("")) {
                setProfitReportValues.setFacilityId(facilityId);
            }
            if (!customer.equals("")) {
                setProfitReportValues.setCustomer(customer);
            }

            invoiceMonthList.clear();
            invoiceYearList.clear();

            getProfitReport.collectProfitReportInvoiceDate(setProfitReportValues);

            invoiceMonthList = setProfitReportValues.getInvoiceMonthList();
            invoiceYearList = setProfitReportValues.getInvoiceYearList();

            int monthYearSize = invoiceMonthList.size();
            String actShipMonth = "";
            String actShipYear = "";
            totalproj = 0;

            PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream());

            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            writer.setPageEvent(new ProfitReportHeaderFooterDisp());

            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(18, 20, 88, 50);
            document.open();

            addHeader();
            addFooter();
            addOverallHeader();


            PdfContentByte cb = writer.getDirectContent();

            prjoectsHeader.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 130, cb);
            prjoectsFooter.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 470, cb);
            headerDisp.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 104, cb);


            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getPageNumber()), bold),
                    (document.right() - document.left() + 755) / 2 + document.leftMargin(), document.top() - 490, 0);

            PdfPTable contenPdfPTable = new PdfPTable(columnParams);
            dupProjNameListChk.clear();
            for (int j = 0; j < monthYearSize; j++) {

                int projCount = 0;
                actShipMonth = invoiceMonthList.get(j).toString();
                actShipYear = invoiceYearList.get(j).toString();

                projNameList.clear();
                customerNameList.clear();
                plannerNameList.clear();
                hybridPlannerNameList.clear();
                projProductiveActualValueList.clear();
                invoiceAmountList.clear();
                invoiceMonthYearList.clear();



                setProfitReportValues.setFromServletPdf(SourcePage);
                setProfitReportValues.setInvoiceMonth(actShipMonth);
                setProfitReportValues.setInvoiceYear(actShipYear);

                getProfitReport.collectProfitabilityReportDetails(setProfitReportValues);

                projNameList = setProfitReportValues.getProjNameList();
                customerNameList = setProfitReportValues.getCustomerNameList();
                plannerNameList = setProfitReportValues.getPlannerNameList();
                hybridPlannerNameList = setProfitReportValues.getHybridPlannerNameList();
                invoiceAmountList = setProfitReportValues.getInvoiceAmountList();
                projProductiveActualValueList = setProfitReportValues.getProjProductiveValuesList();
                invoiceMonthYearList = setProfitReportValues.getInvoiceMonthAndYearList();

                int projPrinterFilesSize = projNameList.size();

                for (int i = 0; i < projPrinterFilesSize; i++) {
                    projName = projNameList.get(i).toString();

                    customerName = customerNameList.get(i).toString();
                    plannerName = plannerNameList.get(i).toString();
                    hybridPlannerName = hybridPlannerNameList.get(i).toString();
                    invoiceAmount = invoiceAmountList.get(i).toString();
                    actualCost = Double.parseDouble(projProductiveActualValueList.get(i).toString());
                    projInvoiceAmount = Double.parseDouble(invoiceAmountList.get(i).toString());
                    overallProjInvoiceAmount += projInvoiceAmount;
                    if (!dupProjNameListChk.isEmpty()) {
                        if (!dupProjNameListChk.contains(projName)) {
                            dupProjNameListChk.add(projName);
                            overallProjActualCost += actualCost;
                        }
                    } else {
                        dupProjNameListChk.add(projName);
                        overallProjActualCost += actualCost;

                    }



                    if (Math.round(actualCost) == 0) {
                        projActualProfit = "Actual Cost N/A";
                    } else {
                        projProfit = ((projInvoiceAmount - actualCost) * 100) / actualCost;

                        if(projProfit > 0) {
                            profitProjectsList.add(projNameList.get(i).toString());
                        }
                        
                        projActualProfit = String.valueOf(decimalFormat.format(projProfit));
                        projActualProfit += "%";
                    }



                    if (i == 0) {
                        if (projPrinterFilesSize != 0) {
                            String invoiceMonthYear = invoiceMonthYearList.get(0).toString();

                            Phrase catPhrase = new Phrase(invoiceMonthYear, headerFont);

                            PdfPCell cellTitle = new PdfPCell(catPhrase);
                            cellTitle.setColspan(7);
                            cellTitle.setBorder(Rectangle.BOX);
                            if (!invoiceMonthYear.equals("N/A")) {
                                cellTitle.setBackgroundColor(new BaseColor(254, 241, 236));
                            }
                            if (invoiceMonthYear.equals("N/A")) {
                                cellTitle.addElement(new Phrase("* Actual Ship Date is Not Allocated for Following Projects.", goldenRedFont));
                                cellTitle.setBorder(0);
                                cellTitle.setHorizontalAlignment(Phrase.ALIGN_CENTER);
                            }

                            cellTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                            contenPdfPTable.setWidthPercentage(100f);
                            contenPdfPTable.setSpacingAfter(10f);
                            contenPdfPTable.setSpacingBefore(10f);
                            contenPdfPTable.addCell(cellTitle);


                        }
                    }


                    contenPdfPTable.setWidthPercentage(100f);
                    contenPdfPTable.setTotalWidth(800f);


                    PdfPCell cellstat = new PdfPCell(new Phrase(projName, smallNote));
                    cellstat.setFixedHeight(14f);
                    cellstat.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(customerName, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(plannerName, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(hybridPlannerName, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(invoiceAmount, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(decimalFormat.format(actualCost), smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(projActualProfit, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
                    contenPdfPTable.addCell(cellstat);



                    projCount++;


                }
                totalproj += projCount;
                if (projCount != 0) {

                    PdfPCell monthWiseProjCountCell = new PdfPCell(new Phrase("\n" + projCount + " project/s.\n\n", small));
                    monthWiseProjCountCell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
                    monthWiseProjCountCell.setColspan(7);
                    monthWiseProjCountCell.setBorder(0);
                    contenPdfPTable.addCell(monthWiseProjCountCell);
                }
            }


            PdfPCell grandToalCell = new PdfPCell(new Phrase("\n\n Total = " + totalproj + " project/s", small));
            grandToalCell.setColspan(7);
            grandToalCell.setBorder(0);
            contenPdfPTable.addCell(grandToalCell);



            Phrase lineBreak = new Phrase("\n\n\n\n\n");


            overallProjProfit = ((overallProjInvoiceAmount - overallProjActualCost) * 100) / overallProjActualCost;

            PdfPTable profitSummaryTable = new PdfPTable(columnParams1);

            PdfPCell summaryHeaderCell = new PdfPCell(new Phrase("Profitability Report Summary\n\n", headerFont));
            summaryHeaderCell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
            summaryHeaderCell.setColspan(5);
            summaryHeaderCell.setBorder(0);
            profitSummaryTable.addCell(summaryHeaderCell);



            PdfPCell profitSummaryCell = new PdfPCell(new Phrase("Total # Projects", bold));
            profitSummaryCell.setColspan(1);

            //profitSummaryCell.setFixedHeight(10f);
            profitSummaryCell.setPadding(15f);
            profitSummaryCell.setPaddingLeft(30f);
            profitSummaryTable.addCell(profitSummaryCell);


            profitSummaryCell = new PdfPCell(new Phrase("Total Amount Invoiced", bold));
            profitSummaryCell.setPadding(15f);
            profitSummaryCell.setPaddingLeft(30f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase("Total Cost", bold));
            profitSummaryCell.setPadding(15f);
            profitSummaryCell.setPaddingLeft(30f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase("Total Profit (%)", bold));
            profitSummaryCell.setPadding(15f);
            profitSummaryCell.setPaddingLeft(30f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase("Total Jobs Profitable (%)", bold));
            profitSummaryCell.setPadding(15f);
            profitSummaryCell.setPaddingLeft(30f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell.setColspan(4);

            profitSummaryCell = new PdfPCell(new Phrase(String.valueOf(noOfProjects), smallNote));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(35f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase(String.valueOf(decimalFormat.format(overallProjInvoiceAmount)), smallNote));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(30f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase(String.valueOf(decimalFormat.format(overallProjActualCost)), smallNote));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(30f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase(String.valueOf(decimalFormat.format(overallProjProfit)) + "%", smallNote));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(30f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase(noOfProfitable+"%"));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(30f);
            profitSummaryTable.addCell(profitSummaryCell);

            document.add(profitSummaryTable);
            document.add(lineBreak);
            document.add(contenPdfPTable);

            document.close();

        } catch (Exception e) {
            System.out.println("Profitability Servlet Exception" + e);
        }
    }

    public void addHeader() {
        prjoectsHeader = headerClass.profitReportHeaderStyle();

    }

    public void addFooter() {

        prjoectsFooter = headerClass.profitReportFooterStyle();
    }

    public void addOverallHeader() {

        headerDisp = headerClass.overallProfitReportHeaderStyle();
    }

    public void commonStylingTop(PdfPCell cell) {
        cell.setBorder(Rectangle.TOP);
    }

    public void commonStylingBottom(PdfPCell cell) {
        cell.setBorder(Rectangle.BOTTOM);
    }
}
