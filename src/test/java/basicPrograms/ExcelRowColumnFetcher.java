package basicPrograms;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRowColumnFetcher {

    public static void main(String[] args) {
        String downloadPath = "C:\\Users\\sairaj\\Downloads"; // Update your path
        int sheetIndex = 0; // First sheet
        int rowNumber = 0;  // Example: 2nd row (index starts at 0)
        int columnNumber = 1; // Example: 2nd column

        File latestFile = getLatestFileFromDir(downloadPath);
        if (latestFile != null) {
            try (FileInputStream fis = new FileInputStream(latestFile);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheetAt(sheetIndex);
                System.out.println("Reading from sheet: " + sheet.getSheetName());

                // Fetch row
                System.out.println("Row " + rowNumber + " data:");
                List<String> rowData = getRowData(sheet, rowNumber);
                System.out.println(rowData);

                // Fetch column
                System.out.println("Column " + columnNumber + " data:");
                List<String> columnData = getColumnData(sheet, columnNumber);
                System.out.println(columnData);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No Excel file found in download folder.");
        }
    }

    public static File getLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".xlsx"));
        if (files == null || files.length == 0) return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }

    // Fetch row by index
    public static List<String> getRowData(Sheet sheet, int rowIndex) {
        List<String> rowData = new ArrayList<>();
        Row row = sheet.getRow(rowIndex);
        if (row == null) return rowData;

        for (Cell cell : row) {
            rowData.add(getCellValue(cell));
        }
        return rowData;
    }

    // Fetch column by index
    public static List<String> getColumnData(Sheet sheet, int columnIndex) {
        List<String> columnData = new ArrayList<>();
        for (Row row : sheet) {
            Cell cell = row.getCell(columnIndex);
            columnData.add(cell != null ? getCellValue(cell) : "");
        }
        return columnData;
    }

    // Convert cell value to string
    public static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:  return cell.getStringCellValue();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            default:      return "";
        }
    }
}
