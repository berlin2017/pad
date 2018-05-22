package com.berlin.testpad.socre;

import android.content.Context;
import android.util.SparseArray;

import com.berlin.testpad.socre.model.InputModel1;
import com.berlin.testpad.socre.model.InputModel2;
import com.berlin.testpad.socre.model.InputModel3;
import com.berlin.testpad.socre.model.InputModel4;
import com.berlin.testpad.socre.model.InputModel5;
import com.berlin.testpad.socre.model.ScoreModel;
import com.berlin.testpad.user.UserManager;
import com.berlin.testpad.utis.MyUtils;
import com.google.gson.Gson;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by ahxmt on 2018/5/22.
 */

public class ExcelUtils {
    /**
     *excle表格的后缀
     */
    public static final String SUFFIX=".xls";
    public static final String DIR_FILE_NAME="EXCLE_DIR";

    public static final String FIRST_ROW_CONTENT="2017年3月";
    public static final  String[]  SENCOND_VALUES={"1","2"};
    public static final String[] THREE_VALUES={"一班","二班","三班"};
    public static final String[][] FOUR_VALUES={
            {"\n涂思欢\n消元培\n黄村屏\n增东\n纳兰二狗","\n涂思欢\n消元培","\n涂思欢\n消元培"}
            ,{"\n涂思欢\n消元培\n 哪来二狗","\n涂思欢\n消元培","\n涂思欢\n消元培"}
    };

    private static Workbook createWorkbook(){
        return new HSSFWorkbook();
    }

    //写excel
    public static void writeExecleToFile(Context context, ScoreModel scoreModel){
//        ScoreActivity scoreActivity = (ScoreActivity) context;
//        scoreActivity.showLoadingDialog();
        //创建工作簿
        Workbook workbook=createWorkbook();
//        SparseArray<CellStyle> cellStyles=creatCellStyles(workbook);
        //创建execl中的一个表
        Sheet sheet= workbook.createSheet();
        // setSheet(sheet);

//        //创建第一行
//        Row headerRow=sheet.createRow(0);
//        // 设置第一行：48pt的字体的内容
//        headerRow.setHeightInPoints(60);
//        //创建第一行中第一单元格
//        Cell cell=headerRow.createCell(0);
//        cell.setCellValue(FIRST_ROW_CONTENT);
////        cell.setCellStyle(cellStyles.get(0));
//        mergingCells(sheet, CellRangeAddress.valueOf("$A$1:$F$1"));
//        //创建第二行
//        Row secondRow=sheet.createRow(1);
//        secondRow.setHeightInPoints(45);
//        for (int i=0;i<2;++i){
//            mergingCells(sheet,new CellRangeAddress(1,1,i*3,i*3+2));
//            Cell cell1=secondRow.createCell(i*3);
//            cell1.setCellValue(SENCOND_VALUES[i]);
////            cell1.setCellStyle(cellStyles.get(1));
//        }
//        //创建第三行
//        Row threedRow=sheet.createRow(2);
//        threedRow.setHeightInPoints(40);
//        for (int i=0;i<2;++i){
//            for (int j=0;j<3;++j){
//                Cell cell1=threedRow.createCell(i*3+j);
//                cell1.setCellValue(THREE_VALUES[j]);
////                cell1.setCellStyle(cellStyles.get(2));
//            }
//        }
//        //创建第四行
//        Row fourRow=sheet.createRow(3);
//        fourRow.setHeightInPoints(150);
//        for (int i=0;i<FOUR_VALUES.length;++i){
//            for (int j=0;j<FOUR_VALUES[i].length;++j){
//                Cell cell1=fourRow.createCell(i*3+j);
//                cell1.setCellValue(FOUR_VALUES[i][j]);
////                cell1.setCellStyle(cellStyles.get(3));
//            }
//        }

        InputModel1 inputModel1 = new Gson().fromJson(scoreModel.getFragment1(),InputModel1.class);

        InputModel2 inputModel2 = new Gson().fromJson(scoreModel.getFragment2(),InputModel2.class);

        InputModel3 inputModel3 = new Gson().fromJson(scoreModel.getFragment3(),InputModel3.class);

        InputModel4 inputModel4 = new Gson().fromJson(scoreModel.getFragment4(),InputModel4.class);

        InputModel5 inputModel5 = new Gson().fromJson(scoreModel.getFragment5(),InputModel5.class);


        //第一行
        Row headerR = sheet.createRow(0);
        headerR.setHeightInPoints(60);
        //创建第一行中第一单元格
        Cell cell1=headerR.createCell(1);
        cell1.setCellValue("保障人员");

        Cell cell2=headerR.createCell(2);
        cell2.setCellValue("保障设施");

        Cell cell3=headerR.createCell(3);
        cell3.setCellValue("保障装备");

        Cell cell4=headerR.createCell(4);
        cell4.setCellValue("保障过程");

        Cell cell5=headerR.createCell(5);
        cell5.setCellValue("保障制度");

        //第二行

        Row secondRow = sheet.createRow(1);
        secondRow.setHeightInPoints(40);
        Cell secondRow_cell1=secondRow.createCell(0);
        secondRow_cell1.setCellValue("第一个分数");

        Cell secondRow_cell2=secondRow.createCell(1);
        secondRow_cell2.setCellValue(inputModel1.getFragment_input1());

        Cell secondRow_cell3=secondRow.createCell(2);
        secondRow_cell3.setCellValue(inputModel2.getFragment_input1());

        Cell secondRow_cell4=secondRow.createCell(3);
        secondRow_cell4.setCellValue(inputModel3.getFragment_input1());

        Cell secondRow_cell5=secondRow.createCell(4);
        secondRow_cell5.setCellValue(inputModel4.getFragment_input1());

        Cell secondRow_cell6=secondRow.createCell(5);
        secondRow_cell6.setCellValue(inputModel5.getFragment_input1());

        Row secondRow_suggest = sheet.createRow(2);
        secondRow_suggest.setHeightInPoints(100);
        Cell secondRow_suggest_cell1=secondRow_suggest.createCell(0);
        secondRow_suggest_cell1.setCellValue("第一个意见");

        Cell secondRow_suggest_cell2=secondRow_suggest.createCell(1);
        secondRow_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input1());

