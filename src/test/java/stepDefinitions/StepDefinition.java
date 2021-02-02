package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;

import java.io.FileReader;

import com.opencsv.CSVReader;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gherkin.lexer.En;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import cpa.qa.library.*;
import cpa.qa.util.PropertiesUtil;
import org.openqa.selenium.interactions.Actions;

import javax.lang.model.util.ElementScanner6;
//import org.openqa.selenium.remote.server.handler.SendKeys;


@RunWith(Cucumber.class)
public class StepDefinition extends Common {

    String timeStamp = new SimpleDateFormat("yyyyMMddHHssmm").format(new Date());

    @Given("^Open \"([^\"]*)\" and Launch application URL$")
    public void open_and_Launch_application(String BrowserType) throws Exception {
        System.out.println(" ************* User is on landing page *****************");
        LaunchBrowser(BrowserType, appURL);
        Assert("Fail");
    }

    @Given("^Launch application URL$")
    public void Launch_application_Only() {
        System.out.println(" ************* User is on landing page *****************");
        St.add(Launch(appURL));
        Assert("Fail");
    }


    @When("^Login with Credentials \"([^\"]*)\"$")
    public void Login_Search_App(String UserType) {
        System.out.println("********** User login into application with username and password ********************");
        St.add(VerifyExistence("cpalogo"));
        if (!driver.findElements(By.id("btnLoginOtherUser")).isEmpty()) {
            St.add(SingleClick("login_Other_User"));
        }
        if (UserType.equals("IPPU")) {
            St.add(Login(IPPU, IPPU_Pwd));
        } else if (UserType.equals("STM")) {
            St.add(Login(STM, STM_Pwd));
        } else if (UserType.equals("STL")) {
            St.add(Login(STL, STL_Pwd));
        } else if (UserType.equals("STA")) {
            St.add(Login(STA, STA_Pwd));
        } else if (UserType.equals("Pre_ProdUID")) {
            St.add(Login(STA, STA_Pwd));
        }
        driver.get(appURL);
        St.add(VerifyExistence("Project_Heading"));
        Assert("Fail");
    }


    @When("^Login again with Credentials \"([^\"]*)\"$")
    public void Login_Search_App_Again(String UserType) {
        St.add(Launch(appURL));
        Login_Search_App(UserType);
        Assert("Fail");
    }


    @Then("^Home page is populated$")
    public void home_page_is_populated() {
        System.out.println("*********** Home page is populated  *******************");
        waitForElement("Project_Heading", "xpath");
        St.add(VerifyExistence("Project_Heading"));
        Assert("Fail");
    }


    @When("^Heading of page will be \"([^\"]*)\"$")
    public void heading_of_page_will_be(String Name) {
        St.add(VerifyData("Heading", Name, ""));
        Assert("Fail");
    }

    @Then("^\"([^\"]*)\" are available on page (\\d+)$")
    public void Verify_Fields_Request_Quote(String FieldsData, int PageNumber) {
        if (PageNumber == 1) {
            St.add(VerifyData("Headers", "Required information", ""));
            St.add(VerifyData("Paragraph", "Please complete the following fields and click Next to proceed.", ""));
            St.add(VerifyExistence("project_type_Heading"));
            St.add(VerifyExistence("project_type"));
            St.add(VerifyExistence("technicalField_Heading"));
            St.add(VerifyExistence("technicalField"));
            St.add(VerifyExistence("caseOrMatterNumber_Heading"));
            St.add(VerifyExistence("caseOrMatterNumber"));
            St.add(VerifyExistence("Client_Name_Heading"));
            St.add(VerifyExistence("Client_Name"));
            //St.add(VerifyExistence("Project_Title_Heading"));
            //St.add(VerifyExistence("Project_Title"));
            St.add(VerifyExistence("Discard_Draft"));
            St.add(VerifyExistence("Close"));
            St.add(VerifyExistence("Page1_Previous_Button"));
            St.add(VerifyExistence("Page1_Next_Button"));
        } else {
            St.add(VerifyData("Headers", "Optional information", ""));
            St.add(VerifyData("Paragraph", "The following fields will help us to provide an accurate and complete estimate and search. Please fill in as much as you can and click Send to proceed.", ""));
            St.add(VerifyExistence("Desc_Concept_Of_Search"));
            St.add(VerifyExistence("Concept_Of_Search_TextArea"));
            St.add(VerifyExistence("Desc_List_KnowPrior_Art"));
            St.add(VerifyExistence("List_KnowPrior_Art_TextArea"));
            St.add(VerifyExistence("Desc_Budget"));
            St.add(VerifyExistence("Budget_TextBox"));
            St.add(VerifyExistence("Currency_DropDown"));
            St.add(VerifyExistence("Desc_Other_Info"));
            St.add(VerifyExistence("Other_OInfo_TextArea"));
            St.add(VerifyExistence("Desc_Doc"));
            St.add(VerifyExistence("Upload_Files_Drop_Area"));
            St.add(VerifyData("Alert_Msg_Area", "CPA Global is not responsible for, and excludes any liability for, any data received via email. If you desire to email sensitive or export controlled technical data, please send it to exportcontrol@cpaglobal.com.", ""));
            St.add(VerifyExistence("Discard_Button"));
            St.add(VerifyExistence("Request_Quote_CloseButton_P2"));
            St.add(VerifyExistence("Request_Quote_PreviousButton_P2"));
            St.add(VerifyExistence("Send_Button"));
        }
        St.add(SingleClick("Discard_Button"));
        Assert("Fail");

    }


    @Given("^User filled all mandatory fields on page \"([^\"]*)\" with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void Request_Quote_Set_Value(String arg1, String Type_Of_Project, String Technical_Field, String CaseMatterNo) {
        St.add(SingleClick("REQUEST_QUOTE_From_Left_Navigation"));
        if (Type_Of_Project.equals("")) {
            St.add(SetDropdown("technicalField", Technical_Field));
            St.add(EnterText("caseOrMatterNumber", CaseMatterNo));
        } else if (Technical_Field.equals("")) {
            St.add(SetDropdown("project_type", Type_Of_Project));
            St.add(EnterText("caseOrMatterNumber", CaseMatterNo));
        } else if (CaseMatterNo.equals("")) {
            St.add(SetDropdown("project_type", Type_Of_Project));
            St.add(SetDropdown("technicalField", Technical_Field));
        } else {
            St.add(SetDropdown("project_type", Type_Of_Project));
            St.add(SetDropdown("technicalField", Technical_Field));
            St.add(EnterText("caseOrMatterNumber", CaseMatterNo));
        }
        if (arg1.equalsIgnoreCase("both")) {
            St.add(SingleClick("next_projectCreate"));
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\GIT\\ip-search\\Sample_File_5.pdf");
            System.out.println("Uploaded");
            for (int second = 0; second <= objectTimeOut; second++) {
                if (driver.findElement(By.xpath(".//*[@title='Send draft'] | .//button[contains(text(),'Send')]")).isEnabled()) {
                    break;
                }
            }
            Wait("15000");
//			St.add(EnterText("Upload_File","C:\\GIT\\ip-search\\target\\Sample_File_5.pdf"));
            St.add(SingleClick("Send_Button_Request_Quote"));
            waitForElement("h3", "xpath");
            St.add(VerifyData("h3", "Request received", ""));
            St.add(SingleClick("Return_To_Overview_Link"));
        }
        Assert("Fail");
    }


    @When("^Request_Quote quote$")
    public void Request_Quote() {
        St.add(SingleClick("next_projectCreate"));
        St.add(SingleClick("Send_Button_Request_Quote"));
        St.add(VerifyExistence("request_Received"));
        St.add(SingleClick("Return_To_Overview_Link"));
        Assert("Fail");
    }
	
	
/*	@When("^Request_Quote quote with \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\" is mandatory$")
	public void request_quote_mandatory(String arg1, String arg2, String MandatoryFieldDetails){
		if(MandatoryFieldDetails.contains("project_type")) {
			arg1=Technical_Field; arg2=CaseMatterNo;
		}
		else if (MandatoryFieldDetails.contains("Technical_Field")) {
			arg1=Type_Of_Project; arg2=CaseMatterNo;	
		}
		else {
			arg1=Type_Of_Project; arg2=Technical_Field;
		}
	    Request_Quote_Type_OF_Project_Mandatory(arg1, arg2, MandatoryFieldDetails);
	}*/

    @Then("^Validate Error message for \"([^\"]*)\"$")
    public void validate_Error_message(String Message) {
        St.add(VerifyData("Mandatory_Message", Message, ""));
        St.add(SingleClick("Discard_Button"));
        Assert("Fail");
    }

    @Then("^Project is \"([^\"]*)\" created with name \"([^\"]*)\"$")
    public void Verify_Project_is_created(String Flag, String ProjectName) {
        waitTillSpinnerIsAvailable("Middle_Spinner");
        String status = "Fail";
        St.add(VerifyExistence("Project_Heading"));
        if (driver.findElements(By.xpath("//a[contains(text(),'" + ProjectName + "')]")).size() >= 1) {
            if (Flag.contains("successfully")) {
                status = "Pass";
            }
        } else {
            if (Flag.contains("not")) {
                status = "Pass";
            }
        }
        St.add(status);
        Assert("Fail");
    }


    @Then("^Project is \"([^\"]*)\" Available with name \"([^\"]*)\"$")
    public void Verify_Project_is_Available(String Flag, String ProjectName) {
        String status = "Fail";
        St.add(VerifyExistence("Project_Heading"));
        //	St.add(SingleClick("REQUEST_QUOTE_From_Left_Navigation"));

        if (driver.findElements(By.xpath("//a[contains(text(),'" + ProjectName + "')]")).isEmpty()) {
            if (Flag.contains("not")) {
                status = "Pass";
            }
        } else {
            if (Flag.contains("succussfully")) {
                status = "Pass";
            }
        }
        St.add(status);

        Assert("Fail");
    }


    @Then("^Notification message for \"([^\"]*)\" will be available on notification screen$")
    public void Validate_Notification_Icon_Project_List_Page(String ProjectName) {
        St.add(SingleClick("Notification_Icon"));
        St.add(SingleClick("View_All_Notification"));
        System.out.println(VerifyData("First_Message_In_Notification", "Your project with reference '" + ProjectName + "' has been created.", ""));
        St.add(VerifyData("First_Message_In_Notification", "Your project with reference '" + ProjectName + "' has been created.", ""));
        Assert("Fail");
    }


