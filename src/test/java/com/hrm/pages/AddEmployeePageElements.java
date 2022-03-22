package com.hrm.pages;

import com.hrm.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePageElements {
    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(id = "employeeId")
    public WebElement employeeId;

    @FindBy(id = "photofile")
    public WebElement uploadPhoto;

    @FindBy(id = "chkLogin")
    public WebElement createCredentialsCheckbox;

    @FindBy(id = "user_name")
    public WebElement username;

    @FindBy(id = "user_password")
    public WebElement password;

    @FindBy(id = "re_password")
    public WebElement confirmPassword;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    public AddEmployeePageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }
}
