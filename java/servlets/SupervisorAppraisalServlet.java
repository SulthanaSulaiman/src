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
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpSession;
import pathfinder.functions.hr.*;
/**
 *
 * @author Parameshwarant
 */
public class SupervisorAppraisalServlet extends HttpServlet {
   
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
            out.println("<title>Servlet SupervisorAppraisalServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SupervisorAppraisalServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
            pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
            HttpSession session = request.getSession(true);
            String seseid = (String) session.getAttribute("theEid");
            String sesKey = (String) session.getAttribute("empKey");
            String redirect="";
            List answeredQuest = new ArrayList();
            List answeredRemark = new ArrayList();
            List selfqustionAnsFlag = new ArrayList();
            int listedQuest = 0;
            int saveSupAppRmkResult = 0;
            String collectAnsweredQuest = "";
            String collectAnsweredRmk = "";
            String loopElement = "";
            String getDeptParam = request.getParameter("dept")!=null?request.getParameter("dept"):"";
            String getDesigParam = request.getParameter("Designation")!=null?request.getParameter("Designation"):"";
            String currCycleId=request.getParameter("cycleId")!=null ? request.getParameter("cycleId") : "";
            String getEmpNameParam = request.getParameter("empName")!=null?request.getParameter("empName"):"";
            String questCountParam=request.getParameter("questCount")!=null?request.getParameter("questCount"):"";
            String saveOrModify=request.getParameter("saveOrModify")!=null?request.getParameter("saveOrModify"):"";
            String tempSaveChk=request.getParameter("tempSaveSuperApp");
            String saveChk=request.getParameter("saveSuperApp");
            String selfqustinCnt = request.getParameter("selfqustinCnt")!=null?request.getParameter("selfqustinCnt"):"0";
            int selfqust=0;
        if(saveChk!=null||tempSaveChk!=null)
                {

	if(!getEmpNameParam.equals("")&&!getEmpNameParam.equals("ALL")){
                selfqust = Integer.parseInt(selfqustinCnt);
                listedQuest=Integer.parseInt(questCountParam);

                //selfqustinCnt
if(selfqust>0){
                for(int idx=0;idx<selfqust;idx++){

                        //loopElement = "questId"+String.valueOf(idx);
                        //collectAnsweredQuest=request.getParameter(loopElement)!=null?request.getParameter(loopElement):"";
                        loopElement = "cmmtsForself"+String.valueOf(idx);
                        collectAnsweredRmk=request.getParameter(loopElement)!=null?request.getParameter(loopElement):"";
                        collectAnsweredQuest=String.valueOf(idx);
                        collectAnsweredRmk=collectAnsweredRmk.trim();
                        collectAnsweredRmk=collectAnsweredRmk.replace("'", "\\'");
                        answeredQuest.add(collectAnsweredQuest);
                        answeredRemark.add(collectAnsweredRmk);
                        selfqustionAnsFlag.add("1");
                        }

}

                if(listedQuest>0){
                    for(int idx=0;idx<listedQuest;idx++){

                        loopElement = "questId"+String.valueOf(idx);
                        collectAnsweredQuest=request.getParameter(loopElement)!=null?request.getParameter(loopElement):"";
                        loopElement = "rmk"+String.valueOf(idx);
                        collectAnsweredRmk=request.getParameter(loopElement)!=null?request.getParameter(loopElement):"";
                        collectAnsweredQuest=collectAnsweredQuest.trim();
                        collectAnsweredRmk=collectAnsweredRmk.trim();
                        collectAnsweredRmk=collectAnsweredRmk.replace("'", "\\'");
                        answeredQuest.add(collectAnsweredQuest);
                        answeredRemark.add(collectAnsweredRmk);
                        selfqustionAnsFlag.add("0");
                        }
                        }

                     SaveSupervisorAppraisalData saveSuperApp = new SaveSupervisorAppraisalData();
                     saveSuperApp.setMemberEmployeeID(getEmpNameParam);
                     saveSuperApp.setSuperEmployeeID(seseid);
                     saveSuperApp.setSuperEmpEncryptKey(sesKey);
                     saveSuperApp.setCycleCode(currCycleId);
                     if(tempSaveChk!=null)
                        {
                            saveSuperApp.setSuperSubmitFlag("0");
                            tempSaveChk="save";
                        }   else if(saveChk!=null)
                        {
                            saveSuperApp.setSuperSubmitFlag("1");
                        }
                     saveSuperApp.setSuperSaveOrModify(saveOrModify);
                     saveSuperApp.setSuperApprisalStatus("1");
                     saveSuperApp.setSuperQuestionIDList(answeredQuest);
                     saveSuperApp.setSuperRemarkList(answeredRemark);
                     saveSuperApp.setSelfAppQustnFlag(selfqustionAnsFlag);
                     saveSupAppRmkResult = saveSuperApp.saveSupervisorApparaisal();
                     
                     String empIdforurl = splChar.encoding(getEmpNameParam);
                     //System.out.println("empName"+empIdforurl);
                     redirect+="saveSupAppRmkResult="+saveSupAppRmkResult+"&empName="+empIdforurl+"&Designation="+getDesigParam+"&dept="+getDeptParam+"&cycleId="+currCycleId+"&saveOrModify="+saveOrModify;
                     redirect+="&tempSaveSuperApp="+tempSaveChk+"&saveSuperApp="+saveChk+"&questCount="+questCountParam+"&empName='"+empIdforurl+"'";
                     response.sendRedirect("SupervisorAppraisal.jsp?"+redirect);
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
