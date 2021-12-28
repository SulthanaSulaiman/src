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
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
//import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author pathfinder
 */
public class UploadSalaryslipservlet extends HttpServlet {

 
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
         String name="";
       String noOfrows =  request.getParameter("row") != null ? request.getParameter("row") : "";
       String month =  request.getParameter("montofsa") != null ? request.getParameter("montofsa") : "";
       String year =  request.getParameter("yearofsa") != null ? request.getParameter("yearofsa") : "";
       ArrayList emp_code=new ArrayList();
       ArrayList emp_codeForchk=new ArrayList();
       ArrayList pfno=new ArrayList();
       ArrayList esi=new ArrayList();
ArrayList designation=new ArrayList();
ArrayList monthSyear=new ArrayList();
ArrayList empname=new ArrayList();
ArrayList doj=new ArrayList();
ArrayList department=new ArrayList();
ArrayList noofdays=new ArrayList();
ArrayList dayspayable=new ArrayList();
ArrayList lop=new ArrayList();
ArrayList basic=new ArrayList();
ArrayList hra=new ArrayList();
ArrayList va=new ArrayList();
ArrayList ta=new ArrayList();
ArrayList lta=new ArrayList();
ArrayList ma=new ArrayList();
ArrayList actual_gross_salary=new ArrayList();
ArrayList earned_basic=new ArrayList();
ArrayList earned_hra=new ArrayList();
ArrayList earnedva=new ArrayList();
ArrayList earnedta=new ArrayList();
ArrayList earnedlta=new ArrayList();
ArrayList earnedma=new ArrayList();
ArrayList gross_earnedsalary=new ArrayList();
ArrayList arrears=new ArrayList();
ArrayList bonus=new ArrayList();
ArrayList att_bonus=new ArrayList();
ArrayList overtime=new ArrayList();
ArrayList misc=new ArrayList();
ArrayList other_earnings_total=new ArrayList();
ArrayList pf_ded=new ArrayList();
ArrayList esi_ded=new ArrayList();
ArrayList salary_adv=new ArrayList();
ArrayList tds=new ArrayList();
ArrayList proff_tax=new ArrayList();
ArrayList lwf=new ArrayList();
ArrayList other_deductions=new ArrayList();
ArrayList total_ded=new ArrayList();
ArrayList net_transfer=new ArrayList();
ArrayList amount_in_words=new ArrayList();
ArrayList email=new ArrayList();
ArrayList clsl_balance=new ArrayList();
ArrayList pl_balance=new ArrayList();
ArrayList comp_off=new ArrayList();
ArrayList remarks=new ArrayList();
ArrayList uan_no=new ArrayList();
int qrksucc=0;
      // Part paramPart=request.getPart("row");
       //System.out.println(noOfrows);
      // System.out.println(paramPart);
String filenameForsheet3 = "C:\\ScheduleExcel\\S4Carlisle_Payslip_"+month+"_"+year+".xlsm";

        try {
             Logger logger = Logger.getLogger("MyLog");
            FileHandler fh;
             fh = new FileHandler("C:\\ScheduleExcel\\MyLogFile.log");
            logger.addHandler(fh);
            //logger.setLevel(Level.ALL);
            SimpleFormatter formatter1 = new SimpleFormatter();
            fh.setFormatter(formatter1);

            // the following statement is used to log any messages

            if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);

