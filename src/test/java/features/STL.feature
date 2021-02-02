Feature: Validate various scenarios w.r.t. Search team Lead

  Background: User is Logged In
    Given Open "Chrome" and Launch application URL


#TC_02, TC_03, TC_04 can add, update and delete a new pre-negotiated project type  from "Prenegotiated"tab
  Scenario Outline: TC_02, TC_03, TC_04 can add, update and delete a new pre-negotiated project type  from "Prenegotiated"tab
    Given Login with Credentials "<UserType>"
    When "Create" Prenegotiated project using "<Organizations>", "<Owned_By>", "<Name>", "<Internal_Description>", "<Contact_Code>", "<BWF_Project_Id>", "<Originating_order_track>", "<Rate>", "<Hours>", "<Budget>", "<Currency>", "<Effective_Date>", "<Expiration_date>", "<Delivery_Option>", "<Name>"
    Then Prenegotiated Project is "created" successfully, and message "Successfully created the prenegotiated project"
    When "Update" Prenegotiated project using "<Organizations>", "<Owned_By>", "<Updated_Name>", "<Internal_Description>", "<Contact_Code>", "<BWF_Project_Id>", "<Originating_order_track>", "<Rate>", "<Hours>", "<Budget>", "<Currency>", "<Effective_Date>", "<Expiration_date>", "<Delivery_Option>", "<Name>"
    Then Prenegotiated Project is "updated" successfully, and message "Successfully updated the prenegotiated project"
    When Delete "prenegotiated Project" "<Updated_Name>"
    Then "PNPProject" "<Name>" is deleted successfully
    And Click Close Browser
    Examples:
      | UserType | Organizations           | Owned_By      | Name                     | Internal_Description              | Contact_Code | BWF_Project_Id | Originating_order_track | Rate | Hours | Budget | Currency            | Effective_Date | Expiration_date | Delivery_Option   | Updated_Name         |
      | STL      | IPC Dev - Goodman & Co. | Jesse Pinkman | TC_02_PRENEGOTIATED_E2E_ | TC_02_PRENEGOTIATED_E2E_Flow_Desc |              |                |                         | 123  | 100   | 90     | CNY - Yuan Renminbi |                |                 | Standard delivery | Updated_TC_02_PreNeg |


 #TC_05, TC_06 STL can cancel and delete a project from STM project list
  Scenario Outline: TC_05, TC_06 STL can cancel and delete a project from STM project list
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    When Cancel the project
    Then "Project" "<CaseMatterNo>" is cancelled successfully
    When Delete "Project" "<CaseMatterNo>"
    Then "Project" "<CaseMatterNo>" is deleted successfully
    And Click Close Browser
    Examples:
      | UserType | Type_Of_Project | Technical_Field | CaseMatterNo |
      | STL      | Patentability   | Biotechnology   | TC_5_6_STL   |

  #TC_07, TC_08_Validate that when STL update or delete a prenegotiated project name, project created using that renegotiated value should not be updated.
  Scenario Outline: TC_07, TC_08_Validate that when STL update or delete a prenegotiated project name, project created using that renegotiated value should not be updated.
    Given Login with Credentials "<UserType>"
    And "Create" Prenegotiated project using "<Organizations>", "<Owned_By>", "<Name>", "<Internal_Description>", "<Contact_Code>", "<BWF_Project_Id>", "<Originating_order_track>", "<Rate>", "<Hours>", "<Budget>", "<Currency>", "<Effective_Date>", "<Expiration_date>", "<Delivery_Option>", "<Name>"
    And User filled all mandatory fields on page "both" with "<Name>", "<Technical_Field>", "<CaseMatterNo>"
    When "Update" Prenegotiated project using "<Organizations>", "<Owned_By>", "<Updated_Name>", "<Internal_Description>", "<Contact_Code>", "<BWF_Project_Id>", "<Originating_order_track>", "<Rate>", "<Hours>", "<Budget>", "<Currency>", "<Effective_Date>", "<Expiration_date>", "<Delivery_Option>", "<Name>"
    Then Prenegotiated Project name should should be "<Name>, <Internal_Description>"
    When Delete "prenegotiated Project" "<Updated_Name>"
    Then Prenegotiated Project name should should be "<Name>, <Internal_Description>"
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | UserType | Organizations           | Owned_By      | Name                     | Internal_Description              | Contact_Code | BWF_Project_Id | Originating_order_track | Rate | Hours | Budget | Currency            | Effective_Date | Expiration_date | Delivery_Option   | Technical_Field | CaseMatterNo | Updated_Name                     |
      | STL      | IPC Dev - Goodman & Co. | Jesse Pinkman | TC_07_PRENEGOTIATED_E2E_ | TC_07_PRENEGOTIATED_E2E_Flow_Desc |              |                |                         | 123  | 100   | 90     | CNY - Yuan Renminbi |                |                 | Standard delivery | Biotechnology   | TC_7_STL     | Updated_TC_07_PRENEGOTIATED_E2E_ |

