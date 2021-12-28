/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets.pdf;

/**
 *
 * @author Gandhimathidevic
 */

import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import java.text.NumberFormat;
import java.util.*;
import java.text.DecimalFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.*;
import filters.projects.*;
import filters.generic.*;
import pathfinder.functions.contacts.ProjAuthorInfo;
import java.text.SimpleDateFormat;
import java.math.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.RequestDispatcher;


public class BatchTicketReportPdfservlet extends HttpServlet {

    protected PdfPTable prjoectsHeader, prjoectsFooter, footerStatus, headerDisp, pageNumber;
    protected Phrase phrase;
    float[] columnParams = {40f, 25f, 20f, 15f, 15f, 15f};
    float[] columnParams1 = {15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f, 15f};
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
    private static Font blueColorFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 255));
    private static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 9);
   private static Font subFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    PlannerPdfHeader headerClass = new PlannerPdfHeader();



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
        response.setHeader("Content-Disposition", " inline; filename=BatchTicketReport.pdf");

        response.setContentType("application/pdf");
        try {
        //Document document = new Document(PageSize.A4);//new Document(pdf, PageSize.A4);
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);

    String SourcePage = request.getRequestURI().toString();
            SourcePage = SourcePage.substring(SourcePage.lastIndexOf("/") + 1);
            DecimalFormat df3 = new DecimalFormat("000");
            DecimalFormat df2 = new DecimalFormat("00");
            double dispVal = 0;
            String getChapIdParam = request.getParameter("chapId") != null ? request.getParameter("chapId") : "";//SearchProjid
            String getChapNoParam = request.getParameter("chapNo") != null ? request.getParameter("chapNo"):"";
            System.out.println("test"+getChapIdParam);
                 String chapterName = "";
                 String chkBatchID="";
                 String projId="";
                 String chapterNo="";
                 String dispProjName="";
                 String chapterId="";
                 String chapterNumber="";
                 String generatedBatchId="";
                 String getNotes="";
                 String projCategory="";
                 String jobType="";
                 String RevisionNo="";
                 String inDate="";
                 String customer="";
                 String addedDate="";
                 String printedDate="";
                 String division="";
                 String projAuthor="";
                 String projTitle="";
                 String projWorkFlow="";
                 String projStage="";
                 String projColor="";
                 String projTrim="";
                 String projPlanner="";
                 String revisionCount="";
                 String ftpLastUpdatedTimeStamp = "";
                 String ftpLastUpdatedBy = "";
