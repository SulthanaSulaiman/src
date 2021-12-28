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
public class FeedbackServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        String responseText = "";
        try {

                List deptName = new ArrayList();
                List deptCode = new ArrayList();
                List desigName = new ArrayList();
                List desigCode = new ArrayList();
                List empId = new ArrayList();
                List empName = new ArrayList();
                List SelEmpID = new ArrayList();

                filters.generic.DepartmentList dpt = new filters.generic.DepartmentList();
                dpt.collectDeptartment();
                deptName = dpt.getDeptName();
                deptCode = dpt.getDeptCode();

                filters.generic.DesignationList dsg = new filters.generic.DesignationList();
                dsg.collectDesignation();
                desigName = dsg.getDesigName();
                desigCode = dsg.getDesigCode();

                pathfinder.functions.FeedbackDAO feedbackDAO = new pathfinder.functions.FeedbackDAO();
                pathfinder.functions.FeedbackVO feedbackVO = new pathfinder.functions.FeedbackVO();

                javax.servlet.http.HttpSession session = request.getSession();
                //String selEmpIssue =  request.getParameter("listId1");
                if(action.equals("Add")){
                    int recordSave=0;
                    String eidSes = (String) session.getAttribute("theEid");
                    String getFeedbackParam = request.getParameter("feedback")!=null?request.getParameter("feedback"):"";
                    String getSvrParam = request.getParameter("Severity")!=null?request.getParameter("Severity"):"";
                    String getCorrParam = request.getParameter("corr_act")!=null?request.getParameter("corr_act"):"";
                    String getPreventParam = request.getParameter("prevent_act")!=null?request.getParameter("prevent_act"):"";
                    String getCorrTargetParam =  request.getParameter("corr_date")!=null? request.getParameter("corr_date"):"";
                    String getPreventTargetParam =  request.getParameter("prevent_date")!=null? request.getParameter("prevent_date"):"";
                    String issueType = request.getParameter("FeedType")!=null?request.getParameter("FeedType"):"";
                    String projid = request.getParameter("projid")!=null?request.getParameter("projid"):"";

                    SelEmpID.clear();
                    int count=0;

                    String i1= request.getParameter("count") != null ? request.getParameter("count") : "0";
                    int i2=Integer.parseInt(i1);

                    for(int j1=0;j1<i2;j1++)
                    {
                        String r=String.valueOf(j1);
                        String empID="empId"+r; //Check box
                        if(request.getParameter(empID)!=null)
                        {
                            count++;
                            String emp_id=request.getParameter(empID);
                            SelEmpID.add(emp_id);
                        }
                    }
                    pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
                    //System.out.println(issueType);
                    feedbackVO.setSelEmpID(SelEmpID);
                    //feedbackVO.setEmpIdNotify(SelEmpID);
                    feedbackVO.setIssue_type(issueType);
                    feedbackVO.setSeverityCode(getSvrParam);
                    feedbackVO.setCorrAction(splChar.encoding(getCorrParam));
                    feedbackVO.setCorrDate(getCorrTargetParam);
                    feedbackVO.setPrevAction(splChar.encoding(getPreventParam));
                    feedbackVO.setPrevDate(getPreventTargetParam);
                    feedbackVO.setFeedback(splChar.encoding(getFeedbackParam));
                    feedbackVO.setProjId(projid);
                    feedbackVO.setSesEmpId(eidSes);
                    feedbackVO =  feedbackDAO.addFeedback(feedbackVO);
                    recordSave = feedbackVO.getRecordSave();
                    if(recordSave>0){
                        responseText = "recordSave="+recordSave+"&save=save";
                    }else{
                        responseText = "recordSave="+recordSave+"&filter=filter";
                    }
                    
                } else if(action.equals("Remove")){
                    
                    int recordRemove = 0;
                    int count=0;
                    String countTotalChekbox = request.getParameter("removeIssue")!=null?request.getParameter("removeIssue"):"";
                    int checkboxcount = Integer.parseInt(countTotalChekbox);
                    
                    for(int i=0; i<checkboxcount; i++){
                         String selEmpIssue =  request.getParameter("listId"+i);
                        
                        if(selEmpIssue!=null)
                            count++;
                        //System.out.println("selEmpIssue"+selEmpIssue);
                        feedbackVO.setSelEmpIssue(selEmpIssue);
                        feedbackVO = feedbackDAO.completeFeedback(feedbackVO);
                        recordRemove += feedbackVO.getRecordRemove();
                        
                    }
                    if(recordRemove == count && recordRemove>0){
                        responseText = "recordRemove="+recordRemove+"&remove=remove";
                    }else{
                        responseText = "recordRemove="+recordRemove+"&filter=filter";
                    }
                } else if(action.equals("Update")){
                    int recordUpdate = 0;
                    String eidSes = (String) session.getAttribute("theEid");
                    String updCorrAction = request.getParameter("corrActUpdate")!=null?request.getParameter("corrActUpdate"):"";
                    String updPrevAct = request.getParameter("preventActUpdate")!=null?request.getParameter("preventActUpdate"):"";
                    String issueId = request.getParameter("updateMyFeed")!=null?request.getParameter("updateMyFeed"):"";

                    feedbackVO.setCorrAction(updCorrAction);
                    feedbackVO.setPrevAction(updPrevAct);
                    feedbackVO.setSesEmpId(eidSes);
                    feedbackVO.setMyIssueID(issueId);

                    feedbackVO = feedbackDAO.updateFeedback(feedbackVO);
                    recordUpdate = feedbackVO.getRecoredUpdate();
                    if(recordUpdate>0){
                        responseText = "recordUpdate="+recordUpdate+"&update=update";
                    }else{
                        responseText = "recordUpdate="+recordUpdate+"&filter=filter";
                    }
                } else {
                    // Do Nothing
                }
                response.sendRedirect("FeedbackForm.jsp?"+responseText);
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
