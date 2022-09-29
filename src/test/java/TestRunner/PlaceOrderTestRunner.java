package TestRunner;

import Pages.PlaceOrder;
import Pages.Registration;
import SetUp.Setup;
import Utils.TimeWait;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;


public class PlaceOrderTestRunner extends Setup {
    PlaceOrder placeorderPage;
    WebDriver wait;

    //create constructor of class
    public PlaceOrderTestRunner() {
        super();
    }


    @BeforeMethod
    public void RegistrationPage() {
        initilazation();
        placeorderPage = new PlaceOrder(driver);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void PlaceOrderPageTest() throws Exception {

        placeorderPage.clickElectronics();
        Thread.sleep(4000);
        placeorderPage.setProduct();
        Thread.sleep(2000);
        String expectedMesg=prop.getProperty("CELLPHONELANDINGPAGE");
        WebElement mesgtest= driver.findElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"));
        String test=mesgtest.getText();
        Assert.assertTrue(expectedMesg.contains(test));

        //Add to Cart
        placeorderPage.addCart();
        Thread.sleep(2000);

        //shopping cart
        placeorderPage.shoppingCart();
        String shopCart=prop.getProperty("SHOPCART");
        WebElement scart= driver.findElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        String match=scart.getText();
        Assert.assertTrue(shopCart.contains(match));

        //checkout
        placeorderPage.clickCheckOut();
        Thread.sleep(2000);
        String welcome=prop.getProperty("CHECKOUT");
        WebElement checkoutPage= driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        String checkGuesPage=checkoutPage.getText();
        Assert.assertTrue(welcome.contains(checkGuesPage));

        //Guest Check Out
        placeorderPage.guestCheckOut();
        Thread.sleep(3000);

        //checkoutasGuest
        placeorderPage.randomDynamicEmail();
        placeorderPage.billingForm(prop.getProperty("BILL_FNAME"), prop.getProperty("BILL_LNAME"),prop.getProperty("BILL_CITY"),prop.getProperty("BILL_ADD"),prop.getProperty("BILL_ZIP"),prop.getProperty("BILL_PHONE"));
        Thread.sleep(3000);

        //Billing Continue button
       placeorderPage.clickBillingContinueBtn();
       Thread.sleep(3000);

       //shippnng method
        placeorderPage.shippingMethod();
        Thread.sleep(4000);

        //Payment method
        placeorderPage.paymentMethod();
        Thread.sleep(4000);

        //Payment info
        placeorderPage.paymentInfo(prop.getProperty("CARDHOLDERNAME"),prop.getProperty("CARDNUMBER"),prop.getProperty("CARDCODE"),prop.getProperty("EXPMONTH"),prop.getProperty("EXPYEAR"));
        Thread.sleep(4000);
        String billing="Billing Address";
        WebElement billingText=driver.findElement(By.xpath("//div/strong[contains(text(),'Billing Address')]"));
        String binfo=billingText.getText();
        Assert.assertTrue(billing.contains(binfo));

        String subtotal="Sub-Total:";
        WebElement subtotalText=driver.findElement(By.xpath("//tr[1]//td[1]/label[contains(text(),'Sub-Total:')]"));
        String subInfo=subtotalText.getText();
        Assert.assertTrue(subtotal.contains(subInfo));

        //Confirm Place Order
        placeorderPage.confirmOrder();
        Thread.sleep(4000);
//       // String confirmMesg="TYour order has been successfully processed!";
//       // WebElement confrimSuccessMesg=driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[1]/h1"));
//        //WebElement confirmSucessMesg=driver.findElement(By.xpath("//div[@class=\"master-wrapper-page\"]/div[3]/div/child::div/child::div/child::div[2]/div[1]/child::div/strong"));
// try{
//      confrimSuccessMesg=driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[1]/h1"));
// }catch(StaleElementReferenceException e){
//     confrimSuccessMesg=driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[1]/h1"));
//     String matchConfirmMesg=confrimSuccessMesg.getText();
//     Assert.assertTrue(confirmMesg.contains(matchConfirmMesg));
//        }
//        //WebElement confrimSuccessMesg=driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[1]/h1"));
//        //String matchConfirmMesg=confrimSuccessMesg.getText();
//        //Assert.assertTrue(confirmMesg.contains(matchConfirmMesg));


    }

}