    @And("^Request Quote will have Type_Of_Project= \"([^\"]*)\", Technical Field = \"([^\"]*)\",and Case/Matter = \"([^\"]*)\"$")
    public void Verify_Field_Values_RequestQuote_Page(String typeofproject, String technicalfield, String casematterno) {
        //Steps to verify that entered value should not be available on Request Quote Page
        St.add(SingleClick("REQUEST_QUOTE_From_Left_Navigation"));
        St.add(GetSelectedDropDownValue("project_type", typeofproject));
        St.add(GetSelectedDropDownValue("technicalField", technicalfield));
        St.add(VerifyData("caseOrMatterNumber", casematterno, ""));
        St.add(SingleClick("Discard_Button"));
        Assert("Fail");
    }


    @When("^User clicked on logout$")
    public void user_clicked_on_logout() {
        driver.findElement(By.cssSelector(".fa-sign-out")).click();
        driver.findElement(By.xpath("//*[@title='Confirm logout']")).click();
        for (int second = 0; second <= objectTimeOut; second++) {
            if (driver.findElements(By.xpath("//h3[contains(text(),'Sign Off Successful')]")).size() == 1) {
                break;
            } else {
                Wait("4000");
            }
        }
        driver.navigate().back();
        Assert("Fail");
    }

    //    @When("^User clicked on logout$")
//    public void user_clicked_on_logout() {
//        driver.findElement(By.cssSelector(".fa-sign-out")).click();
//        WebElement ele = driver.findElement(By.xpath(".e2e-confirm-signout"));
//        highLighterMethod(driver,ele);
//        ele.click();
//        //       driver.findElement(By.cssSelector(".e2e-confirm-signout")).click();
//        St.add(Launch(appURL));
//        Assert("Fail");
//
//    }
    //Creating a custom function
    public void highLighterMethod(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    @Then("^Successfully logged out$")
    public void Successfully_logged_out() throws InterruptedException {
        St.add(VerifyData("h3", "Sign Off Successful", ""));
        St.add(CloseBrowserInstance());
        Assert("Fail");
    }


    /////////////////////////////////////////////////////////////////////////////////


    @When("^User open project from Project list page$")
    public void user_open_project_from_Project_list_page() {
        Open_Project();
    }


    @Then("^Heading of project is project name$")
    public void heading_of_project_is_project_name() {
        Verify_Project_Heading(CurrentCaseMatterNo);

    }

    @Given("^Given_Clicked on Request_Quote page$")
    public void clicked_on_Requestquote_page() {
        // Click_USer_Quote();
    }


    @When("^User click on edit button of Project Infomation cloumn$")
    public void user_click_on_edit_button_of_project_infomation_cloumn() {
        Click_Project_Info_Edit();
    }

    @Then("^(.+) will become editable$")
    public void will_become_editable(String fieldname) {
        Verify_Project_Info_Editable_Fields(fieldname);
    }

    @And("^Open created Project$")
    public void open_created_project() {
        Open_Project();
    }


    @Given("^Select \"([^\"]*)\" in \"([^\"]*)\"$")
    public void select_in(String value, String ele) {
        St.add(SetDropdown(ele, value));
        Assert("Fail");
    }

    @Given("^Enter value \"([^\"]*)\" in \"([^\"]*)\" and validate various scenarios for add, cancel and update \"([^\"]*)\"$")
    public void validate_Project_Information_Fields(String value, String ele, String updatedValue) {

        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "summary";
            driver.get(projecturl);
        }
        String[] field_Ele_Array = ele.split("_Input");
        String field_Ele = field_Ele_Array[0];
        St.add(SingleClick("Project_Information_Edit_Link"));
        St.add(VerifyData(ele, "", ""));
        St.add(SingleClick("Save"));
        waitForElement(field_Ele, "xpath");
        St.add(VerifyData(field_Ele, "", ""));
        St.add(SingleClick("Project_Information_Edit_Link"));
        St.add(VerifyData(ele, "", ""));

        //Cancel data steps validation
        St.add(EnterText(ele, value));
        St.add(SingleClick("Cancel"));
        waitForElement(field_Ele, "xpath");
        St.add(VerifyData(field_Ele, "", ""));
        St.add(SingleClick("Project_Information_Edit_Link"));
        //St.add(VerifyData(ele, value, ""));

        //add data
        St.add(EnterText(ele, value));
        St.add(SingleClick("Save"));
        waitForElement(field_Ele, "xpath");
        St.add(VerifyData(field_Ele, value, ""));
        St.add(SingleClick("Project_Information_Edit_Link"));
        St.add(VerifyData(ele, value, ""));

        //Update data steps
        St.add(EnterText(ele, updatedValue));
        St.add(SingleClick("Save"));
        waitForElement(field_Ele, "xpath");
        St.add(VerifyData(field_Ele, updatedValue, ""));
        St.add(SingleClick("Project_Information_Edit_Link"));
        St.add(VerifyData(ele, updatedValue, ""));
        St.add(SingleClick("Cancel"));
        Assert("Fail");
    }

    @And("^Enter value \"([^\"]*)\" in \"([^\"]*)\"$")
    public void enterValueIn(String Value, String ele) {
        waitForElement(ele, "xpath");
        St.add(EnterText(ele, Value));
        Assert("Fail");
    }


    @And("^Enter value \"([^\"]*)\" in \"([^\"]*)\" and Select and validate various scenarios for add, cancel and update \"([^\"]*)\"$")
    public void enterValueInAndSelectAndValidateVariousScenariosForAddCancelAndUpdate(String value, String ele, String updatedValue) {
        String[] field_Ele_Array = ele.split("_Input");
        String field_Ele = field_Ele_Array[0];
        String elecss = "";

        String elevalue = driver.findElement(By.xpath(PropertiesUtil.getProperty(field_Ele))).getText();
        St.add(SingleClick("Project_Information_Edit_Link"));
        if (ele.contains("Customer_Company")) {
            elecss = ".e2e-input-companyName";
        } else if (ele.contains("Project_Owner")) {
            elecss = ".e2e-input-projectOwnerName";
        }
        driver.findElement(By.cssSelector(elecss)).clear();
//			driver.findElement(By.cssSelector(elecss)).click();
//			driver.findElement(By.cssSelector(elecss)).sendKeys("A");
//			Wait("15000");
        driver.findElement(By.cssSelector(elecss)).sendKeys(value);
        waitForElement(".e2e-typeahead-result", "cssSelector");
        driver.findElement(By.cssSelector(".e2e-typeahead-result")).click();
        St.add(SingleClick("Cancel"));
        waitForElement(field_Ele, "xpath");

        St.add(VerifyData(field_Ele, value, elevalue));
        St.add(SingleClick("Project_Information_Edit_Link"));
        driver.findElement(By.cssSelector(elecss)).clear();
//			driver.findElement(By.cssSelector(elecss)).click();
//			Wait("15000");
        driver.findElement(By.cssSelector(elecss)).sendKeys(updatedValue);
        waitForElement(".e2e-typeahead-result", "cssSelector");
        driver.findElement(By.cssSelector(".e2e-typeahead-result")).click();
        St.add(SingleClick("Save"));
        waitForElement(field_Ele, "xpath");
        St.add(VerifyData(field_Ele, updatedValue, ""));

        Assert("Fail");

    }



	/*	@Given("^\"([^\"]*)\" \"([^\"]*)\" in \"([^\"]*)\"$")
		public void Click_in(String arg1, String arg2, String arg3){
			assertTrue(!performAction(arg1,"","//a[contains(text(),'"+arg2+"')]","", "").contains("Fail"));
			Wait("2000");
		}*/


    @Given("^Click \"([^\"]*)\"$")
    public void click_Given(String arg1) {
//        waitForElement(arg1, "xpath");
        waitTillSpinnerIsAvailable("Spinner");
        St.add(SingleClick(arg1));
        waitTillSpinnerIsAvailable("Spinner");
        Assert("Fail");

    }

    @Then("^Added user \"([^\"]*)\" should be listed on share project list$")
    public void added_user_should_be_listed_on_share_project_list(String arg1) {
        assertTrue(!VerifyData("ShareUser_Email", arg1, "").contains("Fail"));
    }


    //////////////////////////////////////////////////////////GROUPS/////////////////////

    @Then("^\"([^\"]*)\" error should be populated on \"([^\"]*)\"$")
    public void error_should_be_populated(String arg1, String arg2) {
        assertTrue(!VerifyData(arg2, arg1, "").contains("Fail"));

    }

    @Given("^New Group \"([^\"]*)\" is created with name \"([^\"]*)\"$")
    public void new_group_something_is_created_with_name_something(String strArg1, String groupName) {
        waitForElement("h2", "xpath");
        String status = "Fail";
        St.add(VerifyData("h2", "Groups", ""));
        if (driver.findElements(By.xpath("//a[contains(text(),'" + groupName + "')]")).size() >= 1) {
            status = "Pass";
        }
        St.add(status);
        Assert("Fail");

    }


    @When("^Click delete icon of created group$")
    public void click_delete_icon_of_created_group() {

    }

    @Then("^New created Group is deleted sucessfully$")
    public void new_created_Group_is_deleted_sucessfully() {

    }

    @Then("^Group \"([^\"]*)\" is not created with name \"([^\"]*)\"$")
    public void group_something_is_not_created_with_name_something(String strArg1, String strArg2) {
        //assertTrue(!VerifyData(strArg1, strArg2, "").contains("Pass"));
        System.out.println(strArg1 + " is not created");
    }

	   /* @Then("^New Group is created$")
	    public void Then_new_Group_is_created(){
	        // Write code here that turns the phrase above into concrete actions
	        throw new PendingException();
	    }*/

    @Then("^Added user is not in the list$")
    public void added_user_is_not_in_the_list() {

    }

    @When("^Group is opened$")
    public void group_is_opened() {

    }

    @Then("^No user will be available in the list$")
    public void No_User_available_in_User_list() {
        assertTrue(!VerifyExistence("First_User_Group_tr").contains("Pass"));
//        SingleClick("GROUPS_From_Left_Navigation");
//        SingleClick("Delete_Group_Button");
//        SingleClick("Confirm_Delete_OK_Button");

    }

    @Then("^Validate \"([^\"]*)\" on \"([^\"]*)\"$")
    public void validate_on(String arg1, String arg2) {
        assertTrue(VerifyData(arg2, arg1, "").contains("Fail"));
    }


