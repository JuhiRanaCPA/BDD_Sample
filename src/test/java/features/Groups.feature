Feature: Validate GROUPS Functionality on Search Application

# Test scenario: User is login into application using search team admin credentials
  @SmokeTest
  Scenario Outline: Home page default login
    Given Open "<Browser>" and Launch application URL
    When Login with Credentials "<UserType>"
    Then Home page is populated
    Examples:
      | Browser | UserType |
      | Chrome  | STA      |

#TC_01_Groups: Validate Title is a mandatory field
  Scenario Outline: TC_01_Groups: Validate Title is a mandatory field
    Given Click "GROUPS_From_Left_Navigation"
    And Click "Create_Button_Top_Right_Of_Groups"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    When Click "Create_Groups_Create_Button"
    Then "Title is required" error should be populated on "Error_Message_On_Users_Page"
    Examples:
      | User_Name        |
      | automation admin |

#TC_02_Groups: Validate At the time of create group, if user click on cancel, then group should not be created
  Scenario Outline: TC_02_Groups: Validate At the time of create group, if user click on cancel, then group should not be created
    Given Click "PROJECTS_From_Left_Navigation"
    And Click "GROUPS_From_Left_Navigation"
    And Click "Create_Button_Top_Right_Of_Groups"
    And Enter value "<Group_Name_Value>" in "Title_Text_Box"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    When Click "Create_Groups_Cancel_Button"
    Then Group "Randomly_Created_Group" is not created with name "<Group_Name_Value>"
    Examples:
      | Group_Name_Value                   | User_Name        |
      | Randomly Created Group_TC_02_Group | automation admin |

#TC_03_Groups: Validate successful group creation
  @SmokeTest
  Scenario Outline: TC_03_Groups: Validate successful group creation
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
    Examples:
      | Group_Name_Value                  | User_Name        |
      | Randomly_Created_GroupTC_03_Group | automation admin |

#TC_04_Groups: Validate search team should be able to delete group
  Scenario Outline: TC_04_Groups: Validate search team should be able to delete group
    Given Click "PROJECTS_From_Left_Navigation"
    And Click "GROUPS_From_Left_Navigation"
    And Click "Create_Button_Top_Right_Of_Groups"
    And Enter value "<Group_Name_Value>" in "Title_Text_Box"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    And Click "Create_Groups_Create_Button"
    And Click "Delete_Group_Button"
    When Click "Confirm_Delete_OK_Button"
    Then Created Group "Randomly_Created_Group" is deleted sucessfully
    Examples:
      | Group_Name_Value                   | User_Name        |
      | Randomly Created Group_TC_04_Group | automation admin |

#TC_05_Groups: Search team should be able to delete  users from the list of added user
  Scenario Outline: TC_05_Groups: Search team should be able to delete  users from the list of added user
    Given Click "PROJECTS_From_Left_Navigation"
    And Click "GROUPS_From_Left_Navigation"
    And Click "Create_Button_Top_Right_Of_Groups"
    And Enter value "<Group_Name_Value>" in "Title_Text_Box"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    And Click "Create_Groups_Create_Button"
    And Open group "<Group_Name_Value>"
    And Click "Delete_Added_User"
    And Click "Edit_Groups_Save_Button"
    When Open group "<Group_Name_Value>"
    Then No user will be available in the list
    And Click "Edit_Groups_Cancel_Button"
    And Now delete created group "<Group_Name_Value>"
    Examples:
      | Group_Name_Value                   | User_Name        |
      | Randomly Created Group_TC_05_Group | automation admin |

#TC_06_Groups : Search team can re- add a user who was deleted previously
  Scenario Outline: TC_06_Groups : Search team can re- add a user who was deleted previously
    Given Click "PROJECTS_From_Left_Navigation"
    And Click "GROUPS_From_Left_Navigation"
    And Click "Create_Button_Top_Right_Of_Groups"
    And Enter value "<Group_Name_Value>" in "Title_Text_Box"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    And Click "Create_Groups_Create_Button"
    And Open group "<Group_Name_Value>"
    And Click "Delete_Added_User"
    And Click "Edit_Groups_Save_Button"
    And Open group "<Group_Name_Value>"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    And Click "Edit_Groups_Save_Button"
    And Open group "<Group_Name_Value>"
    When Group is opened
    Then User will be available in the list
    And Click "Edit_Groups_Cancel_Button"
    And Now delete created group "<Group_Name_Value>"
    Examples:
      | Group_Name_Value                   | User_Name        |
      | Randomly Created Group_TC_06_Group | automation admin |


#TC_07_Groups : If SEARCH team try to add a user which is already part of the group, Then application should throw error
  Scenario Outline: TC_07_Groups : If SEARCH team try to add a user which is already part of the group, Then application should throw error
    Given Click "PROJECTS_From_Left_Navigation"
    And Click "GROUPS_From_Left_Navigation"
    And Click "Create_Button_Top_Right_Of_Groups"
    And Enter value "<Group_Name_Value>" in "Title_Text_Box"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    And Click "Create_Groups_Create_Button"
    And Open group "<Group_Name_Value>"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    Then Validate "<User_Name> is already a member of this group." on "Error_Message"
    And Click "Edit_Groups_Cancel_Button"
    And Now delete created group "<Group_Name_Value>"
    Examples:
      | Group_Name_Value                   | User_Name        |
      | Randomly Created Group_TC_07_Group | automation admin |

 # TC_08_Groups : Search team should be able to rename a group
  Scenario Outline: TC_08_Groups : Search team should be able to rename a group
    Given Click "PROJECTS_From_Left_Navigation"
    And Click "GROUPS_From_Left_Navigation"
    And Click "Create_Button_Top_Right_Of_Groups"
    And Enter value "<Group_Name_Value>" in "Title_Text_Box"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    And Click "Create_Groups_Create_Button"
    And Open group "<Group_Name_Value>"
    And Enter value "<Updated_GroupName_Value>" in "Title_Text_Box"
    And Click "Edit_Groups_Save_Button"
    And Open group "<Updated_GroupName_Value>"
    When Group is opened
    Then Validate "Updated Automation Group Name" on "Group_Title"
    And Click "Edit_Groups_Cancel_Button"
    And Now delete created group "<Updated_GroupName_Value>"
    Examples:
      | Group_Name_Value                    | User_Name        | Updated_GroupName_Value       |
      | Randomly Created Group_TC_08_Groups | automation admin | Updated Automation Group Name |

#TC_09_Groups : User should not be deleted as Search team has pressed cancel button
  Scenario Outline: TC_09_Groups : User should not be deleted as Search team has pressed cancel button
    Given Click "PROJECTS_From_Left_Navigation"
    And Click "GROUPS_From_Left_Navigation"
    And Click "Create_Button_Top_Right_Of_Groups"
    And Enter value "<Group_Name_Value>" in "Title_Text_Box"
    And Enter value "<User_Name>" in "Add_User_Text_Box"
    And Click "Searched_User"
    And Click "Add_User_Button"
    And Click "Create_Groups_Create_Button"
    And Open group "<Group_Name_Value>"
    And Click "Delete_Added_User"
    And Click "Edit_Groups_Cancel_Button"
    And Open group "<Group_Name_Value>"
    When Group is opened
    Then User will be available in the list
    And Click "Edit_Groups_Cancel_Button"
    And Now delete created group "<Group_Name_Value>"
    And Click Close Browser
    Examples:
      | Group_Name_Value                   | User_Name        |
      | Randomly Created Group_TC_09_Group | automation admin |



	