package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pathfinder.functions.projects.AddTimeSheetDAO;
import pathfinder.functions.projects.AddTimeSheetVO;
import java.util.ArrayList;

/**
 *
 * @author Aravindhanj
 */
public class AddEmpTimeSheetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AddTimeSheetDAO addTimeSheetDAO = new AddTimeSheetDAO();
        AddTimeSheetVO addTimeSheetVO = new AddTimeSheetVO();

        String getCheck = "";
        String getCurrentEmpId = "";
        String getEmpId = "";
        String getProjId = "";
        String getStageChapter = "";
        String getMilestoneId = "";
        String getActivityCode = "";
        String getStartTime = "";
        String getStartHourMin = "";
        String getEndTime = "";
        String getEndHourMin = "";
        String getPageCount = "";
        String getChargeableStatus = "";
        String getErrorStatus = "";
        String getCompletionStatus = "";
        int getResult = 0;
        
        String getChapterId = "";
        String getStageCode = "";

        String[] keySplit;

        ArrayList empId = new ArrayList();
        ArrayList projId = new ArrayList();
        ArrayList chapterId = new ArrayList();
        ArrayList stageCode = new ArrayList();
        ArrayList milestoneId = new ArrayList();
        ArrayList activityCode = new ArrayList();
        ArrayList startTime = new ArrayList();
        ArrayList endTime = new ArrayList();
        ArrayList completedStatus = new ArrayList();
        ArrayList chargeableStatus = new ArrayList();
        ArrayList errorStatus = new ArrayList();
        ArrayList pageCount = new ArrayList();
        String count = request.getParameter("rowCount") != null? request.getParameter("rowCount") : "0";
        String responseText = "";
        HttpSession session = request.getSession();
        getCurrentEmpId = (String) session.getAttribute("theEid");
        addTimeSheetVO.setCurrentEmpId(getCurrentEmpId);

        if(request.getParameter("save").equals("save")) {
            for(int index = 0; index < Integer.parseInt(count); index++) {
                getCheck = request.getParameter("check"+index) != null? request.getParameter("check"+index) : "";
                if(getCheck == "1" || getCheck.equals("1")) {
                    getEmpId = request.getParameter("emp_name"+index+"_hidden") != null? request.getParameter("emp_name"+index+"_hidden") : "";
                    keySplit = getEmpId.split("@@@");
                    getEmpId = keySplit[0];

                    getProjId = request.getParameter("project_name"+index+"_hidden") != null? request.getParameter("project_name"+index+"_hidden") : "";

                    getStageChapter = request.getParameter("stageChapterSelect"+index)!=null? request.getParameter("stageChapterSelect"+index) : "";
                    keySplit = getStageChapter.split("@@@");
                    getChapterId = keySplit[0];
                    getStageCode = keySplit[1];

                    getMilestoneId = request.getParameter("milestoneSelect"+index)!=null?request.getParameter("milestoneSelect"+index):"";
                    getActivityCode = request.getParameter("activitySelect"+index)!=null?request.getParameter("activitySelect"+index):"";

                    getStartTime = request.getParameter("startDate"+index)!=null?request.getParameter("startDate"+index):"";
                    getStartHourMin = request.getParameter("startTime"+index)!=null?request.getParameter("startTime"+index):"";
                    getStartTime = getStartTime + " " + getStartHourMin;

                    getEndTime = request.getParameter("endDate"+index)!=null?request.getParameter("endDate"+index):"";
                    getEndHourMin = request.getParameter("endTime"+index)!=null?request.getParameter("endTime"+index):"";
                    getEndTime = getEndTime + " " + getEndHourMin;

                    getPageCount = request.getParameter("pageCount"+index)!=null?request.getParameter("pageCount"+index):"";
                    getChargeableStatus = request.getParameter("chargeStatus"+index)!=null?request.getParameter("chargeStatus"+index):"";
                    if(getChargeableStatus.contains("0")) {
                        getErrorStatus = request.getParameter("nonChargeErr"+index)!=null?request.getParameter("nonChargeErr"+index):"";
                    }

                    getCompletionStatus = request.getParameter("completeStatus"+index)!=null?request.getParameter("completeStatus"+index):"";

                    empId.add(getEmpId);
                    projId.add(projId);
                    stageCode.add(getStageCode);
                    chapterId.add(getChapterId);
                    milestoneId.add(getMilestoneId);
                    activityCode.add(getActivityCode);
                    startTime.add(getStartTime);
                    endTime.add(getEndTime);
                    completedStatus.add(getCompletionStatus);
                    chargeableStatus.add(getChargeableStatus);
                    errorStatus.add(getErrorStatus);
                    pageCount.add(getPageCount);
                }
            }
            addTimeSheetVO.setEmpId(empId);
            addTimeSheetVO.setProjId(projId);
            addTimeSheetVO.setStageCode(stageCode);
            addTimeSheetVO.setChapterId(chapterId);
            addTimeSheetVO.setMilestoneId(milestoneId);
            addTimeSheetVO.setActivityCode(activityCode);
            addTimeSheetVO.setStartTime(startTime);
            addTimeSheetVO.setEndTime(endTime);
            addTimeSheetVO.setCompletedStatus(completedStatus);
            addTimeSheetVO.setChargeableStatus(chargeableStatus);
            addTimeSheetVO.setErrorStatus(errorStatus);
            addTimeSheetVO.setPageCount(pageCount);
            if(empId.size() > 0) {
                addTimeSheetDAO.SaveEmpTimeSheet(addTimeSheetVO);
                getResult = addTimeSheetDAO.getResult();
            }
        }
        responseText = "result="+getResult;
        response.sendRedirect("AddEmpTimeTrackSheet.jsp?"+responseText);

        PrintWriter out = response.getWriter();
        out.write(responseText.trim());
    }
}
