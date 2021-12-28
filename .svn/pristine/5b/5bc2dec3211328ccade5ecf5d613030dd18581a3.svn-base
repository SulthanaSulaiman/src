package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import java.io.*;
import java.io.File.*;
import java.util.*;
import java.util.List;

public class UploadServlet extends HttpServlet{

public long calculateFolderSize(File folderPath){


    long folderSize=0;

    if (folderPath.isFile()) {
        folderSize = folderPath.length();
    }
    else{
         //System.out.println("folderPath in Else:"+folderPath);
        File[] subFiles = folderPath.listFiles();
         //System.out.println("subFiles:"+subFiles);
        if(subFiles!=null){

           for (File file : subFiles) {
            if (file.isFile()) {
            folderSize += file.length();
            } else {
            folderSize += this.calculateFolderSize(file);
            }

            }

        }

    }
    return folderSize;
}
private String getFileExt(String xPath)
        {

			//Find extension
			int dotindex = 0;	//extension character position
			dotindex = xPath.lastIndexOf('.');

			if (dotindex == -1){	// no extension
				return "";
			}

			int slashindex = 0;	//seperator character position
			slashindex = Math.max(xPath.lastIndexOf('/'),xPath.lastIndexOf('\\'));

			if (slashindex == -1){	// no seperator characters in string
				return xPath.substring(dotindex);
			}

			if (dotindex < slashindex){	//check last "." character is not before last seperator
				return "";
			}
			return xPath.substring(dotindex);
	}


private  String byteCalc(String value){

int a = Integer.parseInt(value);


float b=0;

int getDotIndex=0;

int unitcount = 0;

String units = "";

	if(a>1024)

	{
		do{
			b=a%1024;
			a=a/1024;

			unitcount++;
		}while(a>1024);
	}

	b = b/1024;
	/*System.out.println("avalue:"+a);
	System.out.println("bvalue:"+b);*/

	String beqvt = String.valueOf(b);
	int beqvtlen = beqvt.length();

	getDotIndex = beqvt.lastIndexOf(".");

	getDotIndex += 1;

	String decimalpart = "";

	if(getDotIndex+2<beqvtlen-1){
	decimalpart = beqvt.substring(getDotIndex,getDotIndex+2);
	}
	else{
	decimalpart = beqvt.substring(getDotIndex);
	}

	if( unitcount == 0){

	units = "B";

	}

	if( unitcount == 1){

	units = "KB";

	}

	if( unitcount == 2){

	units = "MB";

	}

	if( unitcount == 3){

	units = "GB";

	}

	if( unitcount == 4){

	units = "TB";

	}

	String DispalyResult = String.valueOf(a)+"."+decimalpart+" "+units;

return DispalyResult;

}


public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
{

long folderSize=0;
long maxFileSize=0;
String getFiles = "";
String calculateSize = "";
String alertMessage="";
String fwdPage = "";
File testFile=null;

String dispchap_name = (String) request.getAttribute("dispchapname");

List fileNames = new ArrayList();

	boolean isMultipart = ServletFileUpload.isMultipartContent(request);//since FileUpload.isMultiPartContent is deprecated as it has ben defined twice in that class the method has been deprecated. So use the isMultipartContent method in the ServletFileUpload class.
	if(isMultipart)
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		getFiles = request.getParameter("filepath")!=null?request.getParameter("filepath"):"";

		HttpSession ses = request.getSession(true);
		ses.setAttribute("FilePath", getFiles);
		// final stop String
		//System.out.println(request.getParameter("totalfiles"));

		//String getTotalFiles = 	request.getParameter("totalfiles")!=null?request.getParameter("totalfiles"):"0";

//getTotalFiles="3";

		//int filesLoop = Integer.parseInt(getTotalFiles);

 long uploadedFileSize = 0;


		try{

		String FileExt = "";
		String newFilePath=request.getParameter("dest")!=null?request.getParameter("dest"):"";
                fwdPage = request.getParameter("pageName")!=null?request.getParameter("pageName"):"";
                String getIdParam = "";

		/*System.out.println("getFiles:"+getFiles);
                System.out.println("newFilePath:"+newFilePath);
                System.out.println("getIdParam:"+getIdParam);*/

		FileItemFactory itemfactory = new DiskFileItemFactory();
		ServletFileUpload fileupload = new ServletFileUpload(itemfactory);

		List /* FileItem */  parseUpload = fileupload.parseRequest(request);

		Iterator uploaditr = parseUpload.iterator();


/*for(int k=0;k<filesLoop;k++)

		{*/


//String getfile = "filepath"+String.valueOf(k);


String requestedFilePath = request.getParameter("filepath")!=null?request.getParameter("filepath"):"";
		File requestFile = new File(requestedFilePath);

		String newFile_Name = requestFile.getName();



		String newFileName="";

		FileItem item = null;

		String testfilepath = "";

                long checkFileSize = 0;

		while(uploaditr.hasNext())
		{

			item = (FileItem) uploaditr.next();


			if(item.isFormField()){

			String name = item.getFieldName();

			//System.out.println("getFieldName1:"+name);

			if(name.equals("dest"))
			{
			newFilePath = item.getString();

                            testFile = new File(newFilePath);
                            folderSize=calculateFolderSize(testFile);

                            Properties props=null;
                            if(props==null){
                                props = new Properties();
                                InputStream is = getServletContext().getResourceAsStream("WEB-INF/pf.properties");
                                props.load(is);
                                is.close();
                            }

                            String maxSize = props.getProperty("queryAttachmentSize");
                            maxFileSize = Integer.parseInt(maxSize);

			//System.out.println("newFilePath:"+newFilePath);
			}
                         else if(name.equals("pageName")){
                              fwdPage = item.getString();
                         }
                         else if(name.equals("idParams")){
                             getIdParam=item.getString();
                         }
			}
			else{
                          uploadedFileSize = item.getSize();
			String fieldName = item.getFieldName();
			String fileName = item.getName();
                        uploadedFileSize = item.getSize();
			File getFileName=new File(fileName);
			newFileName=getFileName.getName();

			//fwdPage+=newFileName;

			/*System.out.println("newFilePath:"+newFilePath);
			System.out.println("newFileName:"+newFileName);*/
			}

		}
                        checkFileSize = uploadedFileSize+folderSize;
                        calculateSize = byteCalc(Long.toString(folderSize));
fwdPage = "/"+fwdPage+"?"+getIdParam;

/*System.out.println("newFilePath:"+newFilePath);
System.out.println("fwdPage:"+fwdPage);

System.out.println("getIdParam:"+getIdParam);
                        System.out.println("folderSize:"+folderSize);
                        System.out.println("uploadedFilSize:"+uploadedFileSize);
                        System.out.println("checkFileSize:"+checkFileSize);
                        System.out.println("calculateSize:"+calculateSize);*/

			//System.out.println("newFileName:"+newFileName);


			File uploadedFile = new File(newFilePath+"/", newFileName);
			try
                                {
                                    //item.write(uploadedFile);
                                    if(checkFileSize<maxFileSize){
					item.write(uploadedFile);
                                    }
                                    else{
                                        alertMessage="The uploaded file size exceeds the maximum allowed size(1.4 MB). The total size of the  file(s) uploaded is for the query is "+calculateSize;
                                    }
				}
				catch(java.lang.Exception e){
                                 // System.out.println(e);
                                    e.printStackTrace();
				}

//System.out.println("alertMessage:"+alertMessage);



			if(uploadedFile.exists())
			{
                            folderSize=calculateFolderSize(testFile);
                            calculateSize = byteCalc(Long.toString(folderSize));
				request.setAttribute("Fileupload","success");
				request.setAttribute("FileName", newFileName);
                                alertMessage="The total size of the  file(s) uploaded is for the query is "+calculateSize;
				//request.setAttribute("disp_chapter",dispchap_name);
                        }
			else{
                                if(alertMessage.equals("")){
                                    alertMessage = "File not uploaded.";
                                }
				request.setAttribute("Fileupload","failure");
			}

		}catch(FileUploadException er){

		System.out.println("FileUploadException:"+er);

		}




	}


    //System.out.println("alertMessage:"+alertMessage);

    fwdPage=fwdPage+"&uploadAlert="+alertMessage;

//System.out.println("fwdPage:"+fwdPage);
request.setAttribute("reqUploadAlert", alertMessage);
RequestDispatcher rd = request.getRequestDispatcher(fwdPage);
rd.forward(request, response);
//request.getRequestDispatcher(fwdPage).forward(request, response);

    /*RequestDispatcher rd = request.getRequestDispatcher(fwdPage);
    rd.forward(request,response);*/

//response.sendRedirect("/"+fwdPage);

}

}

