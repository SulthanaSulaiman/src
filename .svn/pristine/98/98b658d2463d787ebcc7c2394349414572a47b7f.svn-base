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
public class ProjectErrorCostReportPdfservlet extends HttpServlet {

    protected PdfPTable prjoectsHeader, prjoectsFooter, footerStatus, headerDisp, pageNumber;
    protected Phrase phrase;
    float[] columnParams = {10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
    private static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 11);
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    List invoiceMonthYearList = new ArrayList();
    List invoiceMonthList = new ArrayList();
    List invoiceYearList = new ArrayList();
    List invoiceAmountList = new ArrayList();
    List chennaiErrorList = new ArrayList();
    List dubuqueErrorList = new ArrayList();
    List vendorErrorList = new ArrayList();
    List invoicedDate = new ArrayList();
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
        String facilityId = request.getParameter("facility") != null ? request.getParameter("facility") : "";


        String chennaiErrorTimeChk = "";
        String dubuqueErrorTimeChk = "";
        String vendorErrorTimeChk = "";
        double chennaiErrorTime = 0;
        double dubuqueErrorTime = 0;
        double vendorErrorTime = 0;
        double invoiceAmount = 0;
        double chennaiErrorPercentage = 0;
        double dubuqueErrorPercentage = 0;
        double vendorErrorPercentage = 0;


        try {
            ProjectErrorCostInfoDAO getActivityErrorReportValues = new ProjectErrorCostInfoDAO();
            ProjectErrorCostInfoVO setActivityErrorReportValues = new ProjectErrorCostInfoVO();

            if (!getInvoiceFromDate.equals("") && !getInvoiceToDate.equals("")) {
                setActivityErrorReportValues.setInvoiceDateChk("1");
                setActivityErrorReportValues.setInvoiceDateFrom(getInvoiceFromDate);
                setActivityErrorReportValues.setInvoiceDateTo(getInvoiceToDate);
            }


            if (!facilityId.equals("")) {
                setActivityErrorReportValues.setFacilityId(facilityId);
            }

            getActivityErrorReportValues.getFinalInvoicedMonthYear(setActivityErrorReportValues);

            invoiceMonthList = setActivityErrorReportValues.getInvoiceMonthList();
            invoiceYearList = setActivityErrorReportValues.getInvoiceYearList();
            invoiceAmountList = setActivityErrorReportValues.getInvoiceValueList();
            invoiceMonthYearList = setActivityErrorReportValues.getInvoiceMonthYearList();

            String invoiceMonth = "";
            String invoiceYear = "";

            PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream());

            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            writer.setPageEvent(new ProjErrorCostHeaderFooterDisp());

            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(18, 20, 88, 50);
            document.open();

            addHeader();
            addFooter();
            addOverallHeader();


            PdfContentByte cb = writer.getDirectContent();

            prjoectsHeader.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() + 45, cb);
            prjoectsFooter.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 470, cb);
            headerDisp.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() + 70, cb);


            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getPageNumber()), bold),
                    (document.right() - document.left() + 755) / 2 + document.leftMargin(), document.top() - 490, 0);

            PdfPTable contenPdfPTable = new PdfPTable(columnParams);
           
                setActivityErrorReportValues.setFromServletPdf(SourcePage);
                setActivityErrorReportValues.setInvoiceMonth(invoiceMonth);
                setActivityErrorReportValues.setInvoiceYear(invoiceYear);

                getActivityErrorReportValues.getFinalInvoicedProjects(setActivityErrorReportValues);

                chennaiErrorList = setActivityErrorReportValues.getChnErrorList();
                dubuqueErrorList = setActivityErrorReportValues.getDbqErrorList();
                vendorErrorList = setActivityErrorReportValues.getVendorErrorList();
                invoicedDate  = setActivityErrorReportValues.getInvoiceDateList();

                int finalInvoicedProjSize = invoicedDate.size();
                chennaiErrorTime = 0;
                dubuqueErrorTime = 0;
                vendorErrorTime = 0;

                for (int i = 0; i < finalInvoicedProjSize; i++) {

                    chennaiErrorTimeChk = chennaiErrorList.get(i).toString();
                    dubuqueErrorTimeChk = dubuqueErrorList.get(i).toString();
                    vendorErrorTimeChk = vendorErrorList.get(i).toString();
                    invoiceAmount = Double.parseDouble(invoiceAmountList.get(i).toString());

                    if (!chennaiErrorTimeChk.equals("0")) {
                        chennaiErrorTime = Double.parseDouble(chennaiErrorTimeChk);

                    } else {
                        chennaiErrorTime = 0.00;
                    }
                    if (!dubuqueErrorTimeChk.equals("0")) {
                        dubuqueErrorTime = Double.parseDouble(dubuqueErrorTimeChk);

                    } else {
                        dubuqueErrorTime = 0.00;
                    }
                    if (!vendorErrorTimeChk.equals("0")) {
                        vendorErrorTime = Double.parseDouble(vendorErrorTimeChk);

                    } else {
                        vendorErrorTime = 0.00;
                    }
                
                if (chennaiErrorTime > 0 && invoiceAmount > 0) {
                    chennaiErrorPercentage = (chennaiErrorTime * 100) / invoiceAmount;
                } else {
                    chennaiErrorPercentage = 0.00;
                    chennaiErrorTime = 0.00;
                }
                if (dubuqueErrorTime > 0 && invoiceAmount > 0) {
                    dubuqueErrorPercentage = dubuqueErrorTime * 100 / invoiceAmount;
                } else {
                    dubuqueErrorPercentage = 0.00;
                    dubuqueErrorTime = 0.00;
                }

                if (vendorErrorTime > 0 && invoiceAmount > 0) {
                    vendorErrorPercentage = vendorErrorTime * 100 / invoiceAmount;
                } else {
                    vendorErrorPercentage = 0.00;
                    vendorErrorTime = 0.00;
                }


                contenPdfPTable.setWidthPercentage(100f);
                contenPdfPTable.setTotalWidth(800f);

                PdfPCell cellstat = new PdfPCell(new Phrase(invoicedDate.get(i).toString(), headerFont));
                cellstat.setFixedHeight(25f);
                cellstat.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(String.valueOf(invoiceAmount), smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(String.valueOf(decimalFormat.format(dubuqueErrorTime)), smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(String.valueOf(decimalFormat.format(dubuqueErrorPercentage)) + "%", smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(String.valueOf(decimalFormat.format(chennaiErrorTime)), smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(String.valueOf(decimalFormat.format(chennaiErrorPercentage)) + "%", smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);


                cellstat = new PdfPCell(new Phrase(String.valueOf(decimalFormat.format(vendorErrorTime)), smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(String.valueOf(decimalFormat.format(vendorErrorPercentage)) + "%", smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
                contenPdfPTable.addCell(cellstat);


            }

            document.add(contenPdfPTable);
            document.close();

        } catch (Exception e) {
            System.out.println("Project Error Cost Servlet Exception" + e);
        }
    }

    public void addHeader() {
        prjoectsHeader = headerClass.projErrorCostReportHeaderStyle();

    }

    public void addFooter() {

        prjoectsFooter = headerClass.projErrorCostReportFooterStyle();
    }

    public void addOverallHeader() {

        headerDisp = headerClass.overallProjErrorCostReportHeaderStyle();
    }

    public void commonStylingTop(PdfPCell cell) {
        cell.setBorder(Rectangle.TOP);
    }

    public void commonStylingBottom(PdfPCell cell) {
        cell.setBorder(Rectangle.BOTTOM);
    }
}
