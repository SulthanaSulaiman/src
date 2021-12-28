/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects.chapters;
import java.util.*;
import java.io.Serializable;
import java.io.*;
import java.sql.*;
import connection.DBconnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Gandhimathidevic
 */
public class ExportExcelDataThrPerl {
    private String inputV1;
    private String columnFrom;
    private String columnTo;
    private String milestonesfrom;
    private String milestonesTo;
    private String fileNam="";
    private String rowFrom="";
    private String rowTo;
    private int chap_id;
    private int chap_id1;
    private String chap_Name;
    private String proj_id;
    private String milestones;
    private int milestneCount;
    private String milestneDuedate;
    private String milstnVsprojects;
    private String pages;
    private int milestneidVsproj;
    private List milestonesInstrd;
    private String havToupdduedate;
    private String havToupdChapPag;
    private String havToupdmilestn;
    private String havToupdChap;
    private List stageNameL = new ArrayList();
    private List stagedueDateL = new ArrayList();
    private List stageactualDateL = new ArrayList();
    private List stageIdL = new ArrayList();

    public List getStageNameList(){
    return stageNameL;
    }
    public void setStageNameList(List stageNameL){
    this.stageNameL = stageNameL;
    }
     public List getStageIdList(){
    return stageIdL;
    }
    public void setStageIdList(List stageIdL){
    this.stageIdL = stageIdL;
    }
    public List getdueDateList(){
    return stagedueDateL;
    }
    public void setactualDateList(List stageactualDateL){
    this.stageactualDateL = stageactualDateL;
    }
    public List getactualDateList(){
    return stageactualDateL;
    }
    public void setdueDateList(List stagedueDateL){
    this.stagedueDateL = stagedueDateL;
    }
    public String gethavToupdChap(){
    return havToupdChap;
   }
    public void sethavToupdChap(String havToupdChap){
    this.havToupdChap = havToupdChap;
   }

    public String gethavToupdmilestn(){
    return havToupdmilestn;
   }
    public void sethavToupdmilestn(String havToupdmilestn){
    this.havToupdmilestn = havToupdmilestn;
   }
    public String gethavToupdChapPag(){
    return havToupdChapPag;
   }
    public void sethavToupdChapPag(String havToupdChapPag){
    this.havToupdChapPag = havToupdChapPag;
   }

    public String gethavToupdduedate(){
    return havToupdduedate;
   }
    public void sethavToupdduedate(String havToupdduedate){
    this.havToupdduedate = havToupdduedate;
   }
     public String getPages(){
    return pages;
   }
    public void setPages(String pages){
    this.pages = pages;
   }
    public void setmilstnVsprojects(String milstnVsprojects){
    this.milstnVsprojects = milstnVsprojects;
   }
    public String getmilstnVsprojects(){
    return milstnVsprojects;
   }
     public void setmilstnIdVsprojects(int milestneidVsproj){
    this.milestneidVsproj = milestneidVsproj;
   }
    public int getmilstnIdVsprojects(){
    return milestneidVsproj;
   }
    public void setMilestonesDue(String milestneDuedate){
    this.milestneDuedate = milestneDuedate;
   }
    public String getMilestonesDue(){
    return milestneDuedate;
   }
    public void setMilestones(String milestones){
    this.milestones = milestones;
   }
    public String getMilestones(){
    return milestones;
   }
  public void setMilestonesCnt(int milestneCount){
    this.milestneCount = milestneCount;
   }
    public int getMilestonesCnt(){
    return milestneCount;
   }
    public void setFilenam(String fileNam){
    this.fileNam = fileNam;
   }
    public String getFilenam(){
    return fileNam;
   }
    public void setrowFrom(String rowFrom){
    this.rowFrom = rowFrom;
   }
     public void setrowTo(String rowTo){
    this.rowTo = rowTo;
   }
    public String getrowFrom(){
    return rowFrom;
   }
    public String getrowTo(){
    return rowTo;
   }
     public void setColumnTo(String columnTo){
    this.columnTo = columnTo;
   }
    public String getColumnTo(){
    return columnTo;
   }
    public void setColumnFrom(String columnFrom){
    this.columnFrom = columnFrom;
   }
    public String getColumnFrom(){
    return columnFrom;
   }
    public void setChap_id(int chap_id){
	this.chap_id = chap_id;
}
public int getChap_id(){
	return chap_id;
}
  public void setChap_id1(int chap_id1){
	this.chap_id1 = chap_id1;
}
public int getChap_id1(){
	return chap_id1;
}
public void setChap_Name(String chap_Name){

	this.chap_Name = chap_Name;
}
public String getChap_Name(){
	return chap_Name;
}
public void setProj_id(String proj_id){

	this.proj_id = proj_id;
}
public String getProj_id(){
	return proj_id;
}

