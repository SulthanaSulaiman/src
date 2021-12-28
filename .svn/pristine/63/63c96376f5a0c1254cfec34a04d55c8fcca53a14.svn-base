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
//import java.text.DecimalFormat;
import java.math.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


public class PoSummaryPdf extends HttpServlet {

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
    private static Font smallFont = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
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
        Document document = new Document();

    
      PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            //writer.setPageEvent(new ProjectOnTimeReportHeaderFooterDisp());

            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(18, 20, 88, 50);
            document.open();

            String logoPath = getServletContext().getRealPath("");
            logoPath = logoPath + File.separator + "webimages\\logopng.png";
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(logoPath);
            image.scaleAbsoluteHeight(64);
            image.scaleAbsoluteWidth(211);
            String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";//SearchProjid

            String getProjectName = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
            String vendor_name = request.getParameter("vendor_name") != null ? request.getParameter("vendor_name") : "";

            String vendor_id = request.getParameter("vendor_name_hidden") != null ? request.getParameter("vendor_name_hidden") : "";



            String getFromDateParam = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";//SearchProjid

            String getToDateParam = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";//SearchProjid


            //System.out.println("Vendor Id. :"+vendor_id);
            String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
            java.util.List vendorName= new ArrayList();
            java.util.List createdDate= new ArrayList();
            java.util.List proj_po_name= new ArrayList();
            java.util.List proj_po_number= new ArrayList();
            java.util.List proj_po_itemcode= new ArrayList();
            java.util.List proj_po_date= new ArrayList();
            java.util.List proj_po_description= new ArrayList();
            java.util.List proj_po_rate= new ArrayList();
            java.util.List proj_po_unit= new ArrayList();
            java.util.List proj_po_quantity= new ArrayList();
            java.util.List proj_po_total= new ArrayList();
            java.util.List proj_po_vendorID= new ArrayList();
            java.util.List proj_po_status=new ArrayList();
            java.util.List projPOAlteration = new ArrayList();
            java.util.List projPOReceived = new ArrayList();
            java.util.List projPOReceivedDate = new ArrayList();

           javax.servlet.http.HttpSession session = request.getSession();
        String showTimeStamp = "";
        String showTimeStamp1 = "";
        String emp_fcy = session.getAttribute("empfacility").toString();

        java.util.Date date= new java.util.Date();
        SimpleDateFormat format =new SimpleDateFormat("dd-MMM-yyyy");
        if(emp_fcy == "1" || emp_fcy.equals("1")) {
            //Instead of CURRENT_TIMESTAMP(), TimeStamp will used which has Chennai time.

            showTimeStamp = format.format(date);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, -10);
            calendar.add(Calendar.MINUTE, -30);
            date = calendar.getTime();
            //Instead of CURRENT_TIMESTAMP(), TimeStamp will used which has Dubuque time.

