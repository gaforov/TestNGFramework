package com.hrm.testcases;

import com.hrm.base.BaseClass;
import com.hrm.utils.ConfigsReader;
import org.testng.annotations.Test;

import static com.hrm.base.PageInitializer.*;
import static com.hrm.utils.CommonMethods.*;

public class AddEmployeeTest extends BaseClass {

    /**
     * This @Test method will add employee(s) from the configs package, configuration properties file.
     */
    @Test(groups = "regression")
    public void testAddNewEmployeeFromPropertiesFile() {
        loginPage.loginAndClick(ConfigsReader.getProperty("username"),ConfigsReader.getProperty("password"));
        pimPage.navigateToAddEmployee();
        sendText(addEmployeePage.firstName, ConfigsReader.getProperty("empFirstname"));
        sendText(addEmployeePage.lastName, ConfigsReader.getProperty("empLastname"));
        click_waitForClickability(addEmployeePage.saveButton);
    }

}
