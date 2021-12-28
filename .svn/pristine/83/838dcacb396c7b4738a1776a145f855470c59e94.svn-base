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
import java.util.*;
import freelanceSkill.services.AddFreelancerServicesDetails;
import javax.servlet.RequestDispatcher;


/**
 *
 * @author pathfinder
 */
@WebServlet(name="TestServlet", urlPatterns={"/TestServlet"})
public class FreelancerservicesServlet extends HttpServlet {
   
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
            String freelancerservice="";
            String freelancerSubject ="";
            String freelancerReference ="";
            String freelancerSoftware ="";
            String  freelancerId = request.getParameter("freelancerId") != null ? request.getParameter("freelancerId") : "";
             Map<String,String> freelancerservicemap=new HashMap<String,String>();
             List freelancerServiceList = new ArrayList();
            
            for(int i=1 ; i<19;i++){
                if (request.getParameter("service_checkbox" + i)!=null){
                    freelancerservice=request.getParameter("service_checkbox" + i);
                    freelancerservicemap.put(freelancerservice,"1");
                    freelancerServiceList.add(freelancerservice);
                }
            }
             //subject_checkbox1
 for(int i=1 ; i<29;i++){
                if (request.getParameter("subject_checkbox" + i)!=null){
                    freelancerSubject=request.getParameter("subject_checkbox" + i);
                    freelancerservicemap.put(freelancerSubject,"2");
                    freelancerServiceList.add(freelancerSubject);
                }
            }
             for(int i=1 ; i<16;i++){
                if (request.getParameter("Reference_checkbox" + i)!=null){
                    freelancerReference=request.getParameter("Reference_checkbox" + i);
                    freelancerservicemap.put(freelancerReference,"3");
                    freelancerServiceList.add(freelancerReference);
                }
            }
                          for(int i=1 ; i<14;i++){
                if (request.getParameter("Software_checkbox" + i)!=null){
                    freelancerSoftware=request.getParameter("Software_checkbox" + i);
                    freelancerservicemap.put(freelancerSoftware,"4");
                    freelancerServiceList.add(freelancerSoftware);
                }
            }
AddFreelancerServicesDetails addD = new AddFreelancerServicesDetails();
addD.getDetailsofFreelancer(freelancerServiceList);
List lstFromDao = new ArrayList();
lstFromDao = addD.returnfreelancerDetails1();
List<Object> sublistfreelancerId = new ArrayList();
List<Object> sublistfreelancerName = new ArrayList();
List<Object> freelancerPh = new ArrayList();
List<Object> freelancerAltPh = new ArrayList();
List<Object> freelanceremail = new ArrayList();
List<Object> country = new ArrayList();
List<Object> serviceName = new ArrayList();
sublistfreelancerId = (List<Object>) lstFromDao.get(0);
sublistfreelancerName = (List<Object>) lstFromDao.get(1);
freelancerPh = (List<Object>) lstFromDao.get(2);
freelancerAltPh = (List<Object>) lstFromDao.get(3);
freelanceremail = (List<Object>) lstFromDao.get(4);
country = (List<Object>) lstFromDao.get(5);
serviceName = (List<Object>) lstFromDao.get(6);
String outt="";
out.println("<html><body><h2>The Select query has following results : </h2>");
outt+="<table><tr>";
out.println("<table><tr>");
for(int i =0;i<sublistfreelancerName.size();i++){
    outt+="<tr><td>"+sublistfreelancerName.get(i).toString()+"</td><td>"+freelancerPh.get(i).toString()+"</td><td>"+freelancerAltPh.get(i).toString()+"</td><td>"+freelanceremail.get(i).toString()+"</td><td>"+country.get(i).toString()+"</td><td>"+serviceName.get(i).toString()+"</td></tr>";
out.println("<tr><td>"+sublistfreelancerName.get(i).toString()+"</td><td>"+freelancerPh.get(i).toString()+"</td><td>"+freelancerAltPh.get(i).toString()+"</td><td>"+freelanceremail.get(i).toString()+"</td><td>"+country.get(i).toString()+"</td><td>"+serviceName.get(i).toString()+"</td></tr>");

}
outt+="</tr></table>";
out.println("</tr></table>");
request.setAttribute("queryResults", outt);
RequestDispatcher rd=request.getRequestDispatcher("/retriveFreelancerDetails.jsp");

rd.forward(request, response);

//response.sendRedirect("searchfreelancer.jsp");

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