        Cell secondRow_suggest_cell3=secondRow_suggest.createCell(2);
        secondRow_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input1());

        Cell secondRow_suggest_cell4=secondRow_suggest.createCell(3);
        secondRow_suggest_cell4.setCellValue(inputModel3.getSuggest_fragment_input1());

        Cell secondRow_suggest_cell5=secondRow_suggest.createCell(4);
        secondRow_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input1());

        Cell secondRow_suggest_cell6=secondRow_suggest.createCell(5);
        secondRow_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input1());

        //第3行

        Row row3 = sheet.createRow(3);
        row3.setHeightInPoints(40);

        Cell row3_cell1=row3.createCell(0);
        row3_cell1.setCellValue("第二个输入框分数");

        Cell row3_cell2=row3.createCell(1);
        row3_cell2.setCellValue(inputModel1.getFragment_input2());

        Cell row3_cell3=row3.createCell(2);
        row3_cell3.setCellValue(inputModel2.getFragment_input2());

        Cell row3_cell4=row3.createCell(3);
        row3_cell4.setCellValue(inputModel3.getFragment_input2());

        Cell row3_cell5=row3.createCell(4);
        row3_cell5.setCellValue(inputModel4.getFragment_input2());

        Cell row3_cell6=row3.createCell(5);
        row3_cell6.setCellValue(inputModel5.getFragment_input2());

        Row row3_suggest = sheet.createRow(4);
        row3_suggest.setHeightInPoints(100);
        Cell row3_suggest_cell1=row3_suggest.createCell(0);
        row3_suggest_cell1.setCellValue("第二个意见");

        Cell row3_suggest_cell2=row3_suggest.createCell(1);
        row3_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input2());

        Cell row3_suggest_cell3=row3_suggest.createCell(2);
        row3_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input2());

        Cell row3_suggest_cell4=row3_suggest.createCell(3);
        row3_suggest_cell4.setCellValue(inputModel3.getSuggest_fragment_input2());

        Cell row3_suggest_cell5=row3_suggest.createCell(4);
        row3_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input2());

        Cell row3_suggest_cell6=row3_suggest.createCell(5);
        row3_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input2());

        //第4行

        Row row4 = sheet.createRow(5);
        row4.setHeightInPoints(40);
        Cell row4_cell1=row4.createCell(0);
        row4_cell1.setCellValue("第三个输入框分数");

        Cell row4_cell2=row4.createCell(1);
        row4_cell2.setCellValue(inputModel1.getFragment_input3());

        Cell row4_cell3=row4.createCell(2);
        row4_cell3.setCellValue(inputModel2.getFragment_input3());

        Cell row4_cell4=row3.createCell(3);
        row4_cell4.setCellValue(inputModel3.getFragment_input3());

        Cell row4_cell5=row3.createCell(4);
        row4_cell5.setCellValue(inputModel4.getFragment_input3());

        Cell row4_cell6=row3.createCell(5);
        row4_cell6.setCellValue(inputModel5.getFragment_input3());

        Row row4_suggest = sheet.createRow(6);
        row4_suggest.setHeightInPoints(100);
        Cell row4_suggest_cell1=row4_suggest.createCell(0);
        row4_suggest_cell1.setCellValue("第三个意见");

        Cell row4_suggest_cell2=row4_suggest.createCell(1);
        row4_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input3());

        Cell row4_suggest_cell3=row4_suggest.createCell(2);
        row4_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input3());

        Cell row4_suggest_cell4=row4_suggest.createCell(3);
        row4_suggest_cell4.setCellValue(inputModel3.getSuggest_fragment_input3());

        Cell row4_suggest_cell5=row4_suggest.createCell(4);
        row4_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input3());

        Cell row4_suggest_cell6=row4_suggest.createCell(5);
        row4_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input3());

        //第5行

        Row row5 = sheet.createRow(7);
        row5.setHeightInPoints(40);

        Cell row5_cell1=row5.createCell(0);
        row5_cell1.setCellValue("第四个输入框分数");

        Cell row5_cell2=row5.createCell(1);
        row5_cell2.setCellValue(inputModel1.getFragment_input4());

        Cell row5_cell3=row5.createCell(2);
        row5_cell3.setCellValue(inputModel2.getFragment_input4());

        Cell row5_cell4=row5.createCell(3);
        row5_cell4.setCellValue(inputModel3.getFragment_input4());

        Cell row5_cell5=row5.createCell(4);
        row5_cell5.setCellValue(inputModel4.getFragment_input4());

        Cell row5_cell6=row5.createCell(5);
        row5_cell6.setCellValue(inputModel5.getFragment_input4());

        Row row5_suggest = sheet.createRow(8);
        row5_suggest.setHeightInPoints(100);
        Cell row5_suggest_cell1=row5_suggest.createCell(0);
        row5_suggest_cell1.setCellValue("第四个意见");

        Cell row5_suggest_cell2=row5_suggest.createCell(1);
        row5_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input4());

        Cell row5_suggest_cell3=row5_suggest.createCell(2);
        row5_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input4());

        Cell row5_suggest_cell4=row5_suggest.createCell(3);
        row5_suggest_cell4.setCellValue(inputModel3.getSuggest_fragment_input4());

        Cell row5_suggest_cell5=row5_suggest.createCell(4);
        row5_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input4());

        Cell row5_suggest_cell6=row5_suggest.createCell(5);
        row5_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input4());


        //第6行

        Row row6 = sheet.createRow(9);
        row6.setHeightInPoints(40);

        Cell row6_cell1=row6.createCell(0);
        row6_cell1.setCellValue("第五个输入框分数");

        Cell row6_cell2=row6.createCell(1);
        row6_cell2.setCellValue(inputModel1.getFragment_input5());

        Cell row6_cell3=row6.createCell(2);
        row6_cell3.setCellValue(inputModel2.getFragment_input5());

        Cell row6_cell4=row6.createCell(3);
        row6_cell4.setCellValue(inputModel3.getFragment_input5());

        Cell row6_cell5=row6.createCell(4);
        row6_cell5.setCellValue(inputModel4.getFragment_input5());

        Cell row6_cell6=row6.createCell(5);
        row6_cell6.setCellValue(inputModel5.getFragment_input5());

        Row row6_suggest = sheet.createRow(10);
        row6_suggest.setHeightInPoints(100);
        Cell row6_suggest_cell1=row6_suggest.createCell(0);
        row6_suggest_cell1.setCellValue("第五个意见");

        Cell row6_suggest_cell2=row6_suggest.createCell(1);
        row6_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input5());

        Cell row6_suggest_cell3=row6_suggest.createCell(2);
        row6_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input5());

        Cell row6_suggest_cell4=row6_suggest.createCell(3);
        row6_suggest_cell4.setCellValue(inputModel3.getSuggest_fragment_input5());

        Cell row6_suggest_cell5=row6_suggest.createCell(4);
        row6_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input5());

        Cell row6_suggest_cell6=row6_suggest.createCell(5);
        row6_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input5());


        //第7行

        Row row7 = sheet.createRow(11);
        row7.setHeightInPoints(40);

        Cell row7_cell1=row7.createCell(0);
        row7_cell1.setCellValue("第六个输入框分数");

        Cell row7_cell2=row7.createCell(1);
        row7_cell2.setCellValue(inputModel1.getFragment_input6());

        Cell row7_cell3=row7.createCell(2);
        row7_cell3.setCellValue(inputModel2.getFragment_input6());

        Cell row7_cell4=row7.createCell(3);
        row7_cell4.setCellValue(inputModel3.getFragment_input6());

        Cell row7_cell5=row7.createCell(4);
        row7_cell5.setCellValue(inputModel4.getFragment_input6());

        Cell row7_cell6=row7.createCell(5);
        row7_cell6.setCellValue(inputModel5.getFragment_input6());

        Row row7_suggest = sheet.createRow(12);
        row7_suggest.setHeightInPoints(100);
        Cell row7_suggest_cell1=row7_suggest.createCell(0);
        row7_suggest_cell1.setCellValue("第六个意见");

        Cell row7_suggest_cell2=row7_suggest.createCell(1);
        row7_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input6());

        Cell row7_suggest_cell3=row7_suggest.createCell(2);
        row7_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input6());

        Cell row7_suggest_cell4=row7_suggest.createCell(3);
        row7_suggest_cell4.setCellValue(inputModel3.getSuggest_fragment_input6());

        Cell row7_suggest_cell5=row7_suggest.createCell(4);
        row7_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input6());

        Cell row7_suggest_cell6=row7_suggest.createCell(5);
        row7_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input6());

        //第8行

        Row row8 = sheet.createRow(13);
        row8.setHeightInPoints(40);

        Cell row8_cell1=row8.createCell(0);
        row8_cell1.setCellValue("第七个分数");

        Cell row8_cell2=row8.createCell(1);
        row8_cell2.setCellValue(inputModel1.getFragment_input7());

        Cell row8_cell3=row8.createCell(2);
        row8_cell3.setCellValue(inputModel2.getFragment_input7());

        Cell row8_cell4=row8.createCell(3);
        row8_cell4.setCellValue(inputModel3.getFragment_input7());

        Cell row8_cell5=row8.createCell(4);
        row8_cell5.setCellValue(inputModel4.getFragment_input7());

        Cell row8_cell6=row8.createCell(5);
        row8_cell6.setCellValue(inputModel5.getFragment_input7());

        Row row8_suggest = sheet.createRow(14);
        row8_suggest.setHeightInPoints(100);
        Cell row8_suggest_cell1=row8_suggest.createCell(0);
        row8_suggest_cell1.setCellValue("第七个意见");

        Cell row8_suggest_cell2=row8_suggest.createCell(1);
        row8_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input7());

        Cell row8_suggest_cell3=row8_suggest.createCell(2);
        row8_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input7());

        Cell row8_suggest_cell4=row8_suggest.createCell(3);
        row8_suggest_cell4.setCellValue(inputModel3.getSuggest_fragment_input7());

        Cell row8_suggest_cell5=row8_suggest.createCell(4);
        row8_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input7());

        Cell row8_suggest_cell6=row8_suggest.createCell(5);
        row8_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input7());

        //第9行

        Row row9 = sheet.createRow(15);
        row9.setHeightInPoints(40);

        Cell row9_cell1=row9.createCell(0);
        row9_cell1.setCellValue("第八个分数");

        Cell row9_cell2=row9.createCell(1);
        row9_cell2.setCellValue(inputModel1.getFragment_input8());

        Cell row9_cell3=row9.createCell(2);
        row9_cell3.setCellValue(inputModel2.getFragment_input8());

        Cell row9_cell4=row9.createCell(3);
        row9_cell4.setCellValue(inputModel3.getFragment_input8());

        Cell row9_cell5=row9.createCell(4);
        row9_cell5.setCellValue(inputModel4.getFragment_input8());

        Cell row9_cell6=row9.createCell(5);
        row9_cell6.setCellValue(inputModel5.getFragment_input8());


        Row row9_suggest = sheet.createRow(16);
        row9_suggest.setHeightInPoints(100);
        Cell row9_suggest_cell1=row9_suggest.createCell(0);
        row9_suggest_cell1.setCellValue("第八个意见");

        Cell row9_suggest_cell2=row9_suggest.createCell(1);
        row9_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input8());

        Cell row9_suggest_cell3=row9_suggest.createCell(2);
        row9_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input8());

        Cell row9_suggest_cell4=row9_suggest.createCell(3);
        row9_suggest_cell4.setCellValue(inputModel3.getSuggest_fragment_input8());

        Cell row9_suggest_cell5=row9_suggest.createCell(4);
        row9_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input8());

        Cell row9_suggest_cell6=row9_suggest.createCell(5);
        row9_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input8());

        //第10行

        Row row10 = sheet.createRow(17);
        row10.setHeightInPoints(40);

        Cell row10_cell1=row10.createCell(0);
        row10_cell1.setCellValue("第九个分数");

        Cell row10_cell2=row10.createCell(1);
        row10_cell2.setCellValue(inputModel1.getFragment_input9());

        Cell row10_cell3=row10.createCell(2);
        row10_cell3.setCellValue(inputModel2.getFragment_input9());

