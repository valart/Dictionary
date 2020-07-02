import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TranslateTest {

    private static WebDriver driver;
    String baseUrl = "http://localhost:8080/";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Artjom\\Desktop\\Dictionary\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void translateEnglishWord(){
        driver.findElement(By.tagName("input")).sendKeys("orange");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[2]/button/div/div")).click();
        List<WebElement> translation = driver.findElements(By.className("translation"));
        List<String> answers = new ArrayList<String>();
        for (WebElement element : translation) {
            answers.add(element.getText());
        }
        boolean apelsin = answers.contains("apelsin");
        boolean oranz = answers.contains("oranž");
        Assert.assertTrue(apelsin);
        Assert.assertTrue(oranz);
    }

    @Test
    public void translateEstonianWord(){
        driver.findElement(By.tagName("input")).sendKeys("tee");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div/button/div/div/i")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[2]/button/div/div")).click();
        List<WebElement> translation = driver.findElements(By.className("translation"));
        List<String> answers = new ArrayList<String>();
        for (WebElement element : translation) {
            answers.add(element.getText());
        }
        boolean tea = answers.contains("tea");
        boolean road = answers.contains("road");
        boolean way = answers.contains("way");
        Assert.assertTrue(tea);
        Assert.assertTrue(road);
        Assert.assertTrue(way);
    }

    @Test
    public void checkLanguageSwipe() {
        String englishTitle = driver.findElement(By.tagName("h1")).getText();
        String englishButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[2]/button/div/div")).getText();
        String englishLabel = driver.findElement(By.tagName("label")).getText();
        Assert.assertEquals("DICTIONARY",englishTitle);
        Assert.assertEquals("TRANSLATE", englishButton);
        Assert.assertEquals("Type a word", englishLabel);

        //CLICK CHANGE BUTTON
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div/button/div/div/i")).click();

        String eestiTitle = driver.findElement(By.tagName("h1")).getText();
        String eestiButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[2]/button/div/div")).getText();
        String eestiLabel = driver.findElement(By.tagName("label")).getText();
        Assert.assertEquals("SÕNASTIK",eestiTitle);
        Assert.assertEquals("TÕLGI", eestiButton);
        Assert.assertEquals("Sisestage sõna", eestiLabel);
    }

    @Test
    public void emptyInput() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[2]/button/div/div")).click();
        Thread.sleep(1500);
        String title = driver.findElement(By.xpath("/html/body/div[2]")).getText();
        Assert.assertEquals("Check typed form of word (no marks, no spaces, not empty)", title);
    }
}
