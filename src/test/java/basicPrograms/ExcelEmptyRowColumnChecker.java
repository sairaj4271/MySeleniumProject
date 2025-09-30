package basicPrograms;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Comparator;

public class ExcelEmptyRowColumnChecker {

    public static void main(String[] args) throws Exception {
        
        File downloadDir = new File(System.getProperty("user.home") + "/Downloads");
        File latestFile = Arrays.stream(downloadDir.listFiles((dir, name) -> name.endsWith(".xlsx")))
                .max(Comparator.comparingLong(File::lastModified))
                .orElseThrow(() -> new RuntimeException("No Excel file found"));

        System.out.println("Checking file: " + latestFile.getName());

        
        FileInputStream fis = new FileInputStream(latestFile);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        boolean hasEmptyRow = false;
        boolean hasEmptyColumn = false;

        int lastRowNum = sheet.getLastRowNum();
        int maxColCount = 0;

        
        for (Row row : sheet) {
            if (row != null) {
                maxColCount = Math.max(maxColCount, row.getLastCellNum());
            }
        }

        
        for (Row row : sheet) {
            if (row == null || isRowEmpty(row)) {
                hasEmptyRow = true;
                break;
            }
        }

        
        for (int col = 0; col < maxColCount; col++) {
            boolean columnEmpty = true;
            for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    Cell cell = row.getCell(col);
                    if (cell != null && cell.getCellType() != CellType.BLANK &&
                        !(cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty())) {
                        columnEmpty = false;
                        break;
                    }
                }
            }
            if (columnEmpty) {
                hasEmptyColumn = true;
                break;
            }
        }

        workbook.close();

        System.out.println("Contains empty row: " + hasEmptyRow);
        System.out.println("Contains empty column: " + hasEmptyColumn);
    }

    private static boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK &&
                !(cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty())) {
                return false;
            }
        }
        return true;
    }
}