    @Then("^Validate \"([^\"]*)\" will be added as a new user in the group$")
    public void validate_will_be_added_as_a_new_user_in_the_group(String arg1) {

    }

    @Then("^Created Group \"([^\"]*)\" is deleted sucessfully$")
    public void created_group_something_with_name_something_is_deleted_sucessfully(String strArg1) {
        //Alert("Ok");
	  
	    	/*assertTrue(!VerifyNotExistence(strArg1).contains("Fail"));
	    	System.out.println(strArg1+" Deleted Sucessfully");
	    	*/
    }

    @Then("^User will be available in the list$")
    public void user_will_be_available_in_the_list() {
        assertTrue(!VerifyExistence("First_User_Group_tr").contains("Fail"));
        SingleClick("GROUPS_From_Left_Navigation");

    }

    @And("^Now delete created group \"([^\"]*)\"$")
    public void now_delete_created_group(String GroupName) {
        System.out.println(driver.findElements(By.xpath(".//div/a[contains(text(),'" + GroupName + "')]/following-sibling::span/i")).size());
        driver.findElement(By.xpath(".//div/a[contains(text(),'" + GroupName + "')]/following-sibling::span/i")).click();
        St.add(SingleClick("Confirm_Delete_OK_Button"));
        Assert("Fail");
    }

    @And("^Now delete updated group$")
    public void now_delete_updated_created_group() {
        waitForElement("GROUPS_From_Left_Navigation", "xpath");
        SingleClick("GROUPS_From_Left_Navigation");
        waitForElement("Delete_Updated_Group_Button", "xpath");
        SingleClick("Delete_Updated_Group_Button");
        waitForElement("Confirm_Delete_OK_Button", "xpath");
        SingleClick("Confirm_Delete_OK_Button");
    }


    @And("^Open group \"([^\"]*)\"$")
    public void open_created_group(String GroupName) {
        waitForElement(".//a[contains(text(),'" + GroupName + "')]", "customizedXpath");
        driver.findElement(By.xpath(".//a[contains(text(),'" + GroupName + "')]")).click();
        Assert("Fail");
    }


    /////////////////////
    @Given("^Create a project \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
    public void create_a_project(String Type_Of_Project, String Technical_Field, String CaseMatterNo) {
        Request_Quote_Set_Value("both", Type_Of_Project, Technical_Field, CaseMatterNo);
    }


    @And("^Import features from \"([^\"]*)\"$")
    public void importFeatures(String filepath) throws Throwable {
        CSVReader reader = null;
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        try {
            String url = driver.getCurrentUrl();
            String projectUrl = url.split("proposal")[0] + "summary";
            driver.get(projectUrl);
            waitForElement("Feature_Add_Button", "xpath");
            St.add(SingleClick("Feature_Add_Button"));
            //parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader(filepath));
            String[] nextLine;
            //reads one line at a time
            while ((nextLine = reader.readNext()) != null) {
                for (String feature : nextLine) {
                    waitForElement("Features_Import_Button", "xpath");
                    St.add(SingleClick("Features_Import_Button"));
                    St.add(EnterText("Features_Import_TextArea", feature));
                    St.add(SingleClick("Features_Upload_Button"));
                }
            }
            St.add(SingleClick("Feature_Save"));
        } catch (Exception e) {
            Assert("Fail");
        }
        Assert("Fail");
    }


    @Given("^Add features \"([^\"]*)\"$")
    public void add_features(String features) {
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "summary";
            driver.get(projecturl);
        }
        waitForElement("Feature_Add_Button", "xpath");
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        St.add(SingleClick("Feature_Add_Button"));
        if (features.contains(",")) {
            String[] featureArray = features.split(",");
            for (int i = 1; i <= featureArray.length; i++) {
                String feature = featureArray[i - 1];
                String RelatedClaim = "";
                if (feature.contains(":")) {
                    String[] featureString = feature.split(":");
                    feature = featureString[0];
                    RelatedClaim = featureString[1];
                }
                waitForElement("//input[@id='feat" + i + "']", "customizedXpath");
                driver.findElement(By.xpath("//input[@id='feat" + i + "']")).sendKeys(feature);
                driver.findElement(By.xpath("//textarea[@id='relatedClaims" + i + "']")).sendKeys(RelatedClaim);
                if (i < featureArray.length) {
                    St.add(SingleClick("Add_More_Feature"));
                }
            }
            St.add(SingleClick("Feature_Save"));
        }
        Assert("Fail");
    }

    @When("^Edit features \"([^\"]*)\"$")
    public void editFeatures(String features) {
        St.add(SingleClick("Features_Edit_Link"));
        if (features.contains(",")) {
            String[] featurearray = features.split(",");
            for (int i = 1; i <= featurearray.length; i++) {
                String feature = featurearray[i - 1];
                String RelatedClaim = "";
                if (feature.contains(":")) {
                    String[] featurestring = feature.split(":");
                    feature = featurestring[0];
                    RelatedClaim = featurestring[1];
                }
                waitForElement("//input[@id='feat" + i + "']", "customizedXpath");
                driver.findElement(By.xpath("//input[@id='feat" + i + "']")).clear();
                driver.findElement(By.xpath("//input[@id='feat" + i + "']")).sendKeys(feature);
                driver.findElement(By.xpath("//textarea[@id='relatedClaims" + i + "']")).clear();
                driver.findElement(By.xpath("//textarea[@id='relatedClaims" + i + "']")).sendKeys(RelatedClaim);
            }
            St.add(SingleClick("Feature_Save"));
        }
        Assert("Fail");
    }


    @And("^\"([^\"]*)\" reOrdered successfully$")
    public void reorderedSuccessfully(String arg0) {


    }


    @When("^Delete features \"([^\"]*)\"$")
    public void deleteFeatures(String features) {
        waitForElement("Features_Edit_Link", "xpath");
        St.add(SingleClick("Features_Edit_Link"));
        String actualFeatureValue, actualFeatureRelateClaim = "";
        if (features.contains(",")) {
            String[] featurearray = features.split(",");
            for (int i = 1; i <= featurearray.length; i++) {
                String feature = featurearray[i - 1];
                waitForElement("Features_Delete_First_Feature", "xpath");
                St.add(SingleClick("Features_Delete_First_Feature"));
            }
            St.add(SingleClick("Feature_Save"));

        }
        Assert("Fail");

    }

    @Then("^Features Deleted successfully$")
    public void featuresDeletedSuccessfully() {
        String status;
        waitForElement("Features_Message_No_Feature_Added", "xpath");
        boolean st = driver.findElement(By.xpath("//*[contains(text(),'No feature has been added')]")).isEnabled();
        if (!st) {
            status = "Fail";
        } else {
            status = "Pass";
        }
        St.add(status);
        Assert("Fail");

    }


    @Then("^Features \"([^\"]*)\" are \"([^\"]*)\" successfully$")
    public void featuresAreSuccessfully(String features, String arg1) {

        String actualFeatureValue, actualFeatureRelateClaim = "";
        if (features.contains(",")) {
            String[] featurearray = features.split(",");
            for (int i = 1; i <= featurearray.length; i++) {
                String feature = featurearray[i - 1];
                String RelatedClaim = "";

                if (feature.contains(":")) {
                    String[] featurestring = feature.split(":");
                    feature = featurestring[0];
                    RelatedClaim = featurestring[1];
                }

                if (i == featurearray.length) {
                    waitForElement("//ips-feature-list[@id='nav-feature-list']/div/div[" + i + "]/ips-feature/div[2]/div/span", "customizedXpath");
                    actualFeatureValue = driver.findElement(By.xpath("//ips-feature-list[@id='nav-feature-list']/div/div[" + i + "]/ips-feature/div[2]/div/span")).getText();
                    actualFeatureRelateClaim = driver.findElement(By.xpath("//ips-feature-list[@id='nav-feature-list']/div/div[" + i + "]/ips-feature/div[2]/div/span[2]")).getText();
                } else {
                    waitForElement("//ips-feature-list[@id='nav-feature-list']/div/div[" + i + "]/ips-feature/div[2]/div[2]/span", "customizedXpath");
                    actualFeatureValue = driver.findElement(By.xpath("//ips-feature-list[@id='nav-feature-list']/div/div[" + i + "]/ips-feature/div[2]/div[2]/span")).getText();
                    actualFeatureRelateClaim = driver.findElement(By.xpath("//ips-feature-list[@id='nav-feature-list']/div/div[" + i + "]/ips-feature/div[2]/div[2]/span[2]")).getText();
                }

                if (actualFeatureRelateClaim.equalsIgnoreCase(RelatedClaim)) {
                    St.add("Pass");
                } else {
                    St.add("Fail");
                }
                if (actualFeatureValue.equalsIgnoreCase(feature)) {
                    St.add("Pass");
                } else {
                    St.add("Fail");
                }
            }
        }
        Assert("Fail");
    }


