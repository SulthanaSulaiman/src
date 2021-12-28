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
 */
public class TargetInput implements Serializable {
   
    private String targetOrder="";
    private String query_MonthYear = "";

    private List deptCode = new ArrayList();
    private List deptName = new ArrayList();
    private List targetValue = new ArrayList();

    public TargetInput() {
        
    }

    public void setTargetOrder(String targetOrder){
        this.targetOrder=targetOrder;
    }

    public void setOrderDeptcode(List deptCode){
        this.deptCode=deptCode;
    }

   public void setOrderDeptName(List deptName){
        this.deptName=deptName;
    }

   public void setTarget(List targetValue){
        this.targetValue=targetValue;
    }
   
    public List getTargetValue(){
        return targetValue;
    }

public void calculateTarget(){

Connection con=null;
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
     
       
//System.out.println("addContact:"+addContact);
     statement.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Proj ID:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Proj ID:"+e);
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
