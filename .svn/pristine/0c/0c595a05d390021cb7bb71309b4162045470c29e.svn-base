/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import connection.DBconnection;
import java.sql.SQLException;
import java.text.ParseException;
import pathfinder.functions.projects.TitlesToPrinterRpt;
import filters.projects.ProjectActivityReport;
import filters.projects.ProjectActivityReportManipulation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.BaseClass;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import pathfinder.functions.HeaderFooter;
import pathfinder.functions.PlannerProjHeaderFooter;
import pathfinder.functions.PlannerPdfHeader;
import pathfinder.functions.revenue.InvoiceLineItem;

/**
 *
 * @author Aravindhanj
 */
public class ProjectPlannersPdfServlet extends HttpServlet {

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
    protected PdfPTable plannerHeader, plannerFooter, footerStatus, headerDisp, pageNumber;
    protected Phrase phrase;
    float[] columnParams = {5f, 13f, 36f, 22f, 29f, 20f, 17f, 12f};
    float[] columnParams1 = {40f, 80f, 15f, 15f, 10f};
    float[] columnParams2 = {65f, 65f, 15f, 15f, 10f};
    float[] columnParams3 = {65f};
    
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 15,Font.BOLD, BaseColor.BLACK);
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD,BaseColor.RED);
    private static Font greenFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, new BaseColor(0, 102, 0));
    private static Font goldenRedFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD,new BaseColor(184,134,11));
    private static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 9);
    
    
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    
    TitlesToPrinterRpt proj = new TitlesToPrinterRpt();

    List statusList = new ArrayList();
    List projIdList = new ArrayList();
    List projNameList = new ArrayList();
    List authorNameList = new ArrayList();
    List plannerNameList = new ArrayList();
    List plannerList = new ArrayList();
    List customerList = new ArrayList();
    List fpShipDateList = new ArrayList();
    List dueDateList = new ArrayList();
    List estdPagesList = new ArrayList();
    List categoryList = new ArrayList();
    List colorList = new ArrayList();
    List priorityList = new ArrayList();
    List levelList = new ArrayList();
    List trimList= new ArrayList();
    
    int plannerId;
    int totalproj=0;
    int pageSize=0;
    int countLateProj=0;
    int grandTotalSellingVal = 0, grandTotalPartialVal = 0, grandTotalEstdPages = 0;
    int grandTotalProjects = 0, grandTotalPages = 0, totalEstdPages = 0;
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
        response.setHeader("Content-Disposition", " inline; filename=ProjectPlannersDetails.pdf");

        response.setContentType("application/pdf"); // Code 1
        Document document = new Document();
       
        totalEstdPages = 0;
        grandTotalEstdPages = 0;
        grandTotalProjects = 0;
        grandTotalPages = 0;
        grandTotalSellingVal = 0;
        grandTotalPartialVal = 0;
        grandTotalProjects = 0;

        String getDeptParam = request.getParameter("dept_name") != null ? request.getParameter("dept_name") : "";
        String getProjIdParam = request.getParameter("project_id") != null ? request.getParameter("project_id") : "";
        String getPlannerIdParam = request.getParameter("planner_id") != null ? request.getParameter("planner_id") : "";
        String getFromDateParam = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String getToDateParam = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";

        proj.setDeptCode(getDeptParam);
        proj.setProjID(getProjIdParam);
        proj.setProjFromDate(getFromDateParam);
        proj.setProjToDate(getToDateParam);
        proj.setPlannerId(getPlannerIdParam);

        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        Date today = new Date();
        Date currDate = new Date();
        Date dueDateComp;
        String d=sdf.format(today);
        try {
            currDate = (Date)sdf.parse(d);
            
        } catch (ParseException ex) {
            Logger.getLogger(ProjectPlannersPdfServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String dd=(Date)currDate;
        DBconnection db = new DBconnection();
        Connection con = db.getSampleProperty();
        try {
            plannerList = proj.getPlannerNameList(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectPlannersPdfServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
//        if (getProjIdParam.equals("") && getPlannerIdParam.equals("")) {
//            plannerList.add(null);
//        }
        String bc="";
        int countDueDate=0;
        int countFPDate=0;
        int countNoFP=0;
        int check=0;
        totalproj=0;

        int ab=plannerList.size();
       try
       {
            
           PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream());
           
            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            writer.setPageEvent(new PlannerProjHeaderFooter());

            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(18, 20, 88, 50);
            document.open();
            addHeader();
            addFooter();
            addStatus();
            addOverallHeader();
            PdfContentByte cb = writer.getDirectContent();
            //cb.setLineWidth(10f);
            //cb.setLineDash(3, 3, 0);
            //cb.moveTo(100, 700);
            //cb.lineTo(200, 800);

            //cb.stroke();

            
            plannerHeader.writeSelectedRows(0, -1,
            (document.right() - document.left() - 800) /2
            + document.leftMargin(), document.top()+60, cb);
            plannerFooter.writeSelectedRows(0, -1,
            (document.right() - document.left() - 800) /2
            + document.leftMargin(), document.top()-490, cb);
            headerDisp.writeSelectedRows(0, -1,
            (document.right() - document.left() - 800) /2
            + document.leftMargin(), document.top()+84, cb);
            footerStatus.writeSelectedRows(0, -1,
            (document.right() - document.left() - 800) /2
            + document.leftMargin(), document.top()-460, cb);

            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getPageNumber()),bold),
                    (document.right() - document.left()+755)/ 2 + document.leftMargin(), document.top()-490, 0);
            
            proj.setDeptCode(getDeptParam);
            proj.setProjID(getProjIdParam);
            proj.setProjFromDate(getFromDateParam);
            proj.setProjToDate(getToDateParam);
            proj.setPlannerId(getPlannerIdParam);
            if(ab!=0){
            for(int a=0;a<ab;a++)
            {
            bc=(String) plannerList.get(a);
            proj.plannersProjectDetails(bc);
            statusList=proj.getProjectStatusList();
            projIdList=proj.getProjectIdList();
            projNameList=proj.getProjectNameList();
            authorNameList=proj.getProjectAuthorsList();
            plannerNameList=proj.getProjectPlannerList();
            customerList=proj.getProjectCustomersList();
            //fpShipDateList=proj.getProjectFPShipDateList();
            dueDateList=proj.getProjectDueDateList();
            estdPagesList=proj.getProjectEstdPagesList();
            categoryList=proj.getProjectCategoryList();
            colorList=proj.getProjectColorList();
            priorityList=proj.getProjectPriorityList();
            levelList=proj.getProjectLevelList();
            trimList=proj.getProjectTrimSizeList();
       
            totalEstdPages = 0;

            int count=0;
            int size = projIdList.size();
            String name=plannerNameList.get(0).toString();
            String space="";
            countFPDate=0;
            countDueDate=0;
            countNoFP=0;
            countLateProj=0;
  
                PdfPTable titleTable1 = new PdfPTable(1);
                
                Phrase catPhrase = new Phrase(name, blueFont);
                
                PdfPCell cellTitle = new PdfPCell(catPhrase);
                //Paragraph plannerPara = new Paragraph(catPhrase);
                //plannerPara.setAlignment(Phrase.ALIGN_CENTER);
                cellTitle.setBorder(Rectangle.BOX);
                if(!name.equals(space))
                cellTitle.setBackgroundColor(new BaseColor(254, 241, 236));
                if(name.equals("null")||name.equals(space))
                {
                //cellTitle.setBackgroundColor(new BaseColor(255, 69, 0));
                cellTitle.addElement(new Phrase("* Planner not assigned yet for following projects.", goldenRedFont));
                cellTitle.setBorder(0);
                cellTitle.setHorizontalAlignment(Phrase.ALIGN_CENTER);
                }
                cellTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                titleTable1.setWidthPercentage(100f);
                titleTable1.setSpacingAfter(10f);
                titleTable1.setSpacingBefore(10f);
                titleTable1.addCell(cellTitle);
                
                document.add(titleTable1);

             for (i = 0; i <size; i++)
             {
                 String projName = projNameList.get(i).toString();
                 String projIDName = projIdList.get(i).toString();
                 String projStatus = statusList.get(i).toString();
                 String customerName = customerList.get(i).toString();
                 String author_title = authorNameList.get(i).toString();
                 String planner1 = plannerNameList.get(i).toString();
                 String color = colorList.get(i).toString();
                 name = planner1;
                 String trimSize = trimList.get(i).toString();
                 String dueDate = dueDateList.get(i).toString();
                 String category = categoryList.get(i).toString();
                 String priority = priorityList.get(i).toString();
                 String level = levelList.get(i).toString();
                 String fpshipDate=proj.getFPDataeList(projIDName,con);
                 String codingType=proj.getCodingType(projIDName,con);
                //String fpshipDate = fpShipDateList.get(0).toString();
                 String estimated_page = estdPagesList.get(i).toString();
                         if(estimated_page.equals(space))
                         {
                             count++;
                         }
                        else
                         {
                            totalEstdPages+=Integer.parseInt(estimated_page);

                         }
                        
                        String projAndAuthor=author_title+'/'+projName;

                        PdfPTable contenPdfPTable = new PdfPTable(columnParams);
                        contenPdfPTable.setWidthPercentage(100f);
                        contenPdfPTable.setTotalWidth(800);
                        
                        PdfPCell cellstat = new PdfPCell(new Phrase(projStatus, smallNote));

                        cellstat.setColspan(1);
                        //cellstat.setSpaceCharRatio(5f);
                        //cellstat.setBackgroundColor(new BaseColor(224,255,255));
                        
                        //cellstat.setBorder(Rectangle.BOX);
                        cellstat.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                        cellstat.setBorder(Rectangle.TOP|Rectangle.LEFT);cellstat.setFixedHeight(14f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(projIDName, smallNote));
                        cellstat.setBorder(Rectangle.TOP);cellstat.setFixedHeight(14f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(customerName, smallNote));
                        cellstat.setBorder(Rectangle.TOP);cellstat.setFixedHeight(14f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(author_title, smallNote));
                        cellstat.setBorder(Rectangle.TOP);cellstat.setFixedHeight(14f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(projName, smallNote));
                        cellstat.setHorizontalAlignment(Element.ALIGN_TOP);
                        cellstat.setBorder(Rectangle.TOP);cellstat.setFixedHeight(14f);
                        //cellstat.setBackgroundColor(new BaseColor(211,211,211));
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(planner1, smallNote));
                        cellstat.setBorder(Rectangle.TOP);cellstat.setFixedHeight(14f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(category, smallNote));
                        cellstat.setBorder(Rectangle.TOP);cellstat.setFixedHeight(14f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(priority, smallNote));
                        cellstat.setBorder(Rectangle.TOP|Rectangle.RIGHT);cellstat.setFixedHeight(14f);
                        contenPdfPTable.addCell(cellstat);

                        

                        //cellstat = new PdfPCell(new Phrase("----------------------------------------------------------------------------------------------------------------------------------------------------------------", smallNote));
                        Paragraph  p=new Paragraph(new Chunk(new DottedLineSeparator()));
                        p.setFont(small);p.setAlignment(Paragraph.ALIGN_TOP);
                        //p.add(new Chunk(new DottedLineSeparator()));
                        cellstat= new PdfPCell(new Phrase(p));
                        cellstat.setHorizontalAlignment(PdfPCell.ALIGN_TOP);
                        cellstat.setBackgroundColor(new BaseColor(205,201,201));
                        cellstat.setBorder(Rectangle.RIGHT|Rectangle.LEFT);
                        cellstat.setFixedHeight(1.4f);
                        cellstat.setColspan(8);

                        contenPdfPTable.addCell(cellstat);


                        cellstat = new PdfPCell(new Phrase(color, smallNote));
                        cellstat.setBorder(Rectangle.BOTTOM|Rectangle.LEFT);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(trimSize, smallNote));
                        commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(level, smallNote));
                        commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(codingType, smallNote));
                        commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);

                        cellstat = new PdfPCell(new Phrase(estimated_page, smallNote));
                        commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);

                       if(d.equals(dueDate))
                       {
                        check++; 
                       }
                       //System.out.println("FP Date : " + fpshipDate);
                       //System.out.println("Due Date : " + dueDate);
                       //System.out.println("Current Date : " + currDate);
                        if(!fpshipDate.equals(space)&&!dueDate.equals(space))
                        {
                            try
                            {
                            dueDateComp = (Date)sdf1.parse(dueDate);

                        if(currDate.after(dueDateComp))
                        {
                            countLateProj++;
                        cellstat = new PdfPCell(new Phrase(dueDate, redFont));
                        commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);
                        
                        cellstat = new PdfPCell(new Phrase(fpshipDate, smallNote));
                        commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);
                        
                        }
 else
                        {
                        cellstat = new PdfPCell(new Phrase(dueDate, smallNote));
                        commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);
                                
                        cellstat = new PdfPCell(new Phrase(fpshipDate, smallNote));
                        commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);
                       
                        }
                        
                 }
                            catch (ParseException ex) {
            Logger.getLogger(ProjectPlannersPdfServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                 }
                        else

                            {
                            countFPDate++;
                            if(!dueDate.equals(space)) {
                                dueDateComp = (Date)sdf1.parse(dueDate);
                                if(currDate.after(dueDateComp))
                                {
                                    cellstat = new PdfPCell(new Phrase(dueDate, redFont));
                                } else {
                                    cellstat = new PdfPCell(new Phrase(dueDate, greenFont));
                                }
                            } else {
                                cellstat = new PdfPCell(new Phrase(dueDate, greenFont));
                            }
                            commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                            contenPdfPTable.addCell(cellstat);
                        
                            cellstat = new PdfPCell(new Phrase(fpshipDate, smallNote));
                            commonStylingBottom(cellstat);cellstat.setFixedHeight(13f);
                            contenPdfPTable.addCell(cellstat);

                        
                            }

                       if(!dueDate.equals(space)&&fpshipDate.equals(space))
                       {
                           countNoFP++;
                       }
                       if(dueDate.equals(space))
                        {
                        countDueDate++;
                       }
                        cellstat = new PdfPCell(new Phrase(space, smallNote));
                        cellstat.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);cellstat.setFixedHeight(13f);
                        contenPdfPTable.addCell(cellstat);
                        
                        document.add(contenPdfPTable);
                        
             }
                
                grandTotalEstdPages = grandTotalEstdPages + totalEstdPages;
                totalproj+=size;
                if(bc!=null)
                {
                    Phrase catPhrase1 = new Phrase(size + " Project/s assigned to "+name+".      "+totalEstdPages+" pages", small);
                    Paragraph plannerPara1 = new Paragraph(catPhrase1);
                    plannerPara1.setAlignment(Phrase.ALIGN_CENTER);
                    document.add(plannerPara1);
                    if(countNoFP!=0)
                    {
                    Phrase catPhrase3 = new Phrase(countNoFP + " Project/s without an FP Date(Green).", small);
                    Paragraph plannerPara3 = new Paragraph(catPhrase3);
                    document.add(plannerPara3);
                    }
                     if(countDueDate!=0)
                    {
                    Phrase catPhrase4 = new Phrase(countDueDate + " Project/s without a due date.", small);
                    Paragraph plannerPara4 = new Paragraph(catPhrase4);
                    document.add(plannerPara4);
                    }
                    if(countLateProj!=0)
                    {
                    Phrase catPhrase5 = new Phrase(countLateProj + " Project/s late to customer(Red).", small);
                    Paragraph plannerPara5 = new Paragraph(catPhrase5);
                    document.add(plannerPara5);
                    }
            } else{
                    Phrase catPhrase1 = new Phrase("Planners not assigned yet for "+size+" project/s.       "+totalEstdPages+" pages", small);
                    Paragraph plannerPara1 = new Paragraph(catPhrase1);
                    document.add(plannerPara1);
                    if(countNoFP!=0)
                    {
                    Phrase catPhrase3 = new Phrase(countNoFP + " Project/s without FP Date(Green).", small);
                    Paragraph plannerPara3 = new Paragraph(catPhrase3);
                    document.add(plannerPara3);
                    }
                     if(countDueDate!=0)
                    {
                    Phrase catPhrase4 = new Phrase(countDueDate + " Project/s without a due date.", small);
                    Paragraph plannerPara4 = new Paragraph(catPhrase4);
                    document.add(plannerPara4);
                    }
                    if(countLateProj!=0)
                    {
                    Phrase catPhrase5 = new Phrase(countLateProj + " Project/s late to customer(Red).", small);
                    Paragraph plannerPara5 = new Paragraph(catPhrase5);
                    document.add(plannerPara5);
                    }
                    
            }
                    
                    pageSize++;
           }    

           }
                   
                   
                   Phrase catPhrase = new Phrase(grandTotalEstdPages + " Page/s for " + totalproj + " project/s", small);
                   Paragraph plannerPara = new Paragraph(catPhrase);
                   plannerPara.setAlignment(Paragraph.ALIGN_CENTER);
                   PdfPCell cc = new PdfPCell(catPhrase);
                   cc.addElement(plannerPara);
                   
                   document.add(plannerPara);

            document.close();
            
                
            
            
        } catch (Exception e) {
            System.out.println("servlet Exception" + e.toString());
            e.printStackTrace();
        }
        finally
                {
           try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("servlet Exception" + ex.toString());
        }
        }
    }

    
            
    public void addHeader() {
        plannerHeader = headerClass.plannerHeaderStyle(0);
        
    }
    public void addFooter() {

        plannerFooter = headerClass.plannerFooterStyle();
    }

    public void addOverallHeader() {

       headerDisp=headerClass.overallHeaderStyle();
    }

    public void addStatus() throws SQLException {

       footerStatus=headerClass.statusHeaderStyle();
    }
    public void commonStylingTop(PdfPCell cell) {
        cell.setBorder(Rectangle.TOP);
    }

public void commonStylingBottom(PdfPCell cell) {
        cell.setBorder(Rectangle.BOTTOM);
    }

    
}
