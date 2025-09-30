package basicPrograms;

import java.io.File;

public class FileDownloadCheck {
    public static void main(String[] args) {
        // Get user's download directory
        String home = System.getProperty("user.home");
        String downloadPath = home + "\\Downloads";

        // Expected file name
        String expectedFileName = "example.xlsx";

        // Build full path
        File file = new File(downloadPath + "\\" + expectedFileName);

        // Optional: wait for download to happen
        try {
            Thread.sleep(5000); // wait 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify file is NOT downloaded
        if (!file.exists()) {
            System.out.println("✅ File not downloaded - Test Passed");
        } else {
            System.out.println("❌ File was downloaded - Test Failed");
        }
    }
}
