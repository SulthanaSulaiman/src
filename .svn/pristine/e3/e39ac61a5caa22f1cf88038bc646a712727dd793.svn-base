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

/**
 *
 * @author ramesh
 */
public class CreateProjServlet extends HttpServlet {


    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
String getClientParam="";
String getDivisionParam="";
String getBuyerParam="";
String getSalesParam="";
String getContactParam="";
String getPrinterParam="";
String getStatusParam="";
String getTypeParam="";
String getCategoryParam="";
String getDisciplineParam="";
String getTitleParam="";
String getEditionParam="";
String getAuthorParam="";
String responseText="";

String getPriorityParam="";
String getYearParam="";
String getMssParam="";
String getEstimatedParam="";
String getActualParam="";
String getISBN10Param="";
String getISBN13Param="";
String getBillLocationParam="";
String getColorParam="";
String getHeightParam="";
String getWidthParam="";
String getSaveTypeParam="";
String getSesEmp="";
String getProjLevelParam="";
String getProjEISBNCategoryParam="";
String getProjEISBNParam="";
String projEISBNCatDispValue="";
String getCustomerPO="";
String getCustomerPODate="";
String geteFtpDate="";
String getActualShipDate="";
String noOfChapters="";
String mssFormat="";
String fstPresent="";
String xmlProp="";
String jobLostReasonCode = "";
String projSaveStatus = "";
String paperInfrmtn = "";//paperInformation
String projectDetailsViewFlag=request.getParameter("projectDetailsViewFlag")!=null?request.getParameter("projectDetailsViewFlag"):"";
String projectName=request.getParameter("project_name")!=null?request.getParameter("project_name"):"";
String getProjIdParam=request.getParameter("createdID")!=null?request.getParameter("createdID"):"";
getSaveTypeParam=request.getParameter("saveProjName")!=null?request.getParameter("saveProjName"):"";

String getPryAuthorIdParam="";
String EISBNFieldParam="";

 String getPlannerIdParam = request.getParameter("planner_id_hidden")!=null?request.getParameter("planner_id_hidden"):"";
 
 
 String getHybridPlannerIdParam = request.getParameter("hybrid_planner_id_hidden")!=null?request.getParameter("hybrid_planner_id_hidden"):"";

 
  String getProjPrinterDateParam = request.getParameter("proj_printer_dt")!=null?request.getParameter("proj_printer_dt"):"";
String getProjEstSentDateParam = request.getParameter("proj_estsent_dt")!=null?request.getParameter("proj_estsent_dt"):"";

//get the parameter values
paperInfrmtn = request.getParameter("paperInformation")!=null?request.getParameter("paperInformation"):"";
getPryAuthorIdParam=request.getParameter("id_author_hidden")!=null?request.getParameter("id_author_hidden"):"";
String getSurNameParam=request.getParameter("id_surname")!=null?request.getParameter("id_surname"):"";
//getBuyerParam=request.getParameter("id_buyer")!=null?request.getParameter("id_buyer"):"";


//out.println("getProjIdParam:"+getProjIdParam);
 getClientParam=request.getParameter("id_client")!=null?request.getParameter("id_client"):"";

//out.println("getClientParam"+getClientParam);
 getDivisionParam=request.getParameter("id_divsion")!=null?request.getParameter("id_divsion"):"";
 getBuyerParam=request.getParameter("id_buyer")!=null?request.getParameter("id_buyer"):"";
 getSalesParam=request.getParameter("id_salesperson")!=null?request.getParameter("id_salesperson"):"";
 getContactParam=request.getParameter("id_contact")!=null?request.getParameter("id_contact"):"";
 getPrinterParam=request.getParameter("id_printer")!=null?request.getParameter("id_printer"):"";
 getStatusParam=request.getParameter("id_status")!=null?request.getParameter("id_status"):"";
 getTypeParam=request.getParameter("id_type")!=null?request.getParameter("id_type"):"";
 getCategoryParam=request.getParameter("id_category")!=null?request.getParameter("id_category"):"";
 getDisciplineParam=request.getParameter("id_discipline")!=null?request.getParameter("id_discipline"):"";
 getTitleParam=request.getParameter("id_title")!=null?request.getParameter("id_title"):"";
 getEditionParam=request.getParameter("id_edition")!=null?request.getParameter("id_edition"):"";
 getColorParam=request.getParameter("id_color")!=null?request.getParameter("id_color"):"";
 //getAuthorParam=request.getParameter("id_author")!=null?request.getParameter("id_author"):"";

