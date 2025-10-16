package basicPrograms;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class ReadSpecificCellFromExcel {
    public static void main(String[] args) throws IOException {
        
        String downloadDir = System.getProperty("user.home") + File.separator + "Downloads";

        
        File latestFile = getLatestFile(downloadDir);
        if (latestFile == null || !latestFile.getName().endsWith(".xlsx")) {
            System.out.println("No valid Excel file found.");
            return;
        }

        System.out.println("Reading file: " + latestFile.getName());

        
        int rowNum = 5;  
        int colNum = 0; 

        
        String cellValue = readSpecificCell(latestFile, rowNum, colNum);
        System.out.println("Value at Row " + rowNum + ", Column " + colNum + ": " + cellValue);
    }

    
    private static File getLatestFile(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles(File::isFile);
        if (files == null || files.length == 0) {
            return null;
        }
        return Arrays.stream(files)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
    }

    
    private static String readSpecificCell(File file, int rowNum, int colNum) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        String cellValue = "Cell Not Found";  
        Row row = sheet.getRow(rowNum);  
        if (row != null) {
            Cell cell = row.getCell(colNum);  
            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        cellValue = String.valueOf(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                        break;
                    default:
                        cellValue = "UNKNOWN TYPE";
                }
            }
        }

        workbook.close();
        fis.close();
        return cellValue;
    }
}
