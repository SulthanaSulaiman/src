/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aravindhanj
 */
public class BufferPlanDAO {

    public BufferPlanVO saveBufferPlan(BufferPlanVO bufferPlanVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatementInsert = null;
        PreparedStatement preparedStatementUpdate = null;

        String customerId = "";
        String empId = "";
        String getBufferPlanId = "";
        String getStageCode = "";
        String getMilestoneActCode = "";
        String getBufferDays = "";

        customerId = !bufferPlanVO.getCustomerId().toString().equals("") ? bufferPlanVO.getCustomerId().toString() : "0";
        empId = !bufferPlanVO.getEmpId().toString().equals("") ? bufferPlanVO.getEmpId().toString() : "0";
        int recordSave=0;

        List bufferPlanIdList = bufferPlanVO.getBufferPlanIdList();
        List stageCodeList = bufferPlanVO.getStageCodeList();
        List milestoneActCodeList = bufferPlanVO.getMilestoneActCodeList();
        List bufferDaysList = bufferPlanVO.getBufferDaysList();
        
        try{

            connection = conProp.getSampleProperty();

            preparedStatementInsert = connection.prepareStatement("INSERT INTO buffer_plan (customer_id, stage_code, milestone_act_code,buffer_day) VALUES (?,?,?,?)");

            preparedStatementUpdate = connection.prepareStatement("UPDATE buffer_plan "
                            + " SET customer_id = ?, stage_code = ?, milestone_act_code = ?, buffer_day = ?, last_modified_by = ?, last_modified_date = CURRENT_TIMESTAMP() "
                            + " WHERE buffer_id = ?");

            for(int i=0; i<stageCodeList.size(); i++) {
                getBufferPlanId = bufferPlanIdList.get(i).toString();
                getStageCode = stageCodeList.get(i).toString();
                getMilestoneActCode = !milestoneActCodeList.get(i).toString().equals("") ? milestoneActCodeList.get(i).toString() : "0";
                getBufferDays = !bufferDaysList.get(i).toString().equals("") ? bufferDaysList.get(i).toString() : "0";
                if(getBufferPlanId.equals("0")) {
                    preparedStatementInsert.setInt(1, Integer.parseInt(customerId));
                    preparedStatementInsert.setString(2, getStageCode);
                    preparedStatementInsert.setInt(3, Integer.parseInt(getMilestoneActCode));
                    preparedStatementInsert.setInt(4, Integer.parseInt(getBufferDays));
                    recordSave += preparedStatementInsert.executeUpdate();
                } else {
                    preparedStatementUpdate.setInt(1, Integer.parseInt(customerId));
                    preparedStatementUpdate.setString(2, getStageCode);
                    preparedStatementUpdate.setInt(3, Integer.parseInt(getMilestoneActCode));
                    preparedStatementUpdate.setInt(4, Integer.parseInt(getBufferDays));
                    preparedStatementUpdate.setString(5, empId);
                    preparedStatementUpdate.setInt(6, Integer.parseInt(getBufferPlanId));
                    recordSave += preparedStatementUpdate.executeUpdate();
                }
            }
            if(recordSave == stageCodeList.size()) {
                bufferPlanVO.setResult(1);
            } else {
                bufferPlanVO.setResult(0);
            }
        }catch (SQLException e) {
            System.out.println("SQLException : BufferPlanDAO - addBufferPlan()" + e);
        } finally {
            try {
                preparedStatementInsert.close();
                preparedStatementUpdate.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : BufferPlanDAO - addBufferPlan()" + ex);
            }
        }
        return bufferPlanVO;
    }

