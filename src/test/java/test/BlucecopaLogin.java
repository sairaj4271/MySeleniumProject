package test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Pages.LoginBluecopa;
import base.BaseClass;

public class BlucecopaLogin extends BaseClass {

    @Test
    @Parameters({"baseUrl", "username", "password"})
    public void testLogin(String baseUrl, String username, String password) throws InterruptedException {
        LoginBluecopa loginPage = new LoginBluecopa(driver);

      
        loginPage.gotoLoginPage(baseUrl);

       
        loginPage.login(username, password);
        
    }
}
