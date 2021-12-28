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

/**
 *
 * @author thanuja
 */
public class AddCastOffDetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("servlet");
        javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";//SearchProjid
        
      
        String responseText = "";
        String chapterID="";
        String estPage="";
        String estRemarks="";
        

        int intAddRes = 0;
        pathfinder.functions.projects.chapters.ChapterDetails addCastOff = new pathfinder.functions.projects.chapters.ChapterDetails();
        pathfinder.functions.projects.chapters.ChapterDetailsVO chaptDetVO = null;
       String chapSize = request.getParameter("chapSize") != null ? request.getParameter("chapSize") : "0";
        if (chapSize.equals("")) {
            chapSize = "0";
        }

        
        List rqstList = new ArrayList();

       if (request.getParameter("save") != null) {
        //Insert into ChapterPlanDetVO
            //System.out.println("Save");
            int chapterSize=Integer.parseInt(chapSize);
            String name="";
            for (int i = 0; i < (chapterSize); i++) {
                name = String.valueOf("chk" + i);

                if (request.getParameter(name) != null) {


            chaptDetVO = new pathfinder.functions.projects.chapters.ChapterDetailsVO();
            chaptDetVO.setProjectId(getProjIdParam);
            chapterID = request.getParameter("chk" +  i) != null ? request.getParameter("chk" +  i) : "";
            //System.out.println("chapterID:" + chapterID);
            chaptDetVO.setChapterId(chapterID);
            
            estPage = request.getParameter("estPage" + i);
            estRemarks = request.getParameter("remarks" + i);
           
            chaptDetVO.setEstPages(estPage);
            chaptDetVO.setCastOffRmks(estRemarks);
            chaptDetVO.setCastOffEmpId(sesEmp);
            rqstList.add(chaptDetVO);

        }

        intAddRes = addCastOff.saveCastOffDet(rqstList);
        }
       

        responseText = "save=save&project_name_hidden=" + getProjIdParam + "&intAddRes=" + intAddRes;
        PrintWriter out = response.getWriter();
//out.println(getProjIdParam.trim());
        out.println(responseText.trim());
    }
}
}
