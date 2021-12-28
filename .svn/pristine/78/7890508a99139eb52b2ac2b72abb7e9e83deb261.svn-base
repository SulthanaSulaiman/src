/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.hr;

import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Parameshwarant
 */
public class SaveApproverAppraisalDAO {

    String queryToGetApproverAppQues = "";
    String queryToGetApproverAppDate = "";
    String queryToGetApproverAppDetails = "";
    String queryToSaveApproverApp = "";
    String queryToUpdateApproverApp = "";
    List approvalAppQuesID = new ArrayList();
    List approvalAppRemark = new ArrayList();
    List approvalAppMemberEmpId = new ArrayList();
    List approvalAppEmpId = new ArrayList();
    List approvalAppraisedStatus = new ArrayList();
    List approvalAppSubmitStatus = new ArrayList();
    List approverAppQuesID = new ArrayList();
    List approverAppRemark = new ArrayList();
    List approverAppMemberEmpId = new ArrayList();
    List approverSuperEmpId = new ArrayList();
    List approverAppEmpId = new ArrayList();
    List approverAppStatus = new ArrayList();
    String approverAppStartDate = "";
    String approverAppEndDate = "";
    String appraisalCycleCode = "";
    pathfinder.functions.hr.SaveApproverAppraisalVO setApproverData = new pathfinder.functions.hr.SaveApproverAppraisalVO();

