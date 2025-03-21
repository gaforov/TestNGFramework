package com.hrm.testcases;

import com.hrm.base.BaseClass;
import com.hrm.utils.Constants;
import com.hrm.utils.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.hrm.base.PageInitializer.loginPage;

public class NegativeLoginAndErrMsgValidation extends BaseClass {

    @Test(dataProvider = "getData")
    void testNegativeLogins(String user, String password, String expectedErrorMessage) {
        loginPage.loginAndClick(user, password);
        if (expectedErrorMessage.equals("Invalid credentials")) {
            Assert.assertEquals(loginPage.errorMessage.getText(), expectedErrorMessage);
        } else if (expectedErrorMessage.equals("Required")) {
            Assert.assertEquals(loginPage.inputFieldRequiredErrorText.getText(), expectedErrorMessage);
        } else {
            Assert.fail("Unexpected error message encountered: " + expectedErrorMessage);
        }
    }


    @DataProvider
    Object[][] getData() {
        Object[][] data = {
                {"Admin", "admin1234", "Invalid credentials"},  // valid user, invalid password
                {"Admi", "admin123", "Invalid credentials"},    // invalid user, valid password
                {"Admi", "admin1234", "Invalid credentials"},   // invalid user, invalid password
                {"", "admin1234", "Required"},  // empty user, valid password
                {"Admin", "", "Required"},      // valid user, empty password
                {"", "", "Required"},           // empty user, empty password
        };
        return data;
    }

    // 2nd way, pulling data from Excel.
    @DataProvider
    public Object[][] getLoginDataFromExcel() {
        return ExcelUtility.excelToArray(Constants.TESTDATA_FILEPATH, "NegativeLogins");
    }
}
