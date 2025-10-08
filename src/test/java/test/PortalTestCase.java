package test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.LoginBluecopa;
import Pages.PortalTestcase;
import base.BaseClass;

public class PortalTestCase   extends BaseClass {
	
	@Test
	@Parameters({"baseUrl","username", "password" })

	 public void testforPortal(String baseUrl , String usename , String password ) throws InterruptedException {
		 
		   LoginBluecopa  loginpage  =  new LoginBluecopa(driver);
		   PortalTestcase portal   =new PortalTestcase(driver);
		   
		   
		   loginpage.gotoLoginPage(baseUrl);
		     
		   loginpage.login(usename, password);
		   
		   portal.navigateToPortal();
		   portal.createPortal();
		   portal.InputTable();
		   
		  
	 }
	
	
}
