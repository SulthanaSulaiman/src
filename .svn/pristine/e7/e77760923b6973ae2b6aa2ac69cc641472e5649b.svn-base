
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.contacts.ProjAuthorInfo;

/**
 *
 * @author thanuja
 */
public class SalarySlip extends HttpServlet {

   private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 13,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.NORMAL, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD | Font.UNDERLINE, BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 9);
    private static Font subFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 9);
    private static Font subFont2 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
    private static Font subFontD = new Font(Font.FontFamily.TIMES_ROMAN, 7);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 8,
            Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL,  BaseColor.BLUE);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 7);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
    private String previous_category;
    private String current_category;
    private String bookTitle = "";
    NumberFormat numberFormat = new DecimalFormat("###.00");
    NumberFormat numberFormat3d = new DecimalFormat("###.00#");
    DecimalFormat df = new DecimalFormat("0.###");

            java.util.List item_id = new ArrayList();
            java.util.List item_name = new ArrayList();
            java.util.List item_rate = new ArrayList();
            java.util.List item_quantity = new ArrayList();
            java.util.List item_desc = new ArrayList();
            java.util.List item_value = new ArrayList();
            java.util.List cost_category_id = new ArrayList();
            java.util.List cost_category_name = new ArrayList();
            java.util.List line_item_no = new ArrayList();

            String emptyCheck = "";
            String estDate = "";
            boolean lineItemDispChk=false;
            int lineItemDispCount = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
        String FONTST = "c:/windows/Fonts/Arial.ttf";
       BaseFont bf;
        Font fontT;
        Font fontT1;
        Font fontT2;
        Font fontT3;
       bf = BaseFont.createFont(FONTST, BaseFont.WINANSI, BaseFont.EMBEDDED);
       fontT = new Font(bf, 7.0f,1);
       fontT1 = new Font(bf, 7.2f);
       fontT2 = new Font(bf, 6.5f,1);
       fontT3 = new Font(bf, 6.0f);

        response.setContentType("application/pdf");
        javax.servlet.http.HttpSession session = request.getSession();
        String emp_idV1=request.getParameter("emp_id") != null ? request.getParameter("emp_id") : "";
        String monthV1=request.getParameter("month") != null ? request.getParameter("month") : "";
        String yearV1=request.getParameter("year") != null ? request.getParameter("year") : "";
      // String passhr=request.getParameter("passhr") != null ? request.getParameter("passhr") : "";
      String passhr="karthi2311";
        System.out.println("pass"+passhr);
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "inline; filename="+emp_idV1+monthV1+yearV1+"_salary.pdf");
            String monthV=request.getParameter("month") != null ? request.getParameter("month") : "";
            String yearV=request.getParameter("year") != null ? request.getParameter("year") : "";
            String empSize=request.getParameter("empSize") != null ? request.getParameter("empSize") : "";
        //Document document = new Document(PageSize.A4);//new Document(pdf, PageSize.A4);
            String monthVinwords="";
         if(monthV.equals("1")){
monthVinwords = "JANUARY";
        }
        else if(monthV.equals("2"))
        {
monthVinwords = "FEBRUARY";
        }
        else if(monthV.equals("3"))
        {
monthVinwords = "MARCH";
        }
        else if(monthV.equals("4"))
        {
monthVinwords = "APRIL";
        }
        else if(monthV.equals("5"))
        {
monthVinwords = "MAY";
        }
        else if(monthV.equals("6"))
        {
monthVinwords = "JUNE";
        }
        else if(monthV.equals("7"))
        {
monthVinwords = "JULY";
        }
        else if(monthV.equals("8"))
        {
monthVinwords = "AUGUST";
        }
        else if(monthV.equals("9"))
        {
monthVinwords = "SEPTEMBER";
        }
        else if(monthV.equals("10"))
        {
monthVinwords = "OCTOBER";
        }
        else if(monthV.equals("11"))
        {
monthVinwords = "NOVEMBER";
        }
        else if(monthV.equals("12"))
        {
monthVinwords = "DECEMBER";
        }
        //empcodeforpdf
       for(int i=0; i<Integer.parseInt(empSize);i++){

          Document document = new Document();
      try
      {
          String s =request.getParameter("chkbox" + i) == null?"":request.getParameter("chkbox"+i);
        //String s ="1";
            if(s.equals("1")){
        String filenameForsheet3 = "C:\\ScheduleExcel\\S4Carlisle_Payslip_"+monthV1+"_"+yearV1+".xlsm";
        String emp_idV=request.getParameter("empcodeforpdf"+i) != null ? request.getParameter("empcodeforpdf"+i) : "";
        //String forwritefilename = "D:/ScheduleExcel/Payslip_"+emp_idV+"-"+monthV1+"-"+yearV1+".pdf";
        String forwritefilename = "C:/ScheduleExcel/Payslip_"+emp_idV+"-"+monthV1+"-"+yearV1+".pdf";
        //OutputStream file = new FileOutputStream(new File("D:/ScheduleExcel/Payslip_"+emp_idV+"-"+monthV1+"-"+yearV1+".pdf"));
       OutputStream file = new FileOutputStream(new File("C:/ScheduleExcel/Payslip_"+emp_idV+"-"+monthV1+"-"+yearV1+".pdf"));
         PdfWriter writer = PdfWriter.getInstance(document, file);
         document.open();


         String SourcePage = request.getRequestURI().toString();
        SourcePage = SourcePage.substring(SourcePage.lastIndexOf("/") + 1);

      // PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
       writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

pathfinder.functions.hr.GetSalarydetailsfordisp dispsalary= new pathfinder.functions.hr.GetSalarydetailsfordisp();
dispsalary.setEmp_code(emp_idV);
dispsalary.setYearforsalarydisp(yearV);
dispsalary.setMonthforsalarydisp(monthV);
dispsalary.getsalarydetails();
String logoPath = getServletContext().getRealPath("");
if(dispsalary.getDepartment().equals("Qbend")){
    logoPath = logoPath + File.separator + "webimages\\qbend.png";
                }
 else{
 logoPath = logoPath + File.separator + "webimages\\logopng.png";
 }

            //logoPath = logoPath + File.separator + "webimages\\logopng.png";
            //logoPath = logoPath + File.separator + "webimages\\logopng.png";
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(logoPath);
            if(dispsalary.getDepartment().equals("Qbend")){
           image.scaleAbsoluteHeight(55);
            image.scaleAbsoluteWidth(100);
                }
 else{
           image.scaleAbsoluteHeight(40);
            image.scaleAbsoluteWidth(150);
 }

PdfPTable tableouter = new PdfPTable(1); // 3 columns.
tableouter.setWidthPercentage(88);
tableouter.getDefaultCell().setBorderWidth(2f);
tableouter.getDefaultCell().setPadding(3f);


PdfPTable tableouter3 = new PdfPTable(1); // 3 columns.
tableouter3.setWidthPercentage(88);
tableouter3.setTableEvent(new BorderEvent());
tableouter3.getDefaultCell().setBorder(1);
tableouter3.getDefaultCell().setBorderWidthBottom(2f);
tableouter3.getDefaultCell().setBorderWidthLeft(2f);
tableouter3.getDefaultCell().setBorderWidthRight(2f);
tableouter3.getDefaultCell().setBorderWidthTop(0f);
tableouter3.getDefaultCell().setBorderWidthBottom(2f);
tableouter3.getDefaultCell().setPadding(2f);

PdfPTable tableouter4 = new PdfPTable(1); // 3 columns.
tableouter4.setWidthPercentage(88);
tableouter4.getDefaultCell().setBorderWidth(2f);
tableouter4.getDefaultCell().setPadding(3f);

PdfPTable table = new PdfPTable(4); // 3 columns.
table.setWidthPercentage(88);
table.getDefaultCell().setBorderWidth(1f);
table.getDefaultCell().setPadding(1f);
PdfPTable table1 = new PdfPTable(1);
BorderEvent event = new BorderEvent();
          PdfPCell cellimg = new PdfPCell(new PdfPCell(image, false));
          cellimg.setColspan(4);
          cellimg.setHorizontalAlignment(Element.ALIGN_CENTER);
          disableBorders(cellimg);
          table1.addCell(cellimg);
          PdfPCell celladdr;
          celladdr=new PdfPCell(new Phrase("\n                     60, Industrial Estate,Perungudi, Chennai - 600096, Phone No.: 044 24965411 / 12\n", fontT3));
          celladdr.setBorder(0);
          table1.addCell(celladdr);

          PdfPCell cellT;
        // the cell object
        PdfPCell cellID,cellNME,cellDpt,cellDsg,cellpf,cellesi,celldoj,cellnopay,cellout,cellout1item,cellout1itememp,celluan,celluan1;

        Chunk empno=new Chunk("\n EMP NO: ",fontT);
        Chunk empno1=new Chunk(dispsalary.getEmp_code()+" \n",fontT);
        Phrase emp= new Phrase();
        emp.add(empno);
        emp.add(empno1);
        cellID = new PdfPCell(emp);
        cellID.setColspan(2);
        cellID.setBorderWidthLeft(1f);
        cellID.setBorderWidthRight(0);
        cellID.setBorderWidthTop(1f);
        cellID.setBorderWidthBottom(0);
        table.addCell(cellID);
        // now we add a cell with rowspan 2
        Chunk empnoV=new Chunk("\n        NAME:  ",fontT);
        Chunk empno1V=new Chunk(dispsalary.getName()+" \n",fontT);
        Phrase empV= new Phrase();
        empV.add(empnoV);
        empV.add(empno1V);
        cellNME = new PdfPCell(empV);
        cellNME.setColspan(2);
        cellNME.setBorderWidthLeft(0);
        cellNME.setBorderWidthRight(1f);
        cellNME.setBorderWidthTop(1f);
        cellNME.setBorderWidthBottom(0);
        table.addCell(cellNME);
        Chunk dept=new Chunk(" DEPARTMENT: ",fontT);
        Chunk deptV=new Chunk(dispsalary.getDepartment()+"",fontT);
        Phrase deptP= new Phrase();
        deptP.add(dept);
        deptP.add(deptV);

        cellDpt = new PdfPCell(deptP);
        cellDpt.setColspan(2);
        cellDpt.setBorderWidthLeft(1f);
        cellDpt.setBorderWidthRight(0);
        cellDpt.setBorderWidthTop(0);
        cellDpt.setBorderWidthBottom(0);
        table.addCell(cellDpt);
        Chunk desi=new Chunk("        DESIGNATION:  ",fontT);
        Chunk desiV=new Chunk(dispsalary.getDesignation(),fontT);
        Phrase desiP= new Phrase();
        desiP.add(desi);
        desiP.add(desiV);

        cellDsg = new PdfPCell(desiP);
        cellDsg.setColspan(2);
        cellDsg.setBorderWidthLeft(0);
        cellDsg.setBorderWidthRight(1f);
        cellDsg.setBorderWidthTop(0);
        cellDsg.setBorderWidthBottom(0);
        table.addCell(cellDsg);
        Chunk pfck=new Chunk(" P.F. No.:  ",fontT);
        Chunk pfckV=new Chunk(dispsalary.getPfno(),fontT);
        Phrase pfP= new Phrase();
        pfP.add(pfck);
        pfP.add(pfckV);
        cellpf = new PdfPCell(pfP);
        cellpf.setColspan(2);
        cellpf.setBorderWidthLeft(1f);
        cellpf.setBorderWidthRight(0);
        cellpf.setBorderWidthTop(0);
        cellpf.setBorderWidthBottom(0);
        table.addCell(cellpf);
        Chunk esick=new Chunk("        ESI No.:  ",fontT);
        Chunk esickV=new Chunk(dispsalary.getEsi(),fontT);
        Phrase esiP= new Phrase();
        esiP.add(esick);
        esiP.add(esickV);
        cellesi = new PdfPCell(esiP);
        cellesi.setColspan(2);
        cellesi.setBorderWidthLeft(0);
        cellesi.setBorderWidthRight(1f);
        cellesi.setBorderWidthTop(0);
        cellesi.setBorderWidthBottom(0);
        table.addCell(cellesi);
        Chunk dojck=new Chunk(" D.O.J: ",fontT);
        Chunk dojckV=new Chunk(dispsalary.getDoj(),fontT);
        Phrase dojP= new Phrase();
        dojP.add(dojck);
        dojP.add(dojckV);
        celldoj = new PdfPCell(dojP);
        celldoj.setColspan(2);
        //celldoj.setBorder(5);
        celldoj.setBorderWidthLeft(1f);
        celldoj.setBorderWidthRight(0);
        celldoj.setBorderWidthTop(0);
        celldoj.setBorderWidthBottom(0);
        table.addCell(celldoj);
        Chunk nopck=new Chunk("        NO OF PAYABLE DAYS: ",fontT);
        Chunk nopckV=new Chunk(dispsalary.getDayspayable(),fontT);
        Phrase nopP= new Phrase();
        nopP.add(nopck);
        nopP.add(nopckV);
        cellnopay = new PdfPCell(nopP);
        cellnopay.setColspan(2);
        cellnopay.setBorder(5);
        cellnopay.setBorderWidthLeft(0);
        cellnopay.setBorderWidthRight(1f);
        cellnopay.setBorderWidthTop(0);
        cellnopay.setBorderWidthBottom(0);
        table.addCell(cellnopay);
        Chunk uanpck=new Chunk(" UAN No.: ",fontT);
        Chunk uanpckV=new Chunk(dispsalary.getUan_no()+"\n",fontT);
        Phrase uanpckP= new Phrase();
        uanpckP.add(uanpck);
        uanpckP.add(uanpckV);
        celluan = new PdfPCell(uanpckP);
        celluan.setColspan(2);
        //celluan.setBorder(5);
        celluan.setBorderWidthLeft(1f);
        celluan.setBorderWidthRight(0);
        celluan.setBorderWidthTop(0);
        celluan.setBorderWidthBottom(1f);
        table.addCell(celluan);
        celluan1 = new PdfPCell(new Phrase("\n"));
        celluan1.setColspan(2);
        celluan1.setBorder(5);
        celluan1.setBorderWidthLeft(0);
        celluan1.setBorderWidthRight(1f);
        celluan1.setBorderWidthTop(0);
        celluan1.setBorderWidthBottom(1f);
        table.addCell(celluan1);
        tableouter.addCell(table);

        //table.getDefaultCell().setBorder(Rectangle.BOX);

        PdfPTable table2partTest = new PdfPTable(1); // 3 columns.
        table2partTest.setWidthPercentage(22);
        table2partTest.getDefaultCell().setBorderWidth(1.5f);
        table2partTest.getDefaultCell().setPadding(2f);
        PdfPCell cellout1itemTest = new PdfPCell(new Phrase("            ACTUAL SALARY                 ", fontT));
        table2partTest.addCell(cellout1itemTest);

        PdfPTable table2partTest1 = new PdfPTable(1); // 3 columns.
        table2partTest1.setWidthPercentage(22);
        table2partTest1.getDefaultCell().setBorderWidth(1.5f);
        table2partTest1.getDefaultCell().setPadding(2f);
        PdfPCell cellout1itemTest1 = new PdfPCell(new Phrase("            EARNED SALARY                 ", fontT));
        table2partTest1.addCell(cellout1itemTest1);

        PdfPTable table2partTest2 = new PdfPTable(1); // 3 columns.
        table2partTest2.setWidthPercentage(22);
        table2partTest2.getDefaultCell().setBorderWidth(1.5f);
        table2partTest2.getDefaultCell().setPadding(1f);
        PdfPCell cellout1itemTest2 = new PdfPCell(new Phrase("            OTHER PAYMENTS               ", fontT));
        table2partTest2.addCell(cellout1itemTest2);

        PdfPTable table2partTest3 = new PdfPTable(1); // 3 columns.
        table2partTest3.setWidthPercentage(22);
        table2partTest3.getDefaultCell().setBorderWidth(1.5f);
        table2partTest3.getDefaultCell().setPadding(1f);
        PdfPCell cellout1itemTest3 = new PdfPCell(new Phrase("              DEDUCTIONS                  ", fontT));
        table2partTest3.addCell(cellout1itemTest3);
       PdfPCell cellout1item1,cellout1item2,cellout1item3,cellout1;
       PdfPTable table2part = new PdfPTable(4); // 3 columns.
        table2part.setWidthPercentage(88);
        table2part.getDefaultCell().setBorderWidth(2f);
        table2part.getDefaultCell().setPadding(3f);

        //cellout1item = new PdfPCell(table2partTest);
        cellout1itememp = new PdfPCell(new Phrase("", fontT));

        table2part.addCell(table2partTest);
        table2part.addCell(table2partTest1);
        table2part.addCell(table2partTest2);
        table2part.addCell(table2partTest3);


        PdfPTable table2part3Test = new PdfPTable(4); // 3 columns.
        table2part3Test.setWidthPercentage(88);
        table2part3Test.getDefaultCell().setPadding(3f);
        table2part3Test.getDefaultCell().setBorderWidth(2f);
        PdfPCell alldetails,alldetails1,alldetails2,alldetails3;
        alldetails=new PdfPCell();
//tableouter1.addCell(table2part);
        PdfPTable table2part3 = new PdfPTable(2); // 3 columns.
        table2part3.setWidthPercentage(22);
        PdfPTable table2part31 = new PdfPTable(2); // 3 columns.
        table2part31.setWidthPercentage(22);
        PdfPTable table2part32 = new PdfPTable(2); // 3 columns.
        table2part32.setWidthPercentage(22);
        PdfPTable table2part33 = new PdfPTable(2); // 3 columns.
        table2part33.setWidthPercentage(22);
        PdfPCell basic,basicV,ebasic,ebasicV,arrear,arrearV,pf,pfv,hra,hraV,ehra,ehraV,ta,taV,lta,ltaV,ma,maV,va,vaV,bonus,bonusV,esi,esiV,emptycellspay,emptycellspay1,emptycellspay2;
        basic = new PdfPCell(new Phrase("  Basic", fontT));
        ebasic = new PdfPCell(new Phrase("  Basic", fontT));
        ebasicV = new PdfPCell(new Phrase(""+dispsalary.getEarned_basic()+".00", fontT1));
        ebasicV.setHorizontalAlignment(Element.ALIGN_RIGHT);


        arrear = new PdfPCell(new Phrase("  Arrears", fontT));
        arrearV = new PdfPCell(new Phrase(""+dispsalary.getArrears()+".00", fontT1));
        arrearV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pf = new PdfPCell(new Phrase("  P.F.", fontT));
        pfv = new PdfPCell(new Phrase(""+dispsalary.getPf_ded()+".00", fontT1));
        pfv.setHorizontalAlignment(Element.ALIGN_RIGHT);
        hra = new PdfPCell(new Phrase("  H.R.A", fontT));
        ehra = new PdfPCell(new Phrase("  H.R.A", fontT));
        ehraV = new PdfPCell(new Phrase(""+dispsalary.getEarned_hra()+".00", fontT1));
        ehraV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        bonus= new PdfPCell(new Phrase("  Bonus", fontT));
        bonusV= new PdfPCell(new Phrase(""+dispsalary.getBonus()+".00", fontT1));
        bonusV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        esi= new PdfPCell(new Phrase("  E.S.I", fontT));
        esiV= new PdfPCell(new Phrase(""+dispsalary.getEsi_ded()+".00", fontT1));
        esiV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell eta,etaV,attBonus,attBonusV,profTax,profTaxV,elta,eltaV,overtime,overtimeV,itr,itrV;
        ta = new PdfPCell(new Phrase("  T.A", fontT));
        taV = new PdfPCell(new Phrase(""+dispsalary.getLta()+".00", fontT1));
        taV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        eta = new PdfPCell(new Phrase("  T.A", fontT));
        etaV = new PdfPCell(new Phrase(""+dispsalary.getEarnedta()+".00", fontT1));
        etaV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        attBonus = new PdfPCell(new Phrase("  Att. Bonus", fontT));
        attBonusV = new PdfPCell(new Phrase(""+dispsalary.getAtt_bonus()+".00", fontT1));
        attBonusV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        profTax = new PdfPCell(new Phrase("  Prof. Tax", fontT));
        profTaxV = new PdfPCell(new Phrase(""+dispsalary.getProff_tax()+".00", fontT1));
        profTaxV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        lta = new PdfPCell(new Phrase("  L.T.A", fontT));
        ltaV = new PdfPCell(new Phrase(""+dispsalary.getTa()+".00", fontT1));
        ltaV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        elta = new PdfPCell(new Phrase("  L.T.A", fontT));
        eltaV = new PdfPCell(new Phrase(""+dispsalary.getEarnedlta()+".00", fontT1));
        eltaV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        overtime = new PdfPCell(new Phrase("  Overtime", fontT));
        overtimeV = new PdfPCell(new Phrase(""+dispsalary.getOvertime()+".00", fontT1));
        overtimeV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        itr = new PdfPCell(new Phrase("  I.T.", fontT));
        itrV = new PdfPCell(new Phrase(""+dispsalary.getTds()+".00", fontT1));
        itrV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        emptycellspay = new PdfPCell(new Phrase(" ", fontT));
        emptycellspay1 = new PdfPCell(new Phrase(" ", fontT));
        emptycellspay2 = new PdfPCell(new Phrase(" ", fontT));
        PdfPCell ema,emaV,misc,miscV,salAd,salAdV,eva,evaV,emptynexttoEva,emptynexttoEvaV,othrded,othrdedV,lwf,lwfV;

        ma = new PdfPCell(new Phrase("  MED. ALL.", fontT));
        maV = new PdfPCell(new Phrase(""+dispsalary.getMa()+".00", fontT1));
        maV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        ema = new PdfPCell(new Phrase("  MED. ALL.", fontT));
        emaV = new PdfPCell(new Phrase(""+dispsalary.getEarnedma()+".00", fontT1));
        emaV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        misc = new PdfPCell(new Phrase("  Misc.", fontT));
        miscV = new PdfPCell(new Phrase(" "+dispsalary.getMisc()+".00", fontT1));
        miscV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        salAd = new PdfPCell(new Phrase("  Sal. Adv.", fontT));
        salAdV = new PdfPCell(new Phrase(""+dispsalary.getSalary_adv()+".00", fontT1));
        salAdV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        va = new PdfPCell(new Phrase("  V. ALL.", fontT));
        vaV = new PdfPCell(new Phrase(""+dispsalary.getVa()+".00", fontT1));
        vaV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        eva = new PdfPCell(new Phrase("  V. ALL.", fontT));
        evaV = new PdfPCell(new Phrase(" "+dispsalary.getEarnedva()+".00", fontT1));
        evaV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        emptynexttoEva = new PdfPCell(new Phrase("", fontT));
        emptynexttoEvaV = new PdfPCell(new Phrase("", fontT));
        othrded = new PdfPCell(new Phrase("  Other Ded.", fontT));
        othrdedV = new PdfPCell(new Phrase(""+dispsalary.getOther_deductions()+".00", fontT1));
        othrdedV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        basicV = new PdfPCell(new Phrase(""+dispsalary.getBasic()+".00", fontT1));
        basicV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        hraV = new PdfPCell(new Phrase(""+dispsalary.getHra()+".00", fontT1));
        hraV.setHorizontalAlignment(Element.ALIGN_RIGHT);
        lwf = new PdfPCell(new Phrase("  LWF", fontT));
        lwfV = new PdfPCell(new Phrase(""+dispsalary.getLwf()+".00", fontT1));
        lwfV.setHorizontalAlignment(Element.ALIGN_RIGHT);

      PdfPTable table2part4 = new PdfPTable(4); // 3 columns.
      table2part4.setWidthPercentage(88);
      table2part4.getDefaultCell().setBorderWidth(2f);
      table2part4.getDefaultCell().setPadding(3f);

      PdfPTable table2part4Test = new PdfPTable(2); // 3 columns.
      table2part4Test.setWidthPercentage(22);
      table2part4Test.getDefaultCell().setBorderWidth(1.5f);
      table2part4Test.getDefaultCell().setPadding(1f);

      PdfPTable table2part4Test1 = new PdfPTable(2); // 3 columns.
      table2part4Test1.setWidthPercentage(22);
      table2part4Test1.getDefaultCell().setBorderWidth(1f);
      table2part4Test1.getDefaultCell().setPadding(1f);

      PdfPTable table2part4Test2 = new PdfPTable(2); // 3 columns.
      table2part4Test2.setWidthPercentage(22);
      table2part4Test2.getDefaultCell().setBorderWidth(1f);
      table2part4Test2.getDefaultCell().setPadding(1f);

      PdfPTable table2part4Test3 = new PdfPTable(2); // 3 columns.
      table2part4Test3.setWidthPercentage(22);
      table2part4Test3.getDefaultCell().setBorderWidth(1f);
      table2part4Test3.getDefaultCell().setPadding(1f);


     PdfPCell gross,grossV,egross,egrossV,ototal,ototalV,dtotal,dtotalV;
     gross = new PdfPCell(new Phrase("  GROSS", fontT));
     grossV = new PdfPCell(new Phrase(" "+dispsalary.getActual_gross_salary()+".00", fontT1));
     grossV.setHorizontalAlignment(Element.ALIGN_RIGHT);
     egross = new PdfPCell(new Phrase("  TOTAL", fontT));
     egrossV = new PdfPCell(new Phrase(" "+dispsalary.getGross_earnedsalary()+".00", fontT1));
     egrossV.setHorizontalAlignment(Element.ALIGN_RIGHT);
     ototal = new PdfPCell(new Phrase("  TOTAL", fontT));
     ototalV = new PdfPCell(new Phrase(" "+dispsalary.getOther_earnings_total()+".00", fontT1));
     ototalV.setHorizontalAlignment(Element.ALIGN_RIGHT);
     dtotal = new PdfPCell(new Phrase("  TOTAL", fontT));
     dtotalV = new PdfPCell(new Phrase(" "+dispsalary.getTotal_ded()+".00", fontT1));
     dtotalV.setHorizontalAlignment(Element.ALIGN_RIGHT);

      PdfPTable table2part5 = new PdfPTable(1); // 3 columns.
      table2part5.setWidthPercentage(88);
      table2part5.getDefaultCell().setBorderWidth(1f);
      table2part5.getDefaultCell().setPadding(2f);

      PdfPCell netAmount;
      netAmount = new PdfPCell(new Phrase("\nNET AMOUNT:     "+dispsalary.getNet_transfer()+".00                   "+dispsalary.getAmount_in_words()+"\n", fontT));

table2part3.addCell(basic);
table2part3.addCell(basicV);
table2part3.addCell(hra);
table2part3.addCell(hraV);
table2part3.addCell(ta);
table2part3.addCell(taV);
table2part3.addCell(lta);
table2part3.addCell(ltaV);
table2part3.addCell(ma);
table2part3.addCell(maV);
table2part3.addCell(va);
table2part3.addCell(vaV);
table2part3.addCell(emptycellspay);
table2part3.addCell(emptynexttoEva);
table2part3Test.addCell(table2part3);


table2part31.addCell(ebasic);
table2part31.addCell(ebasicV);
table2part31.addCell(ehra);
table2part31.addCell(ehraV);
table2part31.addCell(eta);
table2part31.addCell(etaV);
table2part31.addCell(elta);
table2part31.addCell(eltaV);
table2part31.addCell(ema);
table2part31.addCell(emaV);
table2part31.addCell(eva);
table2part31.addCell(evaV);
table2part31.addCell(emptynexttoEva);
table2part31.addCell(emptynexttoEva);
table2part3Test.addCell(table2part31);

table2part32.addCell(arrear);
table2part32.addCell(arrearV);
System.out.println("bonus"+bonus);
table2part32.addCell(bonus);
table2part32.addCell(bonusV);
table2part32.addCell(attBonus);
table2part32.addCell(attBonusV);
table2part32.addCell(overtime);
table2part32.addCell(overtimeV);
table2part32.addCell(misc);
table2part32.addCell(miscV);
table2part32.addCell(emptycellspay);
table2part32.addCell(emptycellspay);
table2part32.addCell(emptynexttoEva);
table2part32.addCell(emptynexttoEva);
table2part3Test.addCell(table2part32);

table2part33.addCell(pf);
table2part33.addCell(pfv);
table2part33.addCell(esi);
table2part33.addCell(esiV);
table2part33.addCell(profTax);
table2part33.addCell(profTaxV);
table2part33.addCell(itr);
table2part33.addCell(itrV);
table2part33.addCell(salAd);
table2part33.addCell(salAdV);
table2part33.addCell(othrded);
table2part33.addCell(othrdedV);
table2part33.addCell(lwf);
table2part33.addCell(lwfV);
table2part3Test.addCell(table2part33);

table2part4Test.addCell(gross);
table2part4Test.addCell(grossV);
table2part4Test1.addCell(egross);
table2part4Test1.addCell(egrossV);
table2part4Test2.addCell(ototal);
table2part4Test2.addCell(ototalV);
table2part4Test3.addCell(dtotal);
table2part4Test3.addCell(dtotalV);
table2part4.addCell(table2part4Test);
table2part4.addCell(table2part4Test1);
table2part4.addCell(table2part4Test2);
table2part4.addCell(table2part4Test3);



//netAmount,netAmountV,amountinwod
table2part5.addCell(netAmount);

//table2part5.addCell(amountinwod);
        //table2part.addCell(table2part);
        // now we add a cell with rowspan 2
        //table.addCell(new Phrase("DEPARTMENT:                  ", subFont));


        // we add the four remaining cells with addCell()

  PdfPTable table2part6 = new PdfPTable(7); // 3 columns.
  table2part6.setWidthPercentage(88);
emptycellspay2.setBorder(0);
PdfPCell compoff,compoffV,clsl,clslV,pl,plV;
      compoff = new PdfPCell(new Phrase("Comp. Off", fontT1));
      clsl = new PdfPCell(new Phrase("CL/SL", fontT1));
      pl = new PdfPCell(new Phrase("PL", fontT1));
      compoffV = new PdfPCell(new Phrase(" "+dispsalary.getComp_off(), fontT1));
      clslV = new PdfPCell(new Phrase(" "+dispsalary.getClsl_balance(), fontT1));
      plV = new PdfPCell(new Phrase(" " +dispsalary.getPl_balance(), fontT1));

table2part6.addCell(compoff);
table2part6.addCell(clsl);
table2part6.addCell(pl);
table2part6.addCell(emptycellspay2);
table2part6.addCell(emptycellspay2);
table2part6.addCell(emptycellspay2);
table2part6.addCell(emptycellspay2);
table2part6.addCell(compoffV);
table2part6.addCell(clslV);
table2part6.addCell(plV);
table2part6.setHorizontalAlignment(1);
table2part6.addCell(emptycellspay2);
table2part6.addCell(emptycellspay2);
table2part6.addCell(emptycellspay2);
table2part6.addCell(emptycellspay2);



//table2part6.setWidths( new int[]{ 1, 1, 1, 1 } );
PdfPTable table2part7 = new PdfPTable(1); // 3 columns.
table2part7.setWidthPercentage(88);
PdfPCell emptytablecell;
emptytablecell = new PdfPCell(new Phrase(" ", fontT));
table2part7.addCell(emptytablecell);
table.completeRow();
            //Add empty line

document.add(new Paragraph("\n"));
document.add(new Paragraph("\n"));
document.add(new Paragraph("\n"));
          document.add(table1);

           document.add(new Paragraph("\n                                                                                                         PAYSLIP FOR MONTH OF "+monthVinwords+" "+yearV, fontT));
           document.add(new Paragraph("\n"));
           document.add(tableouter);
           //document.add(table2part7);
           //document.add(table2part);
           document.add(table2part);
           //tableouter2.addCell(table2part3);

           document.add(table2part3Test);
           document.add(table2part4);
           //tableouter3.addCell(table2part4);
           document.add(tableouter3);
           tableouter4.addCell(table2part5);
           document.add(tableouter4);
           document.add(new Paragraph("\n"));
           document.add(new Paragraph("\n"));
           document.add(new Paragraph("                SIGN OF EMPLOYEE :                                                                                                          SIGN OF EMPLOYER :", fontT1));
           document.add(new Paragraph("\n"));
           document.add(new Paragraph("                Note: This is a system generated payslip, signatures are not required.", smallNote));
           document.add(new Paragraph("\n"));
           document.add(new Paragraph("\n"));
           document.add(table2part6);
 writer.setPageEmpty(false);
       // document.newPage();

           //document.add(nestedTable);
             document.close();
            file.close();
            //pathfinder.util.SendPayslip mdao= new pathfinder.util.SendPayslip();
            pathfinder.util.Testcase mdao= new pathfinder.util.Testcase();
            System.out.println(dispsalary.getName());
              System.out.println(dispsalary.getEmail());
                System.out.println(dispsalary.getMonthSyear());
                  System.out.println(forwritefilename);
                    System.out.println(passhr);
                      System.out.println(filenameForsheet3);

            mdao.sendEmailWithAttachments(
                        dispsalary.getName(),dispsalary.getEmail(),forwritefilename,dispsalary.getMonthSyear(),passhr,filenameForsheet3);

         writer.close();
          }
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
        }
            RequestDispatcher rd = request.getRequestDispatcher("/generatePayslip.jsp?&alrtmsg=Mail sent successfully");
  rd.forward(request, response);
 }
       catch(DocumentException d){
       }
        }


    private static void disableBorders(PdfPCell cell) {
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }

    private static void mergeBorders(PdfPCell cell) {
        //cell.disableBorderSide(PdfPCell.LEFT);
        //cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
class BorderEvent implements PdfPTableEvent {
        public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
            float width[] = widths[0];
            float x1 = width[0];
            float x2 = width[width.length - 1];
            float y1 = heights[0];
            float y2 = heights[heights.length - 1];
            PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
            cb.rectangle(x1, y1, x2 - x1, y2 - y1);
            cb.stroke();
            cb.resetRGBColorStroke();
        }}
