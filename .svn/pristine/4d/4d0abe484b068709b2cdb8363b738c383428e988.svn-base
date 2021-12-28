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
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Raghuramanm
 */
public class FollowLoadServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        javax.servlet.http.HttpSession session = request.getSession();

        int saveVar=0;
        int updateVar=0;
        int removeVar=0;
        String responseText = "";
        String eidSes = (String) session.getAttribute("theEid");
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String getStageParam="";
        String fromDateParam="";
        String toDateParam="";
        String inputPageCountParam="";
        String getIdParam="";
        String getStartDateUpdate="";
        String getEndDateUpdate="";
        String getInputPageUpdate="";
        String checkBoxUpdate = "";
        String checkBoxAdd = "";
        
        List scheduleIdUpdate = new ArrayList();
        List startDateUpdate = new ArrayList();
        List endDateUpdate = new ArrayList();
        List inputPageUpdate = new ArrayList();
        ArrayList projStageList = new ArrayList();
        ArrayList fromDateList = new ArrayList();
        ArrayList toDateList = new ArrayList();
        ArrayList inputPageList = new ArrayList();
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        String count = request.getParameter("rowCount") != null ? request.getParameter("rowCount") : "";
        String noOfSchedule = request.getParameter("noOfSchedule") != null ? request.getParameter("noOfSchedule") : "";

        try {

            pathfinder.functions.projects.FollowLoadDAO followLoadDAO = new pathfinder.functions.projects.FollowLoadDAO();
            pathfinder.functions.projects.FollowLoadVO followLoadVO = new pathfinder.functions.projects.FollowLoadVO();
            
            if(action.equals("add")){

                for(int index = 0; index < Integer.parseInt(count); index++) {
                    getStageParam = request.getParameter("stageSelect"+index) != null ? request.getParameter("stageSelect"+index) : "";
                    fromDateParam = request.getParameter("startDate"+index) != null ? request.getParameter("startDate"+index) : "";
                    toDateParam = request.getParameter("endDate"+index) != null ? request.getParameter("endDate"+index) : "";
                    inputPageCountParam = request.getParameter("inputPage"+index) != null ? request.getParameter("inputPage"+index) : "";
                    checkBoxAdd = request.getParameter("check"+index) != null ? request.getParameter("check"+index) : "0";

                    if(checkBoxAdd.equals("1")){ //add only the checked row
                        projStageList.add(getStageParam);
                        fromDateList.add(fromDateParam);
                        toDateList.add(toDateParam);
                        inputPageList.add(inputPageCountParam);
                    }
                }

                if(!getStageParam.equals("")){  //add only the stage selected row
                    followLoadVO.setSesEmpId(eidSes);
                    followLoadVO.setProjId(getProjIdParam);
                    followLoadVO.setProjStagesList(projStageList);
                    followLoadVO.setFromDateList(fromDateList);
                    followLoadVO.setToDateList(toDateList);
                    followLoadVO.setInputPageList(inputPageList);
                    followLoadVO = followLoadDAO.addFollowLoad(followLoadVO);
                    saveVar = followLoadVO.getSaveVar();
                }
                for(int idx = 0; idx < Integer.parseInt(noOfSchedule); idx++){
                    getIdParam = request.getParameter("scheduleId"+idx) != null ? request.getParameter("scheduleId"+idx) : "";
                    getStartDateUpdate = request.getParameter("startDateUpdate"+idx) != null ? request.getParameter("startDateUpdate"+idx) : "";
                    getEndDateUpdate = request.getParameter("endDateUpdate"+idx) != null ? request.getParameter("endDateUpdate"+idx) : "";
                    getInputPageUpdate = request.getParameter("inputPagesUpdate"+idx) != null ? request.getParameter("inputPagesUpdate"+idx) : "";
                    checkBoxUpdate = request.getParameter("selectStage"+idx) != null ? request.getParameter("selectStage"+idx) : "0";
                    System.out.println("update checkbox "+checkBoxUpdate);
                    if(checkBoxUpdate.equals("1")){ //update only the checked row
                        scheduleIdUpdate.add(getIdParam);
                        startDateUpdate.add(getStartDateUpdate);
                        endDateUpdate.add(getEndDateUpdate);
                        inputPageUpdate.add(getInputPageUpdate);
                    }
                }

                followLoadVO.setSesEmpId(eidSes);
                followLoadVO.setScheduleIdUpdate(scheduleIdUpdate);
                followLoadVO.setStartDateUpdate(startDateUpdate);
                followLoadVO.setEndDateUpdate(endDateUpdate);
                followLoadVO.setInputPageUpdate(inputPageUpdate);
                followLoadVO = followLoadDAO.updateFollowLoad(followLoadVO);
                updateVar = followLoadVO.getUpdateVar();

                if(saveVar>0 || updateVar>0){
                    responseText = "project_name_hidden="+getProjIdParam+"&scheduleSave=1";
                }else{
                    responseText = "project_name_hidden="+getProjIdParam+"&scheduleSave=0";
                }
                }else if(action.equals("remove")){
                for(int idx = 0; idx < Integer.parseInt(noOfSchedule); idx++){
                    getIdParam = request.getParameter("scheduleId"+idx) != null ? request.getParameter("scheduleId"+idx) : "";
                    checkBoxUpdate = request.getParameter("selectStage"+idx) != null ? request.getParameter("selectStage"+idx) : "";
                    if(checkBoxUpdate.equals("1")){ //update only the checked row
                        scheduleIdUpdate.add(getIdParam);
                    }

                }

                followLoadVO.setScheduleIdUpdate(scheduleIdUpdate);
                followLoadVO = followLoadDAO.removeFollowLoad(followLoadVO);
                removeVar = followLoadVO.getRemoveVar();

                if(removeVar>0){
                    responseText = "project_name_hidden="+getProjIdParam+"&scheduleRemove=1";
                }else{
                    responseText = "project_name_hidden="+getProjIdParam+"&scheduleRemove=0";
                }
            }

            response.sendRedirect("FollowLoad.jsp?"+responseText);
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
