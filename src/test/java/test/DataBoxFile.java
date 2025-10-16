package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import org.testng.annotations.Test;
import pages.DataBoxPage;
import pages.LoginBluecopa;
import base.BaseClass;
@Listeners(listeners.TestListener.class)

public class DataBoxFile extends BaseClass {

    @Test
    @Parameters({"baseUrl", "username", "password"})
    public void testLoginAndCreateDatabox(String baseUrl, String username, String password) {
        // Login Page
        LoginBluecopa loginPage = new LoginBluecopa(driver);
        loginPage.gotoLoginPage(baseUrl);
        loginPage.login(username, password);

        // DataBox Page
        DataBoxPage dataBoxPage = new DataBoxPage(driver);
        dataBoxPage.navigateToDatabox();
        dataBoxPage.createDatabox();

        // Upload file (replace with actual path)
        dataBoxPage.uploadFileInDatabox("C:/Users/Sairaj/Downloads/multi_sheet_xls1234.xls");

        System.out.println("Created Databox: " + dataBoxPage.getGeneratedFileboxName());
    }
}