//        Cell row10_cell4=row3.createCell(3);
//        row10_cell4.setCellValue(inputModel3.getFragment_input9());

        Cell row10_cell5=row10.createCell(4);
        row10_cell5.setCellValue(inputModel4.getFragment_input9());

        Cell row10_cell6=row10.createCell(5);
        row10_cell6.setCellValue(inputModel5.getFragment_input9());

        Row row10_suggest = sheet.createRow(18);
        row10_suggest.setHeightInPoints(100);
        Cell row10_suggest_cell1=row10_suggest.createCell(0);
        row10_suggest_cell1.setCellValue("第九个意见");

        Cell row10_suggest_cell2=row10_suggest.createCell(1);
        row10_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input9());

        Cell row10_suggest_cell3=row10_suggest.createCell(2);
        row10_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input9());


        Cell row10_suggest_cell5=row10_suggest.createCell(4);
        row10_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input9());

        Cell row10_suggest_cell6=row10_suggest.createCell(5);
        row10_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input9());

        //第11行

        Row row11 = sheet.createRow(19);
        row11.setHeightInPoints(40);

        Cell row11_cell1=row11.createCell(0);
        row11_cell1.setCellValue("第十个分数");

        Cell row11_cell2=row11.createCell(1);
        row11_cell2.setCellValue(inputModel1.getFragment_input10());

        Cell row11_cell3=row11.createCell(2);
        row11_cell3.setCellValue(inputModel2.getFragment_input10());

