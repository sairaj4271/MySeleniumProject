package basicPrograms;
 
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.WebAction;
import com.testsigma.sdk.annotation.Action;
import com.testsigma.sdk.annotation.RunTimeData;
import com.testsigma.sdk.annotation.TestData;
 
import lombok.Data;
 
@Data
@Action(actionText = "Count non-empty cells in row number rowIndex from latest downloaded .xlsx file. Store into variable testData", description = "Counts non-empty cells in the given row from the most recent downloaded Excel (.xlsx) file", applicationType = ApplicationType.WEB)
public class LaunchChroeBrowser extends WebAction {
 
	@TestData(reference = "rowIndex")
	private com.testsigma.sdk.TestData rowIndex;
 
	@TestData(reference = "testData", isRuntimeVariable = true)
	private com.testsigma.sdk.TestData testData;
 
	@RunTimeData
	private com.testsigma.sdk.RunTimeData runTimeData;
 
	private static final String DOWNLOAD_DIR = System.getProperty("user.home") + "/Downloads";
 
	@Override
	public com.testsigma.sdk.Result execute() {
		try {
			File latestFile = getLatestDownloadedFile(".xlsx");
			int index = Integer.parseInt(rowIndex.getValue().toString());
			int count = countNonEmptyCellsInRow(latestFile, index);
			runTimeData.setKey(String.valueOf(count));
			runTimeData.setValue(testData.getValue().toString());
			setSuccessMessage("Non-empty cell count in row " + index + " is: " + count);
			return com.testsigma.sdk.Result.SUCCESS;
		} catch (Exception e) {
			setErrorMessage("Error while counting cells: " + e.getMessage());
			return com.testsigma.sdk.Result.FAILED;
		}
	}
 
	private File getLatestDownloadedFile(String extension) throws Exception {
		try (Stream<Path> files = Files.list(Paths.get(DOWNLOAD_DIR))) {
			return files.filter(f -> f.toString().endsWith(extension))
					.max(Comparator.comparingLong(f -> f.toFile().lastModified())).map(Path::toFile)
					.orElseThrow(() -> new Exception("No recent " + extension + " file found"));
		}
	}
 
	private int countNonEmptyCellsInRow(File file, int rowIndex) throws Exception {
		try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(rowIndex);
			if (row == null)
				return 0;
 
			int count = 0;
			for (Cell cell : row) {
				if (cell != null && cell.getCellType() != CellType.BLANK) {
					count++;
				}
			}
			return count;
		}
	}
}
 
 