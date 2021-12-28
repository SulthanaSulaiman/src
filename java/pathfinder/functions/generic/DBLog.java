/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.generic;

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author ramyams
 */
public class DBLog {

    /**logging the updates in any table
     * input form either in list or string
     */
    connection.DBconnection connection = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private int intRes = 0;
    private int[] intResCnt;
    private String table_name = "";
    private String field_name = "";
    private String linked_field_name = "";
    private String linked_field_value = "";
    private String old_value = "";
    private String new_value = "";
    private String ref_table = "";
    private String ref_field = "";
    private String ref_field_value = "";
    private String date_changed = "";
    private String changed_by = "";
    private List list_table_name = new ArrayList();
    private List list_field_name = new ArrayList();
    private List list_linked_field_name = new ArrayList();
    private List list_linked_field_value = new ArrayList();
    private List list_old_value = new ArrayList();
    private List list_new_value = new ArrayList();
    private List list_changed_by = new ArrayList();
    private List list_ref_table = new ArrayList();
    private List list_ref_field = new ArrayList();
    private List list_ref_field_value = new ArrayList();
    private String sql_query = "";
    private String moduleName = "";
    private String logOption = "";

    /** Creates a new instance of DBLog */
    public DBLog() {
    }

    public DBLog(List list_table_name, List list_field_name, List list_linked_field_name, List list_linked_field_value, List list_old_value,
            List list_new_value, List list_changed_by, List list_ref_table, List list_ref_field, List list_ref_field_value) {

        //System.out.println("inside dblog");
        this.list_table_name = list_table_name;
        this.list_field_name = list_field_name;
        this.list_linked_field_name = list_linked_field_name;
        this.list_linked_field_value = list_linked_field_value;
        this.list_old_value = list_old_value;
        this.list_new_value = list_new_value;
        this.list_changed_by = list_changed_by;
        this.list_ref_table = list_ref_table;
        this.list_ref_field = list_ref_field;
        this.list_ref_field_value = list_ref_field_value;
    }

    public void setTableName(String table_name) {
        this.table_name = table_name;
    }

    public void setFieldName(String field_name) {
        this.field_name = field_name;
    }

    public void setRefTable(String ref_table) {
        this.ref_table = ref_table;
    }

    public void setRefField(String ref_field) {
        this.ref_field = ref_field;
    }

    public void setRefFieldValue(String ref_field_value) {
        this.ref_field_value = ref_field_value;
    }

    public void setLinkFieldName(String linked_field_name) {
        this.linked_field_name = linked_field_name;
    }

    public void setLinkFieldValue(String linked_field_value) {
        this.linked_field_value = linked_field_value;
    }

    public void setOldValue(String old_value) {
        this.old_value = old_value;
        if (old_value.equals("") || old_value.equals("All")) {
            old_value = null;
        }
    }

    public void setNewValue(String new_value) {
        this.new_value = new_value;
        if (new_value.equals("") || new_value.equals("All")) {
            new_value = null;
        }
    }

    public void setChangedBy(String changed_by) {
        this.changed_by = changed_by;
    }

    public void setTableNameList(List list_table_name) {
        this.list_table_name = list_table_name;
    }

    public void setFieldNameList(List list_field_name) {
        this.list_field_name = list_field_name;
    }

    public void setLinkFieldNameList(List list_linked_field_name) {
        this.list_linked_field_name = list_linked_field_name;
    }

    public void setLinkFieldValueList(List list_linked_field_value) {
        this.list_linked_field_value = list_linked_field_value;
    }

    public void setOldValueList(List list_old_value) {
        this.list_old_value = list_old_value;
    }

    public void setReferenceTableName(List list_ref_table) {
        this.list_ref_table = list_ref_table;
    }

    public void setReferenceTableField(List list_ref_field) {
        this.list_ref_field = list_ref_field;
    }

    public void setRefFieldValueList(List list_ref_field_value) {
        this.list_ref_field_value = list_ref_field_value;
    }

    public void setNewValueList(List list_new_value) {
        this.list_new_value = list_new_value;
    }

