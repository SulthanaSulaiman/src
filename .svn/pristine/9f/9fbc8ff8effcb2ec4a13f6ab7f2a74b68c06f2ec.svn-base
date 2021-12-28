/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.text.ParseException;

/**
 *
 * @author Gandhimathidevic
 */
//@WebServlet(name="FilehandlingServelt", urlPatterns={"/FilehandlingServelt"})
public class FilehandlingServelt extends HttpServlet {
   
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
            out.println("<title>Servlet FilehandlingServelt</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilehandlingServelt at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
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

        String ProjectName=request.getParameter("project_name1")!=null?request.getParameter("project_name1"):"";
        String totalrow=request.getParameter("totalrow")!=null?request.getParameter("totalrow"):"";
        String totalcolumn=request.getParameter("totalcolumn")!=null?request.getParameter("totalcolumn"):"";
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String insert = request.getParameter("insert") != null ? request.getParameter("insert") : "";
        String updateP = request.getParameter("updateP") != null ? request.getParameter("updateP") : "";
        String rowUpdate = request.getParameter("rowUpdate") != null ? request.getParameter("rowUpdate") : "";
        String resultChk = request.getParameter("resultChk") != null ? request.getParameter("resultChk") : "";
        String delete = request.getParameter("delete") != null ? request.getParameter("delete") : "";
        String updatestage = request.getParameter("updatestage") != null ? request.getParameter("updatestage") : "";
        String insertstage = request.getParameter("insertstage") != null ? request.getParameter("insertstage") : "";
        String savesta = request.getParameter("savesta") != null ? request.getParameter("savesta") : "";
        String xlsxF = request.getParameter("xlsxF") != null ? request.getParameter("xlsxF") : "";
        String Stagesize1 = request.getParameter("Stagesize1") != null ? request.getParameter("Stagesize1") : "";

        int colmnIdd=1;
        String forInsertidElement = "";
        String forInsertpage = "";
        int chaptrId=0;
        int result1=0;
        int milestId=1;
        int colmnIdd1=0;
        int result3=0;
        List milestoneForhear = new ArrayList();
        //System.out.println("result3"+result3);
        pathfinder.functions.projects.chapters.ExportExcelDataThrPerl exclPSer = new pathfinder.functions.projects.chapters.ExportExcelDataThrPerl();
        if (!insert.equals("")){
        for(int idx=1;idx<=Integer.parseInt(totalrow)+1;idx++){
        colmnIdd++;
        String k = String.valueOf(idx);
        if(idx>1){
    forInsertidElement="outputList"+k+".1";
    forInsertpage="outputList"+k+".2";
    chaptrId = exclPSer.getChapIdXcel();
    exclPSer.setChap_id(chaptrId);
    exclPSer.setChap_Name(request.getParameter(forInsertidElement));
    exclPSer.setPages(request.getParameter(forInsertpage));
    exclPSer.setProj_id(getProjIdParam);
    if(result1==0){
    exclPSer.insertChaptrDetailsToDB(exclPSer);
    }
    }
int milestncount=3;
String milestn="";
String milestoneName="";
String idElement="";
String milestdue="";

for(int colmn=1;colmn<=24;colmn++){
  if(idx>1 && colmn>2){
String k1 = String.valueOf(colmn);
String k2 = String.valueOf(milestncount);
milestn = "outputList1."+k2;
milestoneName=request.getParameter(milestn);
exclPSer.setChap_id1(chaptrId);
idElement = "outputList"+k+"."+k1;
milestdue=request.getParameter(idElement);
if (!xlsxF.equals("")){
    if (milestdue.equals("0")){
    exclPSer.setMilestonesDue("0000-00-00");
    }
    else{
        exclPSer.setMilestonesDue(milestdue);
    }
}
 else{
    if (!milestdue.equals("0")){
        Calendar now = Calendar.getInstance();
        String dateInString = milestdue+" "+now.get(Calendar.YEAR);
        Date date = new Date(dateInString);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        exclPSer.setMilestonesDue(formatter1.format(date));

 }
 else {
 exclPSer.setMilestonesDue("0000-00-00");
 }
 }
exclPSer.setMilestones(milestoneName);
exclPSer.setMilestonesCnt(milestId);
//System.out.println("idElement"+idElement+"=>"+milestdue);
//System.out.println("milestn"+milestn+"=>"+milestoneName);
if(result1==0){
 exclPSer.insertChaptrAndMilestDetailsToDB(exclPSer);
 }
milestncount++;
//System.out.println("colmnIDD"+colmnIdd);
if (colmnIdd==3){
    colmnIdd1++;
//System.out.println("colmnIDD"+colmnIdd);
//System.out.println("idElement"+milestId+"=>"+milestoneName);
exclPSer.setmilstnVsprojects(milestoneName);
exclPSer.setmilstnIdVsprojects(colmnIdd1);
if(result3==0 && resultChk.equals("0")){
exclPSer.insertmilestonesToDB(exclPSer);
 }

 if(result1==1){
 exclPSer.insertChaptrAndMilestDetailsToDB(exclPSer);
 }
}
}
milestId++;
}
}
         response.sendRedirect("ImportExcelAndScheduleChapters1.jsp?&project_name="+ProjectName+"&project_name_hidden="+getProjIdParam);
         }
        if(!updateP.equals("")){
            //System.out.println("updateP"+updateP);
            exclPSer.setProj_id(getProjIdParam);
            exclPSer.milestoneForProj(exclPSer);
            milestoneForhear = exclPSer.getMilestonesInstrd();
         for(int rows=1; rows<=Integer.parseInt(rowUpdate); rows++){
        String chapIdupdatek1 = String.valueOf(rows);
String chapIdElement = "chapId"+chapIdupdatek1+".1";
String chapIdPage = "Page"+chapIdupdatek1+".1";
String havToupdChap =request.getParameter(chapIdElement);
String havToupdChapPag =request.getParameter(chapIdPage) != null ? request.getParameter(chapIdPage) : "N/A";
    for(int x=2; x<=milestoneForhear.size()-1; x++){
String havToupdmilestn = milestoneForhear.get(x).toString();
String duedateupdatek1 = String.valueOf(rows);
String duedateupdatek2 = String.valueOf(x);
String duedateElement = "outlist"+duedateupdatek1+"."+duedateupdatek2;
String havToupdduedate =request.getParameter(duedateElement);
exclPSer.sethavToupdChap(havToupdChap);
exclPSer.sethavToupdChapPag(havToupdChapPag);
exclPSer.sethavToupdduedate(havToupdduedate);
exclPSer.sethavToupdmilestn(havToupdmilestn);
exclPSer.updatePlan(exclPSer);
//System.out.println(duedateElement);
//System.out.println(havToupdduedate+"=>"+havToupdmilestn+"=>"+havToupdChapPag);
//result = statement.executeUpdate("update chpaterdetailsfrmimportxcel set duedate='"+havToupdduedate+"', pages='"+havToupdChapPag+"' WHERE milestones='"+havToupdmilestn+"' and chapId='"+havToupdChap+"'");
}
    }
            response.sendRedirect("ImportExcelAndScheduleChapters1.jsp?&project_name="+ProjectName+"&project_name_hidden="+getProjIdParam);
}
        if (!delete.equals("")){
            exclPSer.deletePlan(getProjIdParam);
            response.sendRedirect("ImportExcelAndScheduleChapters1.jsp?&project_name="+ProjectName+"&project_name_hidden="+getProjIdParam);
        }
        if(savesta.equals("insert")){
           // System.out.println("insert");
            String projId=getProjIdParam;
            String stagedue="";
            String stagenam="";
            String stageactda="";
            for (int stagInt=1; stagInt<Integer.parseInt(request.getParameter("Stagesize1"));stagInt++)
                {
               stagedue = request.getParameter("stageDue"+String.valueOf(stagInt))!=null ? request.getParameter("stageDue"+String.valueOf(stagInt)):"";
               stageactda = request.getParameter("stageActual"+String.valueOf(stagInt))!=null ? request.getParameter("stageActual"+String.valueOf(stagInt)):"";
               stagenam = request.getParameter("stagenam"+String.valueOf(stagInt))!=null? request.getParameter("stagenam"+String.valueOf(stagInt)):"";
             //  System.out.println("stagenam,projId,stagedue,stageactda"+stagenam + projId + stagedue + stageactda);
               if (stagedue.equals("")){
        stagedue="0000-00-00";
    }
                  if (stageactda.equals("")){
        stageactda="0000-00-00";
    }
               exclPSer.insrtStageDuedateDetails(stagenam,projId,stagedue,stageactda,stagInt);
             }
             response.sendRedirect("ImportExcelAndScheduleChapters1.jsp?&project_name="+ProjectName+"&project_name_hidden="+getProjIdParam+"&insertedStage=yes");
        }
        if(savesta.equals("update")){
            String projId=getProjIdParam;
            String stagedueUpdt="";
            String stagenamUpdt="";
            String stageactdaUpdt="";
            int stageid;
            String stageidStrg;
for(int updstg=1; updstg<Integer.parseInt(request.getParameter("Stagesize1"));updstg++){
               stagenamUpdt = request.getParameter("stagenam"+String.valueOf(updstg));
               stagedueUpdt = request.getParameter("editduedate"+String.valueOf(updstg));
               stageactdaUpdt = request.getParameter("editactualdate"+String.valueOf(updstg));
               stageidStrg = request.getParameter("stageId"+String.valueOf(updstg));
               stageid = Integer.parseInt(stageidStrg);
               exclPSer.updateStagesDate(projId,stagedueUpdt,stageactdaUpdt,stageid);
}
              response.sendRedirect("ImportExcelAndScheduleChapters1.jsp?&project_name="+ProjectName+"&project_name_hidden="+getProjIdParam+"&insertedStage=yes");
        }
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