    public BufferPlanVO deleteBufferPlan(BufferPlanVO bufferPlanVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        PreparedStatement preparedStatementDelete = null;
        int recordDelete = 0;
        List bufferPlanIdList = bufferPlanVO.getBufferPlanIdList();

        try {
            connection = conProp.getSampleProperty();
            preparedStatementDelete = connection.prepareStatement("DELETE FROM buffer_plan WHERE buffer_id = ?");

            for(int i=0; i<bufferPlanIdList.size(); i++) {
                preparedStatementDelete.setInt(1, Integer.parseInt(!bufferPlanIdList.get(i).toString().equals("") ? bufferPlanIdList.get(i).toString() : "0"));
                recordDelete += preparedStatementDelete.executeUpdate();
            }
            if(recordDelete == bufferPlanIdList.size()) {
                bufferPlanVO.setResult(1);
            } else {
                bufferPlanVO.setResult(0);
            }
        } catch (SQLException e) {
            System.out.println("SQLException : BufferPlanDAO - deleteBufferPlan()" + e);
        } finally {
            try {
                preparedStatementDelete.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : BufferPlanDAO - deleteBufferPlan()" + ex);
            }
        }
        return bufferPlanVO;
    }

    public BufferPlanVO getBufferplan(BufferPlanVO bufferPlanVO){

        connection.DBconnection conProp = new connection.DBconnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String customerId = bufferPlanVO.getCustomerId();

        List bufferPlanIdList = new ArrayList();
        List customerIdList = new ArrayList();
        List customerNameList = new ArrayList();
        List stageCodeList = new ArrayList();
        List stageNameList = new ArrayList();
        List milestoneActCodeList = new ArrayList();
        List milestoneActNameList = new ArrayList();
        List bufferDaysList = new ArrayList();
        List lastModifiedByList = new ArrayList();
        List lastModifiedDateList = new ArrayList();
        System.out.println(customerId);
        try{

            connection = conProp.getSampleProperty();
            preparedStatement = connection.prepareStatement(""
                    + " SELECT bp.buffer_id, c.contact_id, c.company, ps.stage_code, ps.stage, pma.milestone_act_code, pma.milestone_act_name, bp.buffer_day, bp.last_modified_by, bp.last_modified_date "
                    + " FROM buffer_plan bp, contacts c, project_stage ps, proj_milestone_act pma WHERE "
                    + " bp.customer_id=c.contact_id AND bp.stage_code=ps.stage_code AND bp.milestone_act_code=pma.milestone_act_code AND bp.customer_id=? ");
            preparedStatement.setString(1, customerId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                bufferPlanIdList.add(resultSet.getString(1) != null ? resultSet.getString(1).toString() : "");
                customerIdList.add(resultSet.getString(2) != null ? resultSet.getString(2).toString() : "");
                customerNameList.add(resultSet.getString(3) != null ? resultSet.getString(3).toString() : "");
                stageCodeList.add(resultSet.getString(4) != null ? resultSet.getString(4).toString() : "");
                stageNameList.add(resultSet.getString(5) != null ? resultSet.getString(5).toString() : "");
                milestoneActCodeList.add(resultSet.getString(6) != null ? resultSet.getString(6).toString() : "");
                milestoneActNameList.add(resultSet.getString(7) != null ? resultSet.getString(7).toString() : "");
                bufferDaysList.add(resultSet.getString(8) != null ? resultSet.getString(8).toString() : "");
                lastModifiedByList.add(resultSet.getString(9) != null ? resultSet.getString(9).toString() : "");
                lastModifiedDateList.add(resultSet.getString(10) != null ? resultSet.getString(10).toString() : "");
            }
            bufferPlanVO.setBufferPlanIdList(bufferPlanIdList);
            bufferPlanVO.setCustomerIdList(customerIdList);
            bufferPlanVO.setCustomerNameList(customerNameList);
            bufferPlanVO.setStageCodeList(stageCodeList);
            bufferPlanVO.setStageNameList(stageNameList);
            bufferPlanVO.setMilestoneActCodeList(milestoneActCodeList);
            bufferPlanVO.setMilestoneActNameList(milestoneActNameList);
            bufferPlanVO.setLastModifiedByList(lastModifiedByList);
            bufferPlanVO.setLastModifiedDateList(lastModifiedDateList);
            bufferPlanVO.setBufferDaysList(bufferDaysList);
        } catch (SQLException e) {
            System.out.println("SQLException : BufferPlanDAO - getBufferplan()" + e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : BufferPlanDAO - getBufferplan()" + ex);
            }
        }
        return bufferPlanVO;
    }

}
