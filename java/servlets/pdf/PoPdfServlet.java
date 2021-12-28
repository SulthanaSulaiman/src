/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.revenue.PODetailsDAO;

/**
 *
 * @author Khan
 */
public class PoPdfServlet extends HttpServlet {
    
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD , BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 9);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 10);
    private static Font smallItalic = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);

    DecimalFormat df = new DecimalFormat("0.##");
    NumberFormat numberFormat = new DecimalFormat("###.00");
    NumberFormat numberFormat3d = new DecimalFormat("###.00#");

    private String previous_category;
    private String current_category;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        javax.servlet.http.HttpSession session = request.getSession();
        pathfinder.util.SpecialCharHandler splChar =new pathfinder.util.SpecialCharHandler();
        //String getVendorId = request.getParameter("vendor_id") != null ? request.getParameter("vendor_id") : "";
        String getVendorId = request.getParameter("vendor_name_hidden") != null ? request.getParameter("vendor_name_hidden") : "";
        String getPoNumber = request.getParameter("PO") != null ? request.getParameter("PO") : "";
        String contactEmpIdForShipAdd = request.getParameter("contactEmp_id") != null ? request.getParameter("contactEmp_id") : "";
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";





        //System.out.println("getPoNumber:" + getPoNumber);
        boolean exp = false;
        String fileName = project_name + "_PO.pdf";
        fileName = fileName.replaceAll(" ", "-");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);

        response.setContentType("application/pdf");

        try {

            //System.out.println("inside generatePOPDf");


            Document document = new Document();

            //PdfWriter.getInstance(document, file);
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            document.open();


            pathfinder.functions.revenue.PODetailsDAO poDetailsDAO = new pathfinder.functions.revenue.PODetailsDAO();
            pathfinder.functions.revenue.PODetailsVO poDetailsVO = new pathfinder.functions.revenue.PODetailsVO();

//            NumberFormat numberFormat = new DecimalFormat("###.00");

            String POFrom = "";
            String POTo = "";
            String POTotal = "";
            ArrayList Number = new ArrayList();
            ArrayList PO = new ArrayList();
            ArrayList FROM = new ArrayList();
            ArrayList TO = new ArrayList();
            ArrayList TOTAL = new ArrayList();
            ArrayList LineItemId = new ArrayList();
            ArrayList POId = new ArrayList();
            ArrayList ProjId = new ArrayList();
            ArrayList ProjDispName = new ArrayList();
            ArrayList CategoryId = new ArrayList();
            ArrayList ActivityName = new ArrayList();
            ArrayList ItemId = new ArrayList();
            ArrayList UnitPrice = new ArrayList();
            ArrayList Quantity = new ArrayList();
            ArrayList UnitName = new ArrayList();
            ArrayList Total = new ArrayList();
            ArrayList currenyList = new ArrayList();
            ArrayList currenySymbol = new ArrayList();
            String currencyString ="";
            String vendorId = "";
            String vendorNumber = "";
            String vendorFirstName = "";
            String vendorSurName = "";
            String vendorAddr1 = "";
            String vendorAddr2 = "";
            String vendorAddr3 = "";
            String vendorCity = "";
            String vendorState = "";
            String vendorCountry = "";
            String vendorPhone = "";
            String vendorFax = "";
            String vendorZipCode = "";
            String vendorEmail = "";
            String displayVendorName = "";
            String splited[];
            String currencyy="";
            String contactNameforShipaddToDisp="";
            String poNotes ="";
            String contactemailaddress = "";
            String emp_idforgetemailaddress = "";
            String phoneNumberContactname = "";
            if(!getPoNumber.equals("")) {
                 poDetailsVO.setVendorId(getVendorId);
                 poDetailsVO.setPoNum(getPoNumber);
                 poDetailsVO = poDetailsDAO.getPODetails(poDetailsVO);
                 currencyString=poDetailsVO.getCurrencyCodeStrng();
                 contactNameforShipaddToDisp=poDetailsVO.getcontactNameforShipaddToDisp();
                 poNotes = poDetailsVO.getlimitedtextareaPoNotesToDisp();
                 if(poNotes.contains("&#39;")) {
                poNotes= poNotes.replaceAll("&#39;", "'");

               }
               if(poNotes.contains("&quot;")){
                poNotes= poNotes.replaceAll("&quot;", "\"");
               }
                 poDetailsVO.setContactIdForGetmailadd(contactEmpIdForShipAdd);
                 //poDetailsVO = poDetailsVO.getEmpIdForgetMailAddress();
                 poDetailsVO = poDetailsDAO.gettingMailaddress(poDetailsVO);
                 //System.out.println("contactemailaddress"+contactemailaddress);
                 contactemailaddress = poDetailsVO.getmailadd();
                 //  System.out.println("contactemailaddress"+contactemailaddress);
                 //System.out.println("contactNameforShipaddToDisp"+contactemailaddress);

if (contactEmpIdForShipAdd.equals("10005")){
phoneNumberContactname = "563-580-4533";
}
            else if(contactEmpIdForShipAdd.equals("10005"))
            {
phoneNumberContactname = "563-564-2793";
}
             else if(contactEmpIdForShipAdd.equals("10001"))
            {
phoneNumberContactname = "(563) 556-6584";
}
            else if(contactEmpIdForShipAdd.equals("10026"))
            {
phoneNumberContactname = "1-303-502-0596";
}
            else if(contactEmpIdForShipAdd.equals("10022"))
            {
phoneNumberContactname = "(920) 279-4485";
}
             else if(contactEmpIdForShipAdd.equals("10029"))
            {
phoneNumberContactname = "563-556-8022";
}
 else{
 if(!contactEmpIdForShipAdd.equals("")){
 phoneNumberContactname = "+91 44 24545411";
    }
 }
                 POFrom = poDetailsVO.getFromDate();
                 if(!POFrom.equals("")) {
                    splited = POFrom.split(" ");
                    if(splited.length > 0) {
                        POFrom = splited[0];
                    }
                 }
                 POTo = poDetailsVO.getToDate();
                 POTotal = poDetailsVO.getGrandTotal();
                 LineItemId = poDetailsVO.getLineItemId();
                 POId = poDetailsVO.getPOId();
                 ProjId = poDetailsVO.getProjId();
                 ProjDispName = poDetailsVO.getProjDispName();
                 CategoryId = poDetailsVO.getCategoryId();
                 ActivityName = poDetailsVO.getActivityName();
                 ItemId = poDetailsVO.getItemId();
                 UnitPrice = poDetailsVO.getUnitPrice();
                 Quantity = poDetailsVO.getQuantity();
                 UnitName = poDetailsVO.getUnit();
                 Total = poDetailsVO.getTotal();
                 poDetailsVO = poDetailsDAO.getCurrencyDetails(poDetailsVO);
                 currenyList=poDetailsVO.getCurrencyCodeList1();
                 //poDetailsVO = poDetailsDAO.getCurrencyDetails(poDetailsVO);
                 currenySymbol=poDetailsVO.getCurrencySymbolList1();


            }

//System.out.println("currencyString"+currencyString);
for(int i=0;i<currenyList.size();i++){

if(currenyList.get(i).toString().equals(currencyString)){
    currencyy=currenySymbol.get(i).toString();
//System.out.println("currency"+currencyy);
}
}
document.add(new Paragraph(" "));
          
            Paragraph namePara = new Paragraph();

            //add PO number
            //Font font1 = new Font(Font.FontFamily.HELVETICA  , 15, Font.BOLD);
            namePara.add(new Paragraph("Purchase Order ", headerFont));
            namePara.setAlignment(Element.ALIGN_RIGHT);
            namePara.setIndentationRight(59);
           // document.add(namePara);

            //Add empty line
          //  document.add(Chunk.NEWLINE);

            Paragraph numberPara = new Paragraph();

            //add PO number
            //Font font2 = new Font(Font.FontFamily.HELVETICA  , 15, Font.NORMAL);
            numberPara.add(new Paragraph("PO Number: " + getPoNumber, headerFont));
            numberPara.setAlignment(Element.ALIGN_RIGHT);
            numberPara.setIndentationRight(45);
           // document.add(numberPara);
          //  document.add(Chunk.NEWLINE);
            Paragraph datePara = new Paragraph();

            //add PO number
            //Font font2 = new Font(Font.FontFamily.HELVETICA  , 15, Font.NORMAL);
            datePara.add(new Paragraph("Date: " + POFrom, headerFont));
            datePara.setAlignment(Element.ALIGN_RIGHT);
            datePara.setIndentationRight(55);
          //  document.add(datePara);
//POFrom
            //Add empty line
//            document.add(Chunk.NEWLINE);


            /* Create table to add logo,customer,carlisle job ie.project name in one row
            From address and terms in second row */

            //Create logo
            String logoPath = getServletContext().getRealPath("");
            logoPath = logoPath + File.separator + "webimages\\logopng.png";
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(logoPath);
            image.scaleAbsoluteHeight(64);
            image.scaleAbsoluteWidth(211);

             PdfPTable table = new PdfPTable(2);
             PdfPCell addlogo = new PdfPCell(image, false);
             addlogo.setMinimumHeight(30f);
             disableBorders(addlogo);
             addlogo.setRowspan(3);
            PdfPCell podetailsadd = new PdfPCell(new Paragraph("Purchase Order", headerFont));
            podetailsadd.setIndent(90);
            disableBorders(podetailsadd);

            PdfPCell emptyCell1 = new PdfPCell(new Paragraph(""));
            emptyCell1.setIndent(90);
            disableBorders(emptyCell1);

            PdfPCell podetailsadd1 = new PdfPCell(new Paragraph("\nPO Number: " + getPoNumber, headerFont));
            podetailsadd1.setIndent(90);
            disableBorders(podetailsadd1);
            PdfPCell podetailsadd2 = new PdfPCell(new Paragraph("\nDate: " + POFrom, headerFont));
            podetailsadd2.setIndent(90);
            disableBorders(podetailsadd2);
            
            //Paragraph detailsOfPo = new Paragraph();
            //Chunk Ponumb = new Chunk(numberPara+"\n", smallNote);
            //Chunk Podate = new Chunk(datePara+"\n", smallNote);
            //Phrase p4= new Phrase();
            //p4.add(Ponumb);
            //p4.add(Podate);
//podetailsadd1.addElement(p4);
            //Create a table.
            float[] colsWidth = {4f, 3f}; // Code 1
            //PdfPTable table = new PdfPTable(colsWidth);

            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell1 = new PdfPCell(new PdfPCell(image, false));
            cell1.setMinimumHeight(30f);
            disableBorders(cell1);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
            cell2.setIndent(20);
            disableBorders(cell2);

            pathfinder.functions.revenue.POSummaryInfo poInfo = new pathfinder.functions.revenue.POSummaryInfo();
            poInfo.setPONumber(getPoNumber);
            ///poInfo.getPOSummary();
            ///java.util.List poDate = poInfo.getPODate();

            pathfinder.functions.revenue.POInfo poDet = new pathfinder.functions.revenue.POInfo();
            poDet.setPONumber(getPoNumber);
            ///poDet.getPOInfo();

            //System.out.println("Vendor ID::::"+getVendorId);
            pathfinder.functions.revenue.PODetailsDAO vendorDet = new pathfinder.functions.revenue.PODetailsDAO();
            vendorDet.setVendorId(getVendorId);
            vendorDet.getVendorDetails(poDetailsVO);
            vendorId = poDetailsVO.getVendorId();
            vendorNumber = poDetailsVO.getVendorNumber();
            vendorFirstName = poDetailsVO.getVendorFirstName();
            vendorSurName = poDetailsVO.getVendorSurName();
            vendorAddr1 = poDetailsVO.getVendorAddr1();
            vendorAddr2 = poDetailsVO.getVendorAddr2();
            vendorCity = poDetailsVO.getVendorCity();
            vendorState=poDetailsVO.getVendorState();
            //System.out.println("vendorAddr2::::"+vendorState);
            if (vendorState.equals("")){
            //vendorState="Zip";
            }
            //if (!vendorAddr2.equals("")){
            //vendorAddr3="      "+vendorAddr2;
            //}
 //else{
   //     vendorAddr3=vendorAddr2;
 //}
            vendorCountry = poDetailsVO.getVendorCountry();
            vendorPhone = poDetailsVO.getVendorPhone();
            vendorFax = poDetailsVO.getVendorFax();
            vendorZipCode = poDetailsVO.getVendorZipCode();
            vendorEmail = poDetailsVO.getVendorEmail();
            displayVendorName = poDetailsVO.getDisplayVendoerName();
//System.out.println(vendorEmail);

            //Get the project properties
            pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
            projInfo.setProjId(getProjIdParam);
            ///projInfo.collectProjectName();


            //Create nested Table
            PdfPTable nestedTable;
            nestedTable = new PdfPTable(2);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

//            PdfPCell cell3 = new PdfPCell(new Paragraph("Date Of PO: ", subFont));
//            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell3.setMinimumHeight(20f);
//            disableBorders(cell3);
//
//            ///PdfPCell cell4 = new PdfPCell(new Paragraph(poDate.get(0).toString(), small));
//            PdfPCell cell4 = new PdfPCell(new Paragraph(POFrom +"\n                to\n"+POTo, small));
//            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell4.setMinimumHeight(20f);
//            disableBorders(cell4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Customer # :", subFont));
            disableBorders(cell5);
            cell5.setMinimumHeight(20f);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);

            ///PdfPCell cell6 = new PdfPCell(new Paragraph(projInfo.getClient(), small));
            PdfPCell cell6 = new PdfPCell(new Paragraph("", small));
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setMinimumHeight(20f);
            disableBorders(cell6);

            PdfPCell cell7 = new PdfPCell(new Paragraph("Carlisle Job # :", subFont));
            cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell7.setMinimumHeight(20f);
            disableBorders(cell7);

            PdfPCell cell8 = new PdfPCell(new Paragraph(project_name, small));
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setMinimumHeight(20f);
            disableBorders(cell8);

            PdfPCell cell9 = new PdfPCell(new Paragraph("Terms: ", subFont));
            cell9.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell9.setMinimumHeight(20f);
            disableBorders(cell9);

            ///PdfPCell cell10 = new PdfPCell(new Paragraph(poDet.getTermsName(), small));
            PdfPCell cell10 = new PdfPCell(new Paragraph("", small));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setMinimumHeight(20f);
            disableBorders(cell10);

