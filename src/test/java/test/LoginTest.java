package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Pages.DashboardPage;
import Pages.LoginForLender;
import base.BaseClass;


@Listeners(listeners.TestListener.class)

public class LoginTest extends BaseClass {

    

    @Test
    public void testLogin() {
    	
   LoginForLender loginpage = new LoginForLender(driver);
   DashboardPage  dashborad  = new DashboardPage(driver);
    	   
    loginpage.navigateToURL();
    loginpage.loginData();
    dashborad.CommitmentsPriceOffered();
    
    	
    	
    }
   }