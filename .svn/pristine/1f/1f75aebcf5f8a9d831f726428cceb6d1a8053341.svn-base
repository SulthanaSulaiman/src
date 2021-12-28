/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import connection.DBconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aravindhanj
 */
public class ManageCategoryMilestonesDAO {

    // Wrapper Class to get both JCO and WIP Details
    public ManageCategoryMilestonesVO getFundamentalDetails(ManageCategoryMilestonesVO manageCategoryMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String contentQuery = "";
        List MilestoneList = new ArrayList();
        List MilestoneIdList = new ArrayList();
        List milestoneCategoryList = new ArrayList();
        List CategoryList = new ArrayList();
        List CategoryIdList = new ArrayList();
        List categorySuperCategoryList = new ArrayList();
        List SuperCategoryList = new ArrayList();
        List SuperCategoryIdList = new ArrayList();
        List poActivityIdList = new ArrayList();
        List poActivityList = new ArrayList();
        List poSuperCategoryList = new ArrayList();
        try
        {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            // Get List of Super Categories
            contentQuery = "SELECT super_cat_id, super_category FROM estimate_super_category WHERE super_cat_id<>'9'";

            rs = st.executeQuery(contentQuery);

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    SuperCategoryIdList.add(rs.getString(1));
                } else {
                    SuperCategoryIdList.add("");
                }
                if (rs.getString(2)  != null) {
                    SuperCategoryList.add(rs.getString(2));
                } else {
                    SuperCategoryList.add("");
                }
            }

            // Get List of Categories
            contentQuery = "SELECT category_id, category, super_category FROM estimate_category WHERE category_id>=157";

