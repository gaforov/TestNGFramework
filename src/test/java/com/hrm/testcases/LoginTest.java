package com.hrm.testcases;

import com.hrm.base.BaseClass;
import com.hrm.utils.ConfigsReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.hrm.base.PageInitializer.*;
import static com.hrm.utils.CommonMethods.sendText;

public class LoginTest extends BaseClass {  //You either extend them (not recommended if no inheritance present), could statically important them.
//    public static LoginPageElements login = new LoginPageElements(); not working, why? it needs to be inside startBrowser, after setUp maybe?? Yes.

    @Test(groups = "smoke")
    public void testLoginAdminUser() {
        sendText(loginPage.username, ConfigsReader.getProperty("username"));
        sendText(loginPage.password, ConfigsReader.getProperty("password"));
        loginPage.loginButton.click();
        String expectedUser = "Welcome Admin"; // OrangeHRM=Paul, SyntaxHRM=Admin
        String actualUser = dashboardPage.welcome.getText();
        Assert.assertEquals(actualUser, expectedUser, "Logged in user is NOT Admin");
        System.out.println("Admin login is successful"); // this message only runs if Assertion doesn't fail.
    }

    @Test(groups = {"regression", "smoke"})
    public void testLoginInvalidPassword() {
        //var login = new LoginPageElements();
        String invalidPassword = "abc123";
        sendText(loginPage.username, ConfigsReader.getProperty("username"));
        sendText(loginPage.password, invalidPassword);
        loginPage.loginButton.click();
        String expectedErrorMsg = "Invalid credentials";
        String actualErrorMsg = loginPage.errorMessage.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not correct.");
        //Assert.assertTrue(actualErrorMsg.equals("Invalid credentials"), "Error message is not correct"); // Alternative assertion
    }

    @Test(groups = {"regression", "smoke"})
    public void testLoginEmptyUsername() {
        sendText(loginPage.password, ConfigsReader.getProperty("password"));
        loginPage.loginButton.click();
        String expectedErrorMsg = "Username cannot be empty";
        String actualErrorMsg = loginPage.errorMessage.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is incorrect.");
    }
}
