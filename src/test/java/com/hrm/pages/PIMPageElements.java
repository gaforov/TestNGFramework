package com.hrm.pages;

import com.hrm.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.hrm.utils.CommonMethods.click_waitForClickability;

public class PIMPageElements {
    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement PIM;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmployee;

    public PIMPageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }

    public void navigateToAddEmployee() {
        click_waitForClickability(PIM);
        //jsClick(PIM); // if regular(HTML) click doesn't work, use JS click.
        click_waitForClickability(addEmployee);
    }
}