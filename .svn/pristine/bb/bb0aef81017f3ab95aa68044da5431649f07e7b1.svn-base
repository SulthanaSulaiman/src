/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.projects.AutomatedTimeSheetDAO;
import pathfinder.functions.projects.AutomatedTimeSheetVO;

/**
 *
 * @author Aravindhanj
 */
public class AutomatedTimeSheetServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            AutomatedTimeSheetDAO automatedTimeSheetDAO = new AutomatedTimeSheetDAO();
            AutomatedTimeSheetVO automatedTimeSheetVO = new AutomatedTimeSheetVO();
            String getAction = request.getParameter("action")==null?"":request.getParameter("action");
            String getEmpId = request.getParameter("empId")==null?"":request.getParameter("empId");
            String getChapterCount = request.getParameter("chapterCount")==null?"0":request.getParameter("chapterCount");
            String getProjectParam = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";//SearchProj
            String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";//SearchProj
            String getProjTitleParam = request.getParameter("projectTitle") != null ? request.getParameter("projectTitle") : "";
            String chapterId = "";
            String responseText = "";
            String result = "";
            List mailShipKillList = new ArrayList();

            int count = 0;
            try {
                count = Integer.parseInt(getChapterCount);
            } catch (Exception e) {
                count = 0;
                System.out.println("Exception "+e);
            }

            if(getAction.equals("mailShipKillBulk")) {
                for(int index=0; index<count; index++) {
                    chapterId = request.getParameter("bulkMailShipKill"+index)==null?"":request.getParameter("bulkMailShipKill"+index);
                    if(!chapterId.equals("")) {
                        mailShipKillList.add(chapterId);
                    }
                }
                automatedTimeSheetVO.setBulkMailShipKillList(mailShipKillList);
                automatedTimeSheetVO.setEmpId(getEmpId);
                automatedTimeSheetDAO.bulkMailShipKill(automatedTimeSheetVO);
                result = automatedTimeSheetVO.getResult();
                responseText = "?project_name_hidden="+getProjIdParam+"&project_name="+getProjectParam+"&project_title="+getProjTitleParam+"&result="+result;
            }
            response.sendRedirect("BulkBatchKill.jsp"+responseText);
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