//        Cell row11_cell4=row11.createCell(3);
//        row11_cell4.setCellValue(inputModel3.getFragment_input10());

        Cell row11_cell5=row11.createCell(4);
        row11_cell5.setCellValue(inputModel4.getFragment_input10());

        Cell row11_cell6=row11.createCell(5);
        row11_cell6.setCellValue(inputModel5.getFragment_input10());

        Row row11_suggest = sheet.createRow(20);
        row11_suggest.setHeightInPoints(100);
        Cell row11_suggest_cell1=row11_suggest.createCell(0);
        row11_suggest_cell1.setCellValue("第十个意见");

        Cell row11_suggest_cell2=row11_suggest.createCell(1);
        row11_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input10());

        Cell row11_suggest_cell3=row11_suggest.createCell(2);
        row11_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input10());


        Cell row11_suggest_cell5=row11_suggest.createCell(4);
        row11_suggest_cell5.setCellValue(inputModel4.getSuggest_fragment_input10());

        Cell row11_suggest_cell6=row11_suggest.createCell(5);
        row11_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input10());

        //第12行

        Row row12 = sheet.createRow(21);
        row12.setHeightInPoints(40);

        Cell row12_cell1=row12.createCell(0);
        row12_cell1.setCellValue("第十一个分数");

        Cell row12_cell2=row12.createCell(1);
        row12_cell2.setCellValue(inputModel1.getFragment_input11());

        Cell row12_cell3=row12.createCell(2);
        row12_cell3.setCellValue(inputModel2.getFragment_input11());