    public void setChangedByList(List list_changed_by) {
        this.list_changed_by = list_changed_by;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setLogOption(String logOption) {
        this.logOption = logOption;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getLogOption() {
        return logOption;
    }

    public int createLog() {

        if (list_table_name.size() > 0) {

            try {
                con = connection.getSampleProperty();
//start transaction for batch update
                con.setAutoCommit(false);
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

//                System.out.println(list_table_name);
//                System.out.println(list_field_name);
//                System.out.println(list_linked_field_name);
//                System.out.println(list_linked_field_value);
//                System.out.println(list_ref_field);
//                System.out.println(list_ref_table);
//                System.out.println(list_old_value);
//                System.out.println(list_new_value);
//                System.out.println(list_changed_by);
//                System.out.println(list_ref_field_value);
//                System.out.println("tableNameSize:" + list_table_name.size());
                for (int loop = 0; loop < list_table_name.size(); loop++) {

                    //System.out.println("inside for:" + list_table_name.size());
                    setTableName(list_table_name.get(loop).toString());
                    setFieldName(list_field_name.get(loop).toString());
                    setLinkFieldName(list_linked_field_name.get(loop).toString());
                    setLinkFieldValue(list_linked_field_value.get(loop).toString());
                    // set

                    try {
                        setOldValue(list_old_value.get(loop).toString());
                    } catch (NullPointerException npe) {
                        setOldValue("");
                    }
                    try {
                        setNewValue(list_new_value.get(loop).toString());
                    } catch (NullPointerException npe) {
                        setNewValue("");
                    }

                   // System.out.println("ref_table size:" + list_ref_table.size());

                    setRefTable(list_ref_table.get(loop).toString());

                    setRefField(list_ref_field.get(loop).toString());

                    if (list_ref_field_value.size() > 0) {
                        setRefFieldValue(list_ref_field_value.get(loop).toString());
                    }

                    setChangedBy(list_changed_by.get(loop).toString());

                    if (old_value.equals("") || old_value.equals("All")) {
                        old_value = null;
                    } else {
                        if (old_value.indexOf("'") == -1) {
                            old_value = "'" + old_value + "'";
                        }
                    }

                    if (new_value.equals("") || new_value.equals("All")) {
                        new_value = null;
                    } else {
                        if (new_value.indexOf("'") == -1) {
                            new_value = "'" + new_value + "'";
                        }
                    }

                    if (ref_field_value.equals("") || ref_field_value.equals("All")) {
                        ref_field_value = null;
                    } else {
                        ref_field_value = "'" + ref_field_value + "'";
                    }

                    if (linked_field_value.equals("") || linked_field_value.equals("All")) {
                        linked_field_value = null;
                    } else {
                        linked_field_value = "'" + linked_field_value + "'";
                    }

                    if (linked_field_name.equals("") || linked_field_name.equals("All")) {
                        linked_field_name = null;
                    } else {
                        if (linked_field_name.indexOf("'") == -1) {
                            linked_field_name = "'" + linked_field_name + "'";
                        }
                    }

                    if (changed_by.equals("") || changed_by.equals("All")) {
                        changed_by = null;
                    } else {
                        if (changed_by.indexOf("'") == -1) {
                            changed_by = "'" + changed_by + "'";
                        }
                    }

                    if (linked_field_name == null) {

                        sql_query = "insert into db_log(table_name,field_name,old_value,new_value,date_changed,changed_by ";
                        if (!ref_table.equals("")) {
                            sql_query += " ,reference_table_name,reference_table_field,reference_field_value ";
                        }
                        sql_query += ")"
                                + "values('" + table_name + "','" + field_name + "'," + old_value + "," + new_value + ",current_timestamp()," + changed_by;
                        if (!ref_table.equals("")) {
                            sql_query += " ,'" + ref_table + "','" + ref_field + "'," + ref_field_value + " ";
                        }
                        sql_query += ")";
                        stmt.addBatch(sql_query);

                    } else {
                        sql_query = "insert into db_log(table_name,field_name,linked_field_name,linked_field_value,old_value,new_value,date_changed,changed_by ";

                        if (!ref_table.equals("")) {
                            sql_query += " ,reference_table_name,reference_table_field,reference_field_value ";
                        }
                        sql_query += ")"
                                + "values('" + table_name + "','" + field_name + "'," + linked_field_name + "," + linked_field_value + "," + old_value + "," + new_value + ",current_timestamp()," + changed_by;
                        if (!ref_table.equals("")) {
                            sql_query += " ,'" + ref_table + "','" + ref_field + "'," + ref_field_value + " ";
                        }
                        sql_query += ")";

                        stmt.addBatch(sql_query);
                    }
                    //System.out.println("sql_query : " + sql_query);
                }


                intResCnt = stmt.executeBatch();

                con.commit();
                con.setAutoCommit(true);
                intRes = 1;
                con.close();
                //System.out.println("intRes : " + intResCnt);
                stmt.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
            return intRes;
        } else {

            if (old_value.equals("") || old_value.equals("All")) {
                old_value = null;
            } else {
                old_value = "'" + old_value + "'";
            }

            if (new_value.equals("") || new_value.equals("All")) {
                new_value = null;
            } else {
                new_value = "'" + new_value + "'";
            }

            if (linked_field_name.equals("") || linked_field_name.equals("All")) {
                linked_field_name = null;
            } else {
                linked_field_name = "'" + linked_field_name + "'";
            }

            if (ref_field_value.equals("") || ref_field_value.equals("All")) {
                ref_field_value = null;
            } else {
                ref_field_value = "'" + ref_field_value + "'";
            }

            if (linked_field_value.equals("") || linked_field_value.equals("All")) {
                linked_field_value = null;
            } else {
                linked_field_value = "'" + linked_field_value + "'";
            }

            if (date_changed.equals("") || date_changed.equals("All")) {
                date_changed = null;
            } else {
                date_changed = "'" + new_value + "'";
            }

            if (changed_by.equals("") || changed_by.equals("All")) {
                changed_by = null;
            } else {
                changed_by = "'" + changed_by + "'";
            }

            try {
                con = connection.getSampleProperty();
                stmt = con.createStatement();
                /*  if(linked_field_name.equals("")){
                sql_query = "insert into db_log(table_name,field_name,old_value,new_value,date_changed,changed_by) " +
                "values('"+table_name+"','"+field_name+"',"+old_value+","+new_value+",current_timestamp(),'"+changed_by+"')";
                }else{
                sql_query = "insert into db_log(table_name,field_name,linked_field_name,old_value,new_value,date_changed,changed_by) " +
                "values('"+table_name+"','"+field_name+"','"+linked_field_name+"',"+old_value+","+new_value+",current_timestamp(),'"+changed_by+"')";
                }*/

                if (linked_field_name == null) {

                    sql_query = "insert into db_log(table_name,field_name,old_value,new_value,date_changed,changed_by ";
                    if (!ref_table.equals("")) {
                        sql_query += " ,reference_table_name,reference_table_field,reference_field_value ";
                    }
                    sql_query += ")"
                            + "values('" + table_name + "','" + field_name + "'," + old_value + "," + new_value + ",current_timestamp()," + changed_by;
                    if (!ref_table.equals("")) {
                        sql_query += " ,'" + ref_table + "','" + ref_field + "'," + ref_field_value + " ";
                    }
                    sql_query += ")";
                    stmt.addBatch(sql_query);

                } else {
                    sql_query = "insert into db_log(table_name,field_name,linked_field_name,linked_field_value,old_value,new_value,date_changed,changed_by ";

                    if (!ref_table.equals("")) {
                        sql_query += " ,reference_table_name,reference_table_field,reference_field_value ";
                    }
                    sql_query += ")"
                            + "values('" + table_name + "','" + field_name + "'," + linked_field_name + "," + linked_field_value + "," + old_value + "," + new_value + ",current_timestamp()," + changed_by;
                    if (!ref_table.equals("")) {
                        sql_query += " ,'" + ref_table + "','" + ref_field + "'," + ref_field_value + " ";
                    }
                    sql_query += ")";
                    stmt.addBatch(sql_query);
                }

                try {
                    //System.out.println("sql_query : " + sql_query);
                    intRes = stmt.executeUpdate(sql_query);
                    //  System.out.println("intRes : "+intRes);
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
            return intRes;
        }
    }

    public void DBLogOption() {

        int log_option = 0;

        try {
            con = connection.getSampleProperty();
            stmt = con.createStatement();
            ResultSet rsLogOption = stmt.executeQuery("select log_option from dblog_preference where module_name='" + moduleName + "' ");
            while (rsLogOption.next()) {
                log_option = rsLogOption.getInt("log_option");
            }

            logOption = String.valueOf(log_option);
            rsLogOption.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
}