String ProjAllocFlag = "";
String existing_Server_Id="";
String existing_Server_Name="";
String existing_Server_Ip="";
String existing_Server_uname="";
String existing_Server_pword="";
String existing_Server_path="";

     String loopRoleName="";
     String previousRoleName="";
     String projPrinter="";

                java.util.List mileStoneList = new ArrayList();
                java.util.List mileStoneEndDateList = new ArrayList();
                java.util.List projDeliverableList = new ArrayList();

            pathfinder.functions.projects.BatchTicketReport btr= new pathfinder.functions.projects.BatchTicketReport();
            btr.setChapterId(getChapIdParam);
            btr.generateReportFields();
             String getProjIdParam = btr.getProjId();
            generatedBatchId = btr.getGeneratedBatchId();
            getNotes = btr.getNotes();
            mileStoneList = btr.getMileStoneList();
            mileStoneEndDateList = btr.getMileStoneEndDateList();
            ftpLastUpdatedTimeStamp = btr.getFtpLastUpdatedTimeStamp();
            ftpLastUpdatedBy = btr.getFtpLastUpdatedBy();
            pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
            projInfo.setChapterId(getChapIdParam);

            projInfo.collectProjectName();
          //chkBatchID= ;
          projId=projInfo.getProjId();
          chapterNo=projInfo.getChapter_no();
          dispProjName = projInfo.getAuthor();
          chapterId=getChapIdParam;
          chapterNumber=getChapNoParam;
          projCategory= projInfo.getCategory();
          jobType=projInfo.getType();
          customer=projInfo.getClient();
          addedDate=projInfo.getAddedDate();
          division=projInfo.getDivision();
          projAuthor=projInfo.getProjPrimaryAuthorName();//projAuthor
          projTitle=projInfo.getTitle();
          projColor=projInfo.getColorName();
          projWorkFlow = projInfo.getWorkFlowName();
          projStage = projInfo.getStageName();
          projTrim=projInfo.getPageHeight();
          printedDate = projInfo.getProjectedPrinterDt();
          projPlanner=projInfo.getPlannerName();

          //Get Revision
          projInfo.GetRevisionCount();
          revisionCount = projInfo.getRevisionCount();

          projPrinter=projInfo.getPrinter();
          projDeliverableList=projInfo.getProjDeliverable();
          existing_Server_Name=projInfo.getExisting_Server_Name();
          existing_Server_uname=projInfo.getExisting_Server_uname();
          existing_Server_pword=projInfo.getExisting_Server_pword();
          existing_Server_path=projInfo.getExisting_Server_path();


         java.util.List projAuthorFirstNameList = projInfo.getAuthorFirstName();
         java.util.List projAuthorSecondNameList = projInfo.getAuthorSurName();
          String tempAuthName="";
         java.util.List projAuthorNameList = projInfo.getAuthorSurName();


          if(projAuthorFirstNameList.size()>0){
          for(int idx=0;idx<projAuthorFirstNameList.size();idx++){
              tempAuthName = projAuthorFirstNameList.get(idx).toString()+" "+projAuthorSecondNameList.get(idx).toString();
              projAuthorNameList.add(tempAuthName);
          }
         }


     java.util.List teamroleName = new ArrayList();
     java.util.List teamempName = new ArrayList();
     java.util.List teamempId = new ArrayList();
     java.util.List teamprimaryIncharge = new ArrayList();
     java.util.List teamfacilityName = new ArrayList();
     java.util.List teamfacilityId = new ArrayList();
     java.util.List teamMapId = new ArrayList();
     java.util.List projAllocType = new ArrayList();
     java.util.List projCellFacName = new ArrayList();
     java.util.List projCellFacId = new ArrayList();
     java.util.List projCellDept = new ArrayList();
     java.util.List projCellName = new ArrayList();
     java.util.List projCellTeamId = new ArrayList();
 String dispAuthors = "";
     if(!getProjIdParam.equals("")){
     pathfinder.functions.projects.ProjTeamValues ptmv = new pathfinder.functions.projects.ProjTeamValues();
     ptmv.setProjId(getProjIdParam);
     ptmv.projTeamValue();
     teamempId=ptmv.getEmpId();
     teamempName=ptmv.getEmpName();
     teamprimaryIncharge=ptmv.getPrimaryIncharge();
     teamroleName=ptmv.getRoleName();
     teamfacilityId=ptmv.getFacilityId();
     teamfacilityName=ptmv.getFacilityName();
     teamMapId=ptmv.getMapId();
     projAllocType=ptmv.getProjAllocType();
     projCellFacName=ptmv.getProjCellFacilName();
     projCellFacId=ptmv.getProjCellFacilId();
     projCellDept=ptmv.getProjCellDeptName();
     projCellName=ptmv.getProjCellCellName();
     projCellTeamId=ptmv.getProjCellTeamId();
      String[] parts = chapterNo.split(" ");
       
                        ProjAuthorInfo pai = new ProjAuthorInfo();
                        pai.setPrjId(projId);
                        pai.collectAuthorInfo();
                        for(int index=0; index<pai.getPrimaryAuthor().size(); index++) {
                            if(dispAuthors.length()>50) {
                                dispAuthors = dispAuthors+"\n";
                            }
                            dispAuthors = dispAuthors+pai.getContactFirstName().get(index).toString()+" "+pai.getContactSecondName().get(index).toString();
                            if(index < pai.getPrimaryAuthor().size()-1) {
                                dispAuthors = dispAuthors +" / ";
                            }
                        }
                         //System.out.println("Chapter Name : "+parts);
                         if(parts.length > 1){
                                for(int index=0; index < parts.length; index++) {
                                    try
                                    {
                                        getChapNoParam = df3.format(Double.parseDouble(parts[index]));
                                    }
                                    catch(NumberFormatException nfe)
                                    {
                                        chapterName = chapterName + " " + parts[index];
                                    }
                                }
                         }
     }
      PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            //writer.setPageEvent(new ProjectOnTimeReportHeaderFooterDisp());

            //document.setPageSize(PageSize.A4.rotate());
            //document.setMargins(18, 20, 88, 50);
            document.open();

             String logoPath = getServletContext().getRealPath("");
            logoPath = logoPath + File.separator + "webimages\\logopng.png";
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(logoPath);
            image.scaleAbsoluteHeight(64);
            image.scaleAbsoluteWidth(211);

PdfPTable table = new PdfPTable(3); // 3 columns.

          PdfPCell cellimg = new PdfPCell(new PdfPCell(image, false));
          cellimg.setColspan(3);
          disableBorders(cellimg);
          table.addCell(cellimg);
          PdfPCell cellT;
         
          cellT = new PdfPCell(new Phrase("Batch Ticket Report",headerFont));
        cellT.setColspan(3);
        disableBorders(cellT);
        cellT.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellT);
 PdfPCell cellS;
          cellS = new PdfPCell(new Phrase("", subFont));
                  cellS.setColspan(3);
                  disableBorders(cellT);

         table.addCell(cellS);
          
        // the cell object
        PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Stage:                      "+projStage, subFont));
        cell.setColspan(3);
        table.addCell(cell);
        // now we add a cell with rowspan 2
        cell = new PdfPCell(new Phrase("Job:                        "+projId, subFont));
        
        table.addCell(cell);
        // we add the four remaining cells with addCell()

        table.addCell(new Phrase("Component:                 "+chapterName, subFont));
        table.addCell(new Phrase("From:                      "+getChapNoParam+"  to     "+getChapNoParam, subFont));
        table.addCell(new Phrase("Batch #:                  "+generatedBatchId, subFont));
        table.addCell(new Phrase("Category:                     "+projCategory, subFont));
        table.addCell(new Phrase("Revision #:                "+revisionCount, subFont));
        PdfPCell cell1;
        cell1 = new PdfPCell();
        table.addCell(new Phrase("In Date:                   "+addedDate, subFont));
        table.addCell(new Phrase("Customer/Division#:        "+customer+"/"+division, subFont));
        table.addCell(new Phrase("Author(s):                 "+dispAuthors, subFont));
        table.addCell(new Phrase("Title:                       "+projTitle, subFont));
        table.addCell(new Phrase("Color:                     "+projColor, subFont));
        table.addCell(new Phrase("Trim Size:                 "+projTrim, subFont));
        table.addCell(new Phrase("Book Ship Date:          "+printedDate, subFont));
        table.addCell(new Phrase("Planner:                      "+projPlanner, subFont));
        
        table.addCell(cell1);
        
       cell = new PdfPCell(new Phrase("Internal Team:                      ", subFont));
        cell.setColspan(3);
