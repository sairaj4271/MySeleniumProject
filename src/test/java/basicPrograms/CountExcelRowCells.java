package basicPrograms;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CountExcelRowCells {

    private static final String DOWNLOAD_DIR = System.getProperty("user.home") + "/Downloads";

    public static File getLatestDownloadedFile(String extension) throws Exception {
        try (Stream<Path> files = Files.list(Paths.get(DOWNLOAD_DIR))) {
            return files
                    .filter(f -> f.toString().endsWith(extension))
                    .max(Comparator.comparingLong(f -> f.toFile().lastModified()))
                    .map(Path::toFile)
                    .orElseThrow(() -> new Exception("No recent " + extension + " file found"));
        }
    }

    public static int countNonEmptyCellsInRow(File file, int rowIndex) throws Exception {
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(rowIndex);
            if (row == null) return 0;

            int count = 0;
            for (Cell cell : row) {
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    count++;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        try {
            File latestFile = getLatestDownloadedFile(".xlsx");
            int count = countNonEmptyCellsInRow(latestFile, 1); // 1 = second row
            System.out.println("Non-empty cell count: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
