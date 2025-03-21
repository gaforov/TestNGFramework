package com.hrm.pages;


import com.hrm.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPageElements {

//    @FindBy(id = "welcome")    // doesn't exist anymore
//    public WebElement welcome;

    @FindBy(xpath = "//h6[text()='Dashboard']")
    public WebElement dashboard;

    @FindBy(css = "div#branding img")
    public WebElement logo;

    public DashboardPageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }
}
