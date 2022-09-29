package TestRunner;

import Pages.Registration;
import SetUp.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RegistrationRunner extends Setup {
    Registration regPage;

    //create constructor of class
    public RegistrationRunner() {
        super();
    }


    @BeforeMethod
    public void RegistrationPage() {
        initilazation();
        regPage = new Registration(driver);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void registrationTitleTest() throws Exception {
        regPage.validateRegistrationPageTitle();
        regPage.fillUpForm(prop.getProperty("FNAME"), prop.getProperty("LNAME"));
        regPage.randomDynamicEmail();
        Thread.sleep(3000);
        regPage.setComName(prop.getProperty("COMNAME"));
        regPage.setStatusNewsletter();
        Thread.sleep(3000);
        regPage.setPassword(prop.getProperty("PWD"), prop.getProperty("CONPWD"));
        Thread.sleep(3000);
       regPage.clickRegisterBtn();
        Thread.sleep(7000);
        String expectedMesg=prop.getProperty("REGSUCCESSMESG");
        WebElement mesgtest= driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]"));
        String test=mesgtest.getText();
        Assert.assertTrue(expectedMesg.contains(test));


    }

}