//        Cell row12_cell4=row12.createCell(3);
//        row12_cell4.setCellValue(inputModel3.getFragment_input11());
//
//        Cell row12_cell5=row12.createCell(4);
//        row12_cell5.setCellValue(inputModel4.getFragment_input11());

        Cell row12_cell6=row12.createCell(5);
        row12_cell6.setCellValue(inputModel5.getFragment_input11());

        Row row12_suggest = sheet.createRow(22);
        row12_suggest.setHeightInPoints(100);
        Cell row12_suggest_cell1=row12_suggest.createCell(0);
        row12_suggest_cell1.setCellValue("第十一个意见");

        Cell row12_suggest_cell2=row12_suggest.createCell(1);
        row12_suggest_cell2.setCellValue(inputModel1.getSuggest_fragment_input11());

        Cell row12_suggest_cell3=row12_suggest.createCell(2);
        row12_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input11());


        Cell row12_suggest_cell6=row12_suggest.createCell(5);
        row12_suggest_cell6.setCellValue(inputModel5.getSuggest_fragment_input11());


        //第13行

        Row row13 = sheet.createRow(23);
        row13.setHeightInPoints(40);

        Cell row13_cell1=row13.createCell(0);
        row13_cell1.setCellValue("第十二个分数");

//        Cell row13_cell2=row13.createCell(1);
//        row13_cell2.setCellValue(inputModel1.getFragment_input12());

        Cell row13_cell3=row13.createCell(2);
        row13_cell3.setCellValue(inputModel2.getFragment_input12());