//            PdfPCell cell11 = new PdfPCell(new Paragraph("Type: ", subFont));
//            cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell11.setMinimumHeight(20f);
//            disableBorders(cell11);
//
//            PdfPCell cell12 = new PdfPCell(new Paragraph("Purchase Order", small));
//            cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell12.setMinimumHeight(20f);
//            disableBorders(cell12);


             //Buyer address
            //Paragraph buyer = new Paragraph("\n \n 2436 Meinen Court" + "\n" + "Dubuque, IA 52002" + "\n" + "Telephone: (563) 557-1500" + "\n" + "Fax: (563) 557-1376", small);
            //PdfPCell buyerAdd = new PdfPCell(buyer);
            //buyerAdd.setIndent(60);
            //buyerAdd.setHorizontalAlignment(Element.ALIGN_LEFT);
            //buyerAdd.setMinimumHeight(20f);
            //disableBorders(buyerAdd);

            //Buyer address
            ///Paragraph buyer = new Paragraph(poDet.getBuyerAdd() + "\n" + poDet.getBuyerCity() + "" + poDet.getBuyerState() + "\nTelephones: " + poDet.getBuyerPhone() + "\nFax: " + poDet.getBuyerFax(), small);
            ///Paragraph buyer = new Paragraph("2436 Meinen Court," + "\n" + "Dubuque, IA 52002" + "\n" + "Telephone: (563) 557-1500" + "\n" + "Fax: (563) 557-1376", small);