            showTimeStamp = format.format(date);
        }

            pathfinder.functions.revenue.POSummaryInfo poSummaryInfo = new pathfinder.functions.revenue.POSummaryInfo();
            //poSummaryInfo.setProjID(getProjectName);
            poSummaryInfo.setProjID(getProjIdParam);
            //poSummaryInfo.setVendorName(getVendornme);
            poSummaryInfo.setFromDate(getFromDateParam);
            poSummaryInfo.setToDate(getToDateParam);
            poSummaryInfo.setVendorId(vendor_id);
            poSummaryInfo.vendorBillingReport();

            vendorName.clear();
            proj_po_rate.clear();
            proj_po_unit.clear();
            proj_po_quantity.clear();
            proj_po_number.clear();
            proj_po_name.clear();
            proj_po_itemcode.clear();
            proj_po_description.clear();
            proj_po_date.clear();
            proj_po_total.clear();
            proj_po_vendorID.clear();
            proj_po_status.clear();
            projPOAlteration.clear();
            projPOReceived.clear();
            projPOReceivedDate.clear();

            createdDate= poSummaryInfo.getCreatedDate();
            vendorName= poSummaryInfo.getVendorName();
            proj_po_rate=poSummaryInfo.getProjPORate();
            proj_po_unit=poSummaryInfo.getProjPOUnit();
            proj_po_quantity=poSummaryInfo.getProjPOQuantity();
            proj_po_number = poSummaryInfo.getProjPONumber();
            proj_po_name = poSummaryInfo.getProjPOName();
            proj_po_itemcode=poSummaryInfo.getProjPOItemcode();
            proj_po_description=poSummaryInfo.getProjPODesc();
            proj_po_date = poSummaryInfo.getProjPODate();
            proj_po_total=poSummaryInfo.getProjPOTotal();
            proj_po_vendorID=poSummaryInfo.getProjPOVendorID();
            proj_po_status=poSummaryInfo.getProjPOStatus();
            projPOAlteration = poSummaryInfo.getProjPOAlteration();
            projPOReceived = poSummaryInfo.getProjPOReceived();
            projPOReceivedDate = poSummaryInfo.getProjPOReceivedDate();

             PdfPCell tabcell1;
             PdfPCell tabcell2;
             PdfPCell tabcell3;
             PdfPCell tabcell4;
             PdfPCell tabcell5;
             PdfPCell tabcell6;
             PdfPCell tabcell7;
             PdfPCell tabcell8;
             PdfPCell tabcell9;
             PdfPCell tabcellunit;

             PdfPCell po_nameHead;
             PdfPCell projnameHead;
             PdfPCell dateout;
             PdfPCell activtyHead;
             PdfPCell descrHead;
             PdfPCell quantityHead;
             PdfPCell rateHead;
             PdfPCell unitHead;
             PdfPCell valueHead;
             PdfPCell receivedDateHead;
              //Create logo
            String logoPathVendorBillreprt = getServletContext().getRealPath("");
            logoPathVendorBillreprt = logoPathVendorBillreprt + File.separator + "webimages\\logopng.png";
            com.itextpdf.text.Image imagePdf = com.itextpdf.text.Image.getInstance(logoPathVendorBillreprt);
            imagePdf.scaleAbsoluteHeight(64);
            imagePdf.scaleAbsoluteWidth(211);
            //imagePdf.setAbsolutePosition(400f, 700f);
            float[] colsWidth = {1f, 2f, 1f}; // Code 1
            PdfPTable tableImg = new PdfPTable(colsWidth);

            tableImg.setWidthPercentage(100);
            tableImg.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableImg.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new PdfPCell(imagePdf, false));
            cell3.setIndent(60);
            cell3.setMinimumHeight(60f);
            disableBorders(cell3);
            
            PdfPCell cell2 = new PdfPCell(new Paragraph(""));
            
            cell2.setIndent(20);
            cell2.setMinimumHeight(60f);
            disableBorders(cell2);
            PdfPCell cell1 = new PdfPCell(new Paragraph(""));
            PdfPCell cell4 = new PdfPCell(new Paragraph(""));

            cell1.setIndent(20);
            cell1.setMinimumHeight(60f);
            disableBorders(cell1);
            tableImg.addCell(cell1);
            tableImg.addCell(cell3);
            tableImg.addCell(cell2);
            tableImg.addCell(cell4);
            //Create a table.
            
             Paragraph numberPara = new Paragraph();
             Paragraph numberPara1 = new Paragraph();
             Paragraph numberPara2 = new Paragraph();
             Paragraph numberPara3 = new Paragraph();
             Paragraph numberPara4 = new Paragraph();
             Paragraph numberPara5 = new Paragraph();

             numberPara1.add(new Paragraph("\n                           Vendor Billing Report\n", headerFont));
             numberPara1.setAlignment(Element.ALIGN_MIDDLE);
            //numberPara.setAlignment(Element.ALIGN_RIGHT);
            numberPara1.setIndentationLeft(180);
            document.add(tableImg);
            document.add(numberPara1);
            

            numberPara.add(new Paragraph("Vendor: " + vendor_name+"                                                                                                                                                                Date: " + showTimeStamp+" ", subFont));
            numberPara.setAlignment(Element.ALIGN_LEFT);
            //numberPara.setAlignment(Element.ALIGN_RIGHT);
            numberPara.setIndentationLeft(80);
            document.add(numberPara);
            numberPara4.add(new Paragraph("From: " + getFromDateParam+"\nTo: " + getToDateParam+"\n", subFont));
            numberPara4.setAlignment(Element.ALIGN_LEFT);
            //numberPara.setAlignment(Element.ALIGN_RIGHT);
            numberPara4.setIndentationLeft(80);
            document.add(numberPara4);

             
           DecimalFormat format1 = new DecimalFormat("0.00");
            double totalvalu=0.00;

             po_nameHead = new PdfPCell(new Paragraph("PO Number",subFont));
             projnameHead = new PdfPCell(new Paragraph("Project Name",subFont));
             dateout = new PdfPCell(new Paragraph("Date Out",subFont));
             activtyHead = new PdfPCell(new Paragraph("Activity",subFont));
             descrHead = new PdfPCell(new Paragraph("Description",subFont));
             unitHead = new PdfPCell(new Paragraph("Unit",subFont));
             rateHead = new PdfPCell(new Paragraph("Unit Price Rate",subFont));
             quantityHead = new PdfPCell(new Paragraph("Quantity",subFont));
             valueHead= new PdfPCell(new Paragraph("Value",subFont));
             receivedDateHead = new PdfPCell(new Paragraph("Received Date",subFont));
             PdfPTable table;
             //PdfPTable table1;
             table = new PdfPTable(10);
                               table.addCell(po_nameHead);
                               table.addCell(dateout);
                               table.addCell(projnameHead);
                               //table.addCell("Vendor Name");
                               table.addCell(activtyHead);
                               table.addCell(descrHead);
                               table.addCell(receivedDateHead);
                               table.addCell(unitHead);
                               table.addCell(rateHead);
                               table.addCell(quantityHead);
                               table.addCell(valueHead);
             for(int idx=0;idx<proj_po_name.size();idx++){
                double aDouble = Double.parseDouble(proj_po_total.get(idx).toString());
                double aDouble1 = Double.parseDouble(proj_po_quantity.get(idx).toString());
                System.out.println("proj_po_unit.get(idx).toString()"+proj_po_unit.get(idx).toString());
                System.out.println("proj_po_quantity.get(idx).toString()"+format1.format(aDouble1));
                totalvalu+=aDouble;

                               tabcell1 = new PdfPCell(new Paragraph(proj_po_number.get(idx).toString(),smallNote));
                               tabcell2 = new PdfPCell(new Paragraph(createdDate.get(idx).toString(),smallNote));
                               tabcell3 = new PdfPCell(new Paragraph(proj_po_name.get(idx).toString(),smallNote));
                               tabcell4 = new PdfPCell(new Paragraph(proj_po_itemcode.get(idx).toString(),smallNote));
                               tabcell5 = new PdfPCell(new Paragraph(proj_po_description.get(idx).toString(),smallNote));
                               tabcell6 = new PdfPCell(new Paragraph(proj_po_rate.get(idx).toString(),smallNote));
                               tabcellunit = new PdfPCell(new Paragraph(proj_po_unit.get(idx).toString(),smallNote));
                               tabcell7 = new PdfPCell(new Paragraph(format1.format(aDouble1),smallNote));
                               tabcell8 = new PdfPCell(new Paragraph(String.valueOf(aDouble),smallNote));
                               tabcell9 = new PdfPCell(new Paragraph(projPOReceivedDate.get(idx).toString(),smallNote));
                              
                               table.addCell(tabcell1);
                               table.addCell(tabcell2);
                               table.addCell(tabcell3);
                               table.addCell(tabcell4);
                               table.addCell(tabcell5);
                               table.addCell(tabcell9);
                               table.addCell(tabcellunit);
                               tabcell7.setVerticalAlignment(Element.ALIGN_RIGHT);
                               tabcell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
                               table.addCell(tabcell7);
                               tabcell6.setVerticalAlignment(Element.ALIGN_CENTER);
                               tabcell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                               table.addCell(tabcell6);
                               tabcell8.setVerticalAlignment(Element.ALIGN_RIGHT);
                               tabcell8.setHorizontalAlignment(Element.ALIGN_RIGHT);
                               table.addCell(tabcell8);
                    }
                          //DecimalFormat format2 = new DecimalFormat("0.00");
                            document.add(table);
                            numberPara5.add(new Paragraph("                                                                                                                                                                                                                                                              Total Value: "+String.format("%.2f", totalvalu)+"\n", subFont));
                            document.add(numberPara5);
           
           //document.add(table1);
           //document.add(new Paragraph(showTimeStamp,smallFont));
           writer.setPageEmpty(false);
           PdfContentByte cb = writer.getDirectContent();
Phrase footer = new Phrase(showTimeStamp, smallFont);
ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - 8, 0);
           //document.newPage();

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

}
    private static void disableBorders(PdfPCell cell) {
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }
}
