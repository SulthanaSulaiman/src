/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.contacts.ProjAuthorInfo;
import pathfinder.functions.revenue.EstimateLineItems;
import pathfinder.functions.revenue.EstimationInfo;
import pathfinder.functions.revenue.InvoiceSummaryInfo;
import pathfinder.functions.revenue.InvoiceLine;

/**
 *
 * @author Aravindhanj
 */
public class InvoicePdfServlet extends HttpServlet {

    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.UNDERLINE, BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
    private static Font subFontUL = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.UNDERLINE);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 8);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 7);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
    private String previous_category;
    private String current_category;
    private String emptyCheck = "";
    private String bookTitle = "";
    boolean lineItemDispChk=false;
    int lineItemDispCount = 0;
    //Decimal Format Structure
    NumberFormat numberFormat = new DecimalFormat("###.00");
    NumberFormat numberFormat3d = new DecimalFormat("0.00#");
    NumberFormat integerFormat = new DecimalFormat("###");
    DecimalFormat df = new DecimalFormat("0.###");

    private void formInvoiceLineItemsForDisplay(EstimateLineItems estimateLineItems, Map estimateIdMap, pathfinder.functions.revenue.InvoiceLineItem invoiceLineItems, java.util.List invoiceLineList, boolean previousInvoice) {

        // Iterate through the previous invoices
        // java.util.List prevInvoicesCategories = invoiceLineItems.getCategoryName();
        lineItemDispCount = 0;
        java.util.List prevInvoicesEstimateIds = invoiceLineItems.getEst_line_item_id();
        java.util.List prevInvoicesInvoiceQuantities = invoiceLineItems.getLineItemQuantity();
        java.util.List prevInvoicesDescriptions = invoiceLineItems.getLineItemId();
        java.util.List prevInvoicesCategories = invoiceLineItems.getCategoryId();
        java.util.List prevInvoicesUnitPrices = invoiceLineItems.getLineItemRate();
        java.util.List prevInvoicesPreviouslyInvoicedValues = invoiceLineItems.getLineItemTotal();
        java.util.List prevInvoicesNoteDescription = invoiceLineItems.getLineItemDesc();
        java.util.List prevInvoicesLineItemNo = invoiceLineItems.getLineItemNum();
          
        for(int c=0;c<prevInvoicesLineItemNo.size();c++)
        {
            lineItemDispChk=false;
            emptyCheck=prevInvoicesLineItemNo.get(c).toString() !=null ? prevInvoicesLineItemNo.get(c).toString() : "";
            //System.out.print("lineItemDispChk"+emptyCheck);
            if(!emptyCheck.equals(""))
            {
                lineItemDispChk=true;
                //lineItemDispCount++;
                //System.out.print("lineItemDispChk"+lineItemDispChk);
                break;
            }
        }

        java.util.List estimateQuantities = estimateLineItems.getLineItemQuantity();
        java.util.List estimateTotal = estimateLineItems.getLineItemTotal();
        java.util.List estimateInvoicesLineItemNo = estimateLineItems.getLineItemNo();

        for (int prevInvoicesEstimateIdsIterator = 0; prevInvoicesEstimateIdsIterator < prevInvoicesEstimateIds.size(); prevInvoicesEstimateIdsIterator++) {
            String thisLineEstimateId = prevInvoicesEstimateIds.get(prevInvoicesEstimateIdsIterator).toString();
            int estimateIdIndex = -1;
            if (thisLineEstimateId != "") {
                if (estimateIdMap.get(thisLineEstimateId) != null) {
                    estimateIdIndex = Integer.parseInt(estimateIdMap.get(thisLineEstimateId).toString());
                }
            }
            String thisEstimateQuantity = "";
            String thisLineItemNo = "";
            if (thisLineEstimateId != "" && estimateIdIndex >= 0) {
                thisEstimateQuantity = estimateQuantities.get(estimateIdIndex).toString();
                thisLineItemNo = estimateInvoicesLineItemNo.get(estimateIdIndex).toString();
                estimateQuantities.set(estimateIdIndex, "");
            }
            String thisInvoiceQuantity = prevInvoicesInvoiceQuantities.get(prevInvoicesEstimateIdsIterator).toString();
            String thisCategory = prevInvoicesCategories.get(prevInvoicesEstimateIdsIterator).toString();
            String thisDescription = prevInvoicesDescriptions.get(prevInvoicesEstimateIdsIterator).toString();
            String thisInvoiceUnitPrice = prevInvoicesUnitPrices.get(prevInvoicesEstimateIdsIterator).toString();
            String thisNoteDescription = prevInvoicesNoteDescription.get(prevInvoicesEstimateIdsIterator).toString();
            //String thisLineItemNo = prevInvoicesLineItemNo.get(prevInvoicesEstimateIdsIterator).toString();
            String thisEstimateValue = "";
            if (thisLineEstimateId != "" && estimateIdIndex >= 0) {
                thisEstimateValue = estimateTotal.get(estimateIdIndex).toString();
                estimateTotal.set(estimateIdIndex, "");
            }
            String thisActualValue = Float.toString(Float.parseFloat(thisInvoiceQuantity) * Float.parseFloat(thisInvoiceUnitPrice));
            String thisPreviouslyInvoiced = "0";
            if (previousInvoice) {
                thisPreviouslyInvoiced = prevInvoicesPreviouslyInvoicedValues.get(prevInvoicesEstimateIdsIterator).toString();
            }
            String thisValue = Float.toString(Float.parseFloat(thisActualValue) - Float.parseFloat(thisPreviouslyInvoiced));
            //System.out.println("thisActualValue"+thisActualValue);

            // Fill in the invoiceLineList
            InvoiceLine thisInvoiceLine = new InvoiceLine();
            thisInvoiceLine.setEstimateQuantity(thisEstimateQuantity);
            thisInvoiceLine.setInvoiceQuantity(thisInvoiceQuantity);
            thisInvoiceLine.setCategory(thisCategory);
            thisInvoiceLine.setDescription(thisDescription);
            thisInvoiceLine.setUnitPrice(thisInvoiceUnitPrice);
            thisInvoiceLine.setEstimatedValue(thisEstimateValue);
            thisInvoiceLine.setActualValue(thisActualValue);
            thisInvoiceLine.setPreviouslyInvoicedValue("");
            thisInvoiceLine.setEstimateId(thisLineEstimateId);
            thisInvoiceLine.setNoteDescription(thisNoteDescription);
            thisInvoiceLine.setLineItemNo(thisLineItemNo);
            if (previousInvoice) {
                thisInvoiceLine.setPreviouslyInvoicedValue(thisPreviouslyInvoiced);
            }
            thisInvoiceLine.setInvoiceTotal(thisValue);

            invoiceLineList.add(thisInvoiceLine);
        }

    }

    public Map formEstimateMapbyId(pathfinder.functions.revenue.EstimateLineItems estimateLineItems) {
        Map estimateIdMap = new HashMap();
        java.util.List estimateIds = estimateLineItems.getInvoice_est_item_id();
        for (int estIterator = 0; estIterator < estimateIds.size(); estIterator++) {
            String thisEstimateId = estimateIds.get(estIterator).toString();
            estimateIdMap.put(thisEstimateId, estIterator);
        }

        return estimateIdMap;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //PrintWriter out = response.getWriter();

        String thisInvoiceNumber = request.getParameter("invoiceNumber") != null ? request.getParameter("invoiceNumber") : "";
        String thisProjectId = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        String thisInvoicePartFlag = request.getParameter("invoicePartFlag") != null ? request.getParameter("invoicePartFlag") : "";
        String getInvoicePartNumber = request.getParameter("invoicePartNumber") != null ? request.getParameter("invoicePartNumber") : "";
        String getVendorNumber = "";
        String numbtowordsCur="";
        String numbtowordsCur1="";
        // Get Estimation Details for this project
        pathfinder.functions.revenue.EstimateLineItems estimateLineItems = new pathfinder.functions.revenue.EstimateLineItems();
        estimateLineItems.setProjID(thisProjectId);
        estimateLineItems.getEstimationForInvoice();

        // Get Previous Invoice Details for this project
        pathfinder.functions.revenue.InvoiceLineItem prevInvoicesLineItems = new pathfinder.functions.revenue.InvoiceLineItem();
        prevInvoicesLineItems.setProjID(thisProjectId);
        prevInvoicesLineItems.getPartialInvoiceLineItems();

        // Get Current Invoice Details
        pathfinder.functions.revenue.InvoiceLineItem currentInvoiceLineItems = new pathfinder.functions.revenue.InvoiceLineItem();
        currentInvoiceLineItems.setInvoiceNumber(thisInvoiceNumber);
        currentInvoiceLineItems.collectInvoiceLineItem();
        pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
            projInfo.setProjId(thisProjectId);
            projInfo.collectProjectName();
            String billingAddress = "";
            String forchennai="";
            String billingLocation = projInfo.getBillLocationId();
            if(billingLocation.equals("1")){
            forchennai = "Export ";
            }
        // Get Currency Details for display
        pathfinder.functions.revenue.CurrencyList currencyList = new pathfinder.functions.revenue.CurrencyList();

        // Is the current invoice final or proforma
        boolean finalOrProforma = thisInvoicePartFlag.equals("0") || thisInvoicePartFlag.equals("2");

        // Build a hashmap of estimate ids Vs their indexes
        Map estimateIdMap = formEstimateMapbyId(estimateLineItems);

        // Form a comprehensive list of invoice lines
        java.util.List invoiceLineList = new ArrayList();
        if (finalOrProforma) {
            formInvoiceLineItemsForDisplay(estimateLineItems, estimateIdMap, prevInvoicesLineItems, invoiceLineList, true);
        }
        formInvoiceLineItemsForDisplay(estimateLineItems, estimateIdMap, currentInvoiceLineItems, invoiceLineList, false);


        Iterator invoiceLineIterator = invoiceLineList.iterator();
        Map invoiceLinesByCategoryAndLineDescription = new HashMap();
        Map invoiceLineOrderForCategory = new HashMap();
        InvoiceLine totalInvoiceLine = new InvoiceLine();
        // Group invoice items by category and line item
        double estimateTotal = 0.0;
        double actualTotal = 0.0;
        double previousTotal = 0.0;
        double invoiceTotal = 0.0;
        String tempValue = "0";
        String currencyCode = "";

        int count = 0;

        java.util.List categoryOrder = new ArrayList();
        while (invoiceLineIterator.hasNext()) {
            InvoiceLine invoiceLine = (InvoiceLine) invoiceLineIterator.next();
            String key = invoiceLine.getCategory();
            String line = invoiceLine.getDescription() + "-" + invoiceLine.getEstimateId() + "-" + invoiceLine.getUnitPrice();

            // Check if theres a hashmap for this category
            Map lineMap = (HashMap) invoiceLinesByCategoryAndLineDescription.get(key);
            java.util.List invoiceLineOrder = (ArrayList) invoiceLineOrderForCategory.get(key);
            if (lineMap == null) {
                // if the hash for this category doesnt exist, create it
                lineMap = new HashMap();
                invoiceLineOrder = new ArrayList();
                
                invoiceLinesByCategoryAndLineDescription.put(key, lineMap);
                invoiceLineOrderForCategory.put(key, invoiceLineOrder);

                if(invoiceLine.getEstimateId().equals("") ) {
                    // Add to category order at end
                    count++;
                    categoryOrder.add(key);
                } else {
                    // Add to category order before non estimated items
                    categoryOrder.add(categoryOrder.size()- count, key);
                }
                
            }

            // check if there are lines items for this category and line
            java.util.List valueArray = (ArrayList) lineMap.get(line);
            if (valueArray == null) {
                // if no values exist yet, create the array first, then add line item
                valueArray = new ArrayList();
                valueArray.add(invoiceLine);
                lineMap.put(line, valueArray);

                invoiceLineOrder.add(line);

            } else {
                // merge the existing line and the current invoiceLine
                InvoiceLine prevInvoiceLine = (InvoiceLine) valueArray.get(0);
                String currentInvoiceQuantity = invoiceLine.getInvoiceQuantity();
                String currentActualValue = invoiceLine.getActualValue();
                String currentPreviouslyInvoicedStr = invoiceLine.getPreviouslyInvoicedValue();
                double currentPreviouslyInvoiced = 0.0;
                if (currentPreviouslyInvoicedStr != "") {
                    currentPreviouslyInvoiced = Double.parseDouble(currentPreviouslyInvoicedStr);
                }

                String prevPreviouslyInvoicedStr = prevInvoiceLine.getPreviouslyInvoicedValue();
                double prevPreviouslyInvoiced = 0.0;
                if (prevPreviouslyInvoicedStr != "") {
                    prevPreviouslyInvoiced = Double.parseDouble(prevPreviouslyInvoicedStr);
                }

                String currentInvoiceNotes = invoiceLine.getNoteDescription().trim();
                String prevInvoiceNotes = prevInvoiceLine.getNoteDescription().trim();

                prevInvoiceLine.setInvoiceQuantity(currentInvoiceQuantity);
                prevInvoiceLine.setActualValue(currentActualValue);
                double previouslyInvoicedValue = currentPreviouslyInvoiced + prevPreviouslyInvoiced;
                prevInvoiceLine.setPreviouslyInvoicedValue(String.valueOf(previouslyInvoicedValue));
                double prevInvoiceTotal = Double.parseDouble(currentActualValue) - previouslyInvoicedValue;
                prevInvoiceLine.setInvoiceTotal(String.valueOf(prevInvoiceTotal));
                // the current note should not be empty
                // It should also not be contained in any of the earlier notes
                // If so, append the current notes to the existing notes
                if (currentInvoiceNotes != "" && !prevInvoiceNotes.contains(currentInvoiceNotes)) {
                    currentInvoiceNotes = prevInvoiceNotes + "\n" + currentInvoiceNotes;
                    prevInvoiceLine.setNoteDescription(currentInvoiceNotes);
                }
            }

            // append invoice Line to the hash map

            // Grand Total Computation
            tempValue = invoiceLine.getEstimatedValue();
            if (!tempValue.equals("null") && !tempValue.equals("")) {
                estimateTotal += Double.parseDouble(tempValue);
            }
            /*tempValue = invoiceLine.getActualValue();
            if (!tempValue.equals("null") && !tempValue.equals("")) {
                actualTotal += Double.parseDouble(tempValue);
            }*/
            tempValue = invoiceLine.getPreviouslyInvoicedValue();
            if (!tempValue.equals("null") && !tempValue.equals("")) {
                previousTotal += Double.parseDouble(tempValue);
            }
            tempValue = invoiceLine.getInvoiceTotal();
            if (!tempValue.equals("null") && !tempValue.equals("")) {
                invoiceTotal += Double.parseDouble(tempValue);
            }

        }
        //set the Grand Total Value into the object
        totalInvoiceLine.setEstimatedValue(String.valueOf(estimateTotal));
        //totalInvoiceLine.setActualValue(String.valueOf(actualTotal));
        totalInvoiceLine.setPreviouslyInvoicedValue(String.valueOf(previousTotal));
        totalInvoiceLine.setInvoiceTotal(String.valueOf(invoiceTotal));

        //Show current time stamp
        javax.servlet.http.HttpSession session = request.getSession();
        String showTimeStamp = "";
        String emp_fcy = session.getAttribute("empfacility").toString();

        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        if (emp_fcy == "1" || emp_fcy.equals("1")) {
            //Show Current time stamp of Chennai
            showTimeStamp = format.format(date);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, -11);
            calendar.add(Calendar.MINUTE, -30);
            date = calendar.getTime();
            //Show Current time stamp of Dubuque
            showTimeStamp = format.format(date);
        }

        String fileName = "";
        if(thisInvoicePartFlag.toString().equals("2")) {
            fileName = project_name + "_Proforma.pdf";
        } else {
            fileName = project_name + "_Invoice.pdf";
        }

        fileName = fileName.replaceAll(" ", "-");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);
        response.setContentType("application/pdf");

        try {

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Paragraph numberPara = new Paragraph();

            pathfinder.functions.revenue.InvoiceSummaryInfo summary = new pathfinder.functions.revenue.InvoiceSummaryInfo();
            summary.setInvoiceNumber(thisInvoiceNumber);
            summary.getInvoiceSummary();
            java.util.List getInvDispNum = summary.getProjInvDispNum();

            //add estimation number
             if(thisInvoicePartFlag.toString().equals("2")) {
                    numberPara.add(new Paragraph("Proforma Number: " + getInvDispNum.get(0).toString(), headerFont));

        } else {
                    numberPara.add(new Paragraph(""+forchennai+"Invoice Number: " + getInvDispNum.get(0).toString(), headerFont));
        }
         
            numberPara.setAlignment(Element.ALIGN_RIGHT);
            numberPara.setIndentationRight(45);

            pathfinder.functions.revenue.InvoiceInfo det = new pathfinder.functions.revenue.InvoiceInfo();
            det.setInvoiceNumber(thisInvoiceNumber);
            det.getInvoiceInfo();
            
            String logoPath = getServletContext().getRealPath("");
            if(billingLocation.equals("4")) {
                logoPath = logoPath + File.separator + "webimages\\S4 Carlisle Logo_Transmedia.jpg";
            }
 else{
 logoPath = logoPath + File.separator + "webimages\\logopng.png";
 }
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(logoPath);
            image.scaleAbsoluteHeight(64);
            image.scaleAbsoluteWidth(211);

            // Create a table to display Logo.
            float[] colsWidth = {4f, 3f}; // Code 1
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

            //Get the project properties
            
            String currencyUnicode = projInfo.getUnicode();
            String formattedCode = projInfo.getUnicodeFormatFlag();
            //String currencySymbol = currencyList.unicodeToCharacter(currencyUnicode, formattedCode);
            String currencySymbol = currencyUnicode;
 
            //Create nested Table
            PdfPTable nestedTable;
            nestedTable = new PdfPTable(2);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Date of Invoice: ", subFont));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setMinimumHeight(20f);
            disableBorders(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph(det.getInvoiceDisplayDateInPDF(), small));
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setMinimumHeight(20f);
            disableBorders(cell4);

            //Display vendor number if it is present. else do not display
            /*String vendorNum = projInfo.getVendorNumber();
             PdfPCell cell5 = null;
             PdfPCell cell6 = null;
             if (vendorNum != null && !vendorNum.equals("")) {

             cell5 = new PdfPCell(new Paragraph("Vendor Number: ", subFont));
             disableBorders(cell5);
             cell5.setMinimumHeight(20f);
             cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);

             cell6 = new PdfPCell(new Paragraph(projInfo.getVendorNumber(), small));
             cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell6.setMinimumHeight(20f);
             disableBorders(cell6);
             }*/

            PdfPCell cell7 = new PdfPCell(new Paragraph("Customer #:", subFont));
            disableBorders(cell7);
            cell7.setMinimumHeight(20f);
            cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);

            String tempClient = projInfo.getDivision();
            //tempClient = tempClient.replaceAll(".*\\(|\\).*", "");      //Extract Code
            PdfPCell cell8 = new PdfPCell(new Paragraph(tempClient, small));
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setMinimumHeight(20f);
            disableBorders(cell8);

            PdfPCell vendor_num = new PdfPCell(new Paragraph("Vendor # :", subFont));
            disableBorders(vendor_num);
            vendor_num.setMinimumHeight(20f);
            vendor_num.setHorizontalAlignment(Element.ALIGN_RIGHT);

            projInfo.GetVendorIdList();
            java.util.ArrayList vendorList = new ArrayList();
            vendorList = (ArrayList) projInfo.getVendorIdList();
            String Vendor = "";
            for (int i = 0; i < vendorList.size(); i++) {
                if (i > 0) {
                    Vendor += ", ";
                }
                Vendor += vendorList.get(i);
            }
            PdfPCell vendor_num_val = new PdfPCell(new Paragraph(Vendor, small));
            vendor_num_val.setHorizontalAlignment(Element.ALIGN_LEFT);
            vendor_num_val.setMinimumHeight(20f);
            disableBorders(vendor_num_val);

            PdfPCell cell113 = new PdfPCell(new Paragraph("Customer P.O.: ", subFont));
            disableBorders(cell113);
            cell113.setMinimumHeight(20f);
            cell113.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cell13a = new PdfPCell(new Paragraph(projInfo.getCustomerPO(), small));
            cell13a.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell13a.setMinimumHeight(20f);
            disableBorders(cell13a);

            PdfPCell cell9 = new PdfPCell(new Paragraph("S4Carlisle Job #:", subFont));
            cell9.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell9.setMinimumHeight(20f);
            disableBorders(cell9);

            PdfPCell cell10 = new PdfPCell(new Paragraph(project_name, small));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setMinimumHeight(20f);
            disableBorders(cell10);

            PdfPCell cell11 = new PdfPCell(new Paragraph("Terms: ", subFont));
            cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell11.setMinimumHeight(20f);
            disableBorders(cell11);

            PdfPCell cell12 = new PdfPCell(new Paragraph(det.getTermsName(), small));
            cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell12.setMinimumHeight(20f);
            disableBorders(cell12);

            PdfPCell cell13 = new PdfPCell(new Paragraph("Type of "+forchennai+"Invoice: ", subFont));
            cell13.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell13.setMinimumHeight(20f);
            disableBorders(cell13);
            
            PdfPCell cell14 = null;

            if (!thisInvoicePartFlag.equals("")) {
                if (thisInvoicePartFlag.equals("0")) {
                    cell14 = new PdfPCell(new Paragraph("Final", small));
                } else if (thisInvoicePartFlag.equals("1") && !getInvoicePartNumber.equals("")) {
                    cell14 = new PdfPCell(new Paragraph("Partial " + getInvoicePartNumber, small));
                } else if (thisInvoicePartFlag.equals("2")) {
                    cell14 = new PdfPCell(new Paragraph("Proforma", small));
                }
                cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell14.setMinimumHeight(20f);
                disableBorders(cell14);
            }
            PdfPCell cellgst = new PdfPCell(new Paragraph("GSTIN:", subFont));
            cellgst.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellgst.setMinimumHeight(20f);
            disableBorders(cellgst);

            PdfPCell cellgstNu = new PdfPCell(new Paragraph("33AAFCS2900K1ZK ", small));
            cellgstNu.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellgstNu.setMinimumHeight(20f);
            disableBorders(cellgstNu);

            //PdfPCell cellhsn = new PdfPCell(new Paragraph("ACS: ", subFont));
                 PdfPCell cellhsn = new PdfPCell(new Paragraph("HSN/SAC Code: ", subFont));
            cellhsn.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellhsn.setMinimumHeight(20f);
            disableBorders(cellhsn);
            PdfPCell cellhsnNu = new PdfPCell(new Paragraph("998912", small));
            cellhsnNu.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellhsnNu.setMinimumHeight(20f);
            disableBorders(cellhsnNu);
            PdfPCell cellplace = new PdfPCell(new Paragraph("Place of supply: ", subFont));
            cellplace.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellplace.setMinimumHeight(20f);
            disableBorders(cellplace);

            PdfPCell celltamil = new PdfPCell(new Paragraph(projInfo.getBuyerAddressCountryNme(), small));
            celltamil.setHorizontalAlignment(Element.ALIGN_LEFT);
            celltamil.setMinimumHeight(20f);
            disableBorders(celltamil);
            //Buyer address
            if (billingLocation.equals("2")) {
                billingAddress = "";
            } else if (billingLocation.equals("1")) {
              billingAddress = "60, Industrial Estate," + "\n" + "Perungudi, Chennai - 600096, India" + "\n" + "Telephone: (044) 24545411 / 24545412";
                   }
             else if(billingLocation.equals("3")){
                //billingAddress = "30 Cecil Street #19-08" + "\n" + "Prudential Tower" + "\n" + "Singapore – 049712";
                 billingAddress = "1 Sophia Road,"+"\n" + "#07-17 Peace Centre"+"\n" + "Singapore 228149";
            }
            else if(billingLocation.equals("5")){
                //billingAddress = "30 Cecil Street #19-08" + "\n" + "Prudential Tower" + "\n" + "Singapore – 049712";
                billingAddress = "1 Sophia Road,"+"\n" + "#07-17 Peace Centre"+"\n" + "Singapore 228149";
            }