 getPriorityParam=request.getParameter("id_priority")!=null?request.getParameter("id_priority"):"";
 getYearParam=request.getParameter("id_copyright")!=null?request.getParameter("id_copyright"):"";
 getMssParam=request.getParameter("id_mss")!=null?request.getParameter("id_mss"):"";
 getEstimatedParam=request.getParameter("id_est")!=null?request.getParameter("id_est"):"";
 getActualParam=request.getParameter("id_actual")!=null?request.getParameter("id_actual"):"";
 getISBN10Param=request.getParameter("id_isbn10")!=null?request.getParameter("id_isbn10"):"";
 getISBN13Param=request.getParameter("id_isbn13")!=null?request.getParameter("id_isbn13"):"";
 getBillLocationParam=request.getParameter("id_billLocation")!=null?request.getParameter("id_billLocation"):"";
 getHeightParam=request.getParameter("id_pgheight")!=null?request.getParameter("id_pgheight"):"";
 getWidthParam=request.getParameter("id_pgwidth")!=null?request.getParameter("id_pgwidth"):"";
 getSesEmp=request.getParameter("sesEmp")!=null?request.getParameter("sesEmp"):"";
 getProjLevelParam=request.getParameter("id_level")!=null?request.getParameter("id_level"):"";
 getProjEISBNCategoryParam=request.getParameter("id_eisbn_category")!=null?request.getParameter("id_eisbn_category"):"";
 getProjEISBNParam=request.getParameter("id_eisbn")!=null?request.getParameter("id_eisbn"):"";
 getCustomerPO=request.getParameter("customer_po")!=null?request.getParameter("customer_po"):"";
 System.out.println("customerPOss"+getCustomerPO);
 getCustomerPODate=request.getParameter("customer_podate")!=null?request.getParameter("customer_podate"):"";
  System.out.println("customerPOss1"+geteFtpDate);
 geteFtpDate=request.getParameter("eFtp_date")!=null?request.getParameter("eFtp_date"):"";
 // System.out.println("customerPOss"+getCustomerPO);
 getActualShipDate=request.getParameter("actual_ship_date")!=null?request.getParameter("actual_ship_date"):"";
 noOfChapters=request.getParameter("noOfChapters")!=null?request.getParameter("noOfChapters"):"";
 mssFormat=request.getParameter("mssFormat")!=null?request.getParameter("mssFormat"):"";
 fstPresent=request.getParameter("fstPresent")!=null?request.getParameter("fstPresent"):"";
 xmlProp=request.getParameter("xmlProp")!=null?request.getParameter("xmlProp"):"";
 jobLostReasonCode = request.getParameter("job_lost_id")!=null?request.getParameter("job_lost_id"):"";

 pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

 if(!getProjEISBNCategoryParam.equals("")||!getProjEISBNCategoryParam.equals("All")){
     if(getProjEISBNCategoryParam.equals("CSXML")){
        EISBNFieldParam = "proj_isbn_CSXML";
     }
     else if(getProjEISBNCategoryParam.equals("EPUB2.0")) {
         EISBNFieldParam = "proj_isbn_EPUB2_0";
     }
     else if(getProjEISBNCategoryParam.equals("EPUB3.0")) {
         EISBNFieldParam = "proj_isbn_EPUB3_0";
     }
     else if(getProjEISBNCategoryParam.equals("EPUBFL")) {
         EISBNFieldParam = "proj_isbn_EPUBFL";
     }
     else if(getProjEISBNCategoryParam.equals("ESTR")) {
         EISBNFieldParam = "proj_isbn_ESTR";
     }
     else if(getProjEISBNCategoryParam.equals("ESTRKNDL")) {
         EISBNFieldParam = "proj_isbn_ESTRKNDL";
     }
     else if(getProjEISBNCategoryParam.equals("ESTRKPAG")) {
         EISBNFieldParam = "proj_isbn_ESTRKPAG";
     }
     else if(getProjEISBNCategoryParam.equals("ETXT")) {
         EISBNFieldParam = "proj_isbn_ETXT";
     }
     else if(getProjEISBNCategoryParam.equals("ETXTHSPT")) {
         EISBNFieldParam = "proj_isbn_ETXTHSPT";
     }
     else if(getProjEISBNCategoryParam.equals("KF8")) {
         EISBNFieldParam = "proj_isbn_KF8";
     }
     else if(getProjEISBNCategoryParam.equals("KNDL")) {
         EISBNFieldParam = "proj_isbn_KNDL";
     }
     else if(getProjEISBNCategoryParam.equals("KNDLPAG")) {
         EISBNFieldParam = "proj_isbn_KNDLPAG";
     }
     else if(getProjEISBNCategoryParam.equals("LC")) {
         EISBNFieldParam = "proj_isbn_LC";
     }
     else if(getProjEISBNCategoryParam.equals("NC")) {
         EISBNFieldParam = "proj_isbn_NC";
     }
     else if(getProjEISBNCategoryParam.equals("PR")) {
         EISBNFieldParam = "proj_isbn_PR";
     }
     else if(getProjEISBNCategoryParam.equals("SIMPUB")) {
         EISBNFieldParam = "proj_isbn_SIMPUB";
     }
     else if(getProjEISBNCategoryParam.equals("SITB")) {
         EISBNFieldParam = "proj_isbn_SITB";
     }
     else if(getProjEISBNCategoryParam.equals("SMRTBK")) {
         EISBNFieldParam = "proj_isbn_SMRTBK";
     }
     else if(getProjEISBNCategoryParam.equals("VST")) {
         EISBNFieldParam = "proj_isbn_VST";
     }
     else if(getProjEISBNCategoryParam.equals("WBPDF")) {
         EISBNFieldParam = "proj_isbn_WBPDF";
     }
     else if(getProjEISBNCategoryParam.equals("WKR")) {
         EISBNFieldParam = "proj_isbn_WKR";
     }
 }

