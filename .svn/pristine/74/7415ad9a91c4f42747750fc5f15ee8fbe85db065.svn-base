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
 * class next to TargetDept to collect the calculation attributes for the order and the dept that is passe with
 */
public class TargetAttributes implements Serializable {
   
   
    private String targetOrder="";
    

    private List deptCode = new ArrayList();
    private List deptName = new ArrayList();

    private List stageCode = new ArrayList();
    private List activityCode = new ArrayList();
    private List orderDeptCode = new ArrayList();
    private List orderDeptName = new ArrayList();
    private List calculationFactor = new ArrayList();
    private List deptMultiple = new ArrayList();
    private List deptInclude = new ArrayList();
    private List clientInclude = new ArrayList();
    private List clientNotInclude = new ArrayList();

    private List grpStageCode = new ArrayList();
    private List grpDeptList = new ArrayList();
    private List grpQueryDeptList = new ArrayList();
    private List grpDeptCode = new ArrayList();
    private List grpDeptClnt = new ArrayList();
    private List grpDeptClntRem = new ArrayList();
    private List grpActivityCode = new ArrayList();
    

    public TargetAttributes() {
        
    }

    public void setTargetOrder(String targetOrder){
        this.targetOrder=targetOrder;
    }

    public void setTargetDeptcode(List deptCode){
       this.deptCode=deptCode;
    }

    public void setTargetDeptName(List deptName){
       this.deptName=deptName;
    }

   public List getStageCode(){
    return stageCode;
    }

   public List getActivityCode(){
        return activityCode;
    }

    public List getDeptCode(){
    return orderDeptCode;
    }

   /* public List getDeptName(){
    return orderDeptName;
    }*/

   public List getCalculationFactor(){
    return calculationFactor;
    }

    public List getDeptMultiple(){
    return deptMultiple;
    }

    public List getDeptList(){
    return deptInclude;
    }

    public List getClientInclude(){
    return clientInclude;
    }

    public List getClientNotInclude(){
          return clientNotInclude;
    }


public List getGrpStage(){
    return grpStageCode;
    }

    public List getGrpQueryDeptList(){
    return grpQueryDeptList;
    }

    public List getGrpDeptList(){
    return grpDeptList;
    }

    public List getGrpDeptCode(){
    return grpDeptCode;
    }

    public List getGrpClientInclude(){
    return grpDeptClnt;
    }

    public List getGrpClientNotInclude(){
          return grpDeptClntRem;
    }

    public List getGrpActivity(){
          return grpActivityCode;
    }






public void TargetAttribList(){

Connection con=null;
        try{

        //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
       
     Statement statement = con.createStatement();

     String chkValue = "";
     ResultSet rsTargetAttribs = null;
     String loopDept = "";
     StringTokenizer stk1 = null;

     ResultSet rsGrpAttribs=statement.executeQuery("select * from depttarget_stage_group where target_order='"+targetOrder+"' ");
     while(rsGrpAttribs.next()){
        chkValue=rsGrpAttribs.getString("stage_code");
         if(rsGrpAttribs.wasNull()){
            chkValue="";
         }
         grpStageCode.add(chkValue);


        chkValue=rsGrpAttribs.getString("dept_list");
         if(rsGrpAttribs.wasNull()){
            chkValue="";
         }
        grpDeptList.add(chkValue);
              if(!chkValue.equals("")){

                 stk1 = new StringTokenizer(chkValue,",");
                 chkValue="";
                while(stk1.hasMoreTokens()) {
                    chkValue += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        chkValue += ",";
                    }
             }
         }//close of if(!chkValue.equals(""))
         grpQueryDeptList.add(chkValue);


        chkValue=rsGrpAttribs.getString("dept_code");
         if(rsGrpAttribs.wasNull()){
            chkValue="";
         }
        grpDeptCode.add(chkValue);


        chkValue=rsGrpAttribs.getString("client_include");
         if(rsGrpAttribs.wasNull()){
            chkValue="";
         }
              if(!chkValue.equals("")){

                 stk1 = new StringTokenizer(chkValue,",");
                 chkValue="";
                while(stk1.hasMoreTokens()) {
                    chkValue += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        chkValue += ",";
                    }
             }
         }//close of if(!chkValue.equals(""))

        grpDeptClnt.add(chkValue);


        chkValue=rsGrpAttribs.getString("client_not_include");
         if(rsGrpAttribs.wasNull()){
            chkValue="";
         }
              if(!chkValue.equals("")){

                 stk1 = new StringTokenizer(chkValue,",");
                 chkValue="";
                while(stk1.hasMoreTokens()) {
                    chkValue += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        chkValue += ",";
                    }
             }
         }//close of if(!chkValue.equals(""))
        grpDeptClntRem.add(chkValue);

