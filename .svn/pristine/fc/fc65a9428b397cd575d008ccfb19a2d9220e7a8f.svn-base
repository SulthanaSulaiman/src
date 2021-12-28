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
 * @author pathfinder
 */
public class ClosePoServlet extends HttpServlet {
   
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
        String v1="";
        try {
            String getcount = request.getParameter("pocount")!= null ? request.getParameter("pocount") : "";
            String vendorName = request.getParameter("vendor_name_hidden1")!= null ? request.getParameter("vendor_name_hidden1") : "";
            String vendorId = request.getParameter("vendor_name_hidden")!= null ? request.getParameter("vendor_name_hidden") : "";
            System.out.println("ponumb"+vendorId);
            int count = 0;
            count = Integer.parseInt(getcount);
            
            String chk;
            String ponumInput;
            pathfinder.functions.revenue.POSummaryInfo updatePo = new pathfinder.functions.revenue.POSummaryInfo();
            for(int i=0; i<count;i++){
                
                chk="chkbx"+String.valueOf(i);
                ponumInput="ponumberforclose"+String.valueOf(i);
                String chkbx =request.getParameter(chk)!=null?request.getParameter(chk):"";
                String closeon =request.getParameter("closeOn")!= null ? request.getParameter("closeOn") : "";
                System.out.println("closeon"+chkbx);
                 String ponumb="";
                ponumb =request.getParameter("ponumberforclose")!= null ? request.getParameter("ponumberforclose") : "";
                    System.out.println("ponumbs"+ponumb);
               
                if(request.getParameter(chk)!=null){
                    ponumb =request.getParameter("ponumberforclose"+i)!= null ? request.getParameter("ponumberforclose"+i) : "";
                    System.out.println("ponumbs"+ponumb);
                    updatePo.closebulkPos(ponumb,closeon);
                }
                   
                //ponumberforclose
                //closeOn
            //updatePo.closebulkPos(poNum,);
                    v1 = vendorName.trim();
            }
             response.sendRedirect("closePurchaseOrder.jsp?vendornme="+vendorId+"&vendor_name_hidden1="+v1+"&vendor_name_hidden="+vendorId);
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClosePoServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClosePoServlet at " + request.getContextPath () + "</h1>");
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
