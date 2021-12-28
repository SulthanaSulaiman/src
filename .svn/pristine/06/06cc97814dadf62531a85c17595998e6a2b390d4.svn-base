/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.util.MailerDAO;
import pathfinder.util.MailerVO;

/**
 *
 * @author thanuja
 */
public class CreateChapterPlanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        try {
            if (functionCall.equals("saveMileStone")) {
                int saveMileStoneCount = Integer.parseInt(request.getParameter("saveMileStoneCount"));
                int tempId = Integer.parseInt(request.getParameter("chapId"));
                for (int i = 1; i < saveMileStoneCount; i++) {
                    planVO = new pathfinder.functions.projects.chaptersold.ChapterPlanDetVO();
                    planVO.setChapterId(request.getParameter("editChapId" + tempId));
                    planVO.setPlanId(request.getParameter("editPlanId" + tempId) != null ? request.getParameter("editPlanId" + tempId) : "");
                    planVO.setMileStoneId(request.getParameter("mileStoneName" + i));
                    planVO.setPlannedDate(request.getParameter("mileStoneEndDate" + i));
                    rqstList.add(planVO);
                }
                intAddRes = savePlan.saveMileStone(rqstList);
                response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden);
            }
            if (functionCall.equals("deleteChapter")) {
                planVO = new pathfinder.functions.projects.chaptersold.ChapterPlanDetVO();
                planVO.setChapterId(request.getParameter("deletechapid"));
                intAddRes = savePlan.deletePlan(planVO);
                response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden);
                
            }
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
                    response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden + "&errorFlag="+temp+" chapters are inserted and the followings are not inserted " + chapterNotInserted+".");
                }
                else
                    response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden + "&successFlag="+chapterCount+" chapters are inserted successfully.");
            }
            if ((functionCall).equals("deleteMileStone")) {
                /*int bookMapCount = Integer.parseInt(request.getParameter("chapId"));
                int mileStoneCount = Integer.parseInt(request.getParameter("mileCount"));
                 Above two statements used for delete individual milestone of a chapter*/
                int chapDel = Integer.parseInt(request.getParameter("chapDelCall"));
                int chapDelt=0;
                for(int k=1;k<=chapDel;k++)
                {
                    chapDelt=Integer.parseInt(request.getParameter("mileCount"+k));
                
                for (int j = 1; j <= chapDelt; j++) {
                    planVO = new pathfinder.functions.projects.chaptersold.ChapterPlanDetVO();
                    planVO.setPlanId(request.getParameter("edit" + k + "milestonecodedel" + j));
                    rqstList.add(planVO);
                }
                }
                intAddRes = savePlan.deletePlanMileStone(rqstList);
                response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden);
            }
            if((functionCall).equals("deleteChapters")){
                int chapterCount = Integer.parseInt(request.getParameter("chapterCount"));
                List chapterDelete = new ArrayList();
                for(int i=0;i<chapterCount;i++){
                    if(request.getParameter("chapterId"+i)!=null)
                    chapterDelete.add(request.getParameter("chapterId"+i));
                }
                int result = savePlan.deleteChapters(chapterDelete,empId);
                if(result==1)
                    response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden+"&successFlag=Successfully Deleted");
                else
                    response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden+"&errorFlag=Unable to Delete");
            }
            if((functionCall).equals("generateBatch")){
                int chapterCount = Integer.parseInt(request.getParameter("chapterCount"));
                List chapterIdList = new ArrayList();
                List chapterNameList = new ArrayList();
                List chapterDueDateList = new ArrayList();
                List chapterNotesFlagList = new ArrayList();
                List chapterMssFlagList = new ArrayList();
                List chapterArtCountList = new ArrayList();
                for(int i=0;i<chapterCount;i++){
                    if(request.getParameter("chapterId"+i)!=null) {
                        
                        chapterIdList.add(request.getParameter("chapterId"+i));

                        if(request.getParameter("chapterName"+i)!=null) {
                            chapterNameList.add(request.getParameter("chapterName"+i));
                        } else {
                            chapterNameList.add("");
                        }
                        if(request.getParameter("chapterDueDate"+i)!=null) {
                            chapterDueDateList.add(request.getParameter("chapterDueDate"+i));
                        } else {
                            chapterDueDateList.add("");
                        }
                        if(request.getParameter("chapterNotesFlag"+i)!=null) {
                            chapterNotesFlagList.add(request.getParameter("chapterNotesFlag"+i));
                        }
                        //chapterMssPage
                        if(request.getParameter("chapterMssPage"+i)!=null) {
                           
                            chapterMssFlagList.add(request.getParameter("chapterMssPage"+i));
                        }
                        else {
                           
                            chapterNotesFlagList.add("");
                        }
                        //chapterArtCountList
                        if(request.getParameter("chapterartcount"+i)!=null) {
                            //System.out.println("chapterArtCountList"+request.getParameter("chapterArtCountList"+i));
                            chapterArtCountList.add(request.getParameter("chapterartcount"+i));
                        }
                        else {
                            
                            chapterArtCountList.add("");
                        }
                    }
                }
                mailVO.setProjId(projectId);
                mailVO.setProjName(projectName);
                mailVO.setEmpId(empId);
                mailVO.setStageName(request.getParameter("displayStage") != null ? request.getParameter("displayStage").toString() : "");
                mailVO.setProjDeptCode(request.getParameter("projDept") != null ? request.getParameter("projDept").toString() : "");
                mailVO.setChapterIdList(chapterIdList);
                mailVO.setChapterNameList(chapterNameList);
                mailVO.setChapterDueDateList(chapterDueDateList);
                mailVO.setChapterNotesFlagList(chapterNotesFlagList);
                mailVO.setMssPageIdList(chapterMssFlagList);
                mailVO.setStage(stage);
                mailVO.setArtCountList(chapterArtCountList);
                int result = mailDAO.sendBatchTicketMail(mailVO);
                List recipientSentList = new ArrayList();
                recipientSentList = mailVO.getRecipientList();
                String recipientStr = recipientSentList.toString();
                if(result==1 && recipientSentList.size() > 0) {
                    response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden+"&successFlag=Ticket successfully Generated<br>"+recipientStr);
                } else {
                    if(recipientSentList.isEmpty()) {
                        response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden+"&errorFlag=Unable to Generate the Batch Ticket. No Recipient/s Found");
                    } else {
                        response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden+"&errorFlag=Unable to Generate the Batch Ticket");
                    }
                }
            }
            if ((functionCall).equals("saveorder")) {
                int bookMapCount = Integer.parseInt(request.getParameter("chapter_count"));
                int mileStoneCount = Integer.parseInt(request.getParameter("milestone_count"));
                for (int j = 0; j < mileStoneCount; j++) {
                    planVO = new pathfinder.functions.projects.chaptersold.ChapterPlanDetVO();
                    planVO.setPlanId(request.getParameter(bookMapCount + "orderplanId" + j));
                    planVO.setChapterOrderId(request.getParameter(bookMapCount + "chapIdOrder" + j));
                    rqstList.add(planVO);
                }
                intAddRes = savePlan.orderMilestone(rqstList);
                if(intAddRes==1)
                    response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden + "&successFlag=Milestone Order Saved");
                else
                    response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden + "&errorFlag=Milestone Order Not Saved");
            }
            if (functionCall.equals("updateChapter")) {
                int bookMapCount = Integer.parseInt(request.getParameter("bookMapCount"));
                String todayChap = "";
                for (int i = 1; i <= bookMapCount && bookMapCount != -1; i++) {
                    String chkboxUpdate = request.getParameter("chapSelect"+i)!=null?request.getParameter("chapSelect"+i):"";
                    if(!chkboxUpdate.equals("")){
                        String milecount = request.getParameter("mileCount" + i);
                        String addmilecount = request.getParameter("addmileCount" + i);
                        int mileStoneCount = 0;
                        int addmileStoneCount = -1;
                        if (milecount != null) {
                            mileStoneCount = Integer.parseInt(request.getParameter("mileCount" + i));
                        }
                        if (addmilecount != null) {
                            addmileStoneCount = Integer.parseInt(request.getParameter("addmileCount" + i));
                        }

                        List mileendList = new ArrayList();
                        List mileList = new ArrayList();
                        List editAutoFill = new ArrayList();
                        List updateAutoFill =  new ArrayList();
                        List addAutoFill = new ArrayList();
                        planVO = new pathfinder.functions.projects.chaptersold.ChapterPlanDetVO();
                        planVO.setProjectId(projectId);
                        planVO.setChapterStgCode(stage);
                        planVO.setChapterId(request.getParameter("editChapId" + i));
                        planVO.setChapterName(request.getParameter("editchap" + i));
                        planVO.setMssCount(request.getParameter("editmsscount" + i));
                        planVO.setArtCount(request.getParameter("editartcount" + i));
                        planVO.setProcess(request.getParameter("editstatus" + i));
                        planVO.setRemark(request.getParameter("editremark" + i));
                        
                        savePlan.getAutoFillFlag(planVO);
                        addAutoFill = planVO.getAutoFillFlag();
                        
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date today = new Date();
                        String dateWithoutTime = dateFormat.format(today);
                        //planVO.setEndDate(request.getParameter("edit" + i + "mile" + mileStoneCount));
                        for (int j = 1; j <= mileStoneCount; j++) {
                            if(!request.getParameter("edit" + i + "milestonecode" + j).equals("-1")){
                            if (request.getParameter("edit" + i + "milestoneactcode" + j).equals("21")) { //FTP Operator Date
                                planVO.setEndDate(request.getParameter("edit" + i + "mile" + j));

                                String chkboxMilestoneUpdate = request.getParameter("edit" + i + "milestonecodedel" + j)!=null?request.getParameter("edit" + i + "milestonecodedel" + j):"";
                                if(!chkboxMilestoneUpdate.equals("")){    // check the selected chapter is updated in FTP Milestone
                                    if(request.getParameter("edit" + i + "mile" + j).equals(dateWithoutTime)){ //Updating FTP today
                                        if(todayChap.equals("")){
                                            todayChap=request.getParameter("editchap" + i);
                                        }else{
                                            todayChap=todayChap+", "+request.getParameter("editchap" + i);
                                        }
                                }
                            }
                            }
                            String temp = request.getParameter("edit" + i + "milestonecode" + j) != null ? request.getParameter("edit" + i + "milestonecode" + j) : "";
                            if (!temp.equals("")) {
                                mileList.add(request.getParameter("edit" + i + "milestonecode" + j));
                                mileendList.add(request.getParameter("edit" + i + "mile" + j));
                                editAutoFill.add(request.getParameter("edit"+i+"ManualFill"+j));
                            }
                            }
                        }
                        for(int idx=0;idx<editAutoFill.size();idx++){
                            if(editAutoFill.get(idx).equals("0") || addAutoFill.get(idx).equals("0")){
                                updateAutoFill.add("0");
                            }else{
                                updateAutoFill.add("1");
                            }
                        }
                        
                        planVO.setMileStoneBook(mileList);
                        planVO.setMileStoneBookend(mileendList);
                        planVO.setAutoFillFlag(updateAutoFill);
                        rqstList.add(planVO);
                        //To Save Add more milestone on updation
                        for(int j=0;j<addmileStoneCount;j++){
                            if(!request.getParameter("add" + i + "milename" + j).equals("-1")){
                                if(request.getParameter("add"+i+"milename"+j).equals("21")){
                                    if(request.getParameter("add"+i+"enddate"+j).equals(dateWithoutTime)){
                                        if(todayChap.equals("")){
                                            todayChap=request.getParameter("editchap" + i);
                                        }else{
                                            todayChap=todayChap+", "+request.getParameter("editchap" + i);
                                        }
                                    }
                                }
                                planVO = new pathfinder.functions.projects.chaptersold.ChapterPlanDetVO();
                                planVO.setChapterId(request.getParameter("editChapId" + i));
                                planVO.setMileStoneId(request.getParameter("add" + i + "milename" + j));
                                planVO.setPlannedDate(request.getParameter("add" + i + "enddate" + j));
                                addrqstList.add(planVO);
                            }
                        }
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
                    
                    if (bookMapCount != -1) {
                        intAddRes = savePlan.editPlan(rqstList,empId);
                        if(intAddRes==1)
                        intAddRes = savePlan.saveMileStone(addrqstList);
                    }
                    if(intAddRes == 1){
                        response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden + "&successFlag=Chapters Updated Successfully");
                    } else {
                        response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden + "&errorFlag=Chapters Not Updated Properly");
                    }
                   
            }
            //response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden);
        } catch (Exception e) {
            System.out.print("Exception" + e.toString());
            response.sendRedirect("CreateChapterPlan.jsp?project_name=" + projectName + "&project_name_hidden=" + projectId + "&stageTest=" + stage + "&menu_flag_hidden=" + getMenuFlagHidden + "&addEdit=" + getEditFlagHidden);
        }
    }
}
