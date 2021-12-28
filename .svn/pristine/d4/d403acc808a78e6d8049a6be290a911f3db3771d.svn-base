/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.io.*;

import java.beans.*;
import java.io.Serializable;


public class CellTargetReport implements Serializable {
    
    
    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    
    private String queryData="";
    private List departmentNameList= new ArrayList();
    private String retTotalUnits="0";
    private String deptCode="";

    public String getCellCode() {
        return cellCode;
    }

    public void setCellCode(String cellCode) {
        this.cellCode = cellCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getMileStoneCode() {
        return mileStoneCode;
    }

    public void setMileStoneCode(String mileStoneCode) {
        this.mileStoneCode = mileStoneCode;
    }
    private String cellCode="";
    private String mileStoneCode="";
    
    
    private String loopDeptCode = "" ;

    public String getLoopCellCode() {
        return loopCellCode;
    }

    public void setLoopCellCode(String loopCellCode) {
        this.loopCellCode = loopCellCode;
    }

    public String getLoopDeptCode() {
        return loopDeptCode;
    }

    public void setLoopDeptCode(String loopDeptCode) {
        this.loopDeptCode = loopDeptCode;
    }
    private String loopCellCode = "" ;

    public List getCellCodeList() {
        return cellCodeList;
    }

    public void setCellCodeList(List cellCodeList) {
        this.cellCodeList = cellCodeList;
    }

    public List getCellNameList() {
        return cellNameList;
    }

    public void setCellNameList(List cellNameList) {
        this.cellNameList = cellNameList;
    }

    public List getDepartmentNameList() {
        return departmentNameList;
    }

    public void setDepartmentNameList(List departmentNameList) {
        this.departmentNameList = departmentNameList;
    }

    public List getDeptCodeList() {
        return deptCodeList;
    }

    public void setDeptCodeList(List deptCodeList) {
        this.deptCodeList = deptCodeList;
    }

    public List getMileStoneCodeList() {
        return mileStoneCodeList;
    }

    public void setMileStoneCodeList(List mileStoneCodeList) {
        this.mileStoneCodeList = mileStoneCodeList;
    }

    public List getMileStoneNameList() {
        return mileStoneNameList;
    }

    public void setMileStoneNameList(List mileStoneNameList) {
        this.mileStoneNameList = mileStoneNameList;
    }

    public List getMstnDelayedPercentageList() {
        return mstnDelayedPercentageList;
    }

    public void setMstnDelayedPercentageList(List mstnDelayedPercentageList) {
        this.mstnDelayedPercentageList = mstnDelayedPercentageList;
    }

    public List getMstnPercentageList() {
        return mstnPercentageList;
    }

    public void setMstnPercentageList(List mstnPercentageList) {
        this.mstnPercentageList = mstnPercentageList;
    }

    public List getUnitsList() {
        return unitsList;
    }

    public void setUnitsList(List unitsList) {
        this.unitsList = unitsList;
    }
    private List deptCodeList= new ArrayList();
    private List cellCodeList= new ArrayList();
    private List cellNameList= new ArrayList();
    private List unitsList= new ArrayList();
    private List mileStoneCodeList= new ArrayList();
    private List mileStoneNameList= new ArrayList();
    private List mstnPercentageList= new ArrayList();
    private List mstnDelayedPercentageList= new ArrayList();
    
private String percentTotalUnits = "";
private String aheadPercent = "";
private String onTimePercent = "";
private String delayedPercent = "";
private String loopMilestoneCode = "";

    
    public CellTargetReport() {
      
    }
    
    public void collectCellTarget(){
        try{
           con = connection.getSampleProperty();
            stmt = con.createStatement();
            
           /* System.out.println("select dpt.department,dpt.dept_code,"
                    + "cl.cell_name,cl.cell_code,sum(act.proof_pages),"
                    + " pma.milestone_act_name,pma.milestone_act_code "
                    + "from department dpt, emp_cell_map ecm, cell cl,activity act,proj_milestone_act pma "
                    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and act.emp_id=ecm.emp_id "
                    + "and act.milestone_code=pma.milestone_act_code and act.end_time is not null"
                    + " group by pma.milestone_act_code,ecm.cell_code,ecm.dept_code"
                    + " order by dpt.dept_code,ecm.cell_code,pma.milestone_act_name");*/
            
            String sql_select="select dpt.department,dpt.dept_code,"
                    + "cl.cell_name,cl.cell_code,sum(act.proof_pages),"
                    + " pma.milestone_act_name,pma.milestone_act_code "
                    + "from department dpt, emp_cell_map ecm, cell cl,activity act,proj_milestone_act pma ";
            String  sql_where = "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and act.emp_id=ecm.emp_id "
                    + "and act.milestone_code=pma.milestone_act_code and act.end_time is not null";
            
            //System.out.println("sql_select for collectCellTarget :"+sql_select);
            
            
            if(!deptCode.equals("")){
                sql_where += " and dpt.dept_code='"+deptCode+"' ";
            }
             if(!cellCode.equals("")){
                sql_where += " and cl.cell_code='"+cellCode+"' ";
            }
            
           sql_select=sql_select+sql_where+ " group by pma.milestone_act_code,ecm.cell_code,ecm.dept_code"
                    + " order by dpt.dept_code,ecm.cell_code,pma.milestone_act_name";
           
           //System.out.println("sql_select for cell Target:"+sql_select);
            
            ResultSet rsCollectDetails = stmt.executeQuery(sql_select);
            
            while(rsCollectDetails.next()){                
                queryData = rsCollectDetails.getString("dpt.department");                
                //if(!departmentNameList.contains(queryData)){
                    departmentNameList.add(queryData);
                    deptCodeList.add(rsCollectDetails.getString("dpt.dept_code"));
                //}
                
                queryData = rsCollectDetails.getString("cl.cell_code");                
                //if(!cellCodeList.contains(queryData)){
                    cellNameList.add(rsCollectDetails.getString("cl.cell_name"));
                    cellCodeList.add(queryData);
                //}
                
                queryData = rsCollectDetails.getString("pma.milestone_act_code"); 
                mileStoneCodeList.add(queryData);
                
                queryData = rsCollectDetails.getString("pma.milestone_act_name"); 
                mileStoneNameList.add(queryData);
                
                queryData = rsCollectDetails.getString("sum(act.proof_pages)");
                unitsList.add(queryData);                
            }
            
            
            //System.out.println("departmentNameList:"+departmentNameList.size());
            //System.out.println("deptCodeList:"+deptCodeList.size());
            //System.out.println("cellCodeList:"+cellCodeList.size());
            //System.out.println("cellNameList:"+cellNameList.size());
            //System.out.println("unitsList:"+unitsList.size());
            //System.out.println("mileStoneCodeList:"+mileStoneCodeList.size());
            //System.out.println("mileStoneNameList:"+mileStoneNameList.size());
           
            rsCollectDetails.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}
    }
    
    
public String collectTotalUnits(){
        try{
           con = connection.getSampleProperty();
            stmt = con.createStatement();
            
            /*System.out.println("select dpt.department,cl.cell_name,sum(act.proof_pages) "
            + "from department dpt, emp_cell_map ecm, cell cl,activity act "
                    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and"
                    + " act.emp_id=ecm.emp_id and act.end_time is not null and"
                    + " act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and"
                    + " cl.cell_code='"+loopCellCode+"'  group by ecm.cell_code,ecm.dept_code order by ecm.cell_code");*/
            
            ResultSet rsCollectTotalUnits = stmt.executeQuery("select sum(act.proof_pages) "
            + "from department dpt, emp_cell_map ecm, cell cl,activity act "
                    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and"
                    + " act.emp_id=ecm.emp_id and act.end_time is not null and"
                    + " act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and"
                    + " cl.cell_code='"+loopCellCode+"'  group by ecm.cell_code,ecm.dept_code order by ecm.cell_code");
           if(rsCollectTotalUnits.next()){
               
               rsCollectTotalUnits = stmt.executeQuery("select sum(act.proof_pages) "
            + "from department dpt, emp_cell_map ecm, cell cl,activity act "
                    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and"
                    + " act.emp_id=ecm.emp_id and act.end_time is not null and"
                    + " act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and"
                    + " cl.cell_code='"+loopCellCode+"'  group by ecm.cell_code,ecm.dept_code order by ecm.cell_code");
               
               while(rsCollectTotalUnits.next()){
                if(rsCollectTotalUnits.wasNull()){
                    retTotalUnits="0";
                }
                else{
                    retTotalUnits=rsCollectTotalUnits.getString("sum(act.proof_pages)");
                }
                
            }
           }
           else{
               retTotalUnits="0";
           }
            rsCollectTotalUnits.close();
            
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}
        return retTotalUnits;
    }
    
public void calculateMilestonePercent(){
        try{
           con = connection.getSampleProperty();
            stmt = con.createStatement();
            
            ResultSet rsCollectPercentage = null;
            
            
            
        
    rsCollectPercentage = stmt.executeQuery("select (sum(act.proof_pages)/"+percentTotalUnits+")*100  from department dpt, emp_cell_map ecm, cell cl,activity act,chapter_plan chp "
    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and act.emp_id=ecm.emp_id and  "
    + "act.end_time is not null and act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and "
    + "cl.cell_code='"+loopCellCode+"' and act.milestone_code='"+loopMilestoneCode+"' and act.chapter_id=chp.chapter_id and "
    + "act.milestone_code=chp.milestone_id and date(chp.end_date)<date(chp.planned_Date) "
    + "group by ecm.cell_code,ecm.dept_code order by ecm.cell_code");

    if(rsCollectPercentage.next()){
        rsCollectPercentage = stmt.executeQuery("select (sum(act.proof_pages)/"+percentTotalUnits+")*100  from department dpt, emp_cell_map ecm, cell cl,activity act,chapter_plan chp "
    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and act.emp_id=ecm.emp_id and  "
    + "act.end_time is not null and act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and "
    + "cl.cell_code='"+loopCellCode+"' and act.milestone_code='"+loopMilestoneCode+"' and act.chapter_id=chp.chapter_id and "
    + "act.milestone_code=chp.milestone_id and date(chp.end_date)<date(chp.planned_Date) "
    + "group by ecm.cell_code,ecm.dept_code order by ecm.cell_code");

         while(rsCollectPercentage.next()){
           aheadPercent = rsCollectPercentage.getString(1);
        }
        
    }else{
        aheadPercent="0";
    }
        
        
        if(loopDeptCode.equals("CHN-WKH")){
           /*System.out.println("calculate percent:"+"select (sum(act.proof_pages)/"+percentTotalUnits+")*100  from department dpt, emp_cell_map ecm, cell cl,activity act,chapter_plan chp "
    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and act.emp_id=ecm.emp_id and  "
    + "act.end_time is not null and act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and "
    + "cl.cell_code='"+loopCellCode+"' and act.milestone_code='"+loopMilestoneCode+"' and act.chapter_id=chp.chapter_id and "
    + "act.milestone_code=chp.milestone_id and date(chp.end_date)=date(chp.planned_Date) "
    + "group by ecm.cell_code,ecm.dept_code order by ecm.cell_code"); */
        }
        
      
         rsCollectPercentage = stmt.executeQuery("select (sum(act.proof_pages)/"+percentTotalUnits+")*100  from department dpt, emp_cell_map ecm, cell cl,activity act,chapter_plan chp "
    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and act.emp_id=ecm.emp_id and  "
    + "act.end_time is not null and act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and "
    + "cl.cell_code='"+loopCellCode+"' and act.milestone_code='"+loopMilestoneCode+"' and act.chapter_id=chp.chapter_id and "
    + "act.milestone_code=chp.milestone_id and date(chp.end_date)=date(chp.planned_Date) "
    + "group by ecm.cell_code,ecm.dept_code order by ecm.cell_code");
      
        if(rsCollectPercentage.next()){
             rsCollectPercentage = stmt.executeQuery("select (sum(act.proof_pages)/"+percentTotalUnits+")*100  from department dpt, emp_cell_map ecm, cell cl,activity act,chapter_plan chp "
    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and act.emp_id=ecm.emp_id and  "
    + "act.end_time is not null and act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and "
    + "cl.cell_code='"+loopCellCode+"' and act.milestone_code='"+loopMilestoneCode+"' and act.chapter_id=chp.chapter_id and "
    + "act.milestone_code=chp.milestone_id and date(chp.end_date)=date(chp.planned_Date) "
    + "group by ecm.cell_code,ecm.dept_code order by ecm.cell_code");
        while(rsCollectPercentage.next()){
           onTimePercent = rsCollectPercentage.getString(1);
        }  
        }
        else{
            onTimePercent="0";
        }
       
        
          rsCollectPercentage = stmt.executeQuery("select (sum(act.proof_pages)/"+percentTotalUnits+")*100  from department dpt, emp_cell_map ecm, cell cl,activity act,chapter_plan chp "
    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and act.emp_id=ecm.emp_id and  "
    + "act.end_time is not null and act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and "
    + "cl.cell_code='"+loopCellCode+"' and act.milestone_code='"+loopMilestoneCode+"' and act.chapter_id=chp.chapter_id and "
    + "act.milestone_code=chp.milestone_id and date(chp.end_date)>date(chp.planned_Date) "
    + "group by ecm.cell_code,ecm.dept_code order by ecm.cell_code");
        if(rsCollectPercentage.next()){
                    rsCollectPercentage = stmt.executeQuery("select (sum(act.proof_pages)/"+percentTotalUnits+")*100  from department dpt, emp_cell_map ecm, cell cl,activity act,chapter_plan chp "
    + "where dpt.dept_code=ecm.dept_code and cl.cell_code=ecm.cell_code and act.emp_id=ecm.emp_id and  "
    + "act.end_time is not null and act.milestone_code!='select' and dpt.dept_code='"+loopDeptCode+"' and "
    + "cl.cell_code='"+loopCellCode+"' and act.milestone_code='"+loopMilestoneCode+"' and act.chapter_id=chp.chapter_id and "
    + "act.milestone_code=chp.milestone_id and date(chp.end_date)>date(chp.planned_Date) "
    + "group by ecm.cell_code,ecm.dept_code order by ecm.cell_code");
        while(rsCollectPercentage.next()){
           delayedPercent = rsCollectPercentage.getString(1);
        } 
            
        }else{
            delayedPercent="0";
        }
      rsCollectPercentage.close();

}catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}
       // return retTotalUnits;
    }


    public String getLoopMilestoneCode() {
        return loopMilestoneCode;
    }

    public void setLoopMilestoneCode(String loopMilestoneCode) {
        this.loopMilestoneCode = loopMilestoneCode;
    }
    public String getAheadPercent() {
        return aheadPercent;
    }

    public void setAheadPercent(String aheadPercent) {
        this.aheadPercent = aheadPercent;
    }

    public String getDelayedPercent() {
        return delayedPercent;
    }

    public void setDelayedPercent(String delayedPercent) {
        this.delayedPercent = delayedPercent;
    }

    public String getOnTimePercent() {
        return onTimePercent;
    }

    public void setOnTimePercent(String onTimePercent) {
        this.onTimePercent = onTimePercent;
    }

    public String getPercentTotalUnits() {
        return percentTotalUnits;
    }

    public void setPercentTotalUnits(String percentTotalUnits) {
        this.percentTotalUnits = percentTotalUnits;
    }



}
