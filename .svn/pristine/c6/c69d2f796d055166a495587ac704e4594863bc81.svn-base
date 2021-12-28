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
import pathfinder.functions.contacts.ProjAuthorInfo;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
/**
 *
 * @author Aravindhanj
 */
public class BidProjEstimationPdfServlet extends HttpServlet {

    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 13,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.NORMAL, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD | Font.UNDERLINE, BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 8);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 7);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
    private String previous_category;
    private String current_category;
    private String bookTitle = "";
    NumberFormat numberFormat = new DecimalFormat("###.00");
    NumberFormat numberFormat3d = new DecimalFormat("0.00#");
    DecimalFormat df = new DecimalFormat("0.###");

            java.util.List item_id = new ArrayList();
            java.util.List item_name = new ArrayList();
            java.util.List item_rate = new ArrayList();
            java.util.List item_quantity = new ArrayList();
            java.util.List item_desc = new ArrayList();
            java.util.List item_value = new ArrayList();
            java.util.List cost_category_id = new ArrayList();
            java.util.List cost_category_name = new ArrayList();
            java.util.List line_item_no = new ArrayList();

            String emptyCheck = "";
            String estDate = "";
            int lineItemDispCount = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        String getEstimationNumber = request.getParameter("estimationNumber") != null ? request.getParameter("estimationNumber") : "";
        String getEstimationDispNumber = request.getParameter("estimationDispNumber") != null ? request.getParameter("estimationDispNumber") : "";
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        lineItemDispCount = 0;
        boolean exp = false;
        String getVendorNumber = "";
        String fileName = project_name + "_Estimation.pdf";
        fileName = fileName.replaceAll(" ", "-");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);

        response.setContentType("application/pdf");

        try {

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            writer.setPageEvent(new footer());
            document.open();

            Paragraph numberPara = new Paragraph();

            //add estimation number
            numberPara.add(new Paragraph("Estimation Number: " + getEstimationDispNumber, headerFont));
            numberPara.setAlignment(Element.ALIGN_RIGHT);
            numberPara.setAlignment(Element.ALIGN_RIGHT);
            numberPara.setIndentationRight(45);
            document.add(numberPara);

            //Add empty line
            document.add(Chunk.NEWLINE);


            /* Create table to add logo,customer,carlisle job ie.project name in one row
            From address and terms in second row */

            //Create logo
            String logoPath = getServletContext().getRealPath("");
            logoPath = logoPath + File.separator + "webimages\\logopng.png";
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(logoPath);
            image.scaleAbsoluteHeight(64);
            image.scaleAbsoluteWidth(211);

            //Create a table.
            float[] colsWidth = {2f, 3f}; // Code 1
            PdfPTable table = new PdfPTable(colsWidth);

            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell1 = new PdfPCell(new PdfPCell(image, false));
            cell1.setMinimumHeight(70f);
            disableBorders(cell1);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
            cell2.setIndent(20);
            disableBorders(cell2);

            pathfinder.functions.revenue.EstimateSummaryInfo summary = new pathfinder.functions.revenue.EstimateSummaryInfo();
            summary.setEstNumber(getEstimationNumber);
            summary.getEstSummary();

            pathfinder.functions.revenue.EstimationInfo det = new pathfinder.functions.revenue.EstimationInfo();
            det.setEstNumber(getEstimationNumber);
            det.setProjId(getProjIdParam);
            det.getEstInfo();
            det.collectEstimationLineItems();
            line_item_no=det.getlineItemNo();
            estDate = det.getEstimateDisplayDate();

            //getBillingNotesForProjectId
            pathfinder.functions.projects.BillingNotesDAO billingNotesDAO = new pathfinder.functions.projects.BillingNotesDAO();
            pathfinder.functions.projects.BillingNotesVO billingNotesVO = new pathfinder.functions.projects.BillingNotesVO();

            ArrayList billingNotesList = new ArrayList();
            ArrayList notesList = new ArrayList();
            ArrayList dateList = new ArrayList();
            ArrayList empNameList = new ArrayList();
            ArrayList projIdList = new ArrayList();
            ArrayList billingNotesIdList = new ArrayList();
            ArrayList chargableFlag = new ArrayList();
            ArrayList billingNotesId = new ArrayList();

            billingNotesVO.setProjId(getProjIdParam);
            billingNotesList = (ArrayList) billingNotesDAO.getBillingNotesForProjectId(billingNotesVO);
            
            Iterator billingNotesItr = billingNotesList.iterator();

            while (billingNotesItr.hasNext()) {
                billingNotesVO = (pathfinder.functions.projects.BillingNotesVO) billingNotesItr.next();
                notesList.add(billingNotesVO.getBillingNotes());
                dateList.add(billingNotesVO.getDate());
                empNameList.add(billingNotesVO.getEmpName());
                projIdList.add(billingNotesVO.getProjId());
                chargableFlag.add(billingNotesVO.getChargeable());
                billingNotesId.add(billingNotesVO.getBillingNotesID());
            }


            //Declaring object to get Currency property.
            pathfinder.functions.revenue.CurrencyList currencyList = new pathfinder.functions.revenue.CurrencyList();

            //Get the project properties
            pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
            projInfo.setProjId(getProjIdParam);
            projInfo.collectProjectName();
            String billingAddress = "";
            String billingLocation = projInfo.getBillLocationId();
            String currencyUnicode = projInfo.getUnicode();
            String formattedCode = projInfo.getUnicodeFormatFlag();
            //String currencySymbol = currencyList.unicodeToCharacter(currencyUnicode, formattedCode);
              String currencySymbol = currencyUnicode;

            //Create nested Table
            PdfPTable nestedTable;
            nestedTable = new PdfPTable(2);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Date Of Estimation :", subFont));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setMinimumHeight(20f);
            disableBorders(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph(estDate, small));
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setMinimumHeight(20f);
            disableBorders(cell4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Customer # :", subFont));
            disableBorders(cell5);
            cell5.setMinimumHeight(20f);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);

            String tempClient = projInfo.getDivision();

            PdfPCell cell6 = new PdfPCell(new Paragraph(tempClient, small));
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setMinimumHeight(20f);
            disableBorders(cell6);

            PdfPCell vendor_num = new PdfPCell(new Paragraph("Vendor # :", subFont));
            disableBorders(vendor_num);
            vendor_num.setMinimumHeight(20f);
            vendor_num.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell vendor_num_val = new PdfPCell(new Paragraph(getVendorNumber, small));
            vendor_num_val.setHorizontalAlignment(Element.ALIGN_LEFT);
            vendor_num_val.setMinimumHeight(20f);
            disableBorders(vendor_num_val);

            PdfPCell cell113 = new PdfPCell(new Paragraph("Customer P.O.:", subFont));
            disableBorders(cell113);
            cell113.setMinimumHeight(20f);
            cell113.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cell13a = new PdfPCell(new Paragraph(projInfo.getCustomerPO(), small));
            cell13a.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell13a.setMinimumHeight(20f);
            disableBorders(cell13a);

            PdfPCell cell7 = new PdfPCell(new Paragraph("S4Carlisle Job #:", subFont));
            cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell7.setMinimumHeight(20f);
            disableBorders(cell7);

            PdfPCell cell8 = new PdfPCell(new Paragraph(project_name, small));
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setMinimumHeight(20f);
            disableBorders(cell8);

            PdfPCell cell9 = new PdfPCell(new Paragraph("Terms :", subFont));
            cell9.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell9.setMinimumHeight(20f);
            disableBorders(cell9);

            PdfPCell cell10 = new PdfPCell(new Paragraph(det.getTermsName(), small));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setMinimumHeight(20f);
            disableBorders(cell10);

            PdfPCell cell11 = new PdfPCell(new Paragraph("Type :", subFont));
            cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell11.setMinimumHeight(20f);
            disableBorders(cell11);

            PdfPCell cell12 = new PdfPCell(new Paragraph("Estimation", small));
            cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell12.setMinimumHeight(20f);
            disableBorders(cell12);

            //Buyer address
            if(billingLocation.equals("2")) {
                billingAddress = "2436 Meinen Court, Dubuque, IA 52002  |  Telephone: (563)557-1500  |  Fax: (563)557-1376";
            } else if(billingLocation.equals("1")){
                billingAddress = "Prakash Towers, 1st Floor" + "\n" + "No.141, Rajiv Gandhi Salai (OMR)" + "\n" + "Kottivakkam, Chennai - 600041, India" + "\n" + "Telephone: (044) 24545411 / 24545412";
            } else {
                billingAddress = "";
            }

            Paragraph buyer = new Paragraph(billingAddress, small);
            PdfPCell buyerAdd = new PdfPCell(buyer);
            buyerAdd.setIndent(50);
            buyerAdd.setHorizontalAlignment(Element.ALIGN_LEFT);
            buyerAdd.setMinimumHeight(20f);
            disableBorders(buyerAdd);

            nestedTable.addCell(cell3);
            nestedTable.addCell(cell4);
            nestedTable.addCell(cell5);
            nestedTable.addCell(cell6);
            nestedTable.addCell(vendor_num);
            nestedTable.addCell(vendor_num_val);
            nestedTable.addCell(cell113);
            nestedTable.addCell(cell13a);

            cell2.addElement(nestedTable);
            table.addCell(cell1);
            table.addCell(cell2);

            //Create new nested table
            nestedTable = new PdfPTable(2);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell nestedCell1 = new PdfPCell(nestedTable);
            disableBorders(nestedCell1);

            //table.addCell(buyerAdd);
            disableBorders(buyerAdd);
            table.addCell(nestedCell1);

            document.add(table);

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
            String buyerDetails=projInfo.getContact();

            //Customer Details
            String clientDetails = projInfo.getClient();
            clientDetails += projInfo.getClientAddress()+"\n";

            PdfPCell billToSt = new PdfPCell(new Paragraph(clientDetails, small));
            billToSt.setVerticalAlignment(Element.ALIGN_TOP);
            disableBorders(billToSt);

            //Wire Transaction Information
            PdfPCell wireTrans = new PdfPCell(new Paragraph("Wire Transaction Information"+"\n"+ "Dubuque Bank & Trust"+"\n"+ "1398 Central Avenue"+"\n"+ "Dubuque, IA 52001"+"\n"+ "Acct #: 10-760-3"+"\n", small));
            wireTrans.setVerticalAlignment(Element.ALIGN_TOP);
            disableBorders(wireTrans);

            //Attention (customer contact)
            PdfPCell attn = new PdfPCell(new Paragraph("Attention:", subFont));
            attn.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(attn);

            PdfPCell attnVal = new PdfPCell(new Paragraph(buyerDetails, small));
            attnVal.setHorizontalAlignment(Element.ALIGN_LEFT);
            disableBorders(attnVal);

            //Author
            String dispAuthors = "";
            ProjAuthorInfo pai = new ProjAuthorInfo();
            pai.setPrjId(getProjIdParam);
            pai.collectAuthorInfo();
            for(int index=0; index<pai.getPrimaryAuthor().size(); index++) {
                if(dispAuthors.length()>50) {
                    dispAuthors = dispAuthors+"\n";
                }
                dispAuthors = dispAuthors+pai.getContactFirstName().get(index)+" "+pai.getContactSecondName().get(index).toString();
                if(index < pai.getPrimaryAuthor().size()-1) {
                    dispAuthors = dispAuthors +" / ";
                }
            }
            PdfPCell author = new PdfPCell(new Paragraph("Author:", subFont));
            author.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(author);

            PdfPCell authorVal = new PdfPCell(new Paragraph(dispAuthors, small));
            disableBorders(authorVal);

            PdfPCell title = new PdfPCell(new Paragraph("Title:", subFont));
            title.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(title);
            bookTitle=projInfo.getTitle();
            String bookEdtion = "";
            bookEdtion=projInfo.getEdition();
            if(!bookEdtion.equals("")){
                bookTitle+=", "+bookEdtion+"e";
            }

            PdfPCell titleVal = new PdfPCell(new Paragraph(bookTitle, small));
            disableBorders(titleVal);

            PdfPCell copyright = new PdfPCell(new Paragraph("Copyright:", subFont));
            copyright.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(copyright);

            PdfPCell copyrightVal = new PdfPCell(new Paragraph(projInfo.getCopyRight(), small));
            disableBorders(copyrightVal);

            PdfPCell isbn = new PdfPCell(new Paragraph("ISBN - 10 / 13:", subFont));
            isbn.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(isbn);

            PdfPCell isbnVal = new PdfPCell(new Paragraph(projInfo.getISBN10() + " / " + projInfo.getISBN13(), small));
            disableBorders(isbnVal);

            PdfPCell emptyCell = new PdfPCell();
            disableBorders(emptyCell);

            table2.addCell(billToDet);
            table2.addCell(billToSt);
            table2.addCell(emptyCell);
            //table2.addCell(wireTrans);

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

            table2.addCell(attn);
            table2.addCell(attnVal);
            table2.addCell(emptyCell);
            table2.addCell(author);
            table2.addCell(authorVal);
            table2.addCell(emptyCell);
            table2.addCell(title);
            table2.addCell(titleVal);
            table2.addCell(emptyCell);
            table2.addCell(copyright);
            table2.addCell(copyrightVal);
            table2.addCell(emptyCell);
            table2.addCell(isbn);
            table2.addCell(isbnVal);
            table2.addCell(emptyCell);

            document.add(table2);

            //next line
            document.add(new Paragraph("\n"));

            //Draw a line....table only with top border
            float[] tableWidths = {10f};
            PdfPTable line = new PdfPTable(tableWidths);
            line.setWidthPercentage(100);

            PdfPCell emptyCellTab = new PdfPCell();
            emptyCellTab.setBorderWidth(1f);
            emptyCellTab.disableBorderSide(PdfPCell.BOTTOM);
            emptyCellTab.disableBorderSide(PdfPCell.LEFT);
            emptyCellTab.disableBorderSide(PdfPCell.RIGHT);

            line.addCell(emptyCellTab);
            document.add(line);

            //Declarations for computation
            Double rateTotal = 0.0;
            Double estTotal = 0.0;

            float[] lineItemsWidths;

            float[] lineItemsWidth1 = {7f, 20f, 7f, 7f, 20f};
            int width=0;

            lineItemsWidths = lineItemsWidth1;
            width=5;

            PdfPTable lineTable = new PdfPTable(width); //Code 3
            lineTable.setWidthPercentage(100);
            lineTable.setHeaderRows(1);
            lineTable.setSplitRows(false);
            lineTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            PdfPCell lineCell = new PdfPCell(new Paragraph("Estimate Qty", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Invoice Qty", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            
            lineCell = new PdfPCell(new Paragraph("Description", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Unit Price", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Estimate", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Comments", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineTable.getDefaultCell().enableBorderSide(PdfPCell.LEFT);
            lineTable.getDefaultCell().enableBorderSide(PdfPCell.RIGHT);

            lineTable.getDefaultCell().setBorderWidth(1f);
            lineTable.setWidths(lineItemsWidths);

            item_id = det.getItemId();
            item_name = det.getItemName();
            item_rate = det.getItemRate();
            item_quantity = det.getItemQuantity();
            item_desc = det.getLineItemDesc();
            item_value = det.getItemValue();
            cost_category_id = det.getCategoryId();
            cost_category_name = det.getCategoryName();
            line_item_no = det.getlineItemNo();

            previous_category = "";
            current_category = "";
            PdfPCell lastRow;
            PdfPCell Row;
            for (int loop = 0; loop < item_id.size(); loop++) {
                
                //Display cost category
                if (cost_category_id.size() > 0) {

                    current_category = cost_category_id.get(loop).toString();

                    if (current_category.equals(previous_category)) {
                        //do not show the category
                    } else {
                        if (!current_category.equals("")) {
                            
                            //show the category
                            lineTable.addCell(new Paragraph(" "));
                            lineTable.addCell(new Paragraph(cost_category_name.get(loop).toString(), blueFont));
                            lineTable.addCell(new Paragraph(" "));
                            lineTable.addCell(new Paragraph(" "));
                            lineTable.addCell(new Paragraph(" "));
                        }
                    }
                }

                lineTable.getDefaultCell().setColspan(1);

                if (loop + 1 == item_id.size()) {
                    

                    lastRow = new PdfPCell(new Paragraph(df.format(Double.parseDouble(item_quantity.get(loop).toString())), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.setHorizontalAlignment(Element.ALIGN_CENTER);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);

                    lastRow = new PdfPCell(new Paragraph("       " + item_name.get(loop).toString(), small));

                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Chunk currncyUnit=new Chunk(currencySymbol+"  ",small);
                    Chunk currncyUnitV=new Chunk(numberFormat3d.format(Double.parseDouble(item_rate.get(loop).toString())), small);
                    Phrase currncyUnitCom= new Phrase();
                    currncyUnitCom.add(currncyUnit);
                    currncyUnitCom.add(glue);
                    currncyUnitCom.add(currncyUnitV);
                    PdfPCell cellCurrncyV;
                    cellCurrncyV = new PdfPCell(currncyUnitCom);
                    cellCurrncyV.setBorderWidth(1f);
                    cellCurrncyV.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellCurrncyV.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(cellCurrncyV);

//                    lastRow = new PdfPCell(new Paragraph(currencySymbol,small));
//                    lastRow.setBorderWidth(1f);
//                    lastRow.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    lastRow.disableBorderSide(PdfPCell.TOP);
//                    lineTable.addCell(lastRow);
//
//                    lastRow = new PdfPCell(new Paragraph(numberFormat3d.format(Double.parseDouble(item_rate.get(loop).toString())), small));
//                    lastRow.setBorderWidth(1f);
//                    lastRow.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    lastRow.disableBorderSide(PdfPCell.TOP);
//                    lineTable.addCell(lastRow);
//                    rateTotal += Double.parseDouble(item_rate.get(loop).toString());
                    Chunk glue1 = new Chunk(new VerticalPositionMark());
                    Chunk unitrate=new Chunk(currencySymbol+"  ",small);
                    Chunk unitrateV=new Chunk(numberFormat3d.format(Double.parseDouble(item_value.get(loop).toString())), small);
                    Phrase unitrateCom= new Phrase();
                    unitrateCom.add(currncyUnit);
                    unitrateCom.add(glue1);
                    unitrateCom.add(unitrateV);
                    PdfPCell cellitem_valueV;
                    cellitem_valueV = new PdfPCell(unitrateCom);
                    cellitem_valueV.setBorderWidth(1f);
                    cellitem_valueV.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellitem_valueV.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(cellitem_valueV);

//                    lastRow = new PdfPCell(new Paragraph(currencySymbol+" "+numberFormat3d.format(Double.parseDouble(item_value.get(loop).toString())), small));
//                    lastRow.setBorderWidth(1f);
//                    lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    lastRow.disableBorderSide(PdfPCell.TOP);
//                    lineTable.addCell(lastRow);
                    estTotal += Double.parseDouble(item_value.get(loop).toString());

                    lastRow = new PdfPCell(new Paragraph(item_desc.get(loop).toString(), smallNote));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);

                } else {
                    //System.out.println("loop"+item_value.get(loop).toString());
                    Row = new PdfPCell(new Paragraph(df.format(Double.parseDouble(item_quantity.get(loop).toString())), small));
                    Row.setHorizontalAlignment(Element.ALIGN_CENTER);
                    Row.disableBorderSide(PdfPCell.TOP);
                    Row.disableBorderSide(PdfPCell.BOTTOM);
                    Row.setBorderWidth(1f);
                    lineTable.addCell(Row);

                    lineTable.addCell(new Paragraph("       " + item_name.get(loop).toString(), small));
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Chunk currncyUnit=new Chunk(currencySymbol+"  ",small);
                    Chunk currncyUnitV=new Chunk(numberFormat3d.format(Double.parseDouble(item_rate.get(loop).toString())), small);
                    Phrase currncyUnitCom= new Phrase();
                    currncyUnitCom.add(currncyUnit);
                    currncyUnitCom.add(glue);
                    currncyUnitCom.add(currncyUnitV);
                    PdfPCell cellCurrncyV;
                    cellCurrncyV = new PdfPCell(currncyUnitCom);
                    cellCurrncyV.setBorderWidth(1f);
                    cellCurrncyV.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellCurrncyV.disableBorderSide(PdfPCell.TOP);
                    cellCurrncyV.disableBorderSide(PdfPCell.BOTTOM);
                    lineTable.addCell(cellCurrncyV);
//                    Row = new PdfPCell(new Paragraph(numberFormat3d.format(Double.parseDouble(item_rate.get(loop).toString())), small));
//                    Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    Row.disableBorderSide(PdfPCell.TOP);
//                    Row.disableBorderSide(PdfPCell.BOTTOM);
//                    Row.setBorderWidth(1f);
//                    lineTable.addCell(Row);
//                    rateTotal += Double.parseDouble(item_rate.get(loop).toString());

//                    Row = new PdfPCell(new Paragraph(currencySymbol+" "+numberFormat3d.format(Double.parseDouble(item_value.get(loop).toString())), small));
//                    Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    Row.disableBorderSide(PdfPCell.TOP);
//                    Row.disableBorderSide(PdfPCell.BOTTOM);
//                    Row.setBorderWidth(1f);
//                    lineTable.addCell(Row);
                    Chunk glue2 = new Chunk(new VerticalPositionMark());
                    Chunk unitrate=new Chunk(currencySymbol+"  ",small);
                    Chunk unitrateV=new Chunk(numberFormat3d.format(Double.parseDouble(item_value.get(loop).toString())), small);
                    Phrase unitrateCom= new Phrase();
                    unitrateCom.add(unitrate);
                    unitrateCom.add(glue2);
                    unitrateCom.add(unitrateV);
                    PdfPCell cellitem_valueV;
                    cellitem_valueV = new PdfPCell(unitrateCom);
                    cellitem_valueV.setBorderWidth(1f);
                    cellitem_valueV.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellitem_valueV.disableBorderSide(PdfPCell.TOP);
                    cellitem_valueV.disableBorderSide(PdfPCell.BOTTOM);
                    lineTable.addCell(cellitem_valueV);
                    estTotal += Double.parseDouble(item_value.get(loop).toString());

                    lineTable.addCell(new Paragraph(item_desc.get(loop).toString(), smallNote));
                }
                previous_category = current_category;
            }

            lineTable.addCell(emptyCell);

            lineTable.addCell(emptyCell);

            if(estTotal > 0) {
                lastRow = new PdfPCell(new Paragraph("Sub-Total", subFont));
                lastRow.setBorderWidth(1f);
                lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastRow.disableBorderSide(PdfPCell.TOP);
                lineTable.addCell(lastRow);
            }

            if(estTotal > 0) {
                lastRow = new PdfPCell(new Paragraph(currencySymbol+" "+numberFormat3d.format(estTotal), subFont));
                lastRow.setBorderWidth(1f);
                lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastRow.disableBorderSide(PdfPCell.TOP);
                lineTable.addCell(lastRow);
            }

            lineTable.addCell(emptyCell);

            lineTable.setSpacingBefore(20);
            document.add(lineTable);

            //next line
            
            Paragraph notesPara = new Paragraph();
            notesPara.add(new Paragraph("Notes ", smallBold));
            notesPara.setAlignment(Element.ALIGN_LEFT);
            document.add(notesPara);

            document.add(new Paragraph("\n"));
            
            int notesSize = notesList.size();
            if(notesSize!=0){
                for(int idx=0; idx < notesList.size(); idx++) {

                    PdfPTable notesOuterTable = new PdfPTable(2);
                    notesOuterTable.setWidthPercentage(100);
                    int[] widths = {10, 90};
                    notesOuterTable.setWidths(widths);
                    PdfPCell notesOuterCellTab = new PdfPCell();
                    notesOuterCellTab = new PdfPCell(new Paragraph("Note"+(idx+1), small));
                    notesOuterCellTab.setHorizontalAlignment(Element.ALIGN_LEFT);
                    notesOuterCellTab.setBorderColor(BaseColor.GRAY);
                    if(idx==0){
                        notesOuterCellTab.disableBorderSide(PdfPCell.RIGHT);
                    }else if(idx==(notesSize-1)){
                        notesOuterCellTab.disableBorderSide(PdfPCell.RIGHT);
                    }else{
                        notesOuterCellTab.disableBorderSide(PdfPCell.RIGHT);
                    }
                    
                    notesOuterCellTab.setPadding(5);
                    notesOuterCellTab.setBorderWidth(1f);
                    notesOuterTable.addCell(notesOuterCellTab);

                    PdfPCell notesOuterCellTab2 = new PdfPCell();
                    notesOuterCellTab2 = new PdfPCell(new Paragraph(notesList.get(idx).toString(), small));
                    notesOuterCellTab2.setBorderColor(BaseColor.GRAY);
                    if(idx==0){
                        notesOuterCellTab2.disableBorderSide(PdfPCell.LEFT);
                    }else if(idx==(notesSize-1)){
                        notesOuterCellTab2.disableBorderSide(PdfPCell.LEFT);
                    }else{
                        notesOuterCellTab2.disableBorderSide(PdfPCell.LEFT);
                    }
                    notesOuterCellTab2.setPadding(5);
                    notesOuterCellTab2.setBorderWidth(1f);
                    notesOuterTable.addCell(notesOuterCellTab2);

                    document.add(notesOuterTable);
                }
            }else{

                PdfPTable empty = new PdfPTable(1);
                empty.setWidthPercentage(100);
                PdfPCell empty1 = new PdfPCell(new Paragraph());
                empty1.setHorizontalAlignment(Element.ALIGN_CENTER);
                empty1.setFixedHeight(50f);
                empty.addCell(empty1);
                document.add(empty);

            }
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

    public class footer extends PdfPageEventHelper {
      protected PdfPTable footerTable = new PdfPTable(1);

      public footer() {
        footerTable.setTotalWidth(550);
        footerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        footerTable.getDefaultCell().setBorder(PdfPCell.TOP);
        footerTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        //footerTable.addCell(new Phrase("2436 Meinen Court, Dubuque, IA 52002  |  Telephone: (563)557-1500  |  Fax: (563)557-1376", small));
      }

      @Override
      public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        footerTable.writeSelectedRows(0, -1, (document.right() - document.left() - 550) / 2 + document.leftMargin(), document.bottom() - 10, cb);
      }
    }

}
