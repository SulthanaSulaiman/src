/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets.pdf;

import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import java.security.Timestamp;
import java.text.DateFormat;
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

/**
 *
 * @author Aravindhanj
 */
public class TransmittalPdfServlet extends HttpServlet {

    private static Font mainHeaderFont = new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD);
    private static Font subHeaderFont = new Font(Font.FontFamily.HELVETICA, 20, Font.NORMAL);
    private static Font descHeaderFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
    private static Font blockHeaderFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font normalFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
    private static Font subFontUnderLine = new Font(Font.FontFamily.HELVETICA, 9, Font.UNDERLINE | Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 9);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 8);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD , BaseColor.BLUE);
    private String previous_category;
    private String current_category;
    NumberFormat numberFormat = new DecimalFormat("###.00");
    DecimalFormat df = new DecimalFormat("0.###");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        String showTimeStamp = "";
        String emp_fcy = session.getAttribute("empfacility").toString();

        java.util.Date date= new java.util.Date();
        SimpleDateFormat format =new SimpleDateFormat("dd-MMM-yyyy");
        if(emp_fcy == "1" || emp_fcy.equals("1")) {
            showTimeStamp = format.format(date);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, -11);
            calendar.add(Calendar.MINUTE, -30);
            date = calendar.getTime();
            showTimeStamp = format.format(date);
        }

        response.setContentType("application/pdf");
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String getShipId = request.getParameter("shippingId") != null ? request.getParameter("shippingId") : "";

        boolean exp = false;
        String fileName = project_name + "_Transmittal.pdf";
        fileName = fileName.replaceAll(" ", "-");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);

        response.setContentType("application/pdf");


        try {

            //System.out.println("inside generatePDf");

            Document document = new Document();

            pathfinder.functions.revenue.TransmittalDAO transmittalDAO = new pathfinder.functions.revenue.TransmittalDAO();
            pathfinder.functions.revenue.TransmittalVO transmittalVO = new pathfinder.functions.revenue.TransmittalVO();

            String mailDate = "";
            String addressType = "";
            String carrierType = "";
            String shipWithAcct = "";
            String job = "";
            String option = "";
            String reference = "";
            String to = "";
            String remarks = "";
            String contentNote = "";
            String packageContent = "";
            String empId = "";
            String receiver = "";
            String transmittalId = "";
            String shippingId = "";
            String noteId = "";
            String shipMethod = "";
            String shipMethodId = "";
            String boundType = "";
            String inBoundFlag = "";
            String carrierTrackNo = "";
            String carrierCost = "";
            String shipToLoc = "";
            String shipDivisionName = "";
            String shipFromLoc = "";
            String requestDate = "";
            String shipTo = "";
            String shipToAttention = "";
            String shipFrom = "";
            String shipFromName = "";
            String shipToName = "";
            String division = "";

            boolean inBound = false;
            String dispEmp = "";
            String dispContact = "";
            String dispAttention = "";

            String[] contentTypeList;
            String[] packageContentList;

            if(!getShipId.equals("")) {
                transmittalVO.setShipId(getShipId);
                transmittalVO.setProjId(getProjIdParam);
                transmittalVO = transmittalDAO.GetTransmittalDetails(transmittalVO);
                mailDate = transmittalVO.getMailDate();
                addressType = transmittalVO.getAddressType();
                carrierType = transmittalVO.getCarrier();
                shipWithAcct = transmittalVO.getShipWithAcct();
                job = transmittalVO.getJob();
                option = transmittalVO.getOption();
                reference = transmittalVO.getReference();
                to = transmittalVO.getShipTo();
                remarks = transmittalVO.getRemarks();
                contentNote = transmittalVO.getContentNote();
                packageContent = transmittalVO.getPackageContent();
                empId = transmittalVO.getEmpId();
                receiver = transmittalVO.getReceiver();
                transmittalId = transmittalVO.getTransmittalId();
                shippingId = transmittalVO.getShippingId();
                noteId = transmittalVO.getNoteId();
                shipMethod = transmittalVO.getShipMethod();
                shipMethodId = transmittalVO.getShipMethodId();
                boundType = transmittalVO.getBoundType();
                inBoundFlag = transmittalVO.getInBoundFlag();
                carrierTrackNo = transmittalVO.getCarrierTrackNo();
                carrierCost = transmittalVO.getCarrierCost();
                shipToLoc = transmittalVO.getShipToLocation();
                shipFromLoc = transmittalVO.getShipFromLocation();
                requestDate = transmittalVO.getRequestDate();
                shipTo = transmittalVO.getShipTo();
                shipToAttention = transmittalVO.getShipToAttention();
                shipFrom = transmittalVO.getShipFrom();
                shipFromName = transmittalVO.getShipFromName();
                shipToName = transmittalVO.getShipToName();
                division = transmittalVO.getShipDivision();
                transmittalVO.setEmpId(empId);

                if(inBoundFlag.equals("1")) {
                    inBound = true;
                } else {
                    inBound = false;
                }
                
                // Set From and To address depend upon the Bound type
                // If it is InBound then To will be Employee and From will be Customer Contact and llly revers for OutBound
                if(inBound) {
                    transmittalVO.setEmpId(shipTo);
                    transmittalVO.setContact(shipFrom);
                    transmittalVO.setShipDivision(division);
                } else {
                    transmittalVO.setEmpId(shipFrom);
                    transmittalVO.setContact(shipTo);
                    transmittalVO.setShipDivision(division);
                }
                transmittalVO = transmittalDAO.GetEmpAndContactDetails(transmittalVO);

                // From and To Detail
                dispEmp = "";
                dispEmp += transmittalVO.getDispEmpName() != null ? transmittalVO.getDispEmpName() : "";
                dispEmp += transmittalVO.getDispEmpDesig() != null ? "\n"+transmittalVO.getDispEmpDesig() : "";

                dispContact = "";
                dispContact += transmittalVO.getDispContact() != null ? transmittalVO.getDispContact() : "";
                dispContact += (transmittalVO.getShipDivisionName() != null && !transmittalVO.getShipDivisionName().toString().equals("")) ? " - "+transmittalVO.getShipDivisionName() : "";
                dispContact += transmittalVO.getDispContactAdd() != null ? "\n"+transmittalVO.getDispContactAdd() : "";
                dispContact += transmittalVO.getDispContactCity() != null ? "\n"+transmittalVO.getDispContactCity() : "";
                dispContact += transmittalVO.getDispContactCountry() != null ? "\n"+transmittalVO.getDispContactCountry() : "";
                dispContact += transmittalVO.getDispContactCityCode() != null ? "\n"+transmittalVO.getDispContactCityCode() : "";

                dispAttention = transmittalVO.getDispShipToAttention() != null ? transmittalVO.getDispShipToAttention() : "";
            }

            //Get the project properties
            pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
            projInfo.setProjId(getProjIdParam);
            projInfo.collectProjectName();
            projInfo.projectDetails();
            // Manupulate list of Authors into to a String
            ArrayList authorName = (ArrayList) projInfo.getAuthorsList();
            String authors = "";
            for (int index = 0; index < authorName.size(); index++) {
                if (index > 0) {
                    authors += ", ";
                }
                authors += authorName.get(index) + " ";
            }
            
            //PdfWriter.getInstance(document, file);
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Paragraph numberPara = new Paragraph();

            /* Create table to add logo,customer,carlisle job ie.project name in one row
            From address and terms in second row */

            //Create logo
            String logoPath = getServletContext().getRealPath("");
            logoPath = logoPath + File.separator + "webimages\\logopng.png";
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(logoPath);
            image.scaleAbsoluteHeight(64);
            image.scaleAbsoluteWidth(211);

            //Create a table.
            float[] colsWidth = {5f, 8f}; // Code 1
            PdfPTable table = new PdfPTable(colsWidth);

            table.setWidthPercentage(100);
            table.getDefaultCell().setPadding(10);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell logo = new PdfPCell(new PdfPCell(image, false));
            logo.setHorizontalAlignment(Element.ALIGN_CENTER);
            logo.setMinimumHeight(70f);
            disableBorders(logo);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
            cell2.setIndent(20);
            disableBorders(cell2);

            //Create nested Table
            PdfPTable nestedTable;
            nestedTable = new PdfPTable(1);
            nestedTable.getDefaultCell().setPadding(10);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell;
            if(inBound) {
                cell = new PdfPCell(new Paragraph("Inbound Transmittal", subHeaderFont));
            } else {
                cell = new PdfPCell(new Paragraph("Outbound Transmittal", subHeaderFont));
            }
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(4);
            cell.setMinimumHeight(20f);
            disableBorders(cell);
            nestedTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(carrierType, descHeaderFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(4);
            cell.setMinimumHeight(20f);
            disableBorders(cell);
            nestedTable.addCell(cell);

            cell2.addElement(nestedTable);
            table.addCell(logo);
            table.addCell(cell2);

            document.add(table);

            //Add empty line
            document.add(new Paragraph("\n"));

            PdfPTable addrline = new PdfPTable(1);
            addrline.setWidthPercentage(100);

            PdfPCell addrCellTab = new PdfPCell(new Paragraph("2436 Meinen Court \t * \t Dubuque, IA 52002 \t * \t Telephone: (563) 557-1500 \t * \t Fax: (563) 557-1376", small));
            addrCellTab.setBorderWidth(1f);
            addrCellTab.setHorizontalAlignment(Element.ALIGN_CENTER);
            addrCellTab.disableBorderSide(PdfPCell.BOTTOM);
            addrCellTab.disableBorderSide(PdfPCell.LEFT);
            addrCellTab.disableBorderSide(PdfPCell.RIGHT);

            addrline.addCell(addrCellTab);
            document.add(addrline);

            PdfPTable jobNoteline = new PdfPTable(1);
            jobNoteline.setWidthPercentage(100);

            PdfPCell jobNotelineTab = new PdfPCell(new Paragraph("( JobNote ID "+noteId+" )", small));
            jobNotelineTab.setBorderWidth(1f);
            disableBorders(jobNotelineTab);

            jobNoteline.addCell(jobNotelineTab);
            document.add(jobNoteline);

//            //Customer Details
            String clientDetails = projInfo.getClientAddress()+"\n";

            //Author
            String dispAuthors = "";
            ProjAuthorInfo pai = new ProjAuthorInfo();
            pai.setPrjId(getProjIdParam);
            pai.collectAuthorInfo();
            for(int index=0; index<pai.getPrimaryAuthor().size(); index++) {
                if(dispAuthors.length()>50) {
                    dispAuthors = dispAuthors+"\n";
                }
                //dispAuthors = dispAuthors+pai.getContactFirstName().get(index).toString()+" "+pai.getContactSecondName().get(index).toString();
                dispAuthors = dispAuthors+pai.getContactSecondName().get(index).toString();
                if(index < pai.getPrimaryAuthor().size()-1) {
                    dispAuthors = dispAuthors +" / ";
                }
            }

            PdfPCell emptyCell = new PdfPCell();
            disableBorders(emptyCell);

            //next line
            document.add(new Paragraph("\n"));

            PdfPTable treansmittalTable = new PdfPTable(4); //Code 3
            treansmittalTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            treansmittalTable.setWidthPercentage(100);

            PdfPTable tTable = new PdfPTable(4);
            float[] tTableWidth = {12f, 20f, 8f, 15f};
            tTable.setWidths(tTableWidth);

            cell = new PdfPCell(new Paragraph("Mail Date:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(mailDate, normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            if(inBound) {
                cell = new PdfPCell(new Paragraph("To:", subFont));
                cellContentProperty(cell);
                tTable.addCell(cell);

                cell = new PdfPCell(new Paragraph(transmittalVO.getDispEmpName(), normalFont));
                cellDescProperty(cell);
                tTable.addCell(cell);
            } else {
                cell = new PdfPCell(new Paragraph("To:", subFont));
                cellContentProperty(cell);
                tTable.addCell(cell);

                cell = new PdfPCell(new Paragraph(dispContact, normalFont));
                cellDescProperty(cell);
                tTable.addCell(cell);
            }
            cell = new PdfPCell(new Paragraph("Address Type:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("", normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            if(inBound) {
                cell = new PdfPCell(new Paragraph("From:", subFont));
                cell.setRowspan(14);
                cellContentProperty(cell);
                tTable.addCell(cell);

                cell = new PdfPCell(new Paragraph(dispContact, normalFont));
                cell.setRowspan(14);
                cellDescProperty(cell);
                tTable.addCell(cell);
            } else {
                if(!dispAttention.equals("")) {
                    cell = new PdfPCell(new Paragraph("Attention:", subFont));
                } else {
                    cell = new PdfPCell(new Paragraph("", subFont));
                }
                cellContentProperty(cell);
                cell.setRowspan(14);
                tTable.addCell(cell);

                cell = new PdfPCell(new Paragraph(dispAttention, normalFont));
                cellDescProperty(cell);
                cell.setRowspan(14);
                tTable.addCell(cell);
            }

            cell = new PdfPCell(new Paragraph("Carrier:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(carrierType, normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Ship with Acct#:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(shipWithAcct, normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("", normalFont));     // Empty Cell For Separation
            disableBorders(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("", normalFont));     // Empty Cell For Separation
            disableBorders(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Job #:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projInfo.getProjId(), normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Author:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(authors, normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            ///
            cell = new PdfPCell(new Paragraph("Title:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projInfo.getTitle(), normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("10 digit ISBN:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projInfo.getISBN10(), normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("13 digit ISBN:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projInfo.getISBN13(), normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("", normalFont));     // Empty Cell For Separation
            disableBorders(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("", normalFont));     // Empty Cell For Separation
            disableBorders(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Billing Information", subFontUnderLine));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("", normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Option:", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(option, normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Reference :", subFont));
            cellContentProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(reference, normalFont));
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Enclosed are the following items:", blockHeaderFont));
            cellContentProperty(cell);
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tTable.addCell(cell);

            // Nested Table
            PdfPTable encloseTable = new PdfPTable(3);
            float[] encloseTableWidth = {40f, 5f, 40};
            encloseTable.setWidths(encloseTableWidth);

            // Union the Content Note & Package Content lists values
            contentTypeList = contentNote.split("###");
            packageContentList = packageContent.split("###");

            for(int idx=0; idx<contentTypeList.length && (!contentNote.equals("") && !packageContent.equals("")); idx++) {
                /*cell = new PdfPCell(new Paragraph(contentTypeList[idx], normalFont));
                cellContentProperty(cell);
                encloseTable.addCell(cell);
                cell = new PdfPCell(new Paragraph(" -> ", normalFont));
                cellDescProperty(cell);
                encloseTable.addCell(cell);*/

                cell = new PdfPCell(new Paragraph(packageContentList[idx], normalFont));
                cell.setColspan(4);
                cellContentProperty(cell);
                cellDescProperty(cell);
                encloseTable.addCell(cell);
            }
            // End of Nested Table

            cell = new PdfPCell(new Paragraph("", normalFont));
            // Enclose nested table into parent table's cell
            cell.addElement(encloseTable);
            cell.setColspan(4);
            cellDescProperty(cell);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Remarks / Instructions:", blockHeaderFont));
            cellContentProperty(cell);
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(remarks, normalFont));
            cell.setIndent(40);
            cell.setColspan(4);
            cellDescProperty(cell);
            tTable.addCell(cell);

            if(!inBound) {
                cell = new PdfPCell(new Paragraph("", normalFont));
                cell.setColspan(4);
                cell.setMinimumHeight(100);
                cellDescProperty(cell);
                tTable.addCell(cell);

                cell = new PdfPCell(new Paragraph("", normalFont));
                cell.setColspan(2);
                cellDescProperty(cell);
                tTable.addCell(cell);

                cell = new PdfPCell(new Paragraph("Thanks,\n\n"+dispEmp, normalFont));
                cell.setColspan(2);
                cell.setMinimumHeight(100);
                cellDescProperty(cell);
                tTable.addCell(cell);
            }
            document.add(tTable);

            //next line
            document.add(new Paragraph("\n"));

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
        cell.setMinimumHeight(3);
    }

    private static void cellContentProperty(PdfPCell cell) {
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }

    private static void cellDescProperty(PdfPCell cell) {
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
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
