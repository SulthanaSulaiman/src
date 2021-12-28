/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.depttarget;


import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramesh
 * class next to targetorder that collects the departments and their target value for a particuar target order
 */
public class TargetDept implements Serializable {
   
   
    private String targetOrder="";
    private String query_MonthYear = "";
    private String targetDept="";

    private List deptCode = new ArrayList();
    private List stgCode = new ArrayList();
    private List stgName = new ArrayList();
    private List deptName = new ArrayList();
    private List targetValue = new ArrayList();
    private List activityCode = new ArrayList();
    private List factorList = new ArrayList();

    private List tgtStageCode = new ArrayList();//this and next List are added to enable a link in the data of the stages from which  the shippped units could be viewed using the shipped status report
    private List tgtStageLink = new ArrayList();
    private List tgtStageDept = new ArrayList();

     Connection con1=null;
     DBconnection dbcon1 = null;

    public TargetDept() {
        
    }

    public void setTargetOrder(String targetOrder){
        this.targetOrder=targetOrder;
    }

    public void setTargetDept(String targetDept){
        this.targetDept=targetDept;
    }

 /*  public void setMonth(String month){
        this.month=month;
       // System.out.println("getProjId:"+prjid);
   }*/
//addPropValue

    public List getTargetDeptstagecode(){
        return stgCode;
    }

    public List getTargetDeptstagename(){
        return stgName;
    }

    public List getTargetDeptActivity(){
        return activityCode;
    }

    public List getTargetDeptFactor(){
        return factorList;
    }

    public List getTargetDeptcode(){
        return deptCode;
    }

   public List getTargetDeptName(){
        return deptName;
    }

    public List getTargetValue(){
        return targetValue;
    }


    public List getLinkStagecode(){
        return tgtStageCode;
    }

   public List getLinkStatus(){
        return tgtStageLink;
    }

