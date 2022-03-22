package com.hrm.base;


import com.hrm.pages.*;

public class PageInitializer {
    public static LoginPageElements loginPage;
    public static DashboardPageElements dashboardPage;
    public static AddEmployeePageElements addEmployeePage;
    public static PersonalDetailsPageElements personalDetailsPage;
    public static PIMPageElements pimPage;

    public static void initialize() {
        loginPage = new LoginPageElements();
        dashboardPage = new DashboardPageElements();
        addEmployeePage = new AddEmployeePageElements();
        personalDetailsPage = new PersonalDetailsPageElements();
        pimPage = new PIMPageElements();
    }

}