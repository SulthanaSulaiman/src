/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author rajac
 */
public class WIPInfo implements Serializable {

    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String temp_val = "";
    private String proj_id = "";
    private String proj_lineitem_id = "";
    private String WIP_chap_id = "";
    private String WIP_chap_stg = "";
    private String WIP_chap_act = "";

    private List chapter_id = new ArrayList();
    private List chapter_stg = new ArrayList();
    private List proj_lineitems_id = new ArrayList();
    private List proj_lineitems_act_id = new ArrayList();
    private List WIP_FP_lt_chap_id = new ArrayList();
    private List WIP_FP_lt_no_emp = new ArrayList();
    private List WIP_FP_lt_no_hr = new ArrayList();
    private List WIP_RP_lt_chap_id = new ArrayList();
    private List WIP_RP_lt_no_emp = new ArrayList();
    private List WIP_RP_lt_no_hr = new ArrayList();

    public void setProjId(String proj_id) {
        this.proj_id = proj_id;
    }

    public void setProjLineItemId(String proj_lineitem_id) {
        this.proj_lineitem_id = proj_lineitem_id;
    }

    public void setWIPChapAct(String WIP_chap_act) {
        this.WIP_chap_act = WIP_chap_act;
    }

    public void setWIPChapId(String WIP_chap_id) {
        this.WIP_chap_id = WIP_chap_id;
    }

    public void setWIPChapStg(String WIP_chap_stg) {
        this.WIP_chap_stg = WIP_chap_stg;
    }

    public List getChapterId() {
        return chapter_id;
    }

    public List getChapterStg() {
        return chapter_stg;
    }

    public List getProjLineItemsActId() {
        return proj_lineitems_act_id;
    }

    public List getProjLineItemsId() {
        return proj_lineitems_id;
    }

    public List getWIPLineItemsFPChapId() {
        return WIP_FP_lt_chap_id;
    }

    public List getWIPLineItemsFPEmpCount() {
        return WIP_FP_lt_no_emp;
    }

    public List getWIPLineItemsFPHourCount() {
        return WIP_FP_lt_no_hr;
    }

    public List getWIPLineItemsRPChapId() {
        return WIP_RP_lt_chap_id;
    }

    public List getWIPLineItemsRPEmpCount() {
        return WIP_RP_lt_no_emp;
    }

    public List getWIPLineItemsRPHourCount() {
        return WIP_RP_lt_no_hr;
    }


    public void getProjSummary() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("proj_id - in java : "+proj_id);
            //System.out.println("proj_lineitem_id - in java : "+proj_lineitem_id);
            sql_select = "select ch.chapter_id,ch.stage ";
            sql_from = "from chapter ch,projects pr,project_stage ps ";
            sql_where = "ps.stage_code=ch.stage and ch.proj_id = pr.proj_id and ch.proj_id='" + proj_id + "' and (ch.stage='FP' or ch.stage='RP') and ch.chapter_deleted='0' order by ch.stage ";

            sql_select += sql_from + "where " + sql_where;

            rs = stmt.executeQuery(sql_select);