#TC_09_Validate import references functionality in a project
  Scenario Outline: TC_09_Validate import references functionality in a project
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    And Add features "<Features:RelatedClaim>"
    When Import patent "<References>" and verify "<Reference_Messages>"
    Then Reference "<Added_References>" should be successfully added in the project
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | Features:RelatedClaim                   | References              | Reference_Messages | Added_References    |
      | Patentability   | Biotechnology   | TC_09_STL    | STL      | Feature1:123,Feature2:787,Feature3:7898 | US3525361 US3525362 ABC | A cannot be found. | US3525361 US3525362 |
#      | Patentability   | Biotechnology   | TC_09_STL    | STL      | Feature1:123,Feature2:787,Feature3:7898 | US3525361 US3525362 ABC | A cannot be found. | US3525361 US3525362 |
#      | Patentability   | Biotechnology   | TC_09_STL    | STM      | Feature1:123,Feature2:787,Feature3:7898 | US3525361 US3525362 ABC | A cannot be found. | US3525361 US3525362 |
#      | Patentability   | Biotechnology   | TC_09_STL    | IPPU     | Feature1:123,Feature2:787,Feature3:7898 | US3525361 US3525362 ABC | A cannot be found. | US3525361 US3525362 |

  #TC_10_Validate add reference (manually) functionality in a project
  Scenario Outline: TC_10_Validate add reference manually functionality in a project
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    When Add patent manually with details "<Ref_Title>", "<Author>", "<General_Comment>", "<Publication_Date>", "<Link>", "<Citations>", "<Source>", "<Abstract>", "<DOI>", "<Keywords>", "<Full_text>"
    Then Reference "<Ref_Title>" should be successfully added in the project
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | Ref_Title        | Author        | General_Comment | Publication_Date | Link           | Citations | Source | Abstract        | DOI | Keywords | Full_text |
      | Patentability   | Biotechnology   | TC_10_STL    | STL      | Manual Ref Title | Jesse PinkMan | General_Comment | 2020-03-18       | www.google.com | Citations | Source | Manual Abstract | DOI | Keywords | Full_text |

  #TC_11_Validate Upload Patent functionality in a project
  Scenario Outline: TC_11_Validate Upload Patent functionality in a project
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    When Upload patent with details "<Ref_Title>", "<Publication_Number>", "<Kind_Code>", "<Inventor>", "<Abstract>"
    Then Reference "<Ref_Title>" should be successfully added in the project
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | Ref_Title        | Abstract        | Publication_Number | Kind_Code | Inventor        |
      | Patentability   | Biotechnology   | TC_11_STL    | STL      | Manual Ref Title | Manual Abstract | 1228337            | USSy1233  | Sample Inventor |



#TC_12, STL13, TC_14 _Validate STL can add, edit and delete annotation to a project
  Scenario Outline: TC_12, STL13, TC_14 _Validate STL can add, edit and delete annotation to a project
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    And Add features "<Features:RelatedClaim>"
    And Import patent "<References>" and verify "<Reference_Messages>"
    When Reference "<Added_References>" should be successfully added in the project
    Then add "<Added_References>" in a reference "<CitationData>"
    When Edit added annotation with update value "<UpdatedCitationData>"
    Then Annotation is updated successfully with update value "<UpdatedCitationData>"
    When Delete added annotation
    Then annotation is deleted successfully
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | Features:RelatedClaim                   | References | Reference_Messages | Added_References | CitationData                            | UpdatedCitationData                                    |
      | Patentability   | Biotechnology   | TC_12_STL    | STL      | Feature1:123,Feature2:787,Feature3:7898 | US6000000  |                    | US6000000        | Line and column:Line data:Column data11 | Line and column:updated Line data:updatedColumn data11 |