//            Paragraph buyer = new Paragraph("", small);
//            PdfPCell buyerAdd = new PdfPCell(buyer);
//            buyerAdd.setIndent(60);
//            buyerAdd.setHorizontalAlignment(Element.ALIGN_LEFT);
//            buyerAdd.setMinimumHeight(20f);
//            disableBorders(buyerAdd);

//            nestedTable.addCell(cell3);
//            nestedTable.addCell(cell4);
            ///nestedTable.addCell(cell5);
            ///nestedTable.addCell(cell6);
            ///nestedTable.addCell(cell7);
            ///nestedTable.addCell(cell8);

            cell2.addElement(nestedTable);
            //table.addCell(cell1);
            table.addCell(addlogo);
            table.addCell(podetailsadd);
           // table.addCell(emptyCell1);
            table.addCell(podetailsadd1);
            table.addCell(podetailsadd2);
            //table.addCell(podetailsadd3);
           // table.addCell(p4);
            table.addCell(cell2);

            //Create new nested table
            nestedTable = new PdfPTable(2);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            ///nestedTable.addCell(cell9);
            ///nestedTable.addCell(cell10);
//            nestedTable.addCell(cell11);
//            nestedTable.addCell(cell12);

            PdfPCell nestedCell1 = new PdfPCell(nestedTable);
            disableBorders(nestedCell1);

          //  table.addCell(buyerAdd);
            table.addCell(nestedCell1);

            document.add(table);

            //Add empty line
            document.add(Chunk.NEWLINE);

            //Add empty line
            document.add(Chunk.NEWLINE);


            // Add Bill To and Write Transaction
            float[] tableWidth = {0.9f, 3.5f, 3f}; // Code 1
            PdfPTable table2 = new PdfPTable(tableWidth);

            table2.setWidthPercentage(100);

            //Bill To
            PdfPCell billToDet = new PdfPCell(new Paragraph("Bill To: ", subFont));
            billToDet.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(billToDet);

            //Seller Name and street name
            PdfPCell billToSt = new PdfPCell(new Paragraph(poDet.getSellerName() + "\n" + poDet.getSellerAdd() + "\n" + poDet.getSellerCity() + " " + poDet.getSellerState(), small));
            billToSt.setVerticalAlignment(Element.ALIGN_TOP);
            disableBorders(billToSt);

            //Wire Transaction Information
            PdfPCell wireTrans = new PdfPCell(new Paragraph("Wire Transaction Information"+"\n"+ "Dubuque Bank & Trust"+"\n"+ "1398 Central Avenue"+"\n"+ "Dubuque, IA 52001"+"\n"+ "Acct #: 10-760-3"+"\n", small));
            wireTrans.setVerticalAlignment(Element.ALIGN_TOP);
            disableBorders(wireTrans);

            //Author
            PdfPCell author = new PdfPCell(new Paragraph("Author:", subFont));
            author.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(author);

            ///PdfPCell authorVal = new PdfPCell(new Paragraph(projInfo.getAuthor(), small));
            PdfPCell authorVal = new PdfPCell(new Paragraph("", small));
            disableBorders(authorVal);

            PdfPCell copyright = new PdfPCell(new Paragraph("Copyright:", subFont));
            copyright.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(copyright);

            ///PdfPCell copyrightVal = new PdfPCell(new Paragraph(projInfo.getCopyRight(), small));
            PdfPCell copyrightVal = new PdfPCell(new Paragraph("", small));
            disableBorders(copyrightVal);

            PdfPCell title = new PdfPCell(new Paragraph("Title:", subFont));
            title.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(title);

            ///PdfPCell titleVal = new PdfPCell(new Paragraph(projInfo.getTitle(), small));
            PdfPCell titleVal = new PdfPCell(new Paragraph("", small));
            disableBorders(titleVal);



            PdfPCell isbn = new PdfPCell(new Paragraph("ISBN -10/13:", subFont));
            isbn.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(isbn);

            ///PdfPCell isbnVal = new PdfPCell(new Paragraph(projInfo.getISBN10() + " " + projInfo.getISBN13(), small));
            PdfPCell isbnVal = new PdfPCell(new Paragraph("", small));
            disableBorders(isbnVal);

            PdfPCell emptyCell = new PdfPCell();
            disableBorders(emptyCell);

            /*///
            table2.addCell(billToDet);
            table2.addCell(billToSt);
            table2.addCell(wireTrans);

            //to create space . just for alignment
            table2.addCell(emptyCell);
            table2.addCell(emptyCell);
            table2.addCell(emptyCell);
            table2.addCell(emptyCell);
            table2.addCell(emptyCell);
            table2.addCell(emptyCell);
            table2.addCell(emptyCell);
            table2.addCell(emptyCell);
            table2.addCell(emptyCell);

            table2.addCell(author);
            table2.addCell(authorVal);
            table2.addCell(emptyCell);
            table2.addCell(copyright);
            table2.addCell(copyrightVal);
            table2.addCell(emptyCell);
            table2.addCell(title);
            table2.addCell(titleVal);
            table2.addCell(emptyCell);
            table2.addCell(isbn);
            table2.addCell(isbnVal);
            table2.addCell(emptyCell);
            /// */
            document.add(table2);

