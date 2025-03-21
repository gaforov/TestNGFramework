package com.hrm.pages;

import com.hrm.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.hrm.utils.CommonMethods.*;

public class LoginPageElements {

    @FindBy(name = "username")
    public WebElement username;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(css = "div#divLogo img")
    public WebElement hrmLogo;

    @FindBy(xpath = "//p[text()='Invalid credentials']")
    public WebElement errorMessage;


    @FindBy(xpath = "//span[text()='Required']")
    public WebElement inputFieldRequiredErrorText;


    public LoginPageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }

    /*
     if you want to use encapsulation, you can change access modifier of elements to private then call them from
     another public method as below example or simply auto-generate getters/setters for each private method.
     */
    public void setUsername(String userId) {
        sendText(username, userId);
    }

    public WebElement getUsername() {
        return username;
    }

    public void loginAndClick(String uid, String pswd) {
        sendText(username, uid);
        sendText(password, pswd);
        click_waitForClickability(loginButton);  // extra click removed. Do not add/repeat .click() at the end, it is part of method function.
    }


}
