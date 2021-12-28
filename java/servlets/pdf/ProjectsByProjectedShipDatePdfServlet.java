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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import connection.DBconnection;
import java.sql.SQLException;
import pathfinder.functions.projects.ProjectsByProjectedShipDate;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Rectangle;
import pathfinder.functions.PlannerPdfHeader;
import pathfinder.functions.ProjShipDateHeaderFooterDisp;

/**
 *
 * @author Parameshwarant
 */
public class ProjectsByProjectedShipDatePdfServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected PdfPTable prjoectsHeader, prjoectsFooter, footerStatus, headerDisp, pageNumber;
    protected Phrase phrase;
    float[] columnParams = {5f, 25f, 12f, 10f, 8f, 6f, 20f, 7f, 7f};
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.RED);
    private static Font greenFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, new BaseColor(0, 102, 0));
    private static Font goldenRedFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, new BaseColor(184, 134, 11));
    private static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 9);
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    ProjectsByProjectedShipDate proj = new ProjectsByProjectedShipDate();
    List statusList = new ArrayList();
    List projIdList = new ArrayList();
    List projNameList = new ArrayList();
    List authorNameList = new ArrayList();
    List plannerNameList = new ArrayList();
    List plannerList = new ArrayList();
    List customerList = new ArrayList();
    List projShipMonthYearList = new ArrayList();
    List shipDateList = new ArrayList();
    List estdPagesList = new ArrayList();
    List categoryList = new ArrayList();
    List projCreatedList = new ArrayList();
    List priorityList = new ArrayList();
    List projTitleList = new ArrayList();
    List projTypeList = new ArrayList();
    List projShipDateMonthList = new ArrayList();
    List projShipDateYearList = new ArrayList();
    List proj_customer_division = new ArrayList();
    List proj_customer_state = new ArrayList();
    int totalproj = 0;
    int pageSize = 0;
    int grandTotalProjects = 0, grandTotalPages = 0, totalEstdPages = 0, grandTotalEstdPages = 0;
    int i, j, k;

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
        response.setHeader("Content-Disposition", " inline; filename=ProjectsByShipDate.pdf");

        response.setContentType("application/pdf");
        Document document = new Document();

        totalEstdPages = 0;
        grandTotalEstdPages = 0;
        grandTotalProjects = 0;
        grandTotalPages = 0;
        grandTotalProjects = 0;

        proj.getProjectedShipDate();
        projShipDateMonthList = proj.getShipDateMonthList();
        projShipDateYearList = proj.getShipDateYearList();

        String projShipDateMonth = "";
        String projShipDateYear = "";

        totalproj = 0;

        int projShipDateSize = projShipDateMonthList.size();
        try {

            PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream());

            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            writer.setPageEvent(new ProjShipDateHeaderFooterDisp());

            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(18, 20, 88, 50);
            document.open();

            addHeader();
            addFooter();
            addOverallHeader();

            PdfContentByte cb = writer.getDirectContent();

            prjoectsHeader.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() + 50, cb);
            prjoectsFooter.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 470, cb);
            headerDisp.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() + 75, cb);


            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getPageNumber()), bold),
                    (document.right() - document.left() + 755) / 2 + document.leftMargin(), document.top() - 490, 0);


            for (int a = 0; a < projShipDateSize; a++) {
                projShipDateMonth = projShipDateMonthList.get(a).toString();
                projShipDateYear = projShipDateYearList.get(a).toString();

                proj.getGetProjectByProjectedShipDate(projShipDateMonth, projShipDateYear);

                projIdList = proj.getProjectIdList();
                projNameList = proj.getProjectNameList();
                plannerNameList = proj.getProjectPlannerList();
                customerList = proj.getProjectCustomersList();
                shipDateList = proj.getProjectShipDateList();
                estdPagesList = proj.getProjectEstdPagesList();
                categoryList = proj.getProjectCategoryList();
                projTitleList = proj.getProjectTitleList();
                projTypeList = proj.getProjectTypeList();
                projCreatedList = proj.getProjectCreatedDateList();
                projShipMonthYearList = proj.getProjectShipMonthYear();
                proj_customer_division=proj.getProjectCustomersDivisionList();
                proj_customer_state=proj.getProjectCustomersStateList();

                totalEstdPages = 0;

                int count = 0;
                int size = projIdList.size();
                String name = projShipMonthYearList.get(0).toString();
                String projShipMonthYear = "";
                String space = "";


                PdfPTable titleTable1 = new PdfPTable(1);

                Phrase catPhrase = new Phrase(name, blueFont);

                PdfPCell cellTitle = new PdfPCell(catPhrase);

                cellTitle.setBorder(Rectangle.BOX);
                if (!name.equals(space)) {
                    cellTitle.setBackgroundColor(new BaseColor(254, 241, 236));
                }

                cellTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                titleTable1.setWidthPercentage(100f);
                titleTable1.setSpacingAfter(10f);
                titleTable1.setSpacingBefore(10f);
                titleTable1.addCell(cellTitle);

                document.add(titleTable1);

                for (i = 0; i < size; i++) {
                    String projName = projNameList.get(i).toString();
                    String projIDName = projIdList.get(i).toString();
                    String projectShipDate = shipDateList.get(i).toString();
                    String customerName = customerList.get(i).toString();
                    customerName+="/"+proj_customer_state.get(i).toString();
                    customerName+="/"+proj_customer_division.get(i).toString();
                    String project_title = projTitleList.get(i).toString();
                    String plannerName = plannerNameList.get(i).toString();
                    String projectType = projTypeList.get(i).toString();
                    projShipMonthYear = projShipMonthYearList.get(i).toString();
                    String projectCreatedDate = projCreatedList.get(i).toString();
                    String category = categoryList.get(i).toString();
                    String estimatedPages = estdPagesList.get(i).toString();

                    if (estimatedPages.equals(space)) {
                        count++;
                    } else {
                        totalEstdPages += Integer.parseInt(estimatedPages);
                    }

                    PdfPTable contenPdfPTable = new PdfPTable(columnParams);
                    contenPdfPTable.setWidthPercentage(100f);
                    contenPdfPTable.setTotalWidth(800f);

                    PdfPCell cellstat = new PdfPCell(new Phrase(projIDName, smallNote));
                    cellstat.setFixedHeight(34f);

                    cellstat.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    cellstat.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(project_title, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(plannerName, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(category, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(projectType, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(estimatedPages, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(customerName, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(projectCreatedDate, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                    contenPdfPTable.addCell(cellstat);

                    cellstat = new PdfPCell(new Phrase(projectShipDate, smallNote));
                    cellstat.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);

                    contenPdfPTable.addCell(cellstat);


                    document.add(contenPdfPTable);

                }


                grandTotalEstdPages = grandTotalEstdPages + totalEstdPages;
                totalproj += size;
                if (projShipDateMonth != null) {

                    Phrase catPhrase1 = new Phrase(size + " Project/s on " + projShipMonthYear + ".      " + totalEstdPages + " pages", small);
                    Paragraph plannerPara1 = new Paragraph(catPhrase1);
                    plannerPara1.setAlignment(Phrase.ALIGN_CENTER);
                    document.add(plannerPara1);
                }

                pageSize++;
            }




            Phrase grandTotalPagesPhrase = new Phrase("\n\n"+grandTotalEstdPages + " Page/s for " + totalproj + " project/s", small);
            Paragraph plannerPara = new Paragraph(grandTotalPagesPhrase);
            plannerPara.setAlignment(Paragraph.ALIGN_CENTER);
            PdfPCell cc = new PdfPCell(grandTotalPagesPhrase);
            cc.addElement(plannerPara);

            document.add(plannerPara);

            document.close();




        } catch (Exception e) {
            System.out.println("servlet Exception" + e.toString());
            e.printStackTrace();
        } 
    }

    public void addHeader() {
        prjoectsHeader = headerClass.projectsHeaderStyle();

    }

    public void addFooter() {

        prjoectsFooter = headerClass.projectsFooterStyle();
    }

    public void addOverallHeader() {

        headerDisp = headerClass.overallProjectsHeaderStyle();
    }

    public void commonStylingTop(PdfPCell cell) {
        cell.setBorder(Rectangle.TOP);
    }

    public void commonStylingBottom(PdfPCell cell) {
        cell.setBorder(Rectangle.BOTTOM);
    }
}
