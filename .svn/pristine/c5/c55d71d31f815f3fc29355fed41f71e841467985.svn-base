
// need to fix the issue of two numbered and four numbered year problem right for he sake of easiness - "20" is added in front of the year calculation(at the part of time convertion)

// need to check on the AM-PM values

import java.util.TimeZone;
import java.util.TimerTask;
import java.util.Date;
import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.lang.String;
import java.text.*;
import java.sql.*;
/*import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;*/
import java.io.*;
import java.text.ParseException;
import java.text.DateFormatSymbols;


/***These imports are for XL File Generation***/
import java.util.Collections;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.eventmodel.*;
import org.apache.poi.hssf.record.formula.*;
import org.apache.poi.hssf.record.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;

public class ChennaiProductionRpt1

{
	Timer timer1;

	int time,minute,second,month;

	Calendar calendar;

	Connection con,conn;

	Statement st,st1;

	String getaccessDate = "";

 public ChennaiProductionRpt1() /* this method continously checks the server time everyday*/
     {
   		/*getServerTime();
   		timer1 = new Timer();
    		int interval = 1000*60*60; //Every Hour
    		timer1.schedule(new que(),time,interval);*/

		ChennaiProductionReport();
    		//System.out.println(time);
     }
  /*class que extends TimerTask
     {
       
       		 public void run()
        			
			{
						 //getServerTime();
        					
			}
     }*/

  public void getServerTime()
  
