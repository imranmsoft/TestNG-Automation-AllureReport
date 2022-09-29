package Pages;

import Utils.TimeWait;
import org.apache.commons.lang3.RandomStringUtils;
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
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class PlaceOrder {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css="img")
    List<WebElement> imgProducts;
    @FindBy(xpath="//button[normalize-space()='Add to cart']")
    WebElement btnAddCart;
    @FindBy(xpath ="//div[@role='alert']//a[@class='button wc-forward'][normalize-space()='View cart']")
    WebElement viewCart;
    @FindBy(xpath="//a[normalize-space()='Proceed to checkout']")
    WebElement checkOut;
    @FindBy(xpath ="//select[@name='product_cat']")
    WebElement catDropdown;
    @FindBy(xpath="//i[@class='fa fa-search']")
    WebElement searchBtnIcon;

    @FindBy(xpath="//ul[1]/li[2]/a[contains(text(),'Electronics')]")
    WebElement elect;
    @FindBy(xpath = "//a[contains(text(),'Nokia Lumia 1020')]")
    WebElement nokia;
    @FindBy(xpath="//input[@id='product_enteredQuantity_20']")
    WebElement quantity;
    @FindBy(xpath="//button[@id=\"add-to-cart-button-20\"]")
    WebElement addCart;
    @FindBy(xpath="//span[contains(text(),'Shopping cart')]")
    WebElement shopCart;
    @FindBy(xpath="//div[@class='terms-of-service']/input[@type='checkbox']")
    WebElement agreeCheckbox;
    @FindBy(xpath="//button[@id='checkout']")
    WebElement chkBtn;
    @FindBy(xpath ="//button[@class='button-1 checkout-as-guest-button']")
    WebElement guestCheckOut;
    @FindBy(xpath="//input[@id='BillingNewAddress_FirstName']")
    WebElement billingfName;
    @FindBy(xpath="//input[@id='BillingNewAddress_LastName']")
    WebElement billinglName;
    @FindBy(xpath="//input[@id='BillingNewAddress_Email']")
    WebElement billingEmail;
    @FindBy(xpath="//select[@id='BillingNewAddress_CountryId']")
    WebElement billingCountrydrpdown;
    @FindBy(xpath="//input[@id='BillingNewAddress_City']")
    WebElement billingCity;
    @FindBy(xpath="//input[@id='BillingNewAddress_Address1']")
    WebElement billingAdd_1;
    @FindBy(xpath="//input[@id='BillingNewAddress_ZipPostalCode']")
    WebElement billingZip;
    @FindBy(xpath="//input[@id='BillingNewAddress_PhoneNumber']")
    WebElement billingPhone;
    @FindBy(xpath="//input[@id='shippingoption_1' and @value='Next Day Air___Shipping.FixedByWeightByTotal']")
    WebElement nextAir;
    @FindBy(xpath = "//div[@class='payment-details']/input[@id='paymentmethod_1']/following::label[contains(text(),'Credit Card')]")
    WebElement creditCard;
    @FindBy(xpath="//input[@id='CardholderName']")
    WebElement cardHolder;
    @FindBy(xpath = "//input[@id='CardNumber']")
    WebElement carNo;
    @FindBy(xpath = "//input[@id='CardCode']")
    WebElement cardCode;
    @FindBy(xpath="//select[@id='ExpireMonth']")
    WebElement expMonth;
    @FindBy(xpath="//select[@id='ExpireYear']")
    WebElement expYear;
    public PlaceOrder(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void clickElectronics() throws InterruptedException {
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='adaptive-frame-wrapper']/iframe")));
        elect.click();
        WebElement hoverElement = driver.findElement(By.xpath("//ul[1]/li[2]/ul/li[2]/a"));

        // hover Operation
        Actions action = new Actions(driver);
        action.moveToElement(hoverElement).perform();
        System.out.println("HOVER --------------> FInished ");
        action.doubleClick(hoverElement).perform();
        wait=new WebDriverWait(driver, Duration.ofSeconds(TimeWait.PAGE_LOAD_TIMEOUT));
        Thread.sleep(4000);

    }

    public void setProduct() throws InterruptedException {
        nokia.click();
        quantity.clear();
        Thread.sleep(3000);
        quantity.sendKeys("2");


    }

    public void addCart() throws InterruptedException {
        addCart.click();
        Thread.sleep(3000);
        wait=new WebDriverWait(driver, Duration.ofSeconds(TimeWait.PAGE_LOAD_TIMEOUT));

    }
    public void shoppingCart() throws InterruptedException {
        shopCart.click();
        Thread.sleep(3000);
        wait=new WebDriverWait(driver, Duration.ofSeconds(TimeWait.PAGE_LOAD_TIMEOUT));

    }

    public void clickCheckOut() throws InterruptedException {
        agreeCheckbox.click();
        Thread.sleep(3000);
        wait=new WebDriverWait(driver, Duration.ofSeconds(TimeWait.PAGE_LOAD_TIMEOUT));
        chkBtn.click();
        Thread.sleep(3000);


    }
    public void guestCheckOut() throws InterruptedException {
        guestCheckOut.click();
        Thread.sleep(3000);
        wait=new WebDriverWait(driver, Duration.ofSeconds(TimeWait.PAGE_LOAD_TIMEOUT));


    }
    public  void randomDynamicEmail() throws InterruptedException {
        String randomEmail ="random-" + RandomStringUtils.random(6, true, true) + "@example.com";
        System.out.println("Dynamic Email :" + randomEmail);
        billingEmail.sendKeys(randomEmail);
        wait=new WebDriverWait(driver, Duration.ofSeconds(TimeWait.PAGE_LOAD_TIMEOUT));

    }
    public void billingForm(String fname, String lName, String city, String address, String zip, String phone) throws InterruptedException {
        WebElement deleteBtn=driver.findElement(By.xpath("//div[@id='billing-buttons-container']/button[contains(text(),'Delete')]"));
        if(deleteBtn.isDisplayed()){
            deleteBtn.click();
            Thread.sleep(3000);
        }else{
            billingfName.sendKeys(fname);
            billinglName.sendKeys(lName);
            Thread.sleep(4000);
            billingCity.sendKeys(city);
            billingAdd_1.sendKeys(address);
            billingZip.sendKeys(zip);
            billingPhone.sendKeys(phone);
            Thread.sleep(4000);
            Select select=new Select(billingCountrydrpdown);
            Thread.sleep(4000);
            select.selectByVisibleText("Bangladesh");
        }


    }
    //use of javaScript Executor
    public void clickBillingContinueBtn() throws Exception {
        WebElement continueBtn=driver.findElement(By.xpath("//div[@id='billing-buttons-container']/button[@name='save']"));
        continueBtn.click();
        WebElement l = driver.findElement(By.xpath("//div[@id='billing-buttons-container']/button[@name='save']"));
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", l);

    }

    public void shippingMethod() throws InterruptedException {
        Thread.sleep(6000);
        nextAir.click();
        Thread.sleep(3000);
       WebElement l = driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']/button[contains(text(),'Continue')]"));
        new WebDriverWait(driver,Duration.ofMillis(30));
       l.click();
        JavascriptExecutor j = (JavascriptExecutor) driver;
       j.executeScript("arguments[0].click();", l);
        System.out.println("Finally shipping method click");

    }

    public void paymentMethod() throws InterruptedException {
        creditCard.click();
        Thread.sleep(3000);
        WebElement paycont = driver.findElement(By.xpath("//div[@id='payment-method-buttons-container']/button[contains(text(),'Continue')]"));
        new WebDriverWait(driver,Duration.ofMillis(30));
        paycont.click();
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", paycont);
        System.out.println("Finally payment method click");
    }

    public void paymentInfo(String name, String carno, String code,String exm, String exy){

        cardHolder.sendKeys(name);
        carNo.sendKeys(carno);
        cardCode.sendKeys(code);
        Select selectmonth=new Select(expMonth);
        selectmonth.selectByVisibleText(exm);
        Select selectyear=new Select(expYear);
        selectyear.selectByVisibleText(exy);
        WebElement payInfo = driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']/button[contains(text(),'Continue')]"));
        new WebDriverWait(driver,Duration.ofMillis(30));
        payInfo.click();
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", payInfo);
        System.out.println("Finally payment method click");

    }

    public void confirmOrder() throws InterruptedException {
        WebElement confirm = driver.findElement(By.xpath("//div[@id='confirm-order-buttons-container']/button[contains(text(),'Confirm')]"));
       // new WebDriverWait(driver,Duration.ofMillis(30));
        //confirm.click();
        Thread.sleep(5000);
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", confirm);
        System.out.println("Finally payment method click");
        Thread.sleep(10000);
        String windowHandle = driver.getWindowHandle(); // save the original window handle
        driver.switchTo().window(windowHandle);
        System.out.println("confirm button");
//        WebElement confrimSuccessMesg=driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[1]/h1"));
//        wait.until(ExpectedConditions.visibilityOf(confrimSuccessMesg));


    }
}
