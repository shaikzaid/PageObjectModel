package apisecfree.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import apisecfree.util.TestUtil;

public class TestBase {
	
	static WebDriver driver;
	//Initializing driver with static variable//
	
	static Properties prop;
	//prop is a global variable used through out the program, inside child class and testbase//
	
	//TestBase is a constructor.create constructor and read the properties//
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\RED TAG\\Downloads\\ScanFree-20230907T175805Z-001\\ScanFree\\src\\main\\java\\apisecfree\\config\\config.properties");//path of property files//
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 public static void initialization() {
		 String browserName = prop.getProperty("browser");
		 if(browserName.equals("chrome")) {
			 System.setProperty("webdriver.chrome.driver","C:\\Users\\RED TAG\\OWASP ZAP\\webdriver\\windows\\32");
			 driver = new ChromeDriver();
		 }
		 else if(browserName.equals("FF")){
			 System.setProperty("webdriver.gecko.driver", "C:\\Users\\RED TAG\\OWASP ZAP\\webdriver\\windows\\32");
			 driver = new FirefoxDriver();
		 }
		 //public String getDriverPath(){
			//	String driverPath = Properties.getprop("chromedriverpath");
				//if(driverPath!= null) return driverPath;
				//else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
			//}
		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		 driver.get(prop.getProperty("url"));
	 }
	
	
	}