                for(FileItem item : multiparts){
                    if(item.isFormField()){
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
           // FileInputStream file = new FileInputStream(new File(UPLOAD_DIRECTORY + File.separator + name));
            FileInputStream file1 = new FileInputStream(new File("C:\\ScheduleExcel\\S4Carlisle_Payslip_"+month+"_"+year+".xlsm"));
            //Create Workbook instance holding reference to .xlsx file
           XSSFWorkbook workbook = new XSSFWorkbook(file1);

            //Get first/desired sheet from the workbook
             XSSFSheet sheet = workbook.getSheetAt(0);
//FormulaEvaluator formulaEval = workbook.getCreationHelper().createFormulaEvaluator();
            //Iterate through each rows one by one
//            Iterator<Row> rowIterator = sheet.iterator();
//            int noOfrow=0;
//            int noOfcolumn=0;
//            while (rowIterator.hasNext())
//            {
//                 noOfrow++;
//                Row row = rowIterator.next();
//                //For each row, iterate through all the columns
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext())
//                {
//                 noOfcolumn++;
////                    Cell cell = cellIterator.next();
////
////                    //Check the cell type and format accordingly
////                    switch (cell.getCellType())
////                    {
////                        case Cell.CELL_TYPE_NUMERIC:
////                            System.out.print(cell.getNumericCellValue() + "t");
////                            break;
////                        case Cell.CELL_TYPE_STRING:
////                            System.out.print(cell.getStringCellValue() + "t");
////                            break;
////                        case Cell.CELL_TYPE_BOOLEAN:
////                            System.out.print(cell.getStringCellValue() + "t");
////                            break;
////                            case Cell.CELL_TYPE_FORMULA:
////                            System.out.print(cell.getStringCellValue() + "t");
////                            break;
////                    }
//                }
//
//            }
               // int row1 = sheet.getPhysicalNumberOfRows();
pathfinder.functions.hr.SalarydetailsVO svo = new pathfinder.functions.hr.SalarydetailsVO();
pathfinder.functions.hr.SalarydetailsDAO sdao = new pathfinder.functions.hr.SalarydetailsDAO();
pathfinder.functions.hr.GetSalarydetailsfordisp getsdao = new pathfinder.functions.hr.GetSalarydetailsfordisp();
int iteratnorows = Integer.parseInt(noOfrows);
            for (int i = 1; i<=iteratnorows; i++){
		  XSSFRow row = sheet.getRow(i);
                  //int cells = row.getPhysicalNumberOfCells();
                  DataFormatter formatter = new DataFormatter(Locale.US);
		 for(int j=1; j<48; j++){
                      String value = null;
		    XSSFCell cell = row.getCell(j);
 try{
     if (cell.getCellType() == Cell.CELL_TYPE_FORMULA){
    System.out.println(cell.getNumericCellValue());
    logger.info("cell value"+cell);
     value=Double.toString(cell.getNumericCellValue());
}
 else if (cell.getCellType() == Cell.CELL_TYPE_BLANK){
     logger.info("cell value"+cell);
     value="N/A";
     
 }
 
 else{
         System.out.println("cell"+cell);
         value = formatter.formatCellValue(cell);
         logger.info("cell value"+value);
                
 }
    if(j==1){

     emp_code.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
 else if (j == 2) {
     pfno.add(value);
   logger.info("Row:"+i+"column:"+j+"value"+value);
     }
 else if (j == 3) {
     esi.add(value);
    logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 4)
     {
     designation.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
 else if(j == 5)
     {
     monthSyear.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
	 else if(j == 6)
     {
     empname.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
 else if(j == 7)
     {
     doj.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
 else if(j == 8)
     {
     department.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j ==9)
     {
     noofdays.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j == 10)
     {
     dayspayable.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j == 11)
     {
     lop.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j == 12)
     {
     basic.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j == 13)
     {
     hra.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j == 14)
     {
         va.add(value);
         logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j == 15)
     {
ta.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j == 16)
     {
lta.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);
    }
     else if(j == 17)
     {
     ma.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j == 18)
     {
     actual_gross_salary.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);
     }
     else if(j == 19)
     {
earned_basic.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 20)
     {
earned_hra.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 21)
     {
earnedva.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 22)
     {
earnedta.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 23)
     {
earnedlta.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 24)
     {
earnedma.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 25)
     {
gross_earnedsalary.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 26)
     {
arrears.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 27)
     {
bonus.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 28)
     {
att_bonus.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 29)
     {
overtime.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 30)
     {
misc.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 31)
     {
other_earnings_total.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 32)
     {
pf_ded.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 33)
     {
esi_ded.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 34)
     {
salary_adv.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 35)
     {
tds.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 36)
     {
proff_tax.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 37)
     {
lwf.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 38)
     {
other_deductions.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 39)
     {
total_ded.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 40)
     {
net_transfer.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 41)
     {
amount_in_words.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 42)
     {
email.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 43)
     {
clsl_balance.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 44)
     {
pl_balance.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
     else if(j == 45)
     {
     comp_off.add(value);
     logger.info("Row:"+i+"column:"+j+"value"+value);

     }

     else if(j == 46)
     {
//System.out.println("remarks"+value);
remarks.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);

     }
          else if(j == 47)
     {
//System.out.println("uan"+value);

uan_no.add(value);
logger.info("Row:"+i+"column:"+j+"value"+value);
     }

 }
 catch(Exception e){
 e.printStackTrace();
 logger.info("Row:"+i+"column:"+j+"Error"+e.getMessage());
 }
	            }
            }
 svo.setActual_gross_salary(actual_gross_salary);
 svo.setAmount_in_words(amount_in_words);
 svo.setArrears(arrears);
 svo.setAtt_bonus(att_bonus);
 svo.setBasic(basic);
 svo.setClsl_balance(clsl_balance);
 svo.setComp_off(comp_off);
 svo.setDayspayable(dayspayable);
 svo.setDepartment(department);
 svo.setDesignation(designation);
 svo.setDoj(doj);
 svo.setEarned_hra(earned_hra);
 svo.setEarnedma(earnedma);
 svo.setEarnedlta(earnedlta);
 svo.setEarnedta(earnedta);
 svo.setEarnedva(earnedva);
 svo.setEmail(email);
 svo.setEmp_code(emp_code);
 svo.setEsi_ded(esi_ded);
 svo.setGross_earnedsalary(gross_earnedsalary);
 svo.setHra(hra);
 svo.setLop(lop);
 svo.setLta(lta);
 svo.setLwf(lwf);
 svo.setMa(ma);
 svo.setMisc(misc);
 svo.setMonthSyear(monthSyear);
 svo.setName(empname);
 svo.setNet_transfer(net_transfer);
 svo.setNoofdays(noofdays);
 svo.setOther_deductions(other_deductions);
 svo.setOther_earnings_total(other_earnings_total);
 svo.setOvertime(overtime);
 svo.setPf_ded(pf_ded);
 svo.setPl_balance(pl_balance);
 svo.setProff_tax(proff_tax);
 svo.setRemarks(remarks);
 svo.setSalary_adv(salary_adv);
 svo.setSlno(lwf);
 svo.setTa(ta);
 svo.setTds(tds);
 svo.setTotal_ded(total_ded);
 svo.setUan_no(uan_no);
 svo.setVa(va);
 svo.setBonus(bonus);
 svo.setEarned_basic(earned_basic);
 svo.setPfno(pfno);
 svo.setEsi(esi);
 svo.setMonth(month);
 svo.setYear(year);
 getsdao.setMonthforsalarydisp(month);
 getsdao.setYearforsalarydisp(year);
 getsdao.getsalarydetailsForchk();
 emp_codeForchk = getsdao.getEmp_codeL();
 
 String forDeleteing="";
 for (int i =0; i<emp_codeForchk.size(); i++){
getsdao.deleteSalaryifalreadyexist(emp_codeForchk.get(i).toString(),month,year);
 }
qrksucc = sdao.insertsalarydetails(svo);
            file1.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
            if(qrksucc==1){
        RequestDispatcher rd=request.getRequestDispatcher("/generatePayslip.jsp?&success=Upload completed successfully");
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
