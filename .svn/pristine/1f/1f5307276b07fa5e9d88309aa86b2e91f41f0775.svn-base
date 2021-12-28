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
import pathfinder.functions.projects.*;

/**
 *
 * @author Parameshwarant
 */
public class BillingReportPdfservlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected PdfPTable prjoectsHeader, prjoectsFooter, footerStatus, headerDisp, pageNumber;
    protected Phrase phrase;
    float[] columnParams = {30f, 23f, 18f, 18f, 10f, 10f, 8f, 10f, 8f};
    float[] columnParams1 = {10f, 15f, 10f, 10f, 10f};
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
    private static Font blueColorFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 255));
    private static Font goldenRedFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, new BaseColor(184, 134, 11));
    private static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 9);
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    List projNameList = new ArrayList();
    List customerNameList = new ArrayList();
    List plannerNameList = new ArrayList();
    List hybridPlannerNameList = new ArrayList();
    List actShipDateList = new ArrayList();
    List readyToBillDateList = new ArrayList();
    List billedDateList = new ArrayList();
    List actualAndReadyBillDayDiff = new ArrayList();
    List readyBillAndBilledDateDiff = new ArrayList();
    List readyBillAndBilledDayDiff = new ArrayList();
    List actShipMonthYearList = new ArrayList();
    List actShipMonthYearListChk = new ArrayList();
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
        response.setHeader("Content-Disposition", " inline; filename=BillingReport.pdf");

        response.setContentType("application/pdf");
        Document document = new Document();


        String SourcePage = request.getRequestURI().toString();
        SourcePage = SourcePage.substring(SourcePage.lastIndexOf("/") + 1);

        String getActFromDate = request.getParameter("actShipFromDate") != null ? request.getParameter("actShipFromDate") : "";
        String getActToDate = request.getParameter("actShipToDate") != null ? request.getParameter("actShipToDate") : "";
        String plannerId = request.getParameter("planner") != null ? request.getParameter("planner") : "";
        String hybridPlannerId = request.getParameter("hybridPlanner") != null ? request.getParameter("hybridPlanner") : "";
        String numOfBusinesDaysDeduct = request.getParameter("numOfBusinesDaysDeduct") != null ? request.getParameter("numOfBusinesDaysDeduct") : "";


        totalproj = 0;
        int numOfBusinesDaysDeduction = Integer.parseInt(numOfBusinesDaysDeduct);
        double totalProjects = 0;
        double actualAndReadyBillDaysOnTime = 0;
        double readyBillAndBilledDaysOnTime = 0;
        double actualAndReadyBillDaysOnTimePercentage = 0;
        double readyBillAndBilledDaysOnTimePercentage = 0;

        try {

            BillingReportDAO getBillingReport = new BillingReportDAO();
            BillingReportVO setBillingReportValue = new BillingReportVO();

            if (!getActFromDate.equals("") && !getActToDate.equals("")) {
                setBillingReportValue.setActShipDateChk("1");
                setBillingReportValue.setActShipDateFrom(getActFromDate);
                setBillingReportValue.setActShipDateTo(getActToDate);
            }

            if (!plannerId.equals("")) {
                setBillingReportValue.setPlannerId(plannerId);
            }

            if (!hybridPlannerId.equals("")) {
                setBillingReportValue.setHybridPlannerId(hybridPlannerId);
            }

            PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream());

            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            writer.setPageEvent(new BillingReportHeaderFooterDisp());

            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(18, 20, 88, 50);
            document.open();

            addHeader();
            addFooter();
            addOverallHeader();


            PdfContentByte cb = writer.getDirectContent();

            prjoectsHeader.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 125, cb);
            prjoectsFooter.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 470, cb);
            headerDisp.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 100, cb);


            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getPageNumber()), bold),
                    (document.right() - document.left() + 755) / 2 + document.leftMargin(), document.top() - 490, 0);

            PdfPTable contenPdfPTable = new PdfPTable(columnParams);


            int projCount = 0;

            projNameList.clear();
            customerNameList.clear();
            plannerNameList.clear();
            hybridPlannerNameList.clear();
            actShipDateList.clear();
            readyToBillDateList.clear();
            billedDateList.clear();
            actualAndReadyBillDayDiff.clear();
            readyBillAndBilledDateDiff.clear();
            readyBillAndBilledDayDiff.clear();
            actShipMonthYearListChk.clear();

            getBillingReport.getBilledAndReadyToBillProjects(setBillingReportValue);

            projNameList = setBillingReportValue.getProjNameList();
            customerNameList = setBillingReportValue.getCustomerNameList();
            plannerNameList = setBillingReportValue.getPlannerNameList();
            hybridPlannerNameList = setBillingReportValue.getHybridPlannerNameList();
            actShipDateList = setBillingReportValue.getActShipDateList();
            readyToBillDateList = setBillingReportValue.getReadyToBillDateList();
            billedDateList = setBillingReportValue.getBilledDateList();
            actualAndReadyBillDayDiff = setBillingReportValue.getActualAndReadyBillDayDiff();
            readyBillAndBilledDayDiff = setBillingReportValue.getReadyBillAndBilledDayDiff();
            actShipMonthYearList = setBillingReportValue.getActShipMonthYearList();

            int projPrinterFilesSize = projNameList.size();
            totalProjects += projPrinterFilesSize;

            for (int i = 0; i < projPrinterFilesSize; i++) {
                String projName = projNameList.get(i).toString();
                String customerName = customerNameList.get(i).toString();
                String plannerName = plannerNameList.get(i).toString();
                String hybridPlannerName = hybridPlannerNameList.get(i).toString();
                String actShipDate = actShipDateList.get(i).toString();
                String readyToBillDate = readyToBillDateList.get(i).toString();
                String billedDate = billedDateList.get(i).toString();
                String actShipMonthYear = actShipMonthYearList.get(i).toString();
                boolean monthHeaderDisp = false;
                if (actShipMonthYearListChk.isEmpty()) {
                    actShipMonthYearListChk.add(actShipMonthYear);
                    monthHeaderDisp = true;
                } else if (!actShipMonthYearListChk.contains(actShipMonthYear)) {
                    actShipMonthYearListChk.add(actShipMonthYear);
                    monthHeaderDisp = true;
                }

                String actualAndReadyBillDaysDiff = actualAndReadyBillDayDiff.get(i).toString();
                String readyBillAndBilledDaysDiff = readyBillAndBilledDayDiff.get(i).toString();

                int actualAndReadyBillDaysDiffChk = 0;
                int readyBillAndBilledDaysDiffChk = 0;

                if (!actualAndReadyBillDaysDiff.equals("N/A")) {
                    actualAndReadyBillDaysDiffChk = Integer.parseInt(actualAndReadyBillDaysDiff);

                    if (actualAndReadyBillDaysDiffChk < 0) {
                        actualAndReadyBillDaysDiffChk = 0;
                    }

                    if (actualAndReadyBillDaysDiffChk > numOfBusinesDaysDeduction) {
                        actualAndReadyBillDaysDiffChk -= numOfBusinesDaysDeduction;
                    } else {
                        actualAndReadyBillDaysDiffChk = 0;
                    }

                    actualAndReadyBillDaysDiff = String.valueOf(actualAndReadyBillDaysDiffChk);
                    if (actualAndReadyBillDaysDiffChk == 0 || actualAndReadyBillDaysDiffChk < 0) {
                        actualAndReadyBillDaysOnTime++;
                    }
                }
                if (!readyBillAndBilledDaysDiff.equals("N/A")) {
                    readyBillAndBilledDaysDiffChk = Integer.parseInt(readyBillAndBilledDaysDiff);

                    if (readyBillAndBilledDaysDiffChk < 0) {
                        readyBillAndBilledDaysDiffChk = 0;
                    }

                    if (readyBillAndBilledDaysDiffChk > numOfBusinesDaysDeduction) {
                        readyBillAndBilledDaysDiffChk -= numOfBusinesDaysDeduction;
                    } else {
                        readyBillAndBilledDaysDiffChk = 0;
                    }

                    readyBillAndBilledDaysDiff = String.valueOf(readyBillAndBilledDaysDiffChk);
                    if (readyBillAndBilledDaysDiffChk == 0 || readyBillAndBilledDaysDiffChk < 0) {
                        readyBillAndBilledDaysOnTime++;
                    }
                }

              //  if (actualAndReadyBillDaysDiffChk > 0 || readyBillAndBilledDaysDiffChk > 0) {
                    if (monthHeaderDisp) {
                        Phrase catPhrase = new Phrase(actShipMonthYear, headerFont);
                        PdfPCell cellTitle = new PdfPCell(catPhrase);
                        cellTitle.setColspan(9);
                        cellTitle.setBorder(Rectangle.BOX);
                        if (!actShipMonthYear.equals("N/A")) {
                            cellTitle.setBackgroundColor(new BaseColor(254, 241, 236));
                        }
                        if (actShipMonthYear.equals("N/A")) {
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

                    contenPdfPTable.setWidthPercentage(100f);
                    contenPdfPTable.setTotalWidth(800f);

                    PdfPCell cellstat = new PdfPCell(new Phrase(projName, smallNote));
                    cellstat.setFixedHeight(14f);

                    cellstat.setHorizontalAlignment(Element.ALIGN_MIDDLE);
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

                    cellstat = new PdfPCell(new Phrase(actShipDate, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(readyToBillDate, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(actualAndReadyBillDaysDiff, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(billedDate, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(readyBillAndBilledDaysDiff, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);



                    projCount++;

               // }
            }

            PdfPCell grandToalCell = new PdfPCell(new Phrase("\n\n Total = " + projCount + " project/s", small));
            grandToalCell.setColspan(11);
            grandToalCell.setBorder(0);
            contenPdfPTable.addCell(grandToalCell);

            Phrase lineBreak = new Phrase("\n\n\n\n\n");


            if (actualAndReadyBillDaysOnTime != 0) {
                actualAndReadyBillDaysOnTimePercentage = (actualAndReadyBillDaysOnTime * 100) / totalProjects;
                actualAndReadyBillDaysOnTimePercentage = Double.valueOf(decimalFormat.format(actualAndReadyBillDaysOnTimePercentage));
            }

            if (readyBillAndBilledDaysOnTime != 0) {
                readyBillAndBilledDaysOnTimePercentage = (readyBillAndBilledDaysOnTime * 100) / totalProjects;
                readyBillAndBilledDaysOnTimePercentage = Double.valueOf(decimalFormat.format(readyBillAndBilledDaysOnTimePercentage));
            }

            PdfPTable profitSummaryTable = new PdfPTable(columnParams1);
            profitSummaryTable.setWidthPercentage(100f);
            profitSummaryTable.setTotalWidth(600f);

            PdfPCell summaryHeaderCell = new PdfPCell(new Phrase("Billing Report Summary\n\n", headerFont));
            summaryHeaderCell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
            summaryHeaderCell.setColspan(5);
            summaryHeaderCell.setBorder(0);
            profitSummaryTable.addCell(summaryHeaderCell);


            PdfPCell profitSummaryCell = new PdfPCell(new Phrase("Total # Projects", bold));
            profitSummaryCell.setColspan(1);

            profitSummaryCell.setFixedHeight(24f);
            profitSummaryCell.setPadding(5f);
            profitSummaryTable.addCell(profitSummaryCell);


            profitSummaryCell = new PdfPCell(new Phrase("Total # of projects ready to bill on time", bold));
            profitSummaryCell.setPadding(5f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase("% of projects ready to bill on time", bold));
            profitSummaryCell.setPadding(5f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase("Total # of projects billed on time", bold));
            profitSummaryCell.setPadding(5f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase("% of projects billed on time", bold));
            profitSummaryCell.setPadding(5f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell.setColspan(5);

            profitSummaryCell = new PdfPCell(new Phrase(String.valueOf(Math.round(totalProjects)), smallNote));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(50f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase(String.valueOf(Math.round(actualAndReadyBillDaysOnTime)), smallNote));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(50f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase(String.valueOf(actualAndReadyBillDaysOnTimePercentage) + "%", smallNote));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(50f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase(String.valueOf(Math.round(readyBillAndBilledDaysOnTime)), smallNote));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(50f);
            profitSummaryTable.addCell(profitSummaryCell);

            profitSummaryCell = new PdfPCell(new Phrase(String.valueOf(readyBillAndBilledDaysOnTimePercentage) + "%", smallNote));
            profitSummaryCell.setPadding(5f);
            profitSummaryCell.setPaddingLeft(50f);
            profitSummaryTable.addCell(profitSummaryCell);

            document.add(profitSummaryTable);
            document.add(lineBreak);
            document.add(contenPdfPTable);


            document.close();

        } catch (Exception e) {
            System.out.println("Billing Servlet Exception" + e);
        }
    }

    public void addHeader() {
        prjoectsHeader = headerClass.billingReportProjectsFilesHeaderStyle();

    }

    public void addFooter() {

        prjoectsFooter = headerClass.billingReportProjectsFooterStyle();
    }

    public void addOverallHeader() {

        headerDisp = headerClass.overallBillingReportProjectsHeaderStyle();
    }

    public void commonStylingTop(PdfPCell cell) {
        cell.setBorder(Rectangle.TOP);
    }

    public void commonStylingBottom(PdfPCell cell) {
        cell.setBorder(Rectangle.BOTTOM);
    }
}
