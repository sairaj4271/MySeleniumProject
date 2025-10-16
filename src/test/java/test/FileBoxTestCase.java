package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Filebox;
import pages.LoginBluecopa;
import base.BaseClass;

@Listeners(listeners.TestListener.class)
public class FileBoxTestCase extends BaseClass {
	@Test
    @Parameters({"baseUrl", "username", "password"})
   
    public void testFileBox(String baseUrl, String username, String password) throws InterruptedException {

        // driver is already initialized in BaseClass setUp()
        LoginBluecopa loginPage = new LoginBluecopa(driver);
        Filebox filebox = new Filebox(driver);

        loginPage.gotoLoginPage(baseUrl);
        loginPage.login(username, password);
         filebox.navigateToFilebox();
        filebox.createFilebox();
        filebox.saveFilebox();
        filebox.uploadFileInFilebox("C:/Users/Sairaj/Downloads/multi_sheet_xls1234.xls");

        Thread.sleep(10000);
        System.out.println("Created Filebox: " + filebox.getGeneratedFileboxName());
    }
}
