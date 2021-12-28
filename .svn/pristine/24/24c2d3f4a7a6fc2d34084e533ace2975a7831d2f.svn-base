/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Parameshwarant
 */
public class ShiftServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String currEmpId= (String) session.getAttribute("theEid");
            String getDeptName = request.getParameter("deptName") != null? request.getParameter("deptName"):"";
            String getDesigName = request.getParameter("desigName") != null? request.getParameter("desigName"):"";
            String getEmployeeId = request.getParameter("employeeId") != null? request.getParameter("employeeId"):"";
            String getShiftCompletedFlag = request.getParameter("shiftCompleted") != null? request.getParameter("shiftCompleted"):"";
            String getEmpCount = request.getParameter("empcount") != null? request.getParameter("empcount"):"";
            int empCount = 0;
            if(!getEmpCount.equals("")) {
                empCount = Integer.parseInt(getEmpCount);
            }
            
            pathfinder.functions.hr.EmployeeShiftAllocationVO shiftServletVO = new pathfinder.functions.hr.EmployeeShiftAllocationVO();
            pathfinder.functions.hr.EmployeeShiftAllocationDAO shiftServletDAO = new pathfinder.functions.hr.EmployeeShiftAllocationDAO();
            List shiftIdList = new ArrayList();
            List empIdList = new ArrayList();
            List fromDateList = new ArrayList();
            List toDateList = new ArrayList();
            List empShiftStatusIdList = new ArrayList();
            //int use=1;
            for (int i = 0; i < empCount; i++) {
                
                String chkElement = "chkbx" + i;
                //System.out.println("8888888"+chkElement);
                String chk = request.getParameter(chkElement) != null? request.getParameter(chkElement):"";

                if(chk.equals("1")){
                    String shiftIdElement = "empShift" + i;
                    String empIdElement = "empId" + i;
                    String fromDateElement = "empFromDate" + i;
                    String toDateElement = "empToDate" + i;
                    String empShiftStatusId = "empShiftStatusId" + i;
                    String shiftID = request.getParameter(shiftIdElement)!= null? request.getParameter(shiftIdElement):"";
                    String empId = request.getParameter(empIdElement)!= null? request.getParameter(empIdElement):"";
                    String fromDate = request.getParameter(fromDateElement)!= null? request.getParameter(fromDateElement):"";
                    String toDate = request.getParameter(toDateElement)!= null? request.getParameter(toDateElement):"";
                    String empShiftStatus = request.getParameter(empShiftStatusId)!= null? request.getParameter(empShiftStatusId):"";
                    shiftIdList.add(shiftID);
                    empIdList.add(empId);
                    fromDateList.add(fromDate);
                    toDateList.add(toDate);
                    empShiftStatusIdList.add(empShiftStatus);
                    
                }
            }
            
            shiftServletVO.setShiftIdToAdd(shiftIdList);
            shiftServletVO.setEmpIdToAdd(empIdList);
            shiftServletVO.setFromDate(fromDateList);
            shiftServletVO.setToDate(toDateList);
            shiftServletVO.setCurrEmpId(currEmpId);
            shiftServletVO.setShiftStatusIdToChk(empShiftStatusIdList);
            if(getShiftCompletedFlag.equals("1")) {
                shiftServletVO.setShiftComplete("yes");
                shiftServletVO.setEmpIdForShiftComplete(getEmployeeId);
            }
            //shiftServletVO.set
            
            shiftServletDAO.addUpdateEmpShift(shiftServletVO);
            response.sendRedirect("EmployeeShiftAllocation.jsp?deptName="+getDeptName+"&desigName="+getDesigName+"&search=yes");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException"+e);
        }
    }
}
