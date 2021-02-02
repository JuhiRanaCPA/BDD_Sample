/*package cpa.qa.library;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.ldap.Rdn;

//import atu.testrecorder.ATUTestRecorder;
//import atu.testrecorder.exceptions.ATUTestRecorderException;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
//import org.apache.http.client.fluent.Request;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

//import org.sikuli.script.Pattern;
//import org.sikuli.script.Screen;
import com.aventstack.extentreports.Status;
import com.google.common.collect.Sets;
import com.steadystate.css.dom.Property;

//import cpa.qa.testcases.TestRunner;
import cpa.qa.util.JSWaiter;
//import cpa.qa.util.Keywords;
import cpa.qa.util.PropertiesUtil;

public class Gherkin_Functions {
    public WebDriver wbdriver = null;
    WebDriver driver1 = null;
    WebDriver tempDriver = null;
    public String browserType;
    int sectioncount = 0;
    int actioncount = 0;
    Set<String> sections = new HashSet<String>();
    int x;

    public static EventFiringWebDriver driver;
    WebEventListener eventListener;

//    ATUTestRecorder recorder;

    static {
        System.out.println("Home Directory : " + System.getProperty("user.dir"));
        System.setProperty("user.home", System.getProperty("user.dir"));
    }

    public static Logger log = Logger.getLogger("FileLogger");

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................BEGIN................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: openBrowser Parameters: browserType Return Type: String
     * Objective: Public method to open browser of 'browserType' specified in Test
     * Cases.xlsx
     *
     * @throws Exception
     **//*
    public String openBrowser(String browserType1) throws Exception {
        Date dt = new Date();
//        recorder = new ATUTestRecorder(System.getProperty("user.dir") + "\\videos\\", "FILE_Regression_" + dt.getTime(), false);
//        recorder.start();

        browserType = browserType1;
        // KillDriver();
        if (browserType1.equals("Mozilla")) {
            FirefoxProfile ffProfile = new FirefoxProfile();
            ffProfile.setAcceptUntrustedCertificates(false);
            // File pathToBinary = new File("C:\\Program Files\\Mozilla
            // Firefox\\firefox.exe");
            // FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
            // FirefoxProfile firefoxProfile = new FirefoxProfile();
            // driver = new FirefoxDriver(ffBinary,firefoxProfile);
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
//	        WebDriverManager.firefoxdriver().setup();
            wbdriver = new FirefoxDriver();
        } else if (browserType1.equals("Chrome")) {

            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
//            WebDriverManager.chromedriver().setup();
//          DesiredCapabilities capability = DesiredCapabilities.chrome();
//          capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-arguments");
            options.addArguments("--test-type");
            options.addArguments("--disable-notifications");
            options.addArguments("test");
            options.addArguments("disable-infobars");
//            options.addArguments("--headless");
            wbdriver = new ChromeDriver(options);
//          ChromeOptions opt = new ChromeOptions();
//          opt.addArguments("test");
            // ngDriver = new NgWebDriver((JavascriptExecutor) driver);
            // ngDriver.waitForAngularRequestsToFinish();

        } else if (browserType1.equals("HeadlessBrowser")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
//            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            wbdriver = new ChromeDriver(options);
//            driver = new ChromeDriver(options);
        } else if (browserType1.equals("IE")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\IEDriverServer.exe");
//            WebDriverManager.iedriver().setup();
            //System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\MicrosoftWebDriver.exe");
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//            caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//            caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
            caps.setCapability("enableElementCacheCleanup", true);
            caps.setCapability("requireWindowFocus", true);
            caps.setCapability("nativeEvents", false);
//            caps.setCapability("allow-blocked-content", true);
            caps.setCapability("enablePersistentHover", true);
            caps.setCapability("ignoreProtectedModeSettings", true);
//            caps.setCapability("enableElementCacheCleanup", true);
            caps.setJavascriptEnabled(true);

            wbdriver = new InternetExplorerDriver(caps);
            //driver = new EdgeDriver();
            // DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            // capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
            // true);
            // capabilities.setCapability("requireWindowFocus", true);
            // driver = new InternetExplorerDriver(capabilities);
            // driver = new InternetExplorerDriver();
        }
        driver = new EventFiringWebDriver(wbdriver);
        eventListener = new WebEventListener();
        driver.register(eventListener);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // TestRunner.test.log(Status.PASS, "<b><I>"+browserType1 +"</b></I> is launched
        // successfully. ");
        return "Pass";
    }

    
     * public static int getResponseCode(String url) { try { return
     * Request.Get(url).execute().returnResponse().getStatusLine() .getStatusCode();
     * } catch (Exception e) { throw new RuntimeException(e); } }
     

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: FirefoxProfile Parameters: Return Type: String Objective: sets
     * firefox profile
     **//*

    // Set firefox profile
    public static FirefoxProfile FirefoxDriverProfile() throws Exception {
        String downloadPath = System.getProperty("user.dir").toLowerCase() + "\\testdata\\";
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", downloadPath);
        profile.setPreference("browser.helperApps.neverAsk.openFile",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.closeWhenDone", false);
        return profile;
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: closeBrowser Parameters: null Return Type: String Objective:
     * Public method to close browser by quitting the driver & driverFlaG refers to
     * second driver
     *
     * @throws InterruptedException
     **//*

    public String CloseBrowser(String driverFlag) throws InterruptedException {

        try {
            if (driverFlag != "") {
                // driver.close();
                driver1.quit();
                String path = System.getProperty("user.dir") + "\\Kill.bat";
                ProcessBuilder pb = new ProcessBuilder(path);
                Process p = pb.start();
            } else
                // driver.close();
                driver.quit();
            String path = System.getProperty("user.dir") + "\\Kill.bat";
            ProcessBuilder pb = new ProcessBuilder(path);
            Process p = pb.start();
            // TestRunner.test.log(Status.PASS, " Browser is closed successfully. ");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Pass";

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: Launch Parameters: URL Return Type: String Objective: Public
     * method to Launch the requested 'URL' by the user
     **//*

    public String Launch(String url) {

        try {
            // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(url);
            driver.manage().window().maximize();
            waitUntilAngularReady();
            // Thread.sleep(4000);
        } catch (Exception ex) {
            System.out.println(ex);
            // TestRunner.test.log(Status.FAIL, "<b><I>"+url+"</b></I> launch failed. ");
            return "Fail";
        }

        String windowtitle = driver.getTitle();

        // if(browserType.equals("IE")){
        // driver.navigate().to("javascript:document.getElementById('overridelink').click()");
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // }

        if (windowtitle != null) {
            // TestRunner.test.log(Status.PASS, "<b><I>"+url+"</b></I> launch successful.
            // ");
            return "Pass";
        } else {
            // TestRunner.test.log(Status.FAIL, "<b><I>"+url+"</b></I> launch unsuccessful.
            // ");
            return "Fail";
        }

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: s Parameters: timeInSec ,condition, elementPath Return Type:
     * String Objective: Public function used to wait for the actions dynamically
     *
     * @return
     **//*

    public String dynamicWaiting(String timeInSec, String condition, String elementPath) {
        try {
            WebDriverWait w = new WebDriverWait(driver, Integer.parseInt(timeInSec));
            // waitAll(100);
            switch (condition) {
                case "visible":
                    System.out.println("waiting for element to be visible.....");
                    if (driver.findElements(By.xpath(elementPath)).size() != 0)
                        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
                    else
                        w.until(ExpectedConditions
                                .presenceOfElementLocated(By.xpath(PropertiesUtil.getProperty(elementPath))));
                    break;

                case "click":
                    System.out.println("waiting for element to be clickable.....");
                    if (driver.findElements(By.xpath(elementPath)).size() != 0)
                        w.until(ExpectedConditions.elementToBeClickable(By.xpath(elementPath)));
                    else
                        w.until(ExpectedConditions.elementToBeClickable(By.xpath(PropertiesUtil.getProperty(elementPath))));
                    break;

                case "enable":
                    System.out.println("Waiting for the element to be enabled.....");
                    if (driver.findElements(By.xpath(elementPath)).size() != 0)
                        w.until(ExpectedConditions.numberOfElementsToBe(By.xpath(PropertiesUtil.getProperty(elementPath)),
                                1));
                    // w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(PropertiesUtil.getProperty(elementPath)))));
                case "eenable":
                    System.out.println("Waiting for the element to be enabled.....");
                    if (driver.findElements(By.xpath(elementPath)).size() != 0)
                        w.until(ExpectedConditions.numberOfElementsToBe(By.xpath(PropertiesUtil.getProperty(elementPath)),
                                0));

                case "disable":
                    if (!driver.findElement(By.xpath(elementPath)).isEnabled())
                        System.out.println("waiting for element to be disabled.....");
                    else
                        System.out.println(elementPath + " element is enabled....");
                    break;

            }
            return "Pass";
        } catch (Exception e) {
            System.out.println("Need Handling!!!!!");
            return "Fail";
        }

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: Login Parameters: Username, Password Return Type: String
     * Objective: Perform login operation [Also, (1) Handle JavaScript Alert for
     * invalid login scenarios. (2) Handle Sign In intermittent issue with the new
     * UI]
     **//*

    public String Login(String uid, String pwd) {
        // String a = null;
        if (uid.isEmpty() || pwd.isEmpty())
            return "Fail";
        try {
            String LoginURL = driver.getCurrentUrl();
            System.out.println("url--> " + LoginURL);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).clear();
            driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).sendKeys("");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).clear();
            driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).sendKeys(uid);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btnSubmit"))).click();
            //System.out.println(driver.findElements(By.xpath(PropertiesUtil.getProperty("password"))).size());
            if (driver.findElements(By.xpath(PropertiesUtil.getProperty("password"))).size() > 0) {
                // waitAll(100);
                // dynamicWaiting("10", "visible", PropertiesUtil.getProperty("password"));
                driver.findElement(By.xpath(PropertiesUtil.getProperty("password"))).clear();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("password"))).sendKeys(pwd);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("btnSubmit"))).click();
                waitUntilAngularReady();

                if(driver.findElements(By.xpath(PropertiesUtil.getProperty("msgInvalidLogin"))).size() > 0)
                    return "Fail";
            } else {return "Fail";}
            // String NewURL = driver.getCurrentUrl();
            // System.out.println("New URL now==> "+NewURL);
            // waitAll(100);
//			String a = VerifyExistence("IPPlatformLogo");
//			String b = VerifyExistence("AccessManagementLogo");
//			//System.out.println(a);
//
//			if (a.equalsIgnoreCase("Pass")||b.equalsIgnoreCase("Pass"))
//			{
            System.out.println("Logged in Successfully");
            // TestRunner.test.log(Status.PASS, "<b><I>Login Sucessfull</b></I>");
            return "Pass";
//			}
//
//			// TestRunner.test.log(Status.FAIL, "<b><I>Login UnSucessfull</b></I>");
//			return "Fail";
        } catch (Exception ex) {
            // TestRunner.test.log(Status.FAIL, "<b><I>Exception thrown</b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: Logout Parameters: null Return Type: String Objective: Logs out
     * the user from the application.
     **//*

    public String Logout() {
        try {
            
             * if (handle!="") driver=driver1; else driver=driver;
             
            if (driver.findElements(By.xpath(PropertiesUtil.getProperty("logout_btn"))).size() > 0) {
                Thread.sleep(1000);
                // waitAll(100);
                SingleClick("logout_btn");
//				driver.findElement(By.xpath(PropertiesUtil.getProperty("logout_btn"))).click();
                // waitAll(100);
                Thread.sleep(3000);
                SingleClick("confirm_logout");
                // driver.findElement(By.xpath(PropertiesUtil.getProperty("confirm_logout"))).click();
            } else if (driver.findElements(By.xpath(PropertiesUtil.getProperty("lnk_user"))).size() > 0) {
//				driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_user"))).click();
                SingleClick("lnk_user");
                SingleClick("singOut");
                // waitAll(100);
//				driver.findElement(By.xpath(PropertiesUtil.getProperty("singOut"))).click();
            }
            return "Pass";
        } catch (Exception ex) {
            System.out.println(ex);
            // TestRunner.test.log(Status.FAIL, "<b><I> Unsuccessful Logout</b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ..............................0......................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: JavascriptAlert Parameters: Operation Return Type: String
     * Objective: Function to accept or dismiss java script alert thrown
     **//*

    public String JavascriptAlert(String Operation) throws Throwable {
        try {
            Alert javascriptAlert = driver.switchTo().alert();
            System.out.println(javascriptAlert.getText()); // Get text on alert box

            if (Operation.equalsIgnoreCase("Accept")) {
                javascriptAlert.accept();
            } else if (Operation.equalsIgnoreCase("Dismiss")) {
                javascriptAlert.dismiss();
            } else {
                // TestRunner.test.log(Status.FAIL, "<b><I> Alertbox not found </b></I>");
                return "Fail";
            }

            Thread.sleep(1000);

            // TestRunner.test.log(Status.PASS, "<b><I> Alertbox found </b></I>");
            return "Pass";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL, "<b><I> Alertbox not found </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: KillDriver Parameters: Return Type: String Objective:
     * Kills/Terminates driver instances
     **//*

    public String KillDriver() throws InterruptedException {
        // Runtime rt = Runtime.getRuntime();
        try {
            String path = System.getProperty("user.dir") + "\\Kill.bat";
            ProcessBuilder pb = new ProcessBuilder(path);
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

        return "Pass";
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: EmailReport Parameters: null Return Type: String Objective:
     * Called in the end of the script to send an email report to the desired
     * recipients.
     **//*

    public String EmailReport() throws IOException {

        // Recipient's email ID needs to be mentioned.
        String to = "prathore@cpaglobal.com";
        String cc = "prathore@cpaglobal.com";
        // String cc1 = "XMorera@searchtechnologies.com";
        // String cc2 = "EQuiros@searchtechnologies.com";
        // String cc3 = "pmadriz@searchtechnologies.com";
        // String cc4 = "kmartin@searchtechnologies.com";
        // Sender's email ID needs to be mentioned
        String from = "cpa.discover@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";
        // String host = "ind-domvp03";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            
             * message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc1));
             *
             * message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc2));
             *
             * message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc3));
             * message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc4));
             
            // Set Subject: header field
            message.setSubject("Automation Test Results");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("Attached are the execution results of automated functional tests on " + browserType
                    + ".    \n\n\n");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = System.getProperty("user.dir") + "\\src\\cpa\\qa\\xls\\Test Report.xlsx";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Test Report.xlsx");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Automation run completed successfully....");
            // TestRunner.test.log(Status.FAIL, "<b><I> Report mailed successfully
            // </b></I>");
            return "Pass";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            // TestRunner.test.log(Status.FAIL, "<b><I> Report not mailed </b></I>");
            return "Fail";

        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: ValidateZeroQuickFindResult Parameters: LabelHeader,Element,Text
     * Return Type: String Objective: Validate quick find result label, for ex:
     * Results for "Text" LoadingElement
     **//*
    public String ValidateZeroQuickFindResult(String LabelHeader, String Element, String Text) {
        String flag = "Fail";
        String strVar = driver.findElement(By.xpath(PropertiesUtil.getProperty(LabelHeader))).getText();
        String str = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getText();
        String strVar1 = "Results for " + Text + " (0 results)";

        try {
            if (strVar.equalsIgnoreCase(strVar1) && str.equalsIgnoreCase("No results found")) {
                System.out.println("The mention " + strVar1 + " and the label " + str
                        + " is displayed instead of the grid of results");
                // TestRunner.test.log(Status.PASS, "The mention "+"<b><I>"+strVar1+"</b></I>"+"
                // and the label "+"<b><I>"+str+"</b></I>"+" is displayed instead of the grid of
                // results");
                flag = "Pass";
            } else {
                System.out.println("The mention " + strVar1 + " and the label " + str
                        + " is NOT displayed in the grid of results");
                // TestRunner.test.log(Status.FAIL, "The mention "+"<b><I>"+strVar1+"</b></I>"+"
                // and the label "+"<b><I>"+str+"</b></I>"+" is NOT displayed in the grid of
                // results");
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyExistence Parameters: Element Return Type: String
     * Objective: Verify existence of given element
     **//*

    public String VerifyExistence(String Element) {
        String status;
        try {
            // // waitAll(100);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PropertiesUtil.getProperty(Element))));
//            boolean st = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isDisplayed();
            boolean st = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isEnabled();     //Added by Avanish
            // String st=
            // driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getText();
            // System.out.println("Text value of the field is : " +st);
            System.out.println("Display value of the field  is : " + st);
            if (!st) {
                status = "Fail";
                // Testrunner.test.log(Status.FAIL, "<b><I>"+Element+"</b></I> does not exist.
                // ");
                System.out.println(Element + " does not exist. ");
            } else {
//                highLightElement("yellow", "red", Element);
                status = "Pass";
                // Testrunner.test.log(Status.PASS, "<b><I>"+Element+"</b></I> exists. ");
                System.out.println(Element + " is present on the page. ");
            }

        } catch (Exception e) {
            // e.printStackTrace();
            status = "Fail";
            // Testrunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
        }

        return status;
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyData Parameters: Name Return Type: String Objective:
     * Search the presence of desired publication number in result page.
     **//*

    public String VerifyData(String Elementpath, String Name, String driverFlag) {
        try {
            tempDriver = driver;

            
             * if(driverFlag!="") { driver=driver1; WebDriverWait waiter = new
             * WebDriverWait(driver, 20); WebElement element =
             * waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
             * PropertiesUtil.getProperty(Elementpath)))); }
             

            WebDriverWait waiter = new WebDriverWait(driver, 30);
            // WebElement element =
            // waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PropertiesUtil.getProperty(Elementpath))));
            WebElement element = waiter.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesUtil.getProperty(Elementpath))));

            // Thread.sleep(1000);

            Properties properties = System.getProperties();
            String fld = properties.getProperty(Name);
            String val;
            System.out.println("The ui value of the element is  : "
                    + driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute("value"));
            System.out.println("The ui text  of the element is  : "
                    + driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getText());

            if (fld != null) {
                val = fld;
            } else {
                val = Name.toString();
            }

            System.out.println("The comparing text value is  : " + val);

            // condition for empty value in the argument
            if (val.isEmpty()) {

                if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute("value") != null
                        && driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getText() != null) {
                    if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute("value")
                            .isEmpty()
                            && driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getText()
                            .isEmpty()) {
                        System.out.println("Match found between element value and comparing value as both are blank!");
                        driver = (EventFiringWebDriver) tempDriver;
                        // TestRunner.test.log(Status.PASS, "Match found between element value and
                        // comparing value as both are blank");
                        return "Pass";
                    }
                }

                if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getText() != null) {
                    if (!driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getText().isEmpty()) {
                        System.out.println("NO Match found between element Text and comparing value !");
                        driver = (EventFiringWebDriver) tempDriver;
                        // TestRunner.test.log(Status.FAIL, "NO Match found between element Text and
                        // comparing value");
                        return "Fail";
                    }
                }

                if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath)))
                        .getAttribute("value") != null) {
                    if (!driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute("value")
                            .isEmpty()) {
                        System.out.println("NO Match found between element value and comparing value !");
                        driver = (EventFiringWebDriver) tempDriver;
                        // TestRunner.test.log(Status.FAIL, "NO Match found between element value and
                        // comparing value");
                        return "Fail";
                    }
                }

            }

            // condition for not null value or text
            if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute("value") != null) {
                if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute("value")
                        .toString().contains(val)) {
                    System.out.println("Match found between element value and comparing value !");
                    driver = (EventFiringWebDriver) tempDriver;
                    // TestRunner.test.log(Status.PASS, "Match found between element value and
                    // comparing value");
                    return "Pass";
                } else if (val.equalsIgnoreCase("Results for")) {
                    System.out.println(
                            "Results for " + driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath)))
                                    .getAttribute("value").toString() + " is present on ui");
                    driver = (EventFiringWebDriver) tempDriver;
                    // TestRunner.test.log(Status.PASS, "Results for
                    // "+"<b><I>"+driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute("value").toString()+"</b></I>"+"
                    // is present on ui");
                    return "Pass";
                }
            }

            if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getText() != null) {
                if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getText().toString()
                        .contains(val)) {
                    System.out.println("Match found between element text and comparing value !");
                    driver = (EventFiringWebDriver) tempDriver;
                    // TestRunner.test.log(Status.PASS, "Match found between element text and
                    // comparing value");
                    return "Pass";
                }
            }

            driver = (EventFiringWebDriver) tempDriver;
        } catch (Exception e) {
            e.printStackTrace();
            driver = (EventFiringWebDriver) tempDriver;
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

        return "Fail";
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyNotifications Parameters: Name Return Type: String
     * Objective: Search the presence of desired notification on a result page.
     **//*

    public String VerifyNotifications(String Elementpath, String Name) {
        // System.out.println("Verify notifications with two arguments ie. location and
        // message");
        Properties properties = System.getProperties();
        String fld = properties.getProperty(Name);

        if (fld != null)

            Name = fld;

        else

            Name = Name.toString();

        System.out.println("Comparing value is " + Name);

        try {
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PropertiesUtil.getProperty(Elementpath))));
            // List<WebElement>
            // element=driver.findElements(By.xpath(PropertiesUtil.getProperty(Elementpath)));
            List fld1 = new ArrayList();
            System.out.println(
                    "Element size is " + driver.findElements(By.xpath(PropertiesUtil.getProperty(Elementpath))).size());

            for (int i = 0; i < driver.findElements(By.xpath(PropertiesUtil.getProperty(Elementpath))).size(); i++) {
                System.out.println("The notification message is  : "
                        + driver.findElements(By.xpath(PropertiesUtil.getProperty(Elementpath))).get(i).getText());
                fld1.add(driver.findElements(By.xpath(PropertiesUtil.getProperty(Elementpath))).get(i).getText());
            }

            if (fld1.size() > 0) {
                for (int i = 0; i < fld1.size(); i++) {
                    if (fld1.get(i).toString().contains(Name)) {
                        System.out
                                .println("Match found between UI notification and comparing notification from excel !");
                        // TestRunner.test.log(Status.PASS, "<b><I> Match found between UI notification
                        // and comparing notification from excel </b></I>");
                        return "Pass";
                    } else {
                        System.out.println(
                                " Match NOT found between UI notification and comparing notification from excel");
                        // TestRunner.test.log(Status.FAIL, "<b><I> Match NOT found between UI
                        // notification and comparing notification from excel </b></I>");
                        return "Fail";
                    }
                }
            } else {
                System.out.println("Verification message not displayed");
                // TestRunner.test.log(Status.FAIL, "<b><I> Verification message not displayed
                // </b></I>");
                return "Fail";
            }
            return "Fail";
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.PASS,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyDataProperty Parameters: Elementpath,Name,Property Return
     * Type: String Objective: Search the presence of desired publication number in
     * result page.
     **//*

    public String VerifyDataProperty(String Elementpath, String Name, String Property) {
        System.out.println("Verify data with three arguments ");
        try {
            Thread.sleep(1000);
            Properties properties = System.getProperties();
            String fld;

            if (properties.getProperty(Name) != null) {
                fld = properties.getProperty(Name);
            } else {
                fld = Name;
            }

            System.out.println("The passed value is " + fld);
            String fld1;
            System.out.println("The title  of the element is  : " + driver
                    .findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute(Property.toString()));
            fld1 = driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath)))
                    .getAttribute(Property.toString());

            // Updated By Nazim
            if (fld1 == null)
                fld1 = "NULL";
            // End of update

            if (fld1.toString().contains(fld)) {
                System.out.println("Match found between element value and comparing value !");
                // TestRunner.test.log(Status.PASS, "<b><I>"+fld+"</b></I> does exist, Match
                // found between element value and comparing value ");
                return "Pass";
            } else {
                // TestRunner.test.log(Status.PASS, "<b><I>"+fld+"</b></I> does NOT exist, Match
                // is not found between element value and comparing value ");
                return "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyProperty Parameters: Elementpath,property Return Type:
     * String Objective: Search the presence of desired publication number in result
     * page.
     **//*

    public String VerifyProperty(String Elementpath, String property) {
        try {
            Thread.sleep(1500);
            Properties properties = System.getProperties();
            String fld = properties.getProperty(property);
            String val;
            System.out.println("The ui value of the element is  : "
                    + driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute(property));

            if (fld != null) {
                val = fld;
            } else {
                val = property.toString();
            }

            System.out.println("The comparing text value is  : " + val);

            if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute(property) == null) {
                System.out.println("Match not found between element value and comparing value !");
                // TestRunner.test.log(Status.FAIL,
                // "<b><I>"+driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute(property)+"</b></I>
                // is null. ");
                return "Fail";
            } else if (Boolean.parseBoolean(driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath)))
                    .getAttribute(property)) == true) {
                System.out.println("Match found between element value and comparing value !");
                // TestRunner.test.log(Status.PASS,
                // "<b><I>"+driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute(property)+"</b></I>
                // is matched. ");
                return "Pass";
            } else {
                System.out.println("Match not found between element value and comparing value !");
                // TestRunner.test.log(Status.FAIL, "<b><I> Match not found between element
                // value and comparing value</b></I>");
                return "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
        // return "Fail";
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: EnterText Parameters: FieldName, Text Return Type: String
     * Objective: Enter 'Text' into given 'Field Name'
     **//*

    public String EnterText(String FieldName, String Text) {
        waitAll(100);
        // String txt=Text;
        Properties properties = System.getProperties();
        String fld = properties.getProperty(Text);
        System.out.println(PropertiesUtil.getProperty(FieldName));
        System.out.println(fld);

        try {
            if (fld != null) {
                System.out.println("field is (when repository value not null): " + fld);
                driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).clear();
//                highLightElement("darkgray", "darkslategray", FieldName);
                driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).sendKeys(fld);
            } else if (fld == null) {
                System.out.println("field is (when  repository value is null):" + fld);
                System.out.println("text is " + Text);
                driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).clear();
//                highLightElement("darkgray", "darkslategray", FieldName);
                driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).sendKeys(Text);
            }
//			Thread.sleep(3000);
            // TestRunner.test.log(Status.PASS, "<b><I> Text is entered </b></I>");
            return "Pass";
        } catch (Exception e) {
            System.out.println(e);
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        // return "Fail";
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: IsElementLoaded Parameters: element2 Return Type: String
     * Objective: check if the element is loaded
     **//*
    public String isElementLoaded(String element2) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PropertiesUtil.getProperty(element2))));
            // TestRunner.test.log(Status.PASS, "<b><I> Element is loaded </b></I>");
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyDataInTable Parameters: TableBody,data Return Type: String
     * Objective: Verifies data present in table
     **//*

    public String VerifyDataInTable(String TableBody, String data) {
        Boolean flag = false;
        String[] val = data.split(";");
        String value;
        Properties properties = System.getProperties();
        String fld = properties.getProperty(val[1]);

        if (fld != null) {
            value = fld;
        } else {
            value = val[1].toString();
        }

        try {
            List<WebElement> list = driver.findElements(By.xpath(PropertiesUtil.getProperty(TableBody)));

            for (int i = 0; i <= list.size() - 1; i++) {
                if (list.get(i).getText().contains(val[0]) && list.get(i).getText().contains(value)) {
                    System.out.println("Row having " + val[0] + " and " + fld + " is found in the table");
                    flag = true;
                    break;
                }

            }

            if (val.length > 2) {
                driver.findElement(By.xpath("(//a[text()='" + value + "'])[1]")).click();
            }

            if (!flag) {
                System.out.println("Row having " + val[0] + " and " + fld + " is NOT found in the table");
                // TestRunner.test.log(Status.FAIL, "Row having "+"<b><I>"+val[0]+"</b></I>"+"
                // and "+"<b><I>"+fld+"</b></I>"+" is NOT found in the table");
                return "Fail";
            } else {
                // TestRunner.test.log(Status.PASS, "Row having "+"<b><I>"+val[0]+"</b></I>"+"
                // and "+"<b><I>"+fld+"</b></I>"+" is found in the table");
                return "Pass";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     *
     * Method Name: VerifySections Parameters: s,type Return Type: String Objective:
     * Verify sections present on the UI and in the list
     *
     **//*

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: ClearPickList Parameters: FieldLabel Return Type: String
     * Objective: Clears picklist
     **//*

    public String ClearPickList(String FieldLabel) {
        String Field = "";

        try {
            Field = "//label[contains(text(),'" + FieldLabel + "')]";
            System.out.println("Field name " + Field);

            if (driver.findElement(By.xpath(Field)).isDisplayed())
                ;
            {
                driver.findElement(By.xpath(Field + "//following::input[1]")).click();
                driver.findElement(By.xpath("(" + Field + "//following::span[contains(@class,'cpa-icon-times')])[1]"))
                        .click();
                // TestRunner.test.log(Status.PASS, "<b><I> Picklist cleared</b></I>");
                return "Pass";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     *
     * Method Name: SingleClick Parameters: Element,driverFlag Return Type: String
     * Objective: Clicks on given 'Element' if the element is displayed.
     *
     *
     **//*
//
//	public String SingleClick(String Element, String driverFlag)
//	{
//		try
//		{
//			if(driverFlag!="")
//			{
//				if (driver1.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isDisplayed() && driver.findElements(By.xpath(PropertiesUtil.getProperty(Element))).size()>0)
//				{
//					// waitAll(100);
//					//Thread.sleep(1000);
//					WebElement element=driver1.findElement(By.xpath(PropertiesUtil.getProperty(Element)));
//					JavascriptExecutor executor = (JavascriptExecutor)driver1;
//					executor.executeScript("arguments[0].click();", element);
//					//Thread.sleep(3000);
//				}
//				else
//				{
//					JavaScriptExecutor(Element,"click");
//				}
//			}
//			else
//			{
//				if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isDisplayed()&& driver.findElements(By.xpath(PropertiesUtil.getProperty(Element))).size()>0)
//				{
//					// waitAll(100);
//					//Thread.sleep(1000);
//					driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).click();
//					//Thread.sleep(3000);
//				}
//				else
//				{
//					JavaScriptExecutor(Element,"click");
//				}
//			}
//
//			// TestRunner.test.log(Status.PASS, "<b><I>"+Element+"</b></I> is displayed and clicked ");
//			// waitAll(100);
//			return "Pass";
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			// TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
//			return "Fail";
//		}
//	}

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: SingleClick Parameters: Element,driverFlag Return Type: String
     * Objective: Clicks on given 'Element' if the element is displayed.
     **//*

    public String SingleClick(String Element) {
        try {

            {
                if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isDisplayed()
//                driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isEnabled()
                        && driver.findElements(By.xpath(PropertiesUtil.getProperty(Element))).size() > 0) {
//                    log.info(driver.findElements(By.xpath(PropertiesUtil.getProperty(Element))).size());
                    waitUntilAngularReady();
//                    highLightElement("seagreen", "coral", Element);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).click();
                    waitUntilAngularReady();
                } else {
                    waitUntilAngularReady();
//                    highLightElement("seagreen", "coral", Element);
                    JavaScriptExecutor(Element, "click");
                    waitUntilAngularReady();
                }
            }

            // TestRunner.test.log(Status.PASS, "<b><I>"+Element+"</b></I> is displayed and
            // clicked ");
            // waitAll(100);
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    *//**
     * Method Name: NormalSingleClick Parameters: Element,driverFlag Return Type:
     * String Objective: Clicks on given 'Element' if the element is displayed.
     **//*

    public String HoverAndClick(String locator) {
        try {

            Actions action = new Actions(driver);
            WebElement element = driver.findElement(By.xpath(PropertiesUtil.getProperty(locator)));

            {
                if (element.isDisplayed()
                        && driver.findElements(By.xpath(PropertiesUtil.getProperty(locator))).size() > 0) {

                    waitUntilAngularReady();
                    action.moveToElement(element).click().perform();
                    waitUntilAngularReady();
                    return "Pass";

                } else {
                    return "Fail";
                }
            }

            // TestRunner.test.log(Status.PASS, "<b><I>"+Element+"</b></I> is displayed and
            // clicked ");
            // waitAll(100);

        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: Click Parameters: ElementName,Verificationpath,VerificationText
     * Return Type: String Objective: Click on given 'ElementName' and validate it
     * by confirming the 'Expected' text after the click.
     **//*

    public String Click(String ElementName, String Verificationpath, String VerificationText) {
        try { // System.out.println(VerificationText);
            driver.findElement(By.xpath(PropertiesUtil.getProperty(ElementName))).click();
            Thread.sleep(2000);

            if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Verificationpath))).isDisplayed())
                ;
            {
                String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty(Verificationpath))).getText();

                System.out.println("Text available on given verification path is : " + Text
                        + ", where as text supplied as input is : " + VerificationText);

                // String Text1 = StringUtils.deleteWhitespace(Text);
                if (Text.toString().contains(VerificationText.toString())) {
                    System.out.println(VerificationText + " : Text Found");

                    return "Pass";
                } else {
                    System.out.println(VerificationText + " : Text not found");
                    return "Fail";
                }
            }
        } catch (NoSuchElementException ex) {
            String Chk = IsPopUpPresent();
            if (Chk == "Pass") {
                System.out.println("Pop Up Present");
                return "Pass";
            } else if (Chk == "Fail") {
                return "Fail";
            } else {
                System.out.println(
                        "The verification is neither a webpage nor a web popup...Please check this scenario ! ");
                return "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: SetCheckbox Parameters: ChkBoxName, State Return Type: String
     * Objective: Check/Uncheck the selected 'Checkbox' to given 'State (On/Off)'
     **//*

    public String SetCheckBox(String ChkBoxName, String State) {
        try {
            System.out.println("Working with Chechbox: " + ChkBoxName);
            boolean chk = driver.findElement(By.xpath(PropertiesUtil.getProperty(ChkBoxName))).isSelected();
            // System.out.println("==>"+ chk);

            if (chk == true && State.equalsIgnoreCase("On")) {
                System.out.println(ChkBoxName + " chechbox is already set to " + State);
            } else if (chk == true && State.equalsIgnoreCase("Off")) {
                driver.findElement(By.xpath(PropertiesUtil.getProperty(ChkBoxName))).click();
            } else if (State.equalsIgnoreCase("On") && chk == false) {
                driver.findElement(By.xpath(PropertiesUtil.getProperty(ChkBoxName))).click();
            } else if (State.equalsIgnoreCase("Off") && chk == false) {
                System.out.println(ChkBoxName + " chechbox is already set to " + State);
            }

            // TestRunner.test.log(Status.PASS, "<b><I> checkbox is </b></I>"+State);
            return "Pass";
        } catch (Exception e) {
            // e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: ExtractDetails Parameters: Type, Element, Option Return : String
     * Type: String Objective: Extract the current value/state for the "Type" of
     * 'Element' and compares it to the desired 'Option' Eg: Type: Dropdown, Element
     * Name: AS_SearchAgainst_dd: Option: Current value of the dropdown
     **//*

    public String ExtractDetails(String Type, String Element, String Option) {
        try {
            switch (Type) {
                case "Dropdown": {
                    Select select = new Select(driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))));

                    WebElement option = select.getFirstSelectedOption();
                    System.out.println(option.getText());

                    if (Option.equals(option.getText())) {
                        System.out.println("Selected Option GetText:" + option.getText());
                        // TestRunner.test.log(Status.PASS,"<b><I> Selected Option GetText is
                        // "+option.getText()+"</b></I>");
                        return "Pass";
                    } else {
                        System.out.println("Extracted Value:" + option.getText());
                        System.out.println("Dropdown current value does not match with the provided Option");
                        // TestRunner.test.log(Status.FAIL,"<b><I>Dropdown current value does not match
                        // with the provided Option</b></I>");
                        return "Fail";
                    }
                }

                case "ChkBox": {
                    System.out.println("Checking for " + Element + " Checkbox's state");
                    boolean chk = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isSelected();

                    if ((chk == true) && (Option.equalsIgnoreCase("On"))) {
                        System.out.println(Element + "is Checked");
                        // TestRunner.test.log(Status.PASS,"<b><I>"+Element+ " is Checked</b></I>");
                        return "Pass";
                    } else if ((chk == false) && (Option.equalsIgnoreCase("Off"))) {
                        System.out.println(Element + "is Unchecked");
                        // TestRunner.test.log(Status.PASS,"<b><I>"+Element+ " is Unchecked</b></I>");
                        return "Pass";
                    } else {
                        System.out.println("Error Occured!");
                        // TestRunner.test.log(Status.FAIL,"<b><I>Error Occured</b></I>");
                        return "Fail";
                    }
                }

                case "radioBtn": {
                    System.out.println("Checking for " + Element + " Radiobtn's state");
                    boolean chk = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isSelected();

                    if ((chk == true) && (Option.equalsIgnoreCase("On"))) {
                        System.out.println(Element + "is Checked");
                        // TestRunner.test.log(Status.PASS,"<b><I>"+Element+ " is Checked</b></I>");
                        return "Pass";
                    } else if ((chk == false) && (Option.equalsIgnoreCase("Off"))) {
                        System.out.println(Element + "is Unchecked");
                        // TestRunner.test.log(Status.PASS,"<b><I>"+Element+ " is Unchecked</b></I>");
                        return "Pass";
                    } else {
                        System.out.println("Error Occured!");
                        // TestRunner.test.log(Status.FAIL,"<b><I>Error Occured</b></I>");
                        return "Fail";
                    }
                }
                default:
                    throw new IllegalArgumentException();

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: SetDropdown Parameters: DropdownName, Value Return Type: String
     * Objective: Select given 'Value' in 'Drop down'
     **//*

    public String SetDropdown(String DropdownName, String Option) {
        try {
            // waitAll(100);
            Select select = new Select(driver.findElement(By.xpath(PropertiesUtil.getProperty(DropdownName))));
            // waitAll(100);
            select.selectByVisibleText(Option);
            // waitAll(100);
            // TestRunner.test.log(Status.PASS,"<b><I>"+Option+"</b></I>"+ "is selected in
            // dropdown");
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: SetRadio Parameters: Radio Button Name: BtnName Return Type:
     * String Objective: Select radio button/ Set the desired radio button as 'On'
     **//*

    public String SetRadio(String BtnName) {
        try {
            // Thread.sleep(1000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty(BtnName))).click();
            // TestRunner.test.log(Status.PASS,"<b><I>"+BtnName+"</b></I>"+ "is selected");
            return "Pass";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: GetData Parameters: FieldName Return Type: String Objective:
     * Returns the data present in given 'FieldName'
     **//*

    public String GetData(String FieldName) {
        try {
            Thread.sleep(3000);

            if (driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).isDisplayed())
                ;
            {
                String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).getText();
                System.out.println("Total count: " + Text);
                String Text2 = StringUtils.substringBetween(Text, "of", "results").trim();
                // Scanner in = new Scanner(Text2).useDelimiter("[^0-9]+");
                int count = Integer.parseInt(Text2);
                System.out.println("Total count: " + count);

                if ((count != 0)
                        && (driver.findElements(By.xpath(PropertiesUtil.getProperty("AllPubnumbr"))).size() != 0)) {
                    // TestRunner.test.log(Status.PASS,"<b><I>Data is returned
                    // sucessfully</b></I>");
                    return "Pass";
                } else {
                    // TestRunner.test.log(Status.FAIL,"<b><I>Data is not returned</b></I>");
                    return "Fail";
                }

            }
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: Navigate Parameters: Name Return Type: String Objective:
     * Navigate user to desired page using Navigation menu.
     **//*

    public String Navigate(String Element1, String Element2) {
        try {
            try {
                if (driver.findElement(By.xpath("//*[@class='modal-header']")).isDisplayed()) {
                    driver.navigate().refresh();
                    Thread.sleep(500);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1))).click();
                    driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2))).click();
                    return "Pass";

                }
            } catch (NoSuchElementException ex) {
                driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1))).click();
                driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2))).click();
                Thread.sleep(350);
            }
            return "Pass";

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (driver.findElement(By.xpath(PropertiesUtil.getProperty("UserName"))).isDisplayed())
                    ;
                {
                    System.out.println("Application logged out abruptly, Forcing Login ");
                    // TestRunner.test.log(Status.PASS,"<b><I> Application logged out abruptly,
                    // Forcing Login </b></I>");
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("UserName"))).clear();
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("UserName")))
                            .sendKeys("cpa.discover@gmail.com");
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("Password"))).clear();
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("Password"))).sendKeys("Test@123");
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("Signin_btn"))).click();
                    Thread.sleep(1000);
                }
            } catch (Exception e1) {
                // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
                e1.printStackTrace();
            }
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyDate Parameters: FromDate, ToDate, Elementname Return
     * Type: String Objective: Verifies that Publication date, Application date and
     * priority date of all records on QRI falls within a specified date range or
     * not.
     **//*

    public String VerifyDate(String FromDate, String ToDate, String Elementname) throws Exception {
        try {
            // Thread.sleep(1000);
            Date From;
            Date To;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            // Convert 'From Date' to YYYY-MM-DD format

            if (FromDate.length() < 5) {
                From = df.parse((FromDate + "-01-01"));
                System.out.println(From + ".....");
            } else if (FromDate.length() < 8) {
                From = df.parse((FromDate + "-01"));
                System.out.println(From + ".....");
            } else {
                From = df.parse(FromDate);
            }
            // Convert 'ToDate' to YYYY-MM-DD format
            if (ToDate.length() < 5) {
                To = df.parse((ToDate + "-01-01"));
                System.out.println(From + "....." + To);
            } else if (ToDate.length() < 8) {
                To = df.parse((ToDate) + "-01");
                System.out.println(From + "....." + To);
            } else {
                To = df.parse(ToDate);
            }
            // Compare given date with patent dates displayed on QRI.
            // driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Expandall_lnk"))).click();

            List<WebElement> AllpubDate = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty(Elementname)));

            for (int i = 1; i < AllpubDate.size(); i++) {
                try {
                    Date ActualDate = df.parse(AllpubDate.get(i).getText());
                    System.out.println("Actual date " + ActualDate);
                    // if(ActualDate.compareTo(From)>=0 & ActualDate.compareTo(To)<=0 )
                    if ((ActualDate.after(From) || ActualDate.equals(From))
                            && (ActualDate.before(To) || ActualDate.equals(To))) {
                        System.out.println("Date verified :" + AllpubDate.get(i).getText());
                        // TestRunner.test.log(Status.PASS,"<b><I>Date verified
                        // :"+AllpubDate.get(i).getText()+"</b></I>");
                        return "Pass";
                    } else {
                        System.out.println("Failed for Date : " + AllpubDate.get(i).getText());
                        // TestRunner.test.log(Status.FAIL,"<b><I>Failed for Date :
                        // "+AllpubDate.get(i).getText()+"</b></I>");
                        return "Fail";
                    }
                } catch (Exception e) {
                    // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
                }
            }
            return "Fail";
        } catch (ParseException e) {
            // e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    *//**
     * Method Name: AddTile Parameters: eType,eName Return Type: String Objective:
     * Verify if the given Element is displayed on the web page or not.
     **//*

    public String AddTile(String URL) {
        try {
            Launch(URL);
            // waitAll(100);
            // SingleClick("btnAccept","");
            // waitAll(100);
            SingleClick("btnAddApp");
            // waitAll(100);
            SingleClick("btnAppLogo");
            // waitAll(100);
            SingleClick("btnInstall");
            // waitAll(100);
            SingleClick("btnAppLogo");
            // waitAll(100);
            SingleClick("btnOpen");
            // waitAll(100);
            return "Pass";
        } catch (Exception e) {
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: Custom Parameters: eType,eName Return Type: String Objective:
     * Verify if the given Element is displayed on the web page or not.
     **//*

    public String Custom(String eType, String eName) {
        System.out.println(eType + "," + eName);
        try {
            if (eType.contains("Link")) {
                driver.findElement(By.linkText(eName)).click();
                Thread.sleep(3000);
                // TestRunner.test.log(Status.PASS,"<b><I>"+eName+"</b></I> is displayed");
                return "Pass";
            } else {
                // TestRunner.test.log(Status.FAIL,"<b><I>"+eName+"</b></I> is not displayed");
                return "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: IsEnabled Parameters: Element Return Type: String Objective:
     * Verify if the given Element is enabled on the web page or not.
     **//*

    public String IsEnabled(String Element) {
        try {
            if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isEnabled()) {
                System.out.println(Element + " is enabled");
                // TestRunner.test.log(Status.PASS,"<b><I>"+Element+"</b></I> is displayed");
                return "Pass";
            } else {
                // TestRunner.test.log(Status.FAIL,"<b><I>"+Element+"</b></I> is not
                // displayed");
                return "Fail";
            }
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: GetText Parameters: Element, fieldName Return Type: String
     * Objective: return pass if element text matches with the expected text.
     **//*

    public String GetText(String Element, String fieldName) {
        try {
            Properties properties = System.getProperties();
            String innerHTML = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element)))
                    .getAttribute("innerHTML");
            String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getText();
            String Value = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getAttribute("value");

            System.out.println("Field Name to carry: " + fieldName);
            System.out.println("Value found : " + Value);
            System.out.println("Text found : " + Text);
            System.out.println("InnerHTML found : " + innerHTML);
            boolean chk = Text.trim().isEmpty();
            // String chk1 = "null";
            boolean chk2 = innerHTML.trim().isEmpty();

            if ((chk == false)) {
                System.out.println(" The Text of " + fieldName + " is " + Text);
                // fieldName=Text;
                properties.setProperty(fieldName, Text.trim());
                System.out.println("Exit Text " + properties.getProperty(fieldName));
                return "Pass";
            } else if ((Value != null)) {
                System.out.println(" The text of " + fieldName + " is " + Value);
                // fieldName=Value;
                properties.setProperty(fieldName, Value.trim());
                System.out.println("Exit Text " + properties.getProperty(fieldName));
                return "Pass";
            } else if ((chk2 == false)) {
                System.out.println(" The innerHTML of " + fieldName + " is " + innerHTML);
                // fieldName=innerHTML;
                properties.setProperty(fieldName, innerHTML.trim());
                System.out.println("Exit Text " + properties.getProperty(fieldName));
                return "Pass";
            } else {
                // TestRunner.test.log(Status.FAIL,"<b><I> Text is not present </b></I>");
                return "Fail";
            }
        } catch (Exception e) {
            // e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";

        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: MatchCount Parameters: Preference, Value,Element Return Type:
     * String Objective: Matches the actual and expected result count.
     **//*

    public String MatchCount(String Preference, String Value, String Element) {
        try {
            switch (Preference) {
                case "Result": {
                    Thread.sleep(3000);
                    String ResultLine = driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Results_lbl")))
                            .getText();
                    // TBD: Use of ExtractString function:
                    String Total = ResultLine.substring(10, 13);
                    System.out.println("Result Per Page dropdown is set to: " + Value + " records");

                    if (Total.contains(Value)) {
                        System.out.println("Result count displayed: " + Total);
                        return "Pass";
                    }
                    break;
                }
                case "PRDCount": {
                    Thread.sleep(3000);
                    String Count = ExtractString("PRD_RecordCount", 7, 11);
                    if (Count.contains(Value)) {
                        System.out.println("Patent record displayed: " + Count);
                        return "Pass";
                    } else {
                        System.out.println("Error Occured!");
                    }
                    break;
                }
                case "List": {
                    Thread.sleep(3000);
                    List<WebElement> Ranks = driver.findElements(By.xpath(PropertiesUtil.getProperty("Ranks")));
                    System.out.println("size is " + Ranks.size());
                    int val = Integer.parseInt(Value);
                    if (Ranks.size() == val) {
                        return "Pass";
                    } else {
                        return "Fail";
                    }
                }
                case "CountRow": {
                    List<WebElement> webElements = driver
                            .findElements(By.xpath(PropertiesUtil.getProperty(Element)));
                    System.out.println("size is " + webElements.size());
                    int val = Integer.parseInt(Value);
                    if (webElements.size() == val) {
                        return "Pass";
                    } else {
                        return "Fail";
                    }
                }
                default:
                    throw new IllegalArgumentException();

            }
            return "Pass";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: ExtractString Parameters: Element,Start,End Return Type: String
     * Objective: Returns the substring from the Element's text by given Start & End
     * index
     **//*

    private String ExtractString(String Element, int Start, int End) {
        try {
            String ExtractedString = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getText();
            String Total = ExtractedString.substring(Start, End);
            // TestRunner.test.log(Status.PASS,"<b><I>"+Total+"</b></I> is extracted");
            return Total;
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: IsPopUpPresent Parameters: Return Type: String Objective: verify
     * & deal with pop up (if present)
     **//*

    private String IsPopUpPresent() {
        try {
            String Check = VerifyExistence(PropertiesUtil.getProperty("Modal_Dialog"));
            if (Check == "Pass") {
                // WebDriver popup = webDriver.switchTo().window(handle);
                String parentWindowHandle = driver.getWindowHandle();
                WebDriver popup = null;
                Iterator<String> windowIterator = (Iterator<String>) driver.getWindowHandles();
                while (windowIterator.hasNext()) {
                    String windowHandle = windowIterator.next();
                    popup = driver.switchTo().window(windowHandle);
                }
            } else if (Check == "Fail") {
                // TestRunner.test.log(Status.FAIL,"<b><I> Pop up is not present </b></I>");
                return "Fail";
            }
            // TestRunner.test.log(Status.PASS,"<b><I> Pop up is present </b></I>");
            return "Pass";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: DataNavigator Parameters: NavigatorName, FilterType,
     * NmbrofFilters Return Type: String Objective: Apply filters on provided data
     * navigator (eg keyword, country, assignee etc)
     **//*

    public String DataNavigator(String NavigatorName, String FilterType, String NmbrofFilters) throws Exception {

        try {
            Integer Initialcount = Resultcount(); // Gets initial count of number of records in QRI
            Integer TotalHitCount = 0;
            int Type;
            int Number = Integer.parseInt(NmbrofFilters);
            System.out.println("Initial count :" + Initialcount);
            if (FilterType.equalsIgnoreCase("Include")) {
                Type = 3;
            } else if (FilterType.equalsIgnoreCase("Exclude")) {
                Type = 4;
            } else {
                System.out.println("Invalid filter type ");
                return "Fail";
            }
            // System.out.println("Prepare list");
            // Apply Desired Filters
            DynamicWait("RP_Navigator_UpdateNav_btn");
            List<WebElement> NmbrofRow = driver
                    .findElements(By.xpath((PropertiesUtil.getProperty(NavigatorName)) + ("/tbody[*]")));
            ArrayList<Integer> RandomNumber = new ArrayList<Integer>();

            for (int j = 0; j < NmbrofRow.size() - 1; j++) {
                RandomNumber.add(j + 1);
            }
            Collections.shuffle(RandomNumber);
            // System.out.println("Prepare Randomnumber");
            for (int i = 0; i < Number; i++) {

                Thread.sleep(200);
                driver.findElement(By.xpath((PropertiesUtil.getProperty(NavigatorName))
                        + ("/tbody[" + RandomNumber.get(i) + "]/tr/td[" + Type + "]/input"))).click();
                // System.out.println(Number);
                String Text = driver.findElement(By.xpath(
                        (PropertiesUtil.getProperty(NavigatorName)) + ("/tbody[" + RandomNumber.get(i) + "]/tr/td[2]")))
                        .getText();
                int Hitcount = Integer.parseInt(Text);
                TotalHitCount = TotalHitCount + Hitcount;

            }

            System.out.println("Hitcount: " + TotalHitCount);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_NV_Apply_btn"))).click();

            // Dynamic wait till navigator get applied
            DynamicWait("RP_Navigator_UpdateNav_btn");
            Thread.sleep(2500);
            Integer B = Resultcount();
            Integer A = (Initialcount - TotalHitCount);
            System.out.println("Final Count :" + B + " (After applying " + FilterType + " Operation) ");

            if (Type == 3 && ((TotalHitCount <= B))) {
                return "Pass";
            } else if (Type == 4 && (B < Initialcount)) {
                return "Pass";
            } else
                return "Fail";

        } catch (NumberFormatException e) {

            e.printStackTrace();
            return "Fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: Resultcount Parameters: Return Type: String Objective: To get
     * record count on QRI.
     **//*

    private int Resultcount() {

        try {
            String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_ResultCount"))).getText();
            String Text2 = StringUtils.substringBetween(Text, "of", "results").trim();
            int count = Integer.parseInt(Text2);
            // System.out.println("Total count: "+ count);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: KeywordHighlight Parameters: ElementPath, ElementName,
     * Operation, NewName Return Type: String Objective: Perform operations like
     * "Delete", "Edit", "toggle switch" on desired keyword.
     **//*

    public String KeywordHighlight(String ElementPath, String ElementName, String Operation, String NewName) {

        try { // Thread.sleep(3000);
            List<WebElement> Allelements = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty(ElementPath)));

            for (int i = 0; i <= Allelements.size(); i++) {
                if (Allelements.get(i).getText().equalsIgnoreCase(ElementName)) {
                    i++;
                    switch (Operation) {
                        case "Delete": {
                            driver.findElement(
                                    By.xpath(".//*[@class='keyword-body container-fluid']/div[" + i + "]/div[3]/a/i"))
                                    .click();
                            System.out.println("Keyword deleted");
                            // TestRunner.test.log(Status.PASS,"<b><I> Keyword deleted </b></I>");
                            return "Pass";
                        }
                        case "Switch": {
                            driver.findElement(
                                    By.xpath(".//*[@class='keyword-body container-fluid']/div[" + i + "]/div[1]/a/span[1]"))
                                    .click();
                            System.out.println("Switch toggled   ");
                            // TestRunner.test.log(Status.PASS,"<b><I> Switch toggled </b></I>");
                            return "Pass";
                        }
                        case "Edit": {
                            // Functionality not implemented yet, Hence function for edit can be modified
                            // once it is implemented.
                        }
                    }
                }

            }
            System.out.println("Match Failed ");
            // TestRunner.test.log(Status.FAIL,"<b><I> Match Failed </b></I>");
            return "Fail";
        } catch (Exception e) {
            // e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyHighlighting Parameters: Color, Keyword, Location Return
     * Type: String Objective: Function verify whether the keyword is highlighted
     * with applied colour on QRI or keywords column itself.
     **//*

    public String VerifyHighlighting(String Color, String Keyword, String Location) {

        try {
            String background_color = null;
            switch (Color) {
                case "Orange": {
                    Color = "E78D3B";
                    background_color = "rgb(231, 141, 59)";
                    break;
                }
                case "Pink": {
                    Color = "E47A7B";
                    background_color = "rgb(228, 122, 123)";
                    break;
                }
                case "Blue": {
                    Color = "4F8BC0";
                    background_color = "rgb(79, 139, 192)";
                    break;
                }
                case "Green": {
                    Color = "1A8B54";
                    background_color = "rgb(26, 139, 84)";
                    break;
                }
                case "Yellow": {
                    Color = "E8E24B";
                    background_color = "rgb(232, 226, 75)";
                    break;
                }
                case "Brown": {
                    Color = "8B522D";
                    background_color = "rgb(139, 82, 45)";
                    break;
                }
                case "Grey": {
                    Color = "A4A4A4";
                    background_color = "rgb(164, 164, 164)";
                    break;
                }
            }

            boolean chk;
            if (Location.contains("ResultList")) {
                chk = driver
                        .findElement(By.xpath(
                                ".//em[@style='background-color: #" + Color + ";' and text()='" + Keyword + "']"))
                        .isDisplayed();
                System.out.println("On Result List :" + chk);
            } else if (Location.contains("ResultTools")) {
                System.out.println(
                        ".//span[@style='background-color: " + background_color + ";' and text()='" + Keyword + "']");
                chk = driver.findElement(By.xpath(
                        ".//span[@style='background-color: " + background_color + ";' and text()='" + Keyword + "']"))
                        .isDisplayed();

                // chk =
                // driver.findElement(By.xpath(".//*[@title='#"+Color+"']/span[text()='"+Keyword+"']")).isDisplayed();
                System.out.println("On result tools : " + chk);
            } else {
                chk = false;
            }

            if (chk == true) {
                // TestRunner.test.log(Status.PASS,"<b><I> Higlighting is present </b></I>");
                return "Pass";
            } else {
                // TestRunner.test.log(Status.FAIL,"<b><I> Higlighting is NOT present
                // </b></I>");
            }
            return "Fail";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: SelectFolder Parameters: RelativePath, FolderName Return Type:
     * String Objective: Click on the desired folder from folder explorer
     **//*

    public String SelectFolder(String RelativePath, String FolderName) {

        try {
            try {
                if (driver.findElement(By.xpath(".//*[@class='branch ng-scope active'][2]/span[1]/i[1]"))
                        .isDisplayed()) {
                    driver.findElement(By.xpath(".//*[@class='branch ng-scope active'][2]/span[1]/i[1]")).click();
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
            }

            // Expand All folders
            List<WebElement> TotalArrow = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty("PF_ExpandAllFolders")));
            for (int i = 0; i < TotalArrow.size(); i++) {
                if (TotalArrow.get(i).isDisplayed() == true) {
                    TotalArrow.get(i).click();
                    // System.out.println("done arrow");
                }
            }

            // Select the desired folder

            List<WebElement> TotalFolders = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty(RelativePath)));

            for (int i = 0; i <= TotalFolders.size(); i++) { // System.out.println(TotalFolders.get(i).getText());
                if (TotalFolders.get(i).getText().equalsIgnoreCase(FolderName)) {
                    String NameOfFolder = TotalFolders.get(i).getText().trim();
                    System.out.println("Folder found: " + NameOfFolder);
                    TotalFolders.get(i).click();
                    Thread.sleep(3000);
                    return "Pass";
                }
            }

            System.out.println("Folder not found");
            return "Fail";
        } catch (Exception e) {
            // e.printStackTrace();
            return "Fail";
        }

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: DismissErrors Parameters: Return Type: String Objective: To
     * dismiss all errors displayed at top of the page.
     **//*

    public String DismissErrors() {
        try {
            boolean chk = driver.findElement(By.xpath(PropertiesUtil.getProperty("allErrors"))).isDisplayed();
            if (chk == true) {
                List<WebElement> Errors = driver
                        .findElements(By.xpath(PropertiesUtil.getProperty("allErrors")));
                // System.out.println("Errors: "+Errors.size());
                for (int i = 0; i <= Errors.size() - 1; i++) {
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("Error"))).click();
                }
                return "Pass";
            } else if (chk == false) {
                System.out.println("No Errors displayed at top of the page");
                // TestRunner.test.log(Status.PASS,"<b><I> No Errors displayed at top of the
                // page </b></I>");
                return "Pass";
            }
            return "Pass";
        } catch (Exception e) {
            // e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: AlertOperation Parameters: ElementPath, ElementName,
     * OperationName Return Type: String Objective: Perform operations such as "Edit
     * alert" , "Turn on" and "Turn off" alert for given Alert name.
     **//*

    public String AlertOperation(String ElementPath, String ElementName, String OperationName) {
        try {
            // Thread.sleep(3000);
            List<WebElement> Allelements = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty(ElementPath)));

            for (int i = 0; i <= Allelements.size(); i++) {
                if (Allelements.get(i).getText().equalsIgnoreCase(ElementName)) {
                    System.out.println("Alert name is : " + (Allelements.get(i).getText()));
                    i++;

                    switch (OperationName) {
                        case "Edit": {
                            driver.findElement(By.xpath(".//*[@id='wrap-inner']/div[2]/div[2]/form/div[2]/div/table/tbody["
                                    + i + "]/tr[1]/td[4]/div[1]/a")).click();
                            // driver.findElement(By.xpath("AM_EditStart_lnk"+i+"AM_EditEnd_lnk")).click();
                            System.out.println("Edit alert");
                            return "Pass";
                        }
                        case "TurnOn": {
                            // turn on path
                        }

                    }
                }
            }
            System.out.println("Match is Failed");
            return "Fail";
        } catch (Exception e) {
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: GetRichText Parameters: Element, DesiredText Return Type: String
     * Objective: Gets text
     **//*

    public String GetRichText(String Element, String DesiredText) {
        try {
            WebElement text = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element)));
            String textagain = text.getAttribute("value");
            System.out.println("Desired Text: " + DesiredText);
            System.out.println("Text Found: " + textagain);
            if (textagain.equalsIgnoreCase(DesiredText) || textagain.contains(DesiredText)) {
                // TestRunner.test.log(Status.PASS,"<b><I>"+textagain+"</b></I> is present");
                return "Pass";
            } else {
                // TestRunner.test.log(Status.PASS,"<b><I>"+textagain+"</b></I> is NOT
                // present");
                return "Fail";
            }
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyOptions Parameters: ElementPath, Options Return Type:
     * String Objective: Perform verification of all options present in Dropdown.
     **//*

    public String VerifyOptions(String ElementPath, String Options) {
        try {
            boolean chk1 = false, chk2 = true;
            List<WebElement> options = driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath)));
            List<String> desiredText = null;
            // Change

            if (Options.contains(";"))
                desiredText = Arrays.asList(Options.split("\\s*;\\s*"));
            else
                desiredText = Arrays.asList(Options.split("\\s*,\\s*"));

            for (int index = 0; index < desiredText.size(); index++) {
                if (desiredText.get(index).equals(options.get(index).getText())) {
                    System.out.println("Option Value: " + options.get(index).getText());
                    System.out.println("desiredText Value: " + desiredText.get(index));
                    chk1 = true;
                } else {
                    chk2 = false;
                }
            }

            if (chk1 && chk2) {
                // TestRunner.test.log(Status.PASS,"<b><I> Dropdown current value matches with
                // the provided Option </b></I>");
                return "Pass";
            }
            System.out.println("Dropdown current value does not match with the provided Option");
            // TestRunner.test.log(Status.FAIL,"<b><I> Dropdown current value matches with
            // the provided Option </b></I>");
            return "Fail";
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: VerifyDropDownOptions Parameters: ElementPath, DropdownOptions
     * Return Type: String Objective: Perform verification of all options present in
     * Dropdown.
     **//*

    public String VerifyDropDownOptions(String ElementPath, String dropdownOptions) {
        try {
            List<WebElement> options = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty(ElementPath) + "/option"));
            Iterator<WebElement> itr = options.iterator();
            List<String> elementTexts = new ArrayList<String>();
            while (itr.hasNext()) {
                elementTexts.add(itr.next().getText().trim());
            }
            System.out.println("Element Text Values: " + elementTexts);
            List<String> desiredText = Arrays.asList(dropdownOptions.split("\\s*,\\s*"));
            System.out.println("Desired Text Values: " + desiredText);

            if (elementTexts.containsAll(desiredText)) {
                // TestRunner.test.log(Status.PASS,"<b><I> Dropdown current value matches with
                // the provided Option </b></I>");
                return "Pass";
            } else {
                System.out.println("Dropdown current value does not match with the provided Option");
                // TestRunner.test.log(Status.FAIL,"<b><I> Dropdown current value does not
                // matches with the provided Option </b></I>");
                return "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: GetSearchBoxText Parameters: Element, DesiredText Return Type:
     * String Objective: return pass if element text matches with the expected text.
     **//*

    public String GetSearchBoxText(String Element, String DesiredText) {
        try {
            WebElement object = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element)));
            String searchtext = object.getAttribute("Value");
            System.out.println("Desired Text: " + DesiredText);
            System.out.println("Text Found: " + searchtext);
            boolean chk = searchtext.isEmpty();
            if ((chk == false) && (searchtext.contains(DesiredText))) {
                // TestRunner.test.log(Status.PASS,"<b><I> element text matches with the
                // expected text </b></I>");
                return "Pass";
            } else {
                // TestRunner.test.log(Status.FAIL,"<b><I> element text does not matches with
                // the expected text </b></I>");
                return "Fail";
            }
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: ListDropDownValues Parameters: ElementPath Return Type: String
     * Objective: Provides the list of all dropdown values
     **//*

    public String ListDropDownValues(String ElementPath) {
        try {
            WebElement options = (WebElement) driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath)));

            Select sel = new Select(options);
            List<WebElement> lista = sel.getOptions();
            System.out.println("List --> count of elements :" + lista.size());

            for (WebElement e : lista) {
                System.out.println(e.getText());
            }

            if (lista.size() != 0) {
                System.out.println("List --> count of elements :" + lista.size());
                // TestRunner.test.log(Status.PASS,"<b><I>List --> count of elements :
                // "+lista.size()+"</b></I>");
                return "Pass";
            } else {
                System.out.println("Dropdown does not contain any values");
                // TestRunner.test.log(Status.FAIL,"<b><I> Dropdown does not contain any values
                // </b></I>");
                return "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("List Dropdown exception");
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: matchElementCount Parameters: RelativePath, DesiredCount Return
     * Type: String Objective: Match row count of a table with the desired count
     **//*

    public String matchElementCount(String RelativePath, String DesiredCount) {

        try {
            List<WebElement> rows = driver.findElements(By.xpath(PropertiesUtil.getProperty(RelativePath)));
            int rowCount = rows.size();

            String ActualCount = Integer.toString(rowCount);
            String DesiredCountFinal = DesiredCount;
            System.out.println(ActualCount);
            System.out.println(DesiredCountFinal);
            if (ActualCount.equals(DesiredCountFinal)) {
                // TestRunner.test.log(Status.PASS,"<b><I>row count is matched</b></I>");
                return "Pass";
            } else {
                // TestRunner.test.log(Status.FAIL,"<b><I>row count is matched</b></I>");
                return "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    *//**
     * Method Name: copyPasteText Parameters: Text to copy, TextBoxElement Return
     * Type: String Objective: Perform copy and paste of text
     **//*

    public String copyPasteText(String TextBoxElement, String text) {
        try {
            driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).clear();
            driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).sendKeys(text);
            driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement)))
                    .sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.chord(Keys.CONTROL, "c"));
            driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).clear();
            WebElement TextBox = driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement)));
            Actions action = new Actions(driver);
            action.contextClick(TextBox).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
                    .sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
            return "Pass";
        } catch (Exception ex) {
            return "Fail";
        }
    }

    *//**
     * Method Name: MatchResults Parameters: Element1, Element2 Return Type: String
     * Objective: Perform Result match for records on QRI page and Export page for a
     * particular Search Keyword
     **//*

    public String MatchResults(String Element1, String Element2) {
        try {
            String ResultLine = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1))).getText();

            String Finalcount = ResultLine.replaceAll("\\D+", "");
            System.out.println("Result displayed for the Search Keyword: " + Finalcount + " records");
            String ResultToMatch = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2))).getText();
            System.out.println("Records exported: " + ResultToMatch + " records");

            if (Finalcount.contains(ResultToMatch)) {
                System.out.println("Result count displayed: " + ResultToMatch + "records");
                // TestRunner.test.log(Status.PASS,"Result count displayed:
                // <b><I>"+ResultToMatch+"</b></I> records");
                return "Pass";
            } else {
                System.out.println("Error Occured!");
                // TestRunner.test.log(Status.FAIL,"Result count displayed:
                // <b><I>"+ResultToMatch+"</b></I> records");
                return "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    *//**
     * Method Name: CalenderWidgetVerification Parameters: M_Y_Element,D_Element,
     * TextBoxPath, Date, Action Return Type: String Objective: Perform all
     * CalenderWidgetVerification.
     **//*

    public String CalenderWidgetVerification(String M_Y_Element, String D_Element, String TextBoxPath, String Date,
                                             String Action) {
        try {
            WebElement Inputbox = driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxPath)));

            String date = Date.substring(8);
            String month = Date.substring(5, 7);
            String year = Date.substring(0, 4);

            switch (Action) {
                case "WidgetVerify": {
                    Inputbox.clear();
                    driver.findElement(By.xpath(".//*[@class='nav pull-right']/li[1]/a")).click();
                    Inputbox.sendKeys(Date);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException w) {
                    }
                    if (M_Y_Element.contains("From")) {
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_YearRange")))
                                .click();
                        Thread.sleep(1000);
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_Month")))
                                .click();
                        Thread.sleep(1000);
                    } else if (M_Y_Element.contains("To")) {
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_YearRange")))
                                .click();
                        Thread.sleep(1000);
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_Month")))
                                .click();
                        Thread.sleep(1000);
                    }
                    WebElement Calender_M_Y = driver.findElement(By.xpath(PropertiesUtil.getProperty(M_Y_Element)));
                    WebElement Calender_D = driver.findElement(By.xpath(PropertiesUtil.getProperty(D_Element)));
                    // Pick Date, Month and Year From Given Date to match with Calender Widget
                    if (Calender_D.getText().equals(date)
                            && Calender_M_Y.getText().equals(monthInString(month) + " " + year))
                        return "Pass";

                }
                break;

                case "CurrentDate": {
                    Inputbox.clear();
                    driver.findElement(By.xpath(".//*[@class='nav pull-right']/li[1]/a")).click();
                    Inputbox.click();
                    if (M_Y_Element.contains("From")) {
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_YearRange")))
                                .click();
                        Thread.sleep(1000);
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_Month")))
                                .click();
                        Thread.sleep(1000);
                    } else if (M_Y_Element.contains("To")) {
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_YearRange")))
                                .click();
                        Thread.sleep(1000);
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_Month")))
                                .click();
                        Thread.sleep(1000);
                    }
                    Calendar cal = Calendar.getInstance();
                    int currenDate = cal.get(Calendar.DATE);
                    int currentMonth = cal.get(Calendar.MONTH) + 1;
                    int currentYear = cal.get(Calendar.YEAR);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException w) {
                    }

                    WebElement Calender_M_Y = driver.findElement(By.xpath(PropertiesUtil.getProperty(M_Y_Element)));
                    WebElement Calender_D = driver.findElement(By.xpath(PropertiesUtil.getProperty(D_Element)));
                    System.out.println(monthInString(Integer.toString(currentMonth)) + " " + Integer.toString(currentYear));
                    if (Calender_D.getText().equals(Integer.toString(currenDate)) && Calender_M_Y.getText()
                            .equals(monthInString(Integer.toString(currentMonth)) + " " + Integer.toString(currentYear)))
                        return "Pass";

                }
                break;
                case "InputVerify": {
                    Inputbox.clear();
                    driver.findElement(By.xpath(".//*[@class='nav pull-right']/li[1]/a")).click();
                    Inputbox.sendKeys(Date);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException w) {
                    }
                    if (M_Y_Element.contains("From")) {
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_YearRange")))
                                .click();
                        Thread.sleep(1000);
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_Month")))
                                .click();
                        Thread.sleep(1000);
                    } else if (M_Y_Element.contains("To")) {
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_YearRange")))
                                .click();
                        Thread.sleep(1000);
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_Month")))
                                .click();
                        Thread.sleep(1000);
                    }
                    WebElement Calender_D = driver.findElement(By.xpath(PropertiesUtil.getProperty(D_Element)));
                    Calender_D.click();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException w) {
                    }
                    String a = Inputbox.getAttribute("value");
                    System.out.println("Extracted date from Input box: " + a);
                    if (Date.equals(a))
                        return "Pass";
                }
                break;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
        System.out.println("Calender Wigdet not working properly");
        return "Fail";

    }

    public String monthInString(String month) {
        String monthString = null;
        int i = Integer.parseInt(month);
        switch (i) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
        }
        return monthString;
    }

    *//**
     * Method Name: NewWindowOperation Parameters: ElementPath, ElementName,
     * Operation Return Type: String Objective: Perform operations such as "Edit
     * alert" , "Turn on" and "Turn off" alert for given Alert name.
     **//*

    public String dNewWindowOperation(String ElementPath, String newWindowText) {

        try {
            boolean chk = false;
            String parentWindowHandle = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(parentWindowHandle)) {
                    driver.switchTo().window(handle);
                    WebElement element = driver.findElement(By.xpath(PropertiesUtil.getProperty(ElementPath)));
                    if (element.getText().trim().equals(newWindowText)) {
                        chk = true;
                    }
                    break;
                }
            }
            // Close Child tab or Window

            if (chk == true) {
                driver.close();
                driver.switchTo().window(parentWindowHandle);
                return "Pass";
            }

            return "Fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    *//**
     * Method Name: FileDownload Parameters: Return Type: Objective: Handle Export
     * File Download PopUp for Export Feature
     **//*
    public String FileDownload(String Sheet_Name, String Element) throws AWTException {
        String Filepath = (System.getProperty("user.dir")).toLowerCase() + "\\testdata\\" + Sheet_Name;
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.download.dir", Filepath);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/vnd.ms-excel");

        WebDriver driver = new FirefoxDriver(firefoxProfile);// new RemoteWebDriver(new
        // URL("http://localhost:4444/wd/hub"), capability);
        driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).click();

        return "Pass";
    }

    *//**
     * Method Name: FileDownloadPopUp Parameters: Return Type: Objective: Handle
     * Export File Download PopUp for Export Feature
     *
     * @throws AWTException
     **//*

    public String FileDownloadPopUp() {
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_TAB);
            // r.delay(1000);
            r.keyPress(KeyEvent.VK_TAB);
            // r.delay(1000);
            r.keyPress(KeyEvent.VK_TAB);
            // r.delay(1000);
            r.keyPress(KeyEvent.VK_TAB);
            // r.delay(1000);
            r.keyPress(KeyEvent.VK_ENTER);

            return "Pass";

        } catch (AWTException e) {
            return "Fail";
        }

    }

    *//**
     *
     * Method Name: ListContainsDesiredText Parameters: Return Type: Objective:
     * Handle Export File Download PopUp for Export Feature
     *
     * @throws AWTException
     *
     **//*

    *//**
     * Method Name: ListHaveDesiredText Parameters: Return Type: Objective: Handle
     * Export File Download PopUp for Export Feature, looks for All Elements in List
     * & perform Action on Them
     *
     * @throws AWTException
     **//*

    public String ListHaveDesireTxt(String ListPath, String DesiredTxt, String action, String concatText) {
        System.out.println("Desired Text is: " + DesiredTxt);
        boolean chk = true;
        Properties properties = System.getProperties();
        String fld = properties.getProperty(DesiredTxt);
        String val;
        if (fld != null) {
            val = fld;
        } else {
            val = DesiredTxt.toString();
        }
        System.out.println("The comparing text value is  : " + val);
        try {
            Thread.sleep(5000);
            List<WebElement> elements = driver.findElements(By.xpath(PropertiesUtil.getProperty(ListPath)));
            if (elements.size() == 0) {
                System.out.println(elements.size());
                chk = false;
            } else if (action == "") {
                for (int i = 0; i <= elements.size() - 1; i++) {
                    System.out.println("UI Element Text of: " + i + " element is " + elements.get(i).getText());
                    if (elements.get(i).getText().toLowerCase().contains(val.toLowerCase())
                            && !elements.get(i).getText().equals("")) {
                        chk = true;
                        break;
                    } else {
                        chk = false;
                    }
                }
            } else if (action.toLowerCase().equals("click")) {
                for (int i = 0; i <= elements.size() - 1; i++) {
                    chk = false;
                    if (elements.get(i).getText().toLowerCase().contains(val.toLowerCase())
                            && !elements.get(i).getText().equals("")) {
                        System.out.println("Element Text:" + elements.get(i).getText());
                        elements.get(i).click();
                        chk = true;
                        break;
                    }

                }
            } else if (action.toLowerCase().equals("validateall")) {
                for (int i = 0; i <= elements.size() - 1; i++) {
                    if (elements.get(i).getText().toLowerCase().equals(val.toLowerCase()) && !driver
                            .findElements(By.xpath(PropertiesUtil.getProperty(ListPath))).get(i).getText().equals("")) {
                        System.out.println("UI Element Text of : " + i + " element is " + elements.get(i).getText()
                                + " matches the compared value");
                        chk = true;
                    } else {
                        System.out.println("UI Element Text of : " + i + " element is " + elements.get(i).getText()
                                + " not matches the compared value");
                        chk = false;
                        break;
                    }
                }

            } else if (action.toLowerCase().contains("validatenotification")) {
                for (int i = 0; i <= elements.size() - 1; i++) {
                    System.out.println("UI Element Text of: " + i + " element is " + elements.get(i).getText().toLowerCase().trim());
                    System.out.println("Compared text is: " + val.toLowerCase() + concatText.toLowerCase().trim());
                    if (elements.get(i).getText().toLowerCase().trim().replaceAll("[\t\n\r\\s]", "")
                            .contains(val.toLowerCase() + concatText.toLowerCase().trim().replaceAll("[\t\n\r\\s]", ""))
                            && !elements.get(i).getText().equals("")) {
                        System.out.println("UI Element Text of : " + i + " element is " + elements.get(i).getText()
                                + " matches the compared value");
                        chk = true;
                        break;
                    } else {
                        System.out.println("UI Element Text of : " + i + " element is " + elements.get(i).getText()
                                + " not matches the compared value");
                        chk = false;
                    }
                }
            }
            // ***********************Added by
            // Himanshi******************************************************************************
            else if (action.toLowerCase().equals("validateone")) {
                for (int i = 0; i <= elements.size() - 1; i++) {
                    if (elements.get(i).getText().toLowerCase().equals(val.toLowerCase()) && !driver
                            .findElements(By.xpath(PropertiesUtil.getProperty(ListPath))).get(i).getText().equals("")) {
                        System.out.println("UI Element Text of : " + i + " element is " + elements.get(i).getText()
                                + " matches the compared value");
                        chk = true;
                        break;
                    } else {
                        System.out.println("UI Element Text of : " + i + " element is " + elements.get(i).getText()
                                + " not matches the compared value");
                        chk = false;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
        System.out.println(chk);
        if (chk) {
            // TestRunner.test.log(Status.PASS,"<b><I>"+DesiredTxt+"</b></I> is present in
            // the list");
            return "Pass";
        }
        // TestRunner.test.log(Status.FAIL,"<b><I>"+DesiredTxt+"</b></I> is NOT present
        // in the list");
        return "Fail";

    }

    *//**
     * Method Name: Print Command Parameters: Return Type: Objective: Handle Export
     * File Download PopUp for Export Feature
     *
     * @throws AWTException
     **//*

    public String PrintCommand() {
        try {
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            Robot r = new Robot();
            System.out.println(driver.getWindowHandles());
            r.setAutoDelay(40);
            r.keyPress(KeyEvent.VK_ESCAPE);
            r.keyRelease(KeyEvent.VK_ESCAPE);
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    *//**
     * Method Name: GetCount Parameters: element Xpath Return Type: String
     * Objective: Get the count of any list item.
     **//*

    public String GetCount(String Element) {
        try {
            // Thread.sleep(3000);
            int rowCount = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element))).size();
            System.out.println("Result count displayed: " + rowCount);
            // TestRunner.test.log(Status.PASS,"<b><I> Result count displayed:
            // "+rowCount+"</b></I>");
            return "Pass";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    *//**
     * Method Name: GetLocation Parameters: Element Return Type: Objective:
     **//*
    public String GetLocation(String Element, String locationX, String locationY) {
        try {
            DismissErrors();
            int LocationX = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getLocation().getX();
            int LocationY = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getLocation().getY();
            System.out.println("X expected location: " + LocationX);
            System.out.println("Y expected Location: " + LocationY);
            if (Integer.toString(LocationX).equals(locationX) && Integer.toString(LocationY).equals(locationY))
                // TestRunner.test.log(Status.PASS,"<b><I>location is
                // "+locationX+","+LocationY+"</b></I>");
                return "Pass";

        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        return "Fail";
    }

    *//**
     * Method Name: DragAndDrop Parameters: Element ,source, Target Return Type:
     * Objective: Drag a drop
     **//*

    public String DragAndDrop(String ElementType, String Source, String Target) {
        switch (ElementType) {
            case "Preference": {
                try {
                    WebElement LocatorFrom = driver.findElement(By.xpath(PropertiesUtil.getProperty(Source)));
                    WebElement LocatorTo = driver.findElement(By.xpath(PropertiesUtil.getProperty(Target)));
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_Export_Setting_link"))).click();
                    Thread.sleep(3000);
                    String textBefore1 = LocatorFrom.getText();
                    String textBefore2 = LocatorTo.getText();
                    System.out.println("Element1 text before action: " + textBefore1);
                    System.out.println("Element2 text before action: " + textBefore2);
                    Actions clicker = new Actions(driver);
                    Thread.sleep(5000);
                    clicker.clickAndHold(LocatorFrom).moveToElement(LocatorTo, 1, 45).build().perform();
                    // ((HasInputDevices)
                    // driver).getMouse().mouseUp(((Locatable)LocatorFrom).getCoordinates());
                    Thread.sleep(5000);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_SaveAsDeafult_btn"))).click();
                    Thread.sleep(3000);
                    // Navigate("NAV_Search", "NAV_Standard_Search");
                    // Thread.sleep(1000);

                    driver.manage().deleteAllCookies();
                    Thread.sleep(1000);
                    CloseBrowser("");
                    openBrowser(browserType);
                    Launch("https://new.discover.cpaglobal.com");
                    Login("jthakur@cpaglobal.com", "Jitu@123");
                    Thread.sleep(3000);
                    Navigate("NAV_Logged_in_as_UserName", "NAV_Manage_Preferences");
                    Thread.sleep(1000);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_Export_Setting_link"))).click();
                    Thread.sleep(1000);

                    String textAfter1 = driver.findElement(By.xpath(PropertiesUtil.getProperty(Source))).getText();
                    String textAfter2 = driver.findElement(By.xpath(PropertiesUtil.getProperty(Target))).getText();
                    System.out.println("Element1 text After action: " + textAfter1);
                    System.out.println("Element2 text After action: " + textAfter2);
                    if (textBefore1.equals(textAfter2) && textBefore2.equals(textAfter1))
                        return "Pass";
                    else
                        return "Fail";

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            case "PRD": {
                try {
                    WebElement LocatorFrom = driver.findElement(By.xpath(PropertiesUtil.getProperty(Source)));
                    WebElement LocatorTo = driver.findElement(By.xpath(PropertiesUtil.getProperty(Target)));
                    // driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_Export_Setting_link"))).click();
                    Thread.sleep(3000);
                    String textBefore1 = LocatorFrom.getText();
                    String textBefore2 = LocatorTo.getText();
                    System.out.println("Element1 text before action: " + textBefore1);
                    System.out.println("Element2 text before action: " + textBefore2);
                    System.out.println(LocatorFrom.getLocation());
                    System.out.println(LocatorTo.getLocation());
                    Actions clicker = new Actions(driver);
                    Thread.sleep(5000);
                    clicker.clickAndHold(LocatorFrom).moveToElement(LocatorTo, 1, 12).build().perform();
                    // ((HasInputDevices)
                    // driver).getMouse().mouseUp(((Locatable)LocatorFrom).getCoordinates());
                    Thread.sleep(5000);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_SaveAsDeafult_btn"))).click();
                    Thread.sleep(3000);

                    driver.manage().deleteAllCookies();
                    Thread.sleep(1000);
                    CloseBrowser("");
                    openBrowser(browserType);
                    Launch("https://new.discover.cpaglobal.com");
                    Login("cpa.discover@gmail.com", "Test@123");
                    Thread.sleep(3000);
                    Navigate("NAV_Search", "NAV_Advanced_Search");
                    EnterText("AS_Pubnumbr_Txtbox", "EP-1000048-B1");
                    Click("Search_btn", "RP_Pubnumbr_label", "Publication Number");
                    Click("RP_PubNum1_lnk", "PRD_Backtoresults_lnk", "Back to results");

                    String textAfter1 = driver.findElement(By.xpath(PropertiesUtil.getProperty(Source))).getText();
                    String textAfter2 = driver.findElement(By.xpath(PropertiesUtil.getProperty(Target))).getText();
                    System.out.println("Element1 text After action: " + textAfter1);
                    System.out.println("Element2 text After action: " + textAfter2);
                    if (textBefore1.equals(textAfter2) && textBefore2.equals(textAfter1))
                        return "Pass";
                    else
                        return "Fail";

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Export": {
                try {
                    WebElement LocatorFrom = driver.findElement(By.xpath(PropertiesUtil.getProperty(Source)));
                    WebElement LocatorTo = driver.findElement(By.xpath(PropertiesUtil.getProperty(Target)));
                    // driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_Export_Setting_link"))).click();
                    Thread.sleep(3000);
                    String textBefore1 = LocatorFrom.getText();
                    String textBefore2 = LocatorTo.getText();
                    System.out.println("Element1 text before action: " + textBefore1);
                    System.out.println("Element2 text before action: " + textBefore2);
                    System.out.println(LocatorFrom.getLocation());
                    System.out.println(LocatorTo.getLocation());
                    Actions clicker = new Actions(driver);
                    Thread.sleep(5000);
                    clicker.clickAndHold(LocatorFrom).moveToElement(LocatorTo, 1, 25).build().perform();
                    // ((HasInputDevices)
                    // driver).getMouse().mouseUp(((Locatable)LocatorFrom).getCoordinates());
                    Thread.sleep(5000);

                    String textAfter1 = driver.findElement(By.xpath(PropertiesUtil.getProperty(Source))).getText();
                    String textAfter2 = driver.findElement(By.xpath(PropertiesUtil.getProperty(Target))).getText();
                    System.out.println("Element1 text After action: " + textAfter1);
                    System.out.println("Element2 text After action: " + textAfter2);
                    if (textBefore1.equals(textAfter2) && textBefore2.equals(textAfter1))
                        return "Pass";
                    else
                        return "Fail";

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;

            case "ModalOverlay": {

            }
            break;
        }
        return "Fail";
    }

    public String VerifySortedList(String Elements, String order) {
        try {
            switch (order) {
                case "ASC": {
                    List<WebElement> elements = driver.findElements(By.xpath(PropertiesUtil.getProperty(Elements)));
                    Iterator<WebElement> itr = elements.iterator();
                    List<String> elementTexts = new ArrayList<String>();
                    while (itr.hasNext()) {
                        elementTexts.add(itr.next().getText().trim());
                    }
                    System.out.println("Element Text Values before sorting: " + elementTexts);
                    List<String> beforeSorting = new ArrayList<String>();
                    for (String text : elementTexts) {
                        beforeSorting.add(text);
                    }
                    Collections.sort(elementTexts);
                    System.out.println("Sorted Element Values: " + elementTexts);
                    if (beforeSorting.equals(elementTexts))
                        // TestRunner.test.log(Status.PASS,"<b><I> Sorted Element Values:
                        // "+elementTexts+"</b></I>");
                        return "Pass";
                }
                case "DESC": {
                    List<WebElement> elements = driver.findElements(By.xpath(PropertiesUtil.getProperty(Elements)));
                    Iterator<WebElement> itr = elements.iterator();
                    List<String> elementTexts = new ArrayList<String>();
                    while (itr.hasNext()) {
                        elementTexts.add(itr.next().getText().trim());
                    }
                    System.out.println("Element Text Values before sorting: " + elementTexts);
                    Collections.reverse(elementTexts);
                    System.out.println("Element Text Values in reverse order: " + elementTexts);
                    List<String> beforeSorting = new ArrayList<String>();
                    for (String text : elementTexts) {
                        beforeSorting.add(text);
                    }
                    Collections.sort(elementTexts);
                    System.out.println("Sorted Element Values: " + elementTexts);
                    if (beforeSorting.equals(elementTexts))
                        // TestRunner.test.log(Status.PASS,"<b><I> Sorted Element Values:
                        // "+elementTexts+"</b></I>");
                        return "Pass";
                }
            }
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        return "Fail";
    }

    *//**
     * Method Name: MouseHover Parameters: Element1, Element2, text Return Type:
     * Objective:
     **//*
    public String MouseHover(String Element1, String Element2, String text) {
        Properties properties = System.getProperties();
        String fld = properties.getProperty(Element1);
        String val;

        if (fld != null)
            val = fld;

        else
            val = Element1.toString();
        try {
            switch (text) {
                case "Highlight": {
                    WebElement LocationX = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));
                    System.out.println(LocationX);
                    Actions builder = new Actions(driver);
                    System.out.println("Color before Hover : " + LocationX.getCssValue("color"));
                    builder.clickAndHold(LocationX).build().perform();
                    Thread.sleep(3000);
                    String colorValue = LocationX.getCssValue("color");
                    System.out.println("Color After Hover : " + LocationX.getCssValue("color"));
                    builder.release().build().perform();
                    if (colorValue.equalsIgnoreCase(Element2))
                        // TestRunner.test.log(Status.PASS,"<b><I>"+colorValue+" matches with
                        // "+Element2+"</b></I>");
                        return "Pass";

                }
                case "MoveToElement": {
                    System.out.println("here" + val);
                    if (driver.findElements(By.xpath("//a[text()='" + val + "']")).size() > 0) {
                        WebElement LocationX = driver.findElement(By.xpath("//a[text()='" + val + "']"));
                        Actions builder = new Actions(driver);
                        builder.moveToElement(LocationX).build().perform();
                        return "Pass";
                    }
                }
                break;
                default: {
                    WebElement LocationX = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));
                    Actions builder = new Actions(driver);
                    builder.clickAndHold(LocationX).build().perform();
                    Thread.sleep(3000);
                    WebElement LocationY = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2)));
                    String ActualText = LocationY.getText().trim();
                    System.out.println("ActualText: " + ActualText);
                    builder.release().build().perform();
                    if (ActualText.equalsIgnoreCase(text) || ActualText.contains(text))
                        // TestRunner.test.log(Status.PASS,"<b><I>"+ActualText+" is equal to
                        // "+text+"</b></I>");
                        return "Pass";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        return "Fail";
    }

    *//**
     * Method Name: GetCSSPropery Parameters: Element1, CSSproperty, DesiredCSS
     * Return Type: Objective:
     **//*
    public String GetCSSProperty(String Element1, String CSSproperty, String DesiredCSS) {
        try {

            if (Element1.isEmpty()) {
                String script = "return window.getComputedStyle(document.querySelector('.required'),'::before').getPropertyValue('"
                        + CSSproperty + "')";
                JavascriptExecutor js = (JavascriptExecutor) driver;
                String val = (String) js.executeScript(script);
                System.out.println("CSS value of Pseudo Element 'After' is: " + val);
                if (val.equalsIgnoreCase(DesiredCSS)) {
                    // TestRunner.test.log(Status.PASS,"<b><I>"+DesiredCSS+" is matched </b></I>");
                    return "Pass";
                }

            }
            WebElement LocationX = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));
            String val = LocationX.getCssValue(CSSproperty);
            System.out.println("CSS Value: " + val);
            if (val.equalsIgnoreCase(DesiredCSS))
                // TestRunner.test.log(Status.PASS,"<b><I>"+DesiredCSS+" is matched </b></I>");
                return "Pass";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        return "Fail";
    }

    *//**
     * Method Name: GetAttribute Parameters: Element, Attribute, DesiredText Return
     * Type: Objective:
     **//*
    public String GetAttribute(String Element, String Attribute, String DesiredText) {
        try {
            WebElement text = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element)));
            String textagain = text.getAttribute(Attribute);
            System.out.println("Desired Text: " + DesiredText);
            System.out.println("Text Found: " + textagain);
            if (textagain.equalsIgnoreCase(DesiredText) || textagain.contains(DesiredText)) {
                // TestRunner.test.log(Status.PASS,"<b><I>"+DesiredText+" is present </b></I>");
                return "Pass";
            } else {
                // TestRunner.test.log(Status.PASS,"<b><I>"+DesiredText+" is NOT present
                // </b></I>");
                return "Fail";
            }
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    *//**
     * Method Name: IsElementClickable Parameters: Element Return Type: Objective:
     * Verifies that elements in a list are clickable
     **//*

    public String IsElementClickable(String Element) {
        boolean chk = true;
        try {
            List<WebElement> clickElementlist = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element)));

            for (int i = 0; i < clickElementlist.size(); i++) {
                if (clickElementlist.get(i).isEnabled()) {
                    System.out.println(Element + " is enabled");
                    chk = chk && true;
                } else
                    chk = chk && false;
            }
            if (chk) {
                // TestRunner.test.log(Status.PASS,"<b><I>"+Element+"</b></I> is enabled");
                return "Pass";
            }
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        return "Fail";
    }

    *//**
     * Method Name: VerifyElementsExistence Parameters: Element Return Type:
     * Objective: Verify Existence of multiple Elements in a list
     **//*

    public String VerifyElementsExistence(String Element) {
        boolean chk = true;
        try {
            List<WebElement> clickElementlist = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element)));

            for (int i = 0; i < clickElementlist.size(); i++) {
                if (clickElementlist.get(i).isDisplayed()) {
                    System.out.println(Element + " is displayed");
                    chk = chk && true;
                } else
                    chk = chk && false;
            }
            if (chk) {
                // TestRunner.test.log(Status.PASS,"<b><I>"+Element+"</b></I> is displayed");
                return "Pass";
            }
        } catch (Exception e) {// TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");return
            // "Fail";}
            return "Fail";
        }

        return "Fail";
    }

    *//**
     * Method Name: SetMultipleCheckbox Parameters: ChkBoxPath, State Return Type:
     * String Objective: Check/Uncheck the selected 'Checkbox' to given 'State
     * (On/Off)'
     **//*

    public String SetMultipleCheckbox(String ChkBoxPath, String State) {
        try {
            System.out.println("Working with Chechboxes: " + ChkBoxPath);
            List<WebElement> Checkboxlist = driver.findElements(By.xpath(PropertiesUtil.getProperty(ChkBoxPath)));
            for (int i = 0; i < Checkboxlist.size(); i++) {

                // System.out.println("==>"+ chk);
                if (Checkboxlist.get(i).isSelected() == true && State.equalsIgnoreCase("On")) {
                    System.out.println(ChkBoxPath + " chechbox is already set to...... " + State);
                } else if (Checkboxlist.get(i).isSelected() == true && State.equalsIgnoreCase("Off")) {
                    Checkboxlist.get(i).click();
                    System.out.println(ChkBoxPath + " chechbox is set to " + State);
                } else if (State.equalsIgnoreCase("On") && Checkboxlist.get(i).isSelected() == false) {
                    Checkboxlist.get(i).click();
                    System.out.println(ChkBoxPath + " chechbox is set to " + State);
                } else if (State.equalsIgnoreCase("Off") && Checkboxlist.get(i).isSelected() == false) {
                    System.out.println(ChkBoxPath + " chechbox is already set to " + State);
                }
            }
            Thread.sleep(3000);
        } catch (Exception e) {
            // e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        // TestRunner.test.log(Status.PASS,"<b><I>"+ChkBoxPath+" is set to
        // </b></I>"+State);
        return "Pass";
    }

    *//**
     * Method Name: VerifyFieldQualifier Parameters: Return Type: String Objective:
     * Apply all types of search term and perform search
     **//*
    public String VerifyFieldQualifier(String searchterm) {
        try {
            DismissErrors();
            Navigate("NAV_Search", "NAV_Advanced_Search");
            System.out.println("Search Tearm: " + searchterm);
            EnterText("AS_KeywordTextArea", searchterm);
            Click("Search_btn", "RP_Pubnumbr_label", "Publication Number");
            String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_ResultCount"))).getText();
            String Text2 = StringUtils.substringBetween(Text, "of", "results").trim();
            int count = Integer.parseInt(Text2);
//            Keywords k = Keywords.getInstance();
//            k.getResultCount(Integer.toString(count));
            System.out.println("Total count: " + count);
            if ((count != 0)
                    && (driver.findElements(By.xpath(PropertiesUtil.getProperty("AllPubnumbr"))).size() != 0)) {
                return "Pass";
            }

        } catch (Exception e) {
            return "Fail";
        }
        return "Fail";
    }

    *//**
     * Method Name: VerifyParentFolder Parameters: RelativePath, FolderName,
     * ParentElementPath, ChildElementPath Return Type: String Objective: Click on
     * the desired folder from folder explorer and verifies Parent and Child
     * Elements
     **//*

    public String VerifyParentFolder(String RelativePath, String FolderName, String ParentElementPath,
                                     String ChildElementPath) {

        try {
            // Expand All folders
            List<WebElement> TotalArrow = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty("PF_ExpandAllFolders")));
            for (int i = 0; i < TotalArrow.size(); i++) {
                if (TotalArrow.get(i).isDisplayed() == true) {
                    TotalArrow.get(i).click();
                    // System.out.println("done arrow");
                }
            }

            // Select the desired folder

            List<WebElement> TotalFolders = driver.findElements(By.xpath(PropertiesUtil.getProperty(RelativePath)));
            int count = 0;
            for (int i = 0; i <= TotalFolders.size(); i++) { // System.out.println(TotalFolders.get(i).getText());
                if (TotalFolders.get(i).getText().equalsIgnoreCase(FolderName)) {
                    System.out.println("Folder found: " + TotalFolders.get(i).getText());
                    // TotalFolders.get(i).click();
                    List<WebElement> Parent = driver
                            .findElements(By.xpath(PropertiesUtil.getProperty(ParentElementPath)));
                    for (WebElement ele : Parent) {
                        if (ele.isDisplayed()) {
                            count++;
                            System.out.println("Parent Folders are: " + ele.getText());
                        }
                    }
                    List<WebElement> Child = driver
                            .findElements(By.xpath(PropertiesUtil.getProperty(ChildElementPath)));
                    for (WebElement el : Child) {
                        if (el.isDisplayed()) {
                            count++;
                            System.out.println();
                            System.out.println("Child Folders are: " + el.getText());
                        }
                    }
                    // System.out.println("Folders are Parent Folders");

                    Thread.sleep(3000);
                    // TestRunner.test.log(Status.PASS,"<b><I> Folder found </b></I>");
                    return "Pass";
                }
            }

            System.out.println("Folder not found");
            // TestRunner.test.log(Status.FAIL,"<b><I> Folder not found </b></I>");
            return "Fail";
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Failed due to Exception");
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    *//**
     * Method Name: CompareData Parameters:
     * Element1,Element2,Element3,Element4,Element5 Return Type: String Objective:
     * Compares the data on one page to Page
     *
     * @throws Exception
     * @throws Exception
     **//*

    public String CompareData(String Element1, String Element2, String Element3, String Element4, String Element5) {
        try {
            List<WebElement> DB_Recent = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element1)));
            String DB_Recent_Data = null;
            for (WebElement el1 : DB_Recent) {
                DB_Recent_Data += el1.getText();

            }
            System.out.println("Data on First Page is: " + DB_Recent_Data);

            driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2))).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty(Element3))).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty(Element4))).click();

            try {
                if (driver.findElement(By.xpath(".//*[@class='branch ng-scope active'][2]/span[1]/i[1]"))
                        .isDisplayed()) {
                    driver.findElement(By.xpath(".//*[@class='branch ng-scope active'][2]/span[1]/i[1]")).click();
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
            }

            List<WebElement> AS_Recent = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element5)));
            String AS_Recent_Data = null;
            for (WebElement el2 : AS_Recent) {
                AS_Recent_Data += el2.getText();
            }
            System.out.println("Data on Second Page is: " + AS_Recent_Data);

            if (DB_Recent_Data.equalsIgnoreCase(AS_Recent_Data)) {
                System.out.println("Data on First Page matches with Data on Second Page");
                // TestRunner.test.log(Status.PASS,"<b><I> Data on First Page matches with Data
                // on Second Page </b></I>");
                return "Pass";
            }
            System.out.println("Data on First Page doesnot match with Data on Second Page");
            // TestRunner.test.log(Status.FAIL,"<b><I> Data on First Page doesnot match with
            // Data on Second Page </b></I>");
            return "Fail";
        } catch (Exception e) {
            System.out.println("Failed due to Exception");
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    *//**
     * Method Name: Security PopUp Parameters: Return Type: Objective: Handle Export
     * File Download PopUp for Export Feature
     *
     * @throws AWTException
     **//*

    public String SecurityPopUP() {

        try {
            Robot r = new Robot();
            Alert alert = driver.switchTo().alert();
            alert.accept();
            Thread.sleep(5000);
            r.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(1000);
            r.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(1000);
            r.keyPress(KeyEvent.VK_TAB);
            Thread.sleep(1000);
            r.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(5000);
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    *//**
     * Method Name: MouseDrag Parameters: Element1, Element2 Return Type: String
     * Objective: Drag the Mouse cursor from one location to another in navigators
     **//*
    public String MouseDrag(String Element1, String Element2) {
        try {

            WebElement LocationX = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));
            WebElement LocationY = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2)));
            Actions builder = new Actions(driver);
            builder.clickAndHold(LocationX).build().perform();
            Thread.sleep(3000);

            builder.release(LocationY).build().perform();
            Thread.sleep(3000);
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    *//**
     * Method Name: ClickOnDesiredLink Parameters: Element1, Element2 Return Type:
     * Objective: Handle Export File Download PopUp for Export Feature
     *
     * @throws AWTException
     **//*

    public String ClickOnDesiredLink(String Element1, String Element2) {
        try {
            int i = 1;
            while (i <= 20) {
                try {
                    WebElement element1 = driver.findElement(
                            By.xpath(PropertiesUtil.getProperty(Element1).replaceAll("%", Integer.toString(i))));
                    if (element1.isDisplayed()) {
                        System.out.println("Desire link found on: " + i + "th row");
                        try {
                            WebElement element2 = driver.findElement(By
                                    .xpath(PropertiesUtil.getProperty(Element2).replaceAll("%", Integer.toString(i))));
                            if (element2.isDisplayed()) {
                                element2.click();
                                Thread.sleep(4000);
                                // TestRunner.test.log(Status.PASS,"<b><I>"+Element2+" is clicked</b></I>");
                                return "Pass";
                            } else {
                                System.out.println("Desire link not found");
                            }
                        } catch (NoSuchElementException e) {
                            System.out.println("Desire link not found");
                        }

                        break;
                    }

                } catch (Exception e) {
                    // System.out.println("Row not found yet");
                }
                i++;
            }
            if (i == 11) {
                // TestRunner.test.log(Status.FAIL,"<b><I>"+Element2+" is not clicked</b></I>");
                return "Fail";
            }
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    *//**
     *
     * Method Name: ValidateNumberFormat Parameters: Country Code, Country Name,
     * Filing Type, Number Type,Effective From Date, Effective To Date, Number
     * Pattern Return Type: Pass or Fail Objective: Validates the number format for
     * given country for specific dates
     *
     * @throws Exception
     *
     **//*

    
     * public String ValidateNumberFormat(String CountryCode, String CountryName,
     * String FilingType, String NumberType,String EffectiveFromDate,String
     * EffectiveToDate, String NumberPattern) { String Combined=
     * CountryCode.concat(CountryName).concat(" - ").concat(FilingType).concat(" - "
     * ).concat(NumberType).concat(" - ").concat(EffectiveFromDate).concat(" - ").
     * concat(EffectiveToDate).concat(" - ");
     * System.out.println("Concatenated string is :"+Combined);
     * driver.findElement(By.xpath(".//*[@id='dijit_form_FilteringSelect_2']")).
     * sendKeys(Combined); if(element1.isDisplayed()){
     * System.out.println("Desire link found on: "+i+"th row"); WebElement
     * element2=driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2).
     * replaceAll("%", Integer.toString(i)))); String
     * element2Text=element2.getText(); System.out.println(element2Text);
     * if(element2Text.contains(Element3)) { return "Pass"; } break; } }
     * catch(Exception e){ System.out.println("Element not found yet"); } i++; }
     * if(i==11) return "Fail";
     *
     * return "Pass"; } catch(Exception e){ e.printStackTrace(); return "Fail"; } }
     *
     *
     

    *//**
     * Method Name: VerifyDesiredLinks Parameters: Element1, Element2, Element 3
     * Return Type: Objective: Verify elements or links corresponding to queries or
     * values in a tables
     *
     * @throws AWTException
     **//*

    public String VerifyDesiredLinks(String Element1, String Element2, String Element3) {
        try {
            int i = 1;
            while (i <= 10) {
                try {
                    WebElement element1 = driver.findElement(
                            By.xpath(PropertiesUtil.getProperty(Element1).replaceAll("%", Integer.toString(i))));

                    if (element1.isDisplayed()) {
                        System.out.println("Desire link found on: " + i + "th row");
                        WebElement element2 = driver.findElement(
                                By.xpath(PropertiesUtil.getProperty(Element2).replaceAll("%", Integer.toString(i))));
                        String element2Text = element2.getText();
                        System.out.println(element2Text);
                        if (element2Text.contains(Element3)) {
                            // TestRunner.test.log(Status.PASS,"<b><I>"+Element3+" is present in
                            // text</b></I>");
                            return "Pass";
                        }
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Element not found yet");
                }
                i++;
            }
            if (i == 11)
                return "Fail";

            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    *//**
     * Method Name: ClickForDownload Parameters: Element1, Return Type: Objective:
     * Handle download window on various browsers
     *
     * @throws AWTException
     **//*

    public String ClickForDownload(String Element1) {
        try {
            WebElement element = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));

            if (browserType.equals("Mozilla")) {
                element.click();
                try {
                    JavascriptAlert("Accept");
                    System.out.println("Alert window found");
                } catch (Throwable e) {
                }
                Thread.sleep(3000);
                FileDownloadPopUp();
                return "Pass";
            } else if (browserType.equals("Chrome")) {
                element.click();
                try {
                    JavascriptAlert("Accept");
                    System.out.println("Alert window found");
                } catch (Throwable e) {
                }
                return "Pass";
            } else if (browserType.equals("IE")) {
                System.out.println("Browser is: " + browserType);

                try {
                    if (driver.findElement(By.xpath(PropertiesUtil.getProperty("AS_Cross_Btn"))).isDisplayed()) {
                        WebElement element2 = driver.findElement(By.xpath(PropertiesUtil.getProperty("AS_Cross_Btn")));
                        element2.click();
                    }

                } catch (Exception e) {
                }
                return "Fail";
            }
            return "Fail";
        } catch (Exception e) {
            return "Fail";
        }

    }

    *//**
     * Method Name: ClearTextbox Parameters: TextBoxElement Return Type: Objective:
     * To clear a textbox/ inputbox
     *
     * @throws AWTException
     **//*
    public String ClearTextbox(String TextBoxElement) {

        try {
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).clear();
            // TestRunner.test.log(Status.PASS,"<b><I> textbox cleared </b></I>");
            return "Pass";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    *//**
     * Method Name: ClickForPrint Parameters: Element1 Return Type: Objective: To
     * cancel or Accept Windows Print Popup
     *
     * @throws AWTException
     **//*

    public String ClickForPrint(String Element1) {
        try {
            WebElement element = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));

            if (browserType.equals("Mozilla")) {
                System.out.println("Browser is: " + browserType);
                element.click();
                FilePrintPopUp();
                return "Pass";
            } else if (browserType.equals("IE")) {
                System.out.println("Browser is: " + browserType);
                element.click();
                FilePrintPopUp();
                return "Pass";
            } else if (browserType.equals("Chrome")) {
                System.out.println("Browser is: " + browserType);
                element.click();
                FilePrintPopUpChrome();
                return "Pass";

            }
            return "Pass";
        } catch (Exception e) {
            return "Fail";
        }

    }

    public String FilePrintPopUpChrome() {
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

        try {
            Robot r = new Robot();
            r.delay(3000);
            r.keyPress(KeyEvent.VK_TAB);
            r.keyRelease(KeyEvent.VK_TAB);
            r.keyPress(KeyEvent.VK_ENTER);
            return "Pass";
        } catch (AWTException e) {
            return "Fail";
        }

    }

    public String FilePrintPopUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        try {

            Robot r = new Robot();
            r.setAutoDelay(40);
            r.keyPress(KeyEvent.VK_ESCAPE);
            r.keyRelease(KeyEvent.VK_ESCAPE);
            r.keyPress(KeyEvent.VK_ALT);
            r.keyPress(KeyEvent.VK_F4);
            r.keyRelease(KeyEvent.VK_ALT);
            r.keyRelease(KeyEvent.VK_F4);
            return "Pass";

        } catch (AWTException e) {
            return "Fail";
        }
    }

    *//**
     * Method Name: SwitchWindows Parameters: Window Return Type: Objective: To
     * switch between Main window and Child Window
     *
     * @throws AWTException
     **//*
    public String SwitchWindows(String Window) {

        try {

            switch (Window) {
                case "Child": {
                    String parentWindowHandle = driver.getWindowHandle();
                    for (String handle : driver.getWindowHandles()) {
                        if (!handle.equals(parentWindowHandle)) {
                            driver.switchTo().window(handle);
                            // TestRunner.test.log(Status.PASS,"<b><I> Switched to child window </b></I>");
                            return "Pass";
                        }
                        break;

                    }
                }
                case "Main": {
                    String childWindowHandle = driver.getWindowHandle();
                    for (String handle : driver.getWindowHandles()) {
                        if (!handle.equals(childWindowHandle)) {
                            driver.switchTo().window(handle);
                            // TestRunner.test.log(Status.PASS,"<b><I> Switched to parent window </b></I>");
                            return "Pass";
                        }
                        break;

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        return "Pass";
    }

    *//**
     * Method Name: ScrollbarPosition Parameters: Desired Text Return Type: String
     * Objective: Clicks on given 'Element' if the element is displayed.
     **//*

    public String ScrollbarPosition(String DesiredText) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String a = js.executeScript(
                    "document.getScroll= function(){if(window.pageYOffset!= undefined){return [pageYOffset];}else{var sx, sy, d= document, r= d.documentElement, b= d.body; sx= r.scrollLeft || b.scrollLeft || 0; sy= r.scrollTop || b.scrollTop || 0; return [sy]; }};return document.getScroll()")
                    .toString();
            String finalString = a.replaceAll("^\\[|\\]$", "");
            System.out.println("scroll position: " + finalString);

            if (finalString.contains(DesiredText)) {
                System.out.println("Scrollbar Position moved to" + finalString + "position");
                return "Pass";
            }
            return "Fail";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "Fail";
        }
    }

    *//**
     * Method Name: TextShouldPresent Parameters: Element Return Type: Objective:
     * Verifies that elements text in a list is present
     **//*

    public String TextShouldPresent(String Element) {
        boolean chk = true;
        try {
            List<WebElement> clickElementlist = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element)));
            int i;
            for (i = 0; i < clickElementlist.size(); i++) {
                if (!clickElementlist.get(i).getText().isEmpty()) {
                    chk = chk && true;
                } else {
                    System.out.println(
                            "String is Empty on " + i + "th row, text is: " + clickElementlist.get(i).getText());
                    chk = chk && false;
                }
            }
            if (chk)
                return "Pass";

        } catch (Exception e) {
            return "Fail";
        }
        return "Fail";
    }

    *//**
     * Method Name: StringFormatMatcher Parameters: Element Return Type: Objective:
     * Verifies that elements text in a list is present
     **//*

    public String StringFormatMatcher(String Element, String DesiredFormat) {
        boolean chk = true;
        try {
            List<WebElement> clickElementlist = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element)));
            int i;
            for (i = 0; i < clickElementlist.size(); i++) {
                if (!clickElementlist.get(i).getText().matches(DesiredFormat)) {
                    chk = chk && true;
                } else {
                    System.out.println("String is not in desire format on " + i + "th row, text is: "
                            + clickElementlist.get(i).getText());
                    chk = chk && false;
                }
            }
            if (chk)
                return "Pass";

        } catch (Exception e) {
            return "Fail";
        }
        return "Fail";
    }

    *//**
     * Method Name: Tooltip Parameters: HoverElement, Tootltip, Text Return Type:
     * Objective: Verify the text in tool tip
     **//*

    public String Tooltip(String HoverElement, String Tootltip, String Text) {
        try {
            WebElement LocatorFrom = driver.findElement(By.xpath(PropertiesUtil.getProperty(HoverElement)));

            Actions clicker = new Actions(driver);
            Thread.sleep(3000);
            clicker.clickAndHold(LocatorFrom).moveToElement(LocatorFrom, 20, 0).build().perform();
            Thread.sleep(5000);
            // ((HasInputDevices)
            // driver).getMouse().mouseUp(((Locatable)LocatorFrom).getCoordinates());
            Thread.sleep(5000);
            WebElement LocatorTooltip = driver.findElement(By.xpath(PropertiesUtil.getProperty(Tootltip)));
            String tootlipText = LocatorTooltip.getText();
            System.out.println("Tooltip text: " + tootlipText);
            if (tootlipText.equalsIgnoreCase(Text) || tootlipText.contains(Text))
                // TestRunner.test.log(Status.PASS,"<b><I>"+tootlipText+" contains
                // "+Text+"</b></I>");
                return "Pass";
        } catch (Exception e) {// TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        return "Fail";
    }

    *//**
     * Method Name: VerifySelectDeselectAll Parameters: Element1, Element2 Return
     * Type: Objective: To verify select all, de-select all feature for multiple
     * check boxes.
     *
     * @throws AWTException
     **//*
    public String VerifySelectDeselectAll(String Element, String Option) {
        try {
            List<WebElement> chkboxes = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty(Element).replaceAll("%", "*")));
            int count = chkboxes.size();
            Boolean chk1 = true;
            Boolean chk2 = false;
            for (int i = 1; i <= count; i++) {
                System.out.println("Checking for " + Element + " Checkbox's state");
                WebElement element1 = driver.findElement(
                        By.xpath(PropertiesUtil.getProperty(Element).replaceAll("%", Integer.toString(i))));
                chk1 = chk1 && element1.isSelected();
                chk2 = chk2 || element1.isSelected();

            }
            if (Option.equals("checked")) {
                if (chk1)
                    System.out.println("All Checkboxes are Checked");
                // TestRunner.test.log(Status.PASS,"<b><I> All Checkboxes are Checked
                // </b></I>");
                return "Pass";
            } else if (Option.equals("unchecked")) {
                if (!chk2)
                    System.out.println("All Checkboxes are UnChecked");
                // TestRunner.test.log(Status.PASS,"<b><I> All Checkboxes are Unchecked
                // </b></I>");
                return "Pass";
            }
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            e.printStackTrace();

        }
        return "Fail";
    }

    *//**
     * Method Name: VerifyOrderOfElements Parameters: ElementPath1, ElementPath2
     * Return Type: String Objective: Perform Order Of Elements.
     **//*

    public String VerifyOrderOfElements(String ElementPath1, String ElementPath2) {
        try {
            boolean chk1 = true, chk2 = true;
            List<WebElement> options1 = driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath1)));
            List<WebElement> options2 = driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath2)));
            if (options1.size() != options2.size())
                return "Fail";
            for (int i = 0; i < options1.size(); i++) {
                if (!options1.get(i).getText().equals(options2.get(i).getText())) {
                    System.out.println("Values are not equal for element 1: " + options1.get(i).getText());
                    System.out.println("Values are not equal for element 2: " + options2.get(i).getText());
                    chk1 = false;
                }
            }

            if (chk1)
                return "Fail";
            else
                return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }

    }

    *//**
     * Method Name: copyPasteTextFromFile Parameters: Text to copy, TextBoxElement
     * Return Type: String Objective: Perform copy and paste of text
     **//*

    public String copyPasteTextFromFile(String TextBoxElement) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(
                    "C:\\Windows\\notepad.exe C:\\Users\\viskumar\\WorkSpace\\Discover\\src\\cpa\\qa\\xls\\File\\username.txt");
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_A);
            r.keyRelease(KeyEvent.VK_A);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_C);
            r.keyRelease(KeyEvent.VK_C);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_ALT);
            r.keyPress(KeyEvent.VK_F4);
            r.keyRelease(KeyEvent.VK_F4);
            r.keyRelease(KeyEvent.VK_ALT);
            driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement)))
                    .sendKeys(Keys.chord(Keys.CONTROL, "v"));

            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }

    }

    *//**
     * Method Name: FileUpload Parameters: Element, Filepath Return Type: String
     * Objective: Apply all types of search term and perform search
     **//*
    public String FileUpload(String Element, String file_name) {
        try {
            waitUntilAngularReady();
            
             * driver.findElement(By.xpath(PropertiesUtil.getProperty("txt_message"))).click
             * (); waitUntilAngularReady();
             
            // String
            // Filepath=(System.getProperty("user.dir")).toLowerCase()+"\\upload\\"+file_name;
            String Filepath = System.getProperty("user.dir") + "\\upload\\" + file_name;
            System.out.println(Filepath);
            System.out.println(PropertiesUtil.getProperty(Element));
            // driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).sendKeys(Filepath+".pdf");
            driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).sendKeys(Filepath);
            waitUntilAngularReady();
            // driver.findElement(By.xpath("//button[text()='Save']")).click();
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    *//**
     * Method Name: Wait Parameters: Element Return Type: String Objective: Verify
     * existence of given element
     **//*

    public String Wait(String ms) {

        try {
            // String s="1000";
            Thread.sleep(Long.parseLong(ms));
            //// waitAll(100);
            System.out.println(" Waiting .... ");
            // TestRunner.test.log(Status.PASS,"<b><I> Wait </b></I>");
            return "Pass";
        } catch (Exception e) {
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    *//**
     * Method Name: Wait Parameters: Element Return Type: String Objective: Verify
     * existence of given element
     **//*

    public String SetProperty(String Variable) {
        Properties properties = System.getProperties();
        System.out.println(" The global variable is " + properties.getProperty("Global"));
        try {
            properties.setProperty(Variable, properties.getProperty("Global"));
            System.out.println(" The global variable is " + properties.getProperty(Variable));
            // TestRunner.test.log(Status.PASS," The global variable is
            // "+"<b><I>"+properties.getProperty(Variable)+"</b></I>");
            return "Pass";
        } catch (Exception e) {
            return "Fail";
        }
    }

    public String DeleteAllKeyword() {

        List<WebElement> keywords = driver.findElements(By.xpath(".//*[@ng-click='deleteKeyword($index)']"));

        try {
            int KeyCount = keywords.size();

            for (int i = 1; i <= KeyCount; i++) {
                driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_KeyHigh_Delete_First"))).click();
            }
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    public String JumptoLastPage() {
        try {
            String totalPages = driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Pages_Total"))).getText()
                    .substring(10);
            System.out.println(totalPages);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Jumptopage_txt"))).sendKeys(totalPages);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Jumptopage_Gobtn"))).click();
            String ActivePageafterJumpto = driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Actv_Page_lnk")))
                    .getText();

            if (ActivePageafterJumpto.equals(totalPages))
                return "Pass";

            else
                return "Fail";
        } catch (Exception e) {
            return "Fail";
        }

    }

    *//**
     * Method Name: GetTextExport Parameters: Element1, Element2 Return Type: String
     * Objective: return pass if Element1 text matches with the Element2 text.
     **//*

    public String GetTextExport(String Element1, String Element2) {

        try {
            String clickChckboxText = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1))).getText();
            System.out.println("Text Found: " + clickChckboxText);
            List<WebElement> selectedchckboxes = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element2)));
            Iterator<WebElement> itr = selectedchckboxes.iterator();
            List<String> elementTexts = new ArrayList<String>();
            while (itr.hasNext()) {
                elementTexts.add(itr.next().getText().trim());
            }
            System.out.println("Element Text Values: " + elementTexts);
            if (elementTexts.contains(clickChckboxText)) {
                return "Pass";
            } else {
                return "Fail";
            }
        } catch (Exception e) {

            return "Fail";
        }
    }

    // Private function to for Dynamic wait of 20 Sec
    private void DynamicWait(String Element) {
        int a = 1;
        while (a < 20) {
            try {
                if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isDisplayed()) {
                    Thread.sleep(1000);
                    System.out.println("incremented: " + a);
                    a++;
                } else
                    break;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if (a == 20) {
            System.out.println("Navigators are not updated due to Application error");
        }
    }

    public String Exportverification(String Element, String Type, String Msg1, String Msg2) {
        try {
            // driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
            driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).click();

            switch (Type) {

                case ("Sync"): {
                    Thread.sleep(5000);
                    String TXT = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getText();
                    // System.out.println(TXT);
                    if (TXT.equalsIgnoreCase(Msg1) || TXT.equalsIgnoreCase(Msg2)) {

                        System.out.println("Export successful");
                        FileDownloadPopUp();
                    } else
                        System.out.println("Export not started");
                    return "Fail";
                }

                case ("Async"):

            }
            return "Pass";

        } catch (Exception e) {

            e.printStackTrace();
            return "Fail";
        }
    }

    // private function to check presence of JavaScript error
    
     * Function name :GetTableValuesbyColumn Parameter: null Return Type: String
     * Objective: Checks/handle JavaScript alert whenever login() is called.
     

    public String GetTableCellValueColum(String TableName, String Value, String Colnum) {
        int rnum, cnum;
        String status = "Fail";
        try {
            Thread.sleep(5000);
            WebElement table_header = driver.findElement(By.xpath(PropertiesUtil.getProperty(TableName)));
            List<WebElement> tr_collection = table_header.findElements(By.tagName("tr"));
            System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
            rnum = 1;
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            for (WebElement trElement : tr_collection) {
                List<WebElement> th_collection = trElement.findElements(By.tagName("td"));
                System.out.println("NUMBER OF COLUMNS=" + th_collection.size());
                cnum = Integer.valueOf(Colnum);
                WebElement tdElement = trElement.findElement(By.xpath(".//td[" + cnum + "]"));
                if (Value.isEmpty()) {
                    if (tdElement.getText().toString().isEmpty()) {
                        System.out.println("row # " + rnum + ", col # " + cnum + " is empty");
                    } else {
                        System.out.println("No Match Found as row # " + rnum + ", col # " + cnum + " Value: "
                                + tdElement.getText());
                        // TestRunner.test.log(Status.FAIL,"No Match Found as row # "+rnum+", col #
                        // "+cnum+ " Value: "+tdElement.getText());
                        return "Fail";
                    }
                } else if (tdElement.getText().toString().contains(Value)) {
                    System.out.println("row # " + rnum + ", col # " + cnum + " Value: " + tdElement.getText()
                            + " contains the desired text");
                    // TestRunner.test.log(Status.PASS,"row # "+rnum+", col # "+cnum+ " Value:
                    // "+tdElement.getText()+" contains the desired text");

                } else {
                    System.out.println("No Match found between the compared value and actual cell value");
                    // TestRunner.test.log(Status.FAIL,"<b><I> No Match found between the compared
                    // value and actual cell value </b></I>");
                    return "Fail";
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            status = "Fail";
        }
        return "Pass";
    }

    // private function to check presence of JavaScript error
    
     * Function name :ValidateDefaultSearch Parameter: parent, child Return Type:
     * String Objective: Checks/handle JavaScript alert whenever login() is called.
     

    public String ValidateDefaultSearch(String parent, String child) {
        String header;
        String val;
        try {
            Thread.sleep(3000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btnSearch"))).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@href='#/" + parent + "/" + child + "/search']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("SearchIcon"))).click();
            Thread.sleep(6000);
            System.out.println("Heading of Search screen is : "
                    + driver.findElement(By.xpath(PropertiesUtil.getProperty("PP_Header"))).getText());
            if (driver.findElement(By.xpath(PropertiesUtil.getProperty("PP_Header"))).getText()
                    .contains("Search Results (showing 1-")) {
                int colNumber = driver
                        .findElements(
                                By.xpath("(.//*[@ng-table='tableParams']//tbody//span[1]//a[1])[1]//preceding::td"))
                        .size() + 1;
                header = driver.findElement(By.xpath("//th[" + colNumber + "]")).getText();
                val = driver.findElement(By.xpath("(.//*[@ng-table='tableParams']//tbody//span[1]//a[1])[1]"))
                        .getText();
                System.out.println("Compared value of " + header + " : " + val);
                driver.findElement(By.xpath("(.//*[@ng-table='tableParams']//tbody//span[1]//a[1])[1]")).click();
                Thread.sleep(6000);
                String[] splitHeader = StringUtils.split(header);
                if (driver
                        .findElements(
                                By.xpath("//label[text()='" + WordUtils.capitalize(header) + "']//following::input[1]"))
                        .size() == 1) {
                    header = header;
                } else if (driver
                        .findElements(By.xpath(
                                "//label[text()='" + WordUtils.capitalize(splitHeader[0]) + "']//following::input[1]"))
                        .size() == 1) {
                    header = splitHeader[0];
                } else if (driver
                        .findElements(By.xpath(
                                "//label[text()='" + WordUtils.capitalize(splitHeader[1]) + "']//following::input[1]"))
                        .size() == 1) {
                    header = splitHeader[1];
                }
                if (!driver
                        .findElement(
                                By.xpath("//label[text()='" + WordUtils.capitalize(header) + "']//following::div[2]"))
                        .getText().equals(val)) {
                    String comparedText = (String) ((JavascriptExecutor) driver)
                            .executeScript("return arguments[0].value;", driver.findElement(By.xpath(
                                    "//label[text()='" + WordUtils.capitalize(header) + "']//following::input[1]")));
                    // String comparedText=(String) ((JavascriptExecutor)
                    // driver).executeScript("return
                    // arguments[0].value;",driver.findElement(By.xpath("//label[text()='"+WordUtils.capitalize("Number")+"']//following::input[1]")));

                    if (!comparedText.isEmpty()) {
                        if (!comparedText.equals(val)) {
                            System.out.println(
                                    "No match found between Comapred Reference Number. Value is :" + comparedText);
                            return "Fail";
                        } else {
                            System.out.println(
                                    "Match found between Comapred Reference Number. Value is :" + comparedText);
                            return "Pass";
                        }
                    }
                } else {
                    System.out.println("Match found between Comapred Reference Number. Value is :" + driver
                            .findElement(By
                                    .xpath("//label[text()='" + WordUtils.capitalize(header) + "']//following::div[2]"))
                            .getText());
                    return "Pass";
                }

            } else if (driver.findElement(By.xpath(PropertiesUtil.getProperty("PP_Header"))).getText()
                    .contains("Search Results (showing 0-")) {
                System.out.println("Search Results are empty");
                return "Fail";
            } else {
                System.out.println("Search Screen not displayed");
                return "Fail";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Fail";
        }
        return "Fail";
    }

    
     * Method Name: GetTableCellValueByColName Parameters: TableName ,Value,Colname
     * Return Type: String Objective: Public function to validate the values of
     * Column name as passed
     
    public String GetTableCellValueByColName(String TableName, String Value, String Colname, String compareType) {
        int rnum, cnum;
        // Properties properties = System.getProperties();
        String status = "Fail";
        int column = 0;
        int failCounter = 0;
        String comparedText = "";
        Properties properties = System.getProperties();
        String fld = properties.getProperty(Value);
        String val;

        if (fld != null) {
            Value = fld;
        } else {
            Value = Value.toString();
        }

        try {
            Thread.sleep(5000);
            WebElement table_header = driver.findElement(By.xpath(PropertiesUtil.getProperty(TableName)));
            List<WebElement> tr_collection = table_header.findElements(By.tagName("tr"));
            System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
            rnum = 1;
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            cnum = 1;
            for (WebElement trElement : tr_collection) {
                List<WebElement> th_collection = trElement.findElements(By.tagName("th"));
                System.out.println(trElement.findElements(By.tagName("th")).size());
                System.out.println("NUMBER OF COLUMNS=" + th_collection.size());

                for (WebElement tdElement : th_collection) {
                    System.out.println("row # " + rnum + ", col # " + cnum + " Value: " + tdElement.getText());
                    cnum++;
                    if (tdElement.getText().toString().toLowerCase().contains(Colname.toLowerCase())) {
                        System.out.println(Colname + "   is found successfully ! ");
                        column = cnum;
                        status = "Pass";
                        break;
                    } else if (cnum > th_collection.size()) {
                        System.out.println(Colname + "  Column Name not found in the table");
                        // TestRunner.test.log(Status.FAIL,"<b><I> Column Name not found in the table
                        // </b></I>");
                        return status;
                    }
                }
                break;
            }

            switch (compareType) {
                case "":
                    cnum = Integer.valueOf(column);
                    if (status == "Pass") {
                        for (WebElement tdElement : tr_collection) {
                            cnum = column - 1;
                            if ((table_header.findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]//input[1]")))
                                    .size() > 0) {
                                WebElement txElement = table_header
                                        .findElement(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]//input[1]"));
                                comparedText = (String) ((JavascriptExecutor) driver)
                                        .executeScript("return arguments[0].value;", txElement);
                            } else if (table_header
                                    .findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span//span"))
                                    .size() > 0) {
                                WebElement txElement = table_header
                                        .findElement(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span//span"));
                                comparedText = txElement.getText();
                            }
                            if (table_header.findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]//a[1]"))
                                    .size() > 0) {
                                WebElement txElement = table_header
                                        .findElement(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]//a[1]"));
                                comparedText = txElement.getText();
                            }
                            if (Value.isEmpty()) {
                                if (comparedText.toString().isEmpty()) {
                                    System.out.println("row # " + rnum + ", col # " + cnum + " is empty");
                                } else {
                                    System.out.println("No Match Found as row # " + rnum + ", col # " + cnum + " Value: "
                                            + comparedText);
                                    status = "Fail";
                                }
                            } else if (comparedText.toString().contains(Value)) {
                                System.out.println("row # " + rnum + ", col # " + cnum + " Value: " + comparedText
                                        + " contains the desired text");
                                
                                 * properties.setProperty(fieldName, comparedText.trim());
                                 * System.out.println(properties.getProperty(fieldName));
                                 
                                status = "Pass";
                                break;
                            } else {
                                System.out.println(
                                        "No Match found between the compared value and actual cell value. Text found at "
                                                + rnum + " and at column " + cnum + " is " + comparedText);
                                status = "Fail";
                            }
                            rnum++;
                        }
                    }
                    break;
                case "verifyText":
                    cnum = Integer.valueOf(column);
                    if (status == "Pass") {
                        for (WebElement tdElement : tr_collection) {
                            if (tr_collection.size() == rnum) {
                                break;
                            }
                            cnum = column - 1;
                            if (table_header.findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]//a[1]"))
                                    .size() > 0) {
                                WebElement txElement = table_header
                                        .findElement(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]//a[1]"));
                                comparedText = txElement.getText();
                            } else if (table_header
                                    .findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span/span")).size() > 0) {
                                WebElement txElement = table_header
                                        .findElement(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span/span"));
                                comparedText = txElement.getText();
                            } else if (table_header
                                    .findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span//input"))
                                    .size() > 0) {
                                WebElement txElement = table_header
                                        .findElement(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span//input"));
                                String existingFile = (String) ((JavascriptExecutor) driver)
                                        .executeScript("return arguments[0].value;", txElement);
                                comparedText = existingFile;
                            }
                            // Special case to check the presence of Image in Conflict row
                            else if (table_header.findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span/i[2]"))
                                    .size() > 0 && Value.equals("ImagePresent")) {
                                System.out.println("Image is displayed");
                                comparedText = "ImagePresent";
                            } else if (table_header
                                    .findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span/i[2]")).size() < 0
                                    && Value.equals("ImagePresent")) {
                                System.out.println("Image not displayed");
                                comparedText = "ImageNotPresent";
                            } else if (table_header.findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/p"))
                                    .size() > 0) {
                                WebElement txElement = table_header
                                        .findElement(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/p"));
                                comparedText = txElement.getText();
                            } else if (table_header.findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span"))
                                    .size() > 0) {
                                WebElement txElement = table_header
                                        .findElement(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span"));
                                comparedText = txElement.getText();
                            }

                            if (Value.isEmpty()) {
                                if (comparedText.toString().isEmpty()) {
                                    System.out.println("row # " + rnum + ", col # " + cnum + " is empty");
                                } else {
                                    System.out.println("No Match Found as row # " + rnum + ", col # " + cnum + " Value: "
                                            + comparedText);
                                    status = "Fail";
                                    failCounter = 1;
                                }
                            } else if (comparedText.contains(Value)) {
                                System.out.println("row # " + rnum + ", col # " + cnum + " Value: " + comparedText
                                        + " contains the desired text");
                                
                                 * properties.setProperty(fieldName, comparedText.trim());
                                 * System.out.println(properties.getProperty(fieldName));
                                 
                                status = "Pass";
                            } else {
                                System.out.println(
                                        "No Match found between the compared value and actual cell value. Text found at "
                                                + rnum + " and at column " + cnum + " is " + comparedText);
                                status = "Fail";
                                failCounter = 1;
                            }

                            rnum++;

                        }
                    }
                    if (failCounter == 1) {
                        return "Fail";
                    }
                    break;

                case "verifyExistence":
                    for (WebElement tdElement : tr_collection) {
                        if (tr_collection.size() == rnum)
                            break;
                        cnum = column - 1;
                        if (table_header.findElements(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span/span"))
                                .size() > 0) {
                            WebElement txElement = table_header
                                    .findElement(By.xpath(".//tr[" + rnum + "]//td[" + cnum + "]/span/span"));
                            comparedText = txElement.getText();
                            System.out.println("Desired Field exist at " + rnum + " and at column " + cnum
                                    + " and the text is " + comparedText);
                            status = "Pass";
                        } else {
                            System.out.println("Desired field does not exist");
                            status = "Fail";
                        }
                        rnum++;
                    }
                    break;

            }
        } catch (Exception ex) {
            System.out.println(ex);
            status = "Fail";
        }
        return status;
    }

    
     * Method Name: LoginAgain Parameters: usrName ,password,driverFlag Return Type:
     * String Objective: Public function to Login in the application with the
     * username and password passed
     

    public String LoginAgain(String usrName, String password, String driverFlag) {
        // String a = null;
        try {
            if (driverFlag != "") {
                driver1.findElement(By.xpath("//input[@class='LoginEdit']")).clear();
                WebElement userNameElement = driver1.findElement(By.xpath("//input[@class='LoginEdit']"));
                JavascriptExecutor js = (JavascriptExecutor) driver1;
                String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
                js.executeScript(scriptSetAttrValue, userNameElement, "value", usrName);
                driver1.findElement(By.xpath("//input[@id='_ctl0_txtPasswordClientInput']")).clear();
                WebElement passwordElement = driver1
                        .findElement(By.xpath("//input[@id='_ctl0_txtPasswordClientInput']"));
                JavascriptExecutor js1 = (JavascriptExecutor) driver1;
                String scriptSetAttrValue1 = "arguments[0].setAttribute(arguments[1],arguments[2])";
                js1.executeScript(scriptSetAttrValue, passwordElement, "value", password);
                WebElement LoginButton = driver1.findElement(By.xpath("//label[@title='Login']"));
                JavascriptExecutor executor = (JavascriptExecutor) driver1;
                executor.executeScript("arguments[0].click();", LoginButton);
                // WebDriverWait waiter = new WebDriverWait(driver1, 20);
                // waitAll(100);
                // WebElement element =
                // waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title='Home']")));
                return "Pass";

            } else {
                String LoginURL = driver.getCurrentUrl();
                System.out.println("url--> " + LoginURL);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).clear();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).sendKeys("");
                driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).clear();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).sendKeys(usrName);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("btnSubmit"))).click();
                // waitAll(100);
                // dynamicWaiting("10", "visible", PropertiesUtil.getProperty("password"));
                driver.findElement(By.xpath(PropertiesUtil.getProperty("password"))).clear();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("password"))).sendKeys(password);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("btnSubmit"))).click();
                // String NewURL = driver.getCurrentUrl();
                // System.out.println("New URL now==> "+NewURL);
                // waitAll(100);
                String a = VerifyExistence("IPPlatformLogo");
                if (a.equalsIgnoreCase("Pass")) {
                    System.out.println("Logged in Successfully");
                    return "Pass";
                } else
                    return "Fail";
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return "Fail";
        }
    }

    
     * Method Name: SplitText Parameters: element
     * ,splitter,ElementLocation,fieldName Return Type: String Objective: Public
     * function to split the Text of the element as passed with the splitter and
     * will return the splitted valued in the fieldName variable
     

    public String SplitText(String element, String splitter, String ElementLocation, String fieldName) {
        Properties properties = System.getProperties();
        String extractedString;
        int location = Integer.parseInt(ElementLocation);
        try {
            WebElement webelement = driver.findElement(By.xpath(PropertiesUtil.getProperty(element)));
            extractedString = webelement.getText();
            System.out.println(extractedString);
            String[] splitArr = StringUtils.split(extractedString, splitter);
            for (int i = 0; i < splitArr.length; i++) {
                System.out.println(i + ") " + splitArr[i]);
            }
            properties.setProperty(fieldName, splitArr[location].trim());
            System.out.println("Exit Text " + properties.getProperty(fieldName));
            // TestRunner.test.log(Status.PASS,"<b><I>"+properties.getProperty(fieldName)+"</b></I>
            // is the splitted value");
            return "Pass";
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }
    
     * Method Name: OpenBrowser2 Parameters: browser Return Type: String Objective:
     * Public function used to launch a broswer
     

    public String OpenBrowser2(String browser) throws Exception {
        browserType = browser;

        if (browser.equals("Mozilla")) {
            // File pathToBinary = new File("C:\\Program Files\\Mozilla
            // Firefox\\firefox.exe");
            // FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
            // FirefoxProfile firefoxProfile = new FirefoxProfile();
            // driver = new FirefoxDriver(ffBinary,firefoxProfile);
            driver1 = new FirefoxDriver();
        } else if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver1 = new ChromeDriver(capability);
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("test");

        } else if (browser.equals("IE")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\IEDriverServer.exe");
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            caps.setCapability("ignoreZoomSetting", true);
            caps.setCapability("nativeEvents", false);
            driver1 = new InternetExplorerDriver(caps);
            // DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            // capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
            // true);
            // capabilities.setCapability("requireWindowFocus", true);
            // driver = new InternetExplorerDriver(capabilities);
            // driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // TestRunner.test.log(Status.PASS,"<b><I>"+browser+" browser launched
        // successfully</b></I>");
        return "Pass";
    }
    
     * Method Name: Launching Parameters: url Return Type: String Objective: Public
     * function used to launch a URL through javascript executor
     

    public String Launching(String url) {

        try {
            // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            Properties properties = System.getProperties();
            String fld = properties.getProperty(url);
            String val;
            if (fld != null) {
                url = fld;
                System.out.println(url);
            } else {
                url = url.toString();
            }
            JavascriptExecutor js = (JavascriptExecutor) driver1;
            if (driver1 instanceof JavascriptExecutor) {
                js.executeScript("window.location = \'" + url + "\'");
            }
            
             * WebDriverWait waiter = new WebDriverWait(driver1, 20); WebElement element =
             * waiter.until(ExpectedConditions.presenceOfElementLocated(By.
             * xpath("//h4[contains(text(),'Welcome to Memotech')]")));
             
            // Thread.sleep(15000);
            // driver1.navigate().to(url);
        } catch (Exception ex) {
            System.out.println(ex);
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
        String windowtitle = driver1.getTitle();
        // if(browserType.equals("IE")){
        // driver.navigate().to("javascript:document.getElementById('overridelink').click()");
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // }
        if (windowtitle != null) {
            // TestRunner.test.log(Status.PASS,"<b><I>"+windowtitle+"</b></I> is the title
            // of the window");
            return "Pass";
        } else
            // TestRunner.test.log(Status.FAIL,"<b><I> No window title present </b></I>");
            return "Fail";

    }

    
     * Method Name: ValidateSearchFields Parameters: parent,data Return Type: String
     * Objective: Public function used to validate the fields on search screen
     

    public String ValidateSearchFields(String parent, String data) {
        try {
            String flag = "Pass";
            String[] val = data.split(";");
            String targetField = "";
            // val[0] gives the Label Name
            if (parent != "") {
                switch (val[1]) {
                    case "Span":
                        if (!(driver.findElements(By.xpath("//h3[contains(text(),'" + parent
                                + "')]//following::span[contains(text(),'" + val[0] + "')]")).size() == 0)) {
                            targetField = "//h3[contains(text(),'" + parent + "')]//following::span[contains(text(),'"
                                    + val[0] + "')]";
                            System.out.println(targetField);
                        } else if (!(driver.findElements(By.xpath("//translate[contains(text(),'" + parent
                                + "')]//following::span[contains(text(),'" + val[0] + "')]")).size() == 0)) {
                            targetField = "//translate[contains(text(),'" + parent
                                    + "')]//following::span[contains(text(),'" + val[0] + "')]";
                            System.out.println(targetField);
                        }
                        break;
                    case "Column":
                        targetField = "//h3[contains(text(),'" + parent + "')]//following::th[contains(text(),'" + val[0]
                                + "')]";
                        break;
                    case "Field":
                        targetField = "//h3[contains(text(),'" + parent + "')]//following::label[contains(text(),'" + val[0]
                                + "')]";
                        break;
                    case "Division":
                        targetField = "//h3[contains(text(),'" + parent + "')]//following::div[contains(text(),'" + val[0]
                                + "')]";
                        break;
                    case "FollowLabel":
                        String[] subVal = val[0].split(",");
                        targetField = "//h3[contains(text(),'" + parent + "')]//following::div[contains(text(),'"
                                + subVal[0] + "')]/../following-sibling::div//label[contains(text(),'" + subVal[1] + "')]";
                        break;
                    case "FollowPickList":
                        targetField = "//h3[contains(text(),'" + parent + "')]//following::div[contains(text(),'" + val[0]
                                + "')]/../following-sibling::div//span[contains(@class,'cpa-icon-ellipsis-h')]";
                        System.out.println(targetField);
                        break;

                    default:
                        targetField = "//h3[text()='" + parent + "']//following::label[contains(text(),'" + val[0] + "')]";
                        break;
                }
            } else if (val[2].equalsIgnoreCase("Check box")) {
                if (driver.findElements(By.xpath("//label[@title='" + val[0] + "']")).size() > 0)
                    targetField = "//label[@title='" + val[0] + "']";
                else {
                    targetField = "//span[contains(text(),'" + val[0] + "']";
                }
            } else
                targetField = "//label[text()='" + val[0] + "']";
            if (driver.findElements(By.xpath(targetField)).size() == 0) {
                System.out.println(val[0] + " - Field is not present on UI");
                // TestRunner.test.log(Status.FAIL,val[0]+" - Field is not present on UI");
                flag = "Fail";
                return flag;
            }
            // val[2] gives the Label Type , Val[1] gives the Operator value
            if (val[2].equals("")) {
                switch (val[2]) {
                    case "Radio button":
                        if (!driver
                                .findElement(By.xpath(targetField + "//following::label[@title='" + val[1] + "']/input[1]"))
                                .isSelected()) {
                            flag = "Fail";
                            return flag;
                        }
                        break;
                    case "Check box":
                        break;
                    default:
                        String dropDownField = "(" + targetField + "//following::select[1])[1]";
                        Select select = new Select(driver.findElement(By.xpath(dropDownField)));
                        String selectedText = select.getFirstSelectedOption().getText();
                        if (!selectedText.equalsIgnoreCase(val[1])) {
                            flag = "Fail";
                            return flag;
                        }
                        break;
                }
                // validating the Field type

                switch (val[2]) {
                    case "Pick list":

                        if (driver
                                .findElements(By.xpath(
                                        "(" + targetField + "//following::div[2]//span[contains(@class,'ellipsis-h')])[1]"))
                                .size() == 0) {
                            System.out.println(val[0] + " - Field is not PickList");
                            // TestRunner.test.log(Status.FAIL,val[0]+" - Field is not PickList");
                            flag = "Fail";
                            return flag;
                        }
                        break;
                    case "Text":
                        if (driver.findElements(By.xpath("(" + targetField + "//following::div[2]//input[1])[1]"))
                                .size() == 0) {
                            System.out.println(val[0] + " - Field is not Text field");
                            // TestRunner.test.log(Status.FAIL,val[0]+" - Field is not Text field");
                            flag = "Fail";
                            return flag;
                        }
                        break;
                    case "Radio button":
                        if (driver
                                .findElements(By.xpath("(" + targetField + "//following::div[1]//input[@type='radio'])[1]"))
                                .size() == 0) {
                            System.out.println(val[0] + " - Field is not radio button");
                            // TestRunner.test.log(Status.FAIL,val[0]+" - Field is not radio button");
                            flag = "Fail";
                            return flag;
                        }
                        break;
                    case "Check box":
                        if (driver.findElements(By.xpath(targetField + "/input")).size() != 0) {
                            if (!driver.findElement(By.xpath(targetField + "/input")).getAttribute("type")
                                    .equalsIgnoreCase("checkbox")) {
                                System.out.println(val[0] + " - Field is not checkBox");
                                // TestRunner.test.log(Status.FAIL,val[0]+" - Field is not checkBox");
                                flag = "Fail";
                                return flag;
                            }
                        } else {
                            System.out.println("Checkbox does not exists");
                            // TestRunner.test.log(Status.FAIL,"<b><I> Checkbox does not exists </b></I>");
                            flag = "Fail";
                            return flag;
                        }
                        break;
                    case "Date":
                        if (driver
                                .findElements(By.xpath("(" + targetField
                                        + "//following::div[2]//span[@class='cpa-icon cpa-icon-calendar'])[1]"))
                                .size() == 0) {
                            System.out.println(val[0] + " - Field is not date");
                            // TestRunner.test.log(Status.FAIL,val[0]+" - Field is not date");
                            flag = "Fail";
                            return flag;
                        }
                        break;
                    default:
                        System.out.println("Requires handling!");
                        // TestRunner.test.log(Status.FAIL,"Requires handling");
                        break;
                }
            }
            return flag;

        } catch (Exception e) {
            System.out.println(e);
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
            // TODO: handle exception
        }
    }

    *//**
     * Method Name: JavaScriptExecutor Parameters: webelment,action Return Type:
     * String Objective: Public function used to perform ajavscript opeartions
     *//*
    public String JavaScriptExecutor(String webelment, String action) {
        String Flag = "Pass";

        try {
            Properties properties = System.getProperties();
            
             * String fld = properties.getProperty(webelment); String val; if (fld != null)
             * val = fld; else val = webelment.toString();
             
            switch (action) {
                case "click":
                    WebElement element = driver.findElement(By.xpath(PropertiesUtil.getProperty(webelment)));
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", element);
                    break;

                case "getText":
                    WebElement elementtext = driver.findElement(By.xpath(webelment));
                    return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;", elementtext);

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Need handling");
            Flag = "Fail";
        }
        return Flag;

    }

    
     * Method Name: CaseSearch Parameters: searchElement,searchLabel,operator,data
     * Return Type: String Objective: Public function used to search the case
     

    public String CaseSearch(String searchElement, String searchLabel, String operator, String data) {
        // Parent and child node should be separated through ;
        String[] val = searchElement.split(";");
        String parent = val[0];
        String child = val[1];
        // More than one searched label is separated through ;
        String[] LabelElement = searchLabel.split(";");
        // data to be searched is separated through ;
        String[] SearchData = data.split(";");
        String[] OperatorVal = operator.split(";");
        String Flag = "Pass";
        try {
            if (!child.equals(" ")) {
                Thread.sleep(1000);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("btnSearch"))).click();
                Thread.sleep(3000);
                driver.findElement(By.xpath("//a[@href='#/" + parent + "/" + child + "/search']")).click();
                Thread.sleep(3000);
            } else
                driver.findElement(By.xpath("//a[@href='#/" + parent + "/" + "search']")).click();
            Thread.sleep(3000);
            for (int i = 0; i <= LabelElement.length - 1; i++) {
                Properties properties = System.getProperties();
                String fld = properties.getProperty(SearchData[i]);
                if (fld != null) {
                    SearchData[i] = fld;
                } else {
                    SearchData[i] = SearchData[i].toString();
                }
                // Topic and Label is splitted by symbol(-)
                String[] Labels = LabelElement[i].split("-");

                String ParentLabel = Labels[0];
                String ChildLabel = Labels[1];
                Select dropdown = new Select(driver.findElement(By.xpath("//h3[text()='" + ParentLabel
                        + "']//following::label[text()='" + ChildLabel + "']//following::div[1]//select[1]")));
                dropdown.selectByVisibleText(OperatorVal[i]);
                Thread.sleep(1000);
                if (driver
                        .findElements(By.xpath("(//h3[text()='" + ParentLabel + "']//following::label[text()='"
                                + ChildLabel + "']//following::div[2]//span[contains(@class,'ellipsis-h')])[1]"))
                        .size() > 0) {
                    String Field = "(//h3[text()='" + ParentLabel + "']//following::label[text()='" + ChildLabel
                            + "']//following::div[2]//span[contains(@class,'ellipsis-h')])[1]";
                    System.out.println(ChildLabel + " - Field is PickList");
                    driver.findElement(By.xpath(Field)).click();
                    Thread.sleep(3000);
                    driver.findElement(By.xpath("//input[ @name='PicklistSearch']")).sendKeys(SearchData[i]);
                    Thread.sleep(4000);
                    driver.findElement(By.xpath("//button[@type='submit']")).click();
                    Thread.sleep(7000);
                    driver.findElement(By.xpath(".//*[@class='container-fluid']//tr[1]//td[1]")).click();
                    Thread.sleep(3000);
                } else if (driver.findElements(By.xpath("(//h3[text()='" + ParentLabel + "']//following::label[text()='"
                        + ChildLabel + "']//following::div[2]//input[1])[1]")).size() > 0) {
                    String Field = "(//h3[text()='" + ParentLabel + "']//following::label[text()='" + ChildLabel
                            + "']//following::div[2]//input[1])[1]";
                    System.out.println(ChildLabel + " - Field is Input");
                    driver.findElement(By.xpath(Field)).sendKeys(SearchData[i]);
                    Thread.sleep(3000);
                } else {
                    System.out.println("Requires handling");
                    Flag = "Fail";
                    // TestRunner.test.log(Status.FAIL,"<b><I> Requires handling </b></I>");
                    return Flag;
                }
            }
            driver.findElement(By.xpath(PropertiesUtil.getProperty("SearchIcon"))).click();
            Thread.sleep(4000);

        } catch (Exception e) {
            System.out.println(e);
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
            // TODO: handle exception
        }
        return Flag;

    }

    
     * Method Name: GetDataProperty Parameters: Elementpath,Property,fieldName
     * Return Type: String Objective: Public function used to get the specific
     * property of Webelment and Store in a Variable
     

    public String GetDataProperty(String Elementpath, String Property, String fieldName) {
        try {
            Properties properties = System.getProperties();
            String fld = properties.getProperty(Property);
            String val;
            if (fld != null) {
                val = fld;
            } else {
                val = Property.toString();
            }
            System.out.println("The Property to be compared is  : " + val);
            if (((WebElement) driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))))
                    .getAttribute(val.toString()) != null) {
                System.out.println("The value of Element Property '" + val + "': " + driver
                        .findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute(val.toString()));
                fld = driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath)))
                        .getAttribute(val.toString());
                properties.setProperty(fieldName, fld);
                // TestRunner.test.log(Status.FAIL,"The Element property <b><I>"+val+"</b></I>
                // exists");
                return "Pass";
            } else {
                System.out.println("The Element property'" + val + "' does not exists");
                // TestRunner.test.log(Status.FAIL,"The Element property <b><I>"+val+"</b></I>
                // does not exists");
                return "Fail";
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    
     *
     
    public String searchStatus(String referenceNumber, String status, String appType, String caseTypeValue,
                               String SearchField) throws InterruptedException, IOException {
        try {
            int searchFieldIndex = 0;
            WebElement searchedValue;
            Properties properties = System.getProperties();
            String fld = properties.getProperty(referenceNumber);
            String val;
            if (fld != null) {
                val = fld;
            } else {
                val = referenceNumber.toString();
            }
            int rowNum = 0;
            waitAll(100);
            dynamicWaiting("15", "visible", "searchButton");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("searchButton"))).click();
            waitAll(100);
//			Thread.sleep(3000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).click();
            waitAll(100);
//			Thread.sleep(3000);
            WebElement caseType = driver.findElement(By.id(PropertiesUtil.getProperty("dd_apptype")));
            caseType.click();
            waitAll(100);
            List<WebElement> appTypeElements = driver.findElements(
                    By.xpath("//ul[@role='menu']//li[@role='menuitem']//a[contains(@ng-click,'caseType.uid')]"));
            System.out.println(appTypeElements.size());
            for (int i = 0; i < appTypeElements.size(); i++) {
                System.out.println(appTypeElements.get(i).getText());
                System.out.println(caseTypeValue);
                if (appTypeElements.get(i).getText().equalsIgnoreCase(caseTypeValue)) {
                    System.out.println(i);
                    searchFieldIndex = i + 1;
                    break;
                }
            }
            driver.findElement(By.xpath(
                    "(//*[text()='" + appType(appType) + "']//following::li/a[text()='" + caseTypeValue + "'])[1]"))
                    .click();
            waitAll(100);
//			Thread.sleep(1000);
            System.out.println("here" + !appType.equalsIgnoreCase("Custom"));
            System.out.println("//a[contains(text(),'" + SearchField + "')]");
            System.out.println("(//*[@ng-if='vm.searchExpanded']//following::button[@id='split-button']//i)["
                    + searchFieldIndex + "]//i");
            switch (appType + "-" + caseTypeValue) {
//			case "Trademark-Direct":
//				driver.findElement(By.xpath("(//*[@ng-if='vm.searchExpanded']//following::button[@id='split-button'])[3]")).click();
//				waitAll(100);
//				break;
                default:
                    driver.findElement(By.xpath("(//*[@ng-if='vm.searchExpanded']//following::button[@id='split-button'])["
                            + searchFieldIndex + "]")).click();
                    waitAll(100);
                    break;
            }

            waitAll(100);
            System.out.println(driver.findElements(By.xpath("//a[contains(text(),'" + SearchField + "')]")).size());
            String searchFieldXpath = appType.equalsIgnoreCase(caseTypeValue) ? appType.toLowerCase()
                    : appType.toLowerCase() + StringUtils.capitalize(caseType(caseTypeValue).replace("-", ""));
            System.out.println("Pushpendra" + searchFieldXpath);
            driver.findElement(By.xpath("(//li[contains(@ng-repeat,'" + searchFieldXpath + "')]//a[contains(text(),'"
                    + FieldValue(SearchField) + "')])[1]")).click();
            waitAll(100);
//			Thread.sleep(3000);
            // }
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).clear();
            waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).sendKeys(val);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_search"))).click();
//			Thread.sleep(3000);
            waitAll(100);
            List<WebElement> elements = driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header")));
            if (driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header"))).size() > 0) {
                List<WebElement> elementStatus = driver
                        .findElements(By.xpath(PropertiesUtil.getProperty("status_rows")));
                for (int i = 0; i < elements.size(); i++) {
                    WebElement result = elements.get(i);
                    if (result.getAttribute("uib-tooltip") != null
                            && result.getAttribute("uib-tooltip").trim().equals(val.trim())
                            && elementStatus.get(i).getText().trim().equals(status.trim())) {
                        System.out.println(
                                "compared text & status for reference found in searched result at row " + i + 1);
                        rowNum = i;
                        return "Pass";
                    } else {

//						driver.findElement(By.xpath("(//*[contains(text(),'"+status+"')]//following::*[contains(@ng-click,'vm')][1])"+(rowNum+1)+"]")).click();
                        // *[contains(text(),'Instructed')]//following::*[contains(@ng-click,'vm')][1]
                        driver.findElement(
                                By.xpath("(//*[contains(@ng-click,'vm') and text()='View'])[" + (rowNum + 1) + "]"))
                                .click();
                        waitAll(100);
//						Thread.sleep(3000);
                        switch (SearchField) {
                            case "Client":
                                // *[contains(@ng-if,'caseInformation')]//following::span[contains(text(),'Client')]//following::div[1]
                                searchedValue = driver.findElement(
                                        By.xpath("//*[contains(@ng-if,'caseInformation')]//following::span[text()='"
                                                + SearchField + "']//following::div[1]"));
//							searchedValue=driver.findElement(By.xpath("(//td[contains(@header-title,'Client name')])[1]"));
                                System.out.println(searchedValue.getText().toLowerCase());
                                System.out.println(val.toLowerCase());
                                break;

                            default:

                                searchedValue = driver.findElements(By
                                        .xpath("(//*[contains(@ng-if,'caseInformation')]//following::span[contains(text(),'"
                                                + SearchField + "')]//following::div[1])[1]"))
                                        .size() > 0
                                        ? driver.findElement(By.xpath(
                                        "//*[contains(@ng-if,'caseInformation')]//following::span[contains(text(),'"
                                                + SearchField + "')]//following::div[1]"))
                                        : driver.findElement(By.xpath(
                                        "//*[contains(@ng-if,'caseInformation')]//following::span[text()='"
                                                + SearchField + "']//following::div[1]"));
                                break;
                        }
                        System.out.println(searchedValue);
                        if (searchedValue.getText().toLowerCase().contains(val.toLowerCase())) {
                            System.out.println("Compared text & status found in the search.");
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_Close"))).click();
                            rowNum = i;
                            waitAll(100);
//							Thread.sleep(2000);
                            return "Pass";
                        } else {
                            System.out.println("Compared text and status is not found");
                            rowNum = i;
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_Close"))).click();
                            waitAll(100);
//							Thread.sleep(2000);
                            return "Fail";

                        }
                    }
                }
            }
            return "Fail";
        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }
    }

    
     *
     
    public String searchStatus1(String referenceNumber, String status, String appType, String caseTypeValue,
                                String SearchField) throws InterruptedException, IOException {
        try {

            String firstddvalue, secondddvalue, ddtext = "";
            WebElement searchedValue;
            Properties properties = System.getProperties();
            String fld = properties.getProperty(referenceNumber);
            String val;
            if (fld != null) {
                val = fld;
            } else {
                val = referenceNumber.toString();
            }

            if (appType.equals("Patent") && caseTypeValue.equals("Post-PCT")) {
                ddtext = "patentPostPctSearchFields";
            } else if (appType.equals("Patent") && caseTypeValue.equals("Direct")) {
                ddtext = "patentDirectSearchFields";
            } else if (appType.equals("Patent") && caseTypeValue.equals("EP Validation")) {
                ddtext = "patentEpValidationSearchFields";
            } else if (appType.equals("Trademark") && caseTypeValue.equals("Direct")) {
                ddtext = "trademarkDirectSearchFields";
                System.out.println(ddtext);
            } else if (appType.equals("Custom") && caseTypeValue.equals("Custom")) {
                ddtext = "customSearchFields";
            }

            if (appType.contentEquals("Trademark")) {
                appType = "Trade Mark";
            }

            int rowNum = 0;
            waitAll(100);
            dynamicWaiting("60", "visible", "searchButton");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("searchButton"))).click();
            waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).click();
            waitAll(100);
            firstddvalue = "(//lh/b[contains(text(),'" + appType + "')]//following::a[text()='" + caseTypeValue
                    + "'])[1]";
            secondddvalue = "//*[contains(@ng-repeat,'" + ddtext + "') and a[contains(text(),'" + SearchField + "')]]";
            System.out.println("ddtext value is " + ddtext);
            System.out.println("First DD XPAth" + firstddvalue);
            System.out.println("Second DD XPAth" + secondddvalue);
            driver.findElement(By.xpath("(//div[@class='input-group-btn dropdown'])[1]")).click();
            driver.findElement(By.xpath(firstddvalue)).click();
            driver.findElement(By.xpath("(//div[@class='input-group-btn dropdown'])[2]")).click();
            driver.findElement(By.xpath(secondddvalue)).click();
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).clear();
            waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).sendKeys(val);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_search"))).click();
            waitAll(100);
            List<WebElement> elements = driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header")));
            if (driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header"))).size() > 0) {
                List<WebElement> elementStatus = driver
                        .findElements(By.xpath(PropertiesUtil.getProperty("status_rows")));
                System.out.println("status row count " + elementStatus.size());
                for (int i = 0; i < elements.size(); i++) {
                    WebElement result = elements.get(i);
                    System.out.println("here is the result " + result.getAttribute("uib-tooltip"));
//				    driver.findElements(By.xpath("(//table[contains(@ng-table,'ableParams')])[1]//td[2]/div")).get(1).getAttribute("uib-tooltip");
                    if (result.getAttribute("uib-tooltip") != null
                            && result.getAttribute("uib-tooltip").trim().equals(val.trim())
                            && elementStatus.get(i).getText().trim().equals(status.trim())) {
                        System.out.println(
                                "compared text & status for reference found in searched result at row " + i + 1);
                        rowNum = i;
                        return "Pass";
                    } else
                        driver.findElement(
                                By.xpath("(//*[contains(@ng-click,'vm') and text()='View'])[" + (rowNum + 1) + "]"))
                                .click();
                    // Thread.sleep(5000);
                    waitAll(100);
                    searchedValue = driver.findElement(By.xpath("(//div[@class='ipf-details']//span[contains(text(),'"
                            + SearchField + "')]//following::div[1])[1]"));
                    System.out.println("Searched value in UI " + searchedValue.getText().toLowerCase());
                    System.out.println("Passed value in code value " + val.toLowerCase());
                    if (searchedValue.getText().toLowerCase().contains(val.toLowerCase())) {
                        System.out.println(" Searched UI " + searchedValue.getText().toLowerCase()
                                + " and Passed code value is " + val.toLowerCase());
                        waitAll(100);
                        SingleClick("btn_Close");
                        return "Pass";
                    } else {
                        System.out.println(" Searched UI " + searchedValue.getText().toLowerCase()
                                + " and Passed code value is " + val.toLowerCase());
                        waitAll(100);
                        SingleClick("btn_Close");
                        return "Fail";
                    }

                }

            }
            if (driver.findElement(By.xpath("btn_Close")).isDisplayed())
                SingleClick("btn_Close");
            return "Fail";
        } catch (Exception e) {

            if (driver.findElement(By.xpath("btn_Close")).isDisplayed())
                SingleClick("btn_Close");
            System.out.println(e);
            return "Fail";
        }
    }

    // Search Status method
    public String searchStatus2(String referenceNumber, String status, String appType, String caseTypeValue,
                                String SearchField, String DisplayField) throws InterruptedException, IOException {
        try {
//Set<String> S = new HashSet<String>();
//S.add("Client");
//S.add("All fields");
            String firstddvalue, secondddvalue, ddtext = "";
            WebElement searchedValue;
            Properties properties = System.getProperties();
            String fld = properties.getProperty(referenceNumber);
            String val;
            if (fld != null) {
                val = fld;
            } else {
                val = referenceNumber.toString();
            }

            if (appType.equals("Patent") && caseTypeValue.equals("Post-PCT")) {
                ddtext = "patentPostPctSearchFields";
                log.info(ddtext);
            } else if (appType.equals("Patent") && caseTypeValue.equals("Direct")) {
                ddtext = "patentDirectSearchFields";
                log.info(ddtext);
            } else if (appType.equals("Patent") && caseTypeValue.equals("EP Validation")) {
                ddtext = "patentEpValidationSearchFields";
                log.info(ddtext);
            }
            else if (appType.equals("Patent") && caseTypeValue.equals("First Filing")) {
                ddtext = "patentFirstFilingSearchFields";
                log.info(ddtext);
            } else if (appType.equals("Trademark") && caseTypeValue.equals("Direct")) {
                ddtext = "trademarkDirectSearchFields";
                log.info(ddtext);
            } else if (appType.equals("Custom") && caseTypeValue.equals("Custom")) {
                ddtext = "customSearchFields";
                log.info(ddtext);
            } else if (appType.equals("Service Request") && caseTypeValue.equals("Service Request")) {
                ddtext = "serviceRequestSearchFields";
                log.info(ddtext);
            } else if (appType.equals("Trademark") && caseTypeValue.equals("First Filing")) {
                ddtext = "trademarkFirstFilingSearchFields";
                log.info(ddtext);
            } else if (appType.equals("Trademark") && caseTypeValue.equals("Renewal")) {
                ddtext = "trademarkRenewalSearchFields";
                log.info(ddtext);
            }

            if (appType.contentEquals("Trademark")) {
                appType = "Trade Mark";
            }

            int rowNum = 0;
            waitAll(100);
            dynamicWaiting("15", "visible", "searchButton");
//driver.findElement(By.xpath(PropertiesUtil.getProperty("searchButton"))).click();
            SingleClick("searchButton");
            waitAll(100);
//            added if-else block to differentiate between Instruct and Receive window
            if (driver.getCurrentUrl().contains("instruct")){
                driver.findElement(By.xpath(PropertiesUtil.getProperty("instruct_inp_SearchInstruction"))).click();
            } else if(driver.getCurrentUrl().contains("receive")){
                driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).click();
            }
            //driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).click();
            //driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();
            waitAll(100);
            firstddvalue = "(//lh/b[contains(text(),'" + appType + "')]//following::a[text()='" + caseTypeValue
                    + "'])[1]";
            secondddvalue = "//*[contains(@ng-repeat,'" + ddtext + "') and a[contains(text(),'" + SearchField + "')]]";
            System.out.println("ddtext value is " + ddtext);
            System.out.println("First DD XPAth" + firstddvalue);
            System.out.println("Second DD XPAth" + secondddvalue);
//            System.out.println("reference no. : " + referenceNumber + " val : " + val);
            driver.findElement(By.xpath("(//div[@class='input-group-btn dropdown'])[1]")).click();
            waitAll(100);
//SingleClick("(//div[@class='input-group-btn dropdown'])[1]");
            driver.findElement(By.xpath(firstddvalue)).click();
            waitAll(100);
//SingleClick(firstddvalue);
//SingleClick("(//div[@class='input-group-btn dropdown'])[2]");
            driver.findElement(By.xpath("(//div[@class='input-group-btn dropdown'])[2]")).click();
            waitAll(100);
            driver.findElement(By.xpath(secondddvalue)).click();
            waitAll(100);
//SingleClick(secondddvalue);
//            added if-else block to differentiate between Instruct and Receive window
            if (driver.getCurrentUrl().contains("instruct")){
                driver.findElement(By.xpath(PropertiesUtil.getProperty("instruct_inp_SearchInstruction"))).clear();
                waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("instruct_inp_SearchInstruction"))).sendKeys(val);
            } else if(driver.getCurrentUrl().contains("receive")){
                driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).clear();
                waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).sendKeys(val);
            }
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).clear();
            waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).sendKeys(val);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_search"))).click();
            waitAll(100);
            List<WebElement> elements;

            if (driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header_Instruct"))).size() > 0) {
                elements = driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header_Instruct")));
            } else {
                elements = driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header_Receive")));
            }

            if (elements.size() > 0) {
                if (SearchField.contains("lient")) {
                    System.out.println(" Since the results count is more than zero , this is pass");
                    return "Pass";
                } else if (SearchField.contains("All fields")) {
                    List<WebElement> elementStatus = driver
                            .findElements(By.xpath(PropertiesUtil.getProperty("status_rows")));
                    System.out.println(driver.findElements(By.xpath(PropertiesUtil.getProperty("status_rows"))).get(0)
                            .getAttribute("innerText"));
                    if (driver.findElements(By.xpath(PropertiesUtil.getProperty("status_rows"))).get(0)
                            .getAttribute("innerText").contains(status)) {
                        System.out.println("Status found matching with UI status as "
                                + elementStatus.get(0).getAttribute("innerText").toString().contains(status)
                                + " and supplied status as " + status);
                        return "Pass";
                    } else {
                        System.out.println("Status not found matching" + status);
                        return "Fail";
                    }
                } else {
                    System.out.println("element.size > 0's else block");
                    List<WebElement> elementStatus = driver.findElements(By.xpath(PropertiesUtil.getProperty("status_rows")));
                    System.out.println("elementStatus : " + elementStatus);
                    if (elementStatus.get(0).getAttribute("innerText").contains(status)) {
                        System.out.println("Status found matching for other than all fields" + status);
                        driver.findElement(By.xpath("(//*[contains(@ng-click,'vm') and text()='View'])[1]")).click();
                        waitAll(100);

//                      this is if-else block is written because for Trademark Renewal IP type, two "Registration no." Displayfield appear
                        if (DisplayField.equals("Registration no.") && driver.getCurrentUrl().contains("instruct"))
                            searchedValue = driver.findElement(By.xpath("(//div[@class='ipf-details']//span[contains(text(),'" + DisplayField + "')]//following::div[1])[2]"));
                        else
                            searchedValue = driver.findElement(By.xpath("(//div[@class='ipf-details']//span[contains(text(),'" + DisplayField + "')]//following::div[1])[1]"));

//                        System.out.println("searchedValue"+searchedValue);
                        if (searchedValue != null) {
                            System.out.println("Searched value in UI " + searchedValue.getText().toLowerCase());
                            System.out.println("Passed value in code value " + val.toLowerCase());
                            if (searchedValue.getText().toLowerCase().contains(val.toLowerCase())) {
                                System.out.println(" Searched UI " + searchedValue.getText().toLowerCase()
                                        + " and Passed code value is " + val.toLowerCase());
                                waitAll(100);
                                SingleClick("btn_dismiss");
                                return "Pass";
                            } else {
                                System.out.println(" Searched UI " + searchedValue.getText().toLowerCase()
                                        + " and Passed code value is " + val.toLowerCase());
                                waitAll(100);
                                SingleClick("btn_dismiss");
                                return "Fail";
                            }
                        } else {
                            System.out.println("else end");
                            return "Fail";
                        }
                    }

                }
            }
        } catch (Exception e) {
//if(driver.findElements(By.xpath("btn_dismiss")).size() != 0)
            SingleClick("btn_dismiss");
            System.out.println(e);
            return "Fail";
        }

        if (driver.findElements(By.xpath("btn_dismiss")).size() != 0)
            SingleClick("btn_dismiss");
        return "Fail";

    }

     public String searchStatus2(String referenceNumber, String status, String appType, String caseTypeValue,
                                 String SearchField, String DisplayField) throws InterruptedException, IOException {
         try {
 //            Set<String> S = new HashSet<String>();
 //            S.add("Client");
 //            S.add("All fields");
             String firstddvalue, secondddvalue, ddtext = "";
             WebElement searchedValue;
             Properties properties = System.getProperties();
             String fld = properties.getProperty(referenceNumber);
             String val;
             if (fld != null) {
                 val = fld;
             } else {
                 val = referenceNumber.toString();
             }

             if (appType.equals("Patent") && caseTypeValue.equals("Post-PCT")) {
                 ddtext = "patentPostPctSearchFields";
             } else if (appType.equals("Patent") && caseTypeValue.equals("Direct")) {
                 ddtext = "patentDirectSearchFields";
             } else if (appType.equals("Patent") && caseTypeValue.equals("EP Validation")) {
                 ddtext = "patentEpValidationSearchFields";
             } else if (appType.equals("Trademark") && caseTypeValue.equals("Direct")) {
                 ddtext = "trademarkDirectSearchFields";
                 System.out.println(ddtext);
             } else if (appType.equals("Custom") && caseTypeValue.equals("Custom")) {
                 ddtext = "customSearchFields";
             }

             if (appType.contentEquals("Trademark")) {
                 appType = "Trade Mark";
             }

             int rowNum = 0;
             waitAll(100);
             dynamicWaiting("15", "visible", "searchButton");
 //            driver.findElement(By.xpath(PropertiesUtil.getProperty("searchButton"))).click();
             SingleClick("searchButton");
             waitAll(100);
             driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).click();
             waitAll(100);
             firstddvalue = "(//lh/b[contains(text(),'" + appType + "')]//following::a[text()='" + caseTypeValue
                     + "'])[1]";
             secondddvalue = "//*[contains(@ng-repeat,'" + ddtext + "') and a[contains(text(),'" + SearchField + "')]]";
             System.out.println("ddtext value is " + ddtext);
             System.out.println("First DD XPAth" + firstddvalue);
             System.out.println("Second DD XPAth" + secondddvalue);
             driver.findElement(By.xpath("(//div[@class='input-group-btn dropdown'])[1]")).click();
             waitAll(100);
 //            SingleClick("(//div[@class='input-group-btn dropdown'])[1]");
             driver.findElement(By.xpath(firstddvalue)).click();
             waitAll(100);
 //            SingleClick(firstddvalue);
 //            SingleClick("(//div[@class='input-group-btn dropdown'])[2]");
             driver.findElement(By.xpath("(//div[@class='input-group-btn dropdown'])[2]")).click();
             waitAll(100);
             driver.findElement(By.xpath(secondddvalue)).click();
             waitAll(100);
 //            SingleClick(secondddvalue);
             driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).clear();
             waitAll(100);
             driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).sendKeys(val);
             driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_search"))).click();
             waitAll(100);
             List<WebElement> elements;

             if (driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header_Instruct"))).size() > 0) {
                 elements = driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header_Instruct")));
             } else {
                 elements = driver.findElements(By.xpath(PropertiesUtil.getProperty("clientRef_header_Receive")));
             }

             if (elements.size() > 0) {
                 if (SearchField.contains("lient")) {
                     System.out.println(" Since the results count is more than zero , this is pass");
                     return "Pass";
                     } else if (SearchField.contains("All fields")) {
                         List<WebElement> elementStatus = driver
                                 .findElements(By.xpath(PropertiesUtil.getProperty("status_rows")));
                         System.out.println(driver.findElements(By.xpath(PropertiesUtil.getProperty("status_rows"))).get(0)
                                 .getAttribute("innerText"));
                     //if (elementStatus.get(0).getAttribute("innerText").toString().equals(status)) {
                     if (elementStatus.get(0).getAttribute("innerText").contains(status)) {
                             System.out.println("Status found matching with UI status as "
                                 //+ elementStatus.get(0).getAttribute("innerText").toString().equals(status)
                                 + elementStatus.get(0).getAttribute("innerText").contains(status)
                                     + " and supplied status as " + status);
                             return "Pass";
                         } else {
                             System.out.println("Status not found matching" + status);
                             return "Fail";
                         }
                         } else {
                         List<WebElement> elementStatus = driver
                                 .findElements(By.xpath(PropertiesUtil.getProperty("status_rows")));
                     //if (elementStatus.get(0).getAttribute("innerText").toString().equals(status)) {
                     if (elementStatus.get(0).getAttribute("innerText").contains(status)) {
                             System.out.println("Status found matching for other than all fields" + status);
                             driver.findElement(By.xpath("(//*[contains(@ng-click,'vm') and text()='View'])[1]")).click();
                             waitAll(100);

                             searchedValue = driver
                                     .findElement(By.xpath("(//div[@class='ipf-details']//span[contains(text(),'"
                                             + DisplayField + "')]//following::div[1])[1]"));
     //                        System.out.println("searchedValue"+searchedValue);
                             if(searchedValue != null) {
                             System.out.println("Searched value in UI " + searchedValue.getText().toLowerCase());
                             System.out.println("Passed value in code value " + val.toLowerCase());
                             if (searchedValue.getText().toLowerCase().contains(val.toLowerCase())) {
                                 System.out.println(" Searched UI " + searchedValue.getText().toLowerCase()
                                         + " and Passed code value is " + val.toLowerCase());
                                 waitAll(100);
                                 SingleClick("btn_dismiss");
                                 return "Pass";
                             } else {
                                 System.out.println(" Searched UI " + searchedValue.getText().toLowerCase()
                                         + " and Passed code value is " + val.toLowerCase());
                                 waitAll(100);
                                 SingleClick("btn_dismiss");
                                 return "Fail";
                             }
                         } else {return "Fail";}
                         }

                     }
             }
         } catch (Exception e) {
 //        	if(driver.findElements(By.xpath("btn_dismiss")).size() != 0)
                 SingleClick("btn_dismiss");
             System.out.println(e);
             return "Fail";
         }

         if(driver.findElements(By.xpath("btn_dismiss")).size() != 0)
             SingleClick("btn_dismiss");
         return "Fail";

     }
 
    public String validateTableValue(String searchSection, String referenceNumber, String columnName, String action,
                                     String status) {
        try {
            String lastPageNumber;
            Properties properties = System.getProperties();
            String fld = properties.getProperty(referenceNumber);
            String val;
            if (fld != null)
                referenceNumber = fld;

            else
                referenceNumber = referenceNumber.toString();
            List<WebElement> headers = driver.findElements(By.xpath(PropertiesUtil.getProperty("dashboardHeading")));

            for (int i = 0; i < headers.size(); i++) {
                if (headers.get(i).getText().toLowerCase().contains(searchSection.toLowerCase())
                        && !headers.get(i).getAttribute("class").contains("active")) {
                    headers.get(i).click();
                    // waitAll(100);
                    break;
                }
            }
            // Setting 100 as number of record to be displayed
            Select pagecount = new Select(driver.findElement(By.xpath(PropertiesUtil.getProperty("headerPageCount"))));
            pagecount.selectByVisibleText("100");
            waitAll(5000);
            List<WebElement> tblRows = driver.findElements(By.xpath(PropertiesUtil.getProperty("tbl_row")));
//			List<WebElement> referenceList=driver.findElements(By.xpath("//div[contains(@class,'dashboard-heading active')]//following::td[contains(@header-title,'"+columnName+"')]"));
            if (tblRows.size() <= 100)
                lastPageNumber = "1";
            else
                lastPageNumber = driver.findElement(By.xpath(PropertiesUtil.getProperty("pageNumber_Last")))
                        .isDisplayed()
                        ? driver.findElement(By.xpath(PropertiesUtil.getProperty("pageNumber_Last"))).getText()
                        : "1";

            for (int pages = 1; pages <= Integer.parseInt(lastPageNumber); pages++) {
                for (int row = 1; row <= tblRows.size(); row++) {
                    if (driver.findElements(By.xpath(
                            "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr[" + row
                                    + "]//td[contains(@header-title,'" + columnName + "')]"))
                            .size() <= 1
                            || driver.findElements(By.xpath(
                            "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                    + row + "]//td[contains(@header-title,'" + columnName + "')]//div"))
                            .size() <= 1) {
                        WebElement searchElementTable = driver.findElement(By.xpath(
                                "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                        + row + "]//td[contains(@header-title,'" + columnName + "')]"));
                        
                         * System.out.println("ere "+referenceNumber);
                         * System.out.println(searchElementTable.getText().trim());
                         
                        if (searchElementTable.getText().trim().equals(referenceNumber.trim())) {
                            switch (action) {
                                case "Remove":
                                    // waitAll(100);
                                    driver.findElement(By.xpath(
                                            "(//div[contains(@class,'dashboard-heading active')]//following::table)[1]//tr["
                                                    + row + "]//span[contains(@class,'fa-ellipsis-v')]"))
                                            .click();
                                    // waitAll(100);
                                    driver.findElement(By.xpath("(//button[text()='Remove'])[last()]")).click();
                                    // waitAll(100);
                                    return "Pass";

                                case "Edit":
                                    // waitAll(100);
                                    driver.findElement(By.xpath(
                                            "(//div[contains(@class,'dashboard-heading active')]//following::table)[1]//tr["
                                                    + row + "]//span[contains(@class,'fa-ellipsis-v')]"))
                                            .click();
                                    // waitAll(100);
                                    driver.findElement(By.xpath("(//button[text()='Edit'])[last()]")).click();
                                    // waitAll(100);
                                    return "Pass";

                                case "Delete":
                                    WebDriverWait w = new WebDriverWait(driver, 15);
                                    w.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                            "(//div[contains(@class,'dashboard-heading active')]//following::table)[1]//tr["
                                                    + row + "]//span[contains(@class,'fa-ellipsis-v')]")));
                                    // waitAll(100);
                                    driver.switchTo().activeElement();
                                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                                    executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(
                                            "(//div[contains(@class,'dashboard-heading active')]//following::table)[1]//tr["
                                                    + row + "]//span[contains(@class,'fa-ellipsis-v')]")));
                                    // waitAll(100);
                                    driver.findElement(By.xpath("(//button[text()='Delete'])[" + row + "]")).click();
                                    // waitAll(100);
                                    driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_confirm"))).click();
                                    // waitAll(100);
                                    return "Pass";

                                case "Instruct":
                                    // waitAll(100);
                                    driver.findElement(By.xpath("(//button[text()='Instruct'])[" + row + "]")).click();
                                    // waitAll(100);
                                    return "Pass";

                                case "Validate":
                                    System.out.println("Condition Matched");
                                    return "Pass";

                                case "Status":
                                    if (driver.findElement(By.xpath(
                                            "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                                    + row + "]//td[contains(@header-title,'Status')]"))
                                            .getText().toLowerCase().contains(status.toLowerCase())) {
                                        System.out.println("Status matched as " + driver.findElement(By.xpath(
                                                "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                                        + row + "]//td[contains(@header-title,'Status')]"))
                                                .getText());
                                        return "Pass";
                                    } else {
                                        System.out.println("Mismatch in status as the status found is " + driver
                                                .findElement(By.xpath(
                                                        "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                                                + row + "]//td[contains(@header-title,'Status')]"))
                                                .getText());
                                        return "Fail";
                                    }

                                case "Country":

                                    String fld1 = properties.getProperty(status);
                                    if (fld1 != null)
                                        status = fld1;
                                    else
                                        status = fld1.toString();
                                    if (driver.findElement(By.xpath(
                                            "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                                    + row + "]//td[contains(@header-title,'Country')]"))
                                            .getText().toLowerCase().contains(status.toLowerCase())
                                            && driver.findElement(By.xpath(
                                            "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                                    + row + "]//td[contains(@header-title,'Country')]"))
                                            .getText().toLowerCase() != "") {
                                        System.out.println("Country is matched as " + driver.findElement(By.xpath(
                                                "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                                        + row + "]//td[contains(@header-title,'Country')]"))
                                                .getText());
                                        return "Pass";
                                    } else {
                                        System.out.println("Mismatch in Country as the Country code found is " + driver
                                                .findElement(By.xpath(
                                                        "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                                                + row + "]//td[contains(@header-title,'Country')]"))
                                                .getText());
                                        return "Fail";
                                    }

                                default:
                                    System.out.println("Need to handle this case!!!!!!!");
                                    return "Fail";

                            }
                        }
                    }
                }
                driver.findElement(By.xpath(PropertiesUtil.getProperty("page_Next"))).click();
                // waitAll(100);
            }
            return "Fail";

        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }
    }

    public String caseType(String keyword) {
        switch (keyword) {
            case "Post-PCT":
                return "PostPct";
            case "Direct":
                return "Direct";

            default:
                System.out.println("need handling");
                break;

        }
        return keyword;
    }

    public String FieldValue(String keyword) {
        switch (keyword) {
            case "Intl. publication no.":
                return "Intl. Publication no.";

            case "mark":
                return "Mark";

            default:
                return keyword;

        }

    }

    public String appType(String keyword) {
        switch (keyword) {
            case "Patent":
                return "Patent";
            case "Trademark":
                return "Trade Mark";
            default:
                System.out.println("need handling");
                break;
        }
        return keyword;
    }

    public String CloseBrowserInstance() throws InterruptedException {
        driver.close();
        driver =null;
        return "Pass";
    }
    
    public String Verify_BrowserInstance_Closed() throws InterruptedException {
    	
    	if(driver.equals(null)){
        return "Pass";
    	}
    	else {
    		return "Fail";
    	}
    }

    public String isFileDownloaded(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            System.out.println(dir_contents[i].getName());
            if (dir_contents[i].getName().contains(fileName)) {
                flag = true;
                break;
            }
        }

        if (flag)
            return "Pass";
        else
            return "Fail";
    }

    public String CreateClient(String userid) throws InterruptedException {
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions
                    .presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("btn_AddClient"))));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_AddClient"))));
            // dynamicWaiting("10", "visible", "inp_Email");
            // waitAll(100);
            WebElement emailId = driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_Email")));
            emailId.sendKeys(userid);
            // dynamicWaiting("10","click","Next");
            // waitAll(100);
            dynamicWaiting("10", "click", "Next");
            driver.findElement(By.xpath("//button[text()='Next']")).click();
            // waitAll(100);
            dynamicWaiting("10", "visible", "inp_AddressLine1");

            // Thread.sleep(3000);
            WebElement addressLine1 = driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_AddressLine1")));
            addressLine1.sendKeys("Sweden");
            WebElement city = driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_city")));
            city.sendKeys("Linko");
            WebElement postalCode = driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_postalCode")));
            postalCode.sendKeys("1020202");
            WebElement country = driver.findElement(By.xpath(PropertiesUtil.getProperty("dd_country")));
            Select countries = new Select(country);
            countries.selectByVisibleText("Sweden");
            WebElement telephone = driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_telephone")));
            telephone.sendKeys("9999999");
            WebElement fax = driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_fax")));
            fax.sendKeys("6876876");
            WebElement currency = driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_currency")));
            currency.sendKeys("Euro");
            WebElement confirm = driver.findElement(By.xpath(PropertiesUtil.getProperty("btnconfirm")));
            // dynamicWaiting("10", "click", "btnconfirm");
            // waitAll(100);
            confirm.click();
            dynamicWaiting("10", "visible", "header_confirmClient");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btnconfirm"))).click();
            // waitAll(100);
            // Thread.sleep(3000);
            dynamicWaiting("10", "click", "header_FileAdmin");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("header_FileAdmin"))).click();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            // waitAll(100);
            System.out.println("*****************************************************************");
            System.out.println("Client has been created succussfully");
            System.out.println("*****************************************************************");
            return "Pass";
        } catch (Exception e) {
            System.out.println("---------------unable to add client------------------------------");
            System.out.println(e);
            return "Fail";
        }
    }

    public String CreateAgent(String clientId, String agentId, String inpCountry) throws InterruptedException {
        try {
            String inpCity = "sweden";
            Thread.sleep(3000);
            // waitAll(100);
            // dynamicWaiting("10", "click","clientUserMenu");
            WebElement clientUser = driver.findElement(By.xpath(PropertiesUtil.getProperty("clientUserMenu")));
            clientUser.click();
            // dynamicWaiting("10", "visible","clientEmailList");
            // waitAll(100);
            String num = driver.findElement(By.xpath(PropertiesUtil.getProperty("lastPage"))).getText();
            System.out.println("Last Page" + num);
            String Flag = "N";
            for (int i = 0; i < Integer.parseInt(num); i++) {

                List<WebElement> emailList = driver
                        .findElements(By.xpath(PropertiesUtil.getProperty("clientEmailList")));
                for (WebElement email : emailList) {
                    if (email.getText().equalsIgnoreCase(clientId)) {
                        System.out.println("Client exist in the list");
                        email.click();
                        // waitAll(100);
                        Flag = "Y";
                        break;
                    }
                }
                if (Flag.equals("N")) {
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_next"))).click();
                    // waitAll(100);
                } else {
                    break;
                }
                // // waitAll(100);
            }
            // waitAll(100);
            // dynamicWaiting("10", "visible","inp_Email");
            // //Thread.sleep(3000);
            System.out.println("outside if...");
            // if
            // (driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_Email"))).getText().equalsIgnoreCase(clientId))
            // {
            // System.out.println("Inside if...");
            // WebElement registerNewAgent = driver
            // .findElement(By.xpath(PropertiesUtil.getProperty("btn_RegisterAgent")));
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_RegisterAgent"))).click();

            // Thread.sleep(1000);
            // waitAll(100);
            // dynamicWaiting("10", "click", "agentName");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("agentName"))).sendKeys(agentId);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_AddressLine1"))).sendKeys("567 sweden");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_postalCode"))).sendKeys("109090");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_city"))).sendKeys(inpCity);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("region"))).sendKeys("swedenCity");
            WebElement countries = driver.findElement(By.xpath(PropertiesUtil.getProperty("dd_country")));
            Select country = new Select(countries);
            country.selectByVisibleText(inpCountry);
            WebElement addFilingCountry = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("btn_addFilingCountry")));
            addFilingCountry.click();
            WebElement listFilingCountry = driver.findElement(By.xpath(PropertiesUtil.getProperty("dd_filingCountry")));
            Select FilingCountryList = new Select(
                    listFilingCountry);
            FilingCountryList.selectByVisibleText("Sweden");
            WebElement btnCreate = driver.findElement(By.xpath(PropertiesUtil.getProperty("createBtn")));
            btnCreate.click();
            WebElement confirm = driver.findElement(By.xpath(PropertiesUtil.getProperty("btnconfirm")));
            // waitAll(100);
            // dynamicWaiting("10", "visible", "btnconfirm");
            confirm.click();
            WebElement btnSave = driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_save")));
            // waitAll(100);
            // dynamicWaiting("10", "visible", "btn_save");

            // connect the client

            dynamicWaiting("10", "click", "btn_connect");
            WebElement pageCount = driver.findElement(By.xpath(PropertiesUtil.getProperty("updateCount")));
            Select pageCountNumber = new Select(
                    pageCount);
            pageCountNumber.selectByVisibleText("100");
            // waitAll(100);

            // Thread.sleep(1000);
            // List<WebElement> connectBtn =
            // driver.findElements(By.xpath(PropertiesUtil.getProperty("btn_connect")));
            int connectbtnSize = driver.findElements(By.xpath(PropertiesUtil.getProperty("btn_connect"))).size();
            // List<WebElement> agentsList =
            // driver.findElements(By.xpath(PropertiesUtil.getProperty("agentList")));
            // List<WebElement> cityList =
            // driver.findElements(By.xpath(PropertiesUtil.getProperty("cityList")));
            // List<WebElement> countryList =
            // driver.findElements(By.xpath(PropertiesUtil.getProperty("countryList")));
            Thread.sleep(3000);

            // for (int i = 1; i <
            // driver.findElements(By.xpath("//table[contains(@ng-table,'agentsTableParams')]//tbody//tr")).size();
            // i++) {
            for (int i = 1; i <= 1; i++) {
                // waitAll(100);
                if (driver.findElements(By.xpath(PropertiesUtil.getProperty("agentList"))).get(i - 1).getText()
                        .equalsIgnoreCase(agentId)
                        && driver.findElements(By.xpath(PropertiesUtil.getProperty("cityList"))).get(i - 1).getText()
                        .equalsIgnoreCase(inpCity)
                        && driver.findElements(By.xpath(PropertiesUtil.getProperty("countryList"))).get(i - 1).getText()
                        .contains(inpCountry)) {
                    System.out.println(
                            "Agent " + agentId + " with city " + inpCity + " and country " + inpCountry + " is found");
                    String connectbtnpath = "//table[contains(@ng-table,'agentsTableParams')]//following-sibling::tbody/tr["
                            + i + "]//a[text()='Connect']";
                    if (driver.findElements(By.xpath(connectbtnpath)).size() != 0) {
                        // Thread.sleep(1000);
                        // waitAll(100);
                        System.out.println("row is " + i);
                        String newConnectBtnPath = "//table[contains(@ng-table,'agentsTableParams')]//following-sibling::tbody/tr["
                                + i + "]//a[text()='Connect']";
                        driver.findElement(By.xpath(newConnectBtnPath)).click();
                        break;
                    }
                }
            }
            // }
            // waitAll(100);
            // dynamicWaiting("10", "visible", "registerUserHeading");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_Email"))).sendKeys(agentId);
            // waitAll(100);
            dynamicWaiting("10", "click", "btn_connectAgent");
            driver.findElement(By.xpath("//button[text()='Connect'][1]")).click();
            // waitAll(100);
            // Thread.sleep(1000);
            // dynamicWaiting("10", "click", "btn_save");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_save"))).click();
            // Thread.sleep(3000);
            // waitAll(100);
            dynamicWaiting("10", "click", "header_FileAdmin");
            // driver.findElement(By.xpath(PropertiesUtil.getProperty("header_FileAdmin"))).click();
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            System.out.println("*****************************************************************");
            System.out.println("Agent has been created succussfully");
            System.out.println("*****************************************************************");
            return "Pass";
        } catch (Exception e) {
            System.out.println("---------------unable to add Agent------------------------------");
            System.out.println(e);
            return "Fail";
        }
    }

    public String BankProfile(String profileTitle) throws InterruptedException {
        try {
            // dynamicWaiting("10", "visible", "lnk_Dashboard");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            WebDriverWait w = new WebDriverWait(driver, 10);
            // w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("lnk_bank"))));
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
            // dynamicWaiting("10", "visible", "managefundsProfile");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("managefundsProfile"))).click();
            // Thread.sleep(1000);
            // dynamicWaiting("10", "visible", "pageCountProfile");
            // waitAll(100);
            WebElement pageCountProfile = driver.findElement(By.xpath(PropertiesUtil.getProperty("pageCountProfile")));
            Select pageCountNumber = new Select(
                    pageCountProfile);
            pageCountNumber.selectByVisibleText("100");
            // dynamicWaiting("10", "click","btn_createFundProfile");
            // waitAll(100);
            WebElement btnCreateFundProfile = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("btn_createFundProfile")));
            btnCreateFundProfile.click();
            WebElement inpTitle = driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_Title")));
            inpTitle.sendKeys(profileTitle);
            // dynamicWaiting("10", "click", "btn_SaveProfile");
            // waitAll(100);
            WebElement saveProfile = driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_SaveProfile")));
            saveProfile.click();
            // Thread.sleep(3000);
            // waitAll(100);
            dynamicWaiting("10", "click", "lnk_Dashboard");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            System.out.println("*****************************************************************");
            System.out.println("Pricing - Funds Management Profile has been created succussfully");
            System.out.println("*****************************************************************");
            return "Pass";
        } catch (Exception e) {
            System.out.println(
                    "---------------------Unable to add Pricing - Funds Management Profile------------------------------");
            System.out.println(e);
            return "Fail";
        }
    }

    public String BankCurrency(String profileName) throws InterruptedException {
        try {
            // dynamicWaiting("10", "visible", "lnk_Dashboard");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            WebDriverWait w = new WebDriverWait(driver, 10);
            // Thread.sleep(1000);
            // waitAll(100);
            // w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("lnk_bank"))));
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
            // waitAll(100);
            // dynamicWaiting("10", "visible",
            // PropertiesUtil.getProperty("managefundsProfile"));
            // Thread.sleep(1000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("managefundsProfile"))).click();
            // waitAll(100);
            // Thread.sleep(3000);
            // dynamicWaiting("10", "visible", "pageCountCurrency");
            WebElement PageCountCurrency = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("pageCountCurrency")));
            Select pageCountNumber = new Select(
                    PageCountCurrency);
            pageCountNumber.selectByVisibleText("100");
            WebElement btnCreateCurrency = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("btn_createCurrency")));
            btnCreateCurrency.click();
            List<WebElement> pageNumber_currency = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_currency")));
            int size = pageNumber_currency.size();
            if (size != 0)
                driver.findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_currency"))).get(size - 1).click();
            // Thread.sleep(1000);
            // waitAll(100);
            WebElement inpTitleCurrency = driver.findElement(By.xpath(PropertiesUtil.getProperty("inpTitle_Currency")));
            Select currencyTitle = new Select(
                    inpTitleCurrency);
            currencyTitle.selectByVisibleText(profileName);
            WebElement inpInvoiceCurrency = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("inpInvoiceCurrency")));
            inpInvoiceCurrency.sendKeys("EUR");
            WebElement inpMarkupCurrency = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("inpMarkupCurrency")));
            inpMarkupCurrency.sendKeys(".01");
            // waitAll(100);
            dynamicWaiting("10", "click", PropertiesUtil.getProperty("btn_SaveCurrency"));
            WebElement saveCurrency = driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_SaveCurrency")));
            saveCurrency.click();
            // Thread.sleep(3000);
            // waitAll(100);
            dynamicWaiting("10", "click", "lnk_Dashboard");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            System.out.println("*****************************************************************");
            System.out.println("Pricing - Funds Management Profile Currency has been created succussfully");
            System.out.println("*****************************************************************");
            return "Pass";
        } catch (Exception e) {
            System.out.println(
                    "---------------------Unable to add Pricing - Funds Management Profile Currency------------------------------");
            System.out.println(e);
            return "Fail";
        }

    }

    public String ServiceChargeProfile(String serviceChargeProfileName) throws InterruptedException {
        try {
            // dynamicWaiting("10", "visible", "lnk_Dashboard");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            // WebDriverWait w = new WebDriverWait(driver, 10);
            // waitAll(100);
            // w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("lnk_bank"))));
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
            // dynamicWaiting("10", "visible", "manageServiceCharge");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("manageServiceCharge"))).click();
            // dynamicWaiting("10", "visible", "btn_createFundProfile");
            // waitAll(100);
            WebElement pageCountSCProfile = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("pageCountSCprofile")));
            Select pageCountNumberSCProfile = new Select(
                    pageCountSCProfile);
            pageCountNumberSCProfile.selectByVisibleText("100");
            // Thread.sleep(1000);
            // waitAll(100);
            List<WebElement> pageNumber = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_SCProfile")));
            // dynamicWaiting("10", "click", "btn_createFundProfile");
            // waitAll(100);
            WebElement btnCreateFundProfile = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("btn_createFundProfile")));
            btnCreateFundProfile.click();
            WebElement inpTitle_SCProfile = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("inpTitle_SCProfile")));
            inpTitle_SCProfile.sendKeys(serviceChargeProfileName);
            dynamicWaiting("10", "click", "btn_SaveProfile");
            // waitAll(100);
            WebElement saveProfile = driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_SaveProfile")));
            saveProfile.click();
            // Thread.sleep(3000);
            // waitAll(100);
            dynamicWaiting("10", "click", "lnk_Dashboard");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            System.out.println("*****************************************************************");
            System.out.println("Pricing - Service Charge Profile has been created succussfully");
            System.out.println("*****************************************************************");
            return "Pass";
        } catch (Exception e) {
            System.out.println(
                    "---------------------Unable to add Pricing - services charges profile------------------------------");
            System.out.println(e);
            return "Fail";
        }

    }

    public String ServiceChargeCountry(String scProfileName) throws InterruptedException {
        try {
            // dynamicWaiting("10", "visible", "lnk_Dashboard");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            WebDriverWait w = new WebDriverWait(driver, 10);
            // waitAll(100);
            // w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("lnk_bank"))));
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
            dynamicWaiting("10", "visible", "manageServiceCharge");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("manageServiceCharge"))).click();
            // dynamicWaiting("10", "visible", "btn_CreateNewCountry");
            // Thread.sleep(1000);
            dynamicWaiting("10", "click", "btn_CreateNewCountry");
            // waitAll(100);
            WebElement btn_new = driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_CreateNewCountry")));
            btn_new.click();
            List<WebElement> pageNumber_Country = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_SCCountry")));
            if (pageNumber_Country.size() != 0)
                pageNumber_Country.get(pageNumber_Country.size() - 1).click();
            // Thread.sleep(1000);
            // waitAll(100);
            WebElement inpTitle_SCCountry = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("inptitle_SCCountry")));
            Select inptitle = new Select(
                    inpTitle_SCCountry);
            inptitle.selectByVisibleText(scProfileName);
            WebElement inpCountryCurrency = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("inpCountryCurreny")));
            inpCountryCurrency.sendKeys("EUR");
            WebElement inpServiceCharge = driver.findElement(By.xpath(PropertiesUtil.getProperty("inpServiceCharge")));
            inpServiceCharge.sendKeys("0.9");
            dynamicWaiting("10", "click", "btn_SaveCountry");
            // waitAll(100);
            WebElement saveButton = driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_SaveCountry")));
            saveButton.click();
            // Thread.sleep(3000);
            // waitAll(100);
            dynamicWaiting("10", "click", "lnk_Dashboard");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            System.out.println("*****************************************************************");
            System.out.println("Pricing - Service Charge Profile Country has been created succussfully");
            System.out.println("*****************************************************************");
            return "Pass";
        } catch (Exception e) {
            System.out.println(
                    "---------------------Unable to add Pricing - services charges profile country------------------------------");
            System.out.println(e);
            return "Fail";
        }

    }

    public String PricingCustomer(String customer, String oracleId) throws InterruptedException {
        try {
            // dynamicWaiting("10", "visible", "lnk_Dashboard");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            WebDriverWait w = new WebDriverWait(driver, 10);
            // w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("lnk_bank"))));
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
            // Thread.sleep(1000);
            // dynamicWaiting("10", "visible", "lnk_CustomerSetting");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_CustomerSetting"))).click();
            // dynamicWaiting("10", "click","btn_createNewCustomer");
            // waitAll(100);
            // Thread.sleep(1000);
            WebElement pageCountCustomer = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("pageCountCustomer")));
            Select pageCount = new Select(
                    pageCountCustomer);
            pageCount.selectByVisibleText("100");
            // Thread.sleep(1000);
            // waitAll(100);
            WebElement createnewCustomer = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("btn_createNewCustomer")));
            createnewCustomer.click();
            WebElement inpCustomerId = driver.findElement(By.xpath(PropertiesUtil.getProperty("inpCustomerId")));
            Select CustomerId = new Select(inpCustomerId);
            CustomerId.selectByVisibleText(customer);
            WebElement inpOracleCusid = driver.findElement(By.xpath(PropertiesUtil.getProperty("inpOracleCusId")));
            inpOracleCusid.sendKeys(oracleId);
            WebElement inpOracleId = driver.findElement(By.xpath(PropertiesUtil.getProperty("inpOracleId")));
            inpOracleId.sendKeys("09");
            WebElement inpEntityId = driver.findElement(By.xpath(PropertiesUtil.getProperty("inpEntityId")));
            inpEntityId.sendKeys("11");
            dynamicWaiting("10", "click", "btn_SaveCustomer");
            // waitAll(100);
            WebElement btn_SaveCustomer = driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_SaveCustomer")));
            btn_SaveCustomer.click();
            // Thread.sleep(3000);
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            System.out.println("*****************************************************************");
            System.out.println("Pricing - Customers has been created succussfully");
            System.out.println("*****************************************************************");
            return "Pass";
        } catch (Exception e) {
            System.out.println("---------------------Unable to add Pricing - Customers------------------------------");
            System.out.println(e);
            return "Fail";
        }

    }

    public String PricingCustomerSettings(String customer, String scProfileId, String fmProfileId, String invCCY)
            throws InterruptedException {
        try {
            // dynamicWaiting("10", "visible", "lnk_Dashboard");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            WebDriverWait w = new WebDriverWait(driver, 10);
            w.until(ExpectedConditions
                    .presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("lnk_bank"))));
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
            // Thread.sleep(1000);
            // dynamicWaiting("10", "visible", "lnk_CustomerSetting");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_CustomerSetting"))).click();
            dynamicWaiting("10", "click", "btn_CreateCustomerSetting");
            // waitAll(100);
            // Thread.sleep(1000);

            WebElement createnewCustomerSetting = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("btn_CreateCustomerSetting")));
            createnewCustomerSetting.click();
            WebElement pageCount = driver.findElement(By.xpath(PropertiesUtil.getProperty("pageCountCustomerSetting")));
            Select pc = new Select(pageCount);
            pc.selectByVisibleText("100");
            List<WebElement> pageNumber = driver
                    .findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_CustomerSetting")));
            if (pageNumber.size() != 0)
                pageNumber.get(pageNumber.size() - 1).click();
            // Thread.sleep(1000);
            // waitAll(100);
            WebElement inpCustomerId = driver.findElement(By.xpath(PropertiesUtil.getProperty("inpCustomerId")));
            Select customerId = new Select(inpCustomerId);
            customerId.selectByVisibleText(customer);
            WebElement inpInvoiceCurrency = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("inpInvoiceCurrency")));
            inpInvoiceCurrency.sendKeys(invCCY);
            WebElement serviceChargeId = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("serviceChargeProfileId")));
            Select scProfile = new Select(
                    serviceChargeId);
            scProfile.selectByVisibleText(scProfileId);
            WebElement fundManagmentId = driver
                    .findElement(By.xpath(PropertiesUtil.getProperty("fundManagmentProfileId")));
            Select fmProfile = new Select(
                    fundManagmentId);
            fmProfile.selectByVisibleText(fmProfileId);
            WebElement fixRateType = driver.findElement(By.xpath(PropertiesUtil.getProperty("fxrateType")));
            Select ratetype = new Select(fixRateType);
            ratetype.selectByVisibleText("Spot Rate");
            WebElement invoiceBreakDown = driver.findElement(By.xpath(PropertiesUtil.getProperty("invoiceBreakDown")));
            Select BreakDown = new Select(
                    invoiceBreakDown);
            BreakDown.selectByVisibleText("Full Breakdown");
            WebElement invoiceType = driver.findElement(By.xpath(PropertiesUtil.getProperty("invoicetype")));
            Select invoiceTypeSelect = new Select(
                    invoiceType);
            invoiceTypeSelect.selectByVisibleText("Monthly");
            dynamicWaiting("10", "click", "btn_saveCustomerSetting");
            // waitAll(100);

            
             * WebElement saveCustomerSetting = driver
             * .findElement(By.xpath(PropertiesUtil.getProperty("btn_saveCustomerSetting")))
             * ;
             *
             * saveCustomerSetting.click();
             
            JavaScriptExecutor("btn_saveCustomerSetting", "click");
            // Thread.sleep(3000);
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            System.out.println("*****************************************************************");
            System.out.println("Pricing - Customers Settings has been created succussfully");
            System.out.println("*****************************************************************");
            return "Pass";
        } catch (Exception e) {
            System.out.println(
                    "---------------------Unable to add Pricing - Customers Settings------------------------------");
            System.out.println(e);
            return "Fail";
        }

    }

    public String ExportInvoice(String dateRange) throws InterruptedException, ParseException {
        try {
            // waitAll(100);
            // dynamicWaiting("10", "visible", "lnk_Dashboard");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            WebDriverWait w = new WebDriverWait(driver, 10);
            // w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("lnk_bank"))));
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
            // waitAll(100);
            dynamicWaiting("10", "visible", "lnk_exportInvoice");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_exportInvoice"))).click();
            // dynamicWaiting("10", "visible", "inp_date");
            // waitAll(100);
            WebElement inp_date = driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_date")));
            inp_date.clear();
            inp_date.sendKeys(dateRange);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("header_FileAdmin"))).click();
            WebElement btn_GetReport = driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_GetReport")));
            btn_GetReport.click();
            // Thread.sleep(3000);
            // waitAll(100);
            dynamicWaiting("10", "click", "lnk_Dashboard");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            System.out.println("*****************************************************************");
            System.out.println("Excel fetched successfully");
            System.out.println("*****************************************************************");
            return "Pass";
        } catch (Exception e) {
            System.out.println("---------------------Unable to fetch Excel------------------------------");
            System.out.println(e);
            return "Fail";
        }

    }

    public String Delete(String elementToDelete, String keyword) throws InterruptedException {
        String flag = "";
        try {
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            WebDriverWait w = new WebDriverWait(driver, 10);
            switch (keyword) {
                case "FMProfile":
                    w.until(ExpectedConditions
                            .presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("lnk_bank"))));
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
                    dynamicWaiting("10", "visible", "managefundsProfile");
                    // waitAll(100);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("managefundsProfile"))).click();
                    // dynamicWaiting("10", "visible", "pageCountProfile");
                    // waitAll(100);
                    WebElement pageCountProfile = driver
                            .findElement(By.xpath(PropertiesUtil.getProperty("pageCountProfile")));
                    Select pageCountNumber = new Select(
                            pageCountProfile);
                    pageCountNumber.selectByVisibleText("100");
                    // waitAll(100);
                    List<WebElement> fmProfileList = driver
                            .findElements(By.xpath(PropertiesUtil.getProperty("fmProfileReadOnly")));
                    for (int i = 1; i < fmProfileList.size(); i++) {
                        if (fmProfileList.get(i - 1).getText().equalsIgnoreCase(elementToDelete)) {
                            // waitAll(100);
                            String deleteBtnPath = "//table[contains(@ng-table,'fmProfilesTableParams')]//following::tbody//tr["
                                    + i + "]//button[contains(@ng-click,'deleteProfile')]";
//						driver.findElement(By.xpath(deleteBtnPath)).click();
//						SingleClick(deleteBtnPath);
                            JavaScriptExecutor(deleteBtnPath, "click");
                            // waitAll(100);
                            System.out.println("********************************************");
                            System.out.println("Fund Managment Profile " + elementToDelete + " has been deleted..");
                            System.out.println("********************************************");
                            flag = "delete";
                            // waitAll(100);
                            break;
                        }
                    }
                    if (flag != "delete") {
                        System.out.println("----------------------------------------------");
                        System.out.println("Fund Managment Profile " + elementToDelete + " is not deleted...");
                        System.out.println("----------------------------------------------");
                        // waitAll(100);
                        dynamicWaiting("10", "click", "lnk_Dashboard");
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                        return "Fail";
                    } else {
                        // waitAll(100);
                        dynamicWaiting("10", "click", "lnk_Dashboard");
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                        return "Pass";

                    }
                case "fmCurrency":
                    // waitAll(100);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
                    // waitAll(100);
                    dynamicWaiting("10", "visible", "managefundsProfile");
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("managefundsProfile"))).click();
                    // waitAll(100);
                    WebElement PageCountCurrency = driver
                            .findElement(By.xpath(PropertiesUtil.getProperty("pageCountCurrency")));
                    Select pageNumber = new Select(
                            PageCountCurrency);
                    Thread.sleep(1000);
                    pageNumber.selectByVisibleText("100");
                    // waitAll(100);
                    if (driver.findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_currency"))).size() != 0) {
                        int numberOfPages = driver.findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_currency")))
                                .size();
                        for (int pageno = 1; pageno <= (numberOfPages + 1); pageno++) {
                            for (int i = 1; i <= driver
                                    .findElements(By.xpath(PropertiesUtil.getProperty("fmCurrencyReadOnly"))).size(); i++) {

                                if (driver.findElements(By.xpath(PropertiesUtil.getProperty("fmCurrencyReadOnly")))
                                        .get(i - 1).getText().equalsIgnoreCase(elementToDelete)) {
                                    // waitAll(100);
                                    String deleteBtnPath = "//table[contains(@ng-table,'fmProfileCurrencyTableParams')]//following::tbody//tr["
                                            + i + "]//button[contains(@ng-click,'delete')]";
                                    driver.findElement(By.xpath(deleteBtnPath)).click();
                                    System.out.println("********************************************");
                                    System.out
                                            .println("Fund Managment Currency " + elementToDelete + " has been deleted..");
                                    System.out.println("********************************************");
                                    // waitAll(100);
                                    flag = "delete";
                                    break;
                                }
                            }
                            driver.findElement(By
                                    .xpath("//th[@title='FM Profile']//following::a[@class='paginate_button']/span[text()='"
                                            + (pageno + 1) + "']"))
                                    .click();
                            // waitAll(100);
                        }
                        if (flag != "delete") {
                            System.out.println("------------------------------------------");
                            System.out.println("Fund Managment Currency " + elementToDelete + " not deleted..");
                            System.out.println("------------------------------------------");
                            // waitAll(100);
                            dynamicWaiting("10", "click", "lnk_Dashboard");
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                            return "Fail";
                        }
                    } else {
                        for (int i = 1; i <= driver.findElements(By.xpath(PropertiesUtil.getProperty("fmCurrencyReadOnly")))
                                .size(); i++) {
                            if (driver.findElements(By.xpath(PropertiesUtil.getProperty("fmCurrencyReadOnly"))).get(i - 1)
                                    .getText().equalsIgnoreCase(elementToDelete)) {
                                // waitAll(100);
                                String deleteBtnPath = "//table[contains(@ng-table,'fmProfileCurrencyTableParams')]//following::tbody//tr["
                                        + i + "]//button[contains(@ng-click,'delete')]";
                                driver.findElement(By.xpath(deleteBtnPath)).click();
                                System.out.println("********************************************");
                                System.out.println("Fund Managment Currency " + elementToDelete + " has been deleted..");
                                System.out.println("********************************************");
                                // waitAll(100);
                                flag = "delete";
                                break;
                            }
                        }
                        if (flag != "delete") {
                            System.out.println("------------------------------------------");
                            System.out.println("Fund Managment Currency " + elementToDelete + " not deleted...");
                            System.out.println("------------------------------------------");
                            // waitAll(100);
                            dynamicWaiting("10", "click", "lnk_Dashboard");
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                            return "Fail";
                        }
                    }
                    // waitAll(100);
                    dynamicWaiting("10", "click", "lnk_Dashboard");
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                    if (flag == "delete")
                        return "Pass";

                case "scProfile":
                    // waitAll(100);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
                    dynamicWaiting("10", "visible", "manageServiceCharge");
                    // waitAll(100);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("manageServiceCharge"))).click();
                    dynamicWaiting("10", "visible", "pageCountSCprofile");
                    WebElement pcSCProfile = driver.findElement(By.xpath(PropertiesUtil.getProperty("pageCountSCprofile")));
                    Select pageCountNumberSCProfile = new Select(
                            pcSCProfile);
                    pageCountNumberSCProfile.selectByVisibleText("100");
                    // Thread.sleep(200);
                    // waitAll(100);
                    List<WebElement> scProfileList = driver
                            .findElements(By.xpath(PropertiesUtil.getProperty("scProfilereadOnly")));
                    System.out.println(scProfileList.size());
                    for (int i = 1; i < scProfileList.size(); i++) {
                        if (scProfileList.get(i - 1).getText().equalsIgnoreCase(elementToDelete)) {
                            // waitAll(100);
                            String deleteBtnPath = "//table[contains(@ng-table,'scProfilesTableParams')]//following::tbody//tr["
                                    + i + "]//button[contains(@ng-click,'delete')]";
                            driver.findElement(By.xpath(deleteBtnPath)).click();
                            System.out.println("********************************************");
                            System.out.println("Service Charge Profile " + elementToDelete + " has been deleted..");
                            System.out.println("********************************************");
                            // waitAll(100);
                            flag = "delete";
                            break;
                        }
                    }
                    if (flag != "delete") {
                        System.out.println("------------------------------------------");
                        System.out.println("Service Charge Profile " + elementToDelete + " not deleted..");
                        System.out.println("------------------------------------------");
                        // waitAll(100);
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                        return "Fail";
                    } else {
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                        return "Pass";
                    }

                case "scCountry":
                    // waitAll(100);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
                    // waitAll(100);
                    dynamicWaiting("10", "visible", "manageServiceCharge");
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("manageServiceCharge"))).click();
                    // waitAll(100);
                    WebElement pageNumber_Country = driver
                            .findElement(By.xpath(PropertiesUtil.getProperty("pageCount_SCCountry")));
                    Select pageNumberCountry = new Select(
                            pageNumber_Country);
                    // waitAll(100);
                    pageNumberCountry.selectByVisibleText("100");
                    // waitAll(100);
                    int numberOfPagesCountry = driver
                            .findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_SCCountry"))).size();
                    System.out.println(numberOfPagesCountry);
                    if (driver.findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_SCCountry"))).size() != 0) {
                        for (int pageno = 1; pageno <= (numberOfPagesCountry + 1); pageno++) {
                            for (int i = 1; i <= driver
                                    .findElements(By.xpath(PropertiesUtil.getProperty("scCountryReadOnly"))).size(); i++) {
                                if (driver.findElements(By.xpath(PropertiesUtil.getProperty("scCountryReadOnly")))
                                        .get(i - 1).getText().equalsIgnoreCase(elementToDelete)) {
                                    // waitAll(100);
                                    String deleteBtnPath = "//table[contains(@ng-table,'scProfileCountryTableParams')]//following::tbody//tr["
                                            + i + "]//button[contains(@ng-click,'delete')]";
                                    driver.findElement(By.xpath(deleteBtnPath)).click();
                                    System.out.println("********************************************");
                                    System.out.println("Service Charge Country " + elementToDelete + " has been deleted..");
                                    System.out.println("********************************************");
                                    // waitAll(100);
                                    flag = "delete";
                                    break;
                                }
                            }
                            driver.findElement(By.xpath(
                                    "//table[contains(@ng-table,'scProfileCountryTableParams')]//following::a[@class='paginate_button']//span[text()='"
                                            + (pageno + 1) + "']"))
                                    .click();
                            // waitAll(100);
                        }
                        if (flag != "delete") {
                            System.out.println("------------------------------------------");
                            System.out.println("Service Charge Country " + elementToDelete + " not deleted..");
                            System.out.println("------------------------------------------");
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                            return "Fail";
                        }
                    } else {
                        for (int i = 1; i <= driver.findElements(By.xpath(PropertiesUtil.getProperty("scCountryReadOnly")))
                                .size(); i++) {
                            if (driver.findElements(By.xpath(PropertiesUtil.getProperty("scCountryReadOnly"))).get(i - 1)
                                    .getText().equalsIgnoreCase(elementToDelete)) {
                                // waitAll(100);
                                String deleteBtnPath = "//table[contains(@ng-table,'scProfileCountryTableParams')]//following::tbody//tr["
                                        + i + "]//button[contains(@ng-click,'delete')]";
                                driver.findElement(By.xpath(deleteBtnPath)).click();
                                System.out.println("********************************************");
                                System.out.println("Service Charge Country " + elementToDelete + " has been deleted..");
                                System.out.println("********************************************");
                                // Thread.sleep(3000);
                                // waitAll(100);
                                flag = "delete";
                                break;
                            }
                        }
                        if (flag != "delete") {
                            System.out.println("------------------------------------------");
                            System.out.println("Service Charge Country " + elementToDelete + " not deleted...");
                            System.out.println("------------------------------------------");
                            // waitAll(100);
                            dynamicWaiting("10", "visible", "lnk_Dashboard");
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                            return "Fail";
                        }
                    }
                    // waitAll(100);
                    dynamicWaiting("10", "visible", "lnk_Dashboard");
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                    return "Pass";

                case "CustProfile":
                    // waitAll(100);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
                    dynamicWaiting("10", "visible", "lnk_CustomerSetting");
                    // waitAll(100);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_CustomerSetting"))).click();
                    // waitAll(100);
                    WebElement pageCount_Costumer = driver
                            .findElement(By.xpath(PropertiesUtil.getProperty("pageCountCustomer")));
                    Select pageCountCustomer = new Select(
                            pageCount_Costumer);
                    pageCountCustomer.selectByVisibleText("100");
                    // waitAll(100);
                    List<WebElement> custList = driver.findElements(By.xpath(PropertiesUtil.getProperty("customerList")));
                    List<WebElement> oracleId = driver.findElements(By.xpath(PropertiesUtil.getProperty("oracleIdList")));
                    System.out.println(custList.size());
                    for (int i = 1; i < custList.size(); i++) {
                        // String[] elementToBeDeleted = elementToDelete.split(";");
                        if (custList.get(i - 1).getText().equalsIgnoreCase(elementToDelete)) {
//														&& oracleId.get(i - 1).getText().equalsIgnoreCase(elementToBeDeleted[1])) {
                            // waitAll(100);
                            String deleteBtnPath = "//*[contains(text(),'" + elementToDelete
                                    + "')]//following::*[@class='fa fa-trash fa-lg'][1]";
                            driver.findElement(By.xpath(deleteBtnPath)).click();
                            System.out.println("********************************************");
                            System.out.println("Customer Profile " + elementToDelete + " has been deleted..");
                            System.out.println("********************************************");
                            // waitAll(100);
                            flag = "delete";
                            break;
                        }
                    }
                    if (flag != "delete") {
                        System.out.println("------------------------------------------");
                        System.out.println("Customer Profile " + elementToDelete + " not deleted..");
                        System.out.println("------------------------------------------");
                        // waitAll(100);
                        dynamicWaiting("10", "click", "lnk_Dashboard");
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                        return "Fail";
                    } else {
                        // waitAll(100);
                        dynamicWaiting("10", "click", "lnk_Dashboard");
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                        return "Pass";
                    }

                case "custSetting":
                    // w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty("lnk_bank"))));
                    // waitAll(100);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_bank"))).click();
                    // Thread.sleep(1000);
                    // waitAll(100);
                    dynamicWaiting("10", "visible", "lnk_CustomerSetting");
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_CustomerSetting"))).click();
                    dynamicWaiting("10", "visible", "pageCountCustomerSetting");
                    WebElement pageNumber_Custsetting = driver
                            .findElement(By.xpath(PropertiesUtil.getProperty("pageCountCustomerSetting")));
                    Select pageNumberCustSetting = new Select(
                            pageNumber_Custsetting);
                    // Thread.sleep(1000);
                    // waitAll(100);
                    pageNumberCustSetting.selectByVisibleText("100");
                    // Thread.sleep(1000);
                    // waitAll(100);
                    int numberOfPagesCust = driver
                            .findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_CustomerSetting"))).size();
                    // System.out.println(numberOfPagesCust);
                    if (driver.findElements(By.xpath(PropertiesUtil.getProperty("pageNumber_CustomerSetting")))
                            .size() != 0) {

                        for (int pageno = 1; pageno <= (numberOfPagesCust + 1); pageno++) {
                            for (int i = 1; i <= driver
                                    .findElements(By.xpath(PropertiesUtil.getProperty("custSettingReadOnly")))
                                    .size(); i++) {
//							String[] elementToBeDeleted = elementToDelete.split(";");

                                if (driver.findElements(By.xpath(PropertiesUtil.getProperty("custSettingReadOnly")))
                                        .get(i - 1).getText().equalsIgnoreCase(elementToDelete)) {
//																		&& driver.findElements(By.xpath(PropertiesUtil.getProperty("invCCYList"))).get(i - 1)){
//																		.getText().equalsIgnoreCase(elementToBeDeleted[1])) {
                                    // waitAll(100);
                                    String deleteBtnPath = "//*[contains(text(),'" + elementToDelete
                                            + "')]//following::*[@class='fa fa-trash fa-lg'][1]";
                                    driver.findElement(By.xpath(deleteBtnPath)).click();
                                    System.out.println("********************************************");
                                    System.out.println("Customer Settings " + elementToDelete + " has been deleted..");
                                    System.out.println("********************************************");
                                    // Thread.sleep(3000);
                                    // waitAll(100);
                                    flag = "delete";
                                    break;
                                }
                            }
                            driver.findElement(By.xpath(
                                    "//table[contains(@ng-table,'customerSettingsTableParams')]//following::a[@class='paginate_button']//span[text()='"
                                            + (pageno + 1) + "']"))
                                    .click();
                            Thread.sleep(100);
                        }
                        if (flag != "delete") {
                            System.out.println("------------------------------------------");
                            System.out.println("Customer Settings " + elementToDelete + " not deleted..");
                            System.out.println("------------------------------------------");
                            // waitAll(100);
                            dynamicWaiting("10", "click", "lnk_Dashboard");
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                            return "Fail";
                        }
                    } else {
                        for (int i = 1; i <= driver
                                .findElements(By.xpath(PropertiesUtil.getProperty("custSettingReadOnly"))).size(); i++) {
                            // System.out.println(i+" record is
                            // "+driver.findElements(By.xpath(properties.getProperty("custSettingReadOnly"))).get(i-1).getText());

                            if (driver.findElements(By.xpath(PropertiesUtil.getProperty("custSettingReadOnly"))).get(i - 1)
                                    .getText().equalsIgnoreCase(elementToDelete)) {
                                // waitAll(100);
                                String deleteBtnPath = "//table[contains(@ng-table,'customerSettingsTableParams')]//following::tbody//tr["
                                        + i + "]//button[contains(@ng-click,'delete')]";
                                driver.findElement(By.xpath(deleteBtnPath)).click();
                                System.out.println("********************************************");
                                System.out.println("Customer Settings " + elementToDelete + " has been deleted..");
                                System.out.println("********************************************");
                                // Thread.sleep(3000);
                                flag = "delete";
                                break;
                            }
                        }
                        if (flag != "delete") {
                            System.out.println("------------------------------------------");
                            System.out.println("Customer Settings " + elementToDelete + " not deleted...");
                            System.out.println("------------------------------------------");
                            // waitAll(100);
                            dynamicWaiting("10", "click", "lnk_Dashboard");
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                            return "Fail";
                        }
                    }
                    // waitAll(100);
                    dynamicWaiting("10", "click", "lnk_Dashboard");
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                    return "Pass";

                case "docType":
                    Thread.sleep(1000);
                    SingleClick("lnk_docType");
                    WebElement pageNo = driver.findElement(By.xpath(PropertiesUtil.getProperty("pageCount")));
                    Select pages = new Select(pageNo);
                    pages.selectByVisibleText("100");
                    waitAll(100);
                    List<WebElement> docTypeList = driver
                            .findElements(By.xpath(PropertiesUtil.getProperty("lnk_edtDocType")));
                    System.out.println(docTypeList.size());
                    for (int i = 1; i < docTypeList.size(); i++) {
                        if (docTypeList.get(i - 1).getText().equalsIgnoreCase(elementToDelete)) {
                            // waitAll(100);
                            String deleteBtnPath = "//*[contains(text(),'" + elementToDelete
                                    + "')]//following::*[@class='fa fa-trash fa-lg'][1]";
                            driver.findElement(By.xpath(deleteBtnPath)).click();
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_confirm"))).click();
                            System.out.println("********************************************");
                            System.out.println("Document type " + elementToDelete + " has been deleted..");
                            System.out.println("********************************************");
                            // waitAll(100);
                            flag = "delete";
                            break;
                        }
                    }
                    if (flag != "delete") {
                        System.out.println("------------------------------------------");
                        System.out.println("Document type " + elementToDelete + " not deleted..");
                        System.out.println("------------------------------------------");
                        // waitAll(100);
                        dynamicWaiting("10", "click", "lnk_Dashboard");
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                        return "Fail";
                    } else {
                        // waitAll(100);
                        SingleClick("btn_Close");
                        dynamicWaiting("10", "click", "lnk_Dashboard");
                        driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                        return "Pass";
                    }

            }
        } catch (Exception e) {
            System.out.println("----------------------UNABLE TO DELETE ERROR OCCURRED-------------------------");
            System.out.println(e);
            return "Fail";
        }
        return flag;
    }

    public String DeleteAgent(String clientId, String agentId, String country, String city)
            throws InterruptedException {
        String flag = "";
        try {
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            dynamicWaiting("10", "visible", "clientUserMenu");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("clientUserMenu"))).click();
            // waitAll(100);
            String num = driver.findElement(By.xpath(PropertiesUtil.getProperty("lastPage"))).getText();
            System.out.println("Last Page" + num);
            String Flag = "N";
            for (int i = 0; i < Integer.parseInt(num); i++) {
                List<WebElement> emailList = driver
                        .findElements(By.xpath(PropertiesUtil.getProperty("clientEmailList")));
                for (WebElement email : emailList) {
                    if (email.getText().equalsIgnoreCase(clientId)) {
                        System.out.println("Client exist in the list");
                        email.click();
                        Flag = "Y";
                        break;
                    }
                }
                if (Flag.equals("N")) {
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_next"))).click();
                    // waitAll(100);
                } else {
                    break;
                }
            }
            // waitAll(100);
            WebElement pageCount = driver.findElement(By.xpath(PropertiesUtil.getProperty("updateCount")));
            Select pageCountAgent = new Select(pageCount);
            pageCountAgent.selectByVisibleText("100");
            // waitAll(100);
            for (int i = 1; i <= 1; i++) {
                if (driver.findElements(By.xpath(PropertiesUtil.getProperty("agentList"))).get(i - 1).getText()
                        .equalsIgnoreCase(agentId)
                        && driver.findElements(By.xpath(PropertiesUtil.getProperty("cityList"))).get(i - 1).getText()
                        .equalsIgnoreCase(city)
                        && driver.findElements(By.xpath(PropertiesUtil.getProperty("countryList"))).get(i - 1).getText()
                        .contains(country)) {
                    System.out.println(
                            "Agent " + agentId + " with city " + city + " and country " + country + " is found");
                    String deletebtnpath = "//table[contains(@ng-table,'agentsTableParams')]//following-sibling::tbody/tr["
                            + i + "]//a[contains(@ui-sref,'remove')]";
                    JavascriptExecutor jse = (JavascriptExecutor) driver;
                    jse.executeScript("arguments[0].scrollIntoView(true);",
                            driver.findElement(By.xpath(deletebtnpath)));
                    driver.findElement(By.xpath(deletebtnpath)).click();
                    dynamicWaiting("10", "click", "btnconfirm");
                    // waitAll(100);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("btnconfirm"))).click();
                    dynamicWaiting("10", "visible", "alertAgentRemove");
                    JavaScriptExecutor("btn_save", "click");
                    flag = "delete";
                    System.out.println("******************************************************");
                    System.out.println(agentId + " has been deleted...");
                    System.out.println("******************************************************");
                    // waitAll(100);
                }
            }
            if (flag != "delete") {
                System.out.println("******************************************************");
                System.out.println(agentId + " not deleted....");
                System.out.println("******************************************************");
                // waitAll(100);
                dynamicWaiting("10", "visible", "header_FileAdmin");
                driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                return "Fail";
            } else {
                // waitAll(100);
                dynamicWaiting("10", "visible", "lnk_Dashboard");
                driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
                return "Pass";
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("******************************************************");
            System.out.println("----------------unable to delete Agent-----------------------");
            System.out.println("******************************************************");
            return "Fail";
        }

    }

    public String DeleteClient(String clientId) throws InterruptedException {
        try {
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("lnk_Dashboard"))).click();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("clientUserMenu"))).click();
            // waitAll(100);
            String num = driver.findElement(By.xpath(PropertiesUtil.getProperty("lastPage"))).getText();
            System.out.println("Last Page" + num);
            String Flag = "N";
            for (int i = 0; i < Integer.parseInt(num); i++) {
                List<WebElement> emailList = driver
                        .findElements(By.xpath(PropertiesUtil.getProperty("clientEmailList")));
                for (WebElement email : emailList) {
                    if (email.getText().equalsIgnoreCase(clientId)) {
                        System.out.println("Client exist in the list");
                        driver.findElement(
                                By.xpath("//*[text()='" + clientId + "']//following::*[@class='fa fa-trash fa-lg'][1]"))
                                .click();
                        // waitAll(100);
                        if (driver.findElement(By.xpath("//strong")).getText().toLowerCase()
                                .contains(clientId.toLowerCase())) {
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("btnconfirm"))).click();
                        } else {
                            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_Cancel"))).click();
                            System.out.println(" Client ID not deleted");
                        }
                        // waitAll(100);
                        Flag = "Y";
                        break;
                    }
                }
                if (Flag.equals("N")) {
                    driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_next"))).click();
                    // waitAll(100);
                } else {
                    break;
                }
            }
            // waitAll(100);

//							for(int i=0;i<Integer.parseInt(num);i++) {
//								int rowid=driver.findElements(By.xpath("//table[contains(@ng-table,'clientsTableParams')]//a[contains(@ui-sref,'update')]")).size();
//								for (int row=0;row<rowid;row++) {
//									if (driver.findElements(By.xpath("//table[contains(@ng-table,'clientsTableParams')]//a[contains(@ui-sref,'update')]")).get(row).getText().toLowerCase().contains(clientId.toLowerCase())) {
//										System.out.println("Push1");
//										driver.findElement(By.xpath("//table[contains(@ng-table,'clientsTableParams')]//tr["+(row+1)+"]//a[contains(@ui-sref,'remove')][1]")).click();
//										// waitAll(100);
//										Thread.sleep(7000);
//										System.out.println("Push");
//										dynamicWaiting("10", "visible", PropertiesUtil.getProperty("btnconfirm"));
//										if(driver.findElement(By.xpath("//strong")).getText().toLowerCase().contains(clientId.toLowerCase())){
//										driver.findElement(By.xpath(PropertiesUtil.getProperty("btnconfirm"))).click(); }
//										else { driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_Cancel"))).click();}
////										JavaScriptExecutor("btnconfirm","click");
//										System.out.println("Push2");
//										// waitAll(100);
//										System.out.println("*******************************");
//										System.out.println("************Client has been deleted************************");
//										System.out.println("*******************************");
//										Flag="Y";
//									}
//								}
//
//								if(Flag.equals("N")) {
//									driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_next"))).click();
//									// waitAll(100);
//								}
//								else {
//
//									// waitAll(100);
//									return"Pass";
//								}
//							}
            return "Pass";
        } catch (Exception e) {

            System.out.println("********************************");
            System.out.println("************Client not deleted***********************");
            System.out.println("*******************************");
            System.out.println(e);
            return "Fail";
        }

    }

    public String createNewOrg(String newOrgName, String childOrgName, String application) throws InterruptedException {
        String child[] = childOrgName.split(";");
        // dynamicWaiting("10", "visible", "agm_dropOrg");
        waitUntilAngularReady();
        driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_dropOrg"))).click();
        // dynamicWaiting("10", "visible", "agm_linkChangeOrg");
        waitUntilAngularReady();
        driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_linkChangeOrg"))).click();
        // dynamicWaiting("10", "visible", "agm_btnAddOrg");
        // waitAll(100);
        JavaScriptExecutor("agm_btnAddOrg", "click");
        // waitAll(100);
        driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_txtOrgName"))).sendKeys(newOrgName);
        // waitAll(100);
        JavaScriptExecutor("agm_btnSaveOrg", "click");
        // waitAll(100);
        driver.findElement(By.xpath(PropertiesUtil.getProperty("org_tabTree"))).click();
        // waitAll(100);

        for (int i = 0; i < child.length; i++) {
            // waitAll(100);
            JavaScriptExecutor("org_btnAddChildOrg", "click");
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("org_txtChildOrgName"))).sendKeys(child[i]);
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnSaveChildOrg"))).click();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnSetupOrg"))).click();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxInheritApps"))).click();
            // waitAll(100);

            if (application.equalsIgnoreCase("admin")) {

                // Adding application
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnAddFileAdmin"))).click();
                Thread.sleep(3000);
                driver.switchTo().alert().accept();
                Thread.sleep(3000);
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnDone"))).click();

                // Assigning Role to application
                // waitAll(100);
                JavaScriptExecutor("org_linkEditRole", "click");
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxFileAdmin"))).click();
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnSaveRole"))).click();

            } else if (application.equalsIgnoreCase("instruct")) {

                // Adding application
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnAddFileInstruct"))).click();
                Thread.sleep(3000);
                driver.switchTo().alert().accept();
                Thread.sleep(7000);
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnDone"))).click();

                // Assigning Role to application
                // waitAll(100);
                JavaScriptExecutor("org_linkEditRole", "click");
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxFileInstruct"))).click();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxFileInstructCustom"))).click();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxFileInstructPatents"))).click();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxFileInstructTMark"))).click();
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnSaveRole"))).click();

            } else if (application.equalsIgnoreCase("receive")) {

                // Adding application
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnAddFileReceive"))).click();
                Thread.sleep(3000);
                driver.switchTo().alert().accept();
                Thread.sleep(5000);
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnDone"))).click();

                // Assigning Role to application
                // waitAll(100);
                JavaScriptExecutor("org_linkEditRole", "click");
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxFileReceive"))).click();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxFileReceiveCustom"))).click();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxFileReceivePatents"))).click();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_chkboxFileReceiveTMark"))).click();
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("org_btnSaveRole"))).click();
                return "Pass";
            } else {
                System.out.println("Not a valid application.");
                return "Fail";
            }
        }
        return "Pass";

        // }
    }

    public void switchToLastWindow() {
        try {
            Set<String> windows = driver.getWindowHandles();
            int winTotal = windows.size();
            int i = 0;
            for (String win : windows) {
                i++;
                if (i == winTotal) {
                    driver.switchTo().window(win);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String createNewUser(String firstName, String lastName, String emailId, String language, String orgName)
            throws InterruptedException {

        try {
            // Navigate to Users tab
//			Thread.sleep(3000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_tabUsers"))).click();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_btnAddUser"))).click();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_txtFirstName"))).sendKeys(firstName);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_txtLastName"))).sendKeys(lastName);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_txtEmail"))).sendKeys(emailId);
            Select lang = new Select(driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_dropLanguage"))));
            lang.selectByVisibleText(language);
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_btnSaveUser"))).click();

            // Attach User to a Org
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_tabPermissions"))).click();
            // waitAll(100);
            Thread.sleep(15000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_btnEditPermission"))).click();
            // waitAll(100);
            driver.findElement(By.xpath("//a[contains(text(),'" + orgName + "')]")).click();
            // JavaScriptExecutor("//a[contains(text(),'"+orgName+"')]","click");

            // waitAll(100);
            Thread.sleep(5000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_btnSavePermission"))).click();

            // Activate User
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("logs_tabLogs"))).click();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("logs_linkEmail"))).click();
            // waitAll(100);
            Select logs = new Select(driver.findElement(By.xpath(PropertiesUtil.getProperty("logs_dropChangeOrg"))));
            logs.selectByIndex(1);
            Thread.sleep(7000);
            logs.selectByIndex(2);
            Thread.sleep(7000);
            logs.selectByIndex(1);
            Thread.sleep(7000);
            logs.selectByIndex(2);
            Thread.sleep(7000);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("logs_txtTo"))).sendKeys(emailId);
            Thread.sleep(7000);
            driver.findElement(By.xpath("//div[contains(text(),'" + emailId + "')]")).click();
            // waitAll(100);
            Thread.sleep(5000);
            System.out.println(driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-scope")).getText());
            driver.findElement(By.cssSelector(".form-control.btn.btn-primary.ng-scope")).click();
            // waitAll(100);
            System.out.println("window : " + driver.getWindowHandle());
            driver.findElement(By.cssSelector(".email-body.ng-binding a")).click();

            // Switch to next tab
            switchToLastWindow();
            driver.switchTo().defaultContent();
            Thread.sleep(5000);
            // // waitAll(100);

            // Enter password and activate user
            driver.findElement(By.cssSelector("#password")).sendKeys("India@123");
            driver.findElement(By.cssSelector("#password2")).sendKeys("India@123");
            driver.findElement(By.cssSelector(".btn.btn-block.btn-primary.btn-bottom")).click();
            // waitAll(100);
            Thread.sleep(3000);
            JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
            myExecutor.executeScript(("window.close()"));
            // // waitAll(100);
            Thread.sleep(3000);
            switchToLastWindow();
            driver.switchTo().defaultContent();
            // // waitAll(100);
            // driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_tabOrganization"))).click();
            return "Pass";
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("cached exception");
            return "Fail";
        }
    }

    public String deleteUser(String orgName, String emailId) throws InterruptedException {
        try {

            // Change Organisation
            driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_dropOrg"))).click();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_linkChangeOrg"))).click();
            // waitAll(100);

            // Search Organisation
            driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_txtSearchField"))).clear();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_txtSearchField"))).sendKeys(orgName);
            // waitAll(100);
            Thread.sleep(3000);
            // Select Searched Organisation
            driver.findElement(By.xpath("//div[contains(text(),'" + orgName + "')]/parent::div")).click();
            // waitAll(100);

            // Search User
            driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_tabUsers"))).click();
            // waitAll(100);
            driver.findElement(By.xpath("//div[contains(text(),'" + emailId + "')]/parent::div")).click();
            // waitAll(100);

            // Check If user is active
            Select usrSelection = new Select(
                    driver.findElement(By.cssSelector(PropertiesUtil.getProperty("usr_dropStatus"))));
            String userStatus = usrSelection.getFirstSelectedOption().getText();
            if (userStatus.equalsIgnoreCase("active")) {
                System.out.println("User Status : " + userStatus);

                Select usrDropStatus = new Select(
                        driver.findElement(By.cssSelector(PropertiesUtil.getProperty("usr_dropStatus"))));
                usrDropStatus.selectByVisibleText("Disabled");
                // waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("usr_btnSaveUser"))).click();
                // waitAll(100);
            }

            driver.findElement(By.cssSelector(PropertiesUtil.getProperty("user_btnDelete"))).click();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            // waitAll(100);
            return "Pass";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Pass";
    }

    public String deleteOrg(String orgName) {
        try {
            // Change Organisation
            driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_dropOrg"))).click();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_linkChangeOrg"))).click();
            // waitAll(100);

            // Search Organisation
            driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_txtSearchField"))).clear();
            // waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("agm_txtSearchField"))).sendKeys(orgName);
            // waitAll(100);
            Thread.sleep(3000);

            // Select Searched Organisation
            driver.findElement(By.xpath("//div[contains(text(),'" + orgName + "')]/parent::div")).click();
            // waitAll(100);
            // Click delete button to delete the Org
            driver.findElement(By.cssSelector(PropertiesUtil.getProperty("user_btnDelete"))).click();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            // waitAll(100);
            return "Pass";
        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }
    }

    public void waitForJQueryLoad() {
        // Wait for jQuery to load
        WebDriver jsWaitDriver = driver;
        WebDriverWait jsWait = new WebDriverWait(jsWaitDriver, 10);
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;
        ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) driver)
                .executeScript("return jQuery.active") == 0);

        // Get JQuery is Ready
        boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

        // Wait JQuery until it is Ready!
        if (!jqueryReady) {
            System.out.println("JQuery is NOT Ready!");
            // Wait for jQuery to load
            jsWait.until(jQueryLoad);
        } else {
            System.out.println("JQuery is Ready!");
        }
    }

    // Wait for Angular Load
    public void waitForAngularLoad() {
        WebDriver jsWaitDriver = driver;
//		WebDriverWait  jsWait = new WebDriverWait(jsWaitDriver, 30);
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;
        WebDriverWait wait = new WebDriverWait(driver, 30);

        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        // Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> Boolean
                .valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());

        // Get Angular is Ready
        boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

        // Wait ANGULAR until it is Ready!
        if (!angularReady) {
//			System.out.println("ANGULAR is NOT Ready!");
            // Wait for Angular to load
            wait.until(angularLoad);
        } else {
//			System.out.println("ANGULAR is Ready!");
        }
    }

    // Wait Until JS Ready
    public void waitUntilJSReady() {
        WebDriver jsWaitDriver = driver;
//		WebDriverWait  jsWait = new WebDriverWait(jsWaitDriver, 30);
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").toString().equals("complete");

        // Get JS is Ready
        boolean jsReady = (Boolean) jsExec.executeScript("return document.readyState").toString().equals("complete");

        // Wait Javascript until it is Ready!
        if (!jsReady) {
//			System.out.println("JS in NOT Ready!");
            // Wait for Javascript to load
            wait.until(jsLoad);
        } else {
//			System.out.println("JS is Ready!");
        }
    }

    // Wait Until JQuery and JS Ready
    public void waitUntilJQueryReady() {
        WebDriver jsWaitDriver = driver;
//		WebDriverWait  jsWait = new WebDriverWait(jsWaitDriver, 10);
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;

        // First check that JQuery is defined on the page. If it is, then wait AJAX
        Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined == true) {
            // Pre Wait for stability (Optional)
            sleep(20);

            // Wait JQuery Load
            waitForJQueryLoad();

            // Wait JS Load
            waitUntilJSReady();

            // Post Wait for stability (Optional)
            sleep(20);
        } else {
//			System.out.println("jQuery is not defined on this site!");
        }
    }

    // Wait Until Angular and JS Ready
    public String waitUntilAngularReady() {
        try {
            WebDriver jsWaitDriver = driver;
//			WebDriverWait jsWait = new WebDriverWait(jsWaitDriver, 30);
            JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;
            // First check that ANGULAR is defined on the page. If it is, then
            // wait ANGULAR
            Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
            if (!angularUnDefined) {
                Boolean angularInjectorUnDefined = (Boolean) jsExec
                        .executeScript("return angular.element(document).injector() === undefined");
                if (!angularInjectorUnDefined) {
                    // Pre Wait for stability (Optional)
                    sleep(20);

                    // Wait Angular Load
                    waitForAngularLoad();

                    // Wait JS Load
                    waitUntilJSReady();

                    // Post Wait for stability (Optional)
                    sleep(20);
                } else {
//					System.out.println("Angular injector is not defined on this site!");
                }
            } else {
//				System.out.println("Angular is not defined on this site!");
            }
            return "Pass";
        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }
    }

    // Wait Until JQuery Angular and JS is ready
    public void waitJQueryAngular() {
        waitUntilJQueryReady();
        // waitAll(100);
    }

    public void sleep(Integer seconds) {
        long secondsLong = (long) seconds;
        try {
            Thread.sleep(secondsLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitAll(Integer seconds) {

        try {
            Thread.sleep((long) seconds);
//			// waitAll(100);
//			waitForJQueryLoad();
//			waitUntilJSReady();
//			waitForAngularLoad();
            waitUntilAngularReady();
//			waitJQueryAngular();
//			waitUntilJQueryReady();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String Wait() {

        try {
            JSWaiter.sleep(5000);
//			Thread.sleep(1000);
            JSWaiter.waitForAngularLoad();
            // JSWaiter.waitForJQueryLoad();
            // JSWaiter.waitJQueryAngular();
            JSWaiter.waitUntilAngularReady();
            // JSWaiter.// waitAll(100);
            // JSWaiter.waitUntilJSReady();
            // JSWaiter.waitUntilJQueryReady();
            return "Pass";
        } catch (Exception e) {
            // e.printStackTrace();
            return "Pass";
        }
    }

    public String validateMultipleList(String listPath1, String listPath2) {
        try {
            boolean chk = false;
            Properties properties = System.getProperties();
            // waitAll(100);
            List<WebElement> elementList1 = driver.findElements(By.xpath(PropertiesUtil.getProperty(listPath1)));
            List<WebElement> elementList2 = driver.findElements(By.xpath(PropertiesUtil.getProperty(listPath2)));
            if (elementList1.size() == 0 || elementList2.size() == 0) {
                System.out.println("Elements in list are empty");
                chk = false;
            } else {
                for (int i = 0; i <= elementList1.size() - 1; i++) {
                    System.out
                            .println("UI Element of list 1 at postion " + i + "  is " + elementList1.get(i).getText());
                    System.out.println("UI Element of list 2 at postion " + i + "  is "
                            + elementList2.get(i).getText().replaceAll("\n", ". "));
                    if (elementList1.get(i).getText().toLowerCase()
                            .contains(elementList2.get(i).getText().toLowerCase().replaceAll("\n", ". "))
                            && !elementList1.get(i).getText().equals("") && !elementList2.get(i).getText().equals("")) {
                        System.out.println("Value matches in both list");
                        chk = true;
                    } else {
                        System.out.println("Value does not matches in both list");
                        chk = false;
                        break;
                    }
                }
            }
            if (chk)
                return "Pass";
            else
                return "Fail";
        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }
    }

    public String setDataProperty(String Elementpath, String Property, String PropertyName) {
        try {
            waitForAngularLoad();
            Properties properties = System.getProperties();
            String fld = properties.getProperty(Property);
            String val;
            if (fld != null) {
                val = fld;
            } else {
                val = Property.toString();
            }
            System.out.println("The Property to be set for attribute " + val + "  is  : " + PropertyName);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('" + Property + "', '" + PropertyName + "')",
                    driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))));
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

    }

    // Validate Communication message for Client while the instruction is reassigned
    // to different Agent after rejection
    public String ClientCommunicationMessages(String Reference) {
        Boolean chk = false;
        // List of all the communication message to be displayed
        ArrayList<String> val = new ArrayList<String>();
        val.add(" has been passed to the agent.");
        // Comment as added by Client
        val.add("AutomationComment");
        val.add(" has been received by the agent as of " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        val.add(" has been rejected  by the agent as of " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        val.add(" has been passed to the agent.");
        val.add(" has been received by the agent as of " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        val.add(" has been acknowledged  by the agent as of " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        // val.add(" has been sent to the PTO by the agent as of "+new
        // SimpleDateFormat("dd MMM yyyy").format(new Date()));
        val.add(" as of " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        val.add(" has been received for the instruction ");
        // val.add(" has been completed by the agent as of "+new SimpleDateFormat("dd
        // MMM yyyy").format(new Date()));
        val.add(" The instruction has been completed and the communication channel closed ");

        // List of all the communication message as displayed on UI
        List<WebElement> elements = driver.findElements(By.xpath(PropertiesUtil.getProperty("comMessage")));
        for (int i = 0; i <= elements.size() - 1; i++) {
            System.out.println("UI Element Text of: " + i + " element is " + elements.get(i).getText());
            System.out.println("Compared text is: " + Reference + val.get(i));

            // validating Comment as added by Client
            if (val.get(i).equals("AutomationComment")) {
                if (elements.get(i).getText().toLowerCase().trim().replaceAll("[\t\n\r\\s]", "")
                        .contains(val.get(i).toLowerCase().trim().replaceAll("[\t\n\r\\s]", ""))) {
                    System.out.println("match found between compared text and actual text as displayed on UI for message " + i);
                    chk = true;
                } else {
                    System.out.println("No Match found between compared and actual text as displayed on UI for message " + i);
                    chk = false;
                    break;
                }
            }

            // Validating message after concatenating reference as provided with the desired
            // message & trimming all the space and new line character
            else if (elements.get(i).getText().toLowerCase().trim().replaceAll("[\t\n\r\\s]", "")
                    .contains(Reference.toLowerCase() + val.get(i).toLowerCase().trim().replaceAll("[\t\n\r\\s]", ""))
                    && !elements.get(i).getText().equals("")
                    || elements.get(i).getText().toLowerCase().trim().replaceAll("[\t\n\r\\s]", "")
                    .contains(val.get(i).toLowerCase().trim().replaceAll("[\t\n\r\\s]", ""))) {
                System.out.println("match found between compared text and actual text as displayed on UI for message " + i);
                chk = true;
            } else {
                System.out.println("No Match found between compared and actual text as displayed on UI for message " + i);
                chk = false;
                break;
            }
        }
        if (chk)
            return "Pass";
        else
            return "Fail";
    }

    // Validate Communication message for Agent rejecting instruction and Comment as
    // added by client
    public String AgentRejectedCommunication(String Reference) {
        Boolean chk = false;

        // List of all the communication message to be displayed on rejecting the
        // instruction
        ArrayList<String> val = new ArrayList<String>();
        val.add(" has been passed to the agent.");
        // Comment as added by Client
        val.add("AutomationComment");
        val.add(" has been received by the agent as of " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        val.add(" has been rejected  by the agent as of " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));

        // List of all the communication message as displayed on UI
        List<WebElement> elements = driver.findElements(By.xpath(PropertiesUtil.getProperty("comMessage")));
        for (int i = 0; i <= elements.size() - 1; i++) {
            System.out.println("UI Element Text of: " + i + " element is " + elements.get(i).getText());
            System.out.println("Compared text is: " + Reference + val.get(i));

            // validating Comment as added by Client
            if (val.get(i).equals("AutomationComment")) {
                if (elements.get(i).getText().toLowerCase().trim().replaceAll("[\t\n\r\\s]", "")
                        .contains(val.get(i).toLowerCase().trim().replaceAll("[\t\n\r\\s]", ""))) {
                    System.out.println(
                            "match found between compared text and actual text as displayed on UI for message " + i);
                    chk = true;
                } else {
                    System.out.println(
                            "No Match found between compared and actual text as displayed on UI for message " + i);
                    chk = false;
                    break;
                }
            }

            // Validating message after concatenating reference as provided with the desired
            // message & trimming all the space and new line character
            else if (elements.get(i).getText().toLowerCase().trim().replaceAll("[\t\n\r\\s]", "")
                    .contains(Reference.toLowerCase() + val.get(i).toLowerCase().trim().replaceAll("[\t\n\r\\s]", ""))
                    && !elements.get(i).getText().equals("") || elements.get(i).getText().toLowerCase().trim().replaceAll("[\t\n\r\\s]", "")
                    .contains(val.get(i).toLowerCase().trim().replaceAll("[\t\n\r\\s]", ""))) {
                System.out.println(
                        "match found between compared text and actual text as displayed on UI for message " + i);
                chk = true;
            } else {
                System.out
                        .println("No Match found between compared and actual text as displayed on UI for message " + i);
                chk = false;
                break;
            }
        }
        if (chk)
            return "Pass";
        else
            return "Fail";
    }

    // validate Communication message for Agent receiving instruction
    public String AgentCompletedInstructionCommunication(String Reference) {
        Boolean chk = false;
        ArrayList<String> val = new ArrayList<String>();

        // List of all the communication message to be displayed on completing the
        // instruction
        val.add(" has been passed to the agent.");
        val.add(" has been received by the agent as of " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        val.add(" has been acknowledged  by the agent as of " + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        val.add(" has been sent to the PTO by the agent as of "
                + new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        // val.add(" as of "+new SimpleDateFormat("dd MMM yyyy").format(new Date()));
        val.add(" has been received for the instruction ");
        // val.add(" has been completed by the agent as of "+new SimpleDateFormat("dd
        // MMM yyyy").format(new Date()));
        val.add(" The instruction has been completed and the communication channel closed");

        // List of all the communication message as displayed on UI
        List<WebElement> elements = driver.findElements(By.xpath(PropertiesUtil.getProperty("comMessage")));
        for (int i = 0; i <= elements.size() - 1; i++) {
            System.out.println("UI Element Text of: " + i + " element is " + elements.get(i).getText());
            System.out.println("Compared text is: " + Reference + val.get(i));

            // Validating message after concatenating reference as provided with the desired
            // message & trimming all the space and new line character
            if (elements.get(i).getText().toLowerCase().trim().replaceAll("[\t\n\r\\s]", "")
                    .contains(Reference.toLowerCase() + val.get(i).toLowerCase().trim().replaceAll("[\t\n\r\\s]", ""))
                    && !elements.get(i).getText().equals("")
                    || elements.get(i).getText().toLowerCase().trim().replaceAll("[\t\n\r\\s]", "")
                    .contains(val.get(i).toLowerCase().trim().replaceAll("[\t\n\r\\s]", ""))) {
                System.out.println(
                        "Match found between compared text and actual text as displayed on UI for message " + i);
                chk = true;
            } else {
                System.out
                        .println("No Match found between compared and actual text as displayed on UI for message " + i);
                chk = false;
                break;
            }
        }
        if (chk)
            return "Pass";
        else
            return "Fail";
    }

    public String normalClick(String Element) {
        // waitAll(100);
        driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).click();
        return "Pass";
    }

    public String actionOnDataProperty(String Elementpath, String property, String propertyName, String action) {
        try {
            // waitAll(100);
            switch (action) {
                case "Click":
                    System.out.println(driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath)))
                            .getAttribute(property.toString()));
                    if (!driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath)))
                            .getAttribute(property.toString()).contains(propertyName))
                        driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).click();
                    else {
                        System.out.println("Element already clicked");
                        return "Fail";
                    }

                    break;

                default:
                    break;
            }

            return "Pass";
        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }
    }

    // **********************Added by
    // Himanshi****************************************************
    public String validateIPType(String searchSection, String columnName) {
        try {
            String lastPageNumber;
            List<WebElement> headers = driver.findElements(By.xpath(PropertiesUtil.getProperty("dashboardHeading")));

            for (int i = 0; i < headers.size(); i++) {
                if (headers.get(i).getText().toLowerCase().contains(searchSection.toLowerCase())
                        && !headers.get(i).getAttribute("class").contains("active")) {
                    headers.get(i).click();
                    break;
                }
            }

            Set<String> set = new HashSet<String>();

            // Setting 100 as number of record to be displayed
            Select pagecount = new Select(driver.findElement(By.xpath(PropertiesUtil.getProperty("headerPageCount"))));
            pagecount.selectByVisibleText("100");
            waitAll(5000);
            List<WebElement> tblRows = new ArrayList<WebElement>();
            tblRows = driver.findElements(By.xpath(PropertiesUtil.getProperty("tbl_row")));
            if (tblRows.size() != 0) {
                if ((tblRows.size() < 10) || (tblRows.size() <= 100))
                    lastPageNumber = "1";
                else
                    lastPageNumber = driver.findElement(By.xpath(PropertiesUtil.getProperty("pageNumber_Last")))
                            .isDisplayed()
                            ? driver.findElement(By.xpath(PropertiesUtil.getProperty("pageNumber_Last")))
                            .getText()
                            : "1";

                for (int pages = 1; pages <= Integer.parseInt(lastPageNumber); pages++) {
                    for (int row = 1; row <= tblRows.size(); row++) {
                        if (driver.findElements(By.xpath(
                                "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                        + row + "]//td[contains(@header-title,'" + columnName + "')]"))
                                .size() <= 1
                                || driver.findElements(By.xpath(
                                "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                        + row + "]//td[contains(@header-title,'" + columnName + "')]//div"))
                                .size() <= 1) {
                            WebElement searchElementTable = driver.findElement(By.xpath(
                                    "(//div[contains(@class,'dashboard-heading active')]//following::table/tbody)[1]//tr["
                                            + row + "]//td[contains(@header-title,'" + columnName + "')]"));
                            set.add(searchElementTable.getText());
                        }
                    }
                }

                if (!set.isEmpty() && set.size() == 3) {
                    System.out.println("All IP types found");
                    System.out.println("The set is: " + set);
                    return "Pass";
                } else if (!set.isEmpty() && set.size() == 1) {
                    Iterator<String> itr = set.iterator();
                    while (itr.hasNext()) {
                        String firstElement = itr.next();

                        if (firstElement.equalsIgnoreCase("Patent")) {
                            System.out.println("Patent IP Type found");
                            System.out.println("The set is: " + set);
                            return "Pass";
                        } else if (firstElement.equalsIgnoreCase("Custom")) {
                            System.out.println("Custom IP Type found");
                            System.out.println("The set is: " + set);
                            return "Pass";
                        } else if (firstElement.equalsIgnoreCase("Trade mark")) {
                            System.out.println("Trade mark IP Type found");
                            System.out.println("The set is: " + set);
                            return "Pass";
                        }

                    }
                    return "Fail";
                }
            } else
                return "Pass";

        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }
        return "Fail";

    }
    // **********************Added by
    // Himanshi****************************************************

    // ***********************Added by
    // Himanshi******************************************************************************
    public String selectIPType(String Element) {
        try {
            waitUntilAngularReady();
            driver.findElement(By.xpath(Element)).click();
            waitUntilAngularReady();
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }
    // ***********************Added by
    // Himanshi******************************************************************************

    public String IsDisabled(String Element) // Developed by Nazim
    {
        try {
            if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isEnabled()) {
                System.out.println(Element + " is enabled");
                // TestRunner.test.log(Status.PASS,"<b><I>"+Element+"</b></I> is displayed");
                return "Fail";
            } else {
                // TestRunner.test.log(Status.FAIL,"<b><I>"+Element+"</b></I> is not
                // displayed");
                System.out.println(Element + " is disabled");
                return "Pass";
            }
        } catch (Exception e) {
            return "Fail";
        }
    }

    
     * Method Name : verifyFieldExistenece Parameters : Element Name, Element Type
     * Return type : String Objective : To identify existence of an element by a
     * name and type Author : Mayank Srivastava Date : 7th Sept 2018
    

    public String verifyExisteneceByNameAndType(String name, String type) {
        String status;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//*[contains(text(),'" + name + "')]/following::" + type + ")[1]")));
            boolean st = driver
                    .findElement(By.xpath("(//*[contains(text(),'" + name + "')]/following::" + type + ")[1]"))
                    .isDisplayed();
            System.out.println("Display value of the field  is : " + st);
            if (!st) {
                status = "Fail";
                System.out.println(name + " " + type + " field does not exist. ");
            } else {
                status = "Pass";
                System.out.println(name + " " + type + " field is present on the page. ");
            }

        } catch (Exception e) {
            // e.printStackTrace();
            status = "Fail";
        }

        return status;
    } 

    
     * Method Name : performAction Parameters : Action Type, Element Name,
     * Text(optional) to be entered Return type : String Objective : To input text
     * to an element by a name and text Author : Mayank Srivastava Date : 7th Sept
     * 2018
     
    public String performAction(String action_type, String tag_name, String element_name, String element_position,
                                String text) {
        // waitAll(100);
        try {
            if (action_type.equals("click")) {
                if (element_name.startsWith("//") || (element_name.startsWith("(//"))) {
                    driver.findElement(By.xpath(element_name)).click();
                    return "Pass";
                } else if (element_name.startsWith("OR.")) {
                    String[] prop_element = element_name.split("R.");
                    System.out.println(prop_element.length);
                    System.out.println(prop_element[1]);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).click();
                    return "Pass";
                } else if (driver.findElements(By.xpath("//*[@" + tag_name + "='" + element_name + "']")).size() > 0) {
                    driver.findElement(By.xpath("//*[@" + tag_name + "='" + element_name + "']")).click();
                    return "Pass";
                } else if (tag_name.equals("select")) {
                    driver.findElement(By.xpath("(//*[contains(text(),'" + element_name
                            + "')]/following::" + tag_name + ")[" + element_position + "]")).click();
                    return "Pass";
                } else {
                    System.out.println("Actions not allowed");
                    return "Fail";
                }
            } else if (action_type.equals("type")) {
                if (element_name.startsWith("//") || element_name.startsWith("(//")) {
                    driver.findElement(By.xpath(element_name)).clear();
                    driver.findElement(By.xpath(element_name)).sendKeys(text);
                } else if (element_name.startsWith("OR.")) {
                    String[] prop_element = element_name.split("R.");
                    System.out.println(prop_element.length);
                    System.out.println(prop_element[1]);
                    driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).clear();
                    driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).sendKeys(text);
                } else {
//                    driver.findElement(By.xpath("(//*[contains(text(),'" + element_name + "')]/following::" + tag_name + ")[" + element_position + "]")).clear();
//                    driver.findElement(By.xpath("(//*[contains(text(),'" + element_name + "')]/following::" + tag_name + ")[" + element_position + "]")).sendKeys(text);
                      driver.findElement(By.xpath("(//*[text()='" + element_name + "']/following::" + tag_name + ")[" + element_position + "]")).clear();
                      driver.findElement(By.xpath("(//*[text()='" + element_name + "']/following::" + tag_name + ")[" + element_position + "]")).sendKeys(text);
                }
                return "Pass";
            } else if (action_type.equals("select")) {

                return "Pass";
            } else {
                System.out.println("Actions allowed : click or type");
                return "Fail";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }
    }

    
     * Method Name : validateErrorMessage Parameters : Message text Return type :
     * String Objective : To validate error messages Author : Mayank Srivastava Date
     * : 7th Sept 2018
     
    public String validateErrorMessage(String ng_if, String message) {
        String status = "";
        boolean st;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);

            if (!ng_if.equals("")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='" + message + "'][contains(@ng-if,'" + ng_if + "')]")));
                st = driver.findElement(By.xpath("//span[text()='" + message + "'][contains(@ng-if,'" + ng_if + "')]"))
                        .isDisplayed();
            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + message + "']")));
                st = driver.findElement(By.xpath("//span[text()='" + message + "']")).isDisplayed();
            }

            if (!st) {
                status = "Fail";
                System.out.println(message + " does not exist. ");
            } else {
                status = "Pass";
                System.out.println(message + " is present on the page. ");
            }
        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }

        return status;
    }

    
     * Method Name : verifyLabelExistanece Parameters : Element FieldName, Element
     * TagName Return type : String Objective : To identify existence of a label by
     * a FieldName and TagName Author : Mayank Srivastava Date : 11th Sept 2018
     

    public String verifyElementExistenece(String FieldName, String TagName, String element_position) {
        System.out.println("FieldName : " + FieldName);
        String status;
        boolean st = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            if (FieldName.startsWith("//") || (FieldName.startsWith("(//"))) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FieldName)));
                st = driver.findElement(By.xpath(FieldName)).isDisplayed();
            } else if (FieldName.startsWith("OR.")) {
                String[] prop_element = FieldName.split("R.");
                System.out.println("Extracted xpath from Function.properties file : " + PropertiesUtil.getProperty(prop_element[1]));
                log.info("Extracted xpath from Function.properties file : " + PropertiesUtil.getProperty(prop_element[1]));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesUtil.getProperty(prop_element[1]))));
                st = driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).isDisplayed();
            } else if (TagName.equals("label") || TagName.equals("span") || TagName.equals("translate")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//" + TagName + "[contains(text(),'" + FieldName + "')]")));
                st = driver.findElement(By.xpath("//" + TagName + "[contains(text(),'" + FieldName + "')]"))
                        .isDisplayed();
            } else
//                if (TagName.equals("input") || TagName.equals("select") || TagName.equals("textarea"))
            {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(text(),'" + FieldName
                        + "')]/following::" + TagName + ")[" + element_position + "]")));
                st = driver.findElement(By.xpath("(//*[contains(text(),'" + FieldName + "')]/following::" + TagName
                        + ")[" + element_position + "]")).isDisplayed();
            }

            System.out.println("Display value of the field  is : " + st);
            if (!st) {
                status = "Fail";
                System.out.println(FieldName + " field does not exist.");
            } else {
                status = "Pass";
                System.out.println(FieldName + " field is present on the page.");
            }
        } catch (Exception e) {
            status = "Fail";
        }

        return status;
    }

    
     * Method Name : getText Parameters : Element locator Return type : String
     * Objective : To return the text of an element Author : Mayank Srivastava Date
     * : 18th Sept 2018
     

    public String getText(String locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
//            highLightElement(locator);
            return driver.findElement(By.xpath(locator)).getText();
        } catch (Exception e) {
            log.fatal("Exception found: ", e);
            return "Fail";
        }
    }

    public String getText(String locator) {
        String[] prop_element = {};
        String element_text = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            if (locator.startsWith("OR.")) {
                prop_element = locator.split("R.");
                log.info("locator --> " + prop_element[1]);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesUtil.getProperty(prop_element[1]))));
                highLightElement(locator);
                element_text = driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).getText();
            } else if (!locator.startsWith("OR.")) {
                log.info("locator --> " + locator);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesUtil.getProperty(locator))));
                highLightElement(locator);
                element_text = driver.findElement(By.xpath(PropertiesUtil.getProperty(locator))).getText();
            } else {
                log.error("Incorrect locator reference!");
            }
            return element_text;
        } catch (Exception e) {
            log.error("Locator not found.");
            return "Locator not found.";
        }

    }

    
     * Method Name : compareText Parameters : Source file, Destination file Return
     * type : String Objective : To compare text of two element Author : Mayank
     * Srivastava Date : 18th Sept 2018
     

    public String compareText(String source, String target) {
        String status = "";
        try {
            status = (source.equals(target)) ? "Pass" : "Fail";
        } catch (Exception e) {
            System.out.println("Comparison mismatch.");
            log.error("Comparison mismatch.");
            status = "Fail";
        }
        return status;
    }

    public String validateElementState(String condition, String element) {
        String status = "";
        String[] prop_element = {};
        boolean st = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            if (element.startsWith("OR.")) {
                prop_element = element.split("R.");
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PropertiesUtil.getProperty(prop_element[1]))));
            }


            switch (condition) {
                case "isVisible":
                    st = driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).isDisplayed();
                    break;
                case "isInvisible":
                    st = !(driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).isDisplayed());
                    break;
                case "isEnabled":
                    st = driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).isEnabled();
                    break;
                case "isDisabled":
                    st = !(driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).isEnabled());
                    break;
                case "isSelected":
                    st = driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).isSelected();
                    break;
                case "isDeselected":
                    st = !(driver.findElement(By.xpath(PropertiesUtil.getProperty(prop_element[1]))).isSelected());
                    break;
                default:
                    System.out.println("Invalid switch criteria");
                    log.info("Invalid switch criteria");
            }

            status = (st) ? "Pass" : "Fail";
        	
        	if(!st)
        		status="Fail";
        	else
        		status="Pass";
        } catch (Exception e) {

        }


        return status;
    }

    // Search Status method
    public String externalSearchStatus(String referenceNumber, String appType, String caseTypeValue, String SearchField) {
        try {
            Set<String> S = new HashSet<String>();
            S.add("Client");
            S.add("All fields");
            String firstddvalue, secondddvalue, ddtext = "";
            WebElement searchedValue;
            Properties properties = System.getProperties();
            String fld = properties.getProperty(referenceNumber);
            String val;
            if (fld != null) {
                val = fld;
            } else {
                val = referenceNumber;
            }

            if (appType.equals("Patent") && caseTypeValue.equals("Post-PCT")) {
                ddtext = "patentPostPctSearchFields";
            } else if (appType.equals("Patent") && caseTypeValue.equals("Direct")) {
                ddtext = "patentDirectSearchFields";
            } else if (appType.equals("Patent") && caseTypeValue.equals("EP Validation")) {
                ddtext = "patentEpValidationSearchFields";
            } else if (appType.equals("Trademark") && caseTypeValue.equals("Direct")) {
                ddtext = "trademarkDirectSearchFields";
                System.out.println(ddtext);
            } else if (appType.equals("Custom") && caseTypeValue.equals("Custom")) {
                ddtext = "customSearchFields";
            }

            if (appType.contentEquals("Trademark")) {
                appType = "Trade Mark";
            }

            int rowNum = 0;
            waitAll(100);
            dynamicWaiting("15", "visible", "searchButton");
//            driver.findElement(By.xpath(PropertiesUtil.getProperty("searchButton"))).click();
            SingleClick("searchButton");
            waitAll(100);
//          added if-else block to differentiate between Instruct and Receive window
            if (driver.getCurrentUrl().contains("instruct")){
                driver.findElement(By.xpath(PropertiesUtil.getProperty("instruct_inp_SearchInstruction"))).click();
            } else if(driver.getCurrentUrl().contains("receive")){
                driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).click();
            }
//            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).click();
            waitAll(100);
            firstddvalue = "(//lh/b[contains(text(),'" + appType + "')]//following::a[text()='" + caseTypeValue
                    + "'])[1]";
            secondddvalue = "//*[contains(@ng-repeat,'" + ddtext + "') and a[contains(text(),'" + SearchField + "')]]";
            System.out.println("ddtext value is " + ddtext);
            System.out.println("First DD XPAth" + firstddvalue);
            System.out.println("Second DD XPAth" + secondddvalue);
            driver.findElement(By.xpath("(//div[@class='input-group-btn dropdown'])[1]")).click();
            waitAll(100);
            driver.findElement(By.xpath(firstddvalue)).click();
            waitAll(100);
            driver.findElement(By.xpath("(//div[@class='input-group-btn dropdown'])[2]")).click();
            waitAll(100);
            driver.findElement(By.xpath(secondddvalue)).click();
            waitAll(100);
//          added if-else block to differentiate between Instruct and Receive window
            if (driver.getCurrentUrl().contains("instruct")){
                driver.findElement(By.xpath(PropertiesUtil.getProperty("instruct_inp_SearchInstruction"))).clear();
                waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("instruct_inp_SearchInstruction"))).sendKeys(val);
            } else if(driver.getCurrentUrl().contains("receive")){
                driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).clear();
                waitAll(100);
                driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).sendKeys(val);
            }
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).clear();
            waitAll(100);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("inp_SearchInstruction"))).sendKeys(val);
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btn_search"))).click();
            waitAll(100);
        } catch (Exception e) {
            System.out.println(e);
            return "Fail";
        }
        return "Pass";

    }

    public List<WebElement> searchWithinElement(String parent_element, String search_for) {
        WebElement element = null;
        String status = "";

        if (parent_element.startsWith("OR.")) {
            String[] parentElement = parent_element.split("R.");
            //System.out.println(parentElement[1]);
            element = driver.findElement(By.xpath(parentElement[1]));
        }

        List<WebElement> child_elements = element.findElements(By.xpath(search_for));

        return child_elements;
    }

    public String VerifyData2(String Elementpath, String Name, String driverFlag) {
        String chk = "False";
        try {
            tempDriver = driver;
            WebDriverWait waiter = new WebDriverWait(driver, 5);
            List<WebElement> elements = waiter.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(PropertiesUtil.getProperty(Elementpath))));

            Properties properties = System.getProperties();
            String fld = properties.getProperty(Name);
            String val;
            if (fld != null) {
                val = fld;
            } else {
                val = Name.toString();
            }
            Collections.reverse(elements);
            for (WebElement element : elements) {
                System.out.println("The ui value of the element is  : " + element.getAttribute("value"));
                System.out.println("The ui text of the element is  : " + element.getText());
                System.out.println("The comparing text value is  : " + val);
                if (val.isEmpty()) {

                    if (element.getAttribute("value") != null
                            && element.getText() != null) {
                        if (element.getAttribute("value").isEmpty() && element.getText().isEmpty()) {
                            System.out.println("Match found between element value and comparing value as both are blank!");
                            driver = (EventFiringWebDriver) tempDriver;
                            // TestRunner.test.log(Status.PASS, "Match found between element value and
                            // comparing value as both are blank");
                            chk = "Pass";
                            break;
                        }
                    }

                    if (element.getText() != null) {
                        if (!element.getText().isEmpty()) {
                            System.out.println("NO Match found between element Text and comparing value !");
                            driver = (EventFiringWebDriver) tempDriver;
                            // TestRunner.test.log(Status.FAIL, "NO Match found between element Text and
                            // comparing value");
                            chk = "Fail";
                        }
                    }

                    if (element.getAttribute("value") != null) {
                        if (!element.getAttribute("value").isEmpty()) {
                            System.out.println("NO Match found between element value and comparing value !");
                            driver = (EventFiringWebDriver) tempDriver;
                            // TestRunner.test.log(Status.FAIL, "NO Match found between element value and
                            // comparing value");
                            chk = "Fail";
                        }
                    }

                }

                // condition for not null value or text
                if (element.getAttribute("value") != null) {
                    if (element.getAttribute("value").contains(val)) {
                        System.out.println("Match found between element value and comparing value !");
                        driver = (EventFiringWebDriver) tempDriver;
                        // TestRunner.test.log(Status.PASS, "Match found between element value and
                        // comparing value");
                        chk = "Pass";
                        break;
                    } else if (val.equalsIgnoreCase("Results for")) {
                        System.out.println(
                                "Results for " + element.getAttribute("value") + " is present on ui");
                        driver = (EventFiringWebDriver) tempDriver;
                        // TestRunner.test.log(Status.PASS, "Results for
                        // "+"<b><I>"+driver.findElement(By.xpath(PropertiesUtil.getProperty(Elementpath))).getAttribute("value").toString()+"</b></I>"+"
                        // is present on ui");
                        chk = "Pass";
                        break;
                    }
                }

                if (element.getText() != null) {
                    if (element.getText().contains(val)) {
                        System.out.println("Match found between element text and comparing value !");
                        driver = (EventFiringWebDriver) tempDriver;
                        // TestRunner.test.log(Status.PASS, "Match found between element text and
                        // comparing value");
                        chk = "Pass";
                        break;
                    }
                }
            }


            // condition for empty value in the argument


            driver = (EventFiringWebDriver) tempDriver;
        } catch (Exception e) {
            e.printStackTrace();
            driver = (EventFiringWebDriver) tempDriver;
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }

        return chk;
    }

    *//**
     * To highlighting the web element on which you are performing any action or verifying its existence
     * used in verifyExistence, enterText, singleClick
     * @param BGcolor
     * @param BRcolor
     * @param element
     *//*
    public void highLightElement(String BGcolor, String BRcolor, String element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element1 = driver.findElement(By.xpath(PropertiesUtil.getProperty(element)));
        js.executeScript("arguments[0].setAttribute('style', 'background: " + BGcolor + "; border: 2px solid " + BRcolor + ";');", element1);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        js.executeScript("arguments[0].setAttribute('style','');", element1);
    }

    *//**
     * To highlighting the web element on which you are performing any action or verifying its existence
     * used in getValue, getText
     * @param element
     *//*
    public void highLightElement(String element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element1 = driver.findElement(By.xpath(element));
        js.executeScript("arguments[0].setAttribute('style', 'background: hotpink; border: 2px solid blue;');", element1);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        js.executeScript("arguments[0].setAttribute('style','');", element1);
    }
    
    @AfterClass
    public void tearDown() {
        log.info("-------------------------------T E S T    E X E C U T I O N   C O M P L E T E D-------------------------------");
//        recorder.stop();
        try {
//            Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\driverKiller.bat");
//            String[] command = {"cmd.exe", "/C", "Start", System.getProperty("user.dir") + "\\driverKiller.bat"};
//            Runtime.getRuntime().exec(command);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    
    
    
    // ....................................................................END................................................................................
    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\

    *//**
     * Method Name: SetDropdown Parameters: DropdownName, Value Return Type: String
     * Objective: Select given 'Value' in 'Drop down'
     **//*

    public String GetSelectedDropDownValue(String DropdownName, String CompareValue) {
        try {

            Select select = new Select(driver.findElement(By.xpath(PropertiesUtil.getProperty(DropdownName))));

            WebElement option = select.getFirstSelectedOption();
           String selectedValue = option.getText();

           if(CompareValue.equalsIgnoreCase(selectedValue)) {
        	   return "Pass";
           }
           else {
        	   return "Fail";
		}
    
        } catch (Exception e) {
            e.printStackTrace();
            // TestRunner.test.log(Status.FAIL,"<b><I> Exception thrown </b></I>");
            return "Fail";
        }
    }

    // *\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\*\\*//*\\*//*\\
    // ....................................................................END................................................................................
    
    
    
    
} // Parenthesis to end class 'Functions'
*/