package com.hrm.pages;

import com.hrm.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PersonalDetailsPageElements {

    @FindBy(id = "personal_cmbNation")
    public WebElement nationalityDD;

    @FindBy(name = "personal[optGender]")
    public List<WebElement> genderRadioGroup;

    @FindBy(css = "div#pdMainContainer h1")
    public WebElement personalDetailsHeaderText;

    @FindBy(id = "personal_txtEmployeeId")
    public WebElement employeeId;

    public PersonalDetailsPageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }
}