            rs = st.executeQuery(contentQuery);

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    CategoryIdList.add(rs.getString(1));
                } else {
                    CategoryIdList.add("");
                }
                if (rs.getString(2)  != null) {
                    CategoryList.add(rs.getString(2));
                } else {
                    CategoryList.add("");
                }
                if (rs.getString(3)  != null) {
                    categorySuperCategoryList.add(rs.getString(3));
                } else {
                    categorySuperCategoryList.add("");
                }
            }

            // Get List of Milestones
            contentQuery = "SELECT milestone_act_code, milestone_act_name, est_category_id FROM proj_milestone_act "
                    + "WHERE JCO_WIP_enable_flag='1'";

            rs = st.executeQuery(contentQuery);

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    MilestoneIdList.add(rs.getString(1));
                } else {
                    MilestoneIdList.add("");
                }
                if (rs.getString(2)  != null) {
                    MilestoneList.add(rs.getString(2));
                } else {
                    MilestoneList.add("");
                }
                if (rs.getString(3)  != null) {
                    milestoneCategoryList.add(rs.getString(3));
                } else {
                    milestoneCategoryList.add("");
                }
            }

            // Get List of PO Activities
            contentQuery = "SELECT billing_item_id, name, super_cat_id FROM billing_lineitems_master";

            rs = st.executeQuery(contentQuery);

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    poActivityIdList.add(rs.getString(1));
                } else {
                    poActivityIdList.add("");
                }
                if (rs.getString(2)  != null) {
                    poActivityList.add(rs.getString(2));
                } else {
                    poActivityList.add("");
                }
                if (rs.getString(3)  != null) {
                    poSuperCategoryList.add(rs.getString(3));
                } else {
                    poSuperCategoryList.add("");
                }
            }

            manageCategoryMilestonesVO.setMilestoneIdList(MilestoneIdList);
            manageCategoryMilestonesVO.setMilestoneList(MilestoneList);
            manageCategoryMilestonesVO.setMilestoneCategoryList(milestoneCategoryList);
            manageCategoryMilestonesVO.setCategoryIdList(CategoryIdList);
            manageCategoryMilestonesVO.setCategoryList(CategoryList);
            manageCategoryMilestonesVO.setCategorySuperCategoryList(categorySuperCategoryList);
            manageCategoryMilestonesVO.setSuperCategoryIdList(SuperCategoryIdList);
            manageCategoryMilestonesVO.setSuperCategoryList(SuperCategoryList);
            manageCategoryMilestonesVO.setPoActivityIdList(poActivityIdList);
            manageCategoryMilestonesVO.setPoActivityList(poActivityList);
            manageCategoryMilestonesVO.setPoSuperCategoryList(poSuperCategoryList);
            
        } catch (SQLException sqle) {
            System.out.println("SQLException : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException : "+npe);
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException : "+npe);
            }
        }
        return manageCategoryMilestonesVO;
    }

    public static void mapCategoryUnderSuperCategory(ManageCategoryMilestonesVO manageCategoryMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;

        String categorySuperCategoryQuery = "";
        String cat = "";
        String sup = "";
        List extSuperCategoryIdList = new ArrayList();
        List extCategoryIdList = new ArrayList();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            extSuperCategoryIdList = manageCategoryMilestonesVO.getSuperCategoryIdList();
            extCategoryIdList = manageCategoryMilestonesVO.getCategoryIdList();
            for(int idx=0; idx<extSuperCategoryIdList.size(); idx++) {
                cat = extCategoryIdList.get(idx).toString();
                sup = extSuperCategoryIdList.get(idx).toString().equals("-1")?"0":extSuperCategoryIdList.get(idx).toString();
                categorySuperCategoryQuery = "UPDATE estimate_category "
                        + "SET super_category='"+sup+"' "
                        + "WHERE category_id='"+cat+"'";
                result += st.executeUpdate(categorySuperCategoryQuery);
            }
            if(result==extSuperCategoryIdList.size()) {
                manageCategoryMilestonesVO.setResult(1);
            } else {
                manageCategoryMilestonesVO.setResult(0);
            }
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException : " + e);
            } catch (Exception e) {
                System.out.println("NullPointerException : " + e);
            }
        }
        return;
    }

    public static void mapMilestoneUnderCategory(ManageCategoryMilestonesVO manageCategoryMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;

        String CategoryMilestoneQuery = "";
        String cat = "";
        String mile = "";
        List extMilestoneIdList = new ArrayList();
        List extCategoryIdList = new ArrayList();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            extMilestoneIdList = manageCategoryMilestonesVO.getMilestoneIdList();
            extCategoryIdList = manageCategoryMilestonesVO.getCategoryIdList();
            for(int idx=0; idx<extMilestoneIdList.size(); idx++) {

                cat = extCategoryIdList.get(idx).toString().equals("-1")?"0":extCategoryIdList.get(idx).toString();
                mile = extMilestoneIdList.get(idx).toString();

                CategoryMilestoneQuery = "UPDATE proj_milestone_act "
                        + "SET est_category_id='"+cat+"' "
                        + "WHERE milestone_act_code='"+mile+"'";
                result += st.executeUpdate(CategoryMilestoneQuery);
            }
            if(result==extMilestoneIdList.size()) {
                manageCategoryMilestonesVO.setResult(1);
            } else {
                manageCategoryMilestonesVO.setResult(0);
            }
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException : " + e);
            } catch (Exception e) {
                System.out.println("NullPointerException : " + e);
            }
        }
        return;
    }

    public static void mapPOActivityUnderSupCategory(ManageCategoryMilestonesVO manageCategoryMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;

        String poSuperCategoryQuery = "";
        String poActivity = "";
        String supCategory = "";
        List extPOActivityIdList = new ArrayList();
        List extSuperCategoryIdList = new ArrayList();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            extPOActivityIdList = manageCategoryMilestonesVO.getPoActivityIdList();
            extSuperCategoryIdList = manageCategoryMilestonesVO.getPoSuperCategoryList();
            for(int idx=0; idx<extPOActivityIdList.size(); idx++) {

                supCategory = extSuperCategoryIdList.get(idx).toString().equals("-1")?"0":extSuperCategoryIdList.get(idx).toString();
                poActivity = extPOActivityIdList.get(idx).toString();

                poSuperCategoryQuery = "UPDATE billing_lineitems_master "
                        + " SET super_cat_id='"+supCategory+"' "
                        + " WHERE billing_item_id='"+poActivity+"'";

                result += st.executeUpdate(poSuperCategoryQuery);
            }

            if(result==extSuperCategoryIdList.size()) {
                manageCategoryMilestonesVO.setResult(1);
            } else {
                manageCategoryMilestonesVO.setResult(0);
            }
            
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException : " + e);
            } catch (Exception e) {
                System.out.println("NullPointerException : " + e);
            }
        }
        return;
    }

     public static void addNewCategory(ManageCategoryMilestonesVO manageCategoryMilestonesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;

        String newCatQuery = "";
        List newCategory = new ArrayList();
        newCategory = manageCategoryMilestonesVO.getNewCategory();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            for(int idx=0; idx<newCategory.size(); idx++) {

                if(!newCategory.get(idx).toString().equals("")) {
                    newCatQuery = "INSERT INTO estimate_category (category) "
                            + "SELECT * FROM (SELECT '"+newCategory.get(idx).toString()+"') AS tmp "
                            + "WHERE NOT EXISTS ( "
                            + "SELECT category FROM estimate_category WHERE category = '"+newCategory.get(idx).toString()+"' ) LIMIT 1";
                    result += st.executeUpdate(newCatQuery);
                } else {
                    result++;
                }
            }
            
            if(result==newCategory.size()) {
                manageCategoryMilestonesVO.setResult(1);
            } else {
                manageCategoryMilestonesVO.setResult(0);
            }

        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException : " + e);
            } catch (Exception e) {
                System.out.println("NullPointerException : " + e);
            }
        }
        return;
    }
}
