Feature: Request Quote

# Test scenario: User is login into application using search team admin credentials
  Scenario Outline: Home page default login
    Given Open "<Browser>" and Launch application URL
    When Login with Credentials "<UserType>"
    Then Home page is populated
    Examples:
      | Browser | UserType |
      | Chrome  | STM      |


# RQ_01_A_Validate Fields on Request Quote Page1 Of Search application
  Scenario Outline: RQ_01_A_Validate Fields on Request Quote Page1 Of Search application
    Given Click "REQUEST_QUOTE_From_Left_Navigation"
    When Heading of page will be "Required information"
    Then "<Fields>" are available on page 1
    Examples:
      | Fields                                                                                                                               |
      | Type of project, Technical field, Case/Matter, Client name, Project title fields and Discard Draft, Close, Previous and Next buttons |


# RQ_01_B_Validate Fields on Request Quote Page2 Of Search application
  Scenario Outline: RQ_01_B_Validate Fields on Request Quote Page2 Of Search application
    Given Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "One" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Click "next_projectCreate"
    Then "<Fields>" are available on page 2
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo                       | Fields                                                                                                                                                                      |
      | Patentability   | Biotechnology   | Testing_project_Created_Automation | Description of concept to be searched, List known prior art, Budget, Other information or comments, upload files fields and Discard Draft, Close, Previous and Next buttons |

#RQ_02_A_Validate Request Quote Discard draft Page 1 functionality for Search application
  Scenario Outline: RQ_02_A_Validate Request Quote Discard draft Page 1 functionality for Search application
    Given Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "One" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Click "Discard_Button"
    Then Project is "not" created with name "<CaseMatterNo>"
    And Request Quote will have Type_Of_Project= "Select project type", Technical Field = "Select technical field",and Case/Matter = ""
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo                       |
      | Patentability   | Biotechnology   | Testing_project_Created_Automation |

#RQ_02_B_Validate Request Quote Discard draft Page 2 functionality for Search application
  Scenario Outline: RQ_02_B_Validate Request Quote Discard draft Page 2 functionality for Search application
    Given Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "One" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Click "next_projectCreate"
    And Click "Discard_Draft"
    Then Project is "not" created with name "<CaseMatterNo>"
    And Request Quote will have Type_Of_Project= "Select project type", Technical Field = "Select technical field",and Case/Matter = ""
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo                       |
      | Patentability   | Biotechnology   | Testing_project_Created_Automation |

#RQ_03_Validate Functionality of " Close Draft" on "Request Quote"
  Scenario Outline: RQ_03_A_Validate Functionality of " Close Draft" on "Request Quote"
    Given Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "One" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Click "Close"
    Then Project is "not" created with name "<CaseMatterNo>"
    And Request Quote will have Type_Of_Project= "<Type_Of_Project>", Technical Field = "<Technical_Field>",and Case/Matter = "<CaseMatterNo>"
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo                       |
      | Patentability   | Biotechnology   | Testing_project_Created_Automation |

#RQ_03_B_Validate Functionality of " Close Draft" on "Request Quote"
  Scenario Outline: RQ_03_B_Validate Functionality of " Close Draft" on "Request Quote"
    Given Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "One" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Click "next_projectCreate"
    And Click "Close"
    Then Project is "not" created with name "<CaseMatterNo>"
    And Request Quote will have Type_Of_Project= "<Type_Of_Project>", Technical Field = "<Technical_Field>",and Case/Matter = "<CaseMatterNo>"
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo                       |
      | Patentability   | Biotechnology   | Testing_project_Created_Automation |


# RQ_04_Validate Mandatory fileds in Request Quote
  Scenario Outline: RQ_04_Validate Mandatory fileds in Request Quote
    Given Click "REQUEST_QUOTE_From_Left_Navigation"
    When  User filled all mandatory fields on page "One" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    And Click "next_projectCreate"
    Then Validate Error message for "<Error_Message>"
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo                       | Error_Message               |
      |                 | Biotechnology   | Testing_project_Created_Automation | Project type is required    |
      | Patentability   |                 | Testing_project_Created_Automation | Technical field is required |
      | Patentability   | Biotechnology   |                                    | Case/Matter # is required   |


#RQ_05_Validate successful project creation on "Request Quote"
  @SmokeTest
  Scenario Outline: RQ_05_Validate successful project creation on "Request Quote"
    Given Click "REQUEST_QUOTE_From_Left_Navigation"
    When User filled all mandatory fields on page "both" with "<Type_Of_Project>", "<Technical_Field>", "<CaseMatterNo>"
    Then Project is "successfully" created with name "<CaseMatterNo>"
    Examples:
      | Type_Of_Project | Technical_Field | CaseMatterNo                 |
      | Patentability   | Biotechnology   | Automation_RQ_S_Prj_Creation |

#RQ_06_Notification check after successful creation
  Scenario Outline: RQ_06_Notification check after successful creation
    Given User clicked on logout
    And Click Close Browser
    And Open "<Browser>" and Launch application URL
    When Login with Credentials "<UserType>"
    Then Notification message for "<CaseMatterNo>" will be available on notification screen
    Examples:
      | Browser | UserType | CaseMatterNo                 |
      | Chrome  | IPPU     | Automation_RQ_S_Prj_Creation |
	
# Post execution steps
  Scenario: Logged out
    Given User is already logged into application with Search team credentials
    When Click Close Browser
    Then Browser is closed

	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	