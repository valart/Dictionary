import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class AddingTest {

    private static WebDriver driver;
    String baseUrl = "http://localhost:8080/";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Artjom\\Desktop\\Dictionary\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        driver.findElement(By.xpath("/html/body/div/div[1]/button[2]/div/div")).click();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void checkLanguageSwipe() {
        String englishTitle = driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/h1")).getText();
        String englishButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/button/div")).getText();
        String upperLabel = driver.findElements(By.tagName("label")).get(1).getText();
        String lowerLabel = driver.findElements(By.tagName("label")).get(2).getText();
        Assert.assertEquals("ADDING NEW WORDS",englishTitle);
        Assert.assertEquals("ADD", englishButton);
        Assert.assertEquals("ENGLISH", upperLabel);
        Assert.assertEquals("ESTONIA", lowerLabel);

        //CHANGE LANGUAGE
        driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/div/div/button/div/div/i")).click();

        String estoniaTitle = driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/h1")).getText();
        String estoniaButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/button/div")).getText();
        upperLabel = driver.findElements(By.tagName("label")).get(1).getText();
        lowerLabel = driver.findElements(By.tagName("label")).get(2).getText();
        Assert.assertEquals("UUTE SÕNADE LISAMINE",estoniaTitle);
        Assert.assertEquals("LISA", estoniaButton);
        Assert.assertEquals("ENGLISH", lowerLabel);
        Assert.assertEquals("ESTONIA", upperLabel);
    }

    @Test
    public void emptyInputs() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/button/div")).click();
        Thread.sleep(1000);
        String oopsTitle = driver.findElement(By.tagName("span")).getText();
        Assert.assertEquals("Oops!", oopsTitle);
    }

}
