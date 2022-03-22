package com.hrm.testcases;
/*
TC 1: HRMS Add Employee:
1. Open Chrome browser, 2. Go to Syntax HRMS website, 3. Login into the application
4. Add 5 different Employees and create login credentials by providing:
First Name, Last Name, Username, Password
1. Provide Employee First and Last Name
2. Verify Employee has been added successfully and take screenshot
(you must have 5 different screenshots)
3. Close the browser
Specify group for this test case, add it into specific suite and execute from xml file.
 */

import com.hrm.base.BaseClass;
import com.hrm.utils.ConfigsReader;
import com.hrm.utils.Constants;
import com.hrm.utils.ExcelUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.hrm.base.PageInitializer.*;
import static com.hrm.utils.CommonMethods.*;

public class AddEmployeeFromExcelTest extends BaseClass {
    // Note: Use Orange HRM for this test. If you use Syntax HRM, Employee IDs will not match, due to syntax site bug, not the code.
    /**
     * This @Test method will add employee(s) from given spreadsheet/excel using DataProvider annotation of TestNG.
     */
    @Test(dataProvider = "userDataFromExcel", groups = {"homework", "addEmp", "regression"})
    public void userDataFromExcel(String firstname, String lastname, String username, String password) {
        // login to HRMS
        loginPage.loginAndClick(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
        // Navigate to PIM --> Add Employee
        pimPage.navigateToAddEmployee();
        waitInSeconds(1);
        // Add employee information
        sendText(addEmployeePage.firstName, firstname);
        sendText(addEmployeePage.lastName, lastname);
        // Get employee ID - needed for later verification/validation.
        String createdEmployeeId = addEmployeePage.employeeId.getAttribute("value"); // getText() didn't work.
        System.out.println("Newly-created Employee ID = " + createdEmployeeId);
        addEmployeePage.createCredentialsCheckbox.click();
        waitInSeconds(1);
        // Credentials section appears after click, fill this section then click save button.
        sendText(addEmployeePage.username, username);
        sendText(addEmployeePage.password, password);
        sendText(addEmployeePage.confirmPassword, password);
        addEmployeePage.saveButton.click();
        //Verify if employee is successfully added
        waitForVisibility(personalDetailsPage.personalDetailsHeaderText);
        String actualEmployeeId = personalDetailsPage.employeeId.getAttribute("value");
        Assert.assertEquals(actualEmployeeId, createdEmployeeId, "Employee IDs don't match");
        // Take screenshot of entire Employee Details Page
        WebElement employeeDetailsPage = driver.findElement(By.id("employee-details"));
//        File sourceFile = employeeDetailsPage.getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(sourceFile, new File("screenshots/HRMS/AddedEmployee.png"));
        //Take screenshot using pre-built function from CommonMethods class:
        takeScreenshot(employeeDetailsPage, "AddedEmployee" + firstname + "_" + lastname);

    }

    @DataProvider(name = "userData")
    public Object[][] getData() {
        return new Object[][]{
//                {"John", "Smith", "Admin", "Hum@nhrm123"},
//                {"Mike", "Tyson", "Syntax", "Syntax123!"},
//                {"Jackie", "Chan", "jchan", "jChan123!"},
//                {"Jackie1", "Chan1", "jchan1", "jChan123!1"},
                {"Jackie", "Chan", "jchan4", "AmirKhan_@123"},
                // if data is already exists it will not add employee again. Unique identifier is 'username'.

        };
    }

    @DataProvider(name = "userDataFromExcel") // if u want to read from this DataProvider, then change name at @Test to this
    public Object[][] getDataExcel() {
        return ExcelUtility.excelToArray(Constants.TESTDATA_FILEPATH,"Employee");
    }
}
