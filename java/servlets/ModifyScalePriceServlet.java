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
 * @author Thanujas
 */
public class ModifyScalePriceServlet extends HttpServlet {
   
    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");
//System.out.println("servlet");
         String getClientIdParam = request.getParameter("client_name_hidden") != null ? request.getParameter("client_name_hidden") : "";//SearchProjid
            String getPriceList = request.getParameter("priceList") != null ? request.getParameter("priceList") : "";//SearchProjid
            String getDisclaimer = request.getParameter("disclaimer") != null ? request.getParameter("disclaimer") : "";//SearchProjid
             String getItemCount = request.getParameter("itemCount") != null ? request.getParameter("itemCount") : "";//SearchProjid


             String priceListId="";
        String empId = request.getParameter("empId") != null ? request.getParameter("empId") : "";




        String getSearchParam = request.getParameter("client_name") != null ? request.getParameter("client_name") : "";
       // String getShowPriceList = request.getParameter("Show Price List") != null ? request.getParameter("Show Price List") : "";
        String getYearParam = request.getParameter("year") != null ? request.getParameter("year") : "";//SearchProjid
        String getEnableParam = request.getParameter("enable") != null ? request.getParameter("enable") : "";//SearchProjid


             if (getItemCount.equals("")) {
            getItemCount = "0";
        }

             String priceId="";
             String rate="";
             List price_id=new ArrayList();
             List price = new ArrayList();
             String getSave = request.getParameter("Save") != null ? request.getParameter("Save") : "";//SearchProjid
String getModify = request.getParameter("Modify")!=null?request.getParameter("Modify"):"";
              int intAddRes = 0;
             String responseText = "";
            

            pathfinder.functions.revenue.PriceList addPrice = addPrice = new pathfinder.functions.revenue.PriceList();
            //new pathfinder.functions.revenue.PriceList();
        priceId = request.getParameter("priceid0") != null ? request.getParameter("priceid0" ) : "";
        //System.out.println("----priceId---:"+priceId);
            if (request.getParameter("Save") != null) {
                
        //Insert into ChapterPlanDetVO
            //System.out.println("Save");
            int itemSize=Integer.parseInt(getItemCount);
            //System.out.println("itemSize:"+itemSize);
            String name="";
            for (int i = 0; i < (itemSize); i++) {
                name = String.valueOf("priceid" + i);
 //System.out.println("Name:"+request.getParameter(name));

                if (request.getParameter(name) != null) {
                     //System.out.println("name:"+name);


            //addPrice = new pathfinder.functions.revenue.PriceList();
            
            priceId = request.getParameter("priceid" +  i) != null ? request.getParameter("priceid" +  i) : "";
            price_id.add(priceId);
            rate = request.getParameter("rate" +  i) != null ? request.getParameter("rate" +  i) : "";
            price.add(rate);

                }
            }
            addPrice.setPriceId(price_id);
            addPrice.setRate(price);

            intAddRes = addPrice.savePriceList();

             responseText = "Save=Save&client_name_hidden=" + getClientIdParam + "&Modify=yes&intAddRes=" + intAddRes;

        }

        if(request.getParameter("enablesub")!=null)
        {

            //System.out.println("ENABLE---------");
            pathfinder.util.importexcel.ImportFrmExlPriceList importExl = new pathfinder.util.importexcel.ImportFrmExlPriceList();
                importExl.setContactId(getClientIdParam);
                importExl.setPriceListId(getPriceList);
                importExl.setEmpId(empId);

                importExl.disableEnable();

                responseText="client_name_hidden=" + getClientIdParam + "&client_name=" + getSearchParam + "&priceList=" + getPriceList + "&year=" + getYearParam +"&priceList=priceList";
               
               
        }

        
        


       
        //System.out.println("responseText:"+responseText);
        PrintWriter out = response.getWriter();
//out.println(getProjIdParam.trim());
        out.println(responseText.trim());
    }
            

   }

