/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
//import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author pathfinder
 */
public class UploadLeavebalance extends HttpServlet {
   private final String UPLOAD_DIRECTORY = "C:/ScheduleExcel";
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
        String noOfrows =  request.getParameter("row") != null ? request.getParameter("row") : "";
        String name="";
        int qrksucc=0;
        try {
            ArrayList emp_code=new ArrayList();
            ArrayList cl=new ArrayList();
            ArrayList pl=new ArrayList();
  if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }

               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
              try
        {
            FileInputStream file = new FileInputStream(new File(UPLOAD_DIRECTORY + File.separator + name));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
               // int row1 = sheet.getPhysicalNumberOfRows();
pathfinder.functions.hr.SalarydetailsVO svo = new pathfinder.functions.hr.SalarydetailsVO();
pathfinder.functions.hr.SalarydetailsDAO sdao = new pathfinder.functions.hr.SalarydetailsDAO();
//pathfinder.functions.hr.GetSalarydetailsfordisp getsdao = new pathfinder.functions.hr.GetSalarydetailsfordisp();
int iteratnorows = Integer.parseInt(noOfrows);
            for (int i = 1; i<=iteratnorows; i++){
                System.out.println("iteratnorows"+iteratnorows);
		 XSSFRow row = sheet.getRow(i);
                  //int cells = row.getPhysicalNumberOfCells();
                  DataFormatter formatter = new DataFormatter(Locale.US);
		 for(int j=1; j<7; j++){
                      String value = null;

		  XSSFCell cell = row.getCell(j);
 try{
     if (cell.getCellType() == Cell.CELL_TYPE_FORMULA){
     //System.out.println(cell.getNumericCellValue());
     value=Double.toString(cell.getNumericCellValue());

}
 else if (cell.getCellType() == Cell.CELL_TYPE_BLANK){
     value="N/A";
 }

 else{
        // System.out.println("cell"+cell);
                   value = formatter.formatCellValue(cell);

 }
    if(j==1){
       if(emp_code.equals("1496")) {
           value="V001";
       }
     emp_code.add(value);
     System.out.println("cellcode"+value);
     }
 else if(j == 5)
     {
     float calculteDays = 1;
     calculteDays =calculteDays+Float.parseFloat(value);
     value = String.valueOf(calculteDays);
     cl.add(value);
     //System.out.println("cellCl"+value);
     }
	 else if(j == 6)
     {
             //System.out.println("cellpl"+value);
     pl.add(value);
     }

 }
 catch(Exception e){
 e.printStackTrace();
 }
	            }
            }
 svo.setClavailableForUpdate(cl);
 svo.setplavailableForUpdate(pl);
 svo.setEmp_code(emp_code);

qrksucc = sdao.UpdateLeavebalanceFromExcel(svo);
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
            if(qrksucc==1){
 RequestDispatcher rd=request.getRequestDispatcher("/viewEmployeeLeaveDetails.jsp");
        rd.forward(request, response);
            }
 else{
 RequestDispatcher rd=request.getRequestDispatcher("/uploadSalaryslip.jsp?&error=Not uploaded");
 }


        } finally {
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
