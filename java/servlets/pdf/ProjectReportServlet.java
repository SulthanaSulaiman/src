/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import connection.DBconnection;
import filters.projects.ProjectActivityReport;
import filters.projects.ProjectActivityReportManipulation;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.BaseClass;
import pathfinder.functions.HeaderFooter;
import pathfinder.functions.PdfHeader;
import pathfinder.functions.revenue.InvoiceLineItem;

/**
 *
 * @author Aravindhanj
 */
public class ProjectReportServlet extends HttpServlet {

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
    protected PdfPTable header, topMostheader;
    protected Phrase phrase;
    float[] columnParams = {2f, 7f, 16f, 22f, 10f, 3f, 8f, 8f, 8f, 8f, 9f, 7f, 7f};
    float[] columnParams1 = {20f, 20f, 20f, 20f};
    float[] columnParams2 = {15f, 15f, 20f, 20f, 10f};
    String categoryStr = "Books";
    private static Font separateFont = new Font(Font.FontFamily.HELVETICA, 15,
            Font.NORMAL, BaseColor.LIGHT_GRAY);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 15,
            Font.BOLD, BaseColor.BLACK);
    private static Font headerFont1 = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 8);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
    private String previous_category;
    private String current_category;
    //Decimal Format Structure
    NumberFormat numberFormat = new DecimalFormat("###.00");
    NumberFormat integerFormat = new DecimalFormat("###");
    DecimalFormat df = new DecimalFormat("###,###.###");
    PdfHeader headerClass = new PdfHeader();
    ProjectActivityReportManipulation proj = new ProjectActivityReportManipulation();
    BaseClass baseObj = new BaseClass();
    InvoiceLineItem invoiceObj;
    List<String> categories = new ArrayList<String>();
    List<String> monthList = new ArrayList<String>();
    List<List> CategoryMonthList = new ArrayList<List>();
    List categoryIn = new ArrayList();
    List<List> projectReportMonth;
    List<List> projectReportcate;
    List projId = new ArrayList();
    List projName = new ArrayList();
    List customerName = new ArrayList();
    List plannerName = new ArrayList();
    List projCreatedDate = new ArrayList();
    List projEstShipDate = new ArrayList();
    List projActShipDate = new ArrayList();
    List projDueMonthYear = new ArrayList();
    List projDueMonthYearChk = new ArrayList();
    List projCategory = new ArrayList();
    List projCategoryChk = new ArrayList();
    List projEstimateValue = new ArrayList();
    List projInvoiceValue = new ArrayList();
    List projActualPages = new ArrayList();
    List projTrimSize = new ArrayList();
    List projColor = new ArrayList();
    List projAuthors = new ArrayList();
    List projStatus = new ArrayList();
    List projTitle = new ArrayList();
    List projEstimatePages = new ArrayList();

    ProjectActivityReport projectActivityReportObject;
    String string = "";
    String category = "";
    String categoryInChk = "";
    String categoryToDisp = "";
    String invoiceValue = "";
    String estimateValue = "";
    String color = "";
    double totalEstimatedvalue = 0, totalPartialed = 0, grandTotalSellingVal = 0, grandTotalPartialVal = 0, totalInvToDisp = 0, totalEstToDisp = 0;
    int grandTotalProjects = 0, grandTotalPages = 0, totalpages = 0, actual_page = 0, estimate_page = 0, totalPagesToDisp = 0, projSize = 0;
    String facilityId = "";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=report9.pdf");

        response.setContentType("application/pdf"); 
        Document document = new Document();
        
        facilityId = request.getParameter("facility_id")==null?"":request.getParameter("facility_id");
        if(facilityId.equals("1")) {
            category = "'Composition','Full Service'";
        } 
        if(facilityId.equals("2") || facilityId.equals("1,2")) {
            category = "'Composition','Full Service','E2E','Full Service - Hybrid'";
        }

        grandTotalProjects = 0;
        grandTotalPages = 0;
        grandTotalSellingVal = 0;
        grandTotalPartialVal = 0;
        grandTotalProjects = 0;

        DBconnection db = new DBconnection();
        Connection con = db.getSampleProperty();
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream());
            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            writer.setPageEvent(new HeaderFooter());

            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(15, 15, 74, 35);
            document.open();
            topMostheader = createFirstTable(con);
            addHeader();

            PdfContentByte cb = writer.getDirectContent();
            Phrase phraseTitle = new Phrase("Active Project Report", headerFont1);
            PdfPCell cellTitle = new PdfPCell(phraseTitle);
            cellTitle.setBorder(0);
            PdfPTable titleTable = new PdfPTable(1);

            titleTable.setTotalWidth(400);
            titleTable.addCell(cellTitle);

            titleTable.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() + 65, cb);

            topMostheader.writeSelectedRows(0, -1,
                    (document.right() - document.left() + 100) / 2
                    + document.leftMargin(), document.top() + 65, cb);
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getPageNumber()),small),
                    (document.right() - document.left()+755)/ 2 + document.leftMargin(), document.top()-490, 0);
   
            document.add(header);
            categoryIn.clear();
            categoryIn.add("IN");
            categoryIn.add("NOTIN");
            categoryIn.add("NULL");

            
            for (int i = 0; i < categoryIn.size(); i++) {
                
                projId.clear();
                projName.clear();
                customerName.clear();
                plannerName.clear();
                projCreatedDate.clear();
                projEstShipDate.clear();
                projActShipDate.clear();
                projDueMonthYear.clear();
                projDueMonthYearChk.clear();
                projCategory.clear();
                projCategoryChk.clear();
                projEstimateValue.clear();
                projInvoiceValue.clear();
                projActualPages.clear();
                projTrimSize.clear();
                projColor.clear();
                projAuthors.clear();
                projStatus.clear();
                projTitle.clear();
                projEstimatePages.clear();
                categoryInChk = categoryIn.get(i).toString();

                proj.collect(category, facilityId, categoryInChk, con);
                
                projId = proj.getProjId();
                projName = proj.getProjName();
                customerName = proj.getCustomerName();
                plannerName = proj.getPlannerName();
                projCreatedDate = proj.getProjCreatedDate();
                projEstShipDate = proj.getProjEstShipDate();
                projActShipDate = proj.getProjActShipDate();
                projDueMonthYear = proj.getProjDueMonthYear();
                projCategory = proj.getProjCategory();
                projEstimateValue = proj.getProjEstimateValue();
                projInvoiceValue = proj.getProjInvoiceValue();
                projActualPages = proj.getProjActualPages();
                projTrimSize = proj.getProjTrimSize();
                projColor = proj.getProjColor();
                projAuthors = proj.getProjAuthors();
                projStatus = proj.getProjStatus();
                projTitle = proj.getProjTitle();
                projEstimatePages = proj.getProjEstimatePages();
                totalEstimatedvalue = 0;
                totalPartialed = 0;
                totalpages = 0;
                Phrase catPhrase = new Phrase();
                Paragraph categoryPara = new Paragraph();
                PdfPTable rowTable = new PdfPTable(3);
                for (int j = 0; j < projId.size(); j++) {
                    
                    categoryToDisp = category;
                    if(categoryInChk.equals("NULL")&&j==0) {
                        projDueMonthYearChk.clear();
                        catPhrase = new Phrase("* Uncategorized", blueFont);
                        categoryPara = new Paragraph(catPhrase);
                        categoryPara.setAlignment(Phrase.ALIGN_CENTER);
                        document.add(categoryPara);
                    }
                    if(i==0&&j==0&&!projCategoryChk.contains(categoryToDisp)) {
                        projDueMonthYearChk.clear();
                        categoryToDisp=categoryToDisp.replace("\'", "");
                        projCategoryChk.add(categoryToDisp);
                        catPhrase = new Phrase(categoryToDisp, blueFont);
                        categoryPara = new Paragraph(catPhrase);
                        categoryPara.setAlignment(Phrase.ALIGN_CENTER);
                        document.add(categoryPara);
                    } else {
                        String categoryChk = projCategory.get(j).toString();
                        if(i!=0&&!projCategoryChk.contains(categoryChk)) {
                            projDueMonthYearChk.clear();
                            
                            projCategoryChk.add(categoryChk);
                            catPhrase = new Phrase(categoryChk, blueFont);
                            categoryPara = new Paragraph(catPhrase);
                            categoryPara.setAlignment(Phrase.ALIGN_CENTER);
                            if(j!=0&&projSize!=0) {
                                rowTable=addRow(document,totalInvToDisp,totalEstToDisp,totalPagesToDisp,projSize);
                                document.add(rowTable);
                            }
                            document.add(categoryPara);
                        }

                    }
                    
                    string = projDueMonthYear.get(j).toString();
                    
                    if(!projDueMonthYearChk.contains(string)) {
                        projDueMonthYearChk.add(string);
                        Phrase monthPhrase = new Phrase("Projects ending the month of " + string+"\n\n", headerFont);
                        PdfPCell monthCell = new PdfPCell(monthPhrase);
                        PdfPTable monthPdfPTable = new PdfPTable(1);
                        monthPdfPTable.setWidthPercentage(100f);
                        monthPdfPTable.setSpacingBefore(20f);
                        monthPdfPTable.setSpacingAfter(10f);
                        if (!string.equals("")) {
                            monthCell = new PdfPCell(monthPhrase);
                        } else {
                            monthCell = new PdfPCell(new Phrase("* Due date yet to assign\n\n",headerFont));
                        }
                        monthCell.setBorderWidth(0);
                        monthCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    
                        monthPdfPTable.addCell(monthCell);
                        if(j!=0&&projSize!=0) {
                            rowTable=addRow(document,totalInvToDisp,totalEstToDisp,totalPagesToDisp,projSize);
                            document.add(rowTable);
                        }
                        document.add(monthPdfPTable);
                    }
                    invoiceValue = projInvoiceValue.get(j).toString();
                    estimateValue = projEstimateValue.get(j).toString();
                    totalEstToDisp += Double.parseDouble(estimateValue);
                    totalInvToDisp += Double.parseDouble(invoiceValue);
                    totalEstimatedvalue += Double.parseDouble(estimateValue);
                    totalPartialed += Double.parseDouble(invoiceValue);
                    projSize++;
                        color = projColor.get(j).toString();
                        if(!color.equals("")) {
                            color += "/c";
                        }
                        actual_page = Integer.parseInt(projActualPages.get(j).toString());
                        estimate_page = Integer.parseInt(projEstimatePages.get(j).toString());
                        totalPagesToDisp += actual_page !=0 ? actual_page : estimate_page;
                        PdfPTable contenPdfPTable = new PdfPTable(columnParams);
                        contenPdfPTable.setWidthPercentage(100f);

                        PdfPCell cellstat = new PdfPCell(new Phrase(projStatus.get(j).toString(), smallNote));
                        cellstat.setFixedHeight(25f);
                        cellstat.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        
                        cellstat = new PdfPCell(new Phrase(projId.get(j).toString(), smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        
                        cellstat = new PdfPCell(new Phrase(customerName.get(j).toString(), smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);


                        cellstat = new PdfPCell(new Phrase(projAuthors.get(j).toString()+" / "+projTitle.get(j).toString(), smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(plannerName.get(j).toString(), smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);


                        cellstat = new PdfPCell(new Phrase(color, smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(projTrimSize.get(j).toString(), smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(projCreatedDate.get(j).toString(), smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(projEstShipDate.get(j).toString(), smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(projActShipDate.get(j).toString(), smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        if(!estimateValue.equals("0")) {
                            estimateValue = "$ "+estimateValue;
                        } else {
                            estimateValue = "";
                        }
                        
                        cellstat = new PdfPCell(new Phrase(estimateValue, smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        if(!invoiceValue.equals("0")) {
                            invoiceValue = "$ "+invoiceValue;
                        } else {
                            invoiceValue = "";
                        }

                        cellstat = new PdfPCell(new Phrase(invoiceValue, smallNote));
                        cellstat.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                        contenPdfPTable.addCell(cellstat);

                        if (actual_page > 0) {
                            totalpages = totalpages + actual_page;
                            cellstat = new PdfPCell(new Phrase(Integer.toString(actual_page), smallBoldNote));
                            
                        } else {
                            totalpages = totalpages + estimate_page;
                            cellstat = new PdfPCell(new Phrase(Integer.toString(estimate_page), smallNote));
                            
                        }
                        cellstat.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);
                        contenPdfPTable.addCell(cellstat);


                        document.add(contenPdfPTable);
                    }
                    rowTable=addRow(document,totalInvToDisp,totalEstToDisp,totalPagesToDisp,projSize);
                    document.add(rowTable);

                    int totalproj = projId.size();

                    Phrase breakLines = new Phrase("\n");
                    document.add(breakLines);

                    PdfPTable totalTable = new PdfPTable(columnParams2);
                    totalTable.setWidthPercentage(100f);
                    totalTable.setSpacingBefore(5f);
                    
                    PdfPCell totalcellBelow = new PdfPCell(new Phrase("Total", small));
                    totalcellBelow.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
                    totalcellBelow.setMinimumHeight(20f);

                    PdfPCell totalprojPdfcellBelow = new PdfPCell(new Phrase(totalproj + " Project/s", small));
                    totalprojPdfcellBelow.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    

                    PdfPCell totalEstdSellPdfcellBelow = new PdfPCell(new Phrase("Estimate Value $ " + df.format(totalEstimatedvalue), small));
                    totalEstdSellPdfcellBelow.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                    PdfPCell totalPartialPdfcellBelow = new PdfPCell(new Phrase(totalPartialed != 0 ? "Invoice Value $ " + df.format(totalPartialed) : "", small));
                    totalPartialPdfcellBelow.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

                    PdfPCell totalActualPagePdfcellBelow = new PdfPCell(new Phrase(Integer.toString(totalpages)+" pages", small));
                    totalActualPagePdfcellBelow.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);

                    totalTable.addCell(totalcellBelow);
                    totalTable.addCell(totalprojPdfcellBelow);
                    totalTable.addCell(totalEstdSellPdfcellBelow);
                    totalTable.addCell(totalPartialPdfcellBelow);
                    totalTable.addCell(totalActualPagePdfcellBelow);

                    document.add(totalTable);

                    grandTotalProjects = grandTotalProjects + totalproj;
                    grandTotalPages = grandTotalPages + totalpages;
                    grandTotalSellingVal = grandTotalSellingVal + totalEstimatedvalue;
                    grandTotalPartialVal = grandTotalPartialVal + totalPartialed;
            }


            PdfPTable totalTable = new PdfPTable(columnParams2);
            totalTable.setWidthPercentage(100f);
            totalTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalTable.setSpacingBefore(5f);

            Phrase breakLines = new Phrase("\n\n");
            document.add(breakLines);

            PdfPCell monthPdfcellBelow = new PdfPCell(new Phrase("Grand Total", smallBold));
            monthPdfcellBelow.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            monthPdfcellBelow.setMinimumHeight(30f);

            PdfPCell totalprojPdfcellBelow = new PdfPCell(new Phrase(Integer.toString(grandTotalProjects) + " Project/s", smallBold));
            totalprojPdfcellBelow.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

            PdfPCell totalEstdSellPdfcellBelow = new PdfPCell(new Phrase("Estimate Value $  " + df.format(grandTotalSellingVal), smallBold));
            totalEstdSellPdfcellBelow.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

            PdfPCell totalPartialPdfcellBelow = new PdfPCell(new Phrase(grandTotalPartialVal != 0 ? "Invoice Value $  " + df.format(grandTotalPartialVal) : "", smallBold));
            totalPartialPdfcellBelow.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

            PdfPCell totalActualPagePdfcellBelow = new PdfPCell(new Phrase(Integer.toString(grandTotalPages)+" pages", smallBold));
            totalActualPagePdfcellBelow.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);

            totalTable.addCell(monthPdfcellBelow);
            totalTable.addCell(totalprojPdfcellBelow);
            totalTable.addCell(totalEstdSellPdfcellBelow);
            totalTable.addCell(totalPartialPdfcellBelow);
            totalTable.addCell(totalActualPagePdfcellBelow);

            document.add(totalTable);

            document.close();
            con.close();

        } catch (Exception e) {
            System.out.println("servlet Exception" + e.toString());
            e.printStackTrace();
        }

    }

    public void addHeader() {
        header = headerClass.headerStyle(0);
    }

    public void commonStyling(PdfPCell cell) {
        cell.setBorder(0);




    }
    public PdfPTable addRow(Document document, double invValue, double estValue, int pages, int projectSize) throws DocumentException {

            PdfPTable totalTable = new PdfPTable(columnParams2);
            totalTable.setWidthPercentage(100f);
            totalTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalTable.setSpacingBefore(5f);

            PdfPCell subTotalcellBelow = new PdfPCell(new Phrase("Sub Total", headerFont));
            subTotalcellBelow.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            subTotalcellBelow.setMinimumHeight(20f);

            PdfPCell totalProjSizeCellBelow = new PdfPCell(new Phrase(Integer.toString(projectSize)+" project/s", headerFont));
            totalProjSizeCellBelow.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

            PdfPCell totalEstdSellPdfcellBelow = new PdfPCell(new Phrase("Estimate Value $  " + df.format(estValue), headerFont));
            totalEstdSellPdfcellBelow.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

            PdfPCell totalPartialPdfcellBelow = new PdfPCell(new Phrase(invValue!=0 ? "Invoice Value $  " + df.format(invValue) : "", headerFont));
            totalPartialPdfcellBelow.setBorder(Rectangle.TOP | Rectangle.BOTTOM);

            PdfPCell totalActualPagePdfcellBelow = new PdfPCell(new Phrase(Integer.toString(pages)+" pages", headerFont));
            totalActualPagePdfcellBelow.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);

            totalTable.addCell(subTotalcellBelow);
            totalTable.addCell(totalProjSizeCellBelow);
            totalTable.addCell(totalEstdSellPdfcellBelow);
            totalTable.addCell(totalPartialPdfcellBelow);
            totalTable.addCell(totalActualPagePdfcellBelow);
            

            totalPagesToDisp = 0;
            totalInvToDisp = 0;
            totalEstToDisp = 0;
            projSize = 0;

            return totalTable;
    }

    public PdfPTable createFirstTable(Connection con) {
        // a table with three columns
        List<String> statusList = proj.get_statusAliasList(con);
        String status, status_alais;
        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(300);
        table.setSpacingAfter(50);
        // the cell object
        PdfPCell cell;
        Phrase phrase;
        phrase = new Phrase("Job Status Key", subFont);
        cell = new PdfPCell(phrase);
        cell.setBorder(0);
        cell.setColspan(3);
        table.addCell(cell);
        for (String str : statusList) {
            phrase = new Phrase(str, smallNote);
            cell = new PdfPCell(phrase);
            cell.setBorder(0);
            table.addCell(cell);
        }
        for (int i = 1; i <= (statusList.size() % 3); i++) {
            cell = new PdfPCell(new Phrase(""));
            cell.setBorder(0);
            table.addCell(cell);
        }




        return table;
    }

}
