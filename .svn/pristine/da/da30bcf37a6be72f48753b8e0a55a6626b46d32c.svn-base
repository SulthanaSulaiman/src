/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.projects.*;
/**
 *
 * @author ramesh
 */
public class BookMapServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        /*response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();*/
        String getProjNameParam=request.getParameter("project_name")!=null?request.getParameter("project_name"):"";
        String getProjIdParam=request.getParameter("createdID")!=null?request.getParameter("createdID"):"";
String getRowCountParam=request.getParameter("AddRowCount")!=null?request.getParameter("AddRowCount"):"";
String getEditCountParam=request.getParameter("dispRowCount")!=null?request.getParameter("dispRowCount"):"0";
String getEditParam = request.getParameter("editBookMap")!=null?request.getParameter("editBookMap"):"";
String getAddChapParam=request.getParameter("AddChap")!=null?request.getParameter("AddChap"):"";
String getMenuFlag=request.getParameter("menu_flag_hidden")!=null?request.getParameter("menu_flag_hidden"):"";

//System.out.println("Hidden Menu Flag:"+getMenuFlag);
//System.out.println("getRowCountParam : "+getRowCountParam);
//System.out.println("getAddChapParam : "+getAddChapParam);

 int editCount = 0;

String getEmpId="";
String getChapNameParam="";
String getNumberedParam="";
String getNumberedLetterParam="";
String getMssPageCount = "";
String getartCount = "";
String getKeystrokeCount="";
String getremarks="";
String getEstStDateParam="";
//String getEstEndDateParam="";
//String getEstTotDateParam="";
//String getPagePrefixParam="";
//String getEstBlanksParam="";
String getActStPageParam="";
String getActEndPageParam="";
String getActualBlanksParam="";
String getActualTotalParam="";
String getChapterIdParam="";
String getchapInputParam="";
String getChapNameParamChk="Misc";
    String chapter_no="";
    String chapters_no="";
    String chapter="";
    String numberedindex="";
    String numberedindexletter="";
    String numberedindexLetter="";
    String mssPageCount = "";
    String mssartCount = "";
    String keystrokeCount="";
    String remarks="";
    //String chapter_name="";
    String estStartPg="";
//    String estEndPg="";
//    String estTotPg="";
//    String PgPfx="";
//    String estBlanks="";
    String actualStart="";
    String actualEnd="";
    String actualBlanks="";
    String actualTotal="";
    String chapInput="";
    String chk="";
    String modifyQuery="";
if(getEditCountParam.equals("")){
    getEditCountParam="0";
    }

    int queryReturn = 0;
    int noOfRows = 0;
noOfRows = Integer.parseInt(getRowCountParam);
editCount = Integer.parseInt(getEditCountParam);

