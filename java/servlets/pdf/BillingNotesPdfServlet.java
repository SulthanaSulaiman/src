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
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.contacts.ProjAuthorInfo;
import pathfinder.functions.revenue.EstimateVO;

/**
 *
 * @author thanuja
 */

public class BillingNotesPdfServlet extends HttpServlet {

    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD , BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 9);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 8);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
    private String previous_category;
    private String current_category;

    
    NumberFormat numberFormat = new DecimalFormat("###.00");
    DecimalFormat df = new DecimalFormat("0.##");



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        String showTimeStamp = "";
        String emp_fcy = session.getAttribute("empfacility").toString();

        java.util.Date date= new java.util.Date();
        SimpleDateFormat format =new SimpleDateFormat("dd-MMM-yyyy");
        if(emp_fcy == "1" || emp_fcy.equals("1")) {
            //Instead of CURRENT_TIMESTAMP(), TimeStamp will used which has Chennai time.
            //showTimeStamp = new Timestamp(date.getTime()).toString();
            showTimeStamp = format.format(date);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, -10);
            //calendar.add(Calendar.HOUR, -11);
            calendar.add(Calendar.MINUTE, -30);
            date = calendar.getTime();
            //Instead of CURRENT_TIMESTAMP(), TimeStamp will used which has Dubuque time.
           // showTimeStamp = new Timestamp(date.getTime()).toString();
            showTimeStamp = format.format(date);
        }

        response.setContentType("application/pdf");
        String getEstimationNumber = request.getParameter("estimationNumber") != null ? request.getParameter("estimationNumber") : "";
        String getEstimationDispNumber = request.getParameter("estimationDispNumber") != null ? request.getParameter("estimationDispNumber") : "";
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";

        //Variables for Billing Notes
        String getChargeable = request.getParameter("radCharge") != null ? request.getParameter("radCharge") : "NULL";
        String getNonChargeable = request.getParameter("radNonCharge") != null ? request.getParameter("radNonCharge") : "NULL";

        //Variables for Estiamte
        String getEstimationCategory = request.getParameter("categoryName") != null ? request.getParameter("categoryName") : "";
        String getEstimationLineItem = request.getParameter("estLineItemName") != null ? request.getParameter("estLineItemName") : "";
        String getEstimationQuantity = request.getParameter("quantity") != null ? request.getParameter("quantity") : "";
        String getEstimationActualQuantity = request.getParameter("actualQuantity") != null ? request.getParameter("actualQuantity") : "";

        boolean exp = false;
        String getVendorNumber = "";
        String fileName = project_name + "_BillingNotes.pdf";
        fileName = fileName.replaceAll(" ", "-");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);

        response.setContentType("application/pdf");


        try {

            //System.out.println("inside generatePDf");

            Document document = new Document();

            //PdfWriter.getInstance(document, file);
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Paragraph numberPara = new Paragraph();

            //add estimation number
            numberPara.add(new Paragraph("Billing Notes ", headerFont));
            numberPara.setAlignment(Element.ALIGN_LEFT);
            numberPara.setAlignment(Element.ALIGN_LEFT);
            numberPara.setIndentationRight(45);
            document.add(numberPara);

            //Add empty line
            document.add(Chunk.NEWLINE);

            //Get the Billing Notes List
            pathfinder.functions.projects.BillingNotesDAO det = new pathfinder.functions.projects.BillingNotesDAO();
            pathfinder.functions.projects.BillingNotesVO billingNotesVO = new pathfinder.functions.projects.BillingNotesVO();
            billingNotesVO.setProjId(getProjIdParam);
            det.getBillingNotesForProjectId(billingNotesVO);

            //Get the Estimate List
            pathfinder.functions.revenue.EstimateDAO estimateDet = new pathfinder.functions.revenue.EstimateDAO();
            pathfinder.functions.revenue.EstimateVO estimateVO = new pathfinder.functions.revenue.EstimateVO();
            estimateVO.setProjId(getProjIdParam);
            estimateDet.getEstimateList(estimateVO);

            //Get the project properties
            pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
            projInfo.setProjId(getProjIdParam);
            projInfo.collectProjectName();

            // Add Job, Author, Title, ISBN Details
            float[] tableWidth = {0.9f, 3.5f}; // Code 1
            PdfPTable table2 = new PdfPTable(tableWidth);


            table2.setWidthPercentage(100);

            //Attention (customer contact)
            PdfPCell jobDet = new PdfPCell(new Paragraph("Job #:", subFont));
            jobDet.setHorizontalAlignment(Element.ALIGN_LEFT);
            jobDet.setBackgroundColor(BaseColor.LIGHT_GRAY);
            disableBorders(jobDet);

            PdfPCell jobDetVal = new PdfPCell(new Paragraph(getProjIdParam, small));
            jobDetVal.setHorizontalAlignment(Element.ALIGN_LEFT);
            jobDetVal.setBackgroundColor(BaseColor.LIGHT_GRAY);
            disableBorders(jobDetVal);

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
            PdfPCell author = new PdfPCell(new Paragraph("Author:", subFont));
            author.setHorizontalAlignment(Element.ALIGN_LEFT);
            author.setBackgroundColor(BaseColor.LIGHT_GRAY);
            disableBorders(author);

            PdfPCell authorVal = new PdfPCell(new Paragraph(dispAuthors, small));
            authorVal.setBackgroundColor(BaseColor.LIGHT_GRAY);
            disableBorders(authorVal);

            PdfPCell title = new PdfPCell(new Paragraph("Title:", subFont));
            title.setHorizontalAlignment(Element.ALIGN_LEFT);
            title.setBackgroundColor(BaseColor.LIGHT_GRAY);
            disableBorders(title);

            PdfPCell titleVal = new PdfPCell(new Paragraph(projInfo.getTitle(), small));
            titleVal.setBackgroundColor(BaseColor.LIGHT_GRAY);
            disableBorders(titleVal);

            PdfPCell isbn = new PdfPCell(new Paragraph("ISBN -10/13:", subFont));
            isbn.setHorizontalAlignment(Element.ALIGN_LEFT);
            isbn.setBackgroundColor(BaseColor.LIGHT_GRAY);
            disableBorders(isbn);

            PdfPCell isbnVal = new PdfPCell(new Paragraph(projInfo.getISBN10() + " / " + projInfo.getISBN13(), small));
            isbnVal.setBackgroundColor(BaseColor.LIGHT_GRAY);
            disableBorders(isbnVal);

            PdfPCell emptyCell = new PdfPCell();
            disableBorders(emptyCell);

            table2.addCell(jobDet);
            table2.addCell(jobDetVal);
            table2.addCell(author);
            table2.addCell(authorVal);
            table2.addCell(title);
            table2.addCell(titleVal);
            table2.addCell(isbn);
            table2.addCell(isbnVal);

            document.add(table2);

            //next line
            document.add(new Paragraph("\n"));

            //Draw a line....table only with top border
            float[] tableWidths = {10f};
            PdfPTable line = new PdfPTable(tableWidths);
            line.setWidthPercentage(100);

            PdfPCell emptyCellTab = new PdfPCell();
            emptyCellTab.setBorderWidth(1f);
            emptyCellTab.disableBorderSide(PdfPCell.TOP);
            emptyCellTab.disableBorderSide(PdfPCell.BOTTOM);
            emptyCellTab.disableBorderSide(PdfPCell.LEFT);
            emptyCellTab.disableBorderSide(PdfPCell.RIGHT);

            line.addCell(emptyCellTab);
            document.add(line);



            //Add Billing Notes Headding
            Paragraph chargeSumm = new Paragraph("Chargeable", headerFont);
            chargeSumm.setIndentationLeft(5);
            document.add(chargeSumm);

            //next line
            document.add(new Paragraph("\n"));

            java.util.List billingNotesList = new ArrayList();
            java.util.List notesList = new ArrayList();
            java.util.List dateList = new ArrayList();
            java.util.List empNameList = new ArrayList();
            java.util.List projIdList = new ArrayList();
            java.util.List billingNotesIdList = new ArrayList();
            java.util.List chargableFlag = new ArrayList();
            java.util.List billingNotesId = new ArrayList();


            java.util.List estimateId = new ArrayList();
            java.util.List categoryName = new ArrayList();
            java.util.List estLineItemName = new ArrayList();
            java.util.List quantity = new ArrayList();
            java.util.List actualQuantity = new ArrayList();
            java.util.List estimateList = new ArrayList();

            billingNotesList.clear();
            notesList.clear();
            dateList.clear();
            empNameList.clear();
            projIdList.clear();
            billingNotesIdList.clear();
            chargableFlag.clear();
            billingNotesId.clear();

            estimateId.clear();
            categoryName.clear();
            estLineItemName.clear();
            quantity.clear();
            actualQuantity.clear();
            estimateList.clear();

            billingNotesVO.setProjId(getProjIdParam);
            billingNotesList = det.getBillingNotesForProjectId(billingNotesVO);


            estimateVO.setProjId(getProjIdParam);
            estimateList = estimateDet.getEstimateList(estimateVO);

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

            Iterator estimateListItr = estimateList.iterator();

            while (estimateListItr.hasNext()) {
                estimateVO =  (EstimateVO)estimateListItr.next();
                estimateId.add(estimateVO.getEstId());
                categoryName.add(estimateVO.getCategory());
                estLineItemName.add(estimateVO.getEstLineItems());
                quantity.add(estimateVO.getQuant());
                actualQuantity.add(estimateVO.getActualQuant());
            }


            float[] billingNotesWidths = {15f, 20f, 68f, 1f, 1f};
            PdfPTable billingTable = new PdfPTable(billingNotesWidths); //Code 3
            billingTable.setWidthPercentage(100);
            billingTable.setHeaderRows(1);
            billingTable.setSplitRows(false);
            billingTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);


            PdfPCell billingLineCell = new PdfPCell(new Paragraph("Date Added", subFont));
            billingLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            billingLineCell.disableBorderSide(PdfPCell.TOP);
            billingLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingLineCell.disableBorderSide(PdfPCell.LEFT);
            billingLineCell.setBorderWidth(1f);
            billingTable.addCell(billingLineCell);


            billingLineCell = new PdfPCell(new Paragraph("Employee Name", subFont));
            billingLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            billingLineCell.disableBorderSide(PdfPCell.TOP);
            billingLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingLineCell.disableBorderSide(PdfPCell.LEFT);
            billingLineCell.setBorderWidth(1f);
            billingTable.addCell(billingLineCell);


            billingLineCell = new PdfPCell(new Paragraph("Added Billing Notes", subFont));
            billingLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            billingLineCell.disableBorderSide(PdfPCell.TOP);
            billingLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingLineCell.disableBorderSide(PdfPCell.LEFT);
            billingLineCell.setBorderWidth(1f);
            billingTable.addCell(billingLineCell);


            billingLineCell = new PdfPCell(new Paragraph("", subFont));
            billingLineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billingLineCell.disableBorderSide(PdfPCell.TOP);
            billingLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingLineCell.disableBorderSide(PdfPCell.LEFT);
            billingLineCell.setBorderWidth(1f);
            billingTable.addCell(billingLineCell);

            billingLineCell = new PdfPCell(new Paragraph("", subFont));
            billingLineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billingLineCell.disableBorderSide(PdfPCell.TOP);
            billingLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingLineCell.disableBorderSide(PdfPCell.LEFT);
            billingLineCell.setBorderWidth(1f);
            billingTable.addCell(billingLineCell);

            billingTable.getDefaultCell().enableBorderSide(PdfPCell.LEFT);
            billingTable.getDefaultCell().enableBorderSide(PdfPCell.RIGHT);

            billingTable.getDefaultCell().setBorderWidth(0f);
            billingTable.setWidths(billingNotesWidths);

            for (int loop = 0; loop < notesList.size(); loop++) {

                if (notesList.size() > 0) {

                    current_category = notesList.get(loop).toString();

                    if (current_category.equals(previous_category)) {
                        //do not show the category
                    } else {
                        if (!current_category.equals("")) {
                            //show the category
                            billingLineCell = new PdfPCell(new Paragraph("", small));
                            billingLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingTable.addCell(billingLineCell);

                            billingLineCell = new PdfPCell(new Paragraph("", small));
                            billingLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingTable.addCell(billingLineCell);

                            billingLineCell = new PdfPCell(new Paragraph("", small));
                            billingLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingTable.addCell(billingLineCell);

                            billingLineCell = new PdfPCell(new Paragraph("", small));
                            billingLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingTable.addCell(billingLineCell);

                            billingLineCell = new PdfPCell(new Paragraph("", small));
                            billingLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingTable.addCell(billingLineCell);

                        }

                    }
                }
                if(chargableFlag.get(loop).toString().equals("1"))
                {
                int slno = loop + 1;
                billingLineCell = new PdfPCell(new Paragraph(dateList.get(loop).toString(), small));
                billingLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                billingLineCell.setPaddingRight(5f);
//                billingLineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != notesList.size())
//                billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                billingLineCell.setBorderWidth(0f);
                billingTable.addCell(billingLineCell);

                billingLineCell = new PdfPCell(new Paragraph(empNameList.get(loop).toString(), small));
                billingLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                billingLineCell.setPaddingLeft(10f);
//                billingLineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != notesList.size())
//                billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                billingLineCell.setBorderWidth(0f);
                billingTable.addCell(billingLineCell);

                billingLineCell = new PdfPCell(new Paragraph(notesList.get(loop).toString(), small));
                billingLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                billingLineCell.setPaddingLeft(10f);
                //lineCell.setPaddingTop(10f);
//                billingLineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != notesList.size())
//                billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                billingLineCell.setBorderWidth(0f);
                billingTable.addCell(billingLineCell);

                
                    billingLineCell = new PdfPCell(new Paragraph("", small));
                    billingLineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    billingLineCell.setPaddingLeft(10f);
//                    billingLineCell.disableBorderSide(PdfPCell.TOP);
                    if(loop+1 != notesList.size())
//                    billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                    billingLineCell.setBorderWidth(0f);
                    billingTable.addCell(billingLineCell);
              
                

                billingLineCell = new PdfPCell(new Paragraph("", small));
//                billingLineCell.setPaddingLeft(10f);
//                billingLineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != notesList.size())
//                billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                billingLineCell.setBorderWidth(0f);
                billingTable.addCell(billingLineCell);
                }

            }

            document.add(billingTable);

            //next line
            document.add(new Paragraph("\n"));

            Paragraph nonChargeSumm = new Paragraph("NonChargeable", headerFont);
            nonChargeSumm.setIndentationLeft(5);
            document.add(nonChargeSumm);

            //next line
            document.add(new Paragraph("\n"));

            float[] billingNotesNonCharge = {15f, 20f, 68f, 1f, 1f};
            PdfPTable billingNonChargeTable = new PdfPTable(billingNotesNonCharge); //Code 3
            billingNonChargeTable.setWidthPercentage(100);
            billingNonChargeTable.setHeaderRows(1);
            billingNonChargeTable.setSplitRows(false);
            billingNonChargeTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);


            PdfPCell billingNonChargeLineCell = new PdfPCell(new Paragraph("Date Added", subFont));
            billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.TOP);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.LEFT);
            billingNonChargeLineCell.setBorderWidth(1f);
            billingNonChargeTable.addCell(billingNonChargeLineCell);


            billingNonChargeLineCell = new PdfPCell(new Paragraph("Employee Name", subFont));
            billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.TOP);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.LEFT);
            billingNonChargeLineCell.setBorderWidth(1f);
            billingNonChargeTable.addCell(billingNonChargeLineCell);


            billingNonChargeLineCell = new PdfPCell(new Paragraph("Added Billing Notes", subFont));
            billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.TOP);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.LEFT);
            billingNonChargeLineCell.setBorderWidth(1f);
            billingNonChargeTable.addCell(billingNonChargeLineCell);


            billingNonChargeLineCell = new PdfPCell(new Paragraph("", subFont));
            billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.TOP);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.LEFT);
            billingNonChargeLineCell.setBorderWidth(1f);
            billingNonChargeTable.addCell(billingNonChargeLineCell);

            billingNonChargeLineCell = new PdfPCell(new Paragraph("", subFont));
            billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.TOP);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.RIGHT);
            billingNonChargeLineCell.disableBorderSide(PdfPCell.LEFT);
            billingNonChargeLineCell.setBorderWidth(1f);
            billingNonChargeTable.addCell(billingNonChargeLineCell);

            /*   lineCell = new PdfPCell(new Paragraph("Description", subFont));
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);*/

            billingNonChargeTable.getDefaultCell().enableBorderSide(PdfPCell.LEFT);
            billingNonChargeTable.getDefaultCell().enableBorderSide(PdfPCell.RIGHT);

            billingNonChargeTable.getDefaultCell().setBorderWidth(0f);
            billingNonChargeTable.setWidths(billingNotesNonCharge);

            for (int loop = 0; loop < notesList.size(); loop++) {

                if (notesList.size() > 0) {

                    current_category = notesList.get(loop).toString();

                    if (current_category.equals(previous_category)) {
                        //do not show the category
                    } else {
                        if (!current_category.equals("")) {
                            //show the category
                            billingNonChargeLineCell = new PdfPCell(new Paragraph("", small));
                            billingNonChargeLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingNonChargeTable.addCell(billingNonChargeLineCell);

                            billingNonChargeLineCell = new PdfPCell(new Paragraph("", small));
                            billingNonChargeLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingNonChargeTable.addCell(billingNonChargeLineCell);

                            billingNonChargeLineCell = new PdfPCell(new Paragraph("", small));
                            billingNonChargeLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingNonChargeTable.addCell(billingNonChargeLineCell);

                            billingNonChargeLineCell = new PdfPCell(new Paragraph("", small));
                            billingNonChargeLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingNonChargeTable.addCell(billingNonChargeLineCell);

                            billingNonChargeLineCell = new PdfPCell(new Paragraph("", small));
                            billingNonChargeLineCell.setBorderWidth(0f);
//                            billingLineCell.disableBorderSide(PdfPCell.TOP);
//                            billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                            billingNonChargeTable.addCell(billingNonChargeLineCell);

                        }

                    }
                }
                if(chargableFlag.get(loop).toString().equals("0"))
                {
                int slno = loop + 1;
                billingNonChargeLineCell = new PdfPCell(new Paragraph(dateList.get(loop).toString(), small));
                billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                billingNonChargeLineCell.setPaddingRight(5f);
//                billingLineCell.disableBorderSide(PdfPCell.TOP);
//                if(loop+1 != notesList.size())
//                billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                billingNonChargeLineCell.setBorderWidth(0f);
                billingNonChargeTable.addCell(billingNonChargeLineCell);

                billingNonChargeLineCell = new PdfPCell(new Paragraph(empNameList.get(loop).toString(), small));
                billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                billingNonChargeLineCell.setPaddingLeft(10f);
//                billingLineCell.disableBorderSide(PdfPCell.TOP);
//                if(loop+1 != notesList.size())
//                billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                billingNonChargeLineCell.setBorderWidth(0f);
                billingNonChargeTable.addCell(billingNonChargeLineCell);

                billingNonChargeLineCell = new PdfPCell(new Paragraph(notesList.get(loop).toString(), small));
                billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                billingNonChargeLineCell.setPaddingLeft(10f);
                //lineCell.setPaddingTop(10f);
//                billingLineCell.disableBorderSide(PdfPCell.TOP);
//                if(loop+1 != notesList.size())
//                billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                billingNonChargeLineCell.setBorderWidth(0f);
                billingNonChargeTable.addCell(billingNonChargeLineCell);


                billingNonChargeLineCell = new PdfPCell(new Paragraph("", small));
                billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    billingNonChargeLineCell.setPaddingLeft(10f);
//                    billingLineCell.disableBorderSide(PdfPCell.TOP);
//                    if(loop+1 != notesList.size())
//                    billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                billingNonChargeLineCell.setBorderWidth(0f);
                billingNonChargeTable.addCell(billingNonChargeLineCell);



                billingNonChargeLineCell = new PdfPCell(new Paragraph("", small));
                billingNonChargeLineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                billingNonChargeLineCell.setPaddingLeft(10f);
//                billingLineCell.disableBorderSide(PdfPCell.TOP);
//                if(loop+1 != notesList.size())
//                billingLineCell.disableBorderSide(PdfPCell.BOTTOM);
                billingNonChargeLineCell.setBorderWidth(0f);
                billingNonChargeTable.addCell(billingNonChargeLineCell);
                }

            }

            document.add(billingNonChargeTable);

            //next line
            document.add(new Paragraph("\n"));

            //Add new page
            document.newPage();

            float[] widths = {3f, 2f}; // Code 1

            PdfPTable lineItem = new PdfPTable(widths); //Code 3
            lineItem.setWidthPercentage(100);
            PdfPCell s4logo = new PdfPCell(new Paragraph("Billing Notes", headerFont));
            disableBorders(s4logo);
            PdfPCell poNum = new PdfPCell(new Paragraph(" ", headerFont));
            disableBorders(poNum);

            lineItem.addCell(s4logo);
            lineItem.addCell(poNum);

            document.add(lineItem);

            //next line
            document.add(new Paragraph("\n"));

            //Table for project properties
            float[] propColWidth = {0.4f, 2.7f, 0.7f, 1.5f}; // Code 1

            PdfPTable projProp = new PdfPTable(propColWidth);
            projProp.setWidthPercentage(100);
            projProp.addCell(jobDet);
            disableBorders(jobDet);
            projProp.addCell(jobDetVal);
            disableBorders(jobDetVal);
            projProp.addCell(author);
            disableBorders(author);
            projProp.addCell(authorVal);
            disableBorders(authorVal);
            projProp.addCell(title);
            disableBorders(title);
            projProp.addCell(titleVal);
            disableBorders(titleVal);
            projProp.addCell(isbn);
            disableBorders(isbn);
            projProp.addCell(isbnVal);
            disableBorders(isbnVal);

            document.add(projProp);



            //next line
            document.add(new Paragraph("\n"));

            //Add Billing Notes Headding
            Paragraph estSumm = new Paragraph("Estimate", headerFont);
            estSumm.setIndentationLeft(5);
            document.add(estSumm);

            //next line
            document.add(new Paragraph("\n"));

            //Declarations for computation
            Double rateTotal = 0.0;
            Double estTotal = 0.0;

            //Display estimation line items
            float[] lineItemsWidths = {5f, 25f, 40f, 15f, 15f};
            PdfPTable lineTable = new PdfPTable(lineItemsWidths); //Code 3
            lineTable.setWidthPercentage(100);
            lineTable.setHeaderRows(1);
            lineTable.setSplitRows(false);
