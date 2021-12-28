/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets.pdf;

import java.io.IOException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.PrintWriter;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raghuramanm
 */
public class CastOffPDFServlet extends HttpServlet {

    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 20,Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10,Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 11,Font.BOLD);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.UNDEFINED, 8, Font.NORMAL);

    java.util.List chapterDetList = new ArrayList();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String getSearchParam = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";//SearchProj
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";//SearchProjid
        String castOffReq = "castoff";

        String title = "";
        String author = "";
        String isbn10 = "";
        String isbn13 = "";
        String customer = "";
        String contactId = "";

        java.util.List disp_chapterId =new ArrayList();
        java.util.List disp_genericName =new ArrayList();
        java.util.List disp_estStartPage =new ArrayList();
        java.util.List disp_mssPageCount= new ArrayList();

        String fileName = getSearchParam+"_CastOff_Report.pdf";
        fileName = fileName.replaceAll(" ", "-");
        
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);

        response.setContentType("application/pdf");

        /*FileInputStream fileInputStream = new FileInputStream(fileName);
        OutputStream responseOutputStream = response.getOutputStream();
        int bytes;
	while ((bytes = fileInputStream.read()) != -1) {
            responseOutputStream.write(bytes);
        }*/
        try {
            
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Paragraph headerPara = new Paragraph();

            headerPara.add(new Paragraph("CastOff Summary", headerFont));
            headerPara.setAlignment(Element.ALIGN_LEFT);
            document.add(headerPara);

            document.add(Chunk.NEWLINE);

            float[] colsWidth = {0.5f, 2f};
            PdfPTable table=new PdfPTable(colsWidth);
            
            pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
            pathfinder.functions.projects.ProjBookMapInfo pbmi = new pathfinder.functions.projects.ProjBookMapInfo();

            if (!getProjIdParam.equals("")) {
                    projInfo.setProjId(getProjIdParam);
                    projInfo.collectProjectName();
                    title = projInfo.getTitle();
                    author = projInfo.getProjPrimaryAuthorName();
                    isbn10 = projInfo.getISBN10();
                    isbn13 = projInfo.getISBN13();
                    customer = projInfo.getClient();
                    contactId = projInfo.getClientCode();
            }

            PdfPCell cell1 = new PdfPCell(new Paragraph("Customer: ", subFont));
            cell1.setMinimumHeight(20f);
            disableBorders(cell1);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Paragraph(customer, small));
            cell2.setMinimumHeight(20f);
            disableBorders(cell2);
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Author: ", subFont));
            cell3.setMinimumHeight(20f);
            disableBorders(cell3);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph(author, small));
            cell4.setMinimumHeight(20f);
            disableBorders(cell4);
            table.addCell(cell4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Title: ", subFont));
            cell5.setMinimumHeight(20f);
            disableBorders(cell5);
            table.addCell(cell5);

            PdfPCell cell6 = new PdfPCell(new Paragraph(title, small));
            cell6.setMinimumHeight(20f);
            disableBorders(cell6);
            table.addCell(cell6);

            document.add(table);

            document.add(new Paragraph("\n"));
            
            float[] col = {1f, 2.5f, 1f, 1f};
            PdfPTable isbnTable=new PdfPTable(col);
            isbnTable.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell isbn10cell = new PdfPCell(new Paragraph("ISBN 10: ", subFont));
            isbn10cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            isbn10cell.setMinimumHeight(20f);
            disableBorders(isbn10cell);
            isbnTable.addCell(isbn10cell);

            PdfPCell isbn10cellVal = new PdfPCell(new Paragraph(isbn10, small));
            isbn10cellVal.setMinimumHeight(20f);
            disableBorders(isbn10cellVal);
            isbnTable.addCell(isbn10cellVal);

            PdfPCell isbn13Cell = new PdfPCell(new Paragraph("ISBN 13: ", subFont));
            isbn13Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            isbn13Cell.setMinimumHeight(20f);
            disableBorders(isbn13Cell);
            isbnTable.addCell(isbn13Cell);

            PdfPCell isbn13CellVal = new PdfPCell(new Paragraph(isbn13, small));
            isbn13CellVal.setMinimumHeight(20f);
            disableBorders(isbn13CellVal);
            isbnTable.addCell(isbn13CellVal);

            document.add(isbnTable);

            float[] cols = {1f, 2f, 2f};
            PdfPTable listTitle=new PdfPTable(cols);
            listTitle.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell componentCell = new PdfPCell(new Paragraph("Component ", smallBold));
            componentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            componentCell.setMinimumHeight(20f);
            disableBorders(componentCell);
            componentCell.enableBorderSide(componentCell.BOTTOM);
            componentCell.setBorderWidthBottom(1f);
            listTitle.addCell(componentCell);

            PdfPCell estPagesCell = new PdfPCell(new Paragraph("Estimated Pages ", smallBold));
            estPagesCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            estPagesCell.setMinimumHeight(20f);
            disableBorders(estPagesCell);
            estPagesCell.enableBorderSide(estPagesCell.BOTTOM);
            estPagesCell.setBorderWidthBottom(1f);
            listTitle.addCell(estPagesCell);

            PdfPCell EmptyCell = new PdfPCell();
            EmptyCell.setMinimumHeight(20f);
            disableBorders(EmptyCell);
            listTitle.addCell(EmptyCell);

            document.add(new Paragraph("\n"));

            document.add(new Paragraph("\n"));

            if(!getProjIdParam.equals("")){
                pbmi.setProjId(getProjIdParam);
                pbmi.collectProjectBookMap();
                disp_chapterId=pbmi.getChapterId();
                disp_genericName=pbmi.getChapterName();
                disp_estStartPage= pbmi.getEstStartPage();
                disp_mssPageCount= pbmi.getMssPageCount();

            }

            int totalEstValues=0;

            for(int index=0; index<disp_chapterId.size(); index++){

                String compCellVal = disp_genericName.get(index).toString();
                PdfPCell compCell = new PdfPCell(new Paragraph(compCellVal, small));
                compCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                compCell.setMinimumHeight(20f);
                disableBorders(compCell);
                listTitle.addCell(compCell);

                if(disp_estStartPage.get(index)!=""){
                    totalEstValues += Integer.parseInt(disp_estStartPage.get(index).toString());
                }else{
                    totalEstValues += 0;
                }
                
                String estPageVal = disp_estStartPage.get(index).toString();
                PdfPCell estPage = new PdfPCell(new Paragraph(estPageVal, small));
                estPage.setHorizontalAlignment(Element.ALIGN_CENTER);
                estPage.setMinimumHeight(20f);
                disableBorders(estPage);
                listTitle.addCell(estPage);

                PdfPCell testCell = new PdfPCell();
                testCell.setMinimumHeight(20f);
                disableBorders(testCell);
                listTitle.addCell(testCell);
            }

            document.add(listTitle);

            float[] colWidths = {1f, 2f, 2f};
            PdfPTable total=new PdfPTable(colWidths);
            total.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell totalCell = new PdfPCell(new Paragraph("Total", smallBold));
            totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalCell.setMinimumHeight(20f);
            disableBorders(totalCell);
            totalCell.enableBorderSide(totalCell.TOP);
            totalCell.setBorderWidthTop(1f);
            total.addCell(totalCell);

            String estPageTotal = Integer.toString(totalEstValues);
            PdfPCell totalCellVal = new PdfPCell(new Paragraph(estPageTotal, smallBold));
            totalCellVal.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalCellVal.setMinimumHeight(20f);
            disableBorders(totalCellVal);
            totalCellVal.enableBorderSide(totalCellVal.TOP);
            totalCellVal.setBorderWidthTop(1f);
            total.addCell(totalCellVal);

            PdfPCell testCell = new PdfPCell();
            testCell.setMinimumHeight(20f);
            disableBorders(testCell);
            total.addCell(testCell);

            document.add(total);

            document.close();


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static void disableBorders(PdfPCell cell) {
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }

}


