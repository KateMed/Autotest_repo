package com.gmail.waylacteal;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ExcelReader {
    //важное примечание!!!
    //если изначальная таблица excel заполнена не полностью, то на пустых местах будет null

    //private File file = new File("C:\\Users\\Waylacteal\\Desktop\\тестирование stc\\Лекция 7\\Test_for_Wrike\\test1.xlsx");
    private File file = new File("test.xlsx");
    public static void main(String[] args) throws IOException {
        ExcelReader readerExcel = new ExcelReader();
        Object[][] d = readerExcel.readExcel("sheet1");
        System.out.println(Arrays.deepToString(d));
    }


    public Object[][] readExcel(String sheetName) throws IOException {
        Object[][] data = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowcount = sheet.getLastRowNum() + 1;
            System.out.print(rowcount);
            int[] cellcountArr = new int[rowcount];
            for (int r = 0; r < rowcount; r++) {
                cellcountArr[r] = sheet.getRow(r).getLastCellNum();
            }
            Arrays.sort(cellcountArr);
            int cellcount = cellcountArr[rowcount - 1];
            data = new Object[rowcount][cellcount];
            for (int r = 0; r < rowcount; r++) {
                XSSFRow row = sheet.getRow(r);
                for (int c = 0; c < cellcount; c++) {
                    data[r][c] = row.getCell(c);
                }
            }
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return data;

    }
}