            chapter_id.clear();
            chapter_stg.clear();
            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {
                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    chapter_id.add(temp_val);

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    chapter_stg.add(temp_val);
                }
            } else {
                chapter_id.add("");
                chapter_stg.add("");
            }

            //System.out.println("chapter_id - in java : "+chapter_id);
            //System.out.println("chapter_stg - in java : "+chapter_stg);
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void getProjLineItemsActivity() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("proj_id - in java : "+proj_id);
            //System.out.println("proj_lineitem_id - in java : "+proj_lineitem_id);
            sql_select = "SELECT ltm.billing_item_id,ltm.activity_code ";
            sql_from = "FROM lineitem_act_map ltm,billing_lineitems_master bltm,activity_type aty ";
            sql_where = "ltm.billing_item_id=bltm.billing_item_id AND aty.activity_code=ltm.activity_code AND ltm.lineitem_act_status='1' AND ltm.billing_item_id='" + proj_lineitem_id + "' ";

            sql_select += sql_from + "where " + sql_where;

            rs = stmt.executeQuery(sql_select);

            proj_lineitems_id.clear();
            proj_lineitems_act_id.clear();
            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {
                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    proj_lineitems_id.add(temp_val);

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    proj_lineitems_act_id.add(temp_val);
                }
            } else {
                proj_lineitems_id.add("");
                proj_lineitems_act_id.add("");
            }

            //System.out.println("chapter_id - in java : "+chapter_id);
            //System.out.println("chapter_stg - in java : "+chapter_stg);
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void getWIPSummary() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            /*System.out.println("WIP_chap_id - in java : "+WIP_chap_id);
            System.out.println("WIP_chap_stg - in java : "+WIP_chap_stg);
            System.out.println("WIP_chap_act - in java : "+WIP_chap_act);*/

            /*WIP_FP_lt_chap_id.clear();
            WIP_FP_lt_no_emp.clear();
            WIP_FP_lt_no_hr.clear();
            WIP_RP_lt_chap_id.clear();
            WIP_RP_lt_no_emp.clear();
            WIP_RP_lt_no_hr.clear();*/

            if (WIP_chap_stg.equals("FP")) {

                sql_select = "SELECT ch.chapter_id,COUNT(DISTINCT(act.emp_id)),SUM(TIME_TO_SEC(TIMEDIFF(act.end_time,act.begin_time))) ";
                sql_from = "FROM activity act,chapter ch,activity_type aty,USER u,project_stage ps ";
                sql_where = "act.chapter_id=ch.chapter_id AND u.emp_id=act.emp_id AND act.activity_code=aty.activity_code AND ps.stage_code=ch.stage "
                        + " AND act.chapter_id='" + WIP_chap_id + "' AND ch.stage='FP' AND act.activity_code='" + WIP_chap_act + "' ";

                sql_select += sql_from + "where " + sql_where;

                rs = stmt.executeQuery(sql_select);

                //WIP_FP_lt_chap_id.clear();
                //WIP_FP_lt_no_emp.clear();
                //WIP_FP_lt_no_hr.clear();
                if (rs.next()) {
                    rs = stmt.executeQuery(sql_select);
                    while (rs.next()) {
                        temp_val = rs.getString(1);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        WIP_FP_lt_chap_id.add(temp_val);

                        temp_val = rs.getString(2);
                        if (rs.wasNull()) {
                            temp_val = "0";
                        }
                        WIP_FP_lt_no_emp.add(temp_val);

                        temp_val = rs.getString(3);
                        if (rs.wasNull()) {
                            temp_val = "0";
                        }
                        WIP_FP_lt_no_hr.add(temp_val);
                    }
                } else {
                    WIP_FP_lt_chap_id.add("");
                    WIP_FP_lt_no_emp.add("0");
                    WIP_FP_lt_no_hr.add("0");
                }
            }

            else if(WIP_chap_stg.equals("RP")) {

                sql_select = "SELECT ch.chapter_id,COUNT(DISTINCT(act.emp_id)),SUM(TIME_TO_SEC(TIMEDIFF(act.end_time,act.begin_time))) ";
                sql_from = "FROM activity act,chapter ch,activity_type aty,USER u,project_stage ps ";
                sql_where = "act.chapter_id=ch.chapter_id AND u.emp_id=act.emp_id AND act.activity_code=aty.activity_code AND ps.stage_code=ch.stage "
                        + " AND act.chapter_id='" + WIP_chap_id + "' AND ch.stage='RP' AND act.activity_code='" + WIP_chap_act + "' ";

                sql_select += sql_from + "where " + sql_where;

                rs = stmt.executeQuery(sql_select);

                //WIP_RP_lt_chap_id.clear();
                //WIP_RP_lt_no_emp.clear();
                //WIP_RP_lt_no_hr.clear();
                if (rs.next()) {
                    rs = stmt.executeQuery(sql_select);
                    while (rs.next()) {
                        temp_val = rs.getString(1);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        WIP_RP_lt_chap_id.add(temp_val);

                        temp_val = rs.getString(2);
                        if (rs.wasNull()) {
                            temp_val = "0";
                        }
                        WIP_RP_lt_no_emp.add(temp_val);

                        temp_val = rs.getString(3);
                        if (rs.wasNull()) {
                            temp_val = "0";
                        }
                        WIP_RP_lt_no_hr.add(temp_val);
                    }
                } else {
                    WIP_RP_lt_chap_id.add("");
                    WIP_RP_lt_no_emp.add("0");
                    WIP_RP_lt_no_hr.add("0");
                }
            }

            /*System.out.println("WIP_FP_lt_chap_id : "+WIP_FP_lt_chap_id);
            System.out.println("WIP_FP_lt_no_emp : "+WIP_FP_lt_no_emp);
            System.out.println("WIP_FP_lt_no_hr : "+WIP_FP_lt_no_hr);
            System.out.println("WIP_RP_lt_chap_id : "+WIP_RP_lt_chap_id);
            System.out.println("WIP_RP_lt_no_emp : "+WIP_RP_lt_no_emp);
            System.out.println("WIP_RP_lt_no_hr : "+WIP_RP_lt_no_hr);*/

            //System.out.println("chapter_id - in java : "+chapter_id);
            //System.out.println("chapter_stg - in java : "+chapter_stg);
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
}
