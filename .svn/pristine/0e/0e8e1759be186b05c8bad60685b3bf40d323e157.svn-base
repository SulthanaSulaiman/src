/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.projects.*;

/**
 *
 * @author Parameshwarant
 */
public class NewToolAndFeatureLaunchFormServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    NewToolAndFeatureLaunchFormVO setLaunchInfo = new NewToolAndFeatureLaunchFormVO();
    NewToolAndFeatureLaunchFormDAO saveOrModifyLaunchInfo = new NewToolAndFeatureLaunchFormDAO();
    int saveOrModifyStatus = 0;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {

            String messageTitle = request.getParameter("messageTitle") !=null ? request.getParameter("messageTitle") : "";
            String messageContent = request.getParameter("messageContent") !=null ? request.getParameter("messageContent") : "";
            String messageSentBy = (String) session.getAttribute("theEid");
            String messageSentTo = request.getParameter("selectDepartment") !=null ? request.getParameter("selectDepartment") : "";
            String messageId = request.getParameter("messageID") !=null ? request.getParameter("messageID") : "";
            String saveOrModify = request.getParameter("saveOrModify") !=null ? request.getParameter("saveOrModify") : "";
            String sourcePage = "";
            messageTitle = messageTitle.replace("'", "\\'");
            setLaunchInfo.setMessageTitle(messageTitle);
            setLaunchInfo.setMessageBy(messageSentBy);
            setLaunchInfo.setMessageContent(messageContent);
            setLaunchInfo.setMessageTo(messageSentTo);
            setLaunchInfo.setSaveOrModify(saveOrModify);
            if(saveOrModify.equals("modify")){
                setLaunchInfo.setMessageId(messageId);
            }
            //System.out.println(messageId+"SourcePage "+saveOrModify);
            saveOrModifyLaunchInfo.saveOrModifyToolLaunchMessage(setLaunchInfo);
            saveOrModifyStatus = setLaunchInfo.getSaveOrModifyStatus();
            messageId = setLaunchInfo.getMessageId();
            sourcePage = "NewToolAndFeatureLaunchForm.jsp?saveOrModifyStatus="+saveOrModifyStatus+"&messageId="+messageId;
            response.sendRedirect(sourcePage);
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