    @Given("^Import patent \"([^\"]*)\" and verify \"([^\"]*)\"$")
    public void import_patent(String Refrences, String warningMessage) {
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("summary")) {
            projecturl = url.split("summary")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("results/add-patent")) {
            projecturl = url.split("results/add-patent")[0] + "results/list";
            driver.get(projecturl);
        }
        waitForElement("Result_DropDown", "xpath");
        St.add(SingleClick("Result_DropDown"));
        St.add(SingleClick("Import_Patent"));
        St.add(EnterText("TextBox_Refrences", Refrences));
        St.add(SingleClick("Search_refernces"));
        if (!warningMessage.isEmpty()) {
            waitForElement("Reference_Warning", "xpath");
            String screenMessage = driver.findElement(By.xpath("//ul[@class='alert alert-warning']")).getText();
            if (screenMessage.contains(warningMessage)) {
                St.add("Pass");
            } else {
                St.add("Fail");
            }
        }
        waitForElement("Select_All_References", "xpath");
        St.add(SingleClick("Select_All_References"));
        St.add(SingleClick("Button_Add_Selected_Refernces"));
        waitForElement("Refrences_Alert", "xpath");
        St.add(VerifyData("Refrences_Alert", "Added patent(s) to project", ""));
        Assert("Fail");
    }

    @When("^ReOrder references in format \"([^\"]*)\"$")
    public void reorderReferencesInFormat(String actionType) throws Throwable {
        String status = "Fail";
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("summary")) {
            projecturl = url.split("summary")[0] + "results/list";
            driver.get(projecturl);

        }
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "results/list";
            driver.get(projecturl);

        }
        if (url.contains("results/add-patent")) {
            projecturl = url.split("results/add-patent")[0] + "results/list";
            driver.get(projecturl);
        }
        waitForElement("ReOrder_References", "xpath");
        St.add(SingleClick("ReOrder_References"));
        if (actionType.equalsIgnoreCase("Down")) {
            waitForElement("ReOrder_Reference_Down_Arrow", "xpath");
            St.add(SingleClick("ReOrder_Reference_Down_Arrow"));
        }
        if (actionType.equalsIgnoreCase("Up")) {
            waitForElement("ReOrder_Reference_Up_Arrow", "xpath");
            St.add(SingleClick("ReOrder_Reference_Up_Arrow"));
        }
        St.add(SingleClick("ReOrder_Reference_Save_Button"));
        Assert("Fail");
    }


    @When("^Add patent manually with details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void addPatentManuallyWithDetails(String Title, String Author, String General_Comment, String Publication_Date, String Link, String Citations, String Source, String Abstract, String DOI, String Keywords, String Full_text) {
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";

        if (url.contains("summary")) {
            projecturl = url.split("summary")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("results/add-patent")) {
            projecturl = url.split("results/add-patent")[0] + "results/list";
            driver.get(projecturl);
        }

        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        St.add(SingleClick("Result_DropDown"));
        St.add(SingleClick("Add_NonPatent_Literature"));
        St.add(EnterText("Add_Patent_Title_TextBox", Title));
        St.add(EnterText("Add_Patent_Inventors_textBox", Author));
        St.add(SingleClick("Add_patent_Open_Calender"));
        St.add(SingleClick("Add_Patent_Calender_Today"));
        St.add(EnterText("Add_Patent_Link_TextBox", Link));
        St.add(EnterText("Add_Patent_Citation_TextBox", Citations));
        St.add(EnterText("Add_patent_Source_TextBox", Source));
        St.add(EnterText("Add_Patent_Abstract", Abstract));
        St.add(EnterText("Add_Patent_Digital_Object_Identifier_TextBox", DOI));
        St.add(SingleClick("Add_Refernces_Button"));
        waitForElement("Refrences_Alert", "xpath");
        if (driver.findElement(By.xpath("//div[@class='alert alert-dismissible e2e-ips-feedback alert-success']")).getText().equalsIgnoreCase("×\n" +
                "Added reference to project")) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        driver.get(url);
        Assert("Fail");
    }

    @When("^Upload patent with details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void uploadPatentWithDetails(String ref_Title, String publication_Number, String kind_Code, String inventor, String Abstract) {
        String status = "Fail";
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";

        if (url.contains("summary")) {
            projecturl = url.split("summary")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("results/add-patent")) {
            projecturl = url.split("results/add-patent")[0] + "results/list";
            driver.get(projecturl);
        }

        waitForElement("Result_DropDown", "xpath");
        St.add(SingleClick("Result_DropDown"));
        St.add(SingleClick("Upload_Patent"));
        St.add(EnterText("Add_Patent_Title_TextBox", ref_Title));
        St.add(EnterText("Upload_Patent_Publication_Number", publication_Number));
        St.add(EnterText("Upload_Patent_Kind_Code", kind_Code));
        St.add(EnterText("Upload_Patent_Inventor", inventor));
        St.add(SingleClick("Upload_Patent_Priority_Date_SelectDate"));
        St.add(SingleClick("Upload_Patent_Calender_Today"));
        St.add(SingleClick("Upload_Patent_Publish_Date_SelectDate"));
        St.add(SingleClick("Upload_Patent_Calender_Today"));
        St.add(SingleClick("Upload_Patent_Filing_Date_SelectDate"));
        St.add(SingleClick("Upload_Patent_Calender_Today"));
        St.add(EnterText("Upload_Patent_Abstract", Abstract));
        St.add(SingleClick("Upload_Patent_Submit_Patent_reference"));
        waitForElement("Refrences_Alert", "xpath");
        if (driver.findElement(By.xpath("//div[@class='alert alert-dismissible e2e-ips-feedback alert-success']")).getText().equalsIgnoreCase("×\n" +
                "Uploaded patent to project")) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        driver.get(url);
        Assert("Fail");
    }


    @Then("^Reference \"([^\"]*)\" should be successfully added in the project$")
    public void reference_should_be_successfully_added_in_the_project(String Refrences) {
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("summary")) {
            projecturl = url.split("summary")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("results/add-patent")) {
            projecturl = url.split("results/add-patent")[0] + "results/list";
            driver.get(projecturl);
        }


        if (Refrences.contains(" ")) {
            String[] referenceArray = Refrences.split(" ");
            for (int i = 0; i < referenceArray.length; i++) {
                if (driver.findElements(By.xpath("//*[contains(text(),'" + referenceArray[i] + "')]")).size() != 0) {
                    St.add("Pass");
                } else {
                    St.add("Fail");
                }
            }
        } else {
            if (driver.findElements(By.xpath("//*[contains(text(),'" + Refrences + "')]")).size() != 0) {
                St.add("Pass");
            } else {
                St.add("Fail");
            }

        }
        Assert("Fail");
    }

    @When("^Add Keywords \"([^\"]*)\" in a reference \"([^\"]*)\"$")
    public void addKeywordsInAReference(String keyword, String reference) {
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("summary")) {
            projecturl = url.split("summary")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("results/add-patent")) {
            projecturl = url.split("results/add-patent")[0] + "results/list";
            driver.get(projecturl);
        }
        St.add(SingleClick("References_Add_Keyword"));
        waitForElement("References_Add_Keyword_Input", "xpath");
        St.add(EnterText("References_Add_Keyword_Input", keyword));
        Wait("3000");
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();
        Assert("Fail");
    }

    @Then("^\"([^\"]*)\" successfully added$")
    public void successfullyAdded(String keyword) {
        String actualkeyword = driver.findElement(By.cssSelector(".label-text")).getText();
        if (actualkeyword.equalsIgnoreCase(keyword)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }

    @Then("^Add Category should not be available for \"([^\"]*)\"$")
    public void addCategoryShouldNotBeAvailableFor(String arg0) {
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";

        if (url.contains("summary")) {
            projecturl = url.split("summary")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("results/add-patent")) {
            projecturl = url.split("results/add-patent")[0] + "results/list";
            driver.get(projecturl);
        }
        if (driver.findElements(By.xpath("(//div[@class='add-category pointer']/i)[1]")).size() == 0) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }


    @When("^Share reference with \"([^\"]*)\"$")
    public void shareReferenceWith(String userId) {
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("summary")) {
            projecturl = url.split("summary")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("results/add-patent")) {
            projecturl = url.split("results/add-patent")[0] + "results/list";
            driver.get(projecturl);
        }
        St.add(SingleClick("Reference_Share_Link"));
        waitForElement("//textarea", "customizedXpath");
        driver.findElement(By.xpath("//textarea")).click();
        driver.findElement(By.cssSelector(".ql-editor > div")).click();
        String errorMessage = driver.findElement(By.xpath("//*[@class='form-control-feedback']")).getText();
        if (errorMessage.equalsIgnoreCase("Email is required")) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        waitForElement("//textarea", "customizedXpath");
        driver.findElement(By.xpath("//textarea")).sendKeys(userId);
        St.add(SingleClick("Reference_Share_Button"));
        waitTillSpinnerIsAvailable("Spinner");
        String message = driver.findElement(By.xpath("//div[@class='alert alert-dismissible e2e-ips-feedback alert-danger']")).getText();
        if (message.equalsIgnoreCase("×\n" +
                "An error occured when sharing reference(s).")) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        St.add(SingleClick("Reference_Share_Cancel_Button"));
        Assert("Fail");
    }

    /*************************Prenegotiated Project ****************************/
    @When("^\"([^\"]*)\" Prenegotiated project using \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void prenegotiatedProjectUsing(String actionType, String organizations, String ownedby, String name, String internaldescription, String contactcode, String bwfprojectid, String originatingordertrack, String rate, String hours, String budget, String currency, String effectivedate, String expirationdate, String deliveryoption, String createdPNPname) {
        St.add(SingleClick("PRENEGOTIATED_From_Left_Navigation"));
        if (actionType.equalsIgnoreCase("Create")) {
            waitForElement("Prenegotiated_Create_Button", "xpath");
            St.add(SingleClick("Prenegotiated_Create_Button"));
            St.add(VerifyExistence("PNP_Organization_Label"));
            St.add(VerifyExistence("PNP_Owned_By_Label"));
            St.add(VerifyExistence("PNP_Name_Label"));
            St.add(VerifyExistence("PNP_Internal_Description_Label"));
            St.add(VerifyExistence("PNP_Contact_Code_Label"));
            St.add(VerifyExistence("PNP_BWF_Project_ID_Label"));
            St.add(VerifyExistence("PNP_Rate_Label"));
            St.add(VerifyExistence("PNP_Hours_Label"));
            St.add(VerifyExistence("PNP_Budget_Label"));
            St.add(VerifyExistence("PNP_Currency_Label"));
            St.add(VerifyExistence("PNP_Effective_Date_Label"));
            St.add(VerifyExistence("PNP_Expiration_Date_Label"));
            St.add(VerifyExistence("PNP_Delivery_Option_Label"));
            St.add(EnterText("PNP_Organization_input", organizations));
            waitForElement("PNP_Select_First_org", "xpath");
            St.add(SingleClick("PNP_Select_First_org"));
            St.add(SingleClick("PNP_Organization_Add_button"));
            St.add(EnterText("PNP_OwnedBy_Input", ownedby));
            waitForElement("PNP_Select_First_Owner", "xpath");
            St.add(SingleClick("PNP_Select_First_Owner"));
            St.add(EnterText("PNP_Name_Input", name));
            St.add(EnterText("PNP_Internal_Description_Input", internaldescription));
            St.add(EnterText("PNP_Contact_Code_Input", contactcode));
            St.add(EnterText("PNP_BWF_Project_ID_Input", bwfprojectid));
            St.add(EnterText("PNP_Originating_Order_Track_Company_Code_Input", originatingordertrack));
            St.add(EnterText("PNP_Rate_Input", rate));
            St.add(EnterText("PNP_Hours_Input", hours));
            St.add(EnterText("PNP_Budget_Input", budget));
            St.add(SetDropdown("PNP_Currency_Input", currency));
            //St.add(EnterText("PNP_Effective_Date_Input", effectivedate));
            //St.add(EnterText("PNP_Expiration_Date_Input", expirationdate));
            if (deliveryoption.contains("Standard delivery")) {
                St.add(SingleClick("PNP_Standard_Delivery_Option_Input"));
            }
            if (deliveryoption.contains("Expected delivery")) {
                St.add(SingleClick("PNP_Expected_Delivery_Option_Input"));
            }
            St.add(SingleClick("PNP_Create_Submit_Button"));
            waitForElement("Success_Alert", "xpath");
        }
        if (actionType.equals("Update")) {
            waitForElement("//a[contains(text(),'" + createdPNPname + "')]", "customizedXpath");
            driver.findElement(By.xpath("//a[contains(text(),'" + createdPNPname + "')]")).click();
            St.add(EnterText("PNP_Name_Input", name));
            St.add(SingleClick("PNP_Project_Update_Button"));
            waitForElement("Success_Alert", "xpath");
        }
        Assert("Fail");
    }

    @And("^Prenegotiated Project is \"([^\"]*)\" successfully, and message \"([^\"]*)\"$")
    public void prenegotiatedProjectIsSuccessfullyAndMessage(String actionType, String message) {
        waitForElement("Success_Alert", "xpath");
        System.out.println(driver.findElement(By.xpath("//div[@class='alert alert-dismissible e2e-ips-feedback alert-success']")).getText());
        St.add(VerifyData("PNP_Project_Create_Message", message, ""));
        St.add(SingleClick("PROJECTS_From_Left_Navigation"));
        Assert("Fail");
    }


