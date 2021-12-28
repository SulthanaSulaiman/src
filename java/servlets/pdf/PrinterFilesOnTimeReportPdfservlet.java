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
public class PrinterFilesOnTimeReportPdfservlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected PdfPTable prjoectsHeader, prjoectsFooter, footerStatus, headerDisp, pageNumber;
    protected Phrase phrase;
    float[] columnParams = {40f, 25f, 20f, 15f, 15f, 15f};
    float[] columnParams1 = {15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f};
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
    private static Font blueColorFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 255));
    private static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 9);
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    List projNameList = new ArrayList();
    List customerNameList = new ArrayList();
    List plannerNameList = new ArrayList();
    List estShipDateList = new ArrayList();
    List actShipDateList = new ArrayList();
    List dayDiffExceptWeekEndsList = new ArrayList();
    List estShipMonthYearList = new ArrayList();
    List estShipMonthYearListChk = new ArrayList();
    List estShipMonthList = new ArrayList();
    List estShipYearList = new ArrayList();
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
        response.setHeader("Content-Disposition", " inline; filename=PrinterFilesOnTimeReport.pdf");

        response.setContentType("application/pdf");
        Document document = new Document();


        String SourcePage = request.getRequestURI().toString();
        SourcePage = SourcePage.substring(SourcePage.lastIndexOf("/") + 1);

        String getEstFromDate = request.getParameter("estShipFromDate") != null ? request.getParameter("estShipFromDate") : "";
        String getEstToDate = request.getParameter("estShipToDate") != null ? request.getParameter("estShipToDate") : "";
        String facilityId = request.getParameter("facility") != null ? request.getParameter("facility") : "";
        ShippedPrinterFilesDAO getPrinterFiles = new ShippedPrinterFilesDAO();
        ShippedPrinterFilesVO setValues = new ShippedPrinterFilesVO();


        int daysDiffChk = 0;
        int projCount = 0;

        float totalShippedPrinterFiles = 0;
        float onTime = 0;
        float onTimePercentage = 0;
        float earlier = 0;
        float earlierPercentage = 0;
        float oneDayDelay = 0;
        float oneDayDelayPercentage = 0;
        float twoAndThreeDayDelay = 0;
        float twoAndThreeDayDelayPercentage = 0;
        float moreThanThreeDayDelay = 0;
        float moreThanThreeDayDelayPercentage = 0;


        try {
            if (!getEstFromDate.equals("") && !getEstToDate.equals("")) {
                setValues.setEstShipDateChk("1");
                setValues.setEstShipDateFrom(getEstFromDate);
                setValues.setEstShipDateTo(getEstToDate);
            }

            if (!facilityId.equals("")) {
                setValues.setFacilityId(facilityId);
            }

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            writer.setPageEvent(new ProjectOnTimeReportHeaderFooterDisp());

            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(18, 20, 88, 50);
            document.open();

            addHeader();
            addFooter();
            addOverallHeader();


            PdfContentByte cb = writer.getDirectContent();

            prjoectsHeader.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 135, cb);
            prjoectsFooter.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 470, cb);
            headerDisp.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 110, cb);


            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getPageNumber()), bold),
                    (document.right() - document.left() + 755) / 2 + document.leftMargin(), document.top() - 490, 0);

            PdfPTable contenPdfPTable = new PdfPTable(columnParams);

            projNameList.clear();
            customerNameList.clear();
            plannerNameList.clear();
            estShipDateList.clear();
            actShipDateList.clear();
            dayDiffExceptWeekEndsList.clear();
            estShipMonthYearList.clear();
            estShipMonthYearListChk.clear();

            setValues.setFromServletPdf(SourcePage);

            getPrinterFiles.getShippedPrinterFiles(setValues);

            projNameList = setValues.getProjNameList();
            customerNameList = setValues.getCustomerNameList();
            plannerNameList = setValues.getPlannerNameList();
            estShipDateList = setValues.getEstShipDateList();
            actShipDateList = setValues.getActShipDateList();
            dayDiffExceptWeekEndsList = setValues.getDayDiffExceptWeekEndsList();
            estShipMonthYearList = setValues.getEstShipMonthYearList();

            int projPrinterFilesSize = projNameList.size();
            totalShippedPrinterFiles += projPrinterFilesSize;
            for (int i = 0; i < projPrinterFilesSize; i++) {


                String projName = projNameList.get(i).toString();
                String customerName = customerNameList.get(i).toString();
                String plannerName = plannerNameList.get(i).toString();
                String estShipDate = estShipDateList.get(i).toString();
                String actShipDate = actShipDateList.get(i).toString();
                String daysDiff = dayDiffExceptWeekEndsList.get(i).toString();
                String projShipMonthYear = estShipMonthYearList.get(i).toString();
                boolean monthHeaderDisp = false;
                if (estShipMonthYearListChk.isEmpty()) {
                    estShipMonthYearListChk.add(projShipMonthYear);
                    monthHeaderDisp = true;
                } else if (!estShipMonthYearListChk.contains(projShipMonthYear)) {
                    estShipMonthYearListChk.add(projShipMonthYear);
                    monthHeaderDisp = true;
                }

                daysDiff = dayDiffExceptWeekEndsList.get(i).toString();
                daysDiffChk = Integer.parseInt(daysDiff);

                if (daysDiffChk == 0) {
                    onTime++;
                }
                if(daysDiffChk<0 && !actShipDate.equals("N/A")) {
                    earlier++;
                }
                if (daysDiffChk == 1) {
                    oneDayDelay++;
                }
                if (daysDiffChk == 2 || daysDiffChk == 3) {
                    twoAndThreeDayDelay++;
                }
                if (daysDiffChk > 3) {
                    moreThanThreeDayDelay++;
                }


                //if (daysDiffChk != 0) {

                    if (monthHeaderDisp) {
                        if (projPrinterFilesSize != 0) {
                            String space = "";
                            Phrase catPhrase = new Phrase(projShipMonthYear, headerFont);
                            PdfPCell cellTitle = new PdfPCell(catPhrase);
                            cellTitle.setColspan(11);

                            cellTitle.setBorder(Rectangle.BOX);
                            if (!projShipMonthYear.equals(space)) {
                                cellTitle.setBackgroundColor(new BaseColor(254, 241, 236));
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

                    cellstat.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    cellstat.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(customerName, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(plannerName, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(estShipDate, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(actShipDate, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(daysDiff, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
                    contenPdfPTable.addCell(cellstat);

                    projCount++;

               // }

            }

            PdfPCell grandToalCell = new PdfPCell(new Phrase("\n\n Total = " + projCount + " project/s", small));
            grandToalCell.setColspan(11);
            grandToalCell.setBorder(0);
            contenPdfPTable.addCell(grandToalCell);

            Phrase lineBreak = new Phrase("\n\n\n\n\n");



            onTimePercentage = (onTime * 100) / totalShippedPrinterFiles;
            onTimePercentage = Float.valueOf(decimalFormat.format(onTimePercentage));

            earlierPercentage = (earlier * 100) / totalShippedPrinterFiles;
            earlierPercentage = Float.valueOf(decimalFormat.format(earlierPercentage));

            oneDayDelayPercentage = (oneDayDelay * 100) / totalShippedPrinterFiles;
            oneDayDelayPercentage = Float.valueOf(decimalFormat.format(oneDayDelayPercentage));

            twoAndThreeDayDelayPercentage = (twoAndThreeDayDelay * 100) / totalShippedPrinterFiles;
            twoAndThreeDayDelayPercentage = Float.valueOf(decimalFormat.format(twoAndThreeDayDelayPercentage));

            moreThanThreeDayDelayPercentage = (moreThanThreeDayDelay * 100) / totalShippedPrinterFiles;
            moreThanThreeDayDelayPercentage = Float.valueOf(decimalFormat.format(moreThanThreeDayDelayPercentage));

            PdfPTable printerFilesOnTimeSummaryTable = new PdfPTable(columnParams1);
            printerFilesOnTimeSummaryTable.setWidthPercentage(100f);
            printerFilesOnTimeSummaryTable.setTotalWidth(800f);

            PdfPCell summaryHeaderCell = new PdfPCell(new Phrase("Printer Files On Time Report Summary\n\n", headerFont));
            summaryHeaderCell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
            summaryHeaderCell.setColspan(11);
            summaryHeaderCell.setBorder(0);
            printerFilesOnTimeSummaryTable.addCell(summaryHeaderCell);


            PdfPCell printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("Total # Projects", bold));
            printerFilesOnTimeSummaryCell.setFixedHeight(24f);
            printerFilesOnTimeSummaryCell.setBorder(Rectangle.BOX);
            printerFilesOnTimeSummaryCell.setPadding(10f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);


            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("Total # of projects shipped on time", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("% of projects shipped on time", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("Total # of projects shipped earlier", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("% of projects shipped earlier", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("Total # of projects within 1 day", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("% of projects shipped within 1 day", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("Total # of projects within 2-3 day", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("% of projects shipped within 2-3 day", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("Total # of projects more than 3 days", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase("% of projects shipped more than 3 days", bold));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);


            printerFilesOnTimeSummaryCell.setColspan(11);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(Math.round(totalShippedPrinterFiles)), smallNote));
            printerFilesOnTimeSummaryCell.setFixedHeight(20f);
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(Math.round(onTime)), smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(onTimePercentage)+"%", smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(Math.round(earlier)), smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(earlierPercentage)+"%", smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(Math.round(oneDayDelay)), smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(oneDayDelayPercentage)+"%", smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(Math.round(twoAndThreeDayDelay)), smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(twoAndThreeDayDelayPercentage)+"%", smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(Math.round(moreThanThreeDayDelay)), smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);

            printerFilesOnTimeSummaryCell = new PdfPCell(new Phrase(String.valueOf(moreThanThreeDayDelayPercentage)+"%", smallNote));
            printerFilesOnTimeSummaryCell.setPadding(5f);
            printerFilesOnTimeSummaryCell.setPaddingLeft(20f);
            printerFilesOnTimeSummaryTable.addCell(printerFilesOnTimeSummaryCell);


            document.add(printerFilesOnTimeSummaryTable);
            document.add(lineBreak);
            document.add(contenPdfPTable);

            document.close();


        } catch (Exception e) {
            System.out.println("Printer Files on Time Servlet Exception" + e);
        }
    }

    public void addHeader() {
        prjoectsHeader = headerClass.printerFilesOnTimeHeaderStyle();

    }

    public void addFooter() {

        prjoectsFooter = headerClass.printerFilesOnTimeFooterStyle();
    }

    public void addOverallHeader() {

        headerDisp = headerClass.overallPrinterFilesOnTimeHeaderStyle();
    }

    public void commonStylingTop(PdfPCell cell) {
        cell.setBorder(Rectangle.TOP);
    }

    public void commonStylingBottom(PdfPCell cell) {
        cell.setBorder(Rectangle.BOTTOM);
    }
}
