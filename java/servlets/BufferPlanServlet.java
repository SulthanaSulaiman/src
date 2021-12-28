
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.BufferPlanDAO;
import pathfinder.functions.BufferPlanVO;

/**
 *
 * @author Aravindhanj
 */
public class BufferPlanServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String getChecked = "";
            String getBufferPlanId = "";
            String getStageCode = "";
            String getMilestoneActCode = "";
            String getBufferDays = "";
            List bufferIdList = new ArrayList();
            List stageCodeList = new ArrayList();
            List milestoneActCodeList = new ArrayList();
            List bufferDaysList = new ArrayList();

            BufferPlanDAO bufferPlanDAO = new BufferPlanDAO();
            BufferPlanVO bufferPlanVO = new BufferPlanVO();

            String action = request.getParameter("action") != null? request.getParameter("action") : "";
            int count = Integer.parseInt(request.getParameter("count") != null? request.getParameter("count") : "0");
            String clientId = request.getParameter("client_name_hidden") != null? request.getParameter("client_name_hidden") : "0";
            String clientName = request.getParameter("client_name") != null? request.getParameter("client_name") : "0";
            String empId = request.getParameter("empId") != null? request.getParameter("empId") : "0";
            if(action.equals("SAVE")) {
                for(int i=0; i < count; i++) {
                    getChecked = request.getParameter("check"+i) != null ? request.getParameter("check"+i) : "0";
                    if(getChecked.equals("1")) {
                        getBufferPlanId = request.getParameter("bufferPlanId"+i) != null ? request.getParameter("bufferPlanId"+i) : "0";
                        getStageCode = request.getParameter("stageCode"+i) != null ? request.getParameter("stageCode"+i) : "";
                        getMilestoneActCode = request.getParameter("milestoneActCode"+i) != null ? request.getParameter("milestoneActCode"+i) : "";
                        getBufferDays = request.getParameter("bufferDays"+i) != null ? request.getParameter("bufferDays"+i) : "";
                        bufferIdList.add(getBufferPlanId);
                        stageCodeList.add(getStageCode);
                        milestoneActCodeList.add(getMilestoneActCode);
                        bufferDaysList.add(getBufferDays);
                    }
                }
                bufferPlanVO.setBufferPlanIdList(bufferIdList);
                bufferPlanVO.setCustomerId(clientId);
                bufferPlanVO.setStageCodeList(stageCodeList);
                bufferPlanVO.setMilestoneActCodeList(milestoneActCodeList);
                bufferPlanVO.setBufferDaysList(bufferDaysList);
                bufferPlanVO.setEmpId(empId);
                bufferPlanVO = bufferPlanDAO.saveBufferPlan(bufferPlanVO);
                int result = bufferPlanVO.getResult();
                response.sendRedirect("BufferPlan.jsp?client_name="+clientName+"&client_name_hidden="+clientId+"&action="+action+"&res="+result);
            }
            if(action.equals("DELETE")) {
                for(int i=0; i < count; i++) {
                    getChecked = request.getParameter("check"+i) != null ? request.getParameter("check"+i) : "0";
                    if(getChecked.equals("1")) {
                        getBufferPlanId = request.getParameter("bufferPlanId"+i) != null ? request.getParameter("bufferPlanId"+i) : "0";
                        bufferIdList.add(getBufferPlanId);
                    }
                }
                bufferPlanVO.setBufferPlanIdList(bufferIdList);
                bufferPlanVO = bufferPlanDAO.deleteBufferPlan(bufferPlanVO);
                int result = bufferPlanVO.getResult();
                response.sendRedirect("BufferPlan.jsp?client_name="+clientName+"&client_name_hidden="+clientId+"&action="+action+"&res="+result);
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