//            else if(billingLocation.equals("7")){
//                billingAddress = "1 Sophia Road,"+"\n" + "#07-17 Peace Centre"+"\n" + "Singapore 228149";
//            }
            else {
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
            /*if (vendorNum != null && !vendorNum.equals("")) {
             nestedTable.addCell(cell5);
             nestedTable.addCell(cell6);
             }*/
            nestedTable.addCell(cell7);
            nestedTable.addCell(cell8);
            nestedTable.addCell(vendor_num);
            nestedTable.addCell(vendor_num_val);
            nestedTable.addCell(cell113);
            
            nestedTable.addCell(cell13a);
            nestedTable.addCell(cell9);
            nestedTable.addCell(cell10);
            
            if(!det.getTermsName().equals(""))
            {
            nestedTable.addCell(cell11);
            nestedTable.addCell(cell12);
            }
            

            cell2.addElement(nestedTable);

            table.addCell(cell1);
            table.addCell(cell2);

            //Create new nested table
            nestedTable = new PdfPTable(2);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            nestedTable.addCell(cell13);
            nestedTable.addCell(cell14);
            if (billingLocation.equals("1")) {
            nestedTable.addCell(cellgst);
            nestedTable.addCell(cellgstNu);
            nestedTable.addCell(cellhsn);
            nestedTable.addCell(cellhsnNu);
    nestedTable.addCell(cellplace);
    nestedTable.addCell(celltamil);
}
            PdfPCell nestedCell1 = new PdfPCell(nestedTable);
            disableBorders(nestedCell1);

            table.addCell(buyerAdd);
            //
            table.addCell(nestedCell1);
            

            // Add Bill To and Write Transaction
            float[] tableWidth = {1f, 3.5f, 3f};
            PdfPTable table2 = new PdfPTable(tableWidth);

            table2.setWidthPercentage(100);

            //Bill To
            PdfPCell billToDet = new PdfPCell(new Paragraph("Bill To: ", subFont));
            billToDet.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(billToDet);

            //Seller Name and street name
            String buyerDetails = "";
            if (!projInfo.getClient().equals(null) && !projInfo.getClient().equals("")) {
                buyerDetails = buyerDetails + projInfo.getClient();
            }
            buyerDetails += projInfo.getClientAddress();
           /* if (!projInfo.getBuyerAddress().equals(null) && !projInfo.getBuyerAddress().equals("")) {
                buyerDetails = buyerDetails + projInfo.getBuyerAddress() + "\n";
            }
            if (!projInfo.getBuyerPhone().equals(null) && !projInfo.getBuyerPhone().equals("")) {
                buyerDetails = buyerDetails + "Telephone: " + projInfo.getBuyerPhone() + "\n";
            }
            if (!projInfo.getBuyerFax().equals(null) && !projInfo.getBuyerFax().equals("")) {
                buyerDetails = buyerDetails + "Fax: " + projInfo.getBuyerFax() + "\n";
            }*/
            // PdfPCell billToSt = new PdfPCell(new Paragraph(projInfo.getClientAddress() + "\n", small));
            PdfPCell billToSt = new PdfPCell(new Paragraph(buyerDetails, small));
            billToSt.setHorizontalAlignment(Element.ALIGN_LEFT);
            disableBorders(billToSt);

            

            //Wire Transaction Information

            PdfPCell wireTrans = new PdfPCell(new Paragraph("Wire Transaction Information" + "\n" + "Dubuque Bank & Trust" + "\n" + "1398 Central Avenue" + "\n" + "Dubuque, IA 52001" + "\n" + "Acct #: 10-760-3" + "\n", small));
            wireTrans.setVerticalAlignment(Element.ALIGN_TOP);
            disableBorders(wireTrans);

            //Attention (customer contact)
            PdfPCell attn = new PdfPCell(new Paragraph("Attention:", subFont));
            attn.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(attn);
            PdfPCell attnVal = new PdfPCell(new Paragraph(projInfo.getContact(), small));
            attnVal.setHorizontalAlignment(Element.ALIGN_LEFT);
            disableBorders(attnVal);

            //Author
            String dispAuthors = "";
            ProjAuthorInfo pai = new ProjAuthorInfo();
            pai.setPrjId(thisProjectId);
            pai.collectAuthorInfo();
            for (int index = 0; index < pai.getPrimaryAuthor().size(); index++) {
                if (dispAuthors.length() > 50) {
                    dispAuthors = dispAuthors + "\n";
                }
                dispAuthors = dispAuthors + pai.getContactFirstName().get(index).toString() + " " + pai.getContactSecondName().get(index).toString();
                if (index < pai.getPrimaryAuthor().size() - 1) {
                    dispAuthors = dispAuthors + " / ";
                }
            }
            PdfPCell author = new PdfPCell(new Paragraph("Author:", subFont));
            author.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(author);
            PdfPCell authorVal = new PdfPCell(new Paragraph(dispAuthors, small));
            authorVal.setHorizontalAlignment(Element.ALIGN_LEFT);
            disableBorders(authorVal);

            PdfPCell copyright = new PdfPCell(new Paragraph("Copyright:", subFont));
            copyright.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(copyright);
            PdfPCell copyrightVal = new PdfPCell(new Paragraph(projInfo.getCopyRight(), small));
            copyrightVal.setHorizontalAlignment(Element.ALIGN_LEFT);
            disableBorders(copyrightVal);

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
            titleVal.setHorizontalAlignment(Element.ALIGN_LEFT);
            disableBorders(titleVal);

            PdfPCell isbn = new PdfPCell(new Paragraph("ISBN - 10 / 13:", subFont));
            isbn.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(isbn);
            PdfPCell isbnVal = new PdfPCell(new Paragraph(projInfo.getISBN10() + " / " + projInfo.getISBN13(), small));
            isbnVal.setHorizontalAlignment(Element.ALIGN_LEFT);
            disableBorders(isbnVal);

            PdfPCell emptyCell = new PdfPCell();
            disableBorders(emptyCell);
            
            table2.addCell(billToDet);
            
            table2.addCell(billToSt);
            table2.addCell(emptyCell);
            //table2.addCell(wireTrans);

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

            //Add Invoice summary
            Paragraph summ = new Paragraph(""+forchennai+"Invoice Summary", headerFont);
            summ.setIndentationLeft(25);

            // Invoice Summary
            PdfPTable summTable = new PdfPTable(4);
            summTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            summTable.setWidthPercentage(80);
            summTable.getDefaultCell().setBorderWidth(1f);

            PdfPCell cell;
            cell = new PdfPCell(new Paragraph("Estimate", subFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderWidth(1f);
            summTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Actual", subFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderWidth(1f);
            summTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Previously Invoiced", subFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderWidth(1f);
            summTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Due This Invoice", subFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderWidth(1f);
            summTable.addCell(cell);

            float[] widths = {3f, 2.5f};

            PdfPTable lineItem = new PdfPTable(widths);
            lineItem.setWidthPercentage(100);
            PdfPCell s4logo;
            if(billingLocation.equals("4")) {
                s4logo = new PdfPCell(new Paragraph("S4Carlisle Transmedia Services Inc", headerFont));
            }
            else if(billingLocation.equals("5")) {
                s4logo = new PdfPCell(new Paragraph("S4Carlisle Publishing Services Pte Ltd", headerFont));
            }
            else{
                s4logo = new PdfPCell(new Paragraph("S4Carlisle Publishing Services", headerFont));
            }
            disableBorders(s4logo);
             if(thisInvoicePartFlag.toString().equals("2")) {

            PdfPCell poNum = new PdfPCell(new Paragraph("Proforma Number: " + getInvDispNum.get(0).toString(), headerFont));
            disableBorders(poNum);
            lineItem.addCell(s4logo);
            lineItem.addCell(poNum);

        } else {
            PdfPCell poNum = new PdfPCell(new Paragraph(""+forchennai+"Invoice Number: " + getInvDispNum.get(0).toString(), headerFont));
            disableBorders(poNum);
            lineItem.addCell(s4logo);
            lineItem.addCell(poNum);
        }
            

            // Table for Project Properties
            float[] propColWidth = {0.5f, 2.3f, 1f, 1.8f};
            PdfPTable projProp = new PdfPTable(propColWidth);
            projProp.setWidthPercentage(100);
            projProp.addCell(author);
            disableBorders(author);
            projProp.addCell(authorVal);
            disableBorders(authorVal);
            cell9.setMinimumHeight(8f);
            projProp.addCell(cell9);
            disableBorders(cell9);
            cell10.setMinimumHeight(8f);
            projProp.addCell(cell10);
            disableBorders(cell10);
            projProp.addCell(title);
            disableBorders(title);
            projProp.addCell(titleVal);
            disableBorders(titleVal);
            projProp.addCell(copyright);
            disableBorders(copyright);
            projProp.addCell(copyrightVal);
            disableBorders(copyrightVal);
            projProp.addCell(emptyCell);
            projProp.addCell(emptyCell);
            projProp.addCell(isbn);
            disableBorders(isbn);
            projProp.addCell(isbnVal);
            disableBorders(isbnVal);

            // Table for Invoice Line Items
            //float[] lineItemsWidths = {7f, 7f, 25f, 9f, 9f, 9f, 9f, 10f};
            float[] lineItemsWidths;
            

            float[] lineItemsWidth1 = {6f, 6f, 18f, 10f, 10f, 10f, 10f, 10f,14f};

            float[] lineItemsWidth3 = {7f, 7f, 25f, 10f, 10f, 10f, 10f, 10f};


            int width=0;
            
            float[] lineItemsWidth2 = {10f, 7f, 7f, 20f, 8f, 10f, 10f, 10f, 15f};

//            if (lineItemDispCount!=0)
//            {
//                lineItemDispChk=true;
//            }
            if(lineItemDispChk)
            {
            lineItemsWidths = lineItemsWidth2;
            if (billingLocation.equals("1")) {
            lineItemsWidths = lineItemsWidth2;
            width=9;
                }
 else {
                lineItemsWidths = lineItemsWidth1;
                width=9;
                }
            }
            else
            {
                if (billingLocation.equals("1")) {
            lineItemsWidths = lineItemsWidth1;
            width=9;
                }
 else{
            lineItemsWidths = lineItemsWidth3;
            
            width=8;
                }
            }

            PdfPTable lineTable = new PdfPTable(width);
            lineTable.setWidthPercentage(100);
            lineTable.setSplitRows(false);
            lineTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            lineTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            lineTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            lineTable.setWidths(lineItemsWidths);
            PdfPCell lineCell;
             if(lineItemDispChk)
            {
            lineCell = new PdfPCell(new Paragraph("Line Item No.", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);
            }

            lineCell = new PdfPCell(new Paragraph("Estimate Qty", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Invoice Qty", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Description", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);
if (billingLocation.equals("1")) {
            lineCell = new PdfPCell(new Paragraph("ACS", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);
            }
            lineCell = new PdfPCell(new Paragraph("Unit Price", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Estimate", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Actual", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Previously Invoiced", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Invoice Total", subFont));
            lineCell.setBorderWidth(1f);
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineTable.addCell(lineCell);

            PdfPCell Row = null;

            boolean isLastRow = false;
            String previousCategory = "";
            String currentCategory = "";

            //Invoice Line Item Iterator according to the category order
            for (int catctr = 0 ; catctr < categoryOrder.size(); catctr++)
            {
                String cat = (String) categoryOrder.get(catctr);
                Map lineMap = (HashMap) invoiceLinesByCategoryAndLineDescription.get(cat);
                java.util.List lineOrder = (ArrayList) invoiceLineOrderForCategory.get(cat);

                invoiceLineIterator = lineMap.entrySet().iterator();

                for (int linectr = 0 ; linectr < lineOrder.size(); linectr++)
                {
                    String linestr = (String) lineOrder.get(linectr);
                    // String cat = (String) categoryOrder.get(catctr);
                    // invoiceLineOrderForCategory
                    // while (invoiceLineIterator.hasNext()) {
                    // Map.Entry kv = (Map.Entry) invoiceLineIterator.next();
                    // Map.Entry kv = (Map.Entry) lineMap.get(linestr);
                    // java.util.List categoryAndLineBasedInvoices = (ArrayList) kv.getValue();
                    java.util.List categoryAndLineBasedInvoices = (ArrayList) lineMap.get(linestr);

                    for (int invoiceLineArrayCtr = 0; invoiceLineArrayCtr < categoryAndLineBasedInvoices.size(); invoiceLineArrayCtr++) {

                        InvoiceLine invoiceLine = (InvoiceLine) categoryAndLineBasedInvoices.get(invoiceLineArrayCtr);

                        currentCategory = invoiceLine.getCategory();
                        if (!previousCategory.equals(currentCategory)) {
                            PdfPCell DummyRow;
                            DummyRow = new PdfPCell(new Paragraph("", small));
                            DummyRow.disableBorderSide(PdfPCell.TOP);
                            DummyRow.disableBorderSide(PdfPCell.BOTTOM);
                            DummyRow.setBorderWidth(1f);

                            Row = new PdfPCell(new Paragraph(invoiceLine.getCategory(), blueFont));
                            Row.disableBorderSide(PdfPCell.TOP);
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                            Row.setBorderWidth(1f);
                             if(lineItemDispChk)
                            {
                                lineTable.addCell(DummyRow);
                            }
                            if (billingLocation.equals("1")) {
                            lineTable.addCell(DummyRow);
                            lineTable.addCell(DummyRow);
                            lineTable.addCell(Row);
                            lineTable.addCell(DummyRow);
                            lineTable.addCell(DummyRow);
                            lineTable.addCell(DummyRow);
                            lineTable.addCell(DummyRow);
                            lineTable.addCell(DummyRow);
                            lineTable.addCell(DummyRow);

                            }
 else{
                            lineTable.addCell(DummyRow);
                            lineTable.addCell(DummyRow);
                            lineTable.addCell(Row);
                             lineTable.addCell(DummyRow);
                      lineTable.addCell(DummyRow);
                      lineTable.addCell(DummyRow);
                      lineTable.addCell(DummyRow);
                      lineTable.addCell(DummyRow);


                            }
                            
                      
                        }

                        //Check for the Last record, to show the bottom border
                        if (catctr == (categoryOrder.size() - 1) && (linectr == lineOrder.size() - 1) && invoiceLineArrayCtr == categoryAndLineBasedInvoices.size() - 1) {
                            isLastRow = true;
                        }
                         if(lineItemDispChk)
                            {
                             Row = new PdfPCell(new Paragraph(invoiceLine.getLineItemNo().toString(), small));
                        
                        Row.setHorizontalAlignment(Element.ALIGN_CENTER);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        lineTable.addCell(Row);
                         }

                        //Display nothing if the line item is not estimated
                        if (invoiceLine.getEstimateQuantity().toString().equals("") || !finalOrProforma) {
                            Row = new PdfPCell(new Paragraph("", small));
                        } else {
                            Row = new PdfPCell(new Paragraph(df.format(Double.parseDouble(invoiceLine.getEstimateQuantity().toString())), small));
                        }
                        Row.setHorizontalAlignment(Element.ALIGN_CENTER);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        lineTable.addCell(Row);

                        Row = new PdfPCell(new Paragraph(df.format(Double.parseDouble(invoiceLine.getInvoiceQuantity().toString())), small));
                        Row.setHorizontalAlignment(Element.ALIGN_CENTER);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        lineTable.addCell(Row);


                        String invoice = invoiceLine.getNoteDescription().toString();

                        // Prepare the description in a sub-table
                        // Sub-table is required in case notes follows
                        // Hence sub-table contains two columns
                        Row = new PdfPCell();
                        float[] noteWidths = {6f, 19f};
                        PdfPTable testTable = new PdfPTable(2);
                        testTable.setWidthPercentage(100);
                        testTable.setWidths(noteWidths);
                        //testTable.getDefaultCell().disableBorderSide(loop);
                        PdfPCell c1 = new PdfPCell();
                        c1 = new PdfPCell(new Paragraph(invoiceLine.getDescription().toString(), small));
                        c1.setColspan(2);
                        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                        c1.setBorder(Rectangle.NO_BORDER);
                        testTable.addCell(c1);

                        if (!invoiceLine.getNoteDescription().toString().equals("")) {
                            // NOTES label
                            c1 = new PdfPCell(new Paragraph("Notes:", smallBoldNote));
                            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            c1.setBorder(Rectangle.NO_BORDER);
                            testTable.addCell(c1);

                            // NOTES value
                            c1 = new PdfPCell(new Paragraph(invoiceLine.getNoteDescription().toString(), smallNote));
                            c1.setBorder(Rectangle.NO_BORDER);
                            testTable.addCell(c1);
                        }

                        // add the subtable to the row element
                        Row.setHorizontalAlignment(Element.ALIGN_LEFT);
                        Row.addElement(testTable);
                        // Row.setBorder(Rectangle.NO_BORDER);
                        Row.setBorderWidth(1f);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        lineTable.addCell(Row);

                        Row = new PdfPCell(new Paragraph("  " + invoiceLine.getDescription().toString() + "  " + invoiceLine.getNoteDescription(), small));
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        //lineTable.addCell(Row);
if (billingLocation.equals("1")) {
                        Row = new PdfPCell(new Paragraph("998912", small));
                        Row.setHorizontalAlignment(Element.ALIGN_LEFT);
                        Row.setHorizontalAlignment(Element.ALIGN_BOTTOM);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        lineTable.addCell(Row);
                        }
                    Chunk currncyUnit=new Chunk(currencySymbol,small);
                    Chunk currncyUnitV=new Chunk(numberFormat3d.format(Double.parseDouble(invoiceLine.getUnitPrice().toString())), small);
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Phrase currncyUnitCom= new Phrase();
                    currncyUnitCom.add(currncyUnit);
                    currncyUnitCom.add(glue);
                    currncyUnitCom.add(currncyUnitV);
                        Row = new PdfPCell(currncyUnitCom);
                        Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        lineTable.addCell(Row);

                        //Display nothing if the line item is not estimated
                        if (invoiceLine.getEstimatedValue().toString().equals("") || !finalOrProforma) {
                            Row = new PdfPCell(new Paragraph("", small));
                        } else {
                    Chunk estimatecurr=new Chunk(currencySymbol,small);
                    Chunk estimateV=new Chunk(numberFormat.format(Double.parseDouble(invoiceLine.getEstimatedValue().toString())), small);
                    Chunk glue1 = new Chunk(new VerticalPositionMark());
                    Phrase estimateCom= new Phrase();
                    estimateCom.add(estimatecurr);
                    estimateCom.add(glue1);
                    estimateCom.add(estimateV);
                            Row = new PdfPCell(estimateCom);
                        }
                        Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        lineTable.addCell(Row);

                        if (finalOrProforma) {
                    Chunk actualcurr=new Chunk(currencySymbol,small);
                    Chunk actualV=new Chunk(numberFormat.format(Double.parseDouble(invoiceLine.getActualValue().toString())), small);
                    Phrase actualCom= new Phrase();
                    actualCom.add(actualcurr);
                    actualCom.add(glue);
                    actualCom.add(actualV);
                    Row = new PdfPCell(new Paragraph(actualCom));
                            actualTotal += Double.parseDouble(invoiceLine.getActualValue().toString());
                        } else {
                            Row = new PdfPCell(new Paragraph("", small));
                        }
                        Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        lineTable.addCell(Row);

                        //Display nothing if the line item didnt invoiced previously
                        if (invoiceLine.getPreviouslyInvoicedValue().toString().equals("") || !finalOrProforma) {
                            Row = new PdfPCell(new Paragraph("", small));
                        } else {
                    Chunk prevInvoicecurr=new Chunk(currencySymbol,small);
                    Chunk prevInvoiceV=new Chunk(numberFormat.format(Double.parseDouble(invoiceLine.getPreviouslyInvoicedValue().toString())), small);
                    Phrase prevInvoiceCom= new Phrase();
                    prevInvoiceCom.add(prevInvoicecurr);
                    prevInvoiceCom.add(glue);
                    prevInvoiceCom.add(prevInvoiceV);
                    Row = new PdfPCell(prevInvoiceCom);
                        }
                        Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        lineTable.addCell(Row);

                        //Display nothing if the line item didnt invoiced currently
                        if (invoiceLine.getInvoiceTotal().toString().equals("0.0")) {
                            Row = new PdfPCell(new Paragraph("", small));
                        } else {
                        Chunk invoiceTotcurr=new Chunk(currencySymbol,small);
                    Chunk invoiceTotV=new Chunk(numberFormat.format(Double.parseDouble(invoiceLine.getInvoiceTotal().toString())), small);
                    Phrase invoiceTotCom= new Phrase();
                    invoiceTotCom.add(invoiceTotcurr);
                    invoiceTotCom.add(glue);
                    invoiceTotCom.add(invoiceTotV);
                            Row = new PdfPCell(invoiceTotCom);
                                                    }
                        Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        Row.disableBorderSide(PdfPCell.TOP);
                        if (!isLastRow) {
                            Row.disableBorderSide(PdfPCell.BOTTOM);
                        }
                        Row.setBorderWidth(1f);
                        lineTable.addCell(Row);

                        previousCategory = currentCategory;
                    }
                } // end of invoiceLineIterator
            } // end of categoryIterator
             if(lineItemDispChk)
                 {
                lineTable.addCell(new Paragraph("", small));
                }
            if (billingLocation.equals("1")) {
            lineTable.addCell(new Paragraph("", small));
            lineTable.addCell(new Paragraph("", small));
            lineTable.addCell(new Paragraph("", small));
            lineTable.addCell(new Paragraph("", small));
            lineTable.addCell(new Paragraph("", small));
            }
 else{
            lineTable.addCell(new Paragraph("", small));
            lineTable.addCell(new Paragraph("", small));
            lineTable.addCell(new Paragraph("", small));
            lineTable.addCell(new Paragraph("", small));

 }
            
            //Actual Total & Invoice Total
            totalInvoiceLine.setActualValue(actualTotal+"");
             if (finalOrProforma) {
                 tempValue = (actualTotal - (Double.parseDouble(totalInvoiceLine.getPreviouslyInvoicedValue().toString())))+"";
                 System.out.println("tempValue"+tempValue);
                 totalInvoiceLine.setInvoiceTotal(tempValue);
             }
                        //Check for null as well as final inovice or not
            if (!totalInvoiceLine.getEstimatedValue().toString().equals("null") && !totalInvoiceLine.getEstimatedValue().toString().equals("0.0") && finalOrProforma) {
                Row = new PdfPCell(new Paragraph(currencySymbol+" " + numberFormat.format(Double.parseDouble(totalInvoiceLine.getEstimatedValue().toString())), smallBold));
                Row.setBorderWidth(1f);
                Row.setHorizontalAlignment(Element.ALIGN_CENTER);
                summTable.addCell(Row);
                            } else {
                Row = new PdfPCell(new Paragraph("", small));
                Row.setBorderWidth(1f);
                summTable.addCell(Row);
            }
            Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Row.disableBorderSide(PdfPCell.TOP);
            lineTable.addCell(Row);

            if (!totalInvoiceLine.getActualValue().toString().equals("null") && !totalInvoiceLine.getActualValue().toString().equals("0.0") && finalOrProforma) {
                Row = new PdfPCell(new Paragraph(currencySymbol+" " + numberFormat.format(Double.parseDouble(totalInvoiceLine.getActualValue().toString())), smallBold));
                Row.setHorizontalAlignment(Element.ALIGN_CENTER);
                Row.setBorderWidth(1f);
                summTable.addCell(Row);
            } else {
                Row = new PdfPCell(new Paragraph("", small));
                Row.setBorderWidth(1f);
                summTable.addCell(Row);
            }
            Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Row.disableBorderSide(PdfPCell.TOP);
            lineTable.addCell(Row);
            if (!totalInvoiceLine.getPreviouslyInvoicedValue().toString().equals("null") && !totalInvoiceLine.getPreviouslyInvoicedValue().toString().equals("0.0") && finalOrProforma) {
                Row = new PdfPCell(new Paragraph(currencySymbol+" " + numberFormat.format(Double.parseDouble(totalInvoiceLine.getPreviouslyInvoicedValue().toString())), smallBold));
                Row.setHorizontalAlignment(Element.ALIGN_CENTER);
                Row.setBorderWidth(1f);
                summTable.addCell(Row);
                            } else {
                Row = new PdfPCell(new Paragraph("", small));
                Row.setBorderWidth(1f);
                summTable.addCell(Row);
            }
            Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Row.disableBorderSide(PdfPCell.TOP);
            lineTable.addCell(Row);
String numtocurrency="";
String numtocurrencychkzero="";
            if (!totalInvoiceLine.getInvoiceTotal().toString().equals("null") && !totalInvoiceLine.getInvoiceTotal().toString().equals("0.0")) {
                Row = new PdfPCell(new Paragraph(""+currencySymbol+" " + numberFormat.format(Double.parseDouble(totalInvoiceLine.getInvoiceTotal().toString())), smallBold));
                numtocurrency=numberFormat.format(Double.parseDouble(totalInvoiceLine.getInvoiceTotal()));
                String numtocurrency2[] = numtocurrency.split("\\.");
                System.out.println("split"+numtocurrency);
                //System.out.println("split1"+numtocurrency2[1]);
                        pathfinder.util.YourNumberMyWord numToword = new pathfinder.util.YourNumberMyWord();
                        numbtowordsCur+=numToword.convert(Long.parseLong(numtocurrency2[0]));

                        if(currencyUnicode.equals("CAD")){
                         numbtowordsCur1+="Canadian dollars ";
                        if(numtocurrency2[1].length()==1){
                                numtocurrencychkzero=numtocurrency2[1]+"0";
                             if(numtocurrencychkzero.equals("00")){}
                            else{
                                numbtowordsCur+=" and Cents";
                                numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]+"0"));
                                numtocurrencychkzero=numtocurrency2[1]+"0";

                            }
                }
                else{
                        if(numtocurrency2[1].equals("00")){}
                        else{
                        numbtowordsCur+=" and Cents";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]));
                        }
                    }
                      }
                         if(currencyUnicode.equals("GBP")){
                         numbtowordsCur1+="Pounds ";
                        if(numtocurrency2[1].length()==1){
                                numtocurrencychkzero=numtocurrency2[1]+"0";
                                if(numtocurrencychkzero.equals("00")){

                            }
                        else{
                            numbtowordsCur+=" and Penny";
                            numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]+"0"));
                            numtocurrencychkzero=numtocurrency2[1]+"0";
                            }

                }
                    else{
                             if(numtocurrency2[1].equals("00")){}
                        else{
                        numbtowordsCur+=" and Penny";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]));
                        }
                        }
                        }
                         
                         if(currencyUnicode.equals("USD")){
                             System.out.println("chkcu"+numbtowordsCur1);
                         numbtowordsCur1+="US Dollars ";
                        if(numtocurrency2[1].length()==1){
                           
                          numtocurrencychkzero=numtocurrency2[1]+"0";
                                if(numtocurrencychkzero.equals("00")){}
                                else{
                                numbtowordsCur+=" and Cents";
                                numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]+"0"));
                                System.out.println("chk"+numbtowordsCur);
                                                  }
                }
                        else{
                             //System.out.println("chkelse"+numtocurrency2[1]);
                             if(numtocurrency2[1].equals("00")){}
                        else{
                             numbtowordsCur+=" and Cents";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]));
                                 }
                             
                             }
                    }
                         if(currencyUnicode.equals("EURO")){
                         numbtowordsCur1+="Euro ";
                        if(numtocurrency2[1].length()==1){
                        numtocurrencychkzero=numtocurrency2[1]+"0";
                                if(numtocurrencychkzero.equals("00")){}
                                else{
                                numbtowordsCur+=" and Cents";
                                numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]+"0"));
                            }
                        }
                    else{
                        if(numtocurrency2[1].equals("00")){}
                        else{
                        numbtowordsCur+=" and Cents";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]));
                            }
                    }
                         }
                          if(currencyUnicode.equals("SGD")){
                         numbtowordsCur1+=" Singapore Dollars ";
                        if(numtocurrency2[1].length()==1){
                            numtocurrencychkzero=numtocurrency2[1]+"0";
                            if(numtocurrencychkzero.equals("00")){}
                         else{
                            numbtowordsCur+=" and Cents";
                            numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]+"0"));
                            }
                            }
                        else{
                             if(numtocurrency2[1].equals("00")){}
                    else{
                             numbtowordsCur+=" and Cents";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]));
                                  }
                              }
                        }
                        
                         if(currencyUnicode.equals("AUD")){
                         numbtowordsCur1+="Australian Dollars ";
                        if(numtocurrency2[1].length()==1){
                            numtocurrencychkzero=numtocurrency2[1]+"0";
                    if(numtocurrencychkzero.equals("00")){}
 else{

                        numbtowordsCur+=" and Cents";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]+"0"));
                }}
 else{
                             if(numtocurrency2[1].equals("00")){}
 else{
                        numbtowordsCur+=" and Cents";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]));
                            }
 }
                        }
                         if(currencyUnicode.equals("RM")){
                         numbtowordsCur1+="Ringgit ";
                        if(numtocurrency2[1].length()==1){
                      numtocurrencychkzero=numtocurrency2[1]+"0";
if(numtocurrencychkzero.equals("00")){}
 else{
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]+"0"));
                            }

                }
 else{
                              if(numtocurrency2[1].equals("00")){}
 else{
                             numbtowordsCur+=" and Sen";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]));
                            }
 }
                        }
                         if(currencyUnicode.equals("HKD")){
                         numbtowordsCur1+="Hong Kong ";
                        if(numtocurrency2[1].length()==1){
                            numtocurrencychkzero=numtocurrency2[1]+"0";
if(numtocurrencychkzero.equals("00")){}
 else{
                        numbtowordsCur+=" and Cents";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]+"0"));
                            }

                }
 else{
                             if(numtocurrency2[1].equals("00")){}
                             else{
                             numbtowordsCur+=" and Cents";
                        numbtowordsCur+=" "+numToword.convert(Long.parseLong(numtocurrency2[1]));
                        
 }
                        }}
                      //  System.out.println(numbtowordsCur);
                Row.setHorizontalAlignment(Element.ALIGN_CENTER);
                Row.setBorderWidth(1f);
                summTable.addCell(Row);
                
                
            } else {
                Row = new PdfPCell(new Paragraph("", small));
                Row.setBorderWidth(1f);
                summTable.addCell(Row);
            }
            
            Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Row.disableBorderSide(PdfPCell.TOP);
            lineTable.addCell(Row);

            // Invoice Number in Head
            document.add(numberPara);
            document.add(Chunk.NEWLINE);

            // Show Logo
            document.add(table);
            //document.add(Chunk.NEWLINE);

            // Header Page Content
            if (billingLocation.equals("1")) {
                document.add(new Paragraph("                       Supply meant for Export under letter of undertaking without payment of Integrated Tax (IGST)",small));
            }
            //document.add(new Paragraph("\n"));
            
            // Draw line
            document.add(new Paragraph("\n"));
            