//            //Add empty line
            

            float[] tableWidth1 = {6.0f, 9.0f}; // Code 1
            PdfPTable tablet = new PdfPTable(tableWidth1); // 3 columns.

            PdfPCell cell1t = new PdfPCell(new Paragraph("To: "+displayVendorName+"",subFont));
            PdfPCell cell1st = new PdfPCell(new Paragraph("Ship To: "  + contactNameforShipaddToDisp + "",subFont));
            PdfPCell cell2t = new PdfPCell(new Paragraph("       "+vendorAddr1,subFont));
            PdfPCell cell2st = new PdfPCell(new Paragraph("               "+phoneNumberContactname  + "",subFont));
            cell1t.setIndent(-30);
            disableBorders(cell1t);
            tablet.addCell(cell1t);
            cell1st.setIndent(60);
            disableBorders(cell1st);
            tablet.addCell(cell1st);
            cell2t.setIndent(-30);
            disableBorders(cell2t);
            tablet.addCell(cell2t);
            cell2st.setIndent(60);
            disableBorders(cell2st);
            tablet.addCell(cell2st);
            PdfPCell cell3t;
            PdfPCell cell3st;
            PdfPCell cell4t;
            PdfPCell cell4st;
                    if (!vendorAddr2.equals(""))
            {
            cell3t = new PdfPCell(new Paragraph("       "+vendorAddr2,subFont));
            cell3st = new PdfPCell(new Paragraph("               "+contactemailaddress  + "",subFont));
            cell3t.setIndent(-30);
            disableBorders(cell3t);
            tablet.addCell(cell3t);
            cell3st.setIndent(60);
            disableBorders(cell3st);
            tablet.addCell(cell3st);
            cell4t = new PdfPCell(new Paragraph("       "+vendorCity+", "+vendorState+" "+vendorZipCode,subFont));
            cell4st = new PdfPCell(new Paragraph("                \n",subFont));
            cell4t.setIndent(-30);
            disableBorders(cell4t);
            tablet.addCell(cell4t);
            cell4st.setIndent(60);
            disableBorders(cell4st);
            tablet.addCell(cell4st);
                    }
 else

            {
            cell3t = new PdfPCell(new Paragraph("       "+vendorCity+", "+vendorState+" "+vendorZipCode,subFont));
            cell3st = new PdfPCell(new Paragraph("               "+contactemailaddress  + "",subFont));
            cell3t.setIndent(-30);
            disableBorders(cell3t);
            tablet.addCell(cell3t);
            cell3st.setIndent(60);
            disableBorders(cell3st);
            tablet.addCell(cell3st);
                                }

            PdfPCell cell6t = new PdfPCell(new Paragraph("       "+vendorCountry+"",subFont));
            PdfPCell cell6st = new PdfPCell(new Paragraph("                \n",subFont));
            cell6t.setIndent(-30);
            disableBorders(cell6t);
            tablet.addCell(cell6t);
            cell6st.setIndent(60);
            disableBorders(cell6st);
            tablet.addCell(cell6st);
            PdfPCell cell7t;
            PdfPCell cell7st;
            if (!vendorPhone.equals(""))
            {
            cell7t = new PdfPCell(new Paragraph("       "+vendorPhone+"",subFont));
            cell7st = new PdfPCell(new Paragraph("                \n",subFont));
            cell7t.setIndent(-30);
            disableBorders(cell7t);
            tablet.addCell(cell7t);
            cell7st.setIndent(60);
            disableBorders(cell7st);
            tablet.addCell(cell7st);
            }
            PdfPCell cell8t;
            PdfPCell cell8st;
            if (!vendorEmail.equals(""))
            {
            cell8t = new PdfPCell(new Paragraph("       "+vendorEmail+"",subFont));
            cell8st = new PdfPCell(new Paragraph("                \n",subFont));
            cell8t.setIndent(-30);
            disableBorders(cell8t);
            tablet.addCell(cell8t);
            cell8st.setIndent(60);
            disableBorders(cell8st);
            tablet.addCell(cell8st);
            }

            document.add(tablet);
          
            

            PdfPTable vendorDetTable = new PdfPTable(2); //Code 3
            vendorDetTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            vendorDetTable.setWidthPercentage(100);
            vendorDetTable.getDefaultCell().setBorder(1);
            PdfPCell vendorCell;

                        //Font vendorFont = new Font(Font.FontFamily.HELVETICA  , 10, Font.BOLD);

            vendorDetTable.getDefaultCell().setBorderWidth(1f);
            vendorCell = new PdfPCell(new Paragraph("To:      \u00a0\u00a0"+displayVendorName, subFont));
            vendorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            vendorCell.setIndent(30);
            vendorCell.setBorderWidth(0f);
            vendorDetTable.addCell(vendorCell);
            vendorDetTable.getDefaultCell().setBorderWidth(1f);
            

            vendorDetTable.getDefaultCell().setBorderWidth(1f);
            vendorCell = new PdfPCell(new Paragraph("Ship To:              "  + contactNameforShipaddToDisp + "\n              \u00a0"  + phoneNumberContactname +"\n                 "+contactemailaddress+"\n", subFont));
            vendorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            vendorCell.setIndent(30);
            vendorCell.setBorderWidth(0f);
            vendorDetTable.addCell(vendorCell);
            vendorDetTable.getDefaultCell().setBorderWidth(1f);
            vendorCell = new PdfPCell(new Paragraph("To:      \u00a0\u00a0"+displayVendorName+"\n      \u00a0\u00a0"+vendorAddr1+"\n"+vendorAddr3+"       \u00a0"+vendorCity+"\n       \u00a0"+vendorState+"-"+vendorZipCode+"\n       \u00a0"+vendorCountry+"\n       \u00a0"+vendorPhone+"\n       \u00a0"+vendorEmail+"\n", subFont));
            vendorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            vendorCell.setIndent(30);
            vendorCell.setBorderWidth(0f);
            vendorDetTable.addCell(vendorCell);
            vendorDetTable.getDefaultCell().setBorderWidth(1f);
