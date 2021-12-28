/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;

import connection.DBconnection;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Parameshwarant
 */
public class AppraisalDateCheck {

    String appraisalType="";
    String queryForGetApprsDate="";
    String appraisalStartDate="";
    String appraisalEndDate="";
    String appraisalTypeId="";

    String empID="";
    String queryForElgblChk="";
    String eligibleFlag="";
    String queryForEligblUpdate="";
    String alertMessage="";
    int eligblUpdateChk;
    int daysDiff;

    String appraisalTypeID="";
    String queryForGetApprslQues="";
    String cycleId="";

    List appraisalQuestion=new ArrayList();
    List appraisalQuestionID=new ArrayList();

    DBconnection dbCon = new DBconnection();
    Connection con=dbCon.getSampleProperty();


    
    public String getAppraisalStartDate()
    {
        return appraisalStartDate;
    }

    public String getAppraisalEndDate()
    {
        return appraisalEndDate;
    }

     public String getcycleCode()
    {
        return cycleId;
     }


    public void selfAppraisalDateVerify()
    {
    try
    {
        Statement st=con.createStatement();
        queryForGetApprsDate="SELECT self_app_start_date,self_app_end_date,cycle_id FROM appraisal_cycle "
                + "WHERE self_app_start_date<=(DATE_FORMAT(CURRENT_TIMESTAMP(),'%Y-%m-%d')) AND self_app_end_date>=(DATE_FORMAT(CURRENT_TIMESTAMP(),'%Y-%m-%d'))";
        ResultSet rs=st.executeQuery(queryForGetApprsDate);

        if(!rs.wasNull())
        while(rs.next())
        {
         appraisalStartDate=rs.getString(1);
         appraisalEndDate=rs.getString(2);
         cycleId=rs.getString(3);
        }
        rs.close();
        st.close();
        con.close();
        }

    catch(SQLException e)
    {
        e.printStackTrace();
        System.out.println(e.toString());
    }
    }
}

    