     {
       		calendar = Calendar.getInstance();        
       	        time= calendar.get(Calendar.HOUR_OF_DAY);
       		minute= calendar.get(Calendar.MINUTE); 
       		second =  calendar.get(Calendar.SECOND);  
		month = calendar.get(Calendar.MONTH); 

		int daynightValue1 = calendar.get(Calendar.AM_PM);

/*System.out.println("time :"+time);
System.out.println("minute :"+minute);
System.out.println("second :"+second);
System.out.println("month :"+month);
System.out.println("daynightValue1 :"+daynightValue1);*/
        
    }


/************************************************************************************/

public void ChennaiProductionReport(){

System.out.println("time :"+time);

		List queryPrj_Id = new ArrayList();
		List Proj_Id = new ArrayList();
		List dueDate_List = new ArrayList();

		int cnt=0;
		int dueDate_ListSize = 0;
		int dataStart_Row = 5;

		List dueDay = new ArrayList();		
		String date;
		String queryProj_id = "";

		
		String changeaccessDate = "";
		String getaccessTime = "";
		String accessconcatTime = "";

		Connection con = null;
		Statement statement = null;

		String Timezone_value = "";

		List timezone_code = new ArrayList();
		List timezone_id = new ArrayList();
		List timezone_ref = new ArrayList();
		List timezone_diffparam = new ArrayList();
		List timezone_diffhrs = new ArrayList();
		List timezone_diffmts = new ArrayList();

		String db_timezone_code = " ";
		String db_timezone_id = " ";
		String db_timezone_ref = " ";
		String db_timezone_diffparam = " ";
		String db_timezone_diffhrs = " ";
		String db_timezone_diffmts = " ";

		try {

			/********* Definition  of xl and htm file names **/

					Class.forName("com.mysql.jdbc.Driver"); 


			con = DriverManager.getConnection("jdbc:mysql://10.1.1.9:3306/pathfinderv2?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "pfv2", "pfv2");
	         	
			//Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/s4access", "root", "root");
			//Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3307/s4access", "root", "root");
			Statement staccess = con.createStatement();


//System.out.println("time s4 :"+time);


			//con = DriverManager.getConnection("jdbc:mysql://localhost:3307/s4?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "root", "root");
			statement = con.createStatement();



//System.out.println("time :"+time);
String fileName_Date = "";

			ResultSet rsGetDisplayDate = staccess.executeQuery("select DATE_FORMAT(CURRENT_TIMESTAMP(),'%d %M %Y'),DATE_FORMAT(CURRENT_TIMESTAMP(),'%r'),DATE_FORMAT(CURRENT_TIMESTAMP(),'%M'),DATE_FORMAT(CURRENT_TIMESTAMP(),'%Y'),DATE_FORMAT(CURRENT_TIMESTAMP(),'%d-%b-%y') ");

			while(rsGetDisplayDate.next())
			{
				getaccessDate = rsGetDisplayDate.getString(1);

				String getresultTime = rsGetDisplayDate.getString(2);
					
				String appendgetDate1 = getaccessDate.substring(0,2);
		
				String appendgetDate2 = rsGetDisplayDate.getString(3);
				
				String appendgetDate3 = rsGetDisplayDate.getString(4);

				fileName_Date = rsGetDisplayDate.getString(5);
				
				changeaccessDate = appendgetDate1+"th"+" "+appendgetDate2+","+" "+appendgetDate3;				
	
				String splitTime1 = getresultTime.substring(0,5);

				String splitTime2 = getresultTime.substring(8);

				getaccessTime = splitTime1+" "+splitTime2;
				
				String concatTime = "";

			}

			System.out.println("getDate :"+getaccessDate);
			/*System.out.println("changeDate :"+changeaccessDate);
			System.out.println("getTime :"+getaccessTime);*/

			accessconcatTime = changeaccessDate+" "+"at"+" "+getaccessTime+" "+"IST";

			int htmaccessupdate = staccess.executeUpdate("update project_update set update_time='"+accessconcatTime+"' where projgroup_id='6' ");

			ResultSet rsGet_TZfixed = staccess.executeQuery("select timezone_fixed from projectgroupname where projgroup_id='6' ");

			while(rsGet_TZfixed.next())
				{
				  Timezone_value = rsGet_TZfixed.getString(1);
				}

				System.out.println("Timezone_value:"+Timezone_value);
			
				if(Timezone_value.equals("1"))
				{
					ResultSet rsGet_Tz = staccess.executeQuery(" select timezone_code,timezone_id,reference_timezone,time_difference_param,diff_hours,diff_minutes from timezone where status='1' ");

					while(rsGet_Tz.next())
					{
						db_timezone_code = rsGet_Tz.getString(1);
						if(rsGet_Tz.wasNull()){
						db_timezone_code="";
						}
						timezone_code.add(db_timezone_code);

						db_timezone_id = rsGet_Tz.getString(2);
						if(rsGet_Tz.wasNull()){
						db_timezone_id="";
						}
						timezone_id.add(db_timezone_id);

						db_timezone_ref = rsGet_Tz.getString(3);
						if(rsGet_Tz.wasNull()){
						db_timezone_ref="";
						}
						timezone_ref.add(db_timezone_ref);

						db_timezone_diffparam = rsGet_Tz.getString(4);
						if(rsGet_Tz.wasNull()){
						db_timezone_diffparam="";
						}
						timezone_diffparam.add(db_timezone_diffparam);

						db_timezone_diffhrs = rsGet_Tz.getString(5);
						if(rsGet_Tz.wasNull()){
						db_timezone_diffhrs="";
						}
						timezone_diffhrs.add(db_timezone_diffhrs);

						db_timezone_diffmts = rsGet_Tz.getString(6);
						if(rsGet_Tz.wasNull()){
						db_timezone_diffmts="";
						}
						timezone_diffmts.add(db_timezone_diffmts);

					}
					// pass the timezone codes,Ids, and restof info from DB to the calling method
					generateTzReport(timezone_code,timezone_id,timezone_ref,timezone_diffparam,timezone_diffhrs,timezone_diffmts,fileName_Date);
}

			
//con2.close();			
con.close();


		
			} /*catch ( IOException ex ) {
           			 ex.printStackTrace();
        		}*/
  			catch(SQLException e){
              			  System.out.println(e);
  			}catch(ClassNotFoundException ce){
                 			System.out.println("unable to load"+ ce);
  			}
}

/******* End of Chennai Production Report ***/


public List generateTzReport(List tz_code,List tz_id,List tz_ref,List tz_diffparam,List tz_diffhr,List tz_diffmt,String FileName_Date)
{

String ISTFolderName = "";

try{




List deptCode = new ArrayList();
List deptName = new ArrayList();

try{

			Connection deptCon = DriverManager.getConnection("jdbc:mysql://10.1.1.9:3306/pathfinderv2?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "pfv2", "pfv2");
			
	         	Statement deptStatement = deptCon.createStatement();
                        ResultSet rsGetDept = deptStatement.executeQuery("select distinct(dpt.dept_code),dpt.department from projects pr,chapter ch,department dpt "+
                                                      "where pr.project_status <> '2' and pr.proj_id=ch.proj_id"+
                                        " and ch.due_date is not null and ch.ship_date is null and dpt.dept_code=pr.dept_code and dpt.dept_code not in ('CHN-WK','DMD') ");
                        while(rsGetDept.next())
                        
                        {
                            deptCode.add(rsGetDept.getString(1));
                            deptName.add(rsGetDept.getString(2));
                        }

/*System.out.println("deptCode:"+deptCode);
System.out.println("deptName:"+deptName);*/
deptCon.close();
}catch(Exception e)
{
System.out.println(e);
}



/**************************************************************************************/
                List queryPrj_Id = new ArrayList();
		List Proj_Id = new ArrayList();
		List dueDate_List = new ArrayList();

		int cnt=0;
		int dueDate_ListSize = 0;
		int dataStart_Row = 5;

		List dueDay = new ArrayList();		
		String date;
		String queryProj_id = "";

		
		String changeaccessDate = "";
		String getaccessTime = "";
		String accessconcatTime = "";

		Connection con = null;


		String Timezone_value = "";

		List timezone_code = new ArrayList();
		List timezone_id = new ArrayList();
		List timezone_ref = new ArrayList();
		List timezone_diffparam = new ArrayList();
		List timezone_diffhrs = new ArrayList();
		List timezone_diffmts = new ArrayList();

		String db_timezone_code = " ";
		String db_timezone_id = " ";
		String db_timezone_ref = " ";
		String db_timezone_diffparam = " ";
		String db_timezone_diffhrs = " ";
		String db_timezone_diffmts = " ";
/**************************************************************************************/

	List getTz_code = tz_code;
	List getTz_id = tz_id;
	List getTz_ref = tz_ref;
	List getTz_diffparam = tz_diffparam;
	List getTz_diffhrs = tz_diffhr;
	List getTz_diffmts = tz_diffmt;

	int get_IST_Index = getTz_code.indexOf("IST");
	
	String getIST_DiffParm = getTz_diffparam.get(get_IST_Index).toString();
	String IST_hr = getTz_diffhrs.get(get_IST_Index).toString();
	String IST_mt = getTz_diffmts.get(get_IST_Index).toString();

	/*if(getTz_diffparam.get(get_IST_Index).trim().equals("+")){
	IST_hr = "-"+IST_hr;
	IST_mt = "-"+IST_mt;
	}*/



	/*File xldir = new File(XLFolder);
	if(!xldir.exists())
	 {
		xldir.mkdir();
         }


 	File htmdir = new File(HTMFolder);
	if(!htmdir.exists())
	 {
		htmdir.mkdir();
         }*/

String loopDeptCode="";
String loopDeptName="";


//the upperloop for dept, in which the loop for each timezone will be executed

  for(int idx=0;idx<deptCode.size();idx++)
   {
		
	loopDeptCode = deptCode.get(idx).toString();
	loopDeptName = deptName.get(idx).toString();

	for(int l1=0;l1<getTz_code.size();l1++)
	{

		db_timezone_code = getTz_code.get(l1).toString();
		db_timezone_id = getTz_id.get(l1).toString();
		db_timezone_ref = getTz_ref.get(l1).toString();
		db_timezone_diffparam = getTz_diffparam.get(l1).toString();
		db_timezone_diffhrs = getTz_diffhrs.get(l1).toString();
		db_timezone_diffmts = getTz_diffmts.get(l1).toString();

		ISTFolderName = "";
	
		String XLFolder = "X:\\Pathfinder\\CPR\\V2_Reports\\CPR-"+loopDeptName+"-"+db_timezone_code+"\\";

		ISTFolderName = "X:\\Pathfinder\\CPR\\V2_Reports\\CPR-"+loopDeptName+"-IST";
		String HTMFolder = "D:\\Sun\\AppServer\\domains\\domain1\\applications\\j2ee-modules\\pathfinder\\GPHTM Data\\Chennai Production Report-"+db_timezone_code+"\\";
	

		String TZ_diffhrs = db_timezone_diffhrs;
		String TZ_diffmts = db_timezone_diffmts;

		//System.out.println("db_timezone_ref:"+db_timezone_ref);
		//String TZ_ref = db_timezone_ref;



		/*System.out.println("TZ_diffhrs:"+TZ_diffhrs);
		System.out.println("TZ_diffmts:"+TZ_diffmts);*/

		/*SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
		Date testdt = sdf.parse(test);
		String day_op= sdf.format(testdt);*/

		Calendar zonecal = new GregorianCalendar(TimeZone.getTimeZone("IST"));//since the estimate time is set with respect to IST, the loop reference timezone is set to IST

		/*testcal.set(Calendar.DATE,15);*/

		//get the string value of each timezone and rest of arraylists and convert them into their integer equivalent
		String rep_timezone = getTz_code.get(l1).toString();//rep_timezone means --> report_timezone
		String rep_timezoneid = getTz_id.get(l1).toString();
		String rep_timezoneref = getTz_ref.get(l1).toString();
		String rep_timediffparam = getTz_diffparam.get(l1).toString();
		String rep_timediffhrs = getTz_diffhrs.get(l1).toString();
		String rep_timediffmts = getTz_diffmts.get(l1).toString();

		if(rep_timediffparam.trim().equals("-"))
		{
		rep_timediffhrs = "-"+rep_timediffhrs ;
		rep_timediffmts = "-"+rep_timediffmts ;
		}

		//System.out.println("rep_timediffparam:"+rep_timediffparam);

		int time_hrs = Integer.parseInt(rep_timediffhrs);
		int time_mts = Integer.parseInt(rep_timediffmts);
	
		//zonecal.set(Calendar.Date(),db.date);
		//zonecal.set(Calendar.Hr(),db.hr));
		//zonecal.set(Calendar.mt(),db.mt));
				
		//if(timediff.paramof ISt wrt GMT.equals("+"))
		//zonecal.add(Calendar.hr,-(GMTHR diff with IST))	
		//zonecal.add(Calendar.mt,-(GMTmt diff with IST))

		//if(timediff.paramof ISt wrt GMT.equals("-"))
		//zonecal.add(Calendar.hr,(GMTHR diff with IST))	
		//zonecal.add(Calendar.mt,(GMTmt diff with IST))
		
		//zonecal.add(Calendar.hr,+/-(respective timezone value))	
		//zonecal.add(Calendar.mt,+/-(respective timezone value))


//System.out.println("generateTZtest:");
		
			//Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/s4access", "root", "root");


			con = DriverManager.getConnection("jdbc:mysql://10.1.1.9:3306/pathfinderv2?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "pfv2", "pfv2");
			
			Statement staccess = con.createStatement();

	         	Statement statement = con.createStatement();

System.out.println("generateTZtest");

/*********java script logic *********/
			
String s = new String("<html>"+"<head>"+" <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" />");

s+="<style type=\"text/css\">";
s+="#container{  text-align: left;";
s+="margin: 20px auto 0 auto;";
s+="padding: 0;";
s+="}#block0, #block1, #block2, #block3, #block4, #block5 {";
s+="margin: 0;";
s+="padding: 10px;";
s+="};";
s+=".active {";
s+="};";
s+=".visited {";
s+="}";
s+="#block0, #block0.active, #block0.visited {";
s+="text-align: center;";
s+="border-top: none;";
s+="}</style>";


s+="<script language=\"JavaScript\" type=\"text/JavaScript\">";
s+="var ScrollWin = {";
s+="w3c : document.getElementById,";
s+="iex : document.all,";
s+="scrollLoop : false,";
s+="scrollInterval : null,";
s+="currentBlock : null,";
s+="getWindowHeight : function()";
s+="{";
s+="if(this.iex) return (document.documentElement.clientHeight) ? document.documentElement.clientHeight : document.body.clientHeight;";
s+="else return window.innerHeight;";
s+="},";
s+="getScrollLeft : function()";
s+="{";
s+="if(this.iex) return (document.documentElement.scrollLeft) ? document.documentElement.scrollLeft : document.body.scrollLeft;";
s+="else return window.pageXOffset;";
s+="},";
s+="getScrollTop : function(){";
s+="if(this.iex) return (document.documentElement.scrollTop) ? document.documentElement.scrollTop : document.body.scrollTop;";
s+="else return window.pageYOffset;";
s+="},";

s+="getElementYpos : function(el){";
s+="var y = 0;";
s+="while(el.offsetParent)";
s+="{";
s+="y += el.offsetTop;";
s+="el = el.offsetParent;";
s+="}";
s+="return y;";
s+="},";
s+="scroll : function(num)";
s+="{";
s+="if(!this.w3c){";
s+="location.href = \"#\"+this.anchorName+num;";
s+="return;";
s+="}";
s+="if(this.scrollLoop){";
s+="clearInterval(this.scrollInterval);";
s+="this.scrollLoop = false;";
s+="this.scrollInterval = null;";
s+="}";
s+="if(this.currentBlock != null) this.currentBlock.className = this.offClassName;";
s+="this.currentBlock = document.getElementById(this.blockName+num);";
s+="this.currentBlock.className = this.onClassName;";
s+="var doc = document.getElementById(this.containerName);";
s+="var documentHeight = this.getElementYpos(doc) + doc.offsetHeight;";
s+="var windowHeight = this.getWindowHeight();";
s+="var ypos = this.getElementYpos(this.currentBlock);";
s+="if(ypos > documentHeight - windowHeight) ypos = documentHeight - windowHeight;";
s+="this.scrollTo(0,ypos);";
s+="},";
s+="scrollTo : function(x,y){";
s+="if(this.scrollLoop)";
s+="{";
s+="var left = this.getScrollLeft();";
s+="var top = this.getScrollTop();";
s+="if(Math.abs(left-x) <= 1 && Math.abs(top-y) <= 1)";
s+="{";
s+="window.scrollTo(x,y);";
s+="clearInterval(this.scrollInterval);";
s+="this.scrollLoop = false;";
s+="this.scrollInterval = null;";
s+="}";
s+="else{";
s+="window.scrollTo(left+(x-left)/2, top+(y-top)/2);";
s+="}";
s+="}else{";
s+="this.scrollInterval = setInterval(\"ScrollWin.scrollTo(\"+x+\",\"+y+\")\",01);";
s+="this.scrollLoop = true;";
s+="}";
s+="}";
s+="};";
s+="ScrollWin.containerName = \"container\";";
s+="ScrollWin.anchorName    = \"anchor\";";
s+="ScrollWin.blockName     = \"block\";";
s+="ScrollWin.onClassName   = \"active\";";
s+="ScrollWin.offClassName  = \"visited\";";    
 s+="</script>";
s+="</head>"+"<body>";

		
			/********* End of javascript logic ***/
/******** Directories and filename creation ***/

			//String subFolder1 = rep_timezone.trim();

	

	String directoryname = XLFolder;

 	File attachmentsDir = new File(directoryname);

	if(!attachmentsDir.exists())
	 {		
		attachmentsDir.mkdirs();	
         }

			System.out.println("FileName_Date:"+FileName_Date);



				String xlfilename = "Chennai Production Report-"+loopDeptName+"-"+db_timezone_code+".xls";

				File f = new File(attachmentsDir,xlfilename);

				FileOutputStream stream = new FileOutputStream(f);

	String directoryname2 = HTMFolder;

 	File attachmentsDir2 = new File(directoryname2);

	if(!attachmentsDir2.exists())
	 {
             attachmentsDir2.mkdirs();		
         }

				String htmfilename = "Chennai Production Report-"+db_timezone_code+".htm";

				File f2 = new File(attachmentsDir2,htmfilename);
	
				FileOutputStream stream2 = new FileOutputStream(f2);

/************* End of Directories and filename creation ************************/


/************ Column and header styles definition ******************************/

/******** Excel Worksheet creation **/
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet();

			HSSFRow headerRow = sheet.createRow((short) 0);

			boolean fop = false;

			sheet.setDefaultColumnWidth((short) 20);

			//sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 0));
			//sheet.addMergedRegion(new Region(1, (short) 0, 0, (short) 1));

    	sheet.addMergedRegion(new Region(1, (short) 2, 1, (short) 3));
			sheet.setDisplayGridlines(fop);

			//sheet.addMergedRegion(new Region(1, (short) 0, 0, (short) 1));

//sheet.addMergedRegion(new Region(4, (short) 4, 2, (short) 5));


			HSSFRow headerRow2 = sheet.createRow((short) 1);

			HSSFCell headerCell1 =  headerRow2.createCell((short) 2);
			HSSFCell headerCell2 =  headerRow2.createCell((short) 3);


			HSSFCellStyle headerStyle = workBook.createCellStyle();

			HSSFCellStyle headerStyle1 = workBook.createCellStyle();

			HSSFCellStyle highlightStyle1 = workBook.createCellStyle();

			HSSFFont font1 = workBook.createFont();
			

			//short colorr = red;

			HSSFCellStyle headerStyle1_withNoBorder = workBook.createCellStyle();

			short colorIndex = HSSFColor.RED.index + 1;


   			font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

    			headerStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			headerStyle.setFillForegroundColor(HSSFColor.ORANGE.index);
			headerStyle.setWrapText(true);
   			headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    			headerStyle.setBottomBorderColor(HSSFColor.BLACK.index);
   			headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
   			headerStyle.setLeftBorderColor(HSSFColor.BLACK.index);
   			headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
    			headerStyle.setRightBorderColor(HSSFColor.BLACK.index);
    			headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
    			headerStyle.setTopBorderColor(HSSFColor.BLACK.index);

			headerStyle1.setFont(font1);
			headerStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			headerStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			headerStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    			headerStyle1.setBottomBorderColor(HSSFColor.BLACK.index);
   			headerStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
   			headerStyle1.setLeftBorderColor(HSSFColor.BLACK.index);
   			headerStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
    			headerStyle1.setRightBorderColor(HSSFColor.BLACK.index);
    			headerStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
    			headerStyle1.setTopBorderColor(HSSFColor.BLACK.index);

			HSSFCellStyle headerStyle_color1 = workBook.createCellStyle();

			headerStyle_color1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			headerStyle_color1.setWrapText(true);
   			headerStyle_color1.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			headerStyle_color1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    			headerStyle_color1.setBottomBorderColor(HSSFColor.BLACK.index);
   			headerStyle_color1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
   			headerStyle_color1.setLeftBorderColor(HSSFColor.BLACK.index);
   			headerStyle_color1.setBorderRight(HSSFCellStyle.BORDER_THIN);
    			headerStyle_color1.setRightBorderColor(HSSFColor.BLACK.index);
    			headerStyle_color1.setBorderTop(HSSFCellStyle.BORDER_THIN);
    			headerStyle_color1.setTopBorderColor(HSSFColor.BLACK.index);

			HSSFFont colorfont1 = workBook.createFont();
			colorfont1.setColor(HSSFColor.RED.index);
			headerStyle_color1.setFont(colorfont1);


			HSSFCellStyle headerStyle_color2 = workBook.createCellStyle();

			/*headerStyle_color2.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
   			headerStyle_color2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			headerStyle_color2.setFillPattern(HSSFCellStyle.SPARSE_DOTS);*/

			headerStyle_color2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			headerStyle_color2.setWrapText(true);
   			headerStyle_color2.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			headerStyle_color2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    			headerStyle_color2.setBottomBorderColor(HSSFColor.BLACK.index);
   			headerStyle_color2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
   			headerStyle_color2.setLeftBorderColor(HSSFColor.BLACK.index);
   			headerStyle_color2.setBorderRight(HSSFCellStyle.BORDER_THIN);
    			headerStyle_color2.setRightBorderColor(HSSFColor.BLACK.index);
    			headerStyle_color2.setBorderTop(HSSFCellStyle.BORDER_THIN);
    			headerStyle_color2.setTopBorderColor(HSSFColor.BLACK.index);

			HSSFFont colorfont2 = workBook.createFont();
			colorfont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			colorfont2.setColor(HSSFColor.BLUE.index);
			headerStyle_color2.setFont(colorfont2);

			HSSFCellStyle headerStyle_color12 = workBook.createCellStyle();

			headerStyle_color12.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			headerStyle_color12.setWrapText(true);
   			headerStyle_color12.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			headerStyle_color12.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    			headerStyle_color12.setBottomBorderColor(HSSFColor.BLACK.index);
   			headerStyle_color12.setBorderLeft(HSSFCellStyle.BORDER_THIN);
   			headerStyle_color12.setLeftBorderColor(HSSFColor.BLACK.index);
   			headerStyle_color12.setBorderRight(HSSFCellStyle.BORDER_THIN);
    			headerStyle_color12.setRightBorderColor(HSSFColor.BLACK.index);
    			headerStyle_color12.setBorderTop(HSSFCellStyle.BORDER_THIN);
    			headerStyle_color12.setTopBorderColor(HSSFColor.BLACK.index);

			HSSFFont colorfont12 = workBook.createFont();
			colorfont12.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			colorfont12.setColor(HSSFColor.TEAL.index);
			headerStyle_color12.setFont(colorfont12);


			short height=11;

			HSSFFont font = workBook.createFont();

   			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints(height);
			headerStyle1_withNoBorder.setFont(font1);
			headerStyle1_withNoBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			headerStyle1_withNoBorder.setFont(font);


/*** end of first style **/

			headerCell1.setCellValue("Chennai Production Report");


			HSSFCellStyle columnHeaderStyle = workBook.createCellStyle();

			HSSFCellStyle columnHeaderStyle2 = workBook.createCellStyle();

			columnHeaderStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
   			columnHeaderStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			columnHeaderStyle.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
			columnHeaderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    			columnHeaderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
   			columnHeaderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
   			columnHeaderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
   			columnHeaderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
    			columnHeaderStyle.setRightBorderColor(HSSFColor.BLACK.index);
    			columnHeaderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
    			columnHeaderStyle.setTopBorderColor(HSSFColor.BLACK.index);


			HSSFFont font2 = workBook.createFont();

			//highlightStyle1.setFillBackground
			highlightStyle1.setFillBackgroundColor((short) 5);
			highlightStyle1.setFont(font2);
			highlightStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			//highlightStyle1.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
			highlightStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    			highlightStyle1.setBottomBorderColor(HSSFColor.BLACK.index);
   			highlightStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
   			highlightStyle1.setLeftBorderColor(HSSFColor.BLACK.index);
   			highlightStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
    			highlightStyle1.setRightBorderColor(HSSFColor.BLACK.index);
    			highlightStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
    			highlightStyle1.setTopBorderColor(HSSFColor.BLACK.index);

				

   			//font.setColor(HSSFFont.COLOR_RED);

   			columnHeaderStyle.setFont(font);
			columnHeaderStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			
				//font2.setColor(HSSFColor.BLUE.index);
   				font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
   				columnHeaderStyle2.setFont(font2);
				columnHeaderStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				columnHeaderStyle2.setWrapText(true);
					//sheet.setDefaultColumnWidth(HSSFSheet.LeftMargin);
				sheet.setGridsPrinted(true);

			columnHeaderStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    			columnHeaderStyle2.setBottomBorderColor(HSSFColor.BLACK.index);
   			columnHeaderStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
   			columnHeaderStyle2.setLeftBorderColor(HSSFColor.BLACK.index);
   			columnHeaderStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
    			columnHeaderStyle2.setRightBorderColor(HSSFColor.BLACK.index);
    			columnHeaderStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
    			columnHeaderStyle2.setTopBorderColor(HSSFColor.BLACK.index);

			headerCell1.setCellStyle(headerStyle1_withNoBorder);
			headerCell2.setCellStyle(headerStyle1_withNoBorder);

/*** till above the column headerstyles are defined **/
dueDate_List.clear();
dueDay.clear();


				 ResultSet rsd = statement.executeQuery("select DATE_FORMAT(CURRENT_TIMESTAMP,'%d-%b-%y') ");

				 while(rsd.next())
              				{
                  				 date = rsd.getString(1);
              				}

				
			String current_date="";
			//ResultSet rsGetDueDate = statement.executeQuery("select distinct(date_format(a.due_date,'%d-%b-%y')),dayname(a.due_date),date_format(current_timestamp(),'%d-%b-%y') from chapter a,projectgroup b where (a.ship_date is null and a.proj_id=b.proj_id and b.projgroup_id='6') or (a.ship_date='0000-00-00 00:00:00' and a.proj_id=b.proj_id and b.projgroup_id='6') order by a.due_date");
			
ResultSet rsGetDueDate = statement.executeQuery("select distinct(date_format(a.due_date,'%d-%b-%y')),dayname(a.due_date),date_format(current_timestamp(),'%d-%b-%y') from chapter a,projects b where (a.ship_date is null and a.due_date is not null and a.proj_id=b.proj_id and a.chapter_status=1 and a.chapter_deleted=0 and b.project_status <> '2' and a.stage not in ('INTART','CYD','PPD','FUS') and b.dept_code = '"+loopDeptCode+"' and a.due_date_received='1') or (a.ship_date='0000-00-00 00:00:00' and a.due_date is not null and a.proj_id=b.proj_id and a.chapter_status=1 and a.chapter_deleted=0 and b.project_status <> '2' and a.stage not in ('INTART','CYD','PPD','FUS') and b.dept_code = '"+loopDeptCode+"' and a.due_date_received='1') order by a.due_date");
				while(rsGetDueDate.next()){

				String rsValue = rsGetDueDate.getString(1);

				cnt ++;

				dueDate_List.add(rsValue);
				dueDay.add(rsGetDueDate.getString(2));
                                current_date=rsGetDueDate.getString(3);
				}

			/*System.out.println("resultDate:"+cnt);*/
			System.out.println("current_date:"+current_date);

			dueDate_ListSize = dueDate_List.size();
			            
	/*** till above the project_id in the group is collected and then added in a colection and the distinct due date for all those projects has been collected based on these duedates looping is to be done for all those projects each date by date **/
                
//Get the projects which does not have due date
                       List dueProjId=new ArrayList();
                       List dueProjName=new ArrayList();
                       List dueStage=new ArrayList();
                       List dueChapNo=new ArrayList();
                       List dueRemark=new ArrayList();
                       List dueEstimatedTime=new ArrayList();
		       List dueTZ_estimatedDateTime=new ArrayList();
		       List dueReceivedDate=new ArrayList(); 
                       List dueEstimatedDate=new ArrayList();
                       List dueMssCount=new ArrayList();
                       
                       dueProjId.clear();
                       dueProjName.clear();
                       dueStage.clear();
                       dueChapNo.clear();
                       dueRemark.clear();
                       dueEstimatedTime.clear();
                       dueEstimatedDate.clear();
		       dueTZ_estimatedDateTime.clear();
                       dueReceivedDate.clear();
                       dueMssCount.clear();
                       
//                       System.out.println("dataStart_Row:");
                      //Display project details without due date 
                     ResultSet rsNoDue=statement.executeQuery("select distinct(b.proj_id),b.proj_name,c.stage,a.chapter_no,a.chapter_id,a.remark,date_format(a.estimated_time,'%d-%b-%y'),date_format(a.receipt_date,'%d-%b-%y'),date_format(a.estimated_time,'%h:%i %p'),a.mss_count,date_format(a.estimated_time,'%d-%m-%Y %h:%i %p') from chapter a, projects b, project_stage c where (a.ship_date is null or a.ship_date='0000-00-00 00:00:00') and a.due_date is not null and c.stage_code=a.stage and a.proj_id=b.proj_id and a.due_date_received='0' and a.stage not in ('INTART','CYD','PPD','FUS') and a.chapter_status=1 and a.chapter_deleted=0 and b.project_status <> '2' and b.dept_code = '"+loopDeptCode+"' order by a.estimated_time");
                     while(rsNoDue.next())
                     {
                         dueProjId.add(rsNoDue.getString(1));
                         dueProjName.add(rsNoDue.getString(2));
                         dueStage.add(rsNoDue.getString(3));
                         dueChapNo.add(rsNoDue.getString(4));
                         dueRemark.add(rsNoDue.getString(6));
                         
                         String chapEstimatedDate=rsNoDue.getString(7);
                         if(rsNoDue.wasNull())
                             chapEstimatedDate="[No estimate yet]";
                         
                         String chapEstimatedTime=rsNoDue.getString(9);
                         if(rsNoDue.wasNull())
                             chapEstimatedTime="[No estimate yet]";
                         
                         dueEstimatedDate.add(chapEstimatedDate);
                         dueEstimatedTime.add(chapEstimatedTime);
                         
                         dueReceivedDate.add(rsNoDue.getString(8));
                         
                         dueMssCount.add(rsNoDue.getString(10));     
			 dueTZ_estimatedDateTime.add(rsNoDue.getString(11));           
                     }




			if(dueProjId.size()>0)                                  
			{
				s+="<div>";
				s+="<div id=\"container\"> <a name=\"anchor0\"></a>";
   				s+="<div id=\"block0\"><a href=\"javascript:ScrollWin.scroll('1')\">Click here to list projects that have no due dates specified by the planner</a></div>";
			}

			s+="<table border=\"0\" align=\"center\"   width=\"900\" class=\"bord\" Frame=\"Box\">";
			//End of Get the projects which does not have due date

					int tableStart_Row = 0;
//System.out.println("loop_DueDate:"+dueDate_List);
			
			for(int i=0;i<dueDate_ListSize;i++)// the big for loop that loops for each due date based on which the projects and their chapters will be floated
				{

					s+="<tr><td>";

					 int currentDateCount=0;

					String loop_DueDate = dueDate_List.get(i).toString();
					String loop_DueDay = dueDay.get(i).toString();

						   if(current_date.equals(dueDate_List.get(i).toString()))
              						{
                  						currentDateCount++;
              						}

						    if(currentDateCount>0)
               						{
							 s+="<b>Today:</b>";
							}

					//System.out.println("dataStart_Row:"+i);

					String loop_dateday = loop_DueDate+", "+loop_DueDay;

					if(i==0){
						dataStart_Row = 5;
					}

					else{
					tableStart_Row++;
					dataStart_Row = tableStart_Row+1;//to be changed as dynamic increment
					}


					tableStart_Row = dataStart_Row+1;

					/*System.out.println("tableStart_Row:"+tableStart_Row);
					System.out.println("dataStart_Row:"+dataStart_Row);*/

					HSSFRow dueDate_Row = sheet.createRow((short) dataStart_Row);

					HSSFCell dueDate_Heading  = dueDate_Row.createCell((short) 0);
					HSSFCell dueDate_Heading1 = dueDate_Row.createCell((short) 1);
					HSSFCell dueDate_Heading2 = dueDate_Row.createCell((short) 2);
					HSSFCell dueDate_Heading3 = dueDate_Row.createCell((short) 3);
					HSSFCell dueDate_Heading4 = dueDate_Row.createCell((short) 4);
					HSSFCell dueDate_Heading5 = dueDate_Row.createCell((short) 5);
					HSSFCell dueDate_Heading6 = dueDate_Row.createCell((short) 6);

					sheet.addMergedRegion(new Region(dataStart_Row, (short) 0, dataStart_Row, (short) 6));

					dueDate_Heading.setCellStyle(columnHeaderStyle);
					dueDate_Heading1.setCellStyle(columnHeaderStyle);
					dueDate_Heading2.setCellStyle(columnHeaderStyle);
					dueDate_Heading3.setCellStyle(columnHeaderStyle);
					dueDate_Heading4.setCellStyle(columnHeaderStyle);
					dueDate_Heading5.setCellStyle(columnHeaderStyle);
					dueDate_Heading6.setCellStyle(columnHeaderStyle);

					dueDate_Heading.setCellValue(loop_dateday);//display due date, day as a table title

					/*** table header row ***/

					HSSFRow tableHeader_Row = sheet.createRow((short) tableStart_Row);

					HSSFCell colheading1 = tableHeader_Row.createCell((short) 0);
					colheading1.setCellStyle(columnHeaderStyle2);
					colheading1.setCellValue("Project");

					HSSFCell colheading2 = tableHeader_Row.createCell((short) 1);
					colheading2.setCellStyle(columnHeaderStyle2);
					colheading2.setCellValue("Received On");

					HSSFCell colheading3 = tableHeader_Row.createCell((short) 2);
					colheading3.setCellStyle(columnHeaderStyle2);
					colheading3.setCellValue("Stage");

					HSSFCell colheading4 = tableHeader_Row.createCell((short) 3);
					colheading4.setCellStyle(columnHeaderStyle2);
					colheading4.setCellValue("Chapters");

					
					HSSFCell colheading4_mss = tableHeader_Row.createCell((short) 4);
					colheading4_mss.setCellStyle(columnHeaderStyle2);
					colheading4_mss.setCellValue("MSS Count");


					HSSFCell colheading5 = tableHeader_Row.createCell((short) 5);
					colheading5.setCellStyle(columnHeaderStyle2);
					colheading5.setCellValue("Estimate");

					HSSFCell colheading6 = tableHeader_Row.createCell((short) 6);
					colheading6.setCellStyle(columnHeaderStyle2);
					colheading6.setCellValue("Remarks");

					/*** end of table header row **/

					s+="<table align=\"center\" class=\"bord\" border=\"1\" width=\"900\"><tr><td bgcolor=\"#CCCCCC\" colspan=\"7\"><font size=\"3\"><b>"+loop_DueDate+", "+loop_DueDay+"</b></font></tr></td>";

					s+="<tr><th width=\"200\">"+"Project"+"</th>"+"<th width=\"100\">"+"Received On"+"</th>"+"<th width=\"100\">"+"Stage"+"</th>"+"<th width=\"150\">"+"Chapters"+"</th>"+"<th width=\"50\">"+"Mss Count"+"</th>"+"<th width=\"150\">"+"Estimate"+"</th>"+"<th width=\"150\">"+"Remarks"+"</th></tr>";

					List proj_Name = new ArrayList();
					List stg_Name = new ArrayList();
					List projId=new ArrayList();
					List chapStgCode = new ArrayList();
                      			List dueDate=new ArrayList();
					//chapStgCode.addElement(rsDetails.getString(7));
                        		List chapterNo=new ArrayList();
                        		List chapterId=new ArrayList();
                        		List estimatedDate=new ArrayList();
                        		List estimatedTime=new ArrayList();
                        		List estimatedDateTime=new ArrayList();
                        		List TZ_estimatedDateTime=new ArrayList();
                        		List remarks=new ArrayList();
					List chapDueDate=new ArrayList();
					List dueFlag=new ArrayList();	
					List receivedDate=new ArrayList();
					List chapterProcess=new ArrayList();	

					//String dispDataQuery1 = "select b.proj_name, date_format(a.due_date,'%d-%b-%y'), c.stage,b.proj_id,a.stage,date(a.due_date),a.stage from chapter a, projects b, project_stage c,client g where (a.ship_date is null and c.stage_code=a.stage and a.proj_id=b.proj_id and date_format(a.due_date,'%d-%b-%y')='"+loop_DueDate+"' )or(a.ship_date = '0000-00-00 00:00:00' and c.stage_code=a.stage and a.proj_id=b.proj_id  and date_format(a.due_date,'%d-%b-%y')='"+loop_DueDate+"' ) group by a.stage,a.proj_id,a.due_date,a.estimated_time order by a.due_date ";

					String dispDataQuery1 = "select b.proj_name, date_format(a.due_date,'%d-%b-%y'), c.stage,b.proj_id,a.stage,date(a.due_date),a.stage,a.due_date_received,date_format(a.receipt_date,'%d-%b-%y'),a.chapter_process from chapter a, projects b, project_stage c where (a.chapter_no not like '%To Delete%'  and a.ship_date is null and c.stage_code=a.stage and a.proj_id=b.proj_id and date_format(a.due_date,'%d-%b-%y')='"+loop_DueDate+"' and a.due_date_received='1' and a.chapter_status=1 and a.chapter_deleted=0 and b.project_status <> '2' and a.stage not in ('INTART','CYD','PPD','FUS')  and b.dept_code = '"+loopDeptCode+"')or(a.chapter_no not like '%To Delete%' and a.ship_date = '0000-00-00 00:00:00' and c.stage_code=a.stage and a.proj_id=b.proj_id  and date_format(a.due_date,'%d-%b-%y')='"+loop_DueDate+"' and a.due_date_received='1' and a.chapter_status=1 and a.chapter_deleted=0 and b.project_status <> '2' and a.stage not in ('INTART','CYD','PPD','FUS') and b.dept_code = '"+loopDeptCode+"') group by a.stage,a.proj_id,a.due_date order by a.due_date ";

//System.out.println("dispDataQuery1 :"+dispDataQuery1);

					ResultSet rsDisplayData1 = statement.executeQuery(dispDataQuery1);

//System.out.println("loop_DueDate:"+loop_DueDate);



					while(rsDisplayData1.next())
					{
						proj_Name.add(rsDisplayData1.getString("b.proj_name"));
						stg_Name.add(rsDisplayData1.getString("c.stage"));
						dueDate.add(rsDisplayData1.getString(2));
						projId.add(rsDisplayData1.getString(4));
						 chapDueDate.add(rsDisplayData1.getString(6));
						chapStgCode.add(rsDisplayData1.getString(7));
                                		dueFlag.add(rsDisplayData1.getString(8));
						receivedDate.add(rsDisplayData1.getString(9));
						chapterProcess.add(rsDisplayData1.getString(10));
					}
					//if(loop_DueDate.equals("19-Dec-07"))
					
//System.out.println("proj_Name:"+proj_Name);

                               		//Check the data and display table data
                                	List dispProject=new ArrayList();
                                	List dispDue=new ArrayList();
					List dispReceivedDate=new ArrayList();
                                	List dispStage=new ArrayList();
                                	List dispChapters=new ArrayList();
                                	List dispEstimateDate=new ArrayList();
                                	List dispEstimateTime=new ArrayList();
                                	List dispEstimateDateTime=new ArrayList();
                                	List dispRemarks=new ArrayList();
                                	List dispChapters1=new ArrayList();
                                	List dispChapDue=new ArrayList();
					List TZ_dispEstimateDateTime = new ArrayList();
					List dispChapterProcess = new ArrayList();

                                	List dispDueFlag=new ArrayList();

					//System.out.println("proj_NameSize:"+proj_Name.size());

					for(int j=0;j<proj_Name.size();j++)
                              		  {

                                	    	chapterNo.clear();
                                        	chapterId.clear();
                                         	estimatedDate.clear();
                                        	estimatedTime.clear();
                                         	estimatedDateTime.clear();
						TZ_estimatedDateTime.clear();
                                         	remarks.clear();
                                    		dispDueFlag.clear();
                                    		dispReceivedDate.clear();
			

                                    		dispProject.clear();
                                    		dispDue.clear();
                                    		dispChapDue.clear();
                                    		dispStage.clear();
                                    		dispChapters.clear();
                                   		dispEstimateDate.clear();
                                    		dispEstimateTime.clear();
                                    		dispEstimateDateTime.clear();
						TZ_dispEstimateDateTime.clear();
                                    		dispRemarks.clear();
						dispChapterProcess.clear();

						tableStart_Row++;

					  	String proj_id=projId.get(j).toString();
                                    		String stge=chapStgCode.get(j).toString();
                                    		String due_dt=dueDate.get(j).toString();

				/********** Dynamically define the table cell names to hold the datas as in the loop **/

						
                                    //System.out.println("select a.chapter_no,a.chapter_id,date_format(a.estimated_time,'%d-%b-%y'),date(a.estimated_time),date_format(a.estimated_time,'%h:%i %p'),a.remark from chapter a where (a.ship_date is null and a.proj_id='"+proj_id+"' and a.stage='"+stge+"' and date_format(a.due_date,'%d-%b-%y')='"+due_dt+"') or (a.ship_date = '0000-00-00 00:00:00' and a.proj_id='"+proj_id+"' and a.stage='"+stge+"' and date_format(a.due_date,'%d-%b-%y')='"+due_dt+"')");

				//System.out.println("select a.chapter_no,a.chapter_id,date_format(a.estimated_time,'%d-%b-%y'),date(a.estimated_time),date_format(a.estimated_time,'%h:%i %p'),a.remark from chapter a where (a.ship_date is null and a.proj_id='"+proj_id+"' and a.stage='"+stge+"' and date_format(a.due_date,'%d-%b-%y')='"+due_dt+"') or (a.ship_date = '0000-00-00 00:00:00' and a.proj_id='"+proj_id+"' and a.stage='"+stge+"' and date_format(a.due_date,'%d-%b-%y')='"+due_dt+"')");

				ResultSet rsChap=statement.executeQuery("select a.chapter_no,a.chapter_id,date(a.estimated_time),date_format(a.estimated_time,'%d-%b-%y'),date_format(a.estimated_time,'%h:%i %p'),a.remark,date_format(a.estimated_time,'%d-%m-%Y %h:%i %p') from chapter a,projects b where (a.ship_date is null and a.proj_id='"+proj_id+"' and a.stage='"+stge+"' and date_format(a.due_date,'%d-%b-%y')='"+due_dt+"' and a.due_date_received='1' and a.chapter_status=1 and a.chapter_deleted=0 and b.project_status <> '2') or (a.ship_date = '0000-00-00 00:00:00' and a.proj_id='"+proj_id+"' and a.stage='"+stge+"' and date_format(a.due_date,'%d-%b-%y')='"+due_dt+"' and a.due_date_received='1' and a.chapter_status=1 and a.chapter_deleted=0 and b.project_status <> '2')");
                                     while(rsChap.next())
                                     {
                                      
                                         chapterNo.add(rsChap.getString(1));
                                         chapterId.add(rsChap.getString(2));
                                         
                                         String estimated_Date=rsChap.getString(4);
                                         if(rsChap.wasNull())
                                             estimated_Date="[No estimate yet]";
                                         //if(!estimatedDate.contains(estimated_Date)) //checks whether the list already contains the data.
                                            estimatedDate.add(estimated_Date);
                                         
                                         String estimated_Time=rsChap.getString(5);
                                         if(rsChap.wasNull())
                                             estimated_Time="[No estimate yet]";
                                         estimatedTime.add(estimated_Time);
                                         
                                         String estimated_DateTime=rsChap.getString(3);
					 if(rsChap.wasNull())
                                             estimated_DateTime="[No estimate yet]";
                                         estimatedDateTime.add(estimated_DateTime);


					  String TZ_estimated_DateTime=rsChap.getString(7);
                                          if(rsChap.wasNull())
                                             TZ_estimated_DateTime="[No estimate yet]";
                                         TZ_estimatedDateTime.add(TZ_estimated_DateTime);
                                         
                                         String chap_remarks=rsChap.getString(6);
                                         //out.println("r:"+chap_remarks);
                                          if(rsChap.wasNull())
                                             chap_remarks="";
                                          
                                         if(chap_remarks.equals(""))
                                         {
                                            //remarks.add(chap_remarks);
                                         }
                                         else
                                         {
                                             // out.println("else:"+chap_remarks);
                                             remarks.add(chap_remarks);
                                         }
                                     }//close of while(rsChap.next())

					//System.out.println("TZ_estimatedDateTime:"+TZ_estimatedDateTime);

				//Check for the estimated date
                                     String prev="";
                                     String next="";

				/*System.out.println("estimatedDate:"+estimatedDate);
				System.out.println("estimatedTime:"+estimatedTime);
				System.out.println("estimatedDateTime:"+estimatedDateTime);*/
		
                                     for(int e=0;e<estimatedDate.size();e++)
                                     {
                                            next=estimatedDate.get(e).toString();
                                            dispChapterProcess.add(chapterProcess.get(j));

						int get_estDtInd = estimatedDate.indexOf(estimatedDate.get(e));

                                            if(dispEstimateDate.contains(next))  //same value
                                            {
                                                if(!dispProject.contains(proj_Name.get(j)))
                                                     dispProject.add(proj_Name.get(j));
                                                if(!dispDue.contains(dueDate.get(j)))
                                                {
                                                    dispDue.add(dueDate.get(j));
                                                    dispChapDue.add(chapDueDate.get(j));
                                                    dispDueFlag.add(dueFlag.get(j));
                                                }
						 if(!dispReceivedDate.contains(receivedDate.get(j)))
                                                {
                                                    dispReceivedDate.add(receivedDate.get(j));
                                                  
                                                }
                                                if(!dispStage.contains(stg_Name.get(j)))
                                                    dispStage.add(stg_Name.get(j));
                                              
                                                
                                                if(!dispEstimateDate.contains(estimatedDate.get(e)))
                                                    dispEstimateDate.add(estimatedDate.get(e));
                                                if(!dispEstimateTime.contains(estimatedTime.get(e)))
                                                    dispEstimateTime.add(estimatedTime.get(e));
                                                if(!dispEstimateDateTime.contains(estimatedDateTime.get(e)))
                                                    dispEstimateDateTime.add(estimatedDateTime.get(e));

						

                                                if(!TZ_dispEstimateDateTime.contains(TZ_estimatedDateTime.get(get_estDtInd)))
                                                    TZ_dispEstimateDateTime.add(TZ_estimatedDateTime.get(get_estDtInd));



                                              //  if(!dispRemarks.contains(remarks.get(e)))
                                                //    dispRemarks.add(remarks.get(e));
                                               }
                                            else
                                            {
                                                //not same value
                                                dispProject.add(proj_Name.get(j));
                                                dispDue.add(dueDate.get(j));
						 dispReceivedDate.add(receivedDate.get(j));
                                                dispDueFlag.add(dueFlag.get(j));
                                                dispChapDue.add(chapDueDate.get(j));
                                                dispStage.add(stg_Name.get(j));
                                                
                                                //if(!dispEstimateDate.contains(estimatedDate.get(e)))
                                                    dispEstimateDate.add(estimatedDate.get(e));
                                                //if(!dispEstimateTime.contains(estimatedTime.get(e)))
                                                    dispEstimateTime.add(estimatedTime.get(e));
                                                //if(!dispEstimateDateTime.contains(estimatedDateTime.get(e)))
                                                    dispEstimateDateTime.add(estimatedDateTime.get(e));
 							TZ_dispEstimateDateTime.add(TZ_estimatedDateTime.get(get_estDtInd));
                                                //if(!dispRemarks.contains(remarks.get(e)))
                                                    //dispRemarks.add(remarks.get(e));
                                            }
                                            prev=next;
                                     }//close of for(e<estimatedDate)

				//System.out.println("dispProject:"+dispProject);
					//Display
                       			for(int t=0;t<dispProject.size();t++)
                       			{
						//System.out.println("Size:");

						//if(dispProject.size()>1){
						if((t!=0))
						tableStart_Row++;
						//}

						HSSFRow tableData_Row = sheet.createRow((short) tableStart_Row);


						s+="<tr><td valign=\"top\" width=\"200\">"+dispProject.get(t).toString()+"</td><td valign=\"top\" width=\"100\">"+dispReceivedDate.get(t).toString();


						
						   s+="</td><td valign=\"top\" width=\"100\">"+dispStage.get(t).toString()+"</td>";


						HSSFCell datacell1 = tableData_Row.createCell((short) 0);
						datacell1.setCellStyle(headerStyle);
						datacell1.setCellValue(dispProject.get(t).toString());

						HSSFCell datacell2 = tableData_Row.createCell((short) 1);
						datacell2.setCellStyle(headerStyle);
						datacell2.setCellValue(dispReceivedDate.get(t).toString());

						HSSFCell datacell3 = tableData_Row.createCell((short) 2);
						datacell3.setCellStyle(headerStyle);
						datacell3.setCellValue(dispStage.get(t).toString());
		
						   //Fetch chapters

							String estimated_dateTime=dispEstimateDateTime.get(t).toString();
                                			String estimatedChapter="";
                                			String chapNoQuery="";
							String mssCount="";
                                			int mss_count=0;

							String estimatedRemark="";
							
                                			int remarkCount=0;
						 
                                if(estimated_dateTime.equals("[No estimate yet]"))
                                    chapNoQuery="select a.chapter_no,a.chapter_id,trim(a.remark),mss_count from chapter a where (a.ship_date is null or a.ship_date = '0000-00-00 00:00:00') and a.proj_id='"+proj_id+"' and a.stage='"+stge+"' and date_format(a.due_date,'%d-%b-%y')='"+due_dt+"' and a.estimated_time is null and a.due_date_received='1' and a.chapter_status=1 and a.chapter_deleted=0 order by trim(a.remark) ";
                                else
                                    chapNoQuery="select a.chapter_no,a.chapter_id,trim(a.remark),mss_count from chapter a where (a.ship_date is null or a.ship_date = '0000-00-00 00:00:00') and a.proj_id='"+proj_id+"' and a.stage='"+stge+"' and date_format(a.due_date,'%d-%b-%y')='"+due_dt+"' and date(a.estimated_time)='"+estimated_dateTime+"' and a.due_date_received='1' and a.chapter_status=1 and a.chapter_deleted=0 order by trim(a.remark)";
                               //out.println(chapNoQuery);
                                ResultSet rsChapNo=statement.executeQuery(chapNoQuery);
                               			 	while(rsChapNo.next())
                                			{
                                    				 if(estimatedChapter.equals(""))
                                        						estimatedChapter=rsChapNo.getString(1);
                                    				 else
                                        						estimatedChapter=estimatedChapter+", "+rsChapNo.getString(1);

								
                                   					 mssCount=rsChapNo.getString(4);
                                   					 mss_count=mss_count+Integer.parseInt(mssCount);
                                    
                                    
                                    					if(estimatedRemark.equals(""))
                                    						{
                                        					if(!rsChapNo.getString(3).equals(""))
                                        						{
                                         						 //Have added ^~ to break the remarks. 
                                            
                                          							estimatedRemark=estimatedRemark+rsChapNo.getString(3)+" ^~ ("+rsChapNo.getString(1);
                                             
                                       							 }
                                    						}
                                    					else
                                    						{
                                        						//Check whether remark already available
                                        						if(estimatedRemark.indexOf(rsChapNo.getString(3))==-1)
                                        						{   //remark not already available
                                          							// estimatedRemark=estimatedRemark+","+rsChapNo.getString(3);
                                             							remarkCount++;
                                                						estimatedRemark=estimatedRemark+")"+","+rsChapNo.getString(3)+" ^~ ("+rsChapNo.getString(1);                                           
                                        						}
                                        						else
                                        						{
                                             							if(remarkCount>=0)
                                                						estimatedRemark=estimatedRemark+","+rsChapNo.getString(1);                                            
                                        						}
                                    						 }

                                			}//close of while(rsChapNo.next())
		
                                				if(!estimatedRemark.equals("")&&remarkCount>=0)
                                    				estimatedRemark=estimatedRemark+")";

							s+="<td valign=\"top\" width=\"150\">"+estimatedChapter+"</td>";

							HSSFCell datacell4 = tableData_Row.createCell((short) 3);
							datacell4.setCellStyle(headerStyle);
							datacell4.setCellValue(estimatedChapter.toString());

							s+="<td valign=\"top\" width=\"50\">"+mss_count+"</td>";		
							
							HSSFCell datacell4_mss = tableData_Row.createCell((short) 4);
							datacell4_mss.setCellStyle(headerStyle);
							datacell4_mss.setCellValue(mss_count);

							HSSFCell datacell5 = tableData_Row.createCell((short) 5);

                                			//Check the due date and estimate date are equal or not
                                			String compareValue="";
                                			String compareValue1="";
                               				// String dueDt=dispDue.get(t).toString();
                                			String dueDt=dispChapDue.get(t).toString();
                                
                                
                                			if(!estimated_dateTime.equals("[No estimate yet]"))
                                			{
                                    						//System.out.println("//////test");
                                    				ResultSet rsCheck=statement.executeQuery("select ('"+estimated_dateTime+"')>('"+dueDt+"'),('"+estimated_dateTime+"')=('"+dueDt+"') ");
                                   						// out.println("select ('"+estimated_dateTime+"')>('"+dueDt+"'),('"+estimated_dateTime+"')=('"+dueDt+"') ");
                                    				while(rsCheck.next())
                                    				{
                                        				compareValue=rsCheck.getString(1);
                                        				compareValue1=rsCheck.getString(2);
                                    				}
                                			}							//System.out.println("dispEstimateDate:"+dispEstimateDate);

//							//System.out.println("currentDateCount:"+currentDateCount);

							if(currentDateCount>0) //Current day
                               				 {


                                    				//Check whether the estimated date is before or after the due date.
                                    				//if it is before the due date then display it in green.
                                    				//if it is after the due date then display it in red.
                                    				//if it is same as due date then just display the time and not date.

								 if(compareValue.equals("1")) //after
                                    					{
                                        					//so display the date in red
                                       						if(!compareValue1.equals("1")) //Not Same day but before
                                       						{
                                          					// out.println("if");

											if(dispChapterProcess.get(t).toString().equals("0")){
										
												
											s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+dispEstimateDate.get(t).toString()+"</font></td>";

											datacell5.setCellStyle(headerStyle_color12);
											datacell5.setCellValue(dispEstimateDate.get(t).toString());

											}
											else{
											s+="<td valign=\"top\" width=\"150\"><font color=\"red\">"+dispEstimateDate.get(t).toString()+"</font></td>";

											datacell5.setCellStyle(headerStyle_color1);
											datacell5.setCellValue(dispEstimateDate.get(t).toString());
											}
										}
                                       						else
                                       						{

											

											if(dispChapterProcess.get(t).toString().equals("0")){
										
												
											s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+dispEstimateDate.get(t).toString()+"</font></td>";

											datacell5.setCellStyle(headerStyle_color12);
											datacell5.setCellValue(dispEstimateDate.get(t).toString());

											}
											else{
											s+="<td valign=\"top\" width=\"150\">"+dispEstimateDate.get(t).toString()+"</td>";
                                						
										
											datacell5.setCellStyle(headerStyle);
											datacell5.setCellValue(dispEstimateDate.get(t).toString());
 											}
										//System.out.println("Ramesh:"+displayString3);
										}
                                    					}
								else if(compareValue.equals("0")) //before
                                    					{
                                                                            String dispEstDate1=dispEstimateDate.get(t).toString();
                                                                            dispEstDate1=dispEstDate1.substring(0,dispEstDate1.lastIndexOf("-"));
									String dispEstTime1=TZ_dispEstimateDateTime.get(t).toString();

                                                                     	if(!db_timezone_code.equals("IST")){

													//returnTZValue(db_timezone_code,dispEstTime1,getIST_DiffParm,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts);


														if(dispChapterProcess.get(t).toString().equals("0")){
	
														s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+returnTZValue(db_timezone_code,dispEstTime1,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts)+"</font></td>";
														datacell5.setCellStyle(headerStyle_color12);
														datacell5.setCellValue(returnTZValue(db_timezone_code,dispEstTime1,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts));

														}
														else{

														s+="<td valign=\"top\" width=\"150\"><font color=\"green\">"+returnTZValue(db_timezone_code,dispEstTime1,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts)+"</font></td>";
														datacell5.setCellStyle(headerStyle_color2);
														datacell5.setCellValue(returnTZValue(db_timezone_code,dispEstTime1,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts));
														}
													}
													else{

													
														if(dispChapterProcess.get(t).toString().equals("0")){
	
														s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+dispEstimateTime.get(t).toString()+" ("+dispEstDate1+")"+"</font></td>";
									
													String displayString1violet = dispEstimateTime.get(t).toString()+" ("+dispEstDate1+")";
													datacell5.setCellStyle(headerStyle_color12);
													datacell5.setCellValue(displayString1violet);
													}
													else{

													s+="<td valign=\"top\" width=\"150\"><font color=\"green\">"+dispEstimateTime.get(t).toString()+" ("+dispEstDate1+")"+"</font></td>";
									
													String displayString1 = dispEstimateTime.get(t).toString()+" ("+dispEstDate1+")";
													datacell5.setCellStyle(headerStyle_color2);
													datacell5.setCellValue(displayString1);											
													}
												}
									}
                                   				else if(compareValue.equals("")) //No estimate yet
                                    					{

										if(dispChapterProcess.get(t).toString().equals("0")){

										s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+dispEstimateTime.get(t).toString()+"</font></td>";
										datacell5.setCellStyle(headerStyle_color12);
										datacell5.setCellValue(dispEstimateTime.get(t).toString());
										}
										else{
										s+="<td valign=\"top\" width=\"150\">"+dispEstimateTime.get(t).toString()+"</td>";
										datacell5.setCellStyle(headerStyle);
										datacell5.setCellValue(dispEstimateTime.get(t).toString());
										}
									}
								}
                                				else
                                					{
										String display_NewDate = "";

                                    						//Display date
                                    						//Check whether the estimated date is before or after the due date.
                                   						//if it is before the due date then display it in green.
                                    						//if it is after the due date then display it in red.

										 if(compareValue.equals("1")) //after
                                    							{
                                                                                     
                                                                                            if(current_date.equals(dispEstimateDate.get(t))) 
                                                                                                {
                                                                                                    String dispEstDate=dispEstimateDate.get(t).toString();

												/*System.out.println("dispEstDate:"+dispEstDate);
												System.out.println("TZ_dispEstimateDateTime:"+TZ_dispEstimateDateTime);*/

                                                                                                    dispEstDate=dispEstDate.substring(0,dispEstDate.lastIndexOf("-"));

													String dispEstTime=TZ_dispEstimateDateTime.get(t).toString();

												        String displayString2=dispEstimateTime.get(t).toString()+" ("+dispEstDate+")";
                                                                                     
                                                                                                    	
                                                                                                  	if(!db_timezone_code.equals("IST")){

													//returnTZValue(db_timezone_code,dispEstTime,getIST_DiffParm,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts);
														if(dispChapterProcess.get(t).toString().equals("0")){
														s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+returnTZValue(db_timezone_code,dispEstTime,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts)+"</font></td>";
														datacell5.setCellStyle(headerStyle_color12);
														datacell5.setCellValue(returnTZValue(db_timezone_code,dispEstTime,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts));
														}				
														else{
														s+="<td valign=\"top\" width=\"150\"><font color=\"red\">"+returnTZValue(db_timezone_code,dispEstTime,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts)+"</font></td>";
														datacell5.setCellStyle(headerStyle_color1);
														datacell5.setCellValue(returnTZValue(db_timezone_code,dispEstTime,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts));
														}												
													}
													else{

														if(dispChapterProcess.get(t).toString().equals("0")){															

															s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+dispEstimateTime.get(t).toString()+" ("+dispEstDate+")"+"</font></td>";
															datacell5.setCellStyle(headerStyle_color12);
															datacell5.setCellValue(displayString2);
															}														
															else{
															s+="<td valign=\"top\" width=\"150\"><font color=\"red\">"+dispEstimateTime.get(t).toString()+" ("+dispEstDate+")"+"</font></td>";
															datacell5.setCellStyle(headerStyle_color1);
															datacell5.setCellValue(displayString2);
															}
													}
													
														
                                                                                                   }
                                                                                                else
                                                                                                    {
													if(dispChapterProcess.get(t).toString().equals("0")){
  														   s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+dispEstimateDate.get(t).toString()+"</font></td>";
                                                                                                        	   datacell5.setCellStyle(headerStyle_color12);
														   datacell5.setCellValue(dispEstimateDate.get(t).toString());
													}
													else{
                                                                                                    		   s+="<td valign=\"top\" width=\"150\"><font color=\"red\">"+dispEstimateDate.get(t).toString()+"</font></td>";
                                                                                                        	   datacell5.setCellStyle(headerStyle_color1);
														   datacell5.setCellValue(dispEstimateDate.get(t).toString());
													}
                                                                                                   }
                                                                                               
											}
                                   					 	 else if(compareValue.equals("0")) //before
                                    							{
                                        							//System.out.println("0");
                                       								if(!compareValue1.equals("1")) //Not Same day but before
                                        								{
                                            									//display in green
                                            									//System.out.println("comp1");

														if(dispChapterProcess.get(t).toString().equals("0")){

											 			s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+dispEstimateDate.get(t).toString()+"</font></td>";
														datacell5.setCellStyle(headerStyle_color12);
														datacell5.setCellValue(dispEstimateDate.get(t).toString());
														}
														else{
	
											 			s+="<td valign=\"top\" width=\"150\"><font color=\"green\">"+dispEstimateDate.get(t).toString()+"</font></td>";
														datacell5.setCellStyle(headerStyle_color2);
														datacell5.setCellValue(dispEstimateDate.get(t).toString());
														}
												         }
                                        								else
                                        								 {
                                            									//System.out.println("else");

														
														if(dispChapterProcess.get(t).toString().equals("0")){
											 			s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+dispEstimateDate.get(t).toString()+"</font></td>";
														datacell5.setCellStyle(headerStyle_color12);
														datacell5.setCellValue(dispEstimateDate.get(t).toString());
														}
														else{
	
														s+="<td valign=\"top\" width=\"150\">"+dispEstimateDate.get(t).toString()+"</td>";
														datacell5.setCellStyle(headerStyle);
														datacell5.setCellValue(dispEstimateDate.get(t).toString());
														}
													 }
											}
                                     							else if(compareValue.equals("")) //No estimate yet
                                    							{

											if(dispChapterProcess.get(t).toString().equals("0")){
												s+="<td valign=\"top\" width=\"150\"><font color=\"#660099\">"+dispEstimateTime.get(t).toString()+"</font></td>";	
												datacell5.setCellStyle(headerStyle_color12);
												datacell5.setCellValue(dispEstimateTime.get(t).toString());
											}
											else{		
												s+="<td valign=\"top\" width=\"150\">"+dispEstimateTime.get(t).toString()+"</td>";
	
												datacell5.setCellStyle(headerStyle);
												datacell5.setCellValue(dispEstimateTime.get(t).toString());
											}
											}
       									}///stopping here

								s+="<td valign=\"top\" width=\"150\">";
								
								int index=0;
                             					int count=0;
                             					String match_exp="^~";
                             					int indexVal=0;
                             
                             					while ((index = estimatedRemark.indexOf(match_exp, index)) != -1)
                             					{
                                					++index;
                                					indexVal=index;
                                					++count;
                                  					//out.println("Count:"+count);
                            					}
							
								
                         				 /***Check for the number of occurance of ^~. 
                             						 **If the number of occurance is  1 then  
                                    					1)If the number of chapter in remark is equal to the number of chapters then just display the remarks alone 
                                    					2)If not then display the remarks with chapter.
                                    
                                    					Eg:1)If remarks is 
                                            					Hold (chapter 1,chapter 2)
                                         					and chapter column is
                                            						chapter 1, chapter 2 
                                         					then display remark as Hold
 
                                       						2)If chapter column is -> chapter 1,chapter 2,chapter 3 then display remark as Hold (chapter 1,chapter 2)   
                              								**If more than one then display remarks and its corresponding chapters.
                           						*/
                           
                             						//If count=1 then just display the remark and not the chapter.

							 //If count=1 then just display the remark and not the chapter.
                             				if(count==1)
                             				{
                                 				//Get the number of chapters inside the remark
                                 			String remarkChapter=estimatedRemark.substring(indexVal+2,estimatedRemark.length());
                                 			int remarkChapCount=remarkChapter.split(",").length;
                                 			int chapterCount=estimatedChapter.split(", ").length;
                                			 if(remarkChapCount==chapterCount)
                                 				{
                                     						//display only the remarks
                                     					estimatedRemark=estimatedRemark.substring(0,indexVal-1);
                                 				}
                                 			else
                                 				{
                                    						//display remarks and chapters 
                                    					estimatedRemark=estimatedRemark.replaceAll("\\^~"," ");
                                 				 }
                             				}
                             				else if(count>1)
                             				{
                                 			estimatedRemark=estimatedRemark.replaceAll("\\^~"," ");
                             				}
							s+=estimatedRemark+"</td></tr>";
						HSSFCell datacell6 = tableData_Row.createCell((short) 6);
						datacell6.setCellStyle(headerStyle);
						datacell6.setCellValue(estimatedRemark);

					}//close of for(t<dispProject.size())

					



				  }//close of for(j<proj_Name.size()) loop


					s+="</table></td></tr>";//end of child table
					s+="<tr><td colspan=\"6\">&nbsp; </td></tr>";


				}//close of for (i<dueDate_ListSize) loop


						s+="</table>";


					
			 if(dueProjId.size()>0)
      				{ 

				tableStart_Row+=3;//int rowFrom, short colFrom, int rowTo, short colTo) 


					sheet.addMergedRegion(new Region(tableStart_Row, (short) 0, tableStart_Row, (short) 2));

							HSSFRow tableHeader_Row_heading = sheet.createRow((short) tableStart_Row);



							HSSFCell colheading1_heading  = tableHeader_Row_heading.createCell((short) 0);
							colheading1_heading.setCellStyle(headerStyle1_withNoBorder);
							colheading1_heading.setCellValue("Projects without a due date from the planner");
					
						tableStart_Row++;

					HSSFRow tableHeader_Row_noDue = sheet.createRow((short) tableStart_Row);

					HSSFCell colheading1_noDue  = tableHeader_Row_noDue .createCell((short) 0);
					colheading1_noDue.setCellStyle(columnHeaderStyle2);
					colheading1_noDue.setCellValue("Project");

					HSSFCell colheading2_noDue  = tableHeader_Row_noDue .createCell((short) 1);
					colheading2_noDue.setCellStyle(columnHeaderStyle2);
					colheading2_noDue.setCellValue("Received On");

					HSSFCell colheading3_noDue  = tableHeader_Row_noDue .createCell((short) 2);
					colheading3_noDue.setCellStyle(columnHeaderStyle2);
					colheading3_noDue.setCellValue("Stage");

					HSSFCell colheading4_noDue  = tableHeader_Row_noDue .createCell((short) 3);
					colheading4_noDue.setCellStyle(columnHeaderStyle2);
					colheading4_noDue.setCellValue("Chapters");

					HSSFCell colheading4_noDuemss  = tableHeader_Row_noDue .createCell((short) 4);
					colheading4_noDuemss.setCellStyle(columnHeaderStyle2);
					colheading4_noDuemss.setCellValue("Mss Count");

					HSSFCell colheading5_noDue  = tableHeader_Row_noDue .createCell((short) 5);
					colheading5_noDue.setCellStyle(columnHeaderStyle2);
					colheading5_noDue.setCellValue("Estimate");

					HSSFCell colheading6_noDue  = tableHeader_Row_noDue .createCell((short) 6);
					colheading6_noDue.setCellStyle(columnHeaderStyle2);
					colheading6_noDue.setCellValue("Remarks");
				

				//System.out.println("tableStart_Row:"+tableStart_Row);
					
				 s+="<a name=\"anchor1\"></a>";
      					s+="<div id=\"block1\">";
        				s+=" <table  border=\"0\" width=\"900\" align=\"center\">";
             				s+=" <tr><td><a href=\"#\" onclick=\"javascript:ScrollWin.scroll('0'); return false;\">Back to top</a>";
                      			s+="&nbsp;&nbsp;&nbsp; <font size=\"3\"><b> Projects without a due date from the planner </b></font></td></tr>";
             
             					s+=" <tr><td align=\"center\" width=\"100%\">";

						s+="<table class=\"bord\" border=\"1\" width=\"100%\" align=\"center\">";
						s+="<tr>";
                      				s+="<th width=\"200\">Project </th>";
                      				s+="<th width=\"100\">Received On </th>";
                     				s+="<th width=\"100\">Stage </th>";
                      				s+="<th width=\"150\">Chapters </th>";
                      				s+="<th width=\"50\">Mss Count</th>";
                      				s+="<th width=\"150\">Estimate </th>";
                      				s+="<th width=\"150\">Remarks </th>";
                     				s+="</tr>";

						for(int p=0;p<dueProjId.size();p++)
                     					{
                    						tableStart_Row++;


								HSSFRow tableData_Row_noDue = sheet.createRow((short) tableStart_Row);

                        					s+="<tr>";
                            					s+="<td width=\"200\">"+dueProjName.get(p)+"</td>";
                            					s+="<td width=\"100\">"+dueReceivedDate.get(p)+"</td>";

                            					s+="<td width=\"100\">"+dueStage.get(p)+"</td>";
                            					s+="<td width=\"150\">"+dueChapNo.get(p)+"</td>";
                            					s+="<td width=\"50\">"+dueMssCount.get(p)+"</td>";
                            					
                            								
								
								HSSFCell datacell1_noDue = tableData_Row_noDue.createCell((short) 0);
								datacell1_noDue.setCellStyle(headerStyle);
								datacell1_noDue.setCellValue(dueProjName.get(p).toString());

								HSSFCell datacell2_noDue = tableData_Row_noDue.createCell((short) 1);
								datacell2_noDue.setCellStyle(headerStyle);
								datacell2_noDue.setCellValue(dueReceivedDate.get(p).toString());

								HSSFCell datacell3_noDue = tableData_Row_noDue.createCell((short) 2);
								datacell3_noDue.setCellStyle(headerStyle);
								datacell3_noDue.setCellValue(dueStage.get(p).toString());

									
								HSSFCell datacell4_noDue = tableData_Row_noDue.createCell((short) 3);
								datacell4_noDue.setCellStyle(headerStyle);
								datacell4_noDue.setCellValue(dueChapNo.get(p).toString());

								
									
								HSSFCell datacell4_noDue_mss = tableData_Row_noDue.createCell((short) 4);
								datacell4_noDue_mss.setCellStyle(headerStyle);
								datacell4_noDue_mss.setCellValue(dueMssCount.get(p).toString());


                                                                HSSFCell datacell5_noDue = tableData_Row_noDue.createCell((short) 5);
								datacell5_noDue.setCellStyle(headerStyle);
                                                                
                                                                 if(current_date.equals(dueEstimatedDate.get(p))) 
                                                                {
                                                                    String dispEstDate=dueEstimatedDate.get(p).toString();
                                                                    dispEstDate=dispEstDate.substring(0,dispEstDate.lastIndexOf("-"));
								String due_dispEstTime=dueTZ_estimatedDateTime.get(p).toString();	
													if(!db_timezone_code.equals("IST")){

													//returnTZValue(db_timezone_code,due_dispEstTime,getIST_DiffParm,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts);

													s+="<td valign=\"top\" width=\"150\">"+returnTZValue(db_timezone_code,due_dispEstTime,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts)+"</td>";
													
													datacell5_noDue.setCellValue(returnTZValue(db_timezone_code,due_dispEstTime,getIST_DiffParm,db_timezone_diffparam,IST_hr,IST_mt,TZ_diffhrs,TZ_diffmts));
													}
													else{
 

                                                                    s+="<td width=\"150\">"+dueEstimatedTime.get(p)+" ("+dispEstDate+")"+"</td>";
                                                                    String displayString3 = dueEstimatedDate.get(p).toString()+" ("+dispEstDate+")";
                                                                 
                                                                    datacell5_noDue.setCellValue(displayString3);
									}

									//System.out.println("Ramesh:"+displayString3);
                                                                
                                                                 }else{
                                                                     s+="<td width=\"150\">"+dueEstimatedDate.get(p)+"</td>";
                                                                     datacell5_noDue.setCellValue(dueEstimatedDate.get(p).toString());                                                                
                                                                 }
								
                                                                s+="<td width=\"150\">"+dueRemark.get(p)+"</td></tr>";	

								HSSFCell datacell6_noDue = tableData_Row_noDue.createCell((short) 6);
								datacell6_noDue.setCellStyle(headerStyle);
								datacell6_noDue.setCellValue(dueRemark.get(p).toString());

                 					} 
			}//close of if(dueProjId.size())

			s+="</table> </td></tr></div></div></div>";
		

					workBook.write(stream);

					PrintStream p = new PrintStream( stream2 );

					p.println(s);

			 		p.close();

			 		stream.close();

					

					System.out.println("End Of Chennai Production Report:"+loopDeptName);









   }//close of for int l1<getTz_code


File delFolder = new File(ISTFolderName);

String fileList[]=delFolder.list();

for(int fileIdx=0;fileIdx<fileList.length;fileIdx++)
{
	System.out.println(fileList[fileIdx].toString());

	System.out.println("FileName:"+ISTFolderName+"\\"+fileList[fileIdx].toString());

	String delFileName = fileList[fileIdx].toString();

File delFile = new File(ISTFolderName+"\\"+delFileName);

try{

boolean delResult = delFile.delete();

System.out.println("delResult:"+delResult);

}catch(Exception e){

System.out.println("FileDel:"+e);

}


}


delFolder.delete();



 }//close of for(deptCode)
con.close();

}catch(Exception e)
{
	System.out.println(e);
}

return null;
}


/******************** end of generate report *******/

/***** returnTZValue *******/

public String returnTZValue(String getdb_timezone_code,String getTZ_Estimatedvalue,String IST_Diffparam,String getdb_timezone_diffparam,String getIST_hr,String getIST_mt,String getTZ_diffhrs,String getTZ_diffmts){


													//Calendar zonecal = new GregorianCalendar(TimeZone.getTimeZone("IST"));

													String display_NewDate  = "";

													String db_timezone_diffparam = getdb_timezone_diffparam;
							
													String TZ_dispEstimateDateTime = getTZ_Estimatedvalue;
													String db_timezone_code = getdb_timezone_code;
													String getIST_DiffParm = IST_Diffparam;
													

													int IST_hr = Integer.parseInt(getIST_hr);
													int IST_mt = Integer.parseInt(getIST_mt);
													int TZ_diffhrs = Integer.parseInt(getTZ_diffhrs);
													int TZ_diffmts = Integer.parseInt(getTZ_diffmts);

													
													if(db_timezone_diffparam.equals("-"))
													{
														TZ_diffhrs = -TZ_diffhrs;
														TZ_diffmts = -TZ_diffmts;
													}
																									String set_yr = TZ_dispEstimateDateTime.substring(TZ_dispEstimateDateTime.lastIndexOf("-")+1,TZ_dispEstimateDateTime.lastIndexOf("-")+5);
													String set_mnth = TZ_dispEstimateDateTime.substring(3,5);
													String set_dat = TZ_dispEstimateDateTime.substring(0,2);

													String set_hr = TZ_dispEstimateDateTime.substring(TZ_dispEstimateDateTime.lastIndexOf(":")-2,TZ_dispEstimateDateTime.lastIndexOf(":"));
													String set_mt =TZ_dispEstimateDateTime.substring(TZ_dispEstimateDateTime.lastIndexOf(":")+1,TZ_dispEstimateDateTime.lastIndexOf(":")+3);
													String set_AM_PM =TZ_dispEstimateDateTime.substring(TZ_dispEstimateDateTime.lastIndexOf(":")+4);									
													//System.out.println("TZ_dispEstimateDateTime :"+TZ_dispEstimateDateTime );
													set_AM_PM=set_AM_PM.trim();
													int cal_AM_PM = 0;
													int cal_yr = Integer.parseInt(set_yr.trim());
													int cal_mnth = Integer.parseInt(set_mnth.trim());
													int cal_dat = Integer.parseInt(set_dat.trim());
													int cal_hr = Integer.parseInt(set_hr.trim());
													int cal_mt = Integer.parseInt(set_mt.trim());
													if(set_AM_PM.equals("AM"))
													{
													//cal_AM_PM = 1 ;
														if(cal_hr==12){
														cal_hr=00;
														}
													
														//System.out.println("cal_hr:"+cal_hr);
													}
													else if(set_AM_PM.equals("PM")){

														if(cal_hr!=12){						
														cal_hr+=12;
														}
													}

													if(!db_timezone_code.equals("IST")){
													
													//cal_mnth = cal_mnth-1;

													Calendar zonecal = new GregorianCalendar(cal_yr,cal_mnth,cal_dat,cal_hr,cal_mt);


													zonecal.set(Calendar.YEAR,cal_yr);
													zonecal.set(Calendar.MONTH,cal_mnth-1);
													zonecal.set(Calendar.DATE,cal_dat);
													zonecal.set(Calendar.HOUR,cal_hr);
													zonecal.set(Calendar.MINUTE,cal_mt);
													//zonecal.set(Calendar.AM_PM,cal_AM_PM);

														if(getIST_DiffParm.equals("+"))
														{
															IST_hr = -IST_hr;
															IST_mt = -IST_mt; 		
														}		
														
													zonecal.add(Calendar.HOUR,IST_hr);
													zonecal.add(Calendar.MINUTE,IST_mt);

													zonecal.add(Calendar.HOUR,TZ_diffhrs);
													zonecal.add(Calendar.MINUTE,TZ_diffmts);

													if(cal_hr==12&&cal_AM_PM==1){
													//cal_AM_PM = 0;
													}

													//System.out.println("cal_AM_PM:"+cal_AM_PM);

													
													//zonecal.set(Calendar.AM_PM,cal_AM_PM);
													//System.out.println("zonecal.getTime():"+zonecal.getTime());

													/*if(set_hr.equals("12")){
													System.out.println("zonecal.getTime():"+zonecal.getTime());

													int addedval = cal_hr-IST_hr+TZ_diffhrs;

													System.out.println("Added Value:"+addedval);

													}*/													

 													SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aaa (dd-MMM)");
         												display_NewDate = sdf.format(zonecal.getTime());
													}
												       
										return display_NewDate;

}//close of returnTZValue


/**** End Of ReturnTZValue ***********/






public static void main(String args[]){
 
        new ChennaiProductionRpt1();
        System.out.println("Task scheduled%");	

}

/************************************************************************************/


}//close of class
