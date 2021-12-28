/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author rajac
 */
public class AddDeptCostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        pathfinder.functions.timesheet.DeptCostDAO deptCostDAO = new pathfinder.functions.timesheet.DeptCostDAO();
        pathfinder.functions.timesheet.DeptCostVO deptCostVO = new pathfinder.functions.timesheet.DeptCostVO();

        javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");

        String responseText = "";
        String returnMsg = "Cost can't be added. It has already added for this department.";
        String returnBankMsg = "Details can't be added. This account number already available in this bank.";
        String returnBillingLocMsg = "Location can't be added. This has been already added.";
        String operation = "";
        boolean isAllowed = true;

        List deptCostList = new ArrayList();
        List bankDetailList = new ArrayList();
        List billingLocationList = new ArrayList();
        List mappedDeptCostID = new ArrayList();
        List mappedDeptCode = new ArrayList();
        List mappedDeptName = new ArrayList();
        List mappedDeptCost = new ArrayList();
        List mappedDeptCurrID = new ArrayList();
        List mappedDeptCurrName = new ArrayList();
        List addedBankAccNum = new ArrayList();
        List addedBankName = new ArrayList();
        List addedBankAddress = new ArrayList();
        List addedBankDetDate = new ArrayList();
        List addedBankDetEmpID = new ArrayList();
        List addedBankDetEmpName = new ArrayList();
        List addedBillingLocation = new ArrayList();
        List addedBillingLocationAddress = new ArrayList();
        List addedBillingLocationDate = new ArrayList();
        List addedBillingLocationEmpID = new ArrayList();
        List addedBillingLocationEmpName = new ArrayList();

        deptCostList.clear();
        bankDetailList.clear();
        billingLocationList.clear();

        deptCostList = deptCostDAO.getDeptCost();
        bankDetailList = deptCostDAO.getBankDetails();
        billingLocationList = deptCostDAO.getBillingLocation();

        Iterator deptCostListItr = deptCostList.iterator();
        Iterator bankDetListItr = bankDetailList.iterator();
        Iterator billingLocationItr = billingLocationList.iterator();

        mappedDeptCostID.clear();
        mappedDeptCode.clear();
        mappedDeptName.clear();
        mappedDeptCost.clear();
        mappedDeptCurrID.clear();
        mappedDeptCurrName.clear();
        addedBankAccNum.clear();
        addedBankName.clear();
        addedBankAddress.clear();
        addedBankDetDate.clear();
        addedBankDetEmpID.clear();
        addedBankDetEmpName.clear();
        addedBillingLocation.clear();
        addedBillingLocationAddress.clear();
        addedBillingLocationDate.clear();
        addedBillingLocationEmpID.clear();
        addedBillingLocationEmpName.clear();

        while (deptCostListItr.hasNext()) {
            deptCostVO = (pathfinder.functions.timesheet.DeptCostVO) deptCostListItr.next();
            mappedDeptCostID.add(deptCostVO.getDeptCostID());
            mappedDeptCode.add(deptCostVO.getMappedDeptCode());
            mappedDeptName.add(deptCostVO.getMappedDeptName());
            mappedDeptCost.add(deptCostVO.getDeptCostVal());
            mappedDeptCurrID.add(deptCostVO.getDeptCostCurrID());
            mappedDeptCurrName.add(deptCostVO.getDeptCostCurrName());
        }

        while (bankDetListItr.hasNext()) {
            deptCostVO = (pathfinder.functions.timesheet.DeptCostVO) bankDetListItr.next();
            addedBankAccNum.add(deptCostVO.getAvailBankAccNum());
            addedBankName.add(deptCostVO.getAvailBankName());
            addedBankAddress.add(deptCostVO.getAvailBankAddress());
            addedBankDetDate.add(deptCostVO.getAvailBankDetAddedDate());
            addedBankDetEmpID.add(deptCostVO.getAvailBankDetAddedEmpID());
            addedBankDetEmpName.add(deptCostVO.getAvailBankDetAddedEmpName());
        }

        while (billingLocationItr.hasNext()) {
            deptCostVO = (pathfinder.functions.timesheet.DeptCostVO) billingLocationItr.next();
            addedBillingLocation.add(deptCostVO.getAvailBillingLocName());
            addedBillingLocationAddress.add(deptCostVO.getAvailBillingLocAddress());
            addedBillingLocationDate.add(deptCostVO.getAvailBillingLocAddedDate());
            addedBillingLocationEmpID.add(deptCostVO.getAvailBillingLocAddedEmpID());
            addedBillingLocationEmpName.add(deptCostVO.getAvailBillingLocAddedEmpName());
        }

        if (request.getParameter("save") != null) {
            operation = request.getParameter("save");
            //System.out.println("operation :" + operation);
        }

        //System.out.println("In Servlet - addedBillingLocation : " + addedBillingLocation);
        //System.out.println("In Servlet - addedBillingLocationAddress : " + addedBillingLocationAddress);
        //System.out.println("In Servlet - addedBillingLocationDate : " + addedBillingLocationDate);
        //System.out.println("In Servlet - addedBillingLocationEmpID : " + addedBillingLocationEmpID);
        //System.out.println("In Servlet - addedBillingLocationEmpName : " + addedBillingLocationEmpName);
        //System.out.println("In Servlet - mappedDeptCostID: " + mappedDeptCostID);
        //System.out.println("In Servlet - mappedDeptCode: " + mappedDeptCode);
        //System.out.println("In Servlet - mappedDeptName: " + mappedDeptName);
        //System.out.println("In Servlet - mappedDeptCost: " + mappedDeptCost);
        //System.out.println("In Servlet - mappedDeptCurrID: " + mappedDeptCurrID);
        //System.out.println("In Servlet - mappedDeptCurrName: " + mappedDeptCurrName);

        //double dept_cost_tot = 0;
        double dept_sal_tot = 0;
        int returnCode = 0;
        int returnCodeFalse = 1;

        String dept_code = request.getParameter("dept_name_hidden") != null ? request.getParameter("dept_name_hidden") : "";
        String salary_total = request.getParameter("tot_salary") != null ? request.getParameter("tot_salary") : "";
        String curr_code = request.getParameter("currency") != null ? request.getParameter("currency") : "";
        String bankName = request.getParameter("bank_name") != null ? request.getParameter("bank_name") : "";
        String bankAddress = request.getParameter("bank_address") != null ? request.getParameter("bank_address") : "";
        String bankAccNum = request.getParameter("acc_num") != null ? request.getParameter("acc_num") : "";
        String billingLoc = request.getParameter("billing_location") != null ? request.getParameter("billing_location") : "";
        String billingLocAddr = request.getParameter("billing_address") != null ? request.getParameter("billing_address") : "";

        //dept_cost_tot = (dept_sal_tot*1.5/dept_tot_emp);

        /*System.out.println("sesEmp:"+sesEmp);
        System.out.println("dept_code:"+dept_code);
        System.out.println("salary_total:"+salary_total);
        System.out.println("emp_total:"+emp_total);
        System.out.println("curr_code:"+curr_code);
        System.out.println("dept_cost_tot:"+dept_cost_tot);*/

        //System.out.println("bankName:" + bankName);
        //System.out.println("bankAddress:" + bankAddress);
        //System.out.println("bankAccNum:" + bankAccNum);


        if (!mappedDeptCostID.isEmpty()) {
            dept_code = request.getParameter("dept_name_hidden") != null ? request.getParameter("dept_name_hidden") : "";
            if (mappedDeptCode.contains(dept_code.trim())) {
                isAllowed = false;
            }
        }

        if (!addedBillingLocation.isEmpty()) {
            billingLoc = request.getParameter("billing_location") != null ? request.getParameter("billing_location") : "";
            if (addedBillingLocation.contains(billingLoc.trim())) {
                isAllowed = false;
            }
        }

        if (!addedBankAccNum.isEmpty()) {
            bankAccNum = request.getParameter("acc_num") != null ? request.getParameter("acc_num") : "";
            bankName = request.getParameter("bank_name") != null ? request.getParameter("bank_name") : "";
            //System.out.println("bankName:" + bankName.trim());
            if (addedBankName.contains(bankName.trim())) {
                for (int idx = 0; idx < addedBankAccNum.size(); idx++) {
                    if (addedBankName.get(idx).toString().equals(bankName.trim())) {
                        //System.out.println("addedBankName.get(idx).toString():" + addedBankName.get(idx).toString());
                        String added_bank_acc_num = addedBankAccNum.get(idx).toString();
                        //System.out.println("added_bank_acc_num:" + added_bank_acc_num.trim());
                        if (added_bank_acc_num.equals(bankAccNum.trim())) {
                            isAllowed = false;
                            break;
                        }
                    }
                }
            }
        }
        //System.out.println("isAllowed - Bank Details: " + isAllowed);
        //System.out.println("isAllowed: " + isAllowed);

        pathfinder.functions.timesheet.AllocateOverhead overhead = new pathfinder.functions.timesheet.AllocateOverhead();

        if (operation.equals("save")) {
            if (isAllowed) {
                dept_sal_tot = Double.parseDouble(salary_total);
                overhead.setCostDeptCode(dept_code);
                overhead.setCostDeptSal(dept_sal_tot);
                //overhead.setDeptCostTot(dept_cost_tot);
                overhead.setCostCurrCode(curr_code);
                overhead.setCostAddedEmp(sesEmp);
                returnCode = overhead.saveDeptCost();
                responseText = overhead.getReturnMessage();
                responseText = "save=save&returnCode=" + returnCode + "&returnMsg=" + responseText + "&" + request.getQueryString();
            } else {
                responseText = "save=save&returnCode=" + returnCodeFalse + "&returnMsg=" + returnMsg;
            }
        }

        if (operation.equals("add_bank_det")) {
            if (isAllowed) {
                //System.out.println("Inside - IF - operation :" + operation);
                overhead.setAddAccNum(bankAccNum);
                overhead.setAddBankAddr(bankAddress);
                overhead.setAddBankName(bankName);
                overhead.setCostAddedEmp(sesEmp);
                returnCode = overhead.saveBankDetails();
                responseText = overhead.getReturnMessage();
                responseText = "save_bank_det=add_bank_det&returnCode=" + returnCode + "&returnMsg=" + responseText + "&" + request.getQueryString();
            } else {
                responseText = "save_bank_det=add_bank_det&returnCode=" + returnCodeFalse + "&returnMsgBank=" + returnBankMsg;
            }
        }

        if (operation.equals("add_billing_loc")) {
            if (isAllowed) {
                //System.out.println("Inside - IF - operation :" + operation);
                overhead.setAddBillingLocation(billingLoc);
                overhead.setAddBillingLocationAddress(billingLocAddr);
                overhead.setCostAddedEmp(sesEmp);
                returnCode = overhead.saveBillingLocation();
                responseText = overhead.getReturnMessage();
                responseText = "save_billing_loc=add_billing_loc&returnCode=" + returnCode + "&returnMsg=" + responseText + "&" + request.getQueryString();
            } else {
                responseText = "save_billing_loc=add_billing_loc&returnCode=" + returnCodeFalse + "&returnMsgBillingLoc=" + returnBillingLocMsg;
            }
        }

        PrintWriter out = response.getWriter();
        //out.println(getProjIdParam.trim());
        //System.out.println("responseText: " + responseText.trim());
        out.println(responseText.trim());
    }
}