document.add(table2);
document.add(new Paragraph("\n"));
document.add(line);
document.add(new Paragraph("\n"));
document.add(new Paragraph("\n"));
            // Summary Header
           

            // Add Summary into Report
            if (billingLocation.equals("1")) {
            document.add(lineTable);
            document.add(new Paragraph("                                                                                                                                                                                                                          CGST(9%)           =          0.00",smallNote));
            document.add(new Paragraph("                                                                                                                                                                                                                          SGST(9%)           =          0.00",smallNote));
            document.add(new Paragraph("                                                                                                                                                                                                                          IGST(18%)          =           0.00",smallNote));
            document.add(new Paragraph("           "+numbtowordsCur1+numbtowordsCur+" Only",smallBold));
//System.out.println("chk"+numbtowordsCur1+numbtowordsCur);
            }
 else{
            document.add(summTable);}

            //Show bank details
            if (billingLocation.equals("1")) {
                document.add(new Paragraph("\n\n\n"));
                document.add(new Paragraph("Wire Transaction Information:", subFontUL));
                document.add(new Chunk("Kindly credit to State Bank of India ",small));
                document.add(new Chunk("(SWIFT ID: SBININBB605) ",subFont));
                document.add(new Chunk(" for full and credit to:\n",small));
                document.add(new Chunk("A/C Number    : 40147153848 ",subFont));
                document.add(new Chunk("of S4Carlisle Publishing Services Pvt. Ltd held with State Bank of India\n",small));
                document.add(new Chunk("Branch Name : ",subFont));
                document.add(new Chunk("SME Adyar Branch\n",small));
                document.add(new Chunk("Bank address : ",subFont));
                document.add(new Chunk("IInd Floor, #5 Ist cross street, Kasturba Nagar, Adyar, Chennai 600020, India\n",small));
               // document.add(new Chunk("Branch Code  : ",subFont));
               // document.add(new Chunk("40154\n",small));
                //document.add(new Chunk("IFSC Code      : ",subFont));
                //document.add(new Chunk("SBININBBM13\n",small));
                String logoPath1 = getServletContext().getRealPath("");
                logoPath1 = logoPath1 + File.separator + "webimages\\Nanda Sign1.jpg";
                com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance(logoPath1);
                image1.scaleAbsolute(220f, 80f);
                image1.setAbsolutePosition(340,98);
                image1.setAlignment(Image.RIGHT);
                //image1.setAlignment(50);
                //document.add(image1);
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                
                //document.add(line);
                //document.add(new Paragraph("IEC No.0401004317                                                                                                                                Authorized Dealer Code No.:01201549000009",small));
              }  else if (billingLocation.equals("3")) {
                document.add(new Paragraph("\n\n\n\n\n\n\n"));
                document.add(new Paragraph("Wire Transaction Information:", subFontUL));
                document.add(new Chunk("Kindly credit to OCBC Bank ",small));
                document.add(new Chunk("(SWIFT ID: OCBCSGSG) ",subFont));
                document.add(new Chunk(" for full and credit to:\n",small));
                document.add(new Chunk("A/C Number    : 503-270068-301 ",subFont));
                document.add(new Chunk("of S4Carlisle Publishing Services Pte. Ltd held with OCBC Bank\n",small));
                document.add(new Chunk("Bank address : ",subFont));
                document.add(new Chunk("65, Chulia Street, No 01-00,Singapore 049513\n",small));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                String logoPath3 = getServletContext().getRealPath("");
                logoPath3 = logoPath3 + File.separator + "webimages\\Nanda Sign1.jpg";
                com.itextpdf.text.Image image3 = com.itextpdf.text.Image.getInstance(logoPath3);
                image3.scaleAbsolute(220f, 80f);
                image3.setAbsolutePosition(340,98);
                image3.setAlignment(Image.RIGHT);
                document.add(image3);
               }
            else if (billingLocation.equals("4")) {
//                document.add(new Paragraph("\n\n\n\n\n\n\n"));
//                document.add(new Paragraph("Pay by ACH", subFontUL));
//                document.add(new Chunk("A/C Number :           446033003421 \n",subFont));
//                document.add(new Chunk("Bank Name :            ",subFont));
//                document.add(new Chunk("Bank of America \n",small));
//                document.add(new Chunk("Beneficiary Name :  ",subFont));
//                document.add(new Chunk("S4Carlisle Transmedia Services Inc\n",small));
//                document.add(new Chunk("Bank address :         ",subFont));
//                document.add(new Chunk("10400 Old Georgetown Road, Bethesda MD 20814\n",small));
//                document.add(new Chunk("Routing Number (ACH) : ",subFont));
//                document.add(new Chunk("052001633\n",small));
                //document.add(new Chunk("Routing Number (WIRE): 026009593\n",subFont));
                document.add(new Paragraph("\n\n\n\n\n\n\n"));
                document.add(new Paragraph("Pay by ACH", subFontUL));
                document.add(new Chunk("A/C Number :446033003421 \n",subFont));
                document.add(new Chunk("Bank Name :",subFont));
                document.add(new Chunk("Bank of America \n",small));
                document.add(new Chunk("Beneficiary Name :  ",subFont));
                document.add(new Chunk("S4Carlisle Transmedia Services Inc\n",small));
                document.add(new Chunk("Bank address :",subFont));
                document.add(new Chunk("10400 Old Georgetown Road, Bethesda MD 20814\n",small));
                document.add(new Chunk("Routing Number (ACH) : ",subFont));
                document.add(new Chunk("052001633\n",small));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                String logoPathUS = getServletContext().getRealPath("");
                logoPathUS = logoPathUS + File.separator + "webimages\\Nanda Sign TI.jpg";
                com.itextpdf.text.Image imageUS = com.itextpdf.text.Image.getInstance(logoPathUS);
                imageUS.scaleAbsolute(220f, 80f);
                imageUS.setAbsolutePosition(340,98);
                imageUS.setAlignment(Image.RIGHT);
                document.add(imageUS);
               }
             else if (billingLocation.equals("5")) {
                document.add(new Paragraph("\n\n\n\n\n\n\n"));
                document.add(new Paragraph("Wire Transaction Information:", subFontUL));
                document.add(new Chunk("Kindly credit to OCBC Bank ",small));
                document.add(new Chunk("(SWIFT ID: OCBCSGSG) ",subFont));
                document.add(new Chunk(" for full and credit to:\n",small));
                document.add(new Chunk("A/C Number    : 689-654879-001 ",subFont));
                document.add(new Chunk("of S4Carlisle Publishing Services Pte. Ltd held with OCBC Bank\n",small));
                document.add(new Chunk("Bank address : ",subFont));
                document.add(new Chunk("65, Chulia Street, No 01-00,Singapore 049513\n",small));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                String logoPath3 = getServletContext().getRealPath("");
                logoPath3 = logoPath3 + File.separator + "webimages\\Nanda Sign1.jpg";
                com.itextpdf.text.Image image3 = com.itextpdf.text.Image.getInstance(logoPath3);
                image3.scaleAbsolute(220f, 80f);
                image3.setAbsolutePosition(340,98);
                image3.setAlignment(Image.RIGHT);
                document.add(image3);
               }
            
            //Next Page
            //document.newPage();
                //image1.scaleAbsoluteHeight(64);
                 //image1.scaleAbsoluteWidth(211);

            // Create a table to display Logo.
            //float[] colsWidth1 = {4f, 3f}; // Code 1
            //PdfPTable tableSing = new PdfPTable(colsWidth1);
            //tableSing.setWidthPercentage(100);
            //tableSing.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            //tableSing.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            //PdfPCell cellSing = new PdfPCell(new PdfPCell(image1));
            //cellSing.setMinimumHeight(70f);
            //disableBorders(cellSing);
            //tableSing.addCell(cellSing);
           //document.add(tableSing);
             //    document.add(Chunk.NEWLINE);
            // Add Headers into Report
