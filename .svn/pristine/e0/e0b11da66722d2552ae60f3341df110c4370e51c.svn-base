package servlets.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.projects.chapters.ProductionOnTimeVO;

/**
 *
 * @author Aravindhanj
 */
public class ProductionOnTimePdfServlet extends HttpServlet {

    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 14,
            Font.BOLD, BaseColor.WHITE);
    private static Font summaryFont = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD, BaseColor.BLACK);
    private static Font commentFont = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD | Font.ITALIC, BaseColor.GRAY);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 7);

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

    String fileName = "Production_On_Time.pdf";

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
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);
        response.setContentType("application/pdf");

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);
            writer.setPageEvent(new ProductionOnTimeVO());
            document.open();

            double totalCount = 0;
            int tempDiff = 0;
            int beforeTime = 0;
            int onTime = 0;
            int oneDayDelay = 0;
            int twoAndThreeDaysDelay = 0;
            int moreThanThreeDaysDelay = 0;
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String symbol = "%";
            
            String getCustomerId = request.getParameter("customerId") != null? request.getParameter("customerId") : "";
            String getPlannerId = request.getParameter("plannerId") != null? request.getParameter("plannerId") : "";
            String getHybridPlannerId = request.getParameter("hybridPlannerId") != null? request.getParameter("hybridPlannerId") : "";
            String getStartDate = request.getParameter("dueStartDate") != null? request.getParameter("dueStartDate") : "";
            String getEndDate = request.getParameter("dueEndDate") != null? request.getParameter("dueEndDate") : "";
            String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";

            pathfinder.functions.projects.chapters.ProductionOnTimeDAO productionOnTimeDAO = new pathfinder.functions.projects.chapters.ProductionOnTimeDAO();
            pathfinder.functions.projects.chapters.ProductionOnTimeVO productionOnTimeVO = new pathfinder.functions.projects.chapters.ProductionOnTimeVO();
            pathfinder.util.TimeDifference timeDifference = new pathfinder.util.TimeDifference();

            productionOnTimeVO.setCustomerId(getCustomerId);
            productionOnTimeVO.setPlannerId(getPlannerId);
            productionOnTimeVO.setHybridPlannerId(getHybridPlannerId);
            productionOnTimeVO.setDueStartDate(getStartDate);
            productionOnTimeVO.setDueEndDate(getEndDate);
            productionOnTimeVO.setProjectId(getProjIdParam);
            productionOnTimeVO = productionOnTimeDAO.getProductionOnTime(productionOnTimeVO);

            List chapterIdList = productionOnTimeVO.getChapterIdList();
            //List projectIdList = productionOnTimeVO.getProjectIdList();
            List projectNameList = productionOnTimeVO.getProjectNameList();
            List customerCompanyList = productionOnTimeVO.getCustomerCompanyList();
            List projPlannerList = productionOnTimeVO.getProjPlannerList();
            List projHybridPlannerList = productionOnTimeVO.getProjHybridPlannerList();
            List chapterStageList = productionOnTimeVO.getChapterStageList();
            List chapterNameList = productionOnTimeVO.getChapterNameList();
            List chapterDueDateList = productionOnTimeVO.getChapterDueDateList();
            List chapterActualDateList = productionOnTimeVO.getChapterActualDateList();
            //List dayDiffList = productionOnTimeVO.getDayDiffList();

            float[] headerTableWidth = {100f};

            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidths(headerTableWidth);
            headerTable.setWidthPercentage(100);
            headerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            PdfPCell headerCell = new PdfPCell(new Paragraph("Production On Time", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setBorder(Element.RECTANGLE);
            headerCell.setBackgroundColor(BaseColor.BLACK);
            headerTable.addCell(headerCell);
            document.add(headerTable);

            //next line
            document.add(new Paragraph("\n"));

            float[] detailsTableWidth = {10f, 12f, 8f, 8f, 8f, 10f, 9f, 9f, 10f};
            PdfPTable lineTable = new PdfPTable(9);
            lineTable.setWidths(detailsTableWidth);
            lineTable.setWidthPercentage(100);
            lineTable.setHeaderRows(1);
            lineTable.setSplitRows(false);
            lineTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            PdfPCell lineCell = new PdfPCell(new Paragraph("Project Name", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Customer", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Planner", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Hybrid Planner", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Stage", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Chapter", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Batch Mail/Ship/Kill Date", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Actual Mail/Ship/Kill Date", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            lineCell = new PdfPCell(new Paragraph("Number of Days Past Due", subFont));
            lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            lineCell.setBorderWidth(1f);
            lineTable.addCell(lineCell);

            for(int idx=0; idx<chapterIdList.size(); idx++) {
                try {
                    tempDiff = timeDifference.DaysWithoutWeekEnds(chapterDueDateList.get(idx).toString(), chapterActualDateList.get(idx).toString());
                    //tempDiff= Integer.parseInt(dayDiffList.get(idx).toString());
                } catch(Exception e) {
                    System.out.println("Exception on ProductionOnTimePdfServlet : "+e);
                    tempDiff= 0;
                }
                if(tempDiff < 0) {
                    beforeTime++;
                } else if(tempDiff == 0) {
                    onTime++;
                } else if(tempDiff == 1) {
                    oneDayDelay++;
                } else if(tempDiff == 2 || tempDiff == 3) {
                    twoAndThreeDaysDelay++;
                } else {
                    moreThanThreeDaysDelay++;
                }
                totalCount++;
                if(tempDiff <= 0) {
                    continue;
                }
                lineCell = new PdfPCell(new Paragraph(projectNameList.get(idx).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(customerCompanyList.get(idx).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(projPlannerList.get(idx).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(projHybridPlannerList.get(idx).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(chapterStageList.get(idx).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(chapterNameList.get(idx).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(chapterDueDateList.get(idx).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(chapterActualDateList.get(idx).toString(), small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);

                lineCell = new PdfPCell(new Paragraph(""+tempDiff, small));
                lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lineCell.setBorderWidth(1f);
                lineTable.addCell(lineCell);
            }

            //next line
            document.add(new Paragraph("\n"));
            
            float[] lineItemsWidth2 = {10f, 12f, 8f, 8f, 8f, 10f, 9f, 9f, 9f, 9f, 9f};

            PdfPTable summaryTable = new PdfPTable(11);
            summaryTable.setWidths(lineItemsWidth2);
            summaryTable.setWidthPercentage(100);
            summaryTable.setHeaderRows(1);
            summaryTable.setSplitRows(false);
            summaryTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            PdfPCell summaryCell = new PdfPCell(new Paragraph("Total # of units mailed", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("Total # of units mailed before time", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("% of units mailed before time", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("Total # of units mailed on time", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("% of units mailed on time", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("Total # of units mailed within 1 day", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("% of units mailed within 1 day", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("Total # of units mailed within 2-3 days", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("% of units mailed within 2-3 days", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("# of units mailed greater than 3 days", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("% of units mailed greater than 3 days", subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(""+totalCount, subFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(""+beforeTime, summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(totalCount!=0 ? decimalFormat.format((beforeTime*100)/totalCount) + symbol: "0%" , summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(""+onTime, summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(totalCount!=0 ? decimalFormat.format((onTime*100)/totalCount) + symbol: "0%" , summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(""+oneDayDelay, summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(totalCount!=0 ? decimalFormat.format((oneDayDelay*100)/totalCount) + symbol: "0%" , summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(""+twoAndThreeDaysDelay, summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(totalCount!=0 ? decimalFormat.format((twoAndThreeDaysDelay*100)/totalCount) + symbol: "0%" , summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(""+moreThanThreeDaysDelay, summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph(totalCount!=0 ? decimalFormat.format((moreThanThreeDaysDelay*100)/totalCount) + symbol: "0%" , summaryFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryTable.addCell(summaryCell);

            summaryCell = new PdfPCell(new Paragraph("" , commentFont));
            disableBorders(summaryCell);
            summaryCell.setColspan(5);
            summaryTable.addCell(summaryCell);

            int dispDelayCount = oneDayDelay+twoAndThreeDaysDelay+moreThanThreeDaysDelay;
            summaryCell = new PdfPCell(new Paragraph("Total # of Delayed chapters: " + dispDelayCount , commentFont));
            summaryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            summaryCell.setBorderWidth(1f);
            summaryCell.setColspan(6);
            summaryTable.addCell(summaryCell);

            document.add(summaryTable);
            
            //Add empty line
            document.add(Chunk.NEWLINE);

            document.add(lineTable);
            document.close();
        } catch (DocumentException e) {
            System.out.println("DocumentException : " + e);
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }

    private static void disableBorders(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
        cell.setMinimumHeight(3);
    }
}
