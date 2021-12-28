
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;
import java.io.Serializable;
import java.util.*;
import java.sql.*;
import connection.*;

/**
 *
 * @author  ramesh
 */
public class PresetLeaveBalance implements Serializable {

    private List lv_TypeId = new ArrayList();
    private List lv_Type = new ArrayList();
    private List lv_Limit = new ArrayList();
    private String set_facilityId = "";
    private String sql_stmt = "";
    private String setType = "";
    private int intTwd = 0;
    private int modTwd = 0;
    private String currMon = "";
    private String empId = "";

    private List app_lv_TypeId = new ArrayList();

    CalculateLeaveBalance clb = new CalculateLeaveBalance();
   /* private List lv_TypeId = new ArrayList();*/

    public void setEmpId(String empId){
        this.empId=empId;
    }

    public void setType(String setType){
        this.setType = setType;
    }

    public List getLvTypeID(){
        return app_lv_TypeId;
    }

    public List getLvType(){
        return lv_Type;
    }

    public List getLvLimit(){
        return lv_Limit;
    }

    public void setIntTwd(int intTwd){
        this.intTwd = intTwd;
    }

    public void setModTwd(int modTwd){
        this.modTwd = modTwd;
    }

    public void setCurrMon(String currMon){
        this.currMon = currMon;
    }

    public void setFacilityId(String set_facilityId){
        this.set_facilityId = set_facilityId;
    }

    private Connection con=null;
    private Statement st = null;
    private Statement st1 = null;
    public void collectLeaveBalance(){
        //System.out.println("set_facilityId ********** :"+set_facilityId);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();

        try{
        //the below block isto collect the leave type id's of the leave that could be applied by the employee

            //System.out.println("setType : "+setType);
            st = con.createStatement();
            st1 = con.createStatement();
            sql_stmt = "select lt.lv_type_id from leave_type lt,lvtype_facilitymap lf " +
                    "where lf.lv_type_id = lt.lv_type_id and facility_id = '"+set_facilityId+"' ";
            if(setType.equals("1")){
                sql_stmt += "and lt.lv_type_id = '3'";
            }
            sql_stmt += "order by lv_type_id";
            //System.out.println("sql_stmt : "+sql_stmt);
            ResultSet rsLvTypeId = st.executeQuery(sql_stmt);
            lv_TypeId.clear();

            while(rsLvTypeId.next()) {
                lv_TypeId.add(rsLvTypeId.getString(1));
            }
            rsLvTypeId.close();
            //System.out.println("lv_TypeId ********** :"+lv_TypeId);
            ResultSet rsLvLimit=null;
            String lvLmt_Sql = "";
//the next resultset isthe totallimit for the applicable leaves

            for(int idx=0;idx<lv_TypeId.size();idx++) {

                lvLmt_Sql = " select lt.lv_type,lmt.lv_limit "+
                        "from leave_type lt,leavetype_limit lmt "+
                        "where lmt.lv_type_id=lt.lv_type_id and lt.lv_type_id='"+lv_TypeId.get(idx).toString()+"' "+
                        "order by lmt.lv_setdate desc limit 1";

                rsLvLimit = st.executeQuery(lvLmt_Sql);

                while(rsLvLimit.next()){
                    lv_Type.add(rsLvLimit.getString("lt.lv_type"));
                    if(!lv_TypeId.get(idx).equals("3")){
                        lv_Limit.add(rsLvLimit.getString("lmt.lv_limit"));
                    }else {
                        //System.out.println("intTwd : "+intTwd);
                        int temp_val = 0;
                        int lv_limt_val = 0;
                        if(intTwd>0){
                            lv_limt_val = Integer.parseInt(rsLvLimit.getString("lmt.lv_limit"));
                            //temp_val = Integer.parseInt(rsLvLimit.getString("lmt.lv_limit"));
                            //System.out.println("select lt.current_bal from leave_balance lt where lt.lv_type_id='"+lv_TypeId.get(idx).toString()+"' and emp_id  = '"+empId+"' order by lt.lv_balance_id desc limit 1 ");
                            ResultSet rs = st1.executeQuery("select lt.current_bal from leave_balance lt where lt.lv_type_id='"+lv_TypeId.get(idx).toString()+"' and emp_id  = '"+empId+"' order by lt.lv_balance_id desc limit 1 ");

                            if(rs.next()){
                                rs = st1.executeQuery("select lt.current_bal from leave_balance lt where lt.lv_type_id='"+lv_TypeId.get(idx).toString()+"' and emp_id  = '"+empId+"' order by lt.lv_balance_id desc limit 1 ");

                                while(rs.next()){
                                    //System.out.println("getInt : "+rs.getInt(1));
                                    temp_val=rs.getInt(1);
                                }
                            }
                            rs.close();
                            temp_val=lv_limt_val+temp_val;

                            /**if(modTwd>0 && currMon.equals("1")){
                                //System.out.println("**** : "+lv_limt_val);
                                clb.setLeaveLimit(String.valueOf(lv_limt_val));
                                clb.setRemainingDays(modTwd);
                                clb.calculateBal();
                                temp_val = lv_limt_val+Integer.parseInt(clb.getCalcBalance());
                            }*/
                            lv_Limit.add(String.valueOf(temp_val));
                        }else{
                            lv_Limit.add("0");
                        }
                    }

                    app_lv_TypeId.add(lv_TypeId.get(idx).toString());
                }
                //app_lv_TypeId - applicable leave type id - vble included to avoid arrayindex out of bound exception
            }//close of for(int loop=0;loop<lv_TypeId.size();loop++)
         /*System.out.println("lv_TypeId:"+lv_TypeId);
         System.out.println("lv_Type:"+lv_Type);
         System.out.println("lv_Limit:"+lv_Limit);*/

            //System.out.println("app_lv_TypeId:"+app_lv_TypeId);
            rsLvLimit.close();
        }catch(SQLException sqle){
            System.out.println("sqle in PresetLeaveBalance:"+sqle);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Excpetion in PresetLeaveBalance:"+e);
        }finally{
            try{
                st1.close();
                st.close();
                con.close();
            }catch(SQLException sqle){
            System.out.println("sqle in PresetLEaveBalance-conclose:"+sqle);
            }
        }
    }
}