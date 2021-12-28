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

/**
 *
 * @author Raghuramanm
 */
public class FeedbackRptPdfServlet extends HttpServlet {

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
    int count=0;
    NumberFormat numberFormat = new DecimalFormat("###.00");
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

            java.util.List issueID = new ArrayList();
            java.util.List msgID = new ArrayList();
            java.util.List msgType = new ArrayList();
            java.util.List msgBody = new ArrayList();
            java.util.List employee = new ArrayList();
            java.util.List employeeID = new ArrayList();
            java.util.List designation = new ArrayList();
            java.util.List dsgCode = new ArrayList();
            java.util.List department = new ArrayList();
            java.util.List dptCode = new ArrayList();
            java.util.List preventiveAction = new ArrayList();
            java.util.List preDate = new ArrayList();
            java.util.List correctiveAction = new ArrayList();
            java.util.List correDate = new ArrayList();
            java.util.List remarks = new ArrayList();
            java.util.List employeeId = new ArrayList();
            java.util.List postDate = new ArrayList();
            java.util.List feedback = new ArrayList();
            java.util.List issue = new ArrayList();
            java.util.List issueType = new ArrayList();
            java.util.List projectName = new ArrayList();
            java.util.List cause = new ArrayList();


            String emptyCheck = "";
            boolean lineItemDispChk=false;
            int lineItemDispCount = 0;

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
            calendar.add(Calendar.MINUTE, -30);
            date = calendar.getTime();
            //Instead of CURRENT_TIMESTAMP(), TimeStamp will used which has Dubuque time.
           // showTimeStamp = new Timestamp(date.getTime()).toString();
            showTimeStamp = format.format(date);
        }

        response.setContentType("application/pdf");
        
        String getDesigParam = request.getParameter("desig")!=null?request.getParameter("desig"):"";
        String getDeptParam = request.getParameter("dept")!=null?request.getParameter("dept"):"";
        String getEmpParam = request.getParameter("emp")!=null?request.getParameter("emp"):"";
        String getTypeParam = request.getParameter("type")!= null?request.getParameter("type"):"";
        String getFromParam = request.getParameter("from")!= null?request.getParameter("from"):"";
        String getToParam = request.getParameter("to")!= null?request.getParameter("to"):"";
        String getSesEmpParam =  request.getParameter("sesEmpID")!= null?request.getParameter("sesEmpID"):"";
        String getViewParam =  request.getParameter("view")!= null?request.getParameter("view"):"";

        String getTempDeptParam = request.getParameter("tempDept")!= null?request.getParameter("tempDept"):"";
        String getTempDesigParam = request.getParameter("tempDesig")!= null?request.getParameter("tempDesig"):"";
        String getTempEmpParam = request.getParameter("tempEmp")!= null?request.getParameter("tempEmp"):"";
        String getTempTypeParam = request.getParameter("tempType")!= null?request.getParameter("tempType"):"";
        
        lineItemDispCount = 0;

        boolean exp = false;
        String getVendorNumber = "";
        String fileName = "Feedback_Report.pdf";

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
            numberPara.add(new Paragraph("Feedback Report",headerFont));
            numberPara.setAlignment(Element.ALIGN_CENTER);
            numberPara.setAlignment(Element.ALIGN_CENTER);
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
            
            //Get the project properties
            pathfinder.functions.FeedbackReportDAO feedbackReportDAO = new pathfinder.functions.FeedbackReportDAO();
            pathfinder.functions.FeedbackReportVO feedbackReportVO = new pathfinder.functions.FeedbackReportVO();
            feedbackReportVO.setDesig(getDesigParam);
            feedbackReportVO.setDept(getDeptParam);
            feedbackReportVO.setEmp(getEmpParam);
            feedbackReportVO.setType(getTypeParam);
            feedbackReportVO.setFromDate(getFromParam);
            feedbackReportVO.setToDate(getToParam);
            feedbackReportVO.setSesEmpId(getSesEmpParam);
            feedbackReportVO.setView(getViewParam);

            feedbackReportVO = feedbackReportDAO.getFeedbackReport(feedbackReportVO);
            issueID = feedbackReportVO.getIssueID();
            postDate = feedbackReportVO.getFeedback();
            employeeID = feedbackReportVO.getEmployeeID();
            employee = feedbackReportVO.getEmployee();
            dsgCode = feedbackReportVO.getDsgCode();
            designation = feedbackReportVO.getDesignation();
            remarks = feedbackReportVO.getRemarks();
            correctiveAction = feedbackReportVO.getCorrectiveAction();
            correDate = feedbackReportVO.getCorreDate();
            preventiveAction = feedbackReportVO.getPreventiveAction();
            preDate = feedbackReportVO.getPreDate();
            department = feedbackReportVO.getDepartment();
            dptCode = feedbackReportVO.getDptCode();
            issueType = feedbackReportVO.getIssueType();
            issue = feedbackReportVO.getIssue();
            
            pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();

            //String billingLocation = projInfo.getBillLocationId();
            String billingAddress = "";

            //Create nested Table
            PdfPTable nestedTable;
            nestedTable = new PdfPTable(2);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);


            PdfPCell cell3 = new PdfPCell(new Paragraph("Department: ", subFont));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setMinimumHeight(20f);
            disableBorders(cell3);
            nestedTable.addCell(cell3);

            /*for(int i=0; i<issueID.size();i++){
                //System.out.println("Hey this is me");
                if(getDeptParam.equals(dptCode.get(i))){
                    getDeptParam = department.get(i).toString();
                }
            }*/
            PdfPCell cell4 = new PdfPCell(new Paragraph(getTempDeptParam, small));
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setMinimumHeight(20f);
            disableBorders(cell4);
            nestedTable.addCell(cell4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Designation :", subFont));
            disableBorders(cell5);
            cell5.setMinimumHeight(20f);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
            nestedTable.addCell(cell5);
            
            /*for(int i=0; i<issueID.size();i++){
                if(getDesigParam.equals(dsgCode.get(i))){
                    getDesigParam = designation.get(i).toString();
                }
            }*/
            PdfPCell cell6 = new PdfPCell(new Paragraph(getTempDesigParam, small));
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setMinimumHeight(20f);
            disableBorders(cell6);
            nestedTable.addCell(cell6);
            
            PdfPCell cell7 = new PdfPCell(new Paragraph("Employee:", subFont));
            disableBorders(cell7);
            cell7.setMinimumHeight(20f);
            cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
            nestedTable.addCell(cell7);

            /*for(int i=0; i<issueID.size();i++){
                if(getEmpParam.equals(employeeID.get(i))){
                    getEmpParam = employee.get(i).toString();
                }
            }*/
            PdfPCell cell8 = new PdfPCell(new Paragraph(getTempEmpParam, small));
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setMinimumHeight(20f);
            disableBorders(cell8);
            nestedTable.addCell(cell8);
            
            PdfPCell cell9 = new PdfPCell(new Paragraph("Feedback Type : ", subFont));
            disableBorders(cell9);
            cell9.setMinimumHeight(20f);
            cell9.setHorizontalAlignment(Element.ALIGN_RIGHT);
            nestedTable.addCell(cell9);

            /*for(int i=0; i<issueID.size();i++){
                if(getTypeParam.equals(issueType.get(i))){
                    getTypeParam = issue.get(i).toString();
                }
            }*/
            PdfPCell cell10 = new PdfPCell(new Paragraph(getTempTypeParam, small));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setMinimumHeight(20f);
            disableBorders(cell10);
            nestedTable.addCell(cell10);
            
            //Buyer address
            /*if(billingLocation.equals("2")) {
                billingAddress = "2436 Meinen Court" + "\n" + "Dubuque, IA 52002" + "\n" + "Telephone: (563) 557-1500" + "\n" + "Fax: (563) 557-1376";
            } else if(billingLocation.equals("1")){
                billingAddress = "Prakash Towers, 1st Floor" + "\n" + "No.141, Rajiv Gandhi Salai (OMR)" + "\n" + "Kottivakkam Chennai - 600041, India" + "\n" + "Telephone: (044) 24545411 / 24545412";
            } else {
                billingAddress = "";
            }*/

            Paragraph buyer = new Paragraph(billingAddress, small);
            PdfPCell buyerAdd = new PdfPCell(buyer);
            buyerAdd.setIndent(50);
            buyerAdd.setHorizontalAlignment(Element.ALIGN_LEFT);
            buyerAdd.setMinimumHeight(20f);
            disableBorders(buyerAdd);

            cell2.addElement(nestedTable);
            table.addCell(cell1);
            table.addCell(cell2);

            //Create new nested table
            nestedTable = new PdfPTable(2);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell nestedCell1 = new PdfPCell(nestedTable);
            disableBorders(nestedCell1);

            table.addCell(buyerAdd);
            disableBorders(buyerAdd);
            table.addCell(nestedCell1);

            document.add(table);

            //Add empty line
            //document.add(Chunk.NEWLINE);

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

            //next line
//            document.add(new Paragraph("\n"));

            if(issueID.isEmpty()){

                Paragraph EmptyPara = new Paragraph();

            //add estimation number
                EmptyPara.add(new Paragraph("No Data to Display ",redFont));
                EmptyPara.setAlignment(Element.ALIGN_CENTER);
                EmptyPara.setAlignment(Element.ALIGN_CENTER);
                EmptyPara.setIndentationRight(45);
                document.add(EmptyPara);

                //Add empty line
                //document.add(Chunk.NEWLINE);

                //document.add(new Paragraph("\n"));

            } else {

                Paragraph recordPara = new Paragraph();
            
                recordPara.add(new Paragraph("Total Records: "+issueID.size()+" ",smallBold));
                //recordPara.add(new Paragraph(issueID.size()));
                recordPara.setAlignment(Element.ALIGN_LEFT);
                recordPara.setAlignment(Element.ALIGN_LEFT);
                recordPara.setIndentationRight(45);
                document.add(recordPara);

                //Add empty line
                //document.add(Chunk.NEWLINE);

                //document.add(new Paragraph("\n"));

            float[] lineItemsWidths;

            //float[] lineItemsWidth1 = {7f, 7f, 25f, 10f, 10f, 10f, 10f, 10f};
            float[] lineItemsWidth1 = {12f, 20f, 15f, 25f, 15f, 12f, 15f, 12f};
            int width=0;
            float[] lineItemsWidth2 = {10f, 7f, 7f, 25f, 10f, 10f, 10f, 10f, 10f};

//            if(lineItemDispCount!=0)
//            {
//                lineItemDispChk=true;
//            }
            if(lineItemDispChk)
            {
            lineItemsWidths = lineItemsWidth2;
            width=9;
            }
            else
            {
            lineItemsWidths = lineItemsWidth1;
            width=8;
            }
            PdfPTable lineTable = new PdfPTable(lineItemsWidths); //Code 3
            lineTable.setWidthPercentage(100);
            lineTable.setHeaderRows(1);
            lineTable.setSplitRows(false);
            //lineTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            if(lineItemDispChk)
            {
            PdfPCell lineCell = new PdfPCell(new Paragraph("Line Item No.", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);
            }


            PdfPCell lineCell = new PdfPCell(new Paragraph("Assigned On", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Name", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Designation", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);


            lineCell = new PdfPCell(new Paragraph("Cause/Remarks", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Corrective Action", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Corrective Action Date", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Preventive Action", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Preventive Action Date", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineTable.getDefaultCell().enableBorderSide(PdfPCell.LEFT);
            lineTable.getDefaultCell().enableBorderSide(PdfPCell.RIGHT);

            lineTable.getDefaultCell().setBorderWidth(1f);
            lineTable.setWidths(lineItemsWidths);

            //Get the table data

            PdfPCell emptyCell = new PdfPCell();
            disableBorders(emptyCell);

            PdfPCell lastRow;
            PdfPCell Row;
            for (int loop = 0; loop < issueID.size(); loop++) {
                /**************************************************************************************/
                String assinedOnDateCell = postDate.get(loop).toString();
                PdfPCell assinedOnDateVal = new PdfPCell(new Paragraph(assinedOnDateCell,small));
                assinedOnDateVal.setHorizontalAlignment(Element.ALIGN_LEFT);
                assinedOnDateVal.setBorderWidth(1f);
                lineTable.addCell(assinedOnDateVal);

                String nameCell = employee.get(loop).toString();
                PdfPCell nameVal = new PdfPCell(new Paragraph(nameCell,small));
                nameVal.setHorizontalAlignment(Element.ALIGN_LEFT);
                nameVal.setBorderWidth(1f);
                lineTable.addCell(nameVal);

                String desigCell = designation.get(loop).toString();
                PdfPCell desigVal = new PdfPCell(new Paragraph(desigCell,small));
                desigVal.setHorizontalAlignment(Element.ALIGN_LEFT);
                desigVal.setBorderWidth(1f);
                lineTable.addCell(desigVal);

                String remarksCell = remarks.get(loop).toString();
                PdfPCell remarksVal = new PdfPCell(new Paragraph(remarksCell,small));
                remarksVal.setHorizontalAlignment(Element.ALIGN_LEFT);
                remarksVal.setBorderWidth(1f);
                lineTable.addCell(remarksVal);

                String correctiveActionCell = correctiveAction.get(loop).toString();
                PdfPCell correctiveActionVal = new PdfPCell(new Paragraph(correctiveActionCell,small));
                correctiveActionVal.setHorizontalAlignment(Element.ALIGN_LEFT);
                correctiveActionVal.setBorderWidth(1f);
                lineTable.addCell(correctiveActionVal);

                String correDateCell = correDate.get(loop).toString();
                PdfPCell correDateVal = new PdfPCell(new Paragraph(correDateCell,small));
                correDateVal.setHorizontalAlignment(Element.ALIGN_LEFT);
                correDateVal.setBorderWidth(1f);
                lineTable.addCell(correDateVal);

                String preventiveActionCell = preventiveAction.get(loop).toString();
                PdfPCell preventiveActionVal = new PdfPCell(new Paragraph(preventiveActionCell,small));
                preventiveActionVal.setHorizontalAlignment(Element.ALIGN_LEFT);
                preventiveActionVal.setBorderWidth(1f);
                lineTable.addCell(preventiveActionVal);

                String preDateCell = preDate.get(loop).toString();
                PdfPCell preDateVal = new PdfPCell(new Paragraph(preDateCell,small));
                preDateVal.setHorizontalAlignment(Element.ALIGN_LEFT);
                preDateVal.setBorderWidth(1f);
                lineTable.addCell(preDateVal);
 /**************************************************************************************/
                
            }
            if(lineItemDispChk)
                    {
                lineTable.addCell(emptyCell);
                    }

            lineTable.addCell(emptyCell);

            lineTable.addCell(emptyCell);

            lineTable.addCell(emptyCell);

            lineTable.addCell(emptyCell);

            lineTable.addCell(emptyCell);

            lineTable.addCell(emptyCell);

            lineTable.setSpacingBefore(20);
            document.add(lineTable);

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
}