#      | Patentability   | Biotechnology   | TC_12_STL    | STL      | Feature1:123,Feature2:787,Feature3:7898 | US6000000  |                    | US6000000        | Line and column:Line data:Column data11 | Line and column:updated Line data:updatedColumn data11 |
#      | Patentability   | Biotechnology   | TC_12_STM   | STM      | Feature1:123,Feature2:787,Feature3:7898 | US6000000  |                    | US6000000        | Line and column:Line data:Column data11 | Line and column:updated Line data:updatedColumn data11 |
#      | Patentability   | Biotechnology   | TC_12_IPPU    | IPPU     | Feature1:123,Feature2:787,Feature3:7898 | US6000000  |                    | US6000000        | Line and column:Line data:Column data11 | Line and column:updated Line data:updatedColumn data11 |


#TC_16_Validate STL can import features
  Scenario Outline: TC_16_Validate STL can import features
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    When Import features from "<CSV_File_Path>"
    Then Features "<Features:RelatedClaim>" are "added" successfully
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | Features:RelatedClaim         | CSV_File_Path                 |
      | Patentability   | Biotechnology   | TC_16_STL    | STL      | Feature_1,Feature_2,Feature_3 | C:\GIT\ip-search\Features.csv |


    #TC_15, TC_17, TC_18, TC_19 Validate STL can add, update, reorder and delete features
  Scenario Outline: TC_15, TC_17, TC_18, TC_19 Validate STL can add, update, reorder and delete features
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    When Add features "<Features:RelatedClaim>"
    Then Features "<Features:RelatedClaim>" are "added" successfully
    And Click "Features_Edit_Link"
    And Wait spinners to disappear
    And Click "Features_ReOrder_Down_Arrow"
    When Click "Feature_Save"
    Then Features "<ReOrderFeatures:RelatedClaim>" are "Re-Order" successfully
    When Edit features "<UpdatedFeatures:RelatedClaim>"
    Then Features "<UpdatedFeatures:RelatedClaim>" are "Update" successfully
    When Delete features "<UpdatedFeatures:RelatedClaim>"
    Then "<UpdatedFeatures:RelatedClaim>" is deleted successfully with message "No feature has been added"
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo                       | UserType | Features:RelatedClaim                   | UpdatedFeatures:RelatedClaim                               | ReOrderFeatures:RelatedClaim            |
      | Patentability   | Biotechnology   | Functional_Automation_Feature_Test | STL      | Feature1:123,Feature2:787,Feature3:7898 | UpdatedFeature1:123,UpdateFeature2:787,UpdateFeature3:7898 | Feature2:787,Feature1:123,Feature3:7898 |



#STL_20_21 Validate STL can upload interim and Final report to a projects
  Scenario Outline: STL_20_21 Validate STL can upload interim and Final report to a projects
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    When Add Report "<Report>" with name "<ReportName>"
    Then Report is added with name "<ReportName> - <Report>"
    Then Publish project
    Then Status of Project is "<ProjectStatus>"
    Then Latest Document contain "<ReportType>"
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo      | UserType | ReportName         | ReportType     | ProjectStatus      | Report  |
      | Patentability   | Biotechnology   | TC_20_STL_Interim | STL      | new Interim Report | Interim report | Search in progress | Interim |
      | Patentability   | Biotechnology   | TC_20_STL_Final   | STL      | new Interim Report | Final report   | Report available   | Final   |

    #STL_25 and STL 26_Validate re-order and sorting results for STL
  Scenario Outline:STL_25 and STL 26_Validate re-order and sorting results for STL
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    And Add features "<Features:RelatedClaim>"
    And Import patent "<References>" and verify "<Reference_Messages>"
    When ReOrder references in format "Down"
    Then References reOrdered successfully "<ReOrdered_References>"
    When ReOrder references in format "Up"
    Then References reOrdered successfully "<ReOrdered_References2>"
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | Features:RelatedClaim                   | References              | Reference_Messages | ReOrdered_References | ReOrdered_References2 |
      | Patentability   | Biotechnology   | TC_25_STL    | STL      | Feature1:123,Feature2:787,Feature3:7898 | US3525361 US3525362 ABC | A cannot be found. | US3525362,US3525361  | US3525361,US3525362   |

  #STL_23, STL_24, and STL_27 STL can add keyword, share a reference in a project also add category to a references should not be available for STL in a project
  Scenario Outline: STL_23, STL_24, and STL_27 STL can add keyword, share a reference in a project also add category to a references should not be available for STL in a project
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    And Add features "<Features:RelatedClaim>"
    And Import patent "<References>" and verify "<Reference_Messages>"
    When Reference "<Added_References>" should be successfully added in the project
    Then Add Category should not be available for "<UserType>"
    When Add Keywords "<Keywords>" in a reference "<References>"
    Then "<Keywords>" successfully added
    When Share reference with "<user>"
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo    | UserType | Features:RelatedClaim                   | References | Reference_Messages | Added_References | Keywords   | user                            |
      | Patentability   | Biotechnology   | TC_23_24_27_STL | STL      | Feature1:123,Feature2:787,Feature3:7898 | US6000000  |                    | US6000000        | keywordOne | wilmer.yates@nomail.example.com |


