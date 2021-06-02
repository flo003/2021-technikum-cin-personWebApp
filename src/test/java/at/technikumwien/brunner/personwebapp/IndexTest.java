package at.technikumwien.brunner.personwebapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

// e2e test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Tag("extended")
public class IndexTest {

    @LocalServerPort
    private int port;

    private WebDriver webDriver;
    private Wait<WebDriver> wait;

    @BeforeAll
    public static void setupBeforeClass(){
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
    }

    @BeforeEach
    public void setup(){
        webDriver = new ChromeDriver( new ChromeOptions()
                .setHeadless(true) // browser without GUI
        );
        webDriver.manage().timeouts() .implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, 3);
    }

    @AfterEach
    public void teardown(){
        webDriver.quit();
    }

    @Test
    public void testIndex(){
        webDriver.get(getUrl(""));
        var button = webDriver.findElement(By.tagName("button"));
        var lis = webDriver.findElements(By.tagName("li"));
        assertEquals("alle Personen anzeigen", button.getText());
        assertEquals(2, lis.size());
        button.submit();
        wait.until(ExpectedConditions.urlToBe(getUrl("?all=true")));
        lis = webDriver.findElements(By.tagName("li"));
        assertEquals(4, lis.size());
    }

    @Test
    public void testIndexAllPerson(){
        webDriver.get(getUrl("?all=true"));
        var button = webDriver.findElement(By.tagName("button"));
        var lis = webDriver.findElements(By.tagName("li"));
        assertEquals("nur aktivierte Personen anzeigen", button.getText());
        assertEquals(4, lis.size());
        button.submit();
        wait.until(ExpectedConditions.urlToBe(getUrl("?")));
        lis = webDriver.findElements(By.tagName("li"));
        assertEquals(2, lis.size());
    }

    private String getUrl(String path){
        return "http://localhost:%d/%s".formatted(port, path);
    }
}
