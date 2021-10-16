package gmailTests;


import gmailPages.Login;
import gmailPages.SendMessage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;
import java.util.concurrent.TimeUnit;

//@Epic("Gmail")
//@Story("Story")
//@Feature("Feature")
public class LoginTests {

    WebDriver webDriver;
    Login login;
    SendMessage sendMessage;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Airis\\IdeaProjects\\SimbirSoftTestTask\\src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        login = new Login(webDriver);
        sendMessage = new SendMessage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.google.com/intl/ru/gmail/about/");
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

//    @Step("Авторизация и отправка письма")
    @Test
    public void loginAuthorizationTests() throws InterruptedException {
        login.signIn();
        login.inputMail("rkhamidullin3@gmail.com");
        login.emailFurther();
        login.inputPassword("userpass");
        login.pwdFurther();
        Thread.sleep(5000);
        Assertions.assertEquals("https://mail.google.com/mail/u/0/#inbox", webDriver.getCurrentUrl());
        List<WebElement> count = webDriver.findElements(By.xpath("//*[@class='bog']//span[contains(text(), 'Simbirsoft')]"));
        int countOfMessages = count.size();
        Assertions.assertNotNull(countOfMessages);
        sendMessage.writeMessage();
        sendMessage.inputRecipient();
        sendMessage.inputMessageSubject();
        webDriver.findElement(By.xpath("//*[@class='Am Al editable LW-avf tS-tW']")).sendKeys(String.valueOf(countOfMessages));
        sendMessage.send();
        Thread.sleep(3000);
        List<WebElement> countAfterSend = webDriver.findElements(By.xpath("//*[@class='bog']//span[contains(text(), 'Simbirsoft')]"));
        int countOfMessagesAfterSend = countAfterSend.size();
        Assertions.assertEquals(countOfMessages+1,countOfMessagesAfterSend);
    }
}