//            vendorCell = new PdfPCell(new Paragraph(vendorFirstName+" "+vendorSurName+" ", smallBold));
//            vendorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorCell.setIndent(30);
//            vendorCell.setBorderWidth(0f);
//            vendorDetTable.addCell(vendorCell);

            //Font vendorFont = new Font(Font.FontFamily.HELVETICA  , 10, Font.BOLD);
//            vendorDetTable.getDefaultCell().setBorderWidth(1f);
//            vendorCell = new PdfPCell(new Paragraph("To: \n"+vendorFirstName+" "+vendorSurName+" \n"+vendorAddr1+",  "+vendorCity+" \n"+vendorCountry+" - "+vendorZipCode+" \n", subFont));
//            vendorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            //vendorCell.setIndent(50);
//            vendorCell.setBorderWidth(0f);
//            vendorDetTable.addCell(vendorCell);
//            vendorDetTable.getDefaultCell().setBorderWidth(1f);
//            vendorCell = new PdfPCell(new Paragraph(vendorFirstName+" "+vendorSurName+" ", smallBold));
//            vendorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorCell.setIndent(30);
//            vendorCell.setBorderWidth(0f);
//            vendorDetTable.addCell(vendorCell);
//
//            vendorCell = new PdfPCell(new Paragraph(vendorAddr1+",  "+vendorCity+" ", smallBold));
//            vendorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorCell.setIndent(30);
//            vendorCell.setBorderWidth(0f);
//            vendorDetTable.addCell(vendorCell);
//
//            vendorCell = new PdfPCell(new Paragraph(vendorCountry+" - "+vendorZipCode+" ", smallBold));
//            vendorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorCell.setIndent(30);
//            vendorCell.setBorderWidth(0f);
//            vendorDetTable.addCell(vendorCell);

             ///summTable.addCell(new Paragraph("Status", subFont));

            //Get the table data
            ///summTable.addCell(new Paragraph(poInfo.getPOValue().get(0).toString(), smallBold));
            ///summTable.addCell(new Paragraph(poInfo.getPOStatus().get(0).toString(), smallBold));

//            vendorCell = new PdfPCell(new Paragraph(" $ "+numberFormat.format(Double.parseDouble(POTotal)), subFont));
//            vendorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            vendorCell.setBorderWidth(1f);
//            vendorDetTable.addCell(vendorCell);


            //document.add(vendorDetTable);

            //next line
            document.add(new Paragraph("\n"));
//            //next line
//            document.add(new Paragraph("\n"));
//            //next line
//            document.add(new Paragraph("\n"));

            PdfPTable vendorTable = new PdfPTable(4); //Code 3
            vendorTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            vendorTable.setWidthPercentage(100);
//            vendorTable.getDefaultCell().setBorder(1);

            //vendorDetTable.getDefaultCell().setBorder(1);
            PdfPCell vendorDetCell;

            //Font vendorDetFont = new Font(Font.FontFamily.HELVETICA  , 7, Font.NORMAL);

//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Phone : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
////            vendorDetCell.setIndent(30);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(vendorPhone, smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.disableBorderSide(PdfPCell.TOP);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Ship To Phone : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
////            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(phoneNumberContactname, smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Fax : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(vendorFax, smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Ship To Fax : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Email : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(vendorEmail, smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
//            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Ship To Email : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(contactemailaddress, smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Account No : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Buyer : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Terms : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Requester : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Confirmed By : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Notes :", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("FOB : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("  ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.BOTTOM);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Ship Via : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(" ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph("Order Date : ", smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.RIGHT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//            vendorDetCell = new PdfPCell(new Paragraph(POFrom, smallNote));
//            vendorDetCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            vendorDetCell.disableBorderSide(PdfPCell.TOP);
//            vendorDetCell.disableBorderSide(PdfPCell.LEFT);
////            vendorDetCell.setIndent(30);
////            vendorDetCell.setBorderWidth(0f);
//            vendorTable.addCell(vendorDetCell);
//            vendorTable.getDefaultCell().setBorderWidth(1f);
//
//
//
//            document.add(vendorTable);