///////////////////////////////////////////

    @Given("^User is already logged into application with Search team credentials$")
    public void user_is_already_logged_into_application_with_search_team_credentials() {
    }


    @Given("^User is logged out$")
    public void LogOut() {
        St.add(SingleClick("Logout"));
        St.add(SingleClick("Confirm_LogOut"));
        driver.navigate().refresh();
        Assert("Fail");
    }


    @When("^Click Close Browser$")
    public void click_close_browser() {
        CloseBrowserInstance();
    }

    @Then("^Browser is closed$")
    public void browser_is_closed() {

    }

    @And("^Wait \"([^\"]*)\" Seconds$")
    public void wait_something_seconds(String Seconds) {
        Wait(Seconds + 000);
    }

    @And("^Open project and verify name \"([^\"]*)\"$")
    public void openProjectWithName(String projectName) {
        for (int second = 0; ; second++) {
            if (second <= objectTimeOut) {
                if (driver.findElements(By.xpath("//a[contains(text(),'" + projectName + "')]")).size() >= 1) {
                    St.add("Pass");
                    break;
                }
            } else {
                St.add("Fail");
            }
        }
        driver.findElement(By.xpath("//a[contains(text(),'" + projectName + "')]")).click();
        Assert("Fail");
//		WebElement webl = driver.findElement(By.xpath("//p[@class='e2e-hidden-title']"));
//
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		String text = js.executeScript("return document.arguments[0].innerHTML", webl).toString();
//		//String text = (String) js.executeScript("return arguments[0].text;", webl);
//		System.out.println(text);
    }

    @And("^Close Browser$")
    public void closeBrowser() {
    }

    @Then("^Add a \"([^\"]*)\" \"([^\"]*)\" and Validate \"([^\"]*)\"$")
    public void addAAndValidate(String actionType, String userDetails, String errorMessage) {
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "summary";
            driver.get(projecturl);
        }
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        St.add(SingleClick("Edit_Project_Share_List"));
        waitTillSpinnerIsAvailable("Middle_Spinner");
        waitForElement("Share_Email_Id", "xpath");
        St.add(EnterText("Share_Email_Id", userDetails));
        St.add(SingleClick("Add_USer"));
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        if (actionType.equalsIgnoreCase("Invalid")) {
            St.add(VerifyData("ShareUser_InvalidUser_Warning_Message", errorMessage, ""));
        }
        if (actionType.equalsIgnoreCase("Valid")) {
            if (driver.findElements(By.xpath("//td[contains(text(),'" + userDetails + "')]")).size() > 0) {
                St.add("Pass");
            } else {
                St.add("Fail");
            }
        }
        St.add(SingleClick("Done_Editing"));
        Assert("Fail");
    }

    @Then("^IPPU user should no be able to see Project Information section$")
    public void ippuUserShouldNoBeAbleToSeeProjectInformationSection() {
        if (driver.findElements(By.xpath("//i[@class='fa fa-fw fa-edit e2e-edit-project-info align-middle pointer']")).size() == 0) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }

    @And("^Open IS_Related and Validate adding  project \"([^\"]*)\"$")
    public void openIS_RelatedAndValidateAddingProject(String projectname) {
        waitForElement("Project_Is_Related_Edit", "xpath");
        St.add(SingleClick("Project_Is_Related_Edit"));
        waitForElement("Add_Is_Related_Projects_TextBox", "xpath");
        St.add(EnterText("Add_Is_Related_Projects_TextBox", projectname));
        waitForElement("//div[contains(text(),'" + projectname + "')]", "customizedXpath");
        driver.findElement(By.xpath("//div[contains(text(),'" + projectname + "')]")).click();
        St.add(SingleClick("Add_Link_Button"));
        waitForElement("//a[contains(text(),'" + projectname + "')]", "customizedXpath");
        driver.findElement(By.xpath("//a[contains(text(),'" + projectname + "')]")).isDisplayed();
        driver.findElement(By.cssSelector(".fa-trash")).click();
        St.add(SingleClick("Confirm_Delete_OK_Button"));

        for (int i = 1; 1 <= 10; i++) {
            waitForElement("//a[contains(text(),'" + projectname + "')]", "customizedXpath");
            if (driver.findElements(By.xpath("//a[contains(text(),'" + projectname + "')]")).size() == 0) {
                break;
            }
        }
        String status = "Fail";
        if (driver.findElements(By.xpath("//a[contains(text(),'" + projectname + "')]")).size() == 0) {
            status = "Pass";
        }

        St.add(status);


        St.add(SingleClick("Project_Is_Related_Edit"));
        Assert("Fail");
    }


    @And("^add \"([^\"]*)\" in a reference \"([^\"]*)\"$")
    public void addAnnotations_In_A_Reference(String annotations, String Citationdata) {
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("summary")) {
            projecturl = url.split("summary")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "results/list";
            driver.get(projecturl);
        }
        if (url.contains("results/add-patent")) {
            projecturl = url.split("results/add-patent")[0] + "results/list";
            driver.get(projecturl);
        }

        waitForElement("//a[contains(text(),'" + annotations + "')]", "customizedXpath");
        //String reference = annotations.split(":")[0];
        //String annotationValue = annotations.split(":")[1];
        driver.findElement(By.xpath("//a[contains(text(),'" + annotations + "')]")).click();
        waitTillSpinnerIsAvailable("Spinner");
        //Locate element for which you wants to retrieve x y coordinates.
        WebElement Image = driver.findElement(By.xpath("//div[@class='col-12 e2e-reference-abstract innography-content']//div[@class='content-area']/span"));
        //Used points class to get x and y coordinates of element.
        Point classname = Image.getLocation();
        int xcordi = classname.getX();
        int ycordi = classname.getY();


        Actions actions = new Actions(wbdriver);
        actions.moveToElement(wbdriver.findElement(By.tagName("body")), 0, 0).build();
        actions.moveByOffset(xcordi + 10, ycordi).doubleClick().build().perform();
        driver.findElement(By.xpath("//div[@class='confirmation-dialog e2e-create-annotation']//i[@class='fa fa-comment-o']")).click();

        String Citation_Style_DropDown = Citationdata.split(":")[0];
        String Citation_Value_1 = Citationdata.split(":")[1];
        String Citation_Value_2 = Citationdata.split(":")[2];

        St.add(SetDropdown("Add_Citation_Style_DropDown", Citation_Style_DropDown));
        if (Citation_Style_DropDown.contains("Line and column")) {
            St.add(EnterText("Add_Citation_Style_Line", Citation_Value_1));
            St.add(EnterText("Add_Citation_Style_Column", Citation_Value_2));
        } else if (Citation_Style_DropDown.contains("Paragraph")) {
            St.add(EnterText("Add_Citation_Style_Paragraph", Citation_Value_1));
        } else {
            St.add(EnterText("Add_Citation_Style_Other", Citation_Value_1));
        }

