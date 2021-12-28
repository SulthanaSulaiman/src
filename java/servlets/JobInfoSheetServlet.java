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
import javax.servlet.http.HttpSession;
import pathfinder.functions.projects.*;

/**
 *
 * @author Aravindhanj
 */
public class JobInfoSheetServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int result = 0;
            String responseText = "";
            pathfinder.functions.projects.JobInfoSheetDAO jobInfoSheetDAO = new pathfinder.functions.projects.JobInfoSheetDAO();
            pathfinder.functions.projects.JobInfoSheetVO jobInfoSheetVO = new pathfinder.functions.projects.JobInfoSheetVO();
            
            HttpSession session = request.getSession();
            String getEmpId = (String) session.getAttribute("theEid");

            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            String projId = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
            String projName = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";

            if(action.equals("Save")) {
                String jobInfoId = request.getParameter("jobInfoId") != null ? request.getParameter("jobInfoId") : "";
                String platform = request.getParameter("platform") != null ? request.getParameter("platform") : "";;
                String linesPerPage = request.getParameter("linesPerPage") != null ? request.getParameter("linesPerPage") : "";;
                String columnsPerPage = request.getParameter("columnsPerPage") != null ? request.getParameter("columnsPerPage") : "";;
                String typeface = request.getParameter("typeface") != null ? request.getParameter("typeface") : "";;
                String sTypeFace = request.getParameter("sTypeFace") != null ? request.getParameter("sTypeFace") : "";;
                String pointSize = request.getParameter("pointSize") != null ? request.getParameter("pointSize") : "";;
                String typeOfDesign = request.getParameter("typeOfDesign") != null ? request.getParameter("typeOfDesign") : "";;
                String slAllowed = request.getParameter("slAllowed") != null ? request.getParameter("slAllowed") : "";;
                String partStart = request.getParameter("partStart") != null ? request.getParameter("partStart") : "";;
                String sectionStart = request.getParameter("sectionStart") != null ? request.getParameter("sectionStart") : "";;
                String chapterStart = request.getParameter("chapterStart") != null ? request.getParameter("chapterStart") : "";;
                String depthAlignment = request.getParameter("depthAlignment") != null ? request.getParameter("depthAlignment") : "";;
                String rhStyle = request.getParameter("rhStyle") != null ? request.getParameter("rhStyle") : "";;
                String blankAllowed = request.getParameter("blankAllowed") != null ? request.getParameter("blankAllowed") : "";;
                String namingConv = request.getParameter("namingConv") != null ? request.getParameter("namingConv") : "";;
                String coFolio = request.getParameter("coFolio") != null ? request.getParameter("coFolio") : "";;
                String designApprovedAt = request.getParameter("designApprovedAt") != null ? request.getParameter("designApprovedAt") : "";;
                String estHours = request.getParameter("estHours") != null ? request.getParameter("estHours") : "";;

                jobInfoSheetVO.setProjId(projId);
                jobInfoSheetVO.setPlatform(platform);
                jobInfoSheetVO.setLinesPerPage(linesPerPage);
                jobInfoSheetVO.setColumnsPerPage(columnsPerPage);
                jobInfoSheetVO.setTypeface(typeface);
                jobInfoSheetVO.setsTypeFace(sTypeFace);
                jobInfoSheetVO.setPointSize(pointSize);
                jobInfoSheetVO.setTypeOfDesign(typeOfDesign);
                jobInfoSheetVO.setSlAllowed(slAllowed);
                jobInfoSheetVO.setPartStart(partStart);
                jobInfoSheetVO.setSectionStart(sectionStart);
                jobInfoSheetVO.setChapterStart(chapterStart);
                jobInfoSheetVO.setDepthAlignment(depthAlignment);
                jobInfoSheetVO.setRhStyle(rhStyle);
                jobInfoSheetVO.setBlankAllowed(blankAllowed);
                jobInfoSheetVO.setNamingConv(namingConv);
                jobInfoSheetVO.setCoFolio(coFolio);
                jobInfoSheetVO.setDesignApprovedAt(designApprovedAt);
                jobInfoSheetVO.setEstHours(estHours);

                if(jobInfoId.contains("")) {
                    jobInfoSheetVO = jobInfoSheetDAO.insertJISDetails(jobInfoSheetVO);
                } else {
                    jobInfoSheetVO = jobInfoSheetDAO.updateJISDetails(jobInfoSheetVO);
                }
                result = jobInfoSheetVO.getResult();

                if(result == 1) {
                    responseText = "project_name_hidden="+projId+"&project_name="+projName+"&result=1";
                } else {
                    responseText = "project_name_hidden="+projId+"&project_name="+projName+"&result=0";
                }
                response.sendRedirect("JobInfoSheet.jsp?"+responseText);
            } else if (action.equals("SaveNote")) {
                String jisGroupId = request.getParameter("jisGroupId") != null ? request.getParameter("jisGroupId") : "";
                String noteContent = request.getParameter("noteContent") != null ? request.getParameter("noteContent") : "";
                jobInfoSheetVO.setEmpId(getEmpId);
                jobInfoSheetVO.setProjId(projId);
                jobInfoSheetVO.setJisGroupId(jisGroupId);
                jobInfoSheetVO.setNoteContent(noteContent);

                jobInfoSheetVO = jobInfoSheetDAO.insertJISNote(jobInfoSheetVO);
                result = jobInfoSheetVO.getResult();

                if(result == 1) {
                    responseText = "project_name_hidden="+projId+"&project_name="+projName+"&result=1";
                } else {
                    responseText = "project_name_hidden="+projId+"&project_name="+projName+"&result=0";
                }
                response.sendRedirect("JobInfoSheet.jsp?"+responseText);
            } else if (action.equals("UpdateNote")) {
                String noteCount = request.getParameter("noteCount") != null ? request.getParameter("noteCount") : "0";
                String noteId = "";
                String jisNoteGroupId = "";
                String jisNoteContent = "";
                List noteIdList = new ArrayList();
                List noteGroupIdList = new ArrayList();
                List noteContentList = new ArrayList();
                for(int i=0; i < Integer.parseInt(noteCount); i++) {
                    noteId = request.getParameter("noteId"+i) != null ? request.getParameter("noteId"+i) : "";
                    jisNoteGroupId = request.getParameter("jisNoteGroupId"+i) != null ? request.getParameter("jisNoteGroupId"+i) : "";
                    jisNoteContent = request.getParameter("noteContent"+i) != null ? request.getParameter("noteContent"+i) : "";
                    noteIdList.add(noteId);
                    noteGroupIdList.add(jisNoteGroupId);
                    noteContentList.add(jisNoteContent);
                }
                jobInfoSheetVO.setEmpId(getEmpId);
                jobInfoSheetVO.setProjId(projId);
                jobInfoSheetVO.setNoteIdList(noteIdList);
                jobInfoSheetVO.setNoteGroupIdList(noteGroupIdList);
                jobInfoSheetVO.setNoteContentList(noteContentList);
                jobInfoSheetVO = jobInfoSheetDAO.updateJISNote(jobInfoSheetVO);

//                System.out.println(noteIdList);
//                System.out.println(noteGroupIdList);
//                System.out.println(noteContentList);

                result = jobInfoSheetVO.getResult();

                if(result == 1) {
                    responseText = "project_name_hidden="+projId+"&project_name="+projName+"&result=1";
                } else {
                    responseText = "project_name_hidden="+projId+"&project_name="+projName+"&result=0";
                }
                response.sendRedirect("JobInfoSheet.jsp?"+responseText);
            }
        } finally { 
            out.close();
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
