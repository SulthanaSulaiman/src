/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.projects;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import connection.DBconnection;

/**
 *
 * @author ramesh
 */
public class MilestoneList implements Serializable {


    DBconnection conProp = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private List mileStoneName = new ArrayList();
    private List mileStoneCode = new ArrayList();
    private List mileStoneRange = new ArrayList();
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where="";

    private String workFlowId="";

   public void setWorkFlowId(String workFlowId){
       this.workFlowId=workFlowId;
       //System.out.println("workFlowId:"+workFlowId);
   }

    public MilestoneList() {

    }


    public void collectMilestoneList(){

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
//("select stage_code,stage,range from project_stage where milestone ='1' ");
        sql_select = " select ps.stage_code,ps.stage,ps.range  ";
        sql_from = "from project_stage ps ";//
        sql_where = " where ps.milestone ='1' ";
        if(!workFlowId.equals("")){
            sql_from += ",project_workflow_milestone_map wfmm " ;
            sql_where += " and ps.stage_code =wfmm.milestone_code and wfmm.workflow_id='"+workFlowId+"' ";
        }

        sql_select += sql_from+sql_where;

        //System.out.println("sql_select:"+sql_select);
            rs = stmt.executeQuery(sql_select);
            mileStoneCode.clear();
            mileStoneName.clear();
            while(rs.next()){
                mileStoneCode.add(rs.getString(1));
                mileStoneName.add(rs.getString(2));
                mileStoneRange.add(rs.getString(3));
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }finally{
            try{
                rs.close();
                stmt.close();
                con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
            }
        }
    }

    public List getMileStoneCode(){
        return mileStoneCode;
    }

    public List getMileStoneName(){
        return mileStoneName;
    }

    public List getMileStoneRange(){
        return mileStoneRange;
    }



}
