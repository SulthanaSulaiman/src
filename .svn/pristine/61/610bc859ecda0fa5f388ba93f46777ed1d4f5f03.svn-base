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
public class JobInfoSheetPdfServlet extends HttpServlet {

    private static Font mainHeaderFont = new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD);
    private static Font subHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
    private static Font descHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    private static Font blockHeaderFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font normalFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
    private static Font subFontUnderLine = new Font(Font.FontFamily.HELVETICA, 9, Font.UNDERLINE | Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 9);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 8);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.RED);
    private static Font blueBoldFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD , BaseColor.BLUE);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL , BaseColor.BLUE);
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
        //String getShipId = request.getParameter("shippingId") != null ? request.getParameter("shippingId") : "";

        boolean exp = false;
        String fileName = project_name + "_Launch-MISC_Notes.pdf";
        fileName = fileName.replaceAll(" ", "-");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=\"" + fileName + "\"");

        response.setContentType("application/pdf");


        try {

            //System.out.println("inside generatePDf");

            Document document = new Document();

            pathfinder.functions.projects.JobInfoSheetDAO jobInfoSheetDAO = new pathfinder.functions.projects.JobInfoSheetDAO();
            pathfinder.functions.projects.JobInfoSheetVO jobInfoSheetVO = new pathfinder.functions.projects.JobInfoSheetVO();
            pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
            

            String jobInfoId = "";
            String projId = "";
            String platform = "";
            String linesPerPage = "";
            String columnsPerPage = "";
            String typeFace = "";
            String sTypeFace = "";
            String pointSize = "";
            String typeOfDesign = "";
            String slAllowed = "";
            String partStart = "";
            String sectionStart = "";
            String chapterStart = "";
            String depthAlignment = "";
            String rhStyle = "";
            String blankAllowed = "";
            String namingConv = "";
            String coFolio = "";
            String designApprovedAt = "";
            String estHours = "";
            String empId = "";
            String noteContent = "";

            String projName = "";
            String projTitle = "";
            String edition = "";
            String client = "";
            String division = "";
            String planner = "";
            String hybridPlanner = "";
            String projCategory = "";
            String discipline = "";
            String projPriority = "";
            String salesPerson = "";
            String projStatus = "";
            String copyRightYear = "";
            String noOfChapters = "";
            String mssPages = "";
            String estimatedPages = "";
            String isbn10 = "";
            String isbn13 = "";
            String projLevel = "";
            String projectedPrinterDate = "";
            String mssFormat = "";
            String fstPresent = "";
            String xmlProp = "";
            String trimSize = "";
            String color = "";

            String disableEdit = "";
            String disableSelectEdit = "";
            
            // Get Current Employee
            String sesEmpId = session.getAttribute("theEid").toString();

            ArrayList jisGroupId = new ArrayList();
            ArrayList jisGroupName = new ArrayList();

            ArrayList jisNoteIdList = new ArrayList();
            ArrayList jisNoteEmpIdList = new ArrayList();
            ArrayList jisNoteEmpNameList = new ArrayList();
            ArrayList jisNoteEmpDesigList = new ArrayList();
            ArrayList jisNoteGroupIdList = new ArrayList();
            ArrayList jisNoteGroupNameList = new ArrayList();
            ArrayList jisNoteCntList = new ArrayList();
            ArrayList jisNoteCreatedList = new ArrayList();

            // Get Group List
            jobInfoSheetVO = jobInfoSheetDAO.getJISGroupDetails();
            jisGroupId = (ArrayList) jobInfoSheetVO.getJisGroupIdList();
            jisGroupName = (ArrayList) jobInfoSheetVO.getJisGroupNameList();

            // Get Group Information List
            jobInfoSheetVO.setProjId(getProjIdParam);
            jobInfoSheetVO = jobInfoSheetDAO.getJISNotes(jobInfoSheetVO);
            jisNoteIdList = (ArrayList) jobInfoSheetVO.getJisNoteIdList();
            jisNoteEmpIdList = (ArrayList) jobInfoSheetVO.getJisNoteEmpIdList();
            jisNoteEmpNameList = (ArrayList) jobInfoSheetVO.getJisNoteEmpNameList();
            jisNoteEmpDesigList = (ArrayList) jobInfoSheetVO.getJisNoteEmpDesigList();
            jisNoteGroupIdList = (ArrayList) jobInfoSheetVO.getJisNoteGroupIdList();
            jisNoteGroupNameList = (ArrayList) jobInfoSheetVO.getJisNoteGroupNameList();
            jisNoteCntList = (ArrayList) jobInfoSheetVO.getJisNoteCntList();
            jisNoteCreatedList = (ArrayList) jobInfoSheetVO.getJisNoteCreatedList();

            if(!getProjIdParam.equals("")) {
                jobInfoSheetVO.setProjId(getProjIdParam);
                jobInfoSheetVO = jobInfoSheetDAO.getJISDetails(jobInfoSheetVO);
                jobInfoSheetVO = jobInfoSheetDAO.getProjectDetailsForJIS(jobInfoSheetVO);

                jobInfoId = jobInfoSheetVO.getJobInfoId();
                projId = jobInfoSheetVO.getProjId();
                platform = jobInfoSheetVO.getPlatform();
                linesPerPage = jobInfoSheetVO.getLinesPerPage();
                columnsPerPage = jobInfoSheetVO.getColumnsPerPage();
                typeFace = jobInfoSheetVO.getTypeface();
                sTypeFace = jobInfoSheetVO.getsTypeFace();
                pointSize = jobInfoSheetVO.getPointSize();
                typeOfDesign = jobInfoSheetVO.getTypeOfDesign();
                slAllowed = jobInfoSheetVO.getSlAllowed();
                partStart = jobInfoSheetVO.getPartStart();
                sectionStart = jobInfoSheetVO.getSectionStart();
                chapterStart = jobInfoSheetVO.getChapterStart();
                depthAlignment = jobInfoSheetVO.getDepthAlignment();
                rhStyle = jobInfoSheetVO.getRhStyle();
                blankAllowed = jobInfoSheetVO.getBlankAllowed();
                namingConv = jobInfoSheetVO.getNamingConv();
                coFolio = jobInfoSheetVO.getCoFolio();
                designApprovedAt = jobInfoSheetVO.getDesignApprovedAt();
                estHours = jobInfoSheetVO.getEstHours();
                empId = jobInfoSheetVO.getEmpId();
                noteContent = jobInfoSheetVO.getNoteContent();

                projName = jobInfoSheetVO.getProjName();
                projTitle = jobInfoSheetVO.getProjTitle();
                edition = jobInfoSheetVO.getEdition();
                client = jobInfoSheetVO.getClient();
                division = jobInfoSheetVO.getDivision();
                planner = jobInfoSheetVO.getPlanner();
                hybridPlanner = jobInfoSheetVO.getHybridPlanner();
                projCategory = jobInfoSheetVO.getProjCategory();
                discipline = jobInfoSheetVO.getDiscipline();
                projPriority = jobInfoSheetVO.getProjPriority();
                salesPerson = jobInfoSheetVO.getSalesPerson();
                projStatus = jobInfoSheetVO.getProjStatus();
                copyRightYear = jobInfoSheetVO.getCopyRightYear();
                noOfChapters = jobInfoSheetVO.getNoOfChapters();
                mssPages = jobInfoSheetVO.getMssPages();
                estimatedPages = jobInfoSheetVO.getEstimatedPages();
                isbn10 = jobInfoSheetVO.getIsbn10();
                isbn13 = jobInfoSheetVO.getIsbn13();
                projLevel = jobInfoSheetVO.getProjLevel();
                projectedPrinterDate = jobInfoSheetVO.getProjectedPrinterDate();
                mssFormat = jobInfoSheetVO.getMssFormat();
                fstPresent = jobInfoSheetVO.getFstPresent();
                xmlProp = jobInfoSheetVO.getXmlProp();
                trimSize = jobInfoSheetVO.getTrimSize();
                color = jobInfoSheetVO.getColor();

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
            image.scaleAbsoluteHeight(30);
            image.scaleAbsoluteWidth(120);

            //Create a table.
            float[] colsWidth = {3f, 10f}; // Code 1
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

            String header = projName+" : "+projTitle;
            header += !edition.equals("") ? ", "+edition+"e" : "";
            PdfPCell cell = new PdfPCell(new Paragraph(header, subHeaderFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(4);
            cell.setMinimumHeight(20f);
            disableBorders(cell);
            nestedTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Launch-MISC Notes", descHeaderFont));
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

            // Master Content Table
            PdfPTable masterTable = new PdfPTable(2);
            float[] masterTableWidth = {25f, 25f};
            masterTable.setWidths(masterTableWidth);

            // Left Content
            PdfPTable leftTable = new PdfPTable(2);
            float[] leftTableWidth = {16f, 20f};
            leftTable.setWidths(leftTableWidth);

            cell = new PdfPCell(new Paragraph("Project Information", descHeaderFont));
            cell.setColspan(2);
            cellHeaderProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Customer:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(splChar.decoding(client), normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Division:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(splChar.decoding(division), normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Customer contact:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("", normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Planner:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(planner, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Hybrid PM:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(hybridPlanner, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Category:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projCategory, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Discipline:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(discipline, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Priority:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projPriority, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Sales person:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(splChar.decoding(salesPerson), normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Status:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projStatus, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Copyright year:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(copyRightYear, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total no. of chapters:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(noOfChapters, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Manuscript pages:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(mssPages, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Estimate pages:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(estimatedPages, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("ISBN-10 :", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(isbn10, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("ISBN-13:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(isbn13, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Complex level:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projLevel, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Project printer date:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projectedPrinterDate, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("MSS format:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(mssFormat, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("FSTs present:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(fstPresent.equals(1)? "Yes" : "No", normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("XML/Non-XML:", subFont));
            cellContentProperty(cell);
            leftTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(xmlProp, normalFont));
            cellDescProperty(cell);
            leftTable.addCell(cell);

            // Right Content
            PdfPTable rightTable = new PdfPTable(2);
            float[] rightTableWidth = {16f, 20f};
            rightTable.setWidths(rightTableWidth);

            cell = new PdfPCell(new Paragraph("Design Information", descHeaderFont));
            cell.setColspan(2);
            cellHeaderProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Trim:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(trimSize, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Color:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(color, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("No. of lines per page:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(linesPerPage, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("No. of columns:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(columnsPerPage, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Type face:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(typeFace, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Secondary type face:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(sTypeFace, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Point size:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(pointSize, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Type of design:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(typeOfDesign, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            ///
            cell = new PdfPCell(new Paragraph("Short/Long allowed:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(slAllowed, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Part starts on:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(partStart, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Section starts on:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(sectionStart, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Chapter starts on:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(chapterStart, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Depth alignment:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(depthAlignment, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("RH style:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(rhStyle, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Blank allowed:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(blankAllowed, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("File naming convention:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(namingConv, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("CO folio:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(coFolio, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Design sample approved date:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(designApprovedAt, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Estimated hours:", subFont));
            cellContentProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(estHours, normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("", normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("", normalFont));
            cellDescProperty(cell);
            rightTable.addCell(cell);

            masterTable.addCell(leftTable);
            masterTable.addCell(rightTable);
            document.add(masterTable);

            //next line
            document.newPage();

            // Create Note Master table.
            float[] noteMasterWidth = {20f};
            PdfPTable noteMasterTable = new PdfPTable(noteMasterWidth);

            noteMasterTable.setWidthPercentage(100);
            noteMasterTable.getDefaultCell().setPadding(10);
            noteMasterTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            noteMasterTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            int count = 0;
            for (int idx = 0; idx < jisGroupId.size(); idx++) {
                cell = new PdfPCell(new Paragraph(jisGroupName.get(idx).toString()+" - Notes/Instructions", blueBoldFont));
                cellHeaderProperty(cell);
                noteMasterTable.addCell(cell);
                count = 0;
                for (int i = 0; i < jisNoteIdList.size(); i++) {
                    if (jisNoteGroupIdList.get(i).toString().equals(jisGroupId.get(idx).toString())) {
                        // Create Note Nested table.
                        float[] noteNestedWidth = {6f, 13f, 6f, 13f};
                        PdfPTable noteNestedtable = new PdfPTable(noteNestedWidth);
                        noteNestedtable.setWidthPercentage(100);
                        noteNestedtable.getDefaultCell().setPadding(10);
                        noteNestedtable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                        noteNestedtable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

                        String getNoteId = jisNoteIdList.get(i).toString();
                        String getGrpId = jisNoteGroupIdList.get(i).toString();
                        String getEmpId = jisNoteEmpIdList.get(i).toString();

                        cell = new PdfPCell(new Paragraph("Posted by :", normalFont));
                        cellContentProperty(cell);
                        noteNestedtable.addCell(cell);

                        cell = new PdfPCell(new Paragraph(jisNoteEmpNameList.get(i).toString(), subFont));
                        cellDescProperty(cell);
                        noteNestedtable.addCell(cell);

                        cell = new PdfPCell(new Paragraph("Created date :", normalFont));
                        cellContentProperty(cell);
                        noteNestedtable.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(jisNoteCreatedList.get(i).toString(), subFont));
                        cellDescProperty(cell);
                        noteNestedtable.addCell(cell);

                        cell = new PdfPCell(new Paragraph("Content : ", normalFont));
                        cellContentProperty(cell);
                        noteNestedtable.addCell(cell);
                        cell = new PdfPCell(new Paragraph(jisNoteCntList.get(i).toString(), normalFont));
                        cell.setColspan(3);
                        cellDescProperty(cell);
                        noteNestedtable.addCell(cell);

                        cell = new PdfPCell(new Paragraph("", subFont));
                        cellNoteContentProperty(cell);
                        cell.addElement(noteNestedtable);

                        noteMasterTable.addCell(cell);
                        count++;
                    }
                }

                if(count == 0) {
                    cell = new PdfPCell(new Paragraph("No information to '"+jisGroupName.get(idx).toString()+"'", blueFont));
                    msgContentProperty(cell);
                    noteMasterTable.addCell(cell);
                }
                
                cell = new PdfPCell(new Paragraph("", subFont));
                cellDescProperty(cell);
                noteMasterTable.addCell(cell);

                cell = new PdfPCell(new Paragraph("", subFont));
                cellDescProperty(cell);
                noteMasterTable.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("", subFont));
                cellDescProperty(cell);
                noteMasterTable.addCell(cell);
            }

            document.add(noteMasterTable);

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
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }

    private static void msgContentProperty(PdfPCell cell) {
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
    }

    private static void cellNoteContentProperty(PdfPCell cell) {
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
    }

    private static void cellHeaderProperty(PdfPCell cell) {
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
    }

    private static void cellDescProperty(PdfPCell cell) {
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
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