//        St.add(SingleClick("Add_Citation_Style_Add_Comments_Add_Button"));
//        St.add(EnterText("Add_Citation_Style_Add_Comments", "Here the added comments"));
        St.add(SingleClick("Create_Annotation_Create_Button"));
        Assert("Fail");
    }

    @When("^Edit added annotation with update value \"([^\"]*)\"$")
    public void editAddedAnnotation(String Citationdata) {
        waitForElement("Update_Annotaion_Icon", "xpath");
        St.add((SingleClick("Update_Annotaion_Icon")));
        String Citation_Style_DropDown = Citationdata.split(":")[0];
        String Citation_Value_1 = Citationdata.split(":")[1];
        String Citation_Value_2 = Citationdata.split(":")[2];

        St.add(SetDropdown("Add_Citation_Style_DropDown", Citation_Style_DropDown));
        if (Citation_Style_DropDown.contains("Line and column")) {
            St.add(EnterText("Add_Citation_Style_Line", Citation_Value_1));
            St.add(EnterText("Add_Citation_Style_Column", Citation_Value_2));
        } else if (Citation_Style_DropDown.contains("Paragraph")) {
            St.add(EnterText("Add_Citation_Style_Paragraph", Citation_Value_1));
        } else {
            St.add(EnterText("Add_Citation_Style_Other", Citation_Value_1));
        }
        St.add(SingleClick("Create_annotation_Update_button"));
        waitTillSpinnerIsAvailable("Spinner");
        Assert("Fail");
    }

    @Then("^Annotation is updated successfully with update value \"([^\"]*)\"$")
    public void annotationIsUpdatedSuccessfully(String Citationdata) {
//        waitUntilAngularReady();
//        Wait("3000");
//        St.add((SingleClick("Update_Annotaion_Icon")));
//        String Citation_Style_DropDown = Citationdata.split(":")[0];
//        String Citation_Value_1 = Citationdata.split(":")[1];
//        String Citation_Value_2 = Citationdata.split(":")[2];
//        if (Citation_Style_DropDown.contains("Line and column")) {
//            if (driver.findElement(By.xpath("//input[@id='line']")).getText().equalsIgnoreCase(Citation_Value_1)) {
//                St.add("Pass");
//            } else {
//                St.add("Fail");
//            }
//            if (driver.findElement(By.xpath("//input[@id='column']")).getText().equalsIgnoreCase(Citation_Value_2)) {
//                St.add("Pass");
//            } else {
//                St.add("Fail");
//            }
//        } else if (Citation_Style_DropDown.contains("Paragraph")) {
//            if (driver.findElement(By.xpath("//input[@id='paragraph']")).getText().equalsIgnoreCase(Citation_Value_1)) {
//                St.add("Pass");
//            } else {
//                St.add("Fail");
//            }
//        } else {
//            if (driver.findElement(By.xpath("//input[@id='other']")).getText().equalsIgnoreCase(Citation_Value_1)) {
//                St.add("Pass");
//            } else {
//                St.add("Fail");
//            }
//        }
//        St.add(SingleClick("Create_Annotation_Cancel_Button"));
//        Assert("Fail");
    }


    @Then("^Delete added annotation$")
    public void deleteAddedAnnotation() {
        waitForElement("Update_Annotaion_Icon", "xpath");
        St.add((SingleClick("Update_Annotaion_Icon")));
        Wait("3000");
        waitForElement("Create_annotation_Delete_button", "xpath");
        St.add(SingleClick("Create_annotation_Delete_button"));
        St.add(SingleClick("Create_annotation_Confirm_Delete_button"));
        waitTillSpinnerIsAvailable("Spinner");
        Assert("Fail");
    }


    @And("^Click link \"([^\"]*)\"$")
    public void clickLink(String name) {
        waitForElement("//a[contains(text(),'" + name + "')]", "customizedXpath");
        driver.findElement(By.xpath("//a[contains(text(),'" + name + "')]")).click();
    }


    @Then("^\"([^\"]*)\" \"([^\"]*)\" is deleted successfully$")
    public void ProjectIsDeletedSuccessfully(String projectType, String name) {
        waitTillSpinnerIsAvailable("Middle_Spinner");
        waitTillSpinnerIsAvailable("Spinner");
        waitForElement("First_Project", "xpath");
        String firstPnpProjectName = "";
        if (projectType.equalsIgnoreCase("Project")) {
            firstPnpProjectName = driver.findElement(By.xpath("//a[@class=\"e2e-link-analyst\"]")).getText();
        }
        if (projectType.equalsIgnoreCase("PNPProject")) {
            firstPnpProjectName = driver.findElement(By.xpath("//a[@class=\"e2e-project-name\"]")).getText();
        }
        if (firstPnpProjectName.equalsIgnoreCase(name)) {
            St.add("Fail");
        } else {
            St.add("Pass");
        }
        Assert("Fail");

    }

    @Then("^\"([^\"]*)\" \"([^\"]*)\" is cancelled successfully$")
    public void isCancelledSuccessfully(String projectType, String name) {
        waitForElement("First_Project", "xpath");
        String firstProjectStatus = "";
        if (name.equalsIgnoreCase(driver.findElement(By.xpath("//div[@class=\"text-truncate\"]/a")).getText())) {
            firstProjectStatus = driver.findElement(By.xpath("//div[@class=\"e2e-project-status\"]")).getText();
            if (firstProjectStatus.equalsIgnoreCase("Cancelled")) {
                St.add("Pass");
            } else {
                St.add("Fail");
            }
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }

    @Then("^Prenegotiated Project name should should be \"([^\"]*)\"$")
    public void prenegotiatedProjectNameShouldShouldBe(String name) {
        St.add(SingleClick("PROJECTS_From_Left_Navigation"));
        Open_Project();
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "summary";
            driver.get(projecturl);
        }
        waitForElement("//div[@class='col-md-3 e2e-initialrequest-projectType column']", "customizedXpath");
        String actualvalue = driver.findElement(By.xpath("//div[@class='col-md-3 e2e-initialrequest-projectType column']")).getText();
        if (actualvalue.equalsIgnoreCase(name)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }

    @And("^Delete \"([^\"]*)\" \"([^\"]*)\"$")
    public void delete(String type, String value) {
        if (type.equalsIgnoreCase("prenegotiated Project")) {
            St.add(SingleClick("PRENEGOTIATED_From_Left_Navigation"));
            clickLink(value);
            St.add(SingleClick("PNP_Delete_Button"));
            St.add(SingleClick("PNO_Confirm_Ok"));
        }
        if (type.equalsIgnoreCase("Project")) {
            St.add(SingleClick("STM_PROJECTS_From_Left_Navigation"));
            waitForElement("Project_Settings_Link", "xpath");
            St.add(SingleClick("Project_Settings_Link"));
            waitForElement("Delete_Project", "xpath");
            St.add(SingleClick("Delete_Project"));
            St.add(SingleClick("Confirm_Ok"));
            waitForElement("//h2[contains(text(),'Search Team Projects')]", "customizedXpath");
        }
        if (type.equalsIgnoreCase("Proposal")) {
            St.add(SingleClick("Proposal_Delete_Button"));
            St.add(SingleClick("Confirm_Ok"));
            waitTillSpinnerIsAvailable("Middle_Spinner");
            waitTillSpinnerIsAvailable("Spinner");
        }

        Assert("Fail");
    }

    @And("^Add Report \"([^\"]*)\" with name \"([^\"]*)\"$")
    public void report(String reportType, String reportName) {
        String url = driver.getCurrentUrl();
        String projecturl = "";
        waitTillSpinnerIsAvailable("Middle_Spinner");
        waitTillSpinnerIsAvailable("Spinner");
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "summary";
            driver.get(projecturl);
        }
        waitForElement("Reports_Add_Report", "xpath");
        St.add(SingleClick("Reports_Add_Report"));
        St.add(EnterText("Reports_Title", reportName));
        St.add(SetDropdown("Reports_Select_ReportType", reportType));
        driver.findElement(By.xpath("//label[@class='drop-area']//input")).sendKeys("C:\\GIT\\ip-search\\Sample_File_5.pdf");
        waitForElement("Reports_Submit_Report", "xpath");
        St.add(SingleClick("Reports_Submit_Report"));
        Assert("Fail");
    }

    @Then("^Report is added with name \"([^\"]*)\"$")
    public void reportIsAddedWithName(String name) {
        waitForElement("//h5[@class=\"font-weight-bold d-inline-block pointer\"]", "customizedXpath");
        String actualvalue = driver.findElement(By.xpath("//h5[@class=\"font-weight-bold d-inline-block pointer\"]")).getText();
        if (actualvalue.equalsIgnoreCase(name)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }


    @When("^\"([^\"]*)\" Introduction \"([^\"]*)\"$")
    public void addIntroduction(String actionType, String introduction) {
        waitForElement("//*[contains(text(),'Is related to:')]", "customizedXpath");
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        if (actionType.equalsIgnoreCase("Add")) {
            String url = driver.getCurrentUrl();
            String projecturl = "";
            if (url.contains("proposal")) {
                projecturl = url.split("proposal")[0] + "summary";
                driver.get(projecturl);
            }
            driver.findElement(By.cssSelector("#nav-introduction .fa")).click();
            Wait("3000");
            driver.findElement(By.cssSelector(".ql-editor > div")).click();
            driver.findElement(By.cssSelector(".ql-editor")).clear();
            driver.findElement(By.cssSelector(".ql-editor")).sendKeys(introduction);
            driver.findElement(By.cssSelector(".card-footer > .btn-secondary")).click();
            driver.findElement(By.cssSelector("#nav-introduction .fa")).click();
            driver.findElement(By.cssSelector(".ql-editor > div")).click();
            driver.findElement(By.cssSelector(".ql-editor")).clear();
            driver.findElement(By.cssSelector(".ql-editor")).sendKeys(introduction);
            driver.findElement(By.xpath("//button[@class='btn btn-primary e2e-introduction-save']")).click();
        }
        if (actionType.equalsIgnoreCase("Update")) {
            driver.findElement(By.xpath("//ips-introduction[@id='nav-introduction']/h3/i[2]")).click();
            driver.findElement(By.cssSelector(".ql-editor > div")).click();
            driver.findElement(By.cssSelector(".ql-editor")).clear();
            driver.findElement(By.cssSelector(".ql-editor")).sendKeys(introduction);
            driver.findElement(By.xpath("//button[@class='btn btn-primary e2e-introduction-save']")).click();
        }

    }

    @Then("^\"([^\"]*)\" is \"([^\"]*)\" successfully$")
    public void isSuccessfully(String introduction, String arg1) {
        waitTillSpinnerIsAvailable("Middle_Spinner");
        waitTillSpinnerIsAvailable("Spinner");
        String actualvalue = driver.findElement(By.xpath("//*[@class='ql-container ql-editor e2e-axe-3rdparty']/div")).getText();
        if (actualvalue.equalsIgnoreCase(introduction)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }


    @Then("^References reOrdered successfully \"([^\"]*)\"$")
    public void referencesReOrderedSuccessfully(String reOrderedRef) {
        St.add(SingleClick("ReOrder_References"));
        if (reOrderedRef.contains(",")) {
            String[] reOrderedRefArray = reOrderedRef.split(",");
            for (int i = 1; i <= reOrderedRefArray.length; i++) {
                String ref = reOrderedRefArray[i - 1];
                String actualValue = driver.findElement(By.xpath("(//*[@class='card-header']/span[2])[" + i + "]")).getText();
                if (actualValue.contains(ref)) {
                    St.add("Pass");
                } else {
                    St.add("Fail");
                }
            }
        }
        St.add(SingleClick("ReOrder_Reference_Cancel_Button"));
        Assert("Fail");
    }

    @And("^Cancel the project$")
    public void cancelTheProject() {
        St.add(SingleClick("STM_PROJECTS_From_Left_Navigation"));
        waitForElement("Project_Settings_Link", "xpath");
        St.add(SingleClick("Project_Settings_Link"));
        waitForElement("Cancel_project", "xpath");
        St.add(SingleClick("Cancel_project"));
        St.add(SingleClick("Confirm_Ok"));
        waitForElement("Success_Alert", "xpath");
        St.add(SingleClick("PROJECTS_From_Left_Navigation"));
        Assert("Fail");

    }

    @When("^\"([^\"]*)\" cannot \"([^\"]*)\" project$")
    public void cannotProject(String userType, String actionType){
        if (driver.findElements(By.xpath("//*[@class='e2e-settings-link']")).size()== 0) {
            St.add("Pass");
        } else St.add("Fail");
        Assert("Fail");
    }
    @Then("^\"([^\"]*)\" LNH is hide to  \"([^\"]*)\"$")
    public void lnhIsHideTo(String ele, String userType) throws Throwable {
        if (driver.findElements(By.xpath(PropertiesUtil.getProperty(ele))).size()== 0) {
            St.add("Pass");
        } else St.add("Fail");
        Assert("Fail");
    }

    @When("^\"([^\"]*)\" Search History \"([^\"]*)\" and entry \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void searchHistoryAndEntry(String actionType, String category, String ref, String query, String
            result, String reviewed) throws Exception {
        String url = driver.getCurrentUrl();
        String projecturl = "";
        if (url.contains("proposal")) {
            projecturl = url.split("proposal")[0] + "summary";
            driver.get(projecturl);

        }
        waitForElement("Search_History_Edit_Link", "xpath");
        St.add(SingleClick("Search_History_Edit_Link"));
        if (actionType.equalsIgnoreCase("Update")) {
            waitForElement("Search_History_Edit_Entry_Link", "xpath");
            St.add(SingleClick("Search_History_Edit_Entry_Link"));
        }
        if (actionType.equalsIgnoreCase("Add")) {
            waitForElement("Search_History_Select_Entry", "xpath");
            St.add(SetDropdown("Search_History_Select_Entry", category));
            St.add(SingleClick("Search_History_Add_Entry_Button"));
        }
        waitForElement("Search_History_Result_Query_Input", "xpath");
        St.add(EnterText("Search_History_Result_Query_Input", query));
        St.add(EnterText("Search_History_Result_Ref_Input", ref));
        St.add(EnterText("Search_History_Result_Input", result));
        if (reviewed.equalsIgnoreCase("yes")) {
            St.add(SingleClick("Search_History_Reviewed_CheckBox"));
        }
        St.add(SingleClick("Search_History_Submit_Button"));
        waitForElement("Search_History_Done_Editing_Button", "xpath");
        St.add((SingleClick("Search_History_Done_Editing_Button")));
        Assert("Fail");
    }

    @Then("^\"([^\"]*)\" is \"([^\"]*)\" successfully with details  \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void isSuccessfullyWithDetails(String category, String actionTypes, String ref, String query, String result, String reviewed) {
        String actualCategory = driver.findElement(By.cssSelector("span > b")).getText();
        String actualReference = driver.findElement(By.cssSelector(".column:nth-child(1) > div")).getText();
        String actualQuery = driver.findElement(By.cssSelector(".flexible-column > div")).getText();
        String actualResult = driver.findElement(By.cssSelector(".column:nth-child(3) > div")).getText();
        if (category.equalsIgnoreCase(actualCategory)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        if (ref.equalsIgnoreCase(actualReference)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        if (query.equalsIgnoreCase(actualQuery)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        if (result.equalsIgnoreCase(actualResult)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }


    @Then("^\"([^\"]*)\" is deleted successfully with message \"([^\"]*)\"$")
    public void isDeletedSuccessfullyWithMessage(String elementType, String expectedMessage) throws Throwable {
        String status = "Fail";
        String actualMessage = "";
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        List<WebElement> elementList = driver.findElements(By.xpath("//em"));
        for (WebElement element : elementList) {
            actualMessage = element.getText();
            if (expectedMessage.contains(actualMessage)) {
                status = "Pass";
                break;
            }
        }
        St.add(status);
        Assert("Fail");
    }

//        if(entry.equalsIgnoreCase("Proposal")) {
//            message = driver.findElement(By.xpath("//*[@class='container-fluid p-0']")).getText().trim();
//        }
//        if(entry.equalsIgnoreCase("Proposal")) {
//            message = driver.findElement(By.xpath("//*[@class='container-fluid p-0']")).getText().trim();
//        }
//
//            if (message.equalsIgnoreCase("No proposal has been added")) {
//                St.add("Pass");
//            } else {
//                St.add("Fail");
//            }
//        Assert("Fail");
//
//    }

    @When("^Delete \"([^\"]*)\"$")
    public void delete(String entry) throws Throwable {
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        waitForElement("Search_History_Delete_Entry", "xpath");
        St.add(SingleClick("Search_History_Delete_Entry"));
        St.add(SingleClick("Confirm_Ok"));
        Assert("Fail");
    }

    @Then("^annotation is deleted successfully$")
    public void annotationIsDeletedSuccessfully() {
        if (driver.findElements(By.xpath(PropertiesUtil.getProperty("Search_History_Delete_Entry"))).size() == 0) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");

    }

    @Then("^Status of Project is \"([^\"]*)\"$")
    public void statusOfProjectIs(String status) throws Throwable {
        Wait("5000");
        waitForElement("PROJECTS_From_Left_Navigation", "xpath");
        St.add(SingleClick("PROJECTS_From_Left_Navigation"));
        waitForElement("Project_Status", "xpath");
        String ProjectStatus = driver.findElement(By.xpath("//div[@class='e2e-project-status']/div")).getText();
        if (ProjectStatus.equalsIgnoreCase(status)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }

    @Then("^Latest Document contain \"([^\"]*)\"$")
    public void latestDocumentContain(String report) throws Throwable {
        waitForElement("//*[@class='pointer e2e-project-document']", "customizedXpath");
        String ProjectStatus = driver.findElement(By.xpath("//*[@class='pointer e2e-project-document']")).getText();
        if (ProjectStatus.equalsIgnoreCase(report)) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }

    @And("^Publish the project \"([^\"]*)\"$")
    public void publishTheProject(String publishList) throws Throwable {
        waitForElement("Publish_Button", "xpath");
        St.add(SingleClick("Publish_Button"));
        if (publishList.contains(",")) {
            String[] publishArray = publishList.split(",");
            for (int i = 1; i <= publishArray.length; i++) {
                String feature = publishArray[i - 1];
                String RelatedClaim = "";
                if (feature.contains(":")) {
                    String[] featureString = feature.split(":");
                    feature = featureString[0];
                    RelatedClaim = featureString[1];
                }
                waitForElement("//input[@id='feat" + i + "']", "customizedXpath");
                driver.findElement(By.xpath("//input[@id='feat" + i + "']")).sendKeys(feature);
                driver.findElement(By.xpath("//textarea[@id='relatedClaims" + i + "']")).sendKeys(RelatedClaim);
                if (i < publishArray.length) {
                    St.add(SingleClick("Add_More_Feature"));
                }
            }
            St.add(SingleClick("Feature_Save"));
        }
        Assert("Fail");
    }

    @Then("^Publish project$")
    public void publishProject() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-850)", "");
        St.add(SingleClick("Publish_Button"));
        waitForElement("Publish_Submit_Button", "xpath");
        St.add(SingleClick("Publish_Submit_Button"));
        for (int second = 0; second <= objectTimeOut; second++) {
            if (driver.findElements(By.xpath("//*[@class='modal-content']")).size() == 0) {
                break;
            }
        }
        for (int second = 0; second <= objectTimeOut; second++) {
            if (driver.findElements(By.xpath("//*[@class='fa fa-fw fa-cog fa-spin align-middle']")).size() == 0) {
                break;
            }
        }
        Assert("Fail");
    }

    @And("^Verify first search history should be \"([^\"]*)\" and second search history should be \"([^\"]*)\"$")
    public void verifyFirstSearchHistoryShouldBeAndSecondSearchHistoryShouldBe(String category1, String category2) throws Throwable {
        waitForElement("(//div[@class='e2e-search-history-header search-history-header my-3'])[1]", "customizedXpath");
        String firstCategory = driver.findElement(By.xpath("(//div[@class='e2e-search-history-header search-history-header my-3'])[1]")).getText();
        String secondCategory = driver.findElement(By.xpath("(//div[@class='e2e-search-history-header search-history-header my-3'])[2]")).getText();
        if (firstCategory.equalsIgnoreCase(category1)) {
            if (secondCategory.equalsIgnoreCase(category2)) {
                St.add("Pass");
            } else {
                St.add("Fail");
            }
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }

    @When("^ReOrder Search History \"([^\"]*)\"$")
    public void reorderSearchHistory(String actionType) {
        waitForElement("//*[@title='" + actionType + "']", "customizedXpath");
        driver.findElement(By.xpath("//*[@title='" + actionType + "']")).click();
        Wait("25000");
        driver.navigate().refresh();
        Wait("25000");
        Assert("Fail");
    }

/////////////////////////////// Proposal methods/////////////////////////////

    @When("^\"([^\"]*)\" proposal with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void addProposalWith(String actionType, String proposalTitle, String summary, String currency) {
        if (actionType.equalsIgnoreCase("Add")) {
            St.add(SingleClick("Proposal_Add_Link"));
            St.add(SingleClick("Proposal_Remove_Phase"));
        } else {
            St.add(SingleClick("Proposal_Edit"));
        }
        St.add(EnterText("Proposal_Title_Input", proposalTitle));
        St.add(EnterText("Proposal_Summary_Input", summary));
        St.add(SetDropdown("Proposal_Currency_Input", currency));

        St.add(SingleClick("Proposal_Save_Button"));
        for (int second = 0; second <= objectTimeOut; second++) {
            if (driver.findElements(By.xpath("//*[@class='fa fa-fw fa-cog fa-spin align-middle']")).size() == 0) {
                break;
            } else {
                Wait("4000");
            }
        }
        // Wait("3000");
        Assert("Fail");
    }

    @Then("^Proposal is \"([^\"]*)\" successfully with  \"([^\"]*)\", \"([^\"]*)\"$")
    public void proposalIsAddedSuccessfullyWith(String actionType, String proposalTitle, String summary) {
        String status = "Fail";
        String actualProposalTitle = driver.findElement(By.xpath("(//*[@class='col-md-9 summary e2e-summary-label'])[1]")).getText().trim();
        String actualSummary = driver.findElement(By.xpath("(//*[@class='col-md-9 summary e2e-summary-label'])[2]")).getText().trim();
        if (actualProposalTitle.equalsIgnoreCase(proposalTitle)) {
            if (summary.equalsIgnoreCase(actualSummary)) {
                status = "Pass";
            }
        }
        St.add(status);
        Assert("Fail");
    }

    @Then("^\"([^\"]*)\" is published$")
    public void isPublished(String type) throws Throwable {
        if (type.equalsIgnoreCase("Proposal")) {
            if (driver.findElements(By.xpath("Proposal_Accept_Button")).size() == 1) {
                St.add("Pass");
            }
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }

    @When("^\"([^\"]*)\" The proposal$")
    public void theProposal(String actionType) {
        String actualHeading = "";
        if (actionType.equalsIgnoreCase("Revert")) {
            St.add(SingleClick("Proposal_Revert"));
            St.add(SingleClick("Proposal_Revert_Comment_Click"));
            Wait("20000");
            St.add(EnterText("Proposal_Revert_Comment", actionType + " Comment"));
            St.add(SingleClick("Confirm_Ok"));

        } else {
            if (actionType.equalsIgnoreCase("Accept")) {
                St.add(SingleClick("Proposal_Accept_Button"));
                actualHeading = driver.findElement(By.xpath("//*[@class='modal-title']")).getText();
            }
            if (actionType.equalsIgnoreCase("Decline")) {
                St.add(SingleClick("Proposal_Decline_Button"));
                actualHeading = driver.findElement(By.xpath("//*[@class='modal-title text-danger']")).getText();
            }

            if (actualHeading.equalsIgnoreCase(actionType + " Proposal on Behalf of a User")) {
                St.add("Pass");
            } else {
                St.add("Fail");
            }
            St.add(SetDropdown("Proposal_Accept_Decline_Communication_Type", " Email"));
            St.add(EnterText("Proposal_Accept_Decline_Communication_Summarize", "Summary of " + actionType));
            St.add(SingleClick("Proposal_Accept_Decline_OK"));
        }
        waitTillSpinnerIsAvailable("Middle_Spinner");
        Assert("Fail");
    }

    @Then("^Proposal is \"([^\"]*)\"$")
    public void proposalIs(String actionType) {
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        String actualHeading = driver.findElement(By.xpath("//*[@class='col-6 d-flex flex-column justify-content-center']/span")).getText().trim();
//        if (actionType.equalsIgnoreCase("Accepted")) {
//            actualHeading = driver.findElement(By.xpath("//*[@class='fa fa-check text-danger']")).getText().trim();
//        }
//        if (actionType.equalsIgnoreCase("Declined")) {
//            actualHeading = driver.findElement(By.xpath("//*[@class='fa fa-check text-danger']")).getText().trim();
//
//        }
        if (actualHeading.equalsIgnoreCase("Proposal is " + actionType + "!")) {
            St.add("Pass");
        } else {
            St.add("Fail");
        }
        Assert("Fail");
    }

    @And("^\"([^\"]*)\" \"([^\"]*)\" from SETTINGS Page$")
    public void copyProjectButtonFor(String actionType, String companyName) {
        for (int second = 0; second <= objectTimeOut; second++) {
            if (driver.findElements(By.xpath("//*[@class='fa fa-fw fa-cog fa-spin align-middle']")).size() == 0) {
                break;
            }
        }
        if (driver.findElements(By.xpath("//*[contains(text(),'" + companyName + "')]")).size() == 0) {
            St.add(EnterText("Settings_Organization", companyName));
            waitForElement("PNP_Select_First_org", "xpath");
            St.add(SingleClick("Settings_Select_First_org"));
            waitTillSpinnerIsAvailable("Spinner");
        }
        if (actionType.equalsIgnoreCase("Disable")) {
            if (driver.findElement(By.xpath("//td[contains(text(), '" + companyName + "')]/following-sibling::td/input")).isEnabled()) {
                driver.findElement(By.xpath("//td[contains(text(), '" + companyName + "')]/following-sibling::td/input")).click();
                St.add("Pass");
            }
        }
        if (actionType.equalsIgnoreCase("Enable")) {
            if (!driver.findElement(By.xpath("//td[contains(text(), '" + companyName + "')]/following-sibling::td/input")).isEnabled()) {
                driver.findElement(By.xpath("//td[contains(text(), '" + companyName + "')]/following-sibling::td/input")).click();
                St.add("Pass");
            }
        }
        St.add(SingleClick("Settings_Save_Button"));
        St.add(SingleClick("Confirm_Ok"));
        Assert("Fail");
    }


    @Then("^Create Copy Button \"([^\"]*)\" available on the page$")
    public void createCopyButtonAvailableOnThePage(String presenceCheck) throws Throwable {
        String status = "Fail";
        Wait("20000");
        if (presenceCheck.equalsIgnoreCase("is")) {
            if (driver.findElements(By.xpath("//*[@class='btn btn-copy btn-secondary pointer create-copy']")).size() == 1) {
                status = "Pass";
            }
        }
        if (presenceCheck.equalsIgnoreCase("is not")) {
            if (driver.findElements(By.xpath("//*[@class='btn btn-copy btn-secondary pointer create-copy']")).size() == 0) {
                status = "Pass";
            }
        }
        St.add(status);
        Assert("Fail");
    }

    @And("^Create a Copy with \"([^\"]*)\" option unchecked$")
    public void createACopyWithOptionUnchecked(String option) {
        St.add(SingleClick("Create_A_Copy_Link"));
        if (option.equalsIgnoreCase("CPA_Records_Checkbox")) {
            St.add(SingleClick("Create_Copy_CPA_Records_Checkbox"));
        }
        if (option.equalsIgnoreCase("Hide_Proposal")) {
            St.add(SingleClick("Create_Copy_Hide_Proposal_Checkbox"));
        }
        if (option.equalsIgnoreCase("Hide_Teams")) {
            St.add(SingleClick("Create_Copy_Hide_Teams_Checkbox"));
        }
        St.add(SingleClick("Create_Copy_Copy_Button"));
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        Assert("Fail");
    }

    @And("^Open \"([^\"]*)\" project$")
    public void openCaseMatterNoCopyProject(String ProjectName) {
        driver.findElement(By.xpath("//a[contains(text(),'" + ProjectName + "')]")).click();
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
        Assert("Fail");
    }

    @And("^Publish project with \"([^\"]*)\"$")
    public void publishProjectWith(String sectionName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-850)", "");
        St.add(SingleClick("Publish_Button"));
        waitForElement("Publish_Submit_Button", "xpath");
        if (sectionName.contains(",")) {
            String[] itemsArray = sectionName.split(",");
            for (int i = 0; i < itemsArray.length; i++) {
                String item = itemsArray[i].trim();
                driver.findElement(By.id(item)).click();
            }
        } else {
            if (!sectionName.isEmpty()) {
                driver.findElement(By.id(sectionName)).click();
            }
        }
        St.add(SingleClick("Publish_Submit_Button"));
        for (int second = 0; second <= objectTimeOut; second++) {
            if (driver.findElements(By.xpath("//*[@class='modal-content']")).size() == 0) {
                break;
            }
        }
        waitTillSpinnerIsAvailable("Middle_Spinner");
        Assert("Fail");
    }

    @And("^Wait spinners to disappear$")
    public void waitSpinnersToDisappear() {
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
    }

    @Then("^\"([^\"]*)\" project is linked under Is Related section$")
    public void projectIsLinkedUnderIsRelatedSection(String projectName) {
        if (driver.findElements(By.xpath("//a[contains(text(),'" + projectName + "')]")).size() >= 1) {
            St.add("Pass");
        } else St.add("Fail");
        Assert("Fail");
    }



    @Then("^\"([^\"]*)\" Tab is hide in the copied project$")
    public void tabIsHideInTheCopiedProject(String items) {
        if (items.contains(",")) {
            String[] itemsArray = items.split(",");
            for (int i = 0; i < itemsArray.length; i++) {
                String item = itemsArray[i];
                List<WebElement> tabList = driver.findElements(By.xpath("//li[@class='nav-item']/a"));
                for (int j = 0; j < tabList.size(); j++) {
                    if (!tabList.get(j).getText().equalsIgnoreCase(item)) {
                        St.add("Pass");
                    } else St.add("Fail");
                }

            }
        }
        Assert("Fail");
    }

    @And("^Navigate to \"([^\"]*)\"$")
    public void navigateTo(String linkName) {
        St.add(SingleClick(linkName));
        waitTillSpinnerIsAvailable("Spinner");
        waitTillSpinnerIsAvailable("Middle_Spinner");
    }



    ///////////////////////////////////////////////////////////////////





    @Given("^Login$")
    public void login() throws Exception {
        LaunchBrowser("Chrome", "https://qa.idsbuilder.com/#/ids/forms");
        St.add(Login("paralegal.user1@qa.idsbuilder.com", "winter2020TG!5"));
        Wait("4000");

    }

    @When("^Add IDS$")
    public void addIDS() {
        //testing_IDSB-296
        int j= 21;
        int k = 22;
        for(int i=1;i<=1000;i++) {
            driver.findElement(By.xpath("//span[contains(text(),'Build')]")).click();
            Wait("10000");
            driver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys("testing_IDSB-296");
            driver.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys("F2");
            driver.findElement(By.xpath("//input[@id='mat-input-2']")).sendKeys("A2");
            driver.findElement(By.xpath("//input[@id='mat-input-2']")).sendKeys(Keys.TAB);
            Wait("2000");
            driver.findElement(By.xpath("//mat-select/div/div/span")).click();
           // driver.findElement(By.xpath("//div[@class='mat-select-value']/span")).click();
           // driver.findElement(By.xpath("//mat-select[@id='mat-select-"+j+"']/div/div/span")).click();
            //driver.findElement(By.xpath("//mat-select[@class='mat-select ng-tns-c18-"+j+" ng-star-inserted mat-select-required mat-select-empty ng-pristine ng-invalid mat-select-invalid ng-touched']/div/div/span")).click();
            Wait("2000");

            driver.findElement(By.xpath("//span[contains(.,'updated_grp_Admin')]")).click();
            driver.findElement(By.xpath("//mat-select[@id='mat-select-"+k+"']/div/div/span")).click();
           // driver.findElement(By.xpath("(//mat-select[@class='mat-select ng-tns-c18-"+k+" ng-star-inserted mat-select-required mat-select-empty ng-untouched ng-pristine ng-invalid']/div/div/span")).click();
            Wait("2000");
            driver.findElement(By.xpath("//span[contains(.,'Attorney User1 (attorney.user1@qa.idsbuilder.com)')]")).click();
            driver.findElement(By.xpath("//span[contains(.,'Create')]")).click();
            Wait("10000");
            j=j+2;
            k=k+2;
            if(i==1000){
                driver.findElement(By.xpath("//*[contains(.,'You have created the maximum number of forms allowed for this patent application (999)')]")).isDisplayed();}
        }

    }

    @Then("^Create IDS$")
    public void createIDS() {
    }
}