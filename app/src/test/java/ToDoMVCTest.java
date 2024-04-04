import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoMVCTest {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://todomvc.com/");
        takeScreenshot(driver, "TestScreenshots/ToDoMVCTest/homepage.png");
    }

    @Test
    void testHomepageTitleContainerToDoMVC(){
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("TodoMVC"));
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();}



    public static void takeScreenshot(WebDriver webdriver,String desiredPath) throws Exception{
        TakesScreenshot screenshot = ((TakesScreenshot)webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }
}