try {
if(request.getParameter("AddChap")!=null){
SaveProjBookMap spbm = new SaveProjBookMap();
//System.out.println("noOfRows in AddChap"+noOfRows);


    for(int idx=0;idx<noOfRows;idx++){

     chapter_no="chapter_no"+String.valueOf(idx);
     chapter="chapter"+String.valueOf(idx);
    chapters_no="chapters_no"+String.valueOf(idx);
     numberedindex="numberedindex"+String.valueOf(idx);
     numberedindexletter="numberedindexletter"+String.valueOf(idx);
     numberedindexLetter="numberedindexLetter"+String.valueOf(idx);
     mssPageCount="mssPageCount"+String.valueOf(idx);
     mssartCount="mssartCount"+String.valueOf(idx);
     keystrokeCount="keystrokeCount"+String.valueOf(idx);
     remarks="remarks"+String.valueOf(idx);
     estStartPg="estStartPg"+String.valueOf(idx);
//     estEndPg="estEndPg"+String.valueOf(idx);
//     estTotPg="estTotPg"+String.valueOf(idx);
//     PgPfx="PgPfx"+String.valueOf(idx);
//     estBlanks="estBlanks"+String.valueOf(idx);
     actualStart="actualStart"+String.valueOf(idx);
     actualEnd="actualEnd"+String.valueOf(idx);
     actualBlanks="actualBlanks"+String.valueOf(idx);
     actualTotal="actualTotal"+String.valueOf(idx);
     chapInput="chapInput"+String.valueOf(idx);
     getchapInputParam=request.getParameter(chapInput)!=null?request.getParameter(chapInput):"";


if(getchapInputParam.equals("1")){
    getChapNameParam=request.getParameter(chapter)!=null?request.getParameter(chapter):"";
    
    if(!getChapNameParam.equals(getChapNameParamChk)){
    getChapNameParam=request.getParameter(chapter)!=null?request.getParameter(chapter):"";
    getNumberedParam=request.getParameter(numberedindex)!=null?request.getParameter(numberedindex):"";
    getNumberedLetterParam=request.getParameter(numberedindexletter)!=null?request.getParameter(numberedindexletter):"";
}
    else
{
    getNumberedParam="";
    getChapNameParam=request.getParameter(chapter_no)!=null?request.getParameter(chapter_no):"";
}
}
else if(getchapInputParam.equals("0")){
    getChapNameParam=request.getParameter(chapters_no)!=null?request.getParameter(chapters_no):"";
    //getNumberedParam=request.getParameter(numberedindex)!=null?request.getParameter(numberedindex):"";
    getNumberedLetterParam=request.getParameter(numberedindexLetter)!=null?request.getParameter(numberedindexLetter):"";
    
}else
{
    getNumberedParam="";
    getChapNameParam=request.getParameter(chapter_no)!=null?request.getParameter(chapter_no):"";
}
//out.println("getChapNameParam-in AddChap:"+getChapNameParam);
getMssPageCount=request.getParameter(mssPageCount)!=null?request.getParameter(mssPageCount):"";
getartCount =request.getParameter(mssartCount)!=null?request.getParameter(mssartCount):"";
getKeystrokeCount=request.getParameter(keystrokeCount)!=null?request.getParameter(keystrokeCount):"";
getremarks=request.getParameter(remarks)!=null?request.getParameter(remarks):"";
getEstStDateParam=request.getParameter(estStartPg)!=null?request.getParameter(estStartPg):"";
//getEstEndDateParam=request.getParameter(estEndPg)!=null?request.getParameter(estEndPg):"";
//getEstTotDateParam=request.getParameter(estTotPg)!=null?request.getParameter(estTotPg):"";
//getPagePrefixParam=request.getParameter(PgPfx)!=null?request.getParameter(PgPfx):"";
//getEstBlanksParam=request.getParameter(estBlanks)!=null?request.getParameter(estBlanks):"";
getActStPageParam=request.getParameter(actualStart)!=null?request.getParameter(actualStart):"";
getActEndPageParam=request.getParameter(actualEnd)!=null?request.getParameter(actualEnd):"";
getActualBlanksParam=request.getParameter(actualBlanks)!=null?request.getParameter(actualBlanks):"";
getActualTotalParam=request.getParameter(actualTotal)!=null?request.getParameter(actualTotal):"";
getEmpId=request.getParameter("empId")!=null?request.getParameter("empId"):"";

spbm.setProjId(getProjIdParam);
spbm.setChapterName(getChapNameParam);
spbm.setNumberedIndex(getNumberedParam);
spbm.setNumberedIndexLetter(getNumberedLetterParam);
spbm.setMssPageCount(getMssPageCount);
spbm.setartCount(getartCount);
spbm.setKeystrokeCount(getKeystrokeCount);
spbm.setRemarks(getremarks);
spbm.setEstStartPage(getEstStDateParam);
//spbm.setEstEndPage(getEstEndDateParam);
//spbm.setEstTotPage(getEstTotDateParam);
//spbm.setEstBlanks(getEstBlanksParam);
spbm.setActStartPage(getActStPageParam);
spbm.setActEndPage(getActEndPageParam);
spbm.setActBlanks(getActualBlanksParam);
spbm.setActTotal(getActualTotalParam);
spbm.setEmpId(getEmpId);


//     System.out.println(">>>>>>>>>>:"+getChapNameParam);
//     System.out.println("<<<<<<<<<<:"+getActStPageParam);
//     System.out.println("//////////:"+getActEndPageParam);
//     System.out.println("++++++++++:"+getActualBlanksParam);
//     System.out.println("----------:"+getActualTotalParam);

//spbm.setPagePrefix(getPagePrefixParam);
spbm.setAddOption("Add");
spbm.addProjBookMap();
queryReturn=spbm.getQueryReturn();
}//for int idx=0

 response.sendRedirect("AddNewProjBookMap.jsp?project_name="+getProjNameParam+"&project_name_hidden="+getProjIdParam+"&queryReturn="+queryReturn+"&AddChap=yes"+"&menu_flag_hidden="+getMenuFlag);

}//close of if("Add")


//the below is for bookmap modification even though same variables are used request parameters are different

if(request.getParameter("editBookMap")!=null){
    SaveProjBookMap modBookMap = new SaveProjBookMap();
//System.out.println("chapter_no:"+chapter_no);
for(int idx=0;idx<editCount;idx++){
chk="chk"+String.valueOf(idx);
getChapterIdParam=request.getParameter(chk)!=null?request.getParameter(chk):"";
if(request.getParameter(chk)!=null){
    if(getEditParam.equals("Modify"))
    {
             chapter_no="edit_chapter_no"+String.valueOf(idx);
             chapter="edit_chapter"+String.valueOf(idx);
             numberedindex="edit_numberedindex"+String.valueOf(idx);
             mssPageCount="edit_mssPageCount"+String.valueOf(idx);
             mssartCount="edit_artCount"+String.valueOf(idx);
             keystrokeCount="edit_keystrokeCount"+String.valueOf(idx);
             estStartPg="edit_estStartPg"+String.valueOf(idx);
//             estTotPg="edit_estTotPg"+String.valueOf(idx);
//             estEndPg="edit_estEndPg"+String.valueOf(idx);
//             PgPfx="edit_PgPfx"+String.valueOf(idx);
//             estBlanks="edit_estBlanks"+String.valueOf(idx);
             actualStart="edit_actualStart"+String.valueOf(idx);
             actualEnd="edit_actualEnd"+String.valueOf(idx);
             actualBlanks="edit_actualBlanks"+String.valueOf(idx);
             actualTotal="edit_actualTotal"+String.valueOf(idx);
             remarks="edit_remarks"+String.valueOf(idx);

        getChapNameParam=request.getParameter(chapter_no)!=null?request.getParameter(chapter_no):"";
        getMssPageCount=request.getParameter(mssPageCount)!=null?request.getParameter(mssPageCount):"";
        getartCount=request.getParameter(mssartCount)!=null?request.getParameter(mssartCount):"";
        getKeystrokeCount=request.getParameter(keystrokeCount)!=null?request.getParameter(keystrokeCount):"";
        getEstStDateParam=request.getParameter(estStartPg)!=null?request.getParameter(estStartPg):"";
//        getEstEndDateParam=request.getParameter(estEndPg)!=null?request.getParameter(estEndPg):"";
//        getEstTotDateParam=request.getParameter(estTotPg)!=null?request.getParameter(estTotPg):"";
//        getPagePrefixParam=request.getParameter(PgPfx)!=null?request.getParameter(PgPfx):"";
//        getEstBlanksParam=request.getParameter(estBlanks)!=null?request.getParameter(estBlanks):"";
        getActStPageParam=request.getParameter(actualStart)!=null?request.getParameter(actualStart):"";
        getActEndPageParam=request.getParameter(actualEnd)!=null?request.getParameter(actualEnd):"";
        getActualBlanksParam=request.getParameter(actualBlanks)!=null?request.getParameter(actualBlanks):"";
        getActualTotalParam=request.getParameter(actualTotal)!=null?request.getParameter(actualTotal):"";
        getchapInputParam=request.getParameter(chapInput)!=null?request.getParameter(chapInput):"";
        getEmpId=request.getParameter("empId")!=null?request.getParameter("empId"):"";
        getremarks= request.getParameter(remarks)!=null?request.getParameter(remarks):"";
        modBookMap.setProjId(getProjIdParam);
        modBookMap.setChapterName(getChapNameParam);
        modBookMap.setMssPageCount(getMssPageCount);
        modBookMap.setartCount(getartCount);
        modBookMap.setKeystrokeCount(getKeystrokeCount);
        modBookMap.setEstStartPage(getEstStDateParam);
//        modBookMap.setEstEndPage(getEstEndDateParam);
//        modBookMap.setEstTotPage(getEstTotDateParam);
//        modBookMap.setEstBlanks(getEstBlanksParam);
        modBookMap.setActStartPage(getActStPageParam);
        modBookMap.setActEndPage(getActEndPageParam);
        modBookMap.setActBlanks(getActualBlanksParam);
        modBookMap.setActTotal(getActualTotalParam);
//        modBookMap.setPagePrefix(getPagePrefixParam);
        modBookMap.setChapterId(getChapterIdParam);
        modBookMap.setEmpId(getEmpId);
        modBookMap.setAddOption("Modify");
        modBookMap.setRemarks(getremarks);
        modBookMap.addProjBookMap();
        queryReturn=modBookMap.getQueryReturn();
        queryReturn=11;
        }
        else if(getEditParam.equals("Delete")){
                modBookMap.setProjId(getProjIdParam);
                modBookMap.setChapterId(getChapterIdParam);
                modBookMap.setAddOption(getEditParam);
             //   System.out.println("getEditParam in Servlet:"+getEditParam);
                modBookMap.addProjBookMap();
                queryReturn=modBookMap.getQueryReturn();
                queryReturn=10;
        }
    }
}
 response.sendRedirect("AddNewProjBookMap.jsp?project_name="+getProjNameParam+"&project_name_hidden="+getProjIdParam+"&queryReturn="+queryReturn+"&editBookMap="+getEditParam+"&menu_flag_hidden="+getMenuFlag);
//System.out.println("Hidden Menu Flag:"+getMenuFlag);
}//of close of edit
        }
finally {

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
