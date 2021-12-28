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
import javax.servlet.http.HttpSession;
import pathfinder.functions.hr.*;

/**
 *
 * @author Parameshwarant
 */
public class ApprovalAppraisalServlet extends HttpServlet {

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
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ApprovalAppraisalServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApprovalAppraisalServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
            HttpSession session = request.getSession(true);
            String seseid = (String) session.getAttribute("theEid");
            String sesKey = (String) session.getAttribute("empKey");
            pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
            String redirect = "";
            List answeredQuest = new ArrayList();
            List answeredRemark = new ArrayList();
            int listedQuest = 0;
            int saveSupAppRmkResult = 0;
            String collectAnsweredQuest = "";
            String collectAnsweredRmk = "";
            String loopElement = "";
            String getDeptParam = request.getParameter("dept") != null ? request.getParameter("dept") : "";
            String getDesigParam = request.getParameter("Designation") != null ? request.getParameter("Designation") : "";
            String currCycleId = request.getParameter("cycleId") != null ? request.getParameter("cycleId") : "";
            String getEmpNameParam = request.getParameter("empName") != null ? request.getParameter("empName") : "";
            String questCountParam = request.getParameter("questCount") != null ? request.getParameter("questCount") : "";
            String saveOrModify = request.getParameter("saveOrModify") != null ? request.getParameter("saveOrModify") : "";
            String tempSaveChk = request.getParameter("tempSaveSuperApp");
            String saveChk = request.getParameter("saveSuperApp");
            String superEmpID = request.getParameter("superEmpId") != null ? request.getParameter("superEmpId") : "";
            
            if (saveChk != null || tempSaveChk != null) {

                if (!getEmpNameParam.equals("") && !getEmpNameParam.equals("ALL")) {

                    listedQuest = Integer.parseInt(questCountParam);

                    if (listedQuest > 0) {
                        for (int idx = 0; idx < listedQuest; idx++) {

                            loopElement = "approveQuestId" + String.valueOf(idx);
                            collectAnsweredQuest = request.getParameter(loopElement) != null ? request.getParameter(loopElement) : "";
                            loopElement = "approveRemark" + String.valueOf(idx);
                            collectAnsweredRmk = request.getParameter(loopElement) != null ? request.getParameter(loopElement) : "";

                            collectAnsweredQuest = collectAnsweredQuest.trim();
                            collectAnsweredRmk = collectAnsweredRmk.trim();
                            collectAnsweredRmk = collectAnsweredRmk.replace("'", "\\'");
                            answeredQuest.add(collectAnsweredQuest);
                            answeredRemark.add(collectAnsweredRmk);
                        }
                    }

                    SaveApproverAppraisalDAO saveApprovalApp = new SaveApproverAppraisalDAO();
                    SaveApproverAppraisalVO setApprovalApp = new SaveApproverAppraisalVO();
                    setApprovalApp.setApprovalAppEmpId(seseid);
                    setApprovalApp.setApprovalAppEmpEncryptKey(sesKey);
                    setApprovalApp.setCycleCode(currCycleId);

                    setApprovalApp.setApprovalAppMemberEmpId(getEmpNameParam);
                    setApprovalApp.setApprovalAppSuperEmpId(superEmpID);

                    if (tempSaveChk != null) {
                        setApprovalApp.setapprovalAppSubmitFlag("0");
                        tempSaveChk = "save";
                    } else if (saveChk != null) {
                        setApprovalApp.setapprovalAppSubmitFlag("1");
                    }
                    setApprovalApp.setSaveOrModifyFlag(saveOrModify);
                    setApprovalApp.setapprovalAppQuesID(answeredQuest);
                    setApprovalApp.setapprovalAppRemark(answeredRemark);

                    System.out.println(answeredRemark+" and "+answeredQuest);
                    String empIdforurl = splChar.encoding(getEmpNameParam);
                    saveSupAppRmkResult = saveApprovalApp.saveApprovalAppraiasal(setApprovalApp);
                    redirect += "saveSupAppRmkResult=" + saveSupAppRmkResult + "&empName=" + empIdforurl + "&Designation=" + getDesigParam + "&dept=" + getDeptParam + "&cycleId=" + currCycleId + "&saveOrModify=" + saveOrModify;
                    redirect += "&tempSaveSuperApp=" + tempSaveChk + "&saveSuperApp=" + saveChk + "&questCount=" + questCountParam;
                    response.sendRedirect("ApprovalAppraisal.jsp?" + redirect);
                }
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
