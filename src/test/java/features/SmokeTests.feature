Feature: Smoke Suite

  Background: User is Logged In
    Given Open "Chrome" and Launch application URL

#DEMO_01_Validate successful project creation on "Request Quote" using IPPU and STM
  @SmokeTest
  Scenario Outline: Smoke_1_Validate successful project creation on "Request Quote"
    And Login with Credentials "<UserType>"
    And Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    Then Project is "successfully" created with name "<CaseMatterNo>"
    And User clicked on logout
    And Login with Credentials "STA"
    When Delete "Project" "<CaseMatterNo>"
    Then "Project" "<CaseMatterNo>" is deleted successfully
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType |
      | Patentability   | Biotechnology   | Smoke_IPPU   | IPPU     |
      | Patentability   | Biotechnology   | Smoke_STM    | STM      |

#DEMO_01_Validate successful project creation on "Request Quote" using , STM, STA, STL
  @SmokeTest
  Scenario Outline: Smoke_3_4_Validate successful project creation on "Request Quote"
    And Login with Credentials "<UserType>"
    And Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    Then Project is "successfully" created with name "<CaseMatterNo>"
    When Delete "Project" "<CaseMatterNo>"
    Then "Project" "<CaseMatterNo>" is deleted successfully
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType |
      | Patentability   | Biotechnology   | Smoke_STL    | STL      |
      | Patentability   | Biotechnology   | Smoke_STA    | STA      |


##Smoke_05_GROUPS_E2E_Flow
  @SmokeTest
  Scenario Outline: TC_03_Groups: Validate successful group creation
    And Login with Credentials "STA"
    Given Click "PROJECTS_From_Left_Navigation"
    And Click "GROUPS_From_Left_Navigation"
    And Click "Create_Button_Top_Right_Of_Groups"
    And Enter value "<Group_Name_Value>" in "Title_Text_Box"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    When Click "Create_Groups_Create_Button"
    Then New Group "Randomly_Created_Group" is created with name "<Group_Name_Value>"
    And Now delete created group "<Group_Name_Value>"
    And Click Close Browser
    Examples:
      | Group_Name_Value  | User_Name        |
      | Smoke_TC_03_Group | automation admin |

##Smoke_06_PRENEGOTIATED_E2E_Flow
  @SmokeTest
  Scenario Outline: Smoke_06_PRENEGOTIATED_E2E_Flow
    Given Login with Credentials "<UserType>"
    When "Create" Prenegotiated project using "<Organizations>", "<Owned_By>", "<Name>", "<Internal_Description>", "<Contact_Code>", "<BWF_Project_Id>", "<Originating_order_track>", "<Rate>", "<Hours>", "<Budget>", "<Currency>", "<Effective_Date>", "<Expiration_date>", "<Delivery_Option>", "<Name>"
    Then Prenegotiated Project is "created" successfully, and message "Successfully created the prenegotiated project"
    When "Update" Prenegotiated project using "<Organizations>", "<Owned_By>", "<Updated_Name>", "<Internal_Description>", "<Contact_Code>", "<BWF_Project_Id>", "<Originating_order_track>", "<Rate>", "<Hours>", "<Budget>", "<Currency>", "<Effective_Date>", "<Expiration_date>", "<Delivery_Option>", "<Name>"
    Then Prenegotiated Project is "updated" successfully, and message "Successfully updated the prenegotiated project"
    When Delete "prenegotiated Project" "<Updated_Name>"
    Then "PNPProject" "<Name>" is deleted successfully
    And Click Close Browser
    Examples:
      | UserType | Organizations           | Owned_By      | Name                           | Internal_Description                    | Contact_Code | BWF_Project_Id | Originating_order_track | Rate | Hours | Budget | Currency            | Effective_Date | Expiration_date | Delivery_Option   | Updated_Name               |
      | STA      | IPC Dev - Goodman & Co. | Jesse Pinkman | SMOKE_TC_02_PRENEGOTIATED_E2E_ | SMOKE_wTC_02_PRENEGOTIATED_E2E_Flow_Desc |              |                |                         | 123  | 100   | 90     | CNY - Yuan Renminbi |                |                 | Standard delivery | Updated_SMOKE_TC_02_PreNeg |
