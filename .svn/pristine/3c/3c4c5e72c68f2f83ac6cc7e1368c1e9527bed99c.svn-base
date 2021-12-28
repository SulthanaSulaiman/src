/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Iterator;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;

public class ScalePriceUploadServlet1 extends HttpServlet {

    private static final long serialVersionUID = -3208409086358916855L;
    static Properties props;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

String priceListId="";
        String empId = request.getParameter("empId") != null ? request.getParameter("empId") : "";
        SortedMap<String, String> key_values = new TreeMap<String, String>();
        String getClientIdParam = request.getParameter("client_name_hidden") != null ? request.getParameter("client_name_hidden") : "";//SearchProjid
        String getPriceList = request.getParameter("priceList") != null ? request.getParameter("priceList") : "";//SearchProjid
        String isSelected = "";
        String getSearchParam = request.getParameter("client_name") != null ? request.getParameter("client_name") : "";
        String getModify = request.getParameter("Show Price List") != null ? request.getParameter("Show Price List") : "";
        String getYearParam = request.getParameter("year") != null ? request.getParameter("year") : "";//SearchProjid
        String getEnableParam = request.getParameter("enable") != null ? request.getParameter("enable") : "";//SearchProjid
String fileName="";
        int isUpload = 1;
        int result = 0;
        int isInserted = 0;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //System.out.println("isMultipart:" + isMultipart);
        if (isMultipart) {
            //System.out.println("isMultipart");
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List items = upload.parseRequest(request);
                //  System.out.println("items:"+items);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    if (item.isFormField()) {

                        //   System.out.println("Got a form field: " + item.getFieldName() + " " + item);
                        key_values.put(item.getFieldName(), item.getString());
                    } else {
                        //if (!item.isFormField()) {
                         fileName = item.getName();


                        //Get the destination path from property file
                        if (props == null) {
                            props = new Properties();
                            InputStream is = getClass().getResourceAsStream("/properties/importexcel/pricelist.properties");
                            //System.out.println("getclass:" + getClass().getResourceAsStream("/properties/importexcel/pricelist.properties"));
                            props.load(is);
                            is.close();

                        }
                        String filePath = props.getProperty("excelPath").trim();

                        // String root = getServletContext().getRealPath("/");
                        //System.out.println("path:" + filePath);
                        File path = new File(filePath);
                        boolean status = true;
                        if (!path.exists()) {
                            status = path.mkdirs();
                        }

                        if (status) {
                            File uploadedFile = new File(path + "/" + fileName.trim());
                            //System.out.println("FilePath:" + uploadedFile.getAbsolutePath());
                            item.write(uploadedFile);

                        } else {
                            isUpload = 0;
                        }
                    }
                }
            } catch (FileUploadException e) {
                isUpload = 0;
                e.printStackTrace();
            } catch (Exception e) {
                isUpload = 0;
                e.printStackTrace();
            }
            //System.out.println("key_values:" + key_values);

            getClientIdParam = key_values.get("client_name_hidden");
            getPriceList = key_values.get("priceList");

            getSearchParam = key_values.get("client_name");
            getYearParam = key_values.get("year");
            getEnableParam = key_values.get("enable");
            //System.out.println("getEnable:"+getEnableParam);

            empId = key_values.get("empId");
      

            //If file is uploaded successfully then import the data from excel to db

            if (isUpload == 1) {
                //System.out.println("File  uploaded");
                pathfinder.util.importexcel.ImportFrmExlPriceList importExl = new pathfinder.util.importexcel.ImportFrmExlPriceList();
                importExl.setContactId(getClientIdParam);
                importExl.setYear(getYearParam);
                importExl.setEmpId(empId);
                importExl.setExcelFilename(fileName);
                importExl.setPriceEnable(getEnableParam);
                importExl.importexcel();
                result = importExl.getResult();
                priceListId = importExl.getPriceListId();
                if (result > 0) {
                    //System.out.println("Records inserted");
                    isInserted = 1;
                } else {
                    //System.out.println("Records not inserted");
                    isInserted = 0;
                }




            } else {
                //System.out.println("File  Not uploaded");
            }




        }

        /* String destination = "UploadScalePrice.jsp?isUpload="+isUpload;
        String dest = getServletContext().getRealPath("/");
        dest = dest + "/" + destination;
        //System.out.println("dest:"+dest);



        // response.sendRedirect(response.encodeRedirectURL(dest));
        response.sendRedirect(dest);*/

        String destination = "/UploadScalePrice.jsp?isUpload=" + isUpload + "&client_name_hidden=" + getClientIdParam + "&client_name=" + getSearchParam + "&priceList=" + priceListId + "&year=" + getYearParam +"&isInserted=" + isInserted+"&Upload=Upload&priceList=priceList";

        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(request, response);

    }
}
