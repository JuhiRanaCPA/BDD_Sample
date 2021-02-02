Feature: Validations related to Project Detailed Page


# Test Scenario: Prj_Det_01: Validate heading on Project Summary Page under Project detailed page
  Scenario Outline: Prj_Det_01: Validate heading on Project Summary Page under Project detailed page
    Given Open "<Browser>" and Launch application URL
    And Login with Credentials "<UserType>"
    And Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    Then Open project and verify name "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo    | Browser | UserType |
      | Patentability   | Biotechnology   | Prj_Det_01_IPPU | Chrome  | IPPU     |


# Test Scenario: Prj_Det_02: "IS Related to" functionality under Project detailed page
  Scenario Outline: Prj_Det_02: "IS Related to" functionality under Project detailed page
    Given Open "<Browser>" and Launch application URL
    And Login with Credentials "<UserType>"
    And Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Open project and verify name "<CaseMatterNo>"
    And Open IS_Related and Validate adding  project "<project_name>"
    When Delete "Project" "<CaseMatterNo>"
    Then "Project" "<CaseMatterNo>" is deleted successfully
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo   | Browser | UserType | project_name |
      | Patentability   | Biotechnology   | Prj_Det_02_STL | Chrome  | STL      | 777          |



 #Test Scenario: Prj_Det_03: Search Team Sharing Settings functionality under Project detailed page
  Scenario Outline: Prj_Det_03: Search Team Sharing Settings functionality under Project detailed page
    Given Open "<Browser>" and Launch application URL
    And Login with Credentials "<UserType>"
    And Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Open project and verify name "<CaseMatterNo>"
    And Add a "Invalid" "<Invalid_User>" and Validate "<Error_Message>"
    And Add a "Valid" "<Valid_User1>" and Validate "added user"
    And Add a "Valid" "<Valid_User2>" and Validate "added user"
    Then Add a "Valid" "<Valid_User3>" and Validate "added user"
    When Delete "Project" "<CaseMatterNo>"
    Then "Project" "<CaseMatterNo>" is deleted successfully
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | Browser | UserType | Invalid_User       | Valid_User1                      | Valid_User2                     | Valid_User3                     | Error_Message                                                                                                                          |
      | Patentability   | Biotechnology   | Prj_Det_03   | Chrome  | STA      | abcsdasdadadasdada | jesse.pinkman@nomail.example.com | walter.white@nomail.example.com | wilmer.yates@nomail.example.com | Could not add user; please select a user from the list of suggestions, if no suggestions appear, try using a different search criteria |


# Test Scenario: Prj_Det_04: Validate fields on Project Information Edit functionality under Project detailed page
  Scenario Outline: Prj_Det_04: Validate fields on Project Information Edit functionality under Project detailed page
    Given Open "<Browser>" and Launch application URL
    And Login with Credentials "<UserType>"
    And Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Open project and verify name "<CaseMatterNo>"
    Then Enter value "<Ref_No>" in "Ref_Id_Input" and validate various scenarios for add, cancel and update "<Updated_Ref_No>"
    And Enter value "<Project_title>" in "Project_Title_Input" and validate various scenarios for add, cancel and update "<Updated_Project_title>"
    And Enter value "<Target_Patents>" in "Target_Patents_Input" and validate various scenarios for add, cancel and update "<Updated_Target_Patents>"
    When Delete "Project" "<CaseMatterNo>"
    Then "Project" "<CaseMatterNo>" is deleted successfully
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | Browser | UserType | Ref_No      | Project_title                        | Customer_Company        | Project_Owner | Updated_Ref_No      | Updated_Project_title                        | Updated_Customer_Company | Updated_Project_Owner | Target_Patents | Updated_Target_Patents |
      | Patentability   | Biotechnology   | Prj_Det_04   | Chrome  | STL      | CPA_Ref_122 | Title of Automation STA Prj Creation | IPC Dev - Goodman & Co. | Skyler White  | Updated_CPA_Ref_122 | Updated Title of Automation STA Prj Creation | IPC Search - Test        | Demo Account 1        | Sample patent  | Update patent 12       |


# Test Scenario: Prj_Det_05: Validate that IPPU user cannot see Project details section, due to authorization
  Scenario Outline: Prj_Det_05: Validate that IPPU user cannot see Project details section, due to authorization
    Given Open "<Browser>" and Launch application URL
    And Login with Credentials "<UserType>"
    And Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Open project and verify name "<CaseMatterNo>"
    Then IPPU user should no be able to see Project Information section
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | Browser | UserType |
      | Patentability   | Biotechnology   | Prj_Det_05   | Chrome  | IPPU     |