if (billingLocation.equals("1")) {}
 else{
   document.newPage();
   document.add(lineItem);
   document.add(projProp);
   document.add(new Paragraph("\n"));
 }
            
      

            // Add Line Items into Report
            if (billingLocation.equals("1")) {
            document.add(summ);
            document.add(new Paragraph("\n"));
            document.add(summTable);
            }
 else{
            document.add(lineTable);}
            if (billingLocation.equals("1")) {
            }
            String logoPath2 = getServletContext().getRealPath("");
            if(billingLocation.equals("4")) {
            logoPath2 = logoPath2 + File.separator + "webimages\\Nanda Sign TI.jpg";
            }
 else {
                logoPath2 = logoPath2 + File.separator + "webimages\\Nanda Sign1.jpg";
 }
            
            com.itextpdf.text.Image image2 = com.itextpdf.text.Image.getInstance(logoPath2);
            image2.scaleAbsolute(220f, 80f);
            image2.setAbsolutePosition(340,98);
            //image1.setAbsolutePosition(50f, 70f);
            image2.setAlignment(Image.RIGHT);
           //image2.setAlignment(Image.BOX);
          document.add(image2);
          document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*
         out.println("<table border=1>");

         out.println("<tr>");
         out.println("<td>");
         out.println("Estimate Quantity");
         out.println("</td>");
         out.println("<td>");
         out.println("Invoice Quantity");
         out.println("</td>");
         out.println("<td>");
         out.println("Category");
         out.println("</td>");
         out.println("<td>");
         out.println("Line Item");
         out.println("</td>");
         out.println("<td>");
         out.println("Unit Price");
         out.println("</td>");
         out.println("<td>");
         out.println("Estimated Values");
         out.println("</td>");
         out.println("<td>");
         out.println("Actual Values");
         out.println("</td>");
         out.println("<td>");
         out.println("Partial Values");
         out.println("</td>");
         out.println("<td>");
         out.println("Due this Invoice");
         out.println("</td>");
         out.println("</tr>");

         while (invoiceLineIterator.hasNext()) {
         Map.Entry pairs = (Map.Entry)invoiceLineIterator.next();
         java.util.List categoryAndLineBasedInvoices = (ArrayList) pairs.getValue();
         for (int invoiceLineArrayCtr = 0; invoiceLineArrayCtr < categoryAndLineBasedInvoices.size(); invoiceLineArrayCtr++) {
         InvoiceLine invoiceLine = (InvoiceLine) categoryAndLineBasedInvoices.get(invoiceLineArrayCtr);

         out.println("<tr>");
         out.println("<td>");
         out.println(invoiceLine.getEstimateQuantity());
         out.println("</td>");
         out.println("<td>");
         out.println(invoiceLine.getInvoiceQuantity());
         out.println("</td>");
         out.println("<td>");
         out.println(invoiceLine.getCategory());
         out.println("</td>");
         out.println("<td>");
         out.println(invoiceLine.getDescription());
         out.println("</td>");
         out.println("<td>");
         out.println(invoiceLine.getUnitPrice());
         out.println("</td>");
         out.println("<td>");
         out.println(invoiceLine.getEstimatedValue());
         out.println("</td>");
         out.println("<td>");
         out.println(invoiceLine.getActualValue());
         out.println("</td>");
         out.println("<td>");
         out.println(invoiceLine.getPreviouslyInvoicedValue());
         out.println("</td>");
         out.println("<td>");
         out.println(invoiceLine.getInvoiceTotal());
         out.println("</td>");
         out.println("</tr>");
         }
         }
         out.println("</table>");

         return;

         /*
         if(finalOrProforma)
         {
         // Iterate through the previous invoices

         }*/
























        /*
         response.setContentType("application/pdf");

         //Show current time stamp
         javax.servlet.http.HttpSession session = request.getSession();
         String showTimeStamp = "";
         String emp_fcy = session.getAttribute("empfacility").toString();

         java.util.Date date = new java.util.Date();
         SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
         if (emp_fcy == "1" || emp_fcy.equals("1")) {
         //Show Current time stamp of Chennai
         showTimeStamp = format.format(date);
         } else {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.add(Calendar.HOUR, -10);
         calendar.add(Calendar.MINUTE, -30);
         date = calendar.getTime();
         //Show Current time stamp of Dubuque
         showTimeStamp = format.format(date);
         }

         //Get parameters
         String getInvoiceNumber = request.getParameter("invoiceNumber") != null ? request.getParameter("invoiceNumber") : "";
         String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
         String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
         String getInvoicePartFlag = request.getParameter("invoicePartFlag") != null ? request.getParameter("invoicePartFlag") : "";
         String getInvoicePartNumber = request.getParameter("invoicePartNumber") != null ? request.getParameter("invoicePartNumber") : "";

         String getVendorNumber = "";
         String fileName = project_name + "_Invoice.pdf";

         response.setHeader("Cache-Control", "max-age=30");
         response.setHeader("Content-Disposition", " inline; filename=" + fileName);
         response.setContentType("application/pdf");

         try {

         System.out.println("inside generatePDf");

         Document document = new Document();

         PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

         document.open();

         Paragraph numberPara = new Paragraph();

         pathfinder.functions.revenue.InvoiceSummaryInfo summary = new pathfinder.functions.revenue.InvoiceSummaryInfo();
         summary.setInvoiceNumber(getInvoiceNumber);
         summary.getInvoiceSummary();
         //java.util.List poDate = summary.getInvoiceDate();
         java.util.List getInvDispNum = summary.getProjInvDispNum();
         //java.util.List getPartNo = summary.getPartFlag();

         //add estimation number
         numberPara.add(new Paragraph("Invoice Number: " + getInvDispNum.get(0).toString(), headerFont));
         numberPara.setAlignment(Element.ALIGN_RIGHT);
         numberPara.setIndentationRight(45);
         document.add(numberPara);

         //Add empty line
         document.add(Chunk.NEWLINE);

         pathfinder.functions.revenue.InvoiceInfo det = new pathfinder.functions.revenue.InvoiceInfo();
         det.setInvoiceNumber(getInvoiceNumber);
         det.getInvoiceInfo();

         /* Create table to add logo,customer,carlisle job ie.project name in one row
         From address and terms in second row */
        /*String logoPath = getServletContext().getRealPath("");
         logoPath = logoPath + File.separator + "webimages\\logopng.png";
         com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(logoPath);
         image.scaleAbsoluteHeight(64);
         image.scaleAbsoluteWidth(211);

         //Create a table.
         float[] colsWidth = {4f, 3f}; // Code 1
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

         //Get the project properties
         pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
         projInfo.setProjId(getProjIdParam);
         projInfo.collectProjectName();

         //Create nested Table
         PdfPTable nestedTable;
         nestedTable = new PdfPTable(2);
         nestedTable.setWidthPercentage(100);
         nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

         PdfPCell cell3 = new PdfPCell(new Paragraph("Date of Invoice: ", subFont));
         cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell3.setMinimumHeight(20f);
         disableBorders(cell3);

         PdfPCell cell4 = new PdfPCell(new Paragraph(showTimeStamp, small));
         cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell4.setMinimumHeight(20f);
         disableBorders(cell4);

         //Display vendor number if it is present. else do not display
         String vendorNum = projInfo.getVendorNumber();
         PdfPCell cell5 = null;
         PdfPCell cell6 = null;
         if (vendorNum != null && !vendorNum.equals("")) {

         cell5 = new PdfPCell(new Paragraph("Vendor Number: ", subFont));
         disableBorders(cell5);
         cell5.setMinimumHeight(20f);
         cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);

         cell6 = new PdfPCell(new Paragraph(projInfo.getVendorNumber(), small));
         cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell6.setMinimumHeight(20f);
         disableBorders(cell6);
         }

         PdfPCell cell7 = new PdfPCell(new Paragraph("Customer #:", subFont));
         disableBorders(cell7);
         cell7.setMinimumHeight(20f);
         cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);

         String tempClient = projInfo.getDivision();
         //tempClient = tempClient.replaceAll(".*\\(|\\).*", "");      //Extract Code
         PdfPCell cell8 = new PdfPCell(new Paragraph(tempClient, small));
         cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell8.setMinimumHeight(20f);
         disableBorders(cell8);

         PdfPCell vendor_num = new PdfPCell(new Paragraph("Vendor # :", subFont));
         disableBorders(vendor_num);
         vendor_num.setMinimumHeight(20f);
         vendor_num.setHorizontalAlignment(Element.ALIGN_RIGHT);

         PdfPCell vendor_num_val = new PdfPCell(new Paragraph(getVendorNumber, small));
         vendor_num_val.setHorizontalAlignment(Element.ALIGN_LEFT);
         vendor_num_val.setMinimumHeight(20f);
         disableBorders(vendor_num_val);

         PdfPCell cell113 = new PdfPCell(new Paragraph("Customer P.O.: ", subFont));
         disableBorders(cell113);
         cell113.setMinimumHeight(20f);
         cell113.setHorizontalAlignment(Element.ALIGN_RIGHT);

         PdfPCell cell13a = new PdfPCell(new Paragraph(projInfo.getCustomerPO(), small));
         cell13a.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell13a.setMinimumHeight(20f);
         disableBorders(cell13a);

         PdfPCell cell9 = new PdfPCell(new Paragraph("S4Carlisle Job #:", subFont));
         cell9.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell9.setMinimumHeight(20f);
         disableBorders(cell9);

         PdfPCell cell10 = new PdfPCell(new Paragraph(project_name, small));
         cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell10.setMinimumHeight(20f);
         disableBorders(cell10);

         PdfPCell cell11 = new PdfPCell(new Paragraph("Terms: ", subFont));
         cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell11.setMinimumHeight(20f);
         disableBorders(cell11);

         PdfPCell cell12 = new PdfPCell(new Paragraph(det.getTermsName(), small));
         cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell12.setMinimumHeight(20f);
         disableBorders(cell12);

         PdfPCell cell13 = new PdfPCell(new Paragraph("Type of Invoice: ", subFont));
         cell13.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell13.setMinimumHeight(20f);
         disableBorders(cell13);

         PdfPCell cell14 = null;

         if (!getInvoicePartFlag.equals("")) {
         if (getInvoicePartFlag.equals("0")) {
         cell14 = new PdfPCell(new Paragraph("Final", small));
         } else if (getInvoicePartFlag.equals("1") && !getInvoicePartNumber.equals("")) {
         cell14 = new PdfPCell(new Paragraph("Partial " + getInvoicePartNumber, small));
         } else if (getInvoicePartFlag.equals("2")) {
         cell14 = new PdfPCell(new Paragraph("Proforma", small));
         }
         cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell14.setMinimumHeight(20f);
         disableBorders(cell14);
         }

         //Buyer address
         Paragraph buyer = new Paragraph("2436 Meinen Court," + "\n" + "Dubuque, IA 52002" + "\n" + "Telephone: (563) 557-1500" + "\n" + "Fax: (563) 557-1376", small);
         PdfPCell buyerAdd = new PdfPCell(buyer);
         buyerAdd.setIndent(60);
         buyerAdd.setHorizontalAlignment(Element.ALIGN_LEFT);
         buyerAdd.setMinimumHeight(20f);
         disableBorders(buyerAdd);

         nestedTable.addCell(cell3);
         nestedTable.addCell(cell4);
         if (vendorNum != null && !vendorNum.equals("")) {
         nestedTable.addCell(cell5);
         nestedTable.addCell(cell6);
         }
         nestedTable.addCell(cell7);
         nestedTable.addCell(cell8);
         nestedTable.addCell(vendor_num);
         nestedTable.addCell(vendor_num_val);
         nestedTable.addCell(cell113);
         nestedTable.addCell(cell13a);
         nestedTable.addCell(cell9);
         nestedTable.addCell(cell10);

         cell2.addElement(nestedTable);

         table.addCell(cell1);
         table.addCell(cell2);

         //Create new nested table
         nestedTable = new PdfPTable(2);
         nestedTable.setWidthPercentage(100);
         nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

         //nestedTable.addCell(cell11);
         //nestedTable.addCell(cell12);
         nestedTable.addCell(cell13);
         nestedTable.addCell(cell14);

         PdfPCell nestedCell1 = new PdfPCell(nestedTable);
         disableBorders(nestedCell1);

         table.addCell(buyerAdd);
         table.addCell(nestedCell1);

         document.add(table);

         //Add empty line
         document.add(Chunk.NEWLINE);

         // Add Bill To and Write Transaction
         float[] tableWidth = {1f, 3.5f, 3f}; // Code 1
         PdfPTable table2 = new PdfPTable(tableWidth);

         table2.setWidthPercentage(100);

         //Bill To
         PdfPCell billToDet = new PdfPCell(new Paragraph("Bill To: ", subFont));
         billToDet.setHorizontalAlignment(Element.ALIGN_RIGHT);
         disableBorders(billToDet);
         //Seller Name and street name
         String buyerDetails = projInfo.getBuyer() + "\n";
         //System.out.println("@@@@1"+projInfo.getBuyerAddress()+"_"+projInfo.getBuyerPhone()+"_"+projInfo.getBuyerFax());
         if (!projInfo.getBuyerAddress().equals(null) && !projInfo.getBuyerAddress().equals("")) {
         buyerDetails = buyerDetails + projInfo.getBuyerAddress() + "\n";
         }
         if (!projInfo.getBuyerPhone().equals(null) && !projInfo.getBuyerPhone().equals("")) {
         buyerDetails = buyerDetails + "Telephone: " + projInfo.getBuyerPhone() + "\n";
         }
         if (!projInfo.getBuyerFax().equals(null) && !projInfo.getBuyerFax().equals("")) {
         buyerDetails = buyerDetails + "Fax: " + projInfo.getBuyerFax() + "\n";
         }
         //System.out.println("Buyer Details :\n"+buyerDetails);
         PdfPCell billToSt = new PdfPCell(new Paragraph(projInfo.getClientAddress() + "\n", small));
         billToSt.setHorizontalAlignment(Element.ALIGN_LEFT);
         disableBorders(billToSt);

         //Wire Transaction Information
         PdfPCell wireTrans = new PdfPCell(new Paragraph("Wire Transaction Information" + "\n" + "Dubuque Bank & Trust" + "\n" + "1398 Central Avenue" + "\n" + "Dubuque, IA 52001" + "\n" + "Acct #: 10-760-3" + "\n", small));
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
         for (int index = 0; index < pai.getPrimaryAuthor().size(); index++) {
         if (dispAuthors.length() > 50) {
         dispAuthors = dispAuthors + "\n";
         }
         dispAuthors = dispAuthors + pai.getContactFirstName().get(index).toString() + " " + pai.getContactSecondName().get(index).toString();
         if (index < pai.getPrimaryAuthor().size() - 1) {
         dispAuthors = dispAuthors + " / ";
         }
         }
         PdfPCell author = new PdfPCell(new Paragraph("Author:", subFont));
         author.setHorizontalAlignment(Element.ALIGN_RIGHT);
         disableBorders(author);
         PdfPCell authorVal = new PdfPCell(new Paragraph(dispAuthors, small));
         authorVal.setHorizontalAlignment(Element.ALIGN_LEFT);
         disableBorders(authorVal);

         PdfPCell copyright = new PdfPCell(new Paragraph("Copyright:", subFont));
         copyright.setHorizontalAlignment(Element.ALIGN_RIGHT);
         disableBorders(copyright);
         PdfPCell copyrightVal = new PdfPCell(new Paragraph(projInfo.getCopyRight(), small));
         copyrightVal.setHorizontalAlignment(Element.ALIGN_LEFT);
         disableBorders(copyrightVal);

         PdfPCell title = new PdfPCell(new Paragraph("Title:", subFont));
         title.setHorizontalAlignment(Element.ALIGN_RIGHT);
         disableBorders(title);
         PdfPCell titleVal = new PdfPCell(new Paragraph(projInfo.getTitle(), small));
         titleVal.setHorizontalAlignment(Element.ALIGN_LEFT);
         disableBorders(titleVal);

         PdfPCell isbn = new PdfPCell(new Paragraph("ISBN - 10 / 13:", subFont));
         isbn.setHorizontalAlignment(Element.ALIGN_RIGHT);
         disableBorders(isbn);
         PdfPCell isbnVal = new PdfPCell(new Paragraph(projInfo.getISBN10() + " / " + projInfo.getISBN13(), small));
         isbnVal.setHorizontalAlignment(Element.ALIGN_LEFT);
         disableBorders(isbnVal);

         PdfPCell emptyCell = new PdfPCell();
         disableBorders(emptyCell);

         table2.addCell(billToDet);
         table2.addCell(billToSt);
         table2.addCell(emptyCell);
         //table2.addCell(wireTrans);

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

         //next line
         document.add(new Paragraph("\n"));

         //Add Invoice summary
         Paragraph summ = new Paragraph("Invoice Summary", headerFont);
         summ.setIndentationLeft(25);

         document.add(summ);

         //next line
         document.add(new Paragraph("\n"));

         PdfPTable summTable = new PdfPTable(4); //Code 3
         summTable.setHorizontalAlignment(Element.ALIGN_CENTER);
         summTable.setWidthPercentage(80);
         summTable.getDefaultCell().setBorderWidth(1f);

         PdfPCell cell = new PdfPCell(new Paragraph("Estimate", subFont));
         cell.setBorderWidth(1f);
         summTable.addCell(cell);
         summTable.addCell(new Paragraph("Actual", subFont));
         summTable.addCell(new Paragraph("Previously Invoiced", subFont));
         summTable.addCell(new Paragraph("Due This Invoice", subFont));

         float[] widths = {3f, 2f};                  // Code 1

         PdfPTable lineItem = new PdfPTable(widths); // Code 3
         lineItem.setWidthPercentage(100);
         PdfPCell s4logo = new PdfPCell(new Paragraph("S4Carlisle Publishing Services", headerFont));
         disableBorders(s4logo);
         PdfPCell poNum = new PdfPCell(new Paragraph("Invoice Number: " + getInvDispNum.get(0).toString(), headerFont));
         disableBorders(poNum);

         lineItem.addCell(s4logo);
         lineItem.addCell(poNum);

         //Table for project properties
         float[] propColWidth = {0.4f, 2.7f, 0.8f, 1.5f}; // Code 1

         PdfPTable projProp = new PdfPTable(propColWidth);
         projProp.setWidthPercentage(100);
         projProp.addCell(author);
         disableBorders(author);
         projProp.addCell(authorVal);
         disableBorders(authorVal);
         cell9.setMinimumHeight(8f);
         projProp.addCell(cell9);
         disableBorders(cell9);
         cell10.setMinimumHeight(8f);
         projProp.addCell(cell10);
         disableBorders(cell10);
         projProp.addCell(title);
         disableBorders(title);
         projProp.addCell(titleVal);
         disableBorders(titleVal);
         projProp.addCell(copyright);
         disableBorders(copyright);
         projProp.addCell(copyrightVal);
         disableBorders(copyrightVal);
         projProp.addCell(emptyCell);
         projProp.addCell(emptyCell);
         projProp.addCell(isbn);
         disableBorders(isbn);
         projProp.addCell(isbnVal);
         disableBorders(isbnVal);

         //Display po line items
         float[] lineItemsWidths = {7f, 7f, 25f, 9f, 9f, 9f, 9f, 10f};
         PdfPTable lineTable = new PdfPTable(8); //Code 3
         lineTable.setWidthPercentage(100);
         lineTable.setHeaderRows(1);
         lineTable.setSplitRows(false);
         lineTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

         PdfPCell lineCell = new PdfPCell(new Paragraph("Estimate Qty", subFont));
         lineCell.setBorderWidth(1f);
         lineTable.addCell(lineCell);

         lineCell = new PdfPCell(new Paragraph("Invoice Qty", subFont));
         lineCell.setBorderWidth(1f);
         lineTable.addCell(lineCell);

         lineCell = new PdfPCell(new Paragraph("Description", subFont));
         lineCell.setBorderWidth(1f);
         lineTable.addCell(lineCell);

         lineCell = new PdfPCell(new Paragraph("Unit Price", subFont));
         lineCell.setBorderWidth(1f);
         lineTable.addCell(lineCell);

         lineCell = new PdfPCell(new Paragraph("Estimate", subFont));
         lineCell.setBorderWidth(1f);
         lineTable.addCell(lineCell);

         lineCell = new PdfPCell(new Paragraph("Actual", subFont));
         lineCell.setBorderWidth(1f);
         lineTable.addCell(lineCell);

         lineCell = new PdfPCell(new Paragraph("Previously Invoiced", subFont));
         lineCell.setBorderWidth(1f);
         lineTable.addCell(lineCell);

         lineCell = new PdfPCell(new Paragraph("Invoice Total", subFont));
         lineCell.setBorderWidth(1f);
         lineTable.addCell(lineCell);

         lineTable.getDefaultCell().enableBorderSide(PdfPCell.LEFT);
         lineTable.getDefaultCell().enableBorderSide(PdfPCell.RIGHT);

         lineTable.getDefaultCell().setBorderWidth(1f);
         lineTable.setWidths(lineItemsWidths);

         //For Final Invoice Line Items
         java.util.List item_id = new ArrayList();
         java.util.List item_name = new ArrayList();
         java.util.List item_rate = new ArrayList();
         java.util.List item_quantity = new ArrayList();
         java.util.List item_desc = new ArrayList();
         java.util.List item_value = new ArrayList();
         java.util.List cost_category_id = new ArrayList();
         java.util.List cost_category_name = new ArrayList();
         java.util.List invoiceEstItemId = new ArrayList();

         //For Estimated Line Items
         java.util.List estimatedQuantity = new ArrayList();
         java.util.List estimatedValue = new ArrayList();
         java.util.List estimatedInvItemId = new ArrayList();

         //For Partial Invoice Line Items
         java.util.List invoicedItemId = new ArrayList();
         java.util.List invoicedItemValue = new ArrayList();
         java.util.List invoicedItemQty = new ArrayList();
         java.util.List invoicedItemCat = new ArrayList();
         java.util.List invoicedItemRate = new ArrayList();
         java.util.List invoiceParEstId = new ArrayList();

         //Get Invoice Details
         pathfinder.functions.revenue.InvoiceLineItem lineItems = new pathfinder.functions.revenue.InvoiceLineItem();
         lineItems.setInvoiceNumber(getInvoiceNumber);
         lineItems.collectInvoiceLineItem();

         item_id.clear();
         item_name.clear();
         item_rate.clear();
         item_quantity.clear();
         //item_desc.clear();
         item_value.clear();
         cost_category_id.clear();
         cost_category_name.clear();

         //Get Final Invoice Details
         item_id = lineItems.getLineItemId();
         invoiceEstItemId = lineItems.getEst_line_item_id();
         item_name = lineItems.getLineItemName();
         item_rate = lineItems.getLineItemRate();
         item_quantity = lineItems.getLineItemQuantity();
         //item_desc = lineItems.getLineItemDesc();
         item_value = lineItems.getLineItemTotal();
         cost_category_id = lineItems.getCategoryId();
         cost_category_name = lineItems.getCategoryName();

         //Get Estimation Details
         pathfinder.functions.revenue.EstimateLineItems estLineItems = new pathfinder.functions.revenue.EstimateLineItems();
         estLineItems.setProjID(getProjIdParam);
         estLineItems.getEstimationForInvoice();
         estimatedInvItemId = estLineItems.getInvoice_est_item_id();
         estimatedQuantity = estLineItems.getLineItemQuantity();
         estimatedValue = estLineItems.getLineItemTotal();
         String showEstQty = "";
         String showEstValue = "";

         //Get Previous Invoice Details
         pathfinder.functions.revenue.InvoiceLineItem invoiceLineItems = new pathfinder.functions.revenue.InvoiceLineItem();
         invoiceLineItems.setProjID(getProjIdParam);
         invoiceLineItems.getPartialInvoiceLineItems();
         invoicedItemRate = invoiceLineItems.getLineItemRate();
         invoicedItemId = invoiceLineItems.getLineItemId();
         invoicedItemValue = invoiceLineItems.getLineItemTotal();
         invoicedItemQty = invoiceLineItems.getLineItemQuantity();
         invoicedItemCat = invoiceLineItems.getCategoryId();
         invoiceParEstId = invoiceLineItems.getEst_line_item_id();

         //Master Hash Table for Invoice Line Items (ILI)
         //Map<ArrayList, ArrayList> ILI = new HashMap<ArrayList, ArrayList>();
         Map<String, String> ILI = new HashMap<String, String>();
         for (int index = 0; index < invoicedItemCat.size(); index++) {
         //ILI.put([invoicedItemId.get(index),invoicedItemCat.get(index)], [invoicedItemQty.get(index),invoicedItemValue.get(index)]);
         ILI.put(invoicedItemId.get(index).toString(), invoicedItemQty.get(index).toString());
         }
         String showPreviousTotal = "";
         String showFinalVal = "";
         String showDueVal = "";

         Double rateTotal = 0.0;
         Double estimateTotal = 0.0;
         Double finalVal = 0.0;
         Double dueVal = 0.0;
         Double finalTotalVal = 0.0;
         Double previousTotal = 0.0;
         Double dueTotal = 0.0;
         Double invoiceTotal = 0.0;
         previous_category = "";
         current_category = "";
         PdfPCell lastRow;
         PdfPCell Row;

         String preCat = "";
         String curCat = "";
         if (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2")) {
         //Display Previously invoiced Category's line items
         for (int index = 0; index < invoicedItemCat.size(); index++) {
         if (!cost_category_id.contains(invoicedItemCat.get(index))) {
         curCat = invoicedItemCat.get(index).toString();
         if (!curCat.equals(preCat)) {
         //show the category
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(invoicedItemCat.get(index).toString(), blueFont));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         }
         //Computation Area of previously invoiced category's line items
         String estVal = "";
         String estQty = "";
         String preInv = "";
         if (estimatedInvItemId.contains(invoiceParEstId.get(index))) {
         estVal = "$ " + numberFormat.format(Double.parseDouble(estimatedValue.get(estimatedInvItemId.indexOf(invoiceParEstId.get(index))).toString()));
         estimateTotal += Double.parseDouble(estimatedValue.get(estimatedInvItemId.indexOf(invoiceParEstId.get(index))).toString());
         estQty = estimatedQuantity.get(estimatedInvItemId.indexOf(invoiceParEstId.get(index))).toString();
         }
         finalTotalVal = finalTotalVal + Double.parseDouble(invoicedItemValue.get(index).toString());
         preInv = invoicedItemValue.get(index).toString();
         previousTotal += Double.parseDouble(invoicedItemValue.get(index).toString());
         rateTotal += Double.parseDouble(invoicedItemRate.get(index).toString());

         //Display Area of previously invoiced category's line items
         if (estQty.equals("")) {
         Row = new PdfPCell(new Paragraph(estQty, small));
         } else {
         Row = new PdfPCell(new Paragraph(df.format(Double.parseDouble(estQty)), small));
         }
         Row.setHorizontalAlignment(Element.ALIGN_CENTER);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         if (invoicedItemQty.get(index).toString().equals("")) {
         Row = new PdfPCell(new Paragraph(invoicedItemQty.get(index).toString(), small));
         } else {
         Row = new PdfPCell(new Paragraph(df.format(Double.parseDouble(invoicedItemQty.get(index).toString())), small));
         }
         Row.setHorizontalAlignment(Element.ALIGN_CENTER);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         lineTable.addCell(new Paragraph("  " + invoicedItemId.get(index).toString(), small));

         Row = new PdfPCell(new Paragraph("  $ " + numberFormat.format(Double.parseDouble(invoicedItemRate.get(index).toString())), small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  " + estVal, small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  $ " + numberFormat.format(Double.parseDouble(preInv.toString())), small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  $ " + numberFormat.format(Double.parseDouble(invoicedItemValue.get(index).toString())), small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         lineTable.addCell(new Paragraph(" "));
         }
         preCat = curCat;
         }
         }

         //Loop to display final invoiced line items
         for (int loop = 0; loop < item_id.size(); loop++) {
         //String showCurretnCat = cost_category_id.get(loop).toString();
         if (cost_category_id.size() > 0) {
         current_category = cost_category_id.get(loop).toString();
         if (current_category.equals(previous_category)) {
         //do not show the category
         } else {
         if (!current_category.equals("")) {
         //show the category
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(cost_category_name.get(loop).toString(), blueFont));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         lineTable.addCell(new Paragraph(" "));
         }
         boolean show = false;
         //Loop to display previously invoiced line item. The category is invoiced in final, but only the line item is not.
         for (int index = 0; index < invoicedItemId.size(); index++) {
         //Condition to check whether the partial invoiced item is not in the final, then show.
         if (!item_name.contains(invoicedItemId.get(index))) {
         show = true;
         } else {
         show = true;
         for (int i = 0; i < item_id.size(); i++) {
         //Does not show when the line item as well as the category is same
         if (cost_category_id.get(i).equals(invoicedItemCat.get(index)) && item_id.get(i).equals(invoicedItemId.get(index))) {
         show = false;
         }
         }
         }
         if (current_category.equals(invoicedItemCat.get(index)) && show && (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2"))) {

         //Computation Area of previously invoiced line items in a category but not in final
         String estVal = "";
         String estQty = "";
         if (estimatedInvItemId.contains(invoiceParEstId.get(index))) {
         estVal = "$ " + numberFormat.format(Double.parseDouble(estimatedValue.get(estimatedInvItemId.indexOf(invoiceParEstId.get(index))).toString()));
         estimateTotal += Double.parseDouble(estimatedValue.get(estimatedInvItemId.indexOf(invoiceParEstId.get(index))).toString());
         estQty = estimatedQuantity.get(estimatedInvItemId.indexOf(invoiceParEstId.get(index))).toString();
         }
         finalTotalVal += Double.parseDouble(invoicedItemValue.get(index).toString());
         previousTotal += Double.parseDouble(invoicedItemValue.get(index).toString());
         rateTotal += Double.parseDouble(invoicedItemRate.get(index).toString());

         //Display Area of previously invoiced line items in a category but not in final
         if (estQty.equals("")) {
         Row = new PdfPCell(new Paragraph(estQty, small));
         } else {
         Row = new PdfPCell(new Paragraph(df.format(Double.parseDouble(estQty)), small));
         }
         Row.setHorizontalAlignment(Element.ALIGN_CENTER);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         if (invoicedItemQty.get(index).toString().equals("")) {
         Row = new PdfPCell(new Paragraph(invoicedItemQty.get(index).toString(), small));
         } else {
         Row = new PdfPCell(new Paragraph(df.format(Double.parseDouble(invoicedItemQty.get(index).toString())), small));
         }
         Row.setHorizontalAlignment(Element.ALIGN_CENTER);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         lineTable.addCell(new Paragraph("  " + invoicedItemId.get(index).toString(), small));

         Row = new PdfPCell(new Paragraph("  $ " + numberFormat.format(Double.parseDouble(invoicedItemRate.get(index).toString())), small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  " + estVal, small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  $ " + numberFormat.format(Double.parseDouble(invoicedItemValue.get(index).toString())), small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  $ " + numberFormat.format(Double.parseDouble(invoicedItemValue.get(index).toString())), small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         lineTable.addCell(new Paragraph(" "));
         }
         }
         }
         }
         lineTable.getDefaultCell().setColspan(1);

         //Computation and matching of Estimation, Partial and Final value by the name & category
         //To get estimate value of corresponding line item
         if (estimatedInvItemId.contains(invoiceEstItemId.get(loop)) && (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2"))) {
         showEstQty = estimatedQuantity.get(estimatedInvItemId.indexOf(invoiceEstItemId.get(loop).toString())).toString();
         showEstValue = "$ " + numberFormat.format(Double.parseDouble(estimatedValue.get(estimatedInvItemId.indexOf(invoiceEstItemId.get(loop).toString())).toString()));
         estimateTotal = estimateTotal + Double.parseDouble(estimatedValue.get(estimatedInvItemId.indexOf(invoiceEstItemId.get(loop).toString())).toString());

         }

         //To get partial value of corresponding line item
         if (!invoiceEstItemId.get(loop).equals("") && invoiceParEstId.contains(invoiceEstItemId.get(loop)) && (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2"))) {
         //Match with the Invoice Estimated ID
         Double partialTempTotal = 0.0;
         for (int i = 0; i < invoiceParEstId.size(); i++) {
         if (invoiceEstItemId.get(loop).equals(invoiceParEstId.get(i))) {
         partialTempTotal += Double.parseDouble(invoicedItemValue.get(invoiceParEstId.indexOf(invoiceParEstId.get(i))).toString());
         }
         }
         showPreviousTotal = "$ " + numberFormat.format(partialTempTotal);
         previousTotal = previousTotal + partialTempTotal;
         dueVal = Double.parseDouble(item_value.get(loop).toString()) - partialTempTotal;
         showDueVal = "$ " + numberFormat.format(dueVal);
         dueTotal += dueVal;
         } else if (invoicedItemId.contains(item_name.get(loop)) && invoicedItemCat.contains(cost_category_id.get(loop)) && invoicedItemRate.contains(item_rate.get(loop)) && (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2"))) {
         //Match with Invoice Line item Name, Category & rate
         String itemID = item_id.get(loop).toString();
         String catID = cost_category_id.get(loop).toString();
         String rate = item_rate.get(loop).toString();
         String partItemID = "";
         String partCatID = "";
         String partRate = "";
         Double partialTempTotal = 0.0;
         for (int i = 0; i < invoicedItemId.size(); i++) {
         partItemID = invoicedItemId.get(i).toString();
         partRate = invoicedItemRate.get(i).toString();
         partCatID = invoicedItemCat.get(i).toString();
         if (partItemID.equals(itemID) && partRate.equals(rate) && partCatID.equals(catID)) {
         partialTempTotal += Double.parseDouble(invoicedItemValue.get(i).toString());
         }
         }
         showPreviousTotal = "$ " + numberFormat.format(partialTempTotal);
         previousTotal = previousTotal + partialTempTotal;
         dueVal = Double.parseDouble(item_value.get(loop).toString()) - partialTempTotal;
         showDueVal = "$ " + numberFormat.format(dueVal);
         dueTotal += dueVal;
         } else {
         //If the line item is introduced in final only
         showDueVal = "$ " + item_value.get(loop).toString();
         dueTotal += Double.parseDouble(item_value.get(loop).toString());

         }

         if (invoicedItemId.contains(item_name.get(loop)) && (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2"))) {
         finalVal = Double.parseDouble(item_value.get(loop).toString());

         }

         if (loop + 1 == item_id.size()) {
         //If the item in the list is the last item, then we have to show the bottom border of the line item row(lastRow).
         if (showEstQty.equals("")) {
         lastRow = new PdfPCell(new Paragraph(showEstQty, small));
         } else {
         lastRow = new PdfPCell(new Paragraph(df.format(Double.parseDouble(showEstQty)), small));
         }
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_CENTER);
         lineTable.addCell(lastRow);

         if (item_quantity.get(loop).toString().equals("")) {
         lastRow = new PdfPCell(new Paragraph(item_quantity.get(loop).toString(), small));
         } else {
         lastRow = new PdfPCell(new Paragraph(df.format(Double.parseDouble(item_quantity.get(loop).toString())), small));
         }
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_CENTER);
         lineTable.addCell(lastRow);

         lastRow = new PdfPCell(new Paragraph("  " + item_name.get(loop).toString(), small));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lineTable.addCell(lastRow);

         lastRow = new PdfPCell(new Paragraph("  $ " + numberFormat.format(Double.parseDouble(item_rate.get(loop).toString())), small));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
         lineTable.addCell(lastRow);

         lastRow = new PdfPCell(new Paragraph("  " + showEstValue, small));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
         lineTable.addCell(lastRow);

         lastRow = new PdfPCell(new Paragraph("  " + showFinalVal, small));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
         lineTable.addCell(lastRow);

         lastRow = new PdfPCell(new Paragraph("  " + showPreviousTotal, small));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
         lineTable.addCell(lastRow);

         ////lastRow = new PdfPCell(new Paragraph("  $ "+numberFormat.format(Double.parseDouble(item_value.get(loop).toString())), small));
         lastRow = new PdfPCell(new Paragraph("  " + showDueVal, small));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
         lineTable.addCell(lastRow);
         } else {
         //If the item in the list is not the last item
         if (showEstQty.equals("")) {
         Row = new PdfPCell(new Paragraph(showEstQty, small));
         } else {
         Row = new PdfPCell(new Paragraph(df.format(Double.parseDouble(showEstQty)), small));
         }
         Row.setHorizontalAlignment(Element.ALIGN_CENTER);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         if (item_quantity.get(loop).toString().equals("")) {
         Row = new PdfPCell(new Paragraph(item_quantity.get(loop).toString(), small));
         } else {
         Row = new PdfPCell(new Paragraph(df.format(Double.parseDouble(item_quantity.get(loop).toString())), small));
         }
         Row.setHorizontalAlignment(Element.ALIGN_CENTER);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         lineTable.addCell(new Paragraph("  " + item_name.get(loop).toString(), small));

         Row = new PdfPCell(new Paragraph("  $ " + numberFormat.format(Double.parseDouble(item_rate.get(loop).toString())), small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  " + showEstValue, small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  " + showFinalVal, small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  " + showPreviousTotal, small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);

         Row = new PdfPCell(new Paragraph("  " + showDueVal, small));
         Row.setHorizontalAlignment(Element.ALIGN_RIGHT);
         Row.disableBorderSide(PdfPCell.TOP);
         Row.disableBorderSide(PdfPCell.BOTTOM);
         Row.setBorderWidth(1f);
         lineTable.addCell(Row);
         }
         previous_category = current_category;
         rateTotal = rateTotal + Double.parseDouble(item_rate.get(loop).toString());
         invoiceTotal = invoiceTotal + Double.parseDouble(item_value.get(loop).toString());
         showEstQty = "";
         showEstValue = "";
         showPreviousTotal = "";
         showFinalVal = "";
         showDueVal = "";
         finalVal = 0.0;
         dueVal = 0.0;

         }

         //Display Total
         String temp;
         String estimate_val = "";
         String actual_val = "";
         String prev_inv_val = "";
         String curr_inv_val = "";
         lineTable.addCell(emptyCell);

         lineTable.addCell(emptyCell);

         lineTable.addCell(emptyCell);

         lineTable.addCell(emptyCell);

         //Estimation Total
         temp = "";
         if (estimateTotal > 0 && (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2"))) {
         temp = "  $ " + numberFormat.format(Double.parseDouble(estimateTotal.toString()));
         estimate_val = "  $ " + numberFormat.format(Double.parseDouble(estimateTotal.toString()));
         }
         lastRow = new PdfPCell(new Paragraph(temp, subFont));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
         lineTable.addCell(lastRow);

         //Final Total
         temp = "";
         if (finalTotalVal > 0 && (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2"))) {
         temp = "  $ " + numberFormat.format(Double.parseDouble(finalTotalVal.toString()));
         actual_val = "  $ " + numberFormat.format(Double.parseDouble(finalTotalVal.toString()));
         }
         lastRow = new PdfPCell(new Paragraph(temp, subFont));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
         lineTable.addCell(lastRow);

         //Partial Total
         temp = "";
         if (previousTotal > 0 && (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2"))) {
         temp = "  $ " + numberFormat.format(Double.parseDouble(previousTotal.toString()));
         prev_inv_val = "  $ " + numberFormat.format(Double.parseDouble(previousTotal.toString()));
         }
         lastRow = new PdfPCell(new Paragraph(temp, subFont));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
         lineTable.addCell(lastRow);

         //Due Total
         temp = "";
         if (previousTotal > 0 && finalTotalVal > 0 && (getInvoicePartFlag.equals("0") || getInvoicePartFlag.equals("2"))) {
         temp = "  $ " + numberFormat.format(Double.parseDouble(dueTotal.toString()));
         curr_inv_val = "  $ " + numberFormat.format(Double.parseDouble(dueTotal.toString()));
         }
         lastRow = new PdfPCell(new Paragraph(temp, subFont));
         lastRow.setBorderWidth(1f);
         lastRow.disableBorderSide(PdfPCell.TOP);
         lastRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
         lineTable.addCell(lastRow);

         //Get the Invoice Summary data
         summTable.addCell(new Paragraph(estimate_val, smallBold));
         summTable.addCell(new Paragraph(actual_val, smallBold));
         summTable.addCell(new Paragraph(prev_inv_val, smallBold));
         summTable.addCell(new Paragraph(curr_inv_val, smallBold));

         // Add Summary Table into the PDF
         document.add(summTable);

         // Add new Page in the PDf
         document.newPage();

         // Headers
         document.add(lineItem);
         document.add(projProp);

         // The invoice details
         lineTable.setSpacingBefore(20);
         document.add(lineTable);
         document.close();

         } catch (DocumentException e) {
         e.printStackTrace();
         } catch (Exception e) {
         e.printStackTrace();
         }*/
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

    private static void enableBorders(PdfPCell cell) {
        cell.enableBorderSide(PdfPCell.LEFT);
        cell.enableBorderSide(PdfPCell.RIGHT);
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