#STL_28_STL can add/update Introduction to a project
  Scenario Outline: STL_28_STL can add/update Introduction to a project
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    When "Add" Introduction "<Introduction>"
    Then "<Introduction>" is "Added" successfully
    When "Update" Introduction "<UpdatedIntroduction>"
    Then "<UpdatedIntroduction>" is "Updated" successfully
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | Introduction                | UpdatedIntroduction                             |
      | Patentability   | Biotechnology   | TC_28_STL    | STL      | TC_28_STL_ New Introduction | TC_28_STL_ New Introduction_updated sucessfully |


#STL_29_31_32_Validate STL can add, Update and delete Search history line by line
  Scenario Outline: STL_29_31_32_Validate STL can add, update and delete Search history line by line
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    When "Add" Search History "<SearchHistory_Category>" and entry "<Ref>", "<Query>", "<Result>", "<Reviewed>"
    Then "<SearchHistory_Category>" is "Added" successfully with details  "<Ref>", "<Query>", "<Result>", "<Reviewed>"
    When "Update" Search History "<SearchHistory_Category>" and entry "<Updated_Ref>", "<Updated_Query>", "<Updated_Result>", "<Updated_Reviewed>"
    Then "<SearchHistory_Category>" is "Updated" successfully with details  "<Updated_Ref>", "<Updated_Query>", "<Updated_Result>", "<Updated_Reviewed>"
    When Delete "Search_History_Delete_Entry"
#    Then "<Search_History_Delete_Entry>" is deleted successfully with message "No Search History has been added"
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | SearchHistory_Category          | Ref | Query                                         | Result | Reviewed | Updated_Ref | Updated_Query                                         | Updated_Result | Updated_Reviewed |
      | Patentability   | Biotechnology   | TC_28_STL    | STL      | Robotics and Automation Society | 1   | new Query for Robotics and Automation Society | 100    | Yes      | 10          | Updated new Query for Robotics and Automation Society | 8999           | no               |