    public List getLinkDeptcode(){
        return tgtStageDept;
    }



public void Targetvalue(){

Connection con=null;
String[] stgArr=null;
String[] activityArr=null;
String[] factorArr=null;

    List tempStage = new ArrayList();
    List tempActivity = new ArrayList();
    List tempFactor = new ArrayList();
    List tempStageName = new ArrayList();

        try{

        //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
       
     Statement statement = con.createStatement(); 
     String chkValue = "";
//" select pp.value from project_properties_value_master ppvm,project_properties pp where ppvm.value=pp.value and pp.proj_id='"+prjid+"' and pp.property_id='"+propId+"'   ");
   //  System.out.println("select target_order from dept_target where date_format(target_date,'%Y-%m')<='"+query_MonthYear+"' order by target_order desc limit 1");
     ResultSet rsTargetDept =  statement.executeQuery(" select dptg.dept_code,dptg.target,dpt.department from dept_target dptg ,department dpt " +
                                                       " where dptg.target_order='"+targetOrder+"' and dpt.dept_code = dptg.dept_code ");


     while(rsTargetDept.next()){
         deptCode.add(rsTargetDept.getString("dptg.dept_code"));
         targetValue.add(rsTargetDept.getString("dptg.target"));
         deptName.add(rsTargetDept.getString("dpt.department"));
     }
     rsTargetDept.close();

     ResultSet rsTargetAttribs =null;


     
    for(int idx=0;idx<deptCode.size();idx++){
        targetDept=deptCode.get(idx).toString();

        if(idx>0){
            //to avoid IndexOutOfBounds Exception while looping in the detail TargetReport and these variables are to display link in the report
        tgtStageCode.add("");
        tgtStageLink.add("");
        tgtStageDept.add("");

        tgtStageCode.add("");
        tgtStageLink.add("");
        tgtStageDept.add("");
        }



//        System.out.println("Stage Link SQL:"+" select dts.stage_code,dts.stage_link,dts.dept_code from dept_target_stage dts  " +
//                                                       " where dts.target_order='"+targetOrder+"' and dts.dept_code = '"+targetDept+"' ");
        
      rsTargetAttribs =  statement.executeQuery(" select dts.stage_code,dts.stage_link,dts.dept_code from dept_target_stage dts  " +
                                                       " where dts.target_order='"+targetOrder+"' and dts.dept_code = '"+targetDept+"' ");
      while(rsTargetAttribs.next()){
        tgtStageCode.add(rsTargetAttribs.getString("dts.stage_code"));
        tgtStageLink.add(rsTargetAttribs.getString("dts.stage_link"));
        tgtStageDept.add(rsTargetAttribs.getString("dts.dept_code"));
      }
    }


            //to avoid IndexOutOfBounds Exception while looping in the 
            //detail TargetReport and these variables are to display link
            //in the report
     //this second time is to avoid Exception being thrown while looping through the last set of elements
        tgtStageCode.add("");
        tgtStageLink.add("");
        tgtStageDept.add("");

        tgtStageCode.add("");
        tgtStageLink.add("");
        tgtStageDept.add("");





//System.out.println("tgtStageCode:"+tgtStageCode);
//System.out.println("tgtStageLink:"+tgtStageLink);
//System.out.println("tgtStageDept:"+tgtStageDept);

rsTargetAttribs =null;

   for(int idx=0;idx<deptCode.size();idx++){
       targetDept=deptCode.get(idx).toString();     
            
      rsTargetAttribs =  statement.executeQuery
      (" select dpts.stage_code,dpts.activity_code,dpts.calculation_factor,dpts.dept_multiple," +
                                    " dpts.dept_include,dpts.client_include,dpts.client_not_include,psg.stage " +
                                    "  from dept_target_stage dpts,project_stage psg " +
                                    " where dpts.target_order='"+targetOrder+"' " +
                                    "  and dpts.dept_code = '"+targetDept+"'" +
                                    " and psg.stage_code=dpts.stage_code ");
    tempStage.clear();
    tempActivity.clear();
    tempFactor.clear();
    tempStageName.clear();

     while(rsTargetAttribs.next()){
      tempStage.add(rsTargetAttribs.getString("dpts.stage_code"));

       chkValue=rsTargetAttribs.getString("dpts.activity_code");
         if(rsTargetAttribs.wasNull()){
            chkValue="";
         }
       tempActivity.add(chkValue);

       chkValue=rsTargetAttribs.getString("dpts.calculation_factor");
         if(rsTargetAttribs.wasNull()){
            chkValue="";
         }
       tempFactor.add(chkValue);

       chkValue=rsTargetAttribs.getString("psg.stage");
         if(rsTargetAttribs.wasNull()){
            chkValue="";
         }
       tempStageName.add(chkValue);

     }
    rsTargetAttribs.close();

    //stgName

      //build an array of stages mapped to the dept and return an arraylist of these arrays
      stgArr= new String[tempStage.size()];
        for(int idx1=0;idx1<tempStage.size();idx1++){
            stgArr[idx1]=tempStage.get(idx1).toString();
        }       
        stgCode.add(stgArr);

      stgArr= new String[tempStageName.size()];
        for(int idx1=0;idx1<tempStageName.size();idx1++){
            stgArr[idx1]=tempStageName.get(idx1).toString();
        }       
        stgName.add(stgArr);

     activityArr= new String[tempActivity.size()];
        for(int idx1=0;idx1<tempActivity.size();idx1++){
            activityArr[idx1]=tempActivity.get(idx1).toString();
        }
    activityCode.add(activityArr);

     factorArr= new String[tempFactor.size()];
        for(int idx1=0;idx1<tempFactor.size();idx1++){
            factorArr[idx1]=tempFactor.get(idx1).toString();
        }
    factorList.add(factorArr);

   }


//System.out.println("addContact:"+addContact);
statement.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in TargetDept:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in TargetDept:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            //System.out.println(e);
            }
            catch(NullPointerException npe){
            //System.out.println(e);
            }catch(Exception npe){
            //System.out.println(e);
            }
        }     
               
               
}

}
