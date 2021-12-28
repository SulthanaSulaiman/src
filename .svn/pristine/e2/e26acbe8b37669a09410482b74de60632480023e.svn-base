/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import connection.DBconnection;
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
public class CategoryCostDAO {


    // Get Cost Fundamental Details
    public CategoryCostVO getAllCategoryCostDetails() {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        CategoryCostVO categoryCostVO = new CategoryCostVO();
        List<String> dispClientId = new ArrayList<String>();
        List<String> dispClient = new ArrayList<String>();
        List<String> dispCategoryCostId = new ArrayList<String>();
        List<String> dispCategory = new ArrayList<String>();
        List<String> dispCategoryValue = new ArrayList<String>();

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try
        {
            con = dbconnection.getSampleProperty();

            // Get List of All Category values
            prepStmt = con.prepareStatement("SELECT (CASE WHEN c.company IS NOT NULL THEN c.company ELSE 'General' END) , "
                    + "	cc.client_id, cc.category_cost_id, ec.category, cc.category_value "
                    + "	FROM estimate_category ec, category_cost cc "
                    + "	LEFT JOIN contacts c ON c.contact_id=cc.client_id "
                    + "	WHERE cc.category_id=ec.category_id "
                    + "ORDER BY client_id");
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    dispClient.add(splChar.decoding(rs.getString(1)));
                } else {
                    dispClient.add("");
                }
                if (rs.getString(2)  != null) {
                    dispClientId.add(rs.getString(2));
                } else {
                    dispClientId.add("");
                }
                if (rs.getString(3)  != null) {
                    dispCategoryCostId.add(rs.getString(3));
                } else {
                    dispCategoryCostId.add("");
                }
                if (rs.getString(4)  != null) {
                    dispCategory.add(rs.getString(4));
                } else {
                    dispCategory.add("");
                }
                if (rs.getString(5)  != null) {
                    dispCategoryValue.add(rs.getString(5));
                } else {
                    dispCategoryValue.add("");
                }
            }