    public ExportExcelDataThrPerl() {
    }
    public ExportExcelDataThrPerl excelDataThrPerl(ExportExcelDataThrPerl exp1){
    final List<String> commands = new ArrayList<String>();
    List output = new ArrayList();
    boolean bool = false;
    boolean bool1 = false;
    String uploadXcelfile="";
    String uploadXcelfile1="";
    pathfinder.util.ReadXLSXFile xcel = new pathfinder.util.ReadXLSXFile();
    File f = null;
    File f1 = null;
  try{
                     //D:\\ScheduleExcel
         f = new File("C:\\ScheduleExcel\\"+ exp1.getFilenam()+ ".xls");
         bool = f.exists();
         if(bool == true)
         {
         uploadXcelfile = "C:\\ScheduleExcel\\"+ exp1.getFilenam()+ ".xls";
         }
 
        f1 = new File("C:\\ScheduleExcel\\"+ exp1.getFilenam()+ ".xlsx");
        bool1 = f1.exists();
        if(bool1 == true)
         {
            //xcel.readingXcel("D:\\Gandhimathi\\TestingApplication\\LivepathfinderCode\\"+ exp1.getFilenam()+ ".xlsx");
          }
 
  }
  catch(Exception e)
  {
      e.printStackTrace();
  }
    System.out.println("uploadXcelfile"+uploadXcelfile);
    String columnFrmXcl = exp1.getColumnFrom();
    String columntoXcl = exp1.getColumnTo();
    String rowFromXcl = exp1.getrowFrom();
    String rowtoXcl = exp1.getrowTo();
    String inputV="";
    int filechk = 0;
    if (bool1 == true && bool == true){
        filechk=1;
    }
    if (bool1 != true && bool == true){
    	commands.add("\\\\10.1.1.100\\Automation\\Perl\\bin\\perl.exe");
	commands.add("C:\\ScheduleExcel\\excel.pl");
	commands.add(uploadXcelfile);
	commands.add(rowFromXcl);
	commands.add(rowtoXcl);
	commands.add(columnFrmXcl);
	commands.add(columntoXcl);
	//ProcessBuilder pb = new ProcessBuilder("\\\\10.1.1.100\\Automation\\Perl\\bin\\perl.exe", "excel.pl");
	ProcessBuilder pb = new ProcessBuilder(commands);
        try {
           Process p=pb.start();
           BufferedReader stdout = new BufferedReader(
           new InputStreamReader(p.getInputStream())
            );
            BufferedWriter stdin = new BufferedWriter(
                new OutputStreamWriter(p.getOutputStream())
            );
            
            //write to perl script's stdin
            stdin.write("testdata");
            //assure that that the data is written and does not remain in the buffer
            stdin.flush();
            //send eof by closing the scripts stdin
            stdin.close();
           inputV =stdout.readLine().toString();
             exp1.setInput(inputV);
        //cpy.DeleteFileExample(uploadXcelfile);
            
             }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }
    else if (bool1 == true)
         {//inputV = xcel.readingXcel("D:\\Gandhimathi\\TestingApplication\\LivepathfinderCode\\"+ exp1.getFilenam()+ ".xlsx",rowFromXcl,rowtoXcl);
        inputV = xcel.readingXcel("C:\\ScheduleExcel\\"+ exp1.getFilenam()+ ".xlsx",rowFromXcl,rowtoXcl);
          exp1.setInput(inputV);
        }
        else if(filechk == 1)
        {
        inputV = "Do not place both type of files in a folder";
        exp1.setInput(inputV);
       }
        return exp1;
    	  }

  public String getInput(){
      System.out.println(inputV1);
            return inputV1;
  }