#STL_33_Validate STL can re-order Search history
  Scenario Outline: STL_33_Validate STL can re-order Search history
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    And "Add" Search History "<SearchHistory_Category>" and entry "<Ref>", "<Query>", "<Result>", "<Reviewed>"
    And "Add" Search History "<SearchHistory_Category2>" and entry "<Ref2>", "<Query2>", "<Result2>", "<Reviewed2>"
    And Verify first search history should be "<SearchHistory_Category>" and second search history should be "<SearchHistory_Category2>"
    When ReOrder Search History "Move Down"
    Then Verify first search history should be "<SearchHistory_Category2>" and second search history should be "<SearchHistory_Category>"
    When ReOrder Search History "Move Up"
    And Verify first search history should be "<SearchHistory_Category>" and second search history should be "<SearchHistory_Category2>"
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | SearchHistory_Category          | Ref | Query                                         | Result | Reviewed | SearchHistory_Category2 | Ref2 | Query2                            | Result2 | Reviewed2 |
      | Patentability   | Biotechnology   | TC_33_STL    | STL      | Robotics and Automation Society | 1   | new Query for Robotics and Automation Society | 100    | Yes      | Engineering Village     | 100  | new Query for Engineering Village | 900     | Yes       |


  #STL_36, 37 and 38 STL can add, update and delete proposal to a project
  Scenario Outline: STL_36, 37 and 38 STL can add, update and delete proposal to a project
    Given Login with Credentials "<UserType>"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And Open created Project
    When "Add" proposal with "<ProposalTitle>", "<Summary>", "<Currency>"
    Then Proposal is "added" successfully with  "<ProposalTitle>", "<Summary>"
    When "Update" proposal with "<ProposalTitle>", "<Summary>", "<Currency>"
    Then Proposal is "updated" successfully with  "<ProposalTitle>", "<Summary>"
    When Delete "Proposal" "<ProposalTitle>"
    Then "<ProposalTitle>" is deleted successfully with message "No proposal has been added"
    When "Add" proposal with "<ProposalTitle>", "<Summary>", "<Currency>"
    Then Proposal is "added" successfully with  "<ProposalTitle>", "<Summary>"
    When Publish project
    Then "Proposal" is published
    When "Decline" The proposal
    Then Proposal is "Declined"
    When "Add" proposal with "<ProposalTitle>", "<Summary>", "<Currency>"
    Then Proposal is "added" successfully with  "<ProposalTitle>", "<Summary>"
    When Publish project
    When "Accept" The proposal
    Then Proposal is "Accepted"
    And Delete "Project" "<CaseMatterNo>"
    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo | UserType | ProposalTitle | Summary             | Currency   |
      | Patentability   | Biotechnology   | TC_36_STL    | STL      | Proposal One  | proposalOne summary | EUR - Euro |

  #SEARCH-2686 Enable Customers to Modify their own projects
  Scenario Outline: SEARCH-2686 Enable Customers to Modify their own projects
    Given Login with Credentials "<UserType>"
    And Click "SETTINGS_From_Left_Navigation"
    And "Enable" "IPC Dev - Goodman & Co." from SETTINGS Page
    And User clicked on logout
    And Login with Credentials "IPPU"
    And User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Project is "successfully" created with name "<CaseMatterNo>"
    And User clicked on logout
    And Login with Credentials "STL"
    And Open created Project
    When "Add" proposal with "<ProposalTitle>", "<Summary>", "<Currency>"
    Then Proposal is "added" successfully with  "<ProposalTitle>", "<Summary>"
    When Add features "<Features:RelatedClaim>"
    Then Features "<Features:RelatedClaim>" are "added" successfully
    When "Add" Search History "<SearchHistory_Category>" and entry "<Ref>", "<Query>", "<Result>", "<Reviewed>"
    Then "<SearchHistory_Category>" is "Added" successfully with details  "<Ref>", "<Query>", "<Result>", "<Reviewed>"
    When "Add" Introduction "<Introduction>"
    Then "<Introduction>" is "Added" successfully
    When Add Report "<Report>" with name "<ReportName>"
    Then Report is added with name "<ReportName> - <Report>"
    When Import patent "<References>" and verify "<Reference_Messages>"
    Then Reference "<Added_References>" should be successfully added in the project
    And Publish project with "team"
    And Status of Project is "<ProjectStatus>"
    And Latest Document contain "<ReportType>"
    And User clicked on logout
    And Login with Credentials "IPPU"
    And Status of Project is "<ProjectStatus>"
    And Latest Document contain "<ReportType>"
    When Open created Project
    Then Create Copy Button "is" available on the page
    When Create a Copy with "CPA_Records_Checkbox" option unchecked
    Then Project is "successfully" created with name "<CaseMatterNo> - Copy 1"
    When Open "<CaseMatterNo> - Copy 1" project
    Then "<CaseMatterNo>" project is linked under Is Related section
    Then "Proposal,Teams" Tab is hide in the copied project
#    Then "ResultTab" is visible in the copied project
    And Navigate to "PROJECTS_From_Left_Navigation"
    And Open "<CaseMatterNo>" project
    When Create a Copy with "Hide_Proposal" option unchecked
    Then Project is "successfully" created with name "<CaseMatterNo> - Copy 2"
    When Open "<CaseMatterNo> - Copy 2" project
    Then "<CaseMatterNo>" project is linked under Is Related section
    Then "Proposal,Teams" Tab is hide in the copied project



    And Click Close Browser
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo        | UserType | ProposalTitle | Summary             | Introduction             | Currency   | Features:RelatedClaim                   | References              | Reference_Messages | Added_References    | SearchHistory_Category          | Ref | Query                                         | Result | Reviewed | ReportName         | ReportType   | ProjectStatus    | Report |
      | Patentability   | Biotechnology   | SEARCH_2686_Project | STL      | Proposal One  | proposalOne summary | SEARCH_2686_Introduction | EUR - Euro | Feature1:123,Feature2:787,Feature3:7898 | US3525361 US3525362 ABC | A cannot be found. | US3525361 US3525362 | Robotics and Automation Society | 1   | new Query for Robotics and Automation Society | 100    | Yes      | new Interim Report | Final report | Report available | Final  |








