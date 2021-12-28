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
public class JobLostReportPdfServlet extends HttpServlet {

    protected PdfPTable prjoectsHeader, prjoectsFooter, headerDisp;
    protected Phrase phrase;
    float[] columnParams = {20f, 10f, 15f, 10f, 10f, 10f};
    private static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 10);
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    List jobIdList = new ArrayList();
    List jobTypeList = new ArrayList();
    List customerList = new ArrayList();
    List customerHeaderDispChk = new ArrayList();
    List jobLostReasonList = new ArrayList();
    List jobEstCostList = new ArrayList();
    List jobInvCostList = new ArrayList();

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

        String getProjCreateDateFrom = request.getParameter("projCreateFromDate") != null ? request.getParameter("projCreateFromDate") : "";
        String getProjCreateDateTo = request.getParameter("projCreateToDate") != null ? request.getParameter("projCreateToDate") : "";
        String getProjTypeId = request.getParameter("projTypeId") != null ? request.getParameter("projTypeId") : "";
        String customerName = "";
        try {
            JobLostReportVO setJobLostValue = new JobLostReportVO();
            JobLostReportDAO getJobLostValue = new JobLostReportDAO();

            if (!getProjCreateDateFrom.equals("") && !getProjCreateDateTo.equals("")) {
                setJobLostValue.setProjSearchFlag("1");
                setJobLostValue.setProjCreateDateFrom(getProjCreateDateFrom);
                setJobLostValue.setProjCreateDateTo(getProjCreateDateTo);

            }
            if (!getProjTypeId.equals("")) {
                setJobLostValue.setProjType(getProjTypeId);
            }

            getJobLostValue.collectJobLostInformation(setJobLostValue);

            jobIdList = setJobLostValue.getJobIdList();
            jobTypeList = setJobLostValue.getJobTypeList();
            customerList = setJobLostValue.getCustomerList();
            jobLostReasonList = setJobLostValue.getJobLostReasonList();
            jobEstCostList = setJobLostValue.getJobEstCostList();
            jobInvCostList = setJobLostValue.getJobInvCostList();


            PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream());

            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            writer.setPageEvent(new JobLostReportVO());

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

            int projIdSize = jobIdList.size();
            customerHeaderDispChk.clear();
            
            for (int i = 0; i < projIdSize; i++) {
                customerName = customerList.get(i).toString();
                if (!customerHeaderDispChk.contains(customerName)) {
                    customerHeaderDispChk.add(customerName);
                    Phrase catPhrase = new Phrase(customerName, headerFont);
                    PdfPCell cellTitle = new PdfPCell(catPhrase);
                    cellTitle.setColspan(10);
                    cellTitle.setBorder(Rectangle.BOX);
                    cellTitle.setBackgroundColor(new BaseColor(254, 241, 236));
                    cellTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    contenPdfPTable.addCell(cellTitle);
                }
                

                contenPdfPTable.setWidthPercentage(100f);
                contenPdfPTable.setTotalWidth(800f);

                PdfPCell cellstat = new PdfPCell(new Phrase(jobIdList.get(i).toString(), smallNote));
                cellstat.setFixedHeight(25f);
                cellstat.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(jobTypeList.get(i).toString(), smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(customerName, smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(jobEstCostList.get(i).toString(), smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(jobInvCostList.get(i).toString(), smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                contenPdfPTable.addCell(cellstat);

                cellstat = new PdfPCell(new Phrase(jobLostReasonList.get(i).toString(), smallNote));
                cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
                contenPdfPTable.addCell(cellstat);

            }

            PdfPCell grandToalCell = new PdfPCell(new Phrase("\n\n Total = " + projIdSize + " project/s", smallNote));
            grandToalCell.setColspan(11);
            grandToalCell.setBorder(0);
            contenPdfPTable.addCell(grandToalCell);

            document.add(contenPdfPTable);
            document.close();

        } catch (Exception e) {
            System.out.println("Job Lost Report Servlet Exception" + e);
        }
    }

    public void addHeader() {
        prjoectsHeader = headerClass.jobLostReportHeaderStyle();
    }

    public void addFooter() {
        prjoectsFooter = headerClass.jobLostReportFooterStyle();
    }

    public void addOverallHeader() {
        headerDisp = headerClass.overallJobLostReportHeaderStyle();
    }
}