  public void setInput(String inputV1){

      this.inputV1=inputV1;
  }
  public int getChapIdXcel(){
      Connection con=null;
      String addChapterPlanId1="";
      int addChapterPlanId=0;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("select max(chap_id) from chapterdetailsbasedexcel");
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    addChapterPlanId = 1;
                } else {
                    addChapterPlanId = Integer.parseInt(rs.getString(1))+1;
                }
            }
      
       statement.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Plan Creation:"+sqle);
        }
      return addChapterPlanId;
  }
  public void insertChaptrDetailsToDB(ExportExcelDataThrPerl exp2){
      Connection con=null;
      int addChapterPlan;
      int chpId;
      String chpNam="";
     
      String projid="";
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();
        chpId=exp2.getChap_id();
        chpNam=exp2.getChap_Name();
        projid=exp2.getProj_id();
        
                //System.out.println("insert into chapter_plan(plan_id,chapter_id,milestone_id,end_date,planned_Date) "+
                    //" values ('"+planId+"',"+chapterId+"','"+mileStoneCode+"','"+endDate+"' )  ");
      addChapterPlan = statement.executeUpdate("insert into chapterdetailsbasedexcel(chap_id,chap_Name,proj_id,addeddate)"+
" values ('"+chpId+"','"+chpNam+"','"+projid+"',CURRENT_TIMESTAMP())");

       statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Plan Creation:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Plan Creation:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
        }

  }
  public void insertmilestonesToDB(ExportExcelDataThrPerl exp3){
      Connection con=null;
      int addChapterPlan;
      int milestonId=0;
      String projid="";
      String milestone="";
      
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();
        projid=exp3.getProj_id();
        milestone=exp3.getmilstnVsprojects();
        milestonId=exp3.getmilstnIdVsprojects();
        

                //System.out.println("insert into chapter_plan(plan_id,chapter_id,milestone_id,end_date,planned_Date) "+
                    //" values ('"+planId+"',"+chapterId+"','"+mileStoneCode+"','"+endDate+"' )  ");
      addChapterPlan = statement.executeUpdate("insert into projectanditsmilestones(milestoneId,projId,milestones)"+
" values ('"+milestonId+"','"+projid+"','"+milestone+"')");

       statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Plan Creation:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Plan Creation:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
        }

  }
  public void insertChaptrAndMilestDetailsToDB(ExportExcelDataThrPerl exp2){
      Connection con=null;
      int addChapterPlan;
      int chpId;
      String chpNam="";
      String chpNam1="";
      String projid="";
      String milstn="";
      int mileCont =0;
      String duedat="";
      String duedat1="";
      String pages;
      
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();
        chpId=exp2.getChap_id1();
        chpNam=exp2.getChap_Name();
        projid=exp2.getProj_id();
        mileCont=exp2.getMilestonesCnt();
        milstn=exp2.getMilestones();
        duedat=exp2.getMilestonesDue();
       // SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//duedat1=formatter1.format(duedat);

        if(duedat.equals("NA")){
        duedat="0000-00-00";
        }
        pages=exp2.getPages();
        //chpNam1 = chpNam.substring(0,chpNam.length() - 2);
        //System.out.println("chpNam1"+chpNam1);

    //          System.out.println("insert into chpaterdetailsfrmimportxcel(projId,duedate,chaptername,milestones,milestoneId)"+
//" values ('"+projid+"','"+duedat+"','"+chpNam+"','"+milstn+"','"+mileCont+"')");
    addChapterPlan = statement.executeUpdate("insert into chpaterdetailsfrmimportxcel(chapId,projId,duedate,chaptername,milestones,milestoneId,pages) "+
" values ('"+chpId+"','"+projid+"','"+duedat+"','"+chpNam+"','"+milstn+"','"+mileCont+"','"+pages+"')");

       statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in creating excel Creation:"+sqle);
            sqle.printStackTrace();
        }catch(Exception e){
            System.out.println("Exception in creating excel Creation:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
        }

  }
  public void setMilestonesInstrd(List milestonesInstrd){
  this.milestonesInstrd=milestonesInstrd;
  }
  public List getMilestonesInstrd(){
  return milestonesInstrd;
  }
  public void milestoneForProj(ExportExcelDataThrPerl exp4){
     List milestonesInstrd1 = new ArrayList();
     Connection con=null;
     int mil=0;
     String projid=exp4.getProj_id();
     milestonesInstrd1.add("Chapter No");
     milestonesInstrd1.add("Pages");
try
{
    DBconnection dbcon = new DBconnection();
    con = dbcon.getSampleProperty();
    Statement statement = con.createStatement();
    ResultSet rsGetMilestn = statement.executeQuery("SELECT milestones FROM projectanditsmilestones WHERE projId='"+projid+"' ORDER BY milestoneId");
    while(rsGetMilestn.next()){
    milestonesInstrd1.add(rsGetMilestn.getString(1));
    mil=1;
   }
 setMilestonesInstrd(milestonesInstrd1);
}
 catch(SQLException e){
 }
  }


  public void updatePlan(ExportExcelDataThrPerl exp5){
     Connection con=null;
     int mil=0;
     String havToupdduedate1=exp5.gethavToupdduedate();
     String havToupdChapPag1=exp5.gethavToupdChapPag();
     String havToupdmilestn1=exp5.gethavToupdmilestn();
     String havToupdChap1=exp5.gethavToupdChap();
     String projid=exp5.getProj_id();
     //System.out.println("havToupdduedate1"+havToupdduedate1+"havToupdChapPag1"+havToupdChapPag1+"havToupdmilestn1"+havToupdmilestn1+"havToupdChap1"+havToupdChap1);
try
{
    DBconnection dbcon = new DBconnection();
    con = dbcon.getSampleProperty();
    Statement statement = con.createStatement();
    statement.executeUpdate("update chpaterdetailsfrmimportxcel set duedate='"+havToupdduedate1+"', pages='"+havToupdChapPag1+"' WHERE milestones='"+havToupdmilestn1+"' and chapId='"+havToupdChap1+"'");
   }
 catch(SQLException e){
 }
  }
  public void deletePlan(String getProjIdParam){
       Connection con=null;
try
{
    DBconnection dbcon = new DBconnection();
    con = dbcon.getSampleProperty();
    Statement statement = con.createStatement();
    statement.executeUpdate("delete from chpaterdetailsfrmimportxcel WHERE projId='"+getProjIdParam+"' AND revise IS NULL");
   }
 catch(SQLException e){
 }
  }
  public List listStages(){
      ExportExcelDataThrPerl exp6 = new ExportExcelDataThrPerl();
     List stagesName = new ArrayList();
     List stagesId = new ArrayList();
     Connection con=null;
try
{
    DBconnection dbcon = new DBconnection();
    con = dbcon.getSampleProperty();
    Statement statement = con.createStatement();
    ResultSet rsGetMilestn = statement.executeQuery("SELECT stageId,stageName FROM stagenameforimportexcel ORDER BY stageId");
    while(rsGetMilestn.next()){
        //System.out.println("stagename"+rsGetMilestn.getString(2));
    stagesName.add(rsGetMilestn.getString(2));
    }
 exp6.setStageNameList(stagesName);
}
 catch(SQLException e){
 }
return stagesName;
  }
   public ExportExcelDataThrPerl projectVslistStages(ExportExcelDataThrPerl exp7){
      //ExportExcelDataThrPerl exp7 = new ExportExcelDataThrPerl();
     List stagesName = new ArrayList();
     List dueDate = new ArrayList();
     List actualDate = new ArrayList();
     List stageId = new ArrayList();
     String projId="";
     projId = exp7.getProj_id();
     //System.out.println("projId"+projId);
     Connection con=null;
try
{
    DBconnection dbcon = new DBconnection();
    con = dbcon.getSampleProperty();
    Statement statement = con.createStatement();
    ResultSet rsGetMilestn = statement.executeQuery("SELECT stageName,duedate,actualDate,stageId FROM stagevsprojforimportexcel where projId='"+projId+"' ORDER BY stageId");
    while(rsGetMilestn.next()){
    stagesName.add(rsGetMilestn.getString(1));
    dueDate.add(rsGetMilestn.getString(2));
    actualDate.add(rsGetMilestn.getString(3));
    stageId.add(rsGetMilestn.getString(4));
    }
 exp7.setStageNameList(stagesName);
 exp7.setdueDateList(dueDate);
 exp7.setactualDateList(actualDate);
 exp7.setStageIdList(stageId);
}
 catch(SQLException e){
 }
return exp7;
  }
   public void insrtStageDuedateDetails(String stagename,String projId,String duedate,String actualdate,int stageId){
       Connection con=null;
       ExportExcelDataThrPerl exp8 = new ExportExcelDataThrPerl();
try{
    if (duedate.equals("")){
        duedate="0000-00-00";
    }
    if (actualdate.equals("")){
        actualdate="0000-00-00";
    }
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();
        //System.out.println("insert into stagevsprojforimportexcel(stageName,projId,duedate,actualDate,stageId) values('"+stagename+"','"+projId+"','"+duedate+"','"+actualdate+"','"+stageId+"')");
        statement.executeUpdate("insert into stagevsprojforimportexcel(stageName,projId,duedate,actualDate,stageId) values('"+stagename+"','"+projId+"','"+duedate+"','"+actualdate+"','"+stageId+"')");
}
catch(Exception e){}
   }

    public void updateStagesDate(String projId,String duedateUpd,String actualdateUpd,int stgeId){
     Connection con=null;
     int mil=0;
      if (duedateUpd.equals("null")){
        duedateUpd="0000-00-00";
    }
    if (actualdateUpd.equals("null")){
        actualdateUpd="0000-00-00";
    }
     //System.out.println("havToupdduedate1"+havToupdduedate1+"havToupdChapPag1"+havToupdChapPag1+"havToupdmilestn1"+havToupdmilestn1+"havToupdChap1"+havToupdChap1);
try
{
    DBconnection dbcon = new DBconnection();
    con = dbcon.getSampleProperty();
    Statement statement = con.createStatement();
    //System.out.println("update stagevsprojforimportexcel set duedate='"+duedateUpd+"', actualDate='"+actualdateUpd+"' WHERE projId='"+projId+"' and stageId='"+stgeId+"'");
    statement.executeUpdate("update stagevsprojforimportexcel set duedate='"+duedateUpd+"', actualDate='"+actualdateUpd+"' WHERE projId='"+projId+"' and stageId='"+stgeId+"'");
   }
 catch(SQLException e){
 }
  }
}

