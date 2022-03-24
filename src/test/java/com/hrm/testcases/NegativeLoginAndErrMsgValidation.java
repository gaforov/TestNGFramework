package com.hrm.testcases;

import com.hrm.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.hrm.base.PageInitializer.loginPage;

public class NegativeLoginAndErrMsgValidation extends BaseClass {

    @Test(dataProvider = "getData")
    void testNegativeLogins(String user, String password, String expectedErrorMessage ) {
        loginPage.loginAndClick(user,password);
        Assert.assertEquals(loginPage.errorMessage.getText(),expectedErrorMessage);
    }


    @DataProvider
    Object[][] getData(){
        Object[][] data = {
                {"Admin", "admin1234", "Invalid credentials"},  // valid user, invalid password
                {"Admi", "admin123", "Invalid credentials"},    // invalid user, valid password
                {"Admi", "admin1234", "Invalid credentials"},   // invalid user, invalid password
                {"", "admin1234", "Username cannot be empty"},  // empty user, valid password
                {"Admin", "", "Password cannot be empty"},      // valid user, empty password
                {"", "", "Username cannot be empty"},           // empty user, empty password
        };
        return data;
    }

}