    public void approverAppraisalDateVerify(SaveApproverAppraisalVO setApproverData) {
        try {
            DBconnection connectToDB = new DBconnection();
            Connection con = connectToDB.getSampleProperty();
            Statement st = con.createStatement();
            queryToGetApproverAppDate = "SELECT approval_app_start_date,approval_app_end_date,cycle_id FROM appraisal_cycle "
                    + "WHERE approval_app_start_date<=(DATE_FORMAT(CURRENT_TIMESTAMP(),'%Y-%m-%d')) AND approval_app_end_date>=(DATE_FORMAT(CURRENT_TIMESTAMP(),'%Y-%m-%d'))";
            ResultSet rs = st.executeQuery(queryToGetApproverAppDate);

            if (!rs.wasNull()) {
                while (rs.next()) {
                    setApproverData.setApproverAppStartDate(rs.getString(1));
                    setApproverData.setApproverAppEndDate(rs.getString(2));
                    setApproverData.setCycleCode(rs.getString(3));
                }
            }

            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    public void approvalAppraisalCheck(SaveApproverAppraisalVO getApproverData) {
        String memberEmpId = getApproverData.getApprovalAppMemberEmpId();
        String superEmpId = getApproverData.getApprovalAppSuperEmpId();
        String approverEmpId = getApproverData.getApprovalAppEmpId();
        String superEmpEncryptKey = getApproverData.getApprovalAppEmpEncryptKey();
        String cycleCode = getApproverData.getCycleCode();
        try {
            DBconnection connectToDB = new DBconnection();
            Connection con = connectToDB.getSampleProperty();
            Statement st = con.createStatement();


            String queryForSuperAppChk = "SELECT approval_app_ques_id, member_emp_id, AES_DECRYPT(approval_app_remark,'" + superEmpEncryptKey + "'),submit_flag,approval_app_date FROM approval_appraisal_remark "
                    + "WHERE cycle_code='" + cycleCode + "' AND approval_emp_id='" + approverEmpId + "' AND member_emp_id='" + memberEmpId + "' AND super_emp_id='" + superEmpId + "'";

            ResultSet rsToGetSuperApp = st.executeQuery(queryForSuperAppChk);
            if (!rsToGetSuperApp.wasNull()) {
                while (rsToGetSuperApp.next()) {
                    approvalAppQuesID.add(rsToGetSuperApp.getString(1));
                    approvalAppMemberEmpId.add(rsToGetSuperApp.getString(2));
                    approvalAppRemark.add(rsToGetSuperApp.getString(3));
                    approvalAppSubmitStatus.add(rsToGetSuperApp.getString(4));
                    approvalAppraisedStatus.add(rsToGetSuperApp.getString(5));

                }
            }

            //System.out.println(" queryForSuperAppChk "+queryForSuperAppChk);
            getApproverData.setapprovalAppQuesID(approvalAppQuesID);
            getApproverData.setapprovalAppRemark(approvalAppRemark);
            getApproverData.setapprovalAppMemberEmpId(approvalAppMemberEmpId);
            getApproverData.setapprovalAppSubmitStatus(approvalAppSubmitStatus);
            getApproverData.setapprovalAppraisedDate(approvalAppraisedStatus);

            rsToGetSuperApp.close();
            st.close();
            con.close();


        } catch (SQLException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public int saveApprovalAppraiasal(SaveApproverAppraisalVO getApproverData) {

        String queryToSaveAppraovalApp = "";
        String queryToUpdateAppraovalApp = "";
        String memberEmpId = getApproverData.getApprovalAppMemberEmpId();
        String superEmpId = getApproverData.getApprovalAppSuperEmpId();
        String approverEmpId = getApproverData.getApprovalAppEmpId();
        String appEmpEncryptKey = getApproverData.getApprovalAppEmpEncryptKey();
        String cycleCode = getApproverData.getCycleCode();
        String saveOrModify = getApproverData.getSaveOrModifyFlag();
        String submitFlag = getApproverData.getapprovalAppSubmitFlag();

        int maxApprovalAppraisalID = 0;
        int saveApprovalAppStatus = 0;

        approvalAppQuesID = getApproverData.getapprovalAppQuesID();
        approvalAppRemark = getApproverData.getapprovalAppRemark();


        try {
            DBconnection connectToDB = new DBconnection();
            Connection con = connectToDB.getSampleProperty();
            Statement st = con.createStatement();

            if (saveOrModify.equals("saveNew")) {


                String queryForGetMaxID = "SELECT CASE WHEN MAX(approval_app_remrk_id) IS NOT NULL THEN MAX(approval_app_remrk_id) ELSE 0 END AS numb FROM approval_appraisal_remark";

                ResultSet rs = st.executeQuery(queryForGetMaxID);
                if (!rs.wasNull()) {

                    while (rs.next()) {
                        maxApprovalAppraisalID = Integer.parseInt(rs.getString(1));

                    }
                } else {
                    maxApprovalAppraisalID = 0;
                }
                rs.close();
                maxApprovalAppraisalID = maxApprovalAppraisalID + 1;


                for (int i = 0; i < approvalAppQuesID.size(); i++) {
                    queryToSaveAppraovalApp = "INSERT INTO approval_appraisal_remark (approval_app_remrk_id, cycle_code, member_emp_id, super_emp_id, approval_emp_id, approval_app_ques_id,"
                            + " approval_app_remark, approval_app_date, submit_flag, approval_app_status_flag)	VALUES ('" + maxApprovalAppraisalID + "','" + cycleCode + "','" + memberEmpId + "','" + superEmpId + "'"
                            + " ,'" + approverEmpId + "','" + approvalAppQuesID.get(i).toString() + "',AES_ENCRYPT('" + approvalAppRemark.get(i).toString() + "','" + appEmpEncryptKey + "'),current_timestamp,'" + submitFlag + "',1)";
                    saveApprovalAppStatus = st.executeUpdate(queryToSaveAppraovalApp);
                    //System.out.println(saveApprovalAppStatus+"saveApprovalAppStatus and queryToSaveAppraovalApp"+queryToSaveAppraovalApp);

                }

            } else {
                for (int i = 0; i < approvalAppQuesID.size(); i++) {
                    queryToUpdateAppraovalApp = "UPDATE approval_appraisal_remark SET approval_app_remark = AES_ENCRYPT('" + approvalAppRemark.get(i).toString() + "','" + appEmpEncryptKey + "') , approval_app_date = current_timestamp, submit_flag = '" + submitFlag + "' "
                            + " WHERE cycle_code = '" + cycleCode + "' and member_emp_id = '" + memberEmpId + "' and super_emp_id = '" + superEmpId + "' and approval_emp_id = '" + approverEmpId + "' and approval_app_ques_id='" + approvalAppQuesID.get(i).toString() + "'";
                    saveApprovalAppStatus = st.executeUpdate(queryToUpdateAppraovalApp);
                    //System.out.println(approvalAppQuesID.size() + " and " + approvalAppRemark.size() + " queryToUpdateAppraovalApp = " + queryToUpdateAppraovalApp);
                }
            }
            st.close();
            con.close();

        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return saveApprovalAppStatus;
    }

    public void getEmployeeForApproval(SaveApproverAppraisalVO getApproverData, String approvalEmpID, String deptCode, String desigCode) {

        List empForApprovalNameList = new ArrayList();
        List empForApprovalIDList = new ArrayList();
        List empTempName = new ArrayList();
        List empTempId = new ArrayList();

        try {
            DBconnection connectToDB = new DBconnection();
            Connection con = connectToDB.getSampleProperty();
            Statement st = con.createStatement();
            
            String cycle_code = getApproverData.getCycleCode();
            
            String queryForEmpForApproval = "SELECT emp_name,emp_id FROM USER WHERE STATUS=1 AND approval_authority='" + approvalEmpID + "' AND dept_code='" + deptCode + "' AND desig_code='" + desigCode + "'";

            ResultSet rs = st.executeQuery(queryForEmpForApproval);
            while (rs.next()) {
                empTempName.add(rs.getString(1));
                empTempId.add(rs.getString(2));
            }
            for(int i=0; i<empTempId.size(); i++){
                String queryForAppraisal =  "SELECT * FROM approval_appraisal_remark WHERE cycle_code='"+cycle_code+"' AND  member_emp_id='"+empTempId.get(i)+"'";
                ResultSet result = st.executeQuery(queryForAppraisal);
                if(!result.next()){
                    empForApprovalNameList.add(empTempName.get(i));
                    empForApprovalIDList.add(empTempId.get(i));
                }
 else{
    empForApprovalNameList.add(empTempName.get(i));
    empForApprovalIDList.add(empTempId.get(i));
 }
            }
            
            getApproverData.setEmpNameForApproval(empForApprovalNameList);
            getApproverData.setEmpIDForApproval(empForApprovalIDList);

            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