//        Cell row12_cell4=row13.createCell(3);
//        row12_cell4.setCellValue(inputModel3.getFragment_input11());
//
//        Cell row12_cell5=row13.createCell(4);
//        row12_cell5.setCellValue(inputModel4.getFragment_input11());

//        Cell row13_cell6=row13.createCell(5);
//        row13_cell6.setCellValue(inputModel5.getFragment_input12());


        Row row13_suggest = sheet.createRow(24);
        row13_suggest.setHeightInPoints(100);
        Cell row13_suggest_cell1=row13_suggest.createCell(0);
        row13_suggest_cell1.setCellValue("第十二个意见");

        Cell row13_suggest_cell3=row13_suggest.createCell(2);
        row13_suggest_cell3.setCellValue(inputModel2.getSuggest_fragment_input12());


        Row row14_suggest = sheet.createRow(26);
        row14_suggest.setHeightInPoints(200);
        Cell row14_suggest_cell1=row14_suggest.createCell(0);
        row14_suggest_cell1.setCellValue("总意见");

        Cell row14_suggest_cell2=row14_suggest.createCell(1);
        row14_suggest_cell2.setCellValue(inputModel1.getSuggest_input());

        Cell row14_suggest_cell3=row14_suggest.createCell(2);
        row14_suggest_cell3.setCellValue(inputModel2.getSuggest_input());

        Cell row14_suggest_cell4=row14_suggest.createCell(3);
        row14_suggest_cell4.setCellValue(inputModel3.getSuggest_input());

        Cell row14_suggest_cell5=row14_suggest.createCell(4);
        row14_suggest_cell5.setCellValue(inputModel4.getSuggest_input());

        Cell row14_suggest_cell6=row14_suggest.createCell(5);
        row14_suggest_cell6.setCellValue(inputModel5.getSuggest_input());


        Row row15_suggest = sheet.createRow(27);
        row15_suggest.setHeightInPoints(200);
        Cell row15_suggest_cell1=row15_suggest.createCell(0);
        row15_suggest_cell1.setCellValue("总问题");

        Cell row15_suggest_cell2=row15_suggest.createCell(1);
        row15_suggest_cell2.setCellValue(inputModel1.getProblem_input());

        Cell row15_suggest_cell3=row15_suggest.createCell(2);
        row15_suggest_cell3.setCellValue(inputModel2.getProblem_input());

        Cell row15_suggest_cell4=row15_suggest.createCell(3);
        row15_suggest_cell4.setCellValue(inputModel3.getProblem_input());

        Cell row15_suggest_cell5=row15_suggest.createCell(4);
        row15_suggest_cell5.setCellValue(inputModel4.getProblem_input());

        Cell row15_suggest_cell6=row15_suggest.createCell(5);
        row15_suggest_cell6.setCellValue(inputModel5.getProblem_input());







        writeFile(workbook,getFile(context),context);
    }

    /**
     * 合并cell单元格
     *
     *  CellRangeAddress构造器中参数：
     *    参数1：first row(0-based)
     *    参数2：last row(0-based)
     *    参数3：first column(0-based)
     *    参数4：last column(0-based)
     *
     * @param sheet
     * @param cellRangeAddress
     */
    private static  void mergingCells(Sheet sheet, CellRangeAddress cellRangeAddress){
        sheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 设置Sheet表
     * @param sheet
     */
    private static void setSheet(Sheet sheet){
        // turn off gridlines（关闭网络线）
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(false);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        //只对HSSF需要用到的
        sheet.setAutobreaks(true);
        printSetup.setFitHeight((short)1);
        printSetup.setFitWidth((short)1);
    }

    /**
     * 获取指定文件
     * @param context
     * @return
     */
    public static  String getFile(Context context){
        File dirFile= MyUtils.getCacheFile(context,DIR_FILE_NAME);
        if(dirFile!=null&&!dirFile.exists()){
            dirFile.mkdirs();
        }
        return  dirFile.getAbsolutePath()+File.separator+new Date().toString()+SUFFIX;
    }

    /**
     * 创建各种不同单元格特征,根据个人需求不同而定 。
     * @return
     */
    private static SparseArray<CellStyle> creatCellStyles(Workbook workbook){
        SparseArray<CellStyle> array=new SparseArray<>();
        //第一行的单元格特征
        CellStyle cellStyle0=createBorderedStyle(workbook);
        Font font0=creatFont(workbook);
        font0.setFontHeightInPoints((short) 14);
        font0.setBold(true);
        cellStyle0.setFont(font0);
        array.put(0,cellStyle0);

        //第二行的单元格特征
        CellStyle cellStyle1=createBorderedStyle(workbook);
        Font font1=creatFont(workbook);
        font1.setBold(true);
        cellStyle1.setFont(font1);
        array.put(1,cellStyle1);
        //第三行的单元格特征
        array.put(2,cellStyle1);
        //第四行的单元格特征

        CellStyle cellStyle3= workbook.createCellStyle();
        cellStyle3.setAlignment(HorizontalAlignment.CENTER);
        cellStyle3.setVerticalAlignment(VerticalAlignment.TOP);
        //使用换行符，需要开启wrap. 换行的文本用“\n”连接
        cellStyle3.setWrapText(true);
        array.put(3,cellStyle3);
        return    array;
    }
    /**
     * 设置表格的内容到四边的距离,表格四边的颜色
     *
     * 对齐方式：
     *   水平： setAlignment();
     *   竖直：setVerticalAlignment()
     *
     * 四边颜色：
     *    底边：  cellStyle.setBottomBorderColor()
     *
     * 四边边距：
     *
     * 填充：
     *
     * 缩进一个字符：
     *   setIndention()
     *
     * 内容类型：
     *   setDataFormat()
     * @param workbook
     * @return
     */
    private  static CellStyle createBorderedStyle(Workbook workbook){
        CellStyle cellStyle= workbook.createCellStyle();
        //对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
/*     //重新设置单元格的四边颜色
    BorderStyle thin=BorderStyle.THIN;
    short blackColor_Index=IndexedColors.BLACK.getIndex();
    cellStyle.setBottomBorderColor(blackColor_Index);
    cellStyle.setBorderBottom(thin);
    cellStyle.setTopBorderColor(blackColor_Index);
    cellStyle.setBorderTop(thin);
    cellStyle.setRightBorderColor(blackColor_Index);
    cellStyle.setBorderRight(thin);
    cellStyle.setLeftBorderColor(blackColor_Index);
    cellStyle.setBorderLeft(thin);*/
        return  cellStyle;
    }
    /**
     * 创建Font
     *
     * 注意点：excle工作簿中字体最大限制为32767，应该重用字体，而不是为每个单元格都创建字体。
     *
     *  其API:
     *    setBold():设置粗体
     *    setFontHeightInPoints():设置字体的点数
     *    setColor():设置字体颜色
     *    setItalic():设置斜体
     * @param workbook
     * @return
     */
    private static Font creatFont(Workbook workbook){
        Font font=workbook.createFont();
        return font;
    }
    /**
     *  将Excle表格写入文件中
     *
     * @param workbook
     * @param fileName
     */
    private static void   writeFile(Workbook workbook ,String fileName,Context context){
        FileOutputStream outputStream=null;
        try{
            outputStream=new FileOutputStream(fileName);
            workbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            ScoreActivity scoreActivity = (ScoreActivity) context;
//            scoreActivity.showLoadingDialog();
            try{
                if(outputStream!=null){
                    outputStream.close();

                }
                if(workbook!=null){
                    workbook.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
