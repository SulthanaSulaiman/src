/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.depttarget;


import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 * beginning class for DeptTarget which colects the TargetOrder and this class will pass the order to the TargetDept Class
 */
public class TargetOrder implements Serializable {
   
    private String year="";
    private String month="";
    private String targetOrder="";
    private String query_MonthYear = "";

    public TargetOrder() {
        
    }

    public void setYear(String year){
        this.year=year;
    }

   public void setMonth(String month){
        this.month=month;
       // System.out.println("getProjId:"+prjid);
   }
//addPropValue

    public String getTargetOrder(){
        return targetOrder;
    }


public void TargetOrdervalue(){

Connection con=null;
        try{
int selected_month=Integer.parseInt(month);
if(selected_month<10){
month = "0"+String.valueOf(selected_month);
}
else{
month = String.valueOf(selected_month);
}

query_MonthYear = year+"-"+month;
        targetOrder="";

        //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
     Statement statement = con.createStatement();

      String chkValue = "";
//" select pp.value from project_properties_value_master ppvm,project_properties pp where ppvm.value=pp.value and pp.proj_id='"+prjid+"' and pp.property_id='"+propId+"'   ");
     //System.out.println("select target_order from dept_target where date_format(target_date,'%Y-%m')<='"+query_MonthYear+"' order by target_order desc limit 1");
     ResultSet rsTargetOrder =  statement.executeQuery("select target_order from dept_target where date_format(target_date,'%Y-%m')<='"+query_MonthYear+"' order by target_order desc limit 1");
     while(rsTargetOrder.next()){
       targetOrder=rsTargetOrder.getString(1);
       if(rsTargetOrder.wasNull()){
         targetOrder="";
        }
       }
     rsTargetOrder.close();
     statement.close();
     
//System.out.println("addContact:"+addContact);
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