//            //Add PO Details
//            Paragraph poSummary = new Paragraph("PO Details", headerFont);
//            poSummary.setIndentationLeft(25);
//            //addEmptyLine(summary,2);
//
//            document.add(poSummary);
//
//            //next line
//            document.add(new Paragraph("\n"));
//
//
//            PdfPTable summTable = new PdfPTable(1); //Code 3
//            summTable.setHorizontalAlignment(Element.ALIGN_LEFT);
//            summTable.setWidthPercentage(30);
//            PdfPCell cell;
//
//            summTable.getDefaultCell().setBorderWidth(1f);
//            cell = new PdfPCell(new Paragraph("PO Value", subFont));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorderWidth(1f);
//            summTable.addCell(cell);
//            ///summTable.addCell(new Paragraph("Status", subFont));
//
//            //Get the table data
//            ///summTable.addCell(new Paragraph(poInfo.getPOValue().get(0).toString(), smallBold));
//            ///summTable.addCell(new Paragraph(poInfo.getPOStatus().get(0).toString(), smallBold));
//
//            cell = new PdfPCell(new Paragraph(" $ "+numberFormat.format(Double.parseDouble(POTotal)), subFont));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorderWidth(1f);
//            summTable.addCell(cell);
//
//            document.add(summTable);




            //Add new page
            //document.newPage();

            //next line
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            

            //next line
//            document.add(new Paragraph("\n"));

//            float[] widths = {3f, 2f}; // Code 1
//
//            PdfPTable lineItem = new PdfPTable(widths); //Code 3
//            lineItem.setWidthPercentage(100);
//            PdfPCell s4logo = new PdfPCell(new Paragraph("S4Carlisle Publishing Services", headerFont));
//            disableBorders(s4logo);
//            Font font3 = new Font(Font.FontFamily.HELVETICA  , 15, Font.NORMAL);
//            PdfPCell poNum = new PdfPCell(new Paragraph("PO Number: " + getPoNumber, font3));
//            disableBorders(poNum);
//
//            lineItem.addCell(s4logo);
//            lineItem.addCell(poNum);
//
//            document.add(lineItem);
//
//            //Table for project properties
//            float[] propColWidth = {0.4f, 2.7f, 0.7f, 1.5f}; // Code 1
//
//            PdfPTable projProp = new PdfPTable(propColWidth);
//            projProp.setWidthPercentage(100);
//            projProp.addCell(author);
//            disableBorders(author);
//            projProp.addCell(authorVal);
//            disableBorders(authorVal);
//            projProp.addCell(copyright);
//            disableBorders(copyright);
//            projProp.addCell(copyrightVal);
//            disableBorders(copyrightVal);
//            projProp.addCell(title);
//            disableBorders(title);
//            projProp.addCell(titleVal);
//            disableBorders(titleVal);
//            projProp.addCell(isbn);
//            disableBorders(isbn);
//            projProp.addCell(isbnVal);
//            disableBorders(isbnVal);
//
//            ///document.add(projProp);
//
//            //next line
//            document.add(new Paragraph("\n"));

