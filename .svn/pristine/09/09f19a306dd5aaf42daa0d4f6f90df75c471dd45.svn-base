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
public class UnMappedMilestoneList implements Serializable {


    DBconnection conProp = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;


    private List unmappedmileStoneName = new ArrayList();
    private List unmappedmileStoneCode = new ArrayList();
    private List unmappedmileStoneRange = new ArrayList();

    private String sql_select = "";
    private String sql_from = "";
    private String sql_where="";

    private String workFlowId="";

   public void setWorkFlowId(String workFlowId){
       this.workFlowId=workFlowId;
       //System.out.println("workFlowId in class:"+workFlowId);
   }

    public UnMappedMilestoneList() {

    }



        public void collectUnmappedMilestone(){

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
//("select stage_code,stage,range from project_stage where milestone ='1' ");
        sql_select = " select ps.stage_code,ps.stage,ps.range    ";
        sql_from = "from project_stage ps ";//
        sql_where = " where ps.milestone ='1' and ps.stage_code not in "
                + "(select milestone_code from project_workflow_milestone_map where workflow_id='"+workFlowId+"' )";


        sql_select += sql_from+sql_where;

        //System.out.println("sql_select:"+sql_select);
           ResultSet rsUnmapped = stmt.executeQuery(sql_select);
            unmappedmileStoneCode.clear();
            unmappedmileStoneName.clear();
            while(rsUnmapped.next()){
                unmappedmileStoneCode.add(rsUnmapped.getString(1));
                unmappedmileStoneName.add(rsUnmapped.getString(2));
                unmappedmileStoneRange.add(rsUnmapped.getString(3));

            }
            rsUnmapped.close();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }finally{
            try{
                stmt.close();
                con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
            }
        }
    }



    public List getUnmappedMileStoneCode(){
        return unmappedmileStoneCode;
    }

    public List getUnmappedMileStoneName(){
        return unmappedmileStoneName;
    }

    public List getUnmappedMileStoneRange(){
        return unmappedmileStoneRange;
    }

}
