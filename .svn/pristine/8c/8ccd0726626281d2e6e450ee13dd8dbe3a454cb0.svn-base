/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.*;
import java.util.*;
import java.text.*;
import java.util.regex.*;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author pathfinder
 */
//@WebServlet("/uploadEmpPhoto")
@MultipartConfig
public class uploadEmpPhoto extends HttpServlet {
   private final String UPLOAD_DIRECTORY = "C:/Users/pathfinder/Desktop/test/";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

         try {
           //  String name="";
           InputStream inputStream = null; // input stream of the upload file
String getEmpId = request.getParameter("empIds")!=null?request.getParameter("empIds"):"";
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("empphoto");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            //FileInputStream fis = new FileInputStream(image);
           // System.out.println(inputStream);
        }
                        //
//   if(ServletFileUpload.isMultipartContent(request)){
//            try {
//
//                DiskFileItemFactory factory = new DiskFileItemFactory();
//
//      ServletFileUpload sfu = new ServletFileUpload(factory);
//      List items = sfu.parseRequest(request);
//  byte[] b=null;
//      Iterator iter = items.iterator();
//
//      while (iter.hasNext()) {
//         FileItem item = (FileItem) iter.next();
//         if (item.isFormField()) {
//
//              b = item.get();
//              System.out.println("yrs"+b);
//          }
//      }
//                  // fis = new FileInputStream(file);
//           // FileInputStream fis = new FileInputStream(file);
//
//
//               //File uploaded successfully
//               request.setAttribute("message", "File Uploaded Successfully");
               connection.DBconnection ConProp = new connection.DBconnection();
            Connection con= null;
            con = ConProp.getSampleProperty();
            PreparedStatement ps = con.prepareStatement("update user set user_Img = ? where emp_id = ?");
           ps.setString(2, getEmpId);
            // size must be converted to int otherwise it results in error
            ps.setBinaryStream(3,inputStream);
            // ps.setBlob(3, inputStream);
            ps.executeUpdate();

            con.close();
//            // response.sendRedirect("empDetails.jsp?windows.onLoad=focusOnEmp("+getEmpId+")");
//            out.println("Added Photo Successfully. Close this window<p>");
//            } catch (Exception ex) {
//                ex.printStackTrace();
//               request.setAttribute("message", "File Upload Failed due to " + ex);
//            }
//
//        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }

            
        
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        finally {
            out.close();
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
