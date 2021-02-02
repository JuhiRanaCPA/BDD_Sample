
package cpa.qa.library;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import cpa.qa.util.PropertiesUtil;
import gherkin.lexer.El;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class Common extends Functions {


    public String browser = System.getProperty("browser");
    public String IPPU = System.getProperty("IPPU");
    public String STM = System.getProperty("STM");
    public String STL = System.getProperty("STL");
    public String STA = System.getProperty("STA");
    public String IPPU_Pwd = System.getProperty("IPPU_Pwd");
    public String STM_Pwd = System.getProperty("STM_Pwd");
    public String STL_Pwd = System.getProperty("STL_Pwd");
    public String STA_Pwd = System.getProperty("STA_Pwd");
    public String Pre_ProdUID =System.getProperty("Pre_ProdUID");
    public String appURL = System.getProperty("appURL");
    public String CaseMatterNo = System.getProperty("CaseMatterNo");
    public String CurrentCaseMatterNo;


    WebDriverWait wt;

    /*@Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser_type){
        if(browser == null){
            switch (browser_type){
                case "Chrome" :
                    browser = "Chrome";
                    break;
                case "Mozilla" :
                    browser = "Mozilla";
                    break;
                case "IE" :
                    browser = "IE";
                    break;
                default :
                    System.out.println("Please specify browser type");
                    break;
            }
        }
    }*/

    public Common() {
        if (browser == null) {

            // Browser and URL
            browser = "Chrome";


            //Pre-prod URL
            //appURL = "https://preprod.ipplatform.com/search/";
            //STA_Pwd="Real.francium.wheedle.kneel.picky.85";


            //Dev URL
            //appURL= "https://az01-sd-search-web-dev.azurewebsites.net/#/projects";

            //Pre-Prod URL
            //appURL ="https://preprod.ipplatform.com/search/";

            // Staging Test Data
            appURL = "https://staging.ipplatform.com/search/";
            IPPU = "walter.white@nomail.example.com";
            STM = "perry.coleman@nomail.example.com";
            STL = "skyler.white@nomail.example.com";
            STA = "jesse.pinkman@nomail.example.com";

            if(appURL.contains("preprod")){
                STL="jesse.pinkman@nomail.example.com";
                IPPU_Pwd = "Soccer.token.topsail.ashore.ticking.33";
                STM_Pwd = "Summons.dogtrot.kindly.aries.becoming.2";
                STL_Pwd = "Real.francium.wheedle.kneel.picky.85";
                STA_Pwd = "Real.francium.wheedle.kneel.picky.85";
            }
            else {
                IPPU_Pwd = "Commodore64@";
                STM_Pwd = "Commodore64@";
                STL_Pwd = "Commodore64@";
                STA_Pwd = "Commodore64@";
            }



            CaseMatterNo = new SimpleDateFormat("yyyyMMddHHssmm").format(new Date());


        }
    }

    // public String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new
    // Date());
    // public String date=new SimpleDateFormat("MM/dd/yy").format(new Date());
    // public String
    // intapplicationNumber="PCT/YY"+timeStamp.substring(0,4)+"/"+timeStamp.substring(timeStamp.length()-6);
    // public String
    // intpublicationNumber="WO/"+timeStamp.substring(0,4)+"/"+timeStamp.substring(timeStamp.length()-6);


    public void testDetail() {
        log.info("<==================" + Thread.currentThread().getStackTrace()[1].getMethodName() + "=======================>");
    }

    public void LaunchBrowser(String Browser, String URL) throws Exception {
        St.add(openBrowser(Browser));
        St.add(Launch(URL));
        Assert("Fail");
    }

    public void Type_Value(String Element, String Value) throws Exception {
        St.add(EnterText(Element, Value));
        Assert("Fail");
    }


    public void Open_Project() {
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        waitForElement("First_Project", "xpath");
        St.add(SingleClick("First_Project"));
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        Assert("Fail");
    }

    public void Click_Project_Info_Edit() {
        waitForElement("Project_Info_Edit", "xpath");
        St.add(SingleClick("Project_Info_Edit"));
        Assert("Fail");
    }


    public void Verify_Project_Info_Editable_Fields(String fieldname) {

        if (fieldname.contains("CPA_Ref_Number")) {
            St.add(VerifyData("Ref_Id", "", ""));
            St.add(EnterText("Ref_Id", "Ref_Id_" + CurrentCaseMatterNo));
        } else if (fieldname.contains("Project_Owner")) {

        } else if (fieldname.contains("Project_Title")) {

        } else if (fieldname.contains("Company_Name")) {

        }

        Assert("Fail");
    }


    public void Verify_Project_Heading(String ProjectName) {
        St.add(VerifyData("Detailed_Project_Heading", ProjectName, ""));
        Assert("Fail");
    }

    public void Request_Quote_Type_OF_Project_Mandatory(String arg1, String arg2, String MandatoryFieldDetails) throws Exception {

        //St.add(SingleClick("Request_Quote"));
        if (MandatoryFieldDetails.contains("project_type")) {
            St.add(SetDropdown("technicalField", arg1));
            St.add(EnterText("caseOrMatterNumber", arg2));
        } else if (MandatoryFieldDetails.contains("Technical_Field")) {
            St.add(SetDropdown("project_type", arg1));
            St.add(EnterText("caseOrMatterNumber", arg2));
        } else if (MandatoryFieldDetails.contains("Case_Matter_Field")) {
            St.add(SetDropdown("project_type", arg1));
            St.add(SetDropdown("technicalField", arg2));
        }

        St.add(SingleClick("next_projectCreate"));
        Assert("Fail");
    }


    public void Request_Quote_Close_Button(String Patentability, String Biotechnology, String CaseMatterNo) throws Exception {
        St.add(SingleClick("Close"));
        St.add(VerifyExistence("Project_Heading"));
        St.add(SingleClick("Request_Quote"));
        St.add(GetSelectedDropDownValue("project_type", Patentability));
        St.add(GetSelectedDropDownValue("technicalField", Biotechnology));
        St.add(VerifyData("caseOrMatterNumber", CaseMatterNo, ""));
        St.add(SingleClick("next_projectCreate"));
        St.add(SingleClick("Close"));
        St.add(VerifyExistence("Project_Heading"));
        St.add(SingleClick("Request_Quote"));
        St.add(GetSelectedDropDownValue("project_type", Patentability));
        St.add(GetSelectedDropDownValue("technicalField", Biotechnology));
        St.add(VerifyData("caseOrMatterNumber", CaseMatterNo, ""));
        Assert("Fail");
    }


    public void Verify_Browser_Closed() throws Exception {
        St.add(Verify_BrowserInstance_Closed());
        Assert("Fail");
    }


    // **********************Added by
    // Himanshi****************************************************
    public String get_Prior_Date(int month, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -month);
        return new SimpleDateFormat(dateFormat).format(cal.getTime());
    }
    // ************************************************************************************

    // **********************Added by
    // Himanshi****************************************************
    public void Edit_Comment_and_Extra_Comment() {
        // Clicking on the Options drop-down menu button
        St.add(SingleClick("btn_Options"));
        St.add(SingleClick("dd_SelectAll"));
        St.add(SingleClick("btn_Options"));

        // Editing the Bulk comment in the Option drop-down menu with "Edit comments"
        // option
        St.add(SingleClick("dd_EditComments"));
        St.add(EnterText("txt_EditComment", "Bulk Comment"));
        waitUntilAngularReady();
        St.add(SingleClick("btn_save"));
        St.add(SingleClick("lnk_EditComment"));
        St.add(VerifyData("txt_EditComment", "Bulk Comment", ""));
        St.add(SingleClick("btn_save"));
        St.add(SingleClick("lnk_EditComment_2"));
        St.add(VerifyData("txt_EditComment", "Bulk Comment", ""));
        St.add(SingleClick("btn_save"));
        St.add(SingleClick("btn_Options"));

        // Editing the Extra Bulk comment in the Option drop-down menu with "Edit extra
        // comments" option
        St.add(SingleClick("dd_EditExtraComments"));
        St.add(EnterText("txt_EditExtraComment", "Extra Bulk Comment"));
        St.add(SingleClick("btn_save"));
        St.add(SingleClick("lnk_EditComment"));
        St.add(VerifyData("txt_EditExtraComment", "Extra Bulk Comment", ""));
        St.add(SingleClick("btn_save"));
        St.add(SingleClick("lnk_EditComment_2"));
        St.add(VerifyData("txt_EditExtraComment", "Extra Bulk Comment", ""));
        St.add(SingleClick("btn_save"));
        St.add(SingleClick("btn_Close"));
        St.add(SingleClick("actionBtnYes"));
        Assert("Fail");
    }
    // **********************Added by
    // Himanshi****************************************************


    /*
     * Author : Mayank Srivastava Date : 11th Sept 2018
     *
     */
    public void validateElement(String FieldName, String TagName, String ElementPosition) {
        St.add(verifyElementExistenece(FieldName, TagName, ElementPosition));
        Assert("Fail");
    }

    /*
     * Author : Mayank Srivastava Date : 11th Sept 2018
     *
     */
    public void verifyValidationMessage(String FieldName, String TagName, String actionType, String InputText, String ErrorMessage, String ng_if) {
        St.add(verifyElementExistenece(FieldName, TagName, "1"));
        St.add(performAction(actionType, TagName, FieldName, "1", InputText));
        driver.findElement(By.xpath("//translate[text()='Main information']")).click();
        St.add(validateErrorMessage(ng_if, ErrorMessage));

        Assert("Fail");
    }


    public void Log_out() throws InterruptedException {
        St.add(Logout());
        St.add(CloseBrowserInstance());
        driver.quit();
        Assert("Fail");
    }

    public void Assert(String Status1) {
        try {
            log.info("<===========================================================================>");
            log.info("Asert Check: Value of Common Status list is " + St);
            System.out.print("St is: " + St);
            assertTrue(!St.contains(Status1));
        } catch (Exception e) {
        } finally {
            St.clear();
            log.info("Common Status list after clearing " + St);
            log.info("<===========================================================================>");
        }
    }

    public void validateUploadFileCompare(String uploaded_file_name, String compare_file_locator) {
        String compare_file_name = driver.findElement(By.xpath(compare_file_locator)).getText();
        St.add(compareText(uploaded_file_name, compare_file_name));
        Assert("Fail");
    }

    public void verifyAdvancedSearchWindow() {
        St.add(performAction("click", "title", "Search instructions", "", ""));
        //String advanced_search_header_text = getText("OR.hrdAdvancedSearchScreen");
        St.add(compareText(getText(PropertiesUtil.getProperty("hrdAdvancedSearchScreen")), "SEARCH INSTRUCTIONS"));
        Assert("Fail");
    }

    public void advanceSearchBy(String filter_by, String search_text, String verify_over) {
        waitUntilAngularReady();
        St.add(performAction("type", "", filter_by, "", search_text));
        St.add(verifyElementExistenece(verify_over, "", ""));
        St.add(performAction("type", "", filter_by, "", ""));
        // St.add(performAction("click","","//div[contains(text(),'Search
        // instructions')]","",""));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert("Fail");
    }


    public String launchApplicationinBrowser(String appURL) {
        try {
            log.info("Launching browser!!!");
            St.add(openBrowser(browser));
            log.info(browser + " browser launched!");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			/*if (driver.findElements(By.xpath("//button[text()='Retry']")).size() != 0) {
				while (driver.findElements(By.xpath("//button[text()='Retry']")).size() != 0) {
					Thread.sleep(40000);
					driver.findElement(By.xpath("//button[text()='Retry']")).click();
				}
			} else {
				St.add(Launch(appURL));
			}*/
            St.add(Launch(appURL));
            log.info("Navigated to url : " + appURL);
            Assert("Fail");
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    public String loginToFoundationIP(String username, String password) {
        try {
            driver.findElement(By.xpath("//a[contains(text(),'Login with your IP Platform account')]")).click();
            driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).clear();
            driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).sendKeys("");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).clear();
            driver.findElement(By.xpath(PropertiesUtil.getProperty("LoginId"))).sendKeys(username);
            log.info("Entered the username!");
            driver.findElement(By.xpath(PropertiesUtil.getProperty("btnSubmit"))).click();
            if (driver.findElements(By.xpath(PropertiesUtil.getProperty("password"))).size() > 0) {
                driver.findElement(By.xpath(PropertiesUtil.getProperty("password"))).clear();
                driver.findElement(By.xpath(PropertiesUtil.getProperty("password"))).sendKeys(password);
                log.info("Entered the password!");
                driver.findElement(By.xpath(PropertiesUtil.getProperty("btnSubmit"))).click();
            }
            Assert.assertEquals(driver.getTitle(), "FoundationIP");
            return "Pass";
        } catch (Exception e) {
            return "Fail";
        }
    }


    public String launchFILEApp() {
        try {
            WebElement btnLaunchFile = driver.findElement(By.id("btn-file-goto"));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(btnLaunchFile));
            btnLaunchFile.click();
            List<String> win_ids = new ArrayList<>(driver.getWindowHandles());
            log.info(win_ids);
            driver.switchTo().window(win_ids.get(1));
            log.info("Switching to Window Id : " + win_ids.get(1));

            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }



    /*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


//	public String SetDate(String DateValue) {
//
//		Date date = Calendar.getInstance().getTime();
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//		String strDate = dateFormat.format(date);
//
//		String Date_Type, Date_Days = "";
//
//		if(DateValue.contains("Future")) {
//
//			date.add(Calendar.date, 15);
//
//			Date_Type = DateValue.split("_")[0];
//			Date_Days = DateValue.split("_")[1];
//		}
//		else {
//			Date_Type ="Current";
//		}
//
//
//		System.out.println("Current Date is "+strDate);
//
//		return strDate;
//	}


} // Parenthesis to end class 'Functions'