        chkValue=rsGrpAttribs.getString("activity_code");
         if(rsGrpAttribs.wasNull()){
            chkValue="";
         }
      if(!chkValue.equals("")){

                 stk1 = new StringTokenizer(chkValue,",");
                 chkValue="";
                while(stk1.hasMoreTokens()) {
                    chkValue += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        chkValue += ",";
                    }
             }
         }//close of if(!chkValue.equals(""))

grpActivityCode.add(chkValue);

         
     }
     rsGrpAttribs.close();







//" select pp.value from project_properties_value_master ppvm,project_properties pp where ppvm.value=pp.value and pp.proj_id='"+prjid+"' and pp.property_id='"+propId+"'   ");
   //  System.out.println("select target_order from dept_target where date_format(target_date,'%Y-%m')<='"+query_MonthYear+"' order by target_order desc limit 1");
//for the target order passed collect the attributes required for calculating the target of each Dept
  for(int idx=0;idx<deptCode.size();idx++)
  {
      loopDept=deptCode.get(idx).toString();
      rsTargetAttribs = statement.executeQuery(" select dpts.stage_code,dpts.activity_code,dpts.calculation_factor,dpts.dept_multiple," +
                                    " dpts.dept_include,dpts.client_include,dpts.client_not_include " +
                                    "  from dept_target_stage dpts " +
                                    " where dpts.target_order='"+targetOrder+"' " +
                                    "  and dpts.dept_code = '"+loopDept+"' ");

     while(rsTargetAttribs.next()){
         //for query purpose the comma seperated values are formattedin the below IF condition .
         //this formatting will change vales "CHN-WK,CHN-CEN,CHN-MGH" to " 'CHN-WK','CHN-CEN','CHN-MGH' "
         //as this will be easier to write an IN cluase query
          chkValue=rsTargetAttribs.getString("dpts.stage_code");
         if(rsTargetAttribs.wasNull()){
            chkValue="";
         }

      if(!chkValue.equals("")){

                 stk1 = new StringTokenizer(chkValue,",");
                 chkValue="";
                while(stk1.hasMoreTokens()) {
                    chkValue += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        chkValue += ",";
                    }
             }
         }//close of if(!chkValue.equals(""))

   stageCode.add(chkValue);

         chkValue=rsTargetAttribs.getString("dpts.activity_code");
         if(rsTargetAttribs.wasNull()){
            chkValue="";
         }      

      if(!chkValue.equals("")){

                 stk1 = new StringTokenizer(chkValue,",");
                 chkValue="";
                while(stk1.hasMoreTokens()) {
                    chkValue += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        chkValue += ",";
                    }
             }
         }//close of if(!chkValue.equals(""))

   activityCode.add(chkValue);

         orderDeptCode.add(loopDept);
         chkValue=rsTargetAttribs.getString("dpts.calculation_factor");
         if(rsTargetAttribs.wasNull()){
            chkValue="";
         }
         calculationFactor.add(chkValue);
         chkValue=rsTargetAttribs.getString("dpts.dept_multiple");
        if(rsTargetAttribs.wasNull()){
            chkValue="";
         }
         deptMultiple.add(chkValue);
         chkValue=rsTargetAttribs.getString("dpts.dept_include");
         if(rsTargetAttribs.wasNull()){
            chkValue="";
         }
         //for query purpose the comma seperated values are formattedin the below IF condition .
         //this formatting will change vales "CHN-WK,CHN-CEN,CHN-MGH" to " 'CHN-WK','CHN-CEN','CHN-MGH' "
         //as this will be easier to write an IN cluase query
         if(!chkValue.equals("")){
            
                 stk1 = new StringTokenizer(chkValue,",");
                 chkValue="";
                while(stk1.hasMoreTokens()) {
                    chkValue += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        chkValue += ",";
                    }

             }

         }//close of if(!chkValue.equals(""))


               deptInclude.add(chkValue);

         chkValue= rsTargetAttribs.getString("dpts.client_include");
         if(rsTargetAttribs.wasNull()){
            chkValue="";
         }
                  if(!chkValue.equals("")){
           
                 stk1 = new StringTokenizer(chkValue,",");
                 chkValue="";
                while(stk1.hasMoreTokens()) {
                    chkValue += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        chkValue += ",";
                    }
 
             }

         }//close of if(!chkValue.equals(""))
             clientInclude.add(chkValue);

       chkValue= rsTargetAttribs.getString("dpts.client_not_include");
         if(rsTargetAttribs.wasNull()){
            chkValue="";
         }
                if(!chkValue.equals("")){
     
                 stk1 = new StringTokenizer(chkValue,",");
                 chkValue="";
                while(stk1.hasMoreTokens()) {
                    chkValue += "'"+stk1.nextToken()+"'";
                    if(stk1.hasMoreElements()){
                        chkValue += ",";
                    }
                }
         }//close of if(!chkValue.equals(""))
         clientNotInclude.add(chkValue);
     }

  }//close of  for(int idx=0;idx<deptCode.size();idx++)


rsTargetAttribs.close();


     
       
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in Target Attribs:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in Target Attribs:"+e);
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
