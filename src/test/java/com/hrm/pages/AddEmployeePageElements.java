package com.hrm.pages;

import com.hrm.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePageElements {
    @FindBy(name = "firstName")
    public WebElement firstName;

    @FindBy(name = "lastName")
    public WebElement lastName;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    public WebElement employeeId;

    @FindBy(id = "photofile")
    public WebElement uploadPhoto;

    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
    public WebElement createLoginDetailsCheckBox;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
    public WebElement username;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[4]")
    public WebElement password;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[5]")
    public WebElement confirmPassword;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    public WebElement saveButton;

    public AddEmployeePageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }
}