 String chkProjAdded="0";
 

//pathfinder.functions.projects.ProjIdGeneralInfo pgi = new pathfinder.functions.projects.ProjIdGeneralInfo();
//pgi.setProjId(getProjIdParam);
//pgi.setPrjEISBNFieldParam(EISBNFieldParam);
//pgi.collectProjectName();
//projEISBNCatDispValue = pgi.getPrjEISBNCatValue();

//if(!projEISBNCatDispValue.equals("")){
//    responseText=projEISBNCatDispValue;
//}



pathfinder.functions.projects.CreateProjId cpi = new pathfinder.functions.projects.CreateProjId();
//getAuthorIdParam
cpi.setPrimaryAuthor(getPryAuthorIdParam);
cpi.setSurName(splChar.ignoreSplChars(getSurNameParam));
cpi.setClient(getClientParam);
cpi.setDivision(getDivisionParam);
cpi.setBuyer(getBuyerParam);
cpi.setSalesPerson(getSalesParam);
cpi.setContact(getContactParam);
cpi.setPrinter(getPrinterParam);
cpi.setStatus(getStatusParam);
cpi.setType(getTypeParam);
cpi.setCategory(getCategoryParam);
cpi.setPaprInf(paperInfrmtn);
cpi.setProjLevelID(getProjLevelParam);
cpi.setProjEISBNCategID(EISBNFieldParam);
cpi.setProjEISBN(getProjEISBNParam);

cpi.setDiscipline(getDisciplineParam);
cpi.setTitle(getTitleParam);
cpi.setEdition(getEditionParam);
cpi.setAuthor(getAuthorParam);

cpi.setPriority(getPriorityParam);
cpi.setCopyRight(getYearParam);
cpi.setMss(getMssParam);
cpi.setEstimated(getEstimatedParam);
cpi.setActual(getActualParam);
cpi.setISBN10(getISBN10Param);
cpi.setISBN13(getISBN13Param);
cpi.setBillLocation(getBillLocationParam);
cpi.setColorId(getColorParam);
cpi.setProjId(getProjIdParam);
cpi.setHeight(getHeightParam);
cpi.setEmpId(getSesEmp);
cpi.setPlanner(getPlannerIdParam);
cpi.setHybridPlanner(getHybridPlannerIdParam);
cpi.setProjectedPrinterDt(getProjPrinterDateParam);
cpi.setProjectedEstSentDt(getProjEstSentDateParam);
cpi.setCustomerPO(getCustomerPO);
cpi.setCustomerPOdate(getCustomerPODate);
cpi.seteFtpDt(geteFtpDate);
cpi.setActualShipDate(getActualShipDate);
cpi.setNoOfChapters(noOfChapters);
cpi.setMssFormat(mssFormat);
cpi.setFstPresent(fstPresent);
cpi.setXmlProp(xmlProp);
//cpi.setWidth(getWidthParam);
cpi.setJobLostCode(jobLostReasonCode);
//System.out.println("paperInfrmtn"+paperInfrmtn);
 if(request.getParameter("saveProjName")!=null){
//if(getSaveTypeParam.equals("Add")){
     cpi.setAddOption(getSaveTypeParam);
     cpi.addProject();

     //if(request.getParameter("saveProjName").equals("Add")){
            chkProjAdded=cpi.getProjAdded();
           if(chkProjAdded.equals("1")){
               projSaveStatus = "yes";
               if(request.getParameter("saveProjName").equals("Add")){
                        getProjIdParam=cpi.getProjId();
                        responseText="saveProjName=Add&createdID="+getProjIdParam+"&projSave=yes";
               }
               else{
                   responseText="saveProjName=Modify&createdID="+getProjIdParam+"&projSave=yes";
               }
            //response.sendRedirect("testtools/AddNewProjName.jsp?createdID="+getProjIdParam);
            }

           else{
                projSaveStatus = "no";
               if(request.getParameter("saveProjName").equals("Add")){
                  getProjIdParam="";
                  responseText="createdID="+getProjIdParam+"&projSave=no&"+request.getQueryString();
               }
               else{
                   responseText="createdID="+getProjIdParam+"&projSave=no&"+request.getQueryString();
               }
           }
     //}

     }

if(projectDetailsViewFlag.equals("1")) {

PrintWriter out =response.getWriter();
//out.println(getProjIdParam.trim());
//out.println(responseText.trim());
response.sendRedirect("ProjectDetailsView.jsp?project_name="+projectName+"&project_name_hidden="+getProjIdParam+"&saveProjName=Modify&projSave="+projSaveStatus);
} else {
response.sendRedirect("NewProjectContainer.jsp?"+responseText);
}
}



    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