//            lineTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);


            PdfPCell lineCell = new PdfPCell(new Paragraph("S.No", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Category", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Estimate Line Items", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Quantity", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Actual Quantity", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

          

            lineTable.getDefaultCell().enableBorderSide(PdfPCell.LEFT);
            lineTable.getDefaultCell().enableBorderSide(PdfPCell.RIGHT);

            lineTable.getDefaultCell().setBorderWidth(1f);
            lineTable.setWidths(lineItemsWidths);

            Double quantGrandTotal = 0.0;
            Double actQuantGrandTotal = 0.0;
            for (int loop = 0; loop < estLineItemName.size(); loop++) {

                if (estLineItemName.size() > 0) {

                    current_category = estLineItemName.get(loop).toString();

                    if (current_category.equals(previous_category)) {
                        //do not show the category
                    } else {
                        if (!current_category.equals("")) {
                            //show the category
                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            lineTable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            lineTable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            lineTable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            lineTable.addCell(lineCell);

                            lineCell = new PdfPCell(new Paragraph("", small));
                            lineCell.setBorderWidth(1f);
                            lineCell.disableBorderSide(PdfPCell.TOP);
                            lineCell.disableBorderSide(PdfPCell.BOTTOM);
                            lineTable.addCell(lineCell);

                        }

                    }
                }
                int slno = loop + 1;
                lineCell = new PdfPCell(new Paragraph(""+ slno, small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setPaddingRight(5f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != estLineItemName.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(categoryName.get(loop).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                lineCell.setPaddingLeft(10f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != estLineItemName.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(estLineItemName.get(loop).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                lineCell.setPaddingLeft(10f);
                //lineCell.setPaddingTop(10f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != estLineItemName.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(df.format(Double.parseDouble(quantity.get(loop).toString())), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lineCell.setPaddingRight(20f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != estLineItemName.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

//                quantGrandTotal += Double.parseDouble(quantity.get(loop).toString());
//                previous_category = current_category;
//
//                System.out.println("quantGrandTotal"+quantGrandTotal);

                String actQty = actualQuantity.get(loop).toString();
                if(!actQty.equals("")){
                    actQty = df.format(Double.parseDouble(actualQuantity.get(loop).toString()));
                }
                lineCell = new PdfPCell(new Paragraph(actQty, small));
                lineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lineCell.setPaddingRight(20f);
                lineCell.disableBorderSide(PdfPCell.TOP);
                if(loop+1 != estLineItemName.size())
                lineCell.disableBorderSide(PdfPCell.BOTTOM);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

//                actQuantGrandTotal += Double.parseDouble(actualQuantity.get(loop).toString());
//                previous_category = current_category;
//
//                System.out.println("actQuantGrandTotal"+actQuantGrandTotal);


            }

//            lineTable.addCell(emptyCell);
//            lineTable.addCell(emptyCell);
//
//            lineCell = new PdfPCell(new Paragraph("$ "+numberFormat.format(quantGrandTotal), subFont));
//            lineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            lineCell.setPaddingRight(20f);
//            lineCell.setBorderWidth(1f);
//            lineTable.addCell(lineCell);
//
////            lineTable.addCell(emptyCell);
////            lineTable.addCell(emptyCell);
//
//            lineCell = new PdfPCell(new Paragraph("$ "+numberFormat.format(actQuantGrandTotal), subFont));
//            lineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            lineCell.setPaddingRight(20f);
//            lineCell.setBorderWidth(1f);
//            lineTable.addCell(lineCell);

            document.add(lineTable);
            document.close();


            //System.out.println("Done");



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
}
