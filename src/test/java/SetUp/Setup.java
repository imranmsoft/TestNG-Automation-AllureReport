package SetUp;

import Utils.Utils;
import jdk.jshell.execution.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Setup {
    public static WebDriver driver;
    public static Properties prop;

    @BeforeTest()  // anotation use
    public void setUp() throws IOException {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void initilazation() {
        String browserName=prop.getProperty("browser");
        if(browserName.equals("chrome")) {
            // windows
            //System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
            //linux
            System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver_linux64/chromedriver");
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--headed");

            driver = new ChromeDriver(ops);
            driver.manage().window().maximize();
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
           // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


            //read url
            driver.get(prop.getProperty("url"));

        }
    }

    //screnshot
    @AfterMethod
    public void screenShot(ITestResult result){
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                Utils util=new Utils(driver);
                util.takeScreenShot();
            }catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
    }
    @AfterTest  //sob kaj ses hole then use hobe
    public void logout(){
        //driver.close();
    }
}