//            PdfPTable linetable = new PdfPTable(7); // Code 1
            float[] lineItemsWidths = {5f, 25f, 20f, 15f, 8f, 15f, 13f };
            PdfPTable linetable = new PdfPTable(lineItemsWidths); //Code 3
            linetable.setWidthPercentage(100);
            linetable.setHeaderRows(1);
            linetable.setSplitRows(false);
            linetable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            linetable.getDefaultCell().setPaddingLeft(20f);
            linetable.getDefaultCell().setFixedHeight(50f);

            PdfPCell lineCell;

            lineCell = new PdfPCell(new Paragraph("LN", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            linetable.addCell(lineCell);



            lineCell = new PdfPCell(new Paragraph("Job", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            linetable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Activity", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            linetable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Description", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            linetable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Quantity", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            linetable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Unit Price", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            linetable.addCell(lineCell);

//            lineCell = new PdfPCell(new Paragraph("Quantity", subFont));
//            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            lineCell.setBorderWidth(1f);
//            linetable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Total", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            linetable.addCell(lineCell);

            Double grandTotal = 0.0;
            for (int loop = 0; loop < ItemId.size(); loop++) {

                if (ProjDispName.size() > 0) {

                    current_category = ProjDispName.get(loop).toString();

                    if (current_category.equals(previous_category)) {
                        //do not show the category
                    } else {
                        if (!current_category.equals("")) {
                            //show the category
                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            linetable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            linetable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            linetable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            linetable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            linetable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            linetable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            linetable.addCell(lineCell);


                        }

                    }
                }
                int slno = loop + 1;
                lineCell = new PdfPCell(new Paragraph(""+ slno, small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setPaddingRight(5f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != ItemId.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                linetable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(ProjDispName.get(loop).toString(), small));
                //lineCell.setPaddingLeft(5f);
                lineCell.setPaddingRight(5f);
                //lineCell.setPaddingTop(10f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != ItemId.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                linetable.addCell(lineCell);

//                lineCell = new PdfPCell(new Paragraph(ItemId.get(loop).toString(), small));
//                lineCell.setPaddingLeft(10f);
//                lineCell.disableBorderSide(PdfPCell.TOP);
//                if(loop+1 != ItemId.size())
//                lineCell.disableBorderSide(PdfPCell.BOTTOM);
//                lineCell.setBorderWidth(1f);
//                linetable.addCell(lineCell);

//                lineCell = new PdfPCell(new Paragraph(" ", small));
//                lineCell.setPaddingLeft(10f);
//                lineCell.disableBorderSide(PdfPCell.TOP);
//                if(loop+1 != ItemId.size())
//                lineCell.disableBorderSide(PdfPCell.BOTTOM);
//                lineCell.setBorderWidth(1f);
//                linetable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(ActivityName.get(loop).toString(), small));
                lineCell.setPaddingLeft(10f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != ItemId.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                linetable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(ItemId.get(loop).toString(), small));
                lineCell.setPaddingLeft(10f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != ItemId.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                linetable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(df.format(Double.parseDouble(Quantity.get(loop).toString())), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lineCell.setPaddingRight(5f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != ItemId.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                linetable.addCell(lineCell);
//if(currenyList.get(loop).toString().equals(currencyString)){
  //  currencyy=currenySymbol.get(loop).toString();
    //System.out.println("Cuur1"+currencyString);
    //System.out.println("Cuur"+currencyy);
                lineCell = new PdfPCell(new Paragraph(currencyy+" "+numberFormat3d.format(Double.parseDouble(UnitPrice.get(loop).toString()))+ " \n" +UnitName.get(loop).toString(), small));

    //            }
 //else{
   //                 currencyy="USD";
     //                        lineCell = new PdfPCell(new Paragraph("$ "+numberFormat3d.format(Double.parseDouble(UnitPrice.get(loop).toString()))+ " \n" +UnitName.get(loop).toString(), small));
 //}
                lineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lineCell.setPaddingRight(20f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != ItemId.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                linetable.addCell(lineCell);


//                lineCell = new PdfPCell(new Paragraph(Quantity.get(loop).toString(), small));
//                lineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                lineCell.setPaddingRight(20f);
//                lineCell.disableBorderSide(PdfPCell.TOP);
//                if(loop+1 != ItemId.size())
//                lineCell.disableBorderSide(PdfPCell.BOTTOM);
//                lineCell.setBorderWidth(1f);
//                linetable.addCell(lineCell);
//if(currenyList.get(loop).toString().equals(currencyString)){
  //  currencyy=currenySymbol.get(loop).toString();
    //System.out.println("Cuur1"+currencyString);
    //System.out.println("Cuur"+currencyy);
            lineCell = new PdfPCell(new Paragraph(currencyy+" "+numberFormat.format(Double.parseDouble(Total.get(loop).toString())), small));
//}
 //else{
   //                 currencyy="USD";
     //           lineCell = new PdfPCell(new Paragraph("$ "+numberFormat.format(Double.parseDouble(Total.get(loop).toString())), small));}
                lineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lineCell.setPaddingRight(10f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != ItemId.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                linetable.addCell(lineCell);

                grandTotal += Double.parseDouble(Total.get(loop).toString());
                previous_category = current_category;
            }

            linetable.addCell(emptyCell);
            linetable.addCell(emptyCell);
            linetable.addCell(emptyCell);
            linetable.addCell(emptyCell);
            linetable.addCell(emptyCell);
            linetable.addCell(emptyCell);
//if (currencyy.equals("INR")){
  //          lineCell = new PdfPCell(new Paragraph("INR "+numberFormat.format(grandTotal), subFont));}
 //else{     lineCell = new PdfPCell(new Paragraph("$ "+numberFormat.format(grandTotal), subFont));}
              lineCell = new PdfPCell(new Paragraph(currencyy+" "+numberFormat.format(grandTotal), subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            lineCell.setPaddingRight(10f);
            lineCell.setBorderWidth(1f);
            linetable.addCell(lineCell);

            document.add(linetable);

            //document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
writer.setPageEmpty(false);
        document.newPage();
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
            float[] expectationsWidths = {1f, 10f};
            PdfPTable expectationsTable = new PdfPTable(expectationsWidths);
            expectationsTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            expectationsTable.setWidthPercentage(100);
            expectationsTable.getDefaultCell().setBorder(1);
            PdfPCell expectationsCell;
//System.out.println(poNotes);
if (!poNotes.equals("")){
            expectationsTable.getDefaultCell().setBorderWidth(1f);
            expectationsCell = new PdfPCell(new Paragraph("Project Notes", smallBoldNote));
            expectationsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            expectationsCell.setBorderWidth(0f);
            expectationsCell.setColspan(2);
            expectationsTable.addCell(expectationsCell);
            expectationsTable.getDefaultCell().setBorderWidth(1f);
            expectationsCell = new PdfPCell(new Paragraph(splChar.decoding(poNotes+"\n\n"), smallNote));
            expectationsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            expectationsCell.setBorderWidth(0f);
            expectationsCell.setColspan(2);
            expectationsTable.addCell(expectationsCell);
            expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));
            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
}
            expectationsTable.getDefaultCell().setBorderWidth(1f);
            expectationsCell = new PdfPCell(new Paragraph("PO Agreement Text ", smallBoldNote));
            expectationsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            expectationsCell.setBorderWidth(0f);
            expectationsCell.setColspan(2);
            expectationsTable.addCell(expectationsCell);
            expectationsTable.getDefaultCell().setBorderWidth(1f);

            expectationsCell = new PdfPCell(new Paragraph("The line item(s) listed display S4Carlisle's job number, S4Carlisle's categorization of the requested task, a description of the task with component breakdowns as needed, the agreed upon rate with rate type, and total(s). For projects with partial billings required, each line item on the PO will identify the amounts you should invoice on each partial, with the last one being adjustable if the final total quantity changes.", smallNote));
            //expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
            expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));

            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);


            expectationsTable.getDefaultCell().setBorderWidth(1f);
            expectationsCell = new PdfPCell(new Paragraph("Service Expectations: ", smallBoldNote));
            expectationsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            expectationsCell.setBorderWidth(0f);
            expectationsCell.setColspan(2);
            expectationsTable.addCell(expectationsCell);
            expectationsTable.getDefaultCell().setBorderWidth(1f);

            expectationsCell = new PdfPCell(new Paragraph("1.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("To return materials to S4Carlisle by the dates provided by the Project Manager.  If a date (or dates) cannot be met, please contact the Project Manager immediately.", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("2.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("Any changes to the agreed upon rate must be brought to the attention of the project manager as soon as possible—preferably prior to the start of the project, but we understand that at times it may take getting into a project before you can identify that the work involved exceeds the original expectations.", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("3.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

             Chunk Copyeditors = new Chunk("Copyeditors only", smallItalic);
             Chunk CopyeditorsAftr = new Chunk("—With each deliverable, provide a list of any additional codes/styles that you have identified that were not supplied to you at the start of the project.", smallNote);
             Phrase p2= new Phrase();
             p2.add(Copyeditors);
             p2.add(CopyeditorsAftr);
             expectationsCell = new PdfPCell(p2);
//Phrase Copyeditors = new Phrase("Copyeditors only", smallItalic);

//Chunk world = new Chunk("World",smallItalic);

            //expectationsCell = new PdfPCell(new Paragraph(Copyeditors+"—With each deliverable, provide a list of any additional codes/styles that you have identified that were not supplied to you at the start of the project.", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsTable.getDefaultCell().setBorderWidth(1f);
            expectationsCell = new PdfPCell(new Paragraph("Invoicing Expectations: ", smallBoldNote));
            expectationsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            expectationsCell.setBorderWidth(0f);
            expectationsCell.setColspan(2);
            expectationsTable.addCell(expectationsCell);
            expectationsTable.getDefaultCell().setBorderWidth(1f);

            expectationsCell = new PdfPCell(new Paragraph("1.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("Addressed to S4Carlisle Publishing Services Pte Ltd., 1 Sophia Road, #07-17 Peace Centre Singapore 228149", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("2.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("Your name and contact information", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("3.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("Unique invoice number", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("4.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("S4Carlisle job number", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("5.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("Description of service(s)", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("6.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("Agreed upon rate", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("7.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("Quantity", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("8.", smallNote));
            expectationsCell.setIndent(30);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("Total(s)", smallNote));
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
            Chunk CopyeditorsBefore = new Chunk("\nThe final invoice should be submitted within 15 days of completing the service. ", smallNote);
            Chunk Copyeditors1 = new Chunk("Copyeditors only", smallItalic);
             Chunk CopyeditorsAftr1 = new Chunk("—for projects that have a per book page rate, the final invoice should be submitted within 15 days of receiving the final book page count.", smallNote);
             Phrase p3= new Phrase();
             p3.add(CopyeditorsBefore);
             p3.add(Copyeditors1);
             p3.add(CopyeditorsAftr1);
             expectationsCell = new PdfPCell(p3);
//Phrase Copy

            //expectationsCell = new PdfPCell(new Paragraph("\nThe final invoice should be received within 15 days of completing the service.  Copyeditors only—for projects that have a per book page rate, the final invoice should be received within 15 days of receiving the final book page count.", smallNote));
            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            expectationsCell = new PdfPCell(new Paragraph("Please sign and return a copy of this agreement before starting a project.  Please retain a copy for your records.  Contractor will only be paid for work completed to the satisfaction of S4Carlisle at the rate agreed upon.", smallNote));
            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

             expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));
            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
            expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));

            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
            expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));

            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
            expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));

            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
            expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));

            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
             expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));

            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
              expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));

            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
              expectationsCell = new PdfPCell(new Paragraph("", smallBoldNote));

            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);
            expectationsCell = new PdfPCell(new Paragraph("X_________________________________________________________________", smallBoldNote));
            expectationsCell.setIndent(10);
            expectationsCell.setColspan(2);
            expectationsCell.setBorderWidth(0f);
            expectationsTable.addCell(expectationsCell);

            document.add(expectationsTable);

            document.close();

            /*
            //Display po line items
            float[] lineItemsWidths = {25f, 25f, 20f, 15f, 15f};
            PdfPTable lineTable = new PdfPTable(lineItemsWidths); //Code 3
            lineTable.setWidthPercentage(100);
            lineTable.setHeaderRows(1);
            lineTable.setSplitRows(false);
            lineTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);


            PdfPCell lineCell = new PdfPCell(new Paragraph("Line Items", subFont));
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Unit Rate", subFont));
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Quantity", subFont));
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Total", subFont));
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Description", subFont));
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineTable.getDefaultCell().enableBorderSide(PdfPCell.LEFT);
            lineTable.getDefaultCell().enableBorderSide(PdfPCell.RIGHT);

            lineTable.getDefaultCell().setBorderWidth(1f);
            lineTable.setWidths(lineItemsWidths);


            java.util.List po_item_id = new ArrayList();
            java.util.List po_item_name = new ArrayList();
            java.util.List po_item_rate = new ArrayList();
            java.util.List po_item_quantity = new ArrayList();
            java.util.List po_item_desc = new ArrayList();
            java.util.List po_item_value = new ArrayList();
            java.util.List cost_category_id = new ArrayList();
            java.util.List cost_category_name = new ArrayList();

            //Get the table data
            pathfinder.functions.revenue.POInfo poDetail = new pathfinder.functions.revenue.POInfo();

            //System.out.println("inside after");
            po_item_id.clear();
            po_item_name.clear();
            po_item_rate.clear();
            po_item_quantity.clear();
            po_item_desc.clear();
            po_item_value.clear();
            cost_category_id.clear();
            cost_category_name.clear();

            poDetail.setPONumber(getPoNumber);
            //poDetail.getPOInfo();

            po_item_id = poDetail.getItemId();
            po_item_name = poDetail.getItemName();
            po_item_rate = poDetail.getItemRate();
            po_item_quantity = poDetail.getItemQuantity();
            po_item_desc = poDetail.getItemDesc();
            po_item_value = poDetail.getItemValue();
            cost_category_id = poDetail.getCategoryId();
            cost_category_name = poDetail.getCategoryName();
            //System.out.println("POitem size:"+po_item_id.size());

            previous_category = "";
            current_category = "";
            PdfPCell lastRow;
            for (int loop = 0; loop < po_item_id.size(); loop++) {
                //System.out.println("Inside loop");
                //Display cost category
                if (cost_category_id.size() > 0) {

                    current_category = cost_category_id.get(loop).toString();

                    if (current_category.equals(previous_category)) {
                        //do not show the category
                    } else {
                        if (!current_category.equals("")) {
                            //show the category

                            //lineTable.getDefaultCell().setColspan(5);

                            lineTable.addCell(new Paragraph(cost_category_name.get(loop).toString(), blueFont));
                            lineTable.addCell(new Paragraph(" "));
                            lineTable.addCell(new Paragraph(" "));
                            lineTable.addCell(new Paragraph(" "));
                            lineTable.addCell(new Paragraph(" "));

                        }

                    }
                }
                lineTable.getDefaultCell().setColspan(1);

                System.out.println("loop:" + loop);
                System.out.println("size:" + po_item_id.size());
                if (loop + 1 == po_item_id.size()) {
                    System.out.println("ifnew");
                    lastRow = new PdfPCell(new Paragraph("  " + po_item_name.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);

                    lastRow = new PdfPCell(new Paragraph(po_item_rate.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);


                    lastRow = new PdfPCell(new Paragraph(po_item_quantity.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);

                    lastRow = new PdfPCell(new Paragraph(po_item_value.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);

                    lastRow = new PdfPCell(new Paragraph(po_item_desc.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);


                } else {
                    System.out.println("else");
                    lineTable.addCell(new Paragraph("  " + po_item_name.get(loop).toString(), small));
                    lineTable.addCell(new Paragraph(po_item_rate.get(loop).toString(), small));
                    lineTable.addCell(new Paragraph(po_item_quantity.get(loop).toString(), small));
                    lineTable.addCell(new Paragraph(po_item_value.get(loop).toString(), small));

                    lineTable.addCell(new Paragraph(po_item_desc.get(loop).toString(), small));
                }

                previous_category = current_category;

            }

            lineTable.setSpacingBefore(20);
            document.add(lineTable);
            */


            document.close();


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void topBorders(PdfPCell cell) {
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }

    private static void bottomBorders(PdfPCell cell) {
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
    }

    private static void sideBorders(PdfPCell cell) {
//        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }

    private static void disableBorders(PdfPCell cell) {
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }

    private static void mergeBorders(PdfPCell cell) {
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
    }


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

}
