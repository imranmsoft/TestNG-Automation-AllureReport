package Pages;

import Utils.Utils;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;
import java.util.UUID;

import java.util.Random;
import Utils.TimeWait;


public class Registration {
    public WebDriver driver;
    WebDriverWait wait;
    //Utils utils=new Utils(driver);
    Faker faker = new Faker();

    @FindBy(xpath = "//a[contains(text(),'Register')]")
    WebElement registerLink;
    @FindBy(xpath = "//input[@id='FirstName']")
    WebElement fName;
    @FindBy(xpath="//input[@id='LastName']")
    WebElement lName;
    @FindBy(xpath = "//input[@id='Email']")
    WebElement emailTest;
    @FindBy(xpath="//span[@class='male']")
    WebElement gender;
    @FindBy(xpath="//select[@name='DateOfBirthDay']")
    WebElement dayDropDown;
    @FindBy(xpath="//select[@name='DateOfBirthMonth']")
    WebElement monthDropDown;
    @FindBy(xpath="//select[@name='DateOfBirthYear']")
    WebElement yearDropDown;
    @FindBy(xpath = "//input[@id='Company']")
    WebElement comName;
    @FindBy(xpath="//input[@id='Password']")
    WebElement pwd;
    @FindBy(xpath="//input[@id='ConfirmPassword']")
    WebElement conPwd;
    @FindBy(xpath="//input[@id='Newsletter']")
    WebElement newsletr;
    @FindBy(xpath="//*[@id=\"register-button\"]")
    WebElement regBtn;
    @FindBy(xpath="//div[contains(text(),'Your registration completed')]")
    WebElement regSuccessMesg;
    //implementing Page Factory by using Constructor "login"

    public Registration(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }


    public void validateRegistrationPageTitle() {
       driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='adaptive-frame-wrapper']/iframe")));
        //utils.switchtoFrame();
        registerLink.click();

    }


    //dynamic email address
    public  void randomDynamicEmail() throws InterruptedException {
        String randomEmail ="random-" + RandomStringUtils.random(6, true, true) + "@example.com";
        System.out.println("Dynamic Email :" + randomEmail);
        emailTest.sendKeys(randomEmail);
        wait=new WebDriverWait(driver, Duration.ofSeconds(TimeWait.PAGE_LOAD_TIMEOUT));

    }
    public void fillUpForm(String fn, String ln) throws InterruptedException {
      gender.click();
      wait=new WebDriverWait(driver, Duration.ofSeconds(TimeWait.PAGE_LOAD_TIMEOUT));
     // wait.until(ExpectedConditions.elementToBeClickable(randomDynamicEmail));
      fName.sendKeys(fn);
      lName.sendKeys(ln);
      Select selectday=new Select(dayDropDown);
      selectday.selectByValue("5");
      Select selectmonth=new Select(monthDropDown);
      selectmonth.selectByValue("6");
      Select selectyear=new Select(yearDropDown);
      selectyear.selectByValue("1990");
      wait=new WebDriverWait(driver, Duration.ofSeconds(TimeWait.PAGE_LOAD_TIMEOUT));


    }

    public void setComName(String cName){
        comName.sendKeys(cName);
    }

      public void setStatusNewsletter(){
          if(!newsletr.isSelected())
              newsletr.click();


      }
    public void setPassword(String paswd, String conpswd){
        pwd.sendKeys(paswd);
        conPwd.sendKeys(conpswd);
    }

    //use of Action Class
    public void clickRegisterBtn() throws Exception {
        System.out.println("after frame");
        Actions action = new Actions(driver);
       WebElement jsElement=driver.findElement(By.xpath("//*[@id=\"register-button\"]"));

        action.doubleClick(jsElement).perform();
        System.out.println("Finally click");

    }


}