            categoryCostVO.setDispClientId(dispClientId);
            categoryCostVO.setDispClient(dispClient);
            categoryCostVO.setDispCategoryCostId(dispCategoryCostId);
            categoryCostVO.setDispCategory(dispCategory);
            categoryCostVO.setDispCategoryValue(dispCategoryValue);

        } catch (SQLException sqle) {
            System.out.println("SQLException : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException : "+npe);
        } finally {
            try {
                rs.close();
                prepStmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException : "+npe);
            }
        }
        return categoryCostVO;
    }

    // Get Cost Fundamental Details
    public CategoryCostVO getGeneralCostDetails(CategoryCostVO categoryCostVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;

        List genCategoryCostId = new ArrayList();
        List genCategoryId = new ArrayList();
        List genCategoryCost = new ArrayList();

        try
        {
            con = dbconnection.getSampleProperty();
            
            // Get List of General Category values
            prepStmt = con.prepareStatement("SELECT category_cost_id, category_id, category_value FROM category_cost WHERE client_id='0'");
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    genCategoryCostId.add(rs.getString(1));
                } else {
                    genCategoryCostId.add("");
                }
                if (rs.getString(2)  != null) {
                    genCategoryId.add(rs.getString(2));
                } else {
                    genCategoryId.add("");
                }
                if (rs.getString(3)  != null) {
                    genCategoryCost.add(rs.getString(3));
                } else {
                    genCategoryCost.add("");
                }
            }

            categoryCostVO.setGenCostCategoryId(genCategoryCostId);
            categoryCostVO.setGenCategoryId(genCategoryId);
            categoryCostVO.setGenCategoryValue(genCategoryCost);
            
        } catch (SQLException sqle) {
            System.out.println("SQLException : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException : "+npe);
        } finally {
            try {
                rs.close();
                prepStmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException : "+npe);
            }
        }
        return categoryCostVO;
    }

    // Get Cost Fundamental Details
    public CategoryCostVO getCategoryCostDetails(CategoryCostVO categoryCostVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;

        String clientId = "";
        List categoryCostId = new ArrayList();
        List categoryId = new ArrayList();
        List categoryCost = new ArrayList();
        List genCategoryCostId = new ArrayList();
        List genCategoryId = new ArrayList();
        List genCategoryCost = new ArrayList();

        try
        {
            con = dbconnection.getSampleProperty();
            clientId = categoryCostVO.getClientId();

            // Get List of Client's Category values
            prepStmt = con.prepareStatement("SELECT category_cost_id, category_id, category_value FROM category_cost WHERE client_id='"+clientId+"'");
            //prepStmt.setString(0, clientId);
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    categoryCostId.add(rs.getString(1));
                } else {
                    categoryCostId.add("");
                }
                if (rs.getString(2)  != null) {
                    categoryId.add(rs.getString(2));
                } else {
                    categoryId.add("");
                }
                if (rs.getString(3)  != null) {
                    categoryCost.add(rs.getString(3));
                } else {
                    categoryCost.add("");
                }
            }

            categoryCostVO.setCostCategoryId(categoryCostId);
            categoryCostVO.setCategoryId(categoryId);
            categoryCostVO.setCategoryValue(categoryCost);

        } catch (SQLException sqle) {
            System.out.println("SQLException : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException : "+npe);
        } finally {
            try {
                rs.close();
                prepStmt.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException : "+npe);
            }
        }
        return categoryCostVO;
    }

    public static CategoryCostVO InsertCategoryValue(CategoryCostVO costCategoryVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;

        String getCategoryId = "";
        String getCategoryValue = "";
        String clientId = "";
        List<String> categoryId = new ArrayList<String>();
        List<String> categoryValue = new ArrayList<String>();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("INSERT INTO category_cost "
                    + "(client_id, category_id, category_value) "
                    + "VALUES "
                    + "(?,?,?)");

            clientId = costCategoryVO.getClientId();
            categoryId = costCategoryVO.getNewCategoryIdList();
            categoryValue = costCategoryVO.getNewCategoryCostList();

            for(int idx=0; idx<categoryId.size(); idx++) {
                getCategoryId = categoryId.get(idx).toString();
                getCategoryValue = categoryValue.get(idx).toString();
                prepStmt.setString(1, clientId);
                prepStmt.setString(2, getCategoryId);
                prepStmt.setString(3, getCategoryValue);
                result += prepStmt.executeUpdate();
            }
            if(result==categoryId.size()) {
                costCategoryVO.setResult(1);
            } else {
                costCategoryVO.setResult(0);
            }
        } catch (SQLException sql) {
            System.out.println("Exception in CategoryCostDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in CategoryCostDAO :"+npe);
        } finally {
            try {
                prepStmt.close();
                con.close();
            } catch (Exception e) {
                System.out.println("Exception in CategoryCostDAO :"+e);
            }
        }
        return costCategoryVO;
    }


    public static CategoryCostVO UpdateCategoryValue(CategoryCostVO costCategoryVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;

        String getCategoryCostId = "";
        String getClientId = "";
        String getCategoryId = "";
        String getCategoryValue = "";
        List<String> costCategoryId = new ArrayList<String>();
        List<String> categoryId = new ArrayList<String>();
        List<String> categoryValue = new ArrayList<String>();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("UPDATE category_cost SET "
                    + "client_id=?, category_id=?, category_value=? "
                    + "WHERE category_cost_id=?");

            getClientId = costCategoryVO.getClientId();
            costCategoryId = costCategoryVO.getCostCategoryId();
            categoryId = costCategoryVO.getCategoryId();
            categoryValue = costCategoryVO.getCategoryValue();

            for(int idx=0; idx<categoryId.size(); idx++) {
                getCategoryCostId = costCategoryId.get(idx).toString();
                getCategoryId = categoryId.get(idx).toString();
                getCategoryValue = categoryValue.get(idx).toString();
                prepStmt.setString(1, getClientId);
                prepStmt.setString(2, getCategoryId);
                prepStmt.setString(3, getCategoryValue);
                prepStmt.setString(4, getCategoryCostId);
                result += prepStmt.executeUpdate();
            }
            if(result==categoryId.size()) {
                costCategoryVO.setResult(1);
            } else {
                costCategoryVO.setResult(0);
            }
        } catch (SQLException sql) {
            System.out.println("Exception in CategoryCostDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in CategoryCostDAO :"+npe);
        } finally {
            try {
                prepStmt.close();
                con.close();
            } catch (Exception e) {
                System.out.println("Exception in CategoryCostDAO :"+e);
            }
        }
        return costCategoryVO;
    }
    
    public static CategoryCostVO DeleteCategoryValue(CategoryCostVO costCategoryVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;

        String getCategoryCostId = "";
        String clientId = "";
        List<String> categoryCostId = new ArrayList<String>();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("DELETE FROM category_cost WHERE client_id=? AND category_cost_id=?");

            clientId = costCategoryVO.getClientId();
            categoryCostId = costCategoryVO.getDelCategoryIdList();

            for(int idx=0; idx<categoryCostId.size(); idx++) {
                getCategoryCostId = categoryCostId.get(idx).toString();
                prepStmt.setString(1, clientId);
                prepStmt.setString(2, getCategoryCostId);
                result += prepStmt.executeUpdate();
            }
            if(result==categoryCostId.size()) {
                costCategoryVO.setResult(1);
            } else {
                costCategoryVO.setResult(0);
            }
        } catch (SQLException sql) {
            System.out.println("Exception in CategoryCostDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in CategoryCostDAO :"+npe);
        } finally {
            try {
                prepStmt.close();
                con.close();
            } catch (Exception e) {
                System.out.println("Exception in CategoryCostDAO :"+e);
            }
        }
        return costCategoryVO;
    }
}
