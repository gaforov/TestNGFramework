package com.hrm.utils;

public class Constants {
    /**
     * Note: Path From Content Root should be added to "user.dir". In IntelliJ right click 'Copy Path/Reference' then
     * choose from Content Root. In this project it starts here: src/test/resources/configs/configuration.properties
     * Tip: if path is correct, in the Run Console it will turn blue like clickable link, click it and takes you to path.
     */
    public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver";
    public static final String GECKO_DRIVER_PATH = System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver";
    public static final String CONFIGURATION_FILEPATH = System.getProperty("user.dir") + "/src/test/resources/configs/configuration.properties";
    public static final String TESTDATA_FILEPATH = System.getProperty("user.dir") + "/src/test/resources/testdata/HrmTestData.xlsx";
    public static final String REPORT_FILEPATH = System.getProperty("user.dir") + "/target/html-report/HRM.html";

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int EXPLICIT_WAIT_TIME = 15;

}
