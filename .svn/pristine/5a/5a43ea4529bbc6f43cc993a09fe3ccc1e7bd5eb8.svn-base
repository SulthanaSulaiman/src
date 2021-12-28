/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets.pdf;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author pathfinder
 */
public class EmployeeHandbookServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//       // reads input file from an absolute path
//        String filePath = "C:\\test.pdf";
//        File downloadFile = new File(filePath);
//        FileInputStream inStream = new FileInputStream(downloadFile);
//
//        // if you want to use a relative path to context root:
//        String relativePath = getServletContext().getRealPath("");
//        System.out.println("relativePath = " + relativePath);
//
//        // obtains ServletContext
//        ServletContext context = getServletContext();
//
//        // gets MIME type of the file
//        String mimeType = context.getMimeType(filePath);
//        if (mimeType == null) {
//            // set to binary type if MIME mapping not found
//            mimeType = "application/octet-stream";
//        }
//        System.out.println("MIME type: " + mimeType);
//
//        // modifies response
//        response.setContentType(mimeType);
//        response.setContentLength((int) downloadFile.length());
//
//        // forces download
//        String headerKey = "Content-Disposition";
//        String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
//        response.setHeader(headerKey, headerValue);
//
//        // obtains response's output stream
//        OutputStream outStream = response.getOutputStream();
//
//        byte[] buffer = new byte[4096];
//        int bytesRead = -1;
//
//        while ((bytesRead = inStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, bytesRead);
//        }
//
//        inStream.close();
//        outStream.close();
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
      //  processRequest(request, response);
        response.setContentType("application/pdf");
response.setHeader("Content-Disposition", "inline; filename=test1.pdf");
OutputStream out = response.getOutputStream();
try {
        FileInputStream in = new FileInputStream("C:\\S4Carlisle_Handbook_V3.pdf");
    int content;
    while ((content = in.read()) != -1) {
        out.write(content);
    }
} catch (IOException e) {
    e.printStackTrace();
}
out.close();
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
