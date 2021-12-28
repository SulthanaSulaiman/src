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
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import pathfinder.util.MailerDAO;
import pathfinder.util.MailerVO;
import java.text.SimpleDateFormat;


/**
 *
 * @author pathfinder
 */
public class QuickplanServlet extends HttpServlet {
   
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
           javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");
        String getMenuFlagHidden = request.getParameter("menu_flag_hidden");
        String getEditFlagHidden = request.getParameter("addEdit");
        pathfinder.functions.projects.chaptersold.ChapterPlanDetVO planVO = null;
        pathfinder.functions.projects.chaptersold.ChapterPlanDetails savePlan = new pathfinder.functions.projects.chaptersold.ChapterPlanDetails();
        MailerDAO mailDAO = new MailerDAO();
        MailerVO mailVO = new MailerVO();

        String functionCall = request.getParameter("functoinCall");//

        String projectName = request.getParameter("project_name");
        String projectId = request.getParameter("project_name_hidden");
        String stage = request.getParameter("stageTest");
        String stageName =  request.getParameter("displayStage");
        String empId= request.getParameter("empId")==null?"":request.getParameter("empId");
        List rqstList = new ArrayList();
        List addrqstList = new ArrayList();
        List recipientList = new ArrayList();
        int intAddRes = 0;
         if (functionCall.equals("addChapter")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date today = new Date();
                String dateWithoutTime = dateFormat.format(today);

                int bookMapCount = Integer.parseInt(request.getParameter("bookMapCount"));
                int mileStoneCount = Integer.parseInt(request.getParameter("mileStoneCount"));
                int miscMapCount = Integer.parseInt(request.getParameter("misccount"));
                int chapterCount = 0;
                String todayChap = "";
                for (int i = 0; i < bookMapCount && bookMapCount != -1; i++) {
                    String s = request.getParameter("chapterCheck" + i) == null?"":request.getParameter("chapterCheck"+i);
                    if(s.equals("1")){
                        chapterCount++;
                    List mileList = new ArrayList();
                    List mileendList = new ArrayList();
                    List autoFillFlagList = new ArrayList();
                    if(request.getParameter("bookMileCount" + i) != null)
                        mileStoneCount = Integer.parseInt(request.getParameter("bookMileCount"+i));
                    else
                        mileStoneCount = 0;
                    planVO = new pathfinder.functions.projects.chaptersold.ChapterPlanDetVO();
                    planVO.setProjectId(projectId);
                    planVO.setChapterStgCode(stage);
                    planVO.setChapterName(request.getParameter("chapterBookMap" + i));
                    planVO.setMssCount(request.getParameter("mssBookMap" + i));
                    if (stage.equals("ART")||stage.equals("ARTCXN")){
                    planVO.setArtCount(request.getParameter("artBookMap" + i));
                    }
                    //artBookMap
                    planVO.setProcess(request.getParameter("statusBookMap" + i));
                    planVO.setRemark(request.getParameter("remarkBookMap" + i));
                    //planVO.setEndDate(request.getParameter("ch" + i + "Milestoneend" + (mileStoneCount - 1)));
                    for (int j = 0; j < mileStoneCount; j++) {
                        if(!request.getParameter("ch" + i + "Milestone" + j).equals("-1")){
                            autoFillFlagList.add(request.getParameter("autoFillFlagAdd"+j));
                            mileList.add(request.getParameter("ch" + i + "Milestone" + j));
                            mileendList.add(request.getParameter("ch" + i + "Milestoneend" + j));
                            if(request.getParameter("ch" + i + "Milestone" + j).equals("21")){
                                planVO.setEndDate(request.getParameter("ch" + i + "Milestoneend" + j));
                                if(request.getParameter("ch" + i + "Milestoneend" + j).equals(dateWithoutTime)){
                                    if(todayChap.equals("")){
                                        todayChap=request.getParameter("chapterBookMap" + i);
                                    }else{
                                        todayChap=todayChap+", "+request.getParameter("chapterBookMap" + i);
                                    }
                            }
                        }
                    }
                    }
                    planVO.setMileStoneBook(mileList);
                    planVO.setMileStoneBookend(mileendList);
                    planVO.setAutoFillFlag(autoFillFlagList);
                    rqstList.add(planVO);
                    }
                }
                /*
                mailVO.setChapters(todayChap);
                mailVO.setProjId(projectId);
                mailVO.setProjName(projectName);
                mailVO.setStageName(stageName);

                recipientList = mailDAO.getRecipientList(mailVO);
                if(!todayChap.equals("") && recipientList.size()>0){
                    mailDAO.sendReportMail(mailVO);
                }*/

                todayChap = "";
                recipientList.clear();
                for (int i = 0; i <= miscMapCount && miscMapCount != -1; i++) {
                    List mileendList = new ArrayList();
                    List mileList = new ArrayList();
                    List autoFillFlagList = new ArrayList();
                    if(request.getParameter("miscMileCount" + i) != null)
                        mileStoneCount = Integer.parseInt(request.getParameter("miscMileCount"+i));
                    else
                        mileStoneCount = 0;
                    planVO = new pathfinder.functions.projects.chaptersold.ChapterPlanDetVO();
                    planVO.setProjectId(projectId);
                    planVO.setChapterStgCode(stage);
                    planVO.setChapterName(request.getParameter("miscchap" + i));
                    chapterCount++;
                    planVO.setMssCount(request.getParameter("miscmsscount" + i));
                    if (stage.equals("ART")||stage.equals("ARTCXN")){
                    planVO.setArtCount(request.getParameter("miscartcount" + i));
                    }
                    planVO.setProcess(request.getParameter("miscprocess" + i));
                    planVO.setRemark(request.getParameter("miscremark" + i));
                    //planVO.setEndDate(request.getParameter("misc" + i + "Milestoneend" + (mileStoneCount - 1)));
                    for (int j = 0; j < mileStoneCount; j++) {
                        if(!request.getParameter("misc" + i + "Milestone" + j).equals("-1")){
                            autoFillFlagList.add(request.getParameter("misc"+i+"Milestone"+j+"auto"));
                            mileList.add(request.getParameter("misc" + i + "Milestone" + j));
                            mileendList.add(request.getParameter("misc" + i + "Milestoneend" + j));
                            if(request.getParameter("misc" + i + "Milestone" + j).equals("21")){
                                planVO.setEndDate(request.getParameter("misc" + i + "Milestoneend" + j));
                                if(request.getParameter("misc" + i + "Milestoneend" + j).equals(dateWithoutTime)){
                                    if(todayChap.equals("")){
                                        todayChap=request.getParameter("miscchap" + i);
                                    }else{
                                        todayChap=todayChap+", "+request.getParameter("miscchap" + i);
                                    }
                            }
                        }
                    }
                    }
                    planVO.setMileStoneBook(mileList);
                    planVO.setMileStoneBookend(mileendList);
                    planVO.setAutoFillFlag(autoFillFlagList);
                    rqstList.add(planVO);
                }
                /*
                mailVO.setChapters(todayChap);
                mailVO.setProjId(projectId);
                mailVO.setProjName(projectName);
                mailVO.setStageName(stageName);

                recipientList = mailDAO.getRecipientList(mailVO);
                if(!todayChap.equals("") && recipientList.size()>0){
                    mailDAO.sendReportMail(mailVO);
                }*/
                List chapterNotInserted = savePlan.savePlan(rqstList,empId);
                if(chapterNotInserted.size()>0){
                    String temp = (chapterCount - chapterNotInserted.size())+"";
                    if(chapterCount - chapterNotInserted.size()==0)
                        temp ="No";
                    response.sendRedirect("ChapterSchedulePlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden + "&errorFlag="+temp+" chapters are inserted and the followings are not inserted " + chapterNotInserted+".");
                }
                else
                    response.sendRedirect("ChapterSchedulePlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden + "&successFlag="+chapterCount+" chapters are inserted successfully.");

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
