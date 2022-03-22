package com.hrm.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ExcelUtility {
    private static FileInputStream fileInputStream;
    private static Workbook workbook;
    private static Sheet sheet;

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");  // The last forward-slash '/' at the very end is not included in the path. If you append anything start with /filename/etc.
        System.out.println(projectPath);
        String filePath = projectPath + "/data/HrmTestData.xlsx";
        System.out.println(filePath);
        String absoluteFilePath = System.getProperty("user.dir") + "/data/HrmTestData.xlsx";
        System.out.println(absoluteFilePath);
        System.out.println(Arrays.deepToString(excelToArray(System.getProperty("user.dir") + "/data/HrmTestData.xlsx", "Employee")));
    }

    private static void openExcel(String filePath) {
        try {
            fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    private static int rowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    private static int colsCount() {
        return sheet.getRow(0).getLastCellNum(); // blank cells, in between, are counted as well
    }

    private static String cellData(int rowIndex, int colIndex) {
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

    // Return a 2d object array of data (using inner loop to retrieve data)
    public static Object[][] excelToArray(String filePath, String sheetName) {
        openExcel(filePath);
        loadSheet(sheetName);

        int rows = rowCount();
        int cols = colsCount(); // this is calling above method and is same as:
                                          // sheet.getRow(0).getLastCellNum(); Length of given column.

        Object[][] data = new Object[rows - 1][cols];  // -1 is to deduct header from the rows.

        for (int i = 1; i < rows; i++) {             // we start from 2nd raw, skip header, thus 1!=0, but i=1.
            for (int j = 0; j < cols; j++) {         // we start from 1st col of 2nd row, thus j=0.
                data[i - 1][j] = cellData(i, j);     // coordinates of very first top-left corner cell is row=0,col=0.
            }
        }
        //System.out.println(Arrays.deepToString(data)); // this is to print cell data when needed and see if there is any null/empty cells exits, to void nullException.

        // Once you are done, close everything.
        try {
            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }

    // Map version: Retrieve data using Map, instead of inner loop.

}