table.addCell(cell);

table.addCell(new Phrase("S4Carlisle Publishing Services-Chennai", subFont));

     for(int id=0;id<projAllocType.size();id++){

         ProjAllocFlag = projAllocType.get(id).toString();
         //System.out.println("projAllocType: "+projAllocType);

     if(ProjAllocFlag.equals("2")) {


     for(int idx=0;idx<teamroleName.size();idx++){
         previousRoleName="";
         if(idx>0){
                previousRoleName=teamroleName.get(idx-1).toString();
         }
         else{

         }

         loopRoleName=teamroleName.get(idx).toString();

         if(previousRoleName.equals(loopRoleName)){

         }
         else{

          teamroleName.get(idx);
         }

    table.addCell(new Phrase("Allocated (Role-wise):                   "+teamMapId.get(idx), subFont));
                    
     if(teamempName.get(idx).toString().equals("")&&teamprimaryIncharge.get(idx).toString().equals("1")){
                   teamfacilityName.get(idx);
                    }
                    else{
        teamfacilityName.get(idx);
                    }

                    if(!teamempName.get(idx).toString().equals("")){
                    if(teamprimaryIncharge.get(idx).toString().equals("1")){
                        teamempName.get(idx);
                    }else{
                        teamempName.get(idx);
                    }
                    }
            }
            }

         if(ProjAllocFlag.equals("1")) {

       
            for(int idx=0;idx<projCellTeamId.size();idx++) {
       

       

           table.addCell(new Phrase("Allocated (Cell-wise):                   "+projCellDept.get(idx), subFont));
                     
           
                }
         }
            }
PdfPCell cell2;
        cell2 = new PdfPCell();
             table.addCell(new Phrase("External Team: S4Carlisle Publishing Services", subFont));
             table.addCell(new Phrase("Download Information:", subFont));
             table.addCell(new Phrase("Printer:"+projPrinter, subFont));
             for(int idx=0;idx<projDeliverableList.size();idx++){
                     table.addCell(new Phrase("Deliverables:"+projDeliverableList.get(idx), subFont));

                                    }
                         
             table.addCell(new Phrase("FTP Information:", subFont));
             table.addCell(new Phrase("Address:"+existing_Server_Name, subFont));
             table.addCell(new Phrase("Remote Path:"+existing_Server_path, subFont));
             table.addCell(new Phrase("Username:"+existing_Server_uname, subFont));
             table.addCell(new Phrase("Password:"+existing_Server_pword, subFont));
              PdfPCell cell3;
        
        cell3 = new PdfPCell(new Phrase("", subFont));
                  cell3.setColspan(3);
                      
         table.addCell(cell3);
PdfPTable nestedTable1 = new PdfPTable(2);
nestedTable1.addCell(new Paragraph("", subFont));
             PdfPTable nestedTable = new PdfPTable(2);
          PdfPCell cellM;
       cellM = new PdfPCell(new Phrase("Milestone and Due Date\n",headerFont));
        cellM.setColspan(3);
        disableBorders(cellM);
        cellM.setHorizontalAlignment(Element.ALIGN_CENTER);
        nestedTable.addCell(cellM);
      
      nestedTable.addCell(new Paragraph("Department", subFont));
      nestedTable.addCell(new Paragraph("Due Date", subFont));
for(int idx=0;idx<mileStoneList.size();idx++){
    nestedTable.addCell(new Phrase(mileStoneList.get(idx).toString(), subFont));
   nestedTable.addCell(new Phrase(mileStoneEndDateList.get(idx).toString(), subFont));
   
            }

      
table.completeRow();
            //Add empty line
document.add(new Paragraph("\n"));
           document.add(table);
           document.add(new Paragraph("\n"));
 writer.setPageEmpty(false);
       // document.newPage();

           document.add(nestedTable);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
RequestDispatcher rd = request.getRequestDispatcher("/generatePayslip.jsp");
  rd.forward(request, response);
}
    private static void disableBorders(PdfPCell cell) {
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }
}
