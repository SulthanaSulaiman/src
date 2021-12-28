/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raghuramanm
 */
public class ManageAppraisalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int saveVar;
        int updateVar;
        int temp;
        String responseSave="";
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        String responseText = "";
        try {

            pathfinder.functions.hr.ManageAppraisalDAO appraisalDAO = new pathfinder.functions.hr.ManageAppraisalDAO();
            pathfinder.functions.hr.ManageAppraisalVO appraisalVO = new pathfinder.functions.hr.ManageAppraisalVO();

            if(action.equals("add")){
                String getAppriasalYear = request.getParameter("appraisalYear")!=null?request.getParameter("appraisalYear"):"";
                String getAppraisalMonth = request.getParameter("appraisalMonth")!=null?request.getParameter("appraisalMonth"):"";
                String getAppraisalDesc = request.getParameter("appraisalDesc")!=null?request.getParameter("appraisalDesc"):"";
                String getSelfAppStart = request.getParameter("newSelfAppraisalStart")!=null?request.getParameter("newSelfAppraisalStart"):"";
                String getSelfAppEnd = request.getParameter("newSelfAppraisalEnd")!=null?request.getParameter("newSelfAppraisalEnd"):"";
                String getSupAppStart = request.getParameter("newSupAppraisalStart")!=null?request.getParameter("newSupAppraisalStart"):"";
                String getSupAppEnd = request.getParameter("newSupAppraisalEnd")!=null?request.getParameter("newSupAppraisalEnd"):"";
                String getAppAppStart = request.getParameter("newAppAppraisalStart")!=null?request.getParameter("newAppAppraisalStart"):"";
                String getAppAppEnd = request.getParameter("newAppAppraisalEnd")!=null?request.getParameter("newAppAppraisalEnd"):"";
                //String getTotalCycles = request.getParameter("totalCycles")!=null?request.getParameter("totalCycles"):"";

                appraisalVO.setAppraisalYear(getAppriasalYear);
                appraisalVO.setAppraisalMonth(getAppraisalMonth);
                appraisalVO = appraisalDAO.getappraisalName(appraisalVO);
                //getAppriasalYear = appraisalVO.getAppraisalYear();
                getAppraisalMonth = appraisalVO.getAppraisalMonth();

                appraisalVO.setAppraisalYear(getAppriasalYear);
                appraisalVO.setAppraisalMonth(getAppraisalMonth);
                appraisalVO.setAppraisalDesc(getAppraisalDesc);
                appraisalVO.setSelfAppStart(getSelfAppStart);
                appraisalVO.setSelfAppEnd(getSelfAppEnd);
                appraisalVO.setSupAppStart(getSupAppStart);
                appraisalVO.setSupAppEnd(getSupAppEnd);
                appraisalVO.setAppAppStart(getAppAppStart);
                appraisalVO.setAppAppEnd(getAppAppEnd);
                //appraisalVO.setTotalCycles(getTotalCycles);
                appraisalVO = appraisalDAO.addAppraisal(appraisalVO);
                saveVar = appraisalVO.getSaveVar();
                if(saveVar>0){
                    responseText="recordSave=1";
                }else{
                    responseText="recordSave=0";
                }
            }else if(action.equals("update")){
                String getSelfAppStart = request.getParameter("selfAppraisalStart")!=null?request.getParameter("selfAppraisalStart"):"";
                String getSelfAppEnd = request.getParameter("selfAppraisalEnd")!=null?request.getParameter("selfAppraisalEnd"):"";
                String getSupAppStart = request.getParameter("supAppraisalStart")!=null?request.getParameter("supAppraisalStart"):"";
                String getSupAppEnd = request.getParameter("supAppraisalEnd")!=null?request.getParameter("supAppraisalEnd"):"";
                String getAppAppStart = request.getParameter("appAppraisalStart")!=null?request.getParameter("appAppraisalStart"):"";
                String getAppAppEnd = request.getParameter("appAppraisalEnd")!=null?request.getParameter("appAppraisalEnd"):"";

                appraisalVO.setSelfAppStart(getSelfAppStart);
                appraisalVO.setSelfAppEnd(getSelfAppEnd);
                appraisalVO.setSupAppStart(getSupAppStart);
                appraisalVO.setSupAppEnd(getSupAppEnd);
                appraisalVO.setAppAppStart(getAppAppStart);
                appraisalVO.setAppAppEnd(getAppAppEnd);
                appraisalVO = appraisalDAO.updateAppraisal(appraisalVO);
                updateVar = appraisalVO.getUpdateVar();
                if(updateVar>0){
                    responseText="recordUpdate=1";
                }else{
                    responseText="recordUpdate=0";
                }
            }
            response.sendRedirect("ManageAppraisal.jsp?"+responseText);

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
