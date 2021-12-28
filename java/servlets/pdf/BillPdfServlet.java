/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thanuja
 */
public class BillPdfServlet extends HttpServlet {

    private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD, BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD);
    private static Font small = new Font(Font.FontFamily.TIMES_ROMAN, 10);
    private String previous_category;
    private String current_category;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        javax.servlet.http.HttpSession session = request.getSession();
        String getBillNumber = request.getParameter("billNumber") != null ? request.getParameter("billNumber") : "";
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        //System.out.println("getBillNumber:" + getBillNumber);
        boolean exp = false;
        String fileName = project_name + "_Bill.pdf";
        fileName = fileName.replaceAll(" ", "-");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);

        response.setContentType("application/pdf");



        try {



            Document document = new Document();


            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Paragraph numberPara = new Paragraph();

            //add Bill number
            numberPara.add(new Paragraph("Bill Number: " + getBillNumber, headerFont));
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

            pathfinder.functions.revenue.BillingSummaryInfo summary = new pathfinder.functions.revenue.BillingSummaryInfo();
            summary.setBillNumber(getBillNumber);
            summary.getBillSummary();
            java.util.List poDate = summary.getBillDate();

            //Get the project properties
            pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
            projInfo.setProjId(getProjIdParam);
            projInfo.collectProjectName();


            //Create nested Table
            PdfPTable nestedTable;
            nestedTable = new PdfPTable(2);
            nestedTable.setWidthPercentage(100);
            nestedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);



            PdfPCell cell3 = new PdfPCell(new Paragraph("Date Of Bill: ", subFont));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setMinimumHeight(20f);
            disableBorders(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph(poDate.get(0).toString(), small));
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setMinimumHeight(20f);
            disableBorders(cell4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Customer # :", subFont));
            disableBorders(cell5);
            cell5.setMinimumHeight(20f);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cell6 = new PdfPCell(new Paragraph(projInfo.getClient(), small));
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setMinimumHeight(20f);
            disableBorders(cell6);

            PdfPCell cell7 = new PdfPCell(new Paragraph("Calisle Job # :", subFont));
            cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell7.setMinimumHeight(20f);
            disableBorders(cell7);

            PdfPCell cell8 = new PdfPCell(new Paragraph(project_name, small));
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setMinimumHeight(20f);
            disableBorders(cell8);

            nestedTable.addCell(cell3);
            nestedTable.addCell(cell4);
            nestedTable.addCell(cell5);
            nestedTable.addCell(cell6);
            nestedTable.addCell(cell7);
            nestedTable.addCell(cell8);

            cell2.addElement(nestedTable);

            table.addCell(cell1);
            table.addCell(cell2);
            document.add(table);

            //Add empty line
            document.add(Chunk.NEWLINE);

            float[] tableWidth = {0.9f, 3.5f, 3f}; // Code 1
            PdfPTable table2 = new PdfPTable(tableWidth);

            table2.setWidthPercentage(100);

            //Author
            PdfPCell author = new PdfPCell(new Paragraph("Author:", subFont));
            author.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(author);

            PdfPCell authorVal = new PdfPCell(new Paragraph(projInfo.getAuthor(), small));
            disableBorders(authorVal);

            PdfPCell copyright = new PdfPCell(new Paragraph("Copyright:", subFont));
            copyright.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(copyright);

            PdfPCell copyrightVal = new PdfPCell(new Paragraph(projInfo.getCopyRight(), small));
            disableBorders(copyrightVal);

            PdfPCell title = new PdfPCell(new Paragraph("Title:", subFont));
            title.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(title);

            PdfPCell titleVal = new PdfPCell(new Paragraph(projInfo.getTitle(), small));
            disableBorders(titleVal);



            PdfPCell isbn = new PdfPCell(new Paragraph("ISBN -10/13:", subFont));
            isbn.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(isbn);

            PdfPCell isbnVal = new PdfPCell(new Paragraph(projInfo.getISBN10() + " " + projInfo.getISBN13(), small));
            disableBorders(isbnVal);

            PdfPCell emptyCell = new PdfPCell();
            disableBorders(emptyCell);

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


            //Add Bill summary
            Paragraph summ = new Paragraph("Bill Summary", headerFont);
            summ.setIndentationLeft(25);


            document.add(summ);

//next line
            document.add(new Paragraph("\n"));

            PdfPTable summTable = new PdfPTable(2); //Code 3
            summTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            summTable.setWidthPercentage(100);
            summTable.getDefaultCell().setBorderWidth(1f);

            PdfPCell cell = new PdfPCell(new Paragraph("Bill Value", subFont));
            cell.setBorderWidth(1f);
            summTable.addCell(cell);
            summTable.addCell(new Paragraph("Status", subFont));


            //Get the table data
            summTable.addCell(new Paragraph(summary.getBillValue().get(0).toString(), smallBold));
            summTable.addCell(new Paragraph(summary.getBillStatus().get(0).toString(), smallBold));


            document.add(summTable);

            //Add new page
            document.newPage();

            float[] widths = {3f, 2f}; // Code 1

            PdfPTable lineItem = new PdfPTable(widths); //Code 3
            //lineItem.setHorizontalAlignment(Element.ALIGN_LEFT);
            lineItem.setWidthPercentage(100);
            PdfPCell s4logo = new PdfPCell(new Paragraph("S4Carlisle Publishing Services", headerFont));
            disableBorders(s4logo);
            PdfPCell poNum = new PdfPCell(new Paragraph("Bill Number: " + getBillNumber, headerFont));
            disableBorders(poNum);

            lineItem.addCell(s4logo);
            lineItem.addCell(poNum);

            document.add(lineItem);


            //Table for project properties
            float[] propColWidth = {0.4f, 2.7f, 0.7f, 1.5f}; // Code 1

            PdfPTable projProp = new PdfPTable(propColWidth);
            projProp.setWidthPercentage(100);
            projProp.addCell(author);
            disableBorders(author);
            projProp.addCell(authorVal);
            disableBorders(authorVal);
            projProp.addCell(copyright);
            disableBorders(copyright);
            projProp.addCell(copyrightVal);
            disableBorders(copyrightVal);
            projProp.addCell(title);
            disableBorders(title);
            projProp.addCell(titleVal);
            disableBorders(titleVal);
            projProp.addCell(isbn);
            disableBorders(isbn);
            projProp.addCell(isbnVal);
            disableBorders(isbnVal);

            document.add(projProp);

            //Display Bill line items
            float[] lineItemsWidths = {25f, 25f, 20f, 15f, 15f};
            PdfPTable lineTable = new PdfPTable(5); //Code 3
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



            java.util.List item_id = new ArrayList();
            java.util.List item_name = new ArrayList();
            java.util.List item_rate = new ArrayList();
            java.util.List item_quantity = new ArrayList();
            java.util.List item_desc = new ArrayList();
            java.util.List item_value = new ArrayList();
            java.util.List cost_category_id = new ArrayList();
            java.util.List cost_category_name = new ArrayList();


            //Get the table data



            //System.out.println("inside after");
            item_id.clear();
            item_name.clear();
            item_rate.clear();
            item_quantity.clear();
            item_desc.clear();
            item_value.clear();
            cost_category_id.clear();
            cost_category_name.clear();


            pathfinder.functions.revenue.BillingLineItem det = new pathfinder.functions.revenue.BillingLineItem();
            det.setBillNumber(getBillNumber);
            det.collectBillLineItem();




            item_id = det.getLineItemId();
            item_name = det.getLineItemName();
            item_rate = det.getLineItemRate();
            item_quantity = det.getLineItemQuantity();
            item_desc = det.getLineItemDesc();
            item_value = det.getLineItemTotal();
            cost_category_id = det.getCategoryId();
            cost_category_name = det.getCategoryName();
            //System.out.println("item size:" + item_id.size());

            previous_category = "";
            current_category = "";
            PdfPCell lastRow;

            for (int loop = 0; loop < item_id.size(); loop++) {


                //Display cost category
                if (cost_category_id.size() > 0) {

                    current_category = cost_category_id.get(loop).toString();

                    if (current_category.equals(previous_category)) {
                        //do not show the category
                    } else {
                        if (!current_category.equals("")) {
                            //show the category
                            lineTable.addCell(new Paragraph(cost_category_name.get(loop).toString(), blueFont));
                            lineTable.addCell(new Paragraph(" "));
                            lineTable.addCell(new Paragraph(" "));
                            lineTable.addCell(new Paragraph(" "));
                            lineTable.addCell(new Paragraph(" "));
                        }

                    }
                }

                lineTable.getDefaultCell().setColspan(1);

                //System.out.println("loop:" + loop);
                //System.out.println("size:" + item_id.size());
                if (loop + 1 == item_id.size()) {
                    //System.out.println("ifnew");
                    lastRow = new PdfPCell(new Paragraph("  " + item_name.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);

                    lastRow = new PdfPCell(new Paragraph(item_rate.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);


                    lastRow = new PdfPCell(new Paragraph(item_quantity.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);

                    lastRow = new PdfPCell(new Paragraph(item_value.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);

                    lastRow = new PdfPCell(new Paragraph(item_desc.get(loop).toString(), small));
                    lastRow.setBorderWidth(1f);
                    lastRow.disableBorderSide(PdfPCell.TOP);
                    lineTable.addCell(lastRow);


                } else {

                    lineTable.addCell(new Paragraph("  " + item_name.get(loop).toString(), small));
                    lineTable.addCell(new Paragraph(item_rate.get(loop).toString(), small));
                    lineTable.addCell(new Paragraph(item_quantity.get(loop).toString(), small));
                    lineTable.addCell(new Paragraph(item_value.get(loop).toString(), small));
                    lineTable.addCell(new Paragraph(item_desc.get(loop).toString(), small));
                }
                previous_category = current_category;

            }

            lineTable.setSpacingBefore(20);
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
