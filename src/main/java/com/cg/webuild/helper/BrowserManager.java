package com.cg.webuild.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.cg.webuild.helper.BaseProperties;
import com.cg.webuild.helper.WebDriverListener;

public class BrowserManager extends BaseProperties {
	
	private static BrowserManager browserInst=null;
	private WebDriver basedriver;
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
    private static Logger log = Logger.getLogger(BrowserManager.class);
    
    private BrowserManager() {
    }
    
    public static BrowserManager getInstance() {
		if(browserInst==null) {
			browserInst=new BrowserManager();
		}
		return browserInst;
	}

	public WebDriver getDriver() throws Exception
	{
		synchronized(this){
		  readProperties();
		  log.info("Current Browser is : " + browser);
		  String os=System.getProperty("os.name");
		  log.info("Current OS is : " + os);
		  if(browser.trim().toLowerCase().equals("chrome"))
		  {
			  ChromeOptions options=new ChromeOptions();
			  options.addArguments("--no-sandbox");
			  options.addArguments("disable-infobars");
			  options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			  options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			  if(os.toLowerCase().trim().contains("mac"))
				  System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/mac/chromedriver");
			  else
				  System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/windows/chromedriver.exe");

			  basedriver = new ChromeDriver(options);
			  SessionId sessionid = ((RemoteWebDriver) basedriver).getSessionId();
			  log.info("Created Session Id - " + sessionid);
		  }
		  else if(browser.trim().toLowerCase().equals("firefox"))
		  {
			  FirefoxOptions options = new FirefoxOptions();
			  options.setAcceptInsecureCerts(true);
			  DesiredCapabilities cap=new DesiredCapabilities();
			  cap.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
			  cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			  cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			  if(os.toLowerCase().trim().contains("mac"))
				  System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/mac/geckodriver");
			  else
				  System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/windows/geckodriver.exe");
			  basedriver = new FirefoxDriver(options);
		  }
		  else if(browser.trim().toLowerCase().equals("internet explorer"))
		  {
			  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			  capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			  capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			  InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
			  System.setProperty("webdriver.ie.driver", "src/main/resources/driver/windows/IEDriverServer.exe");
			  basedriver = new InternetExplorerDriver(options);
		  }
	    }
		  
		EventFiringWebDriver efwd=new EventFiringWebDriver(basedriver);
		WebDriverListener eventListener=new WebDriverListener(basedriver);
		efwd.register(eventListener);
		driver.set(basedriver);
		driver.get().manage().window().maximize();
		driver.get().manage().deleteAllCookies();

		return driver.get();
	}
	
	public WebDriver getWebDriver() throws Exception {
		return driver == null ? getDriver() : driver.get();
	}
	
	public void quitDriver() {
		if (driver != null) {
			try {
				driver.get().quit();
			} catch (WebDriverException var1) {
				log.info("**********CAUGHT EXCEPTION IN DRIVER TEAR DOWN**********");
				log.info(var1);
			}
		}
	}
}