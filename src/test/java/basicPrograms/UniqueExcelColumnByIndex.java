package basicPrograms;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.*;

public class UniqueExcelColumnByIndex {

    public static void main(String[] args) {
        String downloadsPath = System.getProperty("user.home") + "/Downloads";
        int targetColumnIndex = 1; // ✅ Change this to the desired column index (0-based)

        try {
            File latestFile = getLatestDownloadedFile(downloadsPath, ".xlsx");
            if (latestFile == null) {
                System.out.println("No Excel file found in Downloads folder.");
                return;
            }

            System.out.println("Reading from file: " + latestFile.getName());

            Set<String> uniqueValues = getUniqueColumnValuesByIndex(latestFile, targetColumnIndex);
            System.out.println("Unique values in column index " + targetColumnIndex + ": " + uniqueValues);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File getLatestDownloadedFile(String folderPath, String extension) {
        File dir = new File(folderPath);
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(extension));

        if (files == null || files.length == 0) return null;

        return Arrays.stream(files)
                     .max(Comparator.comparingLong(File::lastModified))
                     .orElse(null);
    }

    private static Set<String> getUniqueColumnValuesByIndex(File excelFile, int colIndex) throws Exception {
        Set<String> uniqueValues = new HashSet<>();

        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Loop from row 0 (or 1 if skipping header row)
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(colIndex);
                    if (cell != null) {
                        String value = getCellValue(cell);
                        if (!value.isEmpty()) {
                            uniqueValues.add(value);
                        }
                    }
                }
            }
        }

        return uniqueValues;
    }

    private static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
