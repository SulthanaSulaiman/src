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
import pathfinder.functions.contacts.ProjAuthorInfo;
import com.itextpdf.text.pdf.PdfPageEventHelper;

/**
 *
 * @author pathfinder
 */
public class TestPDF extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=test.pdf");
        response.setContentType("application/pdf");
        Document document = new Document();
        try {
           
           
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        
       com.itextpdf.text.List list2 = new com.itextpdf.text.List(12);
        list2.setListSymbol("\u2022");
        list2.add(new ListItem(30, "Value 1"));
        list2.add(new ListItem(30, "Value 2"));
        list2.add(new ListItem(30, "Value 3"));
        PdfPTable table = new PdfPTable(1);
//        for (int i = 1; i < 6; i++) {
//            table.addCell("key " + i);
//            table.addCell(cell);
//            table.addCell("value " + i);
//            table.addCell("test" + i);
//            table.addCell("test1" + i);
//        }
        Chunk dept=new Chunk(" DEPARTMENT: ");
        Chunk deptV=new Chunk("R&D");
        Phrase deptP= new Phrase();
        deptP.add(dept);
        deptP.add(deptV);
        PdfPCell cellDpt;
        cellDpt = new PdfPCell(deptP);
        table.addCell(cellDpt);
        Paragraph para = new Paragraph("test");
        document.add(para);
        document.add(table);
        
        table.setKeepTogether(true);
        document.add(table);
        document.close();
        }  catch (DocumentException e) {
